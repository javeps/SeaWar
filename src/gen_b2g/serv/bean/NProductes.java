package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NProductes {
    public static final int _CID = -1491013329;

    public List<NProducte> army_productings = new NewList();; 

    public List<Map> army_productings_maps() {
        List<Map> r = new NewList<Map>();
        if(army_productings == null) return r;
        for(NProducte _e : army_productings) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NProducte> maps_army_productings(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NProducte e = NProducte.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(-446378338, army_productings_maps());
        return r;
    }


    public static NProductes parse(NewMap map2) {
        if(map2 == null) return null;

        NProductes r = new NProductes();
        r.army_productings = maps_army_productings( map2.getList(-446378338) );
        return r;
    }

    public String toString() {
        return "NProductes[army_productings=" + army_productings + "]";
    }

}
