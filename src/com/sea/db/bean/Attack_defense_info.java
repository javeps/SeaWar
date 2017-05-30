package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - attack_defense_info
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Attack_defense_info extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 678465130; // com.sea.db.bean.Attack_defense_info

    public static String TABLENAME = "attack_defense_info";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "attmcid", "builds", "obsts", "amrys", "teches", "heros"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT"};


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
    public String attmcid;
    public String builds;
    public String obsts;
    public String amrys;
    public String teches;
    public String heros;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Attack_defense_info setId(int id){
        this.id = id;
        return this;
    }

    public String getAttmcid(){
        return attmcid;
    }

    public Attack_defense_info setAttmcid(String attmcid){
        String _old = attmcid;
        this.attmcid = attmcid;
        changeIt("attmcid", _old, attmcid);
        return this;
    }

    public String getBuilds(){
        return builds;
    }

    public Attack_defense_info setBuilds(String builds){
        String _old = builds;
        this.builds = builds;
        changeIt("builds", _old, builds);
        return this;
    }

    public String getObsts(){
        return obsts;
    }

    public Attack_defense_info setObsts(String obsts){
        String _old = obsts;
        this.obsts = obsts;
        changeIt("obsts", _old, obsts);
        return this;
    }

    public String getAmrys(){
        return amrys;
    }

    public Attack_defense_info setAmrys(String amrys){
        String _old = amrys;
        this.amrys = amrys;
        changeIt("amrys", _old, amrys);
        return this;
    }

    public String getTeches(){
        return teches;
    }

    public Attack_defense_info setTeches(String teches){
        String _old = teches;
        this.teches = teches;
        changeIt("teches", _old, teches);
        return this;
    }

    public String getHeros(){
        return heros;
    }

    public Attack_defense_info setHeros(String heros){
        String _old = heros;
        this.heros = heros;
        changeIt("heros", _old, heros);
        return this;
    }

    public int compareTo(Attack_defense_info v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Attack_defense_info v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Attack_defense_info newAttack_defense_info(Integer id, String attmcid, String builds, String obsts, String amrys, String teches, String heros) {
        Attack_defense_info result = new Attack_defense_info();
        result.id = id;
        result.attmcid = attmcid;
        result.builds = builds;
        result.obsts = obsts;
        result.amrys = amrys;
        result.teches = teches;
        result.heros = heros;
        return result;
    }

    public static Attack_defense_info newAttack_defense_info(Attack_defense_info attack_defense_info) {
        Attack_defense_info result = new Attack_defense_info();
        result.id = attack_defense_info.id;
        result.attmcid = attack_defense_info.attmcid;
        result.builds = attack_defense_info.builds;
        result.obsts = attack_defense_info.obsts;
        result.amrys = attack_defense_info.amrys;
        result.teches = attack_defense_info.teches;
        result.heros = attack_defense_info.heros;
        return result;
    }

    public Attack_defense_info createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, attmcid);
        B2OutputStream.writeObject(_out, builds);
        B2OutputStream.writeObject(_out, obsts);
        B2OutputStream.writeObject(_out, amrys);
        B2OutputStream.writeObject(_out, teches);
        B2OutputStream.writeObject(_out, heros);
    }

    public static final Attack_defense_info readObject(InputStream _in) throws Exception {
        Attack_defense_info result = new Attack_defense_info();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.attmcid = (String) B2InputStream.readObject(_in);
        result.builds = (String) B2InputStream.readObject(_in);
        result.obsts = (String) B2InputStream.readObject(_in);
        result.amrys = (String) B2InputStream.readObject(_in);
        result.teches = (String) B2InputStream.readObject(_in);
        result.heros = (String) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getAttack_defense_info(){
        Attack_defense_info attack_defense_info = null; // attack_defense_info
        { // new and insert Attack_defense_info (attack_defense_info)
            int id = 0; 	// id
            String attmcid = ""; 	// attmcid
            String builds = ""; 	// builds
            String obsts = ""; 	// obsts
            String amrys = ""; 	// amrys
            String teches = ""; 	// teches
            String heros = ""; 	// heros
            attack_defense_info = Attack_defense_info.newAttack_defense_info(id, attmcid, builds, obsts, amrys, teches, heros);
        }
        attack_defense_info = attack_defense_info.insert();

        int id = attack_defense_info.getId(); 	// id
        String attmcid = attack_defense_info.getAttmcid(); 	// attmcid
        String builds = attack_defense_info.getBuilds(); 	// builds
        String obsts = attack_defense_info.getObsts(); 	// obsts
        String amrys = attack_defense_info.getAmrys(); 	// amrys
        String teches = attack_defense_info.getTeches(); 	// teches
        String heros = attack_defense_info.getHeros(); 	// heros
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
        }
        return 0;
    }

    public Attack_defense_info setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Attack_defense_info setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        }
        return this;
    }

    public Attack_defense_info changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Attack_defense_info changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        }
        return this;
    }

    public Attack_defense_info changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Attack_defense_info changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
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

    public Attack_defense_info setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Attack_defense_info setDouble(String fieldEn, double value2) {
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
        case "attmcid": 
            return attmcid;
        case "builds": 
            return builds;
        case "obsts": 
            return obsts;
        case "amrys": 
            return amrys;
        case "teches": 
            return teches;
        case "heros": 
            return heros;
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
        case "attmcid":
            return attmcid;
        case "builds":
            return builds;
        case "obsts":
            return obsts;
        case "amrys":
            return amrys;
        case "teches":
            return teches;
        case "heros":
            return heros;
        }
        return null;
    }

    public Attack_defense_info setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Attack_defense_info setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "attmcid":
            return setAttmcid(value2);
        case "builds":
            return setBuilds(value2);
        case "obsts":
            return setObsts(value2);
        case "amrys":
            return setAmrys(value2);
        case "teches":
            return setTeches(value2);
        case "heros":
            return setHeros(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Attack_defense_info setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Attack_defense_info setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Attack_defense_info");
        result.put("id", id);
        result.put("attmcid", attmcid);
        result.put("builds", builds);
        result.put("obsts", obsts);
        result.put("amrys", amrys);
        result.put("teches", teches);
        result.put("heros", heros);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("attmcid", attmcid);
        result.put("builds", builds);
        result.put("obsts", obsts);
        result.put("amrys", amrys);
        result.put("teches", teches);
        result.put("heros", heros);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Attack_defense_info");
        result.put("id", id);
        result.put("attmcid", attmcid);
        result.put("builds", builds);
        result.put("obsts", obsts);
        result.put("amrys", amrys);
        result.put("teches", teches);
        result.put("heros", heros);
        return result;
    }

    public Attack_defense_info mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String attmcid = (String)e.get("attmcid");
        String builds = (String)e.get("builds");
        String obsts = (String)e.get("obsts");
        String amrys = (String)e.get("amrys");
        String teches = (String)e.get("teches");
        String heros = (String)e.get("heros");

        if(id == null) id = 0;
        if(attmcid == null) attmcid = "";
        if(builds == null) builds = "";
        if(obsts == null) obsts = "";
        if(amrys == null) amrys = "";
        if(teches == null) teches = "";
        if(heros == null) heros = "";

        setId(id);
        setAttmcid(attmcid);
        setBuilds(builds);
        setObsts(obsts);
        setAmrys(amrys);
        setTeches(teches);
        setHeros(heros);

        return this;
    }

    public static final Attack_defense_info mapTo(Map e){
        Attack_defense_info result = new Attack_defense_info();

        Integer id = (Integer)e.get("id");
        String attmcid = (String)e.get("attmcid");
        String builds = (String)e.get("builds");
        String obsts = (String)e.get("obsts");
        String amrys = (String)e.get("amrys");
        String teches = (String)e.get("teches");
        String heros = (String)e.get("heros");

        if(id == null) id = 0;
        if(attmcid == null) attmcid = "";
        if(builds == null) builds = "";
        if(obsts == null) obsts = "";
        if(amrys == null) amrys = "";
        if(teches == null) teches = "";
        if(heros == null) heros = "";

        result.id = id;
        result.attmcid = attmcid;
        result.builds = builds;
        result.obsts = obsts;
        result.amrys = amrys;
        result.teches = teches;
        result.heros = heros;

        return result;
    }

    public static final Attack_defense_info originalTo(Map e){
        Attack_defense_info result = new Attack_defense_info();

        Integer id = (Integer)e.get("id");
        String attmcid = (String)e.get("attmcid");
        String builds = (String)e.get("builds");
        String obsts = (String)e.get("obsts");
        String amrys = (String)e.get("amrys");
        String teches = (String)e.get("teches");
        String heros = (String)e.get("heros");

        if(id == null) id = 0;
        if(attmcid == null) attmcid = "";
        if(builds == null) builds = "";
        if(obsts == null) obsts = "";
        if(amrys == null) amrys = "";
        if(teches == null) teches = "";
        if(heros == null) heros = "";

        result.id = id;
        result.attmcid = attmcid;
        result.builds = builds;
        result.obsts = obsts;
        result.amrys = amrys;
        result.teches = teches;
        result.heros = heros;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Attack_defense_info createFor(byte[] b) throws Exception {
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
    public static final Attack_defense_info loadByKey(int id) {
        return Attack_defense_infoEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Attack_defense_info insert() {
        Attack_defense_info result = Attack_defense_infoEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Attack_defense_info asyncInsert() {
        Attack_defense_info result = Attack_defense_infoEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Attack_defense_info insert2() {
        return Attack_defense_infoEntity.insert2(this);
    }

    public final Attack_defense_info asyncInsert2() {
        Attack_defense_info result = Attack_defense_infoEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Attack_defense_info update() {
        return Attack_defense_infoEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Attack_defense_infoEntity.asynchronousUpdate( this );
        return true;
    }

    public final Attack_defense_info insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Attack_defense_infoEntity.delete(id);
    }

    public final void asyncDelete() {
        Attack_defense_infoEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Attack_defense_info clone2() {
        try{
            return (Attack_defense_info) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
