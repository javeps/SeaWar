package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.NTech;
import gen_b2g.serv.bean.NTeches;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.process.ProTech;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_tech;
import com.sea.db.dao.Player_techDAO;
import com.sea.db.internal.Player_techInternal;
import com.sea.logic.logicEntity.LEArmy;
//import com.sea.content.AppContext;

//seawar_design - player_tech
@SuppressWarnings({ "static-access" })
public class Player_techEntity extends Player_techInternal {
	static Log log = LogFactory.getLog(Player_techEntity.class);

	public static final Player_techEntity my = new Player_techEntity();

	static Player_techDAO Player_techDAO = null;

	public static Player_techDAO Player_techDAO() {
		if (Player_techDAO == null)
			Player_techDAO = new Player_techDAO(com.sea.content.AppContext.ds());
		return Player_techDAO;
	}

	// static Player_techDAO Player_techDAO99 = null;
	// public static Player_techDAO Player_techDAO99() {
	// if( Player_techDAO99 == null)
	// Player_techDAO99 = new Player_techDAO(com.sea.content.AppContext.ds99());
	// return Player_techDAO99;
	// }

	public static void insertMmTry(final Player_tech player_tech) {
		Player_techDAO DAO = Player_techDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(player_tech, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// ================== 数据库操作 begin ==================
	// 插入数据
	public static void insertTech(Player_tech r2) {
		if (r2 != null) {
			ProTech.inBuild(r2);
		}
	}

	// 更新数据
	public static void updateTech(Player_tech r2) {
		if (r2 != null) {
			ProTech.upBuild(r2);
		}
	}

	// 删除数据
	public static void deleteTech(Player_tech origin) {
		if (origin != null) {
			ProTech.deletePB(origin);
		}
	}

	// 取得用户的某个科技
	public static Player_tech getPlayerTech(Player p, int gid) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		return getPlayerTech(pcid, gid);
	}

	public static Player_tech getPlayerTech(int pcid, int gid) {
		return ProTech.getHeroByPcidGid(pcid, gid);
	}

	public static List<Player_tech> getPlayerTechList(int pcid, String teches) {
		List<Player_tech> r2 = null;
		r2 = ProTech.getListByPcid(pcid, teches);
		return r2;
	}

	// ================== 数据库操作 end ==================
	public static boolean isHasTechGid(int gid) {
		return LEArmy.isHasAmryGid(gid);
	}

	// 创建新科技对象
	public static Player_tech createNewTech(int gid, int lvl, int pcid,
			String pname) {
		String tech_name = LEArmy.getArmyNameGid(gid);
		Player_tech r2 = Player_tech.newPlayer_tech(0, tech_name, pcid, pname,
				gid, lvl);
		return r2;
	}

	// 创建新科技并插入数据
	public static boolean createNewTechInsert(Player p, int gid, int lvl) {
		boolean r2 = false;
		boolean is_has_gid = isHasTechGid(gid);
		if (is_has_gid && p != null) {
			int pcid = p.getPcid();
			Player_tech p_t = createNewTech(gid, lvl, pcid, p.getPname());
			insertTech(p_t);
			String teches = p.getTeches();
			p.setTeches(teches + gid + ",");
			PlayerEntity.updatePlayer(p);
			r2 = true;
		}
		return r2;
	}

	public static boolean upgradeTech(Player p, int gid) {
		boolean r2 = false;
		Player_tech p_t = getPlayerTech(p, gid);
		if (p_t == null) {
			r2 = createNewTechInsert(p, gid, 1);
		}
		return r2;
	}

	// 完成升级科技
	public static boolean finisTech(Player p, int gid) {
		boolean r2 = false;
		Player_tech p_t = getPlayerTech(p, gid);
		if (p_t == null) {
			r2 = createNewTechInsert(p, gid, 2);
		} else {
			int pre_lvl = p_t.getLvl();
			pre_lvl++;
			p_t.setLvl(pre_lvl);
			updateTech(p_t);
		}
		return r2;
	}

	// 取得用户的科技list
	public static List<Player_tech> getPlayerTechList(Player p) {
		if (p == null)
			return null;
		String teches = p.getTeches();
		List<Player_tech> r2 = getPlayerTechList(p.getPcid(), teches);
		if ((teches == null || "".equals(teches.trim())) && r2 != null) {
			StringBuffer buffer = new StringBuffer();
			for (Player_tech item : r2) {
				buffer.append(item.getGid()).append(",");
			}
			teches = buffer.toString();
			p.setTeches(teches);
			PlayerEntity.updatePlayer(p);
		}
		return r2;
	}

	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	// 数据库对象，转换为客户端数据
	public static NTech toSUPTech(Player_tech p_t) {
		if (p_t == null)
			return null;
		NTech r2 = new NTech();
		r2.gid = p_t.gid;
		r2.lvl = p_t.lvl;
		r2.tid = p_t.id;
		r2.tname = p_t.tech_name;
		return r2;
	}

	// 把数据对象集合List转换成客户端对象集合List
	public static List<NTech> toSUPTechList(List<Player_tech> p_t_list) {
		List<NTech> r2 = new ArrayList<NTech>();
		if (p_t_list != null) {
			for (Player_tech item : p_t_list) {
				NTech supt = toSUPTech(item);
				if (supt == null)
					continue;
				r2.add(supt);
			}
		}
		return r2;
	}

	// 取得LIST集合并转换成客户端需求的List集合对象
	public static NTeches toSUPTeches(List<Player_tech> p_t_list, NTeches r2) {
		if (p_t_list == null || r2 == null)
			return r2;
		r2.techs = toSUPTechList(p_t_list);
		return r2;
	}
	// types end

}
