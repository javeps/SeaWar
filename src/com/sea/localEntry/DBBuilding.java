package com.sea.localEntry;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.bowlong.bio2.B2Helper;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DBBuilding {

	public int ID; // ID

	public String Name; // 名字
	public String NameKey; // 显示名称Key
	public int GID; // GID

	public int Type; // 类型id

	public int Lev; // 等级

	public int BuildTimeM; // 升级时间（分钟）

	public String BuildRscType; // 花费资源类型（g：金币 o：石油）
	public int BuildCost; // 花费资源数

	public int HeadquartersLevel; // 需要主基地等级

	public int Space; // 空间

	public int SpaceLimited; // 空间上限

	public int Width; // 占地宽

	public boolean PlaceSea; // 建在浅海
	public boolean PlaceGround; // 建在陆地
	public int Height; // 占地长

	public String SelectedEffect; // 选中音效
	public String Icon; // 图标
	public int MaxStoredGold; // 最大储存金币

	public int MaxStoredOil; // 最大储存油

	public String ProducesRscType; // 生产资源类型
	public int ResourcePerHour; // 每小时产量

	public int ResourceMax; // 最大生产资源

	public int ResourceIconLimit; // 收集多少资源才显示收集图标

	public String CollectEffect; // 采集音效
	public int UnitProduction; // 队列空间

	public int BoostCost; // 加速花费

	public int Hitpoints; // 生命值

	public int RegenTime; // 恢复时间（秒）

	public int MaxMembers; // 最大会员数

	public int ArsenalLev; // 需要兵工厂等级

	public int TechTypeID; // 要求科技类型id

	public int TechlevLimit; // 科技等级限制

	public boolean Hidden; // 隐身
	public boolean IsFlying; // 可以飞行
	public int MoveSpeed; // 移动速度

	public boolean CanDrag; // 可以拖动
	public String DeployEffect; // 部署音效
	public int Bullets; // 使用子弹

	public int AttackRange; // 攻击范围

	public int MinAttackRange; // 最小攻击范围

	public int AttackSpeedMS; // 攻击速度（毫秒）

	public int Damage; // 攻击力（单次攻击）

	public int PreferedTargetDamageMod; // 特殊目标伤害倍数

	public int PreferedTargetType; // 优先目标类型

	public int FrozenTime; // 持续时间（毫秒）

	public int DamageRadius; // 攻击波及半径

	public String AttackEffect; // 攻击音效
	public boolean AirTargets; // 是否攻击空中目标
	public boolean GroundTargets; // 是否攻击地面目标
	public int DestructionXP; // 摧毁获得经验

	public int BuildExp; // 建造获得经验

	public int TriggerRadius; // 触发半径

	public String DescKey; // 描述

	public Hashtable ToMap() {
		Hashtable r = new Hashtable();
		r.put("ID", ID);
		r.put("Name", Name);
		r.put("NameKey", NameKey);
		r.put("GID", GID);
		r.put("Type", Type);
		r.put("Lev", Lev);
		r.put("BuildTimeM", BuildTimeM);
		r.put("BuildRscType", BuildRscType);
		r.put("BuildCost", BuildCost);
		r.put("HeadquartersLevel", HeadquartersLevel);
		r.put("Space", Space);
		r.put("SpaceLimited", SpaceLimited);
		r.put("Width", Width);
		r.put("PlaceSea", PlaceSea);
		r.put("PlaceGround", PlaceGround);
		r.put("Height", Height);
		r.put("SelectedEffect", SelectedEffect);
		r.put("Icon", Icon);
		r.put("MaxStoredGold", MaxStoredGold);
		r.put("MaxStoredOil", MaxStoredOil);
		r.put("ProducesRscType", ProducesRscType);
		r.put("ResourcePerHour", ResourcePerHour);
		r.put("ResourceMax", ResourceMax);
		r.put("ResourceIconLimit", ResourceIconLimit);
		r.put("CollectEffect", CollectEffect);
		r.put("UnitProduction", UnitProduction);
		r.put("BoostCost", BoostCost);
		r.put("Hitpoints", Hitpoints);
		r.put("RegenTime", RegenTime);
		r.put("MaxMembers", MaxMembers);
		r.put("ArsenalLev", ArsenalLev);
		r.put("TechTypeID", TechTypeID);
		r.put("TechlevLimit", TechlevLimit);
		r.put("Hidden", Hidden);
		r.put("IsFlying", IsFlying);
		r.put("MoveSpeed", MoveSpeed);
		r.put("CanDrag", CanDrag);
		r.put("DeployEffect", DeployEffect);
		r.put("Bullets", Bullets);
		r.put("AttackRange", AttackRange);
		r.put("MinAttackRange", MinAttackRange);
		r.put("AttackSpeedMS", AttackSpeedMS);
		r.put("Damage", Damage);
		r.put("PreferedTargetDamageMod", PreferedTargetDamageMod);
		r.put("PreferedTargetType", PreferedTargetType);
		r.put("FrozenTime", FrozenTime);
		r.put("DamageRadius", DamageRadius);
		r.put("AttackEffect", AttackEffect);
		r.put("AirTargets", AirTargets);
		r.put("GroundTargets", GroundTargets);
		r.put("DestructionXP", DestructionXP);
		r.put("BuildExp", BuildExp);
		r.put("TriggerRadius", TriggerRadius);
		r.put("DescKey", DescKey);
		return r;
	}

	public static DBBuilding parse(Map map) {
		if (map == null)
			return null;
		DBBuilding r = new DBBuilding();
		r.ID = MapEx.getInt(map, "ID");
		r.Name = MapEx.getString(map, "Name");
		r.NameKey = MapEx.getString(map, "NameKey");
		r.GID = MapEx.getInt(map, "GID");
		r.Type = MapEx.getInt(map, "Type");
		r.Lev = MapEx.getInt(map, "Lev");
		r.BuildTimeM = MapEx.getInt(map, "BuildTimeM");
		r.BuildRscType = MapEx.getString(map, "BuildRscType");
		r.BuildCost = MapEx.getInt(map, "BuildCost");
		r.HeadquartersLevel = MapEx.getInt(map, "HeadquartersLevel");
		r.Space = MapEx.getInt(map, "Space");
		r.SpaceLimited = MapEx.getInt(map, "SpaceLimited");
		r.Width = MapEx.getInt(map, "Width");
		r.PlaceSea = MapEx.getBoolean(map, "PlaceSea");
		r.PlaceGround = MapEx.getBoolean(map, "PlaceGround");
		r.Height = MapEx.getInt(map, "Height");
		r.SelectedEffect = MapEx.getString(map, "SelectedEffect");
		r.Icon = MapEx.getString(map, "Icon");
		r.MaxStoredGold = MapEx.getInt(map, "MaxStoredGold");
		r.MaxStoredOil = MapEx.getInt(map, "MaxStoredOil");
		r.ProducesRscType = MapEx.getString(map, "ProducesRscType");
		r.ResourcePerHour = MapEx.getInt(map, "ResourcePerHour");
		r.ResourceMax = MapEx.getInt(map, "ResourceMax");
		r.ResourceIconLimit = MapEx.getInt(map, "ResourceIconLimit");
		r.CollectEffect = MapEx.getString(map, "CollectEffect");
		r.UnitProduction = MapEx.getInt(map, "UnitProduction");
		r.BoostCost = MapEx.getInt(map, "BoostCost");
		r.Hitpoints = MapEx.getInt(map, "Hitpoints");
		r.RegenTime = MapEx.getInt(map, "RegenTime");
		r.MaxMembers = MapEx.getInt(map, "MaxMembers");
		r.ArsenalLev = MapEx.getInt(map, "ArsenalLev");
		r.TechTypeID = MapEx.getInt(map, "TechTypeID");
		r.TechlevLimit = MapEx.getInt(map, "TechlevLimit");
		r.Hidden = MapEx.getBoolean(map, "Hidden");
		r.IsFlying = MapEx.getBoolean(map, "IsFlying");
		r.MoveSpeed = MapEx.getInt(map, "MoveSpeed");
		r.CanDrag = MapEx.getBoolean(map, "CanDrag");
		r.DeployEffect = MapEx.getString(map, "DeployEffect");
		r.Bullets = MapEx.getInt(map, "Bullets");
		r.AttackRange = MapEx.getInt(map, "AttackRange");
		r.MinAttackRange = MapEx.getInt(map, "MinAttackRange");
		r.AttackSpeedMS = MapEx.getInt(map, "AttackSpeedMS");
		r.Damage = MapEx.getInt(map, "Damage");
		r.PreferedTargetDamageMod = MapEx
				.getInt(map, "PreferedTargetDamageMod");
		r.PreferedTargetType = MapEx.getInt(map, "PreferedTargetType");
		r.FrozenTime = MapEx.getInt(map, "FrozenTime");
		r.DamageRadius = MapEx.getInt(map, "DamageRadius");
		r.AttackEffect = MapEx.getString(map, "AttackEffect");
		r.AirTargets = MapEx.getBoolean(map, "AirTargets");
		r.GroundTargets = MapEx.getBoolean(map, "GroundTargets");
		r.DestructionXP = MapEx.getInt(map, "DestructionXP");
		r.BuildExp = MapEx.getInt(map, "BuildExp");
		r.TriggerRadius = MapEx.getInt(map, "TriggerRadius");
		r.DescKey = MapEx.getString(map, "DescKey");
		return r;
	}

	private static List<DBBuilding> _list;

	public static List<DBBuilding> getListInDB() {
		if (_list == null) {
			_list = new ArrayList<DBBuilding>();
			String path = "json/game/local/cfgBuilding";
			byte[] bt = UtileTools.readBytes(path);
			List origin = null;
			try {
				origin = B2Helper.toList(bt);
			} catch (Exception e) {
			}
			if (origin == null || origin.isEmpty())
				return _list;
			DBBuilding dtmp = null;
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

	public static DBBuilding getDBByGidLvl(int gid, int lvl) {
		DBBuilding r = null;
		List<DBBuilding> list = getListInDB();
		if (list == null || list.isEmpty())
			return r;
		for (DBBuilding item : list) {
			int igid = item.GID;
			int ilvl = item.Lev;
			if (igid == gid && ilvl == lvl) {
				r = item;
				break;
			}
		}
		return r;
	}

	public static boolean isCanUpGrade(int gid, int curLvl, int htlvl,
			long ndTime, boolean isVerityTime) {
		int lvl = curLvl + 1;
		DBBuilding r = getDBByGidLvl(gid, lvl);
		if (r == null)
			return false;

		int needHTLvl = r.HeadquartersLevel;
		boolean b = htlvl >= needHTLvl;
		if (isVerityTime) {
			long rndTime = r.BuildTimeM * DateEx.TIME_MINUTE;
			b = b && rndTime <= ndTime;
		}
		return b;
	}
}
