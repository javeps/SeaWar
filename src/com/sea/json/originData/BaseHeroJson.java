package com.sea.json.originData;

import java.util.List;
import java.util.Map;

import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class BaseHeroJson {

	static List listBase = null;

	private static final String Json_Path_Hero = "json/game/heroBase.json";

	private static final String BHJ_HGID = "hgid";
	private static final String BHJ_NAME = "name";
	private static final String BHJ_HP = "eachHP";
	private static final String BHJ_DAMAGE = "eachDamage";
	private static final String BHJ_SPEED = "eachSpeed";
	static final String BHJ_SpeedInit = "initSpeed";
	private static final String BHJ_SpeedMax = "maxSpeed";
	static final String BHJ_STATUS = "status";

	public static List readListData() {
		if (listBase == null) {
			String path = Json_Path_Hero;
			listBase = UtileTools.readStrList(path);
		}
		return listBase;
	}

	// ===================

	/*** 根据英雄的gid取得基础map值 **/
	static private Map getHeroBaseBy(int hgid) {
		Map map = null;
		List list = readListData();
		for (Object object : list) {
			Map item = (Map) object;
			int itemhgid = MapEx.getInt(item, BHJ_HGID);
			if (itemhgid == hgid) {
				map = item;
				break;
			}
		}
		return map;
	}

	/*** 取得英雄的名称 **/
	public static String getHeroName(int hgid) {
		Map map = getHeroBaseBy(hgid);
		String name = MapEx.getString(map, BHJ_NAME);
		return name;
	}

	/*** 取得英雄的成长血量 **/
	public static int getHP(int hgid) {
		Map map = getHeroBaseBy(hgid);
		int val = MapEx.getInt(map, BHJ_HP);
		return val;
	}

	/*** 取得英雄的成长伤害 **/
	public static int getDam(int hgid) {
		Map map = getHeroBaseBy(hgid);
		int val = MapEx.getInt(map, BHJ_DAMAGE);
		return val;
	}

	/*** 取得英雄的成长数度 **/
	public static int getSpeed(int hgid) {
		Map map = getHeroBaseBy(hgid);
		int val = MapEx.getInt(map, BHJ_SPEED);
		return val;
	}

	/*** 取得英雄的数度成长的最大值(与初始值越接近越块) **/
	static public int getSpeedMax(int hgid) {
		Map map = getHeroBaseBy(hgid);
		int max = MapEx.getInt(map, BHJ_SpeedMax);
		return max;
	}
}
