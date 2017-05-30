package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - player_armys
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Player_armys extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1492748334; // com.sea.db.bean.Player_armys

    public static String TABLENAME = "player_armys";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "army_name", "pcid", "player_name", "gid", "lvl", "num", "bcid"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "INT", "VARCHAR", "INT", "INT", "INT", "INT"};


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
    public String army_name;
    public int pcid;
    public String player_name;
    public int gid;
    public int lvl;
    public int num;
    public int bcid;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Player_armys setId(int id){
        this.id = id;
        return this;
    }

    public String getArmy_name(){
        return army_name;
    }

    public Player_armys setArmy_name(String army_name){
        String _old = army_name;
        this.army_name = army_name;
        changeIt("army_name", _old, army_name);
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Player_armys setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Player_armys changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Player_armys changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Player_armys changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Player_armys changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPlayer_name(){
        return player_name;
    }

    public Player_armys setPlayer_name(String player_name){
        String _old = player_name;
        this.player_name = player_name;
        changeIt("player_name", _old, player_name);
        return this;
    }

    public int getGid(){
        return gid;
    }

    public Player_armys setGid(int gid){
        int _old = gid;
        this.gid = gid;
        changeIt("gid", _old, gid);
        return this;
    }

    public Player_armys changeGid(int gid){
        return setGid(this.gid + gid);
    }

    public Player_armys changeGidWithMin(int gid, int _min){
        int _v2 = this.gid + gid;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public Player_armys changeGidWithMax(int gid, int _max){
        int _v2 = this.gid + gid;
        return setGid(_v2 > _max  ? _max : _v2);
    }

    public Player_armys changeGidWithMinMax(int gid, int _min, int _max){
        int _v2 = this.gid + gid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public int getLvl(){
        return lvl;
    }

    public Player_armys setLvl(int lvl){
        int _old = lvl;
        this.lvl = lvl;
        changeIt("lvl", _old, lvl);
        return this;
    }

    public Player_armys changeLvl(int lvl){
        return setLvl(this.lvl + lvl);
    }

    public Player_armys changeLvlWithMin(int lvl, int _min){
        int _v2 = this.lvl + lvl;
        return setLvl(_v2 < _min  ? _min : _v2);
    }

    public Player_armys changeLvlWithMax(int lvl, int _max){
        int _v2 = this.lvl + lvl;
        return setLvl(_v2 > _max  ? _max : _v2);
    }

    public Player_armys changeLvlWithMinMax(int lvl, int _min, int _max){
        int _v2 = this.lvl + lvl;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLvl(_v2 < _min  ? _min : _v2);
    }

    public int getNum(){
        return num;
    }

    public Player_armys setNum(int num){
        int _old = num;
        this.num = num;
        changeIt("num", _old, num);
        return this;
    }

    public Player_armys changeNum(int num){
        return setNum(this.num + num);
    }

    public Player_armys changeNumWithMin(int num, int _min){
        int _v2 = this.num + num;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public Player_armys changeNumWithMax(int num, int _max){
        int _v2 = this.num + num;
        return setNum(_v2 > _max  ? _max : _v2);
    }

    public Player_armys changeNumWithMinMax(int num, int _min, int _max){
        int _v2 = this.num + num;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public int getBcid(){
        return bcid;
    }

    public Player_armys setBcid(int bcid){
        int _old = bcid;
        this.bcid = bcid;
        changeIt("bcid", _old, bcid);
        return this;
    }

    public Player_armys changeBcid(int bcid){
        return setBcid(this.bcid + bcid);
    }

    public Player_armys changeBcidWithMin(int bcid, int _min){
        int _v2 = this.bcid + bcid;
        return setBcid(_v2 < _min  ? _min : _v2);
    }

    public Player_armys changeBcidWithMax(int bcid, int _max){
        int _v2 = this.bcid + bcid;
        return setBcid(_v2 > _max  ? _max : _v2);
    }

    public Player_armys changeBcidWithMinMax(int bcid, int _min, int _max){
        int _v2 = this.bcid + bcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBcid(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Player_armys v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Player_armys v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Player_armys newPlayer_armys(Integer id, String army_name, Integer pcid, String player_name, Integer gid, Integer lvl, Integer num, Integer bcid) {
        Player_armys result = new Player_armys();
        result.id = id;
        result.army_name = army_name;
        result.pcid = pcid;
        result.player_name = player_name;
        result.gid = gid;
        result.lvl = lvl;
        result.num = num;
        result.bcid = bcid;
        return result;
    }

    public static Player_armys newPlayer_armys(Player_armys player_armys) {
        Player_armys result = new Player_armys();
        result.id = player_armys.id;
        result.army_name = player_armys.army_name;
        result.pcid = player_armys.pcid;
        result.player_name = player_armys.player_name;
        result.gid = player_armys.gid;
        result.lvl = player_armys.lvl;
        result.num = player_armys.num;
        result.bcid = player_armys.bcid;
        return result;
    }

    public Player_armys createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, army_name);
        B2OutputStream.writeObject(_out, pcid);
        B2OutputStream.writeObject(_out, player_name);
        B2OutputStream.writeObject(_out, gid);
        B2OutputStream.writeObject(_out, lvl);
        B2OutputStream.writeObject(_out, num);
        B2OutputStream.writeObject(_out, bcid);
    }

    public static final Player_armys readObject(InputStream _in) throws Exception {
        Player_armys result = new Player_armys();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.army_name = (String) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        result.player_name = (String) B2InputStream.readObject(_in);
        result.gid = (Integer) B2InputStream.readObject(_in);
        result.lvl = (Integer) B2InputStream.readObject(_in);
        result.num = (Integer) B2InputStream.readObject(_in);
        result.bcid = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getPlayer_armys(){
        Player_armys player_armys = null; // player_armys
        { // new and insert Player_armys (player_armys)
            int id = 0; 	// id
            String army_name = ""; 	// army_name
            int pcid = 0; 	// pcid
            String player_name = ""; 	// player_name
            int gid = 0; 	// gid
            int lvl = 0; 	// lvl
            int num = 0; 	// num
            int bcid = 0; 	// bcid
            player_armys = Player_armys.newPlayer_armys(id, army_name, pcid, player_name, gid, lvl, num, bcid);
        }
        player_armys = player_armys.insert();

        int id = player_armys.getId(); 	// id
        String army_name = player_armys.getArmy_name(); 	// army_name
        int pcid = player_armys.getPcid(); 	// pcid
        String player_name = player_armys.getPlayer_name(); 	// player_name
        int gid = player_armys.getGid(); 	// gid
        int lvl = player_armys.getLvl(); 	// lvl
        int num = player_armys.getNum(); 	// num
        int bcid = player_armys.getBcid(); 	// bcid
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
        case "num":
            return num;
        case "bcid":
            return bcid;
        }
        return 0;
    }

    public Player_armys setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Player_armys setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "pcid":
            return setPcid(value2);
        case "gid":
            return setGid(value2);
        case "lvl":
            return setLvl(value2);
        case "num":
            return setNum(value2);
        case "bcid":
            return setBcid(value2);
        }
        return this;
    }

    public Player_armys changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Player_armys changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "pcid":
            return changePcid(value2);
        case "gid":
            return changeGid(value2);
        case "lvl":
            return changeLvl(value2);
        case "num":
            return changeNum(value2);
        case "bcid":
            return changeBcid(value2);
        }
        return this;
    }

    public Player_armys changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Player_armys changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "pcid":
            return changePcidWithMin(value2, _min);
        case "gid":
            return changeGidWithMin(value2, _min);
        case "lvl":
            return changeLvlWithMin(value2, _min);
        case "num":
            return changeNumWithMin(value2, _min);
        case "bcid":
            return changeBcidWithMin(value2, _min);
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

    public Player_armys setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Player_armys setDouble(String fieldEn, double value2) {
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
        case "army_name": 
            return army_name;
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
        case "army_name":
            return army_name;
        case "pcid":
            return pcid;
        case "player_name":
            return player_name;
        case "gid":
            return gid;
        case "lvl":
            return lvl;
        case "num":
            return num;
        case "bcid":
            return bcid;
        }
        return null;
    }

    public Player_armys setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Player_armys setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "army_name":
            return setArmy_name(value2);
        case "player_name":
            return setPlayer_name(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Player_armys setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Player_armys setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Player_armys");
        result.put("id", id);
        result.put("army_name", army_name);
        result.put("pcid", pcid);
        result.put("player_name", player_name);
        result.put("gid", gid);
        result.put("lvl", lvl);
        result.put("num", num);
        result.put("bcid", bcid);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("army_name", army_name);
        result.put("pcid", pcid);
        result.put("player_name", player_name);
        result.put("gid", gid);
        result.put("lvl", lvl);
        result.put("num", num);
        result.put("bcid", bcid);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Player_armys");
        result.put("id", id);
        result.put("army_name", army_name);
        result.put("pcid", pcid);
        result.put("player_name", player_name);
        result.put("gid", gid);
        result.put("lvl", lvl);
        result.put("num", num);
        result.put("bcid", bcid);
        return result;
    }

    public Player_armys mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String army_name = (String)e.get("army_name");
        Integer pcid = (Integer)e.get("pcid");
        String player_name = (String)e.get("player_name");
        Integer gid = (Integer)e.get("gid");
        Integer lvl = (Integer)e.get("lvl");
        Integer num = (Integer)e.get("num");
        Integer bcid = (Integer)e.get("bcid");

        if(id == null) id = 0;
        if(army_name == null) army_name = "";
        if(pcid == null) pcid = 0;
        if(player_name == null) player_name = "";
        if(gid == null) gid = 0;
        if(lvl == null) lvl = 0;
        if(num == null) num = 0;
        if(bcid == null) bcid = 0;

        setId(id);
        setArmy_name(army_name);
        setPcid(pcid);
        setPlayer_name(player_name);
        setGid(gid);
        setLvl(lvl);
        setNum(num);
        setBcid(bcid);

        return this;
    }

    public static final Player_armys mapTo(Map e){
        Player_armys result = new Player_armys();

        Integer id = (Integer)e.get("id");
        String army_name = (String)e.get("army_name");
        Integer pcid = (Integer)e.get("pcid");
        String player_name = (String)e.get("player_name");
        Integer gid = (Integer)e.get("gid");
        Integer lvl = (Integer)e.get("lvl");
        Integer num = (Integer)e.get("num");
        Integer bcid = (Integer)e.get("bcid");

        if(id == null) id = 0;
        if(army_name == null) army_name = "";
        if(pcid == null) pcid = 0;
        if(player_name == null) player_name = "";
        if(gid == null) gid = 0;
        if(lvl == null) lvl = 0;
        if(num == null) num = 0;
        if(bcid == null) bcid = 0;

        result.id = id;
        result.army_name = army_name;
        result.pcid = pcid;
        result.player_name = player_name;
        result.gid = gid;
        result.lvl = lvl;
        result.num = num;
        result.bcid = bcid;

        return result;
    }

    public static final Player_armys originalTo(Map e){
        Player_armys result = new Player_armys();

        Integer id = (Integer)e.get("id");
        String army_name = (String)e.get("army_name");
        Integer pcid = (Integer)e.get("pcid");
        String player_name = (String)e.get("player_name");
        Integer gid = (Integer)e.get("gid");
        Integer lvl = (Integer)e.get("lvl");
        Integer num = (Integer)e.get("num");
        Integer bcid = (Integer)e.get("bcid");

        if(id == null) id = 0;
        if(army_name == null) army_name = "";
        if(pcid == null) pcid = 0;
        if(player_name == null) player_name = "";
        if(gid == null) gid = 0;
        if(lvl == null) lvl = 0;
        if(num == null) num = 0;
        if(bcid == null) bcid = 0;

        result.id = id;
        result.army_name = army_name;
        result.pcid = pcid;
        result.player_name = player_name;
        result.gid = gid;
        result.lvl = lvl;
        result.num = num;
        result.bcid = bcid;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Player_armys createFor(byte[] b) throws Exception {
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
    public static final Player_armys loadByKey(int id) {
        return Player_armysEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Player_armys insert() {
        Player_armys result = Player_armysEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Player_armys asyncInsert() {
        Player_armys result = Player_armysEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Player_armys insert2() {
        return Player_armysEntity.insert2(this);
    }

    public final Player_armys asyncInsert2() {
        Player_armys result = Player_armysEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Player_armys update() {
        return Player_armysEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Player_armysEntity.asynchronousUpdate( this );
        return true;
    }

    public final Player_armys insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Player_armysEntity.delete(id);
    }

    public final void asyncDelete() {
        Player_armysEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Player_armys clone2() {
        try{
            return (Player_armys) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
