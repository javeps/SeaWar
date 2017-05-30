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

import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.sea.cache.jedis.game.AssistUserPlayerJedis;
import com.sea.cache.jedis.game.PlayerJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.User;

public class ProPlayer implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProPlayer.class);

	static final Map<Integer, Player> pcidCache = Svc.newSortedMap();
	static final Map<Integer, Set<Integer>> ucidCache = new ConcurrentHashMap<Integer, Set<Integer>>();
	static final Map<String, Integer> pnameCache = new ConcurrentHashMap<String, Integer>();
	static final Map<Integer, String> pnameAgoCache = new ConcurrentHashMap<Integer, String>();

	/** 所有玩家 **/
	static final List<Player> alllist = new CopyOnWriteArrayList<Player>();

	static final List<Player> inlist = new CopyOnWriteArrayList<Player>();
	static final List<Player> uplist = new CopyOnWriteArrayList<Player>();

	static public void loadCache(List<Player> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Player p) {
		if (p == null)
			return;

		int pcid = p.getPcid();
		int ucid = p.getUcid();
		String pname = "";
		if (pnameAgoCache.containsKey(pcid)) {
			pname = pnameAgoCache.get(pcid);
			pnameCache.remove(pname);
		}

		pname = p.getPname();
		pcidCache.put(pcid, p);
		pnameCache.put(pname, pcid);
		pnameAgoCache.put(pcid, pname);
		Set<Integer> ucidSet = ucidCache.get(ucid);
		if (ucidSet == null)
			ucidSet = new HashSet<Integer>();
		ucidSet.add(pcid);
		ucidCache.put(ucid, ucidSet);
	}

	static public void clearCache(List<Player> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Player user : origin) {
			clearCache(user);
		}
	}

	static public void clearCache(Player p) {
		if (p == null)
			return;
		// synchronized (pcidCache) {
		int ucid = p.getUcid();
		int pcid = p.getPcid();
		String pname = p.getPname();
		pcidCache.remove(pcid);
		pnameCache.remove(pname);
		pnameAgoCache.remove(pcid);
		Set<Integer> lgSet = ucidCache.get(ucid);
		if (lgSet == null || lgSet.isEmpty())
			return;
		int len = lgSet.size();
		if (len > 1) {
			lgSet.remove(pcid);
			ucidCache.put(ucid, lgSet);
		} else {
			ucidCache.remove(ucid);
		}
		// }
	}

	static public void clearCacheByPcid(int pcid) {
		Player p = pcidCache.get(pcid);
		if (p == null)
			return;
		clearCache(p);
	}

	// 取得数据
	static public List<Player> getListByUser(User u) {
		if (u == null)
			return null;
		int ucid = u.getUcid();
		String pcids = u.getPcids();
		return getListByUcid(ucid, pcids);
	}

	static public List<Player> getListByUcid(int ucid, String pcids) {
		Set<Integer> set = ucidCache.get(ucid);
		List<Player> list = null;
		if (set != null) {
			list = new ArrayList<Player>();
			Player p = null;
			for (Integer pcid : set) {
				p = getPlayerByPcidUcid(pcid, ucid);
				if (p == null)
					continue;
				list.add(p);
			}
		} else {
			list = PlayerJedis.getList(pcids, ucid);
			loadCache(list);
		}
		return list;
	}

	static public Player getPlayerBy(String key) {
		if (key == null || "".equals(key))
			return null;
		key = key.replaceAll("[a-zA-Z]+", "");
		String[] dstr = key.split(":");
		int len = dstr.length;
		String ucidStr = "";
		String pcidStr = "";
		for (int i = 0; i < len; i++) {
			String id = dstr[i];
			if (id == null || "".equals(id))
				continue;
			if ("".equals(ucidStr)) {
				ucidStr = id;
				continue;
			}
			if ("".equals(pcidStr)) {
				pcidStr = id;
				continue;
			}
		}

		int ucid = 0;
		int pcid = 0;
		try {
			ucid = Integer.parseInt(ucidStr);
			pcid = Integer.parseInt(pcidStr);
		} catch (Exception e) {
		}

		if (pcid == 0)
			return null;

		return getPlayerByPcidUcid(pcid, ucid);
	}

	static public boolean isHas(int pcid) {
		return pcidCache.containsKey(pcid);
	}

	static public Player getPlayerByPcidUcid(int pcid, int ucid) {
		Player p = null;
		// synchronized (pcidCache) {
		p = pcidCache.get(pcid);
		if (p == null) {
			p = PlayerJedis.getPlayerBy(ucid, pcid);
		}
		loadCache(p);
		// }
		return p;
	}

	static public Player getPlayerByPcid(int pcid) {
		if (pcid == 0)
			return null;
		Player p = null;
		// synchronized (pcidCache) {
		p = pcidCache.get(pcid);
		if (p == null) {
			p = AssistUserPlayerJedis.getPlayerByPcid(pcid);
		}
		loadCache(p);
		// }
		return p;
	}

	static public Player getPlayerByPname(String pname) {
		if (Svc.isEmpty(pname))
			return null;
		Player p = AssistUserPlayerJedis.getPlayerByPname(pname);
		loadCache(p);
		return p;
	}

	static protected boolean isLoadAll = false;

	static public List<Player> getListAll() {
		if (!isLoadAll) {
			isLoadAll = true;
			List<Player> list = PlayerJedis.getListAll();
			if (!Svc.isEmpty(list))
				alllist.addAll(list);
		}

		if (pcidCache.size() != alllist.size()) {
			loadCache(alllist);
		}
		return alllist;
	}

	static public void loadAll() {
		getListAll();
	}

	static public void delPlayers() {
		delPlayers(300);
	}

	static public void delPlayers(int delNum) {
		if (!isBlDeAll4Third) {
			isBlDeAll4Third = true;
			delPlayer4Third();
			return;
		}

		clearDataByTime();
		List<Player> all = getListAll();
		PlayerJedis.delPlayers(all, delNum);
	}

	static public void delCache(Player p) {
		clearCache(p);
		if (p != null) {
			inlist.remove(p);
			uplist.remove(p);
			alllist.remove(p);
		}
	}

	static public void delData(Player p) {
		delCache(p);
		PlayerJedis.delData(p);
	}

	static public void setPlayer(Player p) {
		if (p == null)
			return;

		loadCache(p);

		if (!inlist.contains(p)) {
			inlist.add(p);
		}

		if (!alllist.contains(p)) {
			alllist.add(p);
		}
	}

	static public void upPlayer(Player p) {
		if (p == null)
			return;
		loadCache(p);
		if (inlist.contains(p))
			inlist.remove(p);
		if (!uplist.contains(p)) {
			uplist.add(p);
		}
	}

	// static public void saveData() {
	// if (!Svc.isEmpty(inlist)) {
	// PlayerJedis.setList(inlist);
	// }
	// if (!Svc.isEmpty(uplist)) {
	// PlayerJedis.setList(uplist);
	// }
	// inlist.clear();
	// uplist.clear();
	// }

	static public void saveData() {
		List<Player> list = new CopyOnWriteArrayList<Player>();

		if (!Svc.isEmpty(uplist)) {
			for (Player item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		PlayerJedis.setList(list);

		inlist.clear();
		uplist.clear();
	}

	static public void savePlInfoData() {
		// synchronized (pcidCache) {
		saveData();
		// 一下数据是对象
		ProBuild.saveData();
		ProObst.saveData();
		ProHero.saveData();
		ProTech.saveData();
		ProAmry.saveData();
		ProProduct.saveData();
		// }
	}

	static public void clearData() {
		// synchronized (pcidCache) {
		saveData();
		pcidCache.clear();
		ucidCache.clear();
		pnameCache.clear();
		pnameAgoCache.clear();

		// 一下数据是对象
		ProBuild.clearData();
		ProObst.clearData();
		ProHero.clearData();
		ProTech.clearData();
		ProAmry.clearData();
		ProProduct.clearData();
		// }
	}

	static void clearPInfoByPcid(int pcid) {
		Player p = pcidCache.get(pcid);
		if (p != null && p.getIsonline())
			return;
		ProBuild.clearCache(pcid);
		ProObst.clearCache(pcid);
		ProHero.clearCache(pcid);
		ProTech.clearCache(pcid);
		ProAmry.clearCache(pcid);
		ProProduct.clearCache(pcid);
	}

	static public void clearDataByTime() {
		// synchronized (pcidCache) {
		saveData();
		ProBuild.saveData();
		ProObst.saveData();
		ProHero.saveData();
		ProTech.saveData();
		ProAmry.saveData();
		ProProduct.saveData();

		long now_time = System.currentTimeMillis();
		long diff_time = now_time - DateEx.TIME_HOUR * 3;

		Set<Integer> keys = pcidCache.keySet();
		List<Integer> listKeys = new ArrayList<Integer>();
		listKeys.addAll(keys);
		for (Integer key : listKeys) {
			Player p = pcidCache.get(key);
			if (p == null)
				continue;
			boolean isOnline = p.getIsonline();
			if (isOnline)
				continue;
			long t = p.getLastlogintime();
			if (t <= diff_time)
				clearPInfoByPcid(key);
		}
		// }
	}

	// 删除3个月没有登录过的用户帐号(包括充值帐号)
	static boolean isBlDeAll4Third = true;

	static private void delPlayer4Third() {
		List<Player> all = getListAll();
		if (ListEx.isEmpty(all))
			return;
		List<Player> delList = new ArrayList<Player>();
		long diffTime = DateEx.TIME_DAY * 30 * 3;
		long nowTime = System.currentTimeMillis();

		int allLen = all.size();
		int delLen = 0;
		int resLen = 0;

		for (int i = 0; i < allLen; i++) {
			Player p = all.get(i);
			if (p == null)
				continue;

			if (p.getIsonline())
				continue;

			String ccid = p.getClancid();
			if (ccid != null) {
				ccid = ccid.trim();
				if (!Svc.isEmpty(ccid) && !"0".equals(ccid)) {
					continue;
				}
			}

			long lastLogin = p.getLastlogintime();
			lastLogin += diffTime;
			if (lastLogin <= nowTime) {
				long ptime = p.getProtecttime();
				if (ptime <= nowTime) {
					delList.add(p);
				}
			}
		}

		delLen = delList.size();
		resLen = allLen - delLen;
		log.info("==删除3个月没有登录过的用户帐号==总计====" + allLen + ",剩余:" + resLen + ",删除：" + delLen);
		PlayerJedis.delPlayersByDelList(delList);
	}
}