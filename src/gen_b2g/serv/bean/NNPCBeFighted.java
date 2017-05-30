package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NNPCBeFighted {
    public static final int _CID = -533352327;

    public int ncid; 
    public int star; 
    public int gold; // 被掠夺了的金币资源值
    public int oil; // 被掠夺了的油币资源值


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3375504, ncid);
        r.put(3540562, star);
        r.put(3178592, gold);
        r.put(110034, oil);
        return r;
    }


    public static NNPCBeFighted parse(NewMap map2) {
        if(map2 == null) return null;

        NNPCBeFighted r = new NNPCBeFighted();
        r.ncid = map2.getInt(3375504);
        r.star = map2.getInt(3540562);
        r.gold = map2.getInt(3178592);
        r.oil = map2.getInt(110034);
        return r;
    }

    public String toString() {
        return "NNPCBeFighted[ncid=" + ncid + ", star=" + star + ", gold=" + gold + ", oil=" + oil + "]";
    }

}
