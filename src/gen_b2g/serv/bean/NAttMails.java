package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NAttMails {
    public static final int _CID = 1337326057;

    public List<NAttMail> attMails = new NewList();; 

    public List<Map> attMails_maps() {
        List<Map> r = new NewList<Map>();
        if(attMails == null) return r;
        for(NAttMail _e : attMails) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NAttMail> maps_attMails(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NAttMail e = NAttMail.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(520209275, attMails_maps());
        return r;
    }


    public static NAttMails parse(NewMap map2) {
        if(map2 == null) return null;

        NAttMails r = new NAttMails();
        r.attMails = maps_attMails( map2.getList(520209275) );
        return r;
    }

    public String toString() {
        return "NAttMails[attMails=" + attMails + "]";
    }

}
