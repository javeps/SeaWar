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

import com.sea.cache.process.ProObst;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.dao.Player_build_obstDAO;
import com.sea.db.internal.Player_build_obstInternal;
//import com.sea.content.AppContext;
import com.sea.logic.logicEntity.LEBuild;

//seawar_design - player_build_obst
@SuppressWarnings({ "static-access" })
public class Player_build_obstEntity extends Player_build_obstInternal {
	static Log log = LogFactory.getLog(Player_build_obstEntity.class);

	public static final Player_build_obstEntity my = new Player_build_obstEntity();

	static Player_build_obstDAO Player_build_obstDAO = null;

	public static Player_build_obstDAO Player_build_obstDAO() {
		if (Player_build_obstDAO == null)
			Player_build_obstDAO = new Player_build_obstDAO(
					com.sea.content.AppContext.ds());
		return Player_build_obstDAO;
	}

	// static Player_build_obstDAO Player_build_obstDAO99 = null;
	// public static Player_build_obstDAO Player_build_obstDAO99() {
	// if( Player_build_obstDAO99 == null)
	// Player_build_obstDAO99 = new
	// Player_build_obstDAO(com.sea.content.AppContext.ds99());
	// return Player_build_obstDAO99;
	// }

	public static void insertMmTry(final Player_build_obst player_build_obst) {
		Player_build_obstDAO DAO = Player_build_obstDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(player_build_obst, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// 插入数据
	public static void insertNew(Player_build_obst r2) {
		if (r2 != null) {
			ProObst.inBuild(r2);
		}
	}

	// 更新数据
	public static void updateCur(Player_build_obst r2) {
		if (r2 != null) {
			ProObst.upBuild(r2);
		}
	}

	// 删除数据
	public static void deleteCur(Player_build_obst origin) {
		if (origin != null) {
			ProObst.deletePB(origin);
		}
	}

	public static List<Player_build_obst> getListByPcid(int pcid, String obstes) {
		List<Player_build_obst> r2 = null;
		r2 = ProObst.getListByPcid(pcid, obstes);
		return r2;
	}

	// 返回创建的新的建筑对象
	public static Player_build_obst createNew(int pid, String pname, int gid,
			long cooldown, int lvl, int position, int client_id) {
		String building_name = LEBuild.getBuldingNameGid(gid);
		int type = LEBuild.getTypeByGid(gid);
		int state = LEBuild.Status_Default;
		Player_build_obst r2 = Player_build_obst.newPlayer_build_obst(0,
				client_id, building_name, pid, pname, gid, lvl, cooldown,
				position, state, type);
		return r2;
	}

	// 创建新建筑并且插入数据库
	public static Player_build_obst createNewInsert(int pid, String pname,
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
		Player_build_obst r2 = createNew(pid, pname, gid, cooldown, lvl,
				position, client_id);
		insertNew(r2);
		return r2;
	}

	// 取得角色的所有建筑对象(包括树)List
	public static List<Player_build_obst> getList(Player p) {
		List<Player_build_obst> r2 = null;
		if (p != null) {
			r2 = getListByPcid(p.getPcid(), p.getObstes());
		}
		return r2;
	}

	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	// 把数据建筑对象转换成客户端建筑对象
	public static NBuild toSUPBuid(Player_build_obst pbu) {
		if (pbu == null)
			return null;
		NBuild r2 = new NBuild();
		r2.client_id = pbu.getBcid();
		r2.bname = pbu.getBuilding_name();
		r2.gid = pbu.getGid();
		r2.pos_index = pbu.getPosition_index();
		r2.state = pbu.getState();
		r2.type = pbu.getType();
		r2.lvl = pbu.getLvl();
		r2.cooldown = pbu.getCooldown_ms();
		return r2;
	}

	// 把数据建筑对象集合List转换成客户端建筑对象集合List
	public static List<NBuild> toSUPBuildList(List<Player_build_obst> blist) {
		List<NBuild> sblist = new ArrayList<NBuild>();
		if (blist == null)
			return sblist;
		for (Player_build_obst item : blist) {
			NBuild supb = toSUPBuid(item);
			if (supb == null)
				continue;
			sblist.add(supb);
		}
		return sblist;
	}

	// 转换成客户端需求的List集合对象
	public static NBuilds toSUPBuilds(List<Player_build_obst> blist,
			NBuilds sups) {
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
