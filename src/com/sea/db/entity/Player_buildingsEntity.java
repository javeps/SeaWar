package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.NBuild;
import gen_b2g.serv.bean.NBuilds;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.process.ProBuild;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_buildings;
import com.sea.db.dao.Player_buildingsDAO;
import com.sea.db.internal.Player_buildingsInternal;
import com.sea.logic.logicEntity.LEBuild;
//import com.sea.content.AppContext;

//seawar_design - player_buildings
@SuppressWarnings({ "static-access" })
public class Player_buildingsEntity extends Player_buildingsInternal {
	static Log log = LogFactory.getLog(Player_buildingsEntity.class);

	public static final Player_buildingsEntity my = new Player_buildingsEntity();

	static Player_buildingsDAO Player_buildingsDAO = null;

	public static Player_buildingsDAO Player_buildingsDAO() {
		if (Player_buildingsDAO == null)
			Player_buildingsDAO = new Player_buildingsDAO(
					com.sea.content.AppContext.ds());
		return Player_buildingsDAO;
	}

	// static Player_buildingsDAO Player_buildingsDAO99 = null;
	// public static Player_buildingsDAO Player_buildingsDAO99() {
	// if( Player_buildingsDAO99 == null)
	// Player_buildingsDAO99 = new
	// Player_buildingsDAO(com.sea.content.AppContext.ds99());
	// return Player_buildingsDAO99;
	// }

	public static void insertMmTry(final Player_buildings player_buildings) {
		Player_buildingsDAO DAO = Player_buildingsDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(player_buildings, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// ================== 数据库操作 begin ==================
	// 判断建筑等级是否足够
	public static boolean isEnoughLvl(Player_buildings p_b, int needLvl) {
		boolean r2 = false;
		if (p_b != null) {
			r2 = p_b.getLvl() >= needLvl;
		}
		return r2;
	}

	// 插入数据
	public static void insertBuild(Player_buildings r2) {
		if (r2 != null) {
			ProBuild.inBuild(r2);
		}
	}

	// 更新数据
	public static void updateBuild(Player_buildings r2) {
		if (r2 != null) {
			ProBuild.upBuild(r2);
		}
	}

	// 删除数据
	public static void deleteBuild(Player_buildings origin) {
		if (origin != null) {
			ProBuild.deletePB(origin);
		}
	}

	public static List<Player_buildings> getListInData(int pcid, String buildes) {
		List<Player_buildings> r2 = null;
		r2 = ProBuild.getListByPcid(pcid, buildes);
		return r2;
	}

	// 取得角色对象的建筑
	public static Player_buildings getBuildByPlayer(Player p, int bcid) {
		Player_buildings r2 = null;
		if (p == null)
			return r2;
		int pcid = p.getPcid();
		r2 = ProBuild.getBuildByPcidBcid(pcid, bcid);
		return r2;
	}

	// ================== 数据库操作 end ==================
	// 返回创建的新的建筑对象
	public static Player_buildings createNewBuild(int pid, String pname,
			int gid, long cooldown, int lvl, int position, int client_id) {
		String building_name = LEBuild.getBuldingNameGid(gid);
		int type = LEBuild.getTypeByGid(gid);
		int state = LEBuild.Status_Default;
		long cur_produce_res = 0;
		long end_tech_up_time = 0;
		int cur_up_tech_gid = 0;
		long begin_army_time = 0;
		long res_produce_begin_time = 0;

		if (cooldown <= 0
				&& (gid == LEBuild.GID_GoldMine || gid == LEBuild.GID_OilWell)) {
			res_produce_begin_time = System.currentTimeMillis();
		}

		Player_buildings r2 = Player_buildings.newPlayer_buildings(0,
				client_id, building_name, pid, pname, gid, lvl, cooldown,
				position, state, type, cur_produce_res, res_produce_begin_time,
				cur_up_tech_gid, end_tech_up_time, begin_army_time);
		return r2;
	}

	// 创建新建筑并且插入数据库
	public static Player_buildings createNewBuildInsert(int pid, String pname,
			int gid, long cooldown, int lvl, int position, int client_id) {
		if (gid == LEBuild.GID_Wall) {
			cooldown = 0;
		}
		if (cooldown > 0) {
			long now_time = System.currentTimeMillis();
			cooldown += now_time;
		} else {
			if (lvl == 0)
				lvl = 1;
		}
		Player_buildings r2 = createNewBuild(pid, pname, gid, cooldown, lvl,
				position, client_id);
		insertBuild(r2);
		return r2;
	}

	// 取得角色的所有建筑对象(包括树)List
	public static List<Player_buildings> getPlayerBuidList(Player p) {
		List<Player_buildings> r2 = null;
		if (p != null) {
			int pcid = p.getPcid();
			String builds = p.getBuilds();
			r2 = getListInData(pcid, builds);
		}
		return r2;
	}

	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	// 把数据建筑对象转换成客户端建筑对象
	public static NBuild toSUPBuid(Player_buildings pbu) {
		if (pbu == null)
			return null;
		NBuild r2 = new NBuild();
		r2.begin_army_time = pbu.begin_army_time;
		r2.client_id = pbu.getBcid();
		r2.bname = pbu.building_name;
		r2.cur_pro_res = pbu.cur_produce_res;
		r2.end_army_time = 0;
		r2.gid = pbu.gid;
		r2.pos_index = pbu.position_index;
		r2.state = pbu.state;
		r2.type = pbu.type;

		long end_tech = pbu.end_tech_up_time;
		long res_beg = pbu.res_produce_begin_time;
		int up_tech_gid = pbu.cur_up_tech_gid;
		int p_lvl = pbu.lvl;
		long cool_down = pbu.cooldown_ms;
		r2.cur_up_tech_gid = up_tech_gid;
		r2.pro_res_beg_time = res_beg;
		r2.end_tech_up_time = end_tech;
		r2.lvl = p_lvl;
		r2.cooldown = cool_down;
		return r2;
	}

	// 把数据建筑对象集合List转换成客户端建筑对象集合List
	public static List<NBuild> toSUPBuildList(List<Player_buildings> blist) {
		List<NBuild> sblist = new ArrayList<NBuild>();
		if (blist == null)
			return sblist;
		for (Player_buildings item : blist) {
			NBuild supb = toSUPBuid(item);
			if (supb == null)
				continue;
			sblist.add(supb);
		}
		return sblist;
	}

	// 转换成客户端需求的List集合对象
	public static NBuilds toSUPBuilds(List<Player_buildings> blist, NBuilds sups) {
		if (blist == null || sups == null)
			return sups;
		List<NBuild> origin = toSUPBuildList(blist);

		if (sups.builds == null || sups.builds.size() == 0) {
			sups.builds = origin;
		} else {
			sups.builds.addAll(origin);
		}
		return sups;
	}
	// types end

}
