using System;
using System.Collections;
using Toolkit;

namespace ServiceClientBuilder {

public interface TcpChannel {
    void send(Hashtable map);
}

public class ConstantType {
    public const string Type_Sign_Key = "coolape"; // 签名迷匙
    public const string Type_Res_Oil = "o"; // 油
    public const string Type_Res_Crystal = "c"; // 水晶
    public const string Type_Res_Gold = "g"; // 金币
    public const int Type_Att_Charater = 1; // 兵
    public const int Type_Att_Clan = 2; // 兄弟盟
    public const int Type_Att_Spell = 3; // 药水
    public const int Type_Att_Hero = 4; // 英雄
    public const int Type_Chat_Pub = 1; // 公聊
    public const int Type_Chat_Pri = 2; // 私聊
    public const int Type_Chat_Clan = 3; // 联盟聊
    public const int Type_Mail_System = 1; // 系统邮件
    public const int Type_Mail_Player = 2; // 玩家邮件
    public const int Type_Mail_Server = 3; // 客服邮件
    public const int Type_Mail_System_Player = 4; // 系统与玩家通信邮件
    public const int Type_Reward_EXP = 1; // 奖励经验
    public const int Type_Reward_DIAM = 2; // 奖励砖石
    public const int Type_Reward_GOLD = 3; // 奖励金币
    public const int Type_Reward_OIL = 4; // 奖励油
    public const int Type_Reward_SHIP = 5; // 奖励舰船
    public const int Type_Reward_WORKER = 6; // 奖励工人
    public const int Type_Reward_SPELL = 7; // 奖励药水技能
    public const int Type_Reward_HERO = 8; // 奖励英雄
    public const int Type_Reward_Energy = 9; // 能源片
    public const int Type_User_Normal = 0; // 一般玩家
    public const int Type_User_NPC = 1; // NPC玩家
    public const int Type_User_GM = 2; // 游戏GM
    public const int Type_Hero_Live = 0; // 英雄正常
    public const int Type_Hero_Dead = 1; // 英雄已死亡
    public const int Type_ClanMember_Admin = 0; // 联盟成员职位--创建者
    public const int Type_ClanMember_Elders = 1; // 联盟成员职位--长老
    public const int Type_ClanMember_Normal = 2; // 联盟成员职位--成员
}
public class NAchievement {
    public const int _CID = 599038465;

    public int gid; 
    public int localId; 
    public int val; // 当前完成值
    public bool isDraw; // 是否领取过奖励


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(102338, gid);
        r.Add(338409958, localId);
        r.Add(116513, val);
        r.Add(-1180563058, isDraw);
        return r;
    }


    public static NAchievement parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NAchievement r = new NAchievement();
        r.gid = map2.getInt(102338);
        r.localId = map2.getInt(338409958);
        r.val = map2.getInt(116513);
        r.isDraw = map2.getBool(-1180563058);
        return r;
    }

}
public class NAchievements {
    public const int _CID = 1390323346;

    public ArrayList lists; 

    public ArrayList lists_maps() {
        ArrayList r = new ArrayList();
        if(lists == null) return r;
        foreach(NAchievement _e in lists) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_lists(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NAchievement e = NAchievement.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(102982549, lists_maps());
        return r;
    }


    public static NAchievements parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NAchievements r = new NAchievements();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

}
public class NArmy {
    public const int _CID = 74084107;

    public int aid; 
    public string aname = ""; 
    public int gid; 
    public int lvl; 
    public int num; 
    public int buildClientId; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(96572, aid);
        r.Add(92955244, aname);
        r.Add(102338, gid);
        r.Add(107554, lvl);
        r.Add(109446, num);
        r.Add(400364244, buildClientId);
        return r;
    }


    public static NArmy parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NArmy r = new NArmy();
        r.aid = map2.getInt(96572);
        r.aname = map2.getString(92955244);
        r.gid = map2.getInt(102338);
        r.lvl = map2.getInt(107554);
        r.num = map2.getInt(109446);
        r.buildClientId = map2.getInt(400364244);
        return r;
    }

}
public class NArmys {
    public const int _CID = -1998359864;

    public ArrayList armys; 

    public ArrayList armys_maps() {
        ArrayList r = new ArrayList();
        if(armys == null) return r;
        foreach(NArmy _e in armys) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_armys(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NArmy e = NArmy.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(93086326, armys_maps());
        return r;
    }


    public static NArmys parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NArmys r = new NArmys();
        r.armys = maps_armys( map2.getList(93086326) );
        return r;
    }

}
public class NAttMail {
    public const int _CID = -511049782;

    public int mid; 
    public string mcid = ""; 
    public int attPid; 
    public string attName = ""; 
    public string clancid = ""; 
    public string clanName = ""; 
    public int clanIcon; 
    public int preRenown; 
    public int attGold; 
    public int attOil; 
    public int star; 
    public int attRenown; 
    public bool isHitBack; 
    public long createTime; 
    public long endTime; 
    public NAttackInfos attInfo; 
    public int egid; 
    public int num; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(108104, mid);
        r.Add(3345713, mcid);
        r.Add(-1407275222, attPid);
        r.Add(-675925812, attName);
        r.Add(853456552, clancid);
        r.Add(686716417, clanName);
        r.Add(686569455, clanIcon);
        r.Add(-1866709618, preRenown);
        r.Add(-676120927, attGold);
        r.Add(-1407276175, attOil);
        r.Add(3540562, star);
        r.Add(-906389748, attRenown);
        r.Add(346575504, isHitBack);
        r.Add(1369213417, createTime);
        r.Add(-1607243192, endTime);
        r.Add(-676062481, attInfo.toMap());
        r.Add(3111229, egid);
        r.Add(109446, num);
        return r;
    }


    public static NAttMail parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NAttMail r = new NAttMail();
        r.mid = map2.getInt(108104);
        r.mcid = map2.getString(3345713);
        r.attPid = map2.getInt(-1407275222);
        r.attName = map2.getString(-675925812);
        r.clancid = map2.getString(853456552);
        r.clanName = map2.getString(686716417);
        r.clanIcon = map2.getInt(686569455);
        r.preRenown = map2.getInt(-1866709618);
        r.attGold = map2.getInt(-676120927);
        r.attOil = map2.getInt(-1407276175);
        r.star = map2.getInt(3540562);
        r.attRenown = map2.getInt(-906389748);
        r.isHitBack = map2.getBool(346575504);
        r.createTime = map2.getLong(1369213417);
        r.endTime = map2.getLong(-1607243192);
        r.attInfo = NAttackInfos.parse(map2.getNewMap(-676062481));
        r.egid = map2.getInt(3111229);
        r.num = map2.getInt(109446);
        return r;
    }

}
public class NAttMails {
    public const int _CID = 1337326057;

    public ArrayList attMails; 

    public ArrayList attMails_maps() {
        ArrayList r = new ArrayList();
        if(attMails == null) return r;
        foreach(NAttMail _e in attMails) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_attMails(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NAttMail e = NAttMail.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(520209275, attMails_maps());
        return r;
    }


    public static NAttMails parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NAttMails r = new NAttMails();
        r.attMails = maps_attMails( map2.getList(520209275) );
        return r;
    }

}
public class NAttackInfo {
    public const int _CID = 1603384836;

    public int type; 
    public int bcid; 
    public int gid; 
    public int lvl; 
    public int num; 
    public double x; 
    public double y; 
    public long diffTime; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3575610, type);
        r.Add(3018012, bcid);
        r.Add(102338, gid);
        r.Add(107554, lvl);
        r.Add(109446, num);
        r.Add(120, x);
        r.Add(121, y);
        r.Add(-97040270, diffTime);
        return r;
    }


    public static NAttackInfo parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NAttackInfo r = new NAttackInfo();
        r.type = map2.getInt(3575610);
        r.bcid = map2.getInt(3018012);
        r.gid = map2.getInt(102338);
        r.lvl = map2.getInt(107554);
        r.num = map2.getInt(109446);
        r.x = map2.getDouble(120);
        r.y = map2.getDouble(121);
        r.diffTime = map2.getLong(-97040270);
        return r;
    }

}
public class NAttackInfos {
    public const int _CID = -1834677521;

    public ArrayList infos; 

    public ArrayList infos_maps() {
        ArrayList r = new ArrayList();
        if(infos == null) return r;
        foreach(NAttackInfo _e in infos) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_infos(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NAttackInfo e = NAttackInfo.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(100348293, infos_maps());
        return r;
    }


    public static NAttackInfos parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NAttackInfos r = new NAttackInfos();
        r.infos = maps_infos( map2.getList(100348293) );
        return r;
    }

}
public class NBool {
    public const int _CID = 74111064;

    public bool val; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(116513, val);
        return r;
    }


    public static NBool parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NBool r = new NBool();
        r.val = map2.getBool(116513);
        return r;
    }

}
public class NBuild {
    public const int _CID = -1997351232;

    public int client_id; 
    public string bname = ""; 
    public int gid; 
    public int lvl; 
    public long cooldown; 
    public int pos_index; 
    public int state; 
    public int type; 
    public long cur_pro_res; 
    public long pro_res_beg_time; 
    public int cur_up_tech_gid; 
    public long end_tech_up_time; 
    public long begin_army_time; 
    public long end_army_time; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(-1904089585, client_id);
        r.Add(93878765, bname);
        r.Add(102338, gid);
        r.Add(107554, lvl);
        r.Add(-546109589, cooldown);
        r.Add(1410805543, pos_index);
        r.Add(109757585, state);
        r.Add(3575610, type);
        r.Add(-1164548849, cur_pro_res);
        r.Add(1799759545, pro_res_beg_time);
        r.Add(-1656969154, cur_up_tech_gid);
        r.Add(-983812564, end_tech_up_time);
        r.Add(213919769, begin_army_time);
        r.Add(46408139, end_army_time);
        return r;
    }


    public static NBuild parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NBuild r = new NBuild();
        r.client_id = map2.getInt(-1904089585);
        r.bname = map2.getString(93878765);
        r.gid = map2.getInt(102338);
        r.lvl = map2.getInt(107554);
        r.cooldown = map2.getLong(-546109589);
        r.pos_index = map2.getInt(1410805543);
        r.state = map2.getInt(109757585);
        r.type = map2.getInt(3575610);
        r.cur_pro_res = map2.getLong(-1164548849);
        r.pro_res_beg_time = map2.getLong(1799759545);
        r.cur_up_tech_gid = map2.getInt(-1656969154);
        r.end_tech_up_time = map2.getLong(-983812564);
        r.begin_army_time = map2.getLong(213919769);
        r.end_army_time = map2.getLong(46408139);
        return r;
    }

}
public class NBuildBlast {
    public const int _CID = -1227226344;

    public int beAttPid; 
    public ArrayList bcids; 
    public ArrayList bgids; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(411838797, beAttPid);
        r.Add(93558487, bcids);
        r.Add(93677651, bgids);
        return r;
    }


    public static NBuildBlast parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NBuildBlast r = new NBuildBlast();
        r.beAttPid = map2.getInt(411838797);
        r.bcids = map2.getList(93558487);
        r.bgids = map2.getList(93677651);
        return r;
    }

}
public class NBuilds {
    public const int _CID = -1788345933;

    public ArrayList builds; 

