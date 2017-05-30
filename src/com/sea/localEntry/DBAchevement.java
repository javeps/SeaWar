package com.sea.localEntry;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.bowlong.bio2.B2Helper;
import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DBAchevement {
	public int id; // ID
	public int Type; // 类型
	public int GID; // GID
	public String Name; // 名称
	public String NameKey; // 名称KEY
	public String Description; // 描述
	public String DesKey; // 描述KEY
	public int Exp; // 获得经验
	public int Diamond; // 获得宝石
	public int BuildingGID; // 升级建筑GID
	public int BuildingLevel; // 升级建筑达到等级
	public int Star; // 副本获得星数量
	public int Gold; // 抢到金币
	public int Wood; // 抢到木材
	public int Oil; // 抢到石油
	public int Cup; // 奖杯数量达成
	public int DBuildingID; // 摧毁建筑ID
	public int DBuildingNum; // 摧毁建筑数量
	public int AttackWinNum; // 赢得进攻战役数量
	public int DefenseWinNum; // 赢得防守战役胜利
	public int NeedBuildingGid; // 要求建筑GID
	public int NeedBuildingLev; // 要求建筑等级
	public int ShipGid; // 舰船GID
	public int TaskFinshValues; // 完成任务数量

	public Hashtable ToMap() {
		Hashtable r = new Hashtable();
		r.put("id", id);
		r.put("Type", Type);
		r.put("GID", GID);
		r.put("Name", Name);
		r.put("NameKey", NameKey);
		r.put("Description", Description);
		r.put("DesKey", DesKey);
		r.put("Exp", Exp);
		r.put("Diamond", Diamond);
		r.put("BuildingGID", BuildingGID);
		r.put("BuildingLevel", BuildingLevel);
		r.put("Star", Star);
		r.put("Gold", Gold);
		r.put("Wood", Wood);
		r.put("Oil", Oil);
		r.put("Cup", Cup);
		r.put("DBuildingID", DBuildingID);
		r.put("DBuildingNum", DBuildingNum);
		r.put("AttackWinNum", AttackWinNum);
		r.put("DefenseWinNum", DefenseWinNum);
		r.put("NeedBuildingGid", NeedBuildingGid);
		r.put("NeedBuildingLev", NeedBuildingLev);
		r.put("ShipGid", ShipGid);
		r.put("TaskFinshValues", TaskFinshValues);
		return r;
	}

	public static DBAchevement parse(Map map) {
		if (map == null)
			return null;
		DBAchevement r = new DBAchevement();
		r.id = MapEx.getInt(map, "id");
		r.Type = MapEx.getInt(map, "Type");
		r.GID = MapEx.getInt(map, "GID");
		r.Name = MapEx.getString(map, "Name");
		r.NameKey = MapEx.getString(map, "NameKey");
		r.Description = MapEx.getString(map, "Description");
		r.DesKey = MapEx.getString(map, "DesKey");
		r.Exp = MapEx.getInt(map, "Exp");
		r.Diamond = MapEx.getInt(map, "Diamond");
		r.BuildingGID = MapEx.getInt(map, "BuildingGID");
		r.BuildingLevel = MapEx.getInt(map, "BuildingLevel");
		r.Star = MapEx.getInt(map, "Star");
		r.Gold = MapEx.getInt(map, "Gold");
		r.Wood = MapEx.getInt(map, "Wood");
		r.Oil = MapEx.getInt(map, "Oil");
		r.Cup = MapEx.getInt(map, "Cup");
		r.DBuildingID = MapEx.getInt(map, "DBuildingID");
		r.DBuildingNum = MapEx.getInt(map, "DBuildingNum");
		r.AttackWinNum = MapEx.getInt(map, "AttackWinNum");
		r.DefenseWinNum = MapEx.getInt(map, "DefenseWinNum");
		r.NeedBuildingGid = MapEx.getInt(map, "NeedBuildingGid");
		r.NeedBuildingLev = MapEx.getInt(map, "NeedBuildingLev");
		r.ShipGid = MapEx.getInt(map, "ShipGid");
		r.TaskFinshValues = MapEx.getInt(map, "TaskFinshValues");
		return r;
	}

	// 数据读取
	private static List<DBAchevement> _list;

	public static List<DBAchevement> getListInDB() {
		if (_list == null) {
			_list = new ArrayList<DBAchevement>();
			String path = "json/game/local/cfgAchevement";
			byte[] bt = UtileTools.readBytes(path);
			List origin = null;
			try {
				origin = B2Helper.toList(bt);
			} catch (Exception e) {
			}
			if (origin == null || origin.isEmpty())
				return _list;
			DBAchevement dtmp = null;
			Map map = null;
			for (Object obj : origin) {
				map = (Map) obj;
				dtmp = parse(map);
				if (dtmp == null)
					continue;
				_list.add(dtmp);
			}
		}
		return _list;
	}

	static public DBAchevement getDBActivity(int dbId) {
		List<DBAchevement> list = getListInDB();
		if (list == null || list.isEmpty())
			return null;

		DBAchevement db = null;
		for (DBAchevement item : list) {
			if (item.id == dbId) {
				db = item;
				break;
			}
		}
		return db;
	}
}
