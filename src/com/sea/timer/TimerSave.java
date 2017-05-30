package com.sea.timer;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;

import com.bowlong.util.DateEx;
import com.sea.cache.process.ProAmry;
import com.sea.cache.process.ProBuild;
import com.sea.cache.process.ProClan;
import com.sea.cache.process.ProClanMember;
import com.sea.cache.process.ProClanReq;
import com.sea.cache.process.ProHero;
import com.sea.cache.process.ProObst;
import com.sea.cache.process.ProPlayer;
import com.sea.cache.process.ProProduct;
import com.sea.cache.process.ProTech;
import com.sea.cache.process.ProUser;
import com.sea.content.Svc;
import com.sea.logic.logicOperate.LogicAtomicInteger;
import com.sea.tools.UtileTools;

public class TimerSave implements Runnable {

	static Log log = Svc.getLog(TimerSave.class);

	static ScheduledExecutorService SES = Svc.newScheduledThreadPool(
			"TimerSave", 2);

	static TimerSave _self;

	private TimerSave() {
	}

	public static TimerSave getInstance() {
		if (_self == null) {
			_self = new TimerSave();
		}
		return _self;
	}

	public static void startTimer() {
		TimerSave t = getInstance();

		long Time_Hour = DateEx.TIME_HOUR;
		long Time_Minute = DateEx.TIME_MINUTE;
		long Time_Second = DateEx.TIME_SECOND;

		Date now_date = new Date();

		int h = DateEx.hour(now_date);
		int m = DateEx.minute(now_date);
		int s = DateEx.second(now_date);

		long curTime = h * Time_Hour + m * Time_Minute + s * Time_Second;

		long nextH = Time_Minute;
		// nextH = 0 * Time_Hour + 2 * Time_Minute + 0 * Time_Second;
		nextH = 0 * Time_Hour + 10 * Time_Minute + 0 * Time_Second;

		long initialDelay = nextH - curTime;
		long delay = nextH;

		while (initialDelay <= 0) {
			initialDelay += delay;
		}

		Svc.scheduled8FixedRate(SES, t, initialDelay, delay);
	}

	@Override
	public void run() {
		try {
			exceMethod();
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}

	private void exceMethod() throws Exception {
		System.out.println("====== save ====");
		saveLogicAtomic();
		save();
	}

	void saveLogicAtomic() {
		LogicAtomicInteger.save();
	}

	void save() throws Exception {
		ProUser.saveData();
		ProPlayer.saveData();
		ProBuild.saveData();
		ProObst.saveData();
		ProHero.saveData();
		ProTech.saveData();
		ProAmry.saveData();
		ProProduct.saveData();
		//ProChat.saveData();

		// =======联盟
		ProClan.saveData();
		ProClanMember.saveData();
		ProClanReq.saveData();
	}

	static public void saveAndClear() throws Exception {
		ProUser.clearData();
		ProPlayer.clearData();
		//ProChat.clearData();
	}
}
