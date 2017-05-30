package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NChat {
    public static final int _CID = 74133702;

    public int ncid; 
    public int uuid; 
    public int type; 
    public String content = ""; 
    public String toPName = ""; 
    public String fromPName = ""; 
    public long createTime; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3375504, ncid);
        r.put(3601339, uuid);
        r.put(3575610, type);
        r.put(951530617, content);
        r.put(-1169617568, toPName);
        r.put(65694097, fromPName);
        r.put(1369213417, createTime);
        return r;
    }


    public static NChat parse(NewMap map2) {
        if(map2 == null) return null;

        NChat r = new NChat();
        r.ncid = map2.getInt(3375504);
        r.uuid = map2.getInt(3601339);
        r.type = map2.getInt(3575610);
        r.content = map2.getString(951530617);
        r.toPName = map2.getString(-1169617568);
        r.fromPName = map2.getString(65694097);
        r.createTime = map2.getLong(1369213417);
        return r;
    }

    public String toString() {
        return "NChat[ncid=" + ncid + ", uuid=" + uuid + ", type=" + type + ", content=" + content + ", toPName=" + toPName + ", fromPName=" + fromPName + ", createTime=" + createTime + "]";
    }

}
