package com.sea.proto;

import static com.bowlong.netty.bio2g.B2G.DATA;
import static com.bowlong.netty.bio2g.B2G.SERVER;

import java.util.List;

import com.bowlong.io.FileEx;
import com.bowlong.netty.bio2g.B2Class;
import com.bowlong.netty.bio2g.B2Field;
import com.bowlong.netty.bio2g.B2G;
import com.bowlong.netty.bio2g.B2Method;
import com.toosNets.netty.map.B2Param;
import com.toosNets.netty.map.Bio2GCSharp;
import com.toosNets.netty.map.Bio2GJava;

@B2Class(namespace = "serv")
public class ServiceClientBuilder {
	// =================== 常量
	@B2Class(type = B2G.DATA, remark = "常量", constant = true)
	class ConstantType {
		@B2Field(remark = "签名迷匙", def = "coolape")
		public String Type_Sign_Key;
		@B2Field(remark = "油", def = "o")
		public String Type_Res_Oil;
		@B2Field(remark = "水晶", def = "c")
		public String Type_Res_Crystal;
		@B2Field(remark = "金币", def = "g")
		public String Type_Res_Gold;

		@B2Field(remark = "兵", def = "1")
		public int Type_Att_Charater;
		@B2Field(remark = "兄弟盟", def = "2")
		public int Type_Att_Clan;
		@B2Field(remark = "药水", def = "3")
		public int Type_Att_Spell;
		@B2Field(remark = "英雄", def = "4")
		public int Type_Att_Hero;

		@B2Field(remark = "公聊", def = "1")
		public int Type_Chat_Pub;
		@B2Field(remark = "私聊", def = "2")
		public int Type_Chat_Pri;
		@B2Field(remark = "联盟聊", def = "3")
		public int Type_Chat_Clan;

		@B2Field(remark = "系统邮件", def = "1")
		public int Type_Mail_System;
		@B2Field(remark = "玩家邮件", def = "2")
		public int Type_Mail_Player;
		@B2Field(remark = "客服邮件", def = "3")
		public int Type_Mail_Server;
		@B2Field(remark = "系统与玩家通信邮件", def = "4")
		public int Type_Mail_System_Player;

		@B2Field(remark = "奖励经验", def = "1")
		public int Type_Reward_EXP;
		@B2Field(remark = "奖励砖石", def = "2")
		public int Type_Reward_DIAM;
		@B2Field(remark = "奖励金币", def = "3")
		public int Type_Reward_GOLD;
		@B2Field(remark = "奖励油", def = "4")
		public int Type_Reward_OIL;
		@B2Field(remark = "奖励舰船", def = "5")
		public int Type_Reward_SHIP;
		@B2Field(remark = "奖励工人", def = "6")
		public int Type_Reward_WORKER;
		@B2Field(remark = "奖励药水技能", def = "7")
		public int Type_Reward_SPELL;
		@B2Field(remark = "奖励英雄", def = "8")
		public int Type_Reward_HERO;
		@B2Field(remark = "能源片", def = "9")
		public int Type_Reward_Energy;

		@B2Field(remark = "一般玩家", def = "0")
		public int Type_User_Normal;
		@B2Field(remark = "NPC玩家", def = "1")
		public int Type_User_NPC;
		@B2Field(remark = "游戏GM", def = "2")
		public int Type_User_GM;

		@B2Field(remark = "英雄正常", def = "0")
		public int Type_Hero_Live;
		@B2Field(remark = "英雄已死亡", def = "1")
		public int Type_Hero_Dead;

		@B2Field(remark = "联盟成员职位--创建者", def = "0")
		public int Type_ClanMember_Admin;
		@B2Field(remark = "联盟成员职位--长老", def = "1")
		public int Type_ClanMember_Elders;
		@B2Field(remark = "联盟成员职位--成员", def = "2")
		public int Type_ClanMember_Normal;
	}

	// ///////////////////////////////////////////////////
	@B2Class(type = DATA, remark = "boolVal")
	class NBool {
		public boolean val;
	}

	@B2Class(type = DATA, remark = "int list")
	class NInts {
		public List<NInt> intes;
	}

	@B2Class(type = DATA, remark = "intVal")
	class NInt {
		public int val;
	}

	@B2Class(type = DATA, remark = "longVal")
	class NLong {
		public long val;
	}

	@B2Class(type = DATA, remark = "stringVal")
	class NStrVal {
		public String val;
	}

	@B2Class(type = DATA, remark = "stringListVal")
	class NStrings {
		public List<String> val;
	}

	@B2Class(type = DATA, remark = "doubleVal")
	class NDouble {
		public double val;
	}

	@B2Class(type = DATA, remark = "session")
	class NSession {
		public int sessionKey;
	}

	@B2Class(type = DATA, remark = "返回值")
	class ReturnStatus {
		public int succ;
		long dt;
		public String msg;
	}

	@B2Class(type = DATA, remark = "用户对象")
	class NUser {
		int id;
		String uid;
		String uuid;
		String uname;
		int type;
		int state;
		long logtin_time;
		String model;// 机型
		String channel;
		String version;
		String remain;
	}

