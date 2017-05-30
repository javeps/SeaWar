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
import com.sea.db.bean.Clan_member;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class ClanMemberJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ClanMemberJedis.class);

	public static Clan_member toObjByStrVal(String strVal) {
		Map map = UtileTools.strToMap(strVal);
		return toObjByMap(map);
	}

	public static Clan_member toObjByMap(Map map) {
		if (map == null)
			return null;
		String ccid = MapEx.getString(map, "ccid");
		if ("".equals(ccid.trim()))
			return null;
		int id = MapEx.getInt(map, "id");
		int pcid = MapEx.getInt(map, "pcid");
		int ucid = MapEx.getInt(map, "ucid");
		String cname = MapEx.getString(map, "cname");
		String pname = MapEx.getString(map, "pname");
		int post = MapEx.getInt(map, "post");
		int donateGold = MapEx.getInt(map, "donateGold");
		int donateOil = MapEx.getInt(map, "donateOil");
		int curDGold = MapEx.getInt(map, "curDGold");
		int curDOil = MapEx.getInt(map, "curDOil");

		long lastDGold = MapEx.getLong(map, "lastDGold");
		long lastDOil = MapEx.getLong(map, "lastDOil");
		int renownAll = MapEx.getInt(map, "renownAll");
		int renownWeek = MapEx.getInt(map, "renownWeek");

		int daynumgold = MapEx.getInt(map, "daynumgold");
		int daynumoil = MapEx.getInt(map, "daynumoil");
		int maxdaynum = MapEx.getInt(map, "maxdaynum");

		return Clan_member.newClan_member(id, ccid, cname, ucid, pcid, pname,
				post, donateGold, donateOil, curDGold, curDOil, lastDGold,
				lastDOil, renownAll, renownWeek, daynumgold, daynumoil,
				maxdaynum);
	}

	// ========== method

	public static void setList(List<Clan_member> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		setListDataByKMap(list);
	}

	public static Clan_member getClanMember(String ccid, int pcid) {
		if (ccid == null || "".equals(ccid.trim()))
			return null;
		String key = getKeyClanMember(ccid, pcid);
		Map<String, String> val = JedisHash.getMap(key);
		return toObjByMap(val);
	}

	private static List<String> getListAllKeys(String ccid) {
		String pattern = getPatternClanMember(ccid);
		return JedisHash.getListKeysByPattern(pattern);
	}

	public static int getNumMembers(String ccid) {
		List<String> keyes = getListAllKeys(ccid);
		if (keyes == null || keyes.isEmpty())
			return 0;
		int v = keyes.size();
		keyes = null;
		return v;
	}

	public static List<Clan_member> getListInClan(String ccid) {
		List<String> keyes = getListAllKeys(ccid);
		List<Map<String, String>> result = JedisHash.getListMap(keyes);
		if (result == null || result.isEmpty())
			return null;
		List<Clan_member> r = new ArrayList<Clan_member>();
		for (Map<String, String> map : result) {
			Clan_member o = toObjByMap(map);
			if (o == null)
				continue;
			r.add(o);
		}
		return r;
	}

	public static void delAllMemberInClan(String ccid) {
		String pattern = getPatternClanMember(ccid);
		JedisHash.delAllByPattern(pattern);
	}
}
