package com.sea.logic.logicEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//用于数据下发
public class LEArmy implements Serializable {

	private static final long serialVersionUID = 1L;

	public LEArmy() {
	}

	public static LEArmy newDefault() {
		return new LEArmy();
	}
	
	// ================ gid =================
	public static final int GID_Ship_1 = 1008;// 船上的海盗
	public static final int GID_Ship_2 = 1009;// 炮艇
	public static final int GID_Ship_3 = 1010;// 劫掠快艇
	public static final int GID_Ship_4 = 1011;// 重装劫掠艇
	public static final int GID_Ship_5 = 1012;// 自爆船
	public static final int GID_Ship_6 = 1013;// 地狱快艇
	public static final int GID_Ship_7 = 1014;// 导弹驱逐舰
	public static final int GID_Ship_8 = 1015;// 舰船8
	public static final int GID_Ship_9 = 1016;// 登陆船

	private static Map<Integer, String> _armyNameMap = null;// 兵种名称Map

	// 判断是否拥有这种类型的兵
	public static boolean isHasAmryGid(final int gid) {
		boolean r2 = false;
		switch (gid) {
		case GID_Ship_1:
		case GID_Ship_2:
		case GID_Ship_3:
		case GID_Ship_4:
		case GID_Ship_5:
		case GID_Ship_6:
		case GID_Ship_7:
		case GID_Ship_8:
		case GID_Ship_9:
			r2 = true;
			break;
		default:
			break;
		}
		return r2;
	}

	// 取得兵种名称
	public static String getArmyNameGid(int gid) {
		String r2 = "";
		if (_armyNameMap == null) {
			_armyNameMap = new HashMap<Integer, String>();
			_armyNameMap.put(GID_Ship_1, "陆战队员");
			_armyNameMap.put(GID_Ship_2, "狩猎级驱逐舰");
			_armyNameMap.put(GID_Ship_3, "贼鸥级快艇");
			_armyNameMap.put(GID_Ship_4, "巨人级护卫舰");
			_armyNameMap.put(GID_Ship_5, "毁灭者自爆炸弹");
			_armyNameMap.put(GID_Ship_6, "地狱飞艇");
			_armyNameMap.put(GID_Ship_7, "无畏级战列舰");
			_armyNameMap.put(GID_Ship_8, "舰船8");
			_armyNameMap.put(GID_Ship_9, "破浪级登陆船");
		}
		boolean is_has = _armyNameMap.containsKey(gid);
		if (is_has) {
			r2 = _armyNameMap.get(gid);
		}
		return r2;
	}
}