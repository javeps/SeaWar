package com.sea.json.originData;

import java.util.List;
import java.util.Map;

import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class PayOriginJson {

	public static final String PJ_MONEY = "money";
	public static final String PJ_UNI = "unique";
	public static final String PJ_GEM = "gem";

	public static String getJsonPath(String channel) {
		StringBuffer s_ = new StringBuffer();
		s_.append("json/pay/").append(channel).append(".json");
		String r = s_.toString();
		return r;
	}

	public static List readListData(String channel) {
		String path = getJsonPath(channel);
		List r = UtileTools.readStrList(path);
		return r;
	}

	public static Map getMapBy(String channel, String ideStr) {
		Map r = null;
		if (ideStr == null)
			return r;
		ideStr = ideStr.trim();

		List list = readListData(channel);
		for (Object object : list) {
			Map map = (Map) object;
			String iden = MapEx.getString(map, PJ_UNI);
			if (iden.equals(ideStr)) {
				r = map;
				break;
			}
		}
		UtileTools.clearList(list);
		return r;
	}

	// =========================

	public static int getGemBy(String channel, String ideStr) {
		int r = 0;
		if (ideStr == null)
			return r;
		Map map = getMapBy(channel, ideStr);
		if (map == null)
			return r;

		r = MapEx.getInt(map, PJ_GEM);
		UtileTools.clearMap(map);
		return r;
	}

	public static Map getMapByGems(String channel, String gems) {
		Map r = null;
		if (gems == null)
			return r;
		gems = gems.trim();

		List list = readListData(channel);
		for (Object object : list) {
			Map map = (Map) object;
			String strGems = MapEx.getString(map, PJ_GEM);
			if (strGems.equals(gems)) {
				r = map;
				break;
			}
		}
		UtileTools.clearList(list);
		return r;
	}

	public static String getIdenByGems(String channel, String gems) {
		Map map = getMapByGems(channel, gems);
		if (map == null)
			return "";
		String v = MapEx.getString(map, PJ_UNI);
		UtileTools.clearMap(map);
		return v;
	}

	public static int getMoneyByGems(String channel, String gems) {
		Map map = getMapByGems(channel, gems);
		if (map == null)
			return 0;
		int v = MapEx.getInt(map, PJ_MONEY);
		UtileTools.clearMap(map);
		return v;
	}
	
	// 取得money map
	public static Map getMapByMoney(String channel, String money) {
		Map r = null;
		if (money == null)
			return r;
		money = money.trim();
		List list = readListData(channel);
		for (Object object : list) {
			Map map = (Map) object;
			String strMoney = MapEx.getString(map, PJ_MONEY);
			if (strMoney.equals(money)) {
				r = map;
				break;
			}
		}
		UtileTools.clearList(list);
		return r;
	}

	public static String getIdenByMoney(String channel, String money) {
		Map map = getMapByMoney(channel, money);
		if (map == null)
			return "";
		String v = MapEx.getString(map, PJ_UNI);
		UtileTools.clearMap(map);
		return v;
	}
}
