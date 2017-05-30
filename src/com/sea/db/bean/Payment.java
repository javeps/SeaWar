package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - payment
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Payment extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1265922444; // com.sea.db.bean.Payment

    public static String TABLENAME = "payment";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "unid", "chid", "svcid", "pcid", "channel", "money", "status", "gem", "createtime", "finishtime", "productUUID", "query", "phoneinfo"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR", "INT", "INT", "VARCHAR", "DOUBLE", "INT", "INT", "DATETIME", "BIGINT", "VARCHAR", "TEXT", "TEXT"};


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
    public String unid;
    public String chid;
    public int svcid;
    public int pcid;
    public String channel;
    public double money;
    public int status;
    public int gem;
    public java.util.Date createtime;
    public long finishtime;
    public String productUUID;
    public String query;
    public String phoneinfo;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Payment setId(int id){
        this.id = id;
        return this;
    }

    public String getUnid(){
        return unid;
    }

    public Payment setUnid(String unid){
        String _old = unid;
        this.unid = unid;
        changeIt("unid", _old, unid);
        return this;
    }

    public String getChid(){
        return chid;
    }

    public Payment setChid(String chid){
        String _old = chid;
        this.chid = chid;
        changeIt("chid", _old, chid);
        return this;
    }

    public int getSvcid(){
        return svcid;
    }

    public Payment setSvcid(int svcid){
        int _old = svcid;
        this.svcid = svcid;
        changeIt("svcid", _old, svcid);
        return this;
    }

    public Payment changeSvcid(int svcid){
        return setSvcid(this.svcid + svcid);
    }

    public Payment changeSvcidWithMin(int svcid, int _min){
        int _v2 = this.svcid + svcid;
        return setSvcid(_v2 < _min  ? _min : _v2);
    }

    public Payment changeSvcidWithMax(int svcid, int _max){
        int _v2 = this.svcid + svcid;
        return setSvcid(_v2 > _max  ? _max : _v2);
    }

    public Payment changeSvcidWithMinMax(int svcid, int _min, int _max){
        int _v2 = this.svcid + svcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setSvcid(_v2 < _min  ? _min : _v2);
    }

    public int getPcid(){
        return pcid;
    }

    public Payment setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Payment changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Payment changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Payment changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Payment changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getChannel(){
        return channel;
    }

    public Payment setChannel(String channel){
        String _old = channel;
        this.channel = channel;
        changeIt("channel", _old, channel);
        return this;
    }

    public double getMoney(){
        return money;
    }

    public Payment setMoney(double money){
        double _old = money;
        this.money = money;
        changeIt("money", _old, money);
        return this;
    }

    public Payment changeMoney(double money){
        return setMoney(this.money + money);
    }

    public Payment changeMoneyWithMin(double money, double _min){
        double _v2 = this.money + money;
        return setMoney(_v2 < _min  ? _min : _v2);
    }

    public Payment changeMoneyWithMax(double money, double _max){
        double _v2 = this.money + money;
        return setMoney(_v2 > _max  ? _max : _v2);
    }

    public Payment changeMoneyWithMinMax(double money, double _min, double _max){
        double _v2 = this.money + money;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMoney(_v2 < _min  ? _min : _v2);
    }

    public int getStatus(){
        return status;
    }

    public Payment setStatus(int status){
        int _old = status;
        this.status = status;
        changeIt("status", _old, status);
        return this;
    }

    public Payment changeStatus(int status){
        return setStatus(this.status + status);
    }

    public Payment changeStatusWithMin(int status, int _min){
        int _v2 = this.status + status;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public Payment changeStatusWithMax(int status, int _max){
        int _v2 = this.status + status;
        return setStatus(_v2 > _max  ? _max : _v2);
    }

    public Payment changeStatusWithMinMax(int status, int _min, int _max){
        int _v2 = this.status + status;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatus(_v2 < _min  ? _min : _v2);
    }

    public int getGem(){
        return gem;
    }

    public Payment setGem(int gem){
        int _old = gem;
        this.gem = gem;
        changeIt("gem", _old, gem);
        return this;
    }

    public Payment changeGem(int gem){
        return setGem(this.gem + gem);
    }

    public Payment changeGemWithMin(int gem, int _min){
        int _v2 = this.gem + gem;
        return setGem(_v2 < _min  ? _min : _v2);
    }

    public Payment changeGemWithMax(int gem, int _max){
        int _v2 = this.gem + gem;
        return setGem(_v2 > _max  ? _max : _v2);
    }

    public Payment changeGemWithMinMax(int gem, int _min, int _max){
        int _v2 = this.gem + gem;
        _v2 = _v2 > _max  ? _max : _v2;
        return setGem(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Payment setCreatetime(java.util.Date createtime){
        java.util.Date _old = createtime;
        this.createtime = createtime;
        changeIt("createtime", _old, createtime);
        return this;
    }

    public long getFinishtime(){
        return finishtime;
    }

    public Payment setFinishtime(long finishtime){
        long _old = finishtime;
        this.finishtime = finishtime;
        changeIt("finishtime", _old, finishtime);
        return this;
    }

    public Payment changeFinishtime(long finishtime){
        return setFinishtime(this.finishtime + finishtime);
    }

    public Payment changeFinishtimeWithMin(long finishtime, long _min){
        long _v2 = this.finishtime + finishtime;
        return setFinishtime(_v2 < _min  ? _min : _v2);
    }

    public Payment changeFinishtimeWithMax(long finishtime, long _max){
        long _v2 = this.finishtime + finishtime;
        return setFinishtime(_v2 > _max  ? _max : _v2);
    }

    public Payment changeFinishtimeWithMinMax(long finishtime, long _min, long _max){
        long _v2 = this.finishtime + finishtime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setFinishtime(_v2 < _min  ? _min : _v2);
    }

    public String getProductuuid(){
        return productUUID;
    }

    public Payment setProductuuid(String productUUID){
        String _old = productUUID;
        this.productUUID = productUUID;
        changeIt("productUUID", _old, productUUID);
        return this;
    }

    public String getQuery(){
        return query;
    }

    public Payment setQuery(String query){
        String _old = query;
        this.query = query;
        changeIt("query", _old, query);
        return this;
    }

    public String getPhoneinfo(){
        return phoneinfo;
    }

    public Payment setPhoneinfo(String phoneinfo){
        String _old = phoneinfo;
        this.phoneinfo = phoneinfo;
        changeIt("phoneinfo", _old, phoneinfo);
        return this;
    }

    public int compareTo(Payment v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Payment v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Payment newPayment(Integer id, String unid, String chid, Integer svcid, Integer pcid, String channel, Double money, Integer status, Integer gem, java.util.Date createtime, Long finishtime, String productUUID, String query, String phoneinfo) {
        Payment result = new Payment();
        result.id = id;
        result.unid = unid;
        result.chid = chid;
        result.svcid = svcid;
        result.pcid = pcid;
        result.channel = channel;
        result.money = money;
        result.status = status;
        result.gem = gem;
        result.createtime = createtime;
        result.finishtime = finishtime;
        result.productUUID = productUUID;
        result.query = query;
        result.phoneinfo = phoneinfo;
        return result;
    }

    public static Payment newPayment(Payment payment) {
        Payment result = new Payment();
        result.id = payment.id;
        result.unid = payment.unid;
        result.chid = payment.chid;
        result.svcid = payment.svcid;
        result.pcid = payment.pcid;
        result.channel = payment.channel;
        result.money = payment.money;
        result.status = payment.status;
        result.gem = payment.gem;
        result.createtime = payment.createtime;
        result.finishtime = payment.finishtime;
        result.productUUID = payment.productUUID;
        result.query = payment.query;
        result.phoneinfo = payment.phoneinfo;
        return result;
    }

    public Payment createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, unid);
        B2OutputStream.writeObject(_out, chid);
        B2OutputStream.writeObject(_out, svcid);
        B2OutputStream.writeObject(_out, pcid);
        B2OutputStream.writeObject(_out, channel);
        B2OutputStream.writeObject(_out, money);
        B2OutputStream.writeObject(_out, status);
        B2OutputStream.writeObject(_out, gem);
        B2OutputStream.writeObject(_out, createtime);
        B2OutputStream.writeObject(_out, finishtime);
        B2OutputStream.writeObject(_out, productUUID);
        B2OutputStream.writeObject(_out, query);
        B2OutputStream.writeObject(_out, phoneinfo);
    }

    public static final Payment readObject(InputStream _in) throws Exception {
        Payment result = new Payment();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.unid = (String) B2InputStream.readObject(_in);
        result.chid = (String) B2InputStream.readObject(_in);
        result.svcid = (Integer) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        result.channel = (String) B2InputStream.readObject(_in);
        result.money = (Double) B2InputStream.readObject(_in);
        result.status = (Integer) B2InputStream.readObject(_in);
        result.gem = (Integer) B2InputStream.readObject(_in);
        result.createtime = (java.util.Date) B2InputStream.readObject(_in);
        result.finishtime = (Long) B2InputStream.readObject(_in);
        result.productUUID = (String) B2InputStream.readObject(_in);
        result.query = (String) B2InputStream.readObject(_in);
        result.phoneinfo = (String) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getPayment(){
        Payment payment = null; // payment
        { // new and insert Payment (payment)
            int id = 0; 	// id
            String unid = ""; 	// unid
            String chid = ""; 	// chid
            int svcid = 0; 	// svcid
            int pcid = 0; 	// pcid
            String channel = ""; 	// channel
            double money = 0.0; 	// money
            int status = 0; 	// status
            int gem = 0; 	// gem
            Date createtime = new Date(); 	// createtime
            long finishtime = 0; 	// finishtime
            String productUUID = ""; 	// productUUID
            String query = ""; 	// query
            String phoneinfo = ""; 	// phoneinfo
            payment = Payment.newPayment(id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo);
        }
        payment = payment.insert();

        int id = payment.getId(); 	// id
        String unid = payment.getUnid(); 	// unid
        String chid = payment.getChid(); 	// chid
        int svcid = payment.getSvcid(); 	// svcid
        int pcid = payment.getPcid(); 	// pcid
        String channel = payment.getChannel(); 	// channel
        double money = payment.getMoney(); 	// money
        int status = payment.getStatus(); 	// status
        int gem = payment.getGem(); 	// gem
        Date createtime = payment.getCreatetime(); 	// createtime
        long finishtime = payment.getFinishtime(); 	// finishtime
        String productUUID = payment.getProductuuid(); 	// productUUID
        String query = payment.getQuery(); 	// query
        String phoneinfo = payment.getPhoneinfo(); 	// phoneinfo
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
        case "svcid":
            return svcid;
        case "pcid":
            return pcid;
        case "status":
            return status;
        case "gem":
            return gem;
        }
        return 0;
    }

    public Payment setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Payment setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "svcid":
            return setSvcid(value2);
        case "pcid":
            return setPcid(value2);
        case "status":
            return setStatus(value2);
        case "gem":
            return setGem(value2);
        }
        return this;
    }

    public Payment changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Payment changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "svcid":
            return changeSvcid(value2);
        case "pcid":
            return changePcid(value2);
        case "status":
            return changeStatus(value2);
        case "gem":
            return changeGem(value2);
        }
        return this;
    }

    public Payment changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Payment changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "svcid":
            return changeSvcidWithMin(value2, _min);
        case "pcid":
            return changePcidWithMin(value2, _min);
        case "status":
            return changeStatusWithMin(value2, _min);
        case "gem":
            return changeGemWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        case "money":
            return money;
        }
        return 0;
    }

    public Payment setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Payment setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case "money":
            return setMoney(value2);
        }
        return this;
    }

    public String valueZhStr(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueStr(fieldEn);
    }

    public String valueStr(String fieldEn){
        switch(fieldEn){
        case "unid": 
            return unid;
        case "chid": 
            return chid;
        case "channel": 
            return channel;
        case "productUUID": 
            return productUUID;
        case "query": 
            return query;
        case "phoneinfo": 
            return phoneinfo;
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
        case "unid":
            return unid;
        case "chid":
            return chid;
        case "svcid":
            return svcid;
        case "pcid":
            return pcid;
        case "channel":
            return channel;
        case "money":
            return money;
        case "status":
            return status;
        case "gem":
            return gem;
        case "createtime":
            return createtime;
        case "finishtime":
            return finishtime;
        case "productUUID":
            return productUUID;
        case "query":
            return query;
        case "phoneinfo":
            return phoneinfo;
        }
        return null;
    }

    public Payment setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Payment setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "unid":
            return setUnid(value2);
        case "chid":
            return setChid(value2);
        case "channel":
            return setChannel(value2);
        case "productUUID":
            return setProductuuid(value2);
        case "query":
            return setQuery(value2);
        case "phoneinfo":
            return setPhoneinfo(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Payment setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Payment setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Payment");
        result.put("id", id);
        result.put("unid", unid);
        result.put("chid", chid);
        result.put("svcid", svcid);
        result.put("pcid", pcid);
        result.put("channel", channel);
        result.put("money", money);
        result.put("status", status);
        result.put("gem", gem);
        result.put("createtime", createtime);
        result.put("finishtime", finishtime);
        result.put("productUUID", productUUID);
        result.put("query", query);
        result.put("phoneinfo", phoneinfo);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("unid", unid);
        result.put("chid", chid);
        result.put("svcid", svcid);
        result.put("pcid", pcid);
        result.put("channel", channel);
        result.put("money", money);
        result.put("status", status);
        result.put("gem", gem);
        result.put("createtime", createtime);
        result.put("finishtime", finishtime);
        result.put("productUUID", productUUID);
        result.put("query", query);
        result.put("phoneinfo", phoneinfo);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Payment");
        result.put("id", id);
        result.put("unid", unid);
        result.put("chid", chid);
        result.put("svcid", svcid);
        result.put("pcid", pcid);
        result.put("channel", channel);
        result.put("money", money);
        result.put("status", status);
        result.put("gem", gem);
        result.put("createtime", createtime);
        result.put("finishtime", finishtime);
        result.put("productUUID", productUUID);
        result.put("query", query);
        result.put("phoneinfo", phoneinfo);
        return result;
    }

    public Payment mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String unid = (String)e.get("unid");
        String chid = (String)e.get("chid");
        Integer svcid = (Integer)e.get("svcid");
        Integer pcid = (Integer)e.get("pcid");
        String channel = (String)e.get("channel");
        Double money = (Double)e.get("money");
        Integer status = (Integer)e.get("status");
        Integer gem = (Integer)e.get("gem");
        java.util.Date createtime = (java.util.Date)e.get("createtime");
        Long finishtime = (Long)e.get("finishtime");
        String productUUID = (String)e.get("productUUID");
        String query = (String)e.get("query");
        String phoneinfo = (String)e.get("phoneinfo");

        if(id == null) id = 0;
        if(unid == null) unid = "";
        if(chid == null) chid = "";
        if(svcid == null) svcid = 0;
        if(pcid == null) pcid = 0;
        if(channel == null) channel = "";
        if(money == null) money = 0.0;
        if(status == null) status = 0;
        if(gem == null) gem = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(finishtime == null) finishtime = 0L;
        if(productUUID == null) productUUID = "";
        if(query == null) query = "";
        if(phoneinfo == null) phoneinfo = "";

        setId(id);
        setUnid(unid);
        setChid(chid);
        setSvcid(svcid);
        setPcid(pcid);
        setChannel(channel);
        setMoney(money);
        setStatus(status);
        setGem(gem);
        setCreatetime(createtime);
        setFinishtime(finishtime);
        setProductuuid(productUUID);
        setQuery(query);
        setPhoneinfo(phoneinfo);

        return this;
    }

    public static final Payment mapTo(Map e){
        Payment result = new Payment();

        Integer id = (Integer)e.get("id");
        String unid = (String)e.get("unid");
        String chid = (String)e.get("chid");
        Integer svcid = (Integer)e.get("svcid");
        Integer pcid = (Integer)e.get("pcid");
        String channel = (String)e.get("channel");
        Double money = (Double)e.get("money");
        Integer status = (Integer)e.get("status");
        Integer gem = (Integer)e.get("gem");
        java.util.Date createtime = (java.util.Date)e.get("createtime");
        Long finishtime = (Long)e.get("finishtime");
        String productUUID = (String)e.get("productUUID");
        String query = (String)e.get("query");
        String phoneinfo = (String)e.get("phoneinfo");

        if(id == null) id = 0;
        if(unid == null) unid = "";
        if(chid == null) chid = "";
        if(svcid == null) svcid = 0;
        if(pcid == null) pcid = 0;
        if(channel == null) channel = "";
        if(money == null) money = 0.0;
        if(status == null) status = 0;
        if(gem == null) gem = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(finishtime == null) finishtime = 0L;
        if(productUUID == null) productUUID = "";
        if(query == null) query = "";
        if(phoneinfo == null) phoneinfo = "";

        result.id = id;
        result.unid = unid;
        result.chid = chid;
        result.svcid = svcid;
        result.pcid = pcid;
        result.channel = channel;
        result.money = money;
        result.status = status;
        result.gem = gem;
        result.createtime = createtime;
        result.finishtime = finishtime;
        result.productUUID = productUUID;
        result.query = query;
        result.phoneinfo = phoneinfo;

        return result;
    }

    public static final Payment originalTo(Map e){
        Payment result = new Payment();

        Integer id = (Integer)e.get("id");
        String unid = (String)e.get("unid");
        String chid = (String)e.get("chid");
        Integer svcid = (Integer)e.get("svcid");
        Integer pcid = (Integer)e.get("pcid");
        String channel = (String)e.get("channel");
        Double money = (Double)e.get("money");
        Integer status = (Integer)e.get("status");
        Integer gem = (Integer)e.get("gem");
        java.util.Date createtime = (java.util.Date)e.get("createtime");
        Long finishtime = (Long)e.get("finishtime");
        String productUUID = (String)e.get("productUUID");
        String query = (String)e.get("query");
        String phoneinfo = (String)e.get("phoneinfo");

        if(id == null) id = 0;
        if(unid == null) unid = "";
        if(chid == null) chid = "";
        if(svcid == null) svcid = 0;
        if(pcid == null) pcid = 0;
        if(channel == null) channel = "";
        if(money == null) money = 0.0;
        if(status == null) status = 0;
        if(gem == null) gem = 0;
        if(createtime == null) createtime = new java.util.Date();
        if(finishtime == null) finishtime = 0L;
        if(productUUID == null) productUUID = "";
        if(query == null) query = "";
        if(phoneinfo == null) phoneinfo = "";

        result.id = id;
        result.unid = unid;
        result.chid = chid;
        result.svcid = svcid;
        result.pcid = pcid;
        result.channel = channel;
        result.money = money;
        result.status = status;
        result.gem = gem;
        result.createtime = createtime;
        result.finishtime = finishtime;
        result.productUUID = productUUID;
        result.query = query;
        result.phoneinfo = phoneinfo;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Payment createFor(byte[] b) throws Exception {
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
    public static final Payment loadByKey(int id) {
        return PaymentEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Payment insert() {
        Payment result = PaymentEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Payment asyncInsert() {
        Payment result = PaymentEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Payment insert2() {
        return PaymentEntity.insert2(this);
    }

    public final Payment asyncInsert2() {
        Payment result = PaymentEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Payment update() {
        return PaymentEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        PaymentEntity.asynchronousUpdate( this );
        return true;
    }

    public final Payment insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return PaymentEntity.delete(id);
    }

    public final void asyncDelete() {
        PaymentEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Payment clone2() {
        try{
            return (Payment) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
