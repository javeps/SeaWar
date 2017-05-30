package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.NArmy;
import gen_b2g.serv.bean.NArmys;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.process.ProAmry;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_buildings;
import com.sea.db.dao.Player_armysDAO;
import com.sea.db.internal.Player_armysInternal;
import com.sea.logic.logicEntity.LEArmy;
//import com.sea.content.AppContext;

//seawar_design - player_armys
@SuppressWarnings({ "static-access" })
public class Player_armysEntity extends Player_armysInternal {
	static Log log = LogFactory.getLog(Player_armysEntity.class);

	public static final Player_armysEntity my = new Player_armysEntity();

	static Player_armysDAO Player_armysDAO = null;

	public static Player_armysDAO Player_armysDAO() {
		if (Player_armysDAO == null)
			Player_armysDAO = new Player_armysDAO(
					com.sea.content.AppContext.ds());
		return Player_armysDAO;
	}

	// static Player_armysDAO Player_armysDAO99 = null;
	// public static Player_armysDAO Player_armysDAO99() {
	// if( Player_armysDAO99 == null)
	// Player_armysDAO99 = new
	// Player_armysDAO(com.sea.content.AppContext.ds99());
	// return Player_armysDAO99;
	// }

	public static void insertMmTry(final Player_armys player_armys) {
		Player_armysDAO DAO = Player_armysDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(player_armys, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// ================== 数据库操作 begin ==================
	// 插入数据
	public static void insertArmy(Player_armys r2) {
		if (r2 != null) {
			ProAmry.inEntity(r2);
		}
	}

	// 更新数据
	public static void updateArmy(Player_armys r2) {
		if (r2 != null) {
			ProAmry.upEntity(r2);
		}
	}

	// 删除数据
	public static void deleteArmy(Player_armys origin) {
		if (origin != null) {
			ProAmry.deleteData(origin);
		}
	}

	// 取得某种兵
	public static Player_armys getArmy(int pcid, int gid, int bcid) {
		Player_armys r2 = null;
		r2 = ProAmry.getArmy(pcid, bcid, gid);
		return r2;
	}

	public static List<Player_armys> getPlayerArmyList(int pcid, String builds) {
		List<Player_armys> r2 = null;
		r2 = ProAmry.getListByPcid(pcid, builds);
		if (r2 == null) {
			// r2 = getByPcid(pcid);
			// ArmyJedis.setList(r2);
		}
		return r2;
	}

	// ================== 数据库操作 end ==================
	// 返回创建的新的兵对象
	public static Player_armys createNewArmy(int pcid, String pname, int gid,
			int lvl, int num, int bcid) {
		String army_name = LEArmy.getArmyNameGid(gid);
		Player_armys r2 = Player_armys.newPlayer_armys(0, army_name, pcid,
				pname, gid, lvl, num, bcid);
		return r2;
	}

	// 创建新兵并且插入数据库
	public static Player_armys createNewArmyInsert(Player_buildings p_b,
			int gid, int lvl, int num) {
		Player_armys r2 = null;
		if (p_b == null || num < 0)
			return r2;
		r2 = createNewArmy(p_b.getPcid(), p_b.getPlayer_name(), gid, lvl, num,
				p_b.getBcid());
		insertArmy(r2);
		return r2;
	}

	// 兵的数量增减
	public static Player_armys changeNum(Player_armys p_a, int num) {
		if (p_a != null && num != 0) {
			int cur_num = p_a.getNum();
			int change_num = cur_num + num;
			if (change_num < 0) {
				change_num = 0;
			}
			p_a.setNum(change_num);
		}
		return p_a;
	}

	// 增加兵
	public static boolean addArmy(Player_armys p_a, int num) {
		boolean r2 = false;
		if (p_a != null && num > 0) {
			p_a = changeNum(p_a, num);
			updateArmy(p_a);
			r2 = true;
		}
		return r2;
	}

	// 取得角色拥有的兵List集
	public static List<Player_armys> getPlayerArmyList(Player p) {
		List<Player_armys> r2 = null;
		if (p != null) {
			r2 = getPlayerArmyList(p.getPcid(), p.getBuilds());
		}
		return r2;
	}

	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	// 数据库对象，转换为客户端数据
	public static NArmy toSUPArmy(Player_armys p_a) {
		if (p_a == null)
			return null;
		NArmy r2 = new NArmy();
		r2.aid = p_a.id;
		r2.aname = p_a.army_name;
		r2.gid = p_a.gid;
		r2.lvl = p_a.lvl;
		r2.num = p_a.num;
		r2.buildClientId = p_a.getBcid();
		return r2;
	}

	// 把数据对象集合List转换成客户端对象集合List
	public static List<NArmy> toSUPArmyList(List<Player_armys> paList) {
		List<NArmy> r2 = new ArrayList<NArmy>();
		if (paList != null) {
			for (Player_armys item : paList) {
				NArmy sup_a = toSUPArmy(item);
				if (sup_a == null)
					continue;
				r2.add(sup_a);
			}
		}
		return r2;
	}

	// 取得角色的所有序列并转换成客户端需求的List集合对象
	public static NArmys toSUPArmys(List<Player_armys> paList, NArmys supas) {
		if (paList == null || supas == null)
			return supas;
		supas.armys = toSUPArmyList(paList);
		return supas;
	}
	// types end

}
