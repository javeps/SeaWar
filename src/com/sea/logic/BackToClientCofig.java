package com.sea.logic;

public class BackToClientCofig {
	public static final int Frozen_PLAYER = -10004;// 该号已被封号
	public static final int FAIL_TOKEN = -10003;// 取得Token失败！
	public static final int NONE_HERO = -10002;// 英雄不存在
	public static final int NONE_PLAYER = -10001;// 玩家不存在
	public static final int REQUEST_INVALID = -10000;// 无效的请求
	public static final int ERROR = -9999;// 链接失败，请重新链接！
	public static final int LOGIN_OTHER_MORE = -8000;// 您的帐号在其他地方登录！

	public static final int MORE_PL = -49;// 人员爆满
	public static final int NOT_ENOUGH_WORKER = -48;// 没有足够工人
	
	public static final int Fail_Create_Order = -47;// 创建订单失败
	public static final int Error_LgId_LgPwd = -46;// 帐号或者密码不正确
	public static final int HAS_LgId = -45;// 该帐号已经存在，请重新注册
	public static final int NONE_Clan_Build = -44;// 没有联盟建筑
	public static final int Fail_Rename = -43;// 修改名字失败
	public static final int CLAN_Leave_Time_Enough = -42;// 离开联盟不足24小时
	public static final int CLAN_Disband_Other = -41;// 联盟已解散(其他人操作此联盟时候)
	public static final int CLAN_Donate_Fail = -40;// 捐献资源失败
	public static final int CLAN_Request_Sended = -39;// 请求已经发送了
	public static final int CLAN_NAME_HAS = -38;// 联盟名字已经存在
	public static final int CLAN_Request_BeHandle = -37;// 请求已经处理了
	public static final int CLAN_None_Power_Handle = -36;// 没有处理请求的权限
	public static final int CLAN_Has = -35;// 已有联盟
	public static final int CLAN_Not_Out_Other = -34;// 联盟职位相同，不能剔除玩家
	public static final int CLAN_Not_Same = -33;// 联盟不相同，不能对其经行操作
	public static final int CLAN_Out_U = -32;// 您已被联盟剔除！
	public static final int CLAN_Max_Mun = -31;// 联盟已达最大人数
	public static final int CLAN_Member_None = -30;// 该成员已经离开联盟
	public static final int CLAN_Disband = -29;// 联盟已经解散
	public static final int CLAN_Create_Fail = -28;// 创建联盟失败

	public static final int ONLINE = -27;// 玩家在线!
	public static final int MAX_Energy = -26;// 拥有能源已到最大值，请强化宠物！
	public static final int FAIL_ADD_Energy = -25;// 增加能源失败
	public static final int FAIL_BUY_HERO = -24;// 购买英雄失败
	public static final int NONE_PLAYER_BY_VISIT = -23;// 玩家不存在
	public static final int ATT_MAIL_HIT_BACKED = -22;// 该邮件已反击！！
	public static final int FAIL_BUY_BUILD_ORDER = -21;// 购买建筑序列失败
	public static final int BE_FIGHTING = -20;// 正在被攻击
	public static final int NONE_BUILD = -19;// 玩家的建筑不存在
	public static final int BUILD_TYPE_WRONG = -18;// 建筑类型不对
	public static final int FAIL_MOVE_BUILD = -17;// 玩家建筑移位失败
	public static final int NOT_ENOUGH_RES = -16;// 资源不足
	public static final int FAIL_FINISH_BUILD = -15;// 玩家建筑完成升级失败
	public static final int FAIL_PRODUCT_ARMY = -14;// 造兵失败
	public static final int NOT_ENOUGH_CRYATAL = -13;// 宝石(RMB购买的资源)不足
	public static final int FAIL_RECODE_NPC_INFO = -12;// 记录NPC信息失败(资源也没有加)
	public static final int FAIL_FINISH_PRODUCT_ARMY = -11;// 完成造兵失败
	public static final int FAIL_REDUCE_PRODCUTE_ARMY = -10;// 减少造兵失败
	public static final int FAIL_UP_TECH = -9;// 升级科技失败。
	public static final int FAIL_FINISH_UP_TECH = -8;// 完成升级科技失败。
	public static final int FAIL_FAST_FINISH_PRODUCT_ARMY = -7;// 快速完成造兵失败
	public static final int FAIL_BEGIN_ATTACT = -6;// 开始战斗失败
	public static final int FAIL_REPAIR_TRAP = -5;// 修复陷阱失败
	public static final int NONE_ATT_MAIL = -4;// 战报邮件不存在
	public static final int PLAYER_PROTECTING = -3;// 玩家在保护中
	public static final int NONE_PLAYER_HIT_BACK = -2;// 反击玩家不存在
	public static final int HAS_PLAYER_NAME = -1;// 玩家名字存在
	public static final int SUCCESS = 0;// 成功

