package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NClanRequest {
    public static final int _CID = -735197653;

    public String ccid = ""; // 联盟唯一标识
    public int pcid; 
    public String pname = ""; 
    public int renown; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3047803, ccid);
        r.put(3435086, pcid);
        r.put(106808059, pname);
        r.put(-934580981, renown);
        return r;
    }


    public static NClanRequest parse(NewMap map2) {
        if(map2 == null) return null;

        NClanRequest r = new NClanRequest();
        r.ccid = map2.getString(3047803);
        r.pcid = map2.getInt(3435086);
        r.pname = map2.getString(106808059);
        r.renown = map2.getInt(-934580981);
        return r;
    }

    public String toString() {
        return "NClanRequest[ccid=" + ccid + ", pcid=" + pcid + ", pname=" + pname + ", renown=" + renown + "]";
    }

}
