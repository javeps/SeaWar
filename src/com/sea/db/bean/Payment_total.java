package com.sea.db.bean;

import java.io.*;
import java.util.*;
import java.sql.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.sea.db.entity.*;

//seawar2_design - payment_total
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Payment_total extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 456611537; // com.sea.db.bean.Payment_total

    public static String TABLENAME = "payment_total";

    public static final String primary = "id";

    public static final String[] carrays ={"id", "svcid", "channel", "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "INT", "INT", "INT", "INT", "INT", "INT", "INT", "INT", "INT", "INT", "INT"};


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
    public int svcid;
    public String channel;
    public int january;
    public int february;
    public int march;
    public int april;
    public int may;
    public int june;
    public int july;
    public int august;
    public int september;
    public int october;
    public int november;
    public int december;

    public String _tableId() {
        return TABLENAME;
    }

    public Object _primaryKey() {
        return id;
    }

    public int getId(){
        return id;
    }

    public Payment_total setId(int id){
        this.id = id;
        return this;
    }

    public int getSvcid(){
        return svcid;
    }

    public Payment_total setSvcid(int svcid){
        int _old = svcid;
        this.svcid = svcid;
        changeIt("svcid", _old, svcid);
        return this;
    }

    public Payment_total changeSvcid(int svcid){
        return setSvcid(this.svcid + svcid);
    }

    public Payment_total changeSvcidWithMin(int svcid, int _min){
        int _v2 = this.svcid + svcid;
        return setSvcid(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeSvcidWithMax(int svcid, int _max){
        int _v2 = this.svcid + svcid;
        return setSvcid(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeSvcidWithMinMax(int svcid, int _min, int _max){
        int _v2 = this.svcid + svcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setSvcid(_v2 < _min  ? _min : _v2);
    }

    public String getChannel(){
        return channel;
    }

    public Payment_total setChannel(String channel){
        String _old = channel;
        this.channel = channel;
        changeIt("channel", _old, channel);
        return this;
    }

    public int getJanuary(){
        return january;
    }

    public Payment_total setJanuary(int january){
        int _old = january;
        this.january = january;
        changeIt("january", _old, january);
        return this;
    }

    public Payment_total changeJanuary(int january){
        return setJanuary(this.january + january);
    }

    public Payment_total changeJanuaryWithMin(int january, int _min){
        int _v2 = this.january + january;
        return setJanuary(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeJanuaryWithMax(int january, int _max){
        int _v2 = this.january + january;
        return setJanuary(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeJanuaryWithMinMax(int january, int _min, int _max){
        int _v2 = this.january + january;
        _v2 = _v2 > _max  ? _max : _v2;
        return setJanuary(_v2 < _min  ? _min : _v2);
    }

    public int getFebruary(){
        return february;
    }

    public Payment_total setFebruary(int february){
        int _old = february;
        this.february = february;
        changeIt("february", _old, february);
        return this;
    }

    public Payment_total changeFebruary(int february){
        return setFebruary(this.february + february);
    }

    public Payment_total changeFebruaryWithMin(int february, int _min){
        int _v2 = this.february + february;
        return setFebruary(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeFebruaryWithMax(int february, int _max){
        int _v2 = this.february + february;
        return setFebruary(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeFebruaryWithMinMax(int february, int _min, int _max){
        int _v2 = this.february + february;
        _v2 = _v2 > _max  ? _max : _v2;
        return setFebruary(_v2 < _min  ? _min : _v2);
    }

    public int getMarch(){
        return march;
    }

    public Payment_total setMarch(int march){
        int _old = march;
        this.march = march;
        changeIt("march", _old, march);
        return this;
    }

    public Payment_total changeMarch(int march){
        return setMarch(this.march + march);
    }

    public Payment_total changeMarchWithMin(int march, int _min){
        int _v2 = this.march + march;
        return setMarch(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeMarchWithMax(int march, int _max){
        int _v2 = this.march + march;
        return setMarch(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeMarchWithMinMax(int march, int _min, int _max){
        int _v2 = this.march + march;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMarch(_v2 < _min  ? _min : _v2);
    }

    public int getApril(){
        return april;
    }

    public Payment_total setApril(int april){
        int _old = april;
        this.april = april;
        changeIt("april", _old, april);
        return this;
    }

    public Payment_total changeApril(int april){
        return setApril(this.april + april);
    }

    public Payment_total changeAprilWithMin(int april, int _min){
        int _v2 = this.april + april;
        return setApril(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeAprilWithMax(int april, int _max){
        int _v2 = this.april + april;
        return setApril(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeAprilWithMinMax(int april, int _min, int _max){
        int _v2 = this.april + april;
        _v2 = _v2 > _max  ? _max : _v2;
        return setApril(_v2 < _min  ? _min : _v2);
    }

    public int getMay(){
        return may;
    }

    public Payment_total setMay(int may){
        int _old = may;
        this.may = may;
        changeIt("may", _old, may);
        return this;
    }

    public Payment_total changeMay(int may){
        return setMay(this.may + may);
    }

    public Payment_total changeMayWithMin(int may, int _min){
        int _v2 = this.may + may;
        return setMay(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeMayWithMax(int may, int _max){
        int _v2 = this.may + may;
        return setMay(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeMayWithMinMax(int may, int _min, int _max){
        int _v2 = this.may + may;
        _v2 = _v2 > _max  ? _max : _v2;
        return setMay(_v2 < _min  ? _min : _v2);
    }

    public int getJune(){
        return june;
    }

    public Payment_total setJune(int june){
        int _old = june;
        this.june = june;
        changeIt("june", _old, june);
        return this;
    }

    public Payment_total changeJune(int june){
        return setJune(this.june + june);
    }

    public Payment_total changeJuneWithMin(int june, int _min){
        int _v2 = this.june + june;
        return setJune(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeJuneWithMax(int june, int _max){
        int _v2 = this.june + june;
        return setJune(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeJuneWithMinMax(int june, int _min, int _max){
        int _v2 = this.june + june;
        _v2 = _v2 > _max  ? _max : _v2;
        return setJune(_v2 < _min  ? _min : _v2);
    }

    public int getJuly(){
        return july;
    }

    public Payment_total setJuly(int july){
        int _old = july;
        this.july = july;
        changeIt("july", _old, july);
        return this;
    }

    public Payment_total changeJuly(int july){
        return setJuly(this.july + july);
    }

    public Payment_total changeJulyWithMin(int july, int _min){
        int _v2 = this.july + july;
        return setJuly(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeJulyWithMax(int july, int _max){
        int _v2 = this.july + july;
        return setJuly(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeJulyWithMinMax(int july, int _min, int _max){
        int _v2 = this.july + july;
        _v2 = _v2 > _max  ? _max : _v2;
        return setJuly(_v2 < _min  ? _min : _v2);
    }

    public int getAugust(){
        return august;
    }

    public Payment_total setAugust(int august){
        int _old = august;
        this.august = august;
        changeIt("august", _old, august);
        return this;
    }

    public Payment_total changeAugust(int august){
        return setAugust(this.august + august);
    }

    public Payment_total changeAugustWithMin(int august, int _min){
        int _v2 = this.august + august;
        return setAugust(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeAugustWithMax(int august, int _max){
        int _v2 = this.august + august;
        return setAugust(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeAugustWithMinMax(int august, int _min, int _max){
        int _v2 = this.august + august;
        _v2 = _v2 > _max  ? _max : _v2;
        return setAugust(_v2 < _min  ? _min : _v2);
    }

    public int getSeptember(){
        return september;
    }

    public Payment_total setSeptember(int september){
        int _old = september;
        this.september = september;
        changeIt("september", _old, september);
        return this;
    }

    public Payment_total changeSeptember(int september){
        return setSeptember(this.september + september);
    }

    public Payment_total changeSeptemberWithMin(int september, int _min){
        int _v2 = this.september + september;
        return setSeptember(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeSeptemberWithMax(int september, int _max){
        int _v2 = this.september + september;
        return setSeptember(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeSeptemberWithMinMax(int september, int _min, int _max){
        int _v2 = this.september + september;
        _v2 = _v2 > _max  ? _max : _v2;
        return setSeptember(_v2 < _min  ? _min : _v2);
    }

    public int getOctober(){
        return october;
    }

    public Payment_total setOctober(int october){
        int _old = october;
        this.october = october;
        changeIt("october", _old, october);
        return this;
    }

    public Payment_total changeOctober(int october){
        return setOctober(this.october + october);
    }

    public Payment_total changeOctoberWithMin(int october, int _min){
        int _v2 = this.october + october;
        return setOctober(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeOctoberWithMax(int october, int _max){
        int _v2 = this.october + october;
        return setOctober(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeOctoberWithMinMax(int october, int _min, int _max){
        int _v2 = this.october + october;
        _v2 = _v2 > _max  ? _max : _v2;
        return setOctober(_v2 < _min  ? _min : _v2);
    }

    public int getNovember(){
        return november;
    }

    public Payment_total setNovember(int november){
        int _old = november;
        this.november = november;
        changeIt("november", _old, november);
        return this;
    }

    public Payment_total changeNovember(int november){
        return setNovember(this.november + november);
    }

    public Payment_total changeNovemberWithMin(int november, int _min){
        int _v2 = this.november + november;
        return setNovember(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeNovemberWithMax(int november, int _max){
        int _v2 = this.november + november;
        return setNovember(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeNovemberWithMinMax(int november, int _min, int _max){
        int _v2 = this.november + november;
        _v2 = _v2 > _max  ? _max : _v2;
        return setNovember(_v2 < _min  ? _min : _v2);
    }

    public int getDecember(){
        return december;
    }

    public Payment_total setDecember(int december){
        int _old = december;
        this.december = december;
        changeIt("december", _old, december);
        return this;
    }

    public Payment_total changeDecember(int december){
        return setDecember(this.december + december);
    }

    public Payment_total changeDecemberWithMin(int december, int _min){
        int _v2 = this.december + december;
        return setDecember(_v2 < _min  ? _min : _v2);
    }

    public Payment_total changeDecemberWithMax(int december, int _max){
        int _v2 = this.december + december;
        return setDecember(_v2 > _max  ? _max : _v2);
    }

    public Payment_total changeDecemberWithMinMax(int december, int _min, int _max){
        int _v2 = this.december + december;
        _v2 = _v2 > _max  ? _max : _v2;
        return setDecember(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Payment_total v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Payment_total v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Payment_total newPayment_total(Integer id, Integer svcid, String channel, Integer january, Integer february, Integer march, Integer april, Integer may, Integer june, Integer july, Integer august, Integer september, Integer october, Integer november, Integer december) {
        Payment_total result = new Payment_total();
        result.id = id;
        result.svcid = svcid;
        result.channel = channel;
        result.january = january;
        result.february = february;
        result.march = march;
        result.april = april;
        result.may = may;
        result.june = june;
        result.july = july;
        result.august = august;
        result.september = september;
        result.october = october;
        result.november = november;
        result.december = december;
        return result;
    }

    public static Payment_total newPayment_total(Payment_total payment_total) {
        Payment_total result = new Payment_total();
        result.id = payment_total.id;
        result.svcid = payment_total.svcid;
        result.channel = payment_total.channel;
        result.january = payment_total.january;
        result.february = payment_total.february;
        result.march = payment_total.march;
        result.april = payment_total.april;
        result.may = payment_total.may;
        result.june = payment_total.june;
        result.july = payment_total.july;
        result.august = payment_total.august;
        result.september = payment_total.september;
        result.october = payment_total.october;
        result.november = payment_total.november;
        result.december = payment_total.december;
        return result;
    }

    public Payment_total createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    public void writeObject(OutputStream _out) throws Exception {
        B2OutputStream.writeObject(_out, _CID);
        B2OutputStream.writeObject(_out, id);
        B2OutputStream.writeObject(_out, svcid);
        B2OutputStream.writeObject(_out, channel);
        B2OutputStream.writeObject(_out, january);
        B2OutputStream.writeObject(_out, february);
        B2OutputStream.writeObject(_out, march);
        B2OutputStream.writeObject(_out, april);
        B2OutputStream.writeObject(_out, may);
        B2OutputStream.writeObject(_out, june);
        B2OutputStream.writeObject(_out, july);
        B2OutputStream.writeObject(_out, august);
        B2OutputStream.writeObject(_out, september);
        B2OutputStream.writeObject(_out, october);
        B2OutputStream.writeObject(_out, november);
        B2OutputStream.writeObject(_out, december);
    }

    public static final Payment_total readObject(InputStream _in) throws Exception {
        Payment_total result = new Payment_total();
        result.id = (Integer) B2InputStream.readObject(_in);
        result.svcid = (Integer) B2InputStream.readObject(_in);
        result.channel = (String) B2InputStream.readObject(_in);
        result.january = (Integer) B2InputStream.readObject(_in);
        result.february = (Integer) B2InputStream.readObject(_in);
        result.march = (Integer) B2InputStream.readObject(_in);
        result.april = (Integer) B2InputStream.readObject(_in);
        result.may = (Integer) B2InputStream.readObject(_in);
        result.june = (Integer) B2InputStream.readObject(_in);
        result.july = (Integer) B2InputStream.readObject(_in);
        result.august = (Integer) B2InputStream.readObject(_in);
        result.september = (Integer) B2InputStream.readObject(_in);
        result.october = (Integer) B2InputStream.readObject(_in);
        result.november = (Integer) B2InputStream.readObject(_in);
        result.december = (Integer) B2InputStream.readObject(_in);
        return result;
    }

    /*
    @SuppressWarnings("unused")
    public static void getPayment_total(){
        Payment_total payment_total = null; // payment_total
        { // new and insert Payment_total (payment_total)
            int id = 0; 	// id
            int svcid = 0; 	// svcid
            String channel = ""; 	// channel
            int january = 0; 	// january
            int february = 0; 	// february
            int march = 0; 	// march
            int april = 0; 	// april
            int may = 0; 	// may
            int june = 0; 	// june
            int july = 0; 	// july
            int august = 0; 	// august
            int september = 0; 	// september
            int october = 0; 	// october
            int november = 0; 	// november
            int december = 0; 	// december
            payment_total = Payment_total.newPayment_total(id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december);
        }
        payment_total = payment_total.insert();

        int id = payment_total.getId(); 	// id
        int svcid = payment_total.getSvcid(); 	// svcid
        String channel = payment_total.getChannel(); 	// channel
        int january = payment_total.getJanuary(); 	// january
        int february = payment_total.getFebruary(); 	// february
        int march = payment_total.getMarch(); 	// march
        int april = payment_total.getApril(); 	// april
        int may = payment_total.getMay(); 	// may
        int june = payment_total.getJune(); 	// june
        int july = payment_total.getJuly(); 	// july
        int august = payment_total.getAugust(); 	// august
        int september = payment_total.getSeptember(); 	// september
        int october = payment_total.getOctober(); 	// october
        int november = payment_total.getNovember(); 	// november
        int december = payment_total.getDecember(); 	// december
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
        case "january":
            return january;
        case "february":
            return february;
        case "march":
            return march;
        case "april":
            return april;
        case "may":
            return may;
        case "june":
            return june;
        case "july":
            return july;
        case "august":
            return august;
        case "september":
            return september;
        case "october":
            return october;
        case "november":
            return november;
        case "december":
            return december;
        }
        return 0;
    }

    public Payment_total setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Payment_total setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "id":
            return setId(value2);
        case "svcid":
            return setSvcid(value2);
        case "january":
            return setJanuary(value2);
        case "february":
            return setFebruary(value2);
        case "march":
            return setMarch(value2);
        case "april":
            return setApril(value2);
        case "may":
            return setMay(value2);
        case "june":
            return setJune(value2);
        case "july":
            return setJuly(value2);
        case "august":
            return setAugust(value2);
        case "september":
            return setSeptember(value2);
        case "october":
            return setOctober(value2);
        case "november":
            return setNovember(value2);
        case "december":
            return setDecember(value2);
        }
        return this;
    }

    public Payment_total changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Payment_total changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case "svcid":
            return changeSvcid(value2);
        case "january":
            return changeJanuary(value2);
        case "february":
            return changeFebruary(value2);
        case "march":
            return changeMarch(value2);
        case "april":
            return changeApril(value2);
        case "may":
            return changeMay(value2);
        case "june":
            return changeJune(value2);
        case "july":
            return changeJuly(value2);
        case "august":
            return changeAugust(value2);
        case "september":
            return changeSeptember(value2);
        case "october":
            return changeOctober(value2);
        case "november":
            return changeNovember(value2);
        case "december":
            return changeDecember(value2);
        }
        return this;
    }

    public Payment_total changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Payment_total changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case "svcid":
            return changeSvcidWithMin(value2, _min);
        case "january":
            return changeJanuaryWithMin(value2, _min);
        case "february":
            return changeFebruaryWithMin(value2, _min);
        case "march":
            return changeMarchWithMin(value2, _min);
        case "april":
            return changeAprilWithMin(value2, _min);
        case "may":
            return changeMayWithMin(value2, _min);
        case "june":
            return changeJuneWithMin(value2, _min);
        case "july":
            return changeJulyWithMin(value2, _min);
        case "august":
            return changeAugustWithMin(value2, _min);
        case "september":
            return changeSeptemberWithMin(value2, _min);
        case "october":
            return changeOctoberWithMin(value2, _min);
        case "november":
            return changeNovemberWithMin(value2, _min);
        case "december":
            return changeDecemberWithMin(value2, _min);
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

    public Payment_total setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Payment_total setDouble(String fieldEn, double value2) {
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
        case "channel": 
            return channel;
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
        case "svcid":
            return svcid;
        case "channel":
            return channel;
        case "january":
            return january;
        case "february":
            return february;
        case "march":
            return march;
        case "april":
            return april;
        case "may":
            return may;
        case "june":
            return june;
        case "july":
            return july;
        case "august":
            return august;
        case "september":
            return september;
        case "october":
            return october;
        case "november":
            return november;
        case "december":
            return december;
        }
        return null;
    }

    public Payment_total setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Payment_total setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case "channel":
            return setChannel(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Payment_total setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Payment_total setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.sea.db.bean.Payment_total");
        result.put("id", id);
        result.put("svcid", svcid);
        result.put("channel", channel);
        result.put("january", january);
        result.put("february", february);
        result.put("march", march);
        result.put("april", april);
        result.put("may", may);
        result.put("june", june);
        result.put("july", july);
        result.put("august", august);
        result.put("september", september);
        result.put("october", october);
        result.put("november", november);
        result.put("december", december);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("svcid", svcid);
        result.put("channel", channel);
        result.put("january", january);
        result.put("february", february);
        result.put("march", march);
        result.put("april", april);
        result.put("may", may);
        result.put("june", june);
        result.put("july", july);
        result.put("august", august);
        result.put("september", september);
        result.put("october", october);
        result.put("november", november);
        result.put("december", december);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.sea.db.bean.Payment_total");
        result.put("id", id);
        result.put("svcid", svcid);
        result.put("channel", channel);
        result.put("january", january);
        result.put("february", february);
        result.put("march", march);
        result.put("april", april);
        result.put("may", may);
        result.put("june", june);
        result.put("july", july);
        result.put("august", august);
        result.put("september", september);
        result.put("october", october);
        result.put("november", november);
        result.put("december", december);
        return result;
    }

    public Payment_total mapToObject(Map e){
        Integer id = (Integer)e.get("id");
        Integer svcid = (Integer)e.get("svcid");
        String channel = (String)e.get("channel");
        Integer january = (Integer)e.get("january");
        Integer february = (Integer)e.get("february");
        Integer march = (Integer)e.get("march");
        Integer april = (Integer)e.get("april");
        Integer may = (Integer)e.get("may");
        Integer june = (Integer)e.get("june");
        Integer july = (Integer)e.get("july");
        Integer august = (Integer)e.get("august");
        Integer september = (Integer)e.get("september");
        Integer october = (Integer)e.get("october");
        Integer november = (Integer)e.get("november");
        Integer december = (Integer)e.get("december");

        if(id == null) id = 0;
        if(svcid == null) svcid = 0;
        if(channel == null) channel = "";
        if(january == null) january = 0;
        if(february == null) february = 0;
        if(march == null) march = 0;
        if(april == null) april = 0;
        if(may == null) may = 0;
        if(june == null) june = 0;
        if(july == null) july = 0;
        if(august == null) august = 0;
        if(september == null) september = 0;
        if(october == null) october = 0;
        if(november == null) november = 0;
        if(december == null) december = 0;

        setId(id);
        setSvcid(svcid);
        setChannel(channel);
        setJanuary(january);
        setFebruary(february);
        setMarch(march);
        setApril(april);
        setMay(may);
        setJune(june);
        setJuly(july);
        setAugust(august);
        setSeptember(september);
        setOctober(october);
        setNovember(november);
        setDecember(december);

        return this;
    }

    public static final Payment_total mapTo(Map e){
        Payment_total result = new Payment_total();

        Integer id = (Integer)e.get("id");
        Integer svcid = (Integer)e.get("svcid");
        String channel = (String)e.get("channel");
        Integer january = (Integer)e.get("january");
        Integer february = (Integer)e.get("february");
        Integer march = (Integer)e.get("march");
        Integer april = (Integer)e.get("april");
        Integer may = (Integer)e.get("may");
        Integer june = (Integer)e.get("june");
        Integer july = (Integer)e.get("july");
        Integer august = (Integer)e.get("august");
        Integer september = (Integer)e.get("september");
        Integer october = (Integer)e.get("october");
        Integer november = (Integer)e.get("november");
        Integer december = (Integer)e.get("december");

        if(id == null) id = 0;
        if(svcid == null) svcid = 0;
        if(channel == null) channel = "";
        if(january == null) january = 0;
        if(february == null) february = 0;
        if(march == null) march = 0;
        if(april == null) april = 0;
        if(may == null) may = 0;
        if(june == null) june = 0;
        if(july == null) july = 0;
        if(august == null) august = 0;
        if(september == null) september = 0;
        if(october == null) october = 0;
        if(november == null) november = 0;
        if(december == null) december = 0;

        result.id = id;
        result.svcid = svcid;
        result.channel = channel;
        result.january = january;
        result.february = february;
        result.march = march;
        result.april = april;
        result.may = may;
        result.june = june;
        result.july = july;
        result.august = august;
        result.september = september;
        result.october = october;
        result.november = november;
        result.december = december;

        return result;
    }

    public static final Payment_total originalTo(Map e){
        Payment_total result = new Payment_total();

        Integer id = (Integer)e.get("id");
        Integer svcid = (Integer)e.get("svcid");
        String channel = (String)e.get("channel");
        Integer january = (Integer)e.get("january");
        Integer february = (Integer)e.get("february");
        Integer march = (Integer)e.get("march");
        Integer april = (Integer)e.get("april");
        Integer may = (Integer)e.get("may");
        Integer june = (Integer)e.get("june");
        Integer july = (Integer)e.get("july");
        Integer august = (Integer)e.get("august");
        Integer september = (Integer)e.get("september");
        Integer october = (Integer)e.get("october");
        Integer november = (Integer)e.get("november");
        Integer december = (Integer)e.get("december");

        if(id == null) id = 0;
        if(svcid == null) svcid = 0;
        if(channel == null) channel = "";
        if(january == null) january = 0;
        if(february == null) february = 0;
        if(march == null) march = 0;
        if(april == null) april = 0;
        if(may == null) may = 0;
        if(june == null) june = 0;
        if(july == null) july = 0;
        if(august == null) august = 0;
        if(september == null) september = 0;
        if(october == null) october = 0;
        if(november == null) november = 0;
        if(december == null) december = 0;

        result.id = id;
        result.svcid = svcid;
        result.channel = channel;
        result.january = january;
        result.february = february;
        result.march = march;
        result.april = april;
        result.may = may;
        result.june = june;
        result.july = july;
        result.august = august;
        result.september = september;
        result.october = october;
        result.november = november;
        result.december = december;

        return result;
    }

    public byte[] toBytes() throws Exception {
        return B2Helper.toBytes(toMap());
    }
    public static final Payment_total createFor(byte[] b) throws Exception {
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
    public static final Payment_total loadByKey(int id) {
        return Payment_totalEntity.getByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str);
        }
    }

    public final Payment_total insert() {
        Payment_total result = Payment_totalEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Payment_total asyncInsert() {
        Payment_total result = Payment_totalEntity.asynchronousInsert(this);
        // id = result.id;
        return result;
    }

    public final Payment_total insert2() {
        return Payment_totalEntity.insert2(this);
    }

    public final Payment_total asyncInsert2() {
        Payment_total result = Payment_totalEntity.asynchronousInsert2(this);
        // id = result.id;
        return result;
    }

    public final Payment_total update() {
        return Payment_totalEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Payment_totalEntity.asynchronousUpdate( this );
        return true;
    }

    public final Payment_total insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Payment_totalEntity.delete(id);
    }

    public final void asyncDelete() {
        Payment_totalEntity.asynchronousDelete(id);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Payment_total clone2() {
        try{
            return (Payment_total) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
