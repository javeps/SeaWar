package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NInt {
    public static final int _CID = 2397377;

    public int val; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(116513, val);
        return r;
    }


    public static NInt parse(NewMap map2) {
        if(map2 == null) return null;

        NInt r = new NInt();
        r.val = map2.getInt(116513);
        return r;
    }

    public String toString() {
        return "NInt[val=" + val + "]";
    }

}
