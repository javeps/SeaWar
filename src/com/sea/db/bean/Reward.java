package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - reward
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Reward extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -36829463; // com.sea.db.bean.Reward

    public static String TABLENAME = "reward";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "pcid", "pname", "type", "reward", "createtime"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "TEXT", "BIGINT"};


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
    public int pcid;
    public String pname;
    public int type;
    public String reward;
    public long createtime;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Reward setId(int id){
        this.id = id;
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Reward setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Reward changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Reward changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Reward changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Reward changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPname(){
        return pname;
    }

    public Reward setPname(String pname){
        String _old = pname;
        this.pname = pname;
        changeIt("pname", _old, pname);
        return this;
    }

    public int getType(){
        return type;
    }

    public Reward setType(int type){
        int _old = type;
        this.type = type;
        changeIt("type", _old, type);
        return this;
    }

    public Reward changeType(int type){
        return setType(this.type + type);
    }

    public Reward changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Reward changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Reward changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public String getReward(){
        return reward;
    }

    public Reward setReward(String reward){
        String _old = reward;
        this.reward = reward;
        changeIt("reward", _old, reward);
        return this;
    }

    public long getCreatetime(){
        return createtime;
    }

    public Reward setCreatetime(long createtime){
        long _old = createtime;
        this.createtime = createtime;
        changeIt("createtime", _old, createtime);
        return this;
    }

    public Reward changeCreatetime(long createtime){
        return setCreatetime(this.createtime + createtime);
    }

    public Reward changeCreatetimeWithMin(long createtime, long _min){
        long _v2 = this.createtime + createtime;
        return setCreatetime(_v2 < _min  ? _min : _v2);
    }

    public Reward changeCreatetimeWithMax(long createtime, long _max){
        long _v2 = this.createtime + createtime;
        return setCreatetime(_v2 > _max  ? _max : _v2);
    }

    public Reward changeCreatetimeWithMinMax(long createtime, long _min, long _max){
        long _v2 = this.createtime + createtime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCreatetime(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Reward v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Reward v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Reward newReward(Integer id, Integer pcid, String pname, Integer type, String reward, Long createtime) {
        Reward result = new Reward();
        result.id = id;
        result.pcid = pcid;
        result.pname = pname;
        result.type = type;
        result.reward = reward;
        result.createtime = createtime;
        return result;
    }

    public static Reward newReward(Reward reward) {
        Reward result = new Reward();
        result.id = reward.id;
        result.pcid = reward.pcid;
        result.pname = reward.pname;
        result.type = reward.type;
        result.reward = reward.reward;
        result.createtime = reward.createtime;
        return result;
    }

    public Reward createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, pcid);
        B2OutputStream.writeObject(_out, pname);
        B2OutputStream.writeObject(_out, type);
        B2OutputStream.writeObject(_out, reward);
        B2OutputStream.writeObject(_out, createtime);
    }

    public static final Reward readObject(InputStream _in) throws Exception {
        Reward result = new Reward();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        result.pname = (String) B2InputStream.readObject(_in);
        result.type = (Integer) B2InputStream.readObject(_in);
        result.reward = (String) B2InputStream.readObject(_in);
        result.createtime = (Long) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getReward(){
        Reward reward = null; // reward
        { // new and insert Reward (reward)
            int id = 0; 	// id
            int pcid = 0; 	// pcid
            String pname = ""; 	// pname
            int type = 0; 	// type
            String reward = ""; 	// reward
            long createtime = 0; 	// createtime
            reward = Reward.newReward(id, pcid, pname, type, reward, createtime);
        }
        reward = reward.insert();

        int id = reward.getId(); 	// id
        int pcid = reward.getPcid(); 	// pcid
        String pname = reward.getPname(); 	// pname
        int type = reward.getType(); 	// type
        String reward = reward.getReward(); 	// reward
        long createtime = reward.getCreatetime(); 	// createtime
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
        case "type":
            return type;
        }
        return 0;
    }

    public Reward setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Reward setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "pcid":
            return setPcid(value2);
        case "type":
            return setType(value2);
        }
        return this;
    }

    public Reward changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Reward changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "pcid":
            return changePcid(value2);
        case "type":
            return changeType(value2);
        }
        return this;
    }

    public Reward changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Reward changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "pcid":
            return changePcidWithMin(value2, _min);
        case "type":
            return changeTypeWithMin(value2, _min);
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

    public Reward setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Reward setDouble(String fieldEn, double value2) {
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
        case "pname": 
            return pname;
        case "reward": 
            return reward;
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
        case "pcid":
            return pcid;
        case "pname":
            return pname;
        case "type":
            return type;
        case "reward":
            return reward;
        case "createtime":
            return createtime;
        }
        return null;
    }

    public Reward setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Reward setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "pname":
            return setPname(value2);
        case "reward":
            return setReward(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Reward setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Reward setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Reward");
        result.put("id", id);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("type", type);
        result.put("reward", reward);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("type", type);
        result.put("reward", reward);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Reward");
        result.put("id", id);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("type", type);
        result.put("reward", reward);
        result.put("createtime", createtime);
        return result;
    }

    public Reward mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer type = (Integer)e.get("type");
        String reward = (String)e.get("reward");
        Long createtime = (Long)e.get("createtime");

        if(id == null) id = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(type == null) type = 0;
        if(reward == null) reward = "";
        if(createtime == null) createtime = 0L;

        setId(id);
        setPcid(pcid);
        setPname(pname);
        setType(type);
        setReward(reward);
        setCreatetime(createtime);

        return this;
    }

    public static final Reward mapTo(Map e){
        Reward result = new Reward();

        Integer id = (Integer)e.get("id");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer type = (Integer)e.get("type");
        String reward = (String)e.get("reward");
        Long createtime = (Long)e.get("createtime");

        if(id == null) id = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(type == null) type = 0;
        if(reward == null) reward = "";
        if(createtime == null) createtime = 0L;

        result.id = id;
        result.pcid = pcid;
        result.pname = pname;
        result.type = type;
        result.reward = reward;
        result.createtime = createtime;

        return result;
    }

    public static final Reward originalTo(Map e){
        Reward result = new Reward();

        Integer id = (Integer)e.get("id");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer type = (Integer)e.get("type");
        String reward = (String)e.get("reward");
        Long createtime = (Long)e.get("createtime");

        if(id == null) id = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(type == null) type = 0;
        if(reward == null) reward = "";
        if(createtime == null) createtime = 0L;

        result.id = id;
        result.pcid = pcid;
        result.pname = pname;
        result.type = type;
        result.reward = reward;
        result.createtime = createtime;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Reward createFor(byte[] b) throws Exception {
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
    public static final Reward loadByKey(int id) {
        return RewardEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Reward insert() {
        Reward result = RewardEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Reward asyncInsert() {
        Reward result = RewardEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Reward insert2() {
        return RewardEntity.insert2(this);
    }

    public final Reward asyncInsert2() {
        Reward result = RewardEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Reward update() {
        return RewardEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        RewardEntity.asynchronousUpdate( this );
        return true;
    }

    public final Reward insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return RewardEntity.delete(id);
    }

    public final void asyncDelete() {
        RewardEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Reward clone2() {
        try{
            return (Reward) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
