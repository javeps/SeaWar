package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - player
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Player extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -88255845; // com.sea.db.bean.Player

    public static String TABLENAME = "player";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "pcid", "ucid", "svcid", "pname", "type", "state", "channel", "icon", "exp", "crystal", "renown", "weekRenown", "cur_npc", "npcs", "all_money", "last_money", "last_pay_time", "guid_step", "clancid", "clanPost", "cname", "cicon", "maxBuidId", "maxObstId", "stored_oil", "stored_gold", "isOnline", "protectTime", "maxAttMCId", "maxBONum", "curBONum", "spells", "lastAddObst", "mark", "curtownlvl", "loginDay", "lastLoginTime", "isRewardDay", "firstPayStatus", "pieceHPNum", "pieceDamNum", "pieceAttSpeed", "dayTasks", "lastLeaveClan", "monthCode", "isMonCode", "builds", "obstes", "teches", "heroes", "share", "moneyActivity", "moneyActivityType"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "INT", "VARCHAR", "INT", "INT", "VARCHAR", "INT", "INT", "INT", "INT", "INT", "INT", "TEXT", "INT", "INT", "BIGINT", "INT", "VARCHAR", "INT", "VARCHAR", "INT", "INT", "INT", "INT", "INT", "BIT", "BIGINT", "INT", "INT", "INT", "TEXT", "BIGINT", "TEXT", "INT", "INT", "BIGINT", "BIT", "INT", "INT", "INT", "INT", "TEXT", "BIGINT", "BIGINT", "BIT", "TEXT", "TEXT", "TEXT", "TEXT", "INT", "DOUBLE", "INT"};


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
    public int ucid;
    public int svcid;
    public String pname;
    public int type;
    public int state;
    public String channel;
    public int icon;
    public int exp;
    public int crystal;
    public int renown;
    public int weekRenown;
    public int cur_npc;
    public String npcs;
    public int all_money;
    public int last_money;
    public long last_pay_time;
    public int guid_step;
    public String clancid;
    public int clanPost;
    public String cname;
    public int cicon;
    public int maxBuidId;
    public int maxObstId;
    public int stored_oil;
    public int stored_gold;
    public boolean isOnline;
    public long protectTime;
    public int maxAttMCId;
    public int maxBONum;
    public int curBONum;
    public String spells;
    public long lastAddObst;
    public String mark;
    public int curtownlvl;
    public int loginDay;
    public long lastLoginTime;
    public boolean isRewardDay;
    public int firstPayStatus;
    public int pieceHPNum;
    public int pieceDamNum;
    public int pieceAttSpeed;
    public String dayTasks;
    public long lastLeaveClan;
    public long monthCode;
    public boolean isMonCode;
    public String builds;
    public String obstes;
    public String teches;
    public String heroes;
    public int share;
    public double moneyActivity;
    public int moneyActivityType;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Player setId(int id){
        this.id = id;
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Player setPcid(int pcid){
        int _old = pcid;
        this.pcid = pcid;
        changeIt("pcid", _old, pcid);
        return this;
    }

    public Player changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Player changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Player changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Player changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public int getUcid(){
        return ucid;
    }

    public Player setUcid(int ucid){
        int _old = ucid;
        this.ucid = ucid;
        changeIt("ucid", _old, ucid);
        return this;
    }

    public Player changeUcid(int ucid){
        return setUcid(this.ucid + ucid);
    }

    public Player changeUcidWithMin(int ucid, int _min){
        int _v2 = this.ucid + ucid;
        return setUcid(_v2 < _min  ? _min : _v2);
    }

    public Player changeUcidWithMax(int ucid, int _max){
        int _v2 = this.ucid + ucid;
        return setUcid(_v2 > _max  ? _max : _v2);
    }

    public Player changeUcidWithMinMax(int ucid, int _min, int _max){
        int _v2 = this.ucid + ucid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setUcid(_v2 < _min  ? _min : _v2);
    }

    public int getSvcid(){
        return svcid;
    }

    public Player setSvcid(int svcid){
        int _old = svcid;
        this.svcid = svcid;
        changeIt("svcid", _old, svcid);
        return this;
    }

    public Player changeSvcid(int svcid){
        return setSvcid(this.svcid + svcid);
    }

    public Player changeSvcidWithMin(int svcid, int _min){
        int _v2 = this.svcid + svcid;
        return setSvcid(_v2 < _min  ? _min : _v2);
    }

    public Player changeSvcidWithMax(int svcid, int _max){
        int _v2 = this.svcid + svcid;
        return setSvcid(_v2 > _max  ? _max : _v2);
    }

    public Player changeSvcidWithMinMax(int svcid, int _min, int _max){
        int _v2 = this.svcid + svcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setSvcid(_v2 < _min  ? _min : _v2);
    }

    public String getPname(){
        return pname;
    }

    public Player setPname(String pname){
        String _old = pname;
        this.pname = pname;
        changeIt("pname", _old, pname);
        return this;
    }

    public int getType(){
        return type;
    }

    public Player setType(int type){
        int _old = type;
        this.type = type;
        changeIt("type", _old, type);
        return this;
    }

    public Player changeType(int type){
        return setType(this.type + type);
    }

    public Player changeTypeWithMin(int type, int _min){
        int _v2 = this.type + type;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public Player changeTypeWithMax(int type, int _max){
        int _v2 = this.type + type;
        return setType(_v2 > _max  ? _max : _v2);
    }

    public Player changeTypeWithMinMax(int type, int _min, int _max){
        int _v2 = this.type + type;
        _v2 = _v2 > _max  ? _max : _v2;
        return setType(_v2 < _min  ? _min : _v2);
    }

    public int getState(){
        return state;
    }

    public Player setState(int state){
        int _old = state;
        this.state = state;
        changeIt("state", _old, state);
        return this;
    }

    public Player changeState(int state){
        return setState(this.state + state);
    }

    public Player changeStateWithMin(int state, int _min){
        int _v2 = this.state + state;
        return setState(_v2 < _min  ? _min : _v2);
    }

    public Player changeStateWithMax(int state, int _max){
        int _v2 = this.state + state;
        return setState(_v2 > _max  ? _max : _v2);
    }

    public Player changeStateWithMinMax(int state, int _min, int _max){
        int _v2 = this.state + state;
        _v2 = _v2 > _max  ? _max : _v2;
        return setState(_v2 < _min  ? _min : _v2);
    }

    public String getChannel(){
        return channel;
    }

    public Player setChannel(String channel){
        String _old = channel;
        this.channel = channel;
        changeIt("channel", _old, channel);
        return this;
    }

    public int getIcon(){
        return icon;
    }

    public Player setIcon(int icon){
        int _old = icon;
        this.icon = icon;
        changeIt("icon", _old, icon);
        return this;
    }

    public Player changeIcon(int icon){
        return setIcon(this.icon + icon);
    }

    public Player changeIconWithMin(int icon, int _min){
        int _v2 = this.icon + icon;
        return setIcon(_v2 < _min  ? _min : _v2);
    }

    public Player changeIconWithMax(int icon, int _max){
        int _v2 = this.icon + icon;
        return setIcon(_v2 > _max  ? _max : _v2);
    }

    public Player changeIconWithMinMax(int icon, int _min, int _max){
        int _v2 = this.icon + icon;
        _v2 = _v2 > _max  ? _max : _v2;
        return setIcon(_v2 < _min  ? _min : _v2);
    }

    public int getExp(){
        return exp;
    }

    public Player setExp(int exp){
        int _old = exp;
        this.exp = exp;
        changeIt("exp", _old, exp);
        return this;
    }

    public Player changeExp(int exp){
        return setExp(this.exp + exp);
    }

    public Player changeExpWithMin(int exp, int _min){
        int _v2 = this.exp + exp;
        return setExp(_v2 < _min  ? _min : _v2);
    }

    public Player changeExpWithMax(int exp, int _max){
        int _v2 = this.exp + exp;
        return setExp(_v2 > _max  ? _max : _v2);
    }

    public Player changeExpWithMinMax(int exp, int _min, int _max){
        int _v2 = this.exp + exp;
        _v2 = _v2 > _max  ? _max : _v2;
        return setExp(_v2 < _min  ? _min : _v2);
    }

    public int getCrystal(){
        return crystal;
    }

    public Player setCrystal(int crystal){
        int _old = crystal;
        this.crystal = crystal;
        changeIt("crystal", _old, crystal);
        return this;
    }

    public Player changeCrystal(int crystal){
        return setCrystal(this.crystal + crystal);
    }

    public Player changeCrystalWithMin(int crystal, int _min){
        int _v2 = this.crystal + crystal;
        return setCrystal(_v2 < _min  ? _min : _v2);
    }

    public Player changeCrystalWithMax(int crystal, int _max){
        int _v2 = this.crystal + crystal;
        return setCrystal(_v2 > _max  ? _max : _v2);
    }

    public Player changeCrystalWithMinMax(int crystal, int _min, int _max){
        int _v2 = this.crystal + crystal;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCrystal(_v2 < _min  ? _min : _v2);
    }

    public int getRenown(){
        return renown;
    }

    public Player setRenown(int renown){
        int _old = renown;
        this.renown = renown;
        changeIt("renown", _old, renown);
        return this;
    }

    public Player changeRenown(int renown){
        return setRenown(this.renown + renown);
    }

    public Player changeRenownWithMin(int renown, int _min){
        int _v2 = this.renown + renown;
        return setRenown(_v2 < _min  ? _min : _v2);
    }

    public Player changeRenownWithMax(int renown, int _max){
        int _v2 = this.renown + renown;
        return setRenown(_v2 > _max  ? _max : _v2);
    }

    public Player changeRenownWithMinMax(int renown, int _min, int _max){
        int _v2 = this.renown + renown;
        _v2 = _v2 > _max  ? _max : _v2;
        return setRenown(_v2 < _min  ? _min : _v2);
    }

    public int getWeekrenown(){
        return weekRenown;
    }

    public Player setWeekrenown(int weekRenown){
        int _old = weekRenown;
        this.weekRenown = weekRenown;
        changeIt("weekRenown", _old, weekRenown);
        return this;
    }

    public Player changeWeekrenown(int weekRenown){
        return setWeekrenown(this.weekRenown + weekRenown);
    }

    public Player changeWeekrenownWithMin(int weekRenown, int _min){
        int _v2 = this.weekRenown + weekRenown;
        return setWeekrenown(_v2 < _min  ? _min : _v2);
    }

    public Player changeWeekrenownWithMax(int weekRenown, int _max){
        int _v2 = this.weekRenown + weekRenown;
        return setWeekrenown(_v2 > _max  ? _max : _v2);
    }

    public Player changeWeekrenownWithMinMax(int weekRenown, int _min, int _max){
        int _v2 = this.weekRenown + weekRenown;
        _v2 = _v2 > _max  ? _max : _v2;
        return setWeekrenown(_v2 < _min  ? _min : _v2);
    }

    public int getCur_npc(){
        return cur_npc;
    }

    public Player setCur_npc(int cur_npc){
        int _old = cur_npc;
        this.cur_npc = cur_npc;
        changeIt("cur_npc", _old, cur_npc);
        return this;
    }

    public Player changeCur_npc(int cur_npc){
        return setCur_npc(this.cur_npc + cur_npc);
    }

    public Player changeCur_npcWithMin(int cur_npc, int _min){
        int _v2 = this.cur_npc + cur_npc;
        return setCur_npc(_v2 < _min  ? _min : _v2);
    }

    public Player changeCur_npcWithMax(int cur_npc, int _max){
        int _v2 = this.cur_npc + cur_npc;
        return setCur_npc(_v2 > _max  ? _max : _v2);
    }

    public Player changeCur_npcWithMinMax(int cur_npc, int _min, int _max){
        int _v2 = this.cur_npc + cur_npc;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCur_npc(_v2 < _min  ? _min : _v2);
    }

    public String getNpcs(){
        return npcs;
    }

    public Player setNpcs(String npcs){
        String _old = npcs;
        this.npcs = npcs;
        changeIt("npcs", _old, npcs);
        return this;
    }

    public int getAll_money(){
        return all_money;
    }

    public Player setAll_money(int all_money){
        int _old = all_money;
        this.all_money = all_money;
        changeIt("all_money", _old, all_money);
        return this;
    }

    public Player changeAll_money(int all_money){
        return setAll_money(this.all_money + all_money);
    }

    public Player changeAll_moneyWithMin(int all_money, int _min){
        int _v2 = this.all_money + all_money;
        return setAll_money(_v2 < _min  ? _min : _v2);
    }

    public Player changeAll_moneyWithMax(int all_money, int _max){
        int _v2 = this.all_money + all_money;
        return setAll_money(_v2 > _max  ? _max : _v2);
    }

    public Player changeAll_moneyWithMinMax(int all_money, int _min, int _max){
        int _v2 = this.all_money + all_money;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAll_money(_v2 < _min  ? _min : _v2);
    }

    public int getLast_money(){
        return last_money;
    }

    public Player setLast_money(int last_money){
        int _old = last_money;
        this.last_money = last_money;
        changeIt("last_money", _old, last_money);
        return this;
    }

    public Player changeLast_money(int last_money){
        return setLast_money(this.last_money + last_money);
    }

    public Player changeLast_moneyWithMin(int last_money, int _min){
        int _v2 = this.last_money + last_money;
        return setLast_money(_v2 < _min  ? _min : _v2);
    }

    public Player changeLast_moneyWithMax(int last_money, int _max){
        int _v2 = this.last_money + last_money;
        return setLast_money(_v2 > _max  ? _max : _v2);
    }

    public Player changeLast_moneyWithMinMax(int last_money, int _min, int _max){
        int _v2 = this.last_money + last_money;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLast_money(_v2 < _min  ? _min : _v2);
    }

    public long getLast_pay_time(){
        return last_pay_time;
    }

    public Player setLast_pay_time(long last_pay_time){
        long _old = last_pay_time;
        this.last_pay_time = last_pay_time;
        changeIt("last_pay_time", _old, last_pay_time);
        return this;
    }

    public Player changeLast_pay_time(long last_pay_time){
        return setLast_pay_time(this.last_pay_time + last_pay_time);
    }

    public Player changeLast_pay_timeWithMin(long last_pay_time, long _min){
        long _v2 = this.last_pay_time + last_pay_time;
        return setLast_pay_time(_v2 < _min  ? _min : _v2);
    }

    public Player changeLast_pay_timeWithMax(long last_pay_time, long _max){
        long _v2 = this.last_pay_time + last_pay_time;
        return setLast_pay_time(_v2 > _max  ? _max : _v2);
    }

    public Player changeLast_pay_timeWithMinMax(long last_pay_time, long _min, long _max){
        long _v2 = this.last_pay_time + last_pay_time;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLast_pay_time(_v2 < _min  ? _min : _v2);
    }

    public int getGuid_step(){
        return guid_step;
    }

    public Player setGuid_step(int guid_step){
        int _old = guid_step;
        this.guid_step = guid_step;
        changeIt("guid_step", _old, guid_step);
        return this;
    }

    public Player changeGuid_step(int guid_step){
        return setGuid_step(this.guid_step + guid_step);
    }

    public Player changeGuid_stepWithMin(int guid_step, int _min){
        int _v2 = this.guid_step + guid_step;
        return setGuid_step(_v2 < _min  ? _min : _v2);
    }

    public Player changeGuid_stepWithMax(int guid_step, int _max){
        int _v2 = this.guid_step + guid_step;
        return setGuid_step(_v2 > _max  ? _max : _v2);
    }

    public Player changeGuid_stepWithMinMax(int guid_step, int _min, int _max){
        int _v2 = this.guid_step + guid_step;
        _v2 = _v2 > _max  ? _max : _v2;
        return setGuid_step(_v2 < _min  ? _min : _v2);
    }

    public String getClancid(){
        return clancid;
    }

    public Player setClancid(String clancid){
        String _old = clancid;
        this.clancid = clancid;
        changeIt("clancid", _old, clancid);
        return this;
    }

    public int getClanpost(){
        return clanPost;
    }

    public Player setClanpost(int clanPost){
        int _old = clanPost;
        this.clanPost = clanPost;
        changeIt("clanPost", _old, clanPost);
        return this;
    }

    public Player changeClanpost(int clanPost){
        return setClanpost(this.clanPost + clanPost);
    }

    public Player changeClanpostWithMin(int clanPost, int _min){
        int _v2 = this.clanPost + clanPost;
        return setClanpost(_v2 < _min  ? _min : _v2);
    }

    public Player changeClanpostWithMax(int clanPost, int _max){
        int _v2 = this.clanPost + clanPost;
        return setClanpost(_v2 > _max  ? _max : _v2);
    }

    public Player changeClanpostWithMinMax(int clanPost, int _min, int _max){
        int _v2 = this.clanPost + clanPost;
        _v2 = _v2 > _max  ? _max : _v2;
        return setClanpost(_v2 < _min  ? _min : _v2);
    }

    public String getCname(){
        return cname;
    }

    public Player setCname(String cname){
        String _old = cname;
        this.cname = cname;
        changeIt("cname", _old, cname);
        return this;
    }

    public int getCicon(){
        return cicon;
    }

    public Player setCicon(int cicon){
        int _old = cicon;
        this.cicon = cicon;
        changeIt("cicon", _old, cicon);
        return this;
    }

    public Player changeCicon(int cicon){
        return setCicon(this.cicon + cicon);
    }

    public Player changeCiconWithMin(int cicon, int _min){
        int _v2 = this.cicon + cicon;
        return setCicon(_v2 < _min  ? _min : _v2);
    }

    public Player changeCiconWithMax(int cicon, int _max){
        int _v2 = this.cicon + cicon;
        return setCicon(_v2 > _max  ? _max : _v2);
    }

    public Player changeCiconWithMinMax(int cicon, int _min, int _max){
        int _v2 = this.cicon + cicon;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCicon(_v2 < _min  ? _min : _v2);
    }

    public int getMaxbuidid(){
        return maxBuidId;
    }

    public Player setMaxbuidid(int maxBuidId){
        int _old = maxBuidId;
        this.maxBuidId = maxBuidId;
        changeIt("maxBuidId", _old, maxBuidId);
        return this;
    }

    public Player changeMaxbuidid(int maxBuidId){
        return setMaxbuidid(this.maxBuidId + maxBuidId);
    }

    public Player changeMaxbuididWithMin(int maxBuidId, int _min){
        int _v2 = this.maxBuidId + maxBuidId;
        return setMaxbuidid(_v2 < _min  ? _min : _v2);
    }

    public Player changeMaxbuididWithMax(int maxBuidId, int _max){
        int _v2 = this.maxBuidId + maxBuidId;
        return setMaxbuidid(_v2 > _max  ? _max : _v2);
    }

    public Player changeMaxbuididWithMinMax(int maxBuidId, int _min, int _max){
        int _v2 = this.maxBuidId + maxBuidId;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMaxbuidid(_v2 < _min  ? _min : _v2);
    }

    public int getMaxobstid(){
        return maxObstId;
    }

    public Player setMaxobstid(int maxObstId){
        int _old = maxObstId;
        this.maxObstId = maxObstId;
        changeIt("maxObstId", _old, maxObstId);
        return this;
    }

    public Player changeMaxobstid(int maxObstId){
        return setMaxobstid(this.maxObstId + maxObstId);
    }

    public Player changeMaxobstidWithMin(int maxObstId, int _min){
        int _v2 = this.maxObstId + maxObstId;
        return setMaxobstid(_v2 < _min  ? _min : _v2);
    }

    public Player changeMaxobstidWithMax(int maxObstId, int _max){
        int _v2 = this.maxObstId + maxObstId;
        return setMaxobstid(_v2 > _max  ? _max : _v2);
    }

    public Player changeMaxobstidWithMinMax(int maxObstId, int _min, int _max){
        int _v2 = this.maxObstId + maxObstId;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMaxobstid(_v2 < _min  ? _min : _v2);
    }

    public int getStored_oil(){
        return stored_oil;
    }

    public Player setStored_oil(int stored_oil){
        int _old = stored_oil;
        this.stored_oil = stored_oil;
        changeIt("stored_oil", _old, stored_oil);
        return this;
    }

    public Player changeStored_oil(int stored_oil){
        return setStored_oil(this.stored_oil + stored_oil);
    }

    public Player changeStored_oilWithMin(int stored_oil, int _min){
        int _v2 = this.stored_oil + stored_oil;
        return setStored_oil(_v2 < _min  ? _min : _v2);
    }

    public Player changeStored_oilWithMax(int stored_oil, int _max){
        int _v2 = this.stored_oil + stored_oil;
        return setStored_oil(_v2 > _max  ? _max : _v2);
    }

    public Player changeStored_oilWithMinMax(int stored_oil, int _min, int _max){
        int _v2 = this.stored_oil + stored_oil;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStored_oil(_v2 < _min  ? _min : _v2);
    }

    public int getStored_gold(){
        return stored_gold;
    }

    public Player setStored_gold(int stored_gold){
        int _old = stored_gold;
        this.stored_gold = stored_gold;
        changeIt("stored_gold", _old, stored_gold);
        return this;
    }

    public Player changeStored_gold(int stored_gold){
        return setStored_gold(this.stored_gold + stored_gold);
    }

    public Player changeStored_goldWithMin(int stored_gold, int _min){
        int _v2 = this.stored_gold + stored_gold;
        return setStored_gold(_v2 < _min  ? _min : _v2);
    }

    public Player changeStored_goldWithMax(int stored_gold, int _max){
        int _v2 = this.stored_gold + stored_gold;
        return setStored_gold(_v2 > _max  ? _max : _v2);
    }

    public Player changeStored_goldWithMinMax(int stored_gold, int _min, int _max){
        int _v2 = this.stored_gold + stored_gold;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStored_gold(_v2 < _min  ? _min : _v2);
    }

    public boolean getIsonline(){
        return isOnline;
    }

    public Player setIsonline(boolean isOnline){
        boolean _old = isOnline;
        this.isOnline = isOnline;
        changeIt("isOnline", _old, isOnline);
        return this;
    }

    public long getProtecttime(){
        return protectTime;
    }

    public Player setProtecttime(long protectTime){
        long _old = protectTime;
        this.protectTime = protectTime;
        changeIt("protectTime", _old, protectTime);
        return this;
    }

    public Player changeProtecttime(long protectTime){
        return setProtecttime(this.protectTime + protectTime);
    }

    public Player changeProtecttimeWithMin(long protectTime, long _min){
        long _v2 = this.protectTime + protectTime;
        return setProtecttime(_v2 < _min  ? _min : _v2);
    }

    public Player changeProtecttimeWithMax(long protectTime, long _max){
        long _v2 = this.protectTime + protectTime;
        return setProtecttime(_v2 > _max  ? _max : _v2);
    }

    public Player changeProtecttimeWithMinMax(long protectTime, long _min, long _max){
        long _v2 = this.protectTime + protectTime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setProtecttime(_v2 < _min  ? _min : _v2);
    }

    public int getMaxattmcid(){
        return maxAttMCId;
    }

    public Player setMaxattmcid(int maxAttMCId){
        int _old = maxAttMCId;
        this.maxAttMCId = maxAttMCId;
        changeIt("maxAttMCId", _old, maxAttMCId);
        return this;
    }

    public Player changeMaxattmcid(int maxAttMCId){
        return setMaxattmcid(this.maxAttMCId + maxAttMCId);
    }

    public Player changeMaxattmcidWithMin(int maxAttMCId, int _min){
        int _v2 = this.maxAttMCId + maxAttMCId;
        return setMaxattmcid(_v2 < _min  ? _min : _v2);
    }

    public Player changeMaxattmcidWithMax(int maxAttMCId, int _max){
        int _v2 = this.maxAttMCId + maxAttMCId;
        return setMaxattmcid(_v2 > _max  ? _max : _v2);
    }

    public Player changeMaxattmcidWithMinMax(int maxAttMCId, int _min, int _max){
        int _v2 = this.maxAttMCId + maxAttMCId;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMaxattmcid(_v2 < _min  ? _min : _v2);
    }

    public int getMaxbonum(){
        return maxBONum;
    }

    public Player setMaxbonum(int maxBONum){
        int _old = maxBONum;
        this.maxBONum = maxBONum;
        changeIt("maxBONum", _old, maxBONum);
        return this;
    }

    public Player changeMaxbonum(int maxBONum){
        return setMaxbonum(this.maxBONum + maxBONum);
    }

    public Player changeMaxbonumWithMin(int maxBONum, int _min){
        int _v2 = this.maxBONum + maxBONum;
        return setMaxbonum(_v2 < _min  ? _min : _v2);
    }

    public Player changeMaxbonumWithMax(int maxBONum, int _max){
        int _v2 = this.maxBONum + maxBONum;
        return setMaxbonum(_v2 > _max  ? _max : _v2);
    }

    public Player changeMaxbonumWithMinMax(int maxBONum, int _min, int _max){
        int _v2 = this.maxBONum + maxBONum;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMaxbonum(_v2 < _min  ? _min : _v2);
    }

    public int getCurbonum(){
        return curBONum;
    }

    public Player setCurbonum(int curBONum){
        int _old = curBONum;
        this.curBONum = curBONum;
        changeIt("curBONum", _old, curBONum);
        return this;
    }

    public Player changeCurbonum(int curBONum){
        return setCurbonum(this.curBONum + curBONum);
    }

    public Player changeCurbonumWithMin(int curBONum, int _min){
        int _v2 = this.curBONum + curBONum;
        return setCurbonum(_v2 < _min  ? _min : _v2);
    }

    public Player changeCurbonumWithMax(int curBONum, int _max){
        int _v2 = this.curBONum + curBONum;
        return setCurbonum(_v2 > _max  ? _max : _v2);
    }

    public Player changeCurbonumWithMinMax(int curBONum, int _min, int _max){
        int _v2 = this.curBONum + curBONum;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurbonum(_v2 < _min  ? _min : _v2);
    }

    public String getSpells(){
        return spells;
    }

    public Player setSpells(String spells){
        String _old = spells;
        this.spells = spells;
        changeIt("spells", _old, spells);
        return this;
    }

    public long getLastaddobst(){
        return lastAddObst;
    }

    public Player setLastaddobst(long lastAddObst){
        long _old = lastAddObst;
        this.lastAddObst = lastAddObst;
        changeIt("lastAddObst", _old, lastAddObst);
        return this;
    }

    public Player changeLastaddobst(long lastAddObst){
        return setLastaddobst(this.lastAddObst + lastAddObst);
    }

    public Player changeLastaddobstWithMin(long lastAddObst, long _min){
        long _v2 = this.lastAddObst + lastAddObst;
        return setLastaddobst(_v2 < _min  ? _min : _v2);
    }

    public Player changeLastaddobstWithMax(long lastAddObst, long _max){
        long _v2 = this.lastAddObst + lastAddObst;
        return setLastaddobst(_v2 > _max  ? _max : _v2);
    }

    public Player changeLastaddobstWithMinMax(long lastAddObst, long _min, long _max){
        long _v2 = this.lastAddObst + lastAddObst;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLastaddobst(_v2 < _min  ? _min : _v2);
    }

    public String getMark(){
        return mark;
    }

    public Player setMark(String mark){
        String _old = mark;
        this.mark = mark;
        changeIt("mark", _old, mark);
        return this;
    }

    public int getCurtownlvl(){
        return curtownlvl;
    }

    public Player setCurtownlvl(int curtownlvl){
        int _old = curtownlvl;
        this.curtownlvl = curtownlvl;
        changeIt("curtownlvl", _old, curtownlvl);
        return this;
    }

    public Player changeCurtownlvl(int curtownlvl){
        return setCurtownlvl(this.curtownlvl + curtownlvl);
    }

    public Player changeCurtownlvlWithMin(int curtownlvl, int _min){
        int _v2 = this.curtownlvl + curtownlvl;
        return setCurtownlvl(_v2 < _min  ? _min : _v2);
    }

    public Player changeCurtownlvlWithMax(int curtownlvl, int _max){
        int _v2 = this.curtownlvl + curtownlvl;
        return setCurtownlvl(_v2 > _max  ? _max : _v2);
    }

    public Player changeCurtownlvlWithMinMax(int curtownlvl, int _min, int _max){
        int _v2 = this.curtownlvl + curtownlvl;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCurtownlvl(_v2 < _min  ? _min : _v2);
    }

    public int getLoginday(){
        return loginDay;
    }

    public Player setLoginday(int loginDay){
        int _old = loginDay;
        this.loginDay = loginDay;
        changeIt("loginDay", _old, loginDay);
        return this;
    }

    public Player changeLoginday(int loginDay){
        return setLoginday(this.loginDay + loginDay);
    }

    public Player changeLogindayWithMin(int loginDay, int _min){
        int _v2 = this.loginDay + loginDay;
        return setLoginday(_v2 < _min  ? _min : _v2);
    }

    public Player changeLogindayWithMax(int loginDay, int _max){
        int _v2 = this.loginDay + loginDay;
        return setLoginday(_v2 > _max  ? _max : _v2);
    }

    public Player changeLogindayWithMinMax(int loginDay, int _min, int _max){
        int _v2 = this.loginDay + loginDay;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLoginday(_v2 < _min  ? _min : _v2);
    }

    public long getLastlogintime(){
        return lastLoginTime;
    }

    public Player setLastlogintime(long lastLoginTime){
        long _old = lastLoginTime;
        this.lastLoginTime = lastLoginTime;
        changeIt("lastLoginTime", _old, lastLoginTime);
        return this;
    }

    public Player changeLastlogintime(long lastLoginTime){
        return setLastlogintime(this.lastLoginTime + lastLoginTime);
    }

    public Player changeLastlogintimeWithMin(long lastLoginTime, long _min){
        long _v2 = this.lastLoginTime + lastLoginTime;
        return setLastlogintime(_v2 < _min  ? _min : _v2);
    }

    public Player changeLastlogintimeWithMax(long lastLoginTime, long _max){
        long _v2 = this.lastLoginTime + lastLoginTime;
        return setLastlogintime(_v2 > _max  ? _max : _v2);
    }

    public Player changeLastlogintimeWithMinMax(long lastLoginTime, long _min, long _max){
        long _v2 = this.lastLoginTime + lastLoginTime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLastlogintime(_v2 < _min  ? _min : _v2);
    }

    public boolean getIsrewardday(){
        return isRewardDay;
    }

    public Player setIsrewardday(boolean isRewardDay){
        boolean _old = isRewardDay;
        this.isRewardDay = isRewardDay;
        changeIt("isRewardDay", _old, isRewardDay);
        return this;
    }

    public int getFirstpaystatus(){
        return firstPayStatus;
    }

    public Player setFirstpaystatus(int firstPayStatus){
        int _old = firstPayStatus;
        this.firstPayStatus = firstPayStatus;
        changeIt("firstPayStatus", _old, firstPayStatus);
        return this;
    }

    public Player changeFirstpaystatus(int firstPayStatus){
        return setFirstpaystatus(this.firstPayStatus + firstPayStatus);
    }

    public Player changeFirstpaystatusWithMin(int firstPayStatus, int _min){
        int _v2 = this.firstPayStatus + firstPayStatus;
        return setFirstpaystatus(_v2 < _min  ? _min : _v2);
    }

    public Player changeFirstpaystatusWithMax(int firstPayStatus, int _max){
        int _v2 = this.firstPayStatus + firstPayStatus;
        return setFirstpaystatus(_v2 > _max  ? _max : _v2);
    }

    public Player changeFirstpaystatusWithMinMax(int firstPayStatus, int _min, int _max){
        int _v2 = this.firstPayStatus + firstPayStatus;
        _v2 = _v2 > _max  ? _max : _v2;
        return setFirstpaystatus(_v2 < _min  ? _min : _v2);
    }

    public int getPiecehpnum(){
        return pieceHPNum;
    }

    public Player setPiecehpnum(int pieceHPNum){
        int _old = pieceHPNum;
        this.pieceHPNum = pieceHPNum;
        changeIt("pieceHPNum", _old, pieceHPNum);
        return this;
    }

    public Player changePiecehpnum(int pieceHPNum){
        return setPiecehpnum(this.pieceHPNum + pieceHPNum);
    }

    public Player changePiecehpnumWithMin(int pieceHPNum, int _min){
        int _v2 = this.pieceHPNum + pieceHPNum;
        return setPiecehpnum(_v2 < _min  ? _min : _v2);
    }

    public Player changePiecehpnumWithMax(int pieceHPNum, int _max){
        int _v2 = this.pieceHPNum + pieceHPNum;
        return setPiecehpnum(_v2 > _max  ? _max : _v2);
    }

    public Player changePiecehpnumWithMinMax(int pieceHPNum, int _min, int _max){
        int _v2 = this.pieceHPNum + pieceHPNum;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPiecehpnum(_v2 < _min  ? _min : _v2);
    }

    public int getPiecedamnum(){
        return pieceDamNum;
    }

    public Player setPiecedamnum(int pieceDamNum){
        int _old = pieceDamNum;
        this.pieceDamNum = pieceDamNum;
        changeIt("pieceDamNum", _old, pieceDamNum);
        return this;
    }

    public Player changePiecedamnum(int pieceDamNum){
        return setPiecedamnum(this.pieceDamNum + pieceDamNum);
    }

    public Player changePiecedamnumWithMin(int pieceDamNum, int _min){
        int _v2 = this.pieceDamNum + pieceDamNum;
        return setPiecedamnum(_v2 < _min  ? _min : _v2);
    }

    public Player changePiecedamnumWithMax(int pieceDamNum, int _max){
        int _v2 = this.pieceDamNum + pieceDamNum;
        return setPiecedamnum(_v2 > _max  ? _max : _v2);
    }

    public Player changePiecedamnumWithMinMax(int pieceDamNum, int _min, int _max){
        int _v2 = this.pieceDamNum + pieceDamNum;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPiecedamnum(_v2 < _min  ? _min : _v2);
    }

    public int getPieceattspeed(){
        return pieceAttSpeed;
    }

    public Player setPieceattspeed(int pieceAttSpeed){
        int _old = pieceAttSpeed;
        this.pieceAttSpeed = pieceAttSpeed;
        changeIt("pieceAttSpeed", _old, pieceAttSpeed);
        return this;
    }

    public Player changePieceattspeed(int pieceAttSpeed){
        return setPieceattspeed(this.pieceAttSpeed + pieceAttSpeed);
    }

    public Player changePieceattspeedWithMin(int pieceAttSpeed, int _min){
        int _v2 = this.pieceAttSpeed + pieceAttSpeed;
        return setPieceattspeed(_v2 < _min  ? _min : _v2);
    }

    public Player changePieceattspeedWithMax(int pieceAttSpeed, int _max){
        int _v2 = this.pieceAttSpeed + pieceAttSpeed;
        return setPieceattspeed(_v2 > _max  ? _max : _v2);
    }

    public Player changePieceattspeedWithMinMax(int pieceAttSpeed, int _min, int _max){
        int _v2 = this.pieceAttSpeed + pieceAttSpeed;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPieceattspeed(_v2 < _min  ? _min : _v2);
    }

    public String getDaytasks(){
        return dayTasks;
    }

    public Player setDaytasks(String dayTasks){
        String _old = dayTasks;
        this.dayTasks = dayTasks;
        changeIt("dayTasks", _old, dayTasks);
        return this;
    }

    public long getLastleaveclan(){
        return lastLeaveClan;
    }

    public Player setLastleaveclan(long lastLeaveClan){
        long _old = lastLeaveClan;
        this.lastLeaveClan = lastLeaveClan;
        changeIt("lastLeaveClan", _old, lastLeaveClan);
        return this;
    }

    public Player changeLastleaveclan(long lastLeaveClan){
        return setLastleaveclan(this.lastLeaveClan + lastLeaveClan);
    }

    public Player changeLastleaveclanWithMin(long lastLeaveClan, long _min){
        long _v2 = this.lastLeaveClan + lastLeaveClan;
        return setLastleaveclan(_v2 < _min  ? _min : _v2);
    }

    public Player changeLastleaveclanWithMax(long lastLeaveClan, long _max){
        long _v2 = this.lastLeaveClan + lastLeaveClan;
        return setLastleaveclan(_v2 > _max  ? _max : _v2);
    }

    public Player changeLastleaveclanWithMinMax(long lastLeaveClan, long _min, long _max){
        long _v2 = this.lastLeaveClan + lastLeaveClan;
        _v2 = _v2 > _max  ? _max : _v2;
        return setLastleaveclan(_v2 < _min  ? _min : _v2);
    }

    public long getMonthcode(){
        return monthCode;
    }

    public Player setMonthcode(long monthCode){
        long _old = monthCode;
        this.monthCode = monthCode;
        changeIt("monthCode", _old, monthCode);
        return this;
    }

    public Player changeMonthcode(long monthCode){
        return setMonthcode(this.monthCode + monthCode);
    }

    public Player changeMonthcodeWithMin(long monthCode, long _min){
        long _v2 = this.monthCode + monthCode;
        return setMonthcode(_v2 < _min  ? _min : _v2);
    }

    public Player changeMonthcodeWithMax(long monthCode, long _max){
        long _v2 = this.monthCode + monthCode;
        return setMonthcode(_v2 > _max  ? _max : _v2);
    }

    public Player changeMonthcodeWithMinMax(long monthCode, long _min, long _max){
        long _v2 = this.monthCode + monthCode;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMonthcode(_v2 < _min  ? _min : _v2);
    }

    public boolean getIsmoncode(){
        return isMonCode;
    }

    public Player setIsmoncode(boolean isMonCode){
        boolean _old = isMonCode;
        this.isMonCode = isMonCode;
        changeIt("isMonCode", _old, isMonCode);
        return this;
    }

    public String getBuilds(){
        return builds;
    }

    public Player setBuilds(String builds){
        String _old = builds;
        this.builds = builds;
        changeIt("builds", _old, builds);
        return this;
    }

    public String getObstes(){
        return obstes;
    }

    public Player setObstes(String obstes){
        String _old = obstes;
        this.obstes = obstes;
        changeIt("obstes", _old, obstes);
        return this;
    }

    public String getTeches(){
        return teches;
    }

    public Player setTeches(String teches){
        String _old = teches;
        this.teches = teches;
        changeIt("teches", _old, teches);
        return this;
    }

    public String getHeroes(){
        return heroes;
    }

    public Player setHeroes(String heroes){
        String _old = heroes;
        this.heroes = heroes;
        changeIt("heroes", _old, heroes);
        return this;
    }

    public int getShare(){
        return share;
    }

    public Player setShare(int share){
        int _old = share;
        this.share = share;
        changeIt("share", _old, share);
        return this;
    }

    public Player changeShare(int share){
        return setShare(this.share + share);
    }

    public Player changeShareWithMin(int share, int _min){
        int _v2 = this.share + share;
        return setShare(_v2 < _min  ? _min : _v2);
    }

    public Player changeShareWithMax(int share, int _max){
        int _v2 = this.share + share;
        return setShare(_v2 > _max  ? _max : _v2);
    }

    public Player changeShareWithMinMax(int share, int _min, int _max){
        int _v2 = this.share + share;
        _v2 = _v2 > _max  ? _max : _v2;
        return setShare(_v2 < _min  ? _min : _v2);
    }

    public double getMoneyactivity(){
        return moneyActivity;
    }

    public Player setMoneyactivity(double moneyActivity){
        double _old = moneyActivity;
        this.moneyActivity = moneyActivity;
        changeIt("moneyActivity", _old, moneyActivity);
        return this;
    }

    public Player changeMoneyactivity(double moneyActivity){
        return setMoneyactivity(this.moneyActivity + moneyActivity);
    }

    public Player changeMoneyactivityWithMin(double moneyActivity, double _min){
        double _v2 = this.moneyActivity + moneyActivity;
        return setMoneyactivity(_v2 < _min  ? _min : _v2);
    }

    public Player changeMoneyactivityWithMax(double moneyActivity, double _max){
        double _v2 = this.moneyActivity + moneyActivity;
        return setMoneyactivity(_v2 > _max  ? _max : _v2);
    }

    public Player changeMoneyactivityWithMinMax(double moneyActivity, double _min, double _max){
        double _v2 = this.moneyActivity + moneyActivity;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMoneyactivity(_v2 < _min  ? _min : _v2);
    }

    public int getMoneyactivitytype(){
        return moneyActivityType;
    }

    public Player setMoneyactivitytype(int moneyActivityType){
        int _old = moneyActivityType;
        this.moneyActivityType = moneyActivityType;
        changeIt("moneyActivityType", _old, moneyActivityType);
        return this;
    }

    public Player changeMoneyactivitytype(int moneyActivityType){
        return setMoneyactivitytype(this.moneyActivityType + moneyActivityType);
    }

    public Player changeMoneyactivitytypeWithMin(int moneyActivityType, int _min){
        int _v2 = this.moneyActivityType + moneyActivityType;
        return setMoneyactivitytype(_v2 < _min  ? _min : _v2);
    }

    public Player changeMoneyactivitytypeWithMax(int moneyActivityType, int _max){
        int _v2 = this.moneyActivityType + moneyActivityType;
        return setMoneyactivitytype(_v2 > _max  ? _max : _v2);
    }

    public Player changeMoneyactivitytypeWithMinMax(int moneyActivityType, int _min, int _max){
        int _v2 = this.moneyActivityType + moneyActivityType;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMoneyactivitytype(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Player v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Player v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Player newPlayer(Integer id, Integer pcid, Integer ucid, Integer svcid, String pname, Integer type, Integer state, String channel, Integer icon, Integer exp, Integer crystal, Integer renown, Integer weekRenown, Integer cur_npc, String npcs, Integer all_money, Integer last_money, Long last_pay_time, Integer guid_step, String clancid, Integer clanPost, String cname, Integer cicon, Integer maxBuidId, Integer maxObstId, Integer stored_oil, Integer stored_gold, Boolean isOnline, Long protectTime, Integer maxAttMCId, Integer maxBONum, Integer curBONum, String spells, Long lastAddObst, String mark, Integer curtownlvl, Integer loginDay, Long lastLoginTime, Boolean isRewardDay, Integer firstPayStatus, Integer pieceHPNum, Integer pieceDamNum, Integer pieceAttSpeed, String dayTasks, Long lastLeaveClan, Long monthCode, Boolean isMonCode, String builds, String obstes, String teches, String heroes, Integer share, Double moneyActivity, Integer moneyActivityType) {
        Player result = new Player();
        result.id = id;
        result.pcid = pcid;
        result.ucid = ucid;
        result.svcid = svcid;
        result.pname = pname;
        result.type = type;
        result.state = state;
        result.channel = channel;
        result.icon = icon;
        result.exp = exp;
        result.crystal = crystal;
        result.renown = renown;
        result.weekRenown = weekRenown;
        result.cur_npc = cur_npc;
        result.npcs = npcs;
        result.all_money = all_money;
        result.last_money = last_money;
        result.last_pay_time = last_pay_time;
        result.guid_step = guid_step;
        result.clancid = clancid;
        result.clanPost = clanPost;
        result.cname = cname;
        result.cicon = cicon;
        result.maxBuidId = maxBuidId;
        result.maxObstId = maxObstId;
        result.stored_oil = stored_oil;
        result.stored_gold = stored_gold;
        result.isOnline = isOnline;
        result.protectTime = protectTime;
        result.maxAttMCId = maxAttMCId;
        result.maxBONum = maxBONum;
        result.curBONum = curBONum;
        result.spells = spells;
        result.lastAddObst = lastAddObst;
        result.mark = mark;
        result.curtownlvl = curtownlvl;
        result.loginDay = loginDay;
        result.lastLoginTime = lastLoginTime;
        result.isRewardDay = isRewardDay;
        result.firstPayStatus = firstPayStatus;
        result.pieceHPNum = pieceHPNum;
        result.pieceDamNum = pieceDamNum;
        result.pieceAttSpeed = pieceAttSpeed;
        result.dayTasks = dayTasks;
        result.lastLeaveClan = lastLeaveClan;
        result.monthCode = monthCode;
        result.isMonCode = isMonCode;
        result.builds = builds;
        result.obstes = obstes;
        result.teches = teches;
        result.heroes = heroes;
        result.share = share;
        result.moneyActivity = moneyActivity;
        result.moneyActivityType = moneyActivityType;
        return result;
    }

    public static Player newPlayer(Player player) {
        Player result = new Player();
        result.id = player.id;
        result.pcid = player.pcid;
        result.ucid = player.ucid;
        result.svcid = player.svcid;
        result.pname = player.pname;
        result.type = player.type;
        result.state = player.state;
        result.channel = player.channel;
        result.icon = player.icon;
        result.exp = player.exp;
        result.crystal = player.crystal;
        result.renown = player.renown;
        result.weekRenown = player.weekRenown;
        result.cur_npc = player.cur_npc;
        result.npcs = player.npcs;
        result.all_money = player.all_money;
        result.last_money = player.last_money;
        result.last_pay_time = player.last_pay_time;
        result.guid_step = player.guid_step;
        result.clancid = player.clancid;
        result.clanPost = player.clanPost;
        result.cname = player.cname;
        result.cicon = player.cicon;
        result.maxBuidId = player.maxBuidId;
        result.maxObstId = player.maxObstId;
        result.stored_oil = player.stored_oil;
        result.stored_gold = player.stored_gold;
        result.isOnline = player.isOnline;
        result.protectTime = player.protectTime;
        result.maxAttMCId = player.maxAttMCId;
        result.maxBONum = player.maxBONum;
        result.curBONum = player.curBONum;
        result.spells = player.spells;
        result.lastAddObst = player.lastAddObst;
        result.mark = player.mark;
        result.curtownlvl = player.curtownlvl;
        result.loginDay = player.loginDay;
        result.lastLoginTime = player.lastLoginTime;
        result.isRewardDay = player.isRewardDay;
        result.firstPayStatus = player.firstPayStatus;
        result.pieceHPNum = player.pieceHPNum;
        result.pieceDamNum = player.pieceDamNum;
        result.pieceAttSpeed = player.pieceAttSpeed;
        result.dayTasks = player.dayTasks;
        result.lastLeaveClan = player.lastLeaveClan;
        result.monthCode = player.monthCode;
        result.isMonCode = player.isMonCode;
        result.builds = player.builds;
        result.obstes = player.obstes;
        result.teches = player.teches;
        result.heroes = player.heroes;
        result.share = player.share;
        result.moneyActivity = player.moneyActivity;
        result.moneyActivityType = player.moneyActivityType;
        return result;
    }

    public Player createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, pcid);
        B2OutputStream.writeObject(_out, ucid);
        B2OutputStream.writeObject(_out, svcid);
        B2OutputStream.writeObject(_out, pname);
        B2OutputStream.writeObject(_out, type);
        B2OutputStream.writeObject(_out, state);
        B2OutputStream.writeObject(_out, channel);
        B2OutputStream.writeObject(_out, icon);
        B2OutputStream.writeObject(_out, exp);
        B2OutputStream.writeObject(_out, crystal);
        B2OutputStream.writeObject(_out, renown);
        B2OutputStream.writeObject(_out, weekRenown);
        B2OutputStream.writeObject(_out, cur_npc);
        B2OutputStream.writeObject(_out, npcs);
        B2OutputStream.writeObject(_out, all_money);
        B2OutputStream.writeObject(_out, last_money);
        B2OutputStream.writeObject(_out, last_pay_time);
        B2OutputStream.writeObject(_out, guid_step);
        B2OutputStream.writeObject(_out, clancid);
        B2OutputStream.writeObject(_out, clanPost);
        B2OutputStream.writeObject(_out, cname);
        B2OutputStream.writeObject(_out, cicon);
        B2OutputStream.writeObject(_out, maxBuidId);
        B2OutputStream.writeObject(_out, maxObstId);
        B2OutputStream.writeObject(_out, stored_oil);
        B2OutputStream.writeObject(_out, stored_gold);
        B2OutputStream.writeObject(_out, isOnline);
        B2OutputStream.writeObject(_out, protectTime);
        B2OutputStream.writeObject(_out, maxAttMCId);
        B2OutputStream.writeObject(_out, maxBONum);
        B2OutputStream.writeObject(_out, curBONum);
        B2OutputStream.writeObject(_out, spells);
        B2OutputStream.writeObject(_out, lastAddObst);
        B2OutputStream.writeObject(_out, mark);
        B2OutputStream.writeObject(_out, curtownlvl);
        B2OutputStream.writeObject(_out, loginDay);
        B2OutputStream.writeObject(_out, lastLoginTime);
        B2OutputStream.writeObject(_out, isRewardDay);
        B2OutputStream.writeObject(_out, firstPayStatus);
        B2OutputStream.writeObject(_out, pieceHPNum);
        B2OutputStream.writeObject(_out, pieceDamNum);
        B2OutputStream.writeObject(_out, pieceAttSpeed);
        B2OutputStream.writeObject(_out, dayTasks);
        B2OutputStream.writeObject(_out, lastLeaveClan);
        B2OutputStream.writeObject(_out, monthCode);
        B2OutputStream.writeObject(_out, isMonCode);
        B2OutputStream.writeObject(_out, builds);
        B2OutputStream.writeObject(_out, obstes);
        B2OutputStream.writeObject(_out, teches);
        B2OutputStream.writeObject(_out, heroes);
        B2OutputStream.writeObject(_out, share);
        B2OutputStream.writeObject(_out, moneyActivity);
        B2OutputStream.writeObject(_out, moneyActivityType);
    }

    public static final Player readObject(InputStream _in) throws Exception {
        Player result = new Player();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.pcid = (Integer) B2InputStream.readObject(_in);
        result.ucid = (Integer) B2InputStream.readObject(_in);
        result.svcid = (Integer) B2InputStream.readObject(_in);
        result.pname = (String) B2InputStream.readObject(_in);
        result.type = (Integer) B2InputStream.readObject(_in);
        result.state = (Integer) B2InputStream.readObject(_in);
        result.channel = (String) B2InputStream.readObject(_in);
        result.icon = (Integer) B2InputStream.readObject(_in);
        result.exp = (Integer) B2InputStream.readObject(_in);
        result.crystal = (Integer) B2InputStream.readObject(_in);
        result.renown = (Integer) B2InputStream.readObject(_in);
        result.weekRenown = (Integer) B2InputStream.readObject(_in);
        result.cur_npc = (Integer) B2InputStream.readObject(_in);
        result.npcs = (String) B2InputStream.readObject(_in);
        result.all_money = (Integer) B2InputStream.readObject(_in);
        result.last_money = (Integer) B2InputStream.readObject(_in);
        result.last_pay_time = (Long) B2InputStream.readObject(_in);
        result.guid_step = (Integer) B2InputStream.readObject(_in);
        result.clancid = (String) B2InputStream.readObject(_in);
        result.clanPost = (Integer) B2InputStream.readObject(_in);
        result.cname = (String) B2InputStream.readObject(_in);
        result.cicon = (Integer) B2InputStream.readObject(_in);
        result.maxBuidId = (Integer) B2InputStream.readObject(_in);
        result.maxObstId = (Integer) B2InputStream.readObject(_in);
        result.stored_oil = (Integer) B2InputStream.readObject(_in);
        result.stored_gold = (Integer) B2InputStream.readObject(_in);
        result.isOnline = (Boolean) B2InputStream.readObject(_in);
        result.protectTime = (Long) B2InputStream.readObject(_in);
        result.maxAttMCId = (Integer) B2InputStream.readObject(_in);
        result.maxBONum = (Integer) B2InputStream.readObject(_in);
        result.curBONum = (Integer) B2InputStream.readObject(_in);
        result.spells = (String) B2InputStream.readObject(_in);
        result.lastAddObst = (Long) B2InputStream.readObject(_in);
        result.mark = (String) B2InputStream.readObject(_in);
        result.curtownlvl = (Integer) B2InputStream.readObject(_in);
        result.loginDay = (Integer) B2InputStream.readObject(_in);
        result.lastLoginTime = (Long) B2InputStream.readObject(_in);
        result.isRewardDay = (Boolean) B2InputStream.readObject(_in);
        result.firstPayStatus = (Integer) B2InputStream.readObject(_in);
        result.pieceHPNum = (Integer) B2InputStream.readObject(_in);
        result.pieceDamNum = (Integer) B2InputStream.readObject(_in);
        result.pieceAttSpeed = (Integer) B2InputStream.readObject(_in);
        result.dayTasks = (String) B2InputStream.readObject(_in);
        result.lastLeaveClan = (Long) B2InputStream.readObject(_in);
        result.monthCode = (Long) B2InputStream.readObject(_in);
        result.isMonCode = (Boolean) B2InputStream.readObject(_in);
        result.builds = (String) B2InputStream.readObject(_in);
        result.obstes = (String) B2InputStream.readObject(_in);
        result.teches = (String) B2InputStream.readObject(_in);
        result.heroes = (String) B2InputStream.readObject(_in);
        result.share = (Integer) B2InputStream.readObject(_in);
        result.moneyActivity = (Double) B2InputStream.readObject(_in);
        result.moneyActivityType = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getPlayer(){
        Player player = null; // player
        { // new and insert Player (player)
            int id = 0; 	// id
            int pcid = 0; 	// pcid
            int ucid = 0; 	// ucid
            int svcid = 0; 	// svcid
            String pname = ""; 	// pname
            int type = 0; 	// type
            int state = 0; 	// state
            String channel = ""; 	// channel
            int icon = 0; 	// icon
            int exp = 0; 	// exp
            int crystal = 0; 	// crystal
            int renown = 0; 	// renown
            int weekRenown = 0; 	// weekRenown
            int cur_npc = 0; 	// cur_npc
            String npcs = ""; 	// npcs
            int all_money = 0; 	// all_money
            int last_money = 0; 	// last_money
            long last_pay_time = 0; 	// last_pay_time
            int guid_step = 0; 	// guid_step
            String clancid = ""; 	// clancid
            int clanPost = 0; 	// clanPost
            String cname = ""; 	// cname
            int cicon = 0; 	// cicon
            int maxBuidId = 0; 	// maxBuidId
            int maxObstId = 0; 	// maxObstId
            int stored_oil = 0; 	// stored_oil
            int stored_gold = 0; 	// stored_gold
            boolean isOnline = true; 	// isOnline
            long protectTime = 0; 	// protectTime
            int maxAttMCId = 0; 	// maxAttMCId
            int maxBONum = 0; 	// maxBONum
            int curBONum = 0; 	// curBONum
            String spells = ""; 	// spells
            long lastAddObst = 0; 	// lastAddObst
            String mark = ""; 	// mark
            int curtownlvl = 0; 	// curtownlvl
            int loginDay = 0; 	// loginDay
            long lastLoginTime = 0; 	// lastLoginTime
            boolean isRewardDay = true; 	// isRewardDay
            int firstPayStatus = 0; 	// firstPayStatus
            int pieceHPNum = 0; 	// pieceHPNum
            int pieceDamNum = 0; 	// pieceDamNum
            int pieceAttSpeed = 0; 	// pieceAttSpeed
            String dayTasks = ""; 	// dayTasks
            long lastLeaveClan = 0; 	// lastLeaveClan
            long monthCode = 0; 	// monthCode
            boolean isMonCode = true; 	// isMonCode
            String builds = ""; 	// builds
            String obstes = ""; 	// obstes
            String teches = ""; 	// teches
            String heroes = ""; 	// heroes
            int share = 0; 	// share
            double moneyActivity = 0.0; 	// moneyActivity
            int moneyActivityType = 0; 	// moneyActivityType
            player = Player.newPlayer(id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType);
        }
        player = player.insert();

        int id = player.getId(); 	// id
        int pcid = player.getPcid(); 	// pcid
        int ucid = player.getUcid(); 	// ucid
        int svcid = player.getSvcid(); 	// svcid
        String pname = player.getPname(); 	// pname
        int type = player.getType(); 	// type
        int state = player.getState(); 	// state
        String channel = player.getChannel(); 	// channel
        int icon = player.getIcon(); 	// icon
        int exp = player.getExp(); 	// exp
        int crystal = player.getCrystal(); 	// crystal
        int renown = player.getRenown(); 	// renown
        int weekRenown = player.getWeekrenown(); 	// weekRenown
        int cur_npc = player.getCur_npc(); 	// cur_npc
        String npcs = player.getNpcs(); 	// npcs
        int all_money = player.getAll_money(); 	// all_money
        int last_money = player.getLast_money(); 	// last_money
        long last_pay_time = player.getLast_pay_time(); 	// last_pay_time
        int guid_step = player.getGuid_step(); 	// guid_step
        String clancid = player.getClancid(); 	// clancid
        int clanPost = player.getClanpost(); 	// clanPost
        String cname = player.getCname(); 	// cname
        int cicon = player.getCicon(); 	// cicon
        int maxBuidId = player.getMaxbuidid(); 	// maxBuidId
        int maxObstId = player.getMaxobstid(); 	// maxObstId
        int stored_oil = player.getStored_oil(); 	// stored_oil
        int stored_gold = player.getStored_gold(); 	// stored_gold
        boolean isOnline = player.getIsonline(); 	// isOnline
        long protectTime = player.getProtecttime(); 	// protectTime
        int maxAttMCId = player.getMaxattmcid(); 	// maxAttMCId
        int maxBONum = player.getMaxbonum(); 	// maxBONum
        int curBONum = player.getCurbonum(); 	// curBONum
        String spells = player.getSpells(); 	// spells
        long lastAddObst = player.getLastaddobst(); 	// lastAddObst
        String mark = player.getMark(); 	// mark
        int curtownlvl = player.getCurtownlvl(); 	// curtownlvl
        int loginDay = player.getLoginday(); 	// loginDay
        long lastLoginTime = player.getLastlogintime(); 	// lastLoginTime
        boolean isRewardDay = player.getIsrewardday(); 	// isRewardDay
        int firstPayStatus = player.getFirstpaystatus(); 	// firstPayStatus
        int pieceHPNum = player.getPiecehpnum(); 	// pieceHPNum
        int pieceDamNum = player.getPiecedamnum(); 	// pieceDamNum
        int pieceAttSpeed = player.getPieceattspeed(); 	// pieceAttSpeed
        String dayTasks = player.getDaytasks(); 	// dayTasks
        long lastLeaveClan = player.getLastleaveclan(); 	// lastLeaveClan
        long monthCode = player.getMonthcode(); 	// monthCode
        boolean isMonCode = player.getIsmoncode(); 	// isMonCode
        String builds = player.getBuilds(); 	// builds
        String obstes = player.getObstes(); 	// obstes
        String teches = player.getTeches(); 	// teches
        String heroes = player.getHeroes(); 	// heroes
        int share = player.getShare(); 	// share
        double moneyActivity = player.getMoneyactivity(); 	// moneyActivity
        int moneyActivityType = player.getMoneyactivitytype(); 	// moneyActivityType
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
        case "ucid":
            return ucid;
        case "svcid":
            return svcid;
        case "type":
            return type;
        case "state":
            return state;
        case "icon":
            return icon;
        case "exp":
            return exp;
        case "crystal":
            return crystal;
        case "renown":
            return renown;
        case "weekRenown":
            return weekRenown;
        case "cur_npc":
            return cur_npc;
        case "all_money":
            return all_money;
        case "last_money":
            return last_money;
        case "guid_step":
            return guid_step;
        case "clanPost":
            return clanPost;
        case "cicon":
            return cicon;
        case "maxBuidId":
            return maxBuidId;
        case "maxObstId":
            return maxObstId;
        case "stored_oil":
            return stored_oil;
        case "stored_gold":
            return stored_gold;
        case "maxAttMCId":
            return maxAttMCId;
        case "maxBONum":
            return maxBONum;
        case "curBONum":
            return curBONum;
        case "curtownlvl":
            return curtownlvl;
        case "loginDay":
            return loginDay;
        case "firstPayStatus":
            return firstPayStatus;
        case "pieceHPNum":
            return pieceHPNum;
        case "pieceDamNum":
            return pieceDamNum;
        case "pieceAttSpeed":
            return pieceAttSpeed;
        case "share":
            return share;
        case "moneyActivityType":
            return moneyActivityType;
        }
        return 0;
    }

    public Player setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Player setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "pcid":
            return setPcid(value2);
        case "ucid":
            return setUcid(value2);
        case "svcid":
            return setSvcid(value2);
        case "type":
            return setType(value2);
        case "state":
            return setState(value2);
        case "icon":
            return setIcon(value2);
        case "exp":
            return setExp(value2);
        case "crystal":
            return setCrystal(value2);
        case "renown":
            return setRenown(value2);
        case "weekRenown":
            return setWeekrenown(value2);
        case "cur_npc":
            return setCur_npc(value2);
        case "all_money":
            return setAll_money(value2);
        case "last_money":
            return setLast_money(value2);
        case "guid_step":
            return setGuid_step(value2);
        case "clanPost":
            return setClanpost(value2);
        case "cicon":
            return setCicon(value2);
        case "maxBuidId":
            return setMaxbuidid(value2);
        case "maxObstId":
            return setMaxobstid(value2);
        case "stored_oil":
            return setStored_oil(value2);
        case "stored_gold":
            return setStored_gold(value2);
        case "maxAttMCId":
            return setMaxattmcid(value2);
        case "maxBONum":
            return setMaxbonum(value2);
        case "curBONum":
            return setCurbonum(value2);
        case "curtownlvl":
            return setCurtownlvl(value2);
        case "loginDay":
            return setLoginday(value2);
        case "firstPayStatus":
            return setFirstpaystatus(value2);
        case "pieceHPNum":
            return setPiecehpnum(value2);
        case "pieceDamNum":
            return setPiecedamnum(value2);
        case "pieceAttSpeed":
            return setPieceattspeed(value2);
        case "share":
            return setShare(value2);
        case "moneyActivityType":
            return setMoneyactivitytype(value2);
        }
        return this;
    }

    public Player changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Player changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "pcid":
            return changePcid(value2);
        case "ucid":
            return changeUcid(value2);
        case "svcid":
            return changeSvcid(value2);
        case "type":
            return changeType(value2);
        case "state":
            return changeState(value2);
        case "icon":
            return changeIcon(value2);
        case "exp":
            return changeExp(value2);
        case "crystal":
            return changeCrystal(value2);
        case "renown":
            return changeRenown(value2);
        case "weekRenown":
            return changeWeekrenown(value2);
        case "cur_npc":
            return changeCur_npc(value2);
        case "all_money":
            return changeAll_money(value2);
        case "last_money":
            return changeLast_money(value2);
        case "guid_step":
            return changeGuid_step(value2);
        case "clanPost":
            return changeClanpost(value2);
        case "cicon":
            return changeCicon(value2);
        case "maxBuidId":
            return changeMaxbuidid(value2);
        case "maxObstId":
            return changeMaxobstid(value2);
        case "stored_oil":
            return changeStored_oil(value2);
        case "stored_gold":
            return changeStored_gold(value2);
        case "maxAttMCId":
            return changeMaxattmcid(value2);
        case "maxBONum":
            return changeMaxbonum(value2);
        case "curBONum":
            return changeCurbonum(value2);
        case "curtownlvl":
            return changeCurtownlvl(value2);
        case "loginDay":
            return changeLoginday(value2);
        case "firstPayStatus":
            return changeFirstpaystatus(value2);
        case "pieceHPNum":
            return changePiecehpnum(value2);
        case "pieceDamNum":
            return changePiecedamnum(value2);
        case "pieceAttSpeed":
            return changePieceattspeed(value2);
        case "share":
            return changeShare(value2);
        case "moneyActivityType":
            return changeMoneyactivitytype(value2);
        }
        return this;
    }

    public Player changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Player changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "pcid":
            return changePcidWithMin(value2, _min);
        case "ucid":
            return changeUcidWithMin(value2, _min);
        case "svcid":
            return changeSvcidWithMin(value2, _min);
        case "type":
            return changeTypeWithMin(value2, _min);
        case "state":
            return changeStateWithMin(value2, _min);
        case "icon":
            return changeIconWithMin(value2, _min);
        case "exp":
            return changeExpWithMin(value2, _min);
        case "crystal":
            return changeCrystalWithMin(value2, _min);
        case "renown":
            return changeRenownWithMin(value2, _min);
        case "weekRenown":
            return changeWeekrenownWithMin(value2, _min);
        case "cur_npc":
            return changeCur_npcWithMin(value2, _min);
        case "all_money":
            return changeAll_moneyWithMin(value2, _min);
        case "last_money":
            return changeLast_moneyWithMin(value2, _min);
        case "guid_step":
            return changeGuid_stepWithMin(value2, _min);
        case "clanPost":
            return changeClanpostWithMin(value2, _min);
        case "cicon":
            return changeCiconWithMin(value2, _min);
        case "maxBuidId":
            return changeMaxbuididWithMin(value2, _min);
        case "maxObstId":
            return changeMaxobstidWithMin(value2, _min);
        case "stored_oil":
            return changeStored_oilWithMin(value2, _min);
        case "stored_gold":
            return changeStored_goldWithMin(value2, _min);
        case "maxAttMCId":
            return changeMaxattmcidWithMin(value2, _min);
        case "maxBONum":
            return changeMaxbonumWithMin(value2, _min);
        case "curBONum":
            return changeCurbonumWithMin(value2, _min);
        case "curtownlvl":
            return changeCurtownlvlWithMin(value2, _min);
        case "loginDay":
            return changeLogindayWithMin(value2, _min);
        case "firstPayStatus":
            return changeFirstpaystatusWithMin(value2, _min);
        case "pieceHPNum":
            return changePiecehpnumWithMin(value2, _min);
        case "pieceDamNum":
            return changePiecedamnumWithMin(value2, _min);
        case "pieceAttSpeed":
            return changePieceattspeedWithMin(value2, _min);
        case "share":
            return changeShareWithMin(value2, _min);
        case "moneyActivityType":
            return changeMoneyactivitytypeWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        case "moneyActivity":
            return moneyActivity;
        }
        return 0;
    }

    public Player setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Player setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        case "moneyActivity":
            return setMoneyactivity(value2);
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
        case "channel": 
            return channel;
        case "npcs": 
            return npcs;
        case "clancid": 
            return clancid;
        case "cname": 
            return cname;
        case "spells": 
            return spells;
        case "mark": 
            return mark;
        case "dayTasks": 
            return dayTasks;
        case "builds": 
            return builds;
        case "obstes": 
            return obstes;
        case "teches": 
            return teches;
        case "heroes": 
            return heroes;
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
        case "ucid":
            return ucid;
        case "svcid":
            return svcid;
        case "pname":
            return pname;
        case "type":
            return type;
        case "state":
            return state;
        case "channel":
            return channel;
        case "icon":
            return icon;
        case "exp":
            return exp;
        case "crystal":
            return crystal;
        case "renown":
            return renown;
        case "weekRenown":
            return weekRenown;
        case "cur_npc":
            return cur_npc;
        case "npcs":
            return npcs;
        case "all_money":
            return all_money;
        case "last_money":
            return last_money;
        case "last_pay_time":
            return last_pay_time;
        case "guid_step":
            return guid_step;
        case "clancid":
            return clancid;
        case "clanPost":
            return clanPost;
        case "cname":
            return cname;
        case "cicon":
            return cicon;
        case "maxBuidId":
            return maxBuidId;
        case "maxObstId":
            return maxObstId;
        case "stored_oil":
            return stored_oil;
        case "stored_gold":
            return stored_gold;
        case "isOnline":
            return isOnline;
        case "protectTime":
            return protectTime;
        case "maxAttMCId":
            return maxAttMCId;
        case "maxBONum":
            return maxBONum;
        case "curBONum":
            return curBONum;
        case "spells":
            return spells;
        case "lastAddObst":
            return lastAddObst;
        case "mark":
            return mark;
        case "curtownlvl":
            return curtownlvl;
        case "loginDay":
            return loginDay;
        case "lastLoginTime":
            return lastLoginTime;
        case "isRewardDay":
            return isRewardDay;
        case "firstPayStatus":
            return firstPayStatus;
        case "pieceHPNum":
            return pieceHPNum;
        case "pieceDamNum":
            return pieceDamNum;
        case "pieceAttSpeed":
            return pieceAttSpeed;
        case "dayTasks":
            return dayTasks;
        case "lastLeaveClan":
            return lastLeaveClan;
        case "monthCode":
            return monthCode;
        case "isMonCode":
            return isMonCode;
        case "builds":
            return builds;
        case "obstes":
            return obstes;
        case "teches":
            return teches;
        case "heroes":
            return heroes;
        case "share":
            return share;
        case "moneyActivity":
            return moneyActivity;
        case "moneyActivityType":
            return moneyActivityType;
        }
        return null;
    }

    public Player setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Player setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "pname":
            return setPname(value2);
        case "channel":
            return setChannel(value2);
        case "npcs":
            return setNpcs(value2);
        case "clancid":
            return setClancid(value2);
        case "cname":
            return setCname(value2);
        case "spells":
            return setSpells(value2);
        case "mark":
            return setMark(value2);
        case "dayTasks":
            return setDaytasks(value2);
        case "builds":
            return setBuilds(value2);
        case "obstes":
            return setObstes(value2);
        case "teches":
            return setTeches(value2);
        case "heroes":
            return setHeroes(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Player setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Player setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Player");
        result.put("id", id);
        result.put("pcid", pcid);
        result.put("ucid", ucid);
        result.put("svcid", svcid);
        result.put("pname", pname);
        result.put("type", type);
        result.put("state", state);
        result.put("channel", channel);
        result.put("icon", icon);
        result.put("exp", exp);
        result.put("crystal", crystal);
        result.put("renown", renown);
        result.put("weekRenown", weekRenown);
        result.put("cur_npc", cur_npc);
        result.put("npcs", npcs);
        result.put("all_money", all_money);
        result.put("last_money", last_money);
        result.put("last_pay_time", last_pay_time);
        result.put("guid_step", guid_step);
        result.put("clancid", clancid);
        result.put("clanPost", clanPost);
        result.put("cname", cname);
        result.put("cicon", cicon);
        result.put("maxBuidId", maxBuidId);
        result.put("maxObstId", maxObstId);
        result.put("stored_oil", stored_oil);
        result.put("stored_gold", stored_gold);
        result.put("isOnline", isOnline);
        result.put("protectTime", protectTime);
        result.put("maxAttMCId", maxAttMCId);
        result.put("maxBONum", maxBONum);
        result.put("curBONum", curBONum);
        result.put("spells", spells);
        result.put("lastAddObst", lastAddObst);
        result.put("mark", mark);
        result.put("curtownlvl", curtownlvl);
        result.put("loginDay", loginDay);
        result.put("lastLoginTime", lastLoginTime);
        result.put("isRewardDay", isRewardDay);
        result.put("firstPayStatus", firstPayStatus);
        result.put("pieceHPNum", pieceHPNum);
        result.put("pieceDamNum", pieceDamNum);
        result.put("pieceAttSpeed", pieceAttSpeed);
        result.put("dayTasks", dayTasks);
        result.put("lastLeaveClan", lastLeaveClan);
        result.put("monthCode", monthCode);
        result.put("isMonCode", isMonCode);
        result.put("builds", builds);
        result.put("obstes", obstes);
        result.put("teches", teches);
        result.put("heroes", heroes);
        result.put("share", share);
        result.put("moneyActivity", moneyActivity);
        result.put("moneyActivityType", moneyActivityType);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("pcid", pcid);
        result.put("ucid", ucid);
        result.put("svcid", svcid);
        result.put("pname", pname);
        result.put("type", type);
        result.put("state", state);
        result.put("channel", channel);
        result.put("icon", icon);
        result.put("exp", exp);
        result.put("crystal", crystal);
        result.put("renown", renown);
        result.put("weekRenown", weekRenown);
        result.put("cur_npc", cur_npc);
        result.put("npcs", npcs);
        result.put("all_money", all_money);
        result.put("last_money", last_money);
        result.put("last_pay_time", last_pay_time);
        result.put("guid_step", guid_step);
        result.put("clancid", clancid);
        result.put("clanPost", clanPost);
        result.put("cname", cname);
        result.put("cicon", cicon);
        result.put("maxBuidId", maxBuidId);
        result.put("maxObstId", maxObstId);
        result.put("stored_oil", stored_oil);
        result.put("stored_gold", stored_gold);
        result.put("isOnline", isOnline);
        result.put("protectTime", protectTime);
        result.put("maxAttMCId", maxAttMCId);
        result.put("maxBONum", maxBONum);
        result.put("curBONum", curBONum);
        result.put("spells", spells);
        result.put("lastAddObst", lastAddObst);
        result.put("mark", mark);
        result.put("curtownlvl", curtownlvl);
        result.put("loginDay", loginDay);
        result.put("lastLoginTime", lastLoginTime);
        result.put("isRewardDay", isRewardDay);
        result.put("firstPayStatus", firstPayStatus);
        result.put("pieceHPNum", pieceHPNum);
        result.put("pieceDamNum", pieceDamNum);
        result.put("pieceAttSpeed", pieceAttSpeed);
        result.put("dayTasks", dayTasks);
        result.put("lastLeaveClan", lastLeaveClan);
        result.put("monthCode", monthCode);
        result.put("isMonCode", isMonCode);
        result.put("builds", builds);
        result.put("obstes", obstes);
        result.put("teches", teches);
        result.put("heroes", heroes);
        result.put("share", share);
        result.put("moneyActivity", moneyActivity);
        result.put("moneyActivityType", moneyActivityType);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Player");
        result.put("id", id);
        result.put("pcid", pcid);
        result.put("ucid", ucid);
        result.put("svcid", svcid);
        result.put("pname", pname);
        result.put("type", type);
        result.put("state", state);
        result.put("channel", channel);
        result.put("icon", icon);
        result.put("exp", exp);
        result.put("crystal", crystal);
        result.put("renown", renown);
        result.put("weekRenown", weekRenown);
        result.put("cur_npc", cur_npc);
        result.put("npcs", npcs);
        result.put("all_money", all_money);
        result.put("last_money", last_money);
        result.put("last_pay_time", last_pay_time);
        result.put("guid_step", guid_step);
        result.put("clancid", clancid);
        result.put("clanPost", clanPost);
        result.put("cname", cname);
        result.put("cicon", cicon);
        result.put("maxBuidId", maxBuidId);
        result.put("maxObstId", maxObstId);
        result.put("stored_oil", stored_oil);
        result.put("stored_gold", stored_gold);
        result.put("isOnline", isOnline);
        result.put("protectTime", protectTime);
        result.put("maxAttMCId", maxAttMCId);
        result.put("maxBONum", maxBONum);
        result.put("curBONum", curBONum);
        result.put("spells", spells);
        result.put("lastAddObst", lastAddObst);
        result.put("mark", mark);
        result.put("curtownlvl", curtownlvl);
        result.put("loginDay", loginDay);
        result.put("lastLoginTime", lastLoginTime);
        result.put("isRewardDay", isRewardDay);
        result.put("firstPayStatus", firstPayStatus);
        result.put("pieceHPNum", pieceHPNum);
        result.put("pieceDamNum", pieceDamNum);
        result.put("pieceAttSpeed", pieceAttSpeed);
        result.put("dayTasks", dayTasks);
        result.put("lastLeaveClan", lastLeaveClan);
        result.put("monthCode", monthCode);
        result.put("isMonCode", isMonCode);
        result.put("builds", builds);
        result.put("obstes", obstes);
        result.put("teches", teches);
        result.put("heroes", heroes);
        result.put("share", share);
        result.put("moneyActivity", moneyActivity);
        result.put("moneyActivityType", moneyActivityType);
        return result;
    }

    public Player mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer pcid = (Integer)e.get("pcid");
        Integer ucid = (Integer)e.get("ucid");
        Integer svcid = (Integer)e.get("svcid");
        String pname = (String)e.get("pname");
        Integer type = (Integer)e.get("type");
        Integer state = (Integer)e.get("state");
        String channel = (String)e.get("channel");
        Integer icon = (Integer)e.get("icon");
        Integer exp = (Integer)e.get("exp");
        Integer crystal = (Integer)e.get("crystal");
        Integer renown = (Integer)e.get("renown");
        Integer weekRenown = (Integer)e.get("weekRenown");
        Integer cur_npc = (Integer)e.get("cur_npc");
        String npcs = (String)e.get("npcs");
        Integer all_money = (Integer)e.get("all_money");
        Integer last_money = (Integer)e.get("last_money");
        Long last_pay_time = (Long)e.get("last_pay_time");
        Integer guid_step = (Integer)e.get("guid_step");
        String clancid = (String)e.get("clancid");
        Integer clanPost = (Integer)e.get("clanPost");
        String cname = (String)e.get("cname");
        Integer cicon = (Integer)e.get("cicon");
        Integer maxBuidId = (Integer)e.get("maxBuidId");
        Integer maxObstId = (Integer)e.get("maxObstId");
        Integer stored_oil = (Integer)e.get("stored_oil");
        Integer stored_gold = (Integer)e.get("stored_gold");
        Boolean isOnline = (Boolean)e.get("isOnline");
        Long protectTime = (Long)e.get("protectTime");
        Integer maxAttMCId = (Integer)e.get("maxAttMCId");
        Integer maxBONum = (Integer)e.get("maxBONum");
        Integer curBONum = (Integer)e.get("curBONum");
        String spells = (String)e.get("spells");
        Long lastAddObst = (Long)e.get("lastAddObst");
        String mark = (String)e.get("mark");
        Integer curtownlvl = (Integer)e.get("curtownlvl");
        Integer loginDay = (Integer)e.get("loginDay");
        Long lastLoginTime = (Long)e.get("lastLoginTime");
        Boolean isRewardDay = (Boolean)e.get("isRewardDay");
        Integer firstPayStatus = (Integer)e.get("firstPayStatus");
        Integer pieceHPNum = (Integer)e.get("pieceHPNum");
        Integer pieceDamNum = (Integer)e.get("pieceDamNum");
        Integer pieceAttSpeed = (Integer)e.get("pieceAttSpeed");
        String dayTasks = (String)e.get("dayTasks");
        Long lastLeaveClan = (Long)e.get("lastLeaveClan");
        Long monthCode = (Long)e.get("monthCode");
        Boolean isMonCode = (Boolean)e.get("isMonCode");
        String builds = (String)e.get("builds");
        String obstes = (String)e.get("obstes");
        String teches = (String)e.get("teches");
        String heroes = (String)e.get("heroes");
        Integer share = (Integer)e.get("share");
        Double moneyActivity = (Double)e.get("moneyActivity");
        Integer moneyActivityType = (Integer)e.get("moneyActivityType");

        if(id == null) id = 0;
        if(pcid == null) pcid = 0;
        if(ucid == null) ucid = 0;
        if(svcid == null) svcid = 0;
        if(pname == null) pname = "";
        if(type == null) type = 0;
        if(state == null) state = 0;
        if(channel == null) channel = "";
        if(icon == null) icon = 0;
        if(exp == null) exp = 0;
        if(crystal == null) crystal = 0;
        if(renown == null) renown = 0;
        if(weekRenown == null) weekRenown = 0;
        if(cur_npc == null) cur_npc = 0;
        if(npcs == null) npcs = "";
        if(all_money == null) all_money = 0;
        if(last_money == null) last_money = 0;
        if(last_pay_time == null) last_pay_time = 0L;
        if(guid_step == null) guid_step = 0;
        if(clancid == null) clancid = "";
        if(clanPost == null) clanPost = 0;
        if(cname == null) cname = "";
        if(cicon == null) cicon = 0;
        if(maxBuidId == null) maxBuidId = 0;
        if(maxObstId == null) maxObstId = 0;
        if(stored_oil == null) stored_oil = 0;
        if(stored_gold == null) stored_gold = 0;
        if(isOnline == null) isOnline = false;
        if(protectTime == null) protectTime = 0L;
        if(maxAttMCId == null) maxAttMCId = 0;
        if(maxBONum == null) maxBONum = 0;
        if(curBONum == null) curBONum = 0;
        if(spells == null) spells = "";
        if(lastAddObst == null) lastAddObst = 0L;
        if(mark == null) mark = "";
        if(curtownlvl == null) curtownlvl = 0;
        if(loginDay == null) loginDay = 0;
        if(lastLoginTime == null) lastLoginTime = 0L;
        if(isRewardDay == null) isRewardDay = false;
        if(firstPayStatus == null) firstPayStatus = 0;
        if(pieceHPNum == null) pieceHPNum = 0;
        if(pieceDamNum == null) pieceDamNum = 0;
        if(pieceAttSpeed == null) pieceAttSpeed = 0;
        if(dayTasks == null) dayTasks = "";
        if(lastLeaveClan == null) lastLeaveClan = 0L;
        if(monthCode == null) monthCode = 0L;
        if(isMonCode == null) isMonCode = false;
        if(builds == null) builds = "";
        if(obstes == null) obstes = "";
        if(teches == null) teches = "";
        if(heroes == null) heroes = "";
        if(share == null) share = 0;
        if(moneyActivity == null) moneyActivity = 0.0;
        if(moneyActivityType == null) moneyActivityType = 0;

        setId(id);
        setPcid(pcid);
        setUcid(ucid);
        setSvcid(svcid);
        setPname(pname);
        setType(type);
        setState(state);
        setChannel(channel);
        setIcon(icon);
        setExp(exp);
        setCrystal(crystal);
        setRenown(renown);
        setWeekrenown(weekRenown);
        setCur_npc(cur_npc);
        setNpcs(npcs);
        setAll_money(all_money);
        setLast_money(last_money);
        setLast_pay_time(last_pay_time);
        setGuid_step(guid_step);
        setClancid(clancid);
        setClanpost(clanPost);
        setCname(cname);
        setCicon(cicon);
        setMaxbuidid(maxBuidId);
        setMaxobstid(maxObstId);
        setStored_oil(stored_oil);
        setStored_gold(stored_gold);
        setIsonline(isOnline);
        setProtecttime(protectTime);
        setMaxattmcid(maxAttMCId);
        setMaxbonum(maxBONum);
        setCurbonum(curBONum);
        setSpells(spells);
        setLastaddobst(lastAddObst);
        setMark(mark);
        setCurtownlvl(curtownlvl);
        setLoginday(loginDay);
        setLastlogintime(lastLoginTime);
        setIsrewardday(isRewardDay);
        setFirstpaystatus(firstPayStatus);
        setPiecehpnum(pieceHPNum);
        setPiecedamnum(pieceDamNum);
        setPieceattspeed(pieceAttSpeed);
        setDaytasks(dayTasks);
        setLastleaveclan(lastLeaveClan);
        setMonthcode(monthCode);
        setIsmoncode(isMonCode);
        setBuilds(builds);
        setObstes(obstes);
        setTeches(teches);
        setHeroes(heroes);
        setShare(share);
        setMoneyactivity(moneyActivity);
        setMoneyactivitytype(moneyActivityType);

        return this;
    }

    public static final Player mapTo(Map e){
        Player result = new Player();

        Integer id = (Integer)e.get("id");
        Integer pcid = (Integer)e.get("pcid");
        Integer ucid = (Integer)e.get("ucid");
        Integer svcid = (Integer)e.get("svcid");
        String pname = (String)e.get("pname");
        Integer type = (Integer)e.get("type");
        Integer state = (Integer)e.get("state");
        String channel = (String)e.get("channel");
        Integer icon = (Integer)e.get("icon");
        Integer exp = (Integer)e.get("exp");
        Integer crystal = (Integer)e.get("crystal");
        Integer renown = (Integer)e.get("renown");
        Integer weekRenown = (Integer)e.get("weekRenown");
        Integer cur_npc = (Integer)e.get("cur_npc");
        String npcs = (String)e.get("npcs");
        Integer all_money = (Integer)e.get("all_money");
        Integer last_money = (Integer)e.get("last_money");
        Long last_pay_time = (Long)e.get("last_pay_time");
        Integer guid_step = (Integer)e.get("guid_step");
        String clancid = (String)e.get("clancid");
        Integer clanPost = (Integer)e.get("clanPost");
        String cname = (String)e.get("cname");
        Integer cicon = (Integer)e.get("cicon");
        Integer maxBuidId = (Integer)e.get("maxBuidId");
        Integer maxObstId = (Integer)e.get("maxObstId");
        Integer stored_oil = (Integer)e.get("stored_oil");
        Integer stored_gold = (Integer)e.get("stored_gold");
        Boolean isOnline = (Boolean)e.get("isOnline");
        Long protectTime = (Long)e.get("protectTime");
        Integer maxAttMCId = (Integer)e.get("maxAttMCId");
        Integer maxBONum = (Integer)e.get("maxBONum");
        Integer curBONum = (Integer)e.get("curBONum");
        String spells = (String)e.get("spells");
        Long lastAddObst = (Long)e.get("lastAddObst");
        String mark = (String)e.get("mark");
        Integer curtownlvl = (Integer)e.get("curtownlvl");
        Integer loginDay = (Integer)e.get("loginDay");
        Long lastLoginTime = (Long)e.get("lastLoginTime");
        Boolean isRewardDay = (Boolean)e.get("isRewardDay");
        Integer firstPayStatus = (Integer)e.get("firstPayStatus");
        Integer pieceHPNum = (Integer)e.get("pieceHPNum");
        Integer pieceDamNum = (Integer)e.get("pieceDamNum");
        Integer pieceAttSpeed = (Integer)e.get("pieceAttSpeed");
        String dayTasks = (String)e.get("dayTasks");
        Long lastLeaveClan = (Long)e.get("lastLeaveClan");
        Long monthCode = (Long)e.get("monthCode");
        Boolean isMonCode = (Boolean)e.get("isMonCode");
        String builds = (String)e.get("builds");
        String obstes = (String)e.get("obstes");
        String teches = (String)e.get("teches");
        String heroes = (String)e.get("heroes");
        Integer share = (Integer)e.get("share");
        Double moneyActivity = (Double)e.get("moneyActivity");
        Integer moneyActivityType = (Integer)e.get("moneyActivityType");

        if(id == null) id = 0;
        if(pcid == null) pcid = 0;
        if(ucid == null) ucid = 0;
        if(svcid == null) svcid = 0;
        if(pname == null) pname = "";
        if(type == null) type = 0;
        if(state == null) state = 0;
        if(channel == null) channel = "";
        if(icon == null) icon = 0;
        if(exp == null) exp = 0;
        if(crystal == null) crystal = 0;
        if(renown == null) renown = 0;
        if(weekRenown == null) weekRenown = 0;
        if(cur_npc == null) cur_npc = 0;
        if(npcs == null) npcs = "";
        if(all_money == null) all_money = 0;
        if(last_money == null) last_money = 0;
        if(last_pay_time == null) last_pay_time = 0L;
        if(guid_step == null) guid_step = 0;
        if(clancid == null) clancid = "";
        if(clanPost == null) clanPost = 0;
        if(cname == null) cname = "";
        if(cicon == null) cicon = 0;
        if(maxBuidId == null) maxBuidId = 0;
        if(maxObstId == null) maxObstId = 0;
        if(stored_oil == null) stored_oil = 0;
        if(stored_gold == null) stored_gold = 0;
        if(isOnline == null) isOnline = false;
        if(protectTime == null) protectTime = 0L;
        if(maxAttMCId == null) maxAttMCId = 0;
        if(maxBONum == null) maxBONum = 0;
        if(curBONum == null) curBONum = 0;
        if(spells == null) spells = "";
        if(lastAddObst == null) lastAddObst = 0L;
        if(mark == null) mark = "";
        if(curtownlvl == null) curtownlvl = 0;
        if(loginDay == null) loginDay = 0;
        if(lastLoginTime == null) lastLoginTime = 0L;
        if(isRewardDay == null) isRewardDay = false;
        if(firstPayStatus == null) firstPayStatus = 0;
        if(pieceHPNum == null) pieceHPNum = 0;
        if(pieceDamNum == null) pieceDamNum = 0;
        if(pieceAttSpeed == null) pieceAttSpeed = 0;
        if(dayTasks == null) dayTasks = "";
        if(lastLeaveClan == null) lastLeaveClan = 0L;
        if(monthCode == null) monthCode = 0L;
        if(isMonCode == null) isMonCode = false;
        if(builds == null) builds = "";
        if(obstes == null) obstes = "";
        if(teches == null) teches = "";
        if(heroes == null) heroes = "";
        if(share == null) share = 0;
        if(moneyActivity == null) moneyActivity = 0.0;
        if(moneyActivityType == null) moneyActivityType = 0;

        result.id = id;
        result.pcid = pcid;
        result.ucid = ucid;
        result.svcid = svcid;
        result.pname = pname;
        result.type = type;
        result.state = state;
        result.channel = channel;
        result.icon = icon;
        result.exp = exp;
        result.crystal = crystal;
        result.renown = renown;
        result.weekRenown = weekRenown;
        result.cur_npc = cur_npc;
        result.npcs = npcs;
        result.all_money = all_money;
        result.last_money = last_money;
        result.last_pay_time = last_pay_time;
        result.guid_step = guid_step;
        result.clancid = clancid;
        result.clanPost = clanPost;
        result.cname = cname;
        result.cicon = cicon;
        result.maxBuidId = maxBuidId;
        result.maxObstId = maxObstId;
        result.stored_oil = stored_oil;
        result.stored_gold = stored_gold;
        result.isOnline = isOnline;
        result.protectTime = protectTime;
        result.maxAttMCId = maxAttMCId;
        result.maxBONum = maxBONum;
        result.curBONum = curBONum;
        result.spells = spells;
        result.lastAddObst = lastAddObst;
        result.mark = mark;
        result.curtownlvl = curtownlvl;
        result.loginDay = loginDay;
        result.lastLoginTime = lastLoginTime;
        result.isRewardDay = isRewardDay;
        result.firstPayStatus = firstPayStatus;
        result.pieceHPNum = pieceHPNum;
        result.pieceDamNum = pieceDamNum;
        result.pieceAttSpeed = pieceAttSpeed;
        result.dayTasks = dayTasks;
        result.lastLeaveClan = lastLeaveClan;
        result.monthCode = monthCode;
        result.isMonCode = isMonCode;
        result.builds = builds;
        result.obstes = obstes;
        result.teches = teches;
        result.heroes = heroes;
        result.share = share;
        result.moneyActivity = moneyActivity;
        result.moneyActivityType = moneyActivityType;

        return result;
    }

    public static final Player originalTo(Map e){
        Player result = new Player();

        Integer id = (Integer)e.get("id");
        Integer pcid = (Integer)e.get("pcid");
        Integer ucid = (Integer)e.get("ucid");
        Integer svcid = (Integer)e.get("svcid");
        String pname = (String)e.get("pname");
        Integer type = (Integer)e.get("type");
        Integer state = (Integer)e.get("state");
        String channel = (String)e.get("channel");
        Integer icon = (Integer)e.get("icon");
        Integer exp = (Integer)e.get("exp");
        Integer crystal = (Integer)e.get("crystal");
        Integer renown = (Integer)e.get("renown");
        Integer weekRenown = (Integer)e.get("weekRenown");
        Integer cur_npc = (Integer)e.get("cur_npc");
        String npcs = (String)e.get("npcs");
        Integer all_money = (Integer)e.get("all_money");
        Integer last_money = (Integer)e.get("last_money");
        Long last_pay_time = (Long)e.get("last_pay_time");
        Integer guid_step = (Integer)e.get("guid_step");
        String clancid = (String)e.get("clancid");
        Integer clanPost = (Integer)e.get("clanPost");
        String cname = (String)e.get("cname");
        Integer cicon = (Integer)e.get("cicon");
        Integer maxBuidId = (Integer)e.get("maxBuidId");
        Integer maxObstId = (Integer)e.get("maxObstId");
        Integer stored_oil = (Integer)e.get("stored_oil");
        Integer stored_gold = (Integer)e.get("stored_gold");
        Boolean isOnline = (Boolean)e.get("isOnline");
        Long protectTime = (Long)e.get("protectTime");
        Integer maxAttMCId = (Integer)e.get("maxAttMCId");
        Integer maxBONum = (Integer)e.get("maxBONum");
        Integer curBONum = (Integer)e.get("curBONum");
        String spells = (String)e.get("spells");
        Long lastAddObst = (Long)e.get("lastAddObst");
        String mark = (String)e.get("mark");
        Integer curtownlvl = (Integer)e.get("curtownlvl");
        Integer loginDay = (Integer)e.get("loginDay");
        Long lastLoginTime = (Long)e.get("lastLoginTime");
        Boolean isRewardDay = (Boolean)e.get("isRewardDay");
        Integer firstPayStatus = (Integer)e.get("firstPayStatus");
        Integer pieceHPNum = (Integer)e.get("pieceHPNum");
        Integer pieceDamNum = (Integer)e.get("pieceDamNum");
        Integer pieceAttSpeed = (Integer)e.get("pieceAttSpeed");
        String dayTasks = (String)e.get("dayTasks");
        Long lastLeaveClan = (Long)e.get("lastLeaveClan");
        Long monthCode = (Long)e.get("monthCode");
        Boolean isMonCode = (Boolean)e.get("isMonCode");
        String builds = (String)e.get("builds");
        String obstes = (String)e.get("obstes");
        String teches = (String)e.get("teches");
        String heroes = (String)e.get("heroes");
        Integer share = (Integer)e.get("share");
        Double moneyActivity = (Double)e.get("moneyActivity");
        Integer moneyActivityType = (Integer)e.get("moneyActivityType");

        if(id == null) id = 0;
        if(pcid == null) pcid = 0;
        if(ucid == null) ucid = 0;
        if(svcid == null) svcid = 0;
        if(pname == null) pname = "";
        if(type == null) type = 0;
        if(state == null) state = 0;
        if(channel == null) channel = "";
        if(icon == null) icon = 0;
        if(exp == null) exp = 0;
        if(crystal == null) crystal = 0;
        if(renown == null) renown = 0;
        if(weekRenown == null) weekRenown = 0;
        if(cur_npc == null) cur_npc = 0;
        if(npcs == null) npcs = "";
        if(all_money == null) all_money = 0;
        if(last_money == null) last_money = 0;
        if(last_pay_time == null) last_pay_time = 0L;
        if(guid_step == null) guid_step = 0;
        if(clancid == null) clancid = "";
        if(clanPost == null) clanPost = 0;
        if(cname == null) cname = "";
        if(cicon == null) cicon = 0;
        if(maxBuidId == null) maxBuidId = 0;
        if(maxObstId == null) maxObstId = 0;
        if(stored_oil == null) stored_oil = 0;
        if(stored_gold == null) stored_gold = 0;
        if(isOnline == null) isOnline = false;
        if(protectTime == null) protectTime = 0L;
        if(maxAttMCId == null) maxAttMCId = 0;
        if(maxBONum == null) maxBONum = 0;
        if(curBONum == null) curBONum = 0;
        if(spells == null) spells = "";
        if(lastAddObst == null) lastAddObst = 0L;
        if(mark == null) mark = "";
        if(curtownlvl == null) curtownlvl = 0;
        if(loginDay == null) loginDay = 0;
        if(lastLoginTime == null) lastLoginTime = 0L;
        if(isRewardDay == null) isRewardDay = false;
        if(firstPayStatus == null) firstPayStatus = 0;
        if(pieceHPNum == null) pieceHPNum = 0;
        if(pieceDamNum == null) pieceDamNum = 0;
        if(pieceAttSpeed == null) pieceAttSpeed = 0;
        if(dayTasks == null) dayTasks = "";
        if(lastLeaveClan == null) lastLeaveClan = 0L;
        if(monthCode == null) monthCode = 0L;
        if(isMonCode == null) isMonCode = false;
        if(builds == null) builds = "";
        if(obstes == null) obstes = "";
        if(teches == null) teches = "";
        if(heroes == null) heroes = "";
        if(share == null) share = 0;
        if(moneyActivity == null) moneyActivity = 0.0;
        if(moneyActivityType == null) moneyActivityType = 0;

        result.id = id;
        result.pcid = pcid;
        result.ucid = ucid;
        result.svcid = svcid;
        result.pname = pname;
        result.type = type;
        result.state = state;
        result.channel = channel;
        result.icon = icon;
        result.exp = exp;
        result.crystal = crystal;
        result.renown = renown;
        result.weekRenown = weekRenown;
        result.cur_npc = cur_npc;
        result.npcs = npcs;
        result.all_money = all_money;
        result.last_money = last_money;
        result.last_pay_time = last_pay_time;
        result.guid_step = guid_step;
        result.clancid = clancid;
        result.clanPost = clanPost;
        result.cname = cname;
        result.cicon = cicon;
        result.maxBuidId = maxBuidId;
        result.maxObstId = maxObstId;
        result.stored_oil = stored_oil;
        result.stored_gold = stored_gold;
        result.isOnline = isOnline;
        result.protectTime = protectTime;
        result.maxAttMCId = maxAttMCId;
        result.maxBONum = maxBONum;
        result.curBONum = curBONum;
        result.spells = spells;
        result.lastAddObst = lastAddObst;
        result.mark = mark;
        result.curtownlvl = curtownlvl;
        result.loginDay = loginDay;
        result.lastLoginTime = lastLoginTime;
        result.isRewardDay = isRewardDay;
        result.firstPayStatus = firstPayStatus;
        result.pieceHPNum = pieceHPNum;
        result.pieceDamNum = pieceDamNum;
        result.pieceAttSpeed = pieceAttSpeed;
        result.dayTasks = dayTasks;
        result.lastLeaveClan = lastLeaveClan;
        result.monthCode = monthCode;
        result.isMonCode = isMonCode;
        result.builds = builds;
        result.obstes = obstes;
        result.teches = teches;
        result.heroes = heroes;
        result.share = share;
        result.moneyActivity = moneyActivity;
        result.moneyActivityType = moneyActivityType;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Player createFor(byte[] b) throws Exception {
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
    public static final Player loadByKey(int id) {
        return PlayerEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Player insert() {
        Player result = PlayerEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Player asyncInsert() {
        Player result = PlayerEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Player insert2() {
        return PlayerEntity.insert2(this);
    }

    public final Player asyncInsert2() {
        Player result = PlayerEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Player update() {
        return PlayerEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        PlayerEntity.asynchronousUpdate( this );
        return true;
    }

    public final Player insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return PlayerEntity.delete(id);
    }

    public final void asyncDelete() {
        PlayerEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Player clone2() {
        try{
            return (Player) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
