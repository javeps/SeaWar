package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NAchievement {
    public static final int _CID = 599038465;

    public int gid; 
    public int localId; 
    public int val; // 当前完成值
    public boolean isDraw; // 是否领取过奖励


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(102338, gid);
        r.put(338409958, localId);
        r.put(116513, val);
        r.put(-1180563058, isDraw);
        return r;
    }


    public static NAchievement parse(NewMap map2) {
        if(map2 == null) return null;

        NAchievement r = new NAchievement();
        r.gid = map2.getInt(102338);
        r.localId = map2.getInt(338409958);
        r.val = map2.getInt(116513);
        r.isDraw = map2.getBoolean(-1180563058);
        return r;
    }

    public String toString() {
        return "NAchievement[gid=" + gid + ", localId=" + localId + ", val=" + val + ", isDraw=" + isDraw + "]";
    }

}
