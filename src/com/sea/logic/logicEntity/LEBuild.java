package com.sea.logic.logicEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//用于数据下发
public class LEBuild implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int Init_Build_ClientId = 1001;// 初始化数据的客服端ID

	private boolean isCanAdd = true;
	private int idMaxClient = 0;
	
	public LEBuild() {
	}

	public LEBuild(int idMaxAtt) {
		setIdMaxClient(idMaxAtt);
	}

	public static LEBuild newDefault() {
		return new LEBuild(0);
	}

	public void setIsCanAdd(boolean val) {
		this.isCanAdd = val;
	}

	public boolean getIsCanAdd() {
		return this.isCanAdd;
	}

	public void setIdMaxClient(final int val) {
		boolean isCan = (val > this.idMaxClient);
		if (isCan) {
			this.idMaxClient = val;
		}
		setIsCanAdd(isCan);
	}

	public int GetIdMaxClient() {
		return this.idMaxClient;
	}
	
	public void reset(int idMax){
		this.idMaxClient = idMax;
		this.isCanAdd = false;
	}

	// ======================================== status
	public static final int Status_Default = 1;// 建筑默认状态
	public static final int Status_Destroy = 6;// 建筑爆炸状态

	// ======================================== type
	public static final int TYPE_BUILDING_BASE = 1;// 普通建筑
	public static final int TYPE_BUILDING_RESOURCE = 2;// 资源建筑
	public static final int TYPE_BUILDING_WALL = 3;// 围墙
	public static final int TYPE_BUILDING_DEFENSE = 4;// 防御建筑
	public static final int TYPE_BUILDING_TRAP = 5;// 陷阱
	public static final int TYPE_BUILDING_DECORATION = 6;// 装饰
	public static final int TYPE_BUILDING_OBSTRUCT = 7;// 障碍
	public static final int TYPE_BUILDING_SHIP = 10;// 舰船

	// ======================================== gid
	public static final int GID_Headquarters = 1;// 主基地
	public static final int GID_TechnologicalCenter = 2;// 科技中心
	public static final int GID_Arsenal = 3;// 兵工厂
	public static final int GID_OilWell = 4;// 油田
	public static final int GID_GoldMine = 5;// 矿洞
	public static final int GID_Alliance = 6;// 联盟
	public static final int GID_Colonial = 7;// 殖民局
	public static final int GID_Wall = 8;// 墙
	public static final int GID_Obstruct = 9;// 障碍
	public static final int GID_DockYard = 10;// 船坞
	public static final int GID_GoldStorage = 11;// 金库
	public static final int GID_OilStorage = 12;// 油库
	public static final int GID_Tree = 13;// 树木
	public static final int GID_TreePine = 14;// 松树
	public static final int GID_Wood = 15;// 树桩
	public static final int GID_Bush = 16;// 灌木
	public static final int GID_Hero_Base = 17;// 英雄基地

	public static final int GID_Cannon = 1001;// 加农炮
	public static final int GID_ArrowTower = 1002;// 箭塔
	public static final int GID_HeavyGun = 1003;// 迫击炮
	public static final int GID_Rocket = 1004;// 防空炮
	public static final int GID_Mortar = 1005;// 法师塔
	public static final int GID_Thunderbolt = 1006;// 电塔
	public static final int GID_DestroyerRocket = 1007;// 毁灭者火箭炮

	public static final int GID_Landmine = 2000;// 地雷
	public static final int GID_AirBomb = 2001;// 防空气球
	public static final int GID_Mine = 2002;// 水雷
	public static final int GID_FrozenMine = 2003;// 冰冻地雷
	public static final int GID_IceStorm = 2004;// 风暴控制器
	public static final int GID_Monster = 2005;// 海怪
	public static final int GID_Swirl = 2006;// 漩涡制造器

	public static final int GID_Flag1 = 9001;// 旗帜1
	public static final int GID_Flag2 = 9002;// 旗帜2
	public static final int GID_Flag3 = 9003;// 旗帜3
	public static final int GID_Flag4 = 9004;// 旗帜4
	public static final int GID_Flag5 = 9005;// 旗帜5
	public static final int GID_Sculpture = 9006;// 雕像

	// ===========================================建筑名称Map
	private static Map<Integer, String> _build_name_map = null;

	// 判断是否拥有这建筑类型
	public static boolean isHasBuildGid(final int gid) {
		boolean r2 = false;
		switch (gid) {
		case GID_Headquarters:
		case GID_TechnologicalCenter:
		case GID_Arsenal:
		case GID_OilWell:
		case GID_GoldMine:
		case GID_Alliance:
		case GID_Colonial:
		case GID_Wall:
		case GID_Obstruct:
		case GID_DockYard:
		case GID_Cannon:
		case GID_ArrowTower:
		case GID_HeavyGun:
		case GID_Rocket:
		case GID_Mortar:
		case GID_Thunderbolt:
		case GID_DestroyerRocket:
		case GID_Landmine:
		case GID_AirBomb:
		case GID_Mine:
		case GID_FrozenMine:
		case GID_IceStorm:
		case GID_Monster:
		case GID_Swirl:
		case GID_GoldStorage:
		case GID_OilStorage:
		case GID_Flag1:
		case GID_Flag2:
		case GID_Flag3:
		case GID_Flag4:
		case GID_Flag5:
		case GID_Sculpture:
		case GID_Tree:
		case GID_TreePine:
		case GID_Wood:
		case GID_Bush:
		case GID_Hero_Base:
			r2 = true;
			break;
		default:
			break;
		}
		return r2;
	}

	// 取得建筑名称
	public static String getBuldingNameGid(int gid) {
		String r2 = "";
		if (_build_name_map == null) {
			_build_name_map = new HashMap<Integer, String>();
			_build_name_map.put(GID_Headquarters, "主基地");
			_build_name_map.put(GID_TechnologicalCenter, "科技中心");
			_build_name_map.put(GID_Arsenal, "兵工厂");
			_build_name_map.put(GID_OilWell, "油田");
			_build_name_map.put(GID_GoldMine, "矿洞");
			_build_name_map.put(GID_Alliance, "联盟");
			_build_name_map.put(GID_Colonial, "殖民局");
			_build_name_map.put(GID_Wall, "墙");
			_build_name_map.put(GID_Obstruct, "障碍");
			_build_name_map.put(GID_DockYard, "船坞码头");
			_build_name_map.put(GID_Cannon, "加农炮");
			_build_name_map.put(GID_ArrowTower, "箭塔");
			_build_name_map.put(GID_HeavyGun, "迫击炮");
			_build_name_map.put(GID_Rocket, "防空炮");
			_build_name_map.put(GID_Mortar, "法师塔");
			_build_name_map.put(GID_Thunderbolt, "电塔");
			_build_name_map.put(GID_DestroyerRocket, "毁灭者火箭炮");
			_build_name_map.put(GID_Landmine, "地雷");
			_build_name_map.put(GID_AirBomb, "防空气球");
			_build_name_map.put(GID_Mine, "水雷");
			_build_name_map.put(GID_FrozenMine, "冰冻地雷");
			_build_name_map.put(GID_IceStorm, "风暴控制器");
			_build_name_map.put(GID_Monster, "海怪");
			_build_name_map.put(GID_Swirl, "漩涡制造器");
			_build_name_map.put(GID_OilStorage, "油库");
			_build_name_map.put(GID_GoldStorage, "金库");
			_build_name_map.put(GID_Hero_Base, "英雄基地");

			_build_name_map.put(GID_Tree, "树");
			_build_name_map.put(GID_TreePine, "松树");
			_build_name_map.put(GID_Wood, "树桩");
			_build_name_map.put(GID_Bush, "灌木");
			_build_name_map.put(GID_Flag1, "旗帜1");
			_build_name_map.put(GID_Flag2, "旗帜2");
			_build_name_map.put(GID_Flag3, "旗帜3");
			_build_name_map.put(GID_Flag4, "旗帜4");
			_build_name_map.put(GID_Flag5, "旗帜5");
			_build_name_map.put(GID_Sculpture, "雕像");
		}
		boolean is_has = _build_name_map.containsKey(gid);
		if (is_has) {
			r2 = _build_name_map.get(gid);
		}
		return r2;
	}

	// 取得建筑类型
	public static int getTypeByGid(int gid) {
		int r2 = TYPE_BUILDING_BASE;
		switch (gid) {
		case GID_Headquarters:
		case GID_TechnologicalCenter:
		case GID_Arsenal:
		case GID_Alliance:
		case GID_Colonial:
		case GID_DockYard:
		case GID_Hero_Base:
			r2 = TYPE_BUILDING_BASE;
			break;
		case GID_OilWell:
		case GID_GoldMine:
		case GID_GoldStorage:
		case GID_OilStorage:
			r2 = TYPE_BUILDING_RESOURCE;
			break;
		case GID_Wall:
			r2 = TYPE_BUILDING_WALL;
			break;
		case GID_Obstruct:
		case GID_Tree:
		case GID_TreePine:
		case GID_Wood:
		case GID_Bush:
			r2 = TYPE_BUILDING_OBSTRUCT;
			break;
		case GID_Cannon:
		case GID_ArrowTower:
		case GID_HeavyGun:
		case GID_Rocket:
		case GID_Mortar:
		case GID_Thunderbolt:
		case GID_DestroyerRocket:
			r2 = TYPE_BUILDING_DEFENSE;
			break;
		case GID_Landmine:
		case GID_AirBomb:
		case GID_Mine:
		case GID_FrozenMine:
		case GID_IceStorm:
		case GID_Monster:
		case GID_Swirl:
			r2 = TYPE_BUILDING_TRAP;
			break;
		case GID_Flag1:
		case GID_Flag2:
		case GID_Flag3:
		case GID_Flag4:
		case GID_Flag5:
		case GID_Sculpture:
			r2 = TYPE_BUILDING_DECORATION;
			break;
		default:
			break;
		}
		return r2;
	}
}
