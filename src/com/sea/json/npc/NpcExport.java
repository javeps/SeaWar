package com.sea.json.npc;

import java.util.List;
import java.util.Map;

import com.sea.db.bean.Player;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_tech;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Player_techEntity;
import com.sea.logic.logicOperate.LogicArmy;
import com.sea.logic.logicOperate.LogicBuild;
import com.sea.logic.logicOperate.LogicObst;
import com.sea.tools.ReadWriteString;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NpcExport {
	public static final int Type_Player = 1;
	public static final int Type_Player_Build = 2;
	public static final int Type_Player_Army = 3;
	public static final int Type_Player_Tech = 4;
	public static final int Type_Player_Obst = 5;
	public static final int Type_Player_Build_Pos = 6;
	public static final int Type_Player_Obst_Pos = 7;

	public static final String Key_Min = "min";
	public static final String Key_Max = "max";

	public static String getJsonPathPlayer(int townLvl, final int type) {
		StringBuffer s_ = new StringBuffer();
		s_.append("json/game/npc/").append("town").append(townLvl).append("/")
				.append("lvl").append("_").append(townLvl);
		switch (type) {
		case Type_Player_Build:
			s_.append("_B");
			break;
		case Type_Player_Build_Pos:
			s_.append("_BPos");
			break;
		case Type_Player_Army:
			s_.append("_A");
			break;
		case Type_Player_Tech:
			s_.append("_T");
			break;
		case Type_Player_Obst:
			s_.append("_O");
			break;
		case Type_Player_Obst_Pos:
			s_.append("_OPos");
			break;
		case Type_Player:
			boolean isWeek = UtileTools.isWeekNow();
			if(isWeek){
				s_.append("_week");
			}
			break;
		default:
			break;
		}
		s_.append(".json");
		String r = s_.toString();
		return r;
	}

	public static void export() {
		List<Player> list_ = PlayerEntity.getListRankByNum(1);
		if (list_ != null && list_.size() > 0) {
			int i = 0;
			for (Player item : list_) {
				writeDate(item, i);
				writeDate(item, i, true, 1);
				i++;
			}
		}
	}

	public static void exportOne(int townLvl, int pos, boolean isOpen) {
		List<Player> list_ = PlayerEntity.getListRankByNum(1);
		if (list_ != null && list_.size() > 0) {
			Player item = list_.get(0);
			if (isOpen) {
				writeDate(item, townLvl);
			}
			writeDate(item, townLvl, isOpen, pos);
		}
	}

	public static void writeDate(Player item, int townlvl) {
		writePlayer(item, townlvl);
		writeArmy(item, townlvl);
		writeTech(item, townlvl);
	}

	public static void writeDate(Player item, int townlvl, boolean isOpen,
			int index) {
		writeBuild(item, townlvl, isOpen, index);
		writeObst(item, townlvl, isOpen, index);
	}

	// ============ player

	public static void writePlayer(Player item, int townlvl) {
		String path = getJsonPathPlayer(townlvl, Type_Player);
		String strCont = getNPC(item);
		Map map = UtileTools.strToMap(strCont);
		strCont = UtileTools.mapToStr(map);
		ReadWriteString.writeStr(path, strCont);
	}

	private static String getNPC(Player item) {
		int minRenown = 100;
		int minGold = 500;
		int minOil = 500;
		int minExp = 50;
		int maxGold = item.getStored_gold();
		int maxOil = item.getStored_oil();

		int maxCanG = maxGold * 2 / 7;
		int maxCanO = maxOil * 2 / 7;

		StringBuffer s_ = new StringBuffer();
		s_.append("{");

		s_.append("\"renown\"").append(":");
		s_.append("\"{");
		s_.append("\"").append(Key_Min).append("\"").append(":")
				.append(minRenown).append(",");
		s_.append("\"").append(Key_Max).append("\"").append(":")
				.append(item.getRenown());
		s_.append("}\"").append(",");

		s_.append("\"exp\"").append(":");
		s_.append("\"{");
		s_.append("\"").append(Key_Min).append("\"").append(":").append(minExp)
				.append(",");
		s_.append("\"").append(Key_Max).append("\"").append(":")
				.append(item.getExp());
		s_.append("}\"").append(",");

		s_.append("\"gold\"").append(":");
		s_.append("\"{");
		s_.append("\"").append(Key_Min).append("\"").append(":")
				.append(minGold).append(",");
		s_.append("\"").append(Key_Max).append("\"").append(":")
				.append(maxCanG);
		s_.append("}\"").append(",");

		s_.append("\"oil\"").append(":");
		s_.append("\"{");
		s_.append("\"").append(Key_Min).append("\"").append(":").append(minOil)
				.append(",");
		s_.append("\"").append(Key_Max).append("\"").append(":")
				.append(maxCanO);
		s_.append("}\"");

		s_.append(",");
		s_.append("\"maxGold\"").append(":").append(maxGold).append(",");
		s_.append("\"maxOild\"").append(":").append(maxOil);

		s_.append("}");
		String r = s_.toString();
		return r;
	}

	// ============ amry

	public static void writeArmy(Player item, int index) {
		String path = getJsonPathPlayer(index, Type_Player_Army);
		List<Player_armys> list_army = LogicArmy.getListOwnArmyInData(item);
		String strCont = getArmy(list_army);

		// Map map = UtileTools.strToMap(strCont);
		// strCont = UtileTools.mapToStr(map);

		List list_to = UtileTools.strToList(strCont);
		strCont = UtileTools.listToStr(list_to);

		ReadWriteString.writeStr(path, strCont);
	}

	private static String getArmy(List<Player_armys> list) {
		String r = "";
		StringBuffer s_ = new StringBuffer();
		s_.append("[");
		if (list != null && list.size() > 0) {
			for (Player_armys item : list) {
				String str_ = getArmy(item);
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

	private static String getArmy(Player_armys item) {
		int lvl = item.getLvl();
		lvl = lvl < 1 ? 1 : lvl;
		StringBuffer s_ = new StringBuffer();
		s_.append("{");
		s_.append("\"num\"").append(":");
		s_.append("\"{");
		s_.append("\"").append(Key_Min).append("\"").append(":").append(0)
				.append(",");
		s_.append("\"").append(Key_Max).append("\"").append(":")
				.append(item.getNum());
		s_.append("}\"").append(",");
		s_.append("\"lvl\"").append(":");
		s_.append("\"{");
		s_.append("\"").append(Key_Min).append("\"").append(":").append(0)
				.append(",");
		s_.append("\"").append(Key_Max).append("\"").append(":").append(lvl);
		s_.append("}\"").append(",");
		s_.append("\"bcid\"").append(":").append(item.getBcid()).append(",");
		s_.append("\"gid\"").append(":").append(item.getGid());
		s_.append("}");
		String r = s_.toString();
		return r;
	}

	// ====== tech

	public static void writeTech(Player item, int index) {
		String path = getJsonPathPlayer(index, Type_Player_Tech);
		List<Player_tech> list_tech = Player_techEntity.getPlayerTechList(item);
		String strCont = getTech(list_tech);

		// Map map = UtileTools.strToMap(strCont);
		// strCont = UtileTools.mapToStr(map);

		List list_to = UtileTools.strToList(strCont);
		strCont = UtileTools.listToStr(list_to);

		ReadWriteString.writeStr(path, strCont);
	}

	private static String getTech(List<Player_tech> list) {
		String r = "";
		StringBuffer s_ = new StringBuffer();
		s_.append("[");
		if (list != null && list.size() > 0) {
			for (Player_tech item : list) {
				String str_ = getTech(item);
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

	private static String getTech(Player_tech item) {
		StringBuffer s_ = new StringBuffer();
		s_.append("{");
		int lvl = item.getLvl();
		lvl = lvl < 1 ? 1 : lvl;
		s_.append("\"lvl\"").append(":");
		s_.append("\"{");
		s_.append("\"").append(Key_Min).append("\"").append(":").append(1)
				.append(",");
		s_.append("\"").append(Key_Max).append("\"").append(":").append(lvl);
		s_.append("}\"").append(",");
		s_.append("\"gid\"").append(":").append(item.getGid());
		s_.append("}");
		String r = s_.toString();
		return r;
	}

	// ============== build

	public static void writeBuild(Player item, int townlvl, boolean isOpen,
			int posIndex) {
		List<Player_buildings> list_builds = LogicBuild.getListInData(item);
		if (isOpen) {
			writeBuild(list_builds, townlvl);
		}
		// pos
		writeBuildPos(townlvl, posIndex, list_builds);
	}

	public static void writeBuild(List<Player_buildings> list, int townlvl) {
		String path = getJsonPathPlayer(townlvl, Type_Player_Build);
		String strCont = getBuild(list);

		// List list_to = UtileTools.strToList(strCont);
		// strCont = UtileTools.listToStr(list_to);

		Map map = UtileTools.strToMap(strCont);
		strCont = UtileTools.mapToStr(map);
		ReadWriteString.writeStr(path, strCont);
	}

	private static String getBuild(List<Player_buildings> list) {
		String r = "";
		StringBuffer s_ = new StringBuffer();
		// s_.append("[");
		s_.append("{");
		if (list != null && list.size() > 0) {
			for (Player_buildings item : list) {
				String str_ = getBuild(item);
				int bcid = item.getBcid();
				s_.append(bcid).append(":").append(str_).append(",");
			}
		}
		r = s_.toString();
		int len_ = r.length();
		if (len_ > 3) {
			r = r.substring(0, len_ - 1);
		}
		r += "}";
		// r += "]";
		return r;
	}

	private static String getBuild(Player_buildings item) {
		int lvl = item.getLvl();
		lvl = lvl < 1 ? 1 : lvl;
		StringBuffer s_ = new StringBuffer();
		// s_.append("'");
		s_.append("{");
		// s_.append("\"bcid\"").append(":").append(item.getBcid()).append(",");
		s_.append("\"gid\"").append(":").append(item.getGid()).append(",");
		s_.append("\"lvl\"").append(":");
		s_.append("\"{");
		s_.append("\"").append(Key_Min).append("\"").append(":").append(1)
				.append(",");
		s_.append("\"").append(Key_Max).append("\"").append(":").append(lvl);
		s_.append("}\"");
		s_.append("}");
		// s_.append("'");
		String r = s_.toString();
		return r;
	}

	// =========== build pos

	public static void writeBuildPos(int townlvl, int posIndex,
			List<Player_buildings> list) {
		if (list == null || list.size() <= 0)
			return;
		String path = getJsonPathPlayer(townlvl, Type_Player_Build_Pos);
		Map map = UtileTools.readStrMap(path);
		map.put("len", posIndex);
		String key = "pos_" + posIndex;
		String valList = getBuildPos(list);
		map.put(key, valList);
		String strCont = UtileTools.mapToStr(map);
		ReadWriteString.writeStr(path, strCont);
	}

	private static String getBuildPos(List<Player_buildings> list) {
		String r = "";
		StringBuffer s_ = new StringBuffer();
		// s_.append("[");
		s_.append("{");
		if (list != null && list.size() > 0) {
			for (Player_buildings item : list) {
				int bcid = item.getBcid();
				int pos = item.getPosition_index();
				s_.append(bcid).append(":").append(pos).append(",");
			}
		}
		r = s_.toString();
		int len_ = r.length();
		if (len_ > 3) {
			r = r.substring(0, len_ - 1);
		}
		// r += "]";
		r += "}";
		return r;
	}

	@SuppressWarnings("unused")
	private static String getBuildPos(Player_buildings item) {
		StringBuffer s_ = new StringBuffer();
		s_.append("{");
		s_.append("\"bcid\"").append(":").append(item.getBcid()).append(",");
		s_.append("\"pos\"").append(":").append(item.getPosition_index());
		s_.append("}");
		String r = s_.toString();
		return r;
	}

	// ========= obst

	public static void writeObst(Player item, int townLvl, boolean isOpen,
			int posIndex) {
		List<Player_build_obst> list_obst = LogicObst.getList(item);
		if (isOpen) {
			writeObst(list_obst, townLvl);
		}
		// pos
		writeObstPos(townLvl, posIndex, list_obst);
	}

	public static void writeObst(List<Player_build_obst> list, int index) {
		String path = getJsonPathPlayer(index, Type_Player_Obst);
		String strCont = getObst(list);
		// List list_to = UtileTools.strToList(strCont);
		// strCont = UtileTools.listToStr(list_to);
		Map map = UtileTools.strToMap(strCont);
		strCont = UtileTools.mapToStr(map);
		ReadWriteString.writeStr(path, strCont);
	}

	private static String getObst(List<Player_build_obst> list) {
		String r = "";
		StringBuffer s_ = new StringBuffer();
		// s_.append("[");
		s_.append("{");
		if (list != null && list.size() > 0) {
			for (Player_build_obst item : list) {
				int bcid = item.getBcid();
				String str_ = getObst(item);
				s_.append(bcid).append(":").append(str_).append(",");
			}
		}
		r = s_.toString();
		int len_ = r.length();
		if (len_ > 3) {
			r = r.substring(0, len_ - 1);
		}
		// r += "]";
		r += "}";
		return r;
	}

	private static String getObst(Player_build_obst item) {
		int lvl = item.getLvl();
		lvl = lvl < 1 ? 1 : lvl;
		StringBuffer s_ = new StringBuffer();
		// s_.append("'");
		s_.append("{");
		// s_.append("\"bcid\"").append(":").append(item.getBcid()).append(",");
		s_.append("\"gid\"").append(":").append(item.getGid()).append(",");
		s_.append("\"lvl\"").append(":");
		s_.append("\"{");
		s_.append("\"").append(Key_Min).append("\"").append(":").append(1)
				.append(",");
		s_.append("\"").append(Key_Max).append("\"").append(":").append(lvl);
		s_.append("}\"");
		s_.append("}");
		// s_.append("'");
		String r = s_.toString();
		return r;
	}

	// =========== Obst pos

	public static void writeObstPos(int townlvl, int posIndex,
			List<Player_build_obst> list) {
		if (list == null || list.size() <= 0)
			return;
		String path = getJsonPathPlayer(townlvl, Type_Player_Obst_Pos);
		Map map = UtileTools.readStrMap(path);
		map.put("len", posIndex);
		String key = "pos_" + posIndex;
		String valList = getObstPos(list);
		map.put(key, valList);
		String strCont = UtileTools.mapToStr(map);
		ReadWriteString.writeStr(path, strCont);
	}

	private static String getObstPos(List<Player_build_obst> list) {
		String r = "";
		StringBuffer s_ = new StringBuffer();
		// s_.append("[");
		s_.append("{");
		if (list != null && list.size() > 0) {
			for (Player_build_obst item : list) {
				int bcid = item.getBcid();
				int pos = item.getPosition_index();
				s_.append(bcid).append(":").append(pos).append(",");
			}
		}
		r = s_.toString();
		int len_ = r.length();
		if (len_ > 3) {
			r = r.substring(0, len_ - 1);
		}
		r += "}";
		// r += "]";
		return r;
	}

	@SuppressWarnings("unused")
	private static String getObstPos(Player_build_obst item) {
		StringBuffer s_ = new StringBuffer();
		s_.append("{");
		s_.append("\"bcid\"").append(":").append(item.getBcid()).append(",");
		s_.append("\"pos\"").append(":").append(item.getPosition_index());
		s_.append("}");
		String r = s_.toString();
		return r;
	}
}
