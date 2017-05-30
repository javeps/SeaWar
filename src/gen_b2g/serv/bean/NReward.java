package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NReward {
    public static final int _CID = -1344648931;

    public int type; // 类型
    public int val; // 奖励值
    public int gid; // gid
    public int lvl; // 等级
    public int bcid; // 建筑的client


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3575610, type);
        r.put(116513, val);
        r.put(102338, gid);
        r.put(107554, lvl);
        r.put(3018012, bcid);
        return r;
    }


    public static NReward parse(NewMap map2) {
        if(map2 == null) return null;

        NReward r = new NReward();
        r.type = map2.getInt(3575610);
        r.val = map2.getInt(116513);
        r.gid = map2.getInt(102338);
        r.lvl = map2.getInt(107554);
        r.bcid = map2.getInt(3018012);
        return r;
    }

    public String toString() {
        return "NReward[type=" + type + ", val=" + val + ", gid=" + gid + ", lvl=" + lvl + ", bcid=" + bcid + "]";
    }

}
