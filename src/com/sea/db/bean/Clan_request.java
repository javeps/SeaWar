package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - clan_request
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Clan_request extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 2145978848; // com.sea.db.bean.Clan_request

    public static String TABLENAME = "clan_request";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "ccid", "pcid", "pname", "renown"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "INT", "VARCHAR", "INT"};


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
    public String ccid;
    public int pcid;
    public String pname;
    public int renown;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Clan_request setId(int id){
        this.id = id;
        return this;
    }

    public String getCcid(){
        return ccid;
    }

    public Clan_request setCcid(String ccid){
        String _old = ccid;
        this.ccid = ccid;
        changeIt("ccid", _old, ccid);
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Clan_request setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Clan_request changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Clan_request changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Clan_request changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Clan_request changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPname(){
        return pname;
    }

    public Clan_request setPname(String pname){
        String _old = pname;
        this.pname = pname;
        changeIt("pname", _old, pname);
        return this;
    }

    public int getRenown(){
        return renown;
    }

    public Clan_request setRenown(int renown){
        int _old = renown;
        this.renown = renown;
        changeIt("renown", _old, renown);
        return this;
    }

    public Clan_request changeRenown(int renown){
        return setRenown(this.renown + renown);
    }

    public Clan_request changeRenownWithMin(int renown, int _min){
        int _v2 = this.renown + renown;
        return setRenown(_v2 < _min  ? _min : _v2);
    }

    public Clan_request changeRenownWithMax(int renown, int _max){
        int _v2 = this.renown + renown;
        return setRenown(_v2 > _max  ? _max : _v2);
    }

    public Clan_request changeRenownWithMinMax(int renown, int _min, int _max){
        int _v2 = this.renown + renown;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRenown(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Clan_request v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Clan_request v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Clan_request newClan_request(Integer id, String ccid, Integer pcid, String pname, Integer renown) {
        Clan_request result = new Clan_request();
        result.id = id;
        result.ccid = ccid;
        result.pcid = pcid;
        result.pname = pname;
        result.renown = renown;
        return result;
    }

    public static Clan_request newClan_request(Clan_request clan_request) {
        Clan_request result = new Clan_request();
        result.id = clan_request.id;
        result.ccid = clan_request.ccid;
        result.pcid = clan_request.pcid;
        result.pname = clan_request.pname;
        result.renown = clan_request.renown;
        return result;
    }

    public Clan_request createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, ccid);
        B2OutputStream.writeObject(_out, pcid);
        B2OutputStream.writeObject(_out, pname);
        B2OutputStream.writeObject(_out, renown);
    }

    public static final Clan_request readObject(InputStream _in) throws Exception {
        Clan_request result = new Clan_request();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.ccid = (String) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        result.pname = (String) B2InputStream.readObject(_in);
        result.renown = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getClan_request(){
        Clan_request clan_request = null; // clan_request
        { // new and insert Clan_request (clan_request)
            int id = 0; 	// id
            String ccid = ""; 	// ccid
            int pcid = 0; 	// pcid
            String pname = ""; 	// pname
            int renown = 0; 	// renown
            clan_request = Clan_request.newClan_request(id, ccid, pcid, pname, renown);
        }
        clan_request = clan_request.insert();

        int id = clan_request.getId(); 	// id
        String ccid = clan_request.getCcid(); 	// ccid
        int pcid = clan_request.getPcid(); 	// pcid
        String pname = clan_request.getPname(); 	// pname
        int renown = clan_request.getRenown(); 	// renown
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
        case "renown":
            return renown;
        }
        return 0;
    }

    public Clan_request setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Clan_request setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "pcid":
            return setPcid(value2);
        case "renown":
            return setRenown(value2);
        }
        return this;
    }

    public Clan_request changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Clan_request changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "pcid":
            return changePcid(value2);
        case "renown":
            return changeRenown(value2);
        }
        return this;
    }

    public Clan_request changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Clan_request changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "pcid":
            return changePcidWithMin(value2, _min);
        case "renown":
            return changeRenownWithMin(value2, _min);
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

    public Clan_request setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Clan_request setDouble(String fieldEn, double value2) {
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
        case "ccid": 
            return ccid;
        case "pname": 
            return pname;
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
        case "ccid":
            return ccid;
        case "pcid":
            return pcid;
        case "pname":
            return pname;
        case "renown":
            return renown;
        }
        return null;
    }

    public Clan_request setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Clan_request setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "ccid":
            return setCcid(value2);
        case "pname":
            return setPname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Clan_request setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Clan_request setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Clan_request");
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("renown", renown);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("renown", renown);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Clan_request");
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("renown", renown);
        return result;
    }

    public Clan_request mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer renown = (Integer)e.get("renown");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(renown == null) renown = 0;

        setId(id);
        setCcid(ccid);
        setPcid(pcid);
        setPname(pname);
        setRenown(renown);

        return this;
    }

    public static final Clan_request mapTo(Map e){
        Clan_request result = new Clan_request();

        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer renown = (Integer)e.get("renown");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(renown == null) renown = 0;

        result.id = id;
        result.ccid = ccid;
        result.pcid = pcid;
        result.pname = pname;
        result.renown = renown;

        return result;
    }

    public static final Clan_request originalTo(Map e){
        Clan_request result = new Clan_request();

        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer renown = (Integer)e.get("renown");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(renown == null) renown = 0;

        result.id = id;
        result.ccid = ccid;
        result.pcid = pcid;
        result.pname = pname;
        result.renown = renown;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Clan_request createFor(byte[] b) throws Exception {
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
    public static final Clan_request loadByKey(int id) {
        return Clan_requestEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Clan_request insert() {
        Clan_request result = Clan_requestEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Clan_request asyncInsert() {
        Clan_request result = Clan_requestEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Clan_request insert2() {
        return Clan_requestEntity.insert2(this);
    }

    public final Clan_request asyncInsert2() {
        Clan_request result = Clan_requestEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Clan_request update() {
        return Clan_requestEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Clan_requestEntity.asynchronousUpdate( this );
        return true;
    }

    public final Clan_request insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Clan_requestEntity.delete(id);
    }

    public final void asyncDelete() {
        Clan_requestEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Clan_request clone2() {
        try{
            return (Clan_request) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
