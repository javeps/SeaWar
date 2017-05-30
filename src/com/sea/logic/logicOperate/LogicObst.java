package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.NBuilds;
import gen_b2g.serv.bean.NInt;
import gen_b2g.serv.bean.NInts;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.process.ProObst;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Player_build_obstEntity;
import com.sea.json.originData.TownHallJson;
import com.sea.localEntry.DBBuilding;
import com.sea.logic.logicEntity.LEBuild;
import com.sea.logic.logicEntity.LEGameMap;
import com.sea.logic.logicEntity.LEObst;
import com.sea.logic.session.Session;

//@SuppressWarnings({ "unchecked", "rawtypes" })
public class LogicObst implements Serializable {

	static Log log = LogFactory.getLog(LogicObst.class);

	private static final long serialVersionUID = 1L;

	private static Session getLeWrap(Player p) {
		return LogicPlayer.getLeWrap(p);
	}

	// ====================取得数据
	private static Player_build_obst getObstInMap(Player p, int ocid) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		return ProObst.getBuildByPcidBcid(pcid, ocid);
	}

	private static int getNumInBuildsByGid(Player p, int gid) {
		int r = 0;
		if (p == null)
			return r;
		List<Player_build_obst> list = getList(p);
		if (list == null || list.isEmpty())
			return r;
		for (Player_build_obst item : list) {
			int igid = item.getGid();
			if (gid == igid)
				r++;
		}
		return r;
	}

	private static boolean isCanCreate(Player p, int bgid) {
		boolean r = false;
		if (p == null)
			return r;
		boolean isJump = false;
		switch (bgid) {
		case LEBuild.GID_Wall:
		case LEBuild.GID_Flag1:
		case LEBuild.GID_Flag2:
		case LEBuild.GID_Flag3:
		case LEBuild.GID_Flag4:
		case LEBuild.GID_Flag5:
			isJump = true;
			break;
		default:
			r = true;
			break;
		}
		if (isJump) {
			int htlvl = p.getCurtownlvl();
			int maxNum = TownHallJson.getNumByTHLvlGid(htlvl, bgid);
			int curNum = getNumInBuildsByGid(p, bgid);
			r = maxNum > curNum;
		}
		return r;
	}

	public static Player_build_obst createNew(Player p, int clientId, int gid,
			int pos, long cooldownMs, int lvl) {
		Player_build_obst r2 = null;
		boolean isCanCreate = isCanCreate(p, gid);
		if (!isCanCreate)
			return r2;

		Session le = getLeWrap(p);
		if (le == null)
			return r2;

		LEObst leObst = le.getLeObst();
		LEGameMap leGmMap = le.getLeGMMap();

		if (leGmMap.isHasPost(pos))
			return r2;

		int curMaxId = leObst.GetIdMaxClient();
		if (curMaxId < LEObst.Init_Obst_ClientId) {
			curMaxId = LEObst.Init_Obst_ClientId;
		}
		if (clientId > curMaxId) {
			curMaxId = clientId;
		}
		leObst.setIdMaxClient(curMaxId);
		if (leObst.getIsCanAdd()) {
			boolean isHasGid = LEBuild.isHasBuildGid(gid);
			if (isHasGid) {
				r2 = Player_build_obstEntity.createNewInsert(p.getPcid(),
						p.getPname(), gid, cooldownMs, lvl, pos, curMaxId);
				p.setMaxobstid(curMaxId);
				String obstes = p.getObstes();
				p.setObstes(obstes + curMaxId + ",");
				PlayerEntity.updatePlayer(p);

				// 添加对象到map中
				leGmMap.addObst(r2);
			}
		}
		return r2;
	}

	public static boolean upgateObstWall(Player p, NInts ocids) {
		boolean r2 = false;
		if (ocids == null || ocids.intes == null)
			return r2;
		Player_build_obst p_b = null;
		NInt one = ocids.intes.get(0);
		p_b = getObstInMap(p, one.val);

		// 判断是否可以升级
		int gid = p_b.getGid();
		int curLvl = p_b.getLvl();
		int htLvl = p.getCurtownlvl();
		boolean isCanUpgrate = DBBuilding.isCanUpGrade(gid, curLvl, htLvl, 0,
				false);
		if (!isCanUpgrate)
			return r2;

		for (NInt item : ocids.intes) {
			int ocid = item.val;
			p_b = getObstInMap(p, ocid);
			if (p_b == null)
				continue;

			int lvl = p_b.getLvl();
			lvl++;
			p_b.setLvl(lvl);
			Player_build_obstEntity.updateCur(p_b);
		}
		r2 = true;
		return r2;
	}

	public static boolean finishObst(Player p, int clientId, boolean isCostC) {
		boolean r2 = false;
		Player_build_obst p_b = getObstInMap(p, clientId);
		if (p_b == null)
			return r2;

		long now_time = System.currentTimeMillis();
		long p_b_time = p_b.getCooldown_ms();
		if (isCostC || now_time >= p_b_time) {
			p_b.setCooldown_ms(0);
			int lvl_ = p_b.getLvl();
			lvl_++;
			p_b.setLvl(lvl_);
			r2 = true;
		}
		if (r2) {
			Player_build_obstEntity.updateCur(p_b);
		}
		return r2;
	}

	public static boolean moveObst(Player p, int ocid, int pos) {
		boolean r2 = false;

		Session le = getLeWrap(p);
		if (le == null)
			return r2;

		LEGameMap leGmMap = le.getLeGMMap();

		boolean isHasPos = leGmMap.isHasPost(pos);
		if (isHasPos)
			return r2;

		Player_build_obst p_b = getObstInMap(p, ocid);
		if (p_b == null)
			return r2;

		leGmMap.resetPost(pos, ocid, LEGameMap.Type_Map_Obst);

		p_b.setPosition_index(pos);
		r2 = true;
		Player_build_obstEntity.updateCur(p_b);
		return r2;
	}

	private static void removeObstInPlayer(Player p, int bcid) {
		if (p == null)
			return;
		String obstes = p.getObstes();
		obstes = obstes.replaceAll(bcid + ",", "");
		p.setObstes(obstes);
		PlayerEntity.updatePlayer(p);
	}

	public static boolean removeObst(Player p, int bcid, long cooldown) {
		boolean r2 = false;
		Player_build_obst p_b = getObstInMap(p, bcid);
		if (p_b == null)
			return r2;

		if (cooldown <= 0) {
			removeObstInPlayer(p, bcid);
			Player_build_obstEntity.deleteCur(p_b);
		} else {
			long now_time = System.currentTimeMillis();
			cooldown += now_time;
			p_b.setCooldown_ms(cooldown);
			Player_build_obstEntity.updateCur(p_b);
		}
		r2 = true;
		return r2;
	}

	public static boolean finishRemoveObst(Player p, int ocid, boolean isCostC) {
		boolean r2 = false;
		Player_build_obst p_b = getObstInMap(p, ocid);
		if (p_b == null)
			return r2;
		long now_time = System.currentTimeMillis();
		long p_b_time = p_b.getCooldown_ms();
		if (isCostC || now_time >= p_b_time) {
			removeObstInPlayer(p, ocid);
			Player_build_obstEntity.deleteCur(p_b);
			r2 = true;
		}
		return r2;
	}

	public static List<Player_build_obst> getList(Player p) {
		if (p == null)
			return null;
		String obstes = p.getObstes();
		List<Player_build_obst> r2 = Player_build_obstEntity.getList(p);
		if (r2 == null || r2.isEmpty())
			return null;

		if (r2 != null) {
			Session le = getLeWrap(p);
			if (le != null) {
				LEGameMap leGmMap = le.getLeGMMap();
				leGmMap.initObsts(r2);
			}
		}

		StringBuffer buffer = new StringBuffer();
		for (Player_build_obst item : r2) {
			buffer.append(item.getBcid()).append(",");
			int status_ = item.getState();
			if (status_ == LEBuild.Status_Destroy) {
				item.setState(LEBuild.Status_Default);
				Player_build_obstEntity.updateCur(item);
			}
		}
		String strObst = buffer.toString();
		if (obstes == null || "".equals(obstes.trim())
				|| !strObst.equals(obstes.trim())) {
			p.setObstes(strObst);
			PlayerEntity.updatePlayer(p);
		}
		return r2;
	}

	public static void getNBuilds(Player p, NBuilds nbuilds) {
		List<Player_build_obst> origin = getList(p);
		Player_build_obstEntity.toSUPBuilds(origin, nbuilds);
	}
}
