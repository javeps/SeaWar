package com.sea.cache.process;

import gen_b2g.serv.bean.ConstantType;

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
import com.sea.cache.jedis.game.AssistUserPlayerJedis;
import com.sea.cache.jedis.game.ClanMemberJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Clan_member;
import com.sea.db.bean.Player;
import com.sea.logic.logicOperate.LogicPlayer;
import com.sea.logic.session.LogicalSession;

public class ProClanMember implements Serializable {

	private static final long serialVersionUID = 1L;
	static Log log = LogFactory.getLog(ProClanMember.class);

	static final Map<String, Clan_member> cmemCache = Svc.newSortedMap();

	static final Map<String, Set<String>> ccidChache = new ConcurrentHashMap<String, Set<String>>();

	static final List<Clan_member> inlist = new CopyOnWriteArrayList<Clan_member>();
	static final List<Clan_member> uplist = new CopyOnWriteArrayList<Clan_member>();

	static String getKey(Clan_member pb) {
		if (pb == null)
			return "";
		return getKey(pb.getCcid(), pb.getPcid());
	}

	static String getKey(String ccid, int pcid) {
		return PStr.b(ccid + "_").e(pcid);
	}

	// =========== 缓存
	static public void loadCache(List<Clan_member> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Clan_member item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Clan_member pb) {
		if (pb == null)
			return;
		String ccid = pb.getCcid();
		String k = getKey(pb);
		cmemCache.put(k, pb);
		Set<String> set = ccidChache.get(ccid);
		if (set == null)
			set = new HashSet<String>();
		set.add(k);
		ccidChache.put(ccid, set);
	}

	static public void clearCache(List<Clan_member> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Clan_member item : origin) {
			clearCache(item);
		}
	}

	static public void clearCache(Clan_member pb) {
		if (pb == null)
			return;
		String ccid = pb.getCcid();
		String k = getKey(pb);
		cmemCache.remove(k);
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
			cmemCache.remove(key);
		}
		ccidChache.remove(ccid);
	}

	// ========= 数据

	static public void inCmem(Clan_member pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (!inlist.contains(pb))
			inlist.add(pb);
	}

	static public void upCMem(List<Clan_member> list) {
		if (Svc.isEmpty(list))
			return;
		for (Clan_member item : list) {
			upCMem(item);
		}
	}

	static public void upCMem(Clan_member pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (inlist.contains(pb)) {
			inlist.remove(pb);
		}
		if (!uplist.contains(pb))
			uplist.add(pb);
	}

	static public void delCMem(Clan_member pb) {
		clearCache(pb);
		inlist.remove(pb);
		uplist.remove(pb);
		ClanMemberJedis.delData(pb);
	}

	static public void delCMem(List<Clan_member> list) {
		if (Svc.isEmpty(list))
			return;
		for (Clan_member clan_member : list) {
			delCMem(clan_member);
		}
	}

	static public Clan_member getClanMember(String ccid, int pcid) {
		if (ccid == null || "".equals(ccid.trim()))
			return null;
		String key = getKey(ccid, pcid);
		Clan_member res = cmemCache.get(key);
		if (res == null) {
			res = ClanMemberJedis.getClanMember(ccid, pcid);
		}
		loadCache(res);
		return res;
	}

	static public List<Clan_member> getListInClan(String ccid) {
		Set<String> keyes = ccidChache.get(ccid);
		List<Clan_member> result = null;
		if (keyes != null) {
			result = new ArrayList<Clan_member>();
			Clan_member tmp = null;
			for (String key : keyes) {
				tmp = cmemCache.get(key);
				if (tmp == null)
					continue;
				result.add(tmp);
			}
		} else {
			result = ClanMemberJedis.getListInClan(ccid);
			loadCache(result);
		}
		return result;
	}

	static public void delAllMemberInClan(String ccid) {
		List<Clan_member> list = getListInClan(ccid);
		List<Integer> pcides = new ArrayList<Integer>();
		if (list != null && !list.isEmpty()) {
			for (Clan_member item : list) {
				int pcid = item.getPcid();
				Player p = AssistUserPlayerJedis.getPlayerByPcid(pcid);
				if (p == null)
					continue;
				int post = p.getClanpost();
				if (post == ConstantType.Type_ClanMember_Admin)
					continue;
				pcides.add(pcid);
				LogicPlayer.upClanCid(p, "", 0, "", 0);
			}
		}
		
		LogicalSession.clanDisband(pcides);
		
		clearCache(ccid);
		
		ClanMemberJedis.delAllMemberInClan(ccid);
	}

	static public void saveData() {
		List<Clan_member> list = new CopyOnWriteArrayList<Clan_member>();

		if (!Svc.isEmpty(uplist)) {
			for (Clan_member item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		ClanMemberJedis.setList(list);

		inlist.clear();
		uplist.clear();
	}
}
