package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NReward;
import gen_b2g.serv.bean.NRewards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.util.MapEx;
import com.sea.channel.ChannelCfg;
import com.sea.db.bean.Clan_member;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Rank_clan;
import com.sea.db.bean.Rank_player;
import com.sea.db.entity.Clan_memberEntity;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Rank_clanEntity;
import com.sea.db.entity.Rank_playerEntity;
import com.sea.db.entity.RewardEntity;
import com.sea.json.originData.CfgJson;
import com.sea.json.originData.LanguageJson;
import com.sea.localEntry.DBAchevement;
import com.sea.localEntry.DBActivity;
import com.sea.logic.session.LogicalSession;
import com.sea.tools.FormulaGame;
import com.sea.tools.UtileTools;

public class LogicReward implements Serializable {

	static Log log = LogFactory.getLog(LogicReward.class);

	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "rawtypes" })
	private static String getStrInfoByRewards(String rewardTit, NRewards lists,
			String strChn) {
		if (lists == null || lists.lists == null || lists.lists.size() <= 0)
			return "";
		if (rewardTit == null)
			rewardTit = "";
		Map map = LanguageJson.getMapDataByChannel(strChn);

		StringBuffer sb = new StringBuffer();
		sb.append(rewardTit);
		String strCont = "";
		String strName = "";
		for (NReward item : lists.lists) {
			switch (item.type) {
			case ConstantType.Type_Reward_DIAM:
				strCont = MapEx.getString(map, LanguageJson.Mail_ms_dam);
				strCont = StrEx.fmt(strCont, item.val);
				sb.append(",").append(strCont);
				break;
			case ConstantType.Type_Reward_EXP:
				strCont = MapEx.getString(map, LanguageJson.Mail_ms_exp);
				strCont = StrEx.fmt(strCont, item.val);
				sb.append(",").append(strCont);
				break;
			case ConstantType.Type_Reward_GOLD:
				strCont = MapEx.getString(map, LanguageJson.Mail_ms_gold);
				strCont = StrEx.fmt(strCont, item.val);
				sb.append(",").append(strCont);
				break;
			case ConstantType.Type_Reward_HERO:
				strName = MapEx.getString(map, LanguageJson.Hero_gid_
						+ item.gid);
				strCont = MapEx.getString(map, LanguageJson.Mail_ms_hero);
				strCont = StrEx.fmt(strCont, strName, item.val);
				sb.append(",").append(strCont);
				break;
			case ConstantType.Type_Reward_OIL:
				strCont = MapEx.getString(map, LanguageJson.Mail_ms_oil);
				strCont = StrEx.fmt(strCont, item.val);
				sb.append(",").append(strCont);
				break;
			case ConstantType.Type_Reward_SHIP:
				strName = MapEx.getString(map, LanguageJson.Ship_gid_
						+ item.gid);
				strCont = MapEx.getString(map, LanguageJson.Mail_ms_ship);
				strCont = StrEx.fmt(strCont, strName, item.val);
				sb.append(",").append(strCont);
				break;
			case ConstantType.Type_Reward_SPELL:
				strName = MapEx.getString(map, LanguageJson.Spell_gid_
						+ item.gid);
				strCont = MapEx.getString(map, LanguageJson.Mail_ms_spell);
				strCont = StrEx.fmt(strCont, strName, item.val);
				sb.append(",").append(strCont);
				break;
			case ConstantType.Type_Reward_WORKER:
				strCont = MapEx.getString(map, LanguageJson.Mail_ms_worker);
				strCont = StrEx.fmt(strCont, item.val);
				sb.append(",").append(strCont);
				break;
			case ConstantType.Type_Reward_Energy:
				strName = MapEx.getString(map, LanguageJson.Energy_gid_
						+ item.gid);
				strCont = MapEx.getString(map, LanguageJson.Mail_ms_energy);
				strCont = StrEx.fmt(strCont, strName, item.val);
				sb.append(",").append(strCont);
				break;
			}
		}

		String reward = sb.toString();
		return reward;
	}

	// =================== 发送奖励 客户端

	private static void reward(Player p, NRewards lists, String rewardTit) {
		if (lists == null || lists.lists == null || lists.lists.size() == 0)
			return;
		boolean isPlayer = p != null;
		for (NReward item : lists.lists) {
			if (isPlayer) {
				rewardPlayer(p, item);
			} else {
				rewardAllPlayer(item);
			}
		}
		recordRewardInfo(p, lists, rewardTit);
	}

	static void rewardAllPlayer(NReward item) {

	}

	static void rewardPlayer(Player p, NReward item) {
		if (p == null || item == null)
			return;
		int type_ = item.type;
		boolean isChangePlayer_ = (type_ == ConstantType.Type_Reward_EXP
				|| type_ == ConstantType.Type_Reward_DIAM
				|| type_ == ConstantType.Type_Reward_GOLD
				|| type_ == ConstantType.Type_Reward_OIL || type_ == ConstantType.Type_Reward_WORKER);
		switch (type_) {
		case ConstantType.Type_Reward_EXP:
			int exp = p.getExp();
			exp += item.val;
			p.setExp(exp);
			break;
		case ConstantType.Type_Reward_DIAM:
			if (item.val < 60) {
				int cry_ = p.getCrystal();
				cry_ += item.val;
				p.setCrystal(cry_);
			}
			break;
		case ConstantType.Type_Reward_GOLD:
			int gold_ = p.getStored_gold();
			gold_ += item.val;
			p.setStored_gold(gold_);
			break;
		case ConstantType.Type_Reward_OIL:
			int oil_ = p.getStored_oil();
			oil_ += item.val;
			p.setStored_oil(oil_);
			break;
		case ConstantType.Type_Reward_WORKER:
			int num = LogicPlayer.changeWokerNumNotUp(p, item.val);
			p.setMaxbonum(num);
			break;
		case ConstantType.Type_Reward_SPELL:
			LogicPlayer.addPlayerSpell(p, item.gid, item.val);
			break;
		case ConstantType.Type_Reward_HERO:
			rewardHero(p, item);
			break;
		case ConstantType.Type_Reward_SHIP:
			rewardShip(p, item);
			break;
		case ConstantType.Type_Reward_Energy:
			LogicPlayer.changeEnergyNum(p, item.gid, item.val);
			break;
		default:
			break;
		}
		if (isChangePlayer_) {
			PlayerEntity.updatePlayer(p);
		}
	}

	private static void rewardHero(Player p, NReward item) {
		LogicHero.createHero(p, item.gid, LogicHero.Gem_LandDragon);
	}

	private static void rewardShip(Player p, NReward item) {
		if (p == null)
			return;
		int num = item.val;
		int gid = item.gid;
		int bcid = item.bcid;
		Player_buildings p_b = LogicBuild.getBuildArsenalInData(p, bcid);
		LogicArmy.addOrNewArmy(p_b, gid, num);
	}

	private static void recordRewardInfo(Player p, NRewards lists,
			String rewardTit) {
		if (lists == null || lists.lists == null || lists.lists.size() <= 0)
			return;

		String strChn = ChannelCfg.DEMO;
		if (p != null)
			strChn = p.getChannel();

		String strReward = getStrInfoByRewards(rewardTit, lists, strChn);

		recordRewardInfo(p, strReward);
	}

	static void recordRewardInfo(Player p, String reward) {
		int type = 1;
		int pcid = 0;
		String pname = "";

		if (p != null) {
			pcid = p.getPcid();
			pname = p.getPname();
			type = 0;
		}

		RewardEntity.createNewInsert(pcid, pname, type, reward);
	}

	static void sendMailRewardToPlayer(Player p, String tit, String cont) {
		LogicMail.sendSystemMailToPlayer(p, tit, cont);
	}

	// 个人上升排行榜奖励
	private static String getContentRewardRankPlayer(int rank, int gem,
			String strChn) {
		String r = "";
		String key = LanguageJson.Mail_ms_toppl;
		r = LanguageJson.getStrValByChn(strChn, key);
		r = StrEx.fmt(r, rank, gem);
		return r;
	}

	public static void sendRewardRankWeekPlayer() {
		List<Rank_player> list = Rank_playerEntity.getListRankByWeek(1);
		if (list == null)
			return;

		boolean isNewReward = CfgJson.isNewReward();

		for (Rank_player item : list) {
			int pcid = item.getPcid();
			int rank = item.getCurrank();
			if (rank > 10)
				break;

			Player p = PlayerEntity.getPlayer(pcid);
			if (p == null)
				continue;

			int val = 0;
			if (isNewReward) {
				val = FormulaGame.getRewardGemByTopPlayerWeekNew(rank);
			} else {
				val = FormulaGame.getRewardGemByTopPlayerWeek(rank);
			}

			p.changeCrystal(val);
			PlayerEntity.updatePlayer(p);

			String strChn = p.getChannel();

			String cont = getContentRewardRankPlayer(rank, val, strChn);

			String tit = LanguageJson.getStrValByChn(strChn,
					LanguageJson.Mail_title_toppl);

			sendMailRewardToPlayer(p, tit, cont);

			LogicalSession.refreshPInfo(pcid);

			recordRewardInfo(p, cont);
		}
	}

	// 个人总排行榜奖励
	public static void sendRewardRankAllPlayer() {
		List<Rank_player> list = Rank_playerEntity.getListRankByAll(1);
		if (list == null)
			return;
		for (Rank_player item : list) {
			int pcid = item.getPcid();
			int rank = item.getCurrank();
			if (rank > 5)
				break;

			Player p = PlayerEntity.getPlayer(pcid);
			if (p == null)
				continue;

			int val = FormulaGame.getRewardGemByTopPlayerAll(rank);
			p.changeCrystal(val);
			PlayerEntity.updatePlayer(p);

			String strChn = p.getChannel();

			String cont = getContentRewardRankPlayer(rank, val, strChn);

			String tit = LanguageJson.getStrValByChn(strChn,
					LanguageJson.Mail_title_toppl2);

			sendMailRewardToPlayer(p, tit, cont);

			LogicalSession.refreshPInfo(pcid);

			recordRewardInfo(p, cont);
		}
	}

	// 联盟榜奖励
	private static String getContentRewardRankWeekClan(int clanRank, int rank,
			int gem, String strChn) {
		String r = "";
		String key = LanguageJson.Mail_ms_topcl;
		r = LanguageJson.getStrValByChn(strChn, key);
		r = StrEx.fmt(r, clanRank, rank, gem);
		return r;
	}

	public static void sendRewardRankWeekClan() {
		List<Rank_clan> list = Rank_clanEntity.getListRankByWeek(1);
		if (list == null)
			return;
		for (Rank_clan item : list) {
			int rank = item.getCurrank();
			if (rank > 3)
				break;
			String ccid = item.getCcid();
			sendRewardRankWeekClanForMember(ccid, rank);
		}
	}

	private static void sendRewardRankWeekClanForMember(String ccid,
			int rankClan) {
		List<Clan_member> list = Clan_memberEntity.getListInClanWeek(ccid);
		if (list == null || list.isEmpty())
			return;
		int index = 1;
		for (Clan_member item : list) {
			int pcid = item.getPcid();
			Player p = PlayerEntity.getPlayer(pcid);
			if (p == null)
				continue;

			int gem = FormulaGame.getRewardGemByTopClan(rankClan, index);
			p.changeCrystal(gem);
			PlayerEntity.updatePlayer(p);

			String strChn = p.getChannel();
			String cont = getContentRewardRankWeekClan(rankClan, index, gem,
					strChn);
			String tit = LanguageJson.getStrValByChn(strChn,
					LanguageJson.Mail_title_topcl);

			sendMailRewardToPlayer(p, tit, cont);

			LogicalSession.refreshPInfo(pcid);

			recordRewardInfo(p, cont);

			index++;
		}
	}

	// ======== 每天登录奖励
	static NRewards getDayLoginRewards(int dayLg, int bcid) {
		NRewards nrewards = new NRewards();
		nrewards.lists = new ArrayList<NReward>();
		DBActivity entity = DBActivity.getDBActivity(dayLg);
		if (entity == null)
			return nrewards;

		if (entity.GoldAward > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_GOLD;
			item.val = entity.GoldAward;
			nrewards.lists.add(item);
		}

		if (entity.OilAward > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_OIL;
			item.val = entity.OilAward;
			nrewards.lists.add(item);
		}

		if (entity.GemAward > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_DIAM;
			item.val = entity.GemAward;
			nrewards.lists.add(item);
		}

		if (entity.ShipIDAward > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_SHIP;
			item.gid = entity.ShipIDAward;
			item.val = entity.ShipNumAward;
			item.bcid = bcid;
			nrewards.lists.add(item);
		}

		if (entity.BuilderAward > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_WORKER;
			item.val = entity.BuilderAward;
			nrewards.lists.add(item);
		}

		if (entity.Skill1Award > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_SPELL;
			item.gid = 3000;
			item.val = entity.Skill1Award;
			nrewards.lists.add(item);
		}

		if (entity.Skill2Award > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_SPELL;
			item.gid = 3001;
			item.val = entity.Skill2Award;
			nrewards.lists.add(item);
		}

		if (entity.Skill3Award > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_SPELL;
			item.gid = 3002;
			item.val = entity.Skill3Award;
			nrewards.lists.add(item);
		}

		if (entity.PieceDamage > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_Energy;
			item.gid = 2;
			item.val = entity.PieceDamage;
			nrewards.lists.add(item);
		}

		if (entity.PieceAttackSpeed > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_Energy;
			item.gid = 3;
			item.val = entity.PieceAttackSpeed;
			nrewards.lists.add(item);
		}

		if (entity.PieceHitpoints > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_Energy;
			item.gid = 1;
			item.val = entity.PieceHitpoints;
			nrewards.lists.add(item);
		}

		if (entity.HeroIDAward > 0) {
			NReward item = new NReward();
			item.type = ConstantType.Type_Reward_HERO;
			item.gid = entity.HeroIDAward;
			item.val = 1;
			nrewards.lists.add(item);
		}

		return nrewards;
	}

	public static void dayLoginReward(Player p, NRewards lists) {
		if (p == null)
			return;
		boolean isRDay = p.getIsrewardday();
		if (!isRDay)
			return;

		int bcid = 0;
		if (lists != null && lists.lists != null) {
			for (NReward item : lists.lists) {
				if (item.type == ConstantType.Type_Reward_SHIP) {
					bcid = item.bcid;
					break;
				}
			}
		}

		lists = getDayLoginRewards(p.getLoginday(), bcid);

		LogicPlayer.changeLoginRewardStatus(p);
		String strTit = "每日登录奖励!";
		reward(p, lists, strTit);
	}

	static private NRewards getNRewardsByAchevement(int dbId) {
		NRewards nrewards = new NRewards();
		nrewards.lists = new ArrayList<NReward>();
		DBAchevement entity = DBAchevement.getDBActivity(dbId);
		if (entity == null)
			return nrewards;
		NReward itemExp = new NReward();
		itemExp.type = ConstantType.Type_Reward_EXP;
		itemExp.val = entity.Exp;
		nrewards.lists.add(itemExp);

		NReward itemGem = new NReward();
		itemGem.type = ConstantType.Type_Reward_DIAM;
		itemGem.val = entity.Diamond;
		nrewards.lists.add(itemGem);
		return nrewards;
	}

	// ======== 日常任务奖励
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean dayTasksReward(Player p, int curID, NRewards lists) {
		if (p == null)
			return false;
		String strTasks = p.getDaytasks();
		Map map = UtileTools.strToMap(strTasks);
		String key = curID + "";
		boolean isGet = MapEx.getBoolean(map, key);
		if (isGet)
			return false;
		map.put(key, true);
		strTasks = UtileTools.mapToStr(map);
		p.setDaytasks(strTasks);
		PlayerEntity.updatePlayer(p);
		String strTit = "日常任务奖励!";

		lists = getNRewardsByAchevement(curID);
		reward(p, lists, strTit);
		return true;
	}

	// ======== 成就奖励
	public static void achievementReward(Player p, int curID, NRewards lists) {
		String strTit = "成就奖励!";
		lists = getNRewardsByAchevement(curID);
		reward(p, lists, strTit);
	}

	// 首次充值奖励
	public static void firstRewardPay(Player p, NRewards lists) {
		if (p == null)
			return;
		int status = p.getFirstpaystatus();
		if (status != 1)
			return;
		String strChn = p.getChannel();
		String strTit = LanguageJson.getStrValByChn(strChn,
				LanguageJson.Mail_title_pay2);
		reward(p, lists, strTit);
		LogicPlayer.changeFirstPayWhenReward(p);
		String strCont = getStrInfoByRewards(strTit, lists, strChn);
		sendMailRewardToPlayer(p, strTit, strCont);
	}
}
