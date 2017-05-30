package com.sea.logic;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NAchievements;
import gen_b2g.serv.bean.NArmys;
import gen_b2g.serv.bean.NAttMails;
import gen_b2g.serv.bean.NAttackInfos;
import gen_b2g.serv.bean.NBuildBlast;
import gen_b2g.serv.bean.NBuilds;
import gen_b2g.serv.bean.NChat;
import gen_b2g.serv.bean.NChats;
import gen_b2g.serv.bean.NClan;
import gen_b2g.serv.bean.NClanMembers;
import gen_b2g.serv.bean.NClanRequests;
import gen_b2g.serv.bean.NClans;
import gen_b2g.serv.bean.NEnergy;
import gen_b2g.serv.bean.NEnergys;
import gen_b2g.serv.bean.NHeros;
import gen_b2g.serv.bean.NInt;
import gen_b2g.serv.bean.NInts;
import gen_b2g.serv.bean.NLong;
import gen_b2g.serv.bean.NMail;
import gen_b2g.serv.bean.NMails;
import gen_b2g.serv.bean.NNPCBeFighteds;
import gen_b2g.serv.bean.NPInfo;
import gen_b2g.serv.bean.NPlayer;
import gen_b2g.serv.bean.NPlayers;
import gen_b2g.serv.bean.NProductes;
import gen_b2g.serv.bean.NRankClans;
import gen_b2g.serv.bean.NRankUsers;
import gen_b2g.serv.bean.NRewards;
import gen_b2g.serv.bean.NSession;
import gen_b2g.serv.bean.NSpells;
import gen_b2g.serv.bean.NStrVal;
import gen_b2g.serv.bean.NTeches;
import gen_b2g.serv.bean.ReturnStatus;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.sea.cache.process.ProAssistFight;
import com.sea.channel.ChannelCfg;
import com.sea.channel.qihu360.OAuth360;
import com.sea.channel.qihu360.PayQiHu360;
import com.sea.db.bean.Attack_mail;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.User;
import com.sea.db.entity.Attack_mailEntity;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Rank_playerEntity;
import com.sea.db.entity.UserEntity;
import com.sea.handler.game.tcpGame.GameTcpAbsRepsonse;
import com.sea.json.npc.NpcImport;
import com.sea.logic.logicOperate.LogicAchievement;
import com.sea.logic.logicOperate.LogicArmy;
import com.sea.logic.logicOperate.LogicAttack;
import com.sea.logic.logicOperate.LogicBuild;
import com.sea.logic.logicOperate.LogicChat;
import com.sea.logic.logicOperate.LogicClan;
import com.sea.logic.logicOperate.LogicEnergy;
import com.sea.logic.logicOperate.LogicHero;
import com.sea.logic.logicOperate.LogicLogin;
import com.sea.logic.logicOperate.LogicMail;
import com.sea.logic.logicOperate.LogicObst;
import com.sea.logic.logicOperate.LogicOrder;
import com.sea.logic.logicOperate.LogicPlayer;
import com.sea.logic.logicOperate.LogicReward;
import com.sea.logic.session.LogicalSession;
import com.sea.logic.session.LogicalVerifyGame;
import com.sea.logic.session.Session;

public class BackToClientLogicHandle extends LogicalVerifyGame {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(BackToClientLogicHandle.class);

	public static boolean isCanIssued(final String methodStr) {
		boolean r2 = true;
		switch (methodStr) {
		case "loginUserByUid":
		case "loginUPlayer":
		case "downLine":
		case "getNChats":
		case "getAttMails":
		case "getNMails":
		case "refreshHeros":
		case "getPInfo":
		case "getAchievements":
		case "getOwnClan":
		case "getOwnClanMember":
		case "getOwnClanRequest":
			r2 = false;
			break;
		default:
			break;
		}
		return r2;
	}

	public static boolean isCanDelayOutTime(final String methodStr) {
		boolean r2 = true;
		switch (methodStr) {
		case "heart":
			r2 = false;
			break;
		default:
			r2 = isCanIssued(methodStr);
			break;
		}
		return r2;
	}

	public static ReturnStatus onLoginUserByUid(TcpChannel chn, String uid,
			String uuid, String pwd, String name, String channel, String model,
			String version, int servid, NPlayers players, ReturnStatus ret)
			throws Exception {
		GameTcpAbsRepsonse absChn = (GameTcpAbsRepsonse) chn;
		User u = null;
		if (channel == null)
			channel = "";

		switch (channel) {
		case ChannelCfg.Cm:
		case ChannelCfg.Ty:
		case ChannelCfg.Wo:
			// case ChannelCfg.He:
			u = UserEntity.getUser(uid, pwd);
			if (u == null) {
				ret = getResultStatus(ret, BackToClientCofig.Error_LgId_LgPwd);
				return ret;
			}
			break;
		default:
			break;
		}

		u = UserEntity.onLoginUser(uid, uuid, pwd, name, channel, model,
				version, servid, players);

		if (u == null) {
			ret = getResultStatus(ret, BackToClientCofig.ERROR);
			return ret;
		}

		absChn.ucid = u.getUcid();
		return ret;
	}

