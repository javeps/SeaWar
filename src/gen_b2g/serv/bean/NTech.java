package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NTech {
    public static final int _CID = 74637316;

    public int tid; 
    public String tname = ""; 
    public int gid; 
    public int lvl; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(114831, tid);
        r.put(110502143, tname);
        r.put(102338, gid);
        r.put(107554, lvl);
        return r;
    }


    public static NTech parse(NewMap map2) {
        if(map2 == null) return null;

        NTech r = new NTech();
        r.tid = map2.getInt(114831);
        r.tname = map2.getString(110502143);
        r.gid = map2.getInt(102338);
        r.lvl = map2.getInt(107554);
        return r;
    }

    public String toString() {
        return "NTech[tid=" + tid + ", tname=" + tname + ", gid=" + gid + ", lvl=" + lvl + "]";
    }

}
