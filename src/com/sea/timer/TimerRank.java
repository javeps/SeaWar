package com.sea.timer;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;

import redis.clients.jedis.Jedis;

import com.bowlong.util.DateEx;
import com.sea.cache.jedis.DCfgJedis;
import com.sea.cache.jedis.GameDelJedis;
import com.sea.cache.jedis.game.AssistUserPlayerJedis;
import com.sea.cache.jedis.game.UserJedis;
import com.sea.cache.jedis.game._GameKeys;
import com.sea.cache.process.ProPlayer;
import com.sea.content.AppContext;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.User;
import com.sea.db.entity.ClanEntity;
import com.sea.db.entity.Rank_clanEntity;
import com.sea.db.entity.Rank_playerEntity;
import com.sea.json.originData.CfgJson;
import com.sea.logic.logicOperate.LogicClan;
import com.sea.logic.logicOperate.LogicPlayer;
import com.sea.logic.logicOperate.LogicReward;
import com.sea.logic.session.LogicalSession;
import com.sea.tools.UtileTools;

public class TimerRank implements Runnable {

	static Log log = Svc.getLog(TimerRank.class);

	static ScheduledExecutorService SES = Svc.newScheduledThreadPool(
			"TimerRank", 2);

	static TimerRank _self;

	static int Time_Delay = 1;
	// 每隔几周发奖【越南服务器每周得将】
	static int ever_week = 2;

	private TimerRank() {
	}

	public static TimerRank getInstance() {
		if (_self == null) {
			_self = new TimerRank();
		}
		return _self;
	}

	public static void startTimer() {

		DCfgJedis.resetRankWhenStart(ever_week);

		TimerRank t = getInstance();
		// 每天执行
		// Svc.scheduledEveryDay(SES, t, 0, 1, 1);

		long Time_Hour = DateEx.TIME_HOUR;
		long Time_Minute = DateEx.TIME_MINUTE;
		long Time_Second = DateEx.TIME_SECOND;

		Date now_date = new Date();
		int h = DateEx.hour(now_date);
		int m = DateEx.minute(now_date);
		int s = DateEx.second(now_date);

		long curTime = h * Time_Hour + m * Time_Minute + s * Time_Second;

		int nextH = ((h / Time_Delay) + 0) * Time_Delay;

		long initialDelay = nextH * Time_Hour - curTime + 30 * Time_Minute;
		long delay = Time_Delay * Time_Hour;

		while (initialDelay <= 0) {
			initialDelay += delay;
		}

		Svc.scheduled8FixedRate(SES, t, initialDelay, delay);
	}

	private void exceMethod() throws Exception {
		Date date = new Date();
		recordRank(date, false);
		sendRewardRankPlayer(date);
	}

	static private long sendRewardTime = 0l;

	static public void sendRewardRankPlayer(Date date) {
		long time = date.getTime();
		if (time > sendRewardTime) {
			sendRewardTime = DCfgJedis.getRankReward();
		}

		boolean isCanSend = time > sendRewardTime;

		if (!isCanSend) {
			long diffMax = DateEx.TIME_MINUTE * 10;
			long diffMin = DateEx.TIME_SECOND * 20;
			long max = sendRewardTime + diffMax;
			long min = sendRewardTime - diffMin;
			isCanSend = time > min && time < max;
		}

		sendRewardRankPlayer(date, isCanSend);
	}

	static public void sendRewardRankPlayer(Date date, boolean isCanSend) {
		if (isCanSend) {
			sendRewardTime += ever_week * DateEx.TIME_WEEK;// 每 两 周

			boolean isNewReward = CfgJson.isNewReward();

			DCfgJedis.changeRankReward(ever_week);// 每 两 周
			sendReward(date, isNewReward);
		}
	}

	/*** 发奖 **/
	static public void sendReward(Date date, boolean isNewReward) {
		String strTime = Svc.tFmt(date);
		log.info("== 发放个人上升排行奖励  == :" + strTime);
		LogicReward.sendRewardRankWeekPlayer();
		try {
			if (isNewReward) {
				log.info("== 发放个人总排行奖励  == :" + strTime);
				LogicReward.sendRewardRankAllPlayer();
				log.info("== 发放联盟上升排行奖励  == :" + strTime);
				LogicReward.sendRewardRankWeekClan();
			}
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		synchronized (LogicalSession.mapSid2Session) {
			clearAllWeek();
			// 清楚周排行分数后，清空了ProRenown所以得重新加载
			// Player_renownEntity.getListAll(true);
		}
	}

	public void recordRank(Date date, boolean isInit) {
		boolean isReset_player = false;
		boolean isReset_clan = false;
		if (date != null) {
			int h = DateEx.hour(date);
			switch (h) {
			case 9:
			case 21:
				isReset_player = true;
				isReset_clan = true;
				break;
			default:
				break;
			}
		}

		if (isInit) {
			isReset_player = true;
			isReset_clan = true;
		}
		Rank_playerEntity.recordRank(100, isReset_player);
		Rank_clanEntity.recordRank(100, isReset_clan);
	}

	@Override
	public void run() {
		try {
			exceMethod();
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}

	// 将用户表数据更新到renown(辅助表)
	public static void repairByPlayer() {

		delPRenown();

		ProPlayer.delPlayers(100);
		List<Player> origin = ProPlayer.getListAll();
		AssistUserPlayerJedis.resetListAll(origin);
	}

	static public void repairByUser() {
		List<User> list = UserJedis.getListAllByNew();
		UserJedis.resetAssistUser(list);
	}

	static void delPRenown() {
		// 删除以前的数据
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			GameDelJedis.delAllByPatternKey(jedis,
					_GameKeys.Pattern_Key_PRenown);
			GameDelJedis.delAllByKeys(jedis.pipelined(), _GameKeys.Key_PRenown);
		} catch (Exception e) {
		} finally {
			AppContext.returnJedis(jedis);
		}
	}

	void rebackClanDonate() {
		ClanEntity.resetClanDonate();
	}

	static public void clearAllWeek() {
		// 此处修改数据是从redis里面读取出来的
		LogicPlayer.clearWeekRenown();
		LogicClan.clearClanRenownWeek();
		ProPlayer.savePlInfoData();
	}

	static public void downClanPoint() {
		String ccid = "ccid1185570998";
		LogicClan.clearClanDonate(ccid, 30, 30);
	}
}
