package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - payment_total
@SuppressWarnings({"rawtypes", "unchecked"})
public class Payment_totalDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Payment_totalDAO.class);

    public static final String TABLE = "payment_total";
    public static String TABLENAME = "payment_total";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "svcid", "channel", "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
    public static String coulmns = "id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december";
    public static String coulmns2 = "svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december";

    public Payment_totalDAO(java.sql.Connection conn) {
        super(conn);
    }

    public Payment_totalDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public Payment_totalDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Payment_total payment_total) {
        return insert(payment_total, TABLENAME);
    }

    public int insert(final Payment_total payment_total, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            payment_total.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december) VALUES (:svcid, :channel, :january, :february, :march, :april, :may, :june, :july, :august, :september, :october, :november, :december)");
            Map map = super.insert(sql.toString(), payment_total);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Payment_total payment_total) {
        asynchronousInsert(payment_total, TABLENAME);
    }

    public void asynchronousInsert(final Payment_total payment_total, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(payment_total, TABLENAME2);
                   } catch (Exception e) {
                       log.error(e2s(e));
                   } finally {
                       decrementAndGet();
                   }
                }
            });
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public int asynchronousInsert2(final Payment_total payment_total) {
        return asynchronousInsert2(payment_total, TABLENAME);
    }

    public int asynchronousInsert2(final Payment_total payment_total, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(payment_total, TABLENAME2);
                   } catch (Exception e) {
                       log.error(e2s(e));
                   } finally {
                       decrementAndGet();
                   }
                }
            });
        } catch(Exception e) {
            log.info(e2s(e));
        }
        return payment_total.id;
    }

    public int insert2(final Payment_total payment_total) {
        return insert2(payment_total, TABLENAME);
    }

    public int insert2(final Payment_total payment_total, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            payment_total.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december) VALUES (:id, :svcid, :channel, :january, :february, :march, :april, :may, :june, :july, :august, :september, :october, :november, :december)");
            Map map = super.insert(sql.toString(), payment_total);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Payment_total> payment_totals) {
        return insert(payment_totals, TABLENAME);
    }

    public int[] insert(final List<Payment_total> payment_totals, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(payment_totals == null || payment_totals.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december) VALUES (:svcid, :channel, :january, :february, :march, :april, :may, :june, :july, :august, :september, :october, :november, :december)");
            return super.batchInsert(sql.toString(), payment_totals);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int id) {
        return deleteByKey(id, TABLENAME);
    }

    public int deleteByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousDeleteByKey(final int id) {
        asynchronousDeleteByKey(id, TABLENAME);
    }

    public void asynchronousDeleteByKey(final int id, final String TABLENAME2) {
        try{
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        deleteByKey(id, TABLENAME2);
                    } catch (Exception e) {
                       log.info(e2s(e));
                    } finally {
                        decrementAndGet();
                    }
                }
            });
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public int[] deleteByKey(final int[] ids) {
        return deleteByKey(ids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            List list = newList();
            for (int id : keys) {
                Map params = newMap();
                params.put("id", id);
                list.add(params);
            }
            return super.batchUpdate(sql.toString(), list);
        } catch(Exception e) {
            log.info(e2s(e));
            return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInKeys(final List<Integer> keys) {
        return deleteInKeys(keys, TABLENAME);
    }

    public int deleteInKeys(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Payment_total> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Payment_total> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Payment_total payment_total = beans.get(i);
                int id = payment_total.id;
                sb.append(id);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment_total> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Payment_total> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Payment_total.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPKs() {
        return selectPKs(TABLENAME);
    }

    public List<Integer> selectPKs(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Map> selectInIndex() {
        return selectInIndex(TABLENAME);
    }

    public List<Map> selectInIndex(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svcid, channel FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment_total> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Payment_total> selectIn(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Payment_total.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment_total> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Payment_total> selectIn2(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Payment_total.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectInPKs(final List<Integer> keys) {
        return selectInPKs(keys, TABLENAME);
    }

    public List<Integer> selectInPKs(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            List<Integer> result = newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment_total> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Payment_total> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Payment_total.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLastPKs(final int num) {
        return selectLastPKs(num, TABLENAME);
    }

    public List<Integer> selectLastPKs(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Payment_total last() {
        return last(TABLENAME);
    }

    public Payment_total last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Payment_total.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment_total> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Payment_total> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Payment_total.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment_total> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Payment_total> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Payment_total.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int id) {
        return selectGtKeyPKs(id, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Payment_total selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Payment_total selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Payment_total.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int maxId() {
        return maxId(TABLENAME);
    }

    public int maxId(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT MAX(id) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Payment_total selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Payment_total selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Payment_total.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Payment_total selectBySvcidChannel(final Integer svcid, String channel) {
        return selectBySvcidChannel(svcid, channel, TABLENAME);
    }

    public Payment_total selectBySvcidChannel(final Integer svcid, String channel, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" WHERE svcid=:svcid AND channel=:channel");
            Map params = newMap();
            params.put("svcid", svcid);
            params.put("channel", channel);
            return super.queryForObject(sql.toString(), params, Payment_total.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int count() {
        return count(TABLENAME);
    }

    public int count(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append("");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment_total> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Payment_total> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svcid, channel, january, february, march, april, may, june, july, august, september, october, november, december FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Payment_total.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByPagePKs(final int begin, final int num) {
        return selectByPagePKs(begin, num, TABLENAME);
    }

    public List<Integer> selectByPagePKs(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Payment_total payment_total) {
        return updateByKey(payment_total, TABLENAME);
    }

    public int updateByKey(final Payment_total payment_total, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = payment_total.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), payment_total);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Payment_total payment_total) {
        asynchronousUpdate(payment_total, TABLENAME);
    }

    public void asynchronousUpdate(final Payment_total payment_total, final String TABLENAME2) {
        try {

            String _ustr = payment_total.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, payment_total);
                    } catch (Exception e) {
                        log.error(e2s(e));
                    } finally {
                        decrementAndGet();
                    }
                }
            });
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public int updateSvcidByKey(final int svcid, final int id){
        return updateSvcidByKey(svcid, id, TABLENAME);
    }

    public int updateSvcidByKey(final int svcid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid=svcid+:svcid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("svcid", svcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvcidWithMinByKey(final int id, final int svcid, final int _min){
        return updateSvcidWithMinByKey(id, svcid, _min, TABLENAME);
    }

    public int updateSvcidWithMinByKey(final int id, final int svcid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid = (select case when svcid+:svcid<=:_min then :_min else svcid+:svcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("svcid", svcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvcidWithMinInKeys(final List<Integer> keys, final int svcid, final int _min){
        return updateSvcidWithMinInKeys(keys, svcid, _min, TABLENAME);
    }

    public int updateSvcidWithMinInKeys(final List<Integer> keys, final int svcid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid = (select case when svcid+:svcid<=:_min then :_min else svcid+:svcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("svcid", svcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvcidWithMaxByKey(final int id, final int svcid, final int _max){
        return updateSvcidWithMaxByKey(id, svcid, _max, TABLENAME);
    }

    public int updateSvcidWithMaxByKey(final int id, final int svcid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid = (select case when svcid+:svcid>=:_max then :_max else svcid+:svcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("svcid", svcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvcidWithMaxInKeys(final List<Integer> keys, final int svcid, final int _max){
        return updateSvcidWithMaxInKeys(keys, svcid, _max, TABLENAME);
    }

    public int updateSvcidWithMaxInKeys(final List<Integer> keys, final int svcid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid = (select case when svcid+:svcid>=:_max then :_max else svcid+:svcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("svcid", svcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvcidWithMinMaxByKey(final int id, final int svcid, final int _min, final int _max){
        return updateSvcidWithMinMaxByKey(id, svcid, _min, _max, TABLENAME);
    }

    public int updateSvcidWithMinMaxByKey(final int id, final int svcid, final int _min, final int _max, final String TABLENAME2){
        if( svcid < 0 ) {
            return updateSvcidWithMinByKey(id, svcid, _min, TABLENAME2);
        } else {
            return updateSvcidWithMaxByKey(id, svcid, _max, TABLENAME2);
        }
    }

    public int updateSvcidWithMinMaxInKeys(final List<Integer> keys, final int svcid, final int _min, final int _max){
        return updateSvcidWithMinMaxInKeys(keys, svcid, _min, _max, TABLENAME);
    }

    public int updateSvcidWithMinMaxInKeys(final List<Integer> keys, final int svcid, final int _min, final int _max, final String TABLENAME2){
        if( svcid < 0 ) {
            return updateSvcidWithMinInKeys(keys, svcid, _min, TABLENAME2);
        } else {
            return updateSvcidWithMaxInKeys(keys, svcid, _max, TABLENAME2);
        }
    }

    public int updateJanuaryByKey(final int january, final int id){
        return updateJanuaryByKey(january, id, TABLENAME);
    }

    public int updateJanuaryByKey(final int january, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET january=january+:january WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("january", january);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJanuaryWithMinByKey(final int id, final int january, final int _min){
        return updateJanuaryWithMinByKey(id, january, _min, TABLENAME);
    }

    public int updateJanuaryWithMinByKey(final int id, final int january, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET january = (select case when january+:january<=:_min then :_min else january+:january end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("january", january);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJanuaryWithMinInKeys(final List<Integer> keys, final int january, final int _min){
        return updateJanuaryWithMinInKeys(keys, january, _min, TABLENAME);
    }

    public int updateJanuaryWithMinInKeys(final List<Integer> keys, final int january, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET january = (select case when january+:january<=:_min then :_min else january+:january end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("january", january);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJanuaryWithMaxByKey(final int id, final int january, final int _max){
        return updateJanuaryWithMaxByKey(id, january, _max, TABLENAME);
    }

    public int updateJanuaryWithMaxByKey(final int id, final int january, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET january = (select case when january+:january>=:_max then :_max else january+:january end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("january", january);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJanuaryWithMaxInKeys(final List<Integer> keys, final int january, final int _max){
        return updateJanuaryWithMaxInKeys(keys, january, _max, TABLENAME);
    }

    public int updateJanuaryWithMaxInKeys(final List<Integer> keys, final int january, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET january = (select case when january+:january>=:_max then :_max else january+:january end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("january", january);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJanuaryWithMinMaxByKey(final int id, final int january, final int _min, final int _max){
        return updateJanuaryWithMinMaxByKey(id, january, _min, _max, TABLENAME);
    }

    public int updateJanuaryWithMinMaxByKey(final int id, final int january, final int _min, final int _max, final String TABLENAME2){
        if( january < 0 ) {
            return updateJanuaryWithMinByKey(id, january, _min, TABLENAME2);
        } else {
            return updateJanuaryWithMaxByKey(id, january, _max, TABLENAME2);
        }
    }

    public int updateJanuaryWithMinMaxInKeys(final List<Integer> keys, final int january, final int _min, final int _max){
        return updateJanuaryWithMinMaxInKeys(keys, january, _min, _max, TABLENAME);
    }

    public int updateJanuaryWithMinMaxInKeys(final List<Integer> keys, final int january, final int _min, final int _max, final String TABLENAME2){
        if( january < 0 ) {
            return updateJanuaryWithMinInKeys(keys, january, _min, TABLENAME2);
        } else {
            return updateJanuaryWithMaxInKeys(keys, january, _max, TABLENAME2);
        }
    }

    public int updateFebruaryByKey(final int february, final int id){
        return updateFebruaryByKey(february, id, TABLENAME);
    }

    public int updateFebruaryByKey(final int february, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET february=february+:february WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("february", february);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFebruaryWithMinByKey(final int id, final int february, final int _min){
        return updateFebruaryWithMinByKey(id, february, _min, TABLENAME);
    }

    public int updateFebruaryWithMinByKey(final int id, final int february, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET february = (select case when february+:february<=:_min then :_min else february+:february end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("february", february);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFebruaryWithMinInKeys(final List<Integer> keys, final int february, final int _min){
        return updateFebruaryWithMinInKeys(keys, february, _min, TABLENAME);
    }

    public int updateFebruaryWithMinInKeys(final List<Integer> keys, final int february, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET february = (select case when february+:february<=:_min then :_min else february+:february end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("february", february);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFebruaryWithMaxByKey(final int id, final int february, final int _max){
        return updateFebruaryWithMaxByKey(id, february, _max, TABLENAME);
    }

    public int updateFebruaryWithMaxByKey(final int id, final int february, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET february = (select case when february+:february>=:_max then :_max else february+:february end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("february", february);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFebruaryWithMaxInKeys(final List<Integer> keys, final int february, final int _max){
        return updateFebruaryWithMaxInKeys(keys, february, _max, TABLENAME);
    }

    public int updateFebruaryWithMaxInKeys(final List<Integer> keys, final int february, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET february = (select case when february+:february>=:_max then :_max else february+:february end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("february", february);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFebruaryWithMinMaxByKey(final int id, final int february, final int _min, final int _max){
        return updateFebruaryWithMinMaxByKey(id, february, _min, _max, TABLENAME);
    }

    public int updateFebruaryWithMinMaxByKey(final int id, final int february, final int _min, final int _max, final String TABLENAME2){
        if( february < 0 ) {
            return updateFebruaryWithMinByKey(id, february, _min, TABLENAME2);
        } else {
            return updateFebruaryWithMaxByKey(id, february, _max, TABLENAME2);
        }
    }

    public int updateFebruaryWithMinMaxInKeys(final List<Integer> keys, final int february, final int _min, final int _max){
        return updateFebruaryWithMinMaxInKeys(keys, february, _min, _max, TABLENAME);
    }

    public int updateFebruaryWithMinMaxInKeys(final List<Integer> keys, final int february, final int _min, final int _max, final String TABLENAME2){
        if( february < 0 ) {
            return updateFebruaryWithMinInKeys(keys, february, _min, TABLENAME2);
        } else {
            return updateFebruaryWithMaxInKeys(keys, february, _max, TABLENAME2);
        }
    }

    public int updateMarchByKey(final int march, final int id){
        return updateMarchByKey(march, id, TABLENAME);
    }

    public int updateMarchByKey(final int march, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET march=march+:march WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("march", march);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMarchWithMinByKey(final int id, final int march, final int _min){
        return updateMarchWithMinByKey(id, march, _min, TABLENAME);
    }

    public int updateMarchWithMinByKey(final int id, final int march, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET march = (select case when march+:march<=:_min then :_min else march+:march end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("march", march);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMarchWithMinInKeys(final List<Integer> keys, final int march, final int _min){
        return updateMarchWithMinInKeys(keys, march, _min, TABLENAME);
    }

    public int updateMarchWithMinInKeys(final List<Integer> keys, final int march, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET march = (select case when march+:march<=:_min then :_min else march+:march end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("march", march);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMarchWithMaxByKey(final int id, final int march, final int _max){
        return updateMarchWithMaxByKey(id, march, _max, TABLENAME);
    }

    public int updateMarchWithMaxByKey(final int id, final int march, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET march = (select case when march+:march>=:_max then :_max else march+:march end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("march", march);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMarchWithMaxInKeys(final List<Integer> keys, final int march, final int _max){
        return updateMarchWithMaxInKeys(keys, march, _max, TABLENAME);
    }

    public int updateMarchWithMaxInKeys(final List<Integer> keys, final int march, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET march = (select case when march+:march>=:_max then :_max else march+:march end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("march", march);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMarchWithMinMaxByKey(final int id, final int march, final int _min, final int _max){
        return updateMarchWithMinMaxByKey(id, march, _min, _max, TABLENAME);
    }

    public int updateMarchWithMinMaxByKey(final int id, final int march, final int _min, final int _max, final String TABLENAME2){
        if( march < 0 ) {
            return updateMarchWithMinByKey(id, march, _min, TABLENAME2);
        } else {
            return updateMarchWithMaxByKey(id, march, _max, TABLENAME2);
        }
    }

    public int updateMarchWithMinMaxInKeys(final List<Integer> keys, final int march, final int _min, final int _max){
        return updateMarchWithMinMaxInKeys(keys, march, _min, _max, TABLENAME);
    }

    public int updateMarchWithMinMaxInKeys(final List<Integer> keys, final int march, final int _min, final int _max, final String TABLENAME2){
        if( march < 0 ) {
            return updateMarchWithMinInKeys(keys, march, _min, TABLENAME2);
        } else {
            return updateMarchWithMaxInKeys(keys, march, _max, TABLENAME2);
        }
    }

    public int updateAprilByKey(final int april, final int id){
        return updateAprilByKey(april, id, TABLENAME);
    }

    public int updateAprilByKey(final int april, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET april=april+:april WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("april", april);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAprilWithMinByKey(final int id, final int april, final int _min){
        return updateAprilWithMinByKey(id, april, _min, TABLENAME);
    }

    public int updateAprilWithMinByKey(final int id, final int april, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET april = (select case when april+:april<=:_min then :_min else april+:april end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("april", april);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAprilWithMinInKeys(final List<Integer> keys, final int april, final int _min){
        return updateAprilWithMinInKeys(keys, april, _min, TABLENAME);
    }

    public int updateAprilWithMinInKeys(final List<Integer> keys, final int april, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET april = (select case when april+:april<=:_min then :_min else april+:april end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("april", april);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAprilWithMaxByKey(final int id, final int april, final int _max){
        return updateAprilWithMaxByKey(id, april, _max, TABLENAME);
    }

    public int updateAprilWithMaxByKey(final int id, final int april, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET april = (select case when april+:april>=:_max then :_max else april+:april end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("april", april);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAprilWithMaxInKeys(final List<Integer> keys, final int april, final int _max){
        return updateAprilWithMaxInKeys(keys, april, _max, TABLENAME);
    }

    public int updateAprilWithMaxInKeys(final List<Integer> keys, final int april, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET april = (select case when april+:april>=:_max then :_max else april+:april end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("april", april);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAprilWithMinMaxByKey(final int id, final int april, final int _min, final int _max){
        return updateAprilWithMinMaxByKey(id, april, _min, _max, TABLENAME);
    }

    public int updateAprilWithMinMaxByKey(final int id, final int april, final int _min, final int _max, final String TABLENAME2){
        if( april < 0 ) {
            return updateAprilWithMinByKey(id, april, _min, TABLENAME2);
        } else {
            return updateAprilWithMaxByKey(id, april, _max, TABLENAME2);
        }
    }

    public int updateAprilWithMinMaxInKeys(final List<Integer> keys, final int april, final int _min, final int _max){
        return updateAprilWithMinMaxInKeys(keys, april, _min, _max, TABLENAME);
    }

    public int updateAprilWithMinMaxInKeys(final List<Integer> keys, final int april, final int _min, final int _max, final String TABLENAME2){
        if( april < 0 ) {
            return updateAprilWithMinInKeys(keys, april, _min, TABLENAME2);
        } else {
            return updateAprilWithMaxInKeys(keys, april, _max, TABLENAME2);
        }
    }

    public int updateMayByKey(final int may, final int id){
        return updateMayByKey(may, id, TABLENAME);
    }

    public int updateMayByKey(final int may, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET may=may+:may WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("may", may);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMayWithMinByKey(final int id, final int may, final int _min){
        return updateMayWithMinByKey(id, may, _min, TABLENAME);
    }

    public int updateMayWithMinByKey(final int id, final int may, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET may = (select case when may+:may<=:_min then :_min else may+:may end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("may", may);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMayWithMinInKeys(final List<Integer> keys, final int may, final int _min){
        return updateMayWithMinInKeys(keys, may, _min, TABLENAME);
    }

    public int updateMayWithMinInKeys(final List<Integer> keys, final int may, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET may = (select case when may+:may<=:_min then :_min else may+:may end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("may", may);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMayWithMaxByKey(final int id, final int may, final int _max){
        return updateMayWithMaxByKey(id, may, _max, TABLENAME);
    }

    public int updateMayWithMaxByKey(final int id, final int may, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET may = (select case when may+:may>=:_max then :_max else may+:may end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("may", may);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMayWithMaxInKeys(final List<Integer> keys, final int may, final int _max){
        return updateMayWithMaxInKeys(keys, may, _max, TABLENAME);
    }

    public int updateMayWithMaxInKeys(final List<Integer> keys, final int may, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET may = (select case when may+:may>=:_max then :_max else may+:may end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("may", may);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMayWithMinMaxByKey(final int id, final int may, final int _min, final int _max){
        return updateMayWithMinMaxByKey(id, may, _min, _max, TABLENAME);
    }

    public int updateMayWithMinMaxByKey(final int id, final int may, final int _min, final int _max, final String TABLENAME2){
        if( may < 0 ) {
            return updateMayWithMinByKey(id, may, _min, TABLENAME2);
        } else {
            return updateMayWithMaxByKey(id, may, _max, TABLENAME2);
        }
    }

    public int updateMayWithMinMaxInKeys(final List<Integer> keys, final int may, final int _min, final int _max){
        return updateMayWithMinMaxInKeys(keys, may, _min, _max, TABLENAME);
    }

    public int updateMayWithMinMaxInKeys(final List<Integer> keys, final int may, final int _min, final int _max, final String TABLENAME2){
        if( may < 0 ) {
            return updateMayWithMinInKeys(keys, may, _min, TABLENAME2);
        } else {
            return updateMayWithMaxInKeys(keys, may, _max, TABLENAME2);
        }
    }

    public int updateJuneByKey(final int june, final int id){
        return updateJuneByKey(june, id, TABLENAME);
    }

    public int updateJuneByKey(final int june, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET june=june+:june WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("june", june);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJuneWithMinByKey(final int id, final int june, final int _min){
        return updateJuneWithMinByKey(id, june, _min, TABLENAME);
    }

    public int updateJuneWithMinByKey(final int id, final int june, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET june = (select case when june+:june<=:_min then :_min else june+:june end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("june", june);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJuneWithMinInKeys(final List<Integer> keys, final int june, final int _min){
        return updateJuneWithMinInKeys(keys, june, _min, TABLENAME);
    }

    public int updateJuneWithMinInKeys(final List<Integer> keys, final int june, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET june = (select case when june+:june<=:_min then :_min else june+:june end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("june", june);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJuneWithMaxByKey(final int id, final int june, final int _max){
        return updateJuneWithMaxByKey(id, june, _max, TABLENAME);
    }

    public int updateJuneWithMaxByKey(final int id, final int june, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET june = (select case when june+:june>=:_max then :_max else june+:june end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("june", june);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJuneWithMaxInKeys(final List<Integer> keys, final int june, final int _max){
        return updateJuneWithMaxInKeys(keys, june, _max, TABLENAME);
    }

    public int updateJuneWithMaxInKeys(final List<Integer> keys, final int june, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET june = (select case when june+:june>=:_max then :_max else june+:june end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("june", june);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJuneWithMinMaxByKey(final int id, final int june, final int _min, final int _max){
        return updateJuneWithMinMaxByKey(id, june, _min, _max, TABLENAME);
    }

    public int updateJuneWithMinMaxByKey(final int id, final int june, final int _min, final int _max, final String TABLENAME2){
        if( june < 0 ) {
            return updateJuneWithMinByKey(id, june, _min, TABLENAME2);
        } else {
            return updateJuneWithMaxByKey(id, june, _max, TABLENAME2);
        }
    }

    public int updateJuneWithMinMaxInKeys(final List<Integer> keys, final int june, final int _min, final int _max){
        return updateJuneWithMinMaxInKeys(keys, june, _min, _max, TABLENAME);
    }

    public int updateJuneWithMinMaxInKeys(final List<Integer> keys, final int june, final int _min, final int _max, final String TABLENAME2){
        if( june < 0 ) {
            return updateJuneWithMinInKeys(keys, june, _min, TABLENAME2);
        } else {
            return updateJuneWithMaxInKeys(keys, june, _max, TABLENAME2);
        }
    }

    public int updateJulyByKey(final int july, final int id){
        return updateJulyByKey(july, id, TABLENAME);
    }

    public int updateJulyByKey(final int july, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET july=july+:july WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("july", july);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJulyWithMinByKey(final int id, final int july, final int _min){
        return updateJulyWithMinByKey(id, july, _min, TABLENAME);
    }

    public int updateJulyWithMinByKey(final int id, final int july, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET july = (select case when july+:july<=:_min then :_min else july+:july end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("july", july);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJulyWithMinInKeys(final List<Integer> keys, final int july, final int _min){
        return updateJulyWithMinInKeys(keys, july, _min, TABLENAME);
    }

    public int updateJulyWithMinInKeys(final List<Integer> keys, final int july, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET july = (select case when july+:july<=:_min then :_min else july+:july end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("july", july);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJulyWithMaxByKey(final int id, final int july, final int _max){
        return updateJulyWithMaxByKey(id, july, _max, TABLENAME);
    }

    public int updateJulyWithMaxByKey(final int id, final int july, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET july = (select case when july+:july>=:_max then :_max else july+:july end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("july", july);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJulyWithMaxInKeys(final List<Integer> keys, final int july, final int _max){
        return updateJulyWithMaxInKeys(keys, july, _max, TABLENAME);
    }

    public int updateJulyWithMaxInKeys(final List<Integer> keys, final int july, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET july = (select case when july+:july>=:_max then :_max else july+:july end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("july", july);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateJulyWithMinMaxByKey(final int id, final int july, final int _min, final int _max){
        return updateJulyWithMinMaxByKey(id, july, _min, _max, TABLENAME);
    }

    public int updateJulyWithMinMaxByKey(final int id, final int july, final int _min, final int _max, final String TABLENAME2){
        if( july < 0 ) {
            return updateJulyWithMinByKey(id, july, _min, TABLENAME2);
        } else {
            return updateJulyWithMaxByKey(id, july, _max, TABLENAME2);
        }
    }

    public int updateJulyWithMinMaxInKeys(final List<Integer> keys, final int july, final int _min, final int _max){
        return updateJulyWithMinMaxInKeys(keys, july, _min, _max, TABLENAME);
    }

    public int updateJulyWithMinMaxInKeys(final List<Integer> keys, final int july, final int _min, final int _max, final String TABLENAME2){
        if( july < 0 ) {
            return updateJulyWithMinInKeys(keys, july, _min, TABLENAME2);
        } else {
            return updateJulyWithMaxInKeys(keys, july, _max, TABLENAME2);
        }
    }

    public int updateAugustByKey(final int august, final int id){
        return updateAugustByKey(august, id, TABLENAME);
    }

    public int updateAugustByKey(final int august, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET august=august+:august WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("august", august);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAugustWithMinByKey(final int id, final int august, final int _min){
        return updateAugustWithMinByKey(id, august, _min, TABLENAME);
    }

    public int updateAugustWithMinByKey(final int id, final int august, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET august = (select case when august+:august<=:_min then :_min else august+:august end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("august", august);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAugustWithMinInKeys(final List<Integer> keys, final int august, final int _min){
        return updateAugustWithMinInKeys(keys, august, _min, TABLENAME);
    }

    public int updateAugustWithMinInKeys(final List<Integer> keys, final int august, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET august = (select case when august+:august<=:_min then :_min else august+:august end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("august", august);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAugustWithMaxByKey(final int id, final int august, final int _max){
        return updateAugustWithMaxByKey(id, august, _max, TABLENAME);
    }

    public int updateAugustWithMaxByKey(final int id, final int august, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET august = (select case when august+:august>=:_max then :_max else august+:august end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("august", august);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAugustWithMaxInKeys(final List<Integer> keys, final int august, final int _max){
        return updateAugustWithMaxInKeys(keys, august, _max, TABLENAME);
    }

    public int updateAugustWithMaxInKeys(final List<Integer> keys, final int august, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET august = (select case when august+:august>=:_max then :_max else august+:august end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("august", august);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAugustWithMinMaxByKey(final int id, final int august, final int _min, final int _max){
        return updateAugustWithMinMaxByKey(id, august, _min, _max, TABLENAME);
    }

    public int updateAugustWithMinMaxByKey(final int id, final int august, final int _min, final int _max, final String TABLENAME2){
        if( august < 0 ) {
            return updateAugustWithMinByKey(id, august, _min, TABLENAME2);
        } else {
            return updateAugustWithMaxByKey(id, august, _max, TABLENAME2);
        }
    }

    public int updateAugustWithMinMaxInKeys(final List<Integer> keys, final int august, final int _min, final int _max){
        return updateAugustWithMinMaxInKeys(keys, august, _min, _max, TABLENAME);
    }

    public int updateAugustWithMinMaxInKeys(final List<Integer> keys, final int august, final int _min, final int _max, final String TABLENAME2){
        if( august < 0 ) {
            return updateAugustWithMinInKeys(keys, august, _min, TABLENAME2);
        } else {
            return updateAugustWithMaxInKeys(keys, august, _max, TABLENAME2);
        }
    }

    public int updateSeptemberByKey(final int september, final int id){
        return updateSeptemberByKey(september, id, TABLENAME);
    }

    public int updateSeptemberByKey(final int september, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET september=september+:september WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("september", september);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSeptemberWithMinByKey(final int id, final int september, final int _min){
        return updateSeptemberWithMinByKey(id, september, _min, TABLENAME);
    }

    public int updateSeptemberWithMinByKey(final int id, final int september, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET september = (select case when september+:september<=:_min then :_min else september+:september end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("september", september);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSeptemberWithMinInKeys(final List<Integer> keys, final int september, final int _min){
        return updateSeptemberWithMinInKeys(keys, september, _min, TABLENAME);
    }

    public int updateSeptemberWithMinInKeys(final List<Integer> keys, final int september, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET september = (select case when september+:september<=:_min then :_min else september+:september end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("september", september);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSeptemberWithMaxByKey(final int id, final int september, final int _max){
        return updateSeptemberWithMaxByKey(id, september, _max, TABLENAME);
    }

    public int updateSeptemberWithMaxByKey(final int id, final int september, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET september = (select case when september+:september>=:_max then :_max else september+:september end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("september", september);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSeptemberWithMaxInKeys(final List<Integer> keys, final int september, final int _max){
        return updateSeptemberWithMaxInKeys(keys, september, _max, TABLENAME);
    }

    public int updateSeptemberWithMaxInKeys(final List<Integer> keys, final int september, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET september = (select case when september+:september>=:_max then :_max else september+:september end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("september", september);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSeptemberWithMinMaxByKey(final int id, final int september, final int _min, final int _max){
        return updateSeptemberWithMinMaxByKey(id, september, _min, _max, TABLENAME);
    }

    public int updateSeptemberWithMinMaxByKey(final int id, final int september, final int _min, final int _max, final String TABLENAME2){
        if( september < 0 ) {
            return updateSeptemberWithMinByKey(id, september, _min, TABLENAME2);
        } else {
            return updateSeptemberWithMaxByKey(id, september, _max, TABLENAME2);
        }
    }

    public int updateSeptemberWithMinMaxInKeys(final List<Integer> keys, final int september, final int _min, final int _max){
        return updateSeptemberWithMinMaxInKeys(keys, september, _min, _max, TABLENAME);
    }

    public int updateSeptemberWithMinMaxInKeys(final List<Integer> keys, final int september, final int _min, final int _max, final String TABLENAME2){
        if( september < 0 ) {
            return updateSeptemberWithMinInKeys(keys, september, _min, TABLENAME2);
        } else {
            return updateSeptemberWithMaxInKeys(keys, september, _max, TABLENAME2);
        }
    }

    public int updateOctoberByKey(final int october, final int id){
        return updateOctoberByKey(october, id, TABLENAME);
    }

    public int updateOctoberByKey(final int october, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET october=october+:october WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("october", october);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOctoberWithMinByKey(final int id, final int october, final int _min){
        return updateOctoberWithMinByKey(id, october, _min, TABLENAME);
    }

    public int updateOctoberWithMinByKey(final int id, final int october, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET october = (select case when october+:october<=:_min then :_min else october+:october end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("october", october);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOctoberWithMinInKeys(final List<Integer> keys, final int october, final int _min){
        return updateOctoberWithMinInKeys(keys, october, _min, TABLENAME);
    }

    public int updateOctoberWithMinInKeys(final List<Integer> keys, final int october, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET october = (select case when october+:october<=:_min then :_min else october+:october end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("october", october);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOctoberWithMaxByKey(final int id, final int october, final int _max){
        return updateOctoberWithMaxByKey(id, october, _max, TABLENAME);
    }

    public int updateOctoberWithMaxByKey(final int id, final int october, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET october = (select case when october+:october>=:_max then :_max else october+:october end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("october", october);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOctoberWithMaxInKeys(final List<Integer> keys, final int october, final int _max){
        return updateOctoberWithMaxInKeys(keys, october, _max, TABLENAME);
    }

    public int updateOctoberWithMaxInKeys(final List<Integer> keys, final int october, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET october = (select case when october+:october>=:_max then :_max else october+:october end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("october", october);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOctoberWithMinMaxByKey(final int id, final int october, final int _min, final int _max){
        return updateOctoberWithMinMaxByKey(id, october, _min, _max, TABLENAME);
    }

    public int updateOctoberWithMinMaxByKey(final int id, final int october, final int _min, final int _max, final String TABLENAME2){
        if( october < 0 ) {
            return updateOctoberWithMinByKey(id, october, _min, TABLENAME2);
        } else {
            return updateOctoberWithMaxByKey(id, october, _max, TABLENAME2);
        }
    }

    public int updateOctoberWithMinMaxInKeys(final List<Integer> keys, final int october, final int _min, final int _max){
        return updateOctoberWithMinMaxInKeys(keys, october, _min, _max, TABLENAME);
    }

    public int updateOctoberWithMinMaxInKeys(final List<Integer> keys, final int october, final int _min, final int _max, final String TABLENAME2){
        if( october < 0 ) {
            return updateOctoberWithMinInKeys(keys, october, _min, TABLENAME2);
        } else {
            return updateOctoberWithMaxInKeys(keys, october, _max, TABLENAME2);
        }
    }

    public int updateNovemberByKey(final int november, final int id){
        return updateNovemberByKey(november, id, TABLENAME);
    }

    public int updateNovemberByKey(final int november, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET november=november+:november WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("november", november);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNovemberWithMinByKey(final int id, final int november, final int _min){
        return updateNovemberWithMinByKey(id, november, _min, TABLENAME);
    }

    public int updateNovemberWithMinByKey(final int id, final int november, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET november = (select case when november+:november<=:_min then :_min else november+:november end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("november", november);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNovemberWithMinInKeys(final List<Integer> keys, final int november, final int _min){
        return updateNovemberWithMinInKeys(keys, november, _min, TABLENAME);
    }

    public int updateNovemberWithMinInKeys(final List<Integer> keys, final int november, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET november = (select case when november+:november<=:_min then :_min else november+:november end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("november", november);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNovemberWithMaxByKey(final int id, final int november, final int _max){
        return updateNovemberWithMaxByKey(id, november, _max, TABLENAME);
    }

    public int updateNovemberWithMaxByKey(final int id, final int november, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET november = (select case when november+:november>=:_max then :_max else november+:november end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("november", november);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNovemberWithMaxInKeys(final List<Integer> keys, final int november, final int _max){
        return updateNovemberWithMaxInKeys(keys, november, _max, TABLENAME);
    }

    public int updateNovemberWithMaxInKeys(final List<Integer> keys, final int november, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET november = (select case when november+:november>=:_max then :_max else november+:november end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("november", november);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNovemberWithMinMaxByKey(final int id, final int november, final int _min, final int _max){
        return updateNovemberWithMinMaxByKey(id, november, _min, _max, TABLENAME);
    }

    public int updateNovemberWithMinMaxByKey(final int id, final int november, final int _min, final int _max, final String TABLENAME2){
        if( november < 0 ) {
            return updateNovemberWithMinByKey(id, november, _min, TABLENAME2);
        } else {
            return updateNovemberWithMaxByKey(id, november, _max, TABLENAME2);
        }
    }

    public int updateNovemberWithMinMaxInKeys(final List<Integer> keys, final int november, final int _min, final int _max){
        return updateNovemberWithMinMaxInKeys(keys, november, _min, _max, TABLENAME);
    }

    public int updateNovemberWithMinMaxInKeys(final List<Integer> keys, final int november, final int _min, final int _max, final String TABLENAME2){
        if( november < 0 ) {
            return updateNovemberWithMinInKeys(keys, november, _min, TABLENAME2);
        } else {
            return updateNovemberWithMaxInKeys(keys, november, _max, TABLENAME2);
        }
    }

    public int updateDecemberByKey(final int december, final int id){
        return updateDecemberByKey(december, id, TABLENAME);
    }

    public int updateDecemberByKey(final int december, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET december=december+:december WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("december", december);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDecemberWithMinByKey(final int id, final int december, final int _min){
        return updateDecemberWithMinByKey(id, december, _min, TABLENAME);
    }

    public int updateDecemberWithMinByKey(final int id, final int december, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET december = (select case when december+:december<=:_min then :_min else december+:december end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("december", december);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDecemberWithMinInKeys(final List<Integer> keys, final int december, final int _min){
        return updateDecemberWithMinInKeys(keys, december, _min, TABLENAME);
    }

    public int updateDecemberWithMinInKeys(final List<Integer> keys, final int december, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET december = (select case when december+:december<=:_min then :_min else december+:december end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("december", december);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDecemberWithMaxByKey(final int id, final int december, final int _max){
        return updateDecemberWithMaxByKey(id, december, _max, TABLENAME);
    }

    public int updateDecemberWithMaxByKey(final int id, final int december, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET december = (select case when december+:december>=:_max then :_max else december+:december end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("december", december);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDecemberWithMaxInKeys(final List<Integer> keys, final int december, final int _max){
        return updateDecemberWithMaxInKeys(keys, december, _max, TABLENAME);
    }

    public int updateDecemberWithMaxInKeys(final List<Integer> keys, final int december, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET december = (select case when december+:december>=:_max then :_max else december+:december end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("december", december);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDecemberWithMinMaxByKey(final int id, final int december, final int _min, final int _max){
        return updateDecemberWithMinMaxByKey(id, december, _min, _max, TABLENAME);
    }

    public int updateDecemberWithMinMaxByKey(final int id, final int december, final int _min, final int _max, final String TABLENAME2){
        if( december < 0 ) {
            return updateDecemberWithMinByKey(id, december, _min, TABLENAME2);
        } else {
            return updateDecemberWithMaxByKey(id, december, _max, TABLENAME2);
        }
    }

    public int updateDecemberWithMinMaxInKeys(final List<Integer> keys, final int december, final int _min, final int _max){
        return updateDecemberWithMinMaxInKeys(keys, december, _min, _max, TABLENAME);
    }

    public int updateDecemberWithMinMaxInKeys(final List<Integer> keys, final int december, final int _min, final int _max, final String TABLENAME2){
        if( december < 0 ) {
            return updateDecemberWithMinInKeys(keys, december, _min, TABLENAME2);
        } else {
            return updateDecemberWithMaxInKeys(keys, december, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Payment_total> payment_totals) {
        return updateByKey(payment_totals, TABLENAME);
    }

    public int[] updateByKey (final List<Payment_total> payment_totals, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(payment_totals == null || payment_totals.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid=:svcid, channel=:channel, january=:january, february=:february, march=:march, april=:april, may=:may, june=:june, july=:july, august=:august, september=:september, october=:october, november=:november, december=:december WHERE id=:id");
            return super.batchUpdate2(sql.toString(), payment_totals);
        } catch(Exception e) {
            log.info(e2s(e));
            return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void createTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`svcid`  INT(11) NOT NULL," +
                "	`channel`  VARCHAR(32) NOT NULL," +
                "	`january`  INT(11) NOT NULL," +
                "	`february`  INT(11) NOT NULL," +
                "	`march`  INT(11) NOT NULL," +
                "	`april`  INT(11) NOT NULL," +
                "	`may`  INT(11) NOT NULL," +
                "	`june`  INT(11) NOT NULL," +
                "	`july`  INT(11) NOT NULL," +
                "	`august`  INT(11) NOT NULL," +
                "	`september`  INT(11) NOT NULL," +
                "	`october`  INT(11) NOT NULL," +
                "	`november`  INT(11) NOT NULL," +
                "	`december`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `svcid` (`svcid`, `channel`)" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";

            Map params = newMap();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            super.update(sql);
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public void createNoUniqueTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`svcid`  INT(11) NOT NULL," +
                "	`channel`  VARCHAR(32) NOT NULL," +
                "	`january`  INT(11) NOT NULL," +
                "	`february`  INT(11) NOT NULL," +
                "	`march`  INT(11) NOT NULL," +
                "	`april`  INT(11) NOT NULL," +
                "	`may`  INT(11) NOT NULL," +
                "	`june`  INT(11) NOT NULL," +
                "	`july`  INT(11) NOT NULL," +
                "	`august`  INT(11) NOT NULL," +
                "	`september`  INT(11) NOT NULL," +
                "	`october`  INT(11) NOT NULL," +
                "	`november`  INT(11) NOT NULL," +
                "	`december`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `svcid` (`svcid`, `channel`)" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";

            Map params = newMap();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            super.update(sql);
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public void truncate(){
        try {
            super.truncate(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void repair(){
        try {
            super.repair(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void optimize(){
        try {
            super.optimize(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

}
