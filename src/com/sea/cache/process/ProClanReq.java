package com.sea.cache.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.PStr;
import com.sea.cache.jedis.game.ClanMemberJedis;
import com.sea.cache.jedis.game.ClanReqJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Clan_request;

public class ProClanReq implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProClanReq.class);

	static final Map<String, Clan_request> entityCache = Svc.newSortedMap();

	static final Map<String, Set<String>> ccidChache = new ConcurrentHashMap<String, Set<String>>();

	static final List<Clan_request> inlist = new CopyOnWriteArrayList<Clan_request>();
	static final List<Clan_request> uplist = new CopyOnWriteArrayList<Clan_request>();

	static String getKey(Clan_request pb) {
		if (pb == null)
			return "";
		return getKey(pb.getCcid(), pb.getPcid());
	}

	static String getKey(String ccid, int pcid) {
		return PStr.b(ccid + "_").e(pcid);
	}

	// =========== 缓存
	static public void loadCache(List<Clan_request> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Clan_request item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Clan_request pb) {
		if (pb == null)
			return;
		String ccid = pb.getCcid();
		String k = getKey(pb);
		entityCache.put(k, pb);
		Set<String> set = ccidChache.get(ccid);
		if (set == null)
			set = new HashSet<String>();
		set.add(k);
		ccidChache.put(ccid, set);
	}

	static public void clearCache(List<Clan_request> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Clan_request item : origin) {
			clearCache(item);
		}
	}

	static public void clearCache(Clan_request pb) {
		if (pb == null)
			return;
		String ccid = pb.getCcid();
		String k = getKey(pb);
		entityCache.remove(k);
		Set<String> set = ccidChache.get(ccid);
		if (set == null || set.isEmpty())
			return;
		int len = set.size();
		if (len > 0) {
			set.remove(k);
			ccidChache.put(ccid, set);
		} else {
			ccidChache.remove(ccid);
		}
	}

	static public void clearCache(String ccid) {
		Set<String> set = ccidChache.get(ccid);
		if (set == null)
			return;
		for (String key : set) {
			entityCache.remove(key);
		}
		ccidChache.remove(ccid);
	}

	// ============== 数据
	static public void inCmem(Clan_request pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (!inlist.contains(pb))
			inlist.add(pb);
	}

	static public void upCMem(List<Clan_request> list) {
		if (Svc.isEmpty(list))
			return;
		for (Clan_request item : list) {
			upCMem(item);
		}
	}

	static public void upCMem(Clan_request pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (inlist.contains(pb)) {
			inlist.remove(pb);
		}
		if (!uplist.contains(pb))
			uplist.add(pb);
	}

	static public void delCMem(Clan_request pb) {
		clearCache(pb);
		inlist.remove(pb);
		uplist.remove(pb);
		ClanMemberJedis.delData(pb);
	}

	static public void delCMem(List<Clan_request> list) {
		if (Svc.isEmpty(list))
			return;
		for (Clan_request Clan_request : list) {
			delCMem(Clan_request);
		}
	}

	static public Clan_request getClanReq(String ccid, int pcid) {
		if (ccid == null || "".equals(ccid.trim()))
			return null;
		String key = getKey(ccid, pcid);
		Clan_request res = entityCache.get(key);
		if (res == null) {
			res = ClanReqJedis.getClanReq(ccid, pcid);
		}
		loadCache(res);
		return res;
	}

	static public List<Clan_request> getListInClan(String ccid) {
		Set<String> keyes = ccidChache.get(ccid);
		List<Clan_request> result = null;
		if (keyes != null) {
			result = new ArrayList<Clan_request>();
			Clan_request tmp = null;
			for (String key : keyes) {
				tmp = entityCache.get(key);
				if (tmp == null)
					continue;
				result.add(tmp);
			}
		} else {
			result = ClanReqJedis.getListInClan(ccid);
			loadCache(result);
		}
		return result;
	}

	public static void delClanReqInClan(String ccid) {
		clearCache(ccid);
		ClanReqJedis.delClanReqInClan(ccid);
	}

	static public void saveData() {
		List<Clan_request> list = new CopyOnWriteArrayList<Clan_request>();

		if (!Svc.isEmpty(uplist)) {
			for (Clan_request item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		ClanReqJedis.setList(list);

		inlist.clear();
		uplist.clear();
	}
}
