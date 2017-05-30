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
import com.sea.cache.jedis.game.ObstJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_build_obst;

public class ProObst implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProObst.class);

	static final Map<String, Player_build_obst> bcidCache = Svc.newSortedMap();
	static final Map<Integer, Set<String>> pcidCache = new ConcurrentHashMap<Integer, Set<String>>();

	static final List<Player_build_obst> inlist = new CopyOnWriteArrayList<Player_build_obst>();
	static final List<Player_build_obst> uplist = new CopyOnWriteArrayList<Player_build_obst>();

	static String getKey(Player_build_obst pb) {
		if (pb == null)
			return "";
		return getKey(pb.getPcid(), pb.getBcid());
	}

	static String getKey(int pcid, int ocid) {
		return PStr.b(pcid + "_").e(ocid);
	}

	static public void loadCache(List<Player_build_obst> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_build_obst item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Player_build_obst pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		String k = getKey(pb);
		bcidCache.put(k, pb);
		Set<String> set = pcidCache.get(pcid);
		if (set == null)
			set = new HashSet<String>();
		set.add(k);
		pcidCache.put(pcid, set);
	}

	static public void clearCache(List<Player_build_obst> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_build_obst item : origin) {
			clearCache(item);
		}
	}

	static public void clearCache(Player_build_obst pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		String k = getKey(pb);
		bcidCache.remove(k);
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
			bcidCache.remove(key);
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
	static public List<Player_build_obst> getListByPlayer(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String cids = p.getObstes();
		return getListByPcid(pcid, cids);
	}

	static public List<Player_build_obst> getListByPcid(int pcid, String bcids) {
		List<String> set = getListBySet(pcid);
		List<Player_build_obst> result = null;
		boolean isNotNull = bcids != null && !bcids.isEmpty();
		int len = 0;
		if (isNotNull) {
			String[] bes = bcids.split(",");
			for (String item : bes) {
				if (item == null || "".equals(item.trim()))
					continue;
				len++;
			}
		}

		boolean isSet = set != null;
		if (isNotNull) {
			isSet = isSet && len == set.size();
		}
		if (isSet) {
			result = getListInCache(pcid);
		} else {
			result = ObstJedis.getList(pcid, bcids);
			loadCache(result);
		}
		return result;
	}

	static public Player_build_obst getBuildByPcidBcid(int pcid, int ocid) {
		String k = getKey(pcid, ocid);
		Player_build_obst pb = bcidCache.get(k);
		if (pb == null) {
			pb = ObstJedis.getBuildByPcidBcid(pcid, ocid);
		}
		loadCache(pb);
		return pb;
	}

	static public void deletePB(Player_build_obst pb) {
		clearCache(pb);
		inlist.remove(pb);
		uplist.remove(pb);
		ObstJedis.delData(pb);
	}

	static public void deleteAllBuildByPlayer(Player p) {
		if (p == null)
			return;
		List<String> set = getListBySet(p);
		if (set != null) {
			for (String key : set) {
				Player_build_obst pb = bcidCache.get(key);
				inlist.remove(pb);
				uplist.remove(pb);
				bcidCache.remove(key);
			}
		}
	}

	static public void inBuild(Player_build_obst pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (!inlist.contains(pb))
			inlist.add(pb);
	}

	static public void upBuild(Player_build_obst pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (inlist.contains(pb))
			inlist.remove(pb);
		if (!uplist.contains(pb))
			uplist.add(pb);
	}

	// static public void saveData() {
	// if (Svc.isEmpty(inlist)) {
	// ObstJedis.setList(inlist);
	// }
	// if (Svc.isEmpty(uplist)) {
	// ObstJedis.setList(uplist);
	// }
	// inlist.clear();
	// uplist.clear();
	// }

	static public void saveData() {
		List<Player_build_obst> list = new CopyOnWriteArrayList<Player_build_obst>();

		if (!Svc.isEmpty(uplist)) {
			for (Player_build_obst item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		ObstJedis.setList(list);

		inlist.clear();
		uplist.clear();
	}

	static public void clearData() {
		saveData();
		pcidCache.clear();
		bcidCache.clear();
	}

	// =======添加一个是否保护了数据
	static public boolean isHasPcid(int pcid) {
		Set<String> set = pcidCache.get(pcid);
		if (set == null)
			return false;
		return true;
	}
	
	static public List<Player_build_obst> getListInCache(int pcid) {
		List<Player_build_obst> result = new ArrayList<Player_build_obst>();
		List<String> set = getListBySet(pcid);
		if(set != null){
			Player_build_obst tmp = null;
			for (String item : set) {
				String[] s2 = item.split("_");
				if (s2.length != 2)
					continue;
				try {
					tmp = getBuildByPcidBcid(Integer.parseInt(s2[0]),
							Integer.parseInt(s2[1]));
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