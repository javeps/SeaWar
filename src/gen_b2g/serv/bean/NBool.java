package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NBool {
    public static final int _CID = 74111064;

    public boolean val; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(116513, val);
        return r;
    }


    public static NBool parse(NewMap map2) {
        if(map2 == null) return null;

        NBool r = new NBool();
        r.val = map2.getBoolean(116513);
        return r;
    }

    public String toString() {
        return "NBool[val=" + val + "]";
    }

}
