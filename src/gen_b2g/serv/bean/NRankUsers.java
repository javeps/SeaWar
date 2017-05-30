package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NRankUsers {
    public static final int _CID = -1090149234;

    public List<NRankUser> lists = new NewList();; 

    public List<Map> lists_maps() {
        List<Map> r = new NewList<Map>();
        if(lists == null) return r;
        for(NRankUser _e : lists) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NRankUser> maps_lists(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NRankUser e = NRankUser.parse(_e);
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


    public static NRankUsers parse(NewMap map2) {
        if(map2 == null) return null;

        NRankUsers r = new NRankUsers();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

    public String toString() {
        return "NRankUsers[lists=" + lists + "]";
    }

}
