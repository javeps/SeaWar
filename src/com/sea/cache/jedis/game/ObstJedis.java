package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.cache.jedis.GameDelJedis;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.KeyValEntity;
import com.sea.cache.process.PInfoAll;
import com.sea.content.Svc;
import com.sea.db.bean.Player_build_obst;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class ObstJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ObstJedis.class);

	public static Player_build_obst toObjByStrVal(String strVal) {
		Map map_ = UtileTools.strToMap(strVal);
		return toObjByMap(map_);
	}

	public static Player_build_obst toObjByMap(Map map) {
		if (map == null)
			return null;
		int bcid = MapEx.getInt(map, "bcid");
		if (bcid <= 0)
			return null;

		int id = MapEx.getInt(map, "id");
		String building_name = MapEx.getString(map, "building_name");
		int pcid = MapEx.getInt(map, "pcid");
		String player_name = MapEx.getString(map, "player_name");
		int gid = MapEx.getInt(map, "gid");
		int lvl = MapEx.getInt(map, "lvl");
		long cooldown_ms = MapEx.getLong(map, "cooldown_ms");
		int position_index = MapEx.getInt(map, "position_index");
		int state = MapEx.getInt(map, "state");
		int type = MapEx.getInt(map, "type");

		return Player_build_obst.newPlayer_build_obst(id, bcid, building_name,
				pcid, player_name, gid, lvl, cooldown_ms, position_index,
				state, type);
	}

	// =========== method
	static public void delData(Player_build_obst pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		int bcid = pb.getBcid();
		String key = _GameKeys.fmtPinfoPcid(pcid);
		String field = _GameKeys.fmtOcid(bcid);
		JedisHash.delByKFields(key, field);
	}

	public static void setList(List<Player_build_obst> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		int len = origin.size();
		for (int i = 0; i < len; i++) {
			Player_build_obst en = origin.get(i);
			if (en == null)
				continue;
			String key = _GameKeys.fmtPinfoPcid(en.getPcid());
			String field = _GameKeys.fmtOcid(en.getBcid());
			String val = getValStr(en);
			KeyValEntity e = new KeyValEntity(key, field, val);
			if (i > 0 && i % 600 == 0) {
				setListDataByKFV(list);
				list.clear();
				continue;
			}
			list.add(e);
		}
		setListDataByKFV(list);
	}

	public static Player_build_obst getBuildByPcidBcid(int pcid, int ocid) {
		String key = _GameKeys.fmtPinfoPcid(pcid);
		String field = _GameKeys.fmtOcid(ocid);
		String val = JedisHash.getValHash(key, field);
		return toObjByStrVal(val);
	}

	public static List<Player_build_obst> getList(int pcid, String obstes) {
		PInfoAll info = _PInfoJedis.loadPinfoByJedisKey(pcid);
		if (info == null || !info.isLoadObsts())
			return getListOld(pcid, obstes);
		return info.getObsts();
	}

	public static List<Player_build_obst> getListOld(int pcid, String obstes) {
		List<String> keys = null;
		String pattern = getPatternObst(pcid);
		List<String> listKeys = JedisHash.getListKeysByPattern(pattern);
		if (obstes == null || "".equals(obstes.trim())) {
			keys = listKeys;
		} else {
			keys = new ArrayList<String>();
			String[] bes = obstes.split(",");
			for (String item : bes) {
				if (item == null || "".equals(item.trim()))
					continue;
				int ocid = 0;
				try {
					ocid = Integer.parseInt(item);
				} catch (Exception e) {
				}
				if (ocid == 0)
					continue;
				String key = getKeyObst(pcid, ocid);
				keys.add(key);
			}

			if (listKeys != null) {
				int len = keys.size();
				int lenList = listKeys.size();
				if (len != lenList)
					keys = listKeys;
			}
		}
		List<Map<String, String>> origin = JedisHash.getListMap(keys);

		delKeys(keys);

		if (origin == null || origin.isEmpty())
			return null;
		List<Player_build_obst> r = new ArrayList<Player_build_obst>();
		for (Map<String, String> map : origin) {
			Player_build_obst o = toObjByMap(map);
			if (o == null)
				continue;
			r.add(o);
		}

		setList(r);

		return r;
	}

	static private void delKeys(List<String> keys) {
		if (Svc.isEmpty(keys))
			return;
		GameDelJedis.delAllByKeys(null, keys);
	}
}