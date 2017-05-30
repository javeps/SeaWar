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
import com.sea.cache.jedis.game.UserJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.User;

public class ProUser implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProUser.class);

	static final Map<Integer, User> ucidCache = Svc.newSortedMap();
	static final Map<String, Set<Integer>> lgCache = new ConcurrentHashMap<String, Set<Integer>>();

	static final List<User> inlist = new CopyOnWriteArrayList<User>();
	static final List<User> uplist = new CopyOnWriteArrayList<User>();

	static String getLgKey(User u) {
		if (u == null)
			return "";
		return getLgKey(u.getLogin_uid(), u.getLogin_pwd());
	}

	static String getLgKey(String lgid, String lgpwd) {
		return PStr.b(lgid).e(lgpwd);
	}

	static public void loadCache(List<User> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (User user : origin) {
			loadCache(user);
		}
	}

	static public void loadCache(User u) {
		if (u == null)
			return;
		int ucid = u.getUcid();
		ucidCache.put(ucid, u);
		String lgKey = getLgKey(u);
		Set<Integer> lgSet = lgCache.get(lgKey);
		if (lgSet == null)
			lgSet = new HashSet<Integer>();
		lgSet.add(ucid);
		lgCache.put(lgKey, lgSet);
	}

	static public void clearCache(List<User> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (User user : origin) {
			clearCache(user);
		}
	}

	static public void clearCache(User u) {
		if (u == null)
			return;
		int ucid = u.getUcid();
		ucidCache.remove(ucid);
		String lgKey = getLgKey(u);
		Set<Integer> lgSet = lgCache.get(lgKey);
		if (lgSet == null || lgSet.isEmpty())
			return;
		int len = lgSet.size();
		if (len > 1) {
			lgSet.remove(ucid);
			lgCache.put(lgKey, lgSet);
		} else {
			lgCache.remove(lgKey);
		}
	}

	// 取得数据
	static public User getUserByUcid(int ucid) {
		User u = ucidCache.get(ucid);
		if (u == null) {
			u = UserJedis.getUserByUcid(ucid);
		}
		loadCache(u);
		return u;
	}

	static public List<User> getUsers(String loginId, String loginPwd)
			throws Exception {
		String key = getLgKey(loginId, loginPwd);
		Set<Integer> set = lgCache.get(key);
		List<User> users = new ArrayList<User>();
		if (set != null) {
			User u = null;
			for (Integer ucid : set) {
				u = getUserByUcid(ucid);
				if (u == null)
					continue;
				users.add(u);
			}
		} else {
			users = UserJedis.getUsers(loginId, loginPwd);
			loadCache(users);
		}
		return users;
	}

	static public User getUser(String loginId, String loginPwd)
			throws Exception {
		List<User> list = getUsers(loginId, loginPwd);
		if (Svc.isEmpty(list))
			return null;
		return list.get(0);
	}

	static protected boolean isLoadAll = false;
	/** 所有玩家 **/
	static final List<User> alllist = new CopyOnWriteArrayList<User>();

	static public List<User> getListAll() {
		if (!isLoadAll) {
			isLoadAll = true;
			List<User> list = UserJedis.getListAllByNew();
			if (!Svc.isEmpty(list))
				alllist.addAll(list);
		}
		if (ucidCache.size() != alllist.size()) {
			loadCache(alllist);
		}
		return alllist;
	}

	static public void inUser(User u) {
		if (u == null)
			return;

		loadCache(u);
		if (!inlist.contains(u)) {
			inlist.add(u);
		}

		if (!alllist.contains(u)) {
			alllist.add(u);
		}
	}

	static public void upUser(User u) {
		if (u == null)
			return;

		loadCache(u);

		if (inlist.contains(u)) {
			inlist.remove(u);
		}

		if (!uplist.contains(u)) {
			uplist.add(u);
		}
	}

	public static void delUser(User u) {
		clearCache(u);
		inlist.remove(u);
		uplist.remove(u);
		alllist.remove(u);
		UserJedis.deleteUser(u);
	}

	static public void delUserByPlayer(Player p, boolean isDel) {
		if (p != null) {
			int ucid = p.getUcid();
			User u = ucidCache.get(ucid);
			inlist.remove(u);
			uplist.remove(u);
			alllist.remove(u);
			if (isDel) {
				if (u != null) {
					clearCache(ucidCache.get(ucid));
				}
			} else {
				int pcid = p.getPcid();
				String pcids = u.getPcids();
				if (pcids == null || "".equals(pcids.trim()))
					return;
				pcids = pcids.replaceAll(pcid + ",", "");
				u.setPcids(pcids);
				upUser(u);
			}
		}
		UserJedis.delUserByPlayer(p, isDel);
	}

	static public void saveData() {
		List<User> list = new CopyOnWriteArrayList<User>();

		if (!Svc.isEmpty(uplist)) {
			for (User item : uplist) {
				list.add(item);
				inlist.remove(item);
			}
		}

		if (!Svc.isEmpty(inlist)) {
			list.addAll(inlist);
		}

		UserJedis.setList(list);

		uplist.clear();
		inlist.clear();
	}

	static public void clearData() {
		saveData();
		ucidCache.clear();
		lgCache.clear();
	}
}