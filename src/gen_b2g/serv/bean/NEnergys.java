package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NEnergys {
    public static final int _CID = -1440521539;

    public List<NEnergy> list = new NewList();; 

    public List<Map> list_maps() {
        List<Map> r = new NewList<Map>();
        if(list == null) return r;
        for(NEnergy _e : list) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NEnergy> maps_list(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NEnergy e = NEnergy.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3322014, list_maps());
        return r;
    }


    public static NEnergys parse(NewMap map2) {
        if(map2 == null) return null;

        NEnergys r = new NEnergys();
        r.list = maps_list( map2.getList(3322014) );
        return r;
    }

    public String toString() {
        return "NEnergys[list=" + list + "]";
    }

}
