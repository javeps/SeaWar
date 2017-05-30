package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - notice
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Notice extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -142193006; // com.sea.db.bean.Notice

    public static String TABLENAME = "notice";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "svid", "content", "begintime", "endtime"};
    public static final String[] dbTypes ={"INT", "INT", "TEXT", "BIGINT", "BIGINT"};


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
    public int svid;
    public String content;
    public long begintime;
    public long endtime;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Notice setId(int id){
        this.id = id;
        return this;
    }

    public int getSvid(){
        return svid;
    }

    public Notice setSvid(int svid){
        int _old = svid;
        this.svid = svid;
        changeIt("svid", _old, svid);
        return this;
    }

    public Notice changeSvid(int svid){
        return setSvid(this.svid + svid);
    }

    public Notice changeSvidWithMin(int svid, int _min){
        int _v2 = this.svid + svid;
        return setSvid(_v2 < _min  ? _min : _v2);
    }

    public Notice changeSvidWithMax(int svid, int _max){
        int _v2 = this.svid + svid;
        return setSvid(_v2 > _max  ? _max : _v2);
    }

    public Notice changeSvidWithMinMax(int svid, int _min, int _max){
        int _v2 = this.svid + svid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setSvid(_v2 < _min  ? _min : _v2);
    }

    public String getContent(){
        return content;
    }

    public Notice setContent(String content){
        String _old = content;
        this.content = content;
        changeIt("content", _old, content);
        return this;
    }

    public long getBegintime(){
        return begintime;
    }

    public Notice setBegintime(long begintime){
        long _old = begintime;
        this.begintime = begintime;
        changeIt("begintime", _old, begintime);
        return this;
    }

    public Notice changeBegintime(long begintime){
        return setBegintime(this.begintime + begintime);
    }

    public Notice changeBegintimeWithMin(long begintime, long _min){
        long _v2 = this.begintime + begintime;
        return setBegintime(_v2 < _min  ? _min : _v2);
    }

    public Notice changeBegintimeWithMax(long begintime, long _max){
        long _v2 = this.begintime + begintime;
        return setBegintime(_v2 > _max  ? _max : _v2);
    }

    public Notice changeBegintimeWithMinMax(long begintime, long _min, long _max){
        long _v2 = this.begintime + begintime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBegintime(_v2 < _min  ? _min : _v2);
    }

    public long getEndtime(){
        return endtime;
    }

    public Notice setEndtime(long endtime){
        long _old = endtime;
        this.endtime = endtime;
        changeIt("endtime", _old, endtime);
        return this;
    }

    public Notice changeEndtime(long endtime){
        return setEndtime(this.endtime + endtime);
    }

    public Notice changeEndtimeWithMin(long endtime, long _min){
        long _v2 = this.endtime + endtime;
        return setEndtime(_v2 < _min  ? _min : _v2);
    }

    public Notice changeEndtimeWithMax(long endtime, long _max){
        long _v2 = this.endtime + endtime;
        return setEndtime(_v2 > _max  ? _max : _v2);
    }

    public Notice changeEndtimeWithMinMax(long endtime, long _min, long _max){
        long _v2 = this.endtime + endtime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setEndtime(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Notice v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Notice v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Notice newNotice(Integer id, Integer svid, String content, Long begintime, Long endtime) {
        Notice result = new Notice();
        result.id = id;
        result.svid = svid;
        result.content = content;
        result.begintime = begintime;
        result.endtime = endtime;
        return result;
    }

    public static Notice newNotice(Notice notice) {
        Notice result = new Notice();
        result.id = notice.id;
        result.svid = notice.svid;
        result.content = notice.content;
        result.begintime = notice.begintime;
        result.endtime = notice.endtime;
        return result;
    }

    public Notice createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, svid);
        B2OutputStream.writeObject(_out, content);
        B2OutputStream.writeObject(_out, begintime);
        B2OutputStream.writeObject(_out, endtime);
    }

    public static final Notice readObject(InputStream _in) throws Exception {
        Notice result = new Notice();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.svid = (Integer) B2InputStream.readObject(_in);
        result.content = (String) B2InputStream.readObject(_in);
        result.begintime = (Long) B2InputStream.readObject(_in);
        result.endtime = (Long) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getNotice(){
        Notice notice = null; // notice
        { // new and insert Notice (notice)
            int id = 0; 	// id
            int svid = 0; 	// svid
            String content = ""; 	// content
            long begintime = 0; 	// begintime
            long endtime = 0; 	// endtime
            notice = Notice.newNotice(id, svid, content, begintime, endtime);
        }
        notice = notice.insert();

        int id = notice.getId(); 	// id
        int svid = notice.getSvid(); 	// svid
        String content = notice.getContent(); 	// content
        long begintime = notice.getBegintime(); 	// begintime
        long endtime = notice.getEndtime(); 	// endtime
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
        case "svid":
            return svid;
        }
        return 0;
    }

    public Notice setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Notice setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "svid":
            return setSvid(value2);
        }
        return this;
    }

    public Notice changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Notice changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "svid":
            return changeSvid(value2);
        }
        return this;
    }

    public Notice changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Notice changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "svid":
            return changeSvidWithMin(value2, _min);
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

    public Notice setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Notice setDouble(String fieldEn, double value2) {
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
        case "svid":
            return svid;
        case "content":
            return content;
        case "begintime":
            return begintime;
        case "endtime":
            return endtime;
        }
        return null;
    }

    public Notice setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Notice setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "content":
            return setContent(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Notice setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Notice setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Notice");
        result.put("id", id);
        result.put("svid", svid);
        result.put("content", content);
        result.put("begintime", begintime);
        result.put("endtime", endtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("svid", svid);
        result.put("content", content);
        result.put("begintime", begintime);
        result.put("endtime", endtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Notice");
        result.put("id", id);
        result.put("svid", svid);
        result.put("content", content);
        result.put("begintime", begintime);
        result.put("endtime", endtime);
        return result;
    }

    public Notice mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer svid = (Integer)e.get("svid");
        String content = (String)e.get("content");
        Long begintime = (Long)e.get("begintime");
        Long endtime = (Long)e.get("endtime");

        if(id == null) id = 0;
        if(svid == null) svid = 0;
        if(content == null) content = "";
        if(begintime == null) begintime = 0L;
        if(endtime == null) endtime = 0L;

        setId(id);
        setSvid(svid);
        setContent(content);
        setBegintime(begintime);
        setEndtime(endtime);

        return this;
    }

    public static final Notice mapTo(Map e){
        Notice result = new Notice();

        Integer id = (Integer)e.get("id");
        Integer svid = (Integer)e.get("svid");
        String content = (String)e.get("content");
        Long begintime = (Long)e.get("begintime");
        Long endtime = (Long)e.get("endtime");

        if(id == null) id = 0;
        if(svid == null) svid = 0;
        if(content == null) content = "";
        if(begintime == null) begintime = 0L;
        if(endtime == null) endtime = 0L;

        result.id = id;
        result.svid = svid;
        result.content = content;
        result.begintime = begintime;
        result.endtime = endtime;

        return result;
    }

    public static final Notice originalTo(Map e){
        Notice result = new Notice();

        Integer id = (Integer)e.get("id");
        Integer svid = (Integer)e.get("svid");
        String content = (String)e.get("content");
        Long begintime = (Long)e.get("begintime");
        Long endtime = (Long)e.get("endtime");

        if(id == null) id = 0;
        if(svid == null) svid = 0;
        if(content == null) content = "";
        if(begintime == null) begintime = 0L;
        if(endtime == null) endtime = 0L;

        result.id = id;
        result.svid = svid;
        result.content = content;
        result.begintime = begintime;
        result.endtime = endtime;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Notice createFor(byte[] b) throws Exception {
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
    public static final Notice loadByKey(int id) {
        return NoticeEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Notice insert() {
        Notice result = NoticeEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Notice asyncInsert() {
        Notice result = NoticeEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Notice insert2() {
        return NoticeEntity.insert2(this);
    }

    public final Notice asyncInsert2() {
        Notice result = NoticeEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Notice update() {
        return NoticeEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        NoticeEntity.asynchronousUpdate( this );
        return true;
    }

    public final Notice insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return NoticeEntity.delete(id);
    }

    public final void asyncDelete() {
        NoticeEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Notice clone2() {
        try{
            return (Notice) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
