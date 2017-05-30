package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NRewards {
    public static final int _CID = 1265556214;

    public List<NReward> lists = new NewList();; 

    public List<Map> lists_maps() {
        List<Map> r = new NewList<Map>();
        if(lists == null) return r;
        for(NReward _e : lists) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NReward> maps_lists(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NReward e = NReward.parse(_e);
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


    public static NRewards parse(NewMap map2) {
        if(map2 == null) return null;

        NRewards r = new NRewards();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

    public String toString() {
        return "NRewards[lists=" + lists + "]";
    }

}
