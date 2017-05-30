package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - servers
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Servers extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -258213610; // com.sea.db.bean.Servers

    public static String TABLENAME = "servers";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "sname", "svip", "port", "status", "version"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR", "INT", "INT", "VARCHAR"};


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
    public String sname;
    public String svip;
    public int port;
    public int status;
    public String version;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Servers setId(int id){
        this.id = id;
        return this;
    }

    public String getSname(){
        return sname;
    }

    public Servers setSname(String sname){
        String _old = sname;
        this.sname = sname;
        changeIt("sname", _old, sname);
        return this;
    }

    public String getSvip(){
        return svip;
    }

    public Servers setSvip(String svip){
        String _old = svip;
        this.svip = svip;
        changeIt("svip", _old, svip);
        return this;
    }

    public int getPort(){
        return port;
    }

    public Servers setPort(int port){
        int _old = port;
        this.port = port;
        changeIt("port", _old, port);
        return this;
    }

    public Servers changePort(int port){
        return setPort(this.port + port);
    }

    public Servers changePortWithMin(int port, int _min){
        int _v2 = this.port + port;
        return setPort(_v2 < _min  ? _min : _v2);
    }

    public Servers changePortWithMax(int port, int _max){
        int _v2 = this.port + port;
        return setPort(_v2 > _max  ? _max : _v2);
    }

    public Servers changePortWithMinMax(int port, int _min, int _max){
        int _v2 = this.port + port;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPort(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Servers setStatus(int status){
        int _old = status;
        this.status = status;
        changeIt("status", _old, status);
        return this;
    }

    public Servers changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Servers changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Servers changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Servers changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public String getVersion(){
        return version;
    }

    public Servers setVersion(String version){
        String _old = version;
        this.version = version;
        changeIt("version", _old, version);
        return this;
    }

    public int compareTo(Servers v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Servers v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Servers newServers(Integer id, String sname, String svip, Integer port, Integer status, String version) {
        Servers result = new Servers();
        result.id = id;
        result.sname = sname;
        result.svip = svip;
        result.port = port;
        result.status = status;
        result.version = version;
        return result;
    }

    public static Servers newServers(Servers servers) {
        Servers result = new Servers();
        result.id = servers.id;
        result.sname = servers.sname;
        result.svip = servers.svip;
        result.port = servers.port;
        result.status = servers.status;
        result.version = servers.version;
        return result;
    }

    public Servers createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, sname);
        B2OutputStream.writeObject(_out, svip);
        B2OutputStream.writeObject(_out, port);
        B2OutputStream.writeObject(_out, status);
        B2OutputStream.writeObject(_out, version);
    }

    public static final Servers readObject(InputStream _in) throws Exception {
        Servers result = new Servers();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.sname = (String) B2InputStream.readObject(_in);
        result.svip = (String) B2InputStream.readObject(_in);
        result.port = (Integer) B2InputStream.readObject(_in);
        result.status = (Integer) B2InputStream.readObject(_in);
        result.version = (String) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getServers(){
        Servers servers = null; // servers
        { // new and insert Servers (servers)
            int id = 0; 	// id
            String sname = ""; 	// sname
            String svip = ""; 	// svip
            int port = 0; 	// port
            int status = 0; 	// status
            String version = ""; 	// version
            servers = Servers.newServers(id, sname, svip, port, status, version);
        }
        servers = servers.insert();

        int id = servers.getId(); 	// id
        String sname = servers.getSname(); 	// sname
        String svip = servers.getSvip(); 	// svip
        int port = servers.getPort(); 	// port
        int status = servers.getStatus(); 	// status
        String version = servers.getVersion(); 	// version
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
        case "port":
            return port;
        case "status":
            return status;
        }
        return 0;
    }

    public Servers setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Servers setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "port":
            return setPort(value2);
        case "status":
            return setStatus(value2);
        }
        return this;
    }

    public Servers changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Servers changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "port":
            return changePort(value2);
        case "status":
            return changeStatus(value2);
        }
        return this;
    }

    public Servers changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Servers changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "port":
            return changePortWithMin(value2, _min);
        case "status":
            return changeStatusWithMin(value2, _min);
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

    public Servers setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Servers setDouble(String fieldEn, double value2) {
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
        case "sname": 
            return sname;
        case "svip": 
            return svip;
        case "version": 
            return version;
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
        case "sname":
            return sname;
        case "svip":
            return svip;
        case "port":
            return port;
        case "status":
            return status;
        case "version":
            return version;
        }
        return null;
    }

    public Servers setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Servers setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "sname":
            return setSname(value2);
        case "svip":
            return setSvip(value2);
        case "version":
            return setVersion(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Servers setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Servers setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Servers");
        result.put("id", id);
        result.put("sname", sname);
        result.put("svip", svip);
        result.put("port", port);
        result.put("status", status);
        result.put("version", version);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("sname", sname);
        result.put("svip", svip);
        result.put("port", port);
        result.put("status", status);
        result.put("version", version);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Servers");
        result.put("id", id);
        result.put("sname", sname);
        result.put("svip", svip);
        result.put("port", port);
        result.put("status", status);
        result.put("version", version);
        return result;
    }

    public Servers mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String sname = (String)e.get("sname");
        String svip = (String)e.get("svip");
        Integer port = (Integer)e.get("port");
        Integer status = (Integer)e.get("status");
        String version = (String)e.get("version");

        if(id == null) id = 0;
        if(sname == null) sname = "";
        if(svip == null) svip = "";
        if(port == null) port = 0;
        if(status == null) status = 0;
        if(version == null) version = "";

        setId(id);
        setSname(sname);
        setSvip(svip);
        setPort(port);
        setStatus(status);
        setVersion(version);

        return this;
    }

    public static final Servers mapTo(Map e){
        Servers result = new Servers();

        Integer id = (Integer)e.get("id");
        String sname = (String)e.get("sname");
        String svip = (String)e.get("svip");
        Integer port = (Integer)e.get("port");
        Integer status = (Integer)e.get("status");
        String version = (String)e.get("version");

        if(id == null) id = 0;
        if(sname == null) sname = "";
        if(svip == null) svip = "";
        if(port == null) port = 0;
        if(status == null) status = 0;
        if(version == null) version = "";

        result.id = id;
        result.sname = sname;
        result.svip = svip;
        result.port = port;
        result.status = status;
        result.version = version;

        return result;
    }

    public static final Servers originalTo(Map e){
        Servers result = new Servers();

        Integer id = (Integer)e.get("id");
        String sname = (String)e.get("sname");
        String svip = (String)e.get("svip");
        Integer port = (Integer)e.get("port");
        Integer status = (Integer)e.get("status");
        String version = (String)e.get("version");

        if(id == null) id = 0;
        if(sname == null) sname = "";
        if(svip == null) svip = "";
        if(port == null) port = 0;
        if(status == null) status = 0;
        if(version == null) version = "";

        result.id = id;
        result.sname = sname;
        result.svip = svip;
        result.port = port;
        result.status = status;
        result.version = version;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Servers createFor(byte[] b) throws Exception {
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
    public static final Servers loadByKey(int id) {
        return ServersEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Servers insert() {
        Servers result = ServersEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Servers asyncInsert() {
        Servers result = ServersEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Servers insert2() {
        return ServersEntity.insert2(this);
    }

    public final Servers asyncInsert2() {
        Servers result = ServersEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Servers update() {
        return ServersEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        ServersEntity.asynchronousUpdate( this );
        return true;
    }

    public final Servers insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return ServersEntity.delete(id);
    }

    public final void asyncDelete() {
        ServersEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Servers clone2() {
        try{
            return (Servers) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