    public ArrayList builds_maps() {
        ArrayList r = new ArrayList();
        if(builds == null) return r;
        foreach(NBuild _e in builds) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_builds(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NBuild e = NBuild.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(-1378023483, builds_maps());
        return r;
    }


    public static NBuilds parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NBuilds r = new NBuilds();
        r.builds = maps_builds( map2.getList(-1378023483) );
        return r;
    }

}
public class NChat {
    public const int _CID = 74133702;

    public int ncid; 
    public int uuid; 
    public int type; 
    public string content = ""; 
    public string toPName = ""; 
    public string fromPName = ""; 
    public long createTime; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3375504, ncid);
        r.Add(3601339, uuid);
        r.Add(3575610, type);
        r.Add(951530617, content);
        r.Add(-1169617568, toPName);
        r.Add(65694097, fromPName);
        r.Add(1369213417, createTime);
        return r;
    }


    public static NChat parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NChat r = new NChat();
        r.ncid = map2.getInt(3375504);
        r.uuid = map2.getInt(3601339);
        r.type = map2.getInt(3575610);
        r.content = map2.getString(951530617);
        r.toPName = map2.getString(-1169617568);
        r.fromPName = map2.getString(65694097);
        r.createTime = map2.getLong(1369213417);
        return r;
    }

}
public class NChats {
    public const int _CID = -1996822419;

    public ArrayList lists; 

    public ArrayList lists_maps() {
        ArrayList r = new ArrayList();
        if(lists == null) return r;
        foreach(NChat _e in lists) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_lists(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NChat e = NChat.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(102982549, lists_maps());
        return r;
    }


    public static NChats parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NChats r = new NChats();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

}
public class NClan {
    public const int _CID = 74137540;

    public string ccid = ""; // 联盟唯一标识
    public string cname = ""; 
    public int icon; 
    public string desc = ""; 
    public int maxNum; 
    public int curNum; 
    public long createTime; 
    public int pointHP; // 增加点数-血量
    public int pointAtt; // 增加点数-攻击力
    public int eachHP; // 血量百分比/点
    public int eachAtt; // 攻击百分比/点
    public long curGold; // 所捐金币
    public long curOil; // 所捐油
    public long maxGold; // 下一等级点数所需金币
    public long maxOil; // 下一等级点数所需油


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3047803, ccid);
        r.Add(94802286, cname);
        r.Add(3226745, icon);
        r.Add(3079825, desc);
        r.Add(-1081154686, maxNum);
        r.Add(-1349154522, curNum);
        r.Add(1369213417, createTime);
        r.Add(-400606568, pointHP);
        r.Add(466092785, pointAtt);
        r.Add(-1310789943, eachHP);
        r.Add(-1979788064, eachAtt);
        r.Add(1125668544, curGold);
        r.Add(-1349153934, curOil);
        r.Add(843728868, maxGold);
        r.Add(-1081154098, maxOil);
        return r;
    }


    public static NClan parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NClan r = new NClan();
        r.ccid = map2.getString(3047803);
        r.cname = map2.getString(94802286);
        r.icon = map2.getInt(3226745);
        r.desc = map2.getString(3079825);
        r.maxNum = map2.getInt(-1081154686);
        r.curNum = map2.getInt(-1349154522);
        r.createTime = map2.getLong(1369213417);
        r.pointHP = map2.getInt(-400606568);
        r.pointAtt = map2.getInt(466092785);
        r.eachHP = map2.getInt(-1310789943);
        r.eachAtt = map2.getInt(-1979788064);
        r.curGold = map2.getLong(1125668544);
        r.curOil = map2.getLong(-1349153934);
        r.maxGold = map2.getLong(843728868);
        r.maxOil = map2.getLong(-1081154098);
        return r;
    }

}
public class NClanMember {
    public const int _CID = 2049758078;

    public string ccid = ""; // 联盟唯一标识
    public int pcid; 
    public string pname = ""; 
    public int post; 
    public int donateGold; 
    public int donateOil; 
    public int curDGold; 
    public int curDOil; 
    public long lastDGold; 
    public long lastDOil; 
    public int renownAll; 
    public int renownWeek; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3047803, ccid);
        r.Add(3435086, pcid);
        r.Add(106808059, pname);
        r.Add(3446944, post);
        r.Add(805138287, donateGold);
        r.Add(1550000355, donateOil);
        r.Add(532027524, curDGold);
        r.Add(1125548334, curDOil);
        r.Add(1987038702, lastDGold);
        r.Add(-1459915132, lastDOil);
        r.Add(-2123926378, renownAll);
        r.Add(-1416559713, renownWeek);
        return r;
    }


    public static NClanMember parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NClanMember r = new NClanMember();
        r.ccid = map2.getString(3047803);
        r.pcid = map2.getInt(3435086);
        r.pname = map2.getString(106808059);
        r.post = map2.getInt(3446944);
        r.donateGold = map2.getInt(805138287);
        r.donateOil = map2.getInt(1550000355);
        r.curDGold = map2.getInt(532027524);
        r.curDOil = map2.getInt(1125548334);
        r.lastDGold = map2.getLong(1987038702);
        r.lastDOil = map2.getLong(-1459915132);
        r.renownAll = map2.getInt(-2123926378);
        r.renownWeek = map2.getInt(-1416559713);
        return r;
    }

}
public class NClanMembers {
    public const int _CID = -882008907;

    public ArrayList list; 

    public ArrayList list_maps() {
        ArrayList r = new ArrayList();
        if(list == null) return r;
        foreach(NClanMember _e in list) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_list(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NClanMember e = NClanMember.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3322014, list_maps());
        return r;
    }


    public static NClanMembers parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NClanMembers r = new NClanMembers();
        r.list = maps_list( map2.getList(3322014) );
        return r;
    }

}
public class NClanRequest {
    public const int _CID = -735197653;

    public string ccid = ""; // 联盟唯一标识
    public int pcid; 
    public string pname = ""; 
    public int renown; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3047803, ccid);
        r.Add(3435086, pcid);
        r.Add(106808059, pname);
        r.Add(-934580981, renown);
        return r;
    }


    public static NClanRequest parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NClanRequest r = new NClanRequest();
        r.ccid = map2.getString(3047803);
        r.pcid = map2.getInt(3435086);
        r.pname = map2.getString(106808059);
        r.renown = map2.getInt(-934580981);
        return r;
    }

}
public class NClanRequests {
    public const int _CID = -1316290648;

    public ArrayList list; 

    public ArrayList list_maps() {
        ArrayList r = new ArrayList();
        if(list == null) return r;
        foreach(NClanRequest _e in list) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_list(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NClanRequest e = NClanRequest.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3322014, list_maps());
        return r;
    }


    public static NClanRequests parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NClanRequests r = new NClanRequests();
        r.list = maps_list( map2.getList(3322014) );
        return r;
    }

}
public class NClans {
    public const int _CID = -1996703441;

    public ArrayList list; 

    public ArrayList list_maps() {
        ArrayList r = new ArrayList();
        if(list == null) return r;
        foreach(NClan _e in list) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_list(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NClan e = NClan.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3322014, list_maps());
        return r;
    }


    public static NClans parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NClans r = new NClans();
        r.list = maps_list( map2.getList(3322014) );
        return r;
    }

}
public class NDouble {
    public const int _CID = -1736280641;

    public double val; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(116513, val);
        return r;
    }


    public static NDouble parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NDouble r = new NDouble();
        r.val = map2.getDouble(116513);
        return r;
    }

}
public class NEnergy {
    public const int _CID = -1709036426;

    public int egid; // GID
    public int num; // 数量


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3111229, egid);
        r.Add(109446, num);
        return r;
    }


    public static NEnergy parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NEnergy r = new NEnergy();
        r.egid = map2.getInt(3111229);
        r.num = map2.getInt(109446);
        return r;
    }

}
public class NEnergys {
    public const int _CID = -1440521539;

    public ArrayList list; 

    public ArrayList list_maps() {
        ArrayList r = new ArrayList();
        if(list == null) return r;
        foreach(NEnergy _e in list) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_list(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NEnergy e = NEnergy.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3322014, list_maps());
        return r;
    }


    public static NEnergys parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NEnergys r = new NEnergys();
        r.list = maps_list( map2.getList(3322014) );
        return r;
    }

}
public class NHero {
    public const int _CID = 74280296;

    public int hcid; 
    public int hgid; 
    public int hp; 
    public int maxHp; 
    public int damage; 
    public int maxDamage; 
    public int speed; 
    public int maxSpeed; 
    public int status; 
    public long deadTime; 
    public int skillGid; 
    public int fightPos; // 战斗开始的位置


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3196758, hcid);
        r.Add(3200602, hgid);
        r.Add(3336, hp);
        r.Add(103671180, maxHp);
        r.Add(-1339126929, damage);
        r.Add(-1024163341, maxDamage);
        r.Add(109641799, speed);
        r.Add(396896579, maxSpeed);
        r.Add(-892481550, status);
        r.Add(502919505, deadTime);
        r.Add(2142429105, skillGid);
        r.Add(-874981340, fightPos);
        return r;
    }


    public static NHero parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NHero r = new NHero();
        r.hcid = map2.getInt(3196758);
        r.hgid = map2.getInt(3200602);
        r.hp = map2.getInt(3336);
        r.maxHp = map2.getInt(103671180);
        r.damage = map2.getInt(-1339126929);
        r.maxDamage = map2.getInt(-1024163341);
        r.speed = map2.getInt(109641799);
        r.maxSpeed = map2.getInt(396896579);
        r.status = map2.getInt(-892481550);
        r.deadTime = map2.getLong(502919505);
        r.skillGid = map2.getInt(2142429105);
        r.fightPos = map2.getInt(-874981340);
        return r;
    }

}
public class NHeros {
    public const int _CID = -1992278005;

    public ArrayList lists; 

    public ArrayList lists_maps() {
        ArrayList r = new ArrayList();
        if(lists == null) return r;
        foreach(NHero _e in lists) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_lists(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NHero e = NHero.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(102982549, lists_maps());
        return r;
    }


    public static NHeros parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NHeros r = new NHeros();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

}
public class NInt {
    public const int _CID = 2397377;

    public int val; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(116513, val);
        return r;
    }


    public static NInt parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NInt r = new NInt();
        r.val = map2.getInt(116513);
        return r;
    }

}
public class NInts {
    public const int _CID = 74318802;

    public ArrayList intes; 

    public ArrayList intes_maps() {
        ArrayList r = new ArrayList();
        if(intes == null) return r;
        foreach(NInt _e in intes) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_intes(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NInt e = NInt.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(100361437, intes_maps());
        return r;
    }


    public static NInts parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NInts r = new NInts();
        r.intes = maps_intes( map2.getList(100361437) );
        return r;
    }

}
public class NLong {
    public const int _CID = 74408938;

    public long val; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(116513, val);
        return r;
    }


    public static NLong parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NLong r = new NLong();
        r.val = map2.getLong(116513);
        return r;
    }

}
public class NMail {
    public const int _CID = 74425125;

    public int mid; 
    public int type; 
    public bool isRead; 
    public string title = ""; 
    public string content = ""; 
    public string toPName = ""; 
    public string fromPName = ""; 
    public long createTime; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(108104, mid);
        r.Add(3575610, type);
        r.Add(-1180158496, isRead);
        r.Add(110371416, title);
        r.Add(951530617, content);
        r.Add(-1169617568, toPName);
        r.Add(65694097, fromPName);
        r.Add(1369213417, createTime);
        return r;
    }


    public static NMail parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NMail r = new NMail();
        r.mid = map2.getInt(108104);
        r.type = map2.getInt(3575610);
        r.isRead = map2.getBool(-1180158496);
        r.title = map2.getString(110371416);
        r.content = map2.getString(951530617);
        r.toPName = map2.getString(-1169617568);
        r.fromPName = map2.getString(65694097);
        r.createTime = map2.getLong(1369213417);
        return r;
    }

}
public class NMails {
    public const int _CID = -1987788306;

