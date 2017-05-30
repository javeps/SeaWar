package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - player_hero
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Player_hero extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 783327006; // com.sea.db.bean.Player_hero

    public static String TABLENAME = "player_hero";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "hcid", "pcid", "pname", "hname", "hgid", "addDamage", "maxDamage", "addHP", "maxHP", "addAttSpeed", "maxAttSpeed", "statues", "createTime", "deadTime", "skillGid", "fpos"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "VARCHAR", "VARCHAR", "INT", "INT", "INT", "INT", "INT", "INT", "INT", "INT", "BIGINT", "BIGINT", "INT", "INT"};


    public static BeanEvent _event;
    public static void setEvent(BeanEvent evt){
        _event = evt;
    }
    public void doEvent(String key, Object oldValue, Object newValue){
        if(_event == null) return;
        if(id <= 0) return;
        _event.doEvent(this, key, oldValue, newValue);
    }

    public int id;
    public int hcid;
    public int pcid;
    public String pname;
    public String hname;
    public int hgid;
    public int addDamage;
    public int maxDamage;
    public int addHP;
    public int maxHP;
    public int addAttSpeed;
    public int maxAttSpeed;
    public int statues;
    public long createTime;
    public long deadTime;
    public int skillGid;
    public int fpos;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Player_hero setId(int id){
        this.id = id;
        return this;
    }

    public int getHcid(){
        return hcid;
    }

    public Player_hero setHcid(int hcid){
        int _old = hcid;
        this.hcid = hcid;
        changeIt("hcid", _old, hcid);
        return this;
    }

    public Player_hero changeHcid(int hcid){
        return setHcid(this.hcid + hcid);
    }

    public Player_hero changeHcidWithMin(int hcid, int _min){
        int _v2 = this.hcid + hcid;
        return setHcid(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeHcidWithMax(int hcid, int _max){
        int _v2 = this.hcid + hcid;
        return setHcid(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeHcidWithMinMax(int hcid, int _min, int _max){
        int _v2 = this.hcid + hcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setHcid(_v2 < _min  ? _min : _v2);
    }

    public int getPcid(){
        return pcid;
    }

    public Player_hero setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Player_hero changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Player_hero changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPname(){
        return pname;
    }

    public Player_hero setPname(String pname){
        String _old = pname;
        this.pname = pname;
        changeIt("pname", _old, pname);
        return this;
    }

    public String getHname(){
        return hname;
    }

    public Player_hero setHname(String hname){
        String _old = hname;
        this.hname = hname;
        changeIt("hname", _old, hname);
        return this;
    }

    public int getHgid(){
        return hgid;
    }

    public Player_hero setHgid(int hgid){
        int _old = hgid;
        this.hgid = hgid;
        changeIt("hgid", _old, hgid);
        return this;
    }

    public Player_hero changeHgid(int hgid){
        return setHgid(this.hgid + hgid);
    }

    public Player_hero changeHgidWithMin(int hgid, int _min){
        int _v2 = this.hgid + hgid;
        return setHgid(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeHgidWithMax(int hgid, int _max){
        int _v2 = this.hgid + hgid;
        return setHgid(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeHgidWithMinMax(int hgid, int _min, int _max){
        int _v2 = this.hgid + hgid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setHgid(_v2 < _min  ? _min : _v2);
    }

    public int getAdddamage(){
        return addDamage;
    }

    public Player_hero setAdddamage(int addDamage){
        int _old = addDamage;
        this.addDamage = addDamage;
        changeIt("addDamage", _old, addDamage);
        return this;
    }

    public Player_hero changeAdddamage(int addDamage){
        return setAdddamage(this.addDamage + addDamage);
    }

    public Player_hero changeAdddamageWithMin(int addDamage, int _min){
        int _v2 = this.addDamage + addDamage;
        return setAdddamage(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeAdddamageWithMax(int addDamage, int _max){
        int _v2 = this.addDamage + addDamage;
        return setAdddamage(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeAdddamageWithMinMax(int addDamage, int _min, int _max){
        int _v2 = this.addDamage + addDamage;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAdddamage(_v2 < _min  ? _min : _v2);
    }

    public int getMaxdamage(){
        return maxDamage;
    }

    public Player_hero setMaxdamage(int maxDamage){
        int _old = maxDamage;
        this.maxDamage = maxDamage;
        changeIt("maxDamage", _old, maxDamage);
        return this;
    }

    public Player_hero changeMaxdamage(int maxDamage){
        return setMaxdamage(this.maxDamage + maxDamage);
    }

    public Player_hero changeMaxdamageWithMin(int maxDamage, int _min){
        int _v2 = this.maxDamage + maxDamage;
        return setMaxdamage(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeMaxdamageWithMax(int maxDamage, int _max){
        int _v2 = this.maxDamage + maxDamage;
        return setMaxdamage(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeMaxdamageWithMinMax(int maxDamage, int _min, int _max){
        int _v2 = this.maxDamage + maxDamage;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMaxdamage(_v2 < _min  ? _min : _v2);
    }

    public int getAddhp(){
        return addHP;
    }

    public Player_hero setAddhp(int addHP){
        int _old = addHP;
        this.addHP = addHP;
        changeIt("addHP", _old, addHP);
        return this;
    }

    public Player_hero changeAddhp(int addHP){
        return setAddhp(this.addHP + addHP);
    }

    public Player_hero changeAddhpWithMin(int addHP, int _min){
        int _v2 = this.addHP + addHP;
        return setAddhp(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeAddhpWithMax(int addHP, int _max){
        int _v2 = this.addHP + addHP;
        return setAddhp(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeAddhpWithMinMax(int addHP, int _min, int _max){
        int _v2 = this.addHP + addHP;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAddhp(_v2 < _min  ? _min : _v2);
    }

    public int getMaxhp(){
        return maxHP;
    }

    public Player_hero setMaxhp(int maxHP){
        int _old = maxHP;
        this.maxHP = maxHP;
        changeIt("maxHP", _old, maxHP);
        return this;
    }

    public Player_hero changeMaxhp(int maxHP){
        return setMaxhp(this.maxHP + maxHP);
    }

    public Player_hero changeMaxhpWithMin(int maxHP, int _min){
        int _v2 = this.maxHP + maxHP;
        return setMaxhp(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeMaxhpWithMax(int maxHP, int _max){
        int _v2 = this.maxHP + maxHP;
        return setMaxhp(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeMaxhpWithMinMax(int maxHP, int _min, int _max){
        int _v2 = this.maxHP + maxHP;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMaxhp(_v2 < _min  ? _min : _v2);
    }

    public int getAddattspeed(){
        return addAttSpeed;
    }

    public Player_hero setAddattspeed(int addAttSpeed){
        int _old = addAttSpeed;
        this.addAttSpeed = addAttSpeed;
        changeIt("addAttSpeed", _old, addAttSpeed);
        return this;
    }

    public Player_hero changeAddattspeed(int addAttSpeed){
        return setAddattspeed(this.addAttSpeed + addAttSpeed);
    }

    public Player_hero changeAddattspeedWithMin(int addAttSpeed, int _min){
        int _v2 = this.addAttSpeed + addAttSpeed;
        return setAddattspeed(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeAddattspeedWithMax(int addAttSpeed, int _max){
        int _v2 = this.addAttSpeed + addAttSpeed;
        return setAddattspeed(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeAddattspeedWithMinMax(int addAttSpeed, int _min, int _max){
        int _v2 = this.addAttSpeed + addAttSpeed;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAddattspeed(_v2 < _min  ? _min : _v2);
    }

    public int getMaxattspeed(){
        return maxAttSpeed;
    }

    public Player_hero setMaxattspeed(int maxAttSpeed){
        int _old = maxAttSpeed;
        this.maxAttSpeed = maxAttSpeed;
        changeIt("maxAttSpeed", _old, maxAttSpeed);
        return this;
    }

    public Player_hero changeMaxattspeed(int maxAttSpeed){
        return setMaxattspeed(this.maxAttSpeed + maxAttSpeed);
    }

    public Player_hero changeMaxattspeedWithMin(int maxAttSpeed, int _min){
        int _v2 = this.maxAttSpeed + maxAttSpeed;
        return setMaxattspeed(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeMaxattspeedWithMax(int maxAttSpeed, int _max){
        int _v2 = this.maxAttSpeed + maxAttSpeed;
        return setMaxattspeed(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeMaxattspeedWithMinMax(int maxAttSpeed, int _min, int _max){
        int _v2 = this.maxAttSpeed + maxAttSpeed;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMaxattspeed(_v2 < _min  ? _min : _v2);
    }

    public int getStatues(){
        return statues;
    }

    public Player_hero setStatues(int statues){
        int _old = statues;
        this.statues = statues;
        changeIt("statues", _old, statues);
        return this;
    }

    public Player_hero changeStatues(int statues){
        return setStatues(this.statues + statues);
    }

    public Player_hero changeStatuesWithMin(int statues, int _min){
        int _v2 = this.statues + statues;
        return setStatues(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeStatuesWithMax(int statues, int _max){
        int _v2 = this.statues + statues;
        return setStatues(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeStatuesWithMinMax(int statues, int _min, int _max){
        int _v2 = this.statues + statues;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatues(_v2 < _min  ? _min : _v2);
    }

    public long getCreatetime(){
        return createTime;
    }

    public Player_hero setCreatetime(long createTime){
        long _old = createTime;
        this.createTime = createTime;
        changeIt("createTime", _old, createTime);
        return this;
    }

    public Player_hero changeCreatetime(long createTime){
        return setCreatetime(this.createTime + createTime);
    }

    public Player_hero changeCreatetimeWithMin(long createTime, long _min){
        long _v2 = this.createTime + createTime;
        return setCreatetime(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeCreatetimeWithMax(long createTime, long _max){
        long _v2 = this.createTime + createTime;
        return setCreatetime(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeCreatetimeWithMinMax(long createTime, long _min, long _max){
        long _v2 = this.createTime + createTime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCreatetime(_v2 < _min  ? _min : _v2);
    }

    public long getDeadtime(){
        return deadTime;
    }

    public Player_hero setDeadtime(long deadTime){
        long _old = deadTime;
        this.deadTime = deadTime;
        changeIt("deadTime", _old, deadTime);
        return this;
    }

    public Player_hero changeDeadtime(long deadTime){
        return setDeadtime(this.deadTime + deadTime);
    }

    public Player_hero changeDeadtimeWithMin(long deadTime, long _min){
        long _v2 = this.deadTime + deadTime;
        return setDeadtime(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeDeadtimeWithMax(long deadTime, long _max){
        long _v2 = this.deadTime + deadTime;
        return setDeadtime(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeDeadtimeWithMinMax(long deadTime, long _min, long _max){
        long _v2 = this.deadTime + deadTime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDeadtime(_v2 < _min  ? _min : _v2);
    }

    public int getSkillgid(){
        return skillGid;
    }

    public Player_hero setSkillgid(int skillGid){
        int _old = skillGid;
        this.skillGid = skillGid;
        changeIt("skillGid", _old, skillGid);
        return this;
    }

    public Player_hero changeSkillgid(int skillGid){
        return setSkillgid(this.skillGid + skillGid);
    }

    public Player_hero changeSkillgidWithMin(int skillGid, int _min){
        int _v2 = this.skillGid + skillGid;
        return setSkillgid(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeSkillgidWithMax(int skillGid, int _max){
        int _v2 = this.skillGid + skillGid;
        return setSkillgid(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeSkillgidWithMinMax(int skillGid, int _min, int _max){
        int _v2 = this.skillGid + skillGid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setSkillgid(_v2 < _min  ? _min : _v2);
    }

    public int getFpos(){
        return fpos;
    }

    public Player_hero setFpos(int fpos){
        int _old = fpos;
        this.fpos = fpos;
        changeIt("fpos", _old, fpos);
        return this;
    }

    public Player_hero changeFpos(int fpos){
        return setFpos(this.fpos + fpos);
    }

    public Player_hero changeFposWithMin(int fpos, int _min){
        int _v2 = this.fpos + fpos;
        return setFpos(_v2 < _min  ? _min : _v2);
    }

    public Player_hero changeFposWithMax(int fpos, int _max){
        int _v2 = this.fpos + fpos;
        return setFpos(_v2 > _max  ? _max : _v2);
    }

    public Player_hero changeFposWithMinMax(int fpos, int _min, int _max){
        int _v2 = this.fpos + fpos;
        _v2 = _v2 > _max  ? _max : _v2;
        return setFpos(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Player_hero v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Player_hero v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Player_hero newPlayer_hero(Integer id, Integer hcid, Integer pcid, String pname, String hname, Integer hgid, Integer addDamage, Integer maxDamage, Integer addHP, Integer maxHP, Integer addAttSpeed, Integer maxAttSpeed, Integer statues, Long createTime, Long deadTime, Integer skillGid, Integer fpos) {
        Player_hero result = new Player_hero();
        result.id = id;
        result.hcid = hcid;
        result.pcid = pcid;
        result.pname = pname;
        result.hname = hname;
        result.hgid = hgid;
        result.addDamage = addDamage;
        result.maxDamage = maxDamage;
        result.addHP = addHP;
        result.maxHP = maxHP;
        result.addAttSpeed = addAttSpeed;
        result.maxAttSpeed = maxAttSpeed;
        result.statues = statues;
        result.createTime = createTime;
        result.deadTime = deadTime;
        result.skillGid = skillGid;
        result.fpos = fpos;
        return result;
    }

    public static Player_hero newPlayer_hero(Player_hero player_hero) {
        Player_hero result = new Player_hero();
        result.id = player_hero.id;
        result.hcid = player_hero.hcid;
        result.pcid = player_hero.pcid;
        result.pname = player_hero.pname;
        result.hname = player_hero.hname;
        result.hgid = player_hero.hgid;
        result.addDamage = player_hero.addDamage;
        result.maxDamage = player_hero.maxDamage;
        result.addHP = player_hero.addHP;
        result.maxHP = player_hero.maxHP;
        result.addAttSpeed = player_hero.addAttSpeed;
        result.maxAttSpeed = player_hero.maxAttSpeed;
        result.statues = player_hero.statues;
        result.createTime = player_hero.createTime;
        result.deadTime = player_hero.deadTime;
        result.skillGid = player_hero.skillGid;
        result.fpos = player_hero.fpos;
        return result;
    }

    public Player_hero createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, hcid);
        B2OutputStream.writeObject(_out, pcid);
        B2OutputStream.writeObject(_out, pname);
        B2OutputStream.writeObject(_out, hname);
        B2OutputStream.writeObject(_out, hgid);
        B2OutputStream.writeObject(_out, addDamage);
        B2OutputStream.writeObject(_out, maxDamage);
        B2OutputStream.writeObject(_out, addHP);
        B2OutputStream.writeObject(_out, maxHP);
        B2OutputStream.writeObject(_out, addAttSpeed);
        B2OutputStream.writeObject(_out, maxAttSpeed);
        B2OutputStream.writeObject(_out, statues);
        B2OutputStream.writeObject(_out, createTime);
        B2OutputStream.writeObject(_out, deadTime);
        B2OutputStream.writeObject(_out, skillGid);
        B2OutputStream.writeObject(_out, fpos);
    }

    public static final Player_hero readObject(InputStream _in) throws Exception {
        Player_hero result = new Player_hero();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.hcid = (Integer) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        result.pname = (String) B2InputStream.readObject(_in);
        result.hname = (String) B2InputStream.readObject(_in);
        result.hgid = (Integer) B2InputStream.readObject(_in);
        result.addDamage = (Integer) B2InputStream.readObject(_in);
        result.maxDamage = (Integer) B2InputStream.readObject(_in);
        result.addHP = (Integer) B2InputStream.readObject(_in);
        result.maxHP = (Integer) B2InputStream.readObject(_in);
        result.addAttSpeed = (Integer) B2InputStream.readObject(_in);
        result.maxAttSpeed = (Integer) B2InputStream.readObject(_in);
        result.statues = (Integer) B2InputStream.readObject(_in);
        result.createTime = (Long) B2InputStream.readObject(_in);
        result.deadTime = (Long) B2InputStream.readObject(_in);
        result.skillGid = (Integer) B2InputStream.readObject(_in);
        result.fpos = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getPlayer_hero(){
        Player_hero player_hero = null; // player_hero
        { // new and insert Player_hero (player_hero)
            int id = 0; 	// id
            int hcid = 0; 	// hcid
            int pcid = 0; 	// pcid
            String pname = ""; 	// pname
            String hname = ""; 	// hname
            int hgid = 0; 	// hgid
            int addDamage = 0; 	// addDamage
            int maxDamage = 0; 	// maxDamage
            int addHP = 0; 	// addHP
            int maxHP = 0; 	// maxHP
            int addAttSpeed = 0; 	// addAttSpeed
            int maxAttSpeed = 0; 	// maxAttSpeed
            int statues = 0; 	// statues
            long createTime = 0; 	// createTime
            long deadTime = 0; 	// deadTime
            int skillGid = 0; 	// skillGid
            int fpos = 0; 	// fpos
            player_hero = Player_hero.newPlayer_hero(id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos);
        }
        player_hero = player_hero.insert();

        int id = player_hero.getId(); 	// id
        int hcid = player_hero.getHcid(); 	// hcid
        int pcid = player_hero.getPcid(); 	// pcid
        String pname = player_hero.getPname(); 	// pname
        String hname = player_hero.getHname(); 	// hname
        int hgid = player_hero.getHgid(); 	// hgid
        int addDamage = player_hero.getAdddamage(); 	// addDamage
        int maxDamage = player_hero.getMaxdamage(); 	// maxDamage
        int addHP = player_hero.getAddhp(); 	// addHP
        int maxHP = player_hero.getMaxhp(); 	// maxHP
        int addAttSpeed = player_hero.getAddattspeed(); 	// addAttSpeed
        int maxAttSpeed = player_hero.getMaxattspeed(); 	// maxAttSpeed
        int statues = player_hero.getStatues(); 	// statues
        long createTime = player_hero.getCreatetime(); 	// createTime
        long deadTime = player_hero.getDeadtime(); 	// deadTime
        int skillGid = player_hero.getSkillgid(); 	// skillGid
        int fpos = player_hero.getFpos(); 	// fpos
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case "id":
            return id;
        case "hcid":
            return hcid;
        case "pcid":
            return pcid;
        case "hgid":
            return hgid;
        case "addDamage":
            return addDamage;
        case "maxDamage":
            return maxDamage;
        case "addHP":
            return addHP;
        case "maxHP":
            return maxHP;
        case "addAttSpeed":
            return addAttSpeed;
        case "maxAttSpeed":
            return maxAttSpeed;
        case "statues":
            return statues;
        case "skillGid":
            return skillGid;
        case "fpos":
            return fpos;
        }
        return 0;
    }

    public Player_hero setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Player_hero setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "hcid":
            return setHcid(value2);
        case "pcid":
            return setPcid(value2);
        case "hgid":
            return setHgid(value2);
        case "addDamage":
            return setAdddamage(value2);
        case "maxDamage":
            return setMaxdamage(value2);
        case "addHP":
            return setAddhp(value2);
        case "maxHP":
            return setMaxhp(value2);
        case "addAttSpeed":
            return setAddattspeed(value2);
        case "maxAttSpeed":
            return setMaxattspeed(value2);
        case "statues":
            return setStatues(value2);
        case "skillGid":
            return setSkillgid(value2);
        case "fpos":
            return setFpos(value2);
        }
        return this;
    }

    public Player_hero changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Player_hero changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "hcid":
            return changeHcid(value2);
        case "pcid":
            return changePcid(value2);
        case "hgid":
            return changeHgid(value2);
        case "addDamage":
            return changeAdddamage(value2);
        case "maxDamage":
            return changeMaxdamage(value2);
        case "addHP":
            return changeAddhp(value2);
        case "maxHP":
            return changeMaxhp(value2);
        case "addAttSpeed":
            return changeAddattspeed(value2);
        case "maxAttSpeed":
            return changeMaxattspeed(value2);
        case "statues":
            return changeStatues(value2);
        case "skillGid":
            return changeSkillgid(value2);
        case "fpos":
            return changeFpos(value2);
        }
        return this;
    }

    public Player_hero changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Player_hero changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "hcid":
            return changeHcidWithMin(value2, _min);
        case "pcid":
            return changePcidWithMin(value2, _min);
        case "hgid":
            return changeHgidWithMin(value2, _min);
        case "addDamage":
            return changeAdddamageWithMin(value2, _min);
        case "maxDamage":
            return changeMaxdamageWithMin(value2, _min);
        case "addHP":
            return changeAddhpWithMin(value2, _min);
        case "maxHP":
            return changeMaxhpWithMin(value2, _min);
        case "addAttSpeed":
            return changeAddattspeedWithMin(value2, _min);
        case "maxAttSpeed":
            return changeMaxattspeedWithMin(value2, _min);
        case "statues":
            return changeStatuesWithMin(value2, _min);
        case "skillGid":
            return changeSkillgidWithMin(value2, _min);
        case "fpos":
            return changeFposWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        }
        return 0;
    }

    public Player_hero setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Player_hero setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        }
        return this;
    }

    public String valueZhStr(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueStr(fieldEn);
    }

    public String valueStr(String fieldEn){
        switch(fieldEn){
        case "pname": 
            return pname;
        case "hname": 
            return hname;
        }
        return "";
    }

    public <T> T vZh(String fieldZh){
        return (T) valueZh(fieldZh);
    }

    public Object valueZh(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return value(fieldEn);
    }

    public <T> T v(String fieldEn){
        return (T) value(fieldEn);
    }

    public Object value(String fieldEn){
        switch(fieldEn){
        case "id":
            return id;
        case "hcid":
            return hcid;
        case "pcid":
            return pcid;
        case "pname":
            return pname;
        case "hname":
            return hname;
        case "hgid":
            return hgid;
        case "addDamage":
            return addDamage;
        case "maxDamage":
            return maxDamage;
        case "addHP":
            return addHP;
        case "maxHP":
            return maxHP;
        case "addAttSpeed":
            return addAttSpeed;
        case "maxAttSpeed":
            return maxAttSpeed;
        case "statues":
            return statues;
        case "createTime":
            return createTime;
        case "deadTime":
            return deadTime;
        case "skillGid":
            return skillGid;
        case "fpos":
            return fpos;
        }
        return null;
    }

    public Player_hero setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Player_hero setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "pname":
            return setPname(value2);
        case "hname":
            return setHname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Player_hero setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Player_hero setJson(String fieldEn, Object o2) {
        try {
            String value2 = com.bowlong.json.JSON.toJSONString(o2);
            return setStr(fieldEn, value2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Map toMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Player_hero");
        result.put("id", id);
        result.put("hcid", hcid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("hname", hname);
        result.put("hgid", hgid);
        result.put("addDamage", addDamage);
        result.put("maxDamage", maxDamage);
        result.put("addHP", addHP);
        result.put("maxHP", maxHP);
        result.put("addAttSpeed", addAttSpeed);
        result.put("maxAttSpeed", maxAttSpeed);
        result.put("statues", statues);
        result.put("createTime", createTime);
        result.put("deadTime", deadTime);
        result.put("skillGid", skillGid);
        result.put("fpos", fpos);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("hcid", hcid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("hname", hname);
        result.put("hgid", hgid);
        result.put("addDamage", addDamage);
        result.put("maxDamage", maxDamage);
        result.put("addHP", addHP);
        result.put("maxHP", maxHP);
        result.put("addAttSpeed", addAttSpeed);
        result.put("maxAttSpeed", maxAttSpeed);
        result.put("statues", statues);
        result.put("createTime", createTime);
        result.put("deadTime", deadTime);
        result.put("skillGid", skillGid);
        result.put("fpos", fpos);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Player_hero");
        result.put("id", id);
        result.put("hcid", hcid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("hname", hname);
        result.put("hgid", hgid);
        result.put("addDamage", addDamage);
        result.put("maxDamage", maxDamage);
        result.put("addHP", addHP);
        result.put("maxHP", maxHP);
        result.put("addAttSpeed", addAttSpeed);
        result.put("maxAttSpeed", maxAttSpeed);
        result.put("statues", statues);
        result.put("createTime", createTime);
        result.put("deadTime", deadTime);
        result.put("skillGid", skillGid);
        result.put("fpos", fpos);
        return result;
    }

    public Player_hero mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer hcid = (Integer)e.get("hcid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        String hname = (String)e.get("hname");
        Integer hgid = (Integer)e.get("hgid");
        Integer addDamage = (Integer)e.get("addDamage");
        Integer maxDamage = (Integer)e.get("maxDamage");
        Integer addHP = (Integer)e.get("addHP");
        Integer maxHP = (Integer)e.get("maxHP");
        Integer addAttSpeed = (Integer)e.get("addAttSpeed");
        Integer maxAttSpeed = (Integer)e.get("maxAttSpeed");
        Integer statues = (Integer)e.get("statues");
        Long createTime = (Long)e.get("createTime");
        Long deadTime = (Long)e.get("deadTime");
        Integer skillGid = (Integer)e.get("skillGid");
        Integer fpos = (Integer)e.get("fpos");

        if(id == null) id = 0;
        if(hcid == null) hcid = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(hname == null) hname = "";
        if(hgid == null) hgid = 0;
        if(addDamage == null) addDamage = 0;
        if(maxDamage == null) maxDamage = 0;
        if(addHP == null) addHP = 0;
        if(maxHP == null) maxHP = 0;
        if(addAttSpeed == null) addAttSpeed = 0;
        if(maxAttSpeed == null) maxAttSpeed = 0;
        if(statues == null) statues = 0;
        if(createTime == null) createTime = 0L;
        if(deadTime == null) deadTime = 0L;
        if(skillGid == null) skillGid = 0;
        if(fpos == null) fpos = 0;

        setId(id);
        setHcid(hcid);
        setPcid(pcid);
        setPname(pname);
        setHname(hname);
        setHgid(hgid);
        setAdddamage(addDamage);
        setMaxdamage(maxDamage);
        setAddhp(addHP);
        setMaxhp(maxHP);
        setAddattspeed(addAttSpeed);
        setMaxattspeed(maxAttSpeed);
        setStatues(statues);
        setCreatetime(createTime);
        setDeadtime(deadTime);
        setSkillgid(skillGid);
        setFpos(fpos);

        return this;
    }

    public static final Player_hero mapTo(Map e){
        Player_hero result = new Player_hero();

        Integer id = (Integer)e.get("id");
        Integer hcid = (Integer)e.get("hcid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        String hname = (String)e.get("hname");
        Integer hgid = (Integer)e.get("hgid");
        Integer addDamage = (Integer)e.get("addDamage");
        Integer maxDamage = (Integer)e.get("maxDamage");
        Integer addHP = (Integer)e.get("addHP");
        Integer maxHP = (Integer)e.get("maxHP");
        Integer addAttSpeed = (Integer)e.get("addAttSpeed");
        Integer maxAttSpeed = (Integer)e.get("maxAttSpeed");
        Integer statues = (Integer)e.get("statues");
        Long createTime = (Long)e.get("createTime");
        Long deadTime = (Long)e.get("deadTime");
        Integer skillGid = (Integer)e.get("skillGid");
        Integer fpos = (Integer)e.get("fpos");

        if(id == null) id = 0;
        if(hcid == null) hcid = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(hname == null) hname = "";
        if(hgid == null) hgid = 0;
        if(addDamage == null) addDamage = 0;
        if(maxDamage == null) maxDamage = 0;
        if(addHP == null) addHP = 0;
        if(maxHP == null) maxHP = 0;
        if(addAttSpeed == null) addAttSpeed = 0;
        if(maxAttSpeed == null) maxAttSpeed = 0;
        if(statues == null) statues = 0;
        if(createTime == null) createTime = 0L;
        if(deadTime == null) deadTime = 0L;
        if(skillGid == null) skillGid = 0;
        if(fpos == null) fpos = 0;

        result.id = id;
        result.hcid = hcid;
        result.pcid = pcid;
        result.pname = pname;
        result.hname = hname;
        result.hgid = hgid;
        result.addDamage = addDamage;
        result.maxDamage = maxDamage;
        result.addHP = addHP;
        result.maxHP = maxHP;
        result.addAttSpeed = addAttSpeed;
        result.maxAttSpeed = maxAttSpeed;
        result.statues = statues;
        result.createTime = createTime;
        result.deadTime = deadTime;
        result.skillGid = skillGid;
        result.fpos = fpos;

        return result;
    }

    public static final Player_hero originalTo(Map e){
        Player_hero result = new Player_hero();

        Integer id = (Integer)e.get("id");
        Integer hcid = (Integer)e.get("hcid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        String hname = (String)e.get("hname");
        Integer hgid = (Integer)e.get("hgid");
        Integer addDamage = (Integer)e.get("addDamage");
        Integer maxDamage = (Integer)e.get("maxDamage");
        Integer addHP = (Integer)e.get("addHP");
        Integer maxHP = (Integer)e.get("maxHP");
        Integer addAttSpeed = (Integer)e.get("addAttSpeed");
        Integer maxAttSpeed = (Integer)e.get("maxAttSpeed");
        Integer statues = (Integer)e.get("statues");
        Long createTime = (Long)e.get("createTime");
        Long deadTime = (Long)e.get("deadTime");
        Integer skillGid = (Integer)e.get("skillGid");
        Integer fpos = (Integer)e.get("fpos");

        if(id == null) id = 0;
        if(hcid == null) hcid = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(hname == null) hname = "";
        if(hgid == null) hgid = 0;
        if(addDamage == null) addDamage = 0;
        if(maxDamage == null) maxDamage = 0;
        if(addHP == null) addHP = 0;
        if(maxHP == null) maxHP = 0;
        if(addAttSpeed == null) addAttSpeed = 0;
        if(maxAttSpeed == null) maxAttSpeed = 0;
        if(statues == null) statues = 0;
        if(createTime == null) createTime = 0L;
        if(deadTime == null) deadTime = 0L;
        if(skillGid == null) skillGid = 0;
        if(fpos == null) fpos = 0;

        result.id = id;
        result.hcid = hcid;
        result.pcid = pcid;
        result.pname = pname;
        result.hname = hname;
        result.hgid = hgid;
        result.addDamage = addDamage;
        result.maxDamage = maxDamage;
        result.addHP = addHP;
        result.maxHP = maxHP;
        result.addAttSpeed = addAttSpeed;
        result.maxAttSpeed = maxAttSpeed;
        result.statues = statues;
        result.createTime = createTime;
        result.deadTime = deadTime;
        result.skillGid = skillGid;
        result.fpos = fpos;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Player_hero createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }
    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = TABLENAME + id;
        return s.hashCode();
    }
    public static final Player_hero loadByKey(int id) {
        return Player_heroEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Player_hero insert() {
        Player_hero result = Player_heroEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Player_hero asyncInsert() {
        Player_hero result = Player_heroEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Player_hero insert2() {
        return Player_heroEntity.insert2(this);
    }

    public final Player_hero asyncInsert2() {
        Player_hero result = Player_heroEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Player_hero update() {
        return Player_heroEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Player_heroEntity.asynchronousUpdate( this );
        return true;
    }

    public final Player_hero insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Player_heroEntity.delete(id);
    }

    public final void asyncDelete() {
        Player_heroEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Player_hero clone2() {
        try{
            return (Player_hero) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