	@B2Class(type = DATA, remark = "用户-角色对象列表")
	class NPlayers {
		public List<NPlayer> uplayers;
	}

	@B2Class(type = DATA, remark = "用户-角色对象")
	class NPlayer {
		int pid;
		int uid;
		int type;
		int serverId;
		String pname;
		int icon;
		int lvl;
		long crystal;// 水晶
		int renown;// 声望值
		int cur_npc_local_id;
		String npcs;// 打了的NPC
		int all_pay_momey;
		int last_pay_money;
		long last_pay_time;
		int guid_step;
		String clancid;
		String cname;
		int cicon;
		int maxBuidId;// 建筑的客户端最大ID
		int maxObstId;// 最大的障碍ID
		long stored_oil;
		long stored_gold;
		int maxAttMailId;// 客服端最大战报ID
		long protectTime;
		long sys_time;
		int maxOrderNum;// 最大队列数量
		@B2Field(remark = "最后一次增加障碍时间")
		long lastAddObst;
		@B2Field(remark = "个人排行奖励时间点")
		long rankRewardTime;
		@B2Field(remark = "连续登录天数")
		int dayLogin;
		@B2Field(remark = "是否已经领奖")
		boolean isReward;
		@B2Field(remark = "首次充值领奖状态1表示成功未领奖")
		int firstPayStatus;
		@B2Field(remark = "生命之源")
		int pieceHPNum;
		@B2Field(remark = "攻击之源")
		int pieceAttNum;
		@B2Field(remark = "速度之源")
		int pieceSpeedNum;
		@B2Field(remark = "当天已领任务")
		String dayTasks;
		@B2Field(remark = "离开联盟的时间")
		long lastLeaveClan;
		@B2Field(remark = "月卡结束时间点")
		long monCode;
	}

	@B2Class(type = DATA, remark = "角色信息")
	class NPInfo {
		int pcid;
		long crystal;// 水晶
		long storedOil;
		long storedGold;
		long protectTime;
		int maxOrderNum;// 最大队列数量
		int firstPayStatus;
	}

	@B2Class(type = DATA, remark = "用户-角色-建筑对象列表")
	class NBuilds {
		public List<NBuild> builds;
	}

	@B2Class(type = DATA, remark = "用户-角色-建筑对象")
	class NBuild {
		int client_id;
		String bname;
		int gid;
		int lvl;
		long cooldown;
		int pos_index;
		int state;
		int type;
		long cur_pro_res;
		long pro_res_beg_time;
		int cur_up_tech_gid;
		long end_tech_up_time;
		long begin_army_time;
		long end_army_time;
	}

	@B2Class(type = DATA, remark = "用户-角色-科技对象列表")
	class NTeches {
		public List<NTech> techs;
	}

	@B2Class(type = DATA, remark = "用户-角色-科技对象")
	class NTech {
		int tid;
		String tname;
		int gid;
		int lvl;
	}

	@B2Class(type = DATA, remark = "用户-角色-兵对象列表")
	class NArmys {
		public List<NArmy> armys;
	}

	@B2Class(type = DATA, remark = "用户-角色-兵对象")
	class NArmy {
		int aid;
		String aname;
		int gid;
		int lvl;
		int num;
		int buildClientId;
	}

	@B2Class(type = DATA, remark = "用户-角色-正在造兵对象列表")
	class NProductes {
		public List<NProducte> army_productings;
	}

	@B2Class(type = DATA, remark = "用户-角色-正在造兵对象")
	class NProducte {
		int pid;
		int gid;
		int building_id;
		int num;
	}

	@B2Class(type = DATA, remark = "聊天内容对象集合")
	class NChats {
		List<NChat> lists;
	}

	@B2Class(type = DATA, remark = "聊天内容对象")
	class NChat {
		int ncid;
		int uuid;
		int type;
		String content;
		// byte[] sound;
		String toPName;
		String fromPName;
		long createTime;
		// int soundSize;
	}

	@B2Class(type = DATA, remark = "法术对象集合")
	class NSpells {
		List<NSpell> lists;
	}

	@B2Class(type = DATA, remark = "法术对象")
	class NSpell {
		int gid;
		int num;
	}

	@B2Class(type = DATA, remark = "成就对象集合")
	class NAchievements {
		List<NAchievement> lists;
	}

	@B2Class(type = DATA, remark = "成就对象")
	class NAchievement {
		int gid;
		int localId;
		@B2Field(remark = "当前完成值")
		int val;
		@B2Field(remark = "是否领取过奖励")
		boolean isDraw;
	}

	@B2Class(type = DATA, remark = "奖励集合")
	class NRewards {
		List<NReward> lists;
	}

	@B2Class(type = DATA, remark = "奖励对象")
	class NReward {
		@B2Field(remark = "类型")
		int type;
		@B2Field(remark = "奖励值")
		int val;
		@B2Field(remark = "gid")
		int gid;
		@B2Field(remark = "等级")
		int lvl;
		@B2Field(remark = "建筑的client")
		int bcid;
	}

