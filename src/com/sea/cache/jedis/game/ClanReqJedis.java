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
import com.sea.db.bean.Clan_request;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class ClanReqJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ClanReqJedis.class);

	public static Clan_request toObjByStrVal(String strVal) {
		Map map = UtileTools.strToMap(strVal);
		return toObjByMap(map);
	}

	public static Clan_request toObjByMap(Map map) {
		if (map == null)
			return null;
		String ccid = MapEx.getString(map, "ccid");
		if ("".equals(ccid.trim()))
			return null;
		int id = MapEx.getInt(map, "id");
		int pcid = MapEx.getInt(map, "pcid");
		String pname = MapEx.getString(map, "pname");
		int renown = MapEx.getInt(map, "renown");

		return Clan_request.newClan_request(id, ccid, pcid, pname, renown);
	}

	// ========== method
	static int ValidTime = 60 * 60 * 24 * 3;// 3天

	// 添加key的有效时间
	public static void setDataByVTime(Clan_request o) {
		if (o == null)
			return;
		String ccid = o.getCcid();
		int pcid = o.getPcid();
		String key = getKeyClanReq(ccid, pcid);
		List<Clan_request> list = new ArrayList<Clan_request>();
		list.add(o);
		setList(list);
		JedisHash.setKeyValidTime(key, ValidTime);
	}

	public static void setList(List<Clan_request> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		setListDataByKMap(list);
	}

	public static Clan_request getClanReq(String ccid, int pcid) {
		Clan_request r = null;
		if (ccid == null || "".equals(ccid.trim()))
			return r;
		String key = getKeyClanReq(ccid, pcid);
		Map<String, String> map = JedisHash.getMap(key);
		return toObjByMap(map);
	}

	private static List<String> getListAllKeys(String ccid) {
		String pattern = getPatternClanReq(ccid);
		return JedisHash.getListKeysByPattern(pattern);
	}

	public static List<Clan_request> getListInClan(String ccid) {
		List<String> keyes = getListAllKeys(ccid);
		List<Map<String, String>> result = JedisHash.getListMap(keyes);
		if (result == null || result.isEmpty())
			return null;
		List<Clan_request> r = new ArrayList<Clan_request>();
		for (Map<String, String> map : result) {
			Clan_request o = toObjByMap(map);
			if (o == null)
				continue;
			r.add(o);
		}
		return r;
	}

	public static void delClanReqInClan(String ccid) {
		String pattern = getPatternClanReq(ccid);
		JedisHash.delAllByPattern(pattern);
	}
}
