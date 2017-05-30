package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NRankUser {
    public static final int _CID = 1073212549;

    public int pid; 
    public String pname = ""; 
    public int pexp; 
    public String clancid = ""; 
    public int clanIcon; 
    public String clanName = ""; 
    public int rankIndex; 
    public int renown; 
    public int weekRenown; 
    public int type; // 0周1总


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(110987, pid);
        r.put(106808059, pname);
        r.put(3437485, pexp);
        r.put(853456552, clancid);
        r.put(686569455, clanIcon);
        r.put(686716417, clanName);
        r.put(-662198266, rankIndex);
        r.put(-934580981, renown);
        r.put(-1226493409, weekRenown);
        r.put(3575610, type);
        return r;
    }


    public static NRankUser parse(NewMap map2) {
        if(map2 == null) return null;

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

    public String toString() {
        return "NRankUser[pid=" + pid + ", pname=" + pname + ", pexp=" + pexp + ", clancid=" + clancid + ", clanIcon=" + clanIcon + ", clanName=" + clanName + ", rankIndex=" + rankIndex + ", renown=" + renown + ", weekRenown=" + weekRenown + ", type=" + type + "]";
    }

}
