package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.NumEx;
import com.bowlong.util.MapEx;
import com.sea.cache.jedis.GameDelJedis;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.JedisList;
import com.sea.cache.jedis.origin.KeyValEntity;
import com.sea.cache.process.PInfoAll;
import com.sea.cache.process.ProBuild;
import com.sea.content.Svc;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_buildings;
import com.sea.logic.logicEntity.LEArmy;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class ArmyJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ArmyJedis.class);

	static String Army_Field_Own = "own";

	static {
		String listKey = getKeyArmyGid();
		boolean isHas = JedisList.isHasKey(listKey);
		if (!isHas) {
			String[] armyGid = { LEArmy.GID_Ship_1 + "",
					LEArmy.GID_Ship_2 + "", LEArmy.GID_Ship_3 + "",
					LEArmy.GID_Ship_6 + "", LEArmy.GID_Ship_5 + "",
					LEArmy.GID_Ship_4 + "", LEArmy.GID_Ship_7 + "",
					LEArmy.GID_Ship_9 + "" };
			JedisList.setValsInList(listKey, armyGid);
		}
	}

	public static String getArmyOwnPattern(int pcid, int bcid) {
		String head = getPatternArmy(pcid, bcid);
		return _GameKeys.appendStr(head, "->", Army_Field_Own);
	}

	public static Player_armys toObjByStrVal(String strVal) {
		Player_armys r = null;
		Map map = UtileTools.strToMap(strVal);
		int id = MapEx.getInt(map, "id");
		int gid = MapEx.getInt(map, "gid");
		boolean isHasGid = LEArmy.isHasAmryGid(gid);
		if (!isHasGid)
			return r;
		String army_name = MapEx.getString(map, "army_name");
		int pcid = MapEx.getInt(map, "pcid");
		String player_name = MapEx.getString(map, "player_name");
		int lvl = MapEx.getInt(map, "lvl");

		lvl = NumEx.limitMinMax(lvl, 1, 6);

		int num = MapEx.getInt(map, "num");
		int bcid = MapEx.getInt(map, "bcid");
		r = Player_armys.newPlayer_armys(id, army_name, pcid, player_name, gid,
				lvl, num, bcid);
		return r;
	}

	// =========== method

	public static void setList(List<Player_armys> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		int len = origin.size();
		for (int i = 0; i < len; i++) {
			Player_armys en = origin.get(i);
			if (en == null)
				continue;
			String key = _GameKeys.fmtPinfoPcid(en.getPcid());
			String field = _GameKeys.fmtBcidOArmy(en.getBcid(), en.getGid());
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

	public static Player_armys getArmy(int pcid, int bcid, int gid) {
		String key = _GameKeys.fmtPinfoPcid(pcid);
		String field = _GameKeys.fmtBcidOArmy(bcid, gid);
		String val = JedisHash.getValHash(key, field);
		return toObjByStrVal(val);
	}

	static public void deleteData(Player_armys item) {
		if (item != null) {
			int pcid = item.getPcid();
			int bcid = item.getBcid();
			int gid = item.getGid();
			String key = getKeyArmy(pcid, bcid, gid);
			Map<String, String> delMap = new HashMap<String, String>();
			delMap.put(key, Army_Field_Own);
			delMap.put(_GameKeys.fmtPinfoPcid(pcid),
					_GameKeys.fmtBcidOArmy(bcid, gid));
			GameDelJedis.delAllByMap(null, delMap);
		}
	}

	public static List<Player_armys> getList(int pcid, String builds) {
		PInfoAll info = _PInfoJedis.loadPinfoByJedisKey(pcid);
		if (info == null || !info.isLoadOwnArmys())
			return getListOld(pcid, builds);
		return info.getOwnArmys();
	}

	static Map<String, String> delOwn = new HashMap<String, String>();

	static private void intOldDelMap(int pcid, int bcid) {
		String[] armyGid = { LEArmy.GID_Ship_1 + "", LEArmy.GID_Ship_2 + "",
				LEArmy.GID_Ship_3 + "", LEArmy.GID_Ship_6 + "",
				LEArmy.GID_Ship_5 + "", LEArmy.GID_Ship_4 + "",
				LEArmy.GID_Ship_7 + "", LEArmy.GID_Ship_9 + "" };
		for (String item : armyGid) {
			String key = _GameKeys.fmt(_GameKeys.Pattern_Key_PArmy, pcid, bcid,
					item);
			delOwn.put(key, Army_Field_Own);
		}
	}

	static private List<Player_armys> getListByPBcid(int pcid, int bcid) {

		intOldDelMap(pcid, bcid);

		String patterns = getArmyOwnPattern(pcid, bcid);
		String listKey = getKeyArmyGid();
		List<String> vals = JedisHash.getValsHashByPattern(listKey, patterns);
		List<Player_armys> r = new ArrayList<Player_armys>();
		for (String v : vals) {
			if (v == null || "".equals(v.trim()))
				continue;
			Player_armys e = toObjByStrVal(v);
			if (e == null)
				continue;
			r.add(e);
		}

		return r;
	}

	static public List<Player_armys> getListOld(int pcid, String builds) {
		List<Player_armys> r = null;
		List<Player_buildings> listBuild = ProBuild
				.getListArsenal(pcid, builds);
		if (listBuild != null) {
			List<Player_armys> origin = new ArrayList<Player_armys>();
			for (Player_buildings item : listBuild) {
				int bcid = item.getBcid();
				List<Player_armys> tmp = getListByPBcid(pcid, bcid);
				if (Svc.isEmpty(tmp))
					continue;
				origin.addAll(tmp);
			}
			if (origin.size() > 0) {
				r = origin;
			}
		}

		setList(r);

		GameDelJedis.delAllByMap(null, delOwn);

		return r;
	}
}
