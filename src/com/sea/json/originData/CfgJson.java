package com.sea.json.originData;

import java.util.Map;

import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class CfgJson {

	static final String Path_Origin = "json/data/cfg.json";

	private static final String Key_IsVerifyMonCode = "isVerifyMonCode";
	private static final String Key_IsNewReward = "isNewReward";
	private static final String Key_FightResRate = "fightResRate";
	private static final String Key_IsOpenMonitor = "isOpenMonitor";

	public static Map readMapData() {
		String path = Path_Origin;
		Map r = UtileTools.readStrMap(path);
		return r;
	}

	public static boolean isVerifyMonCode() {
		Map map = readMapData();
		boolean r = false;
		r = MapEx.getBoolean(map, Key_IsVerifyMonCode);
		UtileTools.clearMap(map);
		return r;
	}

	public static boolean isNewReward() {
		Map map = readMapData();
		boolean r = false;
		r = MapEx.getBoolean(map, Key_IsNewReward);
		UtileTools.clearMap(map);
		return r;
	}

	static public boolean isOpenMonitor() {
		Map map = readMapData();
		boolean r = false;
		r = MapEx.getBoolean(map, Key_IsOpenMonitor);
		UtileTools.clearMap(map);
		return r;
	}

	public static double getFightResRate() {
		Map map = readMapData();
		double r = MapEx.getDouble(map, Key_FightResRate);
		UtileTools.clearMap(map);
		return r;
	}
}