	@B2Class(type = DATA, remark = "被打过了的NPC集合")
	class NNPCBeFighteds {
		List<NNPCBeFighted> lists;
	}

	@B2Class(type = DATA, remark = "被打过了的NPC")
	class NNPCBeFighted {
		int ncid;
		int star;
		@B2Field(remark = "被掠夺了的金币资源值")
		int gold;
		@B2Field(remark = "被掠夺了的油币资源值")
		int oil;
	}

	// ================================战斗的数据 begin =====================
	@B2Class(type = DATA, remark = "战斗信息列表对象")
	class NAttackInfos {
		public List<NAttackInfo> infos;
	}

	@B2Class(type = DATA, remark = "战斗信息对象")
	class NAttackInfo {
		int type;
		int bcid;
		int gid;
		int lvl;// 当时兵等级
		int num;// 兵数量
		double x;
		double y;
		long diffTime;// 从开始时间到放这个兵的时间差(ms);
	}

	@B2Class(type = DATA, remark = "能源集合")
	class NEnergys {
		List<NEnergy> list;
	}

	@B2Class(type = DATA, remark = "能源")
	class NEnergy {
		@B2Field(remark = "GID")
		int egid;
		@B2Field(remark = "数量")
		int num;
	}

	@B2Class(type = DATA, remark = "战斗建筑list对象")
	class NBuildBlast {
		int beAttPid;
		public List<Integer> bcids;
		public List<Integer> bgids;
	}

	@B2Class(type = DATA, remark = "战报邮件列表")
	class NAttMails {
		List<NAttMail> attMails;
	}

	@B2Class(type = DATA, remark = "战报邮件")
	class NAttMail {
		int mid;
		String mcid;
		int attPid;
		String attName;
		String clancid;
		String clanName;
		int clanIcon;
		int preRenown;
		int attGold;
		int attOil;
		int star;
		int attRenown;
		boolean isHitBack;
		long createTime;
		long endTime;
		NAttackInfos attInfo;
		int egid;
		int num;
	}

	@B2Class(type = DATA, remark = "邮件列表")
	class NMails {
		List<NMail> lists;
	}

	@B2Class(type = DATA, remark = "邮件")
	class NMail {
		int mid;
		int type;
		boolean isRead;
		String title;
		String content;
		String toPName;
		String fromPName;
		long createTime;
	}

	@B2Class(type = DATA, remark = "个人排名列表")
	class NRankUsers {
		List<NRankUser> lists;
	}

	@B2Class(type = DATA, remark = "个人排名")
	class NRankUser {
		int pid;
		String pname;
		int pexp;
		String clancid;
		int clanIcon;
		String clanName;
		int rankIndex;
		int renown;
		int weekRenown;
		@B2Field(remark = "0周1总")
		int type;
	}

	@B2Class(type = DATA, remark = "拥有英雄列表")
	class NHeros {
		List<NHero> lists;
	}

	@B2Class(type = DATA, remark = "拥有英雄")
	class NHero {
		int hcid;
		int hgid;
		int hp;
		int maxHp;
		int damage;
		int maxDamage;
		int speed;
		int maxSpeed;
		int status;
		long deadTime;
		int skillGid;
		@B2Field(remark = "战斗开始的位置")
		int fightPos;
	}

	@B2Class(type = DATA, remark = "联盟集合")
	class NClans {
		List<NClan> list;
	}

	@B2Class(type = DATA, remark = "联盟")
	class NClan {
		@B2Field(remark = "联盟唯一标识")
		String ccid;
		String cname;
		int icon;
		String desc;
		int maxNum;
		int curNum;
		long createTime;
		@B2Field(remark = "增加点数-血量")
		int pointHP;
		@B2Field(remark = "增加点数-攻击力")
		int pointAtt;

		@B2Field(remark = "血量百分比/点")
		int eachHP;
		@B2Field(remark = "攻击百分比/点")
		int eachAtt;
		@B2Field(remark = "所捐金币")
		long curGold;
		@B2Field(remark = "所捐油")
		long curOil;
		@B2Field(remark = "下一等级点数所需金币")
		long maxGold;
		@B2Field(remark = "下一等级点数所需油")
		long maxOil;
	}

	@B2Class(type = DATA, remark = "联盟成员集合")
	class NClanMembers {
		List<NClanMember> list;
	}

	@B2Class(type = DATA, remark = "联盟成员")
	class NClanMember {
		@B2Field(remark = "联盟唯一标识")
		String ccid;
		int pcid;
		String pname;
		int post;
		int donateGold;
		int donateOil;
		int curDGold;
		int curDOil;
		long lastDGold;
		long lastDOil;
		int renownAll;
		int renownWeek;
	}

	@B2Class(type = DATA, remark = "联盟请求集合")
	class NClanRequests {
		List<NClanRequest> list;
	}

	@B2Class(type = DATA, remark = "联盟请求")
	class NClanRequest {
		@B2Field(remark = "联盟唯一标识")
		String ccid;
		int pcid;
		String pname;
		int renown;
	}

