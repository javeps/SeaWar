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
import com.sea.cache.jedis.origin.JedisList;
import com.sea.cache.jedis.origin.KeyValEntity;
import com.sea.cache.process.PInfoAll;
import com.sea.content.Svc;
import com.sea.db.bean.Player_hero;
import com.sea.logic.logicOperate.LogicHero;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class HeroJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(HeroJedis.class);

	static {
		String listKey = getKeyHeroGid();
		boolean isHas = JedisHash.isHasKey(listKey);
		if (isHas) {
			JedisHash.deleteKey(listKey);
		}
		String[] gid = { LogicHero.Hero_Gid_ElementsNaga + "",
				LogicHero.Hero_Gid_LandDragon + "",
				LogicHero.Hero_Gid_Machinery + "",
				LogicHero.Hero_Gid_Sprite + "", LogicHero.Hero_Gid_Fire + "",
				LogicHero.Hero_Gid_Jelly + "" };
		JedisList.setValsInList(listKey, gid);
	}

	public static Player_hero toObjByStrVal(String strVal) {
		Map map_ = UtileTools.strToMap(strVal);
		return toObjByMap(map_);
	}

	public static Player_hero toObjByMap(Map map) {
		if (map == null)
			return null;
		int hcid = MapEx.getInt(map, "hcid");
		if (hcid <= 0)
			return null;
		int id = MapEx.getInt(map, "id");
		int pcid = MapEx.getInt(map, "pcid");
		String pname = MapEx.getString(map, "pname");
		String hname = MapEx.getString(map, "hname");
		int hgid = MapEx.getInt(map, "hgid");
		int addDamage = MapEx.getInt(map, "addDamage");
		int maxDamage = MapEx.getInt(map, "maxDamage");
		int addHP = MapEx.getInt(map, "addHP");
		int maxHP = MapEx.getInt(map, "maxHP");
		int addAttSpeed = MapEx.getInt(map, "addAttSpeed");
		int maxAttSpeed = MapEx.getInt(map, "maxAttSpeed");
		int statues = MapEx.getInt(map, "statues");
		int skillGid = MapEx.getInt(map, "skillGid");
		long createTime = MapEx.getLong(map, "createTime");
		long deadTime = MapEx.getLong(map, "deadTime");
		int fpos = MapEx.getInt(map, "fpos");

		return Player_hero.newPlayer_hero(id, hcid, pcid, pname, hname, hgid,
				addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed,
				statues, createTime, deadTime, skillGid, fpos);
	}

	// =========== method

	static public void delData(Player_hero pb) {
		if (pb == null)
			return;
		int pcid = pb.getPcid();
		int hgid = pb.getHgid();
		String key = _GameKeys.fmtPinfoPcid(pcid);
		String field = _GameKeys.fmtHgid(hgid);
		JedisHash.delByKFields(key, field);
	}

	public static boolean isHasHero(int pcid, int hgid) {
		boolean r = false;
		String key = _GameKeys.fmtPinfoPcid(pcid);
		r = JedisHash.isHasHash(key, String.valueOf(hgid));
		return r;
	}

	public static void setList(List<Player_hero> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		int len = origin.size();
		for (int i = 0; i < len; i++) {
			Player_hero en = origin.get(i);
			if (en == null)
				continue;
			String key = _GameKeys.fmtPinfoPcid(en.getPcid());
			String field = _GameKeys.fmtHgid(en.getHgid());
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

	public static Player_hero getHeroByPcidHgid(int pcid, int hgid) {
		String key = _GameKeys.fmtPinfoPcid(pcid);
		String field = _GameKeys.fmtHgid(hgid);
		String val = JedisHash.getValHash(key, field);
		return toObjByStrVal(val);
	}

	public static List<Player_hero> getList(int pcid, String heroes) {
		PInfoAll info = _PInfoJedis.loadPinfoByJedisKey(pcid);
		if (info == null || !info.isLoadHeros())
			return getListOld(pcid, heroes);
		return info.getHeroes();
	}

	public static List<Player_hero> getListOld(int pcid, String heroes) {
		List<String> keys = null;
		if (heroes == null || "".equals(heroes.trim())) {
			String pattern = getPatternHero(pcid);
			keys = JedisHash.getListKeysByPattern(pattern);
		} else {
			keys = new ArrayList<String>();
			String[] bes = heroes.split(",");
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
				String key = getKeyHero(pcid, hgid);
				keys.add(key);
			}
		}
		List<Map<String, String>> origin = JedisHash.getListMap(keys);

		delKeys(keys);

		if (origin == null || origin.isEmpty())
			return null;
		List<Player_hero> r = new ArrayList<Player_hero>();
		for (Map<String, String> v : origin) {
			Player_hero o = toObjByMap(v);
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