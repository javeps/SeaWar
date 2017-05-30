package com.sea.service;

import gen_b2g.serv.SeaWarServiceI;
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

import java.io.ByteArrayOutputStream;

import com.bowlong.netty.bio2.TcpChannel;
import com.sea.logic.BackToClientLogicHandle;

public class SeaWarServiceImp extends SeaWarServiceI {

	@Override
	public TcpChannel chn(int XID) throws Exception {

		return null;
	}

	@Override
	public ByteArrayOutputStream getOutStream() {

		return null;
	}

	@Override
	public void freeOutStream(ByteArrayOutputStream baos) {

	}

	@Override
	public ReturnStatus onLoginUserByUid(TcpChannel chn, String uid,
			String uuid, String pwd, String name, String channel, String model,
			String version, int servid, NPlayers players, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onLoginUserByUid(chn, uid, uuid, pwd,
				name, channel, model, version, servid, players, ret);
	}

	@Override
	public ReturnStatus onLoginUPlayer(TcpChannel chn, int SUPID,
			NSession session, NPlayer player, NNPCBeFighteds npcs,
			NBuilds builds, NTeches teches, NArmys armys,
			NProductes army_produtes, NSpells nspells, NHeros heros,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onLoginUPlayer(chn, SUPID, session,
				player, npcs, builds, teches, armys, army_produtes, nspells,
				heros, ret);
	}

	@Override
	public ReturnStatus onCreateBuilding(TcpChannel chn, int client_id,
			int gid, int pos, String resType, int costVal, int crystal,
			long cooldown_ms, String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onCreateBuilding(chn, client_id, gid,
				pos, resType, costVal, crystal, cooldown_ms, sign, ret);
	}

	@Override
	public ReturnStatus onUpBuilding(TcpChannel chn, int client_id,
			String resType, int costVal, int crystal, long cooldown_ms,
			String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onUpBuilding(chn, client_id, resType,
				costVal, crystal, cooldown_ms, sign, ret);
	}

	@Override
	public ReturnStatus onFinishBuild(TcpChannel chn, int client_id,
			int crystal, String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onFinishBuild(chn, client_id, crystal,
				sign, ret);
	}

	@Override
	public ReturnStatus onRemoveBuild(TcpChannel chn, int client_id,
			String resType, int costVal, int crystal, long cooldown,
			String havType, int havVal, String sign, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onRemoveBuild(chn, client_id, resType,
				costVal, crystal, cooldown, havType, havVal, sign, ret);
	}

	@Override
	public ReturnStatus onMoveBuild(TcpChannel chn, int client_id, int pos,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onMoveBuild(chn, client_id, pos, ret);
	}

	@Override
	public ReturnStatus onHarvestRes(TcpChannel chn, int b_client_id,
			String resType, int harVal, String sign, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onHarvestRes(chn, b_client_id, resType,
				harVal, sign, ret);
	}

	@Override
	public ReturnStatus onProduceArmy(TcpChannel chn, int b_client_id, int gid,
			int num, String resType, int costVal, int crystal, String sign,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onProduceArmy(chn, b_client_id, gid,
				num, resType, costVal, crystal, sign, ret);
	}

	@Override
	public ReturnStatus onFinishProduceArmy(TcpChannel chn, int bcid,
			NProductes lists, String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onFinishProduceArmy(chn, bcid, lists,
				sign, ret);
	}

	@Override
	public ReturnStatus onFastFinishProduceArmy(TcpChannel chn, int bcid,
			int crystal, NProductes lists, String sign, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onFastFinishProduceArmy(chn, bcid,
				crystal, lists, sign, ret);
	}

	@Override
	public ReturnStatus onPveResult(TcpChannel chn, int cur_npc_id, int gold,
			int oil, NNPCBeFighteds npcs, String sign, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onPveResult(chn, cur_npc_id, gold, oil,
				npcs, sign, ret);
	}

	@Override
	public ReturnStatus onUpTechnolgy(TcpChannel chn, int b_client_id, int gid,
			String resType, int costVal, int crystal, int upTime, String sign,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onUpTechnolgy(chn, b_client_id, gid,
				resType, costVal, crystal, upTime, sign, ret);
	}

	@Override
	public ReturnStatus onFinishUpTech(TcpChannel chn, int b_client_id,
			int gid, int crystal, String sign, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onFinishUpTech(chn, b_client_id, gid,
				crystal, sign, ret);
	}

	@Override
	public ReturnStatus onFindBeAttactPlayer(TcpChannel chn, String resType,
			int costVal, int crystal, String sign, NPlayer beAttPlayer,
			NTeches bePTeches, NArmys bePArmys, NBuilds bePBuilds,
			NHeros heros, NClan nclan, NInt canGetGold, NInt canGetOil,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onFindBeAttactPlayer(chn, resType,
				costVal, crystal, sign, beAttPlayer, bePTeches, bePArmys,
				bePBuilds, heros, nclan, canGetGold, canGetOil, ret);
	}

	@Override
	public ReturnStatus onBeginAttact(TcpChannel chn, int attcid,
			boolean isHitBack, NHeros beHeros, NEnergy energy, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onBeginAttact(chn, attcid, isHitBack,
				beHeros, energy, ret);
	}

	@Override
	public ReturnStatus onAttactInfos(TcpChannel chn, int attClientId,
			NAttackInfos attInfos, NAttackInfos offenDeaths,
			NAttackInfos defenseDeaths, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onAttactInfos(chn, attClientId,
				attInfos, offenDeaths, defenseDeaths, ret);
	}

	@Override
	public ReturnStatus onEndAttact(TcpChannel chn, int attClientId, int star,
			int renown, int defenceRenown, int attGold, int attOil,
			int maxGold, int maxOil, NAttackInfos attInfos,
			NBuildBlast bastBuilds, NAttackInfos offenDeaths,
			NAttackInfos defenseDeaths, String sign, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onEndAttact(chn, attClientId, star,
				renown, defenceRenown, attGold, attOil, maxGold, maxOil,
				attInfos, bastBuilds, offenDeaths, defenseDeaths, sign, ret);
	}

	@Override
	public ReturnStatus onGoHome(TcpChannel chn, NPlayer player,
			NNPCBeFighteds npcs, NBuilds builds, NTeches teches, NArmys armys,
			NProductes produtes, NSpells nspells, NHeros heros, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onGoHome(chn, player, npcs, builds,
				teches, armys, produtes, nspells, heros, ret);
	}

	@Override
	public ReturnStatus onPveAttackInfo(TcpChannel chn, int cur_npc_id,
			NAttackInfos deaths, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onPveAttackInfo(chn, cur_npc_id, deaths,
				ret);
	}

	@Override
	public ReturnStatus onReplayAttMail(TcpChannel chn, String mcid,
			NPlayer player, NTeches teches, NArmys amrys, NBuilds builds,
			NAttackInfos attInfo, NLong timeSlot, NHeros defHeros,
			NHeros offHeros, NClan offNclan, NClan defNclan, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onReplayAttMail(chn, mcid, player,
				teches, amrys, builds, attInfo, timeSlot, defHeros, offHeros,
				offNclan, defNclan, ret);
	}

	@Override
	public ReturnStatus onBuyRes(TcpChannel chn, String resType, int buyVal,
			int crystal, String sign, NPlayer player, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onBuyRes(chn, resType, buyVal, crystal,
				sign, player, ret);
	}

	@Override
	public ReturnStatus onHeart(TcpChannel chn, NInt heat, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onHeart(chn, heat, ret);
	}

	@Override
	public ReturnStatus onSetPlayerName(TcpChannel chn, String uname,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onSetPlayerName(chn, uname, ret);
	}

	@Override
	public ReturnStatus onRepairTrap(TcpChannel chn, int b_c_id,
			String resType, int costVal, int crystal, String sign,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onRepairTrap(chn, b_c_id, resType,
				costVal, crystal, sign, ret);
	}

	@Override
	public ReturnStatus onHitBack(TcpChannel chn, String mcid,
			NPlayer beAttPlayer, NTeches bePTeches, NArmys bePArmys,
			NBuilds builds, NHeros heros, NClan nclan, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onHitBack(chn, mcid, beAttPlayer,
				bePTeches, bePArmys, builds, heros, nclan, ret);
	}

	@Override
	public ReturnStatus onDownLine(TcpChannel chn, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onDownLine(chn, ret);
	}

	@Override
	public ReturnStatus onBuyCrystalForOpenness(TcpChannel chn,
			int buyCrystalVal, String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onBuyCrystalForOpenness(chn,
				buyCrystalVal, sign, ret);
	}

	@Override
	public ReturnStatus onGuidNewPlayer(TcpChannel chn, int guid,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGuidNewPlayer(chn, guid, ret);
	}

	@Override
	public ReturnStatus onRandomPlayerName(TcpChannel chn, NStrVal randomName,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onRandomPlayerName(chn, randomName, ret);
	}

	@Override
	public ReturnStatus onSendChat(TcpChannel chn, NChat newChat,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onSendChat(chn, newChat, ret);
	}

	@Override
	public ReturnStatus onBuyOrder(TcpChannel chn, int crystal, String sign,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onBuyOrder(chn, crystal, sign, ret);
	}

	@Override
	public ReturnStatus onGetAttMails(TcpChannel chn, NAttMails attMails,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetAttMails(chn, attMails, ret);
	}

	@Override
	public ReturnStatus onGetNChats(TcpChannel chn, NChats nchats,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetNChats(chn, nchats, ret);
	}

	@Override
	public ReturnStatus onGetNMails(TcpChannel chn, NMails nmails,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetNMails(chn, nmails, ret);
	}

	@Override
	public ReturnStatus onSendMail(TcpChannel chn, NMail newMail,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onSendMail(chn, newMail, ret);
	}

	@Override
	public ReturnStatus onFinishGuid(TcpChannel chn, int guid, NPlayer player,
			NBuilds builds, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onFinishGuid(chn, guid, player, builds,
				ret);
	}

	@Override
	public ReturnStatus onFinishRemoveBuild(TcpChannel chn, int client_id,
			int crystal, String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onFinishRemoveBuild(chn, client_id,
				crystal, sign, ret);
	}

	@Override
	public ReturnStatus onBuySpell(TcpChannel chn, String resType, int costVal,
			int crystal, int gid, String sign, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onBuySpell(chn, resType, costVal,
				crystal, gid, sign, ret);
	}

	@Override
	public ReturnStatus onAddExp(TcpChannel chn, int addVal, String sign,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onAddExp(chn, addVal, sign, ret);
	}

	@Override
	public ReturnStatus onAchievementReward(TcpChannel chn, int localCurId,
			int localNextId, NRewards lists, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onAchievementReward(chn, localCurId,
				localNextId, lists, ret);
	}

	@Override
	public ReturnStatus onAddObst(TcpChannel chn, int bcid, int gid, int pos,
			String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle
				.onAddObst(chn, bcid, gid, pos, sign, ret);
	}

	@Override
	public ReturnStatus onGetAchievements(TcpChannel chn, NAchievements marks,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetAchievements(chn, marks, ret);
	}

	@Override
	public ReturnStatus onReadNMail(TcpChannel chn, int mailId, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onReadNMail(chn, mailId, ret);
	}

	@Override
	public ReturnStatus onGetNRankUse(TcpChannel chn, int page,
			NRankUsers rankUses, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetNRankUse(chn, page, rankUses, ret);
	}

	@Override
	public ReturnStatus onFinishRemoveObst(TcpChannel chn, int ocid,
			int crystal, String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onFinishRemoveObst(chn, ocid, crystal,
				sign, ret);
	}

	@Override
	public ReturnStatus onRemoveObst(TcpChannel chn, int ocid, String resType,
			int costVal, int crystal, long coowdown, String havType,
			int havVal, String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onRemoveObst(chn, ocid, resType,
				costVal, crystal, coowdown, havType, havVal, sign, ret);
	}

	@Override
	public ReturnStatus onAddObstWall(TcpChannel chn, int ocid, int gid,
			int pos, String resType, int costVal, int crystal,
			long cooldown_ms, String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onAddObstWall(chn, ocid, gid, pos,
				resType, costVal, crystal, cooldown_ms, sign, ret);
	}

