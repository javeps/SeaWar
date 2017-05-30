package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NArmys {
    public static final int _CID = -1998359864;

    public List<NArmy> armys = new NewList();; 

    public List<Map> armys_maps() {
        List<Map> r = new NewList<Map>();
        if(armys == null) return r;
        for(NArmy _e : armys) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NArmy> maps_armys(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NArmy e = NArmy.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(93086326, armys_maps());
        return r;
    }


    public static NArmys parse(NewMap map2) {
        if(map2 == null) return null;

        NArmys r = new NArmys();
        r.armys = maps_armys( map2.getList(93086326) );
        return r;
    }

    public String toString() {
        return "NArmys[armys=" + armys + "]";
    }

}
