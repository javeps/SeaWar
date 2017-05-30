package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NTeches {
    public static final int _CID = -1287980110;

    public List<NTech> techs = new NewList();; 

    public List<Map> techs_maps() {
        List<Map> r = new NewList<Map>();
        if(techs == null) return r;
        for(NTech _e : techs) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NTech> maps_techs(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NTech e = NTech.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(110235805, techs_maps());
        return r;
    }


    public static NTeches parse(NewMap map2) {
        if(map2 == null) return null;

        NTeches r = new NTeches();
        r.techs = maps_techs( map2.getList(110235805) );
        return r;
    }

    public String toString() {
        return "NTeches[techs=" + techs + "]";
    }

}
