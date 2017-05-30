package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NLong {
    public static final int _CID = 74408938;

    public long val; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(116513, val);
        return r;
    }


    public static NLong parse(NewMap map2) {
        if(map2 == null) return null;

        NLong r = new NLong();
        r.val = map2.getLong(116513);
        return r;
    }

    public String toString() {
        return "NLong[val=" + val + "]";
    }

}
