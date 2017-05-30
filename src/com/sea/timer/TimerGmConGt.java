package com.sea.timer;

import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;

import com.bowlong.util.DateEx;
import com.sea.content.Svc;
import com.sea.handler.game.tcpGate.GGTClientBootstrap;
import com.sea.tools.UtileTools;

public class TimerGmConGt implements Runnable {

	static Log log = Svc.getLog(TimerGmConGt.class);

	static ScheduledExecutorService SES = Svc.newScheduledThreadPool(
			"GameConnectGate", 2);

	static TimerGmConGt _self;

	private TimerGmConGt() {
	}

	public static TimerGmConGt getInstance() {
		if (_self == null) {
			_self = new TimerGmConGt();
		}
		return _self;
	}

	public static void startTimer() {
		TimerGmConGt t = getInstance();
		try {
			t.exceMethod();
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}

		long time = DateEx.TIME_SECOND;
		long vt = time * 10;
		//
		Svc.scheduled8FixedRate(SES, t, vt, vt);
	}

	private void exceMethod() throws Exception {
		GGTClientBootstrap.getInstanse().timerToConnect();
	}

	@Override
	public void run() {
		try {
			exceMethod();
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}
}
