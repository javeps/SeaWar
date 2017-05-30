package gen_b2g.test.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NString {
    public static final int _CID = -1302308353;

    public String strV = ""; // 内容


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3541061, strV);
        return r;
    }


    public static NString parse(NewMap map2) {
        if(map2 == null) return null;

        NString r = new NString();
        r.strV = map2.getString(3541061);
        return r;
    }

    public String toString() {
        return "NString[strV=" + strV + "]";
    }

}