	@B2Class(type = DATA, remark = "联盟排行榜集合")
	class NRankClans {
		List<NRankClan> list;
	}

	@B2Class(type = DATA, remark = "联盟排行榜")
	class NRankClan {
		@B2Field(remark = "联盟唯一标识")
		String ccid;
		int icon;
		String cname;
		int currank;
		int renownAll;
		int renownWeek;
		@B2Field(remark = "0周1总")
		int type;
		int curnum;
		int maxnum;
	}

	// ================================战斗的数据 end =====================

	@B2Class(type = SERVER)
	interface SeaWarServiceI {
		// ///////////////////////////////服务器端实现///////////////////////////////////
		@B2Method(type = SERVER, params = { "uid", "uuid", "pwd", "name",
				"channel", "model", "version", "servid", "sign", "players" }, remark = "登录玩家")
		ReturnStatus loginUserByUid(String uid, String uuid, String pwd,
				String name, String channel, String model, String version,
				int servid,
				@B2Param(oType = "NPlayers", isOut = true) NPlayers players);

		@B2Method(type = SERVER, params = { "SUPID", "session", "player",
				"npcs", "builds", "teches", "armys", "army_produtes",
				"nspells", "heros" }, remark = "玩家角色")
		ReturnStatus loginUPlayer(
				int SUPID,
				@B2Param(oType = "NSession", isOut = true) NSession session,
				@B2Param(oType = "NPlayer", isOut = true) NPlayer player,
				@B2Param(oType = "NNPCBeFighteds", isOut = true) NNPCBeFighteds npcs,
				@B2Param(oType = "NBuilds", isOut = true) NBuilds builds,
				@B2Param(oType = "NTeches", isOut = true) NTeches teches,
				@B2Param(oType = "NArmys", isOut = true) NArmys armys,
				@B2Param(oType = "NProductes", isOut = true) NProductes army_produtes,
				@B2Param(oType = "NSpells", isOut = true) NSpells nspells,
				@B2Param(oType = "NHeros", isOut = true) NHeros heros);

		@B2Method(type = SERVER, params = { "client_id", "gid", "pos",
				"resType", "costVal", "crystal", "cooldown_ms", "sign" }, remark = "新增建筑")
		ReturnStatus createBuilding(int client_id, int gid, int pos,
				String resType, int costVal, int crystal, long cooldown_ms,
				String sign);

		@B2Method(type = SERVER, params = { "client_id", "resType", "costVal",
				"crystal", "cooldown_ms", "sign" }, remark = "升级建筑")
		ReturnStatus upBuilding(int client_id, String resType, int costVal,
				int crystal, long cooldown_ms, String sign);

		@B2Method(type = SERVER, params = { "client_id", "crystal", "sign" }, remark = "完成升级建筑")
		ReturnStatus finishBuild(int client_id, int crystal, String sign);

		@B2Method(type = SERVER, params = { "client_id", "resType", "costVal",
				"crystal", "coowdown", "havType", "havVal", "sign" }, remark = "移除建筑")
		ReturnStatus removeBuild(int client_id, String resType, int costVal,
				int crystal, long coowdown, String havType, int havVal,
				String sign);

		@B2Method(type = SERVER, params = { "client_id", "crystal", "sign" }, remark = "完成移除")
		ReturnStatus finishRemoveBuild(int client_id, int crystal, String sign);

		@B2Method(type = SERVER, params = { "client_id", "pos" }, remark = "移动建筑")
		ReturnStatus moveBuild(int client_id, int pos);

		@B2Method(type = SERVER, params = { "b_client_id", "resType", "harVal",
				"sign" }, remark = "收资源")
		ReturnStatus harvestRes(int b_client_id, String resType, int harVal,
				String sign);

		@B2Method(type = SERVER, params = { "b_client_id", "gid", "num",
				"resType", "costVal", "crystal", "sign" }, remark = "造兵和减少兵的接口")
		ReturnStatus produceArmy(int b_client_id, int gid, int num,
				String resType, int costVal, int crystal, String sign);

		@B2Method(type = SERVER, params = { "bcid", "lists", "sign" }, remark = "自然完成完成造兵")
		ReturnStatus finishProduceArmy(int bcid, NProductes lists, String sign);

		@B2Method(type = SERVER, params = { "bcid", "crystal", "lists", "sign" }, remark = "快速完成完成造兵")
		ReturnStatus fastFinishProduceArmy(int bcid, int crystal,
				NProductes lists, String sign);

		@B2Method(type = SERVER, params = { "b_client_id", "gid", "resType",
				"costVal", "crystal", "upTime", "sign" }, remark = "升级科技")
		ReturnStatus upTechnolgy(int b_client_id, int gid, String resType,
				int costVal, int crystal, int upTime, String sign);

		@B2Method(type = SERVER, params = { "b_client_id", "gid", "crystal",
				"sign" }, remark = "完成升级科技")
		ReturnStatus finishUpTech(int b_client_id, int gid, int crystal,
				String sign);

		@B2Method(type = SERVER, params = { "cnpcid", "energy" }, remark = "pve战利品")
		ReturnStatus pveFightSpoils(int cnpcid,
				@B2Param(oType = "NEnergy", isOut = true) NEnergy energy);

