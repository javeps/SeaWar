package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - rank_player
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Rank_player extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1918930650; // com.sea.db.bean.Rank_player

    public static String TABLENAME = "rank_player";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "ucid", "pcid", "pname", "pexp", "clancid", "clanIcon", "clanName", "currank", "renown", "weekAddRenown", "type"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "VARCHAR", "INT", "VARCHAR", "INT", "VARCHAR", "INT", "INT", "INT", "INT"};


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
    public int pcid;
    public String pname;
    public int pexp;
    public String clancid;
    public int clanIcon;
    public String clanName;
    public int currank;
    public int renown;
    public int weekAddRenown;
    public int type;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Rank_player setId(int id){
        this.id = id;
        return this;
    }

    public int getUcid(){
        return ucid;
    }

    public Rank_player setUcid(int ucid){
        int _old = ucid;
        this.ucid = ucid;
        changeIt("ucid", _old, ucid);
        return this;
    }

    public Rank_player changeUcid(int ucid){
        return setUcid(this.ucid + ucid);
    }

    public Rank_player changeUcidWithMin(int ucid, int _min){
        int _v2 = this.ucid + ucid;
        return setUcid(_v2 < _min  ? _min : _v2);
    }

    public Rank_player changeUcidWithMax(int ucid, int _max){
        int _v2 = this.ucid + ucid;
        return setUcid(_v2 > _max  ? _max : _v2);
    }

    public Rank_player changeUcidWithMinMax(int ucid, int _min, int _max){
        int _v2 = this.ucid + ucid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setUcid(_v2 < _min  ? _min : _v2);
    }

    public int getPcid(){
        return pcid;
    }

    public Rank_player setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Rank_player changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Rank_player changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Rank_player changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Rank_player changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPname(){
        return pname;
    }

    public Rank_player setPname(String pname){
        String _old = pname;
        this.pname = pname;
        changeIt("pname", _old, pname);
        return this;
    }

    public int getPexp(){
        return pexp;
    }

    public Rank_player setPexp(int pexp){
        int _old = pexp;
        this.pexp = pexp;
        changeIt("pexp", _old, pexp);
        return this;
    }

    public Rank_player changePexp(int pexp){
        return setPexp(this.pexp + pexp);
    }

    public Rank_player changePexpWithMin(int pexp, int _min){
        int _v2 = this.pexp + pexp;
        return setPexp(_v2 < _min  ? _min : _v2);
    }

    public Rank_player changePexpWithMax(int pexp, int _max){
        int _v2 = this.pexp + pexp;
        return setPexp(_v2 > _max  ? _max : _v2);
    }

    public Rank_player changePexpWithMinMax(int pexp, int _min, int _max){
        int _v2 = this.pexp + pexp;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPexp(_v2 < _min  ? _min : _v2);
    }

    public String getClancid(){
        return clancid;
    }

    public Rank_player setClancid(String clancid){
        String _old = clancid;
        this.clancid = clancid;
        changeIt("clancid", _old, clancid);
        return this;
    }

    public int getClanicon(){
        return clanIcon;
    }

    public Rank_player setClanicon(int clanIcon){
        int _old = clanIcon;
        this.clanIcon = clanIcon;
        changeIt("clanIcon", _old, clanIcon);
        return this;
    }

    public Rank_player changeClanicon(int clanIcon){
        return setClanicon(this.clanIcon + clanIcon);
    }

    public Rank_player changeClaniconWithMin(int clanIcon, int _min){
        int _v2 = this.clanIcon + clanIcon;
        return setClanicon(_v2 < _min  ? _min : _v2);
    }

    public Rank_player changeClaniconWithMax(int clanIcon, int _max){
        int _v2 = this.clanIcon + clanIcon;
        return setClanicon(_v2 > _max  ? _max : _v2);
    }

    public Rank_player changeClaniconWithMinMax(int clanIcon, int _min, int _max){
        int _v2 = this.clanIcon + clanIcon;
        _v2 = _v2 > _max  ? _max : _v2;
        return setClanicon(_v2 < _min  ? _min : _v2);
    }

    public String getClanname(){
        return clanName;
    }

    public Rank_player setClanname(String clanName){
        String _old = clanName;
        this.clanName = clanName;
        changeIt("clanName", _old, clanName);
        return this;
    }

    public int getCurrank(){
        return currank;
    }

    public Rank_player setCurrank(int currank){
        int _old = currank;
        this.currank = currank;
        changeIt("currank", _old, currank);
        return this;
    }

    public Rank_player changeCurrank(int currank){
        return setCurrank(this.currank + currank);
    }

    public Rank_player changeCurrankWithMin(int currank, int _min){
        int _v2 = this.currank + currank;
        return setCurrank(_v2 < _min  ? _min : _v2);
    }

    public Rank_player changeCurrankWithMax(int currank, int _max){
        int _v2 = this.currank + currank;
        return setCurrank(_v2 > _max  ? _max : _v2);
    }

    public Rank_player changeCurrankWithMinMax(int currank, int _min, int _max){
        int _v2 = this.currank + currank;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurrank(_v2 < _min  ? _min : _v2);
    }

    public int getRenown(){
        return renown;
    }

    public Rank_player setRenown(int renown){
        int _old = renown;
        this.renown = renown;
        changeIt("renown", _old, renown);
        return this;
    }

    public Rank_player changeRenown(int renown){
        return setRenown(this.renown + renown);
    }

    public Rank_player changeRenownWithMin(int renown, int _min){
        int _v2 = this.renown + renown;
        return setRenown(_v2 < _min  ? _min : _v2);
    }

    public Rank_player changeRenownWithMax(int renown, int _max){
        int _v2 = this.renown + renown;
        return setRenown(_v2 > _max  ? _max : _v2);
    }

    public Rank_player changeRenownWithMinMax(int renown, int _min, int _max){
        int _v2 = this.renown + renown;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRenown(_v2 < _min  ? _min : _v2);
    }

    public int getWeekaddrenown(){
        return weekAddRenown;
    }

    public Rank_player setWeekaddrenown(int weekAddRenown){
        int _old = weekAddRenown;
        this.weekAddRenown = weekAddRenown;
        changeIt("weekAddRenown", _old, weekAddRenown);
        return this;
    }

    public Rank_player changeWeekaddrenown(int weekAddRenown){
        return setWeekaddrenown(this.weekAddRenown + weekAddRenown);
    }

    public Rank_player changeWeekaddrenownWithMin(int weekAddRenown, int _min){
        int _v2 = this.weekAddRenown + weekAddRenown;
        return setWeekaddrenown(_v2 < _min  ? _min : _v2);
    }

    public Rank_player changeWeekaddrenownWithMax(int weekAddRenown, int _max){
        int _v2 = this.weekAddRenown + weekAddRenown;
        return setWeekaddrenown(_v2 > _max  ? _max : _v2);
    }

    public Rank_player changeWeekaddrenownWithMinMax(int weekAddRenown, int _min, int _max){
        int _v2 = this.weekAddRenown + weekAddRenown;
        _v2 = _v2 > _max  ? _max : _v2;
        return setWeekaddrenown(_v2 < _min  ? _min : _v2);
    }

    public int getType(){
        return type;
    }

    public Rank_player setType(int type){
        int _old = type;
        this.type = type;
        changeIt("type", _old, type);
        return this;
    }

    public Rank_player changeType(int type){
        return setType(this.type + type);
    }

    public Rank_player changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Rank_player changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Rank_player changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Rank_player v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Rank_player v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Rank_player newRank_player(Integer id, Integer ucid, Integer pcid, String pname, Integer pexp, String clancid, Integer clanIcon, String clanName, Integer currank, Integer renown, Integer weekAddRenown, Integer type) {
        Rank_player result = new Rank_player();
        result.id = id;
        result.ucid = ucid;
        result.pcid = pcid;
        result.pname = pname;
        result.pexp = pexp;
        result.clancid = clancid;
        result.clanIcon = clanIcon;
        result.clanName = clanName;
        result.currank = currank;
        result.renown = renown;
        result.weekAddRenown = weekAddRenown;
        result.type = type;
        return result;
    }

    public static Rank_player newRank_player(Rank_player rank_player) {
        Rank_player result = new Rank_player();
        result.id = rank_player.id;
        result.ucid = rank_player.ucid;
        result.pcid = rank_player.pcid;
        result.pname = rank_player.pname;
        result.pexp = rank_player.pexp;
        result.clancid = rank_player.clancid;
        result.clanIcon = rank_player.clanIcon;
        result.clanName = rank_player.clanName;
        result.currank = rank_player.currank;
        result.renown = rank_player.renown;
        result.weekAddRenown = rank_player.weekAddRenown;
        result.type = rank_player.type;
        return result;
    }

    public Rank_player createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, ucid);
        B2OutputStream.writeObject(_out, pcid);
        B2OutputStream.writeObject(_out, pname);
        B2OutputStream.writeObject(_out, pexp);
        B2OutputStream.writeObject(_out, clancid);
        B2OutputStream.writeObject(_out, clanIcon);
        B2OutputStream.writeObject(_out, clanName);
        B2OutputStream.writeObject(_out, currank);
        B2OutputStream.writeObject(_out, renown);
        B2OutputStream.writeObject(_out, weekAddRenown);
        B2OutputStream.writeObject(_out, type);
    }

    public static final Rank_player readObject(InputStream _in) throws Exception {
        Rank_player result = new Rank_player();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.ucid = (Integer) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        result.pname = (String) B2InputStream.readObject(_in);
        result.pexp = (Integer) B2InputStream.readObject(_in);
        result.clancid = (String) B2InputStream.readObject(_in);
        result.clanIcon = (Integer) B2InputStream.readObject(_in);
        result.clanName = (String) B2InputStream.readObject(_in);
        result.currank = (Integer) B2InputStream.readObject(_in);
        result.renown = (Integer) B2InputStream.readObject(_in);
        result.weekAddRenown = (Integer) B2InputStream.readObject(_in);
        result.type = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getRank_player(){
        Rank_player rank_player = null; // rank_player
        { // new and insert Rank_player (rank_player)
            int id = 0; 	// id
            int ucid = 0; 	// ucid
            int pcid = 0; 	// pcid
            String pname = ""; 	// pname
            int pexp = 0; 	// pexp
            String clancid = ""; 	// clancid
            int clanIcon = 0; 	// clanIcon
            String clanName = ""; 	// clanName
            int currank = 0; 	// currank
            int renown = 0; 	// renown
            int weekAddRenown = 0; 	// weekAddRenown
            int type = 0; 	// type
            rank_player = Rank_player.newRank_player(id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type);
        }
        rank_player = rank_player.insert();

        int id = rank_player.getId(); 	// id
        int ucid = rank_player.getUcid(); 	// ucid
        int pcid = rank_player.getPcid(); 	// pcid
        String pname = rank_player.getPname(); 	// pname
        int pexp = rank_player.getPexp(); 	// pexp
        String clancid = rank_player.getClancid(); 	// clancid
        int clanIcon = rank_player.getClanicon(); 	// clanIcon
        String clanName = rank_player.getClanname(); 	// clanName
        int currank = rank_player.getCurrank(); 	// currank
        int renown = rank_player.getRenown(); 	// renown
        int weekAddRenown = rank_player.getWeekaddrenown(); 	// weekAddRenown
        int type = rank_player.getType(); 	// type
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
        case "pcid":
            return pcid;
        case "pexp":
            return pexp;
        case "clanIcon":
            return clanIcon;
        case "currank":
            return currank;
        case "renown":
            return renown;
        case "weekAddRenown":
            return weekAddRenown;
        case "type":
            return type;
        }
        return 0;
    }

    public Rank_player setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Rank_player setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "ucid":
            return setUcid(value2);
        case "pcid":
            return setPcid(value2);
        case "pexp":
            return setPexp(value2);
        case "clanIcon":
            return setClanicon(value2);
        case "currank":
            return setCurrank(value2);
        case "renown":
            return setRenown(value2);
        case "weekAddRenown":
            return setWeekaddrenown(value2);
        case "type":
            return setType(value2);
        }
        return this;
    }

    public Rank_player changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Rank_player changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "ucid":
            return changeUcid(value2);
        case "pcid":
            return changePcid(value2);
        case "pexp":
            return changePexp(value2);
        case "clanIcon":
            return changeClanicon(value2);
        case "currank":
            return changeCurrank(value2);
        case "renown":
            return changeRenown(value2);
        case "weekAddRenown":
            return changeWeekaddrenown(value2);
        case "type":
            return changeType(value2);
        }
        return this;
    }

    public Rank_player changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Rank_player changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "ucid":
            return changeUcidWithMin(value2, _min);
        case "pcid":
            return changePcidWithMin(value2, _min);
        case "pexp":
            return changePexpWithMin(value2, _min);
        case "clanIcon":
            return changeClaniconWithMin(value2, _min);
        case "currank":
            return changeCurrankWithMin(value2, _min);
        case "renown":
            return changeRenownWithMin(value2, _min);
        case "weekAddRenown":
            return changeWeekaddrenownWithMin(value2, _min);
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

    public Rank_player setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Rank_player setDouble(String fieldEn, double value2) {
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
        case "clancid": 
            return clancid;
        case "clanName": 
            return clanName;
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
        case "pcid":
            return pcid;
        case "pname":
            return pname;
        case "pexp":
            return pexp;
        case "clancid":
            return clancid;
        case "clanIcon":
            return clanIcon;
        case "clanName":
            return clanName;
        case "currank":
            return currank;
        case "renown":
            return renown;
        case "weekAddRenown":
            return weekAddRenown;
        case "type":
            return type;
        }
        return null;
    }

    public Rank_player setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Rank_player setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "pname":
            return setPname(value2);
        case "clancid":
            return setClancid(value2);
        case "clanName":
            return setClanname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Rank_player setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Rank_player setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Rank_player");
        result.put("id", id);
        result.put("ucid", ucid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("pexp", pexp);
        result.put("clancid", clancid);
        result.put("clanIcon", clanIcon);
        result.put("clanName", clanName);
        result.put("currank", currank);
        result.put("renown", renown);
        result.put("weekAddRenown", weekAddRenown);
        result.put("type", type);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("ucid", ucid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("pexp", pexp);
        result.put("clancid", clancid);
        result.put("clanIcon", clanIcon);
        result.put("clanName", clanName);
        result.put("currank", currank);
        result.put("renown", renown);
        result.put("weekAddRenown", weekAddRenown);
        result.put("type", type);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Rank_player");
        result.put("id", id);
        result.put("ucid", ucid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("pexp", pexp);
        result.put("clancid", clancid);
        result.put("clanIcon", clanIcon);
        result.put("clanName", clanName);
        result.put("currank", currank);
        result.put("renown", renown);
        result.put("weekAddRenown", weekAddRenown);
        result.put("type", type);
        return result;
    }

    public Rank_player mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer ucid = (Integer)e.get("ucid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer pexp = (Integer)e.get("pexp");
        String clancid = (String)e.get("clancid");
        Integer clanIcon = (Integer)e.get("clanIcon");
        String clanName = (String)e.get("clanName");
        Integer currank = (Integer)e.get("currank");
        Integer renown = (Integer)e.get("renown");
        Integer weekAddRenown = (Integer)e.get("weekAddRenown");
        Integer type = (Integer)e.get("type");

        if(id == null) id = 0;
        if(ucid == null) ucid = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(pexp == null) pexp = 0;
        if(clancid == null) clancid = "";
        if(clanIcon == null) clanIcon = 0;
        if(clanName == null) clanName = "";
        if(currank == null) currank = 0;
        if(renown == null) renown = 0;
        if(weekAddRenown == null) weekAddRenown = 0;
        if(type == null) type = 0;

        setId(id);
        setUcid(ucid);
        setPcid(pcid);
        setPname(pname);
        setPexp(pexp);
        setClancid(clancid);
        setClanicon(clanIcon);
        setClanname(clanName);
        setCurrank(currank);
        setRenown(renown);
        setWeekaddrenown(weekAddRenown);
        setType(type);

        return this;
    }

    public static final Rank_player mapTo(Map e){
        Rank_player result = new Rank_player();

        Integer id = (Integer)e.get("id");
        Integer ucid = (Integer)e.get("ucid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer pexp = (Integer)e.get("pexp");
        String clancid = (String)e.get("clancid");
        Integer clanIcon = (Integer)e.get("clanIcon");
        String clanName = (String)e.get("clanName");
        Integer currank = (Integer)e.get("currank");
        Integer renown = (Integer)e.get("renown");
        Integer weekAddRenown = (Integer)e.get("weekAddRenown");
        Integer type = (Integer)e.get("type");

        if(id == null) id = 0;
        if(ucid == null) ucid = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(pexp == null) pexp = 0;
        if(clancid == null) clancid = "";
        if(clanIcon == null) clanIcon = 0;
        if(clanName == null) clanName = "";
        if(currank == null) currank = 0;
        if(renown == null) renown = 0;
        if(weekAddRenown == null) weekAddRenown = 0;
        if(type == null) type = 0;

        result.id = id;
        result.ucid = ucid;
        result.pcid = pcid;
        result.pname = pname;
        result.pexp = pexp;
        result.clancid = clancid;
        result.clanIcon = clanIcon;
        result.clanName = clanName;
        result.currank = currank;
        result.renown = renown;
        result.weekAddRenown = weekAddRenown;
        result.type = type;

        return result;
    }

    public static final Rank_player originalTo(Map e){
        Rank_player result = new Rank_player();

        Integer id = (Integer)e.get("id");
        Integer ucid = (Integer)e.get("ucid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer pexp = (Integer)e.get("pexp");
        String clancid = (String)e.get("clancid");
        Integer clanIcon = (Integer)e.get("clanIcon");
        String clanName = (String)e.get("clanName");
        Integer currank = (Integer)e.get("currank");
        Integer renown = (Integer)e.get("renown");
        Integer weekAddRenown = (Integer)e.get("weekAddRenown");
        Integer type = (Integer)e.get("type");

        if(id == null) id = 0;
        if(ucid == null) ucid = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(pexp == null) pexp = 0;
        if(clancid == null) clancid = "";
        if(clanIcon == null) clanIcon = 0;
        if(clanName == null) clanName = "";
        if(currank == null) currank = 0;
        if(renown == null) renown = 0;
        if(weekAddRenown == null) weekAddRenown = 0;
        if(type == null) type = 0;

        result.id = id;
        result.ucid = ucid;
        result.pcid = pcid;
        result.pname = pname;
        result.pexp = pexp;
        result.clancid = clancid;
        result.clanIcon = clanIcon;
        result.clanName = clanName;
        result.currank = currank;
        result.renown = renown;
        result.weekAddRenown = weekAddRenown;
        result.type = type;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Rank_player createFor(byte[] b) throws Exception {
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
    public static final Rank_player loadByKey(int id) {
        return Rank_playerEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Rank_player insert() {
        Rank_player result = Rank_playerEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Rank_player asyncInsert() {
        Rank_player result = Rank_playerEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Rank_player insert2() {
        return Rank_playerEntity.insert2(this);
    }

    public final Rank_player asyncInsert2() {
        Rank_player result = Rank_playerEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Rank_player update() {
        return Rank_playerEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Rank_playerEntity.asynchronousUpdate( this );
        return true;
    }

    public final Rank_player insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Rank_playerEntity.delete(id);
    }

    public final void asyncDelete() {
        Rank_playerEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Rank_player clone2() {
        try{
            return (Rank_player) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
