package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - rank_clan
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Rank_clan extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 520044687; // com.sea.db.bean.Rank_clan

    public static String TABLENAME = "rank_clan";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "ccid", "icon", "cname", "currank", "renownAll", "renownWeek", "type", "curnum", "maxnum"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "INT", "VARCHAR", "INT", "INT", "INT", "INT", "INT", "INT"};


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
    public int icon;
    public String cname;
    public int currank;
    public int renownAll;
    public int renownWeek;
    public int type;
    public int curnum;
    public int maxnum;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Rank_clan setId(int id){
        this.id = id;
        return this;
    }

    public String getCcid(){
        return ccid;
    }

    public Rank_clan setCcid(String ccid){
        String _old = ccid;
        this.ccid = ccid;
        changeIt("ccid", _old, ccid);
        return this;
    }

    public int getIcon(){
        return icon;
    }

    public Rank_clan setIcon(int icon){
        int _old = icon;
        this.icon = icon;
        changeIt("icon", _old, icon);
        return this;
    }

    public Rank_clan changeIcon(int icon){
        return setIcon(this.icon + icon);
    }

    public Rank_clan changeIconWithMin(int icon, int _min){
        int _v2 = this.icon + icon;
        return setIcon(_v2 < _min  ? _min : _v2);
    }

    public Rank_clan changeIconWithMax(int icon, int _max){
        int _v2 = this.icon + icon;
        return setIcon(_v2 > _max  ? _max : _v2);
    }

    public Rank_clan changeIconWithMinMax(int icon, int _min, int _max){
        int _v2 = this.icon + icon;
        _v2 = _v2 > _max  ? _max : _v2;
        return setIcon(_v2 < _min  ? _min : _v2);
    }

    public String getCname(){
        return cname;
    }

    public Rank_clan setCname(String cname){
        String _old = cname;
        this.cname = cname;
        changeIt("cname", _old, cname);
        return this;
    }

    public int getCurrank(){
        return currank;
    }

    public Rank_clan setCurrank(int currank){
        int _old = currank;
        this.currank = currank;
        changeIt("currank", _old, currank);
        return this;
    }

    public Rank_clan changeCurrank(int currank){
        return setCurrank(this.currank + currank);
    }

    public Rank_clan changeCurrankWithMin(int currank, int _min){
        int _v2 = this.currank + currank;
        return setCurrank(_v2 < _min  ? _min : _v2);
    }

    public Rank_clan changeCurrankWithMax(int currank, int _max){
        int _v2 = this.currank + currank;
        return setCurrank(_v2 > _max  ? _max : _v2);
    }

    public Rank_clan changeCurrankWithMinMax(int currank, int _min, int _max){
        int _v2 = this.currank + currank;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurrank(_v2 < _min  ? _min : _v2);
    }

    public int getRenownall(){
        return renownAll;
    }

    public Rank_clan setRenownall(int renownAll){
        int _old = renownAll;
        this.renownAll = renownAll;
        changeIt("renownAll", _old, renownAll);
        return this;
    }

    public Rank_clan changeRenownall(int renownAll){
        return setRenownall(this.renownAll + renownAll);
    }

    public Rank_clan changeRenownallWithMin(int renownAll, int _min){
        int _v2 = this.renownAll + renownAll;
        return setRenownall(_v2 < _min  ? _min : _v2);
    }

    public Rank_clan changeRenownallWithMax(int renownAll, int _max){
        int _v2 = this.renownAll + renownAll;
        return setRenownall(_v2 > _max  ? _max : _v2);
    }

    public Rank_clan changeRenownallWithMinMax(int renownAll, int _min, int _max){
        int _v2 = this.renownAll + renownAll;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRenownall(_v2 < _min  ? _min : _v2);
    }

    public int getRenownweek(){
        return renownWeek;
    }

    public Rank_clan setRenownweek(int renownWeek){
        int _old = renownWeek;
        this.renownWeek = renownWeek;
        changeIt("renownWeek", _old, renownWeek);
        return this;
    }

    public Rank_clan changeRenownweek(int renownWeek){
        return setRenownweek(this.renownWeek + renownWeek);
    }

    public Rank_clan changeRenownweekWithMin(int renownWeek, int _min){
        int _v2 = this.renownWeek + renownWeek;
        return setRenownweek(_v2 < _min  ? _min : _v2);
    }

    public Rank_clan changeRenownweekWithMax(int renownWeek, int _max){
        int _v2 = this.renownWeek + renownWeek;
        return setRenownweek(_v2 > _max  ? _max : _v2);
    }

    public Rank_clan changeRenownweekWithMinMax(int renownWeek, int _min, int _max){
        int _v2 = this.renownWeek + renownWeek;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRenownweek(_v2 < _min  ? _min : _v2);
    }

    public int getType(){
        return type;
    }

    public Rank_clan setType(int type){
        int _old = type;
        this.type = type;
        changeIt("type", _old, type);
        return this;
    }

    public Rank_clan changeType(int type){
        return setType(this.type + type);
    }

    public Rank_clan changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Rank_clan changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Rank_clan changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public int getCurnum(){
        return curnum;
    }

    public Rank_clan setCurnum(int curnum){
        int _old = curnum;
        this.curnum = curnum;
        changeIt("curnum", _old, curnum);
        return this;
    }

    public Rank_clan changeCurnum(int curnum){
        return setCurnum(this.curnum + curnum);
    }

    public Rank_clan changeCurnumWithMin(int curnum, int _min){
        int _v2 = this.curnum + curnum;
        return setCurnum(_v2 < _min  ? _min : _v2);
    }

    public Rank_clan changeCurnumWithMax(int curnum, int _max){
        int _v2 = this.curnum + curnum;
        return setCurnum(_v2 > _max  ? _max : _v2);
    }

    public Rank_clan changeCurnumWithMinMax(int curnum, int _min, int _max){
        int _v2 = this.curnum + curnum;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurnum(_v2 < _min  ? _min : _v2);
    }

    public int getMaxnum(){
        return maxnum;
    }

    public Rank_clan setMaxnum(int maxnum){
        int _old = maxnum;
        this.maxnum = maxnum;
        changeIt("maxnum", _old, maxnum);
        return this;
    }

    public Rank_clan changeMaxnum(int maxnum){
        return setMaxnum(this.maxnum + maxnum);
    }

    public Rank_clan changeMaxnumWithMin(int maxnum, int _min){
        int _v2 = this.maxnum + maxnum;
        return setMaxnum(_v2 < _min  ? _min : _v2);
    }

    public Rank_clan changeMaxnumWithMax(int maxnum, int _max){
        int _v2 = this.maxnum + maxnum;
        return setMaxnum(_v2 > _max  ? _max : _v2);
    }

    public Rank_clan changeMaxnumWithMinMax(int maxnum, int _min, int _max){
        int _v2 = this.maxnum + maxnum;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMaxnum(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Rank_clan v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Rank_clan v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Rank_clan newRank_clan(Integer id, String ccid, Integer icon, String cname, Integer currank, Integer renownAll, Integer renownWeek, Integer type, Integer curnum, Integer maxnum) {
        Rank_clan result = new Rank_clan();
        result.id = id;
        result.ccid = ccid;
        result.icon = icon;
        result.cname = cname;
        result.currank = currank;
        result.renownAll = renownAll;
        result.renownWeek = renownWeek;
        result.type = type;
        result.curnum = curnum;
        result.maxnum = maxnum;
        return result;
    }

    public static Rank_clan newRank_clan(Rank_clan rank_clan) {
        Rank_clan result = new Rank_clan();
        result.id = rank_clan.id;
        result.ccid = rank_clan.ccid;
        result.icon = rank_clan.icon;
        result.cname = rank_clan.cname;
        result.currank = rank_clan.currank;
        result.renownAll = rank_clan.renownAll;
        result.renownWeek = rank_clan.renownWeek;
        result.type = rank_clan.type;
        result.curnum = rank_clan.curnum;
        result.maxnum = rank_clan.maxnum;
        return result;
    }

    public Rank_clan createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, ccid);
        B2OutputStream.writeObject(_out, icon);
        B2OutputStream.writeObject(_out, cname);
        B2OutputStream.writeObject(_out, currank);
        B2OutputStream.writeObject(_out, renownAll);
        B2OutputStream.writeObject(_out, renownWeek);
        B2OutputStream.writeObject(_out, type);
        B2OutputStream.writeObject(_out, curnum);
        B2OutputStream.writeObject(_out, maxnum);
    }

    public static final Rank_clan readObject(InputStream _in) throws Exception {
        Rank_clan result = new Rank_clan();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.ccid = (String) B2InputStream.readObject(_in);
        result.icon = (Integer) B2InputStream.readObject(_in);
        result.cname = (String) B2InputStream.readObject(_in);
        result.currank = (Integer) B2InputStream.readObject(_in);
        result.renownAll = (Integer) B2InputStream.readObject(_in);
        result.renownWeek = (Integer) B2InputStream.readObject(_in);
        result.type = (Integer) B2InputStream.readObject(_in);
        result.curnum = (Integer) B2InputStream.readObject(_in);
        result.maxnum = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getRank_clan(){
        Rank_clan rank_clan = null; // rank_clan
        { // new and insert Rank_clan (rank_clan)
            int id = 0; 	// id
            String ccid = ""; 	// ccid
            int icon = 0; 	// icon
            String cname = ""; 	// cname
            int currank = 0; 	// currank
            int renownAll = 0; 	// renownAll
            int renownWeek = 0; 	// renownWeek
            int type = 0; 	// type
            int curnum = 0; 	// curnum
            int maxnum = 0; 	// maxnum
            rank_clan = Rank_clan.newRank_clan(id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum);
        }
        rank_clan = rank_clan.insert();

        int id = rank_clan.getId(); 	// id
        String ccid = rank_clan.getCcid(); 	// ccid
        int icon = rank_clan.getIcon(); 	// icon
        String cname = rank_clan.getCname(); 	// cname
        int currank = rank_clan.getCurrank(); 	// currank
        int renownAll = rank_clan.getRenownall(); 	// renownAll
        int renownWeek = rank_clan.getRenownweek(); 	// renownWeek
        int type = rank_clan.getType(); 	// type
        int curnum = rank_clan.getCurnum(); 	// curnum
        int maxnum = rank_clan.getMaxnum(); 	// maxnum
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
        case "icon":
            return icon;
        case "currank":
            return currank;
        case "renownAll":
            return renownAll;
        case "renownWeek":
            return renownWeek;
        case "type":
            return type;
        case "curnum":
            return curnum;
        case "maxnum":
            return maxnum;
        }
        return 0;
    }

    public Rank_clan setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Rank_clan setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "icon":
            return setIcon(value2);
        case "currank":
            return setCurrank(value2);
        case "renownAll":
            return setRenownall(value2);
        case "renownWeek":
            return setRenownweek(value2);
        case "type":
            return setType(value2);
        case "curnum":
            return setCurnum(value2);
        case "maxnum":
            return setMaxnum(value2);
        }
        return this;
    }

    public Rank_clan changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Rank_clan changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "icon":
            return changeIcon(value2);
        case "currank":
            return changeCurrank(value2);
        case "renownAll":
            return changeRenownall(value2);
        case "renownWeek":
            return changeRenownweek(value2);
        case "type":
            return changeType(value2);
        case "curnum":
            return changeCurnum(value2);
        case "maxnum":
            return changeMaxnum(value2);
        }
        return this;
    }

    public Rank_clan changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Rank_clan changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "icon":
            return changeIconWithMin(value2, _min);
        case "currank":
            return changeCurrankWithMin(value2, _min);
        case "renownAll":
            return changeRenownallWithMin(value2, _min);
        case "renownWeek":
            return changeRenownweekWithMin(value2, _min);
        case "type":
            return changeTypeWithMin(value2, _min);
        case "curnum":
            return changeCurnumWithMin(value2, _min);
        case "maxnum":
            return changeMaxnumWithMin(value2, _min);
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

    public Rank_clan setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Rank_clan setDouble(String fieldEn, double value2) {
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
        case "cname": 
            return cname;
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
        case "icon":
            return icon;
        case "cname":
            return cname;
        case "currank":
            return currank;
        case "renownAll":
            return renownAll;
        case "renownWeek":
            return renownWeek;
        case "type":
            return type;
        case "curnum":
            return curnum;
        case "maxnum":
            return maxnum;
        }
        return null;
    }

    public Rank_clan setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Rank_clan setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "ccid":
            return setCcid(value2);
        case "cname":
            return setCname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Rank_clan setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Rank_clan setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Rank_clan");
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("icon", icon);
        result.put("cname", cname);
        result.put("currank", currank);
        result.put("renownAll", renownAll);
        result.put("renownWeek", renownWeek);
        result.put("type", type);
        result.put("curnum", curnum);
        result.put("maxnum", maxnum);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("icon", icon);
        result.put("cname", cname);
        result.put("currank", currank);
        result.put("renownAll", renownAll);
        result.put("renownWeek", renownWeek);
        result.put("type", type);
        result.put("curnum", curnum);
        result.put("maxnum", maxnum);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Rank_clan");
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("icon", icon);
        result.put("cname", cname);
        result.put("currank", currank);
        result.put("renownAll", renownAll);
        result.put("renownWeek", renownWeek);
        result.put("type", type);
        result.put("curnum", curnum);
        result.put("maxnum", maxnum);
        return result;
    }

    public Rank_clan mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        Integer icon = (Integer)e.get("icon");
        String cname = (String)e.get("cname");
        Integer currank = (Integer)e.get("currank");
        Integer renownAll = (Integer)e.get("renownAll");
        Integer renownWeek = (Integer)e.get("renownWeek");
        Integer type = (Integer)e.get("type");
        Integer curnum = (Integer)e.get("curnum");
        Integer maxnum = (Integer)e.get("maxnum");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(icon == null) icon = 0;
        if(cname == null) cname = "";
        if(currank == null) currank = 0;
        if(renownAll == null) renownAll = 0;
        if(renownWeek == null) renownWeek = 0;
        if(type == null) type = 0;
        if(curnum == null) curnum = 0;
        if(maxnum == null) maxnum = 0;

        setId(id);
        setCcid(ccid);
        setIcon(icon);
        setCname(cname);
        setCurrank(currank);
        setRenownall(renownAll);
        setRenownweek(renownWeek);
        setType(type);
        setCurnum(curnum);
        setMaxnum(maxnum);

        return this;
    }

    public static final Rank_clan mapTo(Map e){
        Rank_clan result = new Rank_clan();

        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        Integer icon = (Integer)e.get("icon");
        String cname = (String)e.get("cname");
        Integer currank = (Integer)e.get("currank");
        Integer renownAll = (Integer)e.get("renownAll");
        Integer renownWeek = (Integer)e.get("renownWeek");
        Integer type = (Integer)e.get("type");
        Integer curnum = (Integer)e.get("curnum");
        Integer maxnum = (Integer)e.get("maxnum");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(icon == null) icon = 0;
        if(cname == null) cname = "";
        if(currank == null) currank = 0;
        if(renownAll == null) renownAll = 0;
        if(renownWeek == null) renownWeek = 0;
        if(type == null) type = 0;
        if(curnum == null) curnum = 0;
        if(maxnum == null) maxnum = 0;

        result.id = id;
        result.ccid = ccid;
        result.icon = icon;
        result.cname = cname;
        result.currank = currank;
        result.renownAll = renownAll;
        result.renownWeek = renownWeek;
        result.type = type;
        result.curnum = curnum;
        result.maxnum = maxnum;

        return result;
    }

    public static final Rank_clan originalTo(Map e){
        Rank_clan result = new Rank_clan();

        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        Integer icon = (Integer)e.get("icon");
        String cname = (String)e.get("cname");
        Integer currank = (Integer)e.get("currank");
        Integer renownAll = (Integer)e.get("renownAll");
        Integer renownWeek = (Integer)e.get("renownWeek");
        Integer type = (Integer)e.get("type");
        Integer curnum = (Integer)e.get("curnum");
        Integer maxnum = (Integer)e.get("maxnum");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(icon == null) icon = 0;
        if(cname == null) cname = "";
        if(currank == null) currank = 0;
        if(renownAll == null) renownAll = 0;
        if(renownWeek == null) renownWeek = 0;
        if(type == null) type = 0;
        if(curnum == null) curnum = 0;
        if(maxnum == null) maxnum = 0;

        result.id = id;
        result.ccid = ccid;
        result.icon = icon;
        result.cname = cname;
        result.currank = currank;
        result.renownAll = renownAll;
        result.renownWeek = renownWeek;
        result.type = type;
        result.curnum = curnum;
        result.maxnum = maxnum;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Rank_clan createFor(byte[] b) throws Exception {
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
    public static final Rank_clan loadByKey(int id) {
        return Rank_clanEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Rank_clan insert() {
        Rank_clan result = Rank_clanEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Rank_clan asyncInsert() {
        Rank_clan result = Rank_clanEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Rank_clan insert2() {
        return Rank_clanEntity.insert2(this);
    }

    public final Rank_clan asyncInsert2() {
        Rank_clan result = Rank_clanEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Rank_clan update() {
        return Rank_clanEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Rank_clanEntity.asynchronousUpdate( this );
        return true;
    }

    public final Rank_clan insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Rank_clanEntity.delete(id);
    }

    public final void asyncDelete() {
        Rank_clanEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Rank_clan clone2() {
        try{
            return (Rank_clan) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
