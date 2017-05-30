package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - user
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class User extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1751889403; // com.sea.db.bean.User

    public static String TABLENAME = "user";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "ucid", "login_pwd", "name", "login_uid", "uuid", "login_time", "model", "version", "remain", "pcids"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "BIGINT", "VARCHAR", "VARCHAR", "VARCHAR", "TEXT"};


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
    public int ucid;
    public String login_pwd;
    public String name;
    public String login_uid;
    public String uuid;
    public long login_time;
    public String model;
    public String version;
    public String remain;
    public String pcids;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public User setId(int id){
        this.id = id;
        return this;
    }

    public int getUcid(){
        return ucid;
    }

    public User setUcid(int ucid){
        int _old = ucid;
        this.ucid = ucid;
        changeIt("ucid", _old, ucid);
        return this;
    }

    public User changeUcid(int ucid){
        return setUcid(this.ucid + ucid);
    }

    public User changeUcidWithMin(int ucid, int _min){
        int _v2 = this.ucid + ucid;
        return setUcid(_v2 < _min  ? _min : _v2);
    }

    public User changeUcidWithMax(int ucid, int _max){
        int _v2 = this.ucid + ucid;
        return setUcid(_v2 > _max  ? _max : _v2);
    }

    public User changeUcidWithMinMax(int ucid, int _min, int _max){
        int _v2 = this.ucid + ucid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setUcid(_v2 < _min  ? _min : _v2);
    }

    public String getLogin_pwd(){
        return login_pwd;
    }

    public User setLogin_pwd(String login_pwd){
        String _old = login_pwd;
        this.login_pwd = login_pwd;
        changeIt("login_pwd", _old, login_pwd);
        return this;
    }

    public String getName(){
        return name;
    }

    public User setName(String name){
        String _old = name;
        this.name = name;
        changeIt("name", _old, name);
        return this;
    }

    public String getLogin_uid(){
        return login_uid;
    }

    public User setLogin_uid(String login_uid){
        String _old = login_uid;
        this.login_uid = login_uid;
        changeIt("login_uid", _old, login_uid);
        return this;
    }

    public String getUuid(){
        return uuid;
    }

    public User setUuid(String uuid){
        String _old = uuid;
        this.uuid = uuid;
        changeIt("uuid", _old, uuid);
        return this;
    }

    public long getLogin_time(){
        return login_time;
    }

    public User setLogin_time(long login_time){
        long _old = login_time;
        this.login_time = login_time;
        changeIt("login_time", _old, login_time);
        return this;
    }

    public User changeLogin_time(long login_time){
        return setLogin_time(this.login_time + login_time);
    }

    public User changeLogin_timeWithMin(long login_time, long _min){
        long _v2 = this.login_time + login_time;
        return setLogin_time(_v2 < _min  ? _min : _v2);
    }

    public User changeLogin_timeWithMax(long login_time, long _max){
        long _v2 = this.login_time + login_time;
        return setLogin_time(_v2 > _max  ? _max : _v2);
    }

    public User changeLogin_timeWithMinMax(long login_time, long _min, long _max){
        long _v2 = this.login_time + login_time;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLogin_time(_v2 < _min  ? _min : _v2);
    }

    public String getModel(){
        return model;
    }

    public User setModel(String model){
        String _old = model;
        this.model = model;
        changeIt("model", _old, model);
        return this;
    }

    public String getVersion(){
        return version;
    }

    public User setVersion(String version){
        String _old = version;
        this.version = version;
        changeIt("version", _old, version);
        return this;
    }

    public String getRemain(){
        return remain;
    }

    public User setRemain(String remain){
        String _old = remain;
        this.remain = remain;
        changeIt("remain", _old, remain);
        return this;
    }

    public String getPcids(){
        return pcids;
    }

    public User setPcids(String pcids){
        String _old = pcids;
        this.pcids = pcids;
        changeIt("pcids", _old, pcids);
        return this;
    }

    public int compareTo(User v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(User v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static User newUser(Integer id, Integer ucid, String login_pwd, String name, String login_uid, String uuid, Long login_time, String model, String version, String remain, String pcids) {
        User result = new User();
        result.id = id;
        result.ucid = ucid;
        result.login_pwd = login_pwd;
        result.name = name;
        result.login_uid = login_uid;
        result.uuid = uuid;
        result.login_time = login_time;
        result.model = model;
        result.version = version;
        result.remain = remain;
        result.pcids = pcids;
        return result;
    }

    public static User newUser(User user) {
        User result = new User();
        result.id = user.id;
        result.ucid = user.ucid;
        result.login_pwd = user.login_pwd;
        result.name = user.name;
        result.login_uid = user.login_uid;
        result.uuid = user.uuid;
        result.login_time = user.login_time;
        result.model = user.model;
        result.version = user.version;
        result.remain = user.remain;
        result.pcids = user.pcids;
        return result;
    }

    public User createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, ucid);
        B2OutputStream.writeObject(_out, login_pwd);
        B2OutputStream.writeObject(_out, name);
        B2OutputStream.writeObject(_out, login_uid);
        B2OutputStream.writeObject(_out, uuid);
        B2OutputStream.writeObject(_out, login_time);
        B2OutputStream.writeObject(_out, model);
        B2OutputStream.writeObject(_out, version);
        B2OutputStream.writeObject(_out, remain);
        B2OutputStream.writeObject(_out, pcids);
    }

    public static final User readObject(InputStream _in) throws Exception {
        User result = new User();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.ucid = (Integer) B2InputStream.readObject(_in);
        result.login_pwd = (String) B2InputStream.readObject(_in);
        result.name = (String) B2InputStream.readObject(_in);
        result.login_uid = (String) B2InputStream.readObject(_in);
        result.uuid = (String) B2InputStream.readObject(_in);
        result.login_time = (Long) B2InputStream.readObject(_in);
        result.model = (String) B2InputStream.readObject(_in);
        result.version = (String) B2InputStream.readObject(_in);
        result.remain = (String) B2InputStream.readObject(_in);
        result.pcids = (String) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getUser(){
        User user = null; // user
        { // new and insert User (user)
            int id = 0; 	// id
            int ucid = 0; 	// ucid
            String login_pwd = ""; 	// login_pwd
            String name = ""; 	// name
            String login_uid = ""; 	// login_uid
            String uuid = ""; 	// uuid
            long login_time = 0; 	// login_time
            String model = ""; 	// model
            String version = ""; 	// version
            String remain = ""; 	// remain
            String pcids = ""; 	// pcids
            user = User.newUser(id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids);
        }
        user = user.insert();

        int id = user.getId(); 	// id
        int ucid = user.getUcid(); 	// ucid
        String login_pwd = user.getLogin_pwd(); 	// login_pwd
        String name = user.getName(); 	// name
        String login_uid = user.getLogin_uid(); 	// login_uid
        String uuid = user.getUuid(); 	// uuid
        long login_time = user.getLogin_time(); 	// login_time
        String model = user.getModel(); 	// model
        String version = user.getVersion(); 	// version
        String remain = user.getRemain(); 	// remain
        String pcids = user.getPcids(); 	// pcids
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
        case "ucid":
            return ucid;
        }
        return 0;
    }

    public User setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public User setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "ucid":
            return setUcid(value2);
        }
        return this;
    }

    public User changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public User changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "ucid":
            return changeUcid(value2);
        }
        return this;
    }

    public User changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public User changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "ucid":
            return changeUcidWithMin(value2, _min);
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

    public User setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public User setDouble(String fieldEn, double value2) {
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
        case "login_pwd": 
            return login_pwd;
        case "name": 
            return name;
        case "login_uid": 
            return login_uid;
        case "uuid": 
            return uuid;
        case "model": 
            return model;
        case "version": 
            return version;
        case "remain": 
            return remain;
        case "pcids": 
            return pcids;
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
        case "ucid":
            return ucid;
        case "login_pwd":
            return login_pwd;
        case "name":
            return name;
        case "login_uid":
            return login_uid;
        case "uuid":
            return uuid;
        case "login_time":
            return login_time;
        case "model":
            return model;
        case "version":
            return version;
        case "remain":
            return remain;
        case "pcids":
            return pcids;
        }
        return null;
    }

    public User setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public User setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "login_pwd":
            return setLogin_pwd(value2);
        case "name":
            return setName(value2);
        case "login_uid":
            return setLogin_uid(value2);
        case "uuid":
            return setUuid(value2);
        case "model":
            return setModel(value2);
        case "version":
            return setVersion(value2);
        case "remain":
            return setRemain(value2);
        case "pcids":
            return setPcids(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public User setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public User setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.User");
        result.put("id", id);
        result.put("ucid", ucid);
        result.put("login_pwd", login_pwd);
        result.put("name", name);
        result.put("login_uid", login_uid);
        result.put("uuid", uuid);
        result.put("login_time", login_time);
        result.put("model", model);
        result.put("version", version);
        result.put("remain", remain);
        result.put("pcids", pcids);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("ucid", ucid);
        result.put("login_pwd", login_pwd);
        result.put("name", name);
        result.put("login_uid", login_uid);
        result.put("uuid", uuid);
        result.put("login_time", login_time);
        result.put("model", model);
        result.put("version", version);
        result.put("remain", remain);
        result.put("pcids", pcids);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.User");
        result.put("id", id);
        result.put("ucid", ucid);
        result.put("login_pwd", login_pwd);
        result.put("name", name);
        result.put("login_uid", login_uid);
        result.put("uuid", uuid);
        result.put("login_time", login_time);
        result.put("model", model);
        result.put("version", version);
        result.put("remain", remain);
        result.put("pcids", pcids);
        return result;
    }

    public User mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer ucid = (Integer)e.get("ucid");
        String login_pwd = (String)e.get("login_pwd");
        String name = (String)e.get("name");
        String login_uid = (String)e.get("login_uid");
        String uuid = (String)e.get("uuid");
        Long login_time = (Long)e.get("login_time");
        String model = (String)e.get("model");
        String version = (String)e.get("version");
        String remain = (String)e.get("remain");
        String pcids = (String)e.get("pcids");

        if(id == null) id = 0;
        if(ucid == null) ucid = 0;
        if(login_pwd == null) login_pwd = "";
        if(name == null) name = "";
        if(login_uid == null) login_uid = "";
        if(uuid == null) uuid = "";
        if(login_time == null) login_time = 0L;
        if(model == null) model = "";
        if(version == null) version = "";
        if(remain == null) remain = "";
        if(pcids == null) pcids = "";

        setId(id);
        setUcid(ucid);
        setLogin_pwd(login_pwd);
        setName(name);
        setLogin_uid(login_uid);
        setUuid(uuid);
        setLogin_time(login_time);
        setModel(model);
        setVersion(version);
        setRemain(remain);
        setPcids(pcids);

        return this;
    }

    public static final User mapTo(Map e){
        User result = new User();

        Integer id = (Integer)e.get("id");
        Integer ucid = (Integer)e.get("ucid");
        String login_pwd = (String)e.get("login_pwd");
        String name = (String)e.get("name");
        String login_uid = (String)e.get("login_uid");
        String uuid = (String)e.get("uuid");
        Long login_time = (Long)e.get("login_time");
        String model = (String)e.get("model");
        String version = (String)e.get("version");
        String remain = (String)e.get("remain");
        String pcids = (String)e.get("pcids");

        if(id == null) id = 0;
        if(ucid == null) ucid = 0;
        if(login_pwd == null) login_pwd = "";
        if(name == null) name = "";
        if(login_uid == null) login_uid = "";
        if(uuid == null) uuid = "";
        if(login_time == null) login_time = 0L;
        if(model == null) model = "";
        if(version == null) version = "";
        if(remain == null) remain = "";
        if(pcids == null) pcids = "";

        result.id = id;
        result.ucid = ucid;
        result.login_pwd = login_pwd;
        result.name = name;
        result.login_uid = login_uid;
        result.uuid = uuid;
        result.login_time = login_time;
        result.model = model;
        result.version = version;
        result.remain = remain;
        result.pcids = pcids;

        return result;
    }

    public static final User originalTo(Map e){
        User result = new User();

        Integer id = (Integer)e.get("id");
        Integer ucid = (Integer)e.get("ucid");
        String login_pwd = (String)e.get("login_pwd");
        String name = (String)e.get("name");
        String login_uid = (String)e.get("login_uid");
        String uuid = (String)e.get("uuid");
        Long login_time = (Long)e.get("login_time");
        String model = (String)e.get("model");
        String version = (String)e.get("version");
        String remain = (String)e.get("remain");
        String pcids = (String)e.get("pcids");

        if(id == null) id = 0;
        if(ucid == null) ucid = 0;
        if(login_pwd == null) login_pwd = "";
        if(name == null) name = "";
        if(login_uid == null) login_uid = "";
        if(uuid == null) uuid = "";
        if(login_time == null) login_time = 0L;
        if(model == null) model = "";
        if(version == null) version = "";
        if(remain == null) remain = "";
        if(pcids == null) pcids = "";

        result.id = id;
        result.ucid = ucid;
        result.login_pwd = login_pwd;
        result.name = name;
        result.login_uid = login_uid;
        result.uuid = uuid;
        result.login_time = login_time;
        result.model = model;
        result.version = version;
        result.remain = remain;
        result.pcids = pcids;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final User createFor(byte[] b) throws Exception {
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
    public static final User loadByKey(int id) {
        return UserEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final User insert() {
        User result = UserEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final User asyncInsert() {
        User result = UserEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final User insert2() {
        return UserEntity.insert2(this);
    }

    public final User asyncInsert2() {
        User result = UserEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final User update() {
        return UserEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        UserEntity.asynchronousUpdate( this );
        return true;
    }

    public final User insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return UserEntity.delete(id);
    }

    public final void asyncDelete() {
        UserEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public User clone2() {
        try{
            return (User) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
