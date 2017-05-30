package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.NBuilds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.sea.cache.process.ProBuild;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_buildings;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Player_buildingsEntity;
import com.sea.db.entity.Player_techEntity;
import com.sea.json.originData.TownHallJson;
import com.sea.localEntry.DBBuilding;
import com.sea.logic.logicEntity.LEBuild;
import com.sea.logic.logicEntity.LEGameMap;
import com.sea.logic.session.LogicalEvent;
import com.sea.logic.session.LogicalVerifyGame;
import com.sea.logic.session.Session;

//@SuppressWarnings({ "unchecked", "rawtypes" })
public class LogicBuild implements Serializable {

	static Log log = LogFactory.getLog(LogicBuild.class);

	private static final long serialVersionUID = 1L;

	private static List<Player_buildings> sortByBeginArmyTime(
			List<Player_buildings> r2) {
		if (r2 == null)
			return r2;
		Comparator<Player_buildings> com_p_b = new Comparator<Player_buildings>() {

			@Override
			public int compare(Player_buildings o1, Player_buildings o2) {
				long bat1 = o1.getBegin_army_time();
				long bat2 = o2.getBegin_army_time();
				if (bat1 > bat2)
					return 1;
				else if (bat1 < bat2)
					return -1;
				return 0;
			}
		};
		Collections.sort(r2, com_p_b);
		return r2;
	}

	// ===============Data
	private static boolean finishUpTechInData(Player p, Player_buildings p_b,
			int gems) {
		boolean r2 = false;
		if (p == null)
			return r2;
		if (p_b == null)
			return r2;

		int up_tech_gid = p_b.getCur_up_tech_gid();
		if (up_tech_gid <= 0)
			return r2;

		boolean isCanChange = gems > 0;
		long nowTime = System.currentTimeMillis();
		long end_up_time = p_b.getEnd_tech_up_time();

		if (isCanChange) {
			isCanChange = LogicalVerifyGame.isEnoughTimeGems(end_up_time,
					nowTime, gems);
		} else {
			isCanChange = end_up_time <= nowTime;
		}

		if (isCanChange) {
			p_b.setEnd_tech_up_time(0);
			p_b.setCur_up_tech_gid(0);
			Player_buildingsEntity.updateBuild(p_b);
			Player_techEntity.finisTech(p, up_tech_gid);
			r2 = true;
		}
		return r2;
	}

	public static List<Player_buildings> getListInData(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String builds = p.getBuilds();
		List<Player_buildings> r2 = Player_buildingsEntity.getListInData(pcid,
				builds);
		if (r2 == null)
			return null;

		StringBuffer buffer = new StringBuffer();

		for (Player_buildings item : r2) {
			int type_ = item.getType();
			int status_ = item.getState();
			switch (status_) {
			case LEBuild.Status_Destroy:
				if (type_ != LEBuild.TYPE_BUILDING_TRAP) {
					item.setState(LEBuild.Status_Default);
					Player_buildingsEntity.updateBuild(item);
				}
				break;
			default:
				break;
			}

			int upTechGid = item.getCur_up_tech_gid();
			// 建筑科技
			if (upTechGid > 0) {
				finishUpTechInData(p, item, 0);
			}
			buffer.append(item.getBcid()).append(",");
		}
		String strBuilds = buffer.toString();
		if (builds == null || "".equals(builds)
				|| !strBuilds.equals(builds.trim())) {
			p.setBuilds(strBuilds);
			PlayerEntity.updatePlayer(p);
		}

		return r2;
	}

	// ===============缓存map

	private static Session getLeWrap(Player p) {
		return LogicPlayer.getLeWrap(p);
	}

	// ====================取得数据(缓存中的数据)
	public static Player_buildings getBuildInMap(Player p, int bcid) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		return ProBuild.getBuildByPcidBcid(pcid, bcid);
	}

	public static List<Player_buildings> getListArsenalInMap(Player p) {
		List<Player_buildings> origin = getList(p);
		List<Player_buildings> r = null;
		if (origin == null || origin.isEmpty())
			return r;

		r = new ArrayList<Player_buildings>();
		for (Player_buildings item : origin) {
			boolean isTrue = isTrueArsenal(item);
			if (isTrue)
				r.add(item);
		}
		sortByBeginArmyTime(r);
		return r;
	}

	static public int getNumInBuildsByGid(Player p, int gid) {
		int r = 0;
		if (p == null)
			return r;
		List<Player_buildings> list = getList(p);
		if (list == null || list.isEmpty())
			return r;
		for (Player_buildings item : list) {
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
		int htlvl = p.getCurtownlvl();
		int maxNum = TownHallJson.getNumByTHLvlGid(htlvl, bgid);
		int curNum = getNumInBuildsByGid(p, bgid);
		r = maxNum > curNum;
		return r;
	}

	public static Player_buildings createNewBuild(Player p, int clientId,
			int gid, int pos, long cooldownMs, int lvl) {
		Player_buildings r2 = null;
		boolean isCanCreate = isCanCreate(p, gid);
		if (!isCanCreate)
			return r2;

		Session le = getLeWrap(p);
		if (le == null)
			return r2;

		LEBuild leBuild = le.getLeBuild();
		LEGameMap leGmMap = le.getLeGMMap();

		if (leGmMap.isHasPost(pos))
			return r2;

		int curMaxId = leBuild.GetIdMaxClient();
		if (curMaxId < LEBuild.Init_Build_ClientId) {
			curMaxId = LEBuild.Init_Build_ClientId;
		}
		if (clientId > curMaxId) {
			curMaxId = clientId;
		}
		leBuild.setIdMaxClient(curMaxId);
		if (leBuild.getIsCanAdd()) {
			boolean isHasGid = LEBuild.isHasBuildGid(gid);
			if (isHasGid) {
				r2 = Player_buildingsEntity.createNewBuildInsert(p.getPcid(),
						p.getPname(), gid, cooldownMs, lvl, pos, curMaxId);
				p.setMaxbuidid(curMaxId);
				String build = p.getBuilds();
				p.setBuilds(build + curMaxId + ",");
				PlayerEntity.updatePlayer(p);

				leGmMap.addBuild(r2);
				if (cooldownMs > 0)
					LogicPlayer.changeWorkerCurNum(p, 1);
			}
		}
		return r2;
	}

	public static boolean upgateBuild(Player p, int bcid, long cooldownMs) {
		boolean r2 = false;
		Player_buildings p_b = getBuildInMap(p, bcid);
		if (p_b == null)
			return r2;
		// 判断是否可以升级
		int curLvl = p_b.getLvl();
		if (cooldownMs > 0) {
			long now_time = System.currentTimeMillis();
			cooldownMs += now_time;
			p_b.setCooldown_ms(cooldownMs);

			LogicPlayer.changeWorkerCurNum(p, 1);
		} else {
			curLvl++;
			p_b.setLvl(curLvl);
		}
		Player_buildingsEntity.updateBuild(p_b);
		r2 = true;
		return r2;
	}

	public static boolean finishBuild(Player p, int bcid, int gems) {
		boolean r2 = false;
		Player_buildings p_b = getBuildInMap(p, bcid);
		if (p_b == null)
			return r2;
		boolean isCostC = gems > 0;
		long now_time = System.currentTimeMillis();
		long p_b_time = p_b.getCooldown_ms();

		if (isCostC) {
			boolean isEvent = false;
			if (p_b.getLvl() == 0) {
				isEvent = LogicalEvent.isActiveFinishBuild(p);
			}
			
			if (!isEvent) {
				isCostC = LogicalVerifyGame.isEnoughTimeGems(p_b_time,
						now_time, gems);
			}
		}

		if (isCostC || now_time >= p_b_time) {
			p_b.setCooldown_ms(0);
			int lvl_ = p_b.getLvl();
			int gid = p_b.getGid();
			lvl_++;
			p_b.setLvl(lvl_);
			switch (gid) {
			case LEBuild.GID_GoldMine:
			case LEBuild.GID_OilWell:
				p_b.setRes_produce_begin_time(now_time);
				break;
			case LEBuild.GID_Headquarters:
				LogicPlayer.changeTownLvl(p, lvl_);
				break;
			default:
				break;
			}
			r2 = true;
		}

		if (r2) {
			LogicPlayer.changeWorkerCurNum(p, -1);
			Player_buildingsEntity.updateBuild(p_b);
		}
		return r2;
	}

	public static void resetInfoByBuildWhenLogin(Player p) {
		if (p == null)
			return;
		List<Player_buildings> list = getList(p);
		if (list == null || list.isEmpty())
			return;
		int sumCun = 0;
		long now_time = System.currentTimeMillis();
		for (Player_buildings item : list) {
			boolean isTH = isTrueTownHall(item);
			if (isTH) {
				int lvl = item.getLvl();
				int phtLvl = p.getCurtownlvl();
				if (lvl != phtLvl) {
					p.setCurtownlvl(lvl);
				}
			}

			long cooldown = item.getCooldown_ms();
			if (cooldown > now_time) {
				sumCun++;
			}
		}
		int cur = p.getCurbonum();
		if (sumCun != cur) {
			p.setCurbonum(sumCun);
		}

		PlayerEntity.updatePlayer(p);
	}

	public static boolean moveBuild(Player p, int bcid, int pos) {
		boolean r2 = false;
		Session le = getLeWrap(p);
		if (le == null)
			return r2;

		LEGameMap leGmMap = le.getLeGMMap();

		boolean isHasPos = leGmMap.isHasPost(pos);
		if (isHasPos)
			return r2;

		Player_buildings p_b = getBuildInMap(p, bcid);
		if (p_b == null)
			return r2;

		leGmMap.resetPost(pos, bcid, LEGameMap.Type_Map_Build);

		p_b.setPosition_index(pos);
		Player_buildingsEntity.updateBuild(p_b);
		r2 = true;
		return r2;
	}

	private static void removeBuildInPlayer(Player p, int bcid) {
		if (p == null)
			return;
		String build = p.getBuilds();
		build = build.replaceAll(bcid + ",", "");
		p.setBuilds(build);
		PlayerEntity.updatePlayer(p);
	}

	public static boolean removeBuild(Player p, int bcid, long cooldown) {
		boolean r2 = false;
		Player_buildings p_b = getBuildInMap(p, bcid);
		if (p_b == null)
			return r2;

		if (cooldown <= 0) {
			removeBuildInPlayer(p, bcid);
			Player_buildingsEntity.deleteBuild(p_b);
		} else {
			long now_time = System.currentTimeMillis();
			cooldown += now_time;
			p_b.setCooldown_ms(cooldown);
			Player_buildingsEntity.updateBuild(p_b);
		}

		r2 = true;
		return r2;
	}

	public static boolean finishRemoveBuild(Player p, int bcid, boolean isCostC) {
		boolean r2 = false;
		Player_buildings p_b = getBuildInMap(p, bcid);
		if (p_b == null)
			return r2;

		long now_time = System.currentTimeMillis();
		long p_b_time = p_b.getCooldown_ms();
		if (isCostC || now_time >= p_b_time) {
			removeBuildInPlayer(p, bcid);
			Player_buildingsEntity.deleteBuild(p_b);
			r2 = true;
		}
		return r2;
	}

	// ================取得总的

	public static List<Player_buildings> getList(Player p) {
		if (p == null)
			return null;
		List<Player_buildings> r = getListInData(p);
		if (r != null) {
			Session le = getLeWrap(p);
			if (le != null) {
				LEGameMap leGmMap = le.getLeGMMap();
				leGmMap.initBuilds(r);
			}
		}

		if (r == null || r.isEmpty())
			return r;

		int guid_step = p.getGuid_step();
		if (guid_step >= 100)
			return r;

		List<Player_buildings> r2 = new ArrayList<Player_buildings>();
		A: for (Player_buildings item : r) {
			switch (item.getGid()) {
			case LEBuild.GID_Cannon:
			case LEBuild.GID_Arsenal:
			case LEBuild.GID_OilWell:
			case LEBuild.GID_GoldMine:
			case LEBuild.GID_GoldStorage:
			case LEBuild.GID_OilStorage:
				continue A;
			default:
				break;
			}
			r2.add(item);
		}
		return r2;
	}

	public static void getNBuildsInAll(Player p, NBuilds nbuilds) {
		List<Player_buildings> list_ = getList(p);
		Player_buildingsEntity.toSUPBuilds(list_, nbuilds);
	}

	public static void getNBuildsInData(Player p, NBuilds nbuilds) {
		List<Player_buildings> origin = getListInData(p);
		Player_buildingsEntity.toSUPBuilds(origin, nbuilds);
	}

	// ==========建筑的其他操作

	// 判断玩家的某个建筑类型是否正确
	private static boolean isType(Player_buildings p_b, int gid) {
		boolean r2 = false;
		if (p_b == null)
			return r2;
		int pgid = p_b.getGid();

		r2 = pgid == gid;

		return r2;
	}

	static boolean isTypeInMap(Player p, int bcid, int gid) {
		Player_buildings p_b = getBuildInMap(p, bcid);
		return isType(p_b, gid);
	}

	// 判读否是 兵工厂
	public static boolean isTrueArsenalInMap(Player p, int bcid) {
		Player_buildings p_b = getBuildInMap(p, bcid);
		return isTrueArsenal(p_b);
	}

	public static boolean isTrueArsenal(Player_buildings p_b) {
		int gid = LEBuild.GID_Arsenal;
		return isType(p_b, gid);
	}

	// 是否是资源建筑
	public static boolean isTrueResInMap(Player p, int bcid) {
		Player_buildings p_b = getBuildInMap(p, bcid);
		return isTrueRes(p_b);
	}

	public static boolean isTrueRes(Player_buildings p_b) {
		boolean r2 = false;
		int ggid = LEBuild.GID_GoldMine;
		int ogid = LEBuild.GID_OilWell;
		boolean is_goldmine = isType(p_b, ggid);
		boolean is_oilwell = isType(p_b, ogid);
		r2 = is_goldmine || is_oilwell;
		return r2;
	}

	// 判读否是 兵工厂
	public static boolean isTrueTechInMap(Player p, int bcid) {
		Player_buildings p_b = getBuildInMap(p, bcid);
		return isTrueTech(p_b);
	}

	public static boolean isTrueTech(Player_buildings p_b) {
		int gid = LEBuild.GID_TechnologicalCenter;
		return isType(p_b, gid);
	}

	// 判读否是 城镇中心
	public static boolean isTrueTownHallInMap(Player p, int bcid) {
		Player_buildings p_b = getBuildInMap(p, bcid);
		return isTrueTownHall(p_b);
	}

	public static boolean isTrueTownHall(Player_buildings p_b) {
		int gid = LEBuild.GID_Headquarters;
		return isType(p_b, gid);
	}

	public static boolean isHasClanBuild(Player p) {
		boolean r = false;
		if (p == null)
			return r;
		List<Player_buildings> list = getList(p);
		if (list == null || list.isEmpty())
			return r;
		int clanGid = LEBuild.GID_Alliance;
		for (Player_buildings item : list) {
			int gid = item.getGid();
			if (clanGid == gid) {
				r = true;
				break;
			}
		}
		return r;
	}

	// 改变造兵开始时间
	public static boolean changeProductArmyBTime(Player p, int bcid,
			boolean isFinished) {
		boolean r2 = false;
		Player_buildings p_b = getBuildInMap(p, bcid);
		if (p_b == null)
			return r2;

		boolean isArs = isTrueArsenal(p_b);
		if (!isArs)
			return r2;

		long begin_army_time = 0;

		boolean isHasProduct = LogicArmy.isProducting(p, bcid);

		if (isFinished) {
			if (isHasProduct) {
				begin_army_time = System.currentTimeMillis();
			}
		} else {
			if (isHasProduct) {
				return r2;
			} else {
				begin_army_time = System.currentTimeMillis();
			}
		}
		p_b.setBegin_army_time(begin_army_time);
		Player_buildingsEntity.updateBuild(p_b);
		r2 = true;
		return r2;
	}

	// 修复陷阱
	public static boolean repairTrapBuildInMap(Player p, int bcid) {
		boolean r2 = false;
		Player_buildings p_b = getBuildInMap(p, bcid);
		if (p_b == null)
			return r2;
		if (p_b.type != LEBuild.TYPE_BUILDING_TRAP)
			return r2;
		p_b.setState(LEBuild.Status_Default);
		Player_buildingsEntity.updateBuild(p_b);
		r2 = true;
		return r2;
	}

	// 升级科技
	public static boolean upgateTech(Player p, int bcid, int tgid, int upTime) {
		boolean r2 = false;
		if (p == null)
			return r2;

		int year_min = 365 * 24 * 60;
		if (upTime <= 0 || upTime > year_min)
			return r2;

		Player_buildings p_b = getBuildInMap(p, bcid);
		if (p_b == null)
			return r2;

		boolean isTech = isTrueTech(p_b);
		if (!isTech)
			return r2;

		boolean is_has_tech_gid = Player_techEntity.isHasTechGid(tgid);
		if (!is_has_tech_gid)
			return r2;

		int curUpTGid = p_b.getCur_up_tech_gid();

		if (curUpTGid > 0)
			return r2;

		long nowTime = System.currentTimeMillis();
		nowTime += upTime * DateEx.TIME_MINUTE;
		p_b.setEnd_tech_up_time(nowTime);
		p_b.setCur_up_tech_gid(tgid);

		Player_buildingsEntity.updateBuild(p_b);
		Player_techEntity.upgradeTech(p, tgid);
		r2 = true;
		return r2;
	}

	// 完成升级科技

	public static boolean finishUpTech(Player p, int bcid, int gems) {
		boolean r2 = false;
		Player_buildings p_b = getBuildInMap(p, bcid);
		if (p_b == null)
			return r2;

		boolean isTech = isTrueTech(p_b);
		if (!isTech)
			return r2;

		r2 = finishUpTechInData(p, p_b, gems);
		return r2;
	}

	// === 取得一个 兵营

	public static Player_buildings getBuildArsenalInData(Player p, int bcid) {
		Player_buildings r = null;
		if (p == null)
			return r;
		r = Player_buildingsEntity.getBuildByPlayer(p, bcid);
		return r;
	}

	// 降级
	public static void downBuildLvl(Player p, int bcid, int downLvl) {
		if (downLvl < 1)
			return;
		Player_buildings p_b = getBuildInMap(p, bcid);
		if (p_b == null)
			return;
		p_b.setLvl(downLvl);
		boolean isHT = isTrueTownHall(p_b);
		if (isHT) {
			LogicPlayer.changeTownLvl(p, downLvl);
		}
		Player_buildingsEntity.updateBuild(p_b);
	}

	// 收取资源
	public static boolean harvestRes(Player p, int bcid, String resType,
			int harVal) {
		boolean isTrueBuild = isTrueResInMap(p, bcid);
		if (isTrueBuild) {
			Player_buildings pb = getBuildInMap(p, bcid);
			int lvl = pb.getLvl();
			long wait_time = 30 * DateEx.TIME_SECOND * lvl;
			long now_time = System.currentTimeMillis();
			long cur_time = pb.getRes_produce_begin_time();
			long max_time = cur_time + wait_time;
			boolean isCan = now_time > max_time;
			if (!isCan) {
				isCan = harVal < 100;
			}
			if (isCan) {
				DBBuilding data = LogicalVerifyGame
						.getDBBuild(pb.getGid(), lvl);
				if (data == null)
					return false;

				if (!data.ProducesRscType.equals(resType))
					return false;

				long pre = pb.getRes_produce_begin_time();
				long diff = now_time - pre;
				pb.setRes_produce_begin_time(now_time);
				Player_buildingsEntity.updateBuild(pb);

				// 参量
				double product = (double) data.ResourcePerHour * diff
						/ DateEx.TIME_HOUR;
				product = product > data.ResourceMax ? data.ResourceMax
						: product;

				double dp = Math.abs(product - harVal);
				harVal = dp > 5000 ? (int) product : harVal;
				LogicPlayer.addPlayerRes(p, resType, harVal);
				return true;
			}
		}
		return false;
	}
}
