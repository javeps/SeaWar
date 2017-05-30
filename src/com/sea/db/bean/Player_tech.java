package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - player_tech
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Player_tech extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 783684026; // com.sea.db.bean.Player_tech

    public static String TABLENAME = "player_tech";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "tech_name", "pcid", "player_name", "gid", "lvl"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "INT", "VARCHAR", "INT", "INT"};


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
    public String tech_name;
    public int pcid;
    public String player_name;
    public int gid;
    public int lvl;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Player_tech setId(int id){
        this.id = id;
        return this;
    }

    public String getTech_name(){
        return tech_name;
    }

    public Player_tech setTech_name(String tech_name){
        String _old = tech_name;
        this.tech_name = tech_name;
        changeIt("tech_name", _old, tech_name);
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Player_tech setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Player_tech changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Player_tech changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Player_tech changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Player_tech changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPlayer_name(){
        return player_name;
    }

    public Player_tech setPlayer_name(String player_name){
        String _old = player_name;
        this.player_name = player_name;
        changeIt("player_name", _old, player_name);
        return this;
    }

    public int getGid(){
        return gid;
    }

    public Player_tech setGid(int gid){
        int _old = gid;
        this.gid = gid;
        changeIt("gid", _old, gid);
        return this;
    }

    public Player_tech changeGid(int gid){
        return setGid(this.gid + gid);
    }

    public Player_tech changeGidWithMin(int gid, int _min){
        int _v2 = this.gid + gid;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public Player_tech changeGidWithMax(int gid, int _max){
        int _v2 = this.gid + gid;
        return setGid(_v2 > _max  ? _max : _v2);
    }

    public Player_tech changeGidWithMinMax(int gid, int _min, int _max){
        int _v2 = this.gid + gid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public int getLvl(){
        return lvl;
    }

    public Player_tech setLvl(int lvl){
        int _old = lvl;
        this.lvl = lvl;
        changeIt("lvl", _old, lvl);
        return this;
    }

    public Player_tech changeLvl(int lvl){
        return setLvl(this.lvl + lvl);
    }

    public Player_tech changeLvlWithMin(int lvl, int _min){
        int _v2 = this.lvl + lvl;
        return setLvl(_v2 < _min  ? _min : _v2);
    }

    public Player_tech changeLvlWithMax(int lvl, int _max){
        int _v2 = this.lvl + lvl;
        return setLvl(_v2 > _max  ? _max : _v2);
    }

    public Player_tech changeLvlWithMinMax(int lvl, int _min, int _max){
        int _v2 = this.lvl + lvl;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLvl(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Player_tech v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Player_tech v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Player_tech newPlayer_tech(Integer id, String tech_name, Integer pcid, String player_name, Integer gid, Integer lvl) {
        Player_tech result = new Player_tech();
        result.id = id;
        result.tech_name = tech_name;
        result.pcid = pcid;
        result.player_name = player_name;
        result.gid = gid;
        result.lvl = lvl;
        return result;
    }

    public static Player_tech newPlayer_tech(Player_tech player_tech) {
        Player_tech result = new Player_tech();
        result.id = player_tech.id;
        result.tech_name = player_tech.tech_name;
        result.pcid = player_tech.pcid;
        result.player_name = player_tech.player_name;
        result.gid = player_tech.gid;
        result.lvl = player_tech.lvl;
        return result;
    }

    public Player_tech createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, tech_name);
        B2OutputStream.writeObject(_out, pcid);
        B2OutputStream.writeObject(_out, player_name);
        B2OutputStream.writeObject(_out, gid);
        B2OutputStream.writeObject(_out, lvl);
    }

    public static final Player_tech readObject(InputStream _in) throws Exception {
        Player_tech result = new Player_tech();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.tech_name = (String) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        result.player_name = (String) B2InputStream.readObject(_in);
        result.gid = (Integer) B2InputStream.readObject(_in);
        result.lvl = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getPlayer_tech(){
        Player_tech player_tech = null; // player_tech
        { // new and insert Player_tech (player_tech)
            int id = 0; 	// id
            String tech_name = ""; 	// tech_name
            int pcid = 0; 	// pcid
            String player_name = ""; 	// player_name
            int gid = 0; 	// gid
            int lvl = 0; 	// lvl
            player_tech = Player_tech.newPlayer_tech(id, tech_name, pcid, player_name, gid, lvl);
        }
        player_tech = player_tech.insert();

        int id = player_tech.getId(); 	// id
        String tech_name = player_tech.getTech_name(); 	// tech_name
        int pcid = player_tech.getPcid(); 	// pcid
        String player_name = player_tech.getPlayer_name(); 	// player_name
        int gid = player_tech.getGid(); 	// gid
        int lvl = player_tech.getLvl(); 	// lvl
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
        case "pcid":
            return pcid;
        case "gid":
            return gid;
        case "lvl":
            return lvl;
        }
        return 0;
    }

    public Player_tech setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Player_tech setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "pcid":
            return setPcid(value2);
        case "gid":
            return setGid(value2);
        case "lvl":
            return setLvl(value2);
        }
        return this;
    }

    public Player_tech changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Player_tech changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "pcid":
            return changePcid(value2);
        case "gid":
            return changeGid(value2);
        case "lvl":
            return changeLvl(value2);
        }
        return this;
    }

    public Player_tech changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Player_tech changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "pcid":
            return changePcidWithMin(value2, _min);
        case "gid":
            return changeGidWithMin(value2, _min);
        case "lvl":
            return changeLvlWithMin(value2, _min);
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

    public Player_tech setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Player_tech setDouble(String fieldEn, double value2) {
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
        case "tech_name": 
            return tech_name;
        case "player_name": 
            return player_name;
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
        case "tech_name":
            return tech_name;
        case "pcid":
            return pcid;
        case "player_name":
            return player_name;
        case "gid":
            return gid;
        case "lvl":
            return lvl;
        }
        return null;
    }

    public Player_tech setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Player_tech setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "tech_name":
            return setTech_name(value2);
        case "player_name":
            return setPlayer_name(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Player_tech setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Player_tech setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Player_tech");
        result.put("id", id);
        result.put("tech_name", tech_name);
        result.put("pcid", pcid);
        result.put("player_name", player_name);
        result.put("gid", gid);
        result.put("lvl", lvl);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("tech_name", tech_name);
        result.put("pcid", pcid);
        result.put("player_name", player_name);
        result.put("gid", gid);
        result.put("lvl", lvl);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Player_tech");
        result.put("id", id);
        result.put("tech_name", tech_name);
        result.put("pcid", pcid);
        result.put("player_name", player_name);
        result.put("gid", gid);
        result.put("lvl", lvl);
        return result;
    }

    public Player_tech mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String tech_name = (String)e.get("tech_name");
        Integer pcid = (Integer)e.get("pcid");
        String player_name = (String)e.get("player_name");
        Integer gid = (Integer)e.get("gid");
        Integer lvl = (Integer)e.get("lvl");

        if(id == null) id = 0;
        if(tech_name == null) tech_name = "";
        if(pcid == null) pcid = 0;
        if(player_name == null) player_name = "";
        if(gid == null) gid = 0;
        if(lvl == null) lvl = 0;

        setId(id);
        setTech_name(tech_name);
        setPcid(pcid);
        setPlayer_name(player_name);
        setGid(gid);
        setLvl(lvl);

        return this;
    }

    public static final Player_tech mapTo(Map e){
        Player_tech result = new Player_tech();

        Integer id = (Integer)e.get("id");
        String tech_name = (String)e.get("tech_name");
        Integer pcid = (Integer)e.get("pcid");
        String player_name = (String)e.get("player_name");
        Integer gid = (Integer)e.get("gid");
        Integer lvl = (Integer)e.get("lvl");

        if(id == null) id = 0;
        if(tech_name == null) tech_name = "";
        if(pcid == null) pcid = 0;
        if(player_name == null) player_name = "";
        if(gid == null) gid = 0;
        if(lvl == null) lvl = 0;

        result.id = id;
        result.tech_name = tech_name;
        result.pcid = pcid;
        result.player_name = player_name;
        result.gid = gid;
        result.lvl = lvl;

        return result;
    }

    public static final Player_tech originalTo(Map e){
        Player_tech result = new Player_tech();

        Integer id = (Integer)e.get("id");
        String tech_name = (String)e.get("tech_name");
        Integer pcid = (Integer)e.get("pcid");
        String player_name = (String)e.get("player_name");
        Integer gid = (Integer)e.get("gid");
        Integer lvl = (Integer)e.get("lvl");

        if(id == null) id = 0;
        if(tech_name == null) tech_name = "";
        if(pcid == null) pcid = 0;
        if(player_name == null) player_name = "";
        if(gid == null) gid = 0;
        if(lvl == null) lvl = 0;

        result.id = id;
        result.tech_name = tech_name;
        result.pcid = pcid;
        result.player_name = player_name;
        result.gid = gid;
        result.lvl = lvl;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Player_tech createFor(byte[] b) throws Exception {
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
    public static final Player_tech loadByKey(int id) {
        return Player_techEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Player_tech insert() {
        Player_tech result = Player_techEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Player_tech asyncInsert() {
        Player_tech result = Player_techEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Player_tech insert2() {
        return Player_techEntity.insert2(this);
    }

    public final Player_tech asyncInsert2() {
        Player_tech result = Player_techEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Player_tech update() {
        return Player_techEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Player_techEntity.asynchronousUpdate( this );
        return true;
    }

    public final Player_tech insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Player_techEntity.delete(id);
    }

    public final void asyncDelete() {
        Player_techEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Player_tech clone2() {
        try{
            return (Player_tech) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