		@B2Method(type = SERVER, params = { "cur_npc_id", "deaths" }, remark = "pve战斗放兵")
		ReturnStatus pveAttackInfo(int cur_npc_id, NAttackInfos deaths);

		@B2Method(type = SERVER, params = { "cur_npc_id", "gold", "oil",
				"npcs", "sign" }, remark = "打pve结果")
		ReturnStatus pveResult(int cur_npc_id, int gold, int oil,
				NNPCBeFighteds npcs, String sign);

		@B2Method(type = SERVER, params = { "resType", "costVal", "crystal",
				"sign", "beAttPlayer", "bePTeches", "bePArmys", "bePBuilds",
				"heros", "nclan", "canGetGold", "canGetOil" }, remark = "一般查询被攻击的角色")
		ReturnStatus findBeAttactPlayer(String resType, int costVal,
				int crystal, String sign,
				@B2Param(oType = "NPlayer", isOut = true) NPlayer beAttPlayer,
				@B2Param(oType = "NTeches", isOut = true) NTeches bePTeches,
				@B2Param(oType = "NArmys", isOut = true) NArmys bePArmys,
				@B2Param(oType = "NBuilds", isOut = true) NBuilds bePBuilds,
				@B2Param(oType = "NHeros", isOut = true) NHeros heros,
				@B2Param(oType = "NClan", isOut = true) NClan nclan,
				@B2Param(oType = "NInt", isOut = true) NInt canGetGold,
				@B2Param(oType = "NInt", isOut = true) NInt canGetOil);

		@B2Method(type = SERVER, params = { "attcid", "isHitBack", "beHeros",
				"energy" }, remark = "开始战斗")
		ReturnStatus beginAttact(int attcid, boolean isHitBack, NHeros beHeros,
				@B2Param(oType = "NEnergy", isOut = true) NEnergy energy);

		@B2Method(type = SERVER, params = { "attcid", "attInfos",
				"offenDeaths", "defenseDeaths" }, remark = "战斗信息")
		ReturnStatus attactInfos(int attcid, NAttackInfos attInfos,
				NAttackInfos offenDeaths, NAttackInfos defenseDeaths);

		@B2Method(type = SERVER, params = { "attcid", "star", "renown",
				"defenceRenown", "attGold", "attOil", "maxGold", "maxOil",
				"attInfos", "bastBuilds", "offenDeaths", "defenseDeaths",
				"sign" }, remark = "结束战斗")
		ReturnStatus endAttact(int attcid, int star, int renown,
				int defenceRenown, int attGold, int attOil, int maxGold,
				int maxOil, NAttackInfos attInfos, NBuildBlast bastBuilds,
				NAttackInfos offenDeaths, NAttackInfos defenseDeaths,
				String sign);

		@B2Method(type = SERVER, params = { "player", "npcs", "builds",
				"teches", "armys", "produtes", "nspells", "heros" }, remark = "回到主基地")
		ReturnStatus goHome(
				@B2Param(oType = "NPlayer", isOut = true) NPlayer player,
				@B2Param(oType = "NNPCBeFighteds", isOut = true) NNPCBeFighteds npcs,
				@B2Param(oType = "NBuilds", isOut = true) NBuilds builds,
				@B2Param(oType = "NTeches", isOut = true) NTeches teches,
				@B2Param(oType = "NArmys", isOut = true) NArmys armys,
				@B2Param(oType = "NProductes", isOut = true) NProductes produtes,
				@B2Param(oType = "NSpells", isOut = true) NSpells nspells,
				@B2Param(oType = "NHeros", isOut = true) NHeros heros);

		@B2Method(type = SERVER, params = { "resType", "buyVal", "crystal",
				"sign", "player" }, remark = "购买资源")
		ReturnStatus buyRes(String resType, int buyVal, int crystal,
				String sign,
				@B2Param(oType = "NPlayer", isOut = true) NPlayer player);

		@B2Method(type = SERVER, params = { "mcid", "player", "teches",
				"amrys", "builds", "attInfo", "timeSlot", "heros", "offHeros",
				"offNclan", "defNclan" }, remark = "战斗回放")
		ReturnStatus replayAttMail(
				String mcid,
				@B2Param(oType = "NPlayer", isOut = true) NPlayer player,
				@B2Param(oType = "NTeches", isOut = true) NTeches teches,
				@B2Param(oType = "NArmys", isOut = true) NArmys amrys,
				@B2Param(oType = "NBuilds", isOut = true) NBuilds builds,
				@B2Param(oType = "SUAttackInfos", isOut = true) NAttackInfos attInfo,
				@B2Param(oType = "NLong", isOut = true) NLong timeSlot,
				@B2Param(oType = "NHeros", isOut = true) NHeros defHeros,
				@B2Param(oType = "NHeros", isOut = true) NHeros offHeros,
				@B2Param(oType = "NClan", isOut = true) NClan offNclan,
				@B2Param(oType = "NClan", isOut = true) NClan defNclan);

