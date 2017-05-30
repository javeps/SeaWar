package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.sql.mysql.BeanSupport;
import com.bowlong.util.MapEx;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.db.bean.Clan;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class ClanJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ClanJedis.class);

	public static Clan toObjByStrVal(String strVal) {
		Map map = UtileTools.strToMap(strVal);
		return toObjByMap(map);
	}

	public static Clan toObjByMap(Map map) {
		if (map == null)
			return null;
		String ccid = MapEx.getString(map, "ccid");
		if ("".equals(ccid.trim()))
			return null;
		int id = MapEx.getInt(map, "id");
		String clan_name = MapEx.getString(map, "clan_name");
		int icon = MapEx.getInt(map, "icon");
		int lvl = MapEx.getInt(map, "lvl");
		String desc = MapEx.getString(map, "desc");
		int maxNum = MapEx.getInt(map, "maxNum");
		int curNum = MapEx.getInt(map, "curNum");
		long create_time = MapEx.getLong(map, "create_time");
		int pointHP = MapEx.getInt(map, "pointHP");
		int pointAtt = MapEx.getInt(map, "pointAtt");
		int renownAll = MapEx.getInt(map, "renownAll");
		int renownWeek = MapEx.getInt(map, "renownWeek");

		long nextHPGold = MapEx.getLong(map, "nextHPGold");
		long nextAttOil = MapEx.getLong(map, "nextAttOil");

		long curDonateGold = MapEx.getLong(map, "curDonateGold");
		long curDonateOil = MapEx.getLong(map, "curDonateOil");

		return Clan.newClan(id, ccid, clan_name, icon, lvl, desc, maxNum,
				curNum, create_time, pointHP, pointAtt, renownAll, renownWeek,
				nextHPGold, nextAttOil, curDonateGold, curDonateOil);
	}

	// ========== method

	public static void setList(List<Clan> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		setListDataByKMap(list);
	}

	public static Clan getClanByCcid(String ccid) {
		Clan r = null;
		if (ccid == null || "".equals(ccid.trim()))
			return r;
		String key = getKeyClan(ccid);
		return getClanByCKey(key);
	}

	private static Clan getClanByCKey(String key) {
		if (key == null || "".equals(key.trim()))
			return null;
		Map<String, String> v = JedisHash.getMap(key);
		return toObjByMap(v);
	}

	public static List<Clan> getListAll() {
		List<String> keyes = JedisHash
				.getListKeysByPattern(_GameKeys.Pattern_Key_Clan);
		List<Map<String, String>> result = JedisHash.getListMap(keyes);
		if (result == null || result.isEmpty())
			return null;
		List<Clan> r = new ArrayList<Clan>();
		boolean isChange = false;
		for (Map<String, String> map : result) {
			Clan e = toObjByMap(map);
			if (e == null)
				continue;
			int eCunNum = e.getCurnum();
			int cunNum = ClanMemberJedis.getNumMembers(e.getCcid());
			if (cunNum == 0) {
				delData(e);
				continue;
			}

			if (eCunNum != cunNum) {
				isChange = true;
				e.setCurnum(cunNum);
			}
			r.add(e);
		}
		if (isChange) {
			setList(r);
		}
		return r;
	}
}
