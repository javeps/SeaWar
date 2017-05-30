package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NPlayer {
    public static final int _CID = -1396075313;

    public int pid; 
    public int uid; 
    public int type; 
    public int serverId; 
    public String pname = ""; 
    public int icon; 
    public int lvl; 
    public long crystal; 
    public int renown; 
    public int cur_npc_local_id; 
    public String npcs = ""; 
    public int all_pay_momey; 
    public int last_pay_money; 
    public long last_pay_time; 
    public int guid_step; 
    public String clancid = ""; 
    public String cname = ""; 
    public int cicon; 
    public int maxBuidId; 
    public int maxObstId; 
    public long stored_oil; 
    public long stored_gold; 
    public int maxAttMailId; 
    public long protectTime; 
    public long sys_time; 
    public int maxOrderNum; 
    public long lastAddObst; // 最后一次增加障碍时间
    public long rankRewardTime; // 个人排行奖励时间点
    public int dayLogin; // 连续登录天数
    public boolean isReward; // 是否已经领奖
    public int firstPayStatus; // 首次充值领奖状态1表示成功未领奖
    public int pieceHPNum; // 生命之源
    public int pieceAttNum; // 攻击之源
    public int pieceSpeedNum; // 速度之源
    public String dayTasks = ""; // 当天已领任务
    public long lastLeaveClan; // 离开联盟的时间
    public long monCode; // 月卡结束时间点


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(110987, pid);
        r.put(115792, uid);
        r.put(3575610, type);
        r.put(1379103678, serverId);
        r.put(106808059, pname);
        r.put(3226745, icon);
        r.put(107554, lvl);
        r.put(1047561014, crystal);
        r.put(-934580981, renown);
        r.put(-980510292, cur_npc_local_id);
        r.put(3387826, npcs);
        r.put(-105336438, all_pay_momey);
        r.put(541777920, last_pay_money);
        r.put(1957342093, last_pay_time);
        r.put(-2081040702, guid_step);
        r.put(853456552, clancid);
        r.put(94802286, cname);
        r.put(94655324, cicon);
        r.put(-1063068435, maxBuidId);
        r.put(-708123085, maxObstId);
        r.put(925923126, stored_oil);
        r.put(-1361386628, stored_gold);
        r.put(1818292783, maxAttMailId);
        r.put(-691626436, protectTime);
        r.put(1957135615, sys_time);
        r.put(-1028380964, maxOrderNum);
        r.put(735426623, lastAddObst);
        r.put(1509214184, rankRewardTime);
        r.put(1913609517, dayLogin);
        r.put(-260292359, isReward);
        r.put(435462634, firstPayStatus);
        r.put(-779598032, pieceHPNum);
        r.put(1436202419, pieceAttNum);
        r.put(-1146905907, pieceSpeedNum);
        r.put(1920592210, dayTasks);
        r.put(-133609545, lastLeaveClan);
        r.put(1235182361, monCode);
        return r;
    }


    public static NPlayer parse(NewMap map2) {
        if(map2 == null) return null;

        NPlayer r = new NPlayer();
        r.pid = map2.getInt(110987);
        r.uid = map2.getInt(115792);
        r.type = map2.getInt(3575610);
        r.serverId = map2.getInt(1379103678);
        r.pname = map2.getString(106808059);
        r.icon = map2.getInt(3226745);
        r.lvl = map2.getInt(107554);
        r.crystal = map2.getLong(1047561014);
        r.renown = map2.getInt(-934580981);
        r.cur_npc_local_id = map2.getInt(-980510292);
        r.npcs = map2.getString(3387826);
        r.all_pay_momey = map2.getInt(-105336438);
        r.last_pay_money = map2.getInt(541777920);
        r.last_pay_time = map2.getLong(1957342093);
        r.guid_step = map2.getInt(-2081040702);
        r.clancid = map2.getString(853456552);
        r.cname = map2.getString(94802286);
        r.cicon = map2.getInt(94655324);
        r.maxBuidId = map2.getInt(-1063068435);
        r.maxObstId = map2.getInt(-708123085);
        r.stored_oil = map2.getLong(925923126);
        r.stored_gold = map2.getLong(-1361386628);
        r.maxAttMailId = map2.getInt(1818292783);
        r.protectTime = map2.getLong(-691626436);
        r.sys_time = map2.getLong(1957135615);
        r.maxOrderNum = map2.getInt(-1028380964);
        r.lastAddObst = map2.getLong(735426623);
        r.rankRewardTime = map2.getLong(1509214184);
        r.dayLogin = map2.getInt(1913609517);
        r.isReward = map2.getBoolean(-260292359);
        r.firstPayStatus = map2.getInt(435462634);
        r.pieceHPNum = map2.getInt(-779598032);
        r.pieceAttNum = map2.getInt(1436202419);
        r.pieceSpeedNum = map2.getInt(-1146905907);
        r.dayTasks = map2.getString(1920592210);
        r.lastLeaveClan = map2.getLong(-133609545);
        r.monCode = map2.getLong(1235182361);
        return r;
    }

    public String toString() {
        return "NPlayer[pid=" + pid + ", uid=" + uid + ", type=" + type + ", serverId=" + serverId + ", pname=" + pname + ", icon=" + icon + ", lvl=" + lvl + ", crystal=" + crystal + ", renown=" + renown + ", cur_npc_local_id=" + cur_npc_local_id + ", npcs=" + npcs + ", all_pay_momey=" + all_pay_momey + ", last_pay_money=" + last_pay_money + ", last_pay_time=" + last_pay_time + ", guid_step=" + guid_step + ", clancid=" + clancid + ", cname=" + cname + ", cicon=" + cicon + ", maxBuidId=" + maxBuidId + ", maxObstId=" + maxObstId + ", stored_oil=" + stored_oil + ", stored_gold=" + stored_gold + ", maxAttMailId=" + maxAttMailId + ", protectTime=" + protectTime + ", sys_time=" + sys_time + ", maxOrderNum=" + maxOrderNum + ", lastAddObst=" + lastAddObst + ", rankRewardTime=" + rankRewardTime + ", dayLogin=" + dayLogin + ", isReward=" + isReward + ", firstPayStatus=" + firstPayStatus + ", pieceHPNum=" + pieceHPNum + ", pieceAttNum=" + pieceAttNum + ", pieceSpeedNum=" + pieceSpeedNum + ", dayTasks=" + dayTasks + ", lastLeaveClan=" + lastLeaveClan + ", monCode=" + monCode + "]";
    }

}
