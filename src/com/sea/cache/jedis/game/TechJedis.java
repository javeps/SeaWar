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
import com.sea.db.bean.Player_tech;
import com.sea.db.entity.Player_techEntity;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class TechJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(TechJedis.class);

	public static Player_tech toObjByStrVal(String strVal) {
		Map map_ = UtileTools.strToMap(strVal);
		return toObjByMap(map_);
	}

	public static Player_tech toObjByMap(Map map) {
		if (map == null)
			return null;
		int gid = MapEx.getInt(map, "gid");
		boolean isHasGid = Player_techEntity.isHasTechGid(gid);
		if (!isHasGid)
			return null;

		int id = MapEx.getInt(map, "id");
		String tech_name = MapEx.getString(map, "tech_name");
		int pcid = MapEx.getInt(map, "pcid");
		String player_name = MapEx.getString(map, "player_name");
		int lvl = MapEx.getInt(map, "lvl");
		return Player_tech.newPlayer_tech(id, tech_name, pcid, player_name,
				gid, lvl);
	}

	// =========== hash

	static public void delData(Player_tech pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		int tgid = pb.getGid();
		String key = _GameKeys.fmtPinfoPcid(pcid);
		String field = _GameKeys.fmtTgid(tgid);
		JedisHash.delByKFields(key, field);
	}

	public static void setList(List<Player_tech> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		int len = origin.size();
		for (int i = 0; i < len; i++) {
			Player_tech en = origin.get(i);
			if (en == null)
				continue;
			String key = _GameKeys.fmtPinfoPcid(en.getPcid());
			String field = _GameKeys.fmtTgid(en.getGid());
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

	public static Player_tech getTech(int pcid, int gid) {
		String key = _GameKeys.fmtPinfoPcid(pcid);
		String field = _GameKeys.fmtTgid(gid);
		String val = JedisHash.getValHash(key, field);
		return toObjByStrVal(val);
	}

	public static List<Player_tech> getList(int pcid, String teches) {
		PInfoAll info = _PInfoJedis.loadPinfoByJedisKey(pcid);
		if (info == null || !info.isLoadTechs())
			return getListOld(pcid, teches);
		return info.getTeches();
	}

	public static List<Player_tech> getListOld(int pcid, String teches) {
		List<String> keys = null;
		if (teches == null || "".equals(teches.trim())) {
			String pattern = getPatternTech(pcid);
			keys = JedisHash.getListKeysByPattern(pattern);
		} else {
			keys = new ArrayList<String>();
			String[] bes = teches.split(",");
			for (String item : bes) {
				if (item == null || "".equals(item.trim()))
					continue;
				int hgid = 0;
				try {
					hgid = Integer.parseInt(item);
				} catch (Exception e) {
				}
				if (hgid == 0)
					continue;
				String key = getKeyTech(pcid, hgid);
				keys.add(key);
			}
		}
		List<Map<String, String>> origin = JedisHash.getListMap(keys);

		delKeys(keys);

		if (origin == null || origin.isEmpty())
			return null;
		List<Player_tech> r = new ArrayList<Player_tech>();
		for (Map<String, String> v : origin) {
			Player_tech o = toObjByMap(v);
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
