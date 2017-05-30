package com.sea.timer;

import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;

import com.bowlong.util.DateEx;
import com.sea.content.Svc;
import com.sea.logic.logicOperate.LogicAtomicInteger;
import com.sea.logic.session.LogicalSession;
import com.sea.tools.UtileTools;

@SuppressWarnings("all")
public class TimerDownLine implements Runnable {

	static Log log = Svc.getLog(TimerDownLine.class);

	static ScheduledExecutorService SES = Svc.newScheduledThreadPool(
			"TimerDownLine", 2);

	static long Timer_Repeat = 1 * 60 * DateEx.TIME_SECOND;

	static TimerDownLine _self;

	private TimerDownLine() {
	}

	public static TimerDownLine getInstance() {
		if (_self == null) {
			_self = new TimerDownLine();
		}
		return _self;
	}

	public static void startTimer() {
		TimerDownLine t = getInstance();
		Svc.scheduled8FixedRate(SES, t, Timer_Repeat, Timer_Repeat);
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
		LogicalSession.downLine();
		resetSessionAutoId();
	}

	static public void downLineAllWhenShutDown() {
		LogicalSession.downLineAll();
	}

	static public void initSessionAutoId() {
		LogicalSession.setAtomicId(300);
	}

	static public void resetSessionAutoId() {
		long v = LogicalSession.getAtidVal();
		int max = 59999999;
		if (v > max) {
			initSessionAutoId();
		}
	}
}
