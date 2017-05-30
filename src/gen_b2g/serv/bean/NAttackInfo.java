package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NAttackInfo {
    public static final int _CID = 1603384836;

    public int type; 
    public int bcid; 
    public int gid; 
    public int lvl; 
    public int num; 
    public double x; 
    public double y; 
    public long diffTime; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3575610, type);
        r.put(3018012, bcid);
        r.put(102338, gid);
        r.put(107554, lvl);
        r.put(109446, num);
        r.put(120, x);
        r.put(121, y);
        r.put(-97040270, diffTime);
        return r;
    }


    public static NAttackInfo parse(NewMap map2) {
        if(map2 == null) return null;

        NAttackInfo r = new NAttackInfo();
        r.type = map2.getInt(3575610);
        r.bcid = map2.getInt(3018012);
        r.gid = map2.getInt(102338);
        r.lvl = map2.getInt(107554);
        r.num = map2.getInt(109446);
        r.x = map2.getDouble(120);
        r.y = map2.getDouble(121);
        r.diffTime = map2.getLong(-97040270);
        return r;
    }

    public String toString() {
        return "NAttackInfo[type=" + type + ", bcid=" + bcid + ", gid=" + gid + ", lvl=" + lvl + ", num=" + num + ", x=" + x + ", y=" + y + ", diffTime=" + diffTime + "]";
    }

}
