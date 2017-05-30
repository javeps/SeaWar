package com.sea.json.originData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class TownHallJson {

	private static final String Json_Path = "json/game/townHall.json";

	private static final String Key_Lvl = "thlvl";
	private static final String Key_Gid_Head = "bgid_";

	static List list = null;
	static Map<Integer, Map> mapData = new HashMap<Integer, Map>();
	static int lvlMax = -1;

	static List readListData() {
		if (list == null) {
			String path = Json_Path;
			list = UtileTools.readStrList(path);
			mapData.clear();
			int len = list.size();
			for (int i = 0; i < len; i++) {
				Map map = (Map) list.get(i);
				int lvl = MapEx.getInt(map, Key_Lvl);
				if (lvl <= 0)
					continue;
				if (lvl > lvlMax) {
					lvlMax = lvl;
				}
				mapData.put(lvl, map);
			}
		}
		return list;
	}

	public static void reloadTHall() {
		if (list != null) {
			list.clear();
			list = null;
		}
		readListData();
	}

	// ===================

	public static Map getMapByTHLvl(int lvl) {
		readListData();
		lvl = lvl > lvlMax ? lvlMax : lvl;
		return mapData.get(lvl);
	}

	public static int getNumByTHLvlGid(int lvl, int objgid) {
		int r = 0;
		Map map = getMapByTHLvl(lvl);
		if (map == null)
			return r;
		String key = Key_Gid_Head + objgid;
		r = MapEx.getInt(map, key);
		return r;
	}
}
