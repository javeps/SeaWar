package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - player_buildings
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Player_buildings extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 771771291; // com.sea.db.bean.Player_buildings

    public static String TABLENAME = "player_buildings";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "bcid", "building_name", "pcid", "player_name", "gid", "lvl", "cooldown_ms", "position_index", "state", "type", "cur_produce_res", "res_produce_begin_time", "cur_up_tech_gid", "end_tech_up_time", "begin_army_time"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "VARCHAR", "INT", "INT", "BIGINT", "INT", "INT", "INT", "BIGINT", "BIGINT", "INT", "BIGINT", "BIGINT"};


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
    public long cur_produce_res;
    public long res_produce_begin_time;
    public int cur_up_tech_gid;
    public long end_tech_up_time;
    public long begin_army_time;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Player_buildings setId(int id){
        this.id = id;
        return this;
    }

    public int getBcid(){
        return bcid;
    }

    public Player_buildings setBcid(int bcid){
        int _old = bcid;
        this.bcid = bcid;
        changeIt("bcid", _old, bcid);
        return this;
    }

    public Player_buildings changeBcid(int bcid){
        return setBcid(this.bcid + bcid);
    }

    public Player_buildings changeBcidWithMin(int bcid, int _min){
        int _v2 = this.bcid + bcid;
        return setBcid(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeBcidWithMax(int bcid, int _max){
        int _v2 = this.bcid + bcid;
        return setBcid(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeBcidWithMinMax(int bcid, int _min, int _max){
        int _v2 = this.bcid + bcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBcid(_v2 < _min  ? _min : _v2);
    }

    public String getBuilding_name(){
        return building_name;
    }

    public Player_buildings setBuilding_name(String building_name){
        String _old = building_name;
        this.building_name = building_name;
        changeIt("building_name", _old, building_name);
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Player_buildings setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Player_buildings changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Player_buildings changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPlayer_name(){
        return player_name;
    }

    public Player_buildings setPlayer_name(String player_name){
        String _old = player_name;
        this.player_name = player_name;
        changeIt("player_name", _old, player_name);
        return this;
    }

    public int getGid(){
        return gid;
    }

    public Player_buildings setGid(int gid){
        int _old = gid;
        this.gid = gid;
        changeIt("gid", _old, gid);
        return this;
    }

    public Player_buildings changeGid(int gid){
        return setGid(this.gid + gid);
    }

    public Player_buildings changeGidWithMin(int gid, int _min){
        int _v2 = this.gid + gid;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeGidWithMax(int gid, int _max){
        int _v2 = this.gid + gid;
        return setGid(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeGidWithMinMax(int gid, int _min, int _max){
        int _v2 = this.gid + gid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public int getLvl(){
        return lvl;
    }

    public Player_buildings setLvl(int lvl){
        int _old = lvl;
        this.lvl = lvl;
        changeIt("lvl", _old, lvl);
        return this;
    }

    public Player_buildings changeLvl(int lvl){
        return setLvl(this.lvl + lvl);
    }

    public Player_buildings changeLvlWithMin(int lvl, int _min){
        int _v2 = this.lvl + lvl;
        return setLvl(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeLvlWithMax(int lvl, int _max){
        int _v2 = this.lvl + lvl;
        return setLvl(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeLvlWithMinMax(int lvl, int _min, int _max){
        int _v2 = this.lvl + lvl;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLvl(_v2 < _min  ? _min : _v2);
    }

    public long getCooldown_ms(){
        return cooldown_ms;
    }

    public Player_buildings setCooldown_ms(long cooldown_ms){
        long _old = cooldown_ms;
        this.cooldown_ms = cooldown_ms;
        changeIt("cooldown_ms", _old, cooldown_ms);
        return this;
    }

    public Player_buildings changeCooldown_ms(long cooldown_ms){
        return setCooldown_ms(this.cooldown_ms + cooldown_ms);
    }

    public Player_buildings changeCooldown_msWithMin(long cooldown_ms, long _min){
        long _v2 = this.cooldown_ms + cooldown_ms;
        return setCooldown_ms(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeCooldown_msWithMax(long cooldown_ms, long _max){
        long _v2 = this.cooldown_ms + cooldown_ms;
        return setCooldown_ms(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeCooldown_msWithMinMax(long cooldown_ms, long _min, long _max){
        long _v2 = this.cooldown_ms + cooldown_ms;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCooldown_ms(_v2 < _min  ? _min : _v2);
    }

    public int getPosition_index(){
        return position_index;
    }

    public Player_buildings setPosition_index(int position_index){
        int _old = position_index;
        this.position_index = position_index;
        changeIt("position_index", _old, position_index);
        return this;
    }

    public Player_buildings changePosition_index(int position_index){
        return setPosition_index(this.position_index + position_index);
    }

    public Player_buildings changePosition_indexWithMin(int position_index, int _min){
        int _v2 = this.position_index + position_index;
        return setPosition_index(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changePosition_indexWithMax(int position_index, int _max){
        int _v2 = this.position_index + position_index;
        return setPosition_index(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changePosition_indexWithMinMax(int position_index, int _min, int _max){
        int _v2 = this.position_index + position_index;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPosition_index(_v2 < _min  ? _min : _v2);
    }

    public int getState(){
        return state;
    }

    public Player_buildings setState(int state){
        int _old = state;
        this.state = state;
        changeIt("state", _old, state);
        return this;
    }

    public Player_buildings changeState(int state){
        return setState(this.state + state);
    }

    public Player_buildings changeStateWithMin(int state, int _min){
        int _v2 = this.state + state;
        return setState(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeStateWithMax(int state, int _max){
        int _v2 = this.state + state;
        return setState(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeStateWithMinMax(int state, int _min, int _max){
        int _v2 = this.state + state;
        _v2 = _v2 > _max  ? _max : _v2;
        return setState(_v2 < _min  ? _min : _v2);
    }

    public int getType(){
        return type;
    }

    public Player_buildings setType(int type){
        int _old = type;
        this.type = type;
        changeIt("type", _old, type);
        return this;
    }

    public Player_buildings changeType(int type){
        return setType(this.type + type);
    }

    public Player_buildings changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public long getCur_produce_res(){
        return cur_produce_res;
    }

    public Player_buildings setCur_produce_res(long cur_produce_res){
        long _old = cur_produce_res;
        this.cur_produce_res = cur_produce_res;
        changeIt("cur_produce_res", _old, cur_produce_res);
        return this;
    }

    public Player_buildings changeCur_produce_res(long cur_produce_res){
        return setCur_produce_res(this.cur_produce_res + cur_produce_res);
    }

    public Player_buildings changeCur_produce_resWithMin(long cur_produce_res, long _min){
        long _v2 = this.cur_produce_res + cur_produce_res;
        return setCur_produce_res(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeCur_produce_resWithMax(long cur_produce_res, long _max){
        long _v2 = this.cur_produce_res + cur_produce_res;
        return setCur_produce_res(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeCur_produce_resWithMinMax(long cur_produce_res, long _min, long _max){
        long _v2 = this.cur_produce_res + cur_produce_res;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCur_produce_res(_v2 < _min  ? _min : _v2);
    }

    public long getRes_produce_begin_time(){
        return res_produce_begin_time;
    }

    public Player_buildings setRes_produce_begin_time(long res_produce_begin_time){
        long _old = res_produce_begin_time;
        this.res_produce_begin_time = res_produce_begin_time;
        changeIt("res_produce_begin_time", _old, res_produce_begin_time);
        return this;
    }

    public Player_buildings changeRes_produce_begin_time(long res_produce_begin_time){
        return setRes_produce_begin_time(this.res_produce_begin_time + res_produce_begin_time);
    }

    public Player_buildings changeRes_produce_begin_timeWithMin(long res_produce_begin_time, long _min){
        long _v2 = this.res_produce_begin_time + res_produce_begin_time;
        return setRes_produce_begin_time(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeRes_produce_begin_timeWithMax(long res_produce_begin_time, long _max){
        long _v2 = this.res_produce_begin_time + res_produce_begin_time;
        return setRes_produce_begin_time(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeRes_produce_begin_timeWithMinMax(long res_produce_begin_time, long _min, long _max){
        long _v2 = this.res_produce_begin_time + res_produce_begin_time;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRes_produce_begin_time(_v2 < _min  ? _min : _v2);
    }

    public int getCur_up_tech_gid(){
        return cur_up_tech_gid;
    }

    public Player_buildings setCur_up_tech_gid(int cur_up_tech_gid){
        int _old = cur_up_tech_gid;
        this.cur_up_tech_gid = cur_up_tech_gid;
        changeIt("cur_up_tech_gid", _old, cur_up_tech_gid);
        return this;
    }

    public Player_buildings changeCur_up_tech_gid(int cur_up_tech_gid){
        return setCur_up_tech_gid(this.cur_up_tech_gid + cur_up_tech_gid);
    }

    public Player_buildings changeCur_up_tech_gidWithMin(int cur_up_tech_gid, int _min){
        int _v2 = this.cur_up_tech_gid + cur_up_tech_gid;
        return setCur_up_tech_gid(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeCur_up_tech_gidWithMax(int cur_up_tech_gid, int _max){
        int _v2 = this.cur_up_tech_gid + cur_up_tech_gid;
        return setCur_up_tech_gid(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeCur_up_tech_gidWithMinMax(int cur_up_tech_gid, int _min, int _max){
        int _v2 = this.cur_up_tech_gid + cur_up_tech_gid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCur_up_tech_gid(_v2 < _min  ? _min : _v2);
    }

    public long getEnd_tech_up_time(){
        return end_tech_up_time;
    }

    public Player_buildings setEnd_tech_up_time(long end_tech_up_time){
        long _old = end_tech_up_time;
        this.end_tech_up_time = end_tech_up_time;
        changeIt("end_tech_up_time", _old, end_tech_up_time);
        return this;
    }

    public Player_buildings changeEnd_tech_up_time(long end_tech_up_time){
        return setEnd_tech_up_time(this.end_tech_up_time + end_tech_up_time);
    }

    public Player_buildings changeEnd_tech_up_timeWithMin(long end_tech_up_time, long _min){
        long _v2 = this.end_tech_up_time + end_tech_up_time;
        return setEnd_tech_up_time(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeEnd_tech_up_timeWithMax(long end_tech_up_time, long _max){
        long _v2 = this.end_tech_up_time + end_tech_up_time;
        return setEnd_tech_up_time(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeEnd_tech_up_timeWithMinMax(long end_tech_up_time, long _min, long _max){
        long _v2 = this.end_tech_up_time + end_tech_up_time;
        _v2 = _v2 > _max  ? _max : _v2;
        return setEnd_tech_up_time(_v2 < _min  ? _min : _v2);
    }

    public long getBegin_army_time(){
        return begin_army_time;
    }

    public Player_buildings setBegin_army_time(long begin_army_time){
        long _old = begin_army_time;
        this.begin_army_time = begin_army_time;
        changeIt("begin_army_time", _old, begin_army_time);
        return this;
    }

    public Player_buildings changeBegin_army_time(long begin_army_time){
        return setBegin_army_time(this.begin_army_time + begin_army_time);
    }

    public Player_buildings changeBegin_army_timeWithMin(long begin_army_time, long _min){
        long _v2 = this.begin_army_time + begin_army_time;
        return setBegin_army_time(_v2 < _min  ? _min : _v2);
    }

    public Player_buildings changeBegin_army_timeWithMax(long begin_army_time, long _max){
        long _v2 = this.begin_army_time + begin_army_time;
        return setBegin_army_time(_v2 > _max  ? _max : _v2);
    }

    public Player_buildings changeBegin_army_timeWithMinMax(long begin_army_time, long _min, long _max){
        long _v2 = this.begin_army_time + begin_army_time;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBegin_army_time(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Player_buildings v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Player_buildings v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Player_buildings newPlayer_buildings(Integer id, Integer bcid, String building_name, Integer pcid, String player_name, Integer gid, Integer lvl, Long cooldown_ms, Integer position_index, Integer state, Integer type, Long cur_produce_res, Long res_produce_begin_time, Integer cur_up_tech_gid, Long end_tech_up_time, Long begin_army_time) {
        Player_buildings result = new Player_buildings();
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
        result.cur_produce_res = cur_produce_res;
        result.res_produce_begin_time = res_produce_begin_time;
        result.cur_up_tech_gid = cur_up_tech_gid;
        result.end_tech_up_time = end_tech_up_time;
        result.begin_army_time = begin_army_time;
        return result;
    }

    public static Player_buildings newPlayer_buildings(Player_buildings player_buildings) {
        Player_buildings result = new Player_buildings();
        result.id = player_buildings.id;
        result.bcid = player_buildings.bcid;
        result.building_name = player_buildings.building_name;
        result.pcid = player_buildings.pcid;
        result.player_name = player_buildings.player_name;
        result.gid = player_buildings.gid;
        result.lvl = player_buildings.lvl;
        result.cooldown_ms = player_buildings.cooldown_ms;
        result.position_index = player_buildings.position_index;
        result.state = player_buildings.state;
        result.type = player_buildings.type;
        result.cur_produce_res = player_buildings.cur_produce_res;
        result.res_produce_begin_time = player_buildings.res_produce_begin_time;
        result.cur_up_tech_gid = player_buildings.cur_up_tech_gid;
        result.end_tech_up_time = player_buildings.end_tech_up_time;
        result.begin_army_time = player_buildings.begin_army_time;
        return result;
    }

    public Player_buildings createFor(ResultSet rs) throws SQLException {
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
        B2OutputStream.writeObject(_out, cur_produce_res);
        B2OutputStream.writeObject(_out, res_produce_begin_time);
        B2OutputStream.writeObject(_out, cur_up_tech_gid);
        B2OutputStream.writeObject(_out, end_tech_up_time);
        B2OutputStream.writeObject(_out, begin_army_time);
    }

    public static final Player_buildings readObject(InputStream _in) throws Exception {
        Player_buildings result = new Player_buildings();
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
        result.cur_produce_res = (Long) B2InputStream.readObject(_in);
        result.res_produce_begin_time = (Long) B2InputStream.readObject(_in);
        result.cur_up_tech_gid = (Integer) B2InputStream.readObject(_in);
        result.end_tech_up_time = (Long) B2InputStream.readObject(_in);
        result.begin_army_time = (Long) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getPlayer_buildings(){
        Player_buildings player_buildings = null; // player_buildings
        { // new and insert Player_buildings (player_buildings)
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
            long cur_produce_res = 0; 	// cur_produce_res
            long res_produce_begin_time = 0; 	// res_produce_begin_time
            int cur_up_tech_gid = 0; 	// cur_up_tech_gid
            long end_tech_up_time = 0; 	// end_tech_up_time
            long begin_army_time = 0; 	// begin_army_time
            player_buildings = Player_buildings.newPlayer_buildings(id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time);
        }
        player_buildings = player_buildings.insert();

        int id = player_buildings.getId(); 	// id
        int bcid = player_buildings.getBcid(); 	// bcid
        String building_name = player_buildings.getBuilding_name(); 	// building_name
        int pcid = player_buildings.getPcid(); 	// pcid
        String player_name = player_buildings.getPlayer_name(); 	// player_name
        int gid = player_buildings.getGid(); 	// gid
        int lvl = player_buildings.getLvl(); 	// lvl
        long cooldown_ms = player_buildings.getCooldown_ms(); 	// cooldown_ms
        int position_index = player_buildings.getPosition_index(); 	// position_index
        int state = player_buildings.getState(); 	// state
        int type = player_buildings.getType(); 	// type
        long cur_produce_res = player_buildings.getCur_produce_res(); 	// cur_produce_res
        long res_produce_begin_time = player_buildings.getRes_produce_begin_time(); 	// res_produce_begin_time
        int cur_up_tech_gid = player_buildings.getCur_up_tech_gid(); 	// cur_up_tech_gid
        long end_tech_up_time = player_buildings.getEnd_tech_up_time(); 	// end_tech_up_time
        long begin_army_time = player_buildings.getBegin_army_time(); 	// begin_army_time
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
        case "cur_up_tech_gid":
            return cur_up_tech_gid;
        }
        return 0;
    }

    public Player_buildings setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Player_buildings setInt(String fieldEn, int value2) {
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
        case "cur_up_tech_gid":
            return setCur_up_tech_gid(value2);
        }
        return this;
    }

    public Player_buildings changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Player_buildings changeInt(String fieldEn, int value2) {
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
        case "cur_up_tech_gid":
            return changeCur_up_tech_gid(value2);
        }
        return this;
    }

    public Player_buildings changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Player_buildings changeIntWithMin(String fieldEn, int value2, int _min) {
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
        case "cur_up_tech_gid":
            return changeCur_up_tech_gidWithMin(value2, _min);
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

    public Player_buildings setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Player_buildings setDouble(String fieldEn, double value2) {
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
        case "cur_produce_res":
            return cur_produce_res;
        case "res_produce_begin_time":
            return res_produce_begin_time;
        case "cur_up_tech_gid":
            return cur_up_tech_gid;
        case "end_tech_up_time":
            return end_tech_up_time;
        case "begin_army_time":
            return begin_army_time;
        }
        return null;
    }

    public Player_buildings setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Player_buildings setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "building_name":
            return setBuilding_name(value2);
        case "player_name":
            return setPlayer_name(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Player_buildings setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Player_buildings setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Player_buildings");
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
        result.put("cur_produce_res", cur_produce_res);
        result.put("res_produce_begin_time", res_produce_begin_time);
        result.put("cur_up_tech_gid", cur_up_tech_gid);
        result.put("end_tech_up_time", end_tech_up_time);
        result.put("begin_army_time", begin_army_time);
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
        result.put("cur_produce_res", cur_produce_res);
        result.put("res_produce_begin_time", res_produce_begin_time);
        result.put("cur_up_tech_gid", cur_up_tech_gid);
        result.put("end_tech_up_time", end_tech_up_time);
        result.put("begin_army_time", begin_army_time);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Player_buildings");
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
        result.put("cur_produce_res", cur_produce_res);
        result.put("res_produce_begin_time", res_produce_begin_time);
        result.put("cur_up_tech_gid", cur_up_tech_gid);
        result.put("end_tech_up_time", end_tech_up_time);
        result.put("begin_army_time", begin_army_time);
        return result;
    }

    public Player_buildings mapToObject(Map e){
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
        Long cur_produce_res = (Long)e.get("cur_produce_res");
        Long res_produce_begin_time = (Long)e.get("res_produce_begin_time");
        Integer cur_up_tech_gid = (Integer)e.get("cur_up_tech_gid");
        Long end_tech_up_time = (Long)e.get("end_tech_up_time");
        Long begin_army_time = (Long)e.get("begin_army_time");

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
        if(cur_produce_res == null) cur_produce_res = 0L;
        if(res_produce_begin_time == null) res_produce_begin_time = 0L;
        if(cur_up_tech_gid == null) cur_up_tech_gid = 0;
        if(end_tech_up_time == null) end_tech_up_time = 0L;
        if(begin_army_time == null) begin_army_time = 0L;

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
        setCur_produce_res(cur_produce_res);
        setRes_produce_begin_time(res_produce_begin_time);
        setCur_up_tech_gid(cur_up_tech_gid);
        setEnd_tech_up_time(end_tech_up_time);
        setBegin_army_time(begin_army_time);

        return this;
    }

    public static final Player_buildings mapTo(Map e){
        Player_buildings result = new Player_buildings();

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
        Long cur_produce_res = (Long)e.get("cur_produce_res");
        Long res_produce_begin_time = (Long)e.get("res_produce_begin_time");
        Integer cur_up_tech_gid = (Integer)e.get("cur_up_tech_gid");
        Long end_tech_up_time = (Long)e.get("end_tech_up_time");
        Long begin_army_time = (Long)e.get("begin_army_time");

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
        if(cur_produce_res == null) cur_produce_res = 0L;
        if(res_produce_begin_time == null) res_produce_begin_time = 0L;
        if(cur_up_tech_gid == null) cur_up_tech_gid = 0;
        if(end_tech_up_time == null) end_tech_up_time = 0L;
        if(begin_army_time == null) begin_army_time = 0L;

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
        result.cur_produce_res = cur_produce_res;
        result.res_produce_begin_time = res_produce_begin_time;
        result.cur_up_tech_gid = cur_up_tech_gid;
        result.end_tech_up_time = end_tech_up_time;
        result.begin_army_time = begin_army_time;

        return result;
    }

    public static final Player_buildings originalTo(Map e){
        Player_buildings result = new Player_buildings();

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
        Long cur_produce_res = (Long)e.get("cur_produce_res");
        Long res_produce_begin_time = (Long)e.get("res_produce_begin_time");
        Integer cur_up_tech_gid = (Integer)e.get("cur_up_tech_gid");
        Long end_tech_up_time = (Long)e.get("end_tech_up_time");
        Long begin_army_time = (Long)e.get("begin_army_time");

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
        if(cur_produce_res == null) cur_produce_res = 0L;
        if(res_produce_begin_time == null) res_produce_begin_time = 0L;
        if(cur_up_tech_gid == null) cur_up_tech_gid = 0;
        if(end_tech_up_time == null) end_tech_up_time = 0L;
        if(begin_army_time == null) begin_army_time = 0L;

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
        result.cur_produce_res = cur_produce_res;
        result.res_produce_begin_time = res_produce_begin_time;
        result.cur_up_tech_gid = cur_up_tech_gid;
        result.end_tech_up_time = end_tech_up_time;
        result.begin_army_time = begin_army_time;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Player_buildings createFor(byte[] b) throws Exception {
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
    public static final Player_buildings loadByKey(int id) {
        return Player_buildingsEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Player_buildings insert() {
        Player_buildings result = Player_buildingsEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Player_buildings asyncInsert() {
        Player_buildings result = Player_buildingsEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Player_buildings insert2() {
        return Player_buildingsEntity.insert2(this);
    }

    public final Player_buildings asyncInsert2() {
        Player_buildings result = Player_buildingsEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Player_buildings update() {
        return Player_buildingsEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Player_buildingsEntity.asynchronousUpdate( this );
        return true;
    }

    public final Player_buildings insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Player_buildingsEntity.delete(id);
    }

    public final void asyncDelete() {
        Player_buildingsEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Player_buildings clone2() {
        try{
            return (Player_buildings) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
