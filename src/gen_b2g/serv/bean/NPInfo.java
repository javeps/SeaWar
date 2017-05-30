package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NPInfo {
    public static final int _CID = -1985728112;

    public int pcid; 
    public long crystal; 
    public long storedOil; 
    public long storedGold; 
    public long protectTime; 
    public int maxOrderNum; 
    public int firstPayStatus; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3435086, pcid);
        r.put(1047561014, crystal);
        r.put(1692420911, storedOil);
        r.put(925208227, storedGold);
        r.put(-691626436, protectTime);
        r.put(-1028380964, maxOrderNum);
        r.put(435462634, firstPayStatus);
        return r;
    }


    public static NPInfo parse(NewMap map2) {
        if(map2 == null) return null;

        NPInfo r = new NPInfo();
        r.pcid = map2.getInt(3435086);
        r.crystal = map2.getLong(1047561014);
        r.storedOil = map2.getLong(1692420911);
        r.storedGold = map2.getLong(925208227);
        r.protectTime = map2.getLong(-691626436);
        r.maxOrderNum = map2.getInt(-1028380964);
        r.firstPayStatus = map2.getInt(435462634);
        return r;
    }

    public String toString() {
        return "NPInfo[pcid=" + pcid + ", crystal=" + crystal + ", storedOil=" + storedOil + ", storedGold=" + storedGold + ", protectTime=" + protectTime + ", maxOrderNum=" + maxOrderNum + ", firstPayStatus=" + firstPayStatus + "]";
    }

}
