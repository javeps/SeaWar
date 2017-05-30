package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.JedisPipeline;
import com.sea.cache.jedis.origin.KeyValEntity;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.User;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class UserJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(UserJedis.class);

	public static String getValStr(User o) {
		String r = "";
		if (o == null)
			return r;
		Map map = o.toBasicMap();
		r = UtileTools.mapToStr(map);
		return r;
	}

	public static User getValUser(String strVal) {
		User r = getUserByStrVal(strVal);
		if (r == null || r.getUcid() == 0)
			return null;
		return r;
	}

	public static User getUserByStrVal(String strVal) {
		User r = null;
		Map map_ = UtileTools.strToMap(strVal);
		if (Svc.isEmpty(map_))
			return r;
		int ucid = MapEx.getInt(map_, "ucid");
		int id = MapEx.getInt(map_, "id");
		String login_pwd = MapEx.getString(map_, "login_pwd");
		String name = MapEx.getString(map_, "name");
		String login_uid = MapEx.getString(map_, "login_uid");
		String uuid = MapEx.getString(map_, "uuid");
		String model = MapEx.getString(map_, "model");
		String version = MapEx.getString(map_, "version");
		String remain = MapEx.getString(map_, "remain");
		long login_time = MapEx.getLong(map_, "login_time");
		String pcids = MapEx.getString(map_, "pcids");
		r = User.newUser(id, ucid, login_pwd, name, login_uid, uuid,
				login_time, model, version, remain, pcids);
		return r;
	}

	private static List<KeyValEntity> getKVEByUser(User u) {
		if (u == null)
			return null;
		String loginId = u.getLogin_uid();
		String loginPwd = u.getLogin_pwd();
		int ucid = u.getUcid();
		String key = getUKey(loginId, loginPwd);
		String field = ucid + "";
		String value = getValStr(u);
		KeyValEntity ku = new KeyValEntity(key, field, value);
		KeyValEntity kuassist = new KeyValEntity(_GameKeys.Assist_User_Ucid,
				field, key);

		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		list.add(ku);
		list.add(kuassist);
		return list;
	}

	/*** 重新修复数据 ****/
	static public void resetAssistUser(List<User> list) {
		if (Svc.isEmpty(list))
			return;

		JedisHash.delKeys(_GameKeys.Assist_User_Ucid);

		List<KeyValEntity> res = new ArrayList<KeyValEntity>();
		int len = list.size();

		for (int i = 0; i < len; i++) {
			User en = list.get(i);
			if (en == null)
				continue;
			String key = getUKey(en.getLogin_uid(), en.getLogin_pwd());
			String field = String.valueOf(en.getUcid());
			KeyValEntity v = new KeyValEntity(_GameKeys.Assist_User_Ucid,
					field, key);
			res.add(v);
		}

		JedisPipeline.setKeyValList(res);
	}

	public static void setUser(User u) {
		List<KeyValEntity> list = getKVEByUser(u);
		JedisPipeline.setKeyValList(list);
	}

	public static void setList(List<User> origin) {
		if (origin == null || origin.isEmpty())
			return;
		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		for (User user : origin) {
			List<KeyValEntity> tmp = getKVEByUser(user);
			if (tmp == null || tmp.isEmpty())
				continue;
			list.addAll(tmp);
		}
		JedisPipeline.setKeyValList(list);
	}

	public static void deleteUser(User u) {
		if (u == null)
			return;
		String loginId = u.getLogin_uid();
		String loginPwd = u.getLogin_pwd();
		int ucid = u.getUcid();
		String key = getUKey(loginId, loginPwd);
		String field = ucid + "";
		Map<String, String> delMap = new HashMap<String, String>();
		delMap.put(key, field);
		delMap.put(_GameKeys.Assist_User_Ucid, field);
		JedisHash.delByMapKF(delMap);
	}

	public static List<User> getUsers(String loginId, String loginPwd)
			throws Exception {
		String key = getUKey(loginId, loginPwd);
		Map map = JedisHash.getMapListVals(key);
		boolean isNull = MapEx.getBoolean(map, "isNull");
		if (isNull) {
			throw new Exception("redis用完了!");
		}
		List vals = MapEx.getList(map, "list");
		if (vals == null || vals.isEmpty())
			return null;

		List<User> list = new ArrayList<User>();
		for (Object obj : vals) {
			if (obj == null)
				continue;
			User u = getValUser((String) obj);
			if (u == null)
				continue;
			list.add(u);
		}
		return list;
	}

	public static User getUserByUcid(int ucid) {
		if (ucid == 0)
			return null;
		String field = ucid + "";
		String key = JedisHash.getValHash(_GameKeys.Assist_User_Ucid, field);
		String val = JedisHash.getValHash(key, field);
		return getValUser(val);
	}

	public static void delUserByPlayer(Player p, boolean isDel) {
		if (p == null)
			return;
		int ucid = p.getUcid();
		int pcid = p.getPcid();
		User u = getUserByUcid(ucid);
		if (isDel) {
			deleteUser(u);
		} else {
			String pcids = u.getPcids();
			if (pcids == null || "".equals(pcids.trim()))
				return;
			pcids = pcids.replaceAll(pcid + ",", "");
			u.setPcids(pcids);
			setUser(u);
		}
	}

	// 新的用户列表
	public static String getUKey(String lgid, String lgpwd) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_User, lgid, lgpwd);
	}

	private static List<User> getListAllByNewInMap(String key) {
		List<String> vals = JedisHash.getListHashVals(key);
		if (vals == null || vals.size() <= 0) {
			log.info("用户数据为空 key:" + key);
			return null;
		}

		List<String> delKeys = new ArrayList<String>();

		List<User> r = new ArrayList<User>();
		for (String v : vals) {
			User u = getValUser(v);
			if (u == null) {
				u = getUserByStrVal(v);
				if (u != null) {
					String delKey = getUKey(u.getLogin_uid(), u.getLogin_pwd());
					delKeys.add(delKey);
				}
				continue;
			}
			r.add(u);
		}

		JedisHash.delKeysByList(delKeys);

		return r;
	}

	private static List<String> getListAllKeys() {
		return JedisHash.getListKeysByPattern(_GameKeys.Pattern_Key_User_List);
	}

	static public List<User> getListAllByNew() {
		List<String> keyes = getListAllKeys();
		List<User> r = null;
		if (keyes == null || keyes.size() <= 0)
			return r;
		r = new ArrayList<User>();
		for (String k : keyes) {
			List<User> ulist = getListAllByNewInMap(k);
			if (ulist == null || ulist.isEmpty())
				continue;
			r.addAll(ulist);
		}
		return r;
	}
}