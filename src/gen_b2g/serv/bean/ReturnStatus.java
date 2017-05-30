package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReturnStatus {
    public static final int _CID = 991275362;

    public int succ; 
    public long dt; 
    public String msg = ""; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3541570, succ);
        r.put(3216, dt);
        r.put(108417, msg);
        return r;
    }


    public static ReturnStatus parse(NewMap map2) {
        if(map2 == null) return null;

        ReturnStatus r = new ReturnStatus();
        r.succ = map2.getInt(3541570);
        r.dt = map2.getLong(3216);
        r.msg = map2.getString(108417);
        return r;
    }

    public String toString() {
        return "ReturnStatus[succ=" + succ + ", dt=" + dt + ", msg=" + msg + "]";
    }

}
