package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NMail {
    public static final int _CID = 74425125;

    public int mid; 
    public int type; 
    public boolean isRead; 
    public String title = ""; 
    public String content = ""; 
    public String toPName = ""; 
    public String fromPName = ""; 
    public long createTime; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(108104, mid);
        r.put(3575610, type);
        r.put(-1180158496, isRead);
        r.put(110371416, title);
        r.put(951530617, content);
        r.put(-1169617568, toPName);
        r.put(65694097, fromPName);
        r.put(1369213417, createTime);
        return r;
    }


    public static NMail parse(NewMap map2) {
        if(map2 == null) return null;

        NMail r = new NMail();
        r.mid = map2.getInt(108104);
        r.type = map2.getInt(3575610);
        r.isRead = map2.getBoolean(-1180158496);
        r.title = map2.getString(110371416);
        r.content = map2.getString(951530617);
        r.toPName = map2.getString(-1169617568);
        r.fromPName = map2.getString(65694097);
        r.createTime = map2.getLong(1369213417);
        return r;
    }

    public String toString() {
        return "NMail[mid=" + mid + ", type=" + type + ", isRead=" + isRead + ", title=" + title + ", content=" + content + ", toPName=" + toPName + ", fromPName=" + fromPName + ", createTime=" + createTime + "]";
    }

}
