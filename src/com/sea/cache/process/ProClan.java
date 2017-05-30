package com.sea.cache.process;

import gen_b2g.serv.bean.ConstantType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.game.ClanJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Clan;
import com.sea.db.bean.Clan_member;
import com.sea.db.bean.Player;
import com.sea.logic.logicOperate.LogicPlayer;

public class ProClan implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProClan.class);

	static final Map<String, Clan> ccidCache = Svc.newSortedMap();
	// 所有联盟
	static List<Clan> allList = new CopyOnWriteArrayList<Clan>();
	// 辅助联盟的联盟名称map
	static final Map<String, String> mapCname = new ConcurrentHashMap<String, String>();

	static final List<Clan> inlist = new CopyOnWriteArrayList<Clan>();
	static final List<Clan> uplist = new CopyOnWriteArrayList<Clan>();

	// =========== 缓存
	static public void loadCache(List<Clan> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Clan item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Clan pb) {
		if (pb == null)
			return;
		String k = pb.getCcid();
		ccidCache.put(k, pb);
		mapCname.put(pb.getClan_name(), k);
	}

	static public void clearCache(List<Clan> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Clan item : origin) {
			clearCache(item);
		}
	}

	static public void clearCache(Clan pb) {
		if (pb == null)
			return;
		ccidCache.remove(pb.getCcid());
		mapCname.remove(pb.getClan_name());
	}

	// ====== 数据操作
	static public boolean isHasClanName(String cname) {
		return mapCname.containsKey(cname);
	}

	static public List<Clan> getListClanByName(String cname) {
		if (cname == null || "".equals(cname.trim()))
			return null;

		cname = cname.trim();

		List<Clan> all = getListAll();
		if (Svc.isEmpty(all))
			return null;

		List<Clan> list = new ArrayList<Clan>();

		int len = all.size();
		for (int i = 0; i < len; i++) {
			Clan clan = all.get(i);
			if (clan == null)
				continue;
			String itemCname = clan.getClan_name();
			int index = itemCname.indexOf(cname);
			if (index == -1)
				continue;
			list.add(clan);
		}
		return list;
	}

	static public Clan getClanByCcid(String ccid) {
		if (ccid == null || "".equals(ccid.trim()))
			return null;
		Clan r = ccidCache.get(ccid);
		if (r == null) {
			r = ClanJedis.getClanByCcid(ccid);
		}
		loadCache(r);
		return r;
	}

	static boolean isLoadAll = false;

	static public List<Clan> getListAll() {
		if (!isLoadAll) {
			isLoadAll = true;
			List<Clan> clan = ClanJedis.getListAll();
			if (!Svc.isEmpty(clan))
				allList.addAll(clan);
			loadCache(allList);
		}
		return allList;
	}

	static public void inClan(Clan pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (!inlist.contains(pb))
			inlist.add(pb);

		if (!allList.contains(pb))
			allList.add(pb);
	}

	static public void upClan(Clan pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (inlist.contains(pb)) {
			inlist.remove(pb);
		}
		if (!uplist.contains(pb))
			uplist.add(pb);
	}

	static public void upClan(List<Clan> list) {
		if (Svc.isEmpty(list))
			return;
		for (Clan clan : list) {
			upClan(clan);
		}
	}

	static public void delCache(Clan pb) {
		if (pb != null) {
			inlist.remove(pb);
			uplist.remove(pb);
			allList.remove(pb);
		}
		clearCache(pb);
	}

	static public void deleteClan(Clan pb) {
		delCache(pb);
		ClanJedis.delData(pb);
	}

	static public void delClanByPlayer(Player p) {
		if (p == null)
			return;

		String ccid = p.getClancid();
		Clan clan = getClanByCcid(ccid);
		if (clan == null)
			return;
		int pcid = p.getPcid();
		int post = p.getClanpost();

		if (post == ConstantType.Type_ClanMember_Admin) {
			// 删除联盟所有成员，请求，联盟本体
			deleteClan(clan);
			ProClanMember.delAllMemberInClan(ccid);
			ProClanReq.delClanReqInClan(ccid);
		} else {
			// 删除自己在联盟成员的
			Clan_member member = ProClanMember.getClanMember(ccid, pcid);
			ProClanMember.delCMem(member);
		}
		LogicPlayer.upClanCid(p, "", 0, "", 0);
	}

	static public void saveData() {
		List<Clan> list = new CopyOnWriteArrayList<Clan>();

		if (!Svc.isEmpty(uplist)) {
			for (Clan item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		ClanJedis.setList(list);

		inlist.clear();
		uplist.clear();
	}
}