    public ArrayList lists; 

    public ArrayList lists_maps() {
        ArrayList r = new ArrayList();
        if(lists == null) return r;
        foreach(NMail _e in lists) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_lists(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NMail e = NMail.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(102982549, lists_maps());
        return r;
    }


    public static NMails parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NMails r = new NMails();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

}
public class NNPCBeFighted {
    public const int _CID = -533352327;

    public int ncid; 
    public int star; 
    public int gold; // 被掠夺了的金币资源值
    public int oil; // 被掠夺了的油币资源值


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3375504, ncid);
        r.Add(3540562, star);
        r.Add(3178592, gold);
        r.Add(110034, oil);
        return r;
    }


    public static NNPCBeFighted parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NNPCBeFighted r = new NNPCBeFighted();
        r.ncid = map2.getInt(3375504);
        r.star = map2.getInt(3540562);
        r.gold = map2.getInt(3178592);
        r.oil = map2.getInt(110034);
        return r;
    }

}
public class NNPCBeFighteds {
    public const int _CID = 645947162;

    public ArrayList lists; 

    public ArrayList lists_maps() {
        ArrayList r = new ArrayList();
        if(lists == null) return r;
        foreach(NNPCBeFighted _e in lists) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_lists(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NNPCBeFighted e = NNPCBeFighted.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(102982549, lists_maps());
        return r;
    }


    public static NNPCBeFighteds parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NNPCBeFighteds r = new NNPCBeFighteds();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

}
public class NPInfo {
    public const int _CID = -1985728112;

    public int pcid; 
    public long crystal; 
    public long storedOil; 
    public long storedGold; 
    public long protectTime; 
    public int maxOrderNum; 
    public int firstPayStatus; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3435086, pcid);
        r.Add(1047561014, crystal);
        r.Add(1692420911, storedOil);
        r.Add(925208227, storedGold);
        r.Add(-691626436, protectTime);
        r.Add(-1028380964, maxOrderNum);
        r.Add(435462634, firstPayStatus);
        return r;
    }


    public static NPInfo parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NPInfo r = new NPInfo();
        r.pcid = map2.getInt(3435086);
        r.crystal = map2.getLong(1047561014);
        r.storedOil = map2.getLong(1692420911);
        r.storedGold = map2.getLong(925208227);
        r.protectTime = map2.getLong(-691626436);
        r.maxOrderNum = map2.getInt(-1028380964);
        r.firstPayStatus = map2.getInt(435462634);
        return r;
    }

}
public class NPlayer {
    public const int _CID = -1396075313;

    public int pid; 
    public int uid; 
    public int type; 
    public int serverId; 
    public string pname = ""; 
    public int icon; 
    public int lvl; 
    public long crystal; 
    public int renown; 
    public int cur_npc_local_id; 
    public string npcs = ""; 
    public int all_pay_momey; 
    public int last_pay_money; 
    public long last_pay_time; 
    public int guid_step; 
    public string clancid = ""; 
    public string cname = ""; 
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
    public bool isReward; // 是否已经领奖
    public int firstPayStatus; // 首次充值领奖状态1表示成功未领奖
    public int pieceHPNum; // 生命之源
    public int pieceAttNum; // 攻击之源
    public int pieceSpeedNum; // 速度之源
    public string dayTasks = ""; // 当天已领任务
    public long lastLeaveClan; // 离开联盟的时间
    public long monCode; // 月卡结束时间点


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(110987, pid);
        r.Add(115792, uid);
        r.Add(3575610, type);
        r.Add(1379103678, serverId);
        r.Add(106808059, pname);
        r.Add(3226745, icon);
        r.Add(107554, lvl);
        r.Add(1047561014, crystal);
        r.Add(-934580981, renown);
        r.Add(-980510292, cur_npc_local_id);
        r.Add(3387826, npcs);
        r.Add(-105336438, all_pay_momey);
        r.Add(541777920, last_pay_money);
        r.Add(1957342093, last_pay_time);
        r.Add(-2081040702, guid_step);
        r.Add(853456552, clancid);
        r.Add(94802286, cname);
        r.Add(94655324, cicon);
        r.Add(-1063068435, maxBuidId);
        r.Add(-708123085, maxObstId);
        r.Add(925923126, stored_oil);
        r.Add(-1361386628, stored_gold);
        r.Add(1818292783, maxAttMailId);
        r.Add(-691626436, protectTime);
        r.Add(1957135615, sys_time);
        r.Add(-1028380964, maxOrderNum);
        r.Add(735426623, lastAddObst);
        r.Add(1509214184, rankRewardTime);
        r.Add(1913609517, dayLogin);
        r.Add(-260292359, isReward);
        r.Add(435462634, firstPayStatus);
        r.Add(-779598032, pieceHPNum);
        r.Add(1436202419, pieceAttNum);
        r.Add(-1146905907, pieceSpeedNum);
        r.Add(1920592210, dayTasks);
        r.Add(-133609545, lastLeaveClan);
        r.Add(1235182361, monCode);
        return r;
    }


    public static NPlayer parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
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
        r.isReward = map2.getBool(-260292359);
        r.firstPayStatus = map2.getInt(435462634);
        r.pieceHPNum = map2.getInt(-779598032);
        r.pieceAttNum = map2.getInt(1436202419);
        r.pieceSpeedNum = map2.getInt(-1146905907);
        r.dayTasks = map2.getString(1920592210);
        r.lastLeaveClan = map2.getLong(-133609545);
        r.monCode = map2.getLong(1235182361);
        return r;
    }

}
public class NPlayers {
    public const int _CID = -328661628;

    public ArrayList uplayers; 

    public ArrayList uplayers_maps() {
        ArrayList r = new ArrayList();
        if(uplayers == null) return r;
        foreach(NPlayer _e in uplayers) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_uplayers(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NPlayer e = NPlayer.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(1551778717, uplayers_maps());
        return r;
    }


    public static NPlayers parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NPlayers r = new NPlayers();
        r.uplayers = maps_uplayers( map2.getList(1551778717) );
        return r;
    }

}
public class NProducte {
    public const int _CID = -186644540;

    public int pid; 
    public int gid; 
    public int building_id; 
    public int num; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(110987, pid);
        r.Add(102338, gid);
        r.Add(-1417153914, building_id);
        r.Add(109446, num);
        return r;
    }


    public static NProducte parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NProducte r = new NProducte();
        r.pid = map2.getInt(110987);
        r.gid = map2.getInt(102338);
        r.building_id = map2.getInt(-1417153914);
        r.num = map2.getInt(109446);
        return r;
    }

}
public class NProductes {
    public const int _CID = -1491013329;

    public ArrayList army_productings; 

    public ArrayList army_productings_maps() {
        ArrayList r = new ArrayList();
        if(army_productings == null) return r;
        foreach(NProducte _e in army_productings) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_army_productings(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NProducte e = NProducte.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(-446378338, army_productings_maps());
        return r;
    }


    public static NProductes parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NProductes r = new NProductes();
        r.army_productings = maps_army_productings( map2.getList(-446378338) );
        return r;
    }

}
public class NRankClan {
    public const int _CID = 1072669456;

    public string ccid = ""; // 联盟唯一标识
    public int icon; 
    public string cname = ""; 
    public int currank; 
    public int renownAll; 
    public int renownWeek; 
    public int type; // 0周1总
    public int curnum; 
    public int maxnum; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3047803, ccid);
        r.Add(3226745, icon);
        r.Add(94802286, cname);
        r.Add(1126936172, currank);
        r.Add(-2123926378, renownAll);
        r.Add(-1416559713, renownWeek);
        r.Add(3575610, type);
        r.Add(-1349123770, curnum);
        r.Add(-1081123934, maxnum);
        return r;
    }


    public static NRankClan parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NRankClan r = new NRankClan();
        r.ccid = map2.getString(3047803);
        r.icon = map2.getInt(3226745);
        r.cname = map2.getString(94802286);
        r.currank = map2.getInt(1126936172);
        r.renownAll = map2.getInt(-2123926378);
        r.renownWeek = map2.getInt(-1416559713);
        r.type = map2.getInt(3575610);
        r.curnum = map2.getInt(-1349123770);
        r.maxnum = map2.getInt(-1081123934);
        return r;
    }

}
public class NRankClans {
    public const int _CID = -1106985117;

    public ArrayList list; 

    public ArrayList list_maps() {
        ArrayList r = new ArrayList();
        if(list == null) return r;
        foreach(NRankClan _e in list) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_list(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NRankClan e = NRankClan.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3322014, list_maps());
        return r;
    }


    public static NRankClans parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NRankClans r = new NRankClans();
        r.list = maps_list( map2.getList(3322014) );
        return r;
    }

}
public class NRankUser {
    public const int _CID = 1073212549;

    public int pid; 
    public string pname = ""; 
    public int pexp; 
    public string clancid = ""; 
    public int clanIcon; 
    public string clanName = ""; 
    public int rankIndex; 
    public int renown; 
    public int weekRenown; 
    public int type; // 0周1总


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(110987, pid);
        r.Add(106808059, pname);
        r.Add(3437485, pexp);
        r.Add(853456552, clancid);
        r.Add(686569455, clanIcon);
        r.Add(686716417, clanName);
        r.Add(-662198266, rankIndex);
        r.Add(-934580981, renown);
        r.Add(-1226493409, weekRenown);
        r.Add(3575610, type);
        return r;
    }


    public static NRankUser parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NRankUser r = new NRankUser();
        r.pid = map2.getInt(110987);
        r.pname = map2.getString(106808059);
        r.pexp = map2.getInt(3437485);
        r.clancid = map2.getString(853456552);
        r.clanIcon = map2.getInt(686569455);
        r.clanName = map2.getString(686716417);
        r.rankIndex = map2.getInt(-662198266);
        r.renown = map2.getInt(-934580981);
        r.weekRenown = map2.getInt(-1226493409);
        r.type = map2.getInt(3575610);
        return r;
    }

}
public class NRankUsers {
    public const int _CID = -1090149234;

    public ArrayList lists; 

    public ArrayList lists_maps() {
        ArrayList r = new ArrayList();
        if(lists == null) return r;
        foreach(NRankUser _e in lists) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_lists(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NRankUser e = NRankUser.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(102982549, lists_maps());
        return r;
    }


    public static NRankUsers parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NRankUsers r = new NRankUsers();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

}
public class NReward {
    public const int _CID = -1344648931;

    public int type; // 类型
    public int val; // 奖励值
    public int gid; // gid
    public int lvl; // 等级
    public int bcid; // 建筑的client


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3575610, type);
        r.Add(116513, val);
        r.Add(102338, gid);
        r.Add(107554, lvl);
        r.Add(3018012, bcid);
        return r;
    }


    public static NReward parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NReward r = new NReward();
        r.type = map2.getInt(3575610);
        r.val = map2.getInt(116513);
        r.gid = map2.getInt(102338);
        r.lvl = map2.getInt(107554);
        r.bcid = map2.getInt(3018012);
        return r;
    }

}
public class NRewards {
    public const int _CID = 1265556214;

