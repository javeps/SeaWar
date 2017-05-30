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
import com.sea.cache.jedis.game.BuildJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_buildings;
import com.sea.logic.logicOperate.LogicBuild;
import com.sea.logic.session.LogicalSession;
import com.sea.logic.session.Session;

public class ProBuild implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProBuild.class);

	static final Map<String, Player_buildings> bcidCache = Svc.newSortedMap();
	static final Map<Integer, Set<String>> pcidCache = new ConcurrentHashMap<Integer, Set<String>>();

	static final List<Player_buildings> inlist = new CopyOnWriteArrayList<Player_buildings>();
	static final List<Player_buildings> uplist = new CopyOnWriteArrayList<Player_buildings>();

	static String getKey(Player_buildings pb) {
		if (pb == null)
			return "";
		return getKey(pb.getPcid(), pb.getBcid());
	}

	static String getKey(int pcid, int bcid) {
		return PStr.b(pcid + "_").e(bcid);
	}

	static public void loadCache(List<Player_buildings> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_buildings item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Player_buildings pb) {
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

	static public void clearCache(List<Player_buildings> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player_buildings item : origin) {
			clearCache(item);
		}
	}

	static public void clearCache(Player_buildings pb) {
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

	static public void deleteAllBuildByPlayer(Player p) {
		if (p == null)
			return;
		List<String> set = getListBySet(p);
		if (set != null) {
			for (String key : set) {
				Player_buildings pb = bcidCache.get(key);
				inlist.remove(pb);
				uplist.remove(pb);
				bcidCache.remove(key);
			}
		}
	}

	static public List<Player_buildings> getListByPlayer(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String bcids = p.getBuilds();
		return getListByPcid(pcid, bcids);
	}

	public static List<Player_buildings> getListArsenal(int pcid, String builds) {
		Session session = LogicalSession.getSessionByPcid(pcid);
		List<Player_buildings> origin = null;
		if (session != null && session.getPinfo() != null)
			origin = session.getPinfo().getBuilds();
		else
			origin = BuildJedis.getListOld(pcid, builds);

		if (Svc.isEmpty(origin))
			return null;

		List<Player_buildings> r = new ArrayList<Player_buildings>();
		for (Player_buildings item : origin) {
			boolean isArsenal = LogicBuild.isTrueArsenal(item);
			if (!isArsenal)
				continue;
			r.add(item);
		}
		return r;
	}

	static public List<Player_buildings> getListByPcid(int pcid, String bcids) {
		List<String> set = getListBySet(pcid);
		List<Player_buildings> result = null;
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
			result = BuildJedis.getList(pcid, bcids);
			loadCache(result);
		}
		return result;
	}

	static public Player_buildings getBuildByPcidBcid(int pcid, int bcid) {
		String k = getKey(pcid, bcid);
		Player_buildings pb = bcidCache.get(k);
		if (pb == null) {
			pb = BuildJedis.getBuildByPcidBcid(pcid, bcid);
		}
		loadCache(pb);
		return pb;
	}

	static public void deletePB(Player_buildings pb) {
		clearCache(pb);
		inlist.remove(pb);
		uplist.remove(pb);
		BuildJedis.delData(pb);
	}

	static public void inBuild(Player_buildings pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (!inlist.contains(pb))
			inlist.add(pb);
	}

	static public void upBuild(Player_buildings pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (inlist.contains(pb)) {
			System.out.println("更新时rm新建队列-bcid" + pb.getBcid());
			inlist.remove(pb);
		}
		if (!uplist.contains(pb))
			uplist.add(pb);
	}

	// static public void saveData() {
	// if (!Svc.isEmpty(inlist)) {
	// BuildJedis.setList(inlist);
	// }
	//
	// if (!Svc.isEmpty(uplist)) {
	// BuildJedis.setList(uplist);
	// }
	// inlist.clear();
	// uplist.clear();
	// }

	static public void saveData() {
		List<Player_buildings> list = new CopyOnWriteArrayList<Player_buildings>();

		if (!Svc.isEmpty(uplist)) {
			for (Player_buildings item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		BuildJedis.setList(list);

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
		return set.size() >= 6;
	}

	static public List<Player_buildings> getListInCache(int pcid) {
		ArrayList<Player_buildings> result = new ArrayList<Player_buildings>();
		List<String> set = getListBySet(pcid);
		if (set != null) {
			Player_buildings tmp = null;
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