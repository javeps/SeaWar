package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NArmys;
import gen_b2g.serv.bean.NAttackInfo;
import gen_b2g.serv.bean.NAttackInfos;
import gen_b2g.serv.bean.NBuildBlast;
import gen_b2g.serv.bean.NBuilds;
import gen_b2g.serv.bean.NClan;
import gen_b2g.serv.bean.NEnergy;
import gen_b2g.serv.bean.NHeros;
import gen_b2g.serv.bean.NInt;
import gen_b2g.serv.bean.NPlayer;
import gen_b2g.serv.bean.NTeches;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.bowlong.util.Ref;
import com.sea.cache.process.ProAmry;
import com.sea.cache.process.ProAssistFight;
import com.sea.db.bean.Attack_defense_info;
import com.sea.db.bean.Attack_mail;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_hero;
import com.sea.db.bean.Player_tech;
import com.sea.db.entity.Attack_defense_infoEntity;
import com.sea.db.entity.Attack_mailEntity;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Player_armysEntity;
import com.sea.db.entity.Player_buildingsEntity;
import com.sea.db.entity.Player_heroEntity;
import com.sea.db.entity.Player_techEntity;
import com.sea.json.originData.CfgJson;
import com.sea.json.originData.ClanJson;
import com.sea.logic.logicEntity.LEAttack;
import com.sea.logic.logicEntity.LEBuild;
import com.sea.logic.session.LogicalSession;
import com.sea.logic.session.Session;
import com.sea.tools.FormulaWar;

/**
 * 战斗辅助
 * 
 * @author Administrator
 * 
 */
public class LogicAttack {

	static Log log = LogFactory.getLog(LogicAttack.class);

	static long Protect_Time = 3 * 60 * DateEx.TIME_MINUTE;
	static long Protect_Time_BeFind = 5 * DateEx.TIME_MINUTE;

	// =============== 攻击NPC副本 ======================
	public static void updatePVEAttact(Player p, NAttackInfos armyInfos) {
		reduceArmyOnAttacking(true, p, null, armyInfos);
	}

	// ================= 查找被攻击玩家 =================

	public static Player findBeAttPlayer(Player p) {
		Player r2 = null;
		if (p == null)
			return r2;
		int week = p.getWeekrenown();
		int all = p.getRenown();

		int townLvl = p.getCurtownlvl();
		int pcid = p.getPcid();

		// r2 = PlayerEntity.getPlayer(1487);
		// r2 = PlayerEntity.getPlayer(2440);

		r2 = PlayerEntity.getBeFightPlayer(pcid, townLvl, week, all);
		if (r2 != null) {
			LogicPlayer.upPTime(r2, Protect_Time_BeFind);
			ProAssistFight.setFightTempVal(p, r2);
		}
		return r2;
	}

	// 转换被攻击玩家信息
	public static void toBeAttNBuilds(Player beAtt, NBuilds nbuilds) {
		LogicBuild.getNBuildsInData(beAtt, nbuilds);
	}

	public static void toBeAttNBuildsByObst(Player beAtt, NBuilds nbuilds) {
		LogicObst.getNBuilds(beAtt, nbuilds);
	}

	public static void toBeAttNArmys(Player beAtt, NArmys supas) {
		LogicArmy.getNArmysInData(beAtt, supas);
	}

	public static void toBeAttNTeches(Player beAtt, NTeches supas) {
		List<Player_tech> list = Player_techEntity.getPlayerTechList(beAtt);
		Player_techEntity.toSUPTeches(list, supas);
	}

	public static void toBeAttNHeros(Player beAtt, NHeros ns) {
		LogicHero.getNHeros(beAtt, ns);
	}

	public static void toFightNClan(Player p, NClan nclan) {
		LogicClan.getNClan(p, nclan);
	}

	// 战斗回放 转换被攻击方信息
	public static void toDefenseInfo(Attack_mail attMial, NBuilds nbuilds,
			NArmys narmys, NTeches nteches, NHeros defHeros, NHeros offHeros,
			NClan offNclan, NClan defNclan) {
		Attack_defense_info defeseInfo = Attack_defense_infoEntity
				.getDefenseInfo(attMial);
		if (defeseInfo == null)
			return;
		List<Player_buildings> lb = Attack_defense_infoEntity
				.toListDefenseBuildInfo(defeseInfo.getBuilds());
		List<Player_armys> la = Attack_defense_infoEntity
				.toListDefenseArmyInfo(defeseInfo.getAmrys());
		List<Player_tech> lt = Attack_defense_infoEntity
				.toListDefenseTechesInfo(defeseInfo.getTeches());
		List<Player_buildings> lobs = Attack_defense_infoEntity
				.toListDefenseObstInfo(defeseInfo.getObsts());
		List<Player_hero> lherosDef = LogicHero.getListHeroByStr(defeseInfo
				.getHeros());

		List<Player_hero> lherosOff = LogicHero.getListHeroByStr(attMial
				.getAttheros());

		Player_buildingsEntity.toSUPBuilds(lb, nbuilds);
		Player_buildingsEntity.toSUPBuilds(lobs, nbuilds);

		Player_armysEntity.toSUPArmys(la, narmys);
		Player_techEntity.toSUPTeches(lt, nteches);
		Player_heroEntity.getNHeros(lherosDef, defHeros);
		Player_heroEntity.getNHeros(lherosOff, offHeros);

		// 转换联盟信息
		String attCcid = attMial.getClancid();
		String beCcid = attMial.getDefccid();

		boolean isHasClan = attCcid != null && !"0".equals(attCcid.trim())
				&& !"".equals(attCcid.trim());
		boolean isBeHasClan = beCcid != null && !"0".equals(beCcid.trim())
				&& !"".equals(beCcid.trim());
		int eachRateAtt = 0;
		int eachRateHP = 0;
		if (isBeHasClan || isHasClan) {
			eachRateAtt = ClanJson.getEachRateAtt();
			eachRateHP = ClanJson.getEachRateHP();
		}

		if (isHasClan && offNclan != null) {
			offNclan.ccid = attCcid;
			offNclan.cname = attMial.getCname();
			offNclan.eachAtt = eachRateAtt;
			offNclan.eachHP = eachRateHP;
			offNclan.icon = attMial.getCicon();
			offNclan.pointAtt = attMial.getOffenatt();
			offNclan.pointHP = attMial.getOffenhp();
		}

		if (isBeHasClan && defNclan != null) {
			defNclan.ccid = beCcid;
			defNclan.cname = attMial.getDefcname();
			defNclan.eachAtt = eachRateAtt;
			defNclan.eachHP = eachRateHP;
			defNclan.icon = attMial.getDefcicon();
			defNclan.pointAtt = attMial.getDefenseatt();
			defNclan.pointHP = attMial.getDefensehp();
		}

		// offNclan.eachAtt =
	}

	// =============== 攻击玩家 ======================
	private static String getAttMcid(int attPcid, int cid) {
		StringBuffer sb = new StringBuffer();
		sb.append("pcid").append(attPcid).append("_mfcid").append(cid);
		String r = sb.toString();
		return r;
	}

	private static LEAttack getLeAtt(Player att) {
		if (att == null)
			return null;
		int pcid = att.getPcid();
		Session le = LogicalSession.getSessionByPcid(pcid);
		if (le == null)
			return null;
		return le.getLeAttact();
	}

	private static Attack_mail getLeAttMail(Player att) {
		LEAttack le_ = getLeAtt(att);
		if (le_ == null)
			return null;
		return le_.getCurAttMail();
	}

	private static Attack_mail createNewAttMail(Player att, int clientId,
			boolean isHitBack, int egid, int num) {
		Attack_mail r2 = null;
		LEAttack le_ = getLeAtt(att);
		if (le_ == null)
			return r2;

		int curMaxId = att.getMaxattmcid();
		if (curMaxId < LEAttack.Init_AttMail_ClientId) {
			curMaxId = LEAttack.Init_AttMail_ClientId;
		}

		while (clientId <= curMaxId) {
			clientId++;
		}

		if (clientId > curMaxId) {
			curMaxId = clientId;
		}
		le_.setIdMaxClientAtt(curMaxId);
		if (le_.getIsCanAdd()) {
			int attpcid = att.getPcid();
			Player beAtt = ProAssistFight.getBePlayer(attpcid);
			String attCid = getAttMcid(attpcid, curMaxId);
			r2 = Attack_mailEntity.createNewInsert(attCid, att, beAtt,
					isHitBack, egid, num);
			if (r2 != null) {
				LogicPlayer.changeMaxAttCID(att, curMaxId);
				le_.setCurAttMail(r2);
			} else {
				log.info("att == " + (att == null) + " || beAtt == "
						+ (att == null));
			}
		} else {
			log.info("mcid = " + clientId + ",lemcid = "
					+ le_.GetIdMaxClientAtt() + ",curMax = " + curMaxId);
		}
		return r2;
	}

	// 记录被攻击玩家的信息
	private static void recordBeAttPlayerInfo(Attack_mail attMail,
			Player beAtt, NHeros beHeros) {
		if (attMail == null || beAtt == null || LogicPlayer.isNpc(beAtt))
			return;
		String matcid = attMail.getAttcid();
		if (matcid == null || "".equals(matcid.trim()))
			return;

		Attack_defense_info defeseInfo = Attack_defense_infoEntity
				.getDefenseInfo(attMail);

		if (defeseInfo != null)
			return;

		List<Player_buildings> lb = LogicBuild.getListInData(beAtt);
		String strBuild = Attack_defense_infoEntity.toStrDefenseBuildInfo(lb);

		List<Player_armys> la = LogicArmy.getListOwnArmyInData(beAtt);
		String strArmy = Attack_defense_infoEntity.toStrDefenseArmyInfo(la);

		List<Player_tech> lt = Player_techEntity.getPlayerTechList(beAtt);
		String strTech = Attack_defense_infoEntity.toStrDefenseTechesInfo(lt);

		List<Player_build_obst> lo = LogicObst.getList(beAtt);
		String strObst = Attack_defense_infoEntity.toStrDefenseObstInfo(lo);

		String strHero = LogicHero.getStrHeros(beAtt, beHeros);

		Attack_defense_infoEntity.createNewInsert(attMail.getAttcid(),
				strBuild, strObst, strArmy, strTech, strHero);
	}

	// 战斗开始
	public static boolean beginAttact(Player att, int attClientId,
			boolean isHitBack, NHeros beHeros, NEnergy energy) {
		boolean r2 = false;
		if (att == null)
			return r2;
		int attpcid = att.getPcid();
		Player beAtt = ProAssistFight.getBePlayer(attpcid);

		getEnergyByFight(att, beAtt, energy);
		int egid = 0;
		int num = 0;
		if (energy != null && energy.num > 0) {
			egid = energy.egid;
			num = energy.num;
		}
		Attack_mail mail_ = createNewAttMail(att, attClientId, isHitBack, egid,
				num);
		r2 = mail_ != null;
		if (r2) {
			// 记录被攻击者信息
			recordBeAttPlayerInfo(mail_, beAtt, beHeros);
		}
		return r2;
	}

	// 更新记录放兵，减少双方兵
	public static boolean updateAttact(Player att, int attClientId, int star,
			int renown, NAttackInfos attInfos, int beRenown,
			NAttackInfos offenDeaths, NAttackInfos defenseDeaths) {
		boolean r2 = false;
		Attack_mail mail_ = getLeAttMail(att);
		r2 = updataAttMail(mail_, star, renown, 0, 0, attInfos, beRenown, false);
		if (r2) {
			Player beAtt = ProAssistFight.getBePlayer(att);
			// 减少攻击方兵
			reduceArmyOnAttacking(true, att, beAtt, attInfos);
			// 减少防御方兵
			reduceArmyOnAttacking(false, att, beAtt, defenseDeaths);
		}
		return r2;
	}

	// 战斗结束
	public static boolean endAttact(Player att, int attClientId, int star,
			int renown, int defenceRenown, int attGold, int attOil,
			int maxGold, int maxOil, NAttackInfos attInfos,
			NBuildBlast bastBuilds, NAttackInfos offenDeaths,
			NAttackInfos defenseDeaths) {
		boolean r2 = false;
		Attack_mail mail_ = getLeAttMail(att);
		if (mail_ == null) {
			ProAssistFight.removeKey(att);
			return r2;
		}

		int breakBuildNum = 0;
		if (bastBuilds != null && bastBuilds.bcids != null) {
			breakBuildNum = bastBuilds.bcids.size();
		}

		if (breakBuildNum <= 1 && star == 3) {
			Attack_mailEntity.deleteMailFight(mail_);
			ProAssistFight.removeKey(att);
			return r2;
		}

		Player beAtt = ProAssistFight.getBePlayer(att);
		boolean isNpcBeAtt = LogicPlayer.isNpc(beAtt);

		// ========== 加入公式计算分数 begin
		int attRnn = 0;
		if (att != null) {
			attRnn = att.getRenown();
		}
		int beAttRnn = 0;
		if (beAtt != null) {
			beAttRnn = beAtt.getRenown();
		}

		if (attRnn != 0 && beAttRnn != 0) {
			List<Integer> list = FormulaWar.reckonWarGetCups(attRnn, beAttRnn,
					star);
			int offerCup = list.get(0);
			int defenCup = list.get(1);
			boolean isOkey = (renown < offerCup + 3 && renown > offerCup - 3);
			if (!isOkey)
				return false;
			// renown = offerCup;

			isOkey = (defenceRenown < defenCup + 3 && defenceRenown > defenCup - 3);
			if (!isOkey)
				return false;
			// defenceRenown = defenCup;
		}
		// ========== 加入公式计算分数 end

		boolean isWin = (star > 0);
		int winNum = isWin ? 1 : 0;
		int breakHT = 0;
		int breakPJP = 0;

		// ===== 验证抢夺资源
		Ref<Integer> refGold = new Ref<Integer>(0);
		Ref<Integer> refOil = new Ref<Integer>(0);
		getFightResVal(att, beAtt, refGold, refOil);
		attGold = attGold > refGold.val ? refGold.val : attGold;
		attOil = attOil > refOil.val ? refOil.val : attOil;
		// ===== end;

		r2 = updataAttMail(mail_, star, renown, attGold, attOil, attInfos,
				defenceRenown, true);

		if (r2) {
			LogicPlayer.recordFightPInfoForAtt(att, attGold, attOil, maxGold,
					maxOil, renown);
			// 减少攻击方兵
			reduceArmyOnAttacking(true, att, beAtt, attInfos);

			int egid = mail_.getEgid();
			int num = mail_.getNum();
			// 增加碎片
			if (star == 3 && num > 0) {
				LogicPlayer.changeEnergyNum(att, egid, num);
				if (!isNpcBeAtt)
					LogicPlayer.changeEnergyNum(beAtt, egid, -num);
			}

			if (!isNpcBeAtt) {
				// 减少防御方兵
				reduceArmyOnAttacking(false, att, beAtt, defenseDeaths);
				// 减少防御放资源
				LogicPlayer.recordFightPInfo(beAtt, -attGold, -attOil,
						defenceRenown);

				if (isWin) {
					long times_ = Protect_Time * star / 3;
					LogicPlayer.upPTime(beAtt, times_);
				}

				if (breakBuildNum > 0) {
					for (Integer item : bastBuilds.bcids) {
						if (item == null)
							continue;
						Player_buildings p_b = Player_buildingsEntity
								.getBuildByPlayer(beAtt, item);
						if (p_b == null)
							continue;
						p_b.setState(LEBuild.Status_Destroy);
						Player_buildingsEntity.updateBuild(p_b);
					}
				}
			}
		}

		if (breakBuildNum > 0) {
			for (Integer bgid : bastBuilds.bgids) {
				switch (bgid) {
				case LEBuild.GID_Headquarters:
					breakHT = 1;
					break;
				case LEBuild.GID_HeavyGun:
					breakPJP++;
					break;
				default:
					break;
				}
			}
		}
		LogicAchievement.changeAchievements(att, attGold, attOil, renown,
				breakHT, winNum, breakPJP);
		if (isNpcBeAtt) {
			Attack_mailEntity.deleteMailFight(mail_);
		}
		ProAssistFight.removeKey(att);
		return r2;
	}

	// 更新邮件信息
	private static boolean updataAttMail(Attack_mail mailAtt, int star,
			int renown, int attGold, int attOil, NAttackInfos attInfos,
			int beRenown, boolean isEndAtt) {
		boolean r2 = false;
		if (mailAtt == null)
			return r2;
		String attInfo = Attack_mailEntity.toAttMailInfos(mailAtt, attInfos);

		boolean isUped = isEndAtt || star != mailAtt.getStar()
				|| !attInfo.equals(mailAtt.getAttinfos())
				|| renown != mailAtt.getAttrenown()
				|| attGold != mailAtt.getAttgold()
				|| attOil != mailAtt.getAttoil()
				|| beRenown != mailAtt.getBerewon();

		r2 = isUped;

		if (isUped) {
			mailAtt.setAttgold(attGold);
			mailAtt.setAttrenown(renown);
			mailAtt.setAttoil(attOil);
			mailAtt.setStar(star);
			mailAtt.setAttinfos(attInfo);
			mailAtt.setBerewon(beRenown);
			if (isEndAtt) {
				long now_time = System.currentTimeMillis();
				mailAtt.setEnd_time(now_time);
			}
			Attack_mailEntity.updateMailFight(mailAtt);
		}
		return r2;
	}

	private static void reduceArmyOnAttacking(boolean isAttPlayer, Player p,
			Player beAttP, NAttackInfos sua) {
		if (p == null)
			return;
		if (sua == null)
			return;
		Player reduceP = null;
		if (isAttPlayer)
			reduceP = p;
		else
			reduceP = beAttP;

		if (reduceP == null)
			return;

		int pcid = reduceP.getPcid();

		if (pcid == 0)
			return;

		boolean isNpc = LogicPlayer.isNpc(reduceP);
		if (isNpc)
			return;

		Map<String, NAttackInfo> cinfo = new HashMap<String, NAttackInfo>();

		for (NAttackInfo item : sua.infos) {
			int gid = item.gid;
			switch (item.type) {
			case ConstantType.Type_Att_Charater:
				int bcida = item.bcid;
				String key = "Key_" + bcida + "_" + gid;
				NAttackInfo v = cinfo.get(key);
				if (v == null) {
					v = new NAttackInfo();
					v.bcid = bcida;
					v.num = 1;
					v.gid = gid;
					cinfo.put(key, v);
					continue;
				}
				v.num++;
				break;
			case ConstantType.Type_Att_Spell:
				LogicPlayer.reducePlayerSpell(reduceP, gid, item.num);
				break;
			default:
				break;
			}
		}

		for (Entry<String, NAttackInfo> en : cinfo.entrySet()) {
			NAttackInfo v = en.getValue();
			Player_armys pa = ProAmry.getArmy(pcid, v.bcid, v.gid);
			if (pa == null)
				continue;
			int diff = pa.getNum() - v.num;
			if (diff > 0) {
				pa.setNum(diff);
				ProAmry.upEntity(pa);
			} else {
				ProAmry.deleteData(pa);
			}
		}
	}

