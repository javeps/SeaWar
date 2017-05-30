package gen_b2g.serv.bean;

import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NHero {
    public static final int _CID = 74280296;

    public int hcid; 
    public int hgid; 
    public int hp; 
    public int maxHp; 
    public int damage; 
    public int maxDamage; 
    public int speed; 
    public int maxSpeed; 
    public int status; 
    public long deadTime; 
    public int skillGid; 
    public int fightPos; // 战斗开始的位置


    public Map toMap() {
        Map r = new NewMap();
        r.put(-1, _CID);
        r.put(3196758, hcid);
        r.put(3200602, hgid);
        r.put(3336, hp);
        r.put(103671180, maxHp);
        r.put(-1339126929, damage);
        r.put(-1024163341, maxDamage);
        r.put(109641799, speed);
        r.put(396896579, maxSpeed);
        r.put(-892481550, status);
        r.put(502919505, deadTime);
        r.put(2142429105, skillGid);
        r.put(-874981340, fightPos);
        return r;
    }


    public static NHero parse(NewMap map2) {
        if(map2 == null) return null;

        NHero r = new NHero();
        r.hcid = map2.getInt(3196758);
        r.hgid = map2.getInt(3200602);
        r.hp = map2.getInt(3336);
        r.maxHp = map2.getInt(103671180);
        r.damage = map2.getInt(-1339126929);
        r.maxDamage = map2.getInt(-1024163341);
        r.speed = map2.getInt(109641799);
        r.maxSpeed = map2.getInt(396896579);
        r.status = map2.getInt(-892481550);
        r.deadTime = map2.getLong(502919505);
        r.skillGid = map2.getInt(2142429105);
        r.fightPos = map2.getInt(-874981340);
        return r;
    }

    public String toString() {
        return "NHero[hcid=" + hcid + ", hgid=" + hgid + ", hp=" + hp + ", maxHp=" + maxHp + ", damage=" + damage + ", maxDamage=" + maxDamage + ", speed=" + speed + ", maxSpeed=" + maxSpeed + ", status=" + status + ", deadTime=" + deadTime + ", skillGid=" + skillGid + ", fightPos=" + fightPos + "]";
    }

}
