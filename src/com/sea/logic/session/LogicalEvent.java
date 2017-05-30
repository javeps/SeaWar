package com.sea.logic.session;

import java.util.Date;

import com.bowlong.util.DateEx;
import com.sea.db.bean.Player;

/**
 * 活动 必须有足够的gems，通过验证,但是扣除的时候，只是按照活动扣除钻石
 * 
 * @author Canyon
 *
 */
public class LogicalEvent {

	/** 是否开启活动 */
	static public boolean isOpenEvent = false;
	/** 所有活动结束时间 yyyy-MM-dd HH:mm:ss */
	static public String endTimeStr = "2014-11-05 00:00:00";
	/** 测试帐号Pcid */
	static public int testPcid = 5417;

	static private boolean isActiveTime(String edtimeStr) {
		if (!isOpenEvent)
			return false;
		Date d = DateEx.parseDate(edtimeStr, DateEx.fmt_yyyy_MM_dd_HH_mm_ss);
		if (d == null)
			return false;
		long endtime = d.getTime();
		long ntime = System.currentTimeMillis();
		return endtime > ntime;
	}

	/*** 快速完成建筑建造 1gems */
	static public boolean isActiveFinishBuild(Player p) {
		if (p == null)
			return false;
		if (p.getPcid() == testPcid)
			return true;
		return isActiveTime(endTimeStr);
	}

	/*** 快速完成造兵 1gems */
	static public boolean isActiveFinishArmy(Player p) {
		if (p == null)
			return false;
		if (p.getPcid() == testPcid)
			return true;
		return isActiveTime(endTimeStr);
	}

	/*** 购买宠物70% */
	static public boolean isActiveBuyHero(Player p) {
		if (p == null)
			return false;
		if (p.getPcid() == testPcid)
			return true;
		return isActiveTime(endTimeStr);
	}

	/*** 购买资源 花费70% */
	static public boolean isActiveBuyRes(Player p) {
		if (p == null)
			return false;
		if (p.getPcid() == testPcid)
			return true;
		return isActiveTime(endTimeStr);
	}

}
