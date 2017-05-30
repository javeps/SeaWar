package com.sea.json.originData;

import java.util.Map;

import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class ActivitiesJson {

	public static final String Path_Head = "json/game/activities/";
	private static final String PathActive = Path_Head + "activities.json";

	private static final String Key_ChongZhiDouble = "cz2";
	private static final String Key_Energy = "energy";
	private static final String Key_EnergyWeek = "energyWeek";
	private static final String Key_HeroAttribute = "heroAttribute";
	private static final String Key_BoxMultiple = "boxMultiple";
	private static final String Key_MidAutumn = "midAutumn";
	private static final String Key_PayEnergy = "payEnergy";
	private static final String Key_Pay3 = "pay3";

	private static final String Key_TimeBegin = "begintime";
	private static final String Key_TimeEnd = "endtime";
	private static final String Key_Rate = "rate";
	private static final String Key_IsOpen = "isOpen";

	private static final String Time_Fmt = DateEx.fmt_yyyy_MM_dd_HH_mm_ss;

	static Map mapActivte = null;

	static private Map mapData() {
		if (mapActivte == null || mapActivte.isEmpty()) {
			mapActivte = UtileTools.readStrMap(PathActive);
		}
		return mapActivte;
	}

	public static void reloadMapData() {
		if (mapActivte != null) {
			mapActivte.clear();
			mapActivte = null;
		}
		mapData();
	}

	public static boolean getIsOpenPay2() {
		Map map = mapData();
		boolean r = false;
		try {
			Map mapEn = MapEx.getMap(map, Key_ChongZhiDouble);
			String bt = MapEx.getString(mapEn, Key_TimeBegin);
			String et = MapEx.getString(mapEn, Key_TimeEnd);
			long btl = DateEx.parse(bt, Time_Fmt).getTime();
			long etl = DateEx.parse(et, Time_Fmt).getTime();
			long ntl = System.currentTimeMillis();
			r = ntl >= btl && ntl <= etl;
		} catch (Exception e) {

		}
		return r;
	}

	private static boolean isOpenEnergyWeek(Map map, long ntl) {
		boolean r = false;
		Map mapEn = MapEx.getMap(map, Key_EnergyWeek);
		boolean isEWeek = MapEx.getBoolean(mapEn, Key_IsOpen);
		if (isEWeek) {
			r = UtileTools.isWeek(ntl);
		}
		return r;
	}

	private static boolean isOpenEnergyTime(Map map, long ntl) {
		boolean r = false;
		try {
			Map mapEn = MapEx.getMap(map, Key_Energy);
			String bt = MapEx.getString(mapEn, Key_TimeBegin);
			String et = MapEx.getString(mapEn, Key_TimeEnd);
			long btl = DateEx.parse(bt, Time_Fmt).getTime();
			long etl = DateEx.parse(et, Time_Fmt).getTime();
			r = ntl >= btl && ntl <= etl;
		} catch (Exception e) {

		}
		return r;
	}

	public static boolean getIsOpenEnergy() {
		Map map = mapData();
		long ntl = System.currentTimeMillis();
		boolean r = isOpenEnergyTime(map, ntl);
		if (!r) {
			r = isOpenEnergyWeek(map, ntl);
		}
		return r;
	}

	// 增加英雄属性的倍数
	public static double getMultipleHeroAttr() {
		double r = 1;
		Map map = mapData();
		try {
			Map mapEn = MapEx.getMap(map, Key_HeroAttribute);
			String bt = MapEx.getString(mapEn, Key_TimeBegin);
			String et = MapEx.getString(mapEn, Key_TimeEnd);
			long btl = DateEx.parse(bt, Time_Fmt).getTime();
			long etl = DateEx.parse(et, Time_Fmt).getTime();
			long ntl = System.currentTimeMillis();
			boolean isOpen = ntl >= btl && ntl <= etl;
			if (isOpen) {
				r = MapEx.getDouble(mapEn, Key_Rate);
			}
		} catch (Exception e) {

		}
		if (r <= 0)
			r = 1;
		return r;
	}

	// 宝箱所得的能源片倍数
	public static double getMultipleBoxEnergy() {
		double r = 1;
		Map map = mapData();
		try {
			Map mapEn = MapEx.getMap(map, Key_BoxMultiple);
			String bt = MapEx.getString(mapEn, Key_TimeBegin);
			String et = MapEx.getString(mapEn, Key_TimeEnd);
			long btl = DateEx.parse(bt, Time_Fmt).getTime();
			long etl = DateEx.parse(et, Time_Fmt).getTime();
			long ntl = System.currentTimeMillis();
			boolean isOpen = ntl >= btl && ntl <= etl;
			if (isOpen) {
				r = MapEx.getDouble(mapEn, Key_Rate);
			}
		} catch (Exception e) {

		}
		if (r <= 0)
			r = 1;
		return r;
	}

	/**** 中秋节活动 ***/
	public static boolean getIsOpenMidAutumn() {
		boolean r = false;
		Map map = mapData();
		try {
			Map mapEn = MapEx.getMap(map, Key_MidAutumn);
			String bt = MapEx.getString(mapEn, Key_TimeBegin);
			String et = MapEx.getString(mapEn, Key_TimeEnd);
			long ntl = System.currentTimeMillis();
			long btl = DateEx.parse(bt, Time_Fmt).getTime();
			long etl = DateEx.parse(et, Time_Fmt).getTime();
			r = ntl >= btl && ntl <= etl;
		} catch (Exception e) {
		}
		return r;
	}

	/**** 充值送能源片活动 ***/
	public static boolean getIsOpenPayEnergy() {
		boolean r = false;
		Map map = mapData();
		try {
			Map mapEn = MapEx.getMap(map, Key_PayEnergy);
			String bt = MapEx.getString(mapEn, Key_TimeBegin);
			String et = MapEx.getString(mapEn, Key_TimeEnd);
			long ntl = System.currentTimeMillis();
			long btl = DateEx.parse(bt, Time_Fmt).getTime();
			long etl = DateEx.parse(et, Time_Fmt).getTime();
			r = ntl >= btl && ntl <= etl;
		} catch (Exception e) {
		}
		return r;
	}

	/**** 充值的的宝石是原始值+rate/100 * 原始值 ***/
	public static int getPayGemsRate() {
		int r = 0;
		Map map = mapData();
		try {
			Map mapEn = MapEx.getMap(map, Key_Pay3);
			String bt = MapEx.getString(mapEn, Key_TimeBegin);
			String et = MapEx.getString(mapEn, Key_TimeEnd);
			long btl = DateEx.parse(bt, Time_Fmt).getTime();
			long etl = DateEx.parse(et, Time_Fmt).getTime();
			long ntl = System.currentTimeMillis();
			boolean isOpen = ntl >= btl && ntl <= etl;
			if (isOpen) {
				r = MapEx.getInt(mapEn, Key_Rate);
			}
		} catch (Exception e) {
		}
		if (r < 0)
			r = 0;
		return r;
	}
}