    public ArrayList lists; 

    public ArrayList lists_maps() {
        ArrayList r = new ArrayList();
        if(lists == null) return r;
        foreach(NReward _e in lists) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_lists(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NReward e = NReward.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(102982549, lists_maps());
        return r;
    }


    public static NRewards parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NRewards r = new NRewards();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

}
public class NSession {
    public const int _CID = -2145073560;

    public int sessionKey; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(1661834217, sessionKey);
        return r;
    }


    public static NSession parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NSession r = new NSession();
        r.sessionKey = map2.getInt(1661834217);
        return r;
    }

}
public class NSpell {
    public const int _CID = -1981804166;

    public int gid; 
    public int num; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(102338, gid);
        r.Add(109446, num);
        return r;
    }


    public static NSpell parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NSpell r = new NSpell();
        r.gid = map2.getInt(102338);
        r.num = map2.getInt(109446);
        return r;
    }

}
public class NSpells {
    public const int _CID = -1306386887;

    public ArrayList lists; 

    public ArrayList lists_maps() {
        ArrayList r = new ArrayList();
        if(lists == null) return r;
        foreach(NSpell _e in lists) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_lists(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NSpell e = NSpell.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(102982549, lists_maps());
        return r;
    }


    public static NSpells parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NSpells r = new NSpells();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

}
public class NStrVal {
    public const int _CID = -1302327010;

    public string val = ""; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(116513, val);
        return r;
    }


    public static NStrVal parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NStrVal r = new NStrVal();
        r.val = map2.getString(116513);
        return r;
    }

}
public class NStrings {
    public const int _CID = -1716853164;

    public ArrayList val; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(116513, val);
        return r;
    }


    public static NStrings parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NStrings r = new NStrings();
        r.val = map2.getList(116513);
        return r;
    }

}
public class NTech {
    public const int _CID = 74637316;

    public int tid; 
    public string tname = ""; 
    public int gid; 
    public int lvl; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(114831, tid);
        r.Add(110502143, tname);
        r.Add(102338, gid);
        r.Add(107554, lvl);
        return r;
    }


    public static NTech parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NTech r = new NTech();
        r.tid = map2.getInt(114831);
        r.tname = map2.getString(110502143);
        r.gid = map2.getInt(102338);
        r.lvl = map2.getInt(107554);
        return r;
    }

}
public class NTeches {
    public const int _CID = -1287980110;

    public ArrayList techs; 

    public ArrayList techs_maps() {
        ArrayList r = new ArrayList();
        if(techs == null) return r;
        foreach(NTech _e in techs) {
            Hashtable e = _e.toMap();
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }

    public static ArrayList maps_techs(ArrayList maps) {
        ArrayList r = new ArrayList();
        foreach(Hashtable _e in maps) {
            NTech e = NTech.parse(_e);
            if(e == null) continue;
            r.Add(e);
        }
        return r;
    }


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(110235805, techs_maps());
        return r;
    }


    public static NTeches parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NTeches r = new NTeches();
        r.techs = maps_techs( map2.getList(110235805) );
        return r;
    }

}
public class NUser {
    public const int _CID = 74680633;

    public int id; 
    public string uid = ""; 
    public string uuid = ""; 
    public string uname = ""; 
    public int type; 
    public int state; 
    public long logtin_time; 
    public string model = ""; 
    public string channel = ""; 
    public string version = ""; 
    public string remain = ""; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3355, id);
        r.Add(115792, uid);
        r.Add(3601339, uuid);
        r.Add(111425664, uname);
        r.Add(3575610, type);
        r.Add(109757585, state);
        r.Add(-377953801, logtin_time);
        r.Add(104069929, model);
        r.Add(738950403, channel);
        r.Add(351608024, version);
        r.Add(-934624660, remain);
        return r;
    }


    public static NUser parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NUser r = new NUser();
        r.id = map2.getInt(3355);
        r.uid = map2.getString(115792);
        r.uuid = map2.getString(3601339);
        r.uname = map2.getString(111425664);
        r.type = map2.getInt(3575610);
        r.state = map2.getInt(109757585);
        r.logtin_time = map2.getLong(-377953801);
        r.model = map2.getString(104069929);
        r.channel = map2.getString(738950403);
        r.version = map2.getString(351608024);
        r.remain = map2.getString(-934624660);
        return r;
    }

}
public class ReturnStatus {
    public const int _CID = 991275362;

    public int succ; 
    public long dt; 
    public string msg = ""; 


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3541570, succ);
        r.Add(3216, dt);
        r.Add(108417, msg);
        return r;
    }


    public static ReturnStatus parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        ReturnStatus r = new ReturnStatus();
        r.succ = map2.getInt(3541570);
        r.dt = map2.getLong(3216);
        r.msg = map2.getString(108417);
        return r;
    }

}
public abstract class CallSeaWarServiceI {

    public static int __uid;
    public TcpChannel chn;
    public CallSeaWarServiceI(TcpChannel chn) {
        this.chn = chn;
    }

