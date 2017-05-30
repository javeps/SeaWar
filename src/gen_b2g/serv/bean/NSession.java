package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NSession {
    public static final int _CID = -2145073560;

    public int sessionKey; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(1661834217, sessionKey);
        return r;
    }


    public static NSession parse(NewMap map2) {
        if(map2 == null) return null;

        NSession r = new NSession();
        r.sessionKey = map2.getInt(1661834217);
        return r;
    }

    public String toString() {
        return "NSession[sessionKey=" + sessionKey + "]";
    }

}
