package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NSpell {
    public static final int _CID = -1981804166;

    public int gid; 
    public int num; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(102338, gid);
        r.put(109446, num);
        return r;
    }


    public static NSpell parse(NewMap map2) {
        if(map2 == null) return null;

        NSpell r = new NSpell();
        r.gid = map2.getInt(102338);
        r.num = map2.getInt(109446);
        return r;
    }

    public String toString() {
        return "NSpell[gid=" + gid + ", num=" + num + "]";
    }

}
