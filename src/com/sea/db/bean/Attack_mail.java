package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - attack_mail
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Attack_mail extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -439252524; // com.sea.db.bean.Attack_mail

    public static String TABLENAME = "attack_mail";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "attcid", "attPcid", "attPname", "beAttPcid", "beAttPname", "begin_time", "end_time", "star", "preGold", "preOil", "preAttRenown", "attRenown", "attGold", "attOil", "isHitBack", "clancid", "cname", "cicon", "offenHP", "offenAtt", "defccid", "defcname", "defcicon", "defenseHP", "defenseAtt", "attInfos", "beRewon", "egid", "num", "attHeros"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "INT", "VARCHAR", "INT", "VARCHAR", "BIGINT", "BIGINT", "INT", "INT", "INT", "INT", "INT", "INT", "INT", "BIT", "VARCHAR", "VARCHAR", "INT", "INT", "INT", "VARCHAR", "VARCHAR", "INT", "INT", "INT", "TEXT", "INT", "INT", "INT", "TEXT"};


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
    public String attcid;
    public int attPcid;
    public String attPname;
    public int beAttPcid;
    public String beAttPname;
    public long begin_time;
    public long end_time;
    public int star;
    public int preGold;
    public int preOil;
    public int preAttRenown;
    public int attRenown;
    public int attGold;
    public int attOil;
    public boolean isHitBack;
    public String clancid;
    public String cname;
    public int cicon;
    public int offenHP;
    public int offenAtt;
    public String defccid;
    public String defcname;
    public int defcicon;
    public int defenseHP;
    public int defenseAtt;
    public String attInfos;
    public int beRewon;
    public int egid;
    public int num;
    public String attHeros;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Attack_mail setId(int id){
        this.id = id;
        return this;
    }

    public String getAttcid(){
        return attcid;
    }

    public Attack_mail setAttcid(String attcid){
        String _old = attcid;
        this.attcid = attcid;
        changeIt("attcid", _old, attcid);
        return this;
    }

    public int getAttpcid(){
        return attPcid;
    }

    public Attack_mail setAttpcid(int attPcid){
        int _old = attPcid;
        this.attPcid = attPcid;
        changeIt("attPcid", _old, attPcid);
        return this;
    }

    public Attack_mail changeAttpcid(int attPcid){
        return setAttpcid(this.attPcid + attPcid);
    }

    public Attack_mail changeAttpcidWithMin(int attPcid, int _min){
        int _v2 = this.attPcid + attPcid;
        return setAttpcid(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeAttpcidWithMax(int attPcid, int _max){
        int _v2 = this.attPcid + attPcid;
        return setAttpcid(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeAttpcidWithMinMax(int attPcid, int _min, int _max){
        int _v2 = this.attPcid + attPcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAttpcid(_v2 < _min  ? _min : _v2);
    }

    public String getAttpname(){
        return attPname;
    }

    public Attack_mail setAttpname(String attPname){
        String _old = attPname;
        this.attPname = attPname;
        changeIt("attPname", _old, attPname);
        return this;
    }

    public int getBeattpcid(){
        return beAttPcid;
    }

    public Attack_mail setBeattpcid(int beAttPcid){
        int _old = beAttPcid;
        this.beAttPcid = beAttPcid;
        changeIt("beAttPcid", _old, beAttPcid);
        return this;
    }

    public Attack_mail changeBeattpcid(int beAttPcid){
        return setBeattpcid(this.beAttPcid + beAttPcid);
    }

    public Attack_mail changeBeattpcidWithMin(int beAttPcid, int _min){
        int _v2 = this.beAttPcid + beAttPcid;
        return setBeattpcid(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeBeattpcidWithMax(int beAttPcid, int _max){
        int _v2 = this.beAttPcid + beAttPcid;
        return setBeattpcid(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeBeattpcidWithMinMax(int beAttPcid, int _min, int _max){
        int _v2 = this.beAttPcid + beAttPcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBeattpcid(_v2 < _min  ? _min : _v2);
    }

    public String getBeattpname(){
        return beAttPname;
    }

    public Attack_mail setBeattpname(String beAttPname){
        String _old = beAttPname;
        this.beAttPname = beAttPname;
        changeIt("beAttPname", _old, beAttPname);
        return this;
    }

    public long getBegin_time(){
        return begin_time;
    }

    public Attack_mail setBegin_time(long begin_time){
        long _old = begin_time;
        this.begin_time = begin_time;
        changeIt("begin_time", _old, begin_time);
        return this;
    }

    public Attack_mail changeBegin_time(long begin_time){
        return setBegin_time(this.begin_time + begin_time);
    }

    public Attack_mail changeBegin_timeWithMin(long begin_time, long _min){
        long _v2 = this.begin_time + begin_time;
        return setBegin_time(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeBegin_timeWithMax(long begin_time, long _max){
        long _v2 = this.begin_time + begin_time;
        return setBegin_time(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeBegin_timeWithMinMax(long begin_time, long _min, long _max){
        long _v2 = this.begin_time + begin_time;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBegin_time(_v2 < _min  ? _min : _v2);
    }

    public long getEnd_time(){
        return end_time;
    }

    public Attack_mail setEnd_time(long end_time){
        long _old = end_time;
        this.end_time = end_time;
        changeIt("end_time", _old, end_time);
        return this;
    }

    public Attack_mail changeEnd_time(long end_time){
        return setEnd_time(this.end_time + end_time);
    }

    public Attack_mail changeEnd_timeWithMin(long end_time, long _min){
        long _v2 = this.end_time + end_time;
        return setEnd_time(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeEnd_timeWithMax(long end_time, long _max){
        long _v2 = this.end_time + end_time;
        return setEnd_time(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeEnd_timeWithMinMax(long end_time, long _min, long _max){
        long _v2 = this.end_time + end_time;
        _v2 = _v2 > _max  ? _max : _v2;
        return setEnd_time(_v2 < _min  ? _min : _v2);
    }

    public int getStar(){
        return star;
    }

    public Attack_mail setStar(int star){
        int _old = star;
        this.star = star;
        changeIt("star", _old, star);
        return this;
    }

    public Attack_mail changeStar(int star){
        return setStar(this.star + star);
    }

    public Attack_mail changeStarWithMin(int star, int _min){
        int _v2 = this.star + star;
        return setStar(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeStarWithMax(int star, int _max){
        int _v2 = this.star + star;
        return setStar(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeStarWithMinMax(int star, int _min, int _max){
        int _v2 = this.star + star;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStar(_v2 < _min  ? _min : _v2);
    }

    public int getPregold(){
        return preGold;
    }

    public Attack_mail setPregold(int preGold){
        int _old = preGold;
        this.preGold = preGold;
        changeIt("preGold", _old, preGold);
        return this;
    }

    public Attack_mail changePregold(int preGold){
        return setPregold(this.preGold + preGold);
    }

    public Attack_mail changePregoldWithMin(int preGold, int _min){
        int _v2 = this.preGold + preGold;
        return setPregold(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changePregoldWithMax(int preGold, int _max){
        int _v2 = this.preGold + preGold;
        return setPregold(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changePregoldWithMinMax(int preGold, int _min, int _max){
        int _v2 = this.preGold + preGold;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPregold(_v2 < _min  ? _min : _v2);
    }

    public int getPreoil(){
        return preOil;
    }

    public Attack_mail setPreoil(int preOil){
        int _old = preOil;
        this.preOil = preOil;
        changeIt("preOil", _old, preOil);
        return this;
    }

    public Attack_mail changePreoil(int preOil){
        return setPreoil(this.preOil + preOil);
    }

    public Attack_mail changePreoilWithMin(int preOil, int _min){
        int _v2 = this.preOil + preOil;
        return setPreoil(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changePreoilWithMax(int preOil, int _max){
        int _v2 = this.preOil + preOil;
        return setPreoil(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changePreoilWithMinMax(int preOil, int _min, int _max){
        int _v2 = this.preOil + preOil;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPreoil(_v2 < _min  ? _min : _v2);
    }

    public int getPreattrenown(){
        return preAttRenown;
    }

    public Attack_mail setPreattrenown(int preAttRenown){
        int _old = preAttRenown;
        this.preAttRenown = preAttRenown;
        changeIt("preAttRenown", _old, preAttRenown);
        return this;
    }

    public Attack_mail changePreattrenown(int preAttRenown){
        return setPreattrenown(this.preAttRenown + preAttRenown);
    }

    public Attack_mail changePreattrenownWithMin(int preAttRenown, int _min){
        int _v2 = this.preAttRenown + preAttRenown;
        return setPreattrenown(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changePreattrenownWithMax(int preAttRenown, int _max){
        int _v2 = this.preAttRenown + preAttRenown;
        return setPreattrenown(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changePreattrenownWithMinMax(int preAttRenown, int _min, int _max){
        int _v2 = this.preAttRenown + preAttRenown;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPreattrenown(_v2 < _min  ? _min : _v2);
    }

    public int getAttrenown(){
        return attRenown;
    }

    public Attack_mail setAttrenown(int attRenown){
        int _old = attRenown;
        this.attRenown = attRenown;
        changeIt("attRenown", _old, attRenown);
        return this;
    }

    public Attack_mail changeAttrenown(int attRenown){
        return setAttrenown(this.attRenown + attRenown);
    }

    public Attack_mail changeAttrenownWithMin(int attRenown, int _min){
        int _v2 = this.attRenown + attRenown;
        return setAttrenown(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeAttrenownWithMax(int attRenown, int _max){
        int _v2 = this.attRenown + attRenown;
        return setAttrenown(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeAttrenownWithMinMax(int attRenown, int _min, int _max){
        int _v2 = this.attRenown + attRenown;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAttrenown(_v2 < _min  ? _min : _v2);
    }

    public int getAttgold(){
        return attGold;
    }

    public Attack_mail setAttgold(int attGold){
        int _old = attGold;
        this.attGold = attGold;
        changeIt("attGold", _old, attGold);
        return this;
    }

    public Attack_mail changeAttgold(int attGold){
        return setAttgold(this.attGold + attGold);
    }

    public Attack_mail changeAttgoldWithMin(int attGold, int _min){
        int _v2 = this.attGold + attGold;
        return setAttgold(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeAttgoldWithMax(int attGold, int _max){
        int _v2 = this.attGold + attGold;
        return setAttgold(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeAttgoldWithMinMax(int attGold, int _min, int _max){
        int _v2 = this.attGold + attGold;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAttgold(_v2 < _min  ? _min : _v2);
    }

    public int getAttoil(){
        return attOil;
    }

    public Attack_mail setAttoil(int attOil){
        int _old = attOil;
        this.attOil = attOil;
        changeIt("attOil", _old, attOil);
        return this;
    }

    public Attack_mail changeAttoil(int attOil){
        return setAttoil(this.attOil + attOil);
    }

    public Attack_mail changeAttoilWithMin(int attOil, int _min){
        int _v2 = this.attOil + attOil;
        return setAttoil(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeAttoilWithMax(int attOil, int _max){
        int _v2 = this.attOil + attOil;
        return setAttoil(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeAttoilWithMinMax(int attOil, int _min, int _max){
        int _v2 = this.attOil + attOil;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAttoil(_v2 < _min  ? _min : _v2);
    }

    public boolean getIshitback(){
        return isHitBack;
    }

    public Attack_mail setIshitback(boolean isHitBack){
        boolean _old = isHitBack;
        this.isHitBack = isHitBack;
        changeIt("isHitBack", _old, isHitBack);
        return this;
    }

    public String getClancid(){
        return clancid;
    }

    public Attack_mail setClancid(String clancid){
        String _old = clancid;
        this.clancid = clancid;
        changeIt("clancid", _old, clancid);
        return this;
    }

    public String getCname(){
        return cname;
    }

    public Attack_mail setCname(String cname){
        String _old = cname;
        this.cname = cname;
        changeIt("cname", _old, cname);
        return this;
    }

    public int getCicon(){
        return cicon;
    }

    public Attack_mail setCicon(int cicon){
        int _old = cicon;
        this.cicon = cicon;
        changeIt("cicon", _old, cicon);
        return this;
    }

    public Attack_mail changeCicon(int cicon){
        return setCicon(this.cicon + cicon);
    }

    public Attack_mail changeCiconWithMin(int cicon, int _min){
        int _v2 = this.cicon + cicon;
        return setCicon(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeCiconWithMax(int cicon, int _max){
        int _v2 = this.cicon + cicon;
        return setCicon(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeCiconWithMinMax(int cicon, int _min, int _max){
        int _v2 = this.cicon + cicon;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCicon(_v2 < _min  ? _min : _v2);
    }

    public int getOffenhp(){
        return offenHP;
    }

    public Attack_mail setOffenhp(int offenHP){
        int _old = offenHP;
        this.offenHP = offenHP;
        changeIt("offenHP", _old, offenHP);
        return this;
    }

    public Attack_mail changeOffenhp(int offenHP){
        return setOffenhp(this.offenHP + offenHP);
    }

    public Attack_mail changeOffenhpWithMin(int offenHP, int _min){
        int _v2 = this.offenHP + offenHP;
        return setOffenhp(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeOffenhpWithMax(int offenHP, int _max){
        int _v2 = this.offenHP + offenHP;
        return setOffenhp(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeOffenhpWithMinMax(int offenHP, int _min, int _max){
        int _v2 = this.offenHP + offenHP;
        _v2 = _v2 > _max  ? _max : _v2;
        return setOffenhp(_v2 < _min  ? _min : _v2);
    }

    public int getOffenatt(){
        return offenAtt;
    }

    public Attack_mail setOffenatt(int offenAtt){
        int _old = offenAtt;
        this.offenAtt = offenAtt;
        changeIt("offenAtt", _old, offenAtt);
        return this;
    }

    public Attack_mail changeOffenatt(int offenAtt){
        return setOffenatt(this.offenAtt + offenAtt);
    }

    public Attack_mail changeOffenattWithMin(int offenAtt, int _min){
        int _v2 = this.offenAtt + offenAtt;
        return setOffenatt(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeOffenattWithMax(int offenAtt, int _max){
        int _v2 = this.offenAtt + offenAtt;
        return setOffenatt(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeOffenattWithMinMax(int offenAtt, int _min, int _max){
        int _v2 = this.offenAtt + offenAtt;
        _v2 = _v2 > _max  ? _max : _v2;
        return setOffenatt(_v2 < _min  ? _min : _v2);
    }

    public String getDefccid(){
        return defccid;
    }

    public Attack_mail setDefccid(String defccid){
        String _old = defccid;
        this.defccid = defccid;
        changeIt("defccid", _old, defccid);
        return this;
    }

    public String getDefcname(){
        return defcname;
    }

    public Attack_mail setDefcname(String defcname){
        String _old = defcname;
        this.defcname = defcname;
        changeIt("defcname", _old, defcname);
        return this;
    }

    public int getDefcicon(){
        return defcicon;
    }

    public Attack_mail setDefcicon(int defcicon){
        int _old = defcicon;
        this.defcicon = defcicon;
        changeIt("defcicon", _old, defcicon);
        return this;
    }

    public Attack_mail changeDefcicon(int defcicon){
        return setDefcicon(this.defcicon + defcicon);
    }

    public Attack_mail changeDefciconWithMin(int defcicon, int _min){
        int _v2 = this.defcicon + defcicon;
        return setDefcicon(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeDefciconWithMax(int defcicon, int _max){
        int _v2 = this.defcicon + defcicon;
        return setDefcicon(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeDefciconWithMinMax(int defcicon, int _min, int _max){
        int _v2 = this.defcicon + defcicon;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDefcicon(_v2 < _min  ? _min : _v2);
    }

    public int getDefensehp(){
        return defenseHP;
    }

    public Attack_mail setDefensehp(int defenseHP){
        int _old = defenseHP;
        this.defenseHP = defenseHP;
        changeIt("defenseHP", _old, defenseHP);
        return this;
    }

    public Attack_mail changeDefensehp(int defenseHP){
        return setDefensehp(this.defenseHP + defenseHP);
    }

    public Attack_mail changeDefensehpWithMin(int defenseHP, int _min){
        int _v2 = this.defenseHP + defenseHP;
        return setDefensehp(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeDefensehpWithMax(int defenseHP, int _max){
        int _v2 = this.defenseHP + defenseHP;
        return setDefensehp(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeDefensehpWithMinMax(int defenseHP, int _min, int _max){
        int _v2 = this.defenseHP + defenseHP;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDefensehp(_v2 < _min  ? _min : _v2);
    }

    public int getDefenseatt(){
        return defenseAtt;
    }

    public Attack_mail setDefenseatt(int defenseAtt){
        int _old = defenseAtt;
        this.defenseAtt = defenseAtt;
        changeIt("defenseAtt", _old, defenseAtt);
        return this;
    }

    public Attack_mail changeDefenseatt(int defenseAtt){
        return setDefenseatt(this.defenseAtt + defenseAtt);
    }

    public Attack_mail changeDefenseattWithMin(int defenseAtt, int _min){
        int _v2 = this.defenseAtt + defenseAtt;
        return setDefenseatt(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeDefenseattWithMax(int defenseAtt, int _max){
        int _v2 = this.defenseAtt + defenseAtt;
        return setDefenseatt(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeDefenseattWithMinMax(int defenseAtt, int _min, int _max){
        int _v2 = this.defenseAtt + defenseAtt;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDefenseatt(_v2 < _min  ? _min : _v2);
    }

    public String getAttinfos(){
        return attInfos;
    }

    public Attack_mail setAttinfos(String attInfos){
        String _old = attInfos;
        this.attInfos = attInfos;
        changeIt("attInfos", _old, attInfos);
        return this;
    }

    public int getBerewon(){
        return beRewon;
    }

    public Attack_mail setBerewon(int beRewon){
        int _old = beRewon;
        this.beRewon = beRewon;
        changeIt("beRewon", _old, beRewon);
        return this;
    }

    public Attack_mail changeBerewon(int beRewon){
        return setBerewon(this.beRewon + beRewon);
    }

    public Attack_mail changeBerewonWithMin(int beRewon, int _min){
        int _v2 = this.beRewon + beRewon;
        return setBerewon(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeBerewonWithMax(int beRewon, int _max){
        int _v2 = this.beRewon + beRewon;
        return setBerewon(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeBerewonWithMinMax(int beRewon, int _min, int _max){
        int _v2 = this.beRewon + beRewon;
        _v2 = _v2 > _max  ? _max : _v2;
        return setBerewon(_v2 < _min  ? _min : _v2);
    }

    public int getEgid(){
        return egid;
    }

    public Attack_mail setEgid(int egid){
        int _old = egid;
        this.egid = egid;
        changeIt("egid", _old, egid);
        return this;
    }

    public Attack_mail changeEgid(int egid){
        return setEgid(this.egid + egid);
    }

    public Attack_mail changeEgidWithMin(int egid, int _min){
        int _v2 = this.egid + egid;
        return setEgid(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeEgidWithMax(int egid, int _max){
        int _v2 = this.egid + egid;
        return setEgid(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeEgidWithMinMax(int egid, int _min, int _max){
        int _v2 = this.egid + egid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setEgid(_v2 < _min  ? _min : _v2);
    }

    public int getNum(){
        return num;
    }

    public Attack_mail setNum(int num){
        int _old = num;
        this.num = num;
        changeIt("num", _old, num);
        return this;
    }

    public Attack_mail changeNum(int num){
        return setNum(this.num + num);
    }

    public Attack_mail changeNumWithMin(int num, int _min){
        int _v2 = this.num + num;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public Attack_mail changeNumWithMax(int num, int _max){
        int _v2 = this.num + num;
        return setNum(_v2 > _max  ? _max : _v2);
    }

    public Attack_mail changeNumWithMinMax(int num, int _min, int _max){
        int _v2 = this.num + num;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNum(_v2 < _min  ? _min : _v2);
    }

    public String getAttheros(){
        return attHeros;
    }

    public Attack_mail setAttheros(String attHeros){
        String _old = attHeros;
        this.attHeros = attHeros;
        changeIt("attHeros", _old, attHeros);
        return this;
    }

    public int compareTo(Attack_mail v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Attack_mail v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Attack_mail newAttack_mail(Integer id, String attcid, Integer attPcid, String attPname, Integer beAttPcid, String beAttPname, Long begin_time, Long end_time, Integer star, Integer preGold, Integer preOil, Integer preAttRenown, Integer attRenown, Integer attGold, Integer attOil, Boolean isHitBack, String clancid, String cname, Integer cicon, Integer offenHP, Integer offenAtt, String defccid, String defcname, Integer defcicon, Integer defenseHP, Integer defenseAtt, String attInfos, Integer beRewon, Integer egid, Integer num, String attHeros) {
        Attack_mail result = new Attack_mail();
        result.id = id;
        result.attcid = attcid;
        result.attPcid = attPcid;
        result.attPname = attPname;
        result.beAttPcid = beAttPcid;
        result.beAttPname = beAttPname;
        result.begin_time = begin_time;
        result.end_time = end_time;
        result.star = star;
        result.preGold = preGold;
        result.preOil = preOil;
        result.preAttRenown = preAttRenown;
        result.attRenown = attRenown;
        result.attGold = attGold;
        result.attOil = attOil;
        result.isHitBack = isHitBack;
        result.clancid = clancid;
        result.cname = cname;
        result.cicon = cicon;
        result.offenHP = offenHP;
        result.offenAtt = offenAtt;
        result.defccid = defccid;
        result.defcname = defcname;
        result.defcicon = defcicon;
        result.defenseHP = defenseHP;
        result.defenseAtt = defenseAtt;
        result.attInfos = attInfos;
        result.beRewon = beRewon;
        result.egid = egid;
        result.num = num;
        result.attHeros = attHeros;
        return result;
    }

    public static Attack_mail newAttack_mail(Attack_mail attack_mail) {
        Attack_mail result = new Attack_mail();
        result.id = attack_mail.id;
        result.attcid = attack_mail.attcid;
        result.attPcid = attack_mail.attPcid;
        result.attPname = attack_mail.attPname;
        result.beAttPcid = attack_mail.beAttPcid;
        result.beAttPname = attack_mail.beAttPname;
        result.begin_time = attack_mail.begin_time;
        result.end_time = attack_mail.end_time;
        result.star = attack_mail.star;
        result.preGold = attack_mail.preGold;
        result.preOil = attack_mail.preOil;
        result.preAttRenown = attack_mail.preAttRenown;
        result.attRenown = attack_mail.attRenown;
        result.attGold = attack_mail.attGold;
        result.attOil = attack_mail.attOil;
        result.isHitBack = attack_mail.isHitBack;
        result.clancid = attack_mail.clancid;
        result.cname = attack_mail.cname;
        result.cicon = attack_mail.cicon;
        result.offenHP = attack_mail.offenHP;
        result.offenAtt = attack_mail.offenAtt;
        result.defccid = attack_mail.defccid;
        result.defcname = attack_mail.defcname;
        result.defcicon = attack_mail.defcicon;
        result.defenseHP = attack_mail.defenseHP;
        result.defenseAtt = attack_mail.defenseAtt;
        result.attInfos = attack_mail.attInfos;
        result.beRewon = attack_mail.beRewon;
        result.egid = attack_mail.egid;
        result.num = attack_mail.num;
        result.attHeros = attack_mail.attHeros;
        return result;
    }

    public Attack_mail createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, attcid);
        B2OutputStream.writeObject(_out, attPcid);
        B2OutputStream.writeObject(_out, attPname);
        B2OutputStream.writeObject(_out, beAttPcid);
        B2OutputStream.writeObject(_out, beAttPname);
        B2OutputStream.writeObject(_out, begin_time);
        B2OutputStream.writeObject(_out, end_time);
        B2OutputStream.writeObject(_out, star);
        B2OutputStream.writeObject(_out, preGold);
        B2OutputStream.writeObject(_out, preOil);
        B2OutputStream.writeObject(_out, preAttRenown);
        B2OutputStream.writeObject(_out, attRenown);
        B2OutputStream.writeObject(_out, attGold);
        B2OutputStream.writeObject(_out, attOil);
        B2OutputStream.writeObject(_out, isHitBack);
        B2OutputStream.writeObject(_out, clancid);
        B2OutputStream.writeObject(_out, cname);
        B2OutputStream.writeObject(_out, cicon);
        B2OutputStream.writeObject(_out, offenHP);
        B2OutputStream.writeObject(_out, offenAtt);
        B2OutputStream.writeObject(_out, defccid);
        B2OutputStream.writeObject(_out, defcname);
        B2OutputStream.writeObject(_out, defcicon);
        B2OutputStream.writeObject(_out, defenseHP);
        B2OutputStream.writeObject(_out, defenseAtt);
        B2OutputStream.writeObject(_out, attInfos);
        B2OutputStream.writeObject(_out, beRewon);
        B2OutputStream.writeObject(_out, egid);
        B2OutputStream.writeObject(_out, num);
        B2OutputStream.writeObject(_out, attHeros);
    }

    public static final Attack_mail readObject(InputStream _in) throws Exception {
        Attack_mail result = new Attack_mail();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.attcid = (String) B2InputStream.readObject(_in);
        result.attPcid = (Integer) B2InputStream.readObject(_in);
        result.attPname = (String) B2InputStream.readObject(_in);
        result.beAttPcid = (Integer) B2InputStream.readObject(_in);
        result.beAttPname = (String) B2InputStream.readObject(_in);
        result.begin_time = (Long) B2InputStream.readObject(_in);
        result.end_time = (Long) B2InputStream.readObject(_in);
        result.star = (Integer) B2InputStream.readObject(_in);
        result.preGold = (Integer) B2InputStream.readObject(_in);
        result.preOil = (Integer) B2InputStream.readObject(_in);
        result.preAttRenown = (Integer) B2InputStream.readObject(_in);
        result.attRenown = (Integer) B2InputStream.readObject(_in);
        result.attGold = (Integer) B2InputStream.readObject(_in);
        result.attOil = (Integer) B2InputStream.readObject(_in);
        result.isHitBack = (Boolean) B2InputStream.readObject(_in);
        result.clancid = (String) B2InputStream.readObject(_in);
        result.cname = (String) B2InputStream.readObject(_in);
        result.cicon = (Integer) B2InputStream.readObject(_in);
        result.offenHP = (Integer) B2InputStream.readObject(_in);
        result.offenAtt = (Integer) B2InputStream.readObject(_in);
        result.defccid = (String) B2InputStream.readObject(_in);
        result.defcname = (String) B2InputStream.readObject(_in);
        result.defcicon = (Integer) B2InputStream.readObject(_in);
        result.defenseHP = (Integer) B2InputStream.readObject(_in);
        result.defenseAtt = (Integer) B2InputStream.readObject(_in);
        result.attInfos = (String) B2InputStream.readObject(_in);
        result.beRewon = (Integer) B2InputStream.readObject(_in);
        result.egid = (Integer) B2InputStream.readObject(_in);
        result.num = (Integer) B2InputStream.readObject(_in);
        result.attHeros = (String) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getAttack_mail(){
        Attack_mail attack_mail = null; // attack_mail
        { // new and insert Attack_mail (attack_mail)
            int id = 0; 	// id
            String attcid = ""; 	// attcid
            int attPcid = 0; 	// attPcid
            String attPname = ""; 	// attPname
            int beAttPcid = 0; 	// beAttPcid
            String beAttPname = ""; 	// beAttPname
            long begin_time = 0; 	// begin_time
            long end_time = 0; 	// end_time
            int star = 0; 	// star
            int preGold = 0; 	// preGold
            int preOil = 0; 	// preOil
            int preAttRenown = 0; 	// preAttRenown
            int attRenown = 0; 	// attRenown
            int attGold = 0; 	// attGold
            int attOil = 0; 	// attOil
            boolean isHitBack = true; 	// isHitBack
            String clancid = ""; 	// clancid
            String cname = ""; 	// cname
            int cicon = 0; 	// cicon
            int offenHP = 0; 	// offenHP
            int offenAtt = 0; 	// offenAtt
            String defccid = ""; 	// defccid
            String defcname = ""; 	// defcname
            int defcicon = 0; 	// defcicon
            int defenseHP = 0; 	// defenseHP
            int defenseAtt = 0; 	// defenseAtt
            String attInfos = ""; 	// attInfos
            int beRewon = 0; 	// beRewon
            int egid = 0; 	// egid
            int num = 0; 	// num
            String attHeros = ""; 	// attHeros
            attack_mail = Attack_mail.newAttack_mail(id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros);
        }
        attack_mail = attack_mail.insert();

        int id = attack_mail.getId(); 	// id
        String attcid = attack_mail.getAttcid(); 	// attcid
        int attPcid = attack_mail.getAttpcid(); 	// attPcid
        String attPname = attack_mail.getAttpname(); 	// attPname
        int beAttPcid = attack_mail.getBeattpcid(); 	// beAttPcid
        String beAttPname = attack_mail.getBeattpname(); 	// beAttPname
        long begin_time = attack_mail.getBegin_time(); 	// begin_time
        long end_time = attack_mail.getEnd_time(); 	// end_time
        int star = attack_mail.getStar(); 	// star
        int preGold = attack_mail.getPregold(); 	// preGold
        int preOil = attack_mail.getPreoil(); 	// preOil
        int preAttRenown = attack_mail.getPreattrenown(); 	// preAttRenown
        int attRenown = attack_mail.getAttrenown(); 	// attRenown
        int attGold = attack_mail.getAttgold(); 	// attGold
        int attOil = attack_mail.getAttoil(); 	// attOil
        boolean isHitBack = attack_mail.getIshitback(); 	// isHitBack
        String clancid = attack_mail.getClancid(); 	// clancid
        String cname = attack_mail.getCname(); 	// cname
        int cicon = attack_mail.getCicon(); 	// cicon
        int offenHP = attack_mail.getOffenhp(); 	// offenHP
        int offenAtt = attack_mail.getOffenatt(); 	// offenAtt
        String defccid = attack_mail.getDefccid(); 	// defccid
        String defcname = attack_mail.getDefcname(); 	// defcname
        int defcicon = attack_mail.getDefcicon(); 	// defcicon
        int defenseHP = attack_mail.getDefensehp(); 	// defenseHP
        int defenseAtt = attack_mail.getDefenseatt(); 	// defenseAtt
        String attInfos = attack_mail.getAttinfos(); 	// attInfos
        int beRewon = attack_mail.getBerewon(); 	// beRewon
        int egid = attack_mail.getEgid(); 	// egid
        int num = attack_mail.getNum(); 	// num
        String attHeros = attack_mail.getAttheros(); 	// attHeros
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
        case "attPcid":
            return attPcid;
        case "beAttPcid":
            return beAttPcid;
        case "star":
            return star;
        case "preGold":
            return preGold;
        case "preOil":
            return preOil;
        case "preAttRenown":
            return preAttRenown;
        case "attRenown":
            return attRenown;
        case "attGold":
            return attGold;
        case "attOil":
            return attOil;
        case "cicon":
            return cicon;
        case "offenHP":
            return offenHP;
        case "offenAtt":
            return offenAtt;
        case "defcicon":
            return defcicon;
        case "defenseHP":
            return defenseHP;
        case "defenseAtt":
            return defenseAtt;
        case "beRewon":
            return beRewon;
        case "egid":
            return egid;
        case "num":
            return num;
        }
        return 0;
    }

    public Attack_mail setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Attack_mail setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "attPcid":
            return setAttpcid(value2);
        case "beAttPcid":
            return setBeattpcid(value2);
        case "star":
            return setStar(value2);
        case "preGold":
            return setPregold(value2);
        case "preOil":
            return setPreoil(value2);
        case "preAttRenown":
            return setPreattrenown(value2);
        case "attRenown":
            return setAttrenown(value2);
        case "attGold":
            return setAttgold(value2);
        case "attOil":
            return setAttoil(value2);
        case "cicon":
            return setCicon(value2);
        case "offenHP":
            return setOffenhp(value2);
        case "offenAtt":
            return setOffenatt(value2);
        case "defcicon":
            return setDefcicon(value2);
        case "defenseHP":
            return setDefensehp(value2);
        case "defenseAtt":
            return setDefenseatt(value2);
        case "beRewon":
            return setBerewon(value2);
        case "egid":
            return setEgid(value2);
        case "num":
            return setNum(value2);
        }
        return this;
    }

    public Attack_mail changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Attack_mail changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "attPcid":
            return changeAttpcid(value2);
        case "beAttPcid":
            return changeBeattpcid(value2);
        case "star":
            return changeStar(value2);
        case "preGold":
            return changePregold(value2);
        case "preOil":
            return changePreoil(value2);
        case "preAttRenown":
            return changePreattrenown(value2);
        case "attRenown":
            return changeAttrenown(value2);
        case "attGold":
            return changeAttgold(value2);
        case "attOil":
            return changeAttoil(value2);
        case "cicon":
            return changeCicon(value2);
        case "offenHP":
            return changeOffenhp(value2);
        case "offenAtt":
            return changeOffenatt(value2);
        case "defcicon":
            return changeDefcicon(value2);
        case "defenseHP":
            return changeDefensehp(value2);
        case "defenseAtt":
            return changeDefenseatt(value2);
        case "beRewon":
            return changeBerewon(value2);
        case "egid":
            return changeEgid(value2);
        case "num":
            return changeNum(value2);
        }
        return this;
    }

    public Attack_mail changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Attack_mail changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "attPcid":
            return changeAttpcidWithMin(value2, _min);
        case "beAttPcid":
            return changeBeattpcidWithMin(value2, _min);
        case "star":
            return changeStarWithMin(value2, _min);
        case "preGold":
            return changePregoldWithMin(value2, _min);
        case "preOil":
            return changePreoilWithMin(value2, _min);
        case "preAttRenown":
            return changePreattrenownWithMin(value2, _min);
        case "attRenown":
            return changeAttrenownWithMin(value2, _min);
        case "attGold":
            return changeAttgoldWithMin(value2, _min);
        case "attOil":
            return changeAttoilWithMin(value2, _min);
        case "cicon":
            return changeCiconWithMin(value2, _min);
        case "offenHP":
            return changeOffenhpWithMin(value2, _min);
        case "offenAtt":
            return changeOffenattWithMin(value2, _min);
        case "defcicon":
            return changeDefciconWithMin(value2, _min);
        case "defenseHP":
            return changeDefensehpWithMin(value2, _min);
        case "defenseAtt":
            return changeDefenseattWithMin(value2, _min);
        case "beRewon":
            return changeBerewonWithMin(value2, _min);
        case "egid":
            return changeEgidWithMin(value2, _min);
        case "num":
            return changeNumWithMin(value2, _min);
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

    public Attack_mail setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Attack_mail setDouble(String fieldEn, double value2) {
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
        case "attcid": 
            return attcid;
        case "attPname": 
            return attPname;
        case "beAttPname": 
            return beAttPname;
        case "clancid": 
            return clancid;
        case "cname": 
            return cname;
        case "defccid": 
            return defccid;
        case "defcname": 
            return defcname;
        case "attInfos": 
            return attInfos;
        case "attHeros": 
            return attHeros;
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
        case "attcid":
            return attcid;
        case "attPcid":
            return attPcid;
        case "attPname":
            return attPname;
        case "beAttPcid":
            return beAttPcid;
        case "beAttPname":
            return beAttPname;
        case "begin_time":
            return begin_time;
        case "end_time":
            return end_time;
        case "star":
            return star;
        case "preGold":
            return preGold;
        case "preOil":
            return preOil;
        case "preAttRenown":
            return preAttRenown;
        case "attRenown":
            return attRenown;
        case "attGold":
            return attGold;
        case "attOil":
            return attOil;
        case "isHitBack":
            return isHitBack;
        case "clancid":
            return clancid;
        case "cname":
            return cname;
        case "cicon":
            return cicon;
        case "offenHP":
            return offenHP;
        case "offenAtt":
            return offenAtt;
        case "defccid":
            return defccid;
        case "defcname":
            return defcname;
        case "defcicon":
            return defcicon;
        case "defenseHP":
            return defenseHP;
        case "defenseAtt":
            return defenseAtt;
        case "attInfos":
            return attInfos;
        case "beRewon":
            return beRewon;
        case "egid":
            return egid;
        case "num":
            return num;
        case "attHeros":
            return attHeros;
        }
        return null;
    }

    public Attack_mail setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Attack_mail setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "attcid":
            return setAttcid(value2);
        case "attPname":
            return setAttpname(value2);
        case "beAttPname":
            return setBeattpname(value2);
        case "clancid":
            return setClancid(value2);
        case "cname":
            return setCname(value2);
        case "defccid":
            return setDefccid(value2);
        case "defcname":
            return setDefcname(value2);
        case "attInfos":
            return setAttinfos(value2);
        case "attHeros":
            return setAttheros(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Attack_mail setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Attack_mail setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Attack_mail");
        result.put("id", id);
        result.put("attcid", attcid);
        result.put("attPcid", attPcid);
        result.put("attPname", attPname);
        result.put("beAttPcid", beAttPcid);
        result.put("beAttPname", beAttPname);
        result.put("begin_time", begin_time);
        result.put("end_time", end_time);
        result.put("star", star);
        result.put("preGold", preGold);
        result.put("preOil", preOil);
        result.put("preAttRenown", preAttRenown);
        result.put("attRenown", attRenown);
        result.put("attGold", attGold);
        result.put("attOil", attOil);
        result.put("isHitBack", isHitBack);
        result.put("clancid", clancid);
        result.put("cname", cname);
        result.put("cicon", cicon);
        result.put("offenHP", offenHP);
        result.put("offenAtt", offenAtt);
        result.put("defccid", defccid);
        result.put("defcname", defcname);
        result.put("defcicon", defcicon);
        result.put("defenseHP", defenseHP);
        result.put("defenseAtt", defenseAtt);
        result.put("attInfos", attInfos);
        result.put("beRewon", beRewon);
        result.put("egid", egid);
        result.put("num", num);
        result.put("attHeros", attHeros);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("attcid", attcid);
        result.put("attPcid", attPcid);
        result.put("attPname", attPname);
        result.put("beAttPcid", beAttPcid);
        result.put("beAttPname", beAttPname);
        result.put("begin_time", begin_time);
        result.put("end_time", end_time);
        result.put("star", star);
        result.put("preGold", preGold);
        result.put("preOil", preOil);
        result.put("preAttRenown", preAttRenown);
        result.put("attRenown", attRenown);
        result.put("attGold", attGold);
        result.put("attOil", attOil);
        result.put("isHitBack", isHitBack);
        result.put("clancid", clancid);
        result.put("cname", cname);
        result.put("cicon", cicon);
        result.put("offenHP", offenHP);
        result.put("offenAtt", offenAtt);
        result.put("defccid", defccid);
        result.put("defcname", defcname);
        result.put("defcicon", defcicon);
        result.put("defenseHP", defenseHP);
        result.put("defenseAtt", defenseAtt);
        result.put("attInfos", attInfos);
        result.put("beRewon", beRewon);
        result.put("egid", egid);
        result.put("num", num);
        result.put("attHeros", attHeros);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Attack_mail");
        result.put("id", id);
        result.put("attcid", attcid);
        result.put("attPcid", attPcid);
        result.put("attPname", attPname);
        result.put("beAttPcid", beAttPcid);
        result.put("beAttPname", beAttPname);
        result.put("begin_time", begin_time);
        result.put("end_time", end_time);
        result.put("star", star);
        result.put("preGold", preGold);
        result.put("preOil", preOil);
        result.put("preAttRenown", preAttRenown);
        result.put("attRenown", attRenown);
        result.put("attGold", attGold);
        result.put("attOil", attOil);
        result.put("isHitBack", isHitBack);
        result.put("clancid", clancid);
        result.put("cname", cname);
        result.put("cicon", cicon);
        result.put("offenHP", offenHP);
        result.put("offenAtt", offenAtt);
        result.put("defccid", defccid);
        result.put("defcname", defcname);
        result.put("defcicon", defcicon);
        result.put("defenseHP", defenseHP);
        result.put("defenseAtt", defenseAtt);
        result.put("attInfos", attInfos);
        result.put("beRewon", beRewon);
        result.put("egid", egid);
        result.put("num", num);
        result.put("attHeros", attHeros);
        return result;
    }

    public Attack_mail mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        String attcid = (String)e.get("attcid");
        Integer attPcid = (Integer)e.get("attPcid");
        String attPname = (String)e.get("attPname");
        Integer beAttPcid = (Integer)e.get("beAttPcid");
        String beAttPname = (String)e.get("beAttPname");
        Long begin_time = (Long)e.get("begin_time");
        Long end_time = (Long)e.get("end_time");
        Integer star = (Integer)e.get("star");
        Integer preGold = (Integer)e.get("preGold");
        Integer preOil = (Integer)e.get("preOil");
        Integer preAttRenown = (Integer)e.get("preAttRenown");
        Integer attRenown = (Integer)e.get("attRenown");
        Integer attGold = (Integer)e.get("attGold");
        Integer attOil = (Integer)e.get("attOil");
        Boolean isHitBack = (Boolean)e.get("isHitBack");
        String clancid = (String)e.get("clancid");
        String cname = (String)e.get("cname");
        Integer cicon = (Integer)e.get("cicon");
        Integer offenHP = (Integer)e.get("offenHP");
        Integer offenAtt = (Integer)e.get("offenAtt");
        String defccid = (String)e.get("defccid");
        String defcname = (String)e.get("defcname");
        Integer defcicon = (Integer)e.get("defcicon");
        Integer defenseHP = (Integer)e.get("defenseHP");
        Integer defenseAtt = (Integer)e.get("defenseAtt");
        String attInfos = (String)e.get("attInfos");
        Integer beRewon = (Integer)e.get("beRewon");
        Integer egid = (Integer)e.get("egid");
        Integer num = (Integer)e.get("num");
        String attHeros = (String)e.get("attHeros");

        if(id == null) id = 0;
        if(attcid == null) attcid = "";
        if(attPcid == null) attPcid = 0;
        if(attPname == null) attPname = "";
        if(beAttPcid == null) beAttPcid = 0;
        if(beAttPname == null) beAttPname = "";
        if(begin_time == null) begin_time = 0L;
        if(end_time == null) end_time = 0L;
        if(star == null) star = 0;
        if(preGold == null) preGold = 0;
        if(preOil == null) preOil = 0;
        if(preAttRenown == null) preAttRenown = 0;
        if(attRenown == null) attRenown = 0;
        if(attGold == null) attGold = 0;
        if(attOil == null) attOil = 0;
        if(isHitBack == null) isHitBack = false;
        if(clancid == null) clancid = "";
        if(cname == null) cname = "";
        if(cicon == null) cicon = 0;
        if(offenHP == null) offenHP = 0;
        if(offenAtt == null) offenAtt = 0;
        if(defccid == null) defccid = "";
        if(defcname == null) defcname = "";
        if(defcicon == null) defcicon = 0;
        if(defenseHP == null) defenseHP = 0;
        if(defenseAtt == null) defenseAtt = 0;
        if(attInfos == null) attInfos = "";
        if(beRewon == null) beRewon = 0;
        if(egid == null) egid = 0;
        if(num == null) num = 0;
        if(attHeros == null) attHeros = "";

        setId(id);
        setAttcid(attcid);
        setAttpcid(attPcid);
        setAttpname(attPname);
        setBeattpcid(beAttPcid);
        setBeattpname(beAttPname);
        setBegin_time(begin_time);
        setEnd_time(end_time);
        setStar(star);
        setPregold(preGold);
        setPreoil(preOil);
        setPreattrenown(preAttRenown);
        setAttrenown(attRenown);
        setAttgold(attGold);
        setAttoil(attOil);
        setIshitback(isHitBack);
        setClancid(clancid);
        setCname(cname);
        setCicon(cicon);
        setOffenhp(offenHP);
        setOffenatt(offenAtt);
        setDefccid(defccid);
        setDefcname(defcname);
        setDefcicon(defcicon);
        setDefensehp(defenseHP);
        setDefenseatt(defenseAtt);
        setAttinfos(attInfos);
        setBerewon(beRewon);
        setEgid(egid);
        setNum(num);
        setAttheros(attHeros);

        return this;
    }

    public static final Attack_mail mapTo(Map e){
        Attack_mail result = new Attack_mail();

        Integer id = (Integer)e.get("id");
        String attcid = (String)e.get("attcid");
        Integer attPcid = (Integer)e.get("attPcid");
        String attPname = (String)e.get("attPname");
        Integer beAttPcid = (Integer)e.get("beAttPcid");
        String beAttPname = (String)e.get("beAttPname");
        Long begin_time = (Long)e.get("begin_time");
        Long end_time = (Long)e.get("end_time");
        Integer star = (Integer)e.get("star");
        Integer preGold = (Integer)e.get("preGold");
        Integer preOil = (Integer)e.get("preOil");
        Integer preAttRenown = (Integer)e.get("preAttRenown");
        Integer attRenown = (Integer)e.get("attRenown");
        Integer attGold = (Integer)e.get("attGold");
        Integer attOil = (Integer)e.get("attOil");
        Boolean isHitBack = (Boolean)e.get("isHitBack");
        String clancid = (String)e.get("clancid");
        String cname = (String)e.get("cname");
        Integer cicon = (Integer)e.get("cicon");
        Integer offenHP = (Integer)e.get("offenHP");
        Integer offenAtt = (Integer)e.get("offenAtt");
        String defccid = (String)e.get("defccid");
        String defcname = (String)e.get("defcname");
        Integer defcicon = (Integer)e.get("defcicon");
        Integer defenseHP = (Integer)e.get("defenseHP");
        Integer defenseAtt = (Integer)e.get("defenseAtt");
        String attInfos = (String)e.get("attInfos");
        Integer beRewon = (Integer)e.get("beRewon");
        Integer egid = (Integer)e.get("egid");
        Integer num = (Integer)e.get("num");
        String attHeros = (String)e.get("attHeros");

        if(id == null) id = 0;
        if(attcid == null) attcid = "";
        if(attPcid == null) attPcid = 0;
        if(attPname == null) attPname = "";
        if(beAttPcid == null) beAttPcid = 0;
        if(beAttPname == null) beAttPname = "";
        if(begin_time == null) begin_time = 0L;
        if(end_time == null) end_time = 0L;
        if(star == null) star = 0;
        if(preGold == null) preGold = 0;
        if(preOil == null) preOil = 0;
        if(preAttRenown == null) preAttRenown = 0;
        if(attRenown == null) attRenown = 0;
        if(attGold == null) attGold = 0;
        if(attOil == null) attOil = 0;
        if(isHitBack == null) isHitBack = false;
        if(clancid == null) clancid = "";
        if(cname == null) cname = "";
        if(cicon == null) cicon = 0;
        if(offenHP == null) offenHP = 0;
        if(offenAtt == null) offenAtt = 0;
        if(defccid == null) defccid = "";
        if(defcname == null) defcname = "";
        if(defcicon == null) defcicon = 0;
        if(defenseHP == null) defenseHP = 0;
        if(defenseAtt == null) defenseAtt = 0;
        if(attInfos == null) attInfos = "";
        if(beRewon == null) beRewon = 0;
        if(egid == null) egid = 0;
        if(num == null) num = 0;
        if(attHeros == null) attHeros = "";

        result.id = id;
        result.attcid = attcid;
        result.attPcid = attPcid;
        result.attPname = attPname;
        result.beAttPcid = beAttPcid;
        result.beAttPname = beAttPname;
        result.begin_time = begin_time;
        result.end_time = end_time;
        result.star = star;
        result.preGold = preGold;
        result.preOil = preOil;
        result.preAttRenown = preAttRenown;
        result.attRenown = attRenown;
        result.attGold = attGold;
        result.attOil = attOil;
        result.isHitBack = isHitBack;
        result.clancid = clancid;
        result.cname = cname;
        result.cicon = cicon;
        result.offenHP = offenHP;
        result.offenAtt = offenAtt;
        result.defccid = defccid;
        result.defcname = defcname;
        result.defcicon = defcicon;
        result.defenseHP = defenseHP;
        result.defenseAtt = defenseAtt;
        result.attInfos = attInfos;
        result.beRewon = beRewon;
        result.egid = egid;
        result.num = num;
        result.attHeros = attHeros;

        return result;
    }

    public static final Attack_mail originalTo(Map e){
        Attack_mail result = new Attack_mail();

        Integer id = (Integer)e.get("id");
        String attcid = (String)e.get("attcid");
        Integer attPcid = (Integer)e.get("attPcid");
        String attPname = (String)e.get("attPname");
        Integer beAttPcid = (Integer)e.get("beAttPcid");
        String beAttPname = (String)e.get("beAttPname");
        Long begin_time = (Long)e.get("begin_time");
        Long end_time = (Long)e.get("end_time");
        Integer star = (Integer)e.get("star");
        Integer preGold = (Integer)e.get("preGold");
        Integer preOil = (Integer)e.get("preOil");
        Integer preAttRenown = (Integer)e.get("preAttRenown");
        Integer attRenown = (Integer)e.get("attRenown");
        Integer attGold = (Integer)e.get("attGold");
        Integer attOil = (Integer)e.get("attOil");
        Boolean isHitBack = (Boolean)e.get("isHitBack");
        String clancid = (String)e.get("clancid");
        String cname = (String)e.get("cname");
        Integer cicon = (Integer)e.get("cicon");
        Integer offenHP = (Integer)e.get("offenHP");
        Integer offenAtt = (Integer)e.get("offenAtt");
        String defccid = (String)e.get("defccid");
        String defcname = (String)e.get("defcname");
        Integer defcicon = (Integer)e.get("defcicon");
        Integer defenseHP = (Integer)e.get("defenseHP");
        Integer defenseAtt = (Integer)e.get("defenseAtt");
        String attInfos = (String)e.get("attInfos");
        Integer beRewon = (Integer)e.get("beRewon");
        Integer egid = (Integer)e.get("egid");
        Integer num = (Integer)e.get("num");
        String attHeros = (String)e.get("attHeros");

        if(id == null) id = 0;
        if(attcid == null) attcid = "";
        if(attPcid == null) attPcid = 0;
        if(attPname == null) attPname = "";
        if(beAttPcid == null) beAttPcid = 0;
        if(beAttPname == null) beAttPname = "";
        if(begin_time == null) begin_time = 0L;
        if(end_time == null) end_time = 0L;
        if(star == null) star = 0;
        if(preGold == null) preGold = 0;
        if(preOil == null) preOil = 0;
        if(preAttRenown == null) preAttRenown = 0;
        if(attRenown == null) attRenown = 0;
        if(attGold == null) attGold = 0;
        if(attOil == null) attOil = 0;
        if(isHitBack == null) isHitBack = false;
        if(clancid == null) clancid = "";
        if(cname == null) cname = "";
        if(cicon == null) cicon = 0;
        if(offenHP == null) offenHP = 0;
        if(offenAtt == null) offenAtt = 0;
        if(defccid == null) defccid = "";
        if(defcname == null) defcname = "";
        if(defcicon == null) defcicon = 0;
        if(defenseHP == null) defenseHP = 0;
        if(defenseAtt == null) defenseAtt = 0;
        if(attInfos == null) attInfos = "";
        if(beRewon == null) beRewon = 0;
        if(egid == null) egid = 0;
        if(num == null) num = 0;
        if(attHeros == null) attHeros = "";

        result.id = id;
        result.attcid = attcid;
        result.attPcid = attPcid;
        result.attPname = attPname;
        result.beAttPcid = beAttPcid;
        result.beAttPname = beAttPname;
        result.begin_time = begin_time;
        result.end_time = end_time;
        result.star = star;
        result.preGold = preGold;
        result.preOil = preOil;
        result.preAttRenown = preAttRenown;
        result.attRenown = attRenown;
        result.attGold = attGold;
        result.attOil = attOil;
        result.isHitBack = isHitBack;
        result.clancid = clancid;
        result.cname = cname;
        result.cicon = cicon;
        result.offenHP = offenHP;
        result.offenAtt = offenAtt;
        result.defccid = defccid;
        result.defcname = defcname;
        result.defcicon = defcicon;
        result.defenseHP = defenseHP;
        result.defenseAtt = defenseAtt;
        result.attInfos = attInfos;
        result.beRewon = beRewon;
        result.egid = egid;
        result.num = num;
        result.attHeros = attHeros;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Attack_mail createFor(byte[] b) throws Exception {
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
    public static final Attack_mail loadByKey(int id) {
        return Attack_mailEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Attack_mail insert() {
        Attack_mail result = Attack_mailEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Attack_mail asyncInsert() {
        Attack_mail result = Attack_mailEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Attack_mail insert2() {
        return Attack_mailEntity.insert2(this);
    }

    public final Attack_mail asyncInsert2() {
        Attack_mail result = Attack_mailEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Attack_mail update() {
        return Attack_mailEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Attack_mailEntity.asynchronousUpdate( this );
        return true;
    }

    public final Attack_mail insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Attack_mailEntity.delete(id);
    }

    public final void asyncDelete() {
        Attack_mailEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Attack_mail clone2() {
        try{
            return (Attack_mail) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