	@Override
	public ReturnStatus onUpObstWall(TcpChannel chn, NInts ocids,
			String resType, int costVal, int crystal, String sign,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onUpObstWall(chn, ocids, resType,
				costVal, crystal, sign, ret);
	}

	@Override
	public ReturnStatus onMoveObst(TcpChannel chn, int ocid, int pos,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onMoveObst(chn, ocid, pos, ret);
	}

	@Override
	public ReturnStatus onViewPlayer(TcpChannel chn, int pid, NPlayer player,
			NBuilds builds, NTeches teches, NArmys armys, NHeros heros,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onViewPlayer(chn, pid, player, builds,
				teches, armys, heros, ret);
	}

	@Override
	public ReturnStatus onBuyShield(TcpChannel chn, int crystal, long addTime,
			String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onBuyShield(chn, crystal, addTime, sign,
				ret);
	}

	@Override
	public ReturnStatus onGetPInfo(TcpChannel chn, NPInfo pinfo,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetPInfo(chn, pinfo, ret);
	}

	@Override
	public ReturnStatus onRewardDay(TcpChannel chn, NRewards lists,
			String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onRewardDay(chn, lists, sign, ret);
	}

	@Override
	public ReturnStatus onRewardFirstPay(TcpChannel chn, NRewards lists,
			String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onRewardFirstPay(chn, lists, sign, ret);
	}

	@Override
	public ReturnStatus onBuyHero(TcpChannel chn, int hgid, int crystal,
			String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onBuyHero(chn, hgid, crystal, sign, ret);
	}

	@Override
	public ReturnStatus onLiveHero(TcpChannel chn, int hgid, int crystal,
			String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle
				.onLiveHero(chn, hgid, crystal, sign, ret);
	}

	@Override
	public ReturnStatus onDeadHero(TcpChannel chn, int hgid, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onDeadHero(chn, hgid, ret);
	}

	@Override
	public ReturnStatus onRefreshHeros(TcpChannel chn, NHeros heors,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onRefreshHeros(chn, heors, ret);
	}

	@Override
	public ReturnStatus onAddHeroEnergy(TcpChannel chn, int hgid, int egid,
			int gold, int oil, int crystal, String sign, NInt outHgid,
			NInt outEgid, NInt curEnergyNum, NInt addval, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle
				.onAddHeroEnergy(chn, hgid, egid, gold, oil, crystal, sign,
						outHgid, outEgid, curEnergyNum, addval, ret);
	}

	@Override
	public ReturnStatus onPveFightSpoils(TcpChannel chn, int cnpcid,
			NEnergy energy, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onPveFightSpoils(chn, cnpcid, energy,
				ret);
	}

	@Override
	public ReturnStatus onCreateOrderPayMoney(TcpChannel chn, String strUni,
			NStrVal oriderCid, String phoneInfo, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onCreateOrderPayMoney(chn, strUni,
				phoneInfo, oriderCid, ret);
	}

	@Override
	public ReturnStatus onOpenTreasureBox(TcpChannel chn, int crystal, int num,
			String sign, NEnergys energy, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onOpenTreasureBox(chn, crystal, num,
				sign, energy, ret);
	}

	@Override
	public ReturnStatus onLoginQH360(TcpChannel chn, String strCode,
			NStrVal strToken, NStrVal strUID, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onLoginQH360(chn, strCode, strToken,
				strUID, ret);
	}

	@Override
	public ReturnStatus onDayTasksReward(TcpChannel chn, int localDRID,
			NRewards lists, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onDayTasksReward(chn, localDRID, lists,
				ret);
	}

	@Override
	public ReturnStatus onGetNRankUsersByAll(TcpChannel chn, int page,
			NRankUsers rankUses, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetNRankUsersByAll(chn, page,
				rankUses, ret);
	}

	@Override
	public ReturnStatus onGetOwnClanRequest(TcpChannel chn,
			NClanRequests clanreqes, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetOwnClanRequest(chn, clanreqes, ret);
	}

