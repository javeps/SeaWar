package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NAttMail {
    public static final int _CID = -511049782;

    public int mid; 
    public String mcid = ""; 
    public int attPid; 
    public String attName = ""; 
    public String clancid = ""; 
    public String clanName = ""; 
    public int clanIcon; 
    public int preRenown; 
    public int attGold; 
    public int attOil; 
    public int star; 
    public int attRenown; 
    public boolean isHitBack; 
    public long createTime; 
    public long endTime; 
    public NAttackInfos attInfo; 
    public int egid; 
    public int num; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(108104, mid);
        r.put(3345713, mcid);
        r.put(-1407275222, attPid);
        r.put(-675925812, attName);
        r.put(853456552, clancid);
        r.put(686716417, clanName);
        r.put(686569455, clanIcon);
        r.put(-1866709618, preRenown);
        r.put(-676120927, attGold);
        r.put(-1407276175, attOil);
        r.put(3540562, star);
        r.put(-906389748, attRenown);
        r.put(346575504, isHitBack);
        r.put(1369213417, createTime);
        r.put(-1607243192, endTime);
        r.put(-676062481, attInfo.toMap());
        r.put(3111229, egid);
        r.put(109446, num);
        return r;
    }


    public static NAttMail parse(NewMap map2) {
        if(map2 == null) return null;

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
        r.isHitBack = map2.getBoolean(346575504);
        r.createTime = map2.getLong(1369213417);
        r.endTime = map2.getLong(-1607243192);
        r.attInfo = NAttackInfos.parse(map2.getNewMap(-676062481));
        r.egid = map2.getInt(3111229);
        r.num = map2.getInt(109446);
        return r;
    }

    public String toString() {
        return "NAttMail[mid=" + mid + ", mcid=" + mcid + ", attPid=" + attPid + ", attName=" + attName + ", clancid=" + clancid + ", clanName=" + clanName + ", clanIcon=" + clanIcon + ", preRenown=" + preRenown + ", attGold=" + attGold + ", attOil=" + attOil + ", star=" + star + ", attRenown=" + attRenown + ", isHitBack=" + isHitBack + ", createTime=" + createTime + ", endTime=" + endTime + ", attInfo=" + attInfo + ", egid=" + egid + ", num=" + num + "]";
    }

}
