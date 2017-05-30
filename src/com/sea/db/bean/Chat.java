package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - chat
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Chat extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1752436334; // com.sea.db.bean.Chat

    public static String TABLENAME = "chat";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "ccid", "type", "content", "create_time", "fromId", "fromName", "toId", "toName", "clancid"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "TEXT", "BIGINT", "INT", "VARCHAR", "INT", "VARCHAR", "VARCHAR"};


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
    public int ccid;
    public int type;
    public String content;
    public long create_time;
    public int fromId;
    public String fromName;
    public int toId;
    public String toName;
    public String clancid;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Chat setId(int id){
        this.id = id;
        return this;
    }

    public int getCcid(){
        return ccid;
    }

    public Chat setCcid(int ccid){
        int _old = ccid;
        this.ccid = ccid;
        changeIt("ccid", _old, ccid);
        return this;
    }

    public Chat changeCcid(int ccid){
        return setCcid(this.ccid + ccid);
    }

    public Chat changeCcidWithMin(int ccid, int _min){
        int _v2 = this.ccid + ccid;
        return setCcid(_v2 < _min  ? _min : _v2);
    }

    public Chat changeCcidWithMax(int ccid, int _max){
        int _v2 = this.ccid + ccid;
        return setCcid(_v2 > _max  ? _max : _v2);
    }

    public Chat changeCcidWithMinMax(int ccid, int _min, int _max){
        int _v2 = this.ccid + ccid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCcid(_v2 < _min  ? _min : _v2);
    }

    public int getType(){
        return type;
    }

    public Chat setType(int type){
        int _old = type;
        this.type = type;
        changeIt("type", _old, type);
        return this;
    }

    public Chat changeType(int type){
        return setType(this.type + type);
    }

    public Chat changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Chat changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Chat changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public String getContent(){
        return content;
    }

    public Chat setContent(String content){
        String _old = content;
        this.content = content;
        changeIt("content", _old, content);
        return this;
    }

    public long getCreate_time(){
        return create_time;
    }

    public Chat setCreate_time(long create_time){
        long _old = create_time;
        this.create_time = create_time;
        changeIt("create_time", _old, create_time);
        return this;
    }

    public Chat changeCreate_time(long create_time){
        return setCreate_time(this.create_time + create_time);
    }

    public Chat changeCreate_timeWithMin(long create_time, long _min){
        long _v2 = this.create_time + create_time;
        return setCreate_time(_v2 < _min  ? _min : _v2);
    }

    public Chat changeCreate_timeWithMax(long create_time, long _max){
        long _v2 = this.create_time + create_time;
        return setCreate_time(_v2 > _max  ? _max : _v2);
    }

    public Chat changeCreate_timeWithMinMax(long create_time, long _min, long _max){
        long _v2 = this.create_time + create_time;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCreate_time(_v2 < _min  ? _min : _v2);
    }

    public int getFromid(){
        return fromId;
    }

    public Chat setFromid(int fromId){
        int _old = fromId;
        this.fromId = fromId;
        changeIt("fromId", _old, fromId);
        return this;
    }

    public Chat changeFromid(int fromId){
        return setFromid(this.fromId + fromId);
    }

    public Chat changeFromidWithMin(int fromId, int _min){
        int _v2 = this.fromId + fromId;
        return setFromid(_v2 < _min  ? _min : _v2);
    }

    public Chat changeFromidWithMax(int fromId, int _max){
        int _v2 = this.fromId + fromId;
        return setFromid(_v2 > _max  ? _max : _v2);
    }

    public Chat changeFromidWithMinMax(int fromId, int _min, int _max){
        int _v2 = this.fromId + fromId;
        _v2 = _v2 > _max  ? _max : _v2;
        return setFromid(_v2 < _min  ? _min : _v2);
    }

    public String getFromname(){
        return fromName;
    }

    public Chat setFromname(String fromName){
        String _old = fromName;
        this.fromName = fromName;
        changeIt("fromName", _old, fromName);
        return this;
    }

    public int getToid(){
        return toId;
    }

    public Chat setToid(int toId){
        int _old = toId;
        this.toId = toId;
        changeIt("toId", _old, toId);
        return this;
    }

    public Chat changeToid(int toId){
        return setToid(this.toId + toId);
    }

    public Chat changeToidWithMin(int toId, int _min){
        int _v2 = this.toId + toId;
        return setToid(_v2 < _min  ? _min : _v2);
    }

    public Chat changeToidWithMax(int toId, int _max){
        int _v2 = this.toId + toId;
        return setToid(_v2 > _max  ? _max : _v2);
    }

    public Chat changeToidWithMinMax(int toId, int _min, int _max){
        int _v2 = this.toId + toId;
        _v2 = _v2 > _max  ? _max : _v2;
        return setToid(_v2 < _min  ? _min : _v2);
    }

    public String getToname(){
        return toName;
    }

    public Chat setToname(String toName){
        String _old = toName;
        this.toName = toName;
        changeIt("toName", _old, toName);
        return this;
    }

    public String getClancid(){
        return clancid;
    }

    public Chat setClancid(String clancid){
        String _old = clancid;
        this.clancid = clancid;
        changeIt("clancid", _old, clancid);
        return this;
    }

    public int compareTo(Chat v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Chat v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Chat newChat(Integer id, Integer ccid, Integer type, String content, Long create_time, Integer fromId, String fromName, Integer toId, String toName, String clancid) {
        Chat result = new Chat();
        result.id = id;
        result.ccid = ccid;
        result.type = type;
        result.content = content;
        result.create_time = create_time;
        result.fromId = fromId;
        result.fromName = fromName;
        result.toId = toId;
        result.toName = toName;
        result.clancid = clancid;
        return result;
    }

    public static Chat newChat(Chat chat) {
        Chat result = new Chat();
        result.id = chat.id;
        result.ccid = chat.ccid;
        result.type = chat.type;
        result.content = chat.content;
        result.create_time = chat.create_time;
        result.fromId = chat.fromId;
        result.fromName = chat.fromName;
        result.toId = chat.toId;
        result.toName = chat.toName;
        result.clancid = chat.clancid;
        return result;
    }

    public Chat createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, ccid);
        B2OutputStream.writeObject(_out, type);
        B2OutputStream.writeObject(_out, content);
        B2OutputStream.writeObject(_out, create_time);
        B2OutputStream.writeObject(_out, fromId);
        B2OutputStream.writeObject(_out, fromName);
        B2OutputStream.writeObject(_out, toId);
        B2OutputStream.writeObject(_out, toName);
        B2OutputStream.writeObject(_out, clancid);
    }

    public static final Chat readObject(InputStream _in) throws Exception {
        Chat result = new Chat();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.ccid = (Integer) B2InputStream.readObject(_in);
        result.type = (Integer) B2InputStream.readObject(_in);
        result.content = (String) B2InputStream.readObject(_in);
        result.create_time = (Long) B2InputStream.readObject(_in);
        result.fromId = (Integer) B2InputStream.readObject(_in);
        result.fromName = (String) B2InputStream.readObject(_in);
        result.toId = (Integer) B2InputStream.readObject(_in);
        result.toName = (String) B2InputStream.readObject(_in);
        result.clancid = (String) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getChat(){
        Chat chat = null; // chat
        { // new and insert Chat (chat)
            int id = 0; 	// id
            int ccid = 0; 	// ccid
            int type = 0; 	// type
            String content = ""; 	// content
            long create_time = 0; 	// create_time
            int fromId = 0; 	// fromId
            String fromName = ""; 	// fromName
            int toId = 0; 	// toId
            String toName = ""; 	// toName
            String clancid = ""; 	// clancid
            chat = Chat.newChat(id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid);
        }
        chat = chat.insert();

        int id = chat.getId(); 	// id
        int ccid = chat.getCcid(); 	// ccid
        int type = chat.getType(); 	// type
        String content = chat.getContent(); 	// content
        long create_time = chat.getCreate_time(); 	// create_time
        int fromId = chat.getFromid(); 	// fromId
        String fromName = chat.getFromname(); 	// fromName
        int toId = chat.getToid(); 	// toId
        String toName = chat.getToname(); 	// toName
        String clancid = chat.getClancid(); 	// clancid
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
        case "ccid":
            return ccid;
        case "type":
            return type;
        case "fromId":
            return fromId;
        case "toId":
            return toId;
        }
        return 0;
    }

    public Chat setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Chat setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "ccid":
            return setCcid(value2);
        case "type":
            return setType(value2);
        case "fromId":
            return setFromid(value2);
        case "toId":
            return setToid(value2);
        }
        return this;
    }

    public Chat changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Chat changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "ccid":
            return changeCcid(value2);
        case "type":
            return changeType(value2);
        case "fromId":
            return changeFromid(value2);
        case "toId":
            return changeToid(value2);
        }
        return this;
    }

    public Chat changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Chat changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "ccid":
            return changeCcidWithMin(value2, _min);
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

    public Chat setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Chat setDouble(String fieldEn, double value2) {
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
        case "content": 
            return content;
        case "fromName": 
            return fromName;
        case "toName": 
            return toName;
        case "clancid": 
            return clancid;
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
        case "type":
            return type;
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
        case "clancid":
            return clancid;
        }
        return null;
    }

    public Chat setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Chat setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "content":
            return setContent(value2);
        case "fromName":
            return setFromname(value2);
        case "toName":
            return setToname(value2);
        case "clancid":
            return setClancid(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Chat setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Chat setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Chat");
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("type", type);
        result.put("content", content);
        result.put("create_time", create_time);
        result.put("fromId", fromId);
        result.put("fromName", fromName);
        result.put("toId", toId);
        result.put("toName", toName);
        result.put("clancid", clancid);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("type", type);
        result.put("content", content);
        result.put("create_time", create_time);
        result.put("fromId", fromId);
        result.put("fromName", fromName);
        result.put("toId", toId);
        result.put("toName", toName);
        result.put("clancid", clancid);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Chat");
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("type", type);
        result.put("content", content);
        result.put("create_time", create_time);
        result.put("fromId", fromId);
        result.put("fromName", fromName);
        result.put("toId", toId);
        result.put("toName", toName);
        result.put("clancid", clancid);
        return result;
    }

    public Chat mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer ccid = (Integer)e.get("ccid");
        Integer type = (Integer)e.get("type");
        String content = (String)e.get("content");
        Long create_time = (Long)e.get("create_time");
        Integer fromId = (Integer)e.get("fromId");
        String fromName = (String)e.get("fromName");
        Integer toId = (Integer)e.get("toId");
        String toName = (String)e.get("toName");
        String clancid = (String)e.get("clancid");

        if(id == null) id = 0;
        if(ccid == null) ccid = 0;
        if(type == null) type = 0;
        if(content == null) content = "";
        if(create_time == null) create_time = 0L;
        if(fromId == null) fromId = 0;
        if(fromName == null) fromName = "";
        if(toId == null) toId = 0;
        if(toName == null) toName = "";
        if(clancid == null) clancid = "";

        setId(id);
        setCcid(ccid);
        setType(type);
        setContent(content);
        setCreate_time(create_time);
        setFromid(fromId);
        setFromname(fromName);
        setToid(toId);
        setToname(toName);
        setClancid(clancid);

        return this;
    }

    public static final Chat mapTo(Map e){
        Chat result = new Chat();

        Integer id = (Integer)e.get("id");
        Integer ccid = (Integer)e.get("ccid");
        Integer type = (Integer)e.get("type");
        String content = (String)e.get("content");
        Long create_time = (Long)e.get("create_time");
        Integer fromId = (Integer)e.get("fromId");
        String fromName = (String)e.get("fromName");
        Integer toId = (Integer)e.get("toId");
        String toName = (String)e.get("toName");
        String clancid = (String)e.get("clancid");

        if(id == null) id = 0;
        if(ccid == null) ccid = 0;
        if(type == null) type = 0;
        if(content == null) content = "";
        if(create_time == null) create_time = 0L;
        if(fromId == null) fromId = 0;
        if(fromName == null) fromName = "";
        if(toId == null) toId = 0;
        if(toName == null) toName = "";
        if(clancid == null) clancid = "";

        result.id = id;
        result.ccid = ccid;
        result.type = type;
        result.content = content;
        result.create_time = create_time;
        result.fromId = fromId;
        result.fromName = fromName;
        result.toId = toId;
        result.toName = toName;
        result.clancid = clancid;

        return result;
    }

    public static final Chat originalTo(Map e){
        Chat result = new Chat();

        Integer id = (Integer)e.get("id");
        Integer ccid = (Integer)e.get("ccid");
        Integer type = (Integer)e.get("type");
        String content = (String)e.get("content");
        Long create_time = (Long)e.get("create_time");
        Integer fromId = (Integer)e.get("fromId");
        String fromName = (String)e.get("fromName");
        Integer toId = (Integer)e.get("toId");
        String toName = (String)e.get("toName");
        String clancid = (String)e.get("clancid");

        if(id == null) id = 0;
        if(ccid == null) ccid = 0;
        if(type == null) type = 0;
        if(content == null) content = "";
        if(create_time == null) create_time = 0L;
        if(fromId == null) fromId = 0;
        if(fromName == null) fromName = "";
        if(toId == null) toId = 0;
        if(toName == null) toName = "";
        if(clancid == null) clancid = "";

        result.id = id;
        result.ccid = ccid;
        result.type = type;
        result.content = content;
        result.create_time = create_time;
        result.fromId = fromId;
        result.fromName = fromName;
        result.toId = toId;
        result.toName = toName;
        result.clancid = clancid;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Chat createFor(byte[] b) throws Exception {
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
    public static final Chat loadByKey(int id) {
        return ChatEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Chat insert() {
        Chat result = ChatEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Chat asyncInsert() {
        Chat result = ChatEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Chat insert2() {
        return ChatEntity.insert2(this);
    }

    public final Chat asyncInsert2() {
        Chat result = ChatEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Chat update() {
        return ChatEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        ChatEntity.asynchronousUpdate( this );
        return true;
    }

    public final Chat insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return ChatEntity.delete(id);
    }

    public final void asyncDelete() {
        ChatEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Chat clone2() {
        try{
            return (Chat) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
