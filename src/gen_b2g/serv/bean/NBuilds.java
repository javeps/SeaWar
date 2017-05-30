package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NBuilds {
    public static final int _CID = -1788345933;

    public List<NBuild> builds = new NewList();; 

    public List<Map> builds_maps() {
        List<Map> r = new NewList<Map>();
        if(builds == null) return r;
        for(NBuild _e : builds) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NBuild> maps_builds(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NBuild e = NBuild.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(-1378023483, builds_maps());
        return r;
    }


    public static NBuilds parse(NewMap map2) {
        if(map2 == null) return null;

        NBuilds r = new NBuilds();
        r.builds = maps_builds( map2.getList(-1378023483) );
        return r;
    }

    public String toString() {
        return "NBuilds[builds=" + builds + "]";
    }

}
