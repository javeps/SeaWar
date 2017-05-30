package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.cache.jedis.GameDelJedis;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.KeyValEntity;
import com.sea.cache.process.PInfoAll;
import com.sea.cache.process.ProBuild;
import com.sea.content.Svc;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_producting;
import com.sea.logic.logicEntity.LEArmy;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class ProductJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProductJedis.class);

	static String Army_Field_Product = "make";

	public static String getArmyMakePattern(int pcid, int bcid) {
		String head = getPatternArmy(pcid, bcid);
		return _GameKeys.appendStr(head, "->", Army_Field_Product);
	}

	public static String getArmyMakePatternInPcid(int pcid) {
		String head = getPatternBuild(pcid);
		return _GameKeys.appendStr(head, "->", Army_Field_Product);
	}

	public static Player_producting toObjByStrVal(String strVal) {
		Player_producting r = null;
		Map map_ = UtileTools.strToMap(strVal);
		int gid = MapEx.getInt(map_, "gid");
		boolean isHasGid = LEArmy.isHasAmryGid(gid);
		if (!isHasGid)
			return r;
		int id = MapEx.getInt(map_, "id");
		int pcid = MapEx.getInt(map_, "pcid");
		int num = MapEx.getInt(map_, "num");
		int bcid = MapEx.getInt(map_, "bcid");

		r = Player_producting.newPlayer_producting(id, gid, num, bcid, pcid);
		return r;
	}

	// =========== method

	public static void setList(List<Player_producting> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		if (origin == null || origin.size() <= 0)
			return;
		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		int len = origin.size();
		for (int i = 0; i < len; i++) {
			Player_producting en = origin.get(i);
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

	public static Player_producting getArmy(int pcid, int bcid, int gid) {
		String key = _GameKeys.fmtPinfoPcid(pcid);
		String field = _GameKeys.fmtBcidMArmy(bcid, gid);
		String val = JedisHash.getValHash(key, field);
		return toObjByStrVal(val);
	}

	public static void deleteData(Player_producting item) {
		if (item != null) {
			int pcid = item.getPcid();
			int bcid = item.getBcid();
			int gid = item.getGid();
			String key = getKeyArmy(pcid, bcid, gid);
			Map<String, String> delMap = new HashMap<String, String>();
			delMap.put(key, Army_Field_Product);
			delMap.put(_GameKeys.fmtPinfoPcid(pcid),
					_GameKeys.fmtBcidOArmy(bcid, gid));
			GameDelJedis.delAllByMap(null, delMap);
		}
	}

	public static List<Player_producting> getList(int pcid, String builds) {
		PInfoAll info = _PInfoJedis.loadPinfoByJedisKey(pcid);
		if (info == null || !info.isLoadProducts())
			return getListOld(pcid, builds);
		return info.getProducts();
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
			delOwn.put(key, Army_Field_Product);
		}
	}

	static private List<Player_producting> getListByPcidBcid(int pcid, int bcid) {
		intOldDelMap(pcid, bcid);
		String patterns = getArmyMakePattern(pcid, bcid);
		String listKey = getKeyArmyGid();
		List<String> vals = JedisHash.getValsHashByPattern(listKey, patterns);
		List<Player_producting> r = new ArrayList<Player_producting>();
		for (String v : vals) {
			if (v == null || "".equals(v.trim()))
				continue;
			Player_producting e = toObjByStrVal(v);
			if (e == null)
				continue;
			r.add(e);
		}

		return r;
	}

	public static List<Player_producting> getListOld(int pcid, String builds) {
		List<Player_buildings> listBuild = ProBuild
				.getListArsenal(pcid, builds);
		if (listBuild == null)
			return null;
		List<Player_producting> r = new ArrayList<Player_producting>();
		for (Player_buildings item : listBuild) {
			int bcid = item.getBcid();
			List<Player_producting> tmp = getListByPcidBcid(pcid, bcid);
			if (Svc.isEmpty(tmp))
				continue;
			r.addAll(tmp);
		}

		setList(r);

		GameDelJedis.delAllByMap(null, delOwn);

		return r;
	}
}
