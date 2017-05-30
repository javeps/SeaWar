package gen_b2g.serv;

import java.util.*;

import com.bowlong.util.*;
import com.bowlong.netty.bio2.*;

import gen_b2g.serv.bean.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class CallSeaWarServiceI {

    public static int __pid;
    public TcpChannel chn;
    public CallSeaWarServiceI(TcpChannel chn) {
        this.chn = chn;
    }

    // 修改名称
    public void rename(int crystal, String uname) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -934594754);  // cmd:rename
        _map.put(1047561014, crystal);
        _map.put(111425664, uname);
        chn.send(_map);
    }

    // 登录玩家
    public void loginUserByUid(String uid, String uuid, String pwd, String name, String channel, String model, String version, int servid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1615760901);  // cmd:loginUserByUid
        _map.put(115792, uid);
        _map.put(3601339, uuid);
        _map.put(111421, pwd);
        _map.put(3373707, name);
        _map.put(738950403, channel);
        _map.put(104069929, model);
        _map.put(351608024, version);
        _map.put(-905826383, servid);
        chn.send(_map);
    }

    // 新增建筑
    public void createBuilding(int client_id, int gid, int pos, String resType, int costVal, int crystal, long cooldown_ms, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -508077104);  // cmd:createBuilding
        _map.put(-1904089585, client_id);
        _map.put(102338, gid);
        _map.put(111188, pos);
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(185446138, cooldown_ms);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 完成移除
    public void finishRemoveBuild(int client_id, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 2115707319);  // cmd:finishRemoveBuild
        _map.put(-1904089585, client_id);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 自然完成完成造兵
    public void finishProduceArmy(int bcid, NProductes lists, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 929969162);  // cmd:finishProduceArmy
        _map.put(3018012, bcid);
        _map.put(102982549, lists.toMap());
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 快速完成完成造兵
    public void fastFinishProduceArmy(int bcid, int crystal, NProductes lists, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1629150990);  // cmd:fastFinishProduceArmy
        _map.put(3018012, bcid);
        _map.put(1047561014, crystal);
        _map.put(102982549, lists.toMap());
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // pve战利品
    public void pveFightSpoils(int cnpcid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 444729423);  // cmd:pveFightSpoils
        _map.put(-1355658951, cnpcid);
        chn.send(_map);
    }

    // 一般查询被攻击的角色
    public void findBeAttactPlayer(String resType, int costVal, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1056271730);  // cmd:findBeAttactPlayer
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 购买水晶，内部测试用
    public void buyCrystalForOpenness(int buyCrystalVal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1004167270);  // cmd:buyCrystalForOpenness
        _map.put(-797318351, buyCrystalVal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 随机名称
    public void randomPlayerName() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1723144751);  // cmd:randomPlayerName
        chn.send(_map);
    }

    // 成就完成领奖
    public void achievementReward(int localCurId, int localNextId, NRewards lists) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 62679870);  // cmd:achievementReward
        _map.put(1277892304, localCurId);
        _map.put(1260319417, localNextId);
        _map.put(102982549, lists.toMap());
        chn.send(_map);
    }

    // 完成移除障碍
    public void finishRemoveObst(int ocid, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1176996619);  // cmd:finishRemoveObst
        _map.put(3405295, ocid);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 下发的成就列表
    public void getAchievements() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -31856230);  // cmd:getAchievements
        chn.send(_map);
    }

    // 首次充值奖励
    public void rewardFirstPay(NRewards lists, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1127496743);  // cmd:rewardFirstPay
        _map.put(102982549, lists.toMap());
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 开宝箱
    public void openTreasureBox(int crystal, int num, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1070317554);  // cmd:openTreasureBox
        _map.put(1047561014, crystal);
        _map.put(109446, num);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 创建充值订单
    public void createOrderPayMoney(String strUni, String phoneInfo) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 501903050);  // cmd:createOrderPayMoney
        _map.put(-892005121, strUni);
        _map.put(-1029329092, phoneInfo);
        chn.send(_map);
    }

    // 每日日常任务奖励
    public void dayTasksReward(int localDRID, NRewards lists) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 52059937);  // cmd:dayTasksReward
        _map.put(-1205708780, localDRID);
        _map.put(102982549, lists.toMap());
        chn.send(_map);
    }

    // 取得个人总排行
    public void getNRankUsersByAll(int page) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -858116154);  // cmd:getNRankUsersByAll
        _map.put(3433103, page);
        chn.send(_map);
    }

    // 取得自己联盟成员
    public void getOwnClanMember() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -762930944);  // cmd:getOwnClanMember
        chn.send(_map);
    }

    // 取得自己联盟请求
    public void getOwnClanRequest() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -2029211415);  // cmd:getOwnClanRequest
        chn.send(_map);
    }

    // 捐献金币
    public void donateClanGold(int dgold, int curval, int crystal) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1149482139);  // cmd:donateClanGold
        _map.put(95530692, dgold);
        _map.put(-1349116703, curval);
        _map.put(1047561014, crystal);
        chn.send(_map);
    }

    // 取得联盟总排行
    public void getNRankClanByAll(int page) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -858334864);  // cmd:getNRankClanByAll
        _map.put(3433103, page);
        chn.send(_map);
    }

    // 捐献油
    public void donateClanOil(int doil, int curval, int crystal) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -868356563);  // cmd:donateClanOil
        _map.put(3089134, doil);
        _map.put(-1349116703, curval);
        _map.put(1047561014, crystal);
        chn.send(_map);
    }

    // 取得联盟周排行
    public void getNRankClan(int page) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -940903302);  // cmd:getNRankClan
        _map.put(3433103, page);
        chn.send(_map);
    }

    // 改变建筑等级
    public void downBuildLvl(int bcid, int downlvl) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -2102563434);  // cmd:downBuildLvl
        _map.put(3018012, bcid);
        _map.put(1847174208, downlvl);
        chn.send(_map);
    }

    // 渠道快速注册
    public void cmRegistFast(String chnstr) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1717679356);  // cmd:cmRegistFast
        _map.put(-1361243928, chnstr);
        chn.send(_map);
    }

    // 渠道注册
    public void cmRegist(String lgId, String lgPwd, String chnstr) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1137223808);  // cmd:cmRegist
        _map.put(3318774, lgId);
        _map.put(102889410, lgPwd);
        _map.put(-1361243928, chnstr);
        chn.send(_map);
    }

    // 用户分享成功
    public void shareSuccess() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 2009579300);  // cmd:shareSuccess
        chn.send(_map);
    }

    // 打劫商船
    public void lootMerchant(String resType, int resVal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -827208560);  // cmd:lootMerchant
        _map.put(1096575994, resType);
        _map.put(-934456735, resVal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 登录奇虎360取得uid,token
    public void loginQH360(String strCode) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1761920275);  // cmd:loginQH360
        _map.put(-1882890306, strCode);
        chn.send(_map);
    }

    // 玩家角色
    public void loginUPlayer(int SUPID) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1450193907);  // cmd:loginUPlayer
        _map.put(79263689, SUPID);
        chn.send(_map);
    }

    // 升级建筑
    public void upBuilding(int client_id, String resType, int costVal, int crystal, long cooldown_ms, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 840284783);  // cmd:upBuilding
        _map.put(-1904089585, client_id);
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(185446138, cooldown_ms);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 完成升级建筑
    public void finishBuild(int client_id, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1321151899);  // cmd:finishBuild
        _map.put(-1904089585, client_id);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 移除建筑
    public void removeBuild(int client_id, String resType, int costVal, int crystal, long coowdown, String havType, int havVal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -320303030);  // cmd:removeBuild
        _map.put(-1904089585, client_id);
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(-535950858, coowdown);
        _map.put(699727735, havType);
        _map.put(-1224352956, havVal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 移动建筑
    public void moveBuild(int client_id, int pos) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1041716509);  // cmd:moveBuild
        _map.put(-1904089585, client_id);
        _map.put(111188, pos);
        chn.send(_map);
    }

    // 收资源
    public void harvestRes(int b_client_id, String resType, int harVal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1022537609);  // cmd:harvestRes
        _map.put(894389874, b_client_id);
        _map.put(1096575994, resType);
        _map.put(-1224472120, harVal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 造兵和减少兵的接口
    public void produceArmy(int b_client_id, int gid, int num, String resType, int costVal, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1506041187);  // cmd:produceArmy
        _map.put(894389874, b_client_id);
        _map.put(102338, gid);
        _map.put(109446, num);
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 升级科技
    public void upTechnolgy(int b_client_id, int gid, String resType, int costVal, int crystal, int upTime, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1145738292);  // cmd:upTechnolgy
        _map.put(894389874, b_client_id);
        _map.put(102338, gid);
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(-839315448, upTime);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 完成升级科技
    public void finishUpTech(int b_client_id, int gid, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1455260092);  // cmd:finishUpTech
        _map.put(894389874, b_client_id);
        _map.put(102338, gid);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // pve战斗放兵
    public void pveAttackInfo(int cur_npc_id, NAttackInfos deaths) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1521297515);  // cmd:pveAttackInfo
        _map.put(-512671208, cur_npc_id);
        _map.put(-1335772033, deaths.toMap());
        chn.send(_map);
    }

    // 打pve结果
    public void pveResult(int cur_npc_id, int gold, int oil, NNPCBeFighteds npcs, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 809718812);  // cmd:pveResult
        _map.put(-512671208, cur_npc_id);
        _map.put(3178592, gold);
        _map.put(110034, oil);
        _map.put(3387826, npcs.toMap());
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 开始战斗
    public void beginAttact(int attcid, boolean isHitBack, NHeros beHeros) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -740593574);  // cmd:beginAttact
        _map.put(-1407256963, attcid);
        _map.put(346575504, isHitBack);
        _map.put(-257792714, beHeros.toMap());
        chn.send(_map);
    }

    // 战斗信息
    public void attactInfos(int attcid, NAttackInfos attInfos, NAttackInfos offenDeaths, NAttackInfos defenseDeaths) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -428433996);  // cmd:attactInfos
        _map.put(-1407256963, attcid);
        _map.put(516899684, attInfos.toMap());
        _map.put(674996471, offenDeaths.toMap());
        _map.put(-1601665313, defenseDeaths.toMap());
        chn.send(_map);
    }

    // 结束战斗
    public void endAttact(int attcid, int star, int renown, int defenceRenown, int attGold, int attOil, int maxGold, int maxOil, NAttackInfos attInfos, NBuildBlast bastBuilds, NAttackInfos offenDeaths, NAttackInfos defenseDeaths, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1093931788);  // cmd:endAttact
        _map.put(-1407256963, attcid);
        _map.put(3540562, star);
        _map.put(-934580981, renown);
        _map.put(979331451, defenceRenown);
        _map.put(-676120927, attGold);
        _map.put(-1407276175, attOil);
        _map.put(843728868, maxGold);
        _map.put(-1081154098, maxOil);
        _map.put(516899684, attInfos.toMap());
        _map.put(-876129595, bastBuilds.toMap());
        _map.put(674996471, offenDeaths.toMap());
        _map.put(-1601665313, defenseDeaths.toMap());
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 回到主基地
    public void goHome() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1241398809);  // cmd:goHome
        chn.send(_map);
    }

    // 购买资源
    public void buyRes(String resType, int buyVal, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1377571782);  // cmd:buyRes
        _map.put(1096575994, resType);
        _map.put(-1377568069, buyVal);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 战斗回放
    public void replayAttMail(String mcid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -188503535);  // cmd:replayAttMail
        _map.put(3345713, mcid);
        chn.send(_map);
    }

    // 反击
    public void hitBack(String mcid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 926354202);  // cmd:hitBack
        _map.put(3345713, mcid);
        chn.send(_map);
    }

    // 心跳
    public void heart() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 99151942);  // cmd:heart
        chn.send(_map);
    }

    // 设置名字
    public void setPlayerName(String uname) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1722927346);  // cmd:setPlayerName
        _map.put(111425664, uname);
        chn.send(_map);
    }

    // 修复陷阱
    public void repairTrap(int b_c_id, String resType, int costVal, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 2131720282);  // cmd:repairTrap
        _map.put(-1398532044, b_c_id);
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 用户下线
    public void downLine() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1426859958);  // cmd:downLine
        chn.send(_map);
    }

    // 新手步骤
    public void guidNewPlayer(int guid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1203719192);  // cmd:guidNewPlayer
        _map.put(3184265, guid);
        chn.send(_map);
    }

    // 聊天内容
    public void sendChat(NChat newChat) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1246941952);  // cmd:sendChat
        _map.put(1844699416, newChat.toMap());
        chn.send(_map);
    }

    // 购买序列
    public void buyOrder(int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -998949784);  // cmd:buyOrder
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 下发战报邮件
    public void getAttMails() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1696857871);  // cmd:getAttMails
        chn.send(_map);
    }

    // 下发邮件
    public void getNMails() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 624359620);  // cmd:getNMails
        chn.send(_map);
    }

    // 下发聊天信息
    public void getNChats() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 615325507);  // cmd:getNChats
        chn.send(_map);
    }

    // 新邮件
    public void sendMail(NMail newMail) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1247233375);  // cmd:sendMail
        _map.put(1844990839, newMail.toMap());
        chn.send(_map);
    }

    // 结束引导
    public void finishGuid(int guid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1151145404);  // cmd:finishGuid
        _map.put(3184265, guid);
        chn.send(_map);
    }

    // 购买法术
    public void buySpell(String resType, int costVal, int crystal, int gid, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -995314110);  // cmd:buySpell
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(102338, gid);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 增加经验
    public void addExp(int addVal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1422538308);  // cmd:addExp
        _map.put(-1422522688, addVal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 新增障碍
    public void addObst(int ocid, int gid, int pos, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1148737611);  // cmd:addObst
        _map.put(3405295, ocid);
        _map.put(102338, gid);
        _map.put(111188, pos);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 新增障碍城墙
    public void addObstWall(int ocid, int gid, int pos, String resType, int costVal, int crystal, long cooldown_ms, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -612644065);  // cmd:addObstWall
        _map.put(3405295, ocid);
        _map.put(102338, gid);
        _map.put(111188, pos);
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(185446138, cooldown_ms);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 升级障碍-城墙
    public void upObstWall(NInts ocids, String resType, int costVal, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1662400743);  // cmd:upObstWall
        _map.put(105564260, ocids.toMap());
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 移除障碍
    public void removeObst(int ocid, String resType, int costVal, int crystal, long coowdown, String havType, int havVal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1098415640);  // cmd:removeObst
        _map.put(3405295, ocid);
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        _map.put(-535950858, coowdown);
        _map.put(699727735, havType);
        _map.put(-1224352956, havVal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 移动物体
    public void moveObst(int ocid, int pos) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -104574235);  // cmd:moveObst
        _map.put(3405295, ocid);
        _map.put(111188, pos);
        chn.send(_map);
    }

    // 改变邮件状态为__已读
    public void readNMail(int mailId) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1130309809);  // cmd:readNMail
        _map.put(-1081574094, mailId);
        chn.send(_map);
    }

    // 访问别人
    public void viewPlayer(int pid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1955892762);  // cmd:viewPlayer
        _map.put(110987, pid);
        chn.send(_map);
    }

    // 购买保护盾
    public void buyShield(int crystal, long addTime, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -797241969);  // cmd:buyShield
        _map.put(1047561014, crystal);
        _map.put(-1148582130, addTime);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 取得用户信息
    public void getPInfo() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1961543560);  // cmd:getPInfo
        chn.send(_map);
    }

    // 领每日登录奖励
    public void rewardDay(NRewards lists, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1162918381);  // cmd:rewardDay
        _map.put(102982549, lists.toMap());
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 购买英雄
    public void buyHero(int hgid, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 244649888);  // cmd:buyHero
        _map.put(3200602, hgid);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 买活已死英雄
    public void liveHero(int hgid, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1417322246);  // cmd:liveHero
        _map.put(3200602, hgid);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 英雄死亡
    public void deadHero(int hgid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 502558334);  // cmd:deadHero
        _map.put(3200602, hgid);
        chn.send(_map);
    }

    // 升级后英雄的数据下发
    public void refreshHeros() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -67879522);  // cmd:refreshHeros
        chn.send(_map);
    }

    // 增加英雄能源:力量,血量,攻击速度
    public void addHeroEnergy(int hgid, int egid, int costGold, int costOil, int crystal, String sign) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1813858979);  // cmd:addHeroEnergy
        _map.put(3200602, hgid);
        _map.put(3111229, egid);
        _map.put(-425069107, costGold);
        _map.put(956126917, costOil);
        _map.put(1047561014, crystal);
        _map.put(3530173, sign);
        chn.send(_map);
    }

    // 取得个人周排行
    public void getNRankUse(int page) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1138712861);  // cmd:getNRankUse
        _map.put(3433103, page);
        chn.send(_map);
    }

    // 创建联盟
    public void creatClan(int icon, String cname, String cdesc, String resType, int costVal, int crystal) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 597367423);  // cmd:creatClan
        _map.put(3226745, icon);
        _map.put(94802286, cname);
        _map.put(94508404, cdesc);
        _map.put(1096575994, resType);
        _map.put(956133396, costVal);
        _map.put(1047561014, crystal);
        chn.send(_map);
    }

    // 退出联盟
    public void exitClan(String ccid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -2123231084);  // cmd:exitClan
        _map.put(3047803, ccid);
        chn.send(_map);
    }

    // 申请入盟
    public void joinInClan(String ccid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1226689253);  // cmd:joinInClan
        _map.put(3047803, ccid);
        chn.send(_map);
    }

    // 接受入盟
    public void acceptJoinIn(String ccid, int pcid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1704066839);  // cmd:acceptJoinIn
        _map.put(3047803, ccid);
        _map.put(3435086, pcid);
        chn.send(_map);
    }

    // 拒绝入盟
    public void refuseJoinIn(String ccid, int pcid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1366339875);  // cmd:refuseJoinIn
        _map.put(3047803, ccid);
        _map.put(3435086, pcid);
        chn.send(_map);
    }

    // 查找联盟
    public void searchClan(String cname) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -710973410);  // cmd:searchClan
        _map.put(94802286, cname);
        chn.send(_map);
    }

    // 查看联盟
    public void seeClan(String ccid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1970630985);  // cmd:seeClan
        _map.put(3047803, ccid);
        chn.send(_map);
    }

    // 取得自己的联盟
    public void getOwnClan() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -18886970);  // cmd:getOwnClan
        chn.send(_map);
    }

    // 踢出联盟玩家
    public void outClanMember(int pcid) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -2095070594);  // cmd:outClanMember
        _map.put(3435086, pcid);
        chn.send(_map);
    }

    // 修改联盟描述
    public void changeClan(String desc) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -2132422106);  // cmd:changeClan
        _map.put(3079825, desc);
        chn.send(_map);
    }

    // 设置职位
    public void allotClanPost(int pcid, int post) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1229523588);  // cmd:allotClanPost
        _map.put(3435086, pcid);
        _map.put(3446944, post);
        chn.send(_map);
    }


    public static final Set<Integer> CMD = NewSet.create().Add(-934594754).Add(1615760901).Add(-508077104).Add(2115707319).Add(929969162).Add(1629150990).Add(444729423).Add(-1056271730).Add(-1004167270).Add(1723144751).Add(62679870).Add(1176996619).Add(-31856230).Add(1127496743).Add(1070317554).Add(501903050).Add(52059937).Add(-858116154).Add(-762930944).Add(-2029211415).Add(-1149482139).Add(-858334864).Add(-868356563).Add(-940903302).Add(-2102563434).Add(1717679356).Add(1137223808).Add(2009579300).Add(-827208560).Add(-1761920275).Add(-1450193907).Add(840284783).Add(1321151899).Add(-320303030).Add(1041716509).Add(-1022537609).Add(-1506041187).Add(-1145738292).Add(-1455260092).Add(-1521297515).Add(809718812).Add(-740593574).Add(-428433996).Add(1093931788).Add(-1241398809).Add(-1377571782).Add(-188503535).Add(926354202).Add(99151942).Add(-1722927346).Add(2131720282).Add(1426859958).Add(1203719192).Add(1246941952).Add(-998949784).Add(-1696857871).Add(624359620).Add(615325507).Add(1247233375).Add(1151145404).Add(-995314110).Add(-1422538308).Add(-1148737611).Add(-612644065).Add(-1662400743).Add(1098415640).Add(-104574235).Add(-1130309809).Add(-1955892762).Add(-797241969).Add(1961543560).Add(1162918381).Add(244649888).Add(1417322246).Add(502558334).Add(-67879522).Add(1813858979).Add(-1138712861).Add(597367423).Add(-2123231084).Add(1226689253).Add(1704066839).Add(1366339875).Add(-710973410).Add(1970630985).Add(-18886970).Add(-2095070594).Add(-2132422106).Add(-1229523588);

    public static boolean in(NewMap map) throws Exception {
        int cmd = MapEx.getInt(map, 0);
        return CMD.contains(cmd);
    }

    // //////////////////////////////////////////////
    // 逻辑分发
    // //////////////////////////////////////////////

    public void disp(NewMap map) throws Exception {
        int cmd = MapEx.getInt(map, 0);
        disp(cmd, map);
    }
    public void disp(int cmd, NewMap map) throws Exception {
        switch (cmd) {
            case -934594754: { //  修改名称
                __onCallback_rename(map);
                return;
            }
            case 1615760901: { //  登录玩家
                __onCallback_loginUserByUid(map);
                return;
            }
            case -508077104: { //  新增建筑
                __onCallback_createBuilding(map);
                return;
            }
            case 2115707319: { //  完成移除
                __onCallback_finishRemoveBuild(map);
                return;
            }
            case 929969162: { //  自然完成完成造兵
                __onCallback_finishProduceArmy(map);
                return;
            }
            case 1629150990: { //  快速完成完成造兵
                __onCallback_fastFinishProduceArmy(map);
                return;
            }
            case 444729423: { //  pve战利品
                __onCallback_pveFightSpoils(map);
                return;
            }
            case -1056271730: { //  一般查询被攻击的角色
                __onCallback_findBeAttactPlayer(map);
                return;
            }
            case -1004167270: { //  购买水晶，内部测试用
                __onCallback_buyCrystalForOpenness(map);
                return;
            }
            case 1723144751: { //  随机名称
                __onCallback_randomPlayerName(map);
                return;
            }
            case 62679870: { //  成就完成领奖
                __onCallback_achievementReward(map);
                return;
            }
            case 1176996619: { //  完成移除障碍
                __onCallback_finishRemoveObst(map);
                return;
            }
            case -31856230: { //  下发的成就列表
                __onCallback_getAchievements(map);
                return;
            }
            case 1127496743: { //  首次充值奖励
                __onCallback_rewardFirstPay(map);
                return;
            }
            case 1070317554: { //  开宝箱
                __onCallback_openTreasureBox(map);
                return;
            }
            case 501903050: { //  创建充值订单
                __onCallback_createOrderPayMoney(map);
                return;
            }
            case 52059937: { //  每日日常任务奖励
                __onCallback_dayTasksReward(map);
                return;
            }
            case -858116154: { //  取得个人总排行
                __onCallback_getNRankUsersByAll(map);
                return;
            }
            case -762930944: { //  取得自己联盟成员
                __onCallback_getOwnClanMember(map);
                return;
            }
            case -2029211415: { //  取得自己联盟请求
                __onCallback_getOwnClanRequest(map);
                return;
            }
            case -1149482139: { //  捐献金币
                __onCallback_donateClanGold(map);
                return;
            }
            case -858334864: { //  取得联盟总排行
                __onCallback_getNRankClanByAll(map);
                return;
            }
            case -868356563: { //  捐献油
                __onCallback_donateClanOil(map);
                return;
            }
            case -940903302: { //  取得联盟周排行
                __onCallback_getNRankClan(map);
                return;
            }
            case -2102563434: { //  改变建筑等级
                __onCallback_downBuildLvl(map);
                return;
            }
            case 1717679356: { //  渠道快速注册
                __onCallback_cmRegistFast(map);
                return;
            }
            case 1137223808: { //  渠道注册
                __onCallback_cmRegist(map);
                return;
            }
            case 2009579300: { //  用户分享成功
                __onCallback_shareSuccess(map);
                return;
            }
            case -827208560: { //  打劫商船
                __onCallback_lootMerchant(map);
                return;
            }
            case -1761920275: { //  登录奇虎360取得uid,token
                __onCallback_loginQH360(map);
                return;
            }
            case -1450193907: { //  玩家角色
                __onCallback_loginUPlayer(map);
                return;
            }
            case 840284783: { //  升级建筑
                __onCallback_upBuilding(map);
                return;
            }
            case 1321151899: { //  完成升级建筑
                __onCallback_finishBuild(map);
                return;
            }
            case -320303030: { //  移除建筑
                __onCallback_removeBuild(map);
                return;
            }
            case 1041716509: { //  移动建筑
                __onCallback_moveBuild(map);
                return;
            }
            case -1022537609: { //  收资源
                __onCallback_harvestRes(map);
                return;
            }
            case -1506041187: { //  造兵和减少兵的接口
                __onCallback_produceArmy(map);
                return;
            }
            case -1145738292: { //  升级科技
                __onCallback_upTechnolgy(map);
                return;
            }
            case -1455260092: { //  完成升级科技
                __onCallback_finishUpTech(map);
                return;
            }
            case -1521297515: { //  pve战斗放兵
                __onCallback_pveAttackInfo(map);
                return;
            }
            case 809718812: { //  打pve结果
                __onCallback_pveResult(map);
                return;
            }
            case -740593574: { //  开始战斗
                __onCallback_beginAttact(map);
                return;
            }
            case -428433996: { //  战斗信息
                __onCallback_attactInfos(map);
                return;
            }
            case 1093931788: { //  结束战斗
                __onCallback_endAttact(map);
                return;
            }
            case -1241398809: { //  回到主基地
                __onCallback_goHome(map);
                return;
            }
            case -1377571782: { //  购买资源
                __onCallback_buyRes(map);
                return;
            }
            case -188503535: { //  战斗回放
                __onCallback_replayAttMail(map);
                return;
            }
            case 926354202: { //  反击
                __onCallback_hitBack(map);
                return;
            }
            case 99151942: { //  心跳
                __onCallback_heart(map);
                return;
            }
            case -1722927346: { //  设置名字
                __onCallback_setPlayerName(map);
                return;
            }
            case 2131720282: { //  修复陷阱
                __onCallback_repairTrap(map);
                return;
            }
            case 1426859958: { //  用户下线
                __onCallback_downLine(map);
                return;
            }
            case 1203719192: { //  新手步骤
                __onCallback_guidNewPlayer(map);
                return;
            }
            case 1246941952: { //  聊天内容
                __onCallback_sendChat(map);
                return;
            }
            case -998949784: { //  购买序列
                __onCallback_buyOrder(map);
                return;
            }
            case -1696857871: { //  下发战报邮件
                __onCallback_getAttMails(map);
                return;
            }
            case 624359620: { //  下发邮件
                __onCallback_getNMails(map);
                return;
            }
            case 615325507: { //  下发聊天信息
                __onCallback_getNChats(map);
                return;
            }
            case 1247233375: { //  新邮件
                __onCallback_sendMail(map);
                return;
            }
            case 1151145404: { //  结束引导
                __onCallback_finishGuid(map);
                return;
            }
            case -995314110: { //  购买法术
                __onCallback_buySpell(map);
                return;
            }
            case -1422538308: { //  增加经验
                __onCallback_addExp(map);
                return;
            }
            case -1148737611: { //  新增障碍
                __onCallback_addObst(map);
                return;
            }
            case -612644065: { //  新增障碍城墙
                __onCallback_addObstWall(map);
                return;
            }
            case -1662400743: { //  升级障碍-城墙
                __onCallback_upObstWall(map);
                return;
            }
            case 1098415640: { //  移除障碍
                __onCallback_removeObst(map);
                return;
            }
            case -104574235: { //  移动物体
                __onCallback_moveObst(map);
                return;
            }
            case -1130309809: { //  改变邮件状态为__已读
                __onCallback_readNMail(map);
                return;
            }
            case -1955892762: { //  访问别人
                __onCallback_viewPlayer(map);
                return;
            }
            case -797241969: { //  购买保护盾
                __onCallback_buyShield(map);
                return;
            }
            case 1961543560: { //  取得用户信息
                __onCallback_getPInfo(map);
                return;
            }
            case 1162918381: { //  领每日登录奖励
                __onCallback_rewardDay(map);
                return;
            }
            case 244649888: { //  购买英雄
                __onCallback_buyHero(map);
                return;
            }
            case 1417322246: { //  买活已死英雄
                __onCallback_liveHero(map);
                return;
            }
            case 502558334: { //  英雄死亡
                __onCallback_deadHero(map);
                return;
            }
            case -67879522: { //  升级后英雄的数据下发
                __onCallback_refreshHeros(map);
                return;
            }
            case 1813858979: { //  增加英雄能源:力量,血量,攻击速度
                __onCallback_addHeroEnergy(map);
                return;
            }
            case -1138712861: { //  取得个人周排行
                __onCallback_getNRankUse(map);
                return;
            }
            case 597367423: { //  创建联盟
                __onCallback_creatClan(map);
                return;
            }
            case -2123231084: { //  退出联盟
                __onCallback_exitClan(map);
                return;
            }
            case 1226689253: { //  申请入盟
                __onCallback_joinInClan(map);
                return;
            }
            case 1704066839: { //  接受入盟
                __onCallback_acceptJoinIn(map);
                return;
            }
            case 1366339875: { //  拒绝入盟
                __onCallback_refuseJoinIn(map);
                return;
            }
            case -710973410: { //  查找联盟
                __onCallback_searchClan(map);
                return;
            }
            case 1970630985: { //  查看联盟
                __onCallback_seeClan(map);
                return;
            }
            case -18886970: { //  取得自己的联盟
                __onCallback_getOwnClan(map);
                return;
            }
            case -2095070594: { //  踢出联盟玩家
                __onCallback_outClanMember(map);
                return;
            }
            case -2132422106: { //  修改联盟描述
                __onCallback_changeClan(map);
                return;
            }
            case -1229523588: { //  设置职位
                __onCallback_allotClanPost(map);
                return;
            }
        }
        throw new Exception(" cmd: " + cmd + ":" + map + " not found processor.");
    }


    // //////////////////////////////////////////////
    // 参数解析
    // //////////////////////////////////////////////

    // 修改名称
    private void __onCallback_rename(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRename(rst);
    }

    // 登录玩家
    private void __onCallback_loginUserByUid(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayers sign = NPlayers.parse(map2.getNewMap(3530173));

        onLoginUserByUid(sign, rst);
    }

    // 新增建筑
    private void __onCallback_createBuilding(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onCreateBuilding(rst);
    }

    // 完成移除
    private void __onCallback_finishRemoveBuild(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFinishRemoveBuild(rst);
    }

    // 自然完成完成造兵
    private void __onCallback_finishProduceArmy(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFinishProduceArmy(rst);
    }

    // 快速完成完成造兵
    private void __onCallback_fastFinishProduceArmy(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFastFinishProduceArmy(rst);
    }

    // pve战利品
    private void __onCallback_pveFightSpoils(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NEnergy energy = NEnergy.parse(map2.getNewMap(-1298713976));

        onPveFightSpoils(energy, rst);
    }

    // 一般查询被攻击的角色
    private void __onCallback_findBeAttactPlayer(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer beAttPlayer = NPlayer.parse(map2.getNewMap(-1629162529));
        NTeches bePTeches = NTeches.parse(map2.getNewMap(-619666287));
        NArmys bePArmys = NArmys.parse(map2.getNewMap(794145289));
        NBuilds bePBuilds = NBuilds.parse(map2.getNewMap(-1120032110));
        NHeros heros = NHeros.parse(map2.getNewMap(99168185));
        NClan nclan = NClan.parse(map2.getNewMap(104643524));
        NInt canGetGold = NInt.parse(map2.getNewMap(-1479633178));
        NInt canGetOil = NInt.parse(map2.getNewMap(-324817268));

        onFindBeAttactPlayer(beAttPlayer, bePTeches, bePArmys, bePBuilds, heros, nclan, canGetGold, canGetOil, rst);
    }

    // 购买水晶，内部测试用
    private void __onCallback_buyCrystalForOpenness(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onBuyCrystalForOpenness(rst);
    }

    // 随机名称
    private void __onCallback_randomPlayerName(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal randomName = NStrVal.parse(map2.getNewMap(115456494));

        onRandomPlayerName(randomName, rst);
    }

    // 成就完成领奖
    private void __onCallback_achievementReward(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAchievementReward(rst);
    }

    // 完成移除障碍
    private void __onCallback_finishRemoveObst(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFinishRemoveObst(rst);
    }

    // 下发的成就列表
    private void __onCallback_getAchievements(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NAchievements marks = NAchievements.parse(map2.getNewMap(103666502));

        onGetAchievements(marks, rst);
    }

    // 首次充值奖励
    private void __onCallback_rewardFirstPay(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRewardFirstPay(rst);
    }

    // 开宝箱
    private void __onCallback_openTreasureBox(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NEnergys energys = NEnergys.parse(map2.getNewMap(-1605427477));

        onOpenTreasureBox(energys, rst);
    }

    // 创建充值订单
    private void __onCallback_createOrderPayMoney(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal oriderCid = NStrVal.parse(map2.getNewMap(-288806573));

        onCreateOrderPayMoney(oriderCid, rst);
    }

    // 每日日常任务奖励
    private void __onCallback_dayTasksReward(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDayTasksReward(rst);
    }

    // 取得个人总排行
    private void __onCallback_getNRankUsersByAll(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NRankUsers rankUses = NRankUsers.parse(map2.getNewMap(256095768));

        onGetNRankUsersByAll(rankUses, rst);
    }

    // 取得自己联盟成员
    private void __onCallback_getOwnClanMember(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NClanMembers members = NClanMembers.parse(map2.getNewMap(948881689));

        onGetOwnClanMember(members, rst);
    }

    // 取得自己联盟请求
    private void __onCallback_getOwnClanRequest(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NClanRequests clanreqes = NClanRequests.parse(map2.getNewMap(-153257674));

        onGetOwnClanRequest(clanreqes, rst);
    }

    // 捐献金币
    private void __onCallback_donateClanGold(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDonateClanGold(rst);
    }

    // 取得联盟总排行
    private void __onCallback_getNRankClanByAll(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NRankClans rankClans = NRankClans.parse(map2.getNewMap(-667801583));

        onGetNRankClanByAll(rankClans, rst);
    }

    // 捐献油
    private void __onCallback_donateClanOil(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDonateClanOil(rst);
    }

    // 取得联盟周排行
    private void __onCallback_getNRankClan(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NRankClans rankClans = NRankClans.parse(map2.getNewMap(-667801583));

        onGetNRankClan(rankClans, rst);
    }

    // 改变建筑等级
    private void __onCallback_downBuildLvl(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDownBuildLvl(rst);
    }

    // 渠道快速注册
    private void __onCallback_cmRegistFast(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal outLgId = NStrVal.parse(map2.getNewMap(-1107201948));
        NStrVal outLgPwd = NStrVal.parse(map2.getNewMap(36485396));

        onCmRegistFast(outLgId, outLgPwd, rst);
    }

    // 渠道注册
    private void __onCallback_cmRegist(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onCmRegist(rst);
    }

    // 用户分享成功
    private void __onCallback_shareSuccess(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onShareSuccess(rst);
    }

    // 打劫商船
    private void __onCallback_lootMerchant(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onLootMerchant(rst);
    }

    // 登录奇虎360取得uid,token
    private void __onCallback_loginQH360(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal strToken = NStrVal.parse(map2.getNewMap(1775649352));
        NStrVal strUID = NStrVal.parse(map2.getNewMap(-892006305));

        onLoginQH360(strToken, strUID, rst);
    }

    // 玩家角色
    private void __onCallback_loginUPlayer(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NSession session = NSession.parse(map2.getNewMap(1984987798));
        NPlayer player = NPlayer.parse(map2.getNewMap(-985752863));
        NNPCBeFighteds npcs = NNPCBeFighteds.parse(map2.getNewMap(3387826));
        NBuilds builds = NBuilds.parse(map2.getNewMap(-1378023483));
        NTeches teches = NTeches.parse(map2.getNewMap(-877657660));
        NArmys armys = NArmys.parse(map2.getNewMap(93086326));
        NProductes army_produtes = NProductes.parse(map2.getNewMap(1919479750));
        NSpells nspells = NSpells.parse(map2.getNewMap(-2054907335));
        NHeros heros = NHeros.parse(map2.getNewMap(99168185));

        onLoginUPlayer(session, player, npcs, builds, teches, armys, army_produtes, nspells, heros, rst);
    }

    // 升级建筑
    private void __onCallback_upBuilding(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onUpBuilding(rst);
    }

    // 完成升级建筑
    private void __onCallback_finishBuild(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFinishBuild(rst);
    }

    // 移除建筑
    private void __onCallback_removeBuild(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRemoveBuild(rst);
    }

    // 移动建筑
    private void __onCallback_moveBuild(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onMoveBuild(rst);
    }

    // 收资源
    private void __onCallback_harvestRes(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onHarvestRes(rst);
    }

    // 造兵和减少兵的接口
    private void __onCallback_produceArmy(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onProduceArmy(rst);
    }

    // 升级科技
    private void __onCallback_upTechnolgy(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onUpTechnolgy(rst);
    }

    // 完成升级科技
    private void __onCallback_finishUpTech(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFinishUpTech(rst);
    }

    // pve战斗放兵
    private void __onCallback_pveAttackInfo(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onPveAttackInfo(rst);
    }

    // 打pve结果
    private void __onCallback_pveResult(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onPveResult(rst);
    }

    // 开始战斗
    private void __onCallback_beginAttact(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NEnergy energy = NEnergy.parse(map2.getNewMap(-1298713976));

        onBeginAttact(energy, rst);
    }

    // 战斗信息
    private void __onCallback_attactInfos(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAttactInfos(rst);
    }

    // 结束战斗
    private void __onCallback_endAttact(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onEndAttact(rst);
    }

    // 回到主基地
    private void __onCallback_goHome(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer player = NPlayer.parse(map2.getNewMap(-985752863));
        NNPCBeFighteds npcs = NNPCBeFighteds.parse(map2.getNewMap(3387826));
        NBuilds builds = NBuilds.parse(map2.getNewMap(-1378023483));
        NTeches teches = NTeches.parse(map2.getNewMap(-877657660));
        NArmys armys = NArmys.parse(map2.getNewMap(93086326));
        NProductes produtes = NProductes.parse(map2.getNewMap(-1003745436));
        NSpells nspells = NSpells.parse(map2.getNewMap(-2054907335));
        NHeros heros = NHeros.parse(map2.getNewMap(99168185));

        onGoHome(player, npcs, builds, teches, armys, produtes, nspells, heros, rst);
    }

    // 购买资源
    private void __onCallback_buyRes(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer player = NPlayer.parse(map2.getNewMap(-985752863));

        onBuyRes(player, rst);
    }

    // 战斗回放
    private void __onCallback_replayAttMail(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer player = NPlayer.parse(map2.getNewMap(-985752863));
        NTeches teches = NTeches.parse(map2.getNewMap(-877657660));
        NArmys amrys = NArmys.parse(map2.getNewMap(92942176));
        NBuilds builds = NBuilds.parse(map2.getNewMap(-1378023483));
        NAttackInfos attInfo = NAttackInfos.parse(map2.getNewMap(-676062481));
        NLong timeSlot = NLong.parse(map2.getNewMap(-2077392277));
        NHeros heros = NHeros.parse(map2.getNewMap(99168185));
        NHeros offHeros = NHeros.parse(map2.getNewMap(-795697270));
        NClan offNclan = NClan.parse(map2.getNewMap(-790221931));
        NClan defNclan = NClan.parse(map2.getNewMap(626197183));

        onReplayAttMail(player, teches, amrys, builds, attInfo, timeSlot, heros, offHeros, offNclan, defNclan, rst);
    }

    // 反击
    private void __onCallback_hitBack(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer beAttPlayer = NPlayer.parse(map2.getNewMap(-1629162529));
        NTeches bePTeches = NTeches.parse(map2.getNewMap(-619666287));
        NArmys bePArmys = NArmys.parse(map2.getNewMap(794145289));
        NBuilds builds = NBuilds.parse(map2.getNewMap(-1378023483));
        NHeros heros = NHeros.parse(map2.getNewMap(99168185));
        NClan nclan = NClan.parse(map2.getNewMap(104643524));

        onHitBack(beAttPlayer, bePTeches, bePArmys, builds, heros, nclan, rst);
    }

    // 心跳
    private void __onCallback_heart(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NInt heat = NInt.parse(map2.getNewMap(3198448));

        onHeart(heat, rst);
    }

    // 设置名字
    private void __onCallback_setPlayerName(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onSetPlayerName(rst);
    }

    // 修复陷阱
    private void __onCallback_repairTrap(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRepairTrap(rst);
    }

    // 用户下线
    private void __onCallback_downLine(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDownLine(rst);
    }

    // 新手步骤
    private void __onCallback_guidNewPlayer(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onGuidNewPlayer(rst);
    }

    // 聊天内容
    private void __onCallback_sendChat(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onSendChat(rst);
    }

    // 购买序列
    private void __onCallback_buyOrder(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onBuyOrder(rst);
    }

    // 下发战报邮件
    private void __onCallback_getAttMails(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NAttMails attMails = NAttMails.parse(map2.getNewMap(520209275));

        onGetAttMails(attMails, rst);
    }

    // 下发邮件
    private void __onCallback_getNMails(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NMails nmails = NMails.parse(map2.getNewMap(-1042102802));

        onGetNMails(nmails, rst);
    }

    // 下发聊天信息
    private void __onCallback_getNChats(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NChats nchats = NChats.parse(map2.getNewMap(-1051136915));

        onGetNChats(nchats, rst);
    }

    // 新邮件
    private void __onCallback_sendMail(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onSendMail(rst);
    }

    // 结束引导
    private void __onCallback_finishGuid(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer player = NPlayer.parse(map2.getNewMap(-985752863));
        NBuilds builds = NBuilds.parse(map2.getNewMap(-1378023483));

        onFinishGuid(player, builds, rst);
    }

    // 购买法术
    private void __onCallback_buySpell(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onBuySpell(rst);
    }

    // 增加经验
    private void __onCallback_addExp(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAddExp(rst);
    }

    // 新增障碍
    private void __onCallback_addObst(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAddObst(rst);
    }

    // 新增障碍城墙
    private void __onCallback_addObstWall(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAddObstWall(rst);
    }

    // 升级障碍-城墙
    private void __onCallback_upObstWall(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onUpObstWall(rst);
    }

    // 移除障碍
    private void __onCallback_removeObst(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRemoveObst(rst);
    }

    // 移动物体
    private void __onCallback_moveObst(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onMoveObst(rst);
    }

    // 改变邮件状态为__已读
    private void __onCallback_readNMail(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onReadNMail(rst);
    }

    // 访问别人
    private void __onCallback_viewPlayer(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer player = NPlayer.parse(map2.getNewMap(-985752863));
        NBuilds builds = NBuilds.parse(map2.getNewMap(-1378023483));
        NTeches teches = NTeches.parse(map2.getNewMap(-877657660));
        NArmys armys = NArmys.parse(map2.getNewMap(93086326));
        NHeros heros = NHeros.parse(map2.getNewMap(99168185));

        onViewPlayer(player, builds, teches, armys, heros, rst);
    }

    // 购买保护盾
    private void __onCallback_buyShield(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onBuyShield(rst);
    }

    // 取得用户信息
    private void __onCallback_getPInfo(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPInfo pinfo = NPInfo.parse(map2.getNewMap(106671390));

        onGetPInfo(pinfo, rst);
    }

    // 领每日登录奖励
    private void __onCallback_rewardDay(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRewardDay(rst);
    }

    // 购买英雄
    private void __onCallback_buyHero(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onBuyHero(rst);
    }

    // 买活已死英雄
    private void __onCallback_liveHero(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onLiveHero(rst);
    }

    // 英雄死亡
    private void __onCallback_deadHero(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDeadHero(rst);
    }

    // 升级后英雄的数据下发
    private void __onCallback_refreshHeros(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NHeros heors = NHeros.parse(map2.getNewMap(99165395));

        onRefreshHeros(heors, rst);
    }

    // 增加英雄能源:力量,血量,攻击速度
    private void __onCallback_addHeroEnergy(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NInt outHgid = NInt.parse(map2.getNewMap(-1107320120));
        NInt outEgid = NInt.parse(map2.getNewMap(-1107409493));
        NInt curEnergyNum = NInt.parse(map2.getNewMap(258062718));
        NInt addval = NInt.parse(map2.getNewMap(-1422491936));

        onAddHeroEnergy(outHgid, outEgid, curEnergyNum, addval, rst);
    }

    // 取得个人周排行
    private void __onCallback_getNRankUse(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NRankUsers rankUses = NRankUsers.parse(map2.getNewMap(256095768));

        onGetNRankUse(rankUses, rst);
    }

    // 创建联盟
    private void __onCallback_creatClan(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal ccid = NStrVal.parse(map2.getNewMap(3047803));

        onCreatClan(ccid, rst);
    }

    // 退出联盟
    private void __onCallback_exitClan(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onExitClan(rst);
    }

    // 申请入盟
    private void __onCallback_joinInClan(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onJoinInClan(rst);
    }

    // 接受入盟
    private void __onCallback_acceptJoinIn(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAcceptJoinIn(rst);
    }

    // 拒绝入盟
    private void __onCallback_refuseJoinIn(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRefuseJoinIn(rst);
    }

    // 查找联盟
    private void __onCallback_searchClan(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NClans clans = NClans.parse(map2.getNewMap(94742749));

        onSearchClan(clans, rst);
    }

    // 查看联盟
    private void __onCallback_seeClan(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NClan clan = NClan.parse(map2.getNewMap(3056214));
        NClanMembers members = NClanMembers.parse(map2.getNewMap(948881689));

        onSeeClan(clan, members, rst);
    }

    // 取得自己的联盟
    private void __onCallback_getOwnClan(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NClan clan = NClan.parse(map2.getNewMap(3056214));

        onGetOwnClan(clan, rst);
    }

    // 踢出联盟玩家
    private void __onCallback_outClanMember(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onOutClanMember(rst);
    }

    // 修改联盟描述
    private void __onCallback_changeClan(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onChangeClan(rst);
    }

    // 设置职位
    private void __onCallback_allotClanPost(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAllotClanPost(rst);
    }


    // //////////////////////////////////////////////
    // 需要实现的接口
    // //////////////////////////////////////////////

    // 修改名称
    public void onRename(ReturnStatus val) throws Exception {};

    // 登录玩家
    public void onLoginUserByUid(NPlayers sign, ReturnStatus val) throws Exception {};

    // 新增建筑
    public void onCreateBuilding(ReturnStatus val) throws Exception {};

    // 完成移除
    public void onFinishRemoveBuild(ReturnStatus val) throws Exception {};

    // 自然完成完成造兵
    public void onFinishProduceArmy(ReturnStatus val) throws Exception {};

    // 快速完成完成造兵
    public void onFastFinishProduceArmy(ReturnStatus val) throws Exception {};

    // pve战利品
    public void onPveFightSpoils(NEnergy energy, ReturnStatus val) throws Exception {};

    // 一般查询被攻击的角色
    public void onFindBeAttactPlayer(NPlayer beAttPlayer, NTeches bePTeches, NArmys bePArmys, NBuilds bePBuilds, NHeros heros, NClan nclan, NInt canGetGold, NInt canGetOil, ReturnStatus val) throws Exception {};

    // 购买水晶，内部测试用
    public void onBuyCrystalForOpenness(ReturnStatus val) throws Exception {};

    // 随机名称
    public void onRandomPlayerName(NStrVal randomName, ReturnStatus val) throws Exception {};

    // 成就完成领奖
    public void onAchievementReward(ReturnStatus val) throws Exception {};

    // 完成移除障碍
    public void onFinishRemoveObst(ReturnStatus val) throws Exception {};

    // 下发的成就列表
    public void onGetAchievements(NAchievements marks, ReturnStatus val) throws Exception {};

    // 首次充值奖励
    public void onRewardFirstPay(ReturnStatus val) throws Exception {};

    // 开宝箱
    public void onOpenTreasureBox(NEnergys energys, ReturnStatus val) throws Exception {};

    // 创建充值订单
    public void onCreateOrderPayMoney(NStrVal oriderCid, ReturnStatus val) throws Exception {};

    // 每日日常任务奖励
    public void onDayTasksReward(ReturnStatus val) throws Exception {};

    // 取得个人总排行
    public void onGetNRankUsersByAll(NRankUsers rankUses, ReturnStatus val) throws Exception {};

    // 取得自己联盟成员
    public void onGetOwnClanMember(NClanMembers members, ReturnStatus val) throws Exception {};

    // 取得自己联盟请求
    public void onGetOwnClanRequest(NClanRequests clanreqes, ReturnStatus val) throws Exception {};

    // 捐献金币
    public void onDonateClanGold(ReturnStatus val) throws Exception {};

    // 取得联盟总排行
    public void onGetNRankClanByAll(NRankClans rankClans, ReturnStatus val) throws Exception {};

    // 捐献油
    public void onDonateClanOil(ReturnStatus val) throws Exception {};

    // 取得联盟周排行
    public void onGetNRankClan(NRankClans rankClans, ReturnStatus val) throws Exception {};

    // 改变建筑等级
    public void onDownBuildLvl(ReturnStatus val) throws Exception {};

    // 渠道快速注册
    public void onCmRegistFast(NStrVal outLgId, NStrVal outLgPwd, ReturnStatus val) throws Exception {};

    // 渠道注册
    public void onCmRegist(ReturnStatus val) throws Exception {};

    // 用户分享成功
    public void onShareSuccess(ReturnStatus val) throws Exception {};

    // 打劫商船
    public void onLootMerchant(ReturnStatus val) throws Exception {};

    // 登录奇虎360取得uid,token
    public void onLoginQH360(NStrVal strToken, NStrVal strUID, ReturnStatus val) throws Exception {};

    // 玩家角色
    public void onLoginUPlayer(NSession session, NPlayer player, NNPCBeFighteds npcs, NBuilds builds, NTeches teches, NArmys armys, NProductes army_produtes, NSpells nspells, NHeros heros, ReturnStatus val) throws Exception {};

    // 升级建筑
    public void onUpBuilding(ReturnStatus val) throws Exception {};

    // 完成升级建筑
    public void onFinishBuild(ReturnStatus val) throws Exception {};

    // 移除建筑
    public void onRemoveBuild(ReturnStatus val) throws Exception {};

    // 移动建筑
    public void onMoveBuild(ReturnStatus val) throws Exception {};

    // 收资源
    public void onHarvestRes(ReturnStatus val) throws Exception {};

    // 造兵和减少兵的接口
    public void onProduceArmy(ReturnStatus val) throws Exception {};

    // 升级科技
    public void onUpTechnolgy(ReturnStatus val) throws Exception {};

    // 完成升级科技
    public void onFinishUpTech(ReturnStatus val) throws Exception {};

    // pve战斗放兵
    public void onPveAttackInfo(ReturnStatus val) throws Exception {};

    // 打pve结果
    public void onPveResult(ReturnStatus val) throws Exception {};

    // 开始战斗
    public void onBeginAttact(NEnergy energy, ReturnStatus val) throws Exception {};

    // 战斗信息
    public void onAttactInfos(ReturnStatus val) throws Exception {};

    // 结束战斗
    public void onEndAttact(ReturnStatus val) throws Exception {};

    // 回到主基地
    public void onGoHome(NPlayer player, NNPCBeFighteds npcs, NBuilds builds, NTeches teches, NArmys armys, NProductes produtes, NSpells nspells, NHeros heros, ReturnStatus val) throws Exception {};

    // 购买资源
    public void onBuyRes(NPlayer player, ReturnStatus val) throws Exception {};

    // 战斗回放
    public void onReplayAttMail(NPlayer player, NTeches teches, NArmys amrys, NBuilds builds, NAttackInfos attInfo, NLong timeSlot, NHeros heros, NHeros offHeros, NClan offNclan, NClan defNclan, ReturnStatus val) throws Exception {};

    // 反击
    public void onHitBack(NPlayer beAttPlayer, NTeches bePTeches, NArmys bePArmys, NBuilds builds, NHeros heros, NClan nclan, ReturnStatus val) throws Exception {};

    // 心跳
    public void onHeart(NInt heat, ReturnStatus val) throws Exception {};

    // 设置名字
    public void onSetPlayerName(ReturnStatus val) throws Exception {};

    // 修复陷阱
    public void onRepairTrap(ReturnStatus val) throws Exception {};

    // 用户下线
    public void onDownLine(ReturnStatus val) throws Exception {};

    // 新手步骤
    public void onGuidNewPlayer(ReturnStatus val) throws Exception {};

    // 聊天内容
    public void onSendChat(ReturnStatus val) throws Exception {};

    // 购买序列
    public void onBuyOrder(ReturnStatus val) throws Exception {};

    // 下发战报邮件
    public void onGetAttMails(NAttMails attMails, ReturnStatus val) throws Exception {};

    // 下发邮件
    public void onGetNMails(NMails nmails, ReturnStatus val) throws Exception {};

    // 下发聊天信息
    public void onGetNChats(NChats nchats, ReturnStatus val) throws Exception {};

    // 新邮件
    public void onSendMail(ReturnStatus val) throws Exception {};

    // 结束引导
    public void onFinishGuid(NPlayer player, NBuilds builds, ReturnStatus val) throws Exception {};

    // 购买法术
    public void onBuySpell(ReturnStatus val) throws Exception {};

    // 增加经验
    public void onAddExp(ReturnStatus val) throws Exception {};

    // 新增障碍
    public void onAddObst(ReturnStatus val) throws Exception {};

    // 新增障碍城墙
    public void onAddObstWall(ReturnStatus val) throws Exception {};

    // 升级障碍-城墙
    public void onUpObstWall(ReturnStatus val) throws Exception {};

    // 移除障碍
    public void onRemoveObst(ReturnStatus val) throws Exception {};

    // 移动物体
    public void onMoveObst(ReturnStatus val) throws Exception {};

    // 改变邮件状态为__已读
    public void onReadNMail(ReturnStatus val) throws Exception {};

    // 访问别人
    public void onViewPlayer(NPlayer player, NBuilds builds, NTeches teches, NArmys armys, NHeros heros, ReturnStatus val) throws Exception {};

    // 购买保护盾
    public void onBuyShield(ReturnStatus val) throws Exception {};

    // 取得用户信息
    public void onGetPInfo(NPInfo pinfo, ReturnStatus val) throws Exception {};

    // 领每日登录奖励
    public void onRewardDay(ReturnStatus val) throws Exception {};

    // 购买英雄
    public void onBuyHero(ReturnStatus val) throws Exception {};

    // 买活已死英雄
    public void onLiveHero(ReturnStatus val) throws Exception {};

    // 英雄死亡
    public void onDeadHero(ReturnStatus val) throws Exception {};

    // 升级后英雄的数据下发
    public void onRefreshHeros(NHeros heors, ReturnStatus val) throws Exception {};

    // 增加英雄能源:力量,血量,攻击速度
    public void onAddHeroEnergy(NInt outHgid, NInt outEgid, NInt curEnergyNum, NInt addval, ReturnStatus val) throws Exception {};

    // 取得个人周排行
    public void onGetNRankUse(NRankUsers rankUses, ReturnStatus val) throws Exception {};

    // 创建联盟
    public void onCreatClan(NStrVal ccid, ReturnStatus val) throws Exception {};

    // 退出联盟
    public void onExitClan(ReturnStatus val) throws Exception {};

    // 申请入盟
    public void onJoinInClan(ReturnStatus val) throws Exception {};

    // 接受入盟
    public void onAcceptJoinIn(ReturnStatus val) throws Exception {};

    // 拒绝入盟
    public void onRefuseJoinIn(ReturnStatus val) throws Exception {};

    // 查找联盟
    public void onSearchClan(NClans clans, ReturnStatus val) throws Exception {};

    // 查看联盟
    public void onSeeClan(NClan clan, NClanMembers members, ReturnStatus val) throws Exception {};

    // 取得自己的联盟
    public void onGetOwnClan(NClan clan, ReturnStatus val) throws Exception {};

    // 踢出联盟玩家
    public void onOutClanMember(ReturnStatus val) throws Exception {};

    // 修改联盟描述
    public void onChangeClan(ReturnStatus val) throws Exception {};

    // 设置职位
    public void onAllotClanPost(ReturnStatus val) throws Exception {};

}
