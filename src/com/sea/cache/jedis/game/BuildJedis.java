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
import com.sea.db.bean.Player_buildings;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class BuildJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(BuildJedis.class);

	public static Player_buildings toObjByStrVal(String strVal) {
		Map map_ = UtileTools.strToMap(strVal);
		return toObjByMap(map_);
	}

	public static Player_buildings toObjByMap(Map map) {
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
		long cur_produce_res = MapEx.getLong(map, "cur_produce_res");
		long res_produce_begin_time = MapEx.getLong(map,
				"res_produce_begin_time");
		int cur_up_tech_gid = MapEx.getInt(map, "cur_up_tech_gid");
		long end_tech_up_time = MapEx.getLong(map, "end_tech_up_time");
		long begin_army_time = MapEx.getLong(map, "begin_army_time");

		return Player_buildings.newPlayer_buildings(id, bcid, building_name,
				pcid, player_name, gid, lvl, cooldown_ms, position_index,
				state, type, cur_produce_res, res_produce_begin_time,
				cur_up_tech_gid, end_tech_up_time, begin_army_time);
	}

	// =========== method

	static public void delData(Player_buildings pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		int bcid = pb.getBcid();
		String key = _GameKeys.fmtPinfoPcid(pcid);
		String field = _GameKeys.fmtBcid(bcid);
		JedisHash.delByKFields(key, field);
	}

	public static void setList(List<Player_buildings> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		int len = origin.size();
		for (int i = 0; i < len; i++) {
			Player_buildings en = origin.get(i);
			if (en == null)
				continue;
			String key = _GameKeys.fmtPinfoPcid(en.getPcid());
			String field = _GameKeys.fmtBcid(en.getBcid());
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

	public static Player_buildings getBuildByPcidBcid(int pcid, int bcid) {
		String key = _GameKeys.fmtPinfoPcid(pcid);
		String field = _GameKeys.fmtBcid(bcid);
		String val = JedisHash.getValHash(key, field);
		return toObjByStrVal(val);
	}

	public static List<Player_buildings> getList(int pcid, String builds) {
		PInfoAll info = _PInfoJedis.loadPinfoByJedisKey(pcid);
		if (info == null || !info.isLoadBuild())
			return getListOld(pcid, builds);
		return info.getBuilds();
	}

	public static List<Player_buildings> getListOld(int pcid, String builds) {
		List<String> keys = null;
		if (builds == null || "".equals(builds.trim())) {
			String pattern = getPatternBuild(pcid);
			keys = JedisHash.getListKeysByPattern(pattern);
		} else {
			keys = new ArrayList<String>();
			String[] bes = builds.split(",");
			for (String item : bes) {
				if (item == null || "".equals(item.trim()))
					continue;
				int bcid = 0;
				try {
					bcid = Integer.parseInt(item);
				} catch (Exception e) {
				}
				if (bcid == 0)
					continue;
				String key = getKeyBuild(pcid, bcid);
				keys.add(key);
			}
		}

		List<Map<String, String>> origin = JedisHash.getListMap(keys);

		delKeys(keys);

		if (origin == null || origin.isEmpty())
			return null;

		List<Player_buildings> r = new ArrayList<Player_buildings>();
		for (Map<String, String> map : origin) {
			Player_buildings o = toObjByMap(map);
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
		List<String> del = new ArrayList<String>();
		for (String item : keys) {
			if (item.indexOf("agid") == 0)
				continue;
			del.add(item);
		}
		GameDelJedis.delAllByKeys(null, del);
	}
}