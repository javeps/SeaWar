package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NEnergy {
    public static final int _CID = -1709036426;

    public int egid; // GID
    public int num; // 数量


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3111229, egid);
        r.put(109446, num);
        return r;
    }


    public static NEnergy parse(NewMap map2) {
        if(map2 == null) return null;

        NEnergy r = new NEnergy();
        r.egid = map2.getInt(3111229);
        r.num = map2.getInt(109446);
        return r;
    }

    public String toString() {
        return "NEnergy[egid=" + egid + ", num=" + num + "]";
    }

}