	public static ReturnStatus onLoginUPlayer(TcpChannel chn, int SUPID,
			NSession session, NPlayer player, NNPCBeFighteds npcs,
			NBuilds builds, NTeches teches, NArmys armys,
			NProductes army_produtes, NSpells nspells, NHeros heros,
			ReturnStatus ret) throws Exception {

		GameTcpAbsRepsonse absChn = (GameTcpAbsRepsonse) chn;
		absChn.pcid = SUPID;

		Player p = verifyPlayer(chn, ret, true);

		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		int num = LogicalSession.getNumOnline();
		if (num > 650) {
			ret.succ = BackToClientCofig.MORE_PL;
			return ret;
		}

		LogicPlayer.changeStatusWhenLogin(p);

		Session ss = addSession(absChn);
		try {
			ss.initData();
		} catch (Exception e) {
			absChn.close();
			LogicalSession.removeSession(ss.getSessionId());
			ret.succ = BackToClientCofig.MORE_PL;
			return ret;
		}

		session.sessionKey = (int) ss.getSessionId();

		// 判断玩家是否正被其他人攻击

		long fighttime = ProAssistFight.getBePlayerFightEndTime(SUPID);

		if (fighttime > 0) {
			ret = getResultStatus(ret, BackToClientCofig.BE_FIGHTING);
			ret.dt = fighttime;
			return ret;
		}

		LogicBuild.resetInfoByBuildWhenLogin(p);

		// 用户数据
		PlayerEntity.getNPlayer(p, player);
		// 用户的装饰，障碍列表数据
		LogicObst.getNBuilds(p, builds);
		// 用户的建筑列表数据
		LogicBuild.getNBuildsInAll(p, builds);

		// 用户的兵工厂中的兵数据
		LogicArmy.getNProductArmys(p, army_produtes);
		// 用户拥有的兵数据
		LogicArmy.getNOwnArmys(p, armys);
		// 取得用户的科技数据
		LogicAttack.toBeAttNTeches(p, teches);
		// 取得药水
		LogicPlayer.getNSPells(p, nspells);

		// 取得被打后的NPCS
		LogicPlayer.getNNPCs(p, npcs);
		// 取得玩家英雄
		LogicHero.getNHeros(p, heros);

		return ret;
	}

	public static ReturnStatus onCreateBuilding(TcpChannel chn, int client_id,
			int gid, int pos, String resType, int costVal, int crystal,
			long cooldown_ms, String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "newBuild");
		verifyBuildCostGidLvl(p, ret, gid, 1, cooldown_ms, resType, costVal,
				crystal, 1);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		int lvl = 0;
		if (cooldown_ms <= 0) {
			lvl = 1;
		}
		Player_buildings isOk = LogicBuild.createNewBuild(p, client_id, gid,
				pos, cooldown_ms, lvl);
		if (isOk != null)
			isEnoughResAndReduce(p, resType, costVal, crystal);

