package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NDouble {
    public static final int _CID = -1736280641;

    public double val; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(116513, val);
        return r;
    }


    public static NDouble parse(NewMap map2) {
        if(map2 == null) return null;

        NDouble r = new NDouble();
        r.val = map2.getDouble(116513);
        return r;
    }

    public String toString() {
        return "NDouble[val=" + val + "]";
    }

}
