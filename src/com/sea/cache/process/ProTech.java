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
import com.sea.cache.jedis.game.TechJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_tech;

public class ProTech implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProTech.class);

	static final Map<String, Player_tech> tgidCache = Svc.newSortedMap();
	static final Map<Integer, Set<String>> pcidCache = new ConcurrentHashMap<Integer, Set<String>>();

	static final List<Player_tech> inlist = new CopyOnWriteArrayList<Player_tech>();
	static final List<Player_tech> uplist = new CopyOnWriteArrayList<Player_tech>();

	static String getKey(Player_tech pb) {
		if (pb == null)
			return "";
		return getKey(pb.getPcid(), pb.getGid());
	}

	static String getKey(int pcid, int gid) {
		return PStr.b(pcid + "_").e(gid);
	}

	static public void loadCache(List<Player_tech> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_tech item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Player_tech pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		String k = getKey(pb);
		tgidCache.put(k, pb);
		Set<String> set = pcidCache.get(pcid);
		if (set == null)
			set = new HashSet<String>();
		set.add(k);
		pcidCache.put(pcid, set);
	}

	static public void clearCache(List<Player_tech> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_tech item : origin) {
			clearCache(item);
		}
	}

	static public void clearCache(Player_tech pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		String k = getKey(pb);
		tgidCache.remove(k);
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
			tgidCache.remove(key);
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
	static public List<Player_tech> getListByPlayer(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String cids = p.getTeches();
		return getListByPcid(pcid, cids);
	}

	static public List<Player_tech> getListByPcid(int pcid, String cids) {
		List<String> set = getListBySet(pcid);
		List<Player_tech> result = null;
		boolean isNotNull = cids != null && !cids.isEmpty();
		int len = 0;
		if (isNotNull) {
			String[] bes = cids.split(",");
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
			result = TechJedis.getList(pcid, cids);
			loadCache(result);
		}
		return result;
	}

	static public Player_tech getHeroByPcidGid(int pcid, int gid) {
		String k = getKey(pcid, gid);
		Player_tech pb = tgidCache.get(k);
		if (pb == null) {
			pb = TechJedis.getTech(pcid, gid);
		}
		loadCache(pb);
		return pb;
	}

	static public void deletePB(Player_tech pb) {
		clearCache(pb);
		inlist.remove(pb);
		uplist.remove(pb);
		TechJedis.delData(pb);
	}

	static public void deleteAllByPlayer(Player p) {
		if (p == null)
			return;
		List<String> set = getListBySet(p);
		if (set != null) {
			for (String key : set) {
				Player_tech pb = tgidCache.get(key);
				inlist.remove(pb);
				uplist.remove(pb);
				tgidCache.remove(key);
			}
		}
	}

	static public void inBuild(Player_tech pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (!inlist.contains(pb))
			inlist.add(pb);
	}

	static public void upBuild(Player_tech pb) {
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
	// TechJedis.setList(inlist);
	// }
	// if (Svc.isEmpty(uplist)) {
	// TechJedis.setList(uplist);
	// }
	// uplist.clear();
	// inlist.clear();
	// }

	static public void saveData() {
		List<Player_tech> list = new CopyOnWriteArrayList<Player_tech>();

		if (!Svc.isEmpty(uplist)) {
			for (Player_tech item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		TechJedis.setList(list);

		uplist.clear();
		inlist.clear();
	}

	static public void clearData() {
		saveData();
		pcidCache.clear();
		tgidCache.clear();
	}

	// =======添加一个是否保护了数据
	static public boolean isHasPcid(int pcid) {
		Set<String> set = pcidCache.get(pcid);
		if (set == null)
			return false;
		return true;
	}
	
	static public List<Player_tech> getListInCache(int pcid) {
		ArrayList<Player_tech> result = new ArrayList<Player_tech>();
		List<String> set = getListBySet(pcid);
		if(set != null){
			Player_tech tmp = null;
			for (String item : set) {
				String[] s2 = item.split("_");
				if (s2.length != 2)
					continue;
				try {
					tmp = getHeroByPcidGid(Integer.parseInt(s2[0]),
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