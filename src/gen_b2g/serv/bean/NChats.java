package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NChats {
    public static final int _CID = -1996822419;

    public List<NChat> lists = new NewList();; 

    public List<Map> lists_maps() {
        List<Map> r = new NewList<Map>();
        if(lists == null) return r;
        for(NChat _e : lists) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NChat> maps_lists(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NChat e = NChat.parse(_e);
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


    public static NChats parse(NewMap map2) {
        if(map2 == null) return null;

        NChats r = new NChats();
        r.lists = maps_lists( map2.getList(102982549) );
        return r;
    }

    public String toString() {
        return "NChats[lists=" + lists + "]";
    }

}