	// 取得失败信息
	public static String getMsgStr(final int type) {
		String r2 = "";
		switch (type) {
		case ERROR:
			r2 = "链接失败，请重新链接！";
			break;
		case Frozen_PLAYER:
			r2 = "帐号被封！";
			break;
		case NONE_PLAYER:
			r2 = "玩家不存在！";
			break;
		case NONE_BUILD:
			r2 = "玩家的建筑不存在！";
			break;
		case BUILD_TYPE_WRONG:
			r2 = "建筑类型不对！";
			break;
		case FAIL_MOVE_BUILD:
			r2 = "玩家建筑移位失败！";
			break;
		case NOT_ENOUGH_RES:
			r2 = "资源不足！";
			break;
		case FAIL_FINISH_BUILD:
			r2 = "玩家建筑完成升级失败！";
			break;
		case FAIL_PRODUCT_ARMY:
			r2 = "造兵失败！";
			break;
		case NOT_ENOUGH_CRYATAL:
			r2 = "宝石不足！";
			break;
		case FAIL_RECODE_NPC_INFO:
			r2 = "记录NPC信息失败(资源也没有加)！";
			break;
		case FAIL_FINISH_PRODUCT_ARMY:
			r2 = "完成造兵失败！";
			break;
		case FAIL_REDUCE_PRODCUTE_ARMY:
			r2 = "减少造兵失败！";
			break;
		case FAIL_UP_TECH:
			r2 = "升级科技失败！";
			break;
		case FAIL_FINISH_UP_TECH:
			r2 = "完成升级科技失败！";
			break;
		case FAIL_FAST_FINISH_PRODUCT_ARMY:
			r2 = "快速完成造兵失败！";
			break;
		case FAIL_BEGIN_ATTACT:
			r2 = "开始战斗失败,战报错误！";
			break;
		case FAIL_REPAIR_TRAP:
			r2 = "修复陷阱失败！";
			break;
		case NONE_ATT_MAIL:
			r2 = "该战报邮件不存在！";
			break;
		case ATT_MAIL_HIT_BACKED:
			r2 = "该战报邮件已反击过！";
			break;
		case PLAYER_PROTECTING:
			r2 = "玩家在保护中！";
			break;
		case NONE_PLAYER_HIT_BACK:
			r2 = "反击玩家不存在！";
			break;
		case HAS_PLAYER_NAME:
			r2 = "名字已经存在！";
			break;
		case FAIL_BUY_BUILD_ORDER:
			r2 = "购买建筑序列失败!!";
			break;
		case REQUEST_INVALID:
			r2 = "无效的请求!";
			break;
		case NONE_PLAYER_BY_VISIT:
			r2 = "访问的玩家不存在！";
			break;
		case BE_FIGHTING:
			r2 = "您目前正被其他玩家攻击！";
			break;
		case FAIL_BUY_HERO:
			r2 = "购买英雄失败!";
			break;
		case NONE_HERO:
			r2 = "英雄不存在!";
			break;
		case FAIL_ADD_Energy:
			r2 = "增加已经到最大值!";
			break;
		case MAX_Energy:
			r2 = "拥有能源已到最大值，请强化宠物！";
		case FAIL_TOKEN:
			r2 = "取得Token失败！";
			break;
		case ONLINE:
			r2 = "玩家在线！";
			break;
		case LOGIN_OTHER_MORE:
			r2 = "您的帐号在其他地方登录！";
			break;
		default:
			break;
		}
		return r2;
	}
}
