package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NBuild {
    public static final int _CID = -1997351232;

    public int client_id; 
    public String bname = ""; 
    public int gid; 
    public int lvl; 
    public long cooldown; 
    public int pos_index; 
    public int state; 
    public int type; 
    public long cur_pro_res; 
    public long pro_res_beg_time; 
    public int cur_up_tech_gid; 
    public long end_tech_up_time; 
    public long begin_army_time; 
    public long end_army_time; 


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(-1904089585, client_id);
        r.put(93878765, bname);
        r.put(102338, gid);
        r.put(107554, lvl);
        r.put(-546109589, cooldown);
        r.put(1410805543, pos_index);
        r.put(109757585, state);
        r.put(3575610, type);
        r.put(-1164548849, cur_pro_res);
        r.put(1799759545, pro_res_beg_time);
        r.put(-1656969154, cur_up_tech_gid);
        r.put(-983812564, end_tech_up_time);
        r.put(213919769, begin_army_time);
        r.put(46408139, end_army_time);
        return r;
    }


    public static NBuild parse(NewMap map2) {
        if(map2 == null) return null;

        NBuild r = new NBuild();
        r.client_id = map2.getInt(-1904089585);
        r.bname = map2.getString(93878765);
        r.gid = map2.getInt(102338);
        r.lvl = map2.getInt(107554);
        r.cooldown = map2.getLong(-546109589);
        r.pos_index = map2.getInt(1410805543);
        r.state = map2.getInt(109757585);
        r.type = map2.getInt(3575610);
        r.cur_pro_res = map2.getLong(-1164548849);
        r.pro_res_beg_time = map2.getLong(1799759545);
        r.cur_up_tech_gid = map2.getInt(-1656969154);
        r.end_tech_up_time = map2.getLong(-983812564);
        r.begin_army_time = map2.getLong(213919769);
        r.end_army_time = map2.getLong(46408139);
        return r;
    }

    public String toString() {
        return "NBuild[client_id=" + client_id + ", bname=" + bname + ", gid=" + gid + ", lvl=" + lvl + ", cooldown=" + cooldown + ", pos_index=" + pos_index + ", state=" + state + ", type=" + type + ", cur_pro_res=" + cur_pro_res + ", pro_res_beg_time=" + pro_res_beg_time + ", cur_up_tech_gid=" + cur_up_tech_gid + ", end_tech_up_time=" + end_tech_up_time + ", begin_army_time=" + begin_army_time + ", end_army_time=" + end_army_time + "]";
    }

}
