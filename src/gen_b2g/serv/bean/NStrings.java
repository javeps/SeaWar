package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NStrings {
    public static final int _CID = -1716853164;

    public List<String> val = new NewList();; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(116513, val);
        return r;
    }


    public static NStrings parse(NewMap map2) {
        if(map2 == null) return null;

        NStrings r = new NStrings();
        r.val = map2.getList(116513);
        return r;
    }

    public String toString() {
        return "NStrings[val=" + val + "]";
    }

}
