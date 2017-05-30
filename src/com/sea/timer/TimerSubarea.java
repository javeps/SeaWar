package com.sea.timer;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;

import com.bowlong.util.DateEx;
import com.sea.content.Svc;
import com.sea.logic.logicOperate.LogicSubarea;
import com.sea.tools.UtileTools;

/**
 * 定时分区 处理 谷歌,港澳台渠道 执行时间2天下次更新后不用开启 处理 非谷歌,港澳台渠道 执行时间2天下次更新后不用开启
 * 
 * @author Administrator
 * 
 */
public class TimerSubarea implements Runnable {

	static Log log = Svc.getLog(TimerSubarea.class);

	static ScheduledExecutorService SES = Svc.newScheduledThreadPool(
			"TimerWeek", 2);

	static TimerSubarea _self;

	private TimerSubarea() {
	}

	public static TimerSubarea getInstance() {
		if (_self == null) {
			_self = new TimerSubarea();
		}
		return _self;
	}

	public static void startTimer(boolean isDelChina) {
		TimerSubarea t = getInstance();
		long Time_Hour = DateEx.TIME_HOUR;
		long Time_Minute = DateEx.TIME_MINUTE;
		long Time_Second = DateEx.TIME_SECOND;

		Date now_date = new Date();

		int h = DateEx.hour(now_date);
		int m = DateEx.minute(now_date);
		int s = DateEx.second(now_date);

		long curTime = h * Time_Hour + m * Time_Minute + s * Time_Second;

		long nextH = 70 * Time_Minute;

		long initialDelay = nextH - curTime;
		long delay = nextH;

		while (initialDelay <= 0) {
			initialDelay += delay;
		}

		t.isDelChina = isDelChina;

		Svc.scheduled8FixedRate(SES, t, initialDelay, delay);
	}

	boolean isDelChina = false;

	void resetSvcid() throws Exception {
		int newsvicd = 2000;
		LogicSubarea.resetAllPlayerSvcid(newsvicd);
	}

	@Override
	public void run() {
		try {

		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}
}