		return ret;
	}

	public static ReturnStatus onUpBuilding(TcpChannel chn, int client_id,
			String resType, int costVal, int crystal, long cooldown_ms,
			String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "upBuild");
		verifyBuildCostBcid(p, ret, client_id, cooldown_ms, resType, costVal,
				crystal, 2);

		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isOk = LogicBuild.upgateBuild(p, client_id, cooldown_ms);
		if (isOk) {
			isEnoughResAndReduce(p, resType, costVal, crystal);
		}
		return ret;
	}

	public static ReturnStatus onFinishBuild(TcpChannel chn, int client_id,
			int crystal, String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "upBuildFinished");
		verifyRes(p, ret, "", 0, crystal);

		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isCanFinish = isCanFinishBuild(p, client_id);
		if (isCanFinish) {
			boolean is_ok = LogicBuild.finishBuild(p, client_id, crystal);
			if (is_ok) {
				isEnoughResAndReduce(p, "", 0, crystal);
			} else {
				ret = getResultStatus(ret, BackToClientCofig.FAIL_FINISH_BUILD);
			}
		}
		return ret;
	}

	public static ReturnStatus onRemoveBuild(TcpChannel chn, int client_id,
			String resType, int costVal, int crystal, long cooldown,
			String havType, int havVal, String sign, ReturnStatus ret)
			throws Exception {

		Player p = verifyForSign(chn, ret, sign, "rmBuild");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicBuild.removeBuild(p, client_id, cooldown);
		if (!ConstantType.Type_Res_Crystal.equals(havType) && havVal > 0) {
			LogicPlayer.addPlayerRes(p, havType, havVal);
		}
		return ret;
	}

	public static ReturnStatus onFinishRemoveBuild(TcpChannel chn,
			int client_id, int crystal, String sign, ReturnStatus ret)
			throws Exception {

		Player p = verifyForSign(chn, ret, sign, "rmBuildFinished");
		verifyRes(p, ret, "", 0, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		boolean is_cost_c = crystal > 0;
		boolean is_ok = LogicBuild.finishRemoveBuild(p, client_id, is_cost_c);
		if (is_ok) {
			isEnoughResAndReduce(p, "", 0, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_FINISH_BUILD);
		}
		return ret;
	}

	public static ReturnStatus onMoveBuild(TcpChannel chn, int client_id,
			int pos, ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		boolean is_ok = LogicBuild.moveBuild(p, client_id, pos);
		if (!is_ok) {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_MOVE_BUILD);
		}

		return ret;
	}

	public static ReturnStatus onHarvestRes(TcpChannel chn, int bcid,
			String resType, int harVal, String sign, ReturnStatus ret)
			throws Exception {

		Player p = verifyForSign(chn, ret, sign, "harvestRes");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isOkey = LogicBuild.harvestRes(p, bcid, resType, harVal);
		if (!isOkey) {
			ret = getResultStatus(ret, BackToClientCofig.BUILD_TYPE_WRONG);
		}

		int pcid = p.getPcid();
		LogicalSession.refreshPInfo(pcid);

		return ret;
	}

	public static ReturnStatus onProduceArmy(TcpChannel chn, int bcid, int gid,
			int num, String resType, int costVal, int crystal, String sign,
			ReturnStatus ret) throws Exception {

		boolean is_add = costVal < 0;// 造兵true，减少造兵false
		costVal = Math.abs(costVal);
		if (costVal == 0 && crystal == 0) {
			ret.succ = BackToClientCofig.REQUEST_INVALID;
		}

		Player p = verifyForSign(chn, ret, sign, "proArmy");
		verifyRes(p, ret, resType, costVal, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		if (is_add) {
			boolean is_ok = LogicArmy.producteArmy(p, bcid, gid, num);
			if (is_ok) {
				isEnoughResAndReduce(p, resType, costVal, crystal);
			} else {
				ret = getResultStatus(ret, BackToClientCofig.ERROR);
			}
		} else {
			if (costVal != 0) {// 减少造兵
				int changeNum = LogicArmy.reduceProductArmy(p, bcid, gid, num);
				boolean is_ok = changeNum > 0;
				if (is_ok) {
					LogicPlayer.addPlayerRes(p, resType, costVal);
					LogicBuild.changeProductArmyBTime(p, bcid, false);
				} else {
					ret = getResultStatus(ret,
							BackToClientCofig.FAIL_REDUCE_PRODCUTE_ARMY);
				}
			}
		}
		return ret;
	}

	public static ReturnStatus onFinishProduceArmy(TcpChannel chn, int bcid,
			NProductes lists, String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "finishArmy");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean is_ok = LogicArmy.natureFinishProductArmy(p, bcid, lists);
		if (!is_ok) {
			ret = getResultStatus(ret,
					BackToClientCofig.FAIL_FINISH_PRODUCT_ARMY);
		}

		return ret;
	}

	public static ReturnStatus onFastFinishProduceArmy(TcpChannel chn,
			int b_client_id, int crystal, NProductes lists, String sign,
			ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "fastFinishProArmy");
		verifyRes(p, ret, "", 0, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean is_ok = LogicArmy.fastFinishProducteArmy(p, b_client_id, lists);
		if (is_ok) {
			isEnoughResAndReduce(p, "", 0, crystal);
		} else {
			ret = getResultStatus(ret,
					BackToClientCofig.FAIL_FAST_FINISH_PRODUCT_ARMY);
		}
		return ret;
	}

	public static ReturnStatus onUpTechnolgy(TcpChannel chn, int b_client_id,
			int gid, String resType, int costVal, int crystal, int upTime,
			String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "upTech");
		verifyRes(p, ret, resType, costVal, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		boolean is_ok = LogicBuild.upgateTech(p, b_client_id, gid, upTime);
		if (is_ok) {
			isEnoughResAndReduce(p, resType, costVal, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_UP_TECH);
		}
		return ret;
	}

	public static ReturnStatus onFinishUpTech(TcpChannel chn, int b_client_id,
			int gid, int crystal, String sign, ReturnStatus ret)
			throws Exception {

		Player p = verifyForSign(chn, ret, sign, "finishUpTech");
		verifyRes(p, ret, "", 0, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean is_ok = LogicBuild.finishUpTech(p, b_client_id, crystal);
		if (is_ok) {
			isEnoughResAndReduce(p, "", 0, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_FINISH_UP_TECH);
		}
		return ret;
	}

	public static ReturnStatus onPveResult(TcpChannel chn, int cpid, int gold,
			int oil, NNPCBeFighteds npcs, String sign, ReturnStatus ret)
			throws Exception {

		Player p = verifyForSign(chn, ret, sign, "pveResult");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean is_ok = LogicPlayer.isChangeNpcs(p, cpid, npcs);
		if (is_ok) {
			LogicPlayer.addGoidAndOil(p, gold, oil);
			ProAssistFight.addEnergyByNPC(p);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_RECODE_NPC_INFO);
		}

		return ret;
	}

	public static ReturnStatus onPveAttackInfo(TcpChannel chn, int cur_npc_id,
			NAttackInfos attInfos, ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicAttack.updatePVEAttact(p, attInfos);

		return ret;
	}

	public static ReturnStatus onPveFightSpoils(TcpChannel chn, int cnpcid,
			NEnergy energy, ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		int pnpcid = p.getCur_npc();
		if (pnpcid == cnpcid) {
			boolean isBoss = cnpcid > 0 && cnpcid % 10 == 0;
			if (isBoss) {
				LogicEnergy.getNEnergyBy(p, 1, energy);
			} else {
				LogicAttack.getEnergyByFight(p, null, energy);
			}
			ProAssistFight.setNPCEnergy(p, energy);
		}

		return ret;
	}

	public static ReturnStatus onFindBeAttactPlayer(TcpChannel chn,
			String resType, int costVal, int crystal, String sign,
			NPlayer beAttPlayer, NTeches bePTeches, NArmys bePArmys,
			NBuilds bePBuilds, NHeros heros, NClan nclan, NInt canGetGold,
			NInt canGetOil, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "findBePlayer");
		verifyResAndReduce(p, ret, resType, costVal, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		Player beAtt = LogicAttack.findBeAttPlayer(p);
		if (beAtt == null) {
			beAtt = NpcImport.getBeAttNPC(p, beAttPlayer, bePTeches, bePArmys,
					bePBuilds);
		} else {
			// 被攻击玩家信息
			PlayerEntity.getNPlayerByFight(beAtt, beAttPlayer);
			LogicAttack.toBeAttNBuilds(beAtt, bePBuilds);
			LogicAttack.toBeAttNBuildsByObst(beAtt, bePBuilds);
			LogicAttack.toBeAttNArmys(beAtt, bePArmys);
			LogicAttack.toBeAttNTeches(beAtt, bePTeches);
			LogicAttack.toBeAttNHeros(beAtt, heros);
			LogicAttack.toFightNClan(beAtt, nclan);
		}

		LogicAttack.getFightCanGetRes(beAtt, p, canGetGold, canGetOil);

		LogicPlayer.upPTime(p, 0);
		return ret;
	}

	public static ReturnStatus onBeginAttact(TcpChannel chn, int attClientId,
			boolean isHitBack, NHeros beHeros, NEnergy energy, ReturnStatus ret)
			throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean is_ok = LogicAttack.beginAttact(p, attClientId, isHitBack,
				beHeros, energy);
		if (!is_ok) {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_BEGIN_ATTACT);
		}

		return ret;
	}

	public static ReturnStatus onAttactInfos(TcpChannel chn, int attClientId,
			NAttackInfos attInfos, NAttackInfos offenDeaths,
			NAttackInfos defenseDeaths, ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicAttack.updateAttact(p, attClientId, 0, 0, attInfos, 0,
				offenDeaths, defenseDeaths);

		return ret;
	}

	public static ReturnStatus onEndAttact(TcpChannel chn, int attClientId,
			int star, int renown, int defenceRenown, int attGold, int attOil,
			int maxGold, int maxOil, NAttackInfos attInfos,
			NBuildBlast bastBuilds, NAttackInfos offenDeaths,
			NAttackInfos defenseDeaths, String sign, ReturnStatus ret)
			throws Exception {

		Player p = verifyForSign(chn, ret, sign, "endFight");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		if (renown > 60) {
			renown = 0;
			defenceRenown = 0;
		}

		LogicAttack.endAttact(p, attClientId, star, renown, defenceRenown,
				attGold, attOil, maxGold, maxOil, attInfos, bastBuilds,
				offenDeaths, defenseDeaths);

		return ret;
	}

	public static ReturnStatus onGoHome(TcpChannel chn, NPlayer player,
			NNPCBeFighteds npcs, NBuilds builds, NTeches teches, NArmys armys,
			NProductes produtes, NSpells nspells, NHeros heros, ReturnStatus ret)
			throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		// 用户数据
		PlayerEntity.getNPlayer(p, player);
		// 用户的装饰，障碍列表数据
		LogicObst.getNBuilds(p, builds);
		// 用户的建筑列表数据
		LogicBuild.getNBuildsInAll(p, builds);
		// 用户的兵工厂中的兵数据
		LogicArmy.getNProductArmys(p, produtes);
		// 用户拥有的兵数据
		LogicArmy.getNOwnArmys(p, armys);
		// 取得用户的科技数据
		LogicAttack.toBeAttNTeches(p, teches);
		// 取得用户药水
		LogicPlayer.getNSPells(p, nspells);
		// 取得被打后的NPCS
		LogicPlayer.getNNPCs(p, npcs);
		// 英雄
		LogicHero.getNHeros(p, heros);

		return ret;
	}

	public static ReturnStatus onBuyRes(TcpChannel chn, String resType,
			int buyVal, int crystal, String sign, NPlayer player,
			ReturnStatus ret) throws Exception {
		if (crystal <= 0) {
			getResultStatus(ret, BackToClientCofig.NOT_ENOUGH_CRYATAL);
			return ret;
		}

		Player p = verifyForSign(chn, ret, sign, "buyRes");
		verifyResAndReduce(p, ret, "", 0, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		LogicPlayer.buyRes(p, resType, buyVal, crystal);
		PlayerEntity.getNPlayer(p, player);
		return ret;
	}

	public static ReturnStatus onReplayAttMail(TcpChannel chn, String mcid,
			NPlayer player, NTeches teches, NArmys amrys, NBuilds builds,
			NAttackInfos attInfo, NLong timeSlot, NHeros defHeros,
			NHeros offHeros, NClan offNclan, NClan defNclan, ReturnStatus ret)
			throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		Attack_mail att_m = Attack_mailEntity.getMail(p, mcid);
		if (att_m == null) {
			ret = getResultStatus(ret, BackToClientCofig.NONE_ATT_MAIL);
			return ret;
		}

		long slotTime_ = 0;
		long abt = att_m.getBegin_time();
		long ebt = att_m.getEnd_time();
		if (ebt > abt) {
			slotTime_ = ebt - abt;
		} else {
			slotTime_ = DateEx.TIME_MINUTE * 3;
		}
		timeSlot.val = slotTime_;

		// 用户数据
		PlayerEntity.getNPlayer(p, player);

		LogicAttack.toDefenseInfo(att_m, builds, amrys, teches, defHeros,
				offHeros, offNclan, defNclan);

		Attack_mailEntity.toSUAInfos(att_m, true, attInfo);

		return ret;
	}

	public static ReturnStatus onHeart(TcpChannel chn, NInt heat,
			ReturnStatus ret) throws Exception {
		verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		GameTcpAbsRepsonse absChn = (GameTcpAbsRepsonse) chn;
		absChn.session.firstIssused();
		return ret;
	}

	public static ReturnStatus onSetPlayerName(TcpChannel chn, String uname,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isHasName = LogicPlayer.isHasName(uname);
		if (isHasName) {
			ret = getResultStatus(ret, BackToClientCofig.HAS_PLAYER_NAME);
		} else {
			LogicPlayer.rename(p, uname);
		}

		return ret;
	}

	public static ReturnStatus onRepairTrap(TcpChannel chn, int b_c_id,
			String resType, int costVal, int crystal, String sign,
			ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "repairTrap");
		verifyBuildCostBcid(p, ret, b_c_id, 0, resType, costVal, crystal, 3);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isOk = LogicBuild.repairTrapBuildInMap(p, b_c_id);
		if (isOk) {
			isEnoughResAndReduce(p, resType, costVal, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_REPAIR_TRAP);
		}
		return ret;
	}

	public static ReturnStatus onHitBack(TcpChannel chn, String mcid,
			NPlayer beAttPlayer, NTeches bePTeches, NArmys bePArmys,
			NBuilds builds, NHeros heros, NClan nclan, ReturnStatus ret)
			throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		Attack_mail att_mail = Attack_mailEntity.getMail(p, mcid);
		if (att_mail == null) {
			ret = getResultStatus(ret, BackToClientCofig.NONE_ATT_MAIL);
			return ret;
		}
		if (att_mail.getIshitback()) {
			ret = getResultStatus(ret, BackToClientCofig.ATT_MAIL_HIT_BACKED);
			return ret;
		}
		int hbpid = att_mail.getAttpcid();
		Player hitBackPlayer = PlayerEntity.getPlayer(hbpid);
		boolean isCanFight = PlayerEntity.isCanBeFight(hitBackPlayer);
		if (!isCanFight) {
			boolean isOnline = false;
			if (hitBackPlayer != null) {
				isOnline = hitBackPlayer.getIsonline();
			}
			if (isOnline) {
				ret = getResultStatus(ret, BackToClientCofig.ONLINE);
			} else {
				ret = getResultStatus(ret, BackToClientCofig.PLAYER_PROTECTING);
			}
			return ret;
		}

		LogicPlayer.upPTime(p, 0);

		int attPcid = p.getPcid();
		int beAttPcid = hitBackPlayer.getPcid();

		ProAssistFight.setBePlayer(attPcid, beAttPcid);

		LogicAttack.resetHitBackRes(hitBackPlayer, p, beAttPlayer);
		// 反击对象的数据信息
		LogicAttack.toBeAttNBuilds(hitBackPlayer, builds);
		LogicAttack.toBeAttNBuildsByObst(hitBackPlayer, builds);
		LogicAttack.toBeAttNArmys(hitBackPlayer, bePArmys);
		LogicAttack.toBeAttNTeches(hitBackPlayer, bePTeches);
		LogicAttack.toBeAttNHeros(hitBackPlayer, heros);
		LogicAttack.toFightNClan(hitBackPlayer, nclan);

		Attack_mailEntity.upAttMailHitBack(att_mail);

		return ret;
	}

	public static ReturnStatus onDownLine(TcpChannel chn, ReturnStatus ret)
			throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicPlayer.downLine(p);

		return ret;
	}

	public static ReturnStatus onBuyCrystalForOpenness(TcpChannel chn,
			int buyCrystalVal, String sign, ReturnStatus ret) throws Exception {
		if (buyCrystalVal <= 0) {
			getResultStatus(ret, BackToClientCofig.NOT_ENOUGH_RES);
			return ret;
		}
		Player p = verifyForSign(chn, ret, sign, "burGemsSelf");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		LogicPlayer.exchangeActive(p, buyCrystalVal);
		// LogicPlayer.handlePayMoneyBuyMonCode(p, buyCrystalVal);

		return ret;
	}

	public static ReturnStatus onGuidNewPlayer(TcpChannel chn, int guid,
			ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicPlayer.upGuid(p, guid);

		return ret;
	}

	public static ReturnStatus onRandomPlayerName(TcpChannel chn,
			NStrVal randomName, ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		String strChn = p.getChannel();
		String randName = LogicPlayer.randomName(strChn);
		if ("".equals(randName)) {
			randName = p.getPname();
		}
		randomName.val = randName;

		return ret;
	}

	public static ReturnStatus onSendChat(TcpChannel chn, NChat newChat,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicChat.sendChat(p, newChat);
		return ret;
	}

	public static ReturnStatus onBuyOrder(TcpChannel chn, int crystal,
			String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "proArmy");
		verifyRes(p, ret, "", 0, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isOk = LogicPlayer.buyBuildOrder(p, crystal);
		if (isOk) {
			isEnoughResAndReduce(p, "", 0, crystal);
			LogicalSession.refreshPInfo(p.getPcid());
		} else {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_BUY_BUILD_ORDER);
		}
		return ret;
	}

	public static ReturnStatus onGetNChats(TcpChannel chn, NChats nchats,
			ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicChat.getNChats(p, nchats);

		return ret;
	}

	public static ReturnStatus onGetAttMails(TcpChannel chn,
			NAttMails attMails, ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicMail.getNMailAtts(p, attMails);

		return ret;
	}

	public static ReturnStatus onGetNMails(TcpChannel chn, NMails nmails,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		LogicMail.getNMails(p, nmails);

		return ret;
	}

	public static ReturnStatus onSendMail(TcpChannel chn, NMail newMail,
			ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicMail.sendMail(p, newMail);

		return ret;
	}

	public static ReturnStatus onFinishGuid(TcpChannel chn, int guid,
			NPlayer player, NBuilds builds, ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicPlayer.upGuid(p, guid);
		PlayerEntity.getNPlayer(p, player);
		// 用户的装饰，障碍列表数据
		LogicObst.getNBuilds(p, builds);
		// 设置建筑用户名称
		LogicBuild.getNBuildsInAll(p, builds);

		return ret;
	}

	public static ReturnStatus onBuySpell(TcpChannel chn, String resType,
			int costVal, int crystal, int gid, String sign, ReturnStatus ret)
			throws Exception {

		Player p = verifyForSign(chn, ret, sign, "buySpell");
		verifyRes(p, ret, resType, costVal, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isHas = LogicPlayer.isHasSpell(p, gid);
		if (!isHas) {
			LogicPlayer.addPlayerSpell(p, gid);
			isEnoughResAndReduce(p, resType, costVal, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.ERROR);
		}

		return ret;
	}

	public static ReturnStatus onAddExp(TcpChannel chn, int addVal,
			String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "addExp");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		LogicPlayer.addExp(p, addVal);

		return ret;
	}

	public static ReturnStatus onAchievementReward(TcpChannel chn,
			int localCurId, int localNextId, NRewards lists, ReturnStatus ret)
			throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicAchievement.finishAchievementsGetRewards(p, localCurId,
				localNextId, lists);

		return ret;
	}

	public static ReturnStatus onGetAchievements(TcpChannel chn,
			NAchievements marks, ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicAchievement.getNAchievements(p, marks);

		return ret;
	}

	public static ReturnStatus onReadNMail(TcpChannel chn, int mcid,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicMail.readMail(p, mcid);

		return ret;
	}

	public static ReturnStatus onGetNRankUse(TcpChannel chn, int page,
			NRankUsers rankUses, ReturnStatus ret) throws Exception {

		verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		Rank_playerEntity.getNRankUsersByWeek(page, rankUses);

		return ret;
	}

	public static ReturnStatus onAddObst(TcpChannel chn, int bcid, int gid,
			int pos, String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "addObst");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		Player_build_obst isOk = LogicObst.createNew(p, bcid, gid, pos, 0, 1);
		if (isOk == null) {
			ret = getResultStatus(ret, BackToClientCofig.ERROR);
		} else {
			LogicPlayer.changeLastAddObst(p);
		}

		return ret;
	}

	public static ReturnStatus onFinishRemoveObst(TcpChannel chn, int ocid,
			int crystal, String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "finishRmObst");
		verifyRes(p, ret, "", 0, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean is_cost_c = crystal > 0;
		boolean is_ok = LogicObst.finishRemoveObst(p, ocid, is_cost_c);
		if (is_ok) {
			isEnoughResAndReduce(p, "", 0, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_FINISH_BUILD);
		}
		return ret;
	}

	public static ReturnStatus onAddObstWall(TcpChannel chn, int ocid, int gid,
			int pos, String resType, int costVal, int crystal,
			long cooldown_ms, String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "addWall");
		verifyBuildCostGidLvl(p, ret, gid, 1, 0, resType, costVal, crystal, 1);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		int lvl = 0;
		if (cooldown_ms <= 0) {
			lvl = 1;
		}
		Player_build_obst isOk = LogicObst.createNew(p, ocid, gid, pos,
				cooldown_ms, lvl);
		if (isOk != null) {
			isEnoughResAndReduce(p, resType, costVal, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.ERROR);
		}
		return ret;
	}

	public static ReturnStatus onUpObstWall(TcpChannel chn, NInts ocids,
			String resType, int costVal, int crystal, String sign,
			ReturnStatus ret) throws Exception {
		Player p = verifyForSign(chn, ret, sign, "upWall");
		verifyRes(p, ret, resType, costVal, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isOk = LogicObst.upgateObstWall(p, ocids);
		if (isOk) {
			isEnoughResAndReduce(p, resType, costVal, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.ERROR);
		}
		return ret;
	}

	public static ReturnStatus onRemoveObst(TcpChannel chn, int ocid,
			String resType, int costVal, int crystal, long coowdown,
			String havType, int havVal, String sign, ReturnStatus ret)
			throws Exception {

		Player p = verifyForSign(chn, ret, sign, "rmObst");
		verifyRes(p, ret, resType, costVal, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isOk = LogicObst.removeObst(p, ocid, coowdown);
		if (isOk) {
			isEnoughResAndReduce(p, resType, costVal, crystal);
			if (havVal > 0) {
				LogicPlayer.changeRes(p, havType, havVal, false);
			}
		} else {
			ret = getResultStatus(ret, BackToClientCofig.REQUEST_INVALID);
		}

		return ret;
	}

	public static ReturnStatus onMoveObst(TcpChannel chn, int ocid, int pos,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean is_ok = LogicObst.moveObst(p, ocid, pos);
		if (!is_ok) {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_MOVE_BUILD);
		}
		return ret;
	}

	public static ReturnStatus onViewPlayer(TcpChannel chn, int pcid,
			NPlayer player, NBuilds builds, NTeches teches, NArmys armys,
			NHeros heros, ReturnStatus ret) throws Exception {

		verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		Player p = PlayerEntity.getPlayer(pcid);
		if (p == null) {
			ret = getResultStatus(ret, BackToClientCofig.NONE_PLAYER_BY_VISIT);
			return ret;
		}
		// 用户数据
		PlayerEntity.getNPlayer(p, player);
		// 用户的装饰，障碍列表数据
		LogicObst.getNBuilds(p, builds);
		// 用户的建筑列表数据
		LogicBuild.getNBuildsInData(p, builds);
		// 用户拥有的兵数据
		LogicArmy.getNArmysInData(p, armys);
		// 取得用户的科技数据
		LogicAttack.toBeAttNTeches(p, teches);
		// 英雄
		LogicHero.getNHeros(p, heros);

		return ret;
	}

	public static ReturnStatus onBuyShield(TcpChannel chn, int crystal,
			long addTime, String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "buyShield");
		verifyRes(p, ret, "", 0, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		long day = DateEx.TIME_DAY;
		long day2 = 2 * DateEx.TIME_DAY;
		long day4 = 4 * DateEx.TIME_DAY;

		boolean isCanbuy = false;
		if (addTime == day) {
			isCanbuy = crystal == 80;
		} else if (addTime == day2) {
			isCanbuy = crystal == 150;
		} else if (addTime == day4) {
			isCanbuy = crystal == 200;

		}

		if (isCanbuy) {
			isEnoughResAndReduce(p, "", 0, crystal);
		} else {
			ret.succ = BackToClientCofig.ERROR;
			return ret;
		}

		LogicPlayer.changePTime(p, addTime);

		return ret;
	}

	public static ReturnStatus onGetPInfo(TcpChannel chn, NPInfo pinfo,
			ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		GameTcpAbsRepsonse absChn = (GameTcpAbsRepsonse) chn;
		absChn.session.getLePlayer().setCanIssuedPinfo(false);

		LogicPlayer.getNPInfos(p, pinfo);

		return ret;
	}

	public static ReturnStatus onRewardDay(TcpChannel chn, NRewards lists,
			String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "dayReward");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicReward.dayLoginReward(p, lists);
		LogicalSession.refreshPInfoByPlayer(p);
		return ret;
	}

	public static ReturnStatus onRewardFirstPay(TcpChannel chn, NRewards lists,
			String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "firstPay");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		LogicReward.firstRewardPay(p, lists);

		LogicalSession.refreshPInfoByPlayer(p);

		return ret;
	}

	public static ReturnStatus onBuyHero(TcpChannel chn, int hgid, int crystal,
			String sign, ReturnStatus ret) throws Exception {
		Player p = verifyForSign(chn, ret, sign, "buyHero");
		verifyRes(p, ret, "", 0, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isOk = LogicHero.createHero(p, hgid, crystal);

		if (isOk) {
			isEnoughResAndReduce(p, "", 0, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_BUY_HERO);
		}
		return ret;
	}

	public static ReturnStatus onLiveHero(TcpChannel chn, int hgid,
			int crystal, String sign, ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "liveHero");
		verifyRes(p, ret, "", 0, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isOk = LogicHero.liveHero(p, hgid);

		if (isOk) {
			isEnoughResAndReduce(p, "", 0, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.NONE_HERO);
		}

		return ret;
	}

	public static ReturnStatus onDeadHero(TcpChannel chn, int hgid,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		LogicHero.deadHero(p, hgid);

		return ret;
	}

	public static ReturnStatus onRefreshHeros(TcpChannel chn, NHeros heors,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		GameTcpAbsRepsonse absChn = (GameTcpAbsRepsonse) chn;
		absChn.session.getLePlayer().setCanIssuedHeros(false);
		LogicHero.getNHeros(p, heors);

		return ret;
	}

	public static ReturnStatus onAddHeroEnergy(TcpChannel chn, int hgid,
			int egid, int gold, int oil, int crystal, String sign,
			NInt outHgid, NInt outEgid, NInt curEnergyNum, NInt addval,
			ReturnStatus ret) throws Exception {

		Player p = verifyForSign(chn, ret, sign, "addHEnergy");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		String strGold = ConstantType.Type_Res_Gold;
		verifyRes(p, ret, strGold, gold, crystal);

		String strOid = ConstantType.Type_Res_Oil;
		verifyRes(p, ret, strOid, oil, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isCan = LogicEnergy.isEnough(gold, oil, crystal);
		if (!isCan) {
			ret.succ = BackToClientCofig.NOT_ENOUGH_RES;
		}

		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		outHgid.val = hgid;
		outEgid.val = egid;
		LogicEnergy.addEnergy(p, hgid, egid, curEnergyNum, addval);

		if (addval.val == 0) {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_ADD_Energy);
		} else {
			LogicPlayer.changeRes(p, ConstantType.Type_Res_Crystal, -crystal,
					false);
			LogicPlayer.changeRes(p, ConstantType.Type_Res_Gold, -gold, false);
			LogicPlayer.changeRes(p, ConstantType.Type_Res_Oil, -oil, false);
			LogicalSession.refreshPInfo(p.getPcid());
		}

		return ret;
	}

	public static ReturnStatus onOpenTreasureBox(TcpChannel chn, int crystal,
			int num, String sign, NEnergys energys, ReturnStatus ret)
			throws Exception {

		if (num < 0)
			return ret;

		boolean isCan = false;
		int gem = Math.abs(crystal);
		if (num == 1) {
			isCan = (gem == 60);
		} else if (num == 10) {
			isCan = (gem == 490);
		}

		if (!isCan) {
			ret.succ = BackToClientCofig.NOT_ENOUGH_RES;
		}

		Player p = verifyForSign(chn, ret, sign, "buyEnergyBox");
		verifyRes(p, ret, "", 0, gem);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicEnergy.getNEnergysByBox(p, num, energys);
		int len = 0;
		if (energys != null && energys.list != null) {
			len = energys.list.size();
			if (len <= 0) {
				ret = getResultStatus(ret, BackToClientCofig.MAX_Energy);
			} else {
				isEnoughResAndReduce(p, "", 0, gem);
				for (NEnergy item : energys.list) {
					LogicPlayer.changeEnergyNum(p, item.egid, item.num);
				}
			}
		}

		return ret;
	}

	public static ReturnStatus onCreateOrderPayMoney(TcpChannel chn,
			String strUni, String phoneInfo, NStrVal oriderCid, ReturnStatus ret)
			throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		String chnOrderid = LogicOrder.createNewOrder(p, strUni, phoneInfo);
		oriderCid.val = chnOrderid;
		if ("".equals(chnOrderid)) {
			ret.succ = BackToClientCofig.Fail_Create_Order;
		}

		return ret;
	}

	public static ReturnStatus onLoginQH360(TcpChannel chn, String strCode,
			NStrVal strToken, NStrVal strUID, ReturnStatus ret)
			throws Exception {

		Map<String, String> map = PayQiHu360.getQH360UIDAndTokenByCode(strCode);
		strToken.val = MapEx.getString(map, OAuth360.Key_Token);
		strUID.val = MapEx.getString(map, OAuth360.Key_User_ID);
		if (strToken.val == null || "".equals(strToken.val.trim())) {
			ret = getResultStatus(ret, BackToClientCofig.FAIL_TOKEN);
		}

		return ret;
	}

	public static ReturnStatus onDayTasksReward(TcpChannel chn, int localDRID,
			NRewards lists, ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isOkey = LogicReward.dayTasksReward(p, localDRID, lists);
		if (isOkey) {
			LogicalSession.refreshPInfoByPlayer(p);
		}

		return ret;
	}

	public static ReturnStatus onGetNRankUsersByAll(TcpChannel chn, int page,
			NRankUsers rankUses, ReturnStatus ret) throws Exception {

		verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		Rank_playerEntity.getNRankUsersByAll(page, rankUses);

		return ret;
	}

	public static ReturnStatus onCreatClan(TcpChannel chn, int icon,
			String cname, String cdesc, String resType, int costVal,
			int crystal, NStrVal ccid, ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		verifyRes(p, ret, resType, costVal, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		int result = LogicClan.getWaitTime(p);
		if (result != BackToClientCofig.SUCCESS) {
			ret = getResultStatus(ret, result);
			return ret;
		}
		String strCcid = LogicClan.createClan(p, cname, icon, cdesc);
		ccid.val = strCcid;
		if (!"".equals(strCcid)) {
			isEnoughResAndReduce(p, resType, costVal, crystal);
			LogicalSession.refreshPInfoByPlayer(p);
			int pcid = p.getPcid();
			LogicalSession.refreshLEClan(pcid, strCcid, false);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.CLAN_NAME_HAS);
		}

		return ret;
	}

	public static ReturnStatus onExitClan(TcpChannel chn, String ccid,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		int result = LogicClan.exitClan(p);
		ret = getResultStatus(ret, result);
		if (result == BackToClientCofig.SUCCESS) {
			int pcid = p.getPcid();
			LogicalSession.refreshLEClanMember(pcid, ccid, true);
		}

		return ret;
	}

	public static ReturnStatus onJoinInClan(TcpChannel chn, String ccid,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		int result = LogicClan.joinClanReq(p, ccid);
		ret = getResultStatus(ret, result);
		if (result == BackToClientCofig.SUCCESS) {
			int pcid = p.getPcid();
			LogicalSession.refreshLEClanReqForOther(pcid, ccid);
		}

		return ret;
	}

	public static ReturnStatus onAcceptJoinIn(TcpChannel chn, String ccid,
			int pcid, ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		int result = LogicClan.accepteJoin(p, ccid, pcid);
		ret = getResultStatus(ret, result);
		if (result == BackToClientCofig.SUCCESS) {
			boolean isOther = false;
			int ppcid = p.getPcid();
			LogicalSession.refreshLEClan(pcid, ccid, isOther);
			LogicalSession.refreshLEClanMember(ppcid, ccid, isOther);
		}

		return ret;
	}

	public static ReturnStatus onRefuseJoinIn(TcpChannel chn, String ccid,
			int pcid, ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		int result = LogicClan.refuseJoinIn(p, ccid, pcid);
		ret = getResultStatus(ret, result);

		return ret;
	}

	public static ReturnStatus onGetOwnClanRequest(TcpChannel chn,
			NClanRequests clanreqes, ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicClan.getNClanRequests(p, clanreqes);

		return ret;
	}

	public static ReturnStatus onGetOwnClan(TcpChannel chn, NClan clan,
			ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicClan.getNClan(p, clan);

		return ret;
	}

	public static ReturnStatus onGetOwnClanMember(TcpChannel chn,
			NClanMembers members, ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicClan.getNClanMembers(p, members);

		return ret;
	}

	public static ReturnStatus onSearchClan(TcpChannel chn, String cname,
			NClans clans, ReturnStatus ret) throws Exception {

		verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicClan.searchClansByName(cname, clans);

		return ret;
	}

	public static ReturnStatus onSeeClan(TcpChannel chn, String ccid,
			NClan clan, NClanMembers members, ReturnStatus ret)
			throws Exception {

		verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicClan.seeClan(ccid, clan, members, ret);

		return ret;
	}

	public static ReturnStatus onOutClanMember(TcpChannel chn, int pcid,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		boolean isOkey = LogicClan.outClanMember(p, pcid);
		if (isOkey) {
			String ccid = p.getClancid();
			boolean isOther = true;
			int ppcid = p.getPcid();
			LogicalSession.refreshLEClanMember(ppcid, ccid, isOther);
			LogicalSession.refreshOutClan(pcid, false);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.CLAN_None_Power_Handle);
		}

		return ret;
	}

	public static ReturnStatus onChangeClan(TcpChannel chn, String desc,
			ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		boolean isOkey = LogicClan.changeClanDesc(p, desc);
		if (isOkey) {
			String ccid = p.getClancid();
			boolean isOther = true;
			int pcid = p.getPcid();
			LogicalSession.refreshLEClan(pcid, ccid, isOther);
		}

		return ret;
	}

	public static ReturnStatus onAllotClanPost(TcpChannel chn, int pcid,
			int post, ReturnStatus ret) throws Exception {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		boolean isOkey = LogicClan.allotClanPost(p, pcid, post);
		if (isOkey) {
			String ccid = p.getClancid();
			boolean isOther = true;
			int ppcid = p.getPcid();
			LogicalSession.refreshLEClanMember(ppcid, ccid, isOther);
		}

		return ret;
	}

	public static ReturnStatus onDonateClanGold(TcpChannel chn, int dgold,
			int curval, int crystal, ReturnStatus ret) throws Exception {

		String resType = ConstantType.Type_Res_Gold;
		Player p = verifyPlayer(chn, ret, false);
		verifyRes(p, ret, resType, curval, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		boolean isOkey = LogicClan.donateClanGold(p, dgold);
		if (isOkey) {
			isEnoughResAndReduce(p, resType, curval, crystal);
			String ccid = p.getClancid();
			boolean isOther = false;
			int pcid = p.getPcid();
			LogicalSession.refreshLEClan(pcid, ccid, isOther);
			LogicalSession.refreshLEClanMember(pcid, ccid, isOther);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.CLAN_Donate_Fail);
		}

		return ret;
	}

	public static ReturnStatus onDonateClanOil(TcpChannel chn, int doil,
			int curval, int crystal, ReturnStatus ret) throws Exception {

		String resType = ConstantType.Type_Res_Oil;
		Player p = verifyPlayer(chn, ret, false);
		verifyRes(p, ret, resType, curval, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		boolean isOkey = LogicClan.donateClanOil(p, doil);
		if (isOkey) {
			isEnoughResAndReduce(p, resType, curval, crystal);
			String ccid = p.getClancid();
			boolean isOther = false;
			int pcid = p.getPcid();
			LogicalSession.refreshLEClan(pcid, ccid, isOther);
			LogicalSession.refreshLEClanMember(pcid, ccid, isOther);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.CLAN_Donate_Fail);
		}

		return ret;
	}

	public static ReturnStatus onGetNRankClan(TcpChannel chn, int page,
			NRankClans rankClans, ReturnStatus ret) throws Exception {

		verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicClan.getNRankClanWeek(page, rankClans);

		return ret;
	}

	public static ReturnStatus onGetNRankClanByAll(TcpChannel chn, int page,
			NRankClans rankClans, ReturnStatus ret) throws Exception {

		verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}

		LogicClan.getNRankClanAll(page, rankClans);

		return ret;
	}

	public static ReturnStatus onRename(TcpChannel chn, int crystal,
			String uname, ReturnStatus ret) throws Exception {

		if (crystal < 150) {
			ret = getResultStatus(ret, BackToClientCofig.Fail_Rename);
			return ret;
		}

		Player p = verifyPlayer(chn, ret, false);
		verifyRes(p, ret, "", 0, crystal);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		boolean isOkey = LogicPlayer.rename(p, uname);
		if (isOkey) {
			isEnoughResAndReduce(p, "", 0, crystal);
		} else {
			ret = getResultStatus(ret, BackToClientCofig.Fail_Rename);
		}
		LogicalSession.refreshPInfoByPlayer(p);

		return ret;
	}

	public static ReturnStatus onDownBuildLvl(TcpChannel chn, int bcid,
			int downlvl, ReturnStatus ret) throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicBuild.downBuildLvl(p, bcid, downlvl);

		return ret;
	}

	public static ReturnStatus onCmRegistFast(TcpChannel chn, String chnstr,
			NStrVal outLgId, NStrVal outLgPwd, ReturnStatus ret)
			throws Exception {

		LogicLogin.regestUserFastByCm(chnstr, outLgId, outLgPwd);

		return ret;
	}

	public static ReturnStatus onCmRegist(TcpChannel chn, String lgId,
			String lgPwd, String chnstr, ReturnStatus ret) throws Exception {

		boolean isOk = LogicLogin.regestUserByCm(lgId, lgPwd, chnstr);
		if (!isOk) {
			ret = getResultStatus(ret, BackToClientCofig.HAS_LgId);
		}

		return ret;
	}

	static public ReturnStatus onShareSuccess(TcpChannel chn, ReturnStatus ret)
			throws Exception {
		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicPlayer.shareSuccess(p);
		return ret;
	}

	static public ReturnStatus onLootMerchant(TcpChannel chn, String resType,
			int resVal, String sign, ReturnStatus ret) throws Exception {
		Player p = verifyForSign(chn, ret, sign, "robShip");
		if (ret.succ != BackToClientCofig.SUCCESS) {
			return ret;
		}
		LogicPlayer.changeRes(p, resType, resVal, false);
		LogicalSession.refreshPInfoByPlayer(p);
		return ret;
	}
}
