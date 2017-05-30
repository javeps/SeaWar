package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - player_build_obst
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Player_build_obst extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1854103863; // com.sea.db.bean.Player_build_obst

    public static String TABLENAME = "player_build_obst";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "bcid", "building_name", "pcid", "player_name", "gid", "lvl", "cooldown_ms", "position_index", "state", "type"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "VARCHAR", "INT", "INT", "BIGINT", "INT", "INT", "INT"};


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
    public int bcid;
    public String building_name;
    public int pcid;
    public String player_name;
    public int gid;
    public int lvl;
    public long cooldown_ms;
    public int position_index;
    public int state;
    public int type;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Player_build_obst setId(int id){
        this.id = id;
        return this;
    }

    public int getBcid(){
        return bcid;
    }

    public Player_build_obst setBcid(int bcid){
        int _old = bcid;
        this.bcid = bcid;
        changeIt("bcid", _old, bcid);
        return this;
    }

    public Player_build_obst changeBcid(int bcid){
        return setBcid(this.bcid + bcid);
    }

    public Player_build_obst changeBcidWithMin(int bcid, int _min){
        int _v2 = this.bcid + bcid;
        return setBcid(_v2 < _min  ? _min : _v2);
    }

    public Player_build_obst changeBcidWithMax(int bcid, int _max){
        int _v2 = this.bcid + bcid;
        return setBcid(_v2 > _max  ? _max : _v2);
    }

    public Player_build_obst changeBcidWithMinMax(int bcid, int _min, int _max){
        int _v2 = this.bcid + bcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBcid(_v2 < _min  ? _min : _v2);
    }

    public String getBuilding_name(){
        return building_name;
    }

    public Player_build_obst setBuilding_name(String building_name){
        String _old = building_name;
        this.building_name = building_name;
        changeIt("building_name", _old, building_name);
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Player_build_obst setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Player_build_obst changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Player_build_obst changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Player_build_obst changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Player_build_obst changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPlayer_name(){
        return player_name;
    }

    public Player_build_obst setPlayer_name(String player_name){
        String _old = player_name;
        this.player_name = player_name;
        changeIt("player_name", _old, player_name);
        return this;
    }

    public int getGid(){
        return gid;
    }

    public Player_build_obst setGid(int gid){
        int _old = gid;
        this.gid = gid;
        changeIt("gid", _old, gid);
        return this;
    }

    public Player_build_obst changeGid(int gid){
        return setGid(this.gid + gid);
    }

    public Player_build_obst changeGidWithMin(int gid, int _min){
        int _v2 = this.gid + gid;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public Player_build_obst changeGidWithMax(int gid, int _max){
        int _v2 = this.gid + gid;
        return setGid(_v2 > _max  ? _max : _v2);
    }

    public Player_build_obst changeGidWithMinMax(int gid, int _min, int _max){
        int _v2 = this.gid + gid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public int getLvl(){
        return lvl;
    }

    public Player_build_obst setLvl(int lvl){
        int _old = lvl;
        this.lvl = lvl;
        changeIt("lvl", _old, lvl);
        return this;
    }

    public Player_build_obst changeLvl(int lvl){
        return setLvl(this.lvl + lvl);
    }

    public Player_build_obst changeLvlWithMin(int lvl, int _min){
        int _v2 = this.lvl + lvl;
        return setLvl(_v2 < _min  ? _min : _v2);
    }

    public Player_build_obst changeLvlWithMax(int lvl, int _max){
        int _v2 = this.lvl + lvl;
        return setLvl(_v2 > _max  ? _max : _v2);
    }

    public Player_build_obst changeLvlWithMinMax(int lvl, int _min, int _max){
        int _v2 = this.lvl + lvl;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLvl(_v2 < _min  ? _min : _v2);
    }

    public long getCooldown_ms(){
        return cooldown_ms;
    }

    public Player_build_obst setCooldown_ms(long cooldown_ms){
        long _old = cooldown_ms;
        this.cooldown_ms = cooldown_ms;
        changeIt("cooldown_ms", _old, cooldown_ms);
        return this;
    }

    public Player_build_obst changeCooldown_ms(long cooldown_ms){
        return setCooldown_ms(this.cooldown_ms + cooldown_ms);
    }

    public Player_build_obst changeCooldown_msWithMin(long cooldown_ms, long _min){
        long _v2 = this.cooldown_ms + cooldown_ms;
        return setCooldown_ms(_v2 < _min  ? _min : _v2);
    }

    public Player_build_obst changeCooldown_msWithMax(long cooldown_ms, long _max){
        long _v2 = this.cooldown_ms + cooldown_ms;
        return setCooldown_ms(_v2 > _max  ? _max : _v2);
    }

    public Player_build_obst changeCooldown_msWithMinMax(long cooldown_ms, long _min, long _max){
        long _v2 = this.cooldown_ms + cooldown_ms;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCooldown_ms(_v2 < _min  ? _min : _v2);
    }

    public int getPosition_index(){
        return position_index;
    }

    public Player_build_obst setPosition_index(int position_index){
        int _old = position_index;
        this.position_index = position_index;
        changeIt("position_index", _old, position_index);
        return this;
    }

    public Player_build_obst changePosition_index(int position_index){
        return setPosition_index(this.position_index + position_index);
    }

    public Player_build_obst changePosition_indexWithMin(int position_index, int _min){
        int _v2 = this.position_index + position_index;
        return setPosition_index(_v2 < _min  ? _min : _v2);
    }

    public Player_build_obst changePosition_indexWithMax(int position_index, int _max){
        int _v2 = this.position_index + position_index;
        return setPosition_index(_v2 > _max  ? _max : _v2);
    }

    public Player_build_obst changePosition_indexWithMinMax(int position_index, int _min, int _max){
        int _v2 = this.position_index + position_index;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPosition_index(_v2 < _min  ? _min : _v2);
    }

    public int getState(){
        return state;
    }

    public Player_build_obst setState(int state){
        int _old = state;
        this.state = state;
        changeIt("state", _old, state);
        return this;
    }

    public Player_build_obst changeState(int state){
        return setState(this.state + state);
    }

    public Player_build_obst changeStateWithMin(int state, int _min){
        int _v2 = this.state + state;
        return setState(_v2 < _min  ? _min : _v2);
    }

    public Player_build_obst changeStateWithMax(int state, int _max){
        int _v2 = this.state + state;
        return setState(_v2 > _max  ? _max : _v2);
    }

    public Player_build_obst changeStateWithMinMax(int state, int _min, int _max){
        int _v2 = this.state + state;
        _v2 = _v2 > _max  ? _max : _v2;
        return setState(_v2 < _min  ? _min : _v2);
    }

    public int getType(){
        return type;
    }

    public Player_build_obst setType(int type){
        int _old = type;
        this.type = type;
        changeIt("type", _old, type);
        return this;
    }

    public Player_build_obst changeType(int type){
        return setType(this.type + type);
    }

    public Player_build_obst changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Player_build_obst changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Player_build_obst changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Player_build_obst v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Player_build_obst v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Player_build_obst newPlayer_build_obst(Integer id, Integer bcid, String building_name, Integer pcid, String player_name, Integer gid, Integer lvl, Long cooldown_ms, Integer position_index, Integer state, Integer type) {
        Player_build_obst result = new Player_build_obst();
        result.id = id;
        result.bcid = bcid;
        result.building_name = building_name;
        result.pcid = pcid;
        result.player_name = player_name;
        result.gid = gid;
        result.lvl = lvl;
        result.cooldown_ms = cooldown_ms;
        result.position_index = position_index;
        result.state = state;
        result.type = type;
        return result;
    }

    public static Player_build_obst newPlayer_build_obst(Player_build_obst player_build_obst) {
        Player_build_obst result = new Player_build_obst();
        result.id = player_build_obst.id;
        result.bcid = player_build_obst.bcid;
        result.building_name = player_build_obst.building_name;
        result.pcid = player_build_obst.pcid;
        result.player_name = player_build_obst.player_name;
        result.gid = player_build_obst.gid;
        result.lvl = player_build_obst.lvl;
        result.cooldown_ms = player_build_obst.cooldown_ms;
        result.position_index = player_build_obst.position_index;
        result.state = player_build_obst.state;
        result.type = player_build_obst.type;
        return result;
    }

    public Player_build_obst createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, bcid);
        B2OutputStream.writeObject(_out, building_name);
        B2OutputStream.writeObject(_out, pcid);
        B2OutputStream.writeObject(_out, player_name);
        B2OutputStream.writeObject(_out, gid);
        B2OutputStream.writeObject(_out, lvl);
        B2OutputStream.writeObject(_out, cooldown_ms);
        B2OutputStream.writeObject(_out, position_index);
        B2OutputStream.writeObject(_out, state);
        B2OutputStream.writeObject(_out, type);
    }

    public static final Player_build_obst readObject(InputStream _in) throws Exception {
        Player_build_obst result = new Player_build_obst();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.bcid = (Integer) B2InputStream.readObject(_in);
        result.building_name = (String) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        result.player_name = (String) B2InputStream.readObject(_in);
        result.gid = (Integer) B2InputStream.readObject(_in);
        result.lvl = (Integer) B2InputStream.readObject(_in);
        result.cooldown_ms = (Long) B2InputStream.readObject(_in);
        result.position_index = (Integer) B2InputStream.readObject(_in);
        result.state = (Integer) B2InputStream.readObject(_in);
        result.type = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getPlayer_build_obst(){
        Player_build_obst player_build_obst = null; // player_build_obst
        { // new and insert Player_build_obst (player_build_obst)
            int id = 0; 	// id
            int bcid = 0; 	// bcid
            String building_name = ""; 	// building_name
            int pcid = 0; 	// pcid
            String player_name = ""; 	// player_name
            int gid = 0; 	// gid
            int lvl = 0; 	// lvl
            long cooldown_ms = 0; 	// cooldown_ms
            int position_index = 0; 	// position_index
            int state = 0; 	// state
            int type = 0; 	// type
            player_build_obst = Player_build_obst.newPlayer_build_obst(id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type);
        }
        player_build_obst = player_build_obst.insert();

        int id = player_build_obst.getId(); 	// id
        int bcid = player_build_obst.getBcid(); 	// bcid
        String building_name = player_build_obst.getBuilding_name(); 	// building_name
        int pcid = player_build_obst.getPcid(); 	// pcid
        String player_name = player_build_obst.getPlayer_name(); 	// player_name
        int gid = player_build_obst.getGid(); 	// gid
        int lvl = player_build_obst.getLvl(); 	// lvl
        long cooldown_ms = player_build_obst.getCooldown_ms(); 	// cooldown_ms
        int position_index = player_build_obst.getPosition_index(); 	// position_index
        int state = player_build_obst.getState(); 	// state
        int type = player_build_obst.getType(); 	// type
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
        case "bcid":
            return bcid;
        case "pcid":
            return pcid;
        case "gid":
            return gid;
        case "lvl":
            return lvl;
        case "position_index":
            return position_index;
        case "state":
            return state;
        case "type":
            return type;
        }
        return 0;
    }

    public Player_build_obst setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Player_build_obst setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "bcid":
            return setBcid(value2);
        case "pcid":
            return setPcid(value2);
        case "gid":
            return setGid(value2);
        case "lvl":
            return setLvl(value2);
        case "position_index":
            return setPosition_index(value2);
        case "state":
            return setState(value2);
        case "type":
            return setType(value2);
        }
        return this;
    }

    public Player_build_obst changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Player_build_obst changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "bcid":
            return changeBcid(value2);
        case "pcid":
            return changePcid(value2);
        case "gid":
            return changeGid(value2);
        case "lvl":
            return changeLvl(value2);
        case "position_index":
            return changePosition_index(value2);
        case "state":
            return changeState(value2);
        case "type":
            return changeType(value2);
        }
        return this;
    }

    public Player_build_obst changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Player_build_obst changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "bcid":
            return changeBcidWithMin(value2, _min);
        case "pcid":
            return changePcidWithMin(value2, _min);
        case "gid":
            return changeGidWithMin(value2, _min);
        case "lvl":
            return changeLvlWithMin(value2, _min);
        case "position_index":
            return changePosition_indexWithMin(value2, _min);
        case "state":
            return changeStateWithMin(value2, _min);
        case "type":
            return changeTypeWithMin(value2, _min);
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

    public Player_build_obst setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Player_build_obst setDouble(String fieldEn, double value2) {
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
        case "building_name": 
            return building_name;
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
        case "bcid":
            return bcid;
        case "building_name":
            return building_name;
        case "pcid":
            return pcid;
        case "player_name":
            return player_name;
        case "gid":
            return gid;
        case "lvl":
            return lvl;
        case "cooldown_ms":
            return cooldown_ms;
        case "position_index":
            return position_index;
        case "state":
            return state;
        case "type":
            return type;
        }
        return null;
    }

    public Player_build_obst setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Player_build_obst setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "building_name":
            return setBuilding_name(value2);
        case "player_name":
            return setPlayer_name(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Player_build_obst setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Player_build_obst setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Player_build_obst");
        result.put("id", id);
        result.put("bcid", bcid);
        result.put("building_name", building_name);
        result.put("pcid", pcid);
        result.put("player_name", player_name);
        result.put("gid", gid);
        result.put("lvl", lvl);
        result.put("cooldown_ms", cooldown_ms);
        result.put("position_index", position_index);
        result.put("state", state);
        result.put("type", type);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("bcid", bcid);
        result.put("building_name", building_name);
        result.put("pcid", pcid);
        result.put("player_name", player_name);
        result.put("gid", gid);
        result.put("lvl", lvl);
        result.put("cooldown_ms", cooldown_ms);
        result.put("position_index", position_index);
        result.put("state", state);
        result.put("type", type);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Player_build_obst");
        result.put("id", id);
        result.put("bcid", bcid);
        result.put("building_name", building_name);
        result.put("pcid", pcid);
        result.put("player_name", player_name);
        result.put("gid", gid);
        result.put("lvl", lvl);
        result.put("cooldown_ms", cooldown_ms);
        result.put("position_index", position_index);
        result.put("state", state);
        result.put("type", type);
        return result;
    }

    public Player_build_obst mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer bcid = (Integer)e.get("bcid");
        String building_name = (String)e.get("building_name");
        Integer pcid = (Integer)e.get("pcid");
        String player_name = (String)e.get("player_name");
        Integer gid = (Integer)e.get("gid");
        Integer lvl = (Integer)e.get("lvl");
        Long cooldown_ms = (Long)e.get("cooldown_ms");
        Integer position_index = (Integer)e.get("position_index");
        Integer state = (Integer)e.get("state");
        Integer type = (Integer)e.get("type");

        if(id == null) id = 0;
        if(bcid == null) bcid = 0;
        if(building_name == null) building_name = "";
        if(pcid == null) pcid = 0;
        if(player_name == null) player_name = "";
        if(gid == null) gid = 0;
        if(lvl == null) lvl = 0;
        if(cooldown_ms == null) cooldown_ms = 0L;
        if(position_index == null) position_index = 0;
        if(state == null) state = 0;
        if(type == null) type = 0;

        setId(id);
        setBcid(bcid);
        setBuilding_name(building_name);
        setPcid(pcid);
        setPlayer_name(player_name);
        setGid(gid);
        setLvl(lvl);
        setCooldown_ms(cooldown_ms);
        setPosition_index(position_index);
        setState(state);
        setType(type);

        return this;
    }

    public static final Player_build_obst mapTo(Map e){
        Player_build_obst result = new Player_build_obst();

        Integer id = (Integer)e.get("id");
        Integer bcid = (Integer)e.get("bcid");
        String building_name = (String)e.get("building_name");
        Integer pcid = (Integer)e.get("pcid");
        String player_name = (String)e.get("player_name");
        Integer gid = (Integer)e.get("gid");
        Integer lvl = (Integer)e.get("lvl");
        Long cooldown_ms = (Long)e.get("cooldown_ms");
        Integer position_index = (Integer)e.get("position_index");
        Integer state = (Integer)e.get("state");
        Integer type = (Integer)e.get("type");

        if(id == null) id = 0;
        if(bcid == null) bcid = 0;
        if(building_name == null) building_name = "";
        if(pcid == null) pcid = 0;
        if(player_name == null) player_name = "";
        if(gid == null) gid = 0;
        if(lvl == null) lvl = 0;
        if(cooldown_ms == null) cooldown_ms = 0L;
        if(position_index == null) position_index = 0;
        if(state == null) state = 0;
        if(type == null) type = 0;

        result.id = id;
        result.bcid = bcid;
        result.building_name = building_name;
        result.pcid = pcid;
        result.player_name = player_name;
        result.gid = gid;
        result.lvl = lvl;
        result.cooldown_ms = cooldown_ms;
        result.position_index = position_index;
        result.state = state;
        result.type = type;

        return result;
    }

    public static final Player_build_obst originalTo(Map e){
        Player_build_obst result = new Player_build_obst();

        Integer id = (Integer)e.get("id");
        Integer bcid = (Integer)e.get("bcid");
        String building_name = (String)e.get("building_name");
        Integer pcid = (Integer)e.get("pcid");
        String player_name = (String)e.get("player_name");
        Integer gid = (Integer)e.get("gid");
        Integer lvl = (Integer)e.get("lvl");
        Long cooldown_ms = (Long)e.get("cooldown_ms");
        Integer position_index = (Integer)e.get("position_index");
        Integer state = (Integer)e.get("state");
        Integer type = (Integer)e.get("type");

        if(id == null) id = 0;
        if(bcid == null) bcid = 0;
        if(building_name == null) building_name = "";
        if(pcid == null) pcid = 0;
        if(player_name == null) player_name = "";
        if(gid == null) gid = 0;
        if(lvl == null) lvl = 0;
        if(cooldown_ms == null) cooldown_ms = 0L;
        if(position_index == null) position_index = 0;
        if(state == null) state = 0;
        if(type == null) type = 0;

        result.id = id;
        result.bcid = bcid;
        result.building_name = building_name;
        result.pcid = pcid;
        result.player_name = player_name;
        result.gid = gid;
        result.lvl = lvl;
        result.cooldown_ms = cooldown_ms;
        result.position_index = position_index;
        result.state = state;
        result.type = type;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Player_build_obst createFor(byte[] b) throws Exception {
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
    public static final Player_build_obst loadByKey(int id) {
        return Player_build_obstEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Player_build_obst insert() {
        Player_build_obst result = Player_build_obstEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Player_build_obst asyncInsert() {
        Player_build_obst result = Player_build_obstEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Player_build_obst insert2() {
        return Player_build_obstEntity.insert2(this);
    }

    public final Player_build_obst asyncInsert2() {
        Player_build_obst result = Player_build_obstEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Player_build_obst update() {
        return Player_build_obstEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Player_build_obstEntity.asynchronousUpdate( this );
        return true;
    }

    public final Player_build_obst insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Player_build_obstEntity.delete(id);
    }

    public final void asyncDelete() {
        Player_build_obstEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Player_build_obst clone2() {
        try{
            return (Player_build_obst) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
