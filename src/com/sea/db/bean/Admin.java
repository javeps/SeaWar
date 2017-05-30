package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - admin
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Admin extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1507093589; // com.sea.db.bean.Admin

    public static String TABLENAME = "admin";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "name", "lgID", "lgPwd", "post", "powers", "lastLogin", "status"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR", "VARCHAR", "INT", "VARCHAR", "DATETIME", "INT"};


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
    public String name;
    public String lgID;
    public String lgPwd;
    public int post;
    public String powers;
    public java.util.Date lastLogin;
    public int status;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Admin setId(int id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public Admin setName(String name){
        String _old = name;
        this.name = name;
        changeIt("name", _old, name);
        return this;
    }

    public String getLgid(){
        return lgID;
    }

    public Admin setLgid(String lgID){
        String _old = lgID;
        this.lgID = lgID;
        changeIt("lgID", _old, lgID);
        return this;
    }

    public String getLgpwd(){
        return lgPwd;
    }

    public Admin setLgpwd(String lgPwd){
        String _old = lgPwd;
        this.lgPwd = lgPwd;
        changeIt("lgPwd", _old, lgPwd);
        return this;
    }

    public int getPost(){
        return post;
    }

    public Admin setPost(int post){
        int _old = post;
        this.post = post;
        changeIt("post", _old, post);
        return this;
    }

    public Admin changePost(int post){
        return setPost(this.post + post);
    }

    public Admin changePostWithMin(int post, int _min){
        int _v2 = this.post + post;
        return setPost(_v2 < _min  ? _min : _v2);
    }

    public Admin changePostWithMax(int post, int _max){
        int _v2 = this.post + post;
        return setPost(_v2 > _max  ? _max : _v2);
    }

    public Admin changePostWithMinMax(int post, int _min, int _max){
        int _v2 = this.post + post;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPost(_v2 < _min  ? _min : _v2);
    }

    public String getPowers(){
        return powers;
    }

    public Admin setPowers(String powers){
        String _old = powers;
        this.powers = powers;
        changeIt("powers", _old, powers);
        return this;
    }

    public java.util.Date getLastlogin(){
        return lastLogin;
    }

    public Admin setLastlogin(java.util.Date lastLogin){
        java.util.Date _old = lastLogin;
        this.lastLogin = lastLogin;
        changeIt("lastLogin", _old, lastLogin);
        return this;
    }

    public int getStatus(){
        return status;
    }

    public Admin setStatus(int status){
        int _old = status;
        this.status = status;
        changeIt("status", _old, status);
        return this;
    }

    public Admin changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Admin changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Admin changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Admin changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Admin v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Admin v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Admin newAdmin(Integer id, String name, String lgID, String lgPwd, Integer post, String powers, java.util.Date lastLogin, Integer status) {
        Admin result = new Admin();
        result.id = id;
        result.name = name;
        result.lgID = lgID;
        result.lgPwd = lgPwd;
        result.post = post;
        result.powers = powers;
        result.lastLogin = lastLogin;
        result.status = status;
        return result;
    }

    public static Admin newAdmin(Admin admin) {
        Admin result = new Admin();
        result.id = admin.id;
        result.name = admin.name;
        result.lgID = admin.lgID;
        result.lgPwd = admin.lgPwd;
        result.post = admin.post;
        result.powers = admin.powers;
        result.lastLogin = admin.lastLogin;
        result.status = admin.status;
        return result;
    }

    public Admin createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, name);
        B2OutputStream.writeObject(_out, lgID);
        B2OutputStream.writeObject(_out, lgPwd);
        B2OutputStream.writeObject(_out, post);
        B2OutputStream.writeObject(_out, powers);
        B2OutputStream.writeObject(_out, lastLogin);
        B2OutputStream.writeObject(_out, status);
    }

    public static final Admin readObject(InputStream _in) throws Exception {
        Admin result = new Admin();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.name = (String) B2InputStream.readObject(_in);
        result.lgID = (String) B2InputStream.readObject(_in);
        result.lgPwd = (String) B2InputStream.readObject(_in);
        result.post = (Integer) B2InputStream.readObject(_in);
        result.powers = (String) B2InputStream.readObject(_in);
        result.lastLogin = (java.util.Date) B2InputStream.readObject(_in);
        result.status = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getAdmin(){
        Admin admin = null; // admin
        { // new and insert Admin (admin)
            int id = 0; 	// id
            String name = ""; 	// name
            String lgID = ""; 	// lgID
            String lgPwd = ""; 	// lgPwd
            int post = 0; 	// post
            String powers = ""; 	// powers
            Date lastLogin = new Date(); 	// lastLogin
            int status = 0; 	// status
            admin = Admin.newAdmin(id, name, lgID, lgPwd, post, powers, lastLogin, status);
        }
        admin = admin.insert();

        int id = admin.getId(); 	// id
        String name = admin.getName(); 	// name
        String lgID = admin.getLgid(); 	// lgID
        String lgPwd = admin.getLgpwd(); 	// lgPwd
        int post = admin.getPost(); 	// post
        String powers = admin.getPowers(); 	// powers
        Date lastLogin = admin.getLastlogin(); 	// lastLogin
        int status = admin.getStatus(); 	// status
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
        case "post":
            return post;
        case "status":
            return status;
        }
        return 0;
    }

    public Admin setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Admin setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "post":
            return setPost(value2);
        case "status":
            return setStatus(value2);
        }
        return this;
    }

    public Admin changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Admin changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "post":
            return changePost(value2);
        case "status":
            return changeStatus(value2);
        }
        return this;
    }

    public Admin changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Admin changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "post":
            return changePostWithMin(value2, _min);
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

    public Admin setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Admin setDouble(String fieldEn, double value2) {
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
        case "name": 
            return name;
        case "lgID": 
            return lgID;
        case "lgPwd": 
            return lgPwd;
        case "powers": 
            return powers;
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
        case "name":
            return name;
        case "lgID":
            return lgID;
        case "lgPwd":
            return lgPwd;
        case "post":
            return post;
        case "powers":
            return powers;
        case "lastLogin":
            return lastLogin;
        case "status":
            return status;
        }
        return null;
    }

    public Admin setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Admin setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "name":
            return setName(value2);
        case "lgID":
            return setLgid(value2);
        case "lgPwd":
            return setLgpwd(value2);
        case "powers":
            return setPowers(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Admin setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Admin setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Admin");
        result.put("id", id);
        result.put("name", name);
        result.put("lgID", lgID);
        result.put("lgPwd", lgPwd);
        result.put("post", post);
        result.put("powers", powers);
        result.put("lastLogin", lastLogin);
        result.put("status", status);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("name", name);
        result.put("lgID", lgID);
        result.put("lgPwd", lgPwd);
        result.put("post", post);
        result.put("powers", powers);
        result.put("lastLogin", lastLogin);
        result.put("status", status);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Admin");
        result.put("id", id);
        result.put("name", name);
        result.put("lgID", lgID);
        result.put("lgPwd", lgPwd);
        result.put("post", post);
        result.put("powers", powers);
        result.put("lastLogin", lastLogin);
        result.put("status", status);
        return result;
    }

    public Admin mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String name = (String)e.get("name");
        String lgID = (String)e.get("lgID");
        String lgPwd = (String)e.get("lgPwd");
        Integer post = (Integer)e.get("post");
        String powers = (String)e.get("powers");
        java.util.Date lastLogin = (java.util.Date)e.get("lastLogin");
        Integer status = (Integer)e.get("status");

        if(id == null) id = 0;
        if(name == null) name = "";
        if(lgID == null) lgID = "";
        if(lgPwd == null) lgPwd = "";
        if(post == null) post = 0;
        if(powers == null) powers = "";
        if(lastLogin == null) lastLogin = new java.util.Date();
        if(status == null) status = 0;

        setId(id);
        setName(name);
        setLgid(lgID);
        setLgpwd(lgPwd);
        setPost(post);
        setPowers(powers);
        setLastlogin(lastLogin);
        setStatus(status);

        return this;
    }

    public static final Admin mapTo(Map e){
        Admin result = new Admin();

        Integer id = (Integer)e.get("id");
        String name = (String)e.get("name");
        String lgID = (String)e.get("lgID");
        String lgPwd = (String)e.get("lgPwd");
        Integer post = (Integer)e.get("post");
        String powers = (String)e.get("powers");
        java.util.Date lastLogin = (java.util.Date)e.get("lastLogin");
        Integer status = (Integer)e.get("status");

        if(id == null) id = 0;
        if(name == null) name = "";
        if(lgID == null) lgID = "";
        if(lgPwd == null) lgPwd = "";
        if(post == null) post = 0;
        if(powers == null) powers = "";
        if(lastLogin == null) lastLogin = new java.util.Date();
        if(status == null) status = 0;

        result.id = id;
        result.name = name;
        result.lgID = lgID;
        result.lgPwd = lgPwd;
        result.post = post;
        result.powers = powers;
        result.lastLogin = lastLogin;
        result.status = status;

        return result;
    }

    public static final Admin originalTo(Map e){
        Admin result = new Admin();

        Integer id = (Integer)e.get("id");
        String name = (String)e.get("name");
        String lgID = (String)e.get("lgID");
        String lgPwd = (String)e.get("lgPwd");
        Integer post = (Integer)e.get("post");
        String powers = (String)e.get("powers");
        java.util.Date lastLogin = (java.util.Date)e.get("lastLogin");
        Integer status = (Integer)e.get("status");

        if(id == null) id = 0;
        if(name == null) name = "";
        if(lgID == null) lgID = "";
        if(lgPwd == null) lgPwd = "";
        if(post == null) post = 0;
        if(powers == null) powers = "";
        if(lastLogin == null) lastLogin = new java.util.Date();
        if(status == null) status = 0;

        result.id = id;
        result.name = name;
        result.lgID = lgID;
        result.lgPwd = lgPwd;
        result.post = post;
        result.powers = powers;
        result.lastLogin = lastLogin;
        result.status = status;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Admin createFor(byte[] b) throws Exception {
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
    public static final Admin loadByKey(int id) {
        return AdminEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Admin insert() {
        Admin result = AdminEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Admin asyncInsert() {
        Admin result = AdminEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Admin insert2() {
        return AdminEntity.insert2(this);
    }

    public final Admin asyncInsert2() {
        Admin result = AdminEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Admin update() {
        return AdminEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        AdminEntity.asynchronousUpdate( this );
        return true;
    }

    public final Admin insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return AdminEntity.delete(id);
    }

    public final void asyncDelete() {
        AdminEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Admin clone2() {
        try{
            return (Admin) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
