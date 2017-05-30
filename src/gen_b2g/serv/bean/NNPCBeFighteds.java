package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NNPCBeFighteds {
    public static final int _CID = 645947162;

    public List<NNPCBeFighted> lists = new NewList();; 

    public List<Map> lists_maps() {
        List<Map> r = new NewList<Map>();
        if(lists == null) return r;
        for(NNPCBeFighted _e : lists) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NNPCBeFighted> maps_lists(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NNPCBeFighted e = NNPCBeFighted.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(102982549, lists_maps());
        return r;
    }


    public static NNPCBeFighteds parse(NewMap map2) {
        if(map2 == null) return null;

        NNPCBeFighteds r = new NNPCBeFighteds();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

    public String toString() {
        return "NNPCBeFighteds[lists=" + lists + "]";
    }

}
