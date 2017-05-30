package gen_b2g.serv;

import java.util.*;

import com.bowlong.util.*;
import com.bowlong.netty.bio2.*;

import gen_b2g.serv.bean.*;

@SuppressWarnings({ "all"})
public abstract class SeaWarServiceI {

public abstract TcpChannel chn(int XID) throws Exception;


    public static final Set<Integer> CMD = NewSet.create().Add(-934594754).Add(1615760901).Add(-508077104).Add(2115707319).Add(929969162).Add(1629150990).Add(444729423).Add(-1056271730).Add(-1004167270).Add(1723144751).Add(62679870).Add(1176996619).Add(-31856230).Add(1127496743).Add(1070317554).Add(501903050).Add(52059937).Add(-858116154).Add(-762930944).Add(-2029211415).Add(-1149482139).Add(-858334864).Add(-868356563).Add(-940903302).Add(-2102563434).Add(1717679356).Add(1137223808).Add(2009579300).Add(-827208560).Add(-1761920275).Add(-1450193907).Add(840284783).Add(1321151899).Add(-320303030).Add(1041716509).Add(-1022537609).Add(-1506041187).Add(-1145738292).Add(-1455260092).Add(-1521297515).Add(809718812).Add(-740593574).Add(-428433996).Add(1093931788).Add(-1241398809).Add(-1377571782).Add(-188503535).Add(926354202).Add(99151942).Add(-1722927346).Add(2131720282).Add(1426859958).Add(1203719192).Add(1246941952).Add(-998949784).Add(-1696857871).Add(624359620).Add(615325507).Add(1247233375).Add(1151145404).Add(-995314110).Add(-1422538308).Add(-1148737611).Add(-612644065).Add(-1662400743).Add(1098415640).Add(-104574235).Add(-1130309809).Add(-1955892762).Add(-797241969).Add(1961543560).Add(1162918381).Add(244649888).Add(1417322246).Add(502558334).Add(-67879522).Add(1813858979).Add(-1138712861).Add(597367423).Add(-2123231084).Add(1226689253).Add(1704066839).Add(1366339875).Add(-710973410).Add(1970630985).Add(-18886970).Add(-2095070594).Add(-2132422106).Add(-1229523588);

    public static boolean in(NewMap map) throws Exception {
        int cmd = MapEx.getInt(map, 0);
        return CMD.contains(cmd);
    }

    public static boolean in(int cmd) throws Exception {
        return CMD.contains(cmd);
    }

    public abstract java.io.ByteArrayOutputStream getOutStream();
    public abstract void freeOutStream(java.io.ByteArrayOutputStream baos);

    // //////////////////////////////////////////////
    // 逻辑分发
    // //////////////////////////////////////////////

    public String disp(TcpChannel chn, NewMap map) throws Exception {
        if(chn == null) return "";
        int cmd = MapEx.getInt(map, 0);
        return disp(chn, cmd, map);
    }
    public String disp(TcpChannel chn, int cmd, NewMap map) throws Exception {
        if(chn == null) return "";
        switch (cmd) {
            case -934594754: { //  修改名称
                __rename(chn, map);
                return "rename";
            }
            case 1615760901: { //  登录玩家
                __loginUserByUid(chn, map);
                return "loginUserByUid";
            }
            case -508077104: { //  新增建筑
                __createBuilding(chn, map);
                return "createBuilding";
            }
            case 2115707319: { //  完成移除
                __finishRemoveBuild(chn, map);
                return "finishRemoveBuild";
            }
            case 929969162: { //  自然完成完成造兵
                __finishProduceArmy(chn, map);
                return "finishProduceArmy";
            }
            case 1629150990: { //  快速完成完成造兵
                __fastFinishProduceArmy(chn, map);
                return "fastFinishProduceArmy";
            }
            case 444729423: { //  pve战利品
                __pveFightSpoils(chn, map);
                return "pveFightSpoils";
            }
            case -1056271730: { //  一般查询被攻击的角色
                __findBeAttactPlayer(chn, map);
                return "findBeAttactPlayer";
            }
            case -1004167270: { //  购买水晶，内部测试用
                __buyCrystalForOpenness(chn, map);
                return "buyCrystalForOpenness";
            }
            case 1723144751: { //  随机名称
                __randomPlayerName(chn, map);
                return "randomPlayerName";
            }
            case 62679870: { //  成就完成领奖
                __achievementReward(chn, map);
                return "achievementReward";
            }
            case 1176996619: { //  完成移除障碍
                __finishRemoveObst(chn, map);
                return "finishRemoveObst";
            }
            case -31856230: { //  下发的成就列表
                __getAchievements(chn, map);
                return "getAchievements";
            }
            case 1127496743: { //  首次充值奖励
                __rewardFirstPay(chn, map);
                return "rewardFirstPay";
            }
            case 1070317554: { //  开宝箱
                __openTreasureBox(chn, map);
                return "openTreasureBox";
            }
            case 501903050: { //  创建充值订单
                __createOrderPayMoney(chn, map);
                return "createOrderPayMoney";
            }
            case 52059937: { //  每日日常任务奖励
                __dayTasksReward(chn, map);
                return "dayTasksReward";
            }
            case -858116154: { //  取得个人总排行
                __getNRankUsersByAll(chn, map);
                return "getNRankUsersByAll";
            }
            case -762930944: { //  取得自己联盟成员
                __getOwnClanMember(chn, map);
                return "getOwnClanMember";
            }
            case -2029211415: { //  取得自己联盟请求
                __getOwnClanRequest(chn, map);
                return "getOwnClanRequest";
            }
            case -1149482139: { //  捐献金币
                __donateClanGold(chn, map);
                return "donateClanGold";
            }
            case -858334864: { //  取得联盟总排行
                __getNRankClanByAll(chn, map);
                return "getNRankClanByAll";
            }
            case -868356563: { //  捐献油
                __donateClanOil(chn, map);
                return "donateClanOil";
            }
            case -940903302: { //  取得联盟周排行
                __getNRankClan(chn, map);
                return "getNRankClan";
            }
            case -2102563434: { //  改变建筑等级
                __downBuildLvl(chn, map);
                return "downBuildLvl";
            }
            case 1717679356: { //  渠道快速注册
                __cmRegistFast(chn, map);
                return "cmRegistFast";
            }
            case 1137223808: { //  渠道注册
                __cmRegist(chn, map);
                return "cmRegist";
            }
            case 2009579300: { //  用户分享成功
                __shareSuccess(chn, map);
                return "shareSuccess";
            }
            case -827208560: { //  打劫商船
                __lootMerchant(chn, map);
                return "lootMerchant";
            }
            case -1761920275: { //  登录奇虎360取得uid,token
                __loginQH360(chn, map);
                return "loginQH360";
            }
            case -1450193907: { //  玩家角色
                __loginUPlayer(chn, map);
                return "loginUPlayer";
            }
            case 840284783: { //  升级建筑
                __upBuilding(chn, map);
                return "upBuilding";
            }
            case 1321151899: { //  完成升级建筑
                __finishBuild(chn, map);
                return "finishBuild";
            }
            case -320303030: { //  移除建筑
                __removeBuild(chn, map);
                return "removeBuild";
            }
            case 1041716509: { //  移动建筑
                __moveBuild(chn, map);
                return "moveBuild";
            }
            case -1022537609: { //  收资源
                __harvestRes(chn, map);
                return "harvestRes";
            }
            case -1506041187: { //  造兵和减少兵的接口
                __produceArmy(chn, map);
                return "produceArmy";
            }
            case -1145738292: { //  升级科技
                __upTechnolgy(chn, map);
                return "upTechnolgy";
            }
            case -1455260092: { //  完成升级科技
                __finishUpTech(chn, map);
                return "finishUpTech";
            }
            case -1521297515: { //  pve战斗放兵
                __pveAttackInfo(chn, map);
                return "pveAttackInfo";
            }
            case 809718812: { //  打pve结果
                __pveResult(chn, map);
                return "pveResult";
            }
            case -740593574: { //  开始战斗
                __beginAttact(chn, map);
                return "beginAttact";
            }
            case -428433996: { //  战斗信息
                __attactInfos(chn, map);
                return "attactInfos";
            }
            case 1093931788: { //  结束战斗
                __endAttact(chn, map);
                return "endAttact";
            }
            case -1241398809: { //  回到主基地
                __goHome(chn, map);
                return "goHome";
            }
            case -1377571782: { //  购买资源
                __buyRes(chn, map);
                return "buyRes";
            }
            case -188503535: { //  战斗回放
                __replayAttMail(chn, map);
                return "replayAttMail";
            }
            case 926354202: { //  反击
                __hitBack(chn, map);
                return "hitBack";
            }
            case 99151942: { //  心跳
                __heart(chn, map);
                return "heart";
            }
            case -1722927346: { //  设置名字
                __setPlayerName(chn, map);
                return "setPlayerName";
            }
            case 2131720282: { //  修复陷阱
                __repairTrap(chn, map);
                return "repairTrap";
            }
            case 1426859958: { //  用户下线
                __downLine(chn, map);
                return "downLine";
            }
            case 1203719192: { //  新手步骤
                __guidNewPlayer(chn, map);
                return "guidNewPlayer";
            }
            case 1246941952: { //  聊天内容
                __sendChat(chn, map);
                return "sendChat";
            }
            case -998949784: { //  购买序列
                __buyOrder(chn, map);
                return "buyOrder";
            }
            case -1696857871: { //  下发战报邮件
                __getAttMails(chn, map);
                return "getAttMails";
            }
            case 624359620: { //  下发邮件
                __getNMails(chn, map);
                return "getNMails";
            }
            case 615325507: { //  下发聊天信息
                __getNChats(chn, map);
                return "getNChats";
            }
            case 1247233375: { //  新邮件
                __sendMail(chn, map);
                return "sendMail";
            }
            case 1151145404: { //  结束引导
                __finishGuid(chn, map);
                return "finishGuid";
            }
            case -995314110: { //  购买法术
                __buySpell(chn, map);
                return "buySpell";
            }
            case -1422538308: { //  增加经验
                __addExp(chn, map);
                return "addExp";
            }
            case -1148737611: { //  新增障碍
                __addObst(chn, map);
                return "addObst";
            }
            case -612644065: { //  新增障碍城墙
                __addObstWall(chn, map);
                return "addObstWall";
            }
            case -1662400743: { //  升级障碍-城墙
                __upObstWall(chn, map);
                return "upObstWall";
            }
            case 1098415640: { //  移除障碍
                __removeObst(chn, map);
                return "removeObst";
            }
            case -104574235: { //  移动物体
                __moveObst(chn, map);
                return "moveObst";
            }
            case -1130309809: { //  改变邮件状态为__已读
                __readNMail(chn, map);
                return "readNMail";
            }
            case -1955892762: { //  访问别人
                __viewPlayer(chn, map);
                return "viewPlayer";
            }
            case -797241969: { //  购买保护盾
                __buyShield(chn, map);
                return "buyShield";
            }
            case 1961543560: { //  取得用户信息
                __getPInfo(chn, map);
                return "getPInfo";
            }
            case 1162918381: { //  领每日登录奖励
                __rewardDay(chn, map);
                return "rewardDay";
            }
            case 244649888: { //  购买英雄
                __buyHero(chn, map);
                return "buyHero";
            }
            case 1417322246: { //  买活已死英雄
                __liveHero(chn, map);
                return "liveHero";
            }
            case 502558334: { //  英雄死亡
                __deadHero(chn, map);
                return "deadHero";
            }
            case -67879522: { //  升级后英雄的数据下发
                __refreshHeros(chn, map);
                return "refreshHeros";
            }
            case 1813858979: { //  增加英雄能源:力量,血量,攻击速度
                __addHeroEnergy(chn, map);
                return "addHeroEnergy";
            }
            case -1138712861: { //  取得个人周排行
                __getNRankUse(chn, map);
                return "getNRankUse";
            }
            case 597367423: { //  创建联盟
                __creatClan(chn, map);
                return "creatClan";
            }
            case -2123231084: { //  退出联盟
                __exitClan(chn, map);
                return "exitClan";
            }
            case 1226689253: { //  申请入盟
                __joinInClan(chn, map);
                return "joinInClan";
            }
            case 1704066839: { //  接受入盟
                __acceptJoinIn(chn, map);
                return "acceptJoinIn";
            }
            case 1366339875: { //  拒绝入盟
                __refuseJoinIn(chn, map);
                return "refuseJoinIn";
            }
            case -710973410: { //  查找联盟
                __searchClan(chn, map);
                return "searchClan";
            }
            case 1970630985: { //  查看联盟
                __seeClan(chn, map);
                return "seeClan";
            }
            case -18886970: { //  取得自己的联盟
                __getOwnClan(chn, map);
                return "getOwnClan";
            }
            case -2095070594: { //  踢出联盟玩家
                __outClanMember(chn, map);
                return "outClanMember";
            }
            case -2132422106: { //  修改联盟描述
                __changeClan(chn, map);
                return "changeClan";
            }
            case -1229523588: { //  设置职位
                __allotClanPost(chn, map);
                return "allotClanPost";
            }
        }
        throw new Exception(" cmd: " + cmd + ":" + map + " not found processor.");
    }

    // //////////////////////////////////////////////
    // 解析参数
    // //////////////////////////////////////////////

    // 修改名称
    private void __rename(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int crystal = map2.getInt(1047561014);
        String uname = map2.getString(111425664);

        ReturnStatus rst = new ReturnStatus();
        onRename(chn, crystal, uname, rst);
        Map result = new NewMap();
        result.put(0, -934594754);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 登录玩家
    private void __loginUserByUid(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String uid = map2.getString(115792);
        String uuid = map2.getString(3601339);
        String pwd = map2.getString(111421);
        String name = map2.getString(3373707);
        String channel = map2.getString(738950403);
        String model = map2.getString(104069929);
        String version = map2.getString(351608024);
        int servid = map2.getInt(-905826383);
        NPlayers sign = new NPlayers();

        ReturnStatus rst = new ReturnStatus();
        onLoginUserByUid(chn, uid, uuid, pwd, name, channel, model, version, servid, sign, rst);
        Map result = new NewMap();
        result.put(0, 1615760901);
        result.put(1, rst.toMap());
        result.put(3530173, sign.toMap());
        chn.send(result);
    }

    // 新增建筑
    private void __createBuilding(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int client_id = map2.getInt(-1904089585);
        int gid = map2.getInt(102338);
        int pos = map2.getInt(111188);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        long cooldown_ms = map2.getLong(185446138);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onCreateBuilding(chn, client_id, gid, pos, resType, costVal, crystal, cooldown_ms, sign, rst);
        Map result = new NewMap();
        result.put(0, -508077104);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 完成移除
    private void __finishRemoveBuild(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int client_id = map2.getInt(-1904089585);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onFinishRemoveBuild(chn, client_id, crystal, sign, rst);
        Map result = new NewMap();
        result.put(0, 2115707319);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 自然完成完成造兵
    private void __finishProduceArmy(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int bcid = map2.getInt(3018012);
        NProductes lists = NProductes.parse(map2.getNewMap(102982549));
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onFinishProduceArmy(chn, bcid, lists, sign, rst);
        Map result = new NewMap();
        result.put(0, 929969162);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 快速完成完成造兵
    private void __fastFinishProduceArmy(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int bcid = map2.getInt(3018012);
        int crystal = map2.getInt(1047561014);
        NProductes lists = NProductes.parse(map2.getNewMap(102982549));
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onFastFinishProduceArmy(chn, bcid, crystal, lists, sign, rst);
        Map result = new NewMap();
        result.put(0, 1629150990);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // pve战利品
    private void __pveFightSpoils(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int cnpcid = map2.getInt(-1355658951);
        NEnergy energy = new NEnergy();

        ReturnStatus rst = new ReturnStatus();
        onPveFightSpoils(chn, cnpcid, energy, rst);
        Map result = new NewMap();
        result.put(0, 444729423);
        result.put(1, rst.toMap());
        result.put(-1298713976, energy.toMap());
        chn.send(result);
    }

    // 一般查询被攻击的角色
    private void __findBeAttactPlayer(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        NPlayer beAttPlayer = new NPlayer();
        NTeches bePTeches = new NTeches();
        NArmys bePArmys = new NArmys();
        NBuilds bePBuilds = new NBuilds();
        NHeros heros = new NHeros();
        NClan nclan = new NClan();
        NInt canGetGold = new NInt();
        NInt canGetOil = new NInt();

        ReturnStatus rst = new ReturnStatus();
        onFindBeAttactPlayer(chn, resType, costVal, crystal, sign, beAttPlayer, bePTeches, bePArmys, bePBuilds, heros, nclan, canGetGold, canGetOil, rst);
        Map result = new NewMap();
        result.put(0, -1056271730);
        result.put(1, rst.toMap());
        result.put(-1629162529, beAttPlayer.toMap());
        result.put(-619666287, bePTeches.toMap());
        result.put(794145289, bePArmys.toMap());
        result.put(-1120032110, bePBuilds.toMap());
        result.put(99168185, heros.toMap());
        result.put(104643524, nclan.toMap());
        result.put(-1479633178, canGetGold.toMap());
        result.put(-324817268, canGetOil.toMap());
        chn.send(result);
    }

    // 购买水晶，内部测试用
    private void __buyCrystalForOpenness(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int buyCrystalVal = map2.getInt(-797318351);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onBuyCrystalForOpenness(chn, buyCrystalVal, sign, rst);
        Map result = new NewMap();
        result.put(0, -1004167270);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 随机名称
    private void __randomPlayerName(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NStrVal randomName = new NStrVal();

        ReturnStatus rst = new ReturnStatus();
        onRandomPlayerName(chn, randomName, rst);
        Map result = new NewMap();
        result.put(0, 1723144751);
        result.put(1, rst.toMap());
        result.put(115456494, randomName.toMap());
        chn.send(result);
    }

    // 成就完成领奖
    private void __achievementReward(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int localCurId = map2.getInt(1277892304);
        int localNextId = map2.getInt(1260319417);
        NRewards lists = NRewards.parse(map2.getNewMap(102982549));

        ReturnStatus rst = new ReturnStatus();
        onAchievementReward(chn, localCurId, localNextId, lists, rst);
        Map result = new NewMap();
        result.put(0, 62679870);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 完成移除障碍
    private void __finishRemoveObst(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int ocid = map2.getInt(3405295);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onFinishRemoveObst(chn, ocid, crystal, sign, rst);
        Map result = new NewMap();
        result.put(0, 1176996619);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 下发的成就列表
    private void __getAchievements(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NAchievements marks = new NAchievements();

        ReturnStatus rst = new ReturnStatus();
        onGetAchievements(chn, marks, rst);
        Map result = new NewMap();
        result.put(0, -31856230);
        result.put(1, rst.toMap());
        result.put(103666502, marks.toMap());
        chn.send(result);
    }

    // 首次充值奖励
    private void __rewardFirstPay(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NRewards lists = NRewards.parse(map2.getNewMap(102982549));
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onRewardFirstPay(chn, lists, sign, rst);
        Map result = new NewMap();
        result.put(0, 1127496743);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 开宝箱
    private void __openTreasureBox(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int crystal = map2.getInt(1047561014);
        int num = map2.getInt(109446);
        String sign = map2.getString(3530173);
        NEnergys energys = new NEnergys();

        ReturnStatus rst = new ReturnStatus();
        onOpenTreasureBox(chn, crystal, num, sign, energys, rst);
        Map result = new NewMap();
        result.put(0, 1070317554);
        result.put(1, rst.toMap());
        result.put(-1605427477, energys.toMap());
        chn.send(result);
    }

    // 创建充值订单
    private void __createOrderPayMoney(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String strUni = map2.getString(-892005121);
        NStrVal oriderCid = new NStrVal();
        String phoneInfo = map2.getString(-1029329092);

        ReturnStatus rst = new ReturnStatus();
        onCreateOrderPayMoney(chn, strUni, oriderCid, phoneInfo, rst);
        Map result = new NewMap();
        result.put(0, 501903050);
        result.put(1, rst.toMap());
        result.put(-288806573, oriderCid.toMap());
        chn.send(result);
    }

    // 每日日常任务奖励
    private void __dayTasksReward(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int localDRID = map2.getInt(-1205708780);
        NRewards lists = NRewards.parse(map2.getNewMap(102982549));

        ReturnStatus rst = new ReturnStatus();
        onDayTasksReward(chn, localDRID, lists, rst);
        Map result = new NewMap();
        result.put(0, 52059937);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 取得个人总排行
    private void __getNRankUsersByAll(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int page = map2.getInt(3433103);
        NRankUsers rankUses = new NRankUsers();

        ReturnStatus rst = new ReturnStatus();
        onGetNRankUsersByAll(chn, page, rankUses, rst);
        Map result = new NewMap();
        result.put(0, -858116154);
        result.put(1, rst.toMap());
        result.put(256095768, rankUses.toMap());
        chn.send(result);
    }

    // 取得自己联盟成员
    private void __getOwnClanMember(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NClanMembers members = new NClanMembers();

        ReturnStatus rst = new ReturnStatus();
        onGetOwnClanMember(chn, members, rst);
        Map result = new NewMap();
        result.put(0, -762930944);
        result.put(1, rst.toMap());
        result.put(948881689, members.toMap());
        chn.send(result);
    }

    // 取得自己联盟请求
    private void __getOwnClanRequest(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NClanRequests clanreqes = new NClanRequests();

        ReturnStatus rst = new ReturnStatus();
        onGetOwnClanRequest(chn, clanreqes, rst);
        Map result = new NewMap();
        result.put(0, -2029211415);
        result.put(1, rst.toMap());
        result.put(-153257674, clanreqes.toMap());
        chn.send(result);
    }

    // 捐献金币
    private void __donateClanGold(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int dgold = map2.getInt(95530692);
        int curval = map2.getInt(-1349116703);
        int crystal = map2.getInt(1047561014);

        ReturnStatus rst = new ReturnStatus();
        onDonateClanGold(chn, dgold, curval, crystal, rst);
        Map result = new NewMap();
        result.put(0, -1149482139);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 取得联盟总排行
    private void __getNRankClanByAll(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int page = map2.getInt(3433103);
        NRankClans rankClans = new NRankClans();

        ReturnStatus rst = new ReturnStatus();
        onGetNRankClanByAll(chn, page, rankClans, rst);
        Map result = new NewMap();
        result.put(0, -858334864);
        result.put(1, rst.toMap());
        result.put(-667801583, rankClans.toMap());
        chn.send(result);
    }

    // 捐献油
    private void __donateClanOil(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int doil = map2.getInt(3089134);
        int curval = map2.getInt(-1349116703);
        int crystal = map2.getInt(1047561014);

        ReturnStatus rst = new ReturnStatus();
        onDonateClanOil(chn, doil, curval, crystal, rst);
        Map result = new NewMap();
        result.put(0, -868356563);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 取得联盟周排行
    private void __getNRankClan(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int page = map2.getInt(3433103);
        NRankClans rankClans = new NRankClans();

        ReturnStatus rst = new ReturnStatus();
        onGetNRankClan(chn, page, rankClans, rst);
        Map result = new NewMap();
        result.put(0, -940903302);
        result.put(1, rst.toMap());
        result.put(-667801583, rankClans.toMap());
        chn.send(result);
    }

    // 改变建筑等级
    private void __downBuildLvl(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int bcid = map2.getInt(3018012);
        int downlvl = map2.getInt(1847174208);

        ReturnStatus rst = new ReturnStatus();
        onDownBuildLvl(chn, bcid, downlvl, rst);
        Map result = new NewMap();
        result.put(0, -2102563434);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 渠道快速注册
    private void __cmRegistFast(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NStrVal outLgId = new NStrVal();
        NStrVal outLgPwd = new NStrVal();
        String chnstr = map2.getString(-1361243928);

        ReturnStatus rst = new ReturnStatus();
        onCmRegistFast(chn, outLgId, outLgPwd, chnstr, rst);
        Map result = new NewMap();
        result.put(0, 1717679356);
        result.put(1, rst.toMap());
        result.put(-1107201948, outLgId.toMap());
        result.put(36485396, outLgPwd.toMap());
        chn.send(result);
    }

    // 渠道注册
    private void __cmRegist(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String lgId = map2.getString(3318774);
        String lgPwd = map2.getString(102889410);
        String chnstr = map2.getString(-1361243928);

        ReturnStatus rst = new ReturnStatus();
        onCmRegist(chn, lgId, lgPwd, chnstr, rst);
        Map result = new NewMap();
        result.put(0, 1137223808);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 用户分享成功
    private void __shareSuccess(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;

        ReturnStatus rst = new ReturnStatus();
        onShareSuccess(chn, rst);
        Map result = new NewMap();
        result.put(0, 2009579300);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 打劫商船
    private void __lootMerchant(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String resType = map2.getString(1096575994);
        int resVal = map2.getInt(-934456735);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onLootMerchant(chn, resType, resVal, sign, rst);
        Map result = new NewMap();
        result.put(0, -827208560);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 登录奇虎360取得uid,token
    private void __loginQH360(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String strCode = map2.getString(-1882890306);
        NStrVal strToken = new NStrVal();
        NStrVal strUID = new NStrVal();

        ReturnStatus rst = new ReturnStatus();
        onLoginQH360(chn, strCode, strToken, strUID, rst);
        Map result = new NewMap();
        result.put(0, -1761920275);
        result.put(1, rst.toMap());
        result.put(1775649352, strToken.toMap());
        result.put(-892006305, strUID.toMap());
        chn.send(result);
    }

    // 玩家角色
    private void __loginUPlayer(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int SUPID = map2.getInt(79263689);
        NSession session = new NSession();
        NPlayer player = new NPlayer();
        NNPCBeFighteds npcs = new NNPCBeFighteds();
        NBuilds builds = new NBuilds();
        NTeches teches = new NTeches();
        NArmys armys = new NArmys();
        NProductes army_produtes = new NProductes();
        NSpells nspells = new NSpells();
        NHeros heros = new NHeros();

        ReturnStatus rst = new ReturnStatus();
        onLoginUPlayer(chn, SUPID, session, player, npcs, builds, teches, armys, army_produtes, nspells, heros, rst);
        Map result = new NewMap();
        result.put(0, -1450193907);
        result.put(1, rst.toMap());
        result.put(1984987798, session.toMap());
        result.put(-985752863, player.toMap());
        result.put(3387826, npcs.toMap());
        result.put(-1378023483, builds.toMap());
        result.put(-877657660, teches.toMap());
        result.put(93086326, armys.toMap());
        result.put(1919479750, army_produtes.toMap());
        result.put(-2054907335, nspells.toMap());
        result.put(99168185, heros.toMap());
        chn.send(result);
    }

    // 升级建筑
    private void __upBuilding(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int client_id = map2.getInt(-1904089585);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        long cooldown_ms = map2.getLong(185446138);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onUpBuilding(chn, client_id, resType, costVal, crystal, cooldown_ms, sign, rst);
        Map result = new NewMap();
        result.put(0, 840284783);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 完成升级建筑
    private void __finishBuild(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int client_id = map2.getInt(-1904089585);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onFinishBuild(chn, client_id, crystal, sign, rst);
        Map result = new NewMap();
        result.put(0, 1321151899);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 移除建筑
    private void __removeBuild(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int client_id = map2.getInt(-1904089585);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        long coowdown = map2.getLong(-535950858);
        String havType = map2.getString(699727735);
        int havVal = map2.getInt(-1224352956);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onRemoveBuild(chn, client_id, resType, costVal, crystal, coowdown, havType, havVal, sign, rst);
        Map result = new NewMap();
        result.put(0, -320303030);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 移动建筑
    private void __moveBuild(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int client_id = map2.getInt(-1904089585);
        int pos = map2.getInt(111188);

        ReturnStatus rst = new ReturnStatus();
        onMoveBuild(chn, client_id, pos, rst);
        Map result = new NewMap();
        result.put(0, 1041716509);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 收资源
    private void __harvestRes(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int b_client_id = map2.getInt(894389874);
        String resType = map2.getString(1096575994);
        int harVal = map2.getInt(-1224472120);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onHarvestRes(chn, b_client_id, resType, harVal, sign, rst);
        Map result = new NewMap();
        result.put(0, -1022537609);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 造兵和减少兵的接口
    private void __produceArmy(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int b_client_id = map2.getInt(894389874);
        int gid = map2.getInt(102338);
        int num = map2.getInt(109446);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onProduceArmy(chn, b_client_id, gid, num, resType, costVal, crystal, sign, rst);
        Map result = new NewMap();
        result.put(0, -1506041187);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 升级科技
    private void __upTechnolgy(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int b_client_id = map2.getInt(894389874);
        int gid = map2.getInt(102338);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        int upTime = map2.getInt(-839315448);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onUpTechnolgy(chn, b_client_id, gid, resType, costVal, crystal, upTime, sign, rst);
        Map result = new NewMap();
        result.put(0, -1145738292);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 完成升级科技
    private void __finishUpTech(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int b_client_id = map2.getInt(894389874);
        int gid = map2.getInt(102338);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onFinishUpTech(chn, b_client_id, gid, crystal, sign, rst);
        Map result = new NewMap();
        result.put(0, -1455260092);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // pve战斗放兵
    private void __pveAttackInfo(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int cur_npc_id = map2.getInt(-512671208);
        NAttackInfos deaths = NAttackInfos.parse(map2.getNewMap(-1335772033));

        ReturnStatus rst = new ReturnStatus();
        onPveAttackInfo(chn, cur_npc_id, deaths, rst);
        Map result = new NewMap();
        result.put(0, -1521297515);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 打pve结果
    private void __pveResult(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int cur_npc_id = map2.getInt(-512671208);
        int gold = map2.getInt(3178592);
        int oil = map2.getInt(110034);
        NNPCBeFighteds npcs = NNPCBeFighteds.parse(map2.getNewMap(3387826));
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onPveResult(chn, cur_npc_id, gold, oil, npcs, sign, rst);
        Map result = new NewMap();
        result.put(0, 809718812);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 开始战斗
    private void __beginAttact(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int attcid = map2.getInt(-1407256963);
        boolean isHitBack = map2.getBoolean(346575504);
        NHeros beHeros = NHeros.parse(map2.getNewMap(-257792714));
        NEnergy energy = new NEnergy();

        ReturnStatus rst = new ReturnStatus();
        onBeginAttact(chn, attcid, isHitBack, beHeros, energy, rst);
        Map result = new NewMap();
        result.put(0, -740593574);
        result.put(1, rst.toMap());
        result.put(-1298713976, energy.toMap());
        chn.send(result);
    }

    // 战斗信息
    private void __attactInfos(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int attcid = map2.getInt(-1407256963);
        NAttackInfos attInfos = NAttackInfos.parse(map2.getNewMap(516899684));
        NAttackInfos offenDeaths = NAttackInfos.parse(map2.getNewMap(674996471));
        NAttackInfos defenseDeaths = NAttackInfos.parse(map2.getNewMap(-1601665313));

        ReturnStatus rst = new ReturnStatus();
        onAttactInfos(chn, attcid, attInfos, offenDeaths, defenseDeaths, rst);
        Map result = new NewMap();
        result.put(0, -428433996);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 结束战斗
    private void __endAttact(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int attcid = map2.getInt(-1407256963);
        int star = map2.getInt(3540562);
        int renown = map2.getInt(-934580981);
        int defenceRenown = map2.getInt(979331451);
        int attGold = map2.getInt(-676120927);
        int attOil = map2.getInt(-1407276175);
        int maxGold = map2.getInt(843728868);
        int maxOil = map2.getInt(-1081154098);
        NAttackInfos attInfos = NAttackInfos.parse(map2.getNewMap(516899684));
        NBuildBlast bastBuilds = NBuildBlast.parse(map2.getNewMap(-876129595));
        NAttackInfos offenDeaths = NAttackInfos.parse(map2.getNewMap(674996471));
        NAttackInfos defenseDeaths = NAttackInfos.parse(map2.getNewMap(-1601665313));
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onEndAttact(chn, attcid, star, renown, defenceRenown, attGold, attOil, maxGold, maxOil, attInfos, bastBuilds, offenDeaths, defenseDeaths, sign, rst);
        Map result = new NewMap();
        result.put(0, 1093931788);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 回到主基地
    private void __goHome(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NPlayer player = new NPlayer();
        NNPCBeFighteds npcs = new NNPCBeFighteds();
        NBuilds builds = new NBuilds();
        NTeches teches = new NTeches();
        NArmys armys = new NArmys();
        NProductes produtes = new NProductes();
        NSpells nspells = new NSpells();
        NHeros heros = new NHeros();

        ReturnStatus rst = new ReturnStatus();
        onGoHome(chn, player, npcs, builds, teches, armys, produtes, nspells, heros, rst);
        Map result = new NewMap();
        result.put(0, -1241398809);
        result.put(1, rst.toMap());
        result.put(-985752863, player.toMap());
        result.put(3387826, npcs.toMap());
        result.put(-1378023483, builds.toMap());
        result.put(-877657660, teches.toMap());
        result.put(93086326, armys.toMap());
        result.put(-1003745436, produtes.toMap());
        result.put(-2054907335, nspells.toMap());
        result.put(99168185, heros.toMap());
        chn.send(result);
    }

    // 购买资源
    private void __buyRes(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String resType = map2.getString(1096575994);
        int buyVal = map2.getInt(-1377568069);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        NPlayer player = new NPlayer();

        ReturnStatus rst = new ReturnStatus();
        onBuyRes(chn, resType, buyVal, crystal, sign, player, rst);
        Map result = new NewMap();
        result.put(0, -1377571782);
        result.put(1, rst.toMap());
        result.put(-985752863, player.toMap());
        chn.send(result);
    }

    // 战斗回放
    private void __replayAttMail(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String mcid = map2.getString(3345713);
        NPlayer player = new NPlayer();
        NTeches teches = new NTeches();
        NArmys amrys = new NArmys();
        NBuilds builds = new NBuilds();
        NAttackInfos attInfo = new NAttackInfos();
        NLong timeSlot = new NLong();
        NHeros heros = new NHeros();
        NHeros offHeros = new NHeros();
        NClan offNclan = new NClan();
        NClan defNclan = new NClan();

        ReturnStatus rst = new ReturnStatus();
        onReplayAttMail(chn, mcid, player, teches, amrys, builds, attInfo, timeSlot, heros, offHeros, offNclan, defNclan, rst);
        Map result = new NewMap();
        result.put(0, -188503535);
        result.put(1, rst.toMap());
        result.put(-985752863, player.toMap());
        result.put(-877657660, teches.toMap());
        result.put(92942176, amrys.toMap());
        result.put(-1378023483, builds.toMap());
        result.put(-676062481, attInfo.toMap());
        result.put(-2077392277, timeSlot.toMap());
        result.put(99168185, heros.toMap());
        result.put(-795697270, offHeros.toMap());
        result.put(-790221931, offNclan.toMap());
        result.put(626197183, defNclan.toMap());
        chn.send(result);
    }

    // 反击
    private void __hitBack(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String mcid = map2.getString(3345713);
        NPlayer beAttPlayer = new NPlayer();
        NTeches bePTeches = new NTeches();
        NArmys bePArmys = new NArmys();
        NBuilds builds = new NBuilds();
        NHeros heros = new NHeros();
        NClan nclan = new NClan();

        ReturnStatus rst = new ReturnStatus();
        onHitBack(chn, mcid, beAttPlayer, bePTeches, bePArmys, builds, heros, nclan, rst);
        Map result = new NewMap();
        result.put(0, 926354202);
        result.put(1, rst.toMap());
        result.put(-1629162529, beAttPlayer.toMap());
        result.put(-619666287, bePTeches.toMap());
        result.put(794145289, bePArmys.toMap());
        result.put(-1378023483, builds.toMap());
        result.put(99168185, heros.toMap());
        result.put(104643524, nclan.toMap());
        chn.send(result);
    }

    // 心跳
    private void __heart(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NInt heat = new NInt();

        ReturnStatus rst = new ReturnStatus();
        onHeart(chn, heat, rst);
        Map result = new NewMap();
        result.put(0, 99151942);
        result.put(1, rst.toMap());
        result.put(3198448, heat.toMap());
        chn.send(result);
    }

    // 设置名字
    private void __setPlayerName(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String uname = map2.getString(111425664);

        ReturnStatus rst = new ReturnStatus();
        onSetPlayerName(chn, uname, rst);
        Map result = new NewMap();
        result.put(0, -1722927346);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 修复陷阱
    private void __repairTrap(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int b_c_id = map2.getInt(-1398532044);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onRepairTrap(chn, b_c_id, resType, costVal, crystal, sign, rst);
        Map result = new NewMap();
        result.put(0, 2131720282);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 用户下线
    private void __downLine(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;

        ReturnStatus rst = new ReturnStatus();
        onDownLine(chn, rst);
        Map result = new NewMap();
        result.put(0, 1426859958);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 新手步骤
    private void __guidNewPlayer(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int guid = map2.getInt(3184265);

        ReturnStatus rst = new ReturnStatus();
        onGuidNewPlayer(chn, guid, rst);
        Map result = new NewMap();
        result.put(0, 1203719192);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 聊天内容
    private void __sendChat(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NChat newChat = NChat.parse(map2.getNewMap(1844699416));

        ReturnStatus rst = new ReturnStatus();
        onSendChat(chn, newChat, rst);
        Map result = new NewMap();
        result.put(0, 1246941952);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 购买序列
    private void __buyOrder(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onBuyOrder(chn, crystal, sign, rst);
        Map result = new NewMap();
        result.put(0, -998949784);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 下发战报邮件
    private void __getAttMails(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NAttMails attMails = new NAttMails();

        ReturnStatus rst = new ReturnStatus();
        onGetAttMails(chn, attMails, rst);
        Map result = new NewMap();
        result.put(0, -1696857871);
        result.put(1, rst.toMap());
        result.put(520209275, attMails.toMap());
        chn.send(result);
    }

    // 下发邮件
    private void __getNMails(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NMails nmails = new NMails();

        ReturnStatus rst = new ReturnStatus();
        onGetNMails(chn, nmails, rst);
        Map result = new NewMap();
        result.put(0, 624359620);
        result.put(1, rst.toMap());
        result.put(-1042102802, nmails.toMap());
        chn.send(result);
    }

    // 下发聊天信息
    private void __getNChats(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NChats nchats = new NChats();

        ReturnStatus rst = new ReturnStatus();
        onGetNChats(chn, nchats, rst);
        Map result = new NewMap();
        result.put(0, 615325507);
        result.put(1, rst.toMap());
        result.put(-1051136915, nchats.toMap());
        chn.send(result);
    }

    // 新邮件
    private void __sendMail(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NMail newMail = NMail.parse(map2.getNewMap(1844990839));

        ReturnStatus rst = new ReturnStatus();
        onSendMail(chn, newMail, rst);
        Map result = new NewMap();
        result.put(0, 1247233375);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 结束引导
    private void __finishGuid(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int guid = map2.getInt(3184265);
        NPlayer player = new NPlayer();
        NBuilds builds = new NBuilds();

        ReturnStatus rst = new ReturnStatus();
        onFinishGuid(chn, guid, player, builds, rst);
        Map result = new NewMap();
        result.put(0, 1151145404);
        result.put(1, rst.toMap());
        result.put(-985752863, player.toMap());
        result.put(-1378023483, builds.toMap());
        chn.send(result);
    }

    // 购买法术
    private void __buySpell(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        int gid = map2.getInt(102338);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onBuySpell(chn, resType, costVal, crystal, gid, sign, rst);
        Map result = new NewMap();
        result.put(0, -995314110);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 增加经验
    private void __addExp(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int addVal = map2.getInt(-1422522688);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onAddExp(chn, addVal, sign, rst);
        Map result = new NewMap();
        result.put(0, -1422538308);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 新增障碍
    private void __addObst(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int ocid = map2.getInt(3405295);
        int gid = map2.getInt(102338);
        int pos = map2.getInt(111188);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onAddObst(chn, ocid, gid, pos, sign, rst);
        Map result = new NewMap();
        result.put(0, -1148737611);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 新增障碍城墙
    private void __addObstWall(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int ocid = map2.getInt(3405295);
        int gid = map2.getInt(102338);
        int pos = map2.getInt(111188);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        long cooldown_ms = map2.getLong(185446138);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onAddObstWall(chn, ocid, gid, pos, resType, costVal, crystal, cooldown_ms, sign, rst);
        Map result = new NewMap();
        result.put(0, -612644065);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 升级障碍-城墙
    private void __upObstWall(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NInts ocids = NInts.parse(map2.getNewMap(105564260));
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onUpObstWall(chn, ocids, resType, costVal, crystal, sign, rst);
        Map result = new NewMap();
        result.put(0, -1662400743);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 移除障碍
    private void __removeObst(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int ocid = map2.getInt(3405295);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        long coowdown = map2.getLong(-535950858);
        String havType = map2.getString(699727735);
        int havVal = map2.getInt(-1224352956);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onRemoveObst(chn, ocid, resType, costVal, crystal, coowdown, havType, havVal, sign, rst);
        Map result = new NewMap();
        result.put(0, 1098415640);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 移动物体
    private void __moveObst(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int ocid = map2.getInt(3405295);
        int pos = map2.getInt(111188);

        ReturnStatus rst = new ReturnStatus();
        onMoveObst(chn, ocid, pos, rst);
        Map result = new NewMap();
        result.put(0, -104574235);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 改变邮件状态为__已读
    private void __readNMail(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int mailId = map2.getInt(-1081574094);

        ReturnStatus rst = new ReturnStatus();
        onReadNMail(chn, mailId, rst);
        Map result = new NewMap();
        result.put(0, -1130309809);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 访问别人
    private void __viewPlayer(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int pid = map2.getInt(110987);
        NPlayer player = new NPlayer();
        NBuilds builds = new NBuilds();
        NTeches teches = new NTeches();
        NArmys armys = new NArmys();
        NHeros heros = new NHeros();

        ReturnStatus rst = new ReturnStatus();
        onViewPlayer(chn, pid, player, builds, teches, armys, heros, rst);
        Map result = new NewMap();
        result.put(0, -1955892762);
        result.put(1, rst.toMap());
        result.put(-985752863, player.toMap());
        result.put(-1378023483, builds.toMap());
        result.put(-877657660, teches.toMap());
        result.put(93086326, armys.toMap());
        result.put(99168185, heros.toMap());
        chn.send(result);
    }

    // 购买保护盾
    private void __buyShield(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int crystal = map2.getInt(1047561014);
        long addTime = map2.getLong(-1148582130);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onBuyShield(chn, crystal, addTime, sign, rst);
        Map result = new NewMap();
        result.put(0, -797241969);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 取得用户信息
    private void __getPInfo(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NPInfo pinfo = new NPInfo();

        ReturnStatus rst = new ReturnStatus();
        onGetPInfo(chn, pinfo, rst);
        Map result = new NewMap();
        result.put(0, 1961543560);
        result.put(1, rst.toMap());
        result.put(106671390, pinfo.toMap());
        chn.send(result);
    }

    // 领每日登录奖励
    private void __rewardDay(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NRewards lists = NRewards.parse(map2.getNewMap(102982549));
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onRewardDay(chn, lists, sign, rst);
        Map result = new NewMap();
        result.put(0, 1162918381);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 购买英雄
    private void __buyHero(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int hgid = map2.getInt(3200602);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onBuyHero(chn, hgid, crystal, sign, rst);
        Map result = new NewMap();
        result.put(0, 244649888);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 买活已死英雄
    private void __liveHero(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int hgid = map2.getInt(3200602);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);

        ReturnStatus rst = new ReturnStatus();
        onLiveHero(chn, hgid, crystal, sign, rst);
        Map result = new NewMap();
        result.put(0, 1417322246);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 英雄死亡
    private void __deadHero(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int hgid = map2.getInt(3200602);

        ReturnStatus rst = new ReturnStatus();
        onDeadHero(chn, hgid, rst);
        Map result = new NewMap();
        result.put(0, 502558334);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 升级后英雄的数据下发
    private void __refreshHeros(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NHeros heors = new NHeros();

        ReturnStatus rst = new ReturnStatus();
        onRefreshHeros(chn, heors, rst);
        Map result = new NewMap();
        result.put(0, -67879522);
        result.put(1, rst.toMap());
        result.put(99165395, heors.toMap());
        chn.send(result);
    }

    // 增加英雄能源:力量,血量,攻击速度
    private void __addHeroEnergy(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int hgid = map2.getInt(3200602);
        int egid = map2.getInt(3111229);
        int costGold = map2.getInt(-425069107);
        int costOil = map2.getInt(956126917);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        NInt outHgid = new NInt();
        NInt outEgid = new NInt();
        NInt curEnergyNum = new NInt();
        NInt addval = new NInt();

        ReturnStatus rst = new ReturnStatus();
        onAddHeroEnergy(chn, hgid, egid, costGold, costOil, crystal, sign, outHgid, outEgid, curEnergyNum, addval, rst);
        Map result = new NewMap();
        result.put(0, 1813858979);
        result.put(1, rst.toMap());
        result.put(-1107320120, outHgid.toMap());
        result.put(-1107409493, outEgid.toMap());
        result.put(258062718, curEnergyNum.toMap());
        result.put(-1422491936, addval.toMap());
        chn.send(result);
    }

    // 取得个人周排行
    private void __getNRankUse(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int page = map2.getInt(3433103);
        NRankUsers rankUses = new NRankUsers();

        ReturnStatus rst = new ReturnStatus();
        onGetNRankUse(chn, page, rankUses, rst);
        Map result = new NewMap();
        result.put(0, -1138712861);
        result.put(1, rst.toMap());
        result.put(256095768, rankUses.toMap());
        chn.send(result);
    }

    // 创建联盟
    private void __creatClan(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int icon = map2.getInt(3226745);
        String cname = map2.getString(94802286);
        String cdesc = map2.getString(94508404);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        NStrVal ccid = new NStrVal();

        ReturnStatus rst = new ReturnStatus();
        onCreatClan(chn, icon, cname, cdesc, resType, costVal, crystal, ccid, rst);
        Map result = new NewMap();
        result.put(0, 597367423);
        result.put(1, rst.toMap());
        result.put(3047803, ccid.toMap());
        chn.send(result);
    }

    // 退出联盟
    private void __exitClan(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String ccid = map2.getString(3047803);

        ReturnStatus rst = new ReturnStatus();
        onExitClan(chn, ccid, rst);
        Map result = new NewMap();
        result.put(0, -2123231084);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 申请入盟
    private void __joinInClan(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String ccid = map2.getString(3047803);

        ReturnStatus rst = new ReturnStatus();
        onJoinInClan(chn, ccid, rst);
        Map result = new NewMap();
        result.put(0, 1226689253);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 接受入盟
    private void __acceptJoinIn(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String ccid = map2.getString(3047803);
        int pcid = map2.getInt(3435086);

        ReturnStatus rst = new ReturnStatus();
        onAcceptJoinIn(chn, ccid, pcid, rst);
        Map result = new NewMap();
        result.put(0, 1704066839);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 拒绝入盟
    private void __refuseJoinIn(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String ccid = map2.getString(3047803);
        int pcid = map2.getInt(3435086);

        ReturnStatus rst = new ReturnStatus();
        onRefuseJoinIn(chn, ccid, pcid, rst);
        Map result = new NewMap();
        result.put(0, 1366339875);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 查找联盟
    private void __searchClan(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String cname = map2.getString(94802286);
        NClans clans = new NClans();

        ReturnStatus rst = new ReturnStatus();
        onSearchClan(chn, cname, clans, rst);
        Map result = new NewMap();
        result.put(0, -710973410);
        result.put(1, rst.toMap());
        result.put(94742749, clans.toMap());
        chn.send(result);
    }

    // 查看联盟
    private void __seeClan(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String ccid = map2.getString(3047803);
        NClan clan = new NClan();
        NClanMembers members = new NClanMembers();

        ReturnStatus rst = new ReturnStatus();
        onSeeClan(chn, ccid, clan, members, rst);
        Map result = new NewMap();
        result.put(0, 1970630985);
        result.put(1, rst.toMap());
        result.put(3056214, clan.toMap());
        result.put(948881689, members.toMap());
        chn.send(result);
    }

    // 取得自己的联盟
    private void __getOwnClan(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NClan clan = new NClan();

        ReturnStatus rst = new ReturnStatus();
        onGetOwnClan(chn, clan, rst);
        Map result = new NewMap();
        result.put(0, -18886970);
        result.put(1, rst.toMap());
        result.put(3056214, clan.toMap());
        chn.send(result);
    }

    // 踢出联盟玩家
    private void __outClanMember(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int pcid = map2.getInt(3435086);

        ReturnStatus rst = new ReturnStatus();
        onOutClanMember(chn, pcid, rst);
        Map result = new NewMap();
        result.put(0, -2095070594);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 修改联盟描述
    private void __changeClan(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String desc = map2.getString(3079825);

        ReturnStatus rst = new ReturnStatus();
        onChangeClan(chn, desc, rst);
        Map result = new NewMap();
        result.put(0, -2132422106);
        result.put(1, rst.toMap());
        chn.send(result);
    }

    // 设置职位
    private void __allotClanPost(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int pcid = map2.getInt(3435086);
        int post = map2.getInt(3446944);

        ReturnStatus rst = new ReturnStatus();
        onAllotClanPost(chn, pcid, post, rst);
        Map result = new NewMap();
        result.put(0, -1229523588);
        result.put(1, rst.toMap());
        chn.send(result);
    }


    // //////////////////////////////////////////////
    // 需要实现的接口
    // //////////////////////////////////////////////

    // 修改名称
    public abstract ReturnStatus onRename(TcpChannel chn , int crystal, String uname, ReturnStatus ret) throws Exception;
    // 登录玩家
    public abstract ReturnStatus onLoginUserByUid(TcpChannel chn , String uid, String uuid, String pwd, String name, String channel, String model, String version, int servid, NPlayers sign, ReturnStatus ret) throws Exception;
    // 新增建筑
    public abstract ReturnStatus onCreateBuilding(TcpChannel chn , int client_id, int gid, int pos, String resType, int costVal, int crystal, long cooldown_ms, String sign, ReturnStatus ret) throws Exception;
    // 完成移除
    public abstract ReturnStatus onFinishRemoveBuild(TcpChannel chn , int client_id, int crystal, String sign, ReturnStatus ret) throws Exception;
    // 自然完成完成造兵
    public abstract ReturnStatus onFinishProduceArmy(TcpChannel chn , int bcid, NProductes lists, String sign, ReturnStatus ret) throws Exception;
    // 快速完成完成造兵
    public abstract ReturnStatus onFastFinishProduceArmy(TcpChannel chn , int bcid, int crystal, NProductes lists, String sign, ReturnStatus ret) throws Exception;
    // pve战利品
    public abstract ReturnStatus onPveFightSpoils(TcpChannel chn , int cnpcid, NEnergy energy, ReturnStatus ret) throws Exception;
    // 一般查询被攻击的角色
    public abstract ReturnStatus onFindBeAttactPlayer(TcpChannel chn , String resType, int costVal, int crystal, String sign, NPlayer beAttPlayer, NTeches bePTeches, NArmys bePArmys, NBuilds bePBuilds, NHeros heros, NClan nclan, NInt canGetGold, NInt canGetOil, ReturnStatus ret) throws Exception;
    // 购买水晶，内部测试用
    public abstract ReturnStatus onBuyCrystalForOpenness(TcpChannel chn , int buyCrystalVal, String sign, ReturnStatus ret) throws Exception;
    // 随机名称
    public abstract ReturnStatus onRandomPlayerName(TcpChannel chn , NStrVal randomName, ReturnStatus ret) throws Exception;
    // 成就完成领奖
    public abstract ReturnStatus onAchievementReward(TcpChannel chn , int localCurId, int localNextId, NRewards lists, ReturnStatus ret) throws Exception;
    // 完成移除障碍
    public abstract ReturnStatus onFinishRemoveObst(TcpChannel chn , int ocid, int crystal, String sign, ReturnStatus ret) throws Exception;
    // 下发的成就列表
    public abstract ReturnStatus onGetAchievements(TcpChannel chn , NAchievements marks, ReturnStatus ret) throws Exception;
    // 首次充值奖励
    public abstract ReturnStatus onRewardFirstPay(TcpChannel chn , NRewards lists, String sign, ReturnStatus ret) throws Exception;
    // 开宝箱
    public abstract ReturnStatus onOpenTreasureBox(TcpChannel chn , int crystal, int num, String sign, NEnergys energys, ReturnStatus ret) throws Exception;
    // 创建充值订单
    public abstract ReturnStatus onCreateOrderPayMoney(TcpChannel chn , String strUni, NStrVal oriderCid, String phoneInfo, ReturnStatus ret) throws Exception;
    // 每日日常任务奖励
    public abstract ReturnStatus onDayTasksReward(TcpChannel chn , int localDRID, NRewards lists, ReturnStatus ret) throws Exception;
    // 取得个人总排行
    public abstract ReturnStatus onGetNRankUsersByAll(TcpChannel chn , int page, NRankUsers rankUses, ReturnStatus ret) throws Exception;
    // 取得自己联盟成员
    public abstract ReturnStatus onGetOwnClanMember(TcpChannel chn , NClanMembers members, ReturnStatus ret) throws Exception;
    // 取得自己联盟请求
    public abstract ReturnStatus onGetOwnClanRequest(TcpChannel chn , NClanRequests clanreqes, ReturnStatus ret) throws Exception;
    // 捐献金币
    public abstract ReturnStatus onDonateClanGold(TcpChannel chn , int dgold, int curval, int crystal, ReturnStatus ret) throws Exception;
    // 取得联盟总排行
    public abstract ReturnStatus onGetNRankClanByAll(TcpChannel chn , int page, NRankClans rankClans, ReturnStatus ret) throws Exception;
    // 捐献油
    public abstract ReturnStatus onDonateClanOil(TcpChannel chn , int doil, int curval, int crystal, ReturnStatus ret) throws Exception;
    // 取得联盟周排行
    public abstract ReturnStatus onGetNRankClan(TcpChannel chn , int page, NRankClans rankClans, ReturnStatus ret) throws Exception;
    // 改变建筑等级
    public abstract ReturnStatus onDownBuildLvl(TcpChannel chn , int bcid, int downlvl, ReturnStatus ret) throws Exception;
    // 渠道快速注册
    public abstract ReturnStatus onCmRegistFast(TcpChannel chn , NStrVal outLgId, NStrVal outLgPwd, String chnstr, ReturnStatus ret) throws Exception;
    // 渠道注册
    public abstract ReturnStatus onCmRegist(TcpChannel chn , String lgId, String lgPwd, String chnstr, ReturnStatus ret) throws Exception;
    // 用户分享成功
    public abstract ReturnStatus onShareSuccess(TcpChannel chn , ReturnStatus ret) throws Exception;
    // 打劫商船
    public abstract ReturnStatus onLootMerchant(TcpChannel chn , String resType, int resVal, String sign, ReturnStatus ret) throws Exception;
    // 登录奇虎360取得uid,token
    public abstract ReturnStatus onLoginQH360(TcpChannel chn , String strCode, NStrVal strToken, NStrVal strUID, ReturnStatus ret) throws Exception;
    // 玩家角色
    public abstract ReturnStatus onLoginUPlayer(TcpChannel chn , int SUPID, NSession session, NPlayer player, NNPCBeFighteds npcs, NBuilds builds, NTeches teches, NArmys armys, NProductes army_produtes, NSpells nspells, NHeros heros, ReturnStatus ret) throws Exception;
    // 升级建筑
    public abstract ReturnStatus onUpBuilding(TcpChannel chn , int client_id, String resType, int costVal, int crystal, long cooldown_ms, String sign, ReturnStatus ret) throws Exception;
    // 完成升级建筑
    public abstract ReturnStatus onFinishBuild(TcpChannel chn , int client_id, int crystal, String sign, ReturnStatus ret) throws Exception;
    // 移除建筑
    public abstract ReturnStatus onRemoveBuild(TcpChannel chn , int client_id, String resType, int costVal, int crystal, long coowdown, String havType, int havVal, String sign, ReturnStatus ret) throws Exception;
    // 移动建筑
    public abstract ReturnStatus onMoveBuild(TcpChannel chn , int client_id, int pos, ReturnStatus ret) throws Exception;
    // 收资源
    public abstract ReturnStatus onHarvestRes(TcpChannel chn , int b_client_id, String resType, int harVal, String sign, ReturnStatus ret) throws Exception;
    // 造兵和减少兵的接口
    public abstract ReturnStatus onProduceArmy(TcpChannel chn , int b_client_id, int gid, int num, String resType, int costVal, int crystal, String sign, ReturnStatus ret) throws Exception;
    // 升级科技
    public abstract ReturnStatus onUpTechnolgy(TcpChannel chn , int b_client_id, int gid, String resType, int costVal, int crystal, int upTime, String sign, ReturnStatus ret) throws Exception;
    // 完成升级科技
    public abstract ReturnStatus onFinishUpTech(TcpChannel chn , int b_client_id, int gid, int crystal, String sign, ReturnStatus ret) throws Exception;
    // pve战斗放兵
    public abstract ReturnStatus onPveAttackInfo(TcpChannel chn , int cur_npc_id, NAttackInfos deaths, ReturnStatus ret) throws Exception;
    // 打pve结果
    public abstract ReturnStatus onPveResult(TcpChannel chn , int cur_npc_id, int gold, int oil, NNPCBeFighteds npcs, String sign, ReturnStatus ret) throws Exception;
    // 开始战斗
    public abstract ReturnStatus onBeginAttact(TcpChannel chn , int attcid, boolean isHitBack, NHeros beHeros, NEnergy energy, ReturnStatus ret) throws Exception;
    // 战斗信息
    public abstract ReturnStatus onAttactInfos(TcpChannel chn , int attcid, NAttackInfos attInfos, NAttackInfos offenDeaths, NAttackInfos defenseDeaths, ReturnStatus ret) throws Exception;
    // 结束战斗
    public abstract ReturnStatus onEndAttact(TcpChannel chn , int attcid, int star, int renown, int defenceRenown, int attGold, int attOil, int maxGold, int maxOil, NAttackInfos attInfos, NBuildBlast bastBuilds, NAttackInfos offenDeaths, NAttackInfos defenseDeaths, String sign, ReturnStatus ret) throws Exception;
    // 回到主基地
    public abstract ReturnStatus onGoHome(TcpChannel chn , NPlayer player, NNPCBeFighteds npcs, NBuilds builds, NTeches teches, NArmys armys, NProductes produtes, NSpells nspells, NHeros heros, ReturnStatus ret) throws Exception;
    // 购买资源
    public abstract ReturnStatus onBuyRes(TcpChannel chn , String resType, int buyVal, int crystal, String sign, NPlayer player, ReturnStatus ret) throws Exception;
    // 战斗回放
    public abstract ReturnStatus onReplayAttMail(TcpChannel chn , String mcid, NPlayer player, NTeches teches, NArmys amrys, NBuilds builds, NAttackInfos attInfo, NLong timeSlot, NHeros heros, NHeros offHeros, NClan offNclan, NClan defNclan, ReturnStatus ret) throws Exception;
    // 反击
    public abstract ReturnStatus onHitBack(TcpChannel chn , String mcid, NPlayer beAttPlayer, NTeches bePTeches, NArmys bePArmys, NBuilds builds, NHeros heros, NClan nclan, ReturnStatus ret) throws Exception;
    // 心跳
    public abstract ReturnStatus onHeart(TcpChannel chn , NInt heat, ReturnStatus ret) throws Exception;
    // 设置名字
    public abstract ReturnStatus onSetPlayerName(TcpChannel chn , String uname, ReturnStatus ret) throws Exception;
    // 修复陷阱
    public abstract ReturnStatus onRepairTrap(TcpChannel chn , int b_c_id, String resType, int costVal, int crystal, String sign, ReturnStatus ret) throws Exception;
    // 用户下线
    public abstract ReturnStatus onDownLine(TcpChannel chn , ReturnStatus ret) throws Exception;
    // 新手步骤
    public abstract ReturnStatus onGuidNewPlayer(TcpChannel chn , int guid, ReturnStatus ret) throws Exception;
    // 聊天内容
    public abstract ReturnStatus onSendChat(TcpChannel chn , NChat newChat, ReturnStatus ret) throws Exception;
    // 购买序列
    public abstract ReturnStatus onBuyOrder(TcpChannel chn , int crystal, String sign, ReturnStatus ret) throws Exception;
    // 下发战报邮件
    public abstract ReturnStatus onGetAttMails(TcpChannel chn , NAttMails attMails, ReturnStatus ret) throws Exception;
    // 下发邮件
    public abstract ReturnStatus onGetNMails(TcpChannel chn , NMails nmails, ReturnStatus ret) throws Exception;
    // 下发聊天信息
    public abstract ReturnStatus onGetNChats(TcpChannel chn , NChats nchats, ReturnStatus ret) throws Exception;
    // 新邮件
    public abstract ReturnStatus onSendMail(TcpChannel chn , NMail newMail, ReturnStatus ret) throws Exception;
    // 结束引导
    public abstract ReturnStatus onFinishGuid(TcpChannel chn , int guid, NPlayer player, NBuilds builds, ReturnStatus ret) throws Exception;
    // 购买法术
    public abstract ReturnStatus onBuySpell(TcpChannel chn , String resType, int costVal, int crystal, int gid, String sign, ReturnStatus ret) throws Exception;
    // 增加经验
    public abstract ReturnStatus onAddExp(TcpChannel chn , int addVal, String sign, ReturnStatus ret) throws Exception;
    // 新增障碍
    public abstract ReturnStatus onAddObst(TcpChannel chn , int ocid, int gid, int pos, String sign, ReturnStatus ret) throws Exception;
    // 新增障碍城墙
    public abstract ReturnStatus onAddObstWall(TcpChannel chn , int ocid, int gid, int pos, String resType, int costVal, int crystal, long cooldown_ms, String sign, ReturnStatus ret) throws Exception;
    // 升级障碍-城墙
    public abstract ReturnStatus onUpObstWall(TcpChannel chn , NInts ocids, String resType, int costVal, int crystal, String sign, ReturnStatus ret) throws Exception;
    // 移除障碍
    public abstract ReturnStatus onRemoveObst(TcpChannel chn , int ocid, String resType, int costVal, int crystal, long coowdown, String havType, int havVal, String sign, ReturnStatus ret) throws Exception;
    // 移动物体
    public abstract ReturnStatus onMoveObst(TcpChannel chn , int ocid, int pos, ReturnStatus ret) throws Exception;
    // 改变邮件状态为__已读
    public abstract ReturnStatus onReadNMail(TcpChannel chn , int mailId, ReturnStatus ret) throws Exception;
    // 访问别人
    public abstract ReturnStatus onViewPlayer(TcpChannel chn , int pid, NPlayer player, NBuilds builds, NTeches teches, NArmys armys, NHeros heros, ReturnStatus ret) throws Exception;
    // 购买保护盾
    public abstract ReturnStatus onBuyShield(TcpChannel chn , int crystal, long addTime, String sign, ReturnStatus ret) throws Exception;
    // 取得用户信息
    public abstract ReturnStatus onGetPInfo(TcpChannel chn , NPInfo pinfo, ReturnStatus ret) throws Exception;
    // 领每日登录奖励
    public abstract ReturnStatus onRewardDay(TcpChannel chn , NRewards lists, String sign, ReturnStatus ret) throws Exception;
    // 购买英雄
    public abstract ReturnStatus onBuyHero(TcpChannel chn , int hgid, int crystal, String sign, ReturnStatus ret) throws Exception;
    // 买活已死英雄
    public abstract ReturnStatus onLiveHero(TcpChannel chn , int hgid, int crystal, String sign, ReturnStatus ret) throws Exception;
    // 英雄死亡
    public abstract ReturnStatus onDeadHero(TcpChannel chn , int hgid, ReturnStatus ret) throws Exception;
    // 升级后英雄的数据下发
    public abstract ReturnStatus onRefreshHeros(TcpChannel chn , NHeros heors, ReturnStatus ret) throws Exception;
    // 增加英雄能源:力量,血量,攻击速度
    public abstract ReturnStatus onAddHeroEnergy(TcpChannel chn , int hgid, int egid, int costGold, int costOil, int crystal, String sign, NInt outHgid, NInt outEgid, NInt curEnergyNum, NInt addval, ReturnStatus ret) throws Exception;
    // 取得个人周排行
    public abstract ReturnStatus onGetNRankUse(TcpChannel chn , int page, NRankUsers rankUses, ReturnStatus ret) throws Exception;
    // 创建联盟
    public abstract ReturnStatus onCreatClan(TcpChannel chn , int icon, String cname, String cdesc, String resType, int costVal, int crystal, NStrVal ccid, ReturnStatus ret) throws Exception;
    // 退出联盟
    public abstract ReturnStatus onExitClan(TcpChannel chn , String ccid, ReturnStatus ret) throws Exception;
    // 申请入盟
    public abstract ReturnStatus onJoinInClan(TcpChannel chn , String ccid, ReturnStatus ret) throws Exception;
    // 接受入盟
    public abstract ReturnStatus onAcceptJoinIn(TcpChannel chn , String ccid, int pcid, ReturnStatus ret) throws Exception;
    // 拒绝入盟
    public abstract ReturnStatus onRefuseJoinIn(TcpChannel chn , String ccid, int pcid, ReturnStatus ret) throws Exception;
    // 查找联盟
    public abstract ReturnStatus onSearchClan(TcpChannel chn , String cname, NClans clans, ReturnStatus ret) throws Exception;
    // 查看联盟
    public abstract ReturnStatus onSeeClan(TcpChannel chn , String ccid, NClan clan, NClanMembers members, ReturnStatus ret) throws Exception;
    // 取得自己的联盟
    public abstract ReturnStatus onGetOwnClan(TcpChannel chn , NClan clan, ReturnStatus ret) throws Exception;
    // 踢出联盟玩家
    public abstract ReturnStatus onOutClanMember(TcpChannel chn , int pcid, ReturnStatus ret) throws Exception;
    // 修改联盟描述
    public abstract ReturnStatus onChangeClan(TcpChannel chn , String desc, ReturnStatus ret) throws Exception;
    // 设置职位
    public abstract ReturnStatus onAllotClanPost(TcpChannel chn , int pcid, int post, ReturnStatus ret) throws Exception;


    // //////////////////////////////////////////////
    // PV操作 - 逻辑分发
    // //////////////////////////////////////////////

    public String _params(NewMap map) throws Exception {
        int cmd = map.getInt(0);
        return _params(cmd, map);
    }
    public String _params(int cmd, NewMap map) throws Exception {
        switch (cmd) {
            case -934594754: { //  修改名称
                return __rename_params(map);
            }
            case 1615760901: { //  登录玩家
                return __loginUserByUid_params(map);
            }
            case -508077104: { //  新增建筑
                return __createBuilding_params(map);
            }
            case 2115707319: { //  完成移除
                return __finishRemoveBuild_params(map);
            }
            case 929969162: { //  自然完成完成造兵
                return __finishProduceArmy_params(map);
            }
            case 1629150990: { //  快速完成完成造兵
                return __fastFinishProduceArmy_params(map);
            }
            case 444729423: { //  pve战利品
                return __pveFightSpoils_params(map);
            }
            case -1056271730: { //  一般查询被攻击的角色
                return __findBeAttactPlayer_params(map);
            }
            case -1004167270: { //  购买水晶，内部测试用
                return __buyCrystalForOpenness_params(map);
            }
            case 1723144751: { //  随机名称
                return __randomPlayerName_params(map);
            }
            case 62679870: { //  成就完成领奖
                return __achievementReward_params(map);
            }
            case 1176996619: { //  完成移除障碍
                return __finishRemoveObst_params(map);
            }
            case -31856230: { //  下发的成就列表
                return __getAchievements_params(map);
            }
            case 1127496743: { //  首次充值奖励
                return __rewardFirstPay_params(map);
            }
            case 1070317554: { //  开宝箱
                return __openTreasureBox_params(map);
            }
            case 501903050: { //  创建充值订单
                return __createOrderPayMoney_params(map);
            }
            case 52059937: { //  每日日常任务奖励
                return __dayTasksReward_params(map);
            }
            case -858116154: { //  取得个人总排行
                return __getNRankUsersByAll_params(map);
            }
            case -762930944: { //  取得自己联盟成员
                return __getOwnClanMember_params(map);
            }
            case -2029211415: { //  取得自己联盟请求
                return __getOwnClanRequest_params(map);
            }
            case -1149482139: { //  捐献金币
                return __donateClanGold_params(map);
            }
            case -858334864: { //  取得联盟总排行
                return __getNRankClanByAll_params(map);
            }
            case -868356563: { //  捐献油
                return __donateClanOil_params(map);
            }
            case -940903302: { //  取得联盟周排行
                return __getNRankClan_params(map);
            }
            case -2102563434: { //  改变建筑等级
                return __downBuildLvl_params(map);
            }
            case 1717679356: { //  渠道快速注册
                return __cmRegistFast_params(map);
            }
            case 1137223808: { //  渠道注册
                return __cmRegist_params(map);
            }
            case 2009579300: { //  用户分享成功
                return __shareSuccess_params(map);
            }
            case -827208560: { //  打劫商船
                return __lootMerchant_params(map);
            }
            case -1761920275: { //  登录奇虎360取得uid,token
                return __loginQH360_params(map);
            }
            case -1450193907: { //  玩家角色
                return __loginUPlayer_params(map);
            }
            case 840284783: { //  升级建筑
                return __upBuilding_params(map);
            }
            case 1321151899: { //  完成升级建筑
                return __finishBuild_params(map);
            }
            case -320303030: { //  移除建筑
                return __removeBuild_params(map);
            }
            case 1041716509: { //  移动建筑
                return __moveBuild_params(map);
            }
            case -1022537609: { //  收资源
                return __harvestRes_params(map);
            }
            case -1506041187: { //  造兵和减少兵的接口
                return __produceArmy_params(map);
            }
            case -1145738292: { //  升级科技
                return __upTechnolgy_params(map);
            }
            case -1455260092: { //  完成升级科技
                return __finishUpTech_params(map);
            }
            case -1521297515: { //  pve战斗放兵
                return __pveAttackInfo_params(map);
            }
            case 809718812: { //  打pve结果
                return __pveResult_params(map);
            }
            case -740593574: { //  开始战斗
                return __beginAttact_params(map);
            }
            case -428433996: { //  战斗信息
                return __attactInfos_params(map);
            }
            case 1093931788: { //  结束战斗
                return __endAttact_params(map);
            }
            case -1241398809: { //  回到主基地
                return __goHome_params(map);
            }
            case -1377571782: { //  购买资源
                return __buyRes_params(map);
            }
            case -188503535: { //  战斗回放
                return __replayAttMail_params(map);
            }
            case 926354202: { //  反击
                return __hitBack_params(map);
            }
            case 99151942: { //  心跳
                return __heart_params(map);
            }
            case -1722927346: { //  设置名字
                return __setPlayerName_params(map);
            }
            case 2131720282: { //  修复陷阱
                return __repairTrap_params(map);
            }
            case 1426859958: { //  用户下线
                return __downLine_params(map);
            }
            case 1203719192: { //  新手步骤
                return __guidNewPlayer_params(map);
            }
            case 1246941952: { //  聊天内容
                return __sendChat_params(map);
            }
            case -998949784: { //  购买序列
                return __buyOrder_params(map);
            }
            case -1696857871: { //  下发战报邮件
                return __getAttMails_params(map);
            }
            case 624359620: { //  下发邮件
                return __getNMails_params(map);
            }
            case 615325507: { //  下发聊天信息
                return __getNChats_params(map);
            }
            case 1247233375: { //  新邮件
                return __sendMail_params(map);
            }
            case 1151145404: { //  结束引导
                return __finishGuid_params(map);
            }
            case -995314110: { //  购买法术
                return __buySpell_params(map);
            }
            case -1422538308: { //  增加经验
                return __addExp_params(map);
            }
            case -1148737611: { //  新增障碍
                return __addObst_params(map);
            }
            case -612644065: { //  新增障碍城墙
                return __addObstWall_params(map);
            }
            case -1662400743: { //  升级障碍-城墙
                return __upObstWall_params(map);
            }
            case 1098415640: { //  移除障碍
                return __removeObst_params(map);
            }
            case -104574235: { //  移动物体
                return __moveObst_params(map);
            }
            case -1130309809: { //  改变邮件状态为__已读
                return __readNMail_params(map);
            }
            case -1955892762: { //  访问别人
                return __viewPlayer_params(map);
            }
            case -797241969: { //  购买保护盾
                return __buyShield_params(map);
            }
            case 1961543560: { //  取得用户信息
                return __getPInfo_params(map);
            }
            case 1162918381: { //  领每日登录奖励
                return __rewardDay_params(map);
            }
            case 244649888: { //  购买英雄
                return __buyHero_params(map);
            }
            case 1417322246: { //  买活已死英雄
                return __liveHero_params(map);
            }
            case 502558334: { //  英雄死亡
                return __deadHero_params(map);
            }
            case -67879522: { //  升级后英雄的数据下发
                return __refreshHeros_params(map);
            }
            case 1813858979: { //  增加英雄能源:力量,血量,攻击速度
                return __addHeroEnergy_params(map);
            }
            case -1138712861: { //  取得个人周排行
                return __getNRankUse_params(map);
            }
            case 597367423: { //  创建联盟
                return __creatClan_params(map);
            }
            case -2123231084: { //  退出联盟
                return __exitClan_params(map);
            }
            case 1226689253: { //  申请入盟
                return __joinInClan_params(map);
            }
            case 1704066839: { //  接受入盟
                return __acceptJoinIn_params(map);
            }
            case 1366339875: { //  拒绝入盟
                return __refuseJoinIn_params(map);
            }
            case -710973410: { //  查找联盟
                return __searchClan_params(map);
            }
            case 1970630985: { //  查看联盟
                return __seeClan_params(map);
            }
            case -18886970: { //  取得自己的联盟
                return __getOwnClan_params(map);
            }
            case -2095070594: { //  踢出联盟玩家
                return __outClanMember_params(map);
            }
            case -2132422106: { //  修改联盟描述
                return __changeClan_params(map);
            }
            case -1229523588: { //  设置职位
                return __allotClanPost_params(map);
            }
        }
        throw new Exception(" cmd: " + cmd + ":" + map + " not found processor.");
    }

    // //////////////////////////////////////////////
    // PV操作 - 解析参数 X
    // //////////////////////////////////////////////

    // 修改名称
    private String __rename_params(NewMap map2) throws Exception {
        int crystal = map2.getInt(1047561014);
        String uname = map2.getString(111425664);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("rename(");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"uname\":").append(uname).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 登录玩家
    private String __loginUserByUid_params(NewMap map2) throws Exception {
        String uid = map2.getString(115792);
        String uuid = map2.getString(3601339);
        String pwd = map2.getString(111421);
        String name = map2.getString(3373707);
        String channel = map2.getString(738950403);
        String model = map2.getString(104069929);
        String version = map2.getString(351608024);
        int servid = map2.getInt(-905826383);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("loginUserByUid(");
            sb.append("\"uid\":").append(uid).append(",");
            sb.append("\"uuid\":").append(uuid).append(",");
            sb.append("\"pwd\":").append(pwd).append(",");
            sb.append("\"name\":").append(name).append(",");
            sb.append("\"channel\":").append(channel).append(",");
            sb.append("\"model\":").append(model).append(",");
            sb.append("\"version\":").append(version).append(",");
            sb.append("\"servid\":").append(servid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 新增建筑
    private String __createBuilding_params(NewMap map2) throws Exception {
        int client_id = map2.getInt(-1904089585);
        int gid = map2.getInt(102338);
        int pos = map2.getInt(111188);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        long cooldown_ms = map2.getLong(185446138);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("createBuilding(");
            sb.append("\"client_id\":").append(client_id).append(",");
            sb.append("\"gid\":").append(gid).append(",");
            sb.append("\"pos\":").append(pos).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"cooldown_ms\":").append(cooldown_ms).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 完成移除
    private String __finishRemoveBuild_params(NewMap map2) throws Exception {
        int client_id = map2.getInt(-1904089585);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("finishRemoveBuild(");
            sb.append("\"client_id\":").append(client_id).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 自然完成完成造兵
    private String __finishProduceArmy_params(NewMap map2) throws Exception {
        int bcid = map2.getInt(3018012);
        NProductes lists = NProductes.parse(map2.getNewMap(102982549));
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("finishProduceArmy(");
            sb.append("\"bcid\":").append(bcid).append(",");
            sb.append("\"lists\":").append(lists).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 快速完成完成造兵
    private String __fastFinishProduceArmy_params(NewMap map2) throws Exception {
        int bcid = map2.getInt(3018012);
        int crystal = map2.getInt(1047561014);
        NProductes lists = NProductes.parse(map2.getNewMap(102982549));
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("fastFinishProduceArmy(");
            sb.append("\"bcid\":").append(bcid).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"lists\":").append(lists).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // pve战利品
    private String __pveFightSpoils_params(NewMap map2) throws Exception {
        int cnpcid = map2.getInt(-1355658951);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("pveFightSpoils(");
            sb.append("\"cnpcid\":").append(cnpcid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 一般查询被攻击的角色
    private String __findBeAttactPlayer_params(NewMap map2) throws Exception {
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("findBeAttactPlayer(");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 购买水晶，内部测试用
    private String __buyCrystalForOpenness_params(NewMap map2) throws Exception {
        int buyCrystalVal = map2.getInt(-797318351);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("buyCrystalForOpenness(");
            sb.append("\"buyCrystalVal\":").append(buyCrystalVal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 随机名称
    private String __randomPlayerName_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("randomPlayerName(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 成就完成领奖
    private String __achievementReward_params(NewMap map2) throws Exception {
        int localCurId = map2.getInt(1277892304);
        int localNextId = map2.getInt(1260319417);
        NRewards lists = NRewards.parse(map2.getNewMap(102982549));
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("achievementReward(");
            sb.append("\"localCurId\":").append(localCurId).append(",");
            sb.append("\"localNextId\":").append(localNextId).append(",");
            sb.append("\"lists\":").append(lists).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 完成移除障碍
    private String __finishRemoveObst_params(NewMap map2) throws Exception {
        int ocid = map2.getInt(3405295);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("finishRemoveObst(");
            sb.append("\"ocid\":").append(ocid).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 下发的成就列表
    private String __getAchievements_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getAchievements(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 首次充值奖励
    private String __rewardFirstPay_params(NewMap map2) throws Exception {
        NRewards lists = NRewards.parse(map2.getNewMap(102982549));
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("rewardFirstPay(");
            sb.append("\"lists\":").append(lists).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 开宝箱
    private String __openTreasureBox_params(NewMap map2) throws Exception {
        int crystal = map2.getInt(1047561014);
        int num = map2.getInt(109446);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("openTreasureBox(");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"num\":").append(num).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 创建充值订单
    private String __createOrderPayMoney_params(NewMap map2) throws Exception {
        String strUni = map2.getString(-892005121);
        String phoneInfo = map2.getString(-1029329092);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("createOrderPayMoney(");
            sb.append("\"strUni\":").append(strUni).append(",");
            sb.append("\"phoneInfo\":").append(phoneInfo).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 每日日常任务奖励
    private String __dayTasksReward_params(NewMap map2) throws Exception {
        int localDRID = map2.getInt(-1205708780);
        NRewards lists = NRewards.parse(map2.getNewMap(102982549));
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("dayTasksReward(");
            sb.append("\"localDRID\":").append(localDRID).append(",");
            sb.append("\"lists\":").append(lists).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得个人总排行
    private String __getNRankUsersByAll_params(NewMap map2) throws Exception {
        int page = map2.getInt(3433103);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getNRankUsersByAll(");
            sb.append("\"page\":").append(page).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得自己联盟成员
    private String __getOwnClanMember_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getOwnClanMember(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得自己联盟请求
    private String __getOwnClanRequest_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getOwnClanRequest(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 捐献金币
    private String __donateClanGold_params(NewMap map2) throws Exception {
        int dgold = map2.getInt(95530692);
        int curval = map2.getInt(-1349116703);
        int crystal = map2.getInt(1047561014);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("donateClanGold(");
            sb.append("\"dgold\":").append(dgold).append(",");
            sb.append("\"curval\":").append(curval).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得联盟总排行
    private String __getNRankClanByAll_params(NewMap map2) throws Exception {
        int page = map2.getInt(3433103);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getNRankClanByAll(");
            sb.append("\"page\":").append(page).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 捐献油
    private String __donateClanOil_params(NewMap map2) throws Exception {
        int doil = map2.getInt(3089134);
        int curval = map2.getInt(-1349116703);
        int crystal = map2.getInt(1047561014);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("donateClanOil(");
            sb.append("\"doil\":").append(doil).append(",");
            sb.append("\"curval\":").append(curval).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得联盟周排行
    private String __getNRankClan_params(NewMap map2) throws Exception {
        int page = map2.getInt(3433103);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getNRankClan(");
            sb.append("\"page\":").append(page).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 改变建筑等级
    private String __downBuildLvl_params(NewMap map2) throws Exception {
        int bcid = map2.getInt(3018012);
        int downlvl = map2.getInt(1847174208);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("downBuildLvl(");
            sb.append("\"bcid\":").append(bcid).append(",");
            sb.append("\"downlvl\":").append(downlvl).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 渠道快速注册
    private String __cmRegistFast_params(NewMap map2) throws Exception {
        String chnstr = map2.getString(-1361243928);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("cmRegistFast(");
            sb.append("\"chnstr\":").append(chnstr).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 渠道注册
    private String __cmRegist_params(NewMap map2) throws Exception {
        String lgId = map2.getString(3318774);
        String lgPwd = map2.getString(102889410);
        String chnstr = map2.getString(-1361243928);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("cmRegist(");
            sb.append("\"lgId\":").append(lgId).append(",");
            sb.append("\"lgPwd\":").append(lgPwd).append(",");
            sb.append("\"chnstr\":").append(chnstr).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 用户分享成功
    private String __shareSuccess_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("shareSuccess(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 打劫商船
    private String __lootMerchant_params(NewMap map2) throws Exception {
        String resType = map2.getString(1096575994);
        int resVal = map2.getInt(-934456735);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("lootMerchant(");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"resVal\":").append(resVal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 登录奇虎360取得uid,token
    private String __loginQH360_params(NewMap map2) throws Exception {
        String strCode = map2.getString(-1882890306);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("loginQH360(");
            sb.append("\"strCode\":").append(strCode).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 玩家角色
    private String __loginUPlayer_params(NewMap map2) throws Exception {
        int SUPID = map2.getInt(79263689);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("loginUPlayer(");
            sb.append("\"SUPID\":").append(SUPID).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 升级建筑
    private String __upBuilding_params(NewMap map2) throws Exception {
        int client_id = map2.getInt(-1904089585);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        long cooldown_ms = map2.getLong(185446138);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("upBuilding(");
            sb.append("\"client_id\":").append(client_id).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"cooldown_ms\":").append(cooldown_ms).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 完成升级建筑
    private String __finishBuild_params(NewMap map2) throws Exception {
        int client_id = map2.getInt(-1904089585);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("finishBuild(");
            sb.append("\"client_id\":").append(client_id).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 移除建筑
    private String __removeBuild_params(NewMap map2) throws Exception {
        int client_id = map2.getInt(-1904089585);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        long coowdown = map2.getLong(-535950858);
        String havType = map2.getString(699727735);
        int havVal = map2.getInt(-1224352956);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("removeBuild(");
            sb.append("\"client_id\":").append(client_id).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"coowdown\":").append(coowdown).append(",");
            sb.append("\"havType\":").append(havType).append(",");
            sb.append("\"havVal\":").append(havVal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 移动建筑
    private String __moveBuild_params(NewMap map2) throws Exception {
        int client_id = map2.getInt(-1904089585);
        int pos = map2.getInt(111188);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("moveBuild(");
            sb.append("\"client_id\":").append(client_id).append(",");
            sb.append("\"pos\":").append(pos).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 收资源
    private String __harvestRes_params(NewMap map2) throws Exception {
        int b_client_id = map2.getInt(894389874);
        String resType = map2.getString(1096575994);
        int harVal = map2.getInt(-1224472120);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("harvestRes(");
            sb.append("\"b_client_id\":").append(b_client_id).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"harVal\":").append(harVal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 造兵和减少兵的接口
    private String __produceArmy_params(NewMap map2) throws Exception {
        int b_client_id = map2.getInt(894389874);
        int gid = map2.getInt(102338);
        int num = map2.getInt(109446);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("produceArmy(");
            sb.append("\"b_client_id\":").append(b_client_id).append(",");
            sb.append("\"gid\":").append(gid).append(",");
            sb.append("\"num\":").append(num).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 升级科技
    private String __upTechnolgy_params(NewMap map2) throws Exception {
        int b_client_id = map2.getInt(894389874);
        int gid = map2.getInt(102338);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        int upTime = map2.getInt(-839315448);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("upTechnolgy(");
            sb.append("\"b_client_id\":").append(b_client_id).append(",");
            sb.append("\"gid\":").append(gid).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"upTime\":").append(upTime).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 完成升级科技
    private String __finishUpTech_params(NewMap map2) throws Exception {
        int b_client_id = map2.getInt(894389874);
        int gid = map2.getInt(102338);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("finishUpTech(");
            sb.append("\"b_client_id\":").append(b_client_id).append(",");
            sb.append("\"gid\":").append(gid).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // pve战斗放兵
    private String __pveAttackInfo_params(NewMap map2) throws Exception {
        int cur_npc_id = map2.getInt(-512671208);
        NAttackInfos deaths = NAttackInfos.parse(map2.getNewMap(-1335772033));
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("pveAttackInfo(");
            sb.append("\"cur_npc_id\":").append(cur_npc_id).append(",");
            sb.append("\"deaths\":").append(deaths).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 打pve结果
    private String __pveResult_params(NewMap map2) throws Exception {
        int cur_npc_id = map2.getInt(-512671208);
        int gold = map2.getInt(3178592);
        int oil = map2.getInt(110034);
        NNPCBeFighteds npcs = NNPCBeFighteds.parse(map2.getNewMap(3387826));
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("pveResult(");
            sb.append("\"cur_npc_id\":").append(cur_npc_id).append(",");
            sb.append("\"gold\":").append(gold).append(",");
            sb.append("\"oil\":").append(oil).append(",");
            sb.append("\"npcs\":").append(npcs).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 开始战斗
    private String __beginAttact_params(NewMap map2) throws Exception {
        int attcid = map2.getInt(-1407256963);
        boolean isHitBack = map2.getBoolean(346575504);
        NHeros beHeros = NHeros.parse(map2.getNewMap(-257792714));
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("beginAttact(");
            sb.append("\"attcid\":").append(attcid).append(",");
            sb.append("\"isHitBack\":").append(isHitBack).append(",");
            sb.append("\"beHeros\":").append(beHeros).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 战斗信息
    private String __attactInfos_params(NewMap map2) throws Exception {
        int attcid = map2.getInt(-1407256963);
        NAttackInfos attInfos = NAttackInfos.parse(map2.getNewMap(516899684));
        NAttackInfos offenDeaths = NAttackInfos.parse(map2.getNewMap(674996471));
        NAttackInfos defenseDeaths = NAttackInfos.parse(map2.getNewMap(-1601665313));
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("attactInfos(");
            sb.append("\"attcid\":").append(attcid).append(",");
            sb.append("\"attInfos\":").append(attInfos).append(",");
            sb.append("\"offenDeaths\":").append(offenDeaths).append(",");
            sb.append("\"defenseDeaths\":").append(defenseDeaths).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 结束战斗
    private String __endAttact_params(NewMap map2) throws Exception {
        int attcid = map2.getInt(-1407256963);
        int star = map2.getInt(3540562);
        int renown = map2.getInt(-934580981);
        int defenceRenown = map2.getInt(979331451);
        int attGold = map2.getInt(-676120927);
        int attOil = map2.getInt(-1407276175);
        int maxGold = map2.getInt(843728868);
        int maxOil = map2.getInt(-1081154098);
        NAttackInfos attInfos = NAttackInfos.parse(map2.getNewMap(516899684));
        NBuildBlast bastBuilds = NBuildBlast.parse(map2.getNewMap(-876129595));
        NAttackInfos offenDeaths = NAttackInfos.parse(map2.getNewMap(674996471));
        NAttackInfos defenseDeaths = NAttackInfos.parse(map2.getNewMap(-1601665313));
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("endAttact(");
            sb.append("\"attcid\":").append(attcid).append(",");
            sb.append("\"star\":").append(star).append(",");
            sb.append("\"renown\":").append(renown).append(",");
            sb.append("\"defenceRenown\":").append(defenceRenown).append(",");
            sb.append("\"attGold\":").append(attGold).append(",");
            sb.append("\"attOil\":").append(attOil).append(",");
            sb.append("\"maxGold\":").append(maxGold).append(",");
            sb.append("\"maxOil\":").append(maxOil).append(",");
            sb.append("\"attInfos\":").append(attInfos).append(",");
            sb.append("\"bastBuilds\":").append(bastBuilds).append(",");
            sb.append("\"offenDeaths\":").append(offenDeaths).append(",");
            sb.append("\"defenseDeaths\":").append(defenseDeaths).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 回到主基地
    private String __goHome_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("goHome(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 购买资源
    private String __buyRes_params(NewMap map2) throws Exception {
        String resType = map2.getString(1096575994);
        int buyVal = map2.getInt(-1377568069);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("buyRes(");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"buyVal\":").append(buyVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 战斗回放
    private String __replayAttMail_params(NewMap map2) throws Exception {
        String mcid = map2.getString(3345713);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("replayAttMail(");
            sb.append("\"mcid\":").append(mcid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 反击
    private String __hitBack_params(NewMap map2) throws Exception {
        String mcid = map2.getString(3345713);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("hitBack(");
            sb.append("\"mcid\":").append(mcid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 心跳
    private String __heart_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("heart(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 设置名字
    private String __setPlayerName_params(NewMap map2) throws Exception {
        String uname = map2.getString(111425664);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("setPlayerName(");
            sb.append("\"uname\":").append(uname).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 修复陷阱
    private String __repairTrap_params(NewMap map2) throws Exception {
        int b_c_id = map2.getInt(-1398532044);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("repairTrap(");
            sb.append("\"b_c_id\":").append(b_c_id).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 用户下线
    private String __downLine_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("downLine(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 新手步骤
    private String __guidNewPlayer_params(NewMap map2) throws Exception {
        int guid = map2.getInt(3184265);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("guidNewPlayer(");
            sb.append("\"guid\":").append(guid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 聊天内容
    private String __sendChat_params(NewMap map2) throws Exception {
        NChat newChat = NChat.parse(map2.getNewMap(1844699416));
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("sendChat(");
            sb.append("\"newChat\":").append(newChat).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 购买序列
    private String __buyOrder_params(NewMap map2) throws Exception {
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("buyOrder(");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 下发战报邮件
    private String __getAttMails_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getAttMails(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 下发邮件
    private String __getNMails_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getNMails(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 下发聊天信息
    private String __getNChats_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getNChats(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 新邮件
    private String __sendMail_params(NewMap map2) throws Exception {
        NMail newMail = NMail.parse(map2.getNewMap(1844990839));
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("sendMail(");
            sb.append("\"newMail\":").append(newMail).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 结束引导
    private String __finishGuid_params(NewMap map2) throws Exception {
        int guid = map2.getInt(3184265);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("finishGuid(");
            sb.append("\"guid\":").append(guid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 购买法术
    private String __buySpell_params(NewMap map2) throws Exception {
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        int gid = map2.getInt(102338);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("buySpell(");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"gid\":").append(gid).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 增加经验
    private String __addExp_params(NewMap map2) throws Exception {
        int addVal = map2.getInt(-1422522688);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("addExp(");
            sb.append("\"addVal\":").append(addVal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 新增障碍
    private String __addObst_params(NewMap map2) throws Exception {
        int ocid = map2.getInt(3405295);
        int gid = map2.getInt(102338);
        int pos = map2.getInt(111188);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("addObst(");
            sb.append("\"ocid\":").append(ocid).append(",");
            sb.append("\"gid\":").append(gid).append(",");
            sb.append("\"pos\":").append(pos).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 新增障碍城墙
    private String __addObstWall_params(NewMap map2) throws Exception {
        int ocid = map2.getInt(3405295);
        int gid = map2.getInt(102338);
        int pos = map2.getInt(111188);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        long cooldown_ms = map2.getLong(185446138);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("addObstWall(");
            sb.append("\"ocid\":").append(ocid).append(",");
            sb.append("\"gid\":").append(gid).append(",");
            sb.append("\"pos\":").append(pos).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"cooldown_ms\":").append(cooldown_ms).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 升级障碍-城墙
    private String __upObstWall_params(NewMap map2) throws Exception {
        NInts ocids = NInts.parse(map2.getNewMap(105564260));
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("upObstWall(");
            sb.append("\"ocids\":").append(ocids).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 移除障碍
    private String __removeObst_params(NewMap map2) throws Exception {
        int ocid = map2.getInt(3405295);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        long coowdown = map2.getLong(-535950858);
        String havType = map2.getString(699727735);
        int havVal = map2.getInt(-1224352956);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("removeObst(");
            sb.append("\"ocid\":").append(ocid).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"coowdown\":").append(coowdown).append(",");
            sb.append("\"havType\":").append(havType).append(",");
            sb.append("\"havVal\":").append(havVal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 移动物体
    private String __moveObst_params(NewMap map2) throws Exception {
        int ocid = map2.getInt(3405295);
        int pos = map2.getInt(111188);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("moveObst(");
            sb.append("\"ocid\":").append(ocid).append(",");
            sb.append("\"pos\":").append(pos).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 改变邮件状态为__已读
    private String __readNMail_params(NewMap map2) throws Exception {
        int mailId = map2.getInt(-1081574094);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("readNMail(");
            sb.append("\"mailId\":").append(mailId).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 访问别人
    private String __viewPlayer_params(NewMap map2) throws Exception {
        int pid = map2.getInt(110987);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("viewPlayer(");
            sb.append("\"pid\":").append(pid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 购买保护盾
    private String __buyShield_params(NewMap map2) throws Exception {
        int crystal = map2.getInt(1047561014);
        long addTime = map2.getLong(-1148582130);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("buyShield(");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"addTime\":").append(addTime).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得用户信息
    private String __getPInfo_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getPInfo(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 领每日登录奖励
    private String __rewardDay_params(NewMap map2) throws Exception {
        NRewards lists = NRewards.parse(map2.getNewMap(102982549));
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("rewardDay(");
            sb.append("\"lists\":").append(lists).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 购买英雄
    private String __buyHero_params(NewMap map2) throws Exception {
        int hgid = map2.getInt(3200602);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("buyHero(");
            sb.append("\"hgid\":").append(hgid).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 买活已死英雄
    private String __liveHero_params(NewMap map2) throws Exception {
        int hgid = map2.getInt(3200602);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("liveHero(");
            sb.append("\"hgid\":").append(hgid).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 英雄死亡
    private String __deadHero_params(NewMap map2) throws Exception {
        int hgid = map2.getInt(3200602);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("deadHero(");
            sb.append("\"hgid\":").append(hgid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 升级后英雄的数据下发
    private String __refreshHeros_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("refreshHeros(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 增加英雄能源:力量,血量,攻击速度
    private String __addHeroEnergy_params(NewMap map2) throws Exception {
        int hgid = map2.getInt(3200602);
        int egid = map2.getInt(3111229);
        int costGold = map2.getInt(-425069107);
        int costOil = map2.getInt(956126917);
        int crystal = map2.getInt(1047561014);
        String sign = map2.getString(3530173);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("addHeroEnergy(");
            sb.append("\"hgid\":").append(hgid).append(",");
            sb.append("\"egid\":").append(egid).append(",");
            sb.append("\"costGold\":").append(costGold).append(",");
            sb.append("\"costOil\":").append(costOil).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append("\"sign\":").append(sign).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得个人周排行
    private String __getNRankUse_params(NewMap map2) throws Exception {
        int page = map2.getInt(3433103);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getNRankUse(");
            sb.append("\"page\":").append(page).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 创建联盟
    private String __creatClan_params(NewMap map2) throws Exception {
        int icon = map2.getInt(3226745);
        String cname = map2.getString(94802286);
        String cdesc = map2.getString(94508404);
        String resType = map2.getString(1096575994);
        int costVal = map2.getInt(956133396);
        int crystal = map2.getInt(1047561014);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("creatClan(");
            sb.append("\"icon\":").append(icon).append(",");
            sb.append("\"cname\":").append(cname).append(",");
            sb.append("\"cdesc\":").append(cdesc).append(",");
            sb.append("\"resType\":").append(resType).append(",");
            sb.append("\"costVal\":").append(costVal).append(",");
            sb.append("\"crystal\":").append(crystal).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 退出联盟
    private String __exitClan_params(NewMap map2) throws Exception {
        String ccid = map2.getString(3047803);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("exitClan(");
            sb.append("\"ccid\":").append(ccid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 申请入盟
    private String __joinInClan_params(NewMap map2) throws Exception {
        String ccid = map2.getString(3047803);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("joinInClan(");
            sb.append("\"ccid\":").append(ccid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 接受入盟
    private String __acceptJoinIn_params(NewMap map2) throws Exception {
        String ccid = map2.getString(3047803);
        int pcid = map2.getInt(3435086);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("acceptJoinIn(");
            sb.append("\"ccid\":").append(ccid).append(",");
            sb.append("\"pcid\":").append(pcid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 拒绝入盟
    private String __refuseJoinIn_params(NewMap map2) throws Exception {
        String ccid = map2.getString(3047803);
        int pcid = map2.getInt(3435086);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("refuseJoinIn(");
            sb.append("\"ccid\":").append(ccid).append(",");
            sb.append("\"pcid\":").append(pcid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 查找联盟
    private String __searchClan_params(NewMap map2) throws Exception {
        String cname = map2.getString(94802286);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("searchClan(");
            sb.append("\"cname\":").append(cname).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 查看联盟
    private String __seeClan_params(NewMap map2) throws Exception {
        String ccid = map2.getString(3047803);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("seeClan(");
            sb.append("\"ccid\":").append(ccid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得自己的联盟
    private String __getOwnClan_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getOwnClan(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 踢出联盟玩家
    private String __outClanMember_params(NewMap map2) throws Exception {
        int pcid = map2.getInt(3435086);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("outClanMember(");
            sb.append("\"pcid\":").append(pcid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 修改联盟描述
    private String __changeClan_params(NewMap map2) throws Exception {
        String desc = map2.getString(3079825);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("changeClan(");
            sb.append("\"desc\":").append(desc).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 设置职位
    private String __allotClanPost_params(NewMap map2) throws Exception {
        int pcid = map2.getInt(3435086);
        int post = map2.getInt(3446944);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("allotClanPost(");
            sb.append("\"pcid\":").append(pcid).append(",");
            sb.append("\"post\":").append(post).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
}