		@B2Method(type = SERVER, params = { "mcid", "beAttPlayer", "bePTeches",
				"bePArmys", "builds", "heros", "nclan" }, remark = "反击")
		ReturnStatus hitBack(String mcid,
				@B2Param(oType = "NPlayer", isOut = true) NPlayer beAttPlayer,
				@B2Param(oType = "NTeches", isOut = true) NTeches bePTeches,
				@B2Param(oType = "NArmys", isOut = true) NArmys bePArmys,
				@B2Param(oType = "NBuilds", isOut = true) NBuilds builds,
				@B2Param(oType = "NHeros", isOut = true) NHeros heros,
				@B2Param(oType = "NClan", isOut = true) NClan nclan);

		@B2Method(type = SERVER, params = { "heat" }, remark = "心跳")
		ReturnStatus heart(@B2Param(oType = "NInt", isOut = true) NInt heat);

		@B2Method(type = SERVER, params = { "uname" }, remark = "设置名字")
		ReturnStatus setPlayerName(String uname);

		@B2Method(type = SERVER, params = { "b_c_id", "resType", "costVal",
				"crystal", "sign" }, remark = "修复陷阱")
		ReturnStatus repairTrap(int b_c_id, String resType, int costVal,
				int crystal, String sign);

		@B2Method(type = SERVER, params = {}, remark = "用户下线")
		ReturnStatus downLine();

		@B2Method(type = SERVER, params = { "buyCrystalVal", "sign" }, remark = "购买水晶，内部测试用")
		ReturnStatus buyCrystalForOpenness(int buyCrystalVal, String sign);

		@B2Method(type = SERVER, params = { "guid" }, remark = "新手步骤")
		ReturnStatus guidNewPlayer(int guid);

		@B2Method(type = SERVER, params = { "randomName" }, remark = "随机名称")
		ReturnStatus randomPlayerName(
				@B2Param(oType = "OutStrVal", isOut = true) NStrVal randomName);

		@B2Method(type = SERVER, params = { "newChat" }, remark = "聊天内容")
		ReturnStatus sendChat(NChat newChat);

		@B2Method(type = SERVER, params = { "crystal", "sign" }, remark = "购买序列")
		ReturnStatus buyOrder(int crystal, String sign);

		@B2Method(type = SERVER, params = { "attMails" }, remark = "下发战报邮件")
		ReturnStatus getAttMails(
				@B2Param(oType = "SUAttMails", isOut = true) NAttMails attMails);

		@B2Method(type = SERVER, params = { "nmails" }, remark = "下发邮件")
		ReturnStatus getNMails(
				@B2Param(oType = "NMails", isOut = true) NMails nmails);

		@B2Method(type = SERVER, params = { "nchats" }, remark = "下发聊天信息")
		ReturnStatus getNChats(
				@B2Param(oType = "NChats", isOut = true) NChats nchats);

		@B2Method(type = SERVER, params = { "newMail" }, remark = "新邮件")
		ReturnStatus sendMail(NMail newMail);

		@B2Method(type = SERVER, params = { "guid", "player", "builds" }, remark = "结束引导")
		ReturnStatus finishGuid(int guid,
				@B2Param(oType = "NPlayer", isOut = true) NPlayer player,
				@B2Param(oType = "NBuilds", isOut = true) NBuilds builds);

		@B2Method(type = SERVER, params = { "resType", "costVal", "crystal",
				"gid", "sign" }, remark = "购买法术")
		ReturnStatus buySpell(String resType, int costVal, int crystal,
				int gid, String sign);

		@B2Method(type = SERVER, params = { "addVal", "sign" }, remark = "增加经验")
		ReturnStatus addExp(int addVal, String sign);

		@B2Method(type = SERVER, params = { "localCurId", "localNextId",
				"lists", "sign" }, remark = "成就完成领奖")
		ReturnStatus achievementReward(int localCurId, int localNextId,
				NRewards lists);

		@B2Method(type = SERVER, params = { "ocid", "gid", "pos", "sign" }, remark = "新增障碍")
		ReturnStatus addObst(int ocid, int gid, int pos, String sign);

		@B2Method(type = SERVER, params = { "ocid", "gid", "pos", "resType",
				"costVal", "crystal", "cooldown_ms", "sign" }, remark = "新增障碍城墙")
		ReturnStatus addObstWall(int ocid, int gid, int pos, String resType,
				int costVal, int crystal, long cooldown_ms, String sign);

		@B2Method(type = SERVER, params = { "ocids", "resType", "costVal",
				"crystal", "sign" }, remark = "升级障碍-城墙")
		ReturnStatus upObstWall(NInts ocids, String resType, int costVal,
				int crystal, String sign);

		@B2Method(type = SERVER, params = { "ocid", "resType", "costVal",
				"crystal", "coowdown", "havType", "havVal", "sign" }, remark = "移除障碍")
		ReturnStatus removeObst(int ocid, String resType, int costVal,
				int crystal, long coowdown, String havType, int havVal,
				String sign);