	// ========= 取得战斗中 可以获得的三种东西，金币，资源，能源片

	static Map<String, Map<String, Integer>> resMap = new HashMap<String, Map<String, Integer>>();

	static private String getResMapKey(int pcidAtt, int pcidBeAtt) {
		StringBuffer buff = new StringBuffer("fight");
		buff.append(pcidAtt).append("_").append(pcidBeAtt);
		return buff.toString();
	}

	static private void getFightResVal(Player att, Player beAtt,
			Ref<Integer> refGold, Ref<Integer> refOil) {
		int pcidAtt = att.getPcid();
		int pcidBeAtt = 0;
		if (beAtt != null) {
			pcidBeAtt = beAtt.getPcid();
		}
		String key = getResMapKey(pcidAtt, pcidBeAtt);
		Map<String, Integer> map = resMap.get(key);
		int gold = 0;
		int oil = 0;
		if (map != null) {
			gold = map.get("g");
			oil = map.get("o");
			resMap.remove(key);
		}
		refGold.val = gold;
		refOil.val = oil;
	}

	public static void getEnergyByFight(Player att, Player beAtt,
			NEnergy backEnergy) {
		LogicEnergy.getEnergyByFight(att, beAtt, backEnergy);
	}

	// ========= 取得用户战斗可以获得的资源
	static private int getValByTHLvl(int lvl, boolean isGold) {
		int result = lvl * 1000;
		if (isGold) {
			result += (int) ((lvl - 1) * 1000 * 0.5);
		} else {
			result += (int) ((lvl - 2) * 1000 * 0.25);
		}
		return result;
	}

	static private void getResByFight(Player beAtt, Player att,
			Ref<Integer> getGold, Ref<Integer> getOil) {
		int gold = 0;
		int oil = 0;
		int lvl = 2;
		int pcidAtt = att.getPcid();
		int pcidBeAtt = 0;
		if (beAtt != null) {
			gold = beAtt.getStored_gold();
			oil = beAtt.getStored_oil();
			lvl = beAtt.getCurtownlvl();
			pcidBeAtt = beAtt.getPcid();
		} else {
			lvl = att.getCurtownlvl();
		}
		double rate = CfgJson.getFightResRate();
		gold = (int) Math.ceil(gold * rate);
		oil = (int) Math.ceil(oil * rate);
		int minGold = getValByTHLvl(lvl, true);
		gold = gold < minGold ? minGold : gold;
		int minOil = getValByTHLvl(lvl, false);
		oil = oil < minOil ? minOil : oil;
		getGold.val = gold;
		getOil.val = oil;

		// 记录数据
		Map<String, Integer> rMap = new HashMap<String, Integer>();
		rMap.put("g", gold);
		rMap.put("o", oil);
		String key = getResMapKey(pcidAtt, pcidBeAtt);
		resMap.put(key, rMap);
	}

	static public void getFightCanGetRes(Player beAtt, Player att,
			NInt canGetGold, NInt canGetOil) {
		Ref<Integer> getGold = new Ref<Integer>(0);
		Ref<Integer> getOil = new Ref<Integer>(0);
		getResByFight(beAtt, att, getGold, getOil);
		canGetGold.val = getGold.val;
		canGetOil.val = getOil.val;
	}

	static public void resetHitBackRes(Player beAtt, Player att, NPlayer nbeAAtt) {
		if (beAtt != null) {
			Ref<Integer> getGold = new Ref<Integer>(0);
			Ref<Integer> getOil = new Ref<Integer>(0);
			getResByFight(beAtt, att, getGold, getOil);

			PlayerEntity.toSUPlayer(beAtt, nbeAAtt);
			nbeAAtt.stored_gold = getGold.val;
			nbeAAtt.stored_oil = getOil.val;
		}
	}
}
