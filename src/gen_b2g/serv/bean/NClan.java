package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NClan {
    public static final int _CID = 74137540;

    public String ccid = ""; // 联盟唯一标识
    public String cname = ""; 
    public int icon; 
    public String desc = ""; 
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


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3047803, ccid);
        r.put(94802286, cname);
        r.put(3226745, icon);
        r.put(3079825, desc);
        r.put(-1081154686, maxNum);
        r.put(-1349154522, curNum);
        r.put(1369213417, createTime);
        r.put(-400606568, pointHP);
        r.put(466092785, pointAtt);
        r.put(-1310789943, eachHP);
        r.put(-1979788064, eachAtt);
        r.put(1125668544, curGold);
        r.put(-1349153934, curOil);
        r.put(843728868, maxGold);
        r.put(-1081154098, maxOil);
        return r;
    }


    public static NClan parse(NewMap map2) {
        if(map2 == null) return null;

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

    public String toString() {
        return "NClan[ccid=" + ccid + ", cname=" + cname + ", icon=" + icon + ", desc=" + desc + ", maxNum=" + maxNum + ", curNum=" + curNum + ", createTime=" + createTime + ", pointHP=" + pointHP + ", pointAtt=" + pointAtt + ", eachHP=" + eachHP + ", eachAtt=" + eachAtt + ", curGold=" + curGold + ", curOil=" + curOil + ", maxGold=" + maxGold + ", maxOil=" + maxOil + "]";
    }

}
