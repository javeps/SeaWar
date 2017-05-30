package com.sea.json.init;

import java.util.ArrayList;
import java.util.List;

import com.sea.db.bean.Player;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_tech;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Player_armysEntity;
import com.sea.db.entity.Player_buildingsEntity;
import com.sea.db.entity.Player_techEntity;
import com.sea.logic.logicEntity.LEBuild;
import com.sea.logic.logicOperate.LogicObst;
import com.sea.tools.ReadWriteString;

public class JsonExportInit {
	public static final int Type_Player = 1;
	public static final int Type_Player_Build = 2;
	public static final int Type_Player_Army = 3;
	public static final int Type_Player_Tech = 4;
	public static final int Type_Player_Obst = 5;

	public static String getJsonPathPlayer(int index, final int type) {
		StringBuffer s_ = new StringBuffer();
		s_.append("json/game/player/").append("P_").append(index);
		switch (type) {
		case Type_Player_Build:
			s_.append("_B");
			break;
		case Type_Player_Army:
			s_.append("_A");
			break;
		case Type_Player_Tech:
			s_.append("_T");
			break;
		case Type_Player_Obst:
			s_.append("_O");
		default:
			break;
		}
		s_.append(".json");
		String r = s_.toString();
		return r;
	}

	public static void export() {
		List<Player> list_ = PlayerEntity.getListRankByNum(10);
		if (list_ != null && list_.size() > 0) {
			int i = 0;
			for (Player item : list_) {
				writeDate(item, i);
				i++;
			}
		}
	}

	public static void writeDate(Player item, int index) {
		writePlayer(item, index);
		writeArmy(item, index);
		writeBuild(item, index);
		writeTech(item, index);
		writeObst(item, index);
	}

	public static String getJsonPlayer(Player item) {
		StringBuffer s_ = new StringBuffer();
		s_.append("{");
		// s_.append("\"type\"").append(":").append(ConstantType.Type_User_Normal).append(",");
		s_.append("\"crystal\"").append(":").append(item.getCrystal())
				.append(",");
		s_.append("\"maxBuidId\"").append(":").append(item.getMaxbuidid())
				.append(",");
		s_.append("\"maxObstId\"").append(":").append(item.getMaxobstid())
				.append(",");
		// s_.append("\"renown\"").append(":").append(0).append(",");
//		s_.append("\"mark\"").append(":").append("\"").append(item.getMark()).append("\"").append(",");
		s_.append("\"stored_gold\"").append(":").append(item.getStored_gold())
				.append(",");
		s_.append("\"stored_oil\"").append(":").append(item.getStored_oil())
				;
		s_.append("}");
		String r = s_.toString();
		return r;
	}

	public static void writePlayer(Player item, int index) {
		String path = getJsonPathPlayer(index, Type_Player);
		String strCont = getJsonPlayer(item);
		ReadWriteString.writeStr(path, strCont);
	}

	public static String getJsonPlayerBuild(List<Player_buildings> list) {
		String r = "";
		StringBuffer s_ = new StringBuffer();
		s_.append("[");
		if (list != null && list.size() > 0) {
			for (Player_buildings item : list) {
				String str_ = getJsonPlayerBuild(item);
				s_.append(str_).append(",");
			}
		}
		r = s_.toString();
		int len_ = r.length();
		if (len_ > 3) {
			r = r.substring(0, len_ - 1);
		}
		r += "]";
		return r;
	}

	// 用于包含了障碍，装饰的建筑列表
	public static String getJsonPlayerBuild(List<Player_buildings> list,
			int index) {
		String r = "";
		if (list != null && list.size() > 0) {
			List<Player_buildings> list_build = new ArrayList<Player_buildings>();
			List<Player_build_obst> list_obst = new ArrayList<Player_build_obst>();
			for (Player_buildings item : list) {
				int gid = item.getGid();
				switch (gid) {
				case LEBuild.GID_Wall:
				case LEBuild.GID_Obstruct:
				case LEBuild.GID_Tree:
				case LEBuild.GID_TreePine:
				case LEBuild.GID_Wood:
				case LEBuild.GID_Bush:
				case LEBuild.GID_Flag1:
				case LEBuild.GID_Flag2:
				case LEBuild.GID_Flag3:
				case LEBuild.GID_Flag4:
				case LEBuild.GID_Flag5:
				case LEBuild.GID_Sculpture:
					Player_build_obst obst_ = Player_build_obst
							.newPlayer_build_obst(0, item.getBcid(),
									item.getBuilding_name(),
									item.getPcid(), item.getPlayer_name(),
									gid, item.getLvl(), item.getCooldown_ms(),
									item.getPosition_index(), item.getState(),
									item.getType());
					list_obst.add(obst_);
					break;
				default:
					list_build.add(item);
					break;
				}
			}
			r = getJsonPlayerBuild(list_build);
			writeObst(list_obst, index);
		}
		return r;
	}

	public static String getJsonPlayerBuild(Player_buildings item) {
		StringBuffer s_ = new StringBuffer();
		s_.append("{");
		// s_.append("\"type\"").append(":").append(item.getType()).append(",");
		int lvl = item.getLvl();
		lvl = lvl < 1 ? 1 : lvl;
		s_.append("\"lvl\"").append(":").append(lvl).append(",");
		s_.append("\"pos\"").append(":").append(item.getPosition_index())
				.append(",");
		s_.append("\"bcid\"").append(":").append(item.getBcid())
				.append(",");
		s_.append("\"gid\"").append(":").append(item.getGid());
		s_.append("}");
		String r = s_.toString();
		return r;
	}

	public static void writeBuild(Player item, int index) {
		String path = getJsonPathPlayer(index, Type_Player_Build);
		List<Player_buildings> list_builds = Player_buildingsEntity
				.getPlayerBuidList(item);
		String strCont = getJsonPlayerBuild(list_builds);
		ReadWriteString.writeStr(path, strCont);
	}

	public static String getJsonPlayerArmy(List<Player_armys> list) {
		String r = "";
		StringBuffer s_ = new StringBuffer();
		s_.append("[");
		if (list != null && list.size() > 0) {
			for (Player_armys item : list) {
				String str_ = getJsonPlayerArmy(item);
				s_.append(str_).append(",");
			}
		}
		r = s_.toString();
		int len_ = r.length();
		if (len_ > 3) {
			r = r.substring(0, len_ - 1);
		}
		r += "]";
		return r;
	}

	public static String getJsonPlayerArmy(Player_armys item) {
		StringBuffer s_ = new StringBuffer();
		s_.append("{");
		s_.append("\"num\"").append(":").append(item.getNum()).append(",");
		int lvl = item.getLvl();
		lvl = lvl < 1 ? 1 : lvl;
		s_.append("\"lvl\"").append(":").append(lvl).append(",");
		s_.append("\"bcid\"").append(":").append(item.getBcid())
				.append(",");
		s_.append("\"gid\"").append(":").append(item.getGid());
		s_.append("}");
		String r = s_.toString();
		return r;
	}

	public static void writeArmy(Player item, int index) {
		String path = getJsonPathPlayer(index, Type_Player_Army);
		List<Player_armys> list_army = Player_armysEntity
				.getPlayerArmyList(item);
		String strCont = getJsonPlayerArmy(list_army);
		ReadWriteString.writeStr(path, strCont);
	}

	public static String getJsonPlayerTech(List<Player_tech> list) {
		String r = "";
		StringBuffer s_ = new StringBuffer();
		s_.append("[");
		if (list != null && list.size() > 0) {
			for (Player_tech item : list) {
				String str_ = getJsonPlayerTech(item);
				s_.append(str_).append(",");
			}
		}
		r = s_.toString();
		int len_ = r.length();
		if (len_ > 3) {
			r = r.substring(0, len_ - 1);
		}
		r += "]";
		return r;
	}

	public static String getJsonPlayerTech(Player_tech item) {
		StringBuffer s_ = new StringBuffer();
		s_.append("{");
		int lvl = item.getLvl();
		lvl = lvl < 1 ? 1 : lvl;
		s_.append("\"lvl\"").append(":").append(lvl).append(",");
		s_.append("\"gid\"").append(":").append(item.getGid());
		s_.append("}");
		String r = s_.toString();
		return r;
	}

	public static void writeTech(Player item, int index) {
		String path = getJsonPathPlayer(index, Type_Player_Tech);
		List<Player_tech> list_tech = Player_techEntity
				.getPlayerTechList(item);
		String strCont = getJsonPlayerTech(list_tech);
		ReadWriteString.writeStr(path, strCont);
	}

	public static String getJsonPlayerObst(List<Player_build_obst> list) {
		String r = "";
		StringBuffer s_ = new StringBuffer();
		s_.append("[");
		if (list != null && list.size() > 0) {
			for (Player_build_obst item : list) {
				String str_ = getJsonPlayerObst(item);
				s_.append(str_).append(",");
			}
		}
		r = s_.toString();
		int len_ = r.length();
		if (len_ > 3) {
			r = r.substring(0, len_ - 1);
		}
		r += "]";
		return r;
	}

	public static String getJsonPlayerObst(Player_build_obst item) {
		StringBuffer s_ = new StringBuffer();
		s_.append("{");
		// s_.append("\"type\"").append(":").append(item.getType()).append(",");
		int lvl = item.getLvl();
		lvl = lvl < 1 ? 1 : lvl;
		s_.append("\"lvl\"").append(":").append(lvl).append(",");
		s_.append("\"pos\"").append(":").append(item.getPosition_index())
				.append(",");
		s_.append("\"bcid\"").append(":").append(item.getBcid())
				.append(",");
		s_.append("\"gid\"").append(":").append(item.getGid());
		s_.append("}");
		String r = s_.toString();
		return r;
	}

	public static void writeObst(Player item, int index) {
		List<Player_build_obst> list_obst = LogicObst.getList(item);
		writeObst(list_obst, index);
	}

	public static void writeObst(List<Player_build_obst> list, int index) {
		String path = getJsonPathPlayer(index, Type_Player_Obst);
		String strCont = getJsonPlayerObst(list);
		ReadWriteString.writeStr(path, strCont);
	}

	public static void main(String[] args) {
		export();
	}
}
