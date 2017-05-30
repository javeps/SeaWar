package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - clan_member
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Clan_member extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -489700055; // com.sea.db.bean.Clan_member

    public static String TABLENAME = "clan_member";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "ccid", "cname", "ucid", "pcid", "pname", "post", "donateGold", "donateOil", "curDGold", "curDOil", "lastDGold", "lastDOil", "renownAll", "renownWeek", "daynumgold", "daynumoil", "maxdaynum"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR", "INT", "INT", "VARCHAR", "INT", "INT", "INT", "INT", "INT", "BIGINT", "BIGINT", "INT", "INT", "INT", "INT", "INT"};


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
    public String cname;
    public int ucid;
    public int pcid;
    public String pname;
    public int post;
    public int donateGold;
    public int donateOil;
    public int curDGold;
    public int curDOil;
    public long lastDGold;
    public long lastDOil;
    public int renownAll;
    public int renownWeek;
    public int daynumgold;
    public int daynumoil;
    public int maxdaynum;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Clan_member setId(int id){
        this.id = id;
        return this;
    }

    public String getCcid(){
        return ccid;
    }

    public Clan_member setCcid(String ccid){
        String _old = ccid;
        this.ccid = ccid;
        changeIt("ccid", _old, ccid);
        return this;
    }

    public String getCname(){
        return cname;
    }

    public Clan_member setCname(String cname){
        String _old = cname;
        this.cname = cname;
        changeIt("cname", _old, cname);
        return this;
    }

    public int getUcid(){
        return ucid;
    }

    public Clan_member setUcid(int ucid){
        int _old = ucid;
        this.ucid = ucid;
        changeIt("ucid", _old, ucid);
        return this;
    }

    public Clan_member changeUcid(int ucid){
        return setUcid(this.ucid + ucid);
    }

    public Clan_member changeUcidWithMin(int ucid, int _min){
        int _v2 = this.ucid + ucid;
        return setUcid(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeUcidWithMax(int ucid, int _max){
        int _v2 = this.ucid + ucid;
        return setUcid(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeUcidWithMinMax(int ucid, int _min, int _max){
        int _v2 = this.ucid + ucid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setUcid(_v2 < _min  ? _min : _v2);
    }

    public int getPcid(){
        return pcid;
    }

    public Clan_member setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Clan_member changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Clan_member changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPname(){
        return pname;
    }

    public Clan_member setPname(String pname){
        String _old = pname;
        this.pname = pname;
        changeIt("pname", _old, pname);
        return this;
    }

    public int getPost(){
        return post;
    }

    public Clan_member setPost(int post){
        int _old = post;
        this.post = post;
        changeIt("post", _old, post);
        return this;
    }

    public Clan_member changePost(int post){
        return setPost(this.post + post);
    }

    public Clan_member changePostWithMin(int post, int _min){
        int _v2 = this.post + post;
        return setPost(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changePostWithMax(int post, int _max){
        int _v2 = this.post + post;
        return setPost(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changePostWithMinMax(int post, int _min, int _max){
        int _v2 = this.post + post;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPost(_v2 < _min  ? _min : _v2);
    }

    public int getDonategold(){
        return donateGold;
    }

    public Clan_member setDonategold(int donateGold){
        int _old = donateGold;
        this.donateGold = donateGold;
        changeIt("donateGold", _old, donateGold);
        return this;
    }

    public Clan_member changeDonategold(int donateGold){
        return setDonategold(this.donateGold + donateGold);
    }

    public Clan_member changeDonategoldWithMin(int donateGold, int _min){
        int _v2 = this.donateGold + donateGold;
        return setDonategold(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeDonategoldWithMax(int donateGold, int _max){
        int _v2 = this.donateGold + donateGold;
        return setDonategold(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeDonategoldWithMinMax(int donateGold, int _min, int _max){
        int _v2 = this.donateGold + donateGold;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDonategold(_v2 < _min  ? _min : _v2);
    }

    public int getDonateoil(){
        return donateOil;
    }

    public Clan_member setDonateoil(int donateOil){
        int _old = donateOil;
        this.donateOil = donateOil;
        changeIt("donateOil", _old, donateOil);
        return this;
    }

    public Clan_member changeDonateoil(int donateOil){
        return setDonateoil(this.donateOil + donateOil);
    }

    public Clan_member changeDonateoilWithMin(int donateOil, int _min){
        int _v2 = this.donateOil + donateOil;
        return setDonateoil(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeDonateoilWithMax(int donateOil, int _max){
        int _v2 = this.donateOil + donateOil;
        return setDonateoil(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeDonateoilWithMinMax(int donateOil, int _min, int _max){
        int _v2 = this.donateOil + donateOil;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDonateoil(_v2 < _min  ? _min : _v2);
    }

    public int getCurdgold(){
        return curDGold;
    }

    public Clan_member setCurdgold(int curDGold){
        int _old = curDGold;
        this.curDGold = curDGold;
        changeIt("curDGold", _old, curDGold);
        return this;
    }

    public Clan_member changeCurdgold(int curDGold){
        return setCurdgold(this.curDGold + curDGold);
    }

    public Clan_member changeCurdgoldWithMin(int curDGold, int _min){
        int _v2 = this.curDGold + curDGold;
        return setCurdgold(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeCurdgoldWithMax(int curDGold, int _max){
        int _v2 = this.curDGold + curDGold;
        return setCurdgold(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeCurdgoldWithMinMax(int curDGold, int _min, int _max){
        int _v2 = this.curDGold + curDGold;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurdgold(_v2 < _min  ? _min : _v2);
    }

    public int getCurdoil(){
        return curDOil;
    }

    public Clan_member setCurdoil(int curDOil){
        int _old = curDOil;
        this.curDOil = curDOil;
        changeIt("curDOil", _old, curDOil);
        return this;
    }

    public Clan_member changeCurdoil(int curDOil){
        return setCurdoil(this.curDOil + curDOil);
    }

    public Clan_member changeCurdoilWithMin(int curDOil, int _min){
        int _v2 = this.curDOil + curDOil;
        return setCurdoil(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeCurdoilWithMax(int curDOil, int _max){
        int _v2 = this.curDOil + curDOil;
        return setCurdoil(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeCurdoilWithMinMax(int curDOil, int _min, int _max){
        int _v2 = this.curDOil + curDOil;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurdoil(_v2 < _min  ? _min : _v2);
    }

    public long getLastdgold(){
        return lastDGold;
    }

    public Clan_member setLastdgold(long lastDGold){
        long _old = lastDGold;
        this.lastDGold = lastDGold;
        changeIt("lastDGold", _old, lastDGold);
        return this;
    }

    public Clan_member changeLastdgold(long lastDGold){
        return setLastdgold(this.lastDGold + lastDGold);
    }

    public Clan_member changeLastdgoldWithMin(long lastDGold, long _min){
        long _v2 = this.lastDGold + lastDGold;
        return setLastdgold(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeLastdgoldWithMax(long lastDGold, long _max){
        long _v2 = this.lastDGold + lastDGold;
        return setLastdgold(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeLastdgoldWithMinMax(long lastDGold, long _min, long _max){
        long _v2 = this.lastDGold + lastDGold;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLastdgold(_v2 < _min  ? _min : _v2);
    }

    public long getLastdoil(){
        return lastDOil;
    }

    public Clan_member setLastdoil(long lastDOil){
        long _old = lastDOil;
        this.lastDOil = lastDOil;
        changeIt("lastDOil", _old, lastDOil);
        return this;
    }

    public Clan_member changeLastdoil(long lastDOil){
        return setLastdoil(this.lastDOil + lastDOil);
    }

    public Clan_member changeLastdoilWithMin(long lastDOil, long _min){
        long _v2 = this.lastDOil + lastDOil;
        return setLastdoil(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeLastdoilWithMax(long lastDOil, long _max){
        long _v2 = this.lastDOil + lastDOil;
        return setLastdoil(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeLastdoilWithMinMax(long lastDOil, long _min, long _max){
        long _v2 = this.lastDOil + lastDOil;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLastdoil(_v2 < _min  ? _min : _v2);
    }

    public int getRenownall(){
        return renownAll;
    }

    public Clan_member setRenownall(int renownAll){
        int _old = renownAll;
        this.renownAll = renownAll;
        changeIt("renownAll", _old, renownAll);
        return this;
    }

    public Clan_member changeRenownall(int renownAll){
        return setRenownall(this.renownAll + renownAll);
    }

    public Clan_member changeRenownallWithMin(int renownAll, int _min){
        int _v2 = this.renownAll + renownAll;
        return setRenownall(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeRenownallWithMax(int renownAll, int _max){
        int _v2 = this.renownAll + renownAll;
        return setRenownall(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeRenownallWithMinMax(int renownAll, int _min, int _max){
        int _v2 = this.renownAll + renownAll;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRenownall(_v2 < _min  ? _min : _v2);
    }

    public int getRenownweek(){
        return renownWeek;
    }

    public Clan_member setRenownweek(int renownWeek){
        int _old = renownWeek;
        this.renownWeek = renownWeek;
        changeIt("renownWeek", _old, renownWeek);
        return this;
    }

    public Clan_member changeRenownweek(int renownWeek){
        return setRenownweek(this.renownWeek + renownWeek);
    }

    public Clan_member changeRenownweekWithMin(int renownWeek, int _min){
        int _v2 = this.renownWeek + renownWeek;
        return setRenownweek(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeRenownweekWithMax(int renownWeek, int _max){
        int _v2 = this.renownWeek + renownWeek;
        return setRenownweek(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeRenownweekWithMinMax(int renownWeek, int _min, int _max){
        int _v2 = this.renownWeek + renownWeek;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRenownweek(_v2 < _min  ? _min : _v2);
    }

    public int getDaynumgold(){
        return daynumgold;
    }

    public Clan_member setDaynumgold(int daynumgold){
        int _old = daynumgold;
        this.daynumgold = daynumgold;
        changeIt("daynumgold", _old, daynumgold);
        return this;
    }

    public Clan_member changeDaynumgold(int daynumgold){
        return setDaynumgold(this.daynumgold + daynumgold);
    }

    public Clan_member changeDaynumgoldWithMin(int daynumgold, int _min){
        int _v2 = this.daynumgold + daynumgold;
        return setDaynumgold(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeDaynumgoldWithMax(int daynumgold, int _max){
        int _v2 = this.daynumgold + daynumgold;
        return setDaynumgold(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeDaynumgoldWithMinMax(int daynumgold, int _min, int _max){
        int _v2 = this.daynumgold + daynumgold;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDaynumgold(_v2 < _min  ? _min : _v2);
    }

    public int getDaynumoil(){
        return daynumoil;
    }

    public Clan_member setDaynumoil(int daynumoil){
        int _old = daynumoil;
        this.daynumoil = daynumoil;
        changeIt("daynumoil", _old, daynumoil);
        return this;
    }

    public Clan_member changeDaynumoil(int daynumoil){
        return setDaynumoil(this.daynumoil + daynumoil);
    }

    public Clan_member changeDaynumoilWithMin(int daynumoil, int _min){
        int _v2 = this.daynumoil + daynumoil;
        return setDaynumoil(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeDaynumoilWithMax(int daynumoil, int _max){
        int _v2 = this.daynumoil + daynumoil;
        return setDaynumoil(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeDaynumoilWithMinMax(int daynumoil, int _min, int _max){
        int _v2 = this.daynumoil + daynumoil;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDaynumoil(_v2 < _min  ? _min : _v2);
    }

    public int getMaxdaynum(){
        return maxdaynum;
    }

    public Clan_member setMaxdaynum(int maxdaynum){
        int _old = maxdaynum;
        this.maxdaynum = maxdaynum;
        changeIt("maxdaynum", _old, maxdaynum);
        return this;
    }

    public Clan_member changeMaxdaynum(int maxdaynum){
        return setMaxdaynum(this.maxdaynum + maxdaynum);
    }

    public Clan_member changeMaxdaynumWithMin(int maxdaynum, int _min){
        int _v2 = this.maxdaynum + maxdaynum;
        return setMaxdaynum(_v2 < _min  ? _min : _v2);
    }

    public Clan_member changeMaxdaynumWithMax(int maxdaynum, int _max){
        int _v2 = this.maxdaynum + maxdaynum;
        return setMaxdaynum(_v2 > _max  ? _max : _v2);
    }

    public Clan_member changeMaxdaynumWithMinMax(int maxdaynum, int _min, int _max){
        int _v2 = this.maxdaynum + maxdaynum;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMaxdaynum(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Clan_member v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Clan_member v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Clan_member newClan_member(Integer id, String ccid, String cname, Integer ucid, Integer pcid, String pname, Integer post, Integer donateGold, Integer donateOil, Integer curDGold, Integer curDOil, Long lastDGold, Long lastDOil, Integer renownAll, Integer renownWeek, Integer daynumgold, Integer daynumoil, Integer maxdaynum) {
        Clan_member result = new Clan_member();
        result.id = id;
        result.ccid = ccid;
        result.cname = cname;
        result.ucid = ucid;
        result.pcid = pcid;
        result.pname = pname;
        result.post = post;
        result.donateGold = donateGold;
        result.donateOil = donateOil;
        result.curDGold = curDGold;
        result.curDOil = curDOil;
        result.lastDGold = lastDGold;
        result.lastDOil = lastDOil;
        result.renownAll = renownAll;
        result.renownWeek = renownWeek;
        result.daynumgold = daynumgold;
        result.daynumoil = daynumoil;
        result.maxdaynum = maxdaynum;
        return result;
    }

    public static Clan_member newClan_member(Clan_member clan_member) {
        Clan_member result = new Clan_member();
        result.id = clan_member.id;
        result.ccid = clan_member.ccid;
        result.cname = clan_member.cname;
        result.ucid = clan_member.ucid;
        result.pcid = clan_member.pcid;
        result.pname = clan_member.pname;
        result.post = clan_member.post;
        result.donateGold = clan_member.donateGold;
        result.donateOil = clan_member.donateOil;
        result.curDGold = clan_member.curDGold;
        result.curDOil = clan_member.curDOil;
        result.lastDGold = clan_member.lastDGold;
        result.lastDOil = clan_member.lastDOil;
        result.renownAll = clan_member.renownAll;
        result.renownWeek = clan_member.renownWeek;
        result.daynumgold = clan_member.daynumgold;
        result.daynumoil = clan_member.daynumoil;
        result.maxdaynum = clan_member.maxdaynum;
        return result;
    }

    public Clan_member createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, ccid);
        B2OutputStream.writeObject(_out, cname);
        B2OutputStream.writeObject(_out, ucid);
        B2OutputStream.writeObject(_out, pcid);
        B2OutputStream.writeObject(_out, pname);
        B2OutputStream.writeObject(_out, post);
        B2OutputStream.writeObject(_out, donateGold);
        B2OutputStream.writeObject(_out, donateOil);
        B2OutputStream.writeObject(_out, curDGold);
        B2OutputStream.writeObject(_out, curDOil);
        B2OutputStream.writeObject(_out, lastDGold);
        B2OutputStream.writeObject(_out, lastDOil);
        B2OutputStream.writeObject(_out, renownAll);
        B2OutputStream.writeObject(_out, renownWeek);
        B2OutputStream.writeObject(_out, daynumgold);
        B2OutputStream.writeObject(_out, daynumoil);
        B2OutputStream.writeObject(_out, maxdaynum);
    }

    public static final Clan_member readObject(InputStream _in) throws Exception {
        Clan_member result = new Clan_member();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.ccid = (String) B2InputStream.readObject(_in);
        result.cname = (String) B2InputStream.readObject(_in);
        result.ucid = (Integer) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        result.pname = (String) B2InputStream.readObject(_in);
        result.post = (Integer) B2InputStream.readObject(_in);
        result.donateGold = (Integer) B2InputStream.readObject(_in);
        result.donateOil = (Integer) B2InputStream.readObject(_in);
        result.curDGold = (Integer) B2InputStream.readObject(_in);
        result.curDOil = (Integer) B2InputStream.readObject(_in);
        result.lastDGold = (Long) B2InputStream.readObject(_in);
        result.lastDOil = (Long) B2InputStream.readObject(_in);
        result.renownAll = (Integer) B2InputStream.readObject(_in);
        result.renownWeek = (Integer) B2InputStream.readObject(_in);
        result.daynumgold = (Integer) B2InputStream.readObject(_in);
        result.daynumoil = (Integer) B2InputStream.readObject(_in);
        result.maxdaynum = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getClan_member(){
        Clan_member clan_member = null; // clan_member
        { // new and insert Clan_member (clan_member)
            int id = 0; 	// id
            String ccid = ""; 	// ccid
            String cname = ""; 	// cname
            int ucid = 0; 	// ucid
            int pcid = 0; 	// pcid
            String pname = ""; 	// pname
            int post = 0; 	// post
            int donateGold = 0; 	// donateGold
            int donateOil = 0; 	// donateOil
            int curDGold = 0; 	// curDGold
            int curDOil = 0; 	// curDOil
            long lastDGold = 0; 	// lastDGold
            long lastDOil = 0; 	// lastDOil
            int renownAll = 0; 	// renownAll
            int renownWeek = 0; 	// renownWeek
            int daynumgold = 0; 	// daynumgold
            int daynumoil = 0; 	// daynumoil
            int maxdaynum = 0; 	// maxdaynum
            clan_member = Clan_member.newClan_member(id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum);
        }
        clan_member = clan_member.insert();

        int id = clan_member.getId(); 	// id
        String ccid = clan_member.getCcid(); 	// ccid
        String cname = clan_member.getCname(); 	// cname
        int ucid = clan_member.getUcid(); 	// ucid
        int pcid = clan_member.getPcid(); 	// pcid
        String pname = clan_member.getPname(); 	// pname
        int post = clan_member.getPost(); 	// post
        int donateGold = clan_member.getDonategold(); 	// donateGold
        int donateOil = clan_member.getDonateoil(); 	// donateOil
        int curDGold = clan_member.getCurdgold(); 	// curDGold
        int curDOil = clan_member.getCurdoil(); 	// curDOil
        long lastDGold = clan_member.getLastdgold(); 	// lastDGold
        long lastDOil = clan_member.getLastdoil(); 	// lastDOil
        int renownAll = clan_member.getRenownall(); 	// renownAll
        int renownWeek = clan_member.getRenownweek(); 	// renownWeek
        int daynumgold = clan_member.getDaynumgold(); 	// daynumgold
        int daynumoil = clan_member.getDaynumoil(); 	// daynumoil
        int maxdaynum = clan_member.getMaxdaynum(); 	// maxdaynum
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
        case "post":
            return post;
        case "donateGold":
            return donateGold;
        case "donateOil":
            return donateOil;
        case "curDGold":
            return curDGold;
        case "curDOil":
            return curDOil;
        case "renownAll":
            return renownAll;
        case "renownWeek":
            return renownWeek;
        case "daynumgold":
            return daynumgold;
        case "daynumoil":
            return daynumoil;
        case "maxdaynum":
            return maxdaynum;
        }
        return 0;
    }

    public Clan_member setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Clan_member setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "ucid":
            return setUcid(value2);
        case "pcid":
            return setPcid(value2);
        case "post":
            return setPost(value2);
        case "donateGold":
            return setDonategold(value2);
        case "donateOil":
            return setDonateoil(value2);
        case "curDGold":
            return setCurdgold(value2);
        case "curDOil":
            return setCurdoil(value2);
        case "renownAll":
            return setRenownall(value2);
        case "renownWeek":
            return setRenownweek(value2);
        case "daynumgold":
            return setDaynumgold(value2);
        case "daynumoil":
            return setDaynumoil(value2);
        case "maxdaynum":
            return setMaxdaynum(value2);
        }
        return this;
    }

    public Clan_member changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Clan_member changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "ucid":
            return changeUcid(value2);
        case "pcid":
            return changePcid(value2);
        case "post":
            return changePost(value2);
        case "donateGold":
            return changeDonategold(value2);
        case "donateOil":
            return changeDonateoil(value2);
        case "curDGold":
            return changeCurdgold(value2);
        case "curDOil":
            return changeCurdoil(value2);
        case "renownAll":
            return changeRenownall(value2);
        case "renownWeek":
            return changeRenownweek(value2);
        case "daynumgold":
            return changeDaynumgold(value2);
        case "daynumoil":
            return changeDaynumoil(value2);
        case "maxdaynum":
            return changeMaxdaynum(value2);
        }
        return this;
    }

    public Clan_member changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Clan_member changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "ucid":
            return changeUcidWithMin(value2, _min);
        case "pcid":
            return changePcidWithMin(value2, _min);
        case "post":
            return changePostWithMin(value2, _min);
        case "donateGold":
            return changeDonategoldWithMin(value2, _min);
        case "donateOil":
            return changeDonateoilWithMin(value2, _min);
        case "curDGold":
            return changeCurdgoldWithMin(value2, _min);
        case "curDOil":
            return changeCurdoilWithMin(value2, _min);
        case "renownAll":
            return changeRenownallWithMin(value2, _min);
        case "renownWeek":
            return changeRenownweekWithMin(value2, _min);
        case "daynumgold":
            return changeDaynumgoldWithMin(value2, _min);
        case "daynumoil":
            return changeDaynumoilWithMin(value2, _min);
        case "maxdaynum":
            return changeMaxdaynumWithMin(value2, _min);
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

    public Clan_member setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Clan_member setDouble(String fieldEn, double value2) {
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
        case "pname": 
            return pname;
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
        case "cname":
            return cname;
        case "ucid":
            return ucid;
        case "pcid":
            return pcid;
        case "pname":
            return pname;
        case "post":
            return post;
        case "donateGold":
            return donateGold;
        case "donateOil":
            return donateOil;
        case "curDGold":
            return curDGold;
        case "curDOil":
            return curDOil;
        case "lastDGold":
            return lastDGold;
        case "lastDOil":
            return lastDOil;
        case "renownAll":
            return renownAll;
        case "renownWeek":
            return renownWeek;
        case "daynumgold":
            return daynumgold;
        case "daynumoil":
            return daynumoil;
        case "maxdaynum":
            return maxdaynum;
        }
        return null;
    }

    public Clan_member setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Clan_member setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "ccid":
            return setCcid(value2);
        case "cname":
            return setCname(value2);
        case "pname":
            return setPname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Clan_member setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Clan_member setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Clan_member");
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("cname", cname);
        result.put("ucid", ucid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("post", post);
        result.put("donateGold", donateGold);
        result.put("donateOil", donateOil);
        result.put("curDGold", curDGold);
        result.put("curDOil", curDOil);
        result.put("lastDGold", lastDGold);
        result.put("lastDOil", lastDOil);
        result.put("renownAll", renownAll);
        result.put("renownWeek", renownWeek);
        result.put("daynumgold", daynumgold);
        result.put("daynumoil", daynumoil);
        result.put("maxdaynum", maxdaynum);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("cname", cname);
        result.put("ucid", ucid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("post", post);
        result.put("donateGold", donateGold);
        result.put("donateOil", donateOil);
        result.put("curDGold", curDGold);
        result.put("curDOil", curDOil);
        result.put("lastDGold", lastDGold);
        result.put("lastDOil", lastDOil);
        result.put("renownAll", renownAll);
        result.put("renownWeek", renownWeek);
        result.put("daynumgold", daynumgold);
        result.put("daynumoil", daynumoil);
        result.put("maxdaynum", maxdaynum);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Clan_member");
        result.put("id", id);
        result.put("ccid", ccid);
        result.put("cname", cname);
        result.put("ucid", ucid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("post", post);
        result.put("donateGold", donateGold);
        result.put("donateOil", donateOil);
        result.put("curDGold", curDGold);
        result.put("curDOil", curDOil);
        result.put("lastDGold", lastDGold);
        result.put("lastDOil", lastDOil);
        result.put("renownAll", renownAll);
        result.put("renownWeek", renownWeek);
        result.put("daynumgold", daynumgold);
        result.put("daynumoil", daynumoil);
        result.put("maxdaynum", maxdaynum);
        return result;
    }

    public Clan_member mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        String cname = (String)e.get("cname");
        Integer ucid = (Integer)e.get("ucid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer post = (Integer)e.get("post");
        Integer donateGold = (Integer)e.get("donateGold");
        Integer donateOil = (Integer)e.get("donateOil");
        Integer curDGold = (Integer)e.get("curDGold");
        Integer curDOil = (Integer)e.get("curDOil");
        Long lastDGold = (Long)e.get("lastDGold");
        Long lastDOil = (Long)e.get("lastDOil");
        Integer renownAll = (Integer)e.get("renownAll");
        Integer renownWeek = (Integer)e.get("renownWeek");
        Integer daynumgold = (Integer)e.get("daynumgold");
        Integer daynumoil = (Integer)e.get("daynumoil");
        Integer maxdaynum = (Integer)e.get("maxdaynum");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(cname == null) cname = "";
        if(ucid == null) ucid = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(post == null) post = 0;
        if(donateGold == null) donateGold = 0;
        if(donateOil == null) donateOil = 0;
        if(curDGold == null) curDGold = 0;
        if(curDOil == null) curDOil = 0;
        if(lastDGold == null) lastDGold = 0L;
        if(lastDOil == null) lastDOil = 0L;
        if(renownAll == null) renownAll = 0;
        if(renownWeek == null) renownWeek = 0;
        if(daynumgold == null) daynumgold = 0;
        if(daynumoil == null) daynumoil = 0;
        if(maxdaynum == null) maxdaynum = 0;

        setId(id);
        setCcid(ccid);
        setCname(cname);
        setUcid(ucid);
        setPcid(pcid);
        setPname(pname);
        setPost(post);
        setDonategold(donateGold);
        setDonateoil(donateOil);
        setCurdgold(curDGold);
        setCurdoil(curDOil);
        setLastdgold(lastDGold);
        setLastdoil(lastDOil);
        setRenownall(renownAll);
        setRenownweek(renownWeek);
        setDaynumgold(daynumgold);
        setDaynumoil(daynumoil);
        setMaxdaynum(maxdaynum);

        return this;
    }

    public static final Clan_member mapTo(Map e){
        Clan_member result = new Clan_member();

        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        String cname = (String)e.get("cname");
        Integer ucid = (Integer)e.get("ucid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer post = (Integer)e.get("post");
        Integer donateGold = (Integer)e.get("donateGold");
        Integer donateOil = (Integer)e.get("donateOil");
        Integer curDGold = (Integer)e.get("curDGold");
        Integer curDOil = (Integer)e.get("curDOil");
        Long lastDGold = (Long)e.get("lastDGold");
        Long lastDOil = (Long)e.get("lastDOil");
        Integer renownAll = (Integer)e.get("renownAll");
        Integer renownWeek = (Integer)e.get("renownWeek");
        Integer daynumgold = (Integer)e.get("daynumgold");
        Integer daynumoil = (Integer)e.get("daynumoil");
        Integer maxdaynum = (Integer)e.get("maxdaynum");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(cname == null) cname = "";
        if(ucid == null) ucid = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(post == null) post = 0;
        if(donateGold == null) donateGold = 0;
        if(donateOil == null) donateOil = 0;
        if(curDGold == null) curDGold = 0;
        if(curDOil == null) curDOil = 0;
        if(lastDGold == null) lastDGold = 0L;
        if(lastDOil == null) lastDOil = 0L;
        if(renownAll == null) renownAll = 0;
        if(renownWeek == null) renownWeek = 0;
        if(daynumgold == null) daynumgold = 0;
        if(daynumoil == null) daynumoil = 0;
        if(maxdaynum == null) maxdaynum = 0;

        result.id = id;
        result.ccid = ccid;
        result.cname = cname;
        result.ucid = ucid;
        result.pcid = pcid;
        result.pname = pname;
        result.post = post;
        result.donateGold = donateGold;
        result.donateOil = donateOil;
        result.curDGold = curDGold;
        result.curDOil = curDOil;
        result.lastDGold = lastDGold;
        result.lastDOil = lastDOil;
        result.renownAll = renownAll;
        result.renownWeek = renownWeek;
        result.daynumgold = daynumgold;
        result.daynumoil = daynumoil;
        result.maxdaynum = maxdaynum;

        return result;
    }

    public static final Clan_member originalTo(Map e){
        Clan_member result = new Clan_member();

        Integer id = (Integer)e.get("id");
        String ccid = (String)e.get("ccid");
        String cname = (String)e.get("cname");
        Integer ucid = (Integer)e.get("ucid");
        Integer pcid = (Integer)e.get("pcid");
        String pname = (String)e.get("pname");
        Integer post = (Integer)e.get("post");
        Integer donateGold = (Integer)e.get("donateGold");
        Integer donateOil = (Integer)e.get("donateOil");
        Integer curDGold = (Integer)e.get("curDGold");
        Integer curDOil = (Integer)e.get("curDOil");
        Long lastDGold = (Long)e.get("lastDGold");
        Long lastDOil = (Long)e.get("lastDOil");
        Integer renownAll = (Integer)e.get("renownAll");
        Integer renownWeek = (Integer)e.get("renownWeek");
        Integer daynumgold = (Integer)e.get("daynumgold");
        Integer daynumoil = (Integer)e.get("daynumoil");
        Integer maxdaynum = (Integer)e.get("maxdaynum");

        if(id == null) id = 0;
        if(ccid == null) ccid = "";
        if(cname == null) cname = "";
        if(ucid == null) ucid = 0;
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(post == null) post = 0;
        if(donateGold == null) donateGold = 0;
        if(donateOil == null) donateOil = 0;
        if(curDGold == null) curDGold = 0;
        if(curDOil == null) curDOil = 0;
        if(lastDGold == null) lastDGold = 0L;
        if(lastDOil == null) lastDOil = 0L;
        if(renownAll == null) renownAll = 0;
        if(renownWeek == null) renownWeek = 0;
        if(daynumgold == null) daynumgold = 0;
        if(daynumoil == null) daynumoil = 0;
        if(maxdaynum == null) maxdaynum = 0;

        result.id = id;
        result.ccid = ccid;
        result.cname = cname;
        result.ucid = ucid;
        result.pcid = pcid;
        result.pname = pname;
        result.post = post;
        result.donateGold = donateGold;
        result.donateOil = donateOil;
        result.curDGold = curDGold;
        result.curDOil = curDOil;
        result.lastDGold = lastDGold;
        result.lastDOil = lastDOil;
        result.renownAll = renownAll;
        result.renownWeek = renownWeek;
        result.daynumgold = daynumgold;
        result.daynumoil = daynumoil;
        result.maxdaynum = maxdaynum;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Clan_member createFor(byte[] b) throws Exception {
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
    public static final Clan_member loadByKey(int id) {
        return Clan_memberEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Clan_member insert() {
        Clan_member result = Clan_memberEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Clan_member asyncInsert() {
        Clan_member result = Clan_memberEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Clan_member insert2() {
        return Clan_memberEntity.insert2(this);
    }

    public final Clan_member asyncInsert2() {
        Clan_member result = Clan_memberEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Clan_member update() {
        return Clan_memberEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Clan_memberEntity.asynchronousUpdate( this );
        return true;
    }

    public final Clan_member insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Clan_memberEntity.delete(id);
    }

    public final void asyncDelete() {
        Clan_memberEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Clan_member clone2() {
        try{
            return (Clan_member) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
