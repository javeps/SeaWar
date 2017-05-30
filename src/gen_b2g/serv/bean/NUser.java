package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NUser {
    public static final int _CID = 74680633;

    public int id; 
    public String uid = ""; 
    public String uuid = ""; 
    public String uname = ""; 
    public int type; 
    public int state; 
    public long logtin_time; 
    public String model = ""; 
    public String channel = ""; 
    public String version = ""; 
    public String remain = ""; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3355, id);
        r.put(115792, uid);
        r.put(3601339, uuid);
        r.put(111425664, uname);
        r.put(3575610, type);
        r.put(109757585, state);
        r.put(-377953801, logtin_time);
        r.put(104069929, model);
        r.put(738950403, channel);
        r.put(351608024, version);
        r.put(-934624660, remain);
        return r;
    }


    public static NUser parse(NewMap map2) {
        if(map2 == null) return null;

        NUser r = new NUser();
        r.id = map2.getInt(3355);
        r.uid = map2.getString(115792);
        r.uuid = map2.getString(3601339);
        r.uname = map2.getString(111425664);
        r.type = map2.getInt(3575610);
        r.state = map2.getInt(109757585);
        r.logtin_time = map2.getLong(-377953801);
        r.model = map2.getString(104069929);
        r.channel = map2.getString(738950403);
        r.version = map2.getString(351608024);
        r.remain = map2.getString(-934624660);
        return r;
    }

    public String toString() {
        return "NUser[id=" + id + ", uid=" + uid + ", uuid=" + uuid + ", uname=" + uname + ", type=" + type + ", state=" + state + ", logtin_time=" + logtin_time + ", model=" + model + ", channel=" + channel + ", version=" + version + ", remain=" + remain + "]";
    }

}
