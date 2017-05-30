package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NProducte {
    public static final int _CID = -186644540;

    public int pid; 
    public int gid; 
    public int building_id; 
    public int num; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(110987, pid);
        r.put(102338, gid);
        r.put(-1417153914, building_id);
        r.put(109446, num);
        return r;
    }


    public static NProducte parse(NewMap map2) {
        if(map2 == null) return null;

        NProducte r = new NProducte();
        r.pid = map2.getInt(110987);
        r.gid = map2.getInt(102338);
        r.building_id = map2.getInt(-1417153914);
        r.num = map2.getInt(109446);
        return r;
    }

    public String toString() {
        return "NProducte[pid=" + pid + ", gid=" + gid + ", building_id=" + building_id + ", num=" + num + "]";
    }

}
