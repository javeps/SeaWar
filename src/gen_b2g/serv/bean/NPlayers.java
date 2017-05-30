package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NPlayers {
    public static final int _CID = -328661628;

    public List<NPlayer> uplayers = new NewList();; 

    public List<Map> uplayers_maps() {
        List<Map> r = new NewList<Map>();
        if(uplayers == null) return r;
        for(NPlayer _e : uplayers) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NPlayer> maps_uplayers(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NPlayer e = NPlayer.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(1551778717, uplayers_maps());
        return r;
    }


    public static NPlayers parse(NewMap map2) {
        if(map2 == null) return null;

        NPlayers r = new NPlayers();
        r.uplayers = maps_uplayers( map2.getList(1551778717) );
        return r;
    }

    public String toString() {
        return "NPlayers[uplayers=" + uplayers + "]";
    }

}
