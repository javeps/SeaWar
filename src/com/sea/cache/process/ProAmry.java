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
import com.sea.cache.jedis.game.ArmyJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_armys;

public class ProAmry implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProAmry.class);

	static final Map<String, Player_armys> acidCache = Svc.newSortedMap();
	static final Map<Integer, Set<String>> pcidCache = new ConcurrentHashMap<Integer, Set<String>>();

	static final List<Player_armys> inlist = new CopyOnWriteArrayList<Player_armys>();
	static final List<Player_armys> uplist = new CopyOnWriteArrayList<Player_armys>();

	static String getKey(Player_armys pb) {
		if (pb == null)
			return "";
		return getKey(pb.getPcid(), pb.getBcid(), pb.getGid());
	}

	static String getKey(int pcid, int bcid, int gid) {
		return PStr.b(pcid + "_").e(bcid + "_" + gid);
	}

	static public void loadCache(List<Player_armys> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_armys item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Player_armys pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		String k = getKey(pb);
		acidCache.put(k, pb);
		Set<String> set = pcidCache.get(pcid);
		if (set == null)
			set = new HashSet<String>();
		set.add(k);
		pcidCache.put(pcid, set);
	}

	static public void clearCache(List<Player_armys> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_armys item : origin) {
			clearCache(item);
		}
	}

	static public void clearCache(Player_armys pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		String k = getKey(pb);
		acidCache.remove(k);
		Set<String> set = pcidCache.get(pcid);
		if (set == null || set.isEmpty())
			return;
		int len = set.size();
		if (len > 0) {
			set.remove(k);
			pcidCache.put(pcid, set);
		} else {
			pcidCache.remove(pcid);
		}
	}

	static public void clearCache(int pcid) {
		Set<String> set = pcidCache.get(pcid);
		if (set == null)
			return;
		for (String key : set) {
			acidCache.remove(key);
		}
		pcidCache.remove(pcid);
	}

	static public List<String> getListBySet(Player p) {
		if (p == null)
			return null;
		return getListBySet(p.getPcid());
	}

	static public List<String> getListBySet(int pcid) {
		Set<String> set = pcidCache.get(pcid);
		if (Svc.isEmpty(set))
			return null;
		List<String> result = new ArrayList<String>();
		result.addAll(set);
		return result;
	}
	
	// 取得数据

	static public List<Player_armys> getListByPlayer(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String bcids = p.getBuilds();
		return getListByPcid(pcid, bcids);
	}

	static public List<Player_armys> getListByPcid(int pcid, String bcids) {
		List<String> set = getListBySet(pcid);
		List<Player_armys> result = null;
		if (set != null) {
			result = getListInCache(pcid);
		} else {
			result = ArmyJedis.getList(pcid, bcids);
			loadCache(result);
		}
		return result;
	}

	static public Player_armys getArmy(int pcid, int bcid, int gid) {
		String k = getKey(pcid, bcid, gid);
		Player_armys pb = acidCache.get(k);
		if (pb == null) {
			pb = ArmyJedis.getArmy(pcid, bcid, gid);
		}
		loadCache(pb);
		return pb;
	}

	static public void deleteData(Player_armys pb) {
		clearCache(pb);
		inlist.remove(pb);
		uplist.remove(pb);
		ArmyJedis.deleteData(pb);
	}

	static public void inEntity(Player_armys pb) {
		if (pb == null)
			return;

		loadCache(pb);

		if (!inlist.contains(pb))
			inlist.add(pb);
	}

	static public void upEntity(Player_armys pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (inlist.contains(pb)) {
			inlist.remove(pb);
		}
		if (!uplist.contains(pb))
			uplist.add(pb);
	}

	static public void saveData() {
		List<Player_armys> list = new CopyOnWriteArrayList<Player_armys>();

		if (!Svc.isEmpty(uplist)) {
			for (Player_armys item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		ArmyJedis.setList(list);

		inlist.clear();
		uplist.clear();
	}

	static public void clearData() {
		saveData();
		pcidCache.clear();
		acidCache.clear();
	}

	// =======添加一个是否保护了数据
	static public boolean isHasPcid(int pcid) {
		Set<String> set = pcidCache.get(pcid);
		if (set == null)
			return false;
		return true;
	}
	
	static public List<Player_armys> getListInCache(int pcid) {
		ArrayList<Player_armys> result = new ArrayList<Player_armys>();
		List<String> set = getListBySet(pcid);
		if(set != null){
			Player_armys tmp = null;
			for (String item : set) {
				String[] s2 = item.split("_");
				if (s2.length != 3)
					continue;
				try {
					tmp = getArmy(Integer.parseInt(s2[0]),
							Integer.parseInt(s2[1]), Integer.parseInt(s2[2]));
				} catch (Exception e) {
				}

				if (tmp == null)
					continue;

				result.add(tmp);
			}
		}
		return result;
	}
}