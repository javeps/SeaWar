package com.sea.timer;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;

import com.sea.cache.jedis.GameDelJedis;
import com.sea.cache.jedis.game.PVJedis;
import com.sea.cache.jedis.game._GameKeys;
import com.sea.cache.process.ProAssistFight;
import com.sea.content.Svc;
import com.sea.db.entity.Rank_clanEntity;
import com.sea.db.entity.Rank_playerEntity;
import com.sea.db.entity.RewardEntity;
import com.sea.tools.UtileTools;

public class TimerDay implements Runnable {

	static Log log = Svc.getLog(TimerDay.class);

	static ScheduledExecutorService SES = Svc.newScheduledThreadPool(
			"TimerDay", 2);

	static TimerDay _self;

	private TimerDay() {
	}

	public static TimerDay getInstance() {
		if (_self == null) {
			_self = new TimerDay();
		}
		return _self;
	}

	public static void startTimer() {
		TimerDay t = getInstance();
		// 每天执行(晚上4：15执行)
		Svc.scheduledEveryDay(SES, t, 4, 15, 0);
	}

	private void exceMethod() throws Exception {
		Date date = new Date();
		delMail(date);
		delReward(date);
		delRank(date);
		delNPCEnergy();
		delPv(date);
	}

	private void delMail(Date date) {
		GameDelJedis.delMail(date);
	}

	private void delReward(Date date) {
		List<String> timeList = UtileTools.getDelTimerList(date, 5);
		RewardEntity.deleteRewardTable(timeList);
	}

	private void delRank(Date date) {
		List<String> timeList = UtileTools.getDelWeekTimerList(date);
		Rank_playerEntity.deleteRPTable(timeList);
		Rank_clanEntity.deleteRCTable(timeList);
	}

	void delNPCEnergy() {
		ProAssistFight.delNPCEnergyVal();
	}

	private void delPv(Date date) {
		List<String> timeList = UtileTools.getDelTimerList(date, 5);
		PVJedis.delPV(timeList);
	}

	@Override
	public void run() {
		try {
			exceMethod();
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}

	// 删除paramt
	static public void delAllPattern() {
		String[] pattes = new String[] { _GameKeys.Pattern_Key_Mail_Att_Day,
				_GameKeys.Pattern_Key_Mail_Att_Bepcid,
				_GameKeys.Pattern_Key_Mail_Att_Pinfo,
				_GameKeys.Pattern_Key_Mail_Pl_Day,
				_GameKeys.Pattern_Key_Mail_Pl_ToPcid,
				_GameKeys.Pattern_Key_Mail_Pl_Type,
				_GameKeys.Pattern_Key_PRenown, _GameKeys.Pattern_Key_Reward };
		GameDelJedis.delAllByPatternKey(null, pattes);
	}

	// 删除mail
	static public void delMailAndChatPattern() {
		String[] pattes = new String[] { _GameKeys.Pattern_Key_Mail_Att_Day,
				_GameKeys.Pattern_Key_Mail_Att_Bepcid,
				_GameKeys.Pattern_Key_Mail_Att_Pinfo,
				_GameKeys.Pattern_Key_Mail_Pl_Day,
				_GameKeys.Pattern_Key_Mail_Pl_ToPcid,
				_GameKeys.Pattern_Key_Mail_Pl_Type, _GameKeys.Pattern_Key_Chat };
		GameDelJedis.delAllByPatternKey(null, pattes);
	}
}
