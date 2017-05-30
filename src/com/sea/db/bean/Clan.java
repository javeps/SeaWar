package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - clan
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Clan extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1752432496; // com.sea.db.bean.Clan

    public static String TABLENAME = "clan";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "ccid", "clan_name", "icon", "lvl", "desc", "maxNum", "curNum", "create_time", "pointHP", "pointAtt", "renownAll", "renownWeek", "nextHPGold", "nextAttOil", "curDonateGold", "curDonateOil"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR", "INT", "INT", "TEXT", "INT", "INT", "BIGINT", "INT", "INT", "INT", "INT", "BIGINT", "BIGINT", "BIGINT", "BIGINT"};


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
    public String clan_name;
    public int icon;
    public int lvl;
    public String desc;
    public int maxNum;
    public int curNum;
    public long create_time;
    public int pointHP;
    public int pointAtt;
    public int renownAll;
    public int renownWeek;
    public long nextHPGold;
    public long nextAttOil;
    public long curDonateGold;
    public long curDonateOil;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Clan setId(int id){
        this.id = id;
        return this;
    }

    public String getCcid(){
        return ccid;
    }

    public Clan setCcid(String ccid){
        String _old = ccid;
        this.ccid = ccid;
        changeIt("ccid", _old, ccid);
        return this;
    }

    public String getClan_name(){
        return clan_name;
    }

    public Clan setClan_name(String clan_name){
        String _old = clan_name;
        this.clan_name = clan_name;
        changeIt("clan_name", _old, clan_name);
        return this;
    }

    public int getIcon(){
        return icon;
    }

    public Clan setIcon(int icon){
        int _old = icon;
        this.icon = icon;
        changeIt("icon", _old, icon);
        return this;
    }

    public Clan changeIcon(int icon){
        return setIcon(this.icon + icon);
    }

    public Clan changeIconWithMin(int icon, int _min){
        int _v2 = this.icon + icon;
        return setIcon(_v2 < _min  ? _min : _v2);
    }

    public Clan changeIconWithMax(int icon, int _max){
        int _v2 = this.icon + icon;
        return setIcon(_v2 > _max  ? _max : _v2);
    }

    public Clan changeIconWithMinMax(int icon, int _min, int _max){
        int _v2 = this.icon + icon;
        _v2 = _v2 > _max  ? _max : _v2;
        return setIcon(_v2 < _min  ? _min : _v2);
    }

    public int getLvl(){
        return lvl;
    }

    public Clan setLvl(int lvl){
        int _old = lvl;
        this.lvl = lvl;
        changeIt("lvl", _old, lvl);
        return this;
    }

    public Clan changeLvl(int lvl){
        return setLvl(this.lvl + lvl);
    }

    public Clan changeLvlWithMin(int lvl, int _min){
        int _v2 = this.lvl + lvl;
        return setLvl(_v2 < _min  ? _min : _v2);
    }

    public Clan changeLvlWithMax(int lvl, int _max){
        int _v2 = this.lvl + lvl;
        return setLvl(_v2 > _max  ? _max : _v2);
    }

    public Clan changeLvlWithMinMax(int lvl, int _min, int _max){
        int _v2 = this.lvl + lvl;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLvl(_v2 < _min  ? _min : _v2);
    }

    public String getDesc(){
        return desc;
    }

    public Clan setDesc(String desc){
        String _old = desc;
        this.desc = desc;
        changeIt("desc", _old, desc);
        return this;
    }

    public int getMaxnum(){
        return maxNum;
    }

    public Clan setMaxnum(int maxNum){
        int _old = maxNum;
        this.maxNum = maxNum;
        changeIt("maxNum", _old, maxNum);
        return this;
    }

    public Clan changeMaxnum(int maxNum){
        return setMaxnum(this.maxNum + maxNum);
    }

    public Clan changeMaxnumWithMin(int maxNum, int _min){
        int _v2 = this.maxNum + maxNum;
        return setMaxnum(_v2 < _min  ? _min : _v2);
    }

    public Clan changeMaxnumWithMax(int maxNum, int _max){
        int _v2 = this.maxNum + maxNum;
        return setMaxnum(_v2 > _max  ? _max : _v2);
    }

    public Clan changeMaxnumWithMinMax(int maxNum, int _min, int _max){
        int _v2 = this.maxNum + maxNum;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMaxnum(_v2 < _min  ? _min : _v2);
    }

    public int getCurnum(){
        return curNum;
    }

    public Clan setCurnum(int curNum){
        int _old = curNum;
        this.curNum = curNum;
        changeIt("curNum", _old, curNum);
        return this;
    }

    public Clan changeCurnum(int curNum){
        return setCurnum(this.curNum + curNum);
    }

    public Clan changeCurnumWithMin(int curNum, int _min){
        int _v2 = this.curNum + curNum;
        return setCurnum(_v2 < _min  ? _min : _v2);
    }

    public Clan changeCurnumWithMax(int curNum, int _max){
        int _v2 = this.curNum + curNum;
        return setCurnum(_v2 > _max  ? _max : _v2);
    }

    public Clan changeCurnumWithMinMax(int curNum, int _min, int _max){
        int _v2 = this.curNum + curNum;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurnum(_v2 < _min  ? _min : _v2);
    }

    public long getCreate_time(){
        return create_time;
    }

    public Clan setCreate_time(long create_time){
        long _old = create_time;
        this.create_time = create_time;
        changeIt("create_time", _old, create_time);
        return this;
    }

    public Clan changeCreate_time(long create_time){
        return setCreate_time(this.create_time + create_time);
    }

    public Clan changeCreate_timeWithMin(long create_time, long _min){
        long _v2 = this.create_time + create_time;
        return setCreate_time(_v2 < _min  ? _min : _v2);
    }

    public Clan changeCreate_timeWithMax(long create_time, long _max){
        long _v2 = this.create_time + create_time;
        return setCreate_time(_v2 > _max  ? _max : _v2);
    }

    public Clan changeCreate_timeWithMinMax(long create_time, long _min, long _max){
        long _v2 = this.create_time + create_time;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCreate_time(_v2 < _min  ? _min : _v2);
    }

    public int getPointhp(){
        return pointHP;
    }

    public Clan setPointhp(int pointHP){
        int _old = pointHP;
        this.pointHP = pointHP;
        changeIt("pointHP", _old, pointHP);
        return this;
    }

    public Clan changePointhp(int pointHP){
        return setPointhp(this.pointHP + pointHP);
    }

    public Clan changePointhpWithMin(int pointHP, int _min){
        int _v2 = this.pointHP + pointHP;
        return setPointhp(_v2 < _min  ? _min : _v2);
    }

    public Clan changePointhpWithMax(int pointHP, int _max){
        int _v2 = this.pointHP + pointHP;
        return setPointhp(_v2 > _max  ? _max : _v2);
    }

    public Clan changePointhpWithMinMax(int pointHP, int _min, int _max){
        int _v2 = this.pointHP + pointHP;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPointhp(_v2 < _min  ? _min : _v2);
    }

    public int getPointatt(){
        return pointAtt;
    }

    public Clan setPointatt(int pointAtt){
        int _old = pointAtt;
        this.pointAtt = pointAtt;
        changeIt("pointAtt", _old, pointAtt);
        return this;
    }

    public Clan changePointatt(int pointAtt){
        return setPointatt(this.pointAtt + pointAtt);
    }

    public Clan changePointattWithMin(int pointAtt, int _min){
        int _v2 = this.pointAtt + pointAtt;
        return setPointatt(_v2 < _min  ? _min : _v2);
    }

    public Clan changePointattWithMax(int pointAtt, int _max){
        int _v2 = this.pointAtt + pointAtt;
        return setPointatt(_v2 > _max  ? _max : _v2);
    }

    public Clan changePointattWithMinMax(int pointAtt, int _min, int _max){
        int _v2 = this.pointAtt + pointAtt;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPointatt(_v2 < _min  ? _min : _v2);
    }

    public int getRenownall(){
        return renownAll;
    }

    public Clan setRenownall(int renownAll){
        int _old = renownAll;
        this.renownAll = renownAll;
        changeIt("renownAll", _old, renownAll);
        return this;
    }

    public Clan changeRenownall(int renownAll){
        return setRenownall(this.renownAll + renownAll);
    }

    public Clan changeRenownallWithMin(int renownAll, int _min){
        int _v2 = this.renownAll + renownAll;
        return setRenownall(_v2 < _min  ? _min : _v2);
    }

    public Clan changeRenownallWithMax(int renownAll, int _max){
        int _v2 = this.renownAll + renownAll;
        return setRenownall(_v2 > _max  ? _max : _v2);
    }

    public Clan changeRenownallWithMinMax(int renownAll, int _min, int _max){
        int _v2 = this.renownAll + renownAll;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRenownall(_v2 < _min  ? _min : _v2);
    }

    public int getRenownweek(){
        return renownWeek;
    }

    public Clan setRenownweek(int renownWeek){
        int _old = renownWeek;
        this.renownWeek = renownWeek;
        changeIt("renownWeek", _old, renownWeek);
        return this;
    }

    public Clan changeRenownweek(int renownWeek){
        return setRenownweek(this.renownWeek + renownWeek);
    }

    public Clan changeRenownweekWithMin(int renownWeek, int _min){
        int _v2 = this.renownWeek + renownWeek;
        return setRenownweek(_v2 < _min  ? _min : _v2);
    }

    public Clan changeRenownweekWithMax(int renownWeek, int _max){
        int _v2 = this.renownWeek + renownWeek;
        return setRenownweek(_v2 > _max  ? _max : _v2);
    }

    public Clan changeRenownweekWithMinMax(int renownWeek, int _min, int _max){
        int _v2 = this.renownWeek + renownWeek;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRenownweek(_v2 < _min  ? _min : _v2);
    }

    public long getNexthpgold(){
        return nextHPGold;
    }

    public Clan setNexthpgold(long nextHPGold){
        long _old = nextHPGold;
        this.nextHPGold = nextHPGold;
        changeIt("nextHPGold", _old, nextHPGold);
        return this;
    }

    public Clan changeNexthpgold(long nextHPGold){
        return setNexthpgold(this.nextHPGold + nextHPGold);
    }

    public Clan changeNexthpgoldWithMin(long nextHPGold, long _min){
        long _v2 = this.nextHPGold + nextHPGold;
        return setNexthpgold(_v2 < _min  ? _min : _v2);
    }

    public Clan changeNexthpgoldWithMax(long nextHPGold, long _max){
        long _v2 = this.nextHPGold + nextHPGold;
        return setNexthpgold(_v2 > _max  ? _max : _v2);
    }

    public Clan changeNexthpgoldWithMinMax(long nextHPGold, long _min, long _max){
        long _v2 = this.nextHPGold + nextHPGold;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNexthpgold(_v2 < _min  ? _min : _v2);
    }

    public long getNextattoil(){
        return nextAttOil;
    }

    public Clan setNextattoil(long nextAttOil){
        long _old = nextAttOil;
        this.nextAttOil = nextAttOil;
        changeIt("nextAttOil", _old, nextAttOil);
        return this;
    }

    public Clan changeNextattoil(long nextAttOil){
        return setNextattoil(this.nextAttOil + nextAttOil);
    }

    public Clan changeNextattoilWithMin(long nextAttOil, long _min){
        long _v2 = this.nextAttOil + nextAttOil;
        return setNextattoil(_v2 < _min  ? _min : _v2);
    }

    public Clan changeNextattoilWithMax(long nextAttOil, long _max){
        long _v2 = this.nextAttOil + nextAttOil;
        return setNextattoil(_v2 > _max  ? _max : _v2);
    }

    public Clan changeNextattoilWithMinMax(long nextAttOil, long _min, long _max){
        long _v2 = this.nextAttOil + nextAttOil;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNextattoil(_v2 < _min  ? _min : _v2);
    }

    public long getCurdonategold(){
        return curDonateGold;
    }

    public Clan setCurdonategold(long curDonateGold){
        long _old = curDonateGold;
        this.curDonateGold = curDonateGold;
        changeIt("curDonateGold", _old, curDonateGold);
        return this;
    }

    public Clan changeCurdonategold(long curDonateGold){
        return setCurdonategold(this.curDonateGold + curDonateGold);
    }

    public Clan changeCurdonategoldWithMin(long curDonateGold, long _min){
        long _v2 = this.curDonateGold + curDonateGold;
        return setCurdonategold(_v2 < _min  ? _min : _v2);
    }

    public Clan changeCurdonategoldWithMax(long curDonateGold, long _max){
        long _v2 = this.curDonateGold + curDonateGold;
        return setCurdonategold(_v2 > _max  ? _max : _v2);
    }

    public Clan changeCurdonategoldWithMinMax(long curDonateGold, long _min, long _max){
        long _v2 = this.curDonateGold + curDonateGold;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurdonategold(_v2 < _min  ? _min : _v2);
    }

    public long getCurdonateoil(){
        return curDonateOil;
    }

    public Clan setCurdonateoil(long curDonateOil){
        long _old = curDonateOil;
        this.curDonateOil = curDonateOil;
        changeIt("curDonateOil", _old, curDonateOil);
        return this;
    }

    public Clan changeCurdonateoil(long curDonateOil){
        return setCurdonateoil(this.curDonateOil + curDonateOil);
    }

    public Clan changeCurdonateoilWithMin(long curDonateOil, long _min){
        long _v2 = this.curDonateOil + curDonateOil;
        return setCurdonateoil(_v2 < _min  ? _min : _v2);
    }

    public Clan changeCurdonateoilWithMax(long curDonateOil, long _max){
        long _v2 = this.curDonateOil + curDonateOil;
        return setCurdonateoil(_v2 > _max  ? _max : _v2);
    }

    public Clan changeCurdonateoilWithMinMax(long curDonateOil, long _min, long _max){
        long _v2 = this.curDonateOil + curDonateOil;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurdonateoil(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Clan v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Clan v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Clan newClan(Integer id, String ccid, String clan_name, Integer icon, Integer lvl, String desc, Integer maxNum, Integer curNum, Long create_time, Integer pointHP, Integer pointAtt, Integer renownAll, Integer renownWeek, Long nextHPGold, Long nextAttOil, Long curDonateGold, Long curDonateOil) {
        Clan result = new Clan();
        result.id = id;
        result.ccid = ccid;
        result.clan_name = clan_name;
        result.icon = icon;
        result.lvl = lvl;
        result.desc = desc;
        result.maxNum = maxNum;
        result.curNum = curNum;
        result.create_time = create_time;
        result.pointHP = pointHP;
        result.pointAtt = pointAtt;
        result.renownAll = renownAll;
        result.renownWeek = renownWeek;
        result.nextHPGold = nextHPGold;
        result.nextAttOil = nextAttOil;
        result.curDonateGold = curDonateGold;
        result.curDonateOil = curDonateOil;
        return result;
    }

    public static Clan newClan(Clan clan) {
        Clan result = new Clan();
        result.id = clan.id;
        result.ccid = clan.ccid;
        result.clan_name = clan.clan_name;
        result.icon = clan.icon;
        result.lvl = clan.lvl;
        result.desc = clan.desc;
        result.maxNum = clan.maxNum;
        result.curNum = clan.curNum;
        result.create_time = clan.create_time;
        result.pointHP = clan.pointHP;
        result.pointAtt = clan.pointAtt;
        result.renownAll = clan.renownAll;
        result.renownWeek = clan.renownWeek;
        result.nextHPGold = clan.nextHPGold;
        result.nextAttOil = clan.nextAttOil;
        result.curDonateGold = clan.curDonateGold;
        result.curDonateOil = clan.curDonateOil;
        return result;
    }

    public Clan createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, ccid);
        B2OutputStream.writeObject(_out, clan_name);
        B2OutputStream.writeObject(_out, icon);
        B2OutputStream.writeObject(_out, lvl);
        B2OutputStream.writeObject(_out, desc);
        B2OutputStream.writeObject(_out, maxNum);
        B2OutputStream.writeObject(_out, curNum);
        B2OutputStream.writeObject(_out, create_time);
        B2OutputStream.writeObject(_out, pointHP);
        B2OutputStream.writeObject(_out, pointAtt);
        B2OutputStream.writeObject(_out, renownAll);
        B2OutputStream.writeObject(_out, renownWeek);
        B2OutputStream.writeObject(_out, nextHPGold);
        B2OutputStream.writeObject(_out, nextAttOil);
        B2OutputStream.writeObject(_out, curDonateGold);
        B2OutputStream.writeObject(_out, curDonateOil);
    }

    public static final Clan readObject(InputStream _in) throws Exception {
        Clan result = new Clan();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.ccid = (String) B2InputStream.readObject(_in);
        result.clan_name = (String) B2InputStream.readObject(_in);
        result.icon = (Integer) B2InputStream.readObject(_in);
        result.lvl = (Integer) B2InputStream.readObject(_in);
        result.desc = (String) B2InputStream.readObject(_in);
        result.maxNum = (Integer) B2InputStream.readObject(_in);
        result.curNum = (Integer) B2InputStream.readObject(_in);
        result.create_time = (Long) B2InputStream.readObject(_in);
        result.pointHP = (Integer) B2InputStream.readObject(_in);
        result.pointAtt = (Integer) B2InputStream.readObject(_in);
        result.renownAll = (Integer) B2InputStream.readObject(_in);
        result.renownWeek = (Integer) B2InputStream.readObject(_in);
        result.nextHPGold = (Long) B2InputStream.readObject(_in);
        result.nextAttOil = (Long) B2InputStream.readObject(_in);
        result.curDonateGold = (Long) B2InputStream.readObject(_in);
        result.curDonateOil = (Long) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getClan(){
        Clan clan = null; // clan
        { // new and insert Clan (clan)
            int id = 0; 	// id
            String ccid = ""; 	// ccid
            String clan_name = ""; 	// clan_name
            int icon = 0; 	// icon
            int lvl = 0; 	// lvl
            String desc = ""; 	// desc
            int maxNum = 0; 	// maxNum
            int curNum = 0; 	// curNum
            long create_time = 0; 	// create_time
            int pointHP = 0; 	// pointHP
            int pointAtt = 0; 	// pointAtt
            int renownAll = 0; 	// renownAll
            int renownWeek = 0; 	// renownWeek
            long nextHPGold = 0; 	// nextHPGold
            long nextAttOil = 0; 	// nextAttOil
            long curDonateGold = 0; 	// curDonateGold
            long curDonateOil = 0; 	// curDonateOil
            clan = Clan.newClan(id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil);
        }
        clan = clan.insert();

        int id = clan.getId(); 	// id
        String ccid = clan.getCcid(); 	// ccid
        String clan_name = clan.getClan_name(); 	// clan_name
        int icon = clan.getIcon(); 	// icon
        int lvl = clan.getLvl(); 	// lvl
        String desc = clan.getDesc(); 	// desc
        int maxNum = clan.getMaxnum(); 	// maxNum
        int curNum = clan.getCurnum(); 	// curNum
        long create_time = clan.getCreate_time(); 	// create_time
        int pointHP = clan.getPointhp(); 	// pointHP
        int pointAtt = clan.getPointatt(); 	// pointAtt
        int renownAll = clan.getRenownall(); 	// renownAll
        int renownWeek = clan.getRenownweek(); 	// renownWeek
        long nextHPGold = clan.getNexthpgold(); 	// nextHPGold
        long nextAttOil = clan.getNextattoil(); 	// nextAttOil
        long curDonateGold = clan.getCurdonategold(); 	// curDonateGold
        long curDonateOil = clan.getCurdonateoil(); 	// curDonateOil
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
        case "lvl":
            return lvl;
        case "maxNum":
            return maxNum;
        case "curNum":
            return curNum;
        case "pointHP":
            return pointHP;
        case "pointAtt":
            return pointAtt;
        case "renownAll":
            return renownAll;
        case "renownWeek":
            return renownWeek;
        }
        return 0;
    }

    public Clan setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Clan setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "icon":
            return setIcon(value2);
        case "lvl":
            return setLvl(value2);
        case "maxNum":
            return setMaxnum(value2);
        case "curNum":
            return setCurnum(value2);
        case "pointHP":
            return setPointhp(value2);
        case "pointAtt":
            return setPointatt(value2);
        case "renownAll":
            return setRenownall(value2);
        case "renownWeek":
            return setRenownweek(value2);
        }
        return this;
    }

    public Clan changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Clan changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "icon":
            return changeIcon(value2);
        case "lvl":
            return changeLvl(value2);
        case "maxNum":
            return changeMaxnum(value2);
        case "curNum":
            return changeCurnum(value2);
        case "pointHP":
            return changePointhp(value2);
        case "pointAtt":
            return changePointatt(value2);
        case "renownAll":
            return changeRenownall(value2);
        case "renownWeek":
            return changeRenownweek(value2);
        }
        return this;
    }

    public Clan changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Clan changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "icon":
            return changeIconWithMin(value2, _min);
        case "lvl":
            return changeLvlWithMin(value2, _min);
        case "maxNum":
            return changeMaxnumWithMin(value2, _min);
        case "curNum":
            return changeCurnumWithMin(value2, _min);
        case "pointHP":
            return changePointhpWithMin(value2, _min);
        case "pointAtt":
            return changePointattWithMin(value2, _min);
        case "renownAll":
            return changeRenownallWithMin(value2, _min);
        case "renownWeek":
            return changeRenownweekWithMin(value2, _min);
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

    public Clan setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Clan setDouble(String fieldEn, double value2) {
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
        case "clan_name": 
            return clan_name;
        case "desc": 
            return desc;
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
        case "clan_name":
            return clan_name;
        case "icon":
            return icon;
        case "lvl":
            return lvl;
        case "desc":
            return desc;
        case "maxNum":
            return maxNum;
        case "curNum":
            return curNum;
        case "create_time":
            return create_time;
        case "pointHP":
            return pointHP;
        case "pointAtt":
            return pointAtt;
        case "renownAll":
            return renownAll;
        case "renownWeek":
            return renownWeek;
        case "nextHPGold":
            return nextHPGold;
        case "nextAttOil":
            return nextAttOil;
        case "curDonateGold":
            return curDonateGold;
        case "curDonateOil":
            return curDonateOil;
        }
        return null;
    }

    public Clan setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Clan setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "ccid":
            return setCcid(value2);
        case "clan_name":
            return setClan_name(value2);
        case "desc":
            return setDesc(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Clan setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Clan setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Clan");
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("clan_name", clan_name);
        result.put("icon", icon);
        result.put("lvl", lvl);
        result.put("desc", desc);
        result.put("maxNum", maxNum);
        result.put("curNum", curNum);
        result.put("create_time", create_time);
        result.put("pointHP", pointHP);
        result.put("pointAtt", pointAtt);
        result.put("renownAll", renownAll);
        result.put("renownWeek", renownWeek);
        result.put("nextHPGold", nextHPGold);
        result.put("nextAttOil", nextAttOil);
        result.put("curDonateGold", curDonateGold);
        result.put("curDonateOil", curDonateOil);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("clan_name", clan_name);
        result.put("icon", icon);
        result.put("lvl", lvl);
        result.put("desc", desc);
        result.put("maxNum", maxNum);
        result.put("curNum", curNum);
        result.put("create_time", create_time);
        result.put("pointHP", pointHP);
        result.put("pointAtt", pointAtt);
        result.put("renownAll", renownAll);
        result.put("renownWeek", renownWeek);
        result.put("nextHPGold", nextHPGold);
        result.put("nextAttOil", nextAttOil);
        result.put("curDonateGold", curDonateGold);
        result.put("curDonateOil", curDonateOil);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Clan");
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("clan_name", clan_name);
        result.put("icon", icon);
        result.put("lvl", lvl);
        result.put("desc", desc);
        result.put("maxNum", maxNum);
        result.put("curNum", curNum);
        result.put("create_time", create_time);
        result.put("pointHP", pointHP);
        result.put("pointAtt", pointAtt);
        result.put("renownAll", renownAll);
        result.put("renownWeek", renownWeek);
        result.put("nextHPGold", nextHPGold);
        result.put("nextAttOil", nextAttOil);
        result.put("curDonateGold", curDonateGold);
        result.put("curDonateOil", curDonateOil);
        return result;
    }

    public Clan mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        String clan_name = (String)e.get("clan_name");
        Integer icon = (Integer)e.get("icon");
        Integer lvl = (Integer)e.get("lvl");
        String desc = (String)e.get("desc");
        Integer maxNum = (Integer)e.get("maxNum");
        Integer curNum = (Integer)e.get("curNum");
        Long create_time = (Long)e.get("create_time");
        Integer pointHP = (Integer)e.get("pointHP");
        Integer pointAtt = (Integer)e.get("pointAtt");
        Integer renownAll = (Integer)e.get("renownAll");
        Integer renownWeek = (Integer)e.get("renownWeek");
        Long nextHPGold = (Long)e.get("nextHPGold");
        Long nextAttOil = (Long)e.get("nextAttOil");
        Long curDonateGold = (Long)e.get("curDonateGold");
        Long curDonateOil = (Long)e.get("curDonateOil");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(clan_name == null) clan_name = "";
        if(icon == null) icon = 0;
        if(lvl == null) lvl = 0;
        if(desc == null) desc = "";
        if(maxNum == null) maxNum = 0;
        if(curNum == null) curNum = 0;
        if(create_time == null) create_time = 0L;
        if(pointHP == null) pointHP = 0;
        if(pointAtt == null) pointAtt = 0;
        if(renownAll == null) renownAll = 0;
        if(renownWeek == null) renownWeek = 0;
        if(nextHPGold == null) nextHPGold = 0L;
        if(nextAttOil == null) nextAttOil = 0L;
        if(curDonateGold == null) curDonateGold = 0L;
        if(curDonateOil == null) curDonateOil = 0L;

        setId(id);
        setCcid(ccid);
        setClan_name(clan_name);
        setIcon(icon);
        setLvl(lvl);
        setDesc(desc);
        setMaxnum(maxNum);
        setCurnum(curNum);
        setCreate_time(create_time);
        setPointhp(pointHP);
        setPointatt(pointAtt);
        setRenownall(renownAll);
        setRenownweek(renownWeek);
        setNexthpgold(nextHPGold);
        setNextattoil(nextAttOil);
        setCurdonategold(curDonateGold);
        setCurdonateoil(curDonateOil);

        return this;
    }

    public static final Clan mapTo(Map e){
        Clan result = new Clan();

        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        String clan_name = (String)e.get("clan_name");
        Integer icon = (Integer)e.get("icon");
        Integer lvl = (Integer)e.get("lvl");
        String desc = (String)e.get("desc");
        Integer maxNum = (Integer)e.get("maxNum");
        Integer curNum = (Integer)e.get("curNum");
        Long create_time = (Long)e.get("create_time");
        Integer pointHP = (Integer)e.get("pointHP");
        Integer pointAtt = (Integer)e.get("pointAtt");
        Integer renownAll = (Integer)e.get("renownAll");
        Integer renownWeek = (Integer)e.get("renownWeek");
        Long nextHPGold = (Long)e.get("nextHPGold");
        Long nextAttOil = (Long)e.get("nextAttOil");
        Long curDonateGold = (Long)e.get("curDonateGold");
        Long curDonateOil = (Long)e.get("curDonateOil");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(clan_name == null) clan_name = "";
        if(icon == null) icon = 0;
        if(lvl == null) lvl = 0;
        if(desc == null) desc = "";
        if(maxNum == null) maxNum = 0;
        if(curNum == null) curNum = 0;
        if(create_time == null) create_time = 0L;
        if(pointHP == null) pointHP = 0;
        if(pointAtt == null) pointAtt = 0;
        if(renownAll == null) renownAll = 0;
        if(renownWeek == null) renownWeek = 0;
        if(nextHPGold == null) nextHPGold = 0L;
        if(nextAttOil == null) nextAttOil = 0L;
        if(curDonateGold == null) curDonateGold = 0L;
        if(curDonateOil == null) curDonateOil = 0L;

        result.id = id;
        result.ccid = ccid;
        result.clan_name = clan_name;
        result.icon = icon;
        result.lvl = lvl;
        result.desc = desc;
        result.maxNum = maxNum;
        result.curNum = curNum;
        result.create_time = create_time;
        result.pointHP = pointHP;
        result.pointAtt = pointAtt;
        result.renownAll = renownAll;
        result.renownWeek = renownWeek;
        result.nextHPGold = nextHPGold;
        result.nextAttOil = nextAttOil;
        result.curDonateGold = curDonateGold;
        result.curDonateOil = curDonateOil;

        return result;
    }

    public static final Clan originalTo(Map e){
        Clan result = new Clan();

        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        String clan_name = (String)e.get("clan_name");
        Integer icon = (Integer)e.get("icon");
        Integer lvl = (Integer)e.get("lvl");
        String desc = (String)e.get("desc");
        Integer maxNum = (Integer)e.get("maxNum");
        Integer curNum = (Integer)e.get("curNum");
        Long create_time = (Long)e.get("create_time");
        Integer pointHP = (Integer)e.get("pointHP");
        Integer pointAtt = (Integer)e.get("pointAtt");
        Integer renownAll = (Integer)e.get("renownAll");
        Integer renownWeek = (Integer)e.get("renownWeek");
        Long nextHPGold = (Long)e.get("nextHPGold");
        Long nextAttOil = (Long)e.get("nextAttOil");
        Long curDonateGold = (Long)e.get("curDonateGold");
        Long curDonateOil = (Long)e.get("curDonateOil");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(clan_name == null) clan_name = "";
        if(icon == null) icon = 0;
        if(lvl == null) lvl = 0;
        if(desc == null) desc = "";
        if(maxNum == null) maxNum = 0;
        if(curNum == null) curNum = 0;
        if(create_time == null) create_time = 0L;
        if(pointHP == null) pointHP = 0;
        if(pointAtt == null) pointAtt = 0;
        if(renownAll == null) renownAll = 0;
        if(renownWeek == null) renownWeek = 0;
        if(nextHPGold == null) nextHPGold = 0L;
        if(nextAttOil == null) nextAttOil = 0L;
        if(curDonateGold == null) curDonateGold = 0L;
        if(curDonateOil == null) curDonateOil = 0L;

        result.id = id;
        result.ccid = ccid;
        result.clan_name = clan_name;
        result.icon = icon;
        result.lvl = lvl;
        result.desc = desc;
        result.maxNum = maxNum;
        result.curNum = curNum;
        result.create_time = create_time;
        result.pointHP = pointHP;
        result.pointAtt = pointAtt;
        result.renownAll = renownAll;
        result.renownWeek = renownWeek;
        result.nextHPGold = nextHPGold;
        result.nextAttOil = nextAttOil;
        result.curDonateGold = curDonateGold;
        result.curDonateOil = curDonateOil;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Clan createFor(byte[] b) throws Exception {
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
    public static final Clan loadByKey(int id) {
        return ClanEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Clan insert() {
        Clan result = ClanEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Clan asyncInsert() {
        Clan result = ClanEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Clan insert2() {
        return ClanEntity.insert2(this);
    }

    public final Clan asyncInsert2() {
        Clan result = ClanEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Clan update() {
        return ClanEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        ClanEntity.asynchronousUpdate( this );
        return true;
    }

    public final Clan insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return ClanEntity.delete(id);
    }

    public final void asyncDelete() {
        ClanEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Clan clone2() {
        try{
            return (Clan) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
