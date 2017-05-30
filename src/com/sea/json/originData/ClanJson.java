package com.sea.json.originData;

import java.util.Map;

import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class ClanJson {

	public static final String Json_Path_Clan = "json/game/clan.json";

	static final String Key_MaxPointHP = "maxPointHP";
	static final String Key_MaxPointAtt = "maxPointAtt";
	private static final String Key_EachRateHP = "eachRateHP";
	private static final String Key_EachRateAtt = "eachRateAtt";
	private static final String Key_DonateMaxNum = "donateMaxNum";
	public static final String Key_NextResCell_Oil = "nextResCell_Oil";
	public static final String Key_NextResDiff_Oil = "nextResDiff_Oil";
	public static final String Key_NextResFirst_Oil = "nextResFirst_Oil";
	public static final String Key_NextResBase_Oil = "nextResBase_Oil";

	public static final String Key_NextResCell_Gold = "nextResCell_Gold";
	public static final String Key_NextResDiff_Gold = "nextResDiff_Gold";
	public static final String Key_NextResFirst_Gold = "nextResFirst_Gold";
	public static final String Key_NextResBase_Gold = "nextResBase_Gold";

	public static Map readMapData() {
		String path = Json_Path_Clan;
		Map r = UtileTools.readStrMap(path);
		return r;
	}

	public static int getValBykey(Map map, String key) {
		int r = MapEx.getInt(map, key);
		return r;
	}

	public static int getEachRateHP() {
		Map map = readMapData();
		return getValBykey(map, Key_EachRateHP);
	}

	public static int getEachRateAtt() {
		Map map = readMapData();
		return getValBykey(map, Key_EachRateAtt);
	}

	public static int getDonateMaxNum() {
		Map map = readMapData();
		return getValBykey(map, Key_DonateMaxNum);
	}
}
