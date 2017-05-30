package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NRankClans {
    public static final int _CID = -1106985117;

    public List<NRankClan> list = new NewList();; 

    public List<Map> list_maps() {
        List<Map> r = new NewList<Map>();
        if(list == null) return r;
        for(NRankClan _e : list) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NRankClan> maps_list(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NRankClan e = NRankClan.parse(_e);
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


    public static NRankClans parse(NewMap map2) {
        if(map2 == null) return null;

        NRankClans r = new NRankClans();
        r.list = maps_list( map2.getList(3322014) );
        return r;
    }

    public String toString() {
        return "NRankClans[list=" + list + "]";
    }

}