		@B2Method(type = SERVER, params = { "ocid", "crystal", "sign" }, remark = "完成移除障碍")
		ReturnStatus finishRemoveObst(int ocid, int crystal, String sign);

		@B2Method(type = SERVER, params = { "ocid", "pos" }, remark = "移动物体")
		ReturnStatus moveObst(int ocid, int pos);

		@B2Method(type = SERVER, params = { "marks" }, remark = "下发的成就列表")
		ReturnStatus getAchievements(
				@B2Param(oType = "NAchievements", isOut = true) NAchievements marks);

		@B2Method(type = SERVER, params = { "mailId" }, remark = "改变邮件状态为__已读")
		ReturnStatus readNMail(int mailId);

		@B2Method(type = SERVER, params = { "pid", "player", "builds",
				"teches", "armys", "heros" }, remark = "访问别人")
		ReturnStatus viewPlayer(int pid,
				@B2Param(oType = "NPlayer", isOut = true) NPlayer player,
				@B2Param(oType = "NBuilds", isOut = true) NBuilds builds,
				@B2Param(oType = "NTeches", isOut = true) NTeches teches,
				@B2Param(oType = "NArmys", isOut = true) NArmys armys,
				@B2Param(oType = "NHeros", isOut = true) NHeros heros);

		@B2Method(type = SERVER, params = { "crystal", "addTime", "sign" }, remark = "购买保护盾")
		ReturnStatus buyShield(int crystal, long addTime, String sign);

		@B2Method(type = SERVER, params = { "pinfo" }, remark = "取得用户信息")
		ReturnStatus getPInfo(
				@B2Param(oType = "NPInfo", isOut = true) NPInfo pinfo);

		@B2Method(type = SERVER, params = { "lists", "sign" }, remark = "领每日登录奖励")
		ReturnStatus rewardDay(NRewards lists, String sign);

		@B2Method(type = SERVER, params = { "lists", "sign" }, remark = "首次充值奖励")
		ReturnStatus rewardFirstPay(NRewards lists, String sign);

		@B2Method(type = SERVER, params = { "hgid", "crystal", "sign" }, remark = "购买英雄")
		ReturnStatus buyHero(int hgid, int crystal, String sign);

		@B2Method(type = SERVER, params = { "hgid", "crystal", "sign" }, remark = "买活已死英雄")
		ReturnStatus liveHero(int hgid, int crystal, String sign);

		@B2Method(type = SERVER, params = { "hgid" }, remark = "英雄死亡")
		ReturnStatus deadHero(int hgid);

		@B2Method(type = SERVER, params = { "heors" }, remark = "升级后英雄的数据下发")
		ReturnStatus refreshHeros(
				@B2Param(oType = "NHeros", isOut = true) NHeros heros);

		@B2Method(type = SERVER, params = { "hgid", "egid", "costGold",
				"costOil", "crystal", "sign", "outHgid", "outEgid",
				"curEnergyNum", "addval" }, remark = "增加英雄能源:力量,血量,攻击速度")
		ReturnStatus addHeroEnergy(int hgid, int egid, int costGold,
				int costOil, int crystal, String sign,
				@B2Param(oType = "NInt", isOut = true) NInt outHgid,
				@B2Param(oType = "NInt", isOut = true) NInt outEgid,
				@B2Param(oType = "NInt", isOut = true) NInt curEnergyNum,
				@B2Param(oType = "NInt", isOut = true) NInt addval);

		@B2Method(type = SERVER, params = { "crystal", "num", "sign", "energys" }, remark = "开宝箱")
		ReturnStatus openTreasureBox(int crystal, int num, String sign,
				@B2Param(oType = "NEnergys", isOut = true) NEnergys energys);

		@B2Method(type = SERVER, params = { "strUni", "oriderCid", "phoneInfo" }, remark = "创建充值订单")
		ReturnStatus createOrderPayMoney(String strUni,
				@B2Param(oType = "NStrVal", isOut = true) NStrVal oriderCid,
				String phoneInfo);

		@B2Method(type = SERVER, params = { "localDRID", "lists" }, remark = "每日日常任务奖励")
		ReturnStatus dayTasksReward(int localDRID, NRewards lists);

		@B2Method(type = SERVER, params = { "page", "rankUses" }, remark = "取得个人周排行")
		ReturnStatus getNRankUse(int page,
				@B2Param(oType = "NRankUsers", isOut = true) NRankUsers rankUses);

		@B2Method(type = SERVER, params = { "page", "rankUses" }, remark = "取得个人总排行")
		ReturnStatus getNRankUsersByAll(int page,
				@B2Param(oType = "NRankUsers", isOut = true) NRankUsers rankUses);

		@B2Method(type = SERVER, params = { "icon", "cname", "cdesc",
				"resType", "costVal", "crystal", "ccid" }, remark = "创建联盟")
		ReturnStatus creatClan(int icon, String cname, String cdesc,
				String resType, int costVal, int crystal,
				@B2Param(oType = "NStrVal", isOut = true) NStrVal ccid);

		@B2Method(type = SERVER, params = { "ccid" }, remark = "退出联盟")
		ReturnStatus exitClan(String ccid);

