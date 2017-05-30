package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NAttackInfos {
    public static final int _CID = -1834677521;

    public List<NAttackInfo> infos = new NewList();; 

    public List<Map> infos_maps() {
        List<Map> r = new NewList<Map>();
        if(infos == null) return r;
        for(NAttackInfo _e : infos) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NAttackInfo> maps_infos(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NAttackInfo e = NAttackInfo.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(100348293, infos_maps());
        return r;
    }


    public static NAttackInfos parse(NewMap map2) {
        if(map2 == null) return null;

        NAttackInfos r = new NAttackInfos();
        r.infos = maps_infos( map2.getList(100348293) );
        return r;
    }

    public String toString() {
        return "NAttackInfos[infos=" + infos + "]";
    }

}
