package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - player_mail
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Player_mail extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 783471835; // com.sea.db.bean.Player_mail

    public static String TABLENAME = "player_mail";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "mcid", "type", "title", "content", "create_time", "fromId", "fromName", "toId", "toName", "isRead"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "VARCHAR", "TEXT", "BIGINT", "INT", "VARCHAR", "INT", "VARCHAR", "BIT"};


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
    public int mcid;
    public int type;
    public String title;
    public String content;
    public long create_time;
    public int fromId;
    public String fromName;
    public int toId;
    public String toName;
    public boolean isRead;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Player_mail setId(int id){
        this.id = id;
        return this;
    }

    public int getMcid(){
        return mcid;
    }

    public Player_mail setMcid(int mcid){
        int _old = mcid;
        this.mcid = mcid;
        changeIt("mcid", _old, mcid);
        return this;
    }

    public Player_mail changeMcid(int mcid){
        return setMcid(this.mcid + mcid);
    }

    public Player_mail changeMcidWithMin(int mcid, int _min){
        int _v2 = this.mcid + mcid;
        return setMcid(_v2 < _min  ? _min : _v2);
    }

    public Player_mail changeMcidWithMax(int mcid, int _max){
        int _v2 = this.mcid + mcid;
        return setMcid(_v2 > _max  ? _max : _v2);
    }

    public Player_mail changeMcidWithMinMax(int mcid, int _min, int _max){
        int _v2 = this.mcid + mcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMcid(_v2 < _min  ? _min : _v2);
    }

    public int getType(){
        return type;
    }

    public Player_mail setType(int type){
        int _old = type;
        this.type = type;
        changeIt("type", _old, type);
        return this;
    }

    public Player_mail changeType(int type){
        return setType(this.type + type);
    }

    public Player_mail changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Player_mail changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Player_mail changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public String getTitle(){
        return title;
    }

    public Player_mail setTitle(String title){
        String _old = title;
        this.title = title;
        changeIt("title", _old, title);
        return this;
    }

    public String getContent(){
        return content;
    }

    public Player_mail setContent(String content){
        String _old = content;
        this.content = content;
        changeIt("content", _old, content);
        return this;
    }

    public long getCreate_time(){
        return create_time;
    }

    public Player_mail setCreate_time(long create_time){
        long _old = create_time;
        this.create_time = create_time;
        changeIt("create_time", _old, create_time);
        return this;
    }

    public Player_mail changeCreate_time(long create_time){
        return setCreate_time(this.create_time + create_time);
    }

    public Player_mail changeCreate_timeWithMin(long create_time, long _min){
        long _v2 = this.create_time + create_time;
        return setCreate_time(_v2 < _min  ? _min : _v2);
    }

    public Player_mail changeCreate_timeWithMax(long create_time, long _max){
        long _v2 = this.create_time + create_time;
        return setCreate_time(_v2 > _max  ? _max : _v2);
    }

    public Player_mail changeCreate_timeWithMinMax(long create_time, long _min, long _max){
        long _v2 = this.create_time + create_time;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCreate_time(_v2 < _min  ? _min : _v2);
    }

    public int getFromid(){
        return fromId;
    }

    public Player_mail setFromid(int fromId){
        int _old = fromId;
        this.fromId = fromId;
        changeIt("fromId", _old, fromId);
        return this;
    }

    public Player_mail changeFromid(int fromId){
        return setFromid(this.fromId + fromId);
    }

    public Player_mail changeFromidWithMin(int fromId, int _min){
        int _v2 = this.fromId + fromId;
        return setFromid(_v2 < _min  ? _min : _v2);
    }

    public Player_mail changeFromidWithMax(int fromId, int _max){
        int _v2 = this.fromId + fromId;
        return setFromid(_v2 > _max  ? _max : _v2);
    }

    public Player_mail changeFromidWithMinMax(int fromId, int _min, int _max){
        int _v2 = this.fromId + fromId;
        _v2 = _v2 > _max  ? _max : _v2;
        return setFromid(_v2 < _min  ? _min : _v2);
    }

    public String getFromname(){
        return fromName;
    }

    public Player_mail setFromname(String fromName){
        String _old = fromName;
        this.fromName = fromName;
        changeIt("fromName", _old, fromName);
        return this;
    }

    public int getToid(){
        return toId;
    }

    public Player_mail setToid(int toId){
        int _old = toId;
        this.toId = toId;
        changeIt("toId", _old, toId);
        return this;
    }

    public Player_mail changeToid(int toId){
        return setToid(this.toId + toId);
    }

    public Player_mail changeToidWithMin(int toId, int _min){
        int _v2 = this.toId + toId;
        return setToid(_v2 < _min  ? _min : _v2);
    }

    public Player_mail changeToidWithMax(int toId, int _max){
        int _v2 = this.toId + toId;
        return setToid(_v2 > _max  ? _max : _v2);
    }

    public Player_mail changeToidWithMinMax(int toId, int _min, int _max){
        int _v2 = this.toId + toId;
        _v2 = _v2 > _max  ? _max : _v2;
        return setToid(_v2 < _min  ? _min : _v2);
    }

    public String getToname(){
        return toName;
    }

    public Player_mail setToname(String toName){
        String _old = toName;
        this.toName = toName;
        changeIt("toName", _old, toName);
        return this;
    }

    public boolean getIsread(){
        return isRead;
    }

    public Player_mail setIsread(boolean isRead){
        boolean _old = isRead;
        this.isRead = isRead;
        changeIt("isRead", _old, isRead);
        return this;
    }

    public int compareTo(Player_mail v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Player_mail v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Player_mail newPlayer_mail(Integer id, Integer mcid, Integer type, String title, String content, Long create_time, Integer fromId, String fromName, Integer toId, String toName, Boolean isRead) {
        Player_mail result = new Player_mail();
        result.id = id;
        result.mcid = mcid;
        result.type = type;
        result.title = title;
        result.content = content;
        result.create_time = create_time;
        result.fromId = fromId;
        result.fromName = fromName;
        result.toId = toId;
        result.toName = toName;
        result.isRead = isRead;
        return result;
    }

    public static Player_mail newPlayer_mail(Player_mail player_mail) {
        Player_mail result = new Player_mail();
        result.id = player_mail.id;
        result.mcid = player_mail.mcid;
        result.type = player_mail.type;
        result.title = player_mail.title;
        result.content = player_mail.content;
        result.create_time = player_mail.create_time;
        result.fromId = player_mail.fromId;
        result.fromName = player_mail.fromName;
        result.toId = player_mail.toId;
        result.toName = player_mail.toName;
        result.isRead = player_mail.isRead;
        return result;
    }

    public Player_mail createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, mcid);
        B2OutputStream.writeObject(_out, type);
        B2OutputStream.writeObject(_out, title);
        B2OutputStream.writeObject(_out, content);
        B2OutputStream.writeObject(_out, create_time);
        B2OutputStream.writeObject(_out, fromId);
        B2OutputStream.writeObject(_out, fromName);
        B2OutputStream.writeObject(_out, toId);
        B2OutputStream.writeObject(_out, toName);
        B2OutputStream.writeObject(_out, isRead);
    }

    public static final Player_mail readObject(InputStream _in) throws Exception {
        Player_mail result = new Player_mail();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.mcid = (Integer) B2InputStream.readObject(_in);
        result.type = (Integer) B2InputStream.readObject(_in);
        result.title = (String) B2InputStream.readObject(_in);
        result.content = (String) B2InputStream.readObject(_in);
        result.create_time = (Long) B2InputStream.readObject(_in);
        result.fromId = (Integer) B2InputStream.readObject(_in);
        result.fromName = (String) B2InputStream.readObject(_in);
        result.toId = (Integer) B2InputStream.readObject(_in);
        result.toName = (String) B2InputStream.readObject(_in);
        result.isRead = (Boolean) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getPlayer_mail(){
        Player_mail player_mail = null; // player_mail
        { // new and insert Player_mail (player_mail)
            int id = 0; 	// id
            int mcid = 0; 	// mcid
            int type = 0; 	// type
            String title = ""; 	// title
            String content = ""; 	// content
            long create_time = 0; 	// create_time
            int fromId = 0; 	// fromId
            String fromName = ""; 	// fromName
            int toId = 0; 	// toId
            String toName = ""; 	// toName
            boolean isRead = true; 	// isRead
            player_mail = Player_mail.newPlayer_mail(id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead);
        }
        player_mail = player_mail.insert();

        int id = player_mail.getId(); 	// id
        int mcid = player_mail.getMcid(); 	// mcid
        int type = player_mail.getType(); 	// type
        String title = player_mail.getTitle(); 	// title
        String content = player_mail.getContent(); 	// content
        long create_time = player_mail.getCreate_time(); 	// create_time
        int fromId = player_mail.getFromid(); 	// fromId
        String fromName = player_mail.getFromname(); 	// fromName
        int toId = player_mail.getToid(); 	// toId
        String toName = player_mail.getToname(); 	// toName
        boolean isRead = player_mail.getIsread(); 	// isRead
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
        case "mcid":
            return mcid;
        case "type":
            return type;
        case "fromId":
            return fromId;
        case "toId":
            return toId;
        }
        return 0;
    }

    public Player_mail setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Player_mail setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "mcid":
            return setMcid(value2);
        case "type":
            return setType(value2);
        case "fromId":
            return setFromid(value2);
        case "toId":
            return setToid(value2);
        }
        return this;
    }

    public Player_mail changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Player_mail changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "mcid":
            return changeMcid(value2);
        case "type":
            return changeType(value2);
        case "fromId":
            return changeFromid(value2);
        case "toId":
            return changeToid(value2);
        }
        return this;
    }

    public Player_mail changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Player_mail changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "mcid":
            return changeMcidWithMin(value2, _min);
        case "type":
            return changeTypeWithMin(value2, _min);
        case "fromId":
            return changeFromidWithMin(value2, _min);
        case "toId":
            return changeToidWithMin(value2, _min);
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

    public Player_mail setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Player_mail setDouble(String fieldEn, double value2) {
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
        case "title": 
            return title;
        case "content": 
            return content;
        case "fromName": 
            return fromName;
        case "toName": 
            return toName;
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
        case "mcid":
            return mcid;
        case "type":
            return type;
        case "title":
            return title;
        case "content":
            return content;
        case "create_time":
            return create_time;
        case "fromId":
            return fromId;
        case "fromName":
            return fromName;
        case "toId":
            return toId;
        case "toName":
            return toName;
        case "isRead":
            return isRead;
        }
        return null;
    }

    public Player_mail setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Player_mail setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "title":
            return setTitle(value2);
        case "content":
            return setContent(value2);
        case "fromName":
            return setFromname(value2);
        case "toName":
            return setToname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Player_mail setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Player_mail setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Player_mail");
        result.put("id", id);
        result.put("mcid", mcid);
        result.put("type", type);
        result.put("title", title);
        result.put("content", content);
        result.put("create_time", create_time);
        result.put("fromId", fromId);
        result.put("fromName", fromName);
        result.put("toId", toId);
        result.put("toName", toName);
        result.put("isRead", isRead);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("mcid", mcid);
        result.put("type", type);
        result.put("title", title);
        result.put("content", content);
        result.put("create_time", create_time);
        result.put("fromId", fromId);
        result.put("fromName", fromName);
        result.put("toId", toId);
        result.put("toName", toName);
        result.put("isRead", isRead);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Player_mail");
        result.put("id", id);
        result.put("mcid", mcid);
        result.put("type", type);
        result.put("title", title);
        result.put("content", content);
        result.put("create_time", create_time);
        result.put("fromId", fromId);
        result.put("fromName", fromName);
        result.put("toId", toId);
        result.put("toName", toName);
        result.put("isRead", isRead);
        return result;
    }

    public Player_mail mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer mcid = (Integer)e.get("mcid");
        Integer type = (Integer)e.get("type");
        String title = (String)e.get("title");
        String content = (String)e.get("content");
        Long create_time = (Long)e.get("create_time");
        Integer fromId = (Integer)e.get("fromId");
        String fromName = (String)e.get("fromName");
        Integer toId = (Integer)e.get("toId");
        String toName = (String)e.get("toName");
        Boolean isRead = (Boolean)e.get("isRead");

        if(id == null) id = 0;
        if(mcid == null) mcid = 0;
        if(type == null) type = 0;
        if(title == null) title = "";
        if(content == null) content = "";
        if(create_time == null) create_time = 0L;
        if(fromId == null) fromId = 0;
        if(fromName == null) fromName = "";
        if(toId == null) toId = 0;
        if(toName == null) toName = "";
        if(isRead == null) isRead = false;

        setId(id);
        setMcid(mcid);
        setType(type);
        setTitle(title);
        setContent(content);
        setCreate_time(create_time);
        setFromid(fromId);
        setFromname(fromName);
        setToid(toId);
        setToname(toName);
        setIsread(isRead);

        return this;
    }

    public static final Player_mail mapTo(Map e){
        Player_mail result = new Player_mail();

        Integer id = (Integer)e.get("id");
        Integer mcid = (Integer)e.get("mcid");
        Integer type = (Integer)e.get("type");
        String title = (String)e.get("title");
        String content = (String)e.get("content");
        Long create_time = (Long)e.get("create_time");
        Integer fromId = (Integer)e.get("fromId");
        String fromName = (String)e.get("fromName");
        Integer toId = (Integer)e.get("toId");
        String toName = (String)e.get("toName");
        Boolean isRead = (Boolean)e.get("isRead");

        if(id == null) id = 0;
        if(mcid == null) mcid = 0;
        if(type == null) type = 0;
        if(title == null) title = "";
        if(content == null) content = "";
        if(create_time == null) create_time = 0L;
        if(fromId == null) fromId = 0;
        if(fromName == null) fromName = "";
        if(toId == null) toId = 0;
        if(toName == null) toName = "";
        if(isRead == null) isRead = false;

        result.id = id;
        result.mcid = mcid;
        result.type = type;
        result.title = title;
        result.content = content;
        result.create_time = create_time;
        result.fromId = fromId;
        result.fromName = fromName;
        result.toId = toId;
        result.toName = toName;
        result.isRead = isRead;

        return result;
    }

    public static final Player_mail originalTo(Map e){
        Player_mail result = new Player_mail();

        Integer id = (Integer)e.get("id");
        Integer mcid = (Integer)e.get("mcid");
        Integer type = (Integer)e.get("type");
        String title = (String)e.get("title");
        String content = (String)e.get("content");
        Long create_time = (Long)e.get("create_time");
        Integer fromId = (Integer)e.get("fromId");
        String fromName = (String)e.get("fromName");
        Integer toId = (Integer)e.get("toId");
        String toName = (String)e.get("toName");
        Boolean isRead = (Boolean)e.get("isRead");

        if(id == null) id = 0;
        if(mcid == null) mcid = 0;
        if(type == null) type = 0;
        if(title == null) title = "";
        if(content == null) content = "";
        if(create_time == null) create_time = 0L;
        if(fromId == null) fromId = 0;
        if(fromName == null) fromName = "";
        if(toId == null) toId = 0;
        if(toName == null) toName = "";
        if(isRead == null) isRead = false;

        result.id = id;
        result.mcid = mcid;
        result.type = type;
        result.title = title;
        result.content = content;
        result.create_time = create_time;
        result.fromId = fromId;
        result.fromName = fromName;
        result.toId = toId;
        result.toName = toName;
        result.isRead = isRead;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Player_mail createFor(byte[] b) throws Exception {
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
    public static final Player_mail loadByKey(int id) {
        return Player_mailEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Player_mail insert() {
        Player_mail result = Player_mailEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Player_mail asyncInsert() {
        Player_mail result = Player_mailEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Player_mail insert2() {
        return Player_mailEntity.insert2(this);
    }

    public final Player_mail asyncInsert2() {
        Player_mail result = Player_mailEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Player_mail update() {
        return Player_mailEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Player_mailEntity.asynchronousUpdate( this );
        return true;
    }

    public final Player_mail insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Player_mailEntity.delete(id);
    }

    public final void asyncDelete() {
        Player_mailEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Player_mail clone2() {
        try{
            return (Player_mail) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