    // 修改名称
    public void rename(int crystal, String uname) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -934594754);  // cmd:rename
        _map.Add(1047561014, crystal);
        _map.Add(111425664, uname);
        chn.send(_map);
    }

    // 登录玩家
    public void loginUserByUid(String uid, String uuid, String pwd, String name, String channel, String model, String version, int servid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1615760901);  // cmd:loginUserByUid
        _map.Add(115792, uid);
        _map.Add(3601339, uuid);
        _map.Add(111421, pwd);
        _map.Add(3373707, name);
        _map.Add(738950403, channel);
        _map.Add(104069929, model);
        _map.Add(351608024, version);
        _map.Add(-905826383, servid);
        chn.send(_map);
    }

    // 新增建筑
    public void createBuilding(int client_id, int gid, int pos, String resType, int costVal, int crystal, long cooldown_ms, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -508077104);  // cmd:createBuilding
        _map.Add(-1904089585, client_id);
        _map.Add(102338, gid);
        _map.Add(111188, pos);
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(185446138, cooldown_ms);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 完成移除
    public void finishRemoveBuild(int client_id, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 2115707319);  // cmd:finishRemoveBuild
        _map.Add(-1904089585, client_id);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 自然完成完成造兵
    public void finishProduceArmy(int bcid, NProductes lists, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 929969162);  // cmd:finishProduceArmy
        _map.Add(3018012, bcid);
        _map.Add(102982549, lists.toMap());
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 快速完成完成造兵
    public void fastFinishProduceArmy(int bcid, int crystal, NProductes lists, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1629150990);  // cmd:fastFinishProduceArmy
        _map.Add(3018012, bcid);
        _map.Add(1047561014, crystal);
        _map.Add(102982549, lists.toMap());
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // pve战利品
    public void pveFightSpoils(int cnpcid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 444729423);  // cmd:pveFightSpoils
        _map.Add(-1355658951, cnpcid);
        chn.send(_map);
    }

    // 一般查询被攻击的角色
    public void findBeAttactPlayer(String resType, int costVal, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1056271730);  // cmd:findBeAttactPlayer
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 购买水晶，内部测试用
    public void buyCrystalForOpenness(int buyCrystalVal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1004167270);  // cmd:buyCrystalForOpenness
        _map.Add(-797318351, buyCrystalVal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 随机名称
    public void randomPlayerName() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1723144751);  // cmd:randomPlayerName
        chn.send(_map);
    }

    // 成就完成领奖
    public void achievementReward(int localCurId, int localNextId, NRewards lists) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 62679870);  // cmd:achievementReward
        _map.Add(1277892304, localCurId);
        _map.Add(1260319417, localNextId);
        _map.Add(102982549, lists.toMap());
        chn.send(_map);
    }

    // 完成移除障碍
    public void finishRemoveObst(int ocid, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1176996619);  // cmd:finishRemoveObst
        _map.Add(3405295, ocid);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 下发的成就列表
    public void getAchievements() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -31856230);  // cmd:getAchievements
        chn.send(_map);
    }

    // 首次充值奖励
    public void rewardFirstPay(NRewards lists, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1127496743);  // cmd:rewardFirstPay
        _map.Add(102982549, lists.toMap());
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 开宝箱
    public void openTreasureBox(int crystal, int num, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1070317554);  // cmd:openTreasureBox
        _map.Add(1047561014, crystal);
        _map.Add(109446, num);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 创建充值订单
    public void createOrderPayMoney(String strUni, String phoneInfo) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 501903050);  // cmd:createOrderPayMoney
        _map.Add(-892005121, strUni);
        _map.Add(-1029329092, phoneInfo);
        chn.send(_map);
    }

    // 每日日常任务奖励
    public void dayTasksReward(int localDRID, NRewards lists) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 52059937);  // cmd:dayTasksReward
        _map.Add(-1205708780, localDRID);
        _map.Add(102982549, lists.toMap());
        chn.send(_map);
    }

    // 取得个人总排行
    public void getNRankUsersByAll(int page) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -858116154);  // cmd:getNRankUsersByAll
        _map.Add(3433103, page);
        chn.send(_map);
    }

    // 取得自己联盟成员
    public void getOwnClanMember() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -762930944);  // cmd:getOwnClanMember
        chn.send(_map);
    }

    // 取得自己联盟请求
    public void getOwnClanRequest() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -2029211415);  // cmd:getOwnClanRequest
        chn.send(_map);
    }

    // 捐献金币
    public void donateClanGold(int dgold, int curval, int crystal) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1149482139);  // cmd:donateClanGold
        _map.Add(95530692, dgold);
        _map.Add(-1349116703, curval);
        _map.Add(1047561014, crystal);
        chn.send(_map);
    }

    // 取得联盟总排行
    public void getNRankClanByAll(int page) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -858334864);  // cmd:getNRankClanByAll
        _map.Add(3433103, page);
        chn.send(_map);
    }

    // 捐献油
    public void donateClanOil(int doil, int curval, int crystal) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -868356563);  // cmd:donateClanOil
        _map.Add(3089134, doil);
        _map.Add(-1349116703, curval);
        _map.Add(1047561014, crystal);
        chn.send(_map);
    }

    // 取得联盟周排行
    public void getNRankClan(int page) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -940903302);  // cmd:getNRankClan
        _map.Add(3433103, page);
        chn.send(_map);
    }

    // 改变建筑等级
    public void downBuildLvl(int bcid, int downlvl) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -2102563434);  // cmd:downBuildLvl
        _map.Add(3018012, bcid);
        _map.Add(1847174208, downlvl);
        chn.send(_map);
    }

    // 渠道快速注册
    public void cmRegistFast(String chnstr) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1717679356);  // cmd:cmRegistFast
        _map.Add(-1361243928, chnstr);
        chn.send(_map);
    }

    // 渠道注册
    public void cmRegist(String lgId, String lgPwd, String chnstr) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1137223808);  // cmd:cmRegist
        _map.Add(3318774, lgId);
        _map.Add(102889410, lgPwd);
        _map.Add(-1361243928, chnstr);
        chn.send(_map);
    }

    // 用户分享成功
    public void shareSuccess() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 2009579300);  // cmd:shareSuccess
        chn.send(_map);
    }

    // 打劫商船
    public void lootMerchant(String resType, int resVal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -827208560);  // cmd:lootMerchant
        _map.Add(1096575994, resType);
        _map.Add(-934456735, resVal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 登录奇虎360取得uid,token
    public void loginQH360(String strCode) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1761920275);  // cmd:loginQH360
        _map.Add(-1882890306, strCode);
        chn.send(_map);
    }

    // 玩家角色
    public void loginUPlayer(int SUPID) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1450193907);  // cmd:loginUPlayer
        _map.Add(79263689, SUPID);
        chn.send(_map);
    }

    // 升级建筑
    public void upBuilding(int client_id, String resType, int costVal, int crystal, long cooldown_ms, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 840284783);  // cmd:upBuilding
        _map.Add(-1904089585, client_id);
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(185446138, cooldown_ms);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 完成升级建筑
    public void finishBuild(int client_id, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1321151899);  // cmd:finishBuild
        _map.Add(-1904089585, client_id);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 移除建筑
    public void removeBuild(int client_id, String resType, int costVal, int crystal, long coowdown, String havType, int havVal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -320303030);  // cmd:removeBuild
        _map.Add(-1904089585, client_id);
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(-535950858, coowdown);
        _map.Add(699727735, havType);
        _map.Add(-1224352956, havVal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 移动建筑
    public void moveBuild(int client_id, int pos) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1041716509);  // cmd:moveBuild
        _map.Add(-1904089585, client_id);
        _map.Add(111188, pos);
        chn.send(_map);
    }

    // 收资源
    public void harvestRes(int b_client_id, String resType, int harVal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1022537609);  // cmd:harvestRes
        _map.Add(894389874, b_client_id);
        _map.Add(1096575994, resType);
        _map.Add(-1224472120, harVal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 造兵和减少兵的接口
    public void produceArmy(int b_client_id, int gid, int num, String resType, int costVal, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1506041187);  // cmd:produceArmy
        _map.Add(894389874, b_client_id);
        _map.Add(102338, gid);
        _map.Add(109446, num);
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 升级科技
    public void upTechnolgy(int b_client_id, int gid, String resType, int costVal, int crystal, int upTime, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1145738292);  // cmd:upTechnolgy
        _map.Add(894389874, b_client_id);
        _map.Add(102338, gid);
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(-839315448, upTime);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 完成升级科技
    public void finishUpTech(int b_client_id, int gid, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1455260092);  // cmd:finishUpTech
        _map.Add(894389874, b_client_id);
        _map.Add(102338, gid);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // pve战斗放兵
    public void pveAttackInfo(int cur_npc_id, NAttackInfos deaths) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1521297515);  // cmd:pveAttackInfo
        _map.Add(-512671208, cur_npc_id);
        _map.Add(-1335772033, deaths.toMap());
        chn.send(_map);
    }

    // 打pve结果
    public void pveResult(int cur_npc_id, int gold, int oil, NNPCBeFighteds npcs, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 809718812);  // cmd:pveResult
        _map.Add(-512671208, cur_npc_id);
        _map.Add(3178592, gold);
        _map.Add(110034, oil);
        _map.Add(3387826, npcs.toMap());
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 开始战斗
    public void beginAttact(int attcid, bool isHitBack, NHeros beHeros) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -740593574);  // cmd:beginAttact
        _map.Add(-1407256963, attcid);
        _map.Add(346575504, isHitBack);
        _map.Add(-257792714, beHeros.toMap());
        chn.send(_map);
    }

    // 战斗信息
    public void attactInfos(int attcid, NAttackInfos attInfos, NAttackInfos offenDeaths, NAttackInfos defenseDeaths) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -428433996);  // cmd:attactInfos
        _map.Add(-1407256963, attcid);
        _map.Add(516899684, attInfos.toMap());
        _map.Add(674996471, offenDeaths.toMap());
        _map.Add(-1601665313, defenseDeaths.toMap());
        chn.send(_map);
    }

    // 结束战斗
    public void endAttact(int attcid, int star, int renown, int defenceRenown, int attGold, int attOil, int maxGold, int maxOil, NAttackInfos attInfos, NBuildBlast bastBuilds, NAttackInfos offenDeaths, NAttackInfos defenseDeaths, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1093931788);  // cmd:endAttact
        _map.Add(-1407256963, attcid);
        _map.Add(3540562, star);
        _map.Add(-934580981, renown);
        _map.Add(979331451, defenceRenown);
        _map.Add(-676120927, attGold);
        _map.Add(-1407276175, attOil);
        _map.Add(843728868, maxGold);
        _map.Add(-1081154098, maxOil);
        _map.Add(516899684, attInfos.toMap());
        _map.Add(-876129595, bastBuilds.toMap());
        _map.Add(674996471, offenDeaths.toMap());
        _map.Add(-1601665313, defenseDeaths.toMap());
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 回到主基地
    public void goHome() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1241398809);  // cmd:goHome
        chn.send(_map);
    }

    // 购买资源
    public void buyRes(String resType, int buyVal, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1377571782);  // cmd:buyRes
        _map.Add(1096575994, resType);
        _map.Add(-1377568069, buyVal);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 战斗回放
    public void replayAttMail(String mcid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -188503535);  // cmd:replayAttMail
        _map.Add(3345713, mcid);
        chn.send(_map);
    }

    // 反击
    public void hitBack(String mcid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 926354202);  // cmd:hitBack
        _map.Add(3345713, mcid);
        chn.send(_map);
    }

    // 心跳
    public void heart() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 99151942);  // cmd:heart
        chn.send(_map);
    }

    // 设置名字
    public void setPlayerName(String uname) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1722927346);  // cmd:setPlayerName
        _map.Add(111425664, uname);
        chn.send(_map);
    }

    // 修复陷阱
    public void repairTrap(int b_c_id, String resType, int costVal, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 2131720282);  // cmd:repairTrap
        _map.Add(-1398532044, b_c_id);
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 用户下线
    public void downLine() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1426859958);  // cmd:downLine
        chn.send(_map);
    }

    // 新手步骤
    public void guidNewPlayer(int guid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1203719192);  // cmd:guidNewPlayer
        _map.Add(3184265, guid);
        chn.send(_map);
    }

    // 聊天内容
    public void sendChat(NChat newChat) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1246941952);  // cmd:sendChat
        _map.Add(1844699416, newChat.toMap());
        chn.send(_map);
    }

    // 购买序列
    public void buyOrder(int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -998949784);  // cmd:buyOrder
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 下发战报邮件
    public void getAttMails() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1696857871);  // cmd:getAttMails
        chn.send(_map);
    }

    // 下发邮件
    public void getNMails() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 624359620);  // cmd:getNMails
        chn.send(_map);
    }

    // 下发聊天信息
    public void getNChats() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 615325507);  // cmd:getNChats
        chn.send(_map);
    }

    // 新邮件
    public void sendMail(NMail newMail) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1247233375);  // cmd:sendMail
        _map.Add(1844990839, newMail.toMap());
        chn.send(_map);
    }

    // 结束引导
    public void finishGuid(int guid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1151145404);  // cmd:finishGuid
        _map.Add(3184265, guid);
        chn.send(_map);
    }

    // 购买法术
    public void buySpell(String resType, int costVal, int crystal, int gid, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -995314110);  // cmd:buySpell
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(102338, gid);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 增加经验
    public void addExp(int addVal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1422538308);  // cmd:addExp
        _map.Add(-1422522688, addVal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 新增障碍
    public void addObst(int ocid, int gid, int pos, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1148737611);  // cmd:addObst
        _map.Add(3405295, ocid);
        _map.Add(102338, gid);
        _map.Add(111188, pos);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 新增障碍城墙
    public void addObstWall(int ocid, int gid, int pos, String resType, int costVal, int crystal, long cooldown_ms, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -612644065);  // cmd:addObstWall
        _map.Add(3405295, ocid);
        _map.Add(102338, gid);
        _map.Add(111188, pos);
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(185446138, cooldown_ms);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 升级障碍-城墙
    public void upObstWall(NInts ocids, String resType, int costVal, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1662400743);  // cmd:upObstWall
        _map.Add(105564260, ocids.toMap());
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 移除障碍
    public void removeObst(int ocid, String resType, int costVal, int crystal, long coowdown, String havType, int havVal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1098415640);  // cmd:removeObst
        _map.Add(3405295, ocid);
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        _map.Add(-535950858, coowdown);
        _map.Add(699727735, havType);
        _map.Add(-1224352956, havVal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 移动物体
    public void moveObst(int ocid, int pos) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -104574235);  // cmd:moveObst
        _map.Add(3405295, ocid);
        _map.Add(111188, pos);
        chn.send(_map);
    }

    // 改变邮件状态为__已读
    public void readNMail(int mailId) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1130309809);  // cmd:readNMail
        _map.Add(-1081574094, mailId);
        chn.send(_map);
    }

    // 访问别人
    public void viewPlayer(int pid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1955892762);  // cmd:viewPlayer
        _map.Add(110987, pid);
        chn.send(_map);
    }

    // 购买保护盾
    public void buyShield(int crystal, long addTime, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -797241969);  // cmd:buyShield
        _map.Add(1047561014, crystal);
        _map.Add(-1148582130, addTime);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 取得用户信息
    public void getPInfo() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1961543560);  // cmd:getPInfo
        chn.send(_map);
    }

    // 领每日登录奖励
    public void rewardDay(NRewards lists, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1162918381);  // cmd:rewardDay
        _map.Add(102982549, lists.toMap());
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 购买英雄
    public void buyHero(int hgid, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 244649888);  // cmd:buyHero
        _map.Add(3200602, hgid);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 买活已死英雄
    public void liveHero(int hgid, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1417322246);  // cmd:liveHero
        _map.Add(3200602, hgid);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 英雄死亡
    public void deadHero(int hgid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 502558334);  // cmd:deadHero
        _map.Add(3200602, hgid);
        chn.send(_map);
    }

    // 升级后英雄的数据下发
    public void refreshHeros() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -67879522);  // cmd:refreshHeros
        chn.send(_map);
    }

    // 增加英雄能源:力量,血量,攻击速度
    public void addHeroEnergy(int hgid, int egid, int costGold, int costOil, int crystal, String sign) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1813858979);  // cmd:addHeroEnergy
        _map.Add(3200602, hgid);
        _map.Add(3111229, egid);
        _map.Add(-425069107, costGold);
        _map.Add(956126917, costOil);
        _map.Add(1047561014, crystal);
        _map.Add(3530173, sign);
        chn.send(_map);
    }

    // 取得个人周排行
    public void getNRankUse(int page) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1138712861);  // cmd:getNRankUse
        _map.Add(3433103, page);
        chn.send(_map);
    }

    // 创建联盟
    public void creatClan(int icon, String cname, String cdesc, String resType, int costVal, int crystal) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 597367423);  // cmd:creatClan
        _map.Add(3226745, icon);
        _map.Add(94802286, cname);
        _map.Add(94508404, cdesc);
        _map.Add(1096575994, resType);
        _map.Add(956133396, costVal);
        _map.Add(1047561014, crystal);
        chn.send(_map);
    }

    // 退出联盟
    public void exitClan(String ccid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -2123231084);  // cmd:exitClan
        _map.Add(3047803, ccid);
        chn.send(_map);
    }

    // 申请入盟
    public void joinInClan(String ccid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1226689253);  // cmd:joinInClan
        _map.Add(3047803, ccid);
        chn.send(_map);
    }

    // 接受入盟
    public void acceptJoinIn(String ccid, int pcid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1704066839);  // cmd:acceptJoinIn
        _map.Add(3047803, ccid);
        _map.Add(3435086, pcid);
        chn.send(_map);
    }

    // 拒绝入盟
    public void refuseJoinIn(String ccid, int pcid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1366339875);  // cmd:refuseJoinIn
        _map.Add(3047803, ccid);
        _map.Add(3435086, pcid);
        chn.send(_map);
    }

    // 查找联盟
    public void searchClan(String cname) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -710973410);  // cmd:searchClan
        _map.Add(94802286, cname);
        chn.send(_map);
    }

    // 查看联盟
    public void seeClan(String ccid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 1970630985);  // cmd:seeClan
        _map.Add(3047803, ccid);
        chn.send(_map);
    }

    // 取得自己的联盟
    public void getOwnClan() {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -18886970);  // cmd:getOwnClan
        chn.send(_map);
    }

    // 踢出联盟玩家
    public void outClanMember(int pcid) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -2095070594);  // cmd:outClanMember
        _map.Add(3435086, pcid);
        chn.send(_map);
    }

    // 修改联盟描述
    public void changeClan(String desc) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -2132422106);  // cmd:changeClan
        _map.Add(3079825, desc);
        chn.send(_map);
    }

    // 设置职位
    public void allotClanPost(int pcid, int post) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, -1229523588);  // cmd:allotClanPost
        _map.Add(3435086, pcid);
        _map.Add(3446944, post);
        chn.send(_map);
    }


    public static NewSet CMD = NewSet.create().Add(-934594754).Add(1615760901).Add(-508077104).Add(2115707319).Add(929969162).Add(1629150990).Add(444729423).Add(-1056271730).Add(-1004167270).Add(1723144751).Add(62679870).Add(1176996619).Add(-31856230).Add(1127496743).Add(1070317554).Add(501903050).Add(52059937).Add(-858116154).Add(-762930944).Add(-2029211415).Add(-1149482139).Add(-858334864).Add(-868356563).Add(-940903302).Add(-2102563434).Add(1717679356).Add(1137223808).Add(2009579300).Add(-827208560).Add(-1761920275).Add(-1450193907).Add(840284783).Add(1321151899).Add(-320303030).Add(1041716509).Add(-1022537609).Add(-1506041187).Add(-1145738292).Add(-1455260092).Add(-1521297515).Add(809718812).Add(-740593574).Add(-428433996).Add(1093931788).Add(-1241398809).Add(-1377571782).Add(-188503535).Add(926354202).Add(99151942).Add(-1722927346).Add(2131720282).Add(1426859958).Add(1203719192).Add(1246941952).Add(-998949784).Add(-1696857871).Add(624359620).Add(615325507).Add(1247233375).Add(1151145404).Add(-995314110).Add(-1422538308).Add(-1148737611).Add(-612644065).Add(-1662400743).Add(1098415640).Add(-104574235).Add(-1130309809).Add(-1955892762).Add(-797241969).Add(1961543560).Add(1162918381).Add(244649888).Add(1417322246).Add(502558334).Add(-67879522).Add(1813858979).Add(-1138712861).Add(597367423).Add(-2123231084).Add(1226689253).Add(1704066839).Add(1366339875).Add(-710973410).Add(1970630985).Add(-18886970).Add(-2095070594).Add(-2132422106).Add(-1229523588);

    public static bool withIn(Hashtable map) {
        int cmd = MapEx.getInt(map, 0);
        return CMD.Contains(cmd);
    }

    // //////////////////////////////////////////////
    // 逻辑分发
    // //////////////////////////////////////////////

    public void disp(Hashtable map) {
        int cmd = MapEx.getInt(map, 0);
        disp(cmd, map);
    }
    public void disp(int cmd, Hashtable map) {
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
    private void __onCallback_rename(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRename(rst);
    }

    // 登录玩家
    private void __onCallback_loginUserByUid(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayers sign = NPlayers.parse(map2.getMap(3530173));

        onLoginUserByUid(sign, rst);
    }

    // 新增建筑
    private void __onCallback_createBuilding(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onCreateBuilding(rst);
    }

    // 完成移除
    private void __onCallback_finishRemoveBuild(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFinishRemoveBuild(rst);
    }

    // 自然完成完成造兵
    private void __onCallback_finishProduceArmy(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFinishProduceArmy(rst);
    }

    // 快速完成完成造兵
    private void __onCallback_fastFinishProduceArmy(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFastFinishProduceArmy(rst);
    }

    // pve战利品
    private void __onCallback_pveFightSpoils(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NEnergy energy = NEnergy.parse(map2.getMap(-1298713976));

        onPveFightSpoils(energy, rst);
    }

    // 一般查询被攻击的角色
    private void __onCallback_findBeAttactPlayer(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer beAttPlayer = NPlayer.parse(map2.getMap(-1629162529));
        NTeches bePTeches = NTeches.parse(map2.getMap(-619666287));
        NArmys bePArmys = NArmys.parse(map2.getMap(794145289));
        NBuilds bePBuilds = NBuilds.parse(map2.getMap(-1120032110));
        NHeros heros = NHeros.parse(map2.getMap(99168185));
        NClan nclan = NClan.parse(map2.getMap(104643524));
        NInt canGetGold = NInt.parse(map2.getMap(-1479633178));
        NInt canGetOil = NInt.parse(map2.getMap(-324817268));

        onFindBeAttactPlayer(beAttPlayer, bePTeches, bePArmys, bePBuilds, heros, nclan, canGetGold, canGetOil, rst);
    }

    // 购买水晶，内部测试用
    private void __onCallback_buyCrystalForOpenness(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onBuyCrystalForOpenness(rst);
    }

    // 随机名称
    private void __onCallback_randomPlayerName(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal randomName = NStrVal.parse(map2.getMap(115456494));

        onRandomPlayerName(randomName, rst);
    }

    // 成就完成领奖
    private void __onCallback_achievementReward(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAchievementReward(rst);
    }

    // 完成移除障碍
    private void __onCallback_finishRemoveObst(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFinishRemoveObst(rst);
    }

    // 下发的成就列表
    private void __onCallback_getAchievements(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NAchievements marks = NAchievements.parse(map2.getMap(103666502));

        onGetAchievements(marks, rst);
    }

    // 首次充值奖励
    private void __onCallback_rewardFirstPay(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRewardFirstPay(rst);
    }

    // 开宝箱
    private void __onCallback_openTreasureBox(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NEnergys energys = NEnergys.parse(map2.getMap(-1605427477));

        onOpenTreasureBox(energys, rst);
    }

    // 创建充值订单
    private void __onCallback_createOrderPayMoney(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal oriderCid = NStrVal.parse(map2.getMap(-288806573));

        onCreateOrderPayMoney(oriderCid, rst);
    }

    // 每日日常任务奖励
    private void __onCallback_dayTasksReward(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDayTasksReward(rst);
    }

    // 取得个人总排行
    private void __onCallback_getNRankUsersByAll(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NRankUsers rankUses = NRankUsers.parse(map2.getMap(256095768));

        onGetNRankUsersByAll(rankUses, rst);
    }

    // 取得自己联盟成员
    private void __onCallback_getOwnClanMember(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NClanMembers members = NClanMembers.parse(map2.getMap(948881689));

        onGetOwnClanMember(members, rst);
    }

    // 取得自己联盟请求
    private void __onCallback_getOwnClanRequest(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NClanRequests clanreqes = NClanRequests.parse(map2.getMap(-153257674));

        onGetOwnClanRequest(clanreqes, rst);
    }

    // 捐献金币
    private void __onCallback_donateClanGold(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDonateClanGold(rst);
    }

    // 取得联盟总排行
    private void __onCallback_getNRankClanByAll(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NRankClans rankClans = NRankClans.parse(map2.getMap(-667801583));

        onGetNRankClanByAll(rankClans, rst);
    }

    // 捐献油
    private void __onCallback_donateClanOil(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDonateClanOil(rst);
    }

    // 取得联盟周排行
    private void __onCallback_getNRankClan(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NRankClans rankClans = NRankClans.parse(map2.getMap(-667801583));

        onGetNRankClan(rankClans, rst);
    }

    // 改变建筑等级
    private void __onCallback_downBuildLvl(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDownBuildLvl(rst);
    }

    // 渠道快速注册
    private void __onCallback_cmRegistFast(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal outLgId = NStrVal.parse(map2.getMap(-1107201948));
        NStrVal outLgPwd = NStrVal.parse(map2.getMap(36485396));

        onCmRegistFast(outLgId, outLgPwd, rst);
    }

    // 渠道注册
    private void __onCallback_cmRegist(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onCmRegist(rst);
    }

    // 用户分享成功
    private void __onCallback_shareSuccess(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onShareSuccess(rst);
    }

    // 打劫商船
    private void __onCallback_lootMerchant(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onLootMerchant(rst);
    }

    // 登录奇虎360取得uid,token
    private void __onCallback_loginQH360(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal strToken = NStrVal.parse(map2.getMap(1775649352));
        NStrVal strUID = NStrVal.parse(map2.getMap(-892006305));

        onLoginQH360(strToken, strUID, rst);
    }

    // 玩家角色
    private void __onCallback_loginUPlayer(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NSession session = NSession.parse(map2.getMap(1984987798));
        NPlayer player = NPlayer.parse(map2.getMap(-985752863));
        NNPCBeFighteds npcs = NNPCBeFighteds.parse(map2.getMap(3387826));
        NBuilds builds = NBuilds.parse(map2.getMap(-1378023483));
        NTeches teches = NTeches.parse(map2.getMap(-877657660));
        NArmys armys = NArmys.parse(map2.getMap(93086326));
        NProductes army_produtes = NProductes.parse(map2.getMap(1919479750));
        NSpells nspells = NSpells.parse(map2.getMap(-2054907335));
        NHeros heros = NHeros.parse(map2.getMap(99168185));

        onLoginUPlayer(session, player, npcs, builds, teches, armys, army_produtes, nspells, heros, rst);
    }

    // 升级建筑
    private void __onCallback_upBuilding(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onUpBuilding(rst);
    }

    // 完成升级建筑
    private void __onCallback_finishBuild(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFinishBuild(rst);
    }

    // 移除建筑
    private void __onCallback_removeBuild(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRemoveBuild(rst);
    }

    // 移动建筑
    private void __onCallback_moveBuild(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onMoveBuild(rst);
    }

    // 收资源
    private void __onCallback_harvestRes(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onHarvestRes(rst);
    }

    // 造兵和减少兵的接口
    private void __onCallback_produceArmy(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onProduceArmy(rst);
    }

    // 升级科技
    private void __onCallback_upTechnolgy(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onUpTechnolgy(rst);
    }

    // 完成升级科技
    private void __onCallback_finishUpTech(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onFinishUpTech(rst);
    }

    // pve战斗放兵
    private void __onCallback_pveAttackInfo(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onPveAttackInfo(rst);
    }

    // 打pve结果
    private void __onCallback_pveResult(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onPveResult(rst);
    }

    // 开始战斗
    private void __onCallback_beginAttact(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NEnergy energy = NEnergy.parse(map2.getMap(-1298713976));

        onBeginAttact(energy, rst);
    }

    // 战斗信息
    private void __onCallback_attactInfos(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAttactInfos(rst);
    }

    // 结束战斗
    private void __onCallback_endAttact(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onEndAttact(rst);
    }

    // 回到主基地
    private void __onCallback_goHome(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer player = NPlayer.parse(map2.getMap(-985752863));
        NNPCBeFighteds npcs = NNPCBeFighteds.parse(map2.getMap(3387826));
        NBuilds builds = NBuilds.parse(map2.getMap(-1378023483));
        NTeches teches = NTeches.parse(map2.getMap(-877657660));
        NArmys armys = NArmys.parse(map2.getMap(93086326));
        NProductes produtes = NProductes.parse(map2.getMap(-1003745436));
        NSpells nspells = NSpells.parse(map2.getMap(-2054907335));
        NHeros heros = NHeros.parse(map2.getMap(99168185));

        onGoHome(player, npcs, builds, teches, armys, produtes, nspells, heros, rst);
    }

    // 购买资源
    private void __onCallback_buyRes(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer player = NPlayer.parse(map2.getMap(-985752863));

        onBuyRes(player, rst);
    }

    // 战斗回放
    private void __onCallback_replayAttMail(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer player = NPlayer.parse(map2.getMap(-985752863));
        NTeches teches = NTeches.parse(map2.getMap(-877657660));
        NArmys amrys = NArmys.parse(map2.getMap(92942176));
        NBuilds builds = NBuilds.parse(map2.getMap(-1378023483));
        NAttackInfos attInfo = NAttackInfos.parse(map2.getMap(-676062481));
        NLong timeSlot = NLong.parse(map2.getMap(-2077392277));
        NHeros heros = NHeros.parse(map2.getMap(99168185));
        NHeros offHeros = NHeros.parse(map2.getMap(-795697270));
        NClan offNclan = NClan.parse(map2.getMap(-790221931));
        NClan defNclan = NClan.parse(map2.getMap(626197183));

        onReplayAttMail(player, teches, amrys, builds, attInfo, timeSlot, heros, offHeros, offNclan, defNclan, rst);
    }

    // 反击
    private void __onCallback_hitBack(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer beAttPlayer = NPlayer.parse(map2.getMap(-1629162529));
        NTeches bePTeches = NTeches.parse(map2.getMap(-619666287));
        NArmys bePArmys = NArmys.parse(map2.getMap(794145289));
        NBuilds builds = NBuilds.parse(map2.getMap(-1378023483));
        NHeros heros = NHeros.parse(map2.getMap(99168185));
        NClan nclan = NClan.parse(map2.getMap(104643524));

        onHitBack(beAttPlayer, bePTeches, bePArmys, builds, heros, nclan, rst);
    }

    // 心跳
    private void __onCallback_heart(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NInt heat = NInt.parse(map2.getMap(3198448));

        onHeart(heat, rst);
    }

    // 设置名字
    private void __onCallback_setPlayerName(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onSetPlayerName(rst);
    }

    // 修复陷阱
    private void __onCallback_repairTrap(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRepairTrap(rst);
    }

    // 用户下线
    private void __onCallback_downLine(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDownLine(rst);
    }

    // 新手步骤
    private void __onCallback_guidNewPlayer(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onGuidNewPlayer(rst);
    }

    // 聊天内容
    private void __onCallback_sendChat(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onSendChat(rst);
    }

    // 购买序列
    private void __onCallback_buyOrder(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onBuyOrder(rst);
    }

    // 下发战报邮件
    private void __onCallback_getAttMails(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NAttMails attMails = NAttMails.parse(map2.getMap(520209275));

        onGetAttMails(attMails, rst);
    }

    // 下发邮件
    private void __onCallback_getNMails(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NMails nmails = NMails.parse(map2.getMap(-1042102802));

        onGetNMails(nmails, rst);
    }

    // 下发聊天信息
    private void __onCallback_getNChats(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NChats nchats = NChats.parse(map2.getMap(-1051136915));

        onGetNChats(nchats, rst);
    }

    // 新邮件
    private void __onCallback_sendMail(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onSendMail(rst);
    }

    // 结束引导
    private void __onCallback_finishGuid(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer player = NPlayer.parse(map2.getMap(-985752863));
        NBuilds builds = NBuilds.parse(map2.getMap(-1378023483));

        onFinishGuid(player, builds, rst);
    }

    // 购买法术
    private void __onCallback_buySpell(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onBuySpell(rst);
    }

    // 增加经验
    private void __onCallback_addExp(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAddExp(rst);
    }

    // 新增障碍
    private void __onCallback_addObst(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAddObst(rst);
    }

    // 新增障碍城墙
    private void __onCallback_addObstWall(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAddObstWall(rst);
    }

    // 升级障碍-城墙
    private void __onCallback_upObstWall(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onUpObstWall(rst);
    }

    // 移除障碍
    private void __onCallback_removeObst(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRemoveObst(rst);
    }

    // 移动物体
    private void __onCallback_moveObst(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onMoveObst(rst);
    }

    // 改变邮件状态为__已读
    private void __onCallback_readNMail(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onReadNMail(rst);
    }

    // 访问别人
    private void __onCallback_viewPlayer(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPlayer player = NPlayer.parse(map2.getMap(-985752863));
        NBuilds builds = NBuilds.parse(map2.getMap(-1378023483));
        NTeches teches = NTeches.parse(map2.getMap(-877657660));
        NArmys armys = NArmys.parse(map2.getMap(93086326));
        NHeros heros = NHeros.parse(map2.getMap(99168185));

        onViewPlayer(player, builds, teches, armys, heros, rst);
    }

    // 购买保护盾
    private void __onCallback_buyShield(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onBuyShield(rst);
    }

    // 取得用户信息
    private void __onCallback_getPInfo(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NPInfo pinfo = NPInfo.parse(map2.getMap(106671390));

        onGetPInfo(pinfo, rst);
    }

    // 领每日登录奖励
    private void __onCallback_rewardDay(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRewardDay(rst);
    }

    // 购买英雄
    private void __onCallback_buyHero(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onBuyHero(rst);
    }

    // 买活已死英雄
    private void __onCallback_liveHero(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onLiveHero(rst);
    }

    // 英雄死亡
    private void __onCallback_deadHero(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onDeadHero(rst);
    }

    // 升级后英雄的数据下发
    private void __onCallback_refreshHeros(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NHeros heors = NHeros.parse(map2.getMap(99165395));

        onRefreshHeros(heors, rst);
    }

    // 增加英雄能源:力量,血量,攻击速度
    private void __onCallback_addHeroEnergy(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NInt outHgid = NInt.parse(map2.getMap(-1107320120));
        NInt outEgid = NInt.parse(map2.getMap(-1107409493));
        NInt curEnergyNum = NInt.parse(map2.getMap(258062718));
        NInt addval = NInt.parse(map2.getMap(-1422491936));

        onAddHeroEnergy(outHgid, outEgid, curEnergyNum, addval, rst);
    }

    // 取得个人周排行
    private void __onCallback_getNRankUse(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NRankUsers rankUses = NRankUsers.parse(map2.getMap(256095768));

        onGetNRankUse(rankUses, rst);
    }

    // 创建联盟
    private void __onCallback_creatClan(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal ccid = NStrVal.parse(map2.getMap(3047803));

        onCreatClan(ccid, rst);
    }

    // 退出联盟
    private void __onCallback_exitClan(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onExitClan(rst);
    }

    // 申请入盟
    private void __onCallback_joinInClan(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onJoinInClan(rst);
    }

    // 接受入盟
    private void __onCallback_acceptJoinIn(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAcceptJoinIn(rst);
    }

    // 拒绝入盟
    private void __onCallback_refuseJoinIn(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onRefuseJoinIn(rst);
    }

    // 查找联盟
    private void __onCallback_searchClan(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NClans clans = NClans.parse(map2.getMap(94742749));

        onSearchClan(clans, rst);
    }

    // 查看联盟
    private void __onCallback_seeClan(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NClan clan = NClan.parse(map2.getMap(3056214));
        NClanMembers members = NClanMembers.parse(map2.getMap(948881689));

        onSeeClan(clan, members, rst);
    }

    // 取得自己的联盟
    private void __onCallback_getOwnClan(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NClan clan = NClan.parse(map2.getMap(3056214));

        onGetOwnClan(clan, rst);
    }

    // 踢出联盟玩家
    private void __onCallback_outClanMember(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onOutClanMember(rst);
    }

    // 修改联盟描述
    private void __onCallback_changeClan(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onChangeClan(rst);
    }

    // 设置职位
    private void __onCallback_allotClanPost(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);

        onAllotClanPost(rst);
    }


    // //////////////////////////////////////////////
    // 需要实现的接口
    // //////////////////////////////////////////////

    // 修改名称
    public const string const_Rename = "onRename";
    public abstract void onRename(ReturnStatus val);

    // 登录玩家
    public const string const_LoginUserByUid = "onLoginUserByUid";
    public abstract void onLoginUserByUid(NPlayers sign, ReturnStatus val);

    // 新增建筑
    public const string const_CreateBuilding = "onCreateBuilding";
    public abstract void onCreateBuilding(ReturnStatus val);

    // 完成移除
    public const string const_FinishRemoveBuild = "onFinishRemoveBuild";
    public abstract void onFinishRemoveBuild(ReturnStatus val);

    // 自然完成完成造兵
    public const string const_FinishProduceArmy = "onFinishProduceArmy";
    public abstract void onFinishProduceArmy(ReturnStatus val);

    // 快速完成完成造兵
    public const string const_FastFinishProduceArmy = "onFastFinishProduceArmy";
    public abstract void onFastFinishProduceArmy(ReturnStatus val);

    // pve战利品
    public const string const_PveFightSpoils = "onPveFightSpoils";
    public abstract void onPveFightSpoils(NEnergy energy, ReturnStatus val);

    // 一般查询被攻击的角色
    public const string const_FindBeAttactPlayer = "onFindBeAttactPlayer";
    public abstract void onFindBeAttactPlayer(NPlayer beAttPlayer, NTeches bePTeches, NArmys bePArmys, NBuilds bePBuilds, NHeros heros, NClan nclan, NInt canGetGold, NInt canGetOil, ReturnStatus val);

    // 购买水晶，内部测试用
    public const string const_BuyCrystalForOpenness = "onBuyCrystalForOpenness";
    public abstract void onBuyCrystalForOpenness(ReturnStatus val);

    // 随机名称
    public const string const_RandomPlayerName = "onRandomPlayerName";
    public abstract void onRandomPlayerName(NStrVal randomName, ReturnStatus val);

    // 成就完成领奖
    public const string const_AchievementReward = "onAchievementReward";
    public abstract void onAchievementReward(ReturnStatus val);

    // 完成移除障碍
    public const string const_FinishRemoveObst = "onFinishRemoveObst";
    public abstract void onFinishRemoveObst(ReturnStatus val);

    // 下发的成就列表
    public const string const_GetAchievements = "onGetAchievements";
    public abstract void onGetAchievements(NAchievements marks, ReturnStatus val);

    // 首次充值奖励
    public const string const_RewardFirstPay = "onRewardFirstPay";
    public abstract void onRewardFirstPay(ReturnStatus val);

    // 开宝箱
    public const string const_OpenTreasureBox = "onOpenTreasureBox";
    public abstract void onOpenTreasureBox(NEnergys energys, ReturnStatus val);

    // 创建充值订单
    public const string const_CreateOrderPayMoney = "onCreateOrderPayMoney";
    public abstract void onCreateOrderPayMoney(NStrVal oriderCid, ReturnStatus val);

    // 每日日常任务奖励
    public const string const_DayTasksReward = "onDayTasksReward";
    public abstract void onDayTasksReward(ReturnStatus val);

    // 取得个人总排行
    public const string const_GetNRankUsersByAll = "onGetNRankUsersByAll";
    public abstract void onGetNRankUsersByAll(NRankUsers rankUses, ReturnStatus val);

    // 取得自己联盟成员
    public const string const_GetOwnClanMember = "onGetOwnClanMember";
    public abstract void onGetOwnClanMember(NClanMembers members, ReturnStatus val);

    // 取得自己联盟请求
    public const string const_GetOwnClanRequest = "onGetOwnClanRequest";
    public abstract void onGetOwnClanRequest(NClanRequests clanreqes, ReturnStatus val);

    // 捐献金币
    public const string const_DonateClanGold = "onDonateClanGold";
    public abstract void onDonateClanGold(ReturnStatus val);

    // 取得联盟总排行
    public const string const_GetNRankClanByAll = "onGetNRankClanByAll";
    public abstract void onGetNRankClanByAll(NRankClans rankClans, ReturnStatus val);

    // 捐献油
    public const string const_DonateClanOil = "onDonateClanOil";
    public abstract void onDonateClanOil(ReturnStatus val);

    // 取得联盟周排行
    public const string const_GetNRankClan = "onGetNRankClan";
    public abstract void onGetNRankClan(NRankClans rankClans, ReturnStatus val);

    // 改变建筑等级
    public const string const_DownBuildLvl = "onDownBuildLvl";
    public abstract void onDownBuildLvl(ReturnStatus val);

    // 渠道快速注册
    public const string const_CmRegistFast = "onCmRegistFast";
    public abstract void onCmRegistFast(NStrVal outLgId, NStrVal outLgPwd, ReturnStatus val);

    // 渠道注册
    public const string const_CmRegist = "onCmRegist";
    public abstract void onCmRegist(ReturnStatus val);

    // 用户分享成功
    public const string const_ShareSuccess = "onShareSuccess";
    public abstract void onShareSuccess(ReturnStatus val);

    // 打劫商船
    public const string const_LootMerchant = "onLootMerchant";
    public abstract void onLootMerchant(ReturnStatus val);

    // 登录奇虎360取得uid,token
    public const string const_LoginQH360 = "onLoginQH360";
    public abstract void onLoginQH360(NStrVal strToken, NStrVal strUID, ReturnStatus val);

    // 玩家角色
    public const string const_LoginUPlayer = "onLoginUPlayer";
    public abstract void onLoginUPlayer(NSession session, NPlayer player, NNPCBeFighteds npcs, NBuilds builds, NTeches teches, NArmys armys, NProductes army_produtes, NSpells nspells, NHeros heros, ReturnStatus val);

    // 升级建筑
    public const string const_UpBuilding = "onUpBuilding";
    public abstract void onUpBuilding(ReturnStatus val);

    // 完成升级建筑
    public const string const_FinishBuild = "onFinishBuild";
    public abstract void onFinishBuild(ReturnStatus val);

    // 移除建筑
    public const string const_RemoveBuild = "onRemoveBuild";
    public abstract void onRemoveBuild(ReturnStatus val);

    // 移动建筑
    public const string const_MoveBuild = "onMoveBuild";
    public abstract void onMoveBuild(ReturnStatus val);

    // 收资源
    public const string const_HarvestRes = "onHarvestRes";
    public abstract void onHarvestRes(ReturnStatus val);

    // 造兵和减少兵的接口
    public const string const_ProduceArmy = "onProduceArmy";
    public abstract void onProduceArmy(ReturnStatus val);

    // 升级科技
    public const string const_UpTechnolgy = "onUpTechnolgy";
    public abstract void onUpTechnolgy(ReturnStatus val);

    // 完成升级科技
    public const string const_FinishUpTech = "onFinishUpTech";
    public abstract void onFinishUpTech(ReturnStatus val);

    // pve战斗放兵
    public const string const_PveAttackInfo = "onPveAttackInfo";
    public abstract void onPveAttackInfo(ReturnStatus val);

    // 打pve结果
    public const string const_PveResult = "onPveResult";
    public abstract void onPveResult(ReturnStatus val);

    // 开始战斗
    public const string const_BeginAttact = "onBeginAttact";
    public abstract void onBeginAttact(NEnergy energy, ReturnStatus val);

    // 战斗信息
    public const string const_AttactInfos = "onAttactInfos";
    public abstract void onAttactInfos(ReturnStatus val);

    // 结束战斗
    public const string const_EndAttact = "onEndAttact";
    public abstract void onEndAttact(ReturnStatus val);

    // 回到主基地
    public const string const_GoHome = "onGoHome";
    public abstract void onGoHome(NPlayer player, NNPCBeFighteds npcs, NBuilds builds, NTeches teches, NArmys armys, NProductes produtes, NSpells nspells, NHeros heros, ReturnStatus val);

    // 购买资源
    public const string const_BuyRes = "onBuyRes";
    public abstract void onBuyRes(NPlayer player, ReturnStatus val);

    // 战斗回放
    public const string const_ReplayAttMail = "onReplayAttMail";
    public abstract void onReplayAttMail(NPlayer player, NTeches teches, NArmys amrys, NBuilds builds, NAttackInfos attInfo, NLong timeSlot, NHeros heros, NHeros offHeros, NClan offNclan, NClan defNclan, ReturnStatus val);

    // 反击
    public const string const_HitBack = "onHitBack";
    public abstract void onHitBack(NPlayer beAttPlayer, NTeches bePTeches, NArmys bePArmys, NBuilds builds, NHeros heros, NClan nclan, ReturnStatus val);

    // 心跳
    public const string const_Heart = "onHeart";
    public abstract void onHeart(NInt heat, ReturnStatus val);

    // 设置名字
    public const string const_SetPlayerName = "onSetPlayerName";
    public abstract void onSetPlayerName(ReturnStatus val);

    // 修复陷阱
    public const string const_RepairTrap = "onRepairTrap";
    public abstract void onRepairTrap(ReturnStatus val);

    // 用户下线
    public const string const_DownLine = "onDownLine";
    public abstract void onDownLine(ReturnStatus val);

    // 新手步骤
    public const string const_GuidNewPlayer = "onGuidNewPlayer";
    public abstract void onGuidNewPlayer(ReturnStatus val);

    // 聊天内容
    public const string const_SendChat = "onSendChat";
    public abstract void onSendChat(ReturnStatus val);

    // 购买序列
    public const string const_BuyOrder = "onBuyOrder";
    public abstract void onBuyOrder(ReturnStatus val);

    // 下发战报邮件
    public const string const_GetAttMails = "onGetAttMails";
    public abstract void onGetAttMails(NAttMails attMails, ReturnStatus val);

    // 下发邮件
    public const string const_GetNMails = "onGetNMails";
    public abstract void onGetNMails(NMails nmails, ReturnStatus val);

    // 下发聊天信息
    public const string const_GetNChats = "onGetNChats";
    public abstract void onGetNChats(NChats nchats, ReturnStatus val);

    // 新邮件
    public const string const_SendMail = "onSendMail";
    public abstract void onSendMail(ReturnStatus val);

    // 结束引导
    public const string const_FinishGuid = "onFinishGuid";
    public abstract void onFinishGuid(NPlayer player, NBuilds builds, ReturnStatus val);

    // 购买法术
    public const string const_BuySpell = "onBuySpell";
    public abstract void onBuySpell(ReturnStatus val);

    // 增加经验
    public const string const_AddExp = "onAddExp";
    public abstract void onAddExp(ReturnStatus val);

    // 新增障碍
    public const string const_AddObst = "onAddObst";
    public abstract void onAddObst(ReturnStatus val);

    // 新增障碍城墙
    public const string const_AddObstWall = "onAddObstWall";
    public abstract void onAddObstWall(ReturnStatus val);

    // 升级障碍-城墙
    public const string const_UpObstWall = "onUpObstWall";
    public abstract void onUpObstWall(ReturnStatus val);

    // 移除障碍
    public const string const_RemoveObst = "onRemoveObst";
    public abstract void onRemoveObst(ReturnStatus val);

    // 移动物体
    public const string const_MoveObst = "onMoveObst";
    public abstract void onMoveObst(ReturnStatus val);

    // 改变邮件状态为__已读
    public const string const_ReadNMail = "onReadNMail";
    public abstract void onReadNMail(ReturnStatus val);

    // 访问别人
    public const string const_ViewPlayer = "onViewPlayer";
    public abstract void onViewPlayer(NPlayer player, NBuilds builds, NTeches teches, NArmys armys, NHeros heros, ReturnStatus val);

    // 购买保护盾
    public const string const_BuyShield = "onBuyShield";
    public abstract void onBuyShield(ReturnStatus val);

    // 取得用户信息
    public const string const_GetPInfo = "onGetPInfo";
    public abstract void onGetPInfo(NPInfo pinfo, ReturnStatus val);

    // 领每日登录奖励
    public const string const_RewardDay = "onRewardDay";
    public abstract void onRewardDay(ReturnStatus val);

    // 购买英雄
    public const string const_BuyHero = "onBuyHero";
    public abstract void onBuyHero(ReturnStatus val);

    // 买活已死英雄
    public const string const_LiveHero = "onLiveHero";
    public abstract void onLiveHero(ReturnStatus val);

    // 英雄死亡
    public const string const_DeadHero = "onDeadHero";
    public abstract void onDeadHero(ReturnStatus val);

    // 升级后英雄的数据下发
    public const string const_RefreshHeros = "onRefreshHeros";
    public abstract void onRefreshHeros(NHeros heors, ReturnStatus val);

    // 增加英雄能源:力量,血量,攻击速度
    public const string const_AddHeroEnergy = "onAddHeroEnergy";
    public abstract void onAddHeroEnergy(NInt outHgid, NInt outEgid, NInt curEnergyNum, NInt addval, ReturnStatus val);

    // 取得个人周排行
    public const string const_GetNRankUse = "onGetNRankUse";
    public abstract void onGetNRankUse(NRankUsers rankUses, ReturnStatus val);

    // 创建联盟
    public const string const_CreatClan = "onCreatClan";
    public abstract void onCreatClan(NStrVal ccid, ReturnStatus val);

    // 退出联盟
    public const string const_ExitClan = "onExitClan";
    public abstract void onExitClan(ReturnStatus val);

    // 申请入盟
    public const string const_JoinInClan = "onJoinInClan";
    public abstract void onJoinInClan(ReturnStatus val);

    // 接受入盟
    public const string const_AcceptJoinIn = "onAcceptJoinIn";
    public abstract void onAcceptJoinIn(ReturnStatus val);

    // 拒绝入盟
    public const string const_RefuseJoinIn = "onRefuseJoinIn";
    public abstract void onRefuseJoinIn(ReturnStatus val);

    // 查找联盟
    public const string const_SearchClan = "onSearchClan";
    public abstract void onSearchClan(NClans clans, ReturnStatus val);

    // 查看联盟
    public const string const_SeeClan = "onSeeClan";
    public abstract void onSeeClan(NClan clan, NClanMembers members, ReturnStatus val);

    // 取得自己的联盟
    public const string const_GetOwnClan = "onGetOwnClan";
    public abstract void onGetOwnClan(NClan clan, ReturnStatus val);

    // 踢出联盟玩家
    public const string const_OutClanMember = "onOutClanMember";
    public abstract void onOutClanMember(ReturnStatus val);

    // 修改联盟描述
    public const string const_ChangeClan = "onChangeClan";
    public abstract void onChangeClan(ReturnStatus val);

    // 设置职位
    public const string const_AllotClanPost = "onAllotClanPost";
    public abstract void onAllotClanPost(ReturnStatus val);

    }
}
