package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.sql.mysql.BeanSupport;
import com.bowlong.util.MapEx;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.KeyMapEntity;
import com.sea.db.bean.Attack_defense_info;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class MailFPlayerJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(MailFPlayerJedis.class);

	public static Attack_defense_info toObjByStrVal(String strVal) {
		Map map_ = UtileTools.strToMap(strVal);
		return toObjByMap(map_);
	}

	public static Attack_defense_info toObjByMap(Map map) {
		if (map == null)
			return null;
		String attmcid = MapEx.getString(map, "attmcid");
		if (attmcid == null || "".equals(attmcid.trim()))
			return null;
		int id = MapEx.getInt(map, "id");
		String builds = MapEx.getString(map, "builds");
		String obsts = MapEx.getString(map, "obsts");
		String amrys = MapEx.getString(map, "amrys");
		String teches = MapEx.getString(map, "teches");
		String heros = MapEx.getString(map, "heros");
		return Attack_defense_info.newAttack_defense_info(id, attmcid, builds,
				obsts, amrys, teches, heros);
	}

	// =========== method

	public static void delBy(String mcid) {
		String keys = getKeyMailFightBePlayer(mcid);
		JedisHash.delKeys(keys);
	}

	public static void setList(List<Attack_defense_info> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		setListDataByKMap(list);
	}

	static List<String> getListAllKeys() {
		return JedisHash
				.getListKeysByPattern(_GameKeys.Pattern_Key_Mail_Att_Pinfo);
	}

	public static Attack_defense_info getAttMail(String mcid) {
		String key = getKeyMailFightBePlayer(mcid);
		Map<String, String> map = JedisHash.getMap(key);
		Attack_defense_info info = toObjByMap(map);
		if (info == null) {
			String strVal = JedisHash.getValByKey(key);
			info = toObjByStrVal(strVal);
			if (info != null) {
				JedisHash.deleteKey(key);
				KeyMapEntity item = new KeyMapEntity(key, info.toBasicMap());
				JedisHash.setKeyMapEntity(item);
			}
		}
		return info;
	}
}
