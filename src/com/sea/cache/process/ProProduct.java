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
import com.sea.cache.jedis.game.ProductJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_producting;

public class ProProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProProduct.class);

	static final Map<String, Player_producting> acidCache = Svc.newSortedMap();
	static final Map<Integer, Set<String>> pcidCache = new ConcurrentHashMap<Integer, Set<String>>();

	static final List<Player_producting> inlist = new CopyOnWriteArrayList<Player_producting>();
	static final List<Player_producting> uplist = new CopyOnWriteArrayList<Player_producting>();

	static String getKey(Player_producting pb) {
		if (pb == null)
			return "";
		return getKey(pb.getPcid(), pb.getBcid(), pb.getGid());
	}

	static String getKey(int pcid, int bcid, int gid) {
		return PStr.b(pcid + "_").e(bcid + "_" + gid);
	}

	static public void loadCache(List<Player_producting> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_producting item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Player_producting pb) {
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

	static public void clearCache(List<Player_producting> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_producting item : origin) {
			clearCache(item);
		}
	}

	static public void clearCache(Player_producting pb) {
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

	static public List<Player_producting> getListByPlayer(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String bcids = p.getBuilds();
		return getListByPcid(pcid, bcids);
	}

	static public List<Player_producting> getListByPcid(int pcid) {
		return getListByPcid(pcid, "");
	}

	public static List<Player_producting> getListByPcidBcid(int pcid, int bcid) {
		List<Player_producting> list = getListByPcid(pcid, "");
		List<Player_producting> result = new ArrayList<Player_producting>();
		for (Player_producting item : list) {
			if (item.getBcid() == bcid) {
				result.add(item);
			}
		}
		return result;
	}

	static public List<Player_producting> getListByPcid(int pcid, String bcids) {
		List<String> set = getListBySet(pcid);
		List<Player_producting> result = null;
		if (set != null) {
			result = getListInCache(pcid);
		} else {
			result = ProductJedis.getList(pcid, bcids);
			loadCache(result);
		}
		return result;
	}

	static public Player_producting getArmy(int pcid, int bcid, int gid) {
		String k = getKey(pcid, bcid, gid);
		Player_producting pb = acidCache.get(k);
		if (pb == null) {
			pb = ProductJedis.getArmy(pcid, bcid, gid);
		}
		loadCache(pb);
		return pb;
	}

	static public boolean isProducting(int pcid, int bcid) {
		for (int i = 1008; i < 1017; i++) {
			String key = getKey(pcid, bcid, i);
			if (acidCache.containsKey(key))
				return true;
		}
		return false;
	}

	static public void deleteData(Player_producting pb) {
		clearCache(pb);
		inlist.remove(pb);
		uplist.remove(pb);
		ProductJedis.deleteData(pb);
	}

	static public void inEntity(Player_producting pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (!inlist.contains(pb))
			inlist.add(pb);
	}

	static public void upEntity(Player_producting pb) {
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
		List<Player_producting> list = new CopyOnWriteArrayList<Player_producting>();

		if (!Svc.isEmpty(uplist)) {
			for (Player_producting item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		ProductJedis.setList(list);

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

	static public List<Player_producting> getListInCache(int pcid) {
		ArrayList<Player_producting> result = new ArrayList<Player_producting>();
		List<String> set = getListBySet(pcid);
		if (set != null) {
			Player_producting tmp = null;
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