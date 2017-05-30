package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NBuildBlast {
    public static final int _CID = -1227226344;

    public int beAttPid; 
    public List<Integer> bcids = new NewList();; 
    public List<Integer> bgids = new NewList();; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(411838797, beAttPid);
        r.put(93558487, bcids);
        r.put(93677651, bgids);
        return r;
    }


    public static NBuildBlast parse(NewMap map2) {
        if(map2 == null) return null;

        NBuildBlast r = new NBuildBlast();
        r.beAttPid = map2.getInt(411838797);
        r.bcids = map2.getList(93558487);
        r.bgids = map2.getList(93677651);
        return r;
    }

    public String toString() {
        return "NBuildBlast[beAttPid=" + beAttPid + ", bcids=" + bcids + ", bgids=" + bgids + "]";
    }

}
