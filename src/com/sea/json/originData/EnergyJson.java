package com.sea.json.originData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EnergyJson {

	static Log log = LogFactory.getLog(EnergyJson.class);

	private static final String Json_Path_Energy = "json/game/rate/energy.json";
	private static final String Json_Path_Num = "json/game/rate/energyNum.json";
	private static final String Json_Path_AddHP = "json/game/rate/addHP.json";
	private static final String Json_Path_AddDam = "json/game/rate/addDam.json";
	private static final String Json_Path_AddSpeed = "json/game/rate/addSpeed.json";

	// 活动 一 ：能源片掉落概率提高
	private static final String Json_Path_Energy_Activite = ActivitiesJson.Path_Head
			+ "energy.json";
	private static final String Json_Path_Num_Activite = ActivitiesJson.Path_Head
			+ "energyNum.json";

	public static final int Egid_None = 0;// 零之源
	public static final int Egid_HP = 1;// 生命之源
	public static final int Egid_Damage = 2;// 伤害之源
	public static final int Egid_AttSpeed = 3;// 速度之源

	private static final String EJ_EGID = "egid";
	private static final String EJ_NAME = "name";
	private static final String EJ_RATE = "rate";

	public static List readListEnergyData(boolean isActivite) {
		String path = Json_Path_Energy;
		if (isActivite)
			path = Json_Path_Energy_Activite;

		List r = UtileTools.readStrList(path);
		return r;
	}

	public static Map readMapData(String path) {
		Map r = UtileTools.readStrMap(path);
		return r;
	}

	protected static int getValByRate(final String pathMap, int rate) {
		int r = 0;
		try {
			Map map = readMapData(pathMap);
			Set keys = map.keySet();
			List list = new ArrayList();
			list.addAll(keys);
			Collections.sort(list);
			int sum = 0;
			for (Object k : list) {
				int v = MapEx.getInt(map, k);
				sum += v;
				if (sum >= rate) {
					r = Integer.parseInt(k.toString());
					break;
				}
			}
			UtileTools.clearList(list);
			UtileTools.clearMap(map);
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
		return r;
	}

	// ========== energy

	public static Map getEnergyByEgid(int egid, boolean isActivite) {
		Map map = null;
		List list = readListEnergyData(isActivite);
		for (Object object : list) {
			Map item = (Map) object;
			int gid = MapEx.getInt(item, EJ_EGID);
			if (gid == egid) {
				map = item;
				break;
			}
		}
		UtileTools.clearList(list);
		return map;
	}

	public static String getEnergyName(int egid, boolean isActivite) {
		Map map = getEnergyByEgid(egid, isActivite);
		String name = MapEx.getString(map, EJ_NAME);
		UtileTools.clearMap(map);
		return name;
	}

	private static int getEnergyRate(int egid, boolean isActivite) {
		Map map = getEnergyByEgid(egid, isActivite);
		int val = MapEx.getInt(map, EJ_RATE);
		UtileTools.clearMap(map);
		return val;
	}

	public static int getENumByRate(int rate, boolean isActivite) {
		String path = Json_Path_Num;
		if (isActivite)
			path = Json_Path_Num_Activite;
		return getValByRate(path, rate);
	}

	public static int getEgidByRate(int rate, boolean isGetEnergy,
			boolean isActivite) {

		int egid = Egid_None;

		if (isGetEnergy) {
			int ENRate = getEnergyRate(Egid_None, isActivite);
			double ekRate = (1000d - ENRate) * 0.001d;
			double rd = rate * ekRate;
			rate = (int) Math.ceil(rd);
		}

		List list = readListEnergyData(isActivite);
		int sum = 0;

		for (Object object : list) {
			Map item = (Map) object;
			int erate = MapEx.getInt(item, EJ_RATE);
			int tmpgid = MapEx.getInt(item, EJ_EGID);

			if (isGetEnergy) {
				if (tmpgid == Egid_None)
					continue;
			}

			sum += erate;
			if (sum >= rate) {
				egid = tmpgid;
				break;
			}
		}
		UtileTools.clearList(list);
		return egid;
	}

	// ========== 增加能源的值

	public static int getValByAddHP(int rate) {
		return getValByRate(Json_Path_AddHP, rate);
	}

	public static int getValByAddDam(int rate) {
		return getValByRate(Json_Path_AddDam, rate);
	}

	public static int getValByAddSpeed(int rate) {
		return getValByRate(Json_Path_AddSpeed, rate);
	}
}
