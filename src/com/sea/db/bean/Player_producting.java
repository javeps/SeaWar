package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - player_producting
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Player_producting extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 369480887; // com.sea.db.bean.Player_producting

    public static String TABLENAME = "player_producting";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "gid", "num", "bcid", "pcid"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "INT", "INT"};


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
    public int gid;
    public int num;
    public int bcid;
    public int pcid;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Player_producting setId(int id){
        this.id = id;
        return this;
    }

    public int getGid(){
        return gid;
    }

    public Player_producting setGid(int gid){
        int _old = gid;
        this.gid = gid;
        changeIt("gid", _old, gid);
        return this;
    }

    public Player_producting changeGid(int gid){
        return setGid(this.gid + gid);
    }

    public Player_producting changeGidWithMin(int gid, int _min){
        int _v2 = this.gid + gid;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public Player_producting changeGidWithMax(int gid, int _max){
        int _v2 = this.gid + gid;
        return setGid(_v2 > _max  ? _max : _v2);
    }

    public Player_producting changeGidWithMinMax(int gid, int _min, int _max){
        int _v2 = this.gid + gid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setGid(_v2 < _min  ? _min : _v2);
    }

    public int getNum(){
        return num;
    }

    public Player_producting setNum(int num){
        int _old = num;
        this.num = num;
        changeIt("num", _old, num);
        return this;
    }

    public Player_producting changeNum(int num){
        return setNum(this.num + num);
    }

    public Player_producting changeNumWithMin(int num, int _min){
        int _v2 = this.num + num;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public Player_producting changeNumWithMax(int num, int _max){
        int _v2 = this.num + num;
        return setNum(_v2 > _max  ? _max : _v2);
    }

    public Player_producting changeNumWithMinMax(int num, int _min, int _max){
        int _v2 = this.num + num;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public int getBcid(){
        return bcid;
    }

    public Player_producting setBcid(int bcid){
        int _old = bcid;
        this.bcid = bcid;
        changeIt("bcid", _old, bcid);
        return this;
    }

    public Player_producting changeBcid(int bcid){
        return setBcid(this.bcid + bcid);
    }

    public Player_producting changeBcidWithMin(int bcid, int _min){
        int _v2 = this.bcid + bcid;
        return setBcid(_v2 < _min  ? _min : _v2);
    }

    public Player_producting changeBcidWithMax(int bcid, int _max){
        int _v2 = this.bcid + bcid;
        return setBcid(_v2 > _max  ? _max : _v2);
    }

    public Player_producting changeBcidWithMinMax(int bcid, int _min, int _max){
        int _v2 = this.bcid + bcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBcid(_v2 < _min  ? _min : _v2);
    }

    public int getPcid(){
        return pcid;
    }

    public Player_producting setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Player_producting changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Player_producting changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Player_producting changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Player_producting changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Player_producting v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Player_producting v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Player_producting newPlayer_producting(Integer id, Integer gid, Integer num, Integer bcid, Integer pcid) {
        Player_producting result = new Player_producting();
        result.id = id;
        result.gid = gid;
        result.num = num;
        result.bcid = bcid;
        result.pcid = pcid;
        return result;
    }

    public static Player_producting newPlayer_producting(Player_producting player_producting) {
        Player_producting result = new Player_producting();
        result.id = player_producting.id;
        result.gid = player_producting.gid;
        result.num = player_producting.num;
        result.bcid = player_producting.bcid;
        result.pcid = player_producting.pcid;
        return result;
    }

    public Player_producting createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, gid);
        B2OutputStream.writeObject(_out, num);
        B2OutputStream.writeObject(_out, bcid);
        B2OutputStream.writeObject(_out, pcid);
    }

    public static final Player_producting readObject(InputStream _in) throws Exception {
        Player_producting result = new Player_producting();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.gid = (Integer) B2InputStream.readObject(_in);
        result.num = (Integer) B2InputStream.readObject(_in);
        result.bcid = (Integer) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getPlayer_producting(){
        Player_producting player_producting = null; // player_producting
        { // new and insert Player_producting (player_producting)
            int id = 0; 	// id
            int gid = 0; 	// gid
            int num = 0; 	// num
            int bcid = 0; 	// bcid
            int pcid = 0; 	// pcid
            player_producting = Player_producting.newPlayer_producting(id, gid, num, bcid, pcid);
        }
        player_producting = player_producting.insert();

        int id = player_producting.getId(); 	// id
        int gid = player_producting.getGid(); 	// gid
        int num = player_producting.getNum(); 	// num
        int bcid = player_producting.getBcid(); 	// bcid
        int pcid = player_producting.getPcid(); 	// pcid
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
        case "gid":
            return gid;
        case "num":
            return num;
        case "bcid":
            return bcid;
        case "pcid":
            return pcid;
        }
        return 0;
    }

    public Player_producting setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Player_producting setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "gid":
            return setGid(value2);
        case "num":
            return setNum(value2);
        case "bcid":
            return setBcid(value2);
        case "pcid":
            return setPcid(value2);
        }
        return this;
    }

    public Player_producting changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Player_producting changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "gid":
            return changeGid(value2);
        case "num":
            return changeNum(value2);
        case "bcid":
            return changeBcid(value2);
        case "pcid":
            return changePcid(value2);
        }
        return this;
    }

    public Player_producting changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Player_producting changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "gid":
            return changeGidWithMin(value2, _min);
        case "num":
            return changeNumWithMin(value2, _min);
        case "bcid":
            return changeBcidWithMin(value2, _min);
        case "pcid":
            return changePcidWithMin(value2, _min);
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

    public Player_producting setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Player_producting setDouble(String fieldEn, double value2) {
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
        case "gid":
            return gid;
        case "num":
            return num;
        case "bcid":
            return bcid;
        case "pcid":
            return pcid;
        }
        return null;
    }

    public Player_producting setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Player_producting setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Player_producting setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Player_producting setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Player_producting");
        result.put("id", id);
        result.put("gid", gid);
        result.put("num", num);
        result.put("bcid", bcid);
        result.put("pcid", pcid);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("gid", gid);
        result.put("num", num);
        result.put("bcid", bcid);
        result.put("pcid", pcid);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Player_producting");
        result.put("id", id);
        result.put("gid", gid);
        result.put("num", num);
        result.put("bcid", bcid);
        result.put("pcid", pcid);
        return result;
    }

    public Player_producting mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer gid = (Integer)e.get("gid");
        Integer num = (Integer)e.get("num");
        Integer bcid = (Integer)e.get("bcid");
        Integer pcid = (Integer)e.get("pcid");

        if(id == null) id = 0;
        if(gid == null) gid = 0;
        if(num == null) num = 0;
        if(bcid == null) bcid = 0;
        if(pcid == null) pcid = 0;

        setId(id);
        setGid(gid);
        setNum(num);
        setBcid(bcid);
        setPcid(pcid);

        return this;
    }

    public static final Player_producting mapTo(Map e){
        Player_producting result = new Player_producting();

        Integer id = (Integer)e.get("id");
        Integer gid = (Integer)e.get("gid");
        Integer num = (Integer)e.get("num");
        Integer bcid = (Integer)e.get("bcid");
        Integer pcid = (Integer)e.get("pcid");

        if(id == null) id = 0;
        if(gid == null) gid = 0;
        if(num == null) num = 0;
        if(bcid == null) bcid = 0;
        if(pcid == null) pcid = 0;

        result.id = id;
        result.gid = gid;
        result.num = num;
        result.bcid = bcid;
        result.pcid = pcid;

        return result;
    }

    public static final Player_producting originalTo(Map e){
        Player_producting result = new Player_producting();

        Integer id = (Integer)e.get("id");
        Integer gid = (Integer)e.get("gid");
        Integer num = (Integer)e.get("num");
        Integer bcid = (Integer)e.get("bcid");
        Integer pcid = (Integer)e.get("pcid");

        if(id == null) id = 0;
        if(gid == null) gid = 0;
        if(num == null) num = 0;
        if(bcid == null) bcid = 0;
        if(pcid == null) pcid = 0;

        result.id = id;
        result.gid = gid;
        result.num = num;
        result.bcid = bcid;
        result.pcid = pcid;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Player_producting createFor(byte[] b) throws Exception {
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
    public static final Player_producting loadByKey(int id) {
        return Player_productingEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Player_producting insert() {
        Player_producting result = Player_productingEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Player_producting asyncInsert() {
        Player_producting result = Player_productingEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Player_producting insert2() {
        return Player_productingEntity.insert2(this);
    }

    public final Player_producting asyncInsert2() {
        Player_producting result = Player_productingEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Player_producting update() {
        return Player_productingEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Player_productingEntity.asynchronousUpdate( this );
        return true;
    }

    public final Player_producting insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Player_productingEntity.delete(id);
    }

    public final void asyncDelete() {
        Player_productingEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Player_producting clone2() {
        try{
            return (Player_producting) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