		@B2Method(type = SERVER, params = { "ccid" }, remark = "申请入盟")
		ReturnStatus joinInClan(String ccid);

		@B2Method(type = SERVER, params = { "ccid", "pcid" }, remark = "接受入盟")
		ReturnStatus acceptJoinIn(String ccid, int pcid);

		@B2Method(type = SERVER, params = { "ccid", "pcid" }, remark = "拒绝入盟")
		ReturnStatus refuseJoinIn(String ccid, int pcid);

		@B2Method(type = SERVER, params = { "cname", "clans" }, remark = "查找联盟")
		ReturnStatus searchClan(String cname,
				@B2Param(oType = "NClans", isOut = true) NClans clans);

		@B2Method(type = SERVER, params = { "ccid", "clan", "members" }, remark = "查看联盟")
		ReturnStatus seeClan(
				String ccid,
				@B2Param(oType = "NClan", isOut = true) NClan clan,
				@B2Param(oType = "NClanMembers", isOut = true) NClanMembers members);

		@B2Method(type = SERVER, params = { "clan" }, remark = "取得自己的联盟")
		ReturnStatus getOwnClan(
				@B2Param(oType = "NClan", isOut = true) NClan clan);

		@B2Method(type = SERVER, params = { "members" }, remark = "取得自己联盟成员")
		ReturnStatus getOwnClanMember(
				@B2Param(oType = "NClanMembers", isOut = true) NClanMembers members);

		@B2Method(type = SERVER, params = { "clanreqes" }, remark = "取得自己联盟请求")
		ReturnStatus getOwnClanRequest(
				@B2Param(oType = "NClanRequests", isOut = true) NClanRequests clanreqes);

		@B2Method(type = SERVER, params = { "pcid" }, remark = "踢出联盟玩家")
		ReturnStatus outClanMember(int pcid);

		@B2Method(type = SERVER, params = { "desc" }, remark = "修改联盟描述")
		ReturnStatus changeClan(String desc);

		@B2Method(type = SERVER, params = { "pcid", "post" }, remark = "设置职位")
		ReturnStatus allotClanPost(int pcid, int post);

		@B2Method(type = SERVER, params = { "dgold", "curval", "crystal" }, remark = "捐献金币")
		ReturnStatus donateClanGold(int dgold, int curval, int crystal);

		@B2Method(type = SERVER, params = { "doil", "curval", "crystal" }, remark = "捐献油")
		ReturnStatus donateClanOil(int doil, int curval, int crystal);

		@B2Method(type = SERVER, params = { "page", "rankClans" }, remark = "取得联盟周排行")
		ReturnStatus getNRankClan(
				int page,
				@B2Param(oType = "NRankClans", isOut = true) NRankClans rankClans);

		@B2Method(type = SERVER, params = { "page", "rankClans" }, remark = "取得联盟总排行")
		ReturnStatus getNRankClanByAll(
				int page,
				@B2Param(oType = "NRankClans", isOut = true) NRankClans rankClans);

		@B2Method(type = SERVER, params = { "crystal", "uname" }, remark = "修改名称")
		ReturnStatus rename(int crystal, String uname);

		@B2Method(type = SERVER, params = { "bcid", "downlvl" }, remark = "改变建筑等级")
		ReturnStatus downBuildLvl(int bcid, int downlvl);

		@B2Method(type = SERVER, params = { "outLgId", "outLgPwd", "chnstr" }, remark = "渠道快速注册")
		ReturnStatus cmRegistFast(
				@B2Param(oType = "NStrVal", isOut = true) NStrVal outLgId,
				@B2Param(oType = "NStrVal", isOut = true) NStrVal outLgPwd,
				String chnstr);

		@B2Method(type = SERVER, params = { "lgId", "lgPwd", "chnstr" }, remark = "渠道注册")
		ReturnStatus cmRegist(String lgId, String lgPwd, String chnstr);

		@B2Method(type = SERVER, params = {}, remark = "用户分享成功")
		ReturnStatus shareSuccess();

		@B2Method(type = SERVER, params = { "resType", "resVal", "sign" }, remark = "打劫商船")
		ReturnStatus lootMerchant(String resType, int resVal, String sign);

		@B2Method(type = SERVER, params = { "strCode", "strToken", "strUID" }, remark = "登录奇虎360取得uid,token")
		ReturnStatus loginQH360(String strCode,
				@B2Param(oType = "NStrVal", isOut = true) NStrVal strToken,
				@B2Param(oType = "NStrVal", isOut = true) NStrVal strUID);

//		@B2Method(type = SERVER, params = { "sid", "strUID" }, remark = "登录25PP")
//		ReturnStatus login25PP(String sid,
//				@B2Param(oType = "NStrVal", isOut = true) NStrVal strUID);
	}

	// ///////////////////////////////////////////////////

	public static void main(String[] args) throws Exception {
		Class<?> c = ServiceClientBuilder.class;
		boolean src = FileEx.exists("src");
		Bio2GJava.b2g(c, src);
		Bio2GCSharp.b2g(c, src);
	}

}