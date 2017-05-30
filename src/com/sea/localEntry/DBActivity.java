package com.sea.localEntry;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.bowlong.bio2.B2Helper;
import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DBActivity {
	public int id; // ID
	public int GID; // GID
	public String Name; // 名称
	public String NameKey; // 名称KEY
	public String Description; // 描述
	public String DesKey; // 描述KEY
	public boolean Open; // 状态
	public String Icon; // 主图标
	public String EffectPic; // 效果图
	public boolean ShowAward; // 显示奖励
	public boolean ShowButton; // 显示按钮
	public String ButtonNameKey; // 按钮名
	public int GoldAward; // 奖励金币
	public int OilAward; // 奖励石油
	public int GemAward; // 奖励宝石
	public int ShipIDAward; // 奖励舰船id
	public int ShipNumAward; // 奖励舰船数量
	public int BuilderAward; // 奖励工人
	public int Skill1Award; // 奖励技能1
	public int Skill2Award; // 奖励技能2
	public int Skill3Award; // 奖励技能3
	public int PieceHitpoints; // 生命之源
	public int PieceDamage; // 伤害之源
	public int PieceAttackSpeed; // 速度之源
	public int HeroIDAward; // 奖励英雄ID
	public String BegainTime; // 开始时间
	public String EndTime; // 结束时间

	public Hashtable ToMap() {
		Hashtable r = new Hashtable();
		r.put("id", id);
		r.put("GID", GID);
		r.put("Name", Name);
		r.put("NameKey", NameKey);
		r.put("Description", Description);
		r.put("DesKey", DesKey);
		r.put("Open", Open);
		r.put("Icon", Icon);
		r.put("EffectPic", EffectPic);
		r.put("ShowAward", ShowAward);
		r.put("ShowButton", ShowButton);
		r.put("ButtonNameKey", ButtonNameKey);
		r.put("GoldAward", GoldAward);
		r.put("OilAward", OilAward);
		r.put("GemAward", GemAward);
		r.put("ShipIDAward", ShipIDAward);
		r.put("ShipNumAward", ShipNumAward);
		r.put("BuilderAward", BuilderAward);
		r.put("Skill1Award", Skill1Award);
		r.put("Skill2Award", Skill2Award);
		r.put("Skill3Award", Skill3Award);
		r.put("PieceHitpoints", PieceHitpoints);
		r.put("PieceDamage", PieceDamage);
		r.put("PieceAttackSpeed", PieceAttackSpeed);
		r.put("HeroIDAward", HeroIDAward);
		r.put("BegainTime", BegainTime);
		r.put("EndTime", EndTime);
		return r;
	}

	public static DBActivity parse(Map map) {
		if (map == null)
			return null;
		DBActivity r = new DBActivity();
		r.id = MapEx.getInt(map, "id");
		r.GID = MapEx.getInt(map, "GID");
		r.Name = MapEx.getString(map, "Name");
		r.NameKey = MapEx.getString(map, "NameKey");
		r.Description = MapEx.getString(map, "Description");
		r.DesKey = MapEx.getString(map, "DesKey");
		r.Open = MapEx.getBoolean(map, "Open");
		r.Icon = MapEx.getString(map, "Icon");
		r.EffectPic = MapEx.getString(map, "EffectPic");
		r.ShowAward = MapEx.getBoolean(map, "ShowAward");
		r.ShowButton = MapEx.getBoolean(map, "ShowButton");
		r.ButtonNameKey = MapEx.getString(map, "ButtonNameKey");
		r.GoldAward = MapEx.getInt(map, "GoldAward");
		r.OilAward = MapEx.getInt(map, "OilAward");
		r.GemAward = MapEx.getInt(map, "GemAward");
		r.ShipIDAward = MapEx.getInt(map, "ShipIDAward");
		r.ShipNumAward = MapEx.getInt(map, "ShipNumAward");
		r.BuilderAward = MapEx.getInt(map, "BuilderAward");
		r.Skill1Award = MapEx.getInt(map, "Skill1Award");
		r.Skill2Award = MapEx.getInt(map, "Skill2Award");
		r.Skill3Award = MapEx.getInt(map, "Skill3Award");
		r.PieceHitpoints = MapEx.getInt(map, "PieceHitpoints");
		r.PieceDamage = MapEx.getInt(map, "PieceDamage");
		r.PieceAttackSpeed = MapEx.getInt(map, "PieceAttackSpeed");
		r.HeroIDAward = MapEx.getInt(map, "HeroIDAward");
		r.BegainTime = MapEx.getString(map, "BegainTime");
		r.EndTime = MapEx.getString(map, "EndTime");
		return r;
	}

	// 数据读取
	private static List<DBActivity> _list;

	public static List<DBActivity> getListInDB() {
		if (_list == null) {
			_list = new ArrayList<DBActivity>();
			String path = "json/game/local/cfgActivity";
			byte[] bt = UtileTools.readBytes(path);
			List origin = null;
			try {
				origin = B2Helper.toList(bt);
			} catch (Exception e) {
			}
			if (origin == null || origin.isEmpty())
				return _list;
			DBActivity dtmp = null;
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
	
	static public DBActivity getDBActivity(int dayLg){
		dayLg %= 7;
		int dbId = 0;
		if(dayLg == 0){
			dbId = 8;
		}else{
			dbId = dayLg + 1;
		}
		List<DBActivity> list = getListInDB();
		if(list == null || list.isEmpty())
			return null;
		
		DBActivity db = null;
		for (DBActivity item : list) {
			if(item.id == dbId){
				db = item;
				break;
			}
		}
		return db;
	}
}