	@Override
	public ReturnStatus onGetNRankClanByAll(TcpChannel chn, int page,
			NRankClans rankClans, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetNRankClanByAll(chn, page,
				rankClans, ret);
	}

	@Override
	public ReturnStatus onCreatClan(TcpChannel chn, int icon, String cname,
			String cdesc, String resType, int costVal, int crystal,
			NStrVal ccid, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onCreatClan(chn, icon, cname, cdesc,
				resType, costVal, crystal, ccid, ret);
	}

	@Override
	public ReturnStatus onExitClan(TcpChannel chn, String ccid, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onExitClan(chn, ccid, ret);
	}

	@Override
	public ReturnStatus onJoinInClan(TcpChannel chn, String ccid,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onJoinInClan(chn, ccid, ret);
	}

	@Override
	public ReturnStatus onAcceptJoinIn(TcpChannel chn, String ccid, int pcid,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onAcceptJoinIn(chn, ccid, pcid, ret);
	}

	@Override
	public ReturnStatus onRefuseJoinIn(TcpChannel chn, String ccid, int pcid,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onRefuseJoinIn(chn, ccid, pcid, ret);
	}

	@Override
	public ReturnStatus onSearchClan(TcpChannel chn, String cname,
			NClans clans, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onSearchClan(chn, cname, clans, ret);
	}

	@Override
	public ReturnStatus onSeeClan(TcpChannel chn, String ccid, NClan clan,
			NClanMembers members, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onSeeClan(chn, ccid, clan, members, ret);
	}

	@Override
	public ReturnStatus onGetOwnClan(TcpChannel chn, NClan clan,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetOwnClan(chn, clan, ret);
	}

	@Override
	public ReturnStatus onGetNRankClan(TcpChannel chn, int page,
			NRankClans rankClans, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle
				.onGetNRankClan(chn, page, rankClans, ret);
	}

	@Override
	public ReturnStatus onGetOwnClanMember(TcpChannel chn,
			NClanMembers members, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onGetOwnClanMember(chn, members, ret);
	}

	@Override
	public ReturnStatus onDonateClanGold(TcpChannel chn, int dgold, int curval,
			int crystal, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onDonateClanGold(chn, dgold, curval,
				crystal, ret);
	}

	@Override
	public ReturnStatus onDonateClanOil(TcpChannel chn, int doil, int curval,
			int crystal, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onDonateClanOil(chn, doil, curval,
				crystal, ret);
	}

	@Override
	public ReturnStatus onOutClanMember(TcpChannel chn, int pcid,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onOutClanMember(chn, pcid, ret);
	}

	@Override
	public ReturnStatus onChangeClan(TcpChannel chn, String desc,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onChangeClan(chn, desc, ret);
	}

	@Override
	public ReturnStatus onAllotClanPost(TcpChannel chn, int pcid, int post,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onAllotClanPost(chn, pcid, post, ret);
	}

	@Override
	public ReturnStatus onRename(TcpChannel chn, int crystal, String uname,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onRename(chn, crystal, uname, ret);
	}

	@Override
	public ReturnStatus onDownBuildLvl(TcpChannel chn, int bcid, int downlvl,
			ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onDownBuildLvl(chn, bcid, downlvl, ret);
	}

	@Override
	public ReturnStatus onCmRegist(TcpChannel chn, String lgId, String lgPwd,
			String chnstr, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle
				.onCmRegist(chn, lgId, lgPwd, chnstr, ret);
	}

	@Override
	public ReturnStatus onCmRegistFast(TcpChannel chn, NStrVal outLgId,
			NStrVal outLgPwd, String chnstr, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onCmRegistFast(chn, chnstr, outLgId,
				outLgPwd, ret);
	}

	@Override
	public ReturnStatus onShareSuccess(TcpChannel chn, ReturnStatus ret)
			throws Exception {
		return BackToClientLogicHandle.onShareSuccess(chn, ret);
	}

	@Override
	public ReturnStatus onLootMerchant(TcpChannel chn, String resType,
			int resVal, String sign, ReturnStatus ret) throws Exception {
		return BackToClientLogicHandle.onLootMerchant(chn, resType, resVal,
				sign, ret);
	}

}
