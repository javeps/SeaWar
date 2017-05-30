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
import com.sea.cache.jedis.game.HeroJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_hero;

public class ProHero implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProHero.class);

	static final Map<String, Player_hero> hgidCache = Svc.newSortedMap();
	static final Map<Integer, Set<String>> pcidCache = new ConcurrentHashMap<Integer, Set<String>>();

	static final List<Player_hero> inlist = new CopyOnWriteArrayList<Player_hero>();
	static final List<Player_hero> uplist = new CopyOnWriteArrayList<Player_hero>();

	static String getKey(Player_hero pb) {
		if (pb == null)
			return "";
		return getKey(pb.getPcid(), pb.getHgid());
	}

	static String getKey(int pcid, int hgid) {
		return PStr.b(pcid + "_").e(hgid);
	}

	static public void loadCache(List<Player_hero> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_hero item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Player_hero pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		String k = getKey(pb);
		hgidCache.put(k, pb);
		Set<String> set = pcidCache.get(pcid);
		if (set == null)
			set = new HashSet<String>();
		set.add(k);
		pcidCache.put(pcid, set);
	}

	static public void clearCache(List<Player_hero> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_hero item : origin) {
			clearCache(item);
		}
	}

	static public void clearCache(Player_hero pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		String k = getKey(pb);
		hgidCache.remove(k);
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
			hgidCache.remove(key);
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
	static public boolean isHasHero(int pcid, int hgid) {
		boolean r = false;
		String key = getKey(pcid, hgid);
		r = hgidCache.containsKey(key);
		if (!r) {
			r = HeroJedis.isHasHero(pcid, hgid);
		}
		return r;
	}

	static public List<Player_hero> getListByPlayer(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String cids = p.getHeroes();
		return getListByPcid(pcid, cids);
	}

	static public List<Player_hero> getListByPcid(int pcid, String hcids) {
		List<String> set = getListBySet(pcid);
		List<Player_hero> result = null;
		if (set != null) {
			result = getListInCache(pcid);
		} else {
			result = HeroJedis.getList(pcid, hcids);
			loadCache(result);
		}
		return result;
	}

	static public Player_hero getHeroByPcidHgid(int pcid, int hgid) {
		String k = getKey(pcid, hgid);
		Player_hero pb = hgidCache.get(k);
		if (pb == null) {
			pb = HeroJedis.getHeroByPcidHgid(pcid, hgid);
		}
		loadCache(pb);
		return pb;
	}

	static public void deletePB(Player_hero pb) {
		clearCache(pb);
		inlist.remove(pb);
		uplist.remove(pb);
		HeroJedis.delData(pb);
	}

	static public void deleteAllByPlayer(Player p) {
		if (p == null)
			return;
		List<String> set = getListBySet(p);
		if (set != null) {
			for (String key : set) {
				Player_hero pb = hgidCache.get(key);
				inlist.remove(pb);
				uplist.remove(pb);
				hgidCache.remove(key);
			}
		}
	}

	static public void inBuild(Player_hero pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (!inlist.contains(pb))
			inlist.add(pb);
	}

	static public void upBuild(Player_hero pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (inlist.contains(pb))
			inlist.remove(pb);
		if (!uplist.contains(pb))
			uplist.add(pb);
	}

	// static public void saveData() {
	// if (!Svc.isEmpty(inlist)) {
	// HeroJedis.setList(inlist);
	// }
	// if (!Svc.isEmpty(uplist)) {
	// HeroJedis.setList(uplist);
	// }
	// inlist.clear();
	// uplist.clear();
	// }

	static public void saveData() {
		List<Player_hero> list = new CopyOnWriteArrayList<Player_hero>();
		if (!Svc.isEmpty(uplist)) {
			for (Player_hero item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		HeroJedis.setList(list);

		inlist.clear();
		uplist.clear();
	}

	static public void clearData() {
		saveData();
		pcidCache.clear();
		hgidCache.clear();
	}

	// =======添加一个是否保护了数据
	static public boolean isHasPcid(int pcid) {
		Set<String> set = pcidCache.get(pcid);
		if (set == null)
			return false;
		return true;
	}

	static public List<Player_hero> getListInCache(int pcid) {
		ArrayList<Player_hero> result = new ArrayList<Player_hero>();
		List<String> set = getListBySet(pcid);
		if (set != null) {
			Player_hero tmp = null;
			for (String item : set) {
				String[] s2 = item.split("_");
				if (s2.length != 2)
					continue;
				try {
					tmp = getHeroByPcidHgid(Integer.parseInt(s2[0]),
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