package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NInts {
    public static final int _CID = 74318802;

    public List<NInt> intes = new NewList();; 

    public List<Map> intes_maps() {
        List<Map> r = new NewList<Map>();
        if(intes == null) return r;
        for(NInt _e : intes) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NInt> maps_intes(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NInt e = NInt.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(100361437, intes_maps());
        return r;
    }


    public static NInts parse(NewMap map2) {
        if(map2 == null) return null;

        NInts r = new NInts();
        r.intes = maps_intes( map2.getList(100361437) );
        return r;
    }

    public String toString() {
        return "NInts[intes=" + intes + "]";
    }

}
