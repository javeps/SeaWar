package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.GameDelJedis;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.KeyValEntity;
import com.sea.cache.process.ProPlayer;
import com.sea.content.Svc;
import com.sea.db.bean.Player;

public class AssistUserPlayerJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(AssistUserPlayerJedis.class);

	static final String Assist_Player_Old = "k:up";

	static Map<String, String> mapPcid = null;
	static Map<String, String> mapPname = null;

	public static Map<String, String> getMapPcid(boolean isReload) {
		if (mapPcid == null || isReload) {
			mapPcid = JedisHash.getMap(_GameKeys.Assist_Player_PCID);
		}
		if (mapPcid == null) {
			mapPcid = new HashMap<String, String>();
		}
		return mapPcid;
	}

	public static Map<String, String> getMapPname(boolean isReload) {
		if (mapPname == null || isReload) {
			mapPname = JedisHash.getMap(_GameKeys.Assist_Player_Name);
		}
		if (mapPname == null) {
			mapPname = new HashMap<String, String>();
		}
		return mapPname;
	}

	public static void initMapNow() {
		getMapPcid(true);
		getMapPname(true);
	}

	public static String getValStrByPcid(String field) {
		return getMapPcid(false).get(field);
	}

	public static String getValStrByPname(String field) {
		return getMapPname(false).get(field);
	}

	// =========== Method

	private static List<KeyValEntity> getListKVE(Player item) {
		if (item == null)
			return null;

		int ucid = item.getUcid();
		int pcid = item.getPcid();
		String pname = item.getPname();

		if (pname == null || "".equals(pname.trim())) {
			return null;
		}

		String val = PlayerJedis.getKeyPlayer(ucid, pcid);

		KeyValEntity kname = new KeyValEntity(_GameKeys.Assist_Player_Name,
				pname, val);

		String fieldPcid = pcid + "";
		KeyValEntity kpcid = new KeyValEntity(_GameKeys.Assist_Player_PCID,
				fieldPcid, val);

		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		list.add(kname);
		list.add(kpcid);

		getMapPcid(false).put(fieldPcid, val);
		getMapPname(false).put(pname, val);

		return list;
	}

	public static void setUPVal(Player item) {
		List<KeyValEntity> list = getListKVE(item);
		JedisHash.setKeyValList(list);
	}

	public static void resetListAll(List<Player> origin) {
		if (Svc.isEmpty(origin))
			return;

		JedisHash.delKeys(Assist_Player_Old, _GameKeys.Assist_Player_Name,
				_GameKeys.Assist_Player_PCID);

		if (mapPcid == null)
			mapPcid = new HashMap<String, String>();

		if (mapPname == null)
			mapPname = new HashMap<String, String>();

		if (!Svc.isEmpty(mapPcid))
			mapPcid.clear();

		if (!Svc.isEmpty(mapPname))
			mapPname.clear();

		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		KeyValEntity vpcid = null;
		KeyValEntity vpname = null;
		String val = "";
		int len = origin.size();
		for (int i = 0; i < len; i++) {
			Player item = origin.get(i);
			if (item == null)
				continue;

			int guid = item.getGuid_step();
			if (guid < 100)
				continue;

			int ucid = item.getUcid();
			int pcid = item.getPcid();
			String pname = item.getPname();

			if (pname == null) {
				GameDelJedis.deletePlayer(item, true);
				continue;
			}

			pname = pname.trim();

			if ("".equals(pname)) {
				GameDelJedis.deletePlayer(item, true);
				continue;
			}

			String strPcid = String.valueOf(pcid);

			val = PlayerJedis.getKeyPlayer(ucid, pcid);
			vpcid = new KeyValEntity(_GameKeys.Assist_Player_PCID, pcid + "",
					val);
			list.add(vpcid);
			mapPcid.put(strPcid, val);

			vpname = new KeyValEntity(_GameKeys.Assist_Player_Name, pname, val);
			list.add(vpname);
			mapPname.put(pname, val);
		}
		JedisHash.setKeyValList(list);
	}

	public static void updateUPVal(Player item) {
		setUPVal(item);
	}

	public static void deleteUPname(Player item) {
		if (item == null)
			return;
		String key_pname = _GameKeys.Assist_Player_Name;
		String pname = item.getPname();
		boolean isHasPname = JedisHash.isHasHash(key_pname, pname);
		if (isHasPname) {
			getMapPname(false).remove(pname);
			JedisHash.delByKFields(key_pname, pname);
		}
	}

	public static void deleteUPVal(Player item) {
		if (item != null) {
			String pcid = item.getPcid() + "";
			String pname = item.getPname();
			getMapPname(false).remove(pname);
			getMapPcid(false).remove(pcid);
		}
	}

	public static List<Player> getListByPcid() {
		Map<String, String> map = getMapPcid(false);
		if (map == null || map.isEmpty())
			return null;
		Set<Entry<String, String>> entryes = map.entrySet();
		if (entryes == null || entryes.isEmpty())
			return null;

		List<Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>();
		list.addAll(entryes);

		List<Player> r = new ArrayList<Player>();

		for (Entry<String, String> entry : list) {
			String valKey = entry.getValue();
			Player itemPlayer = ProPlayer.getPlayerBy(valKey);
			if (itemPlayer == null)
				continue;
			r.add(itemPlayer);
		}
		return r;
	}

	static List<String> getListNameOld() {
		String key = _GameKeys.Assist_Player_Name;
		List<String> fields = JedisHash.getListHashKeys(key);
		return fields;
	}

	private static List<String> getListName() {
		Set<Entry<String, String>> entryes = getMapPname(false).entrySet();
		if (Svc.isEmpty(entryes))
			return null;

		List<Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>();
		list.addAll(entryes);

		List<String> fields = new ArrayList<String>();
		for (Entry<String, String> entry : list) {
			String key = entry.getKey();
			fields.add(key);
		}
		return fields;
	}

	public static boolean isHasName(String uname) {
		if (uname == null || "".equals(uname.trim()))
			return true;

		List<String> fields = getListName();
		if (fields == null || fields.isEmpty())
			return false;

		uname = uname.trim();
		boolean r = false;
		for (String item : fields) {
			if (item.equalsIgnoreCase(uname)) {
				r = true;
				break;
			}
		}
		return r;
	}

	public static List<String> getListNamesByLike(String uname) {
		if (uname == null || "".equals(uname.trim()))
			return null;

		List<String> fields = getListName();
		if (fields == null || fields.isEmpty())
			return null;

		uname = uname.trim();
		uname = uname.toLowerCase();

		List<String> result = new ArrayList<String>();
		String tmp = "";
		for (String item : fields) {
			tmp = item.toLowerCase();
			if (tmp.indexOf(uname) != -1) {
				String vkey = getValStrByPname(item);
				if (vkey != null && !"".equals(vkey)) {
					int index = vkey.lastIndexOf(":");
					if (index > 0) {
						item += "=" + vkey.substring(index + 1);
					}
				}
				result.add(item);
			}
		}
		return result;
	}

	public static Player getPlayerByPcid(int pcid) {
		return getPlayerBy(pcid + "", false);
	}

	public static Player getPlayerByPname(String pname) {
		return getPlayerBy(pname, true);
	}

	public static Player getPlayerBy(String field, boolean isName) {
		String valKey = null;
		if (isName) {
			valKey = getValStrByPname(field);
		} else {
			valKey = getValStrByPcid(field);
		}
		if (valKey == null || "".equals(valKey.trim()))
			return null;
		return ProPlayer.getPlayerBy(valKey);
	}
}
