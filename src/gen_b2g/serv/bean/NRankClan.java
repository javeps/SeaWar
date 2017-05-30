package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NRankClan {
    public static final int _CID = 1072669456;

    public String ccid = ""; // 联盟唯一标识
    public int icon; 
    public String cname = ""; 
    public int currank; 
    public int renownAll; 
    public int renownWeek; 
    public int type; // 0周1总
    public int curnum; 
    public int maxnum; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3047803, ccid);
        r.put(3226745, icon);
        r.put(94802286, cname);
        r.put(1126936172, currank);
        r.put(-2123926378, renownAll);
        r.put(-1416559713, renownWeek);
        r.put(3575610, type);
        r.put(-1349123770, curnum);
        r.put(-1081123934, maxnum);
        return r;
    }


    public static NRankClan parse(NewMap map2) {
        if(map2 == null) return null;

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

    public String toString() {
        return "NRankClan[ccid=" + ccid + ", icon=" + icon + ", cname=" + cname + ", currank=" + currank + ", renownAll=" + renownAll + ", renownWeek=" + renownWeek + ", type=" + type + ", curnum=" + curnum + ", maxnum=" + maxnum + "]";
    }

}
