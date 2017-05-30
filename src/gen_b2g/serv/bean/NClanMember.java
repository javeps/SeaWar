package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NClanMember {
    public static final int _CID = 2049758078;

    public String ccid = ""; // 联盟唯一标识
    public int pcid; 
    public String pname = ""; 
    public int post; 
    public int donateGold; 
    public int donateOil; 
    public int curDGold; 
    public int curDOil; 
    public long lastDGold; 
    public long lastDOil; 
    public int renownAll; 
    public int renownWeek; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3047803, ccid);
        r.put(3435086, pcid);
        r.put(106808059, pname);
        r.put(3446944, post);
        r.put(805138287, donateGold);
        r.put(1550000355, donateOil);
        r.put(532027524, curDGold);
        r.put(1125548334, curDOil);
        r.put(1987038702, lastDGold);
        r.put(-1459915132, lastDOil);
        r.put(-2123926378, renownAll);
        r.put(-1416559713, renownWeek);
        return r;
    }


    public static NClanMember parse(NewMap map2) {
        if(map2 == null) return null;

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

    public String toString() {
        return "NClanMember[ccid=" + ccid + ", pcid=" + pcid + ", pname=" + pname + ", post=" + post + ", donateGold=" + donateGold + ", donateOil=" + donateOil + ", curDGold=" + curDGold + ", curDOil=" + curDOil + ", lastDGold=" + lastDGold + ", lastDOil=" + lastDOil + ", renownAll=" + renownAll + ", renownWeek=" + renownWeek + "]";
    }

}
