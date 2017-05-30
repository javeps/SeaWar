package com.sea.timer;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.apache.commons.logging.Log;

import redis.clients.jedis.Jedis;

import com.bowlong.util.DateEx;
import com.sea.cache.jedis.game.ArmyJedis;
import com.sea.cache.jedis.game.BuildJedis;
import com.sea.cache.jedis.game.HeroJedis;
import com.sea.cache.jedis.game.ObstJedis;
import com.sea.cache.jedis.game.ProductJedis;
import com.sea.cache.jedis.game.TechJedis;
import com.sea.cache.jedis.game._GameKeys;
import com.sea.cache.process.ProPlayer;
import com.sea.content.AppContext;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.tools.UtileTools;

public class TimerChangeNew implements Runnable {

	static Log log = Svc.getLog(TimerChangeNew.class);

	static ScheduledExecutorService SES = Svc.newScheduledThreadPool(
			"TimerDay", 2);

	static ScheduledFuture<?> SF = null;

	static TimerChangeNew _self;

	private TimerChangeNew() {
	}

	public static TimerChangeNew getInstance() {
		if (_self == null) {
			_self = new TimerChangeNew();
		}
		return _self;
	}

	public static void startTimer() {
		TimerChangeNew t = getInstance();
		// 定时执行
		long Time_Hour = DateEx.TIME_HOUR;
		long Time_Minute = DateEx.TIME_MINUTE;
		long Time_Second = DateEx.TIME_SECOND;

		Date now_date = new Date();

		int h = DateEx.hour(now_date);
		int m = DateEx.minute(now_date);
		int s = DateEx.second(now_date);

		long curTime = h * Time_Hour + m * Time_Minute + s * Time_Second;

		long nextH = Time_Minute;
		nextH = 0 * Time_Hour + 30 * Time_Minute + 0 * Time_Second;

		long initialDelay = nextH - curTime;
		long delay = nextH;

		while (initialDelay <= 0) {
			initialDelay += delay;
		}

		SF = Svc.scheduled8FixedRate(SES, t, initialDelay, delay);
	}

	@Override
	public void run() {
		try {
			excuteFun();
			delPlayer();
			// change();
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}

	void excuteFun() {

	}

	/*** 将每天定时删除用户改为，每小时定时删除用户由300个变为每小时30个 **/
	static public void delPlayer() {
		ProPlayer.delPlayers(30);
	}

	int max = 0;
	int cur = 0;
	int size = 15;

	// 删除paramt
	void change() {
		List<Player> list = ProPlayer.getListAll();
		if (Svc.isEmpty(list)) {
			// log.info("=== 改变是playerList 为空了===" + cur);
			return;
		}

		max = list.size();
		int end = cur + size;
		if (cur > max) {
			// if (SF != null)
			// SF.cancel(true);
			// else
			// SES.shutdownNow();
			// log.info("=== 当前值大于了最大值=== cur = " + cur + ",max = " + max);
			return;
		}

		end = end > max ? max : end;

		Jedis jedis = null;
		String str = "";
		try {
			jedis = AppContext.getJedis();
			if (jedis == null) {
				//log.info("=== timer change jedis为空 === cur = " + cur
				//		+ ",max = " + max);
				return;
			}
			for (int i = cur; i < end; i++) {
				if (i > max)
					break;

				Player pl = list.get(i);
				if (pl == null)
					continue;

				int pcid = pl.getPcid();
				String pinfoKey = _GameKeys.fmtPinfoPcid(pcid);
				if (jedis.exists(pinfoKey)) {
					continue;
				}

				ArmyJedis.getListOld(pcid, str);
				ProductJedis.getListOld(pcid, str);
				BuildJedis.getListOld(pcid, str);
				ObstJedis.getListOld(pcid, str);
				TechJedis.getListOld(pcid, str);
				HeroJedis.getListOld(pcid, str);
			}

			cur = end;
			log.info("=== 正常情况 cur = " + cur + ",max = " + max);
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
			AppContext.returnJedisBroken(null, jedis);
		} finally {
			AppContext.returnJedis(jedis);
		}

	}
}
