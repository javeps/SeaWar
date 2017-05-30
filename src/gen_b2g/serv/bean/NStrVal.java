package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NStrVal {
    public static final int _CID = -1302327010;

    public String val = ""; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(116513, val);
        return r;
    }


    public static NStrVal parse(NewMap map2) {
        if(map2 == null) return null;

        NStrVal r = new NStrVal();
        r.val = map2.getString(116513);
        return r;
    }

    public String toString() {
        return "NStrVal[val=" + val + "]";
    }

}
