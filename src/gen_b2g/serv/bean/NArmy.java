package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NArmy {
    public static final int _CID = 74084107;

    public int aid; 
    public String aname = ""; 
    public int gid; 
    public int lvl; 
    public int num; 
    public int buildClientId; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(96572, aid);
        r.put(92955244, aname);
        r.put(102338, gid);
        r.put(107554, lvl);
        r.put(109446, num);
        r.put(400364244, buildClientId);
        return r;
    }


    public static NArmy parse(NewMap map2) {
        if(map2 == null) return null;

        NArmy r = new NArmy();
        r.aid = map2.getInt(96572);
        r.aname = map2.getString(92955244);
        r.gid = map2.getInt(102338);
        r.lvl = map2.getInt(107554);
        r.num = map2.getInt(109446);
        r.buildClientId = map2.getInt(400364244);
        return r;
    }

    public String toString() {
        return "NArmy[aid=" + aid + ", aname=" + aname + ", gid=" + gid + ", lvl=" + lvl + ", num=" + num + ", buildClientId=" + buildClientId + "]";
    }

}
