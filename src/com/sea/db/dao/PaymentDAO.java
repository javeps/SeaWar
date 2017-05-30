package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - payment
@SuppressWarnings({"rawtypes", "unchecked"})
public class PaymentDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(PaymentDAO.class);

    public static final String TABLE = "payment";
    public static String TABLENAME = "payment";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "unid", "chid", "svcid", "pcid", "channel", "money", "status", "gem", "createtime", "finishtime", "productUUID", "query", "phoneinfo"};
    public static String coulmns = "id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo";
    public static String coulmns2 = "unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo";

    public PaymentDAO(java.sql.Connection conn) {
        super(conn);
    }

    public PaymentDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public PaymentDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Payment payment) {
        return insert(payment, TABLENAME);
    }

    public int insert(final Payment payment, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            payment.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo) VALUES (:unid, :chid, :svcid, :pcid, :channel, :money, :status, :gem, :createtime, :finishtime, :productUUID, :query, :phoneinfo)");
            Map map = super.insert(sql.toString(), payment);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Payment payment) {
        asynchronousInsert(payment, TABLENAME);
    }

    public void asynchronousInsert(final Payment payment, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(payment, TABLENAME2);
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

    public int asynchronousInsert2(final Payment payment) {
        return asynchronousInsert2(payment, TABLENAME);
    }

    public int asynchronousInsert2(final Payment payment, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(payment, TABLENAME2);
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
        return payment.id;
    }

    public int insert2(final Payment payment) {
        return insert2(payment, TABLENAME);
    }

    public int insert2(final Payment payment, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            payment.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo) VALUES (:id, :unid, :chid, :svcid, :pcid, :channel, :money, :status, :gem, :createtime, :finishtime, :productUUID, :query, :phoneinfo)");
            Map map = super.insert(sql.toString(), payment);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Payment> payments) {
        return insert(payments, TABLENAME);
    }

    public int[] insert(final List<Payment> payments, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(payments == null || payments.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo) VALUES (:unid, :chid, :svcid, :pcid, :channel, :money, :status, :gem, :createtime, :finishtime, :productUUID, :query, :phoneinfo)");
            return super.batchInsert(sql.toString(), payments);
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

    public int deleteInBeans(final List<Payment> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Payment> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Payment payment = beans.get(i);
                int id = payment.id;
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

    public List<Payment> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Payment> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Payment.class);
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
            sql.append("SELECT id, unid, svcid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Payment> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Payment.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Payment> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Payment.class);
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

    public List<Payment> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Payment> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Payment.class);
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

    public Payment last() {
        return last(TABLENAME);
    }

    public Payment last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Payment.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Payment> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Payment.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Payment> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Payment> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Payment.class);
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

    public Payment selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Payment selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Payment.class);
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

    public Payment selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Payment selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Payment.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Payment selectByUnidSvcid(final String unid, Integer svcid) {
        return selectByUnidSvcid(unid, svcid, TABLENAME);
    }

    public Payment selectByUnidSvcid(final String unid, Integer svcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" WHERE unid=:unid AND svcid=:svcid");
            Map params = newMap();
            params.put("unid", unid);
            params.put("svcid", svcid);
            return super.queryForObject(sql.toString(), params, Payment.class);
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

    public List<Payment> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Payment> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unid, chid, svcid, pcid, channel, money, status, gem, createtime, finishtime, productUUID, query, phoneinfo FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Payment.class);
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

    public int updateByKey(final Payment payment) {
        return updateByKey(payment, TABLENAME);
    }

    public int updateByKey(final Payment payment, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = payment.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), payment);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Payment payment) {
        asynchronousUpdate(payment, TABLENAME);
    }

    public void asynchronousUpdate(final Payment payment, final String TABLENAME2) {
        try {

            String _ustr = payment.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, payment);
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

    public int updatePcidByKey(final int pcid, final int id){
        return updatePcidByKey(pcid, id, TABLENAME);
    }

    public int updatePcidByKey(final int pcid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid=pcid+:pcid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMinByKey(final int id, final int pcid, final int _min){
        return updatePcidWithMinByKey(id, pcid, _min, TABLENAME);
    }

    public int updatePcidWithMinByKey(final int id, final int pcid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid<=:_min then :_min else pcid+:pcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMinInKeys(final List<Integer> keys, final int pcid, final int _min){
        return updatePcidWithMinInKeys(keys, pcid, _min, TABLENAME);
    }

    public int updatePcidWithMinInKeys(final List<Integer> keys, final int pcid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid<=:_min then :_min else pcid+:pcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMaxByKey(final int id, final int pcid, final int _max){
        return updatePcidWithMaxByKey(id, pcid, _max, TABLENAME);
    }

    public int updatePcidWithMaxByKey(final int id, final int pcid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid>=:_max then :_max else pcid+:pcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMaxInKeys(final List<Integer> keys, final int pcid, final int _max){
        return updatePcidWithMaxInKeys(keys, pcid, _max, TABLENAME);
    }

    public int updatePcidWithMaxInKeys(final List<Integer> keys, final int pcid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid>=:_max then :_max else pcid+:pcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMinMaxByKey(final int id, final int pcid, final int _min, final int _max){
        return updatePcidWithMinMaxByKey(id, pcid, _min, _max, TABLENAME);
    }

    public int updatePcidWithMinMaxByKey(final int id, final int pcid, final int _min, final int _max, final String TABLENAME2){
        if( pcid < 0 ) {
            return updatePcidWithMinByKey(id, pcid, _min, TABLENAME2);
        } else {
            return updatePcidWithMaxByKey(id, pcid, _max, TABLENAME2);
        }
    }

    public int updatePcidWithMinMaxInKeys(final List<Integer> keys, final int pcid, final int _min, final int _max){
        return updatePcidWithMinMaxInKeys(keys, pcid, _min, _max, TABLENAME);
    }

    public int updatePcidWithMinMaxInKeys(final List<Integer> keys, final int pcid, final int _min, final int _max, final String TABLENAME2){
        if( pcid < 0 ) {
            return updatePcidWithMinInKeys(keys, pcid, _min, TABLENAME2);
        } else {
            return updatePcidWithMaxInKeys(keys, pcid, _max, TABLENAME2);
        }
    }

    public int updateMoneyByKey(final double money, final int id){
        return updateMoneyByKey(money, id, TABLENAME);
    }

    public int updateMoneyByKey(final double money, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET money=money+:money WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("money", money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyWithMinByKey(final int id, final double money, final double _min){
        return updateMoneyWithMinByKey(id, money, _min, TABLENAME);
    }

    public int updateMoneyWithMinByKey(final int id, final double money, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET money = (select case when money+:money<=:_min then :_min else money+:money end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("money", money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyWithMinInKeys(final List<Integer> keys, final double money, final double _min){
        return updateMoneyWithMinInKeys(keys, money, _min, TABLENAME);
    }

    public int updateMoneyWithMinInKeys(final List<Integer> keys, final double money, final double _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET money = (select case when money+:money<=:_min then :_min else money+:money end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("money", money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyWithMaxByKey(final int id, final double money, final double _max){
        return updateMoneyWithMaxByKey(id, money, _max, TABLENAME);
    }

    public int updateMoneyWithMaxByKey(final int id, final double money, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET money = (select case when money+:money>=:_max then :_max else money+:money end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("money", money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyWithMaxInKeys(final List<Integer> keys, final double money, final double _max){
        return updateMoneyWithMaxInKeys(keys, money, _max, TABLENAME);
    }

    public int updateMoneyWithMaxInKeys(final List<Integer> keys, final double money, final double _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET money = (select case when money+:money>=:_max then :_max else money+:money end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("money", money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyWithMinMaxByKey(final int id, final double money, final double _min, final double _max){
        return updateMoneyWithMinMaxByKey(id, money, _min, _max, TABLENAME);
    }

    public int updateMoneyWithMinMaxByKey(final int id, final double money, final double _min, final double _max, final String TABLENAME2){
        if( money < 0 ) {
            return updateMoneyWithMinByKey(id, money, _min, TABLENAME2);
        } else {
            return updateMoneyWithMaxByKey(id, money, _max, TABLENAME2);
        }
    }

    public int updateMoneyWithMinMaxInKeys(final List<Integer> keys, final double money, final double _min, final double _max){
        return updateMoneyWithMinMaxInKeys(keys, money, _min, _max, TABLENAME);
    }

    public int updateMoneyWithMinMaxInKeys(final List<Integer> keys, final double money, final double _min, final double _max, final String TABLENAME2){
        if( money < 0 ) {
            return updateMoneyWithMinInKeys(keys, money, _min, TABLENAME2);
        } else {
            return updateMoneyWithMaxInKeys(keys, money, _max, TABLENAME2);
        }
    }

    public int updateStatusByKey(final int status, final int id){
        return updateStatusByKey(status, id, TABLENAME);
    }

    public int updateStatusByKey(final int status, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status=status+:status WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinByKey(final int id, final int status, final int _min){
        return updateStatusWithMinByKey(id, status, _min, TABLENAME);
    }

    public int updateStatusWithMinByKey(final int id, final int status, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinInKeys(final List<Integer> keys, final int status, final int _min){
        return updateStatusWithMinInKeys(keys, status, _min, TABLENAME);
    }

    public int updateStatusWithMinInKeys(final List<Integer> keys, final int status, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status<=:_min then :_min else status+:status end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMaxByKey(final int id, final int status, final int _max){
        return updateStatusWithMaxByKey(id, status, _max, TABLENAME);
    }

    public int updateStatusWithMaxByKey(final int id, final int status, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMaxInKeys(final List<Integer> keys, final int status, final int _max){
        return updateStatusWithMaxInKeys(keys, status, _max, TABLENAME);
    }

    public int updateStatusWithMaxInKeys(final List<Integer> keys, final int status, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET status = (select case when status+:status>=:_max then :_max else status+:status end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("status", status);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusWithMinMaxByKey(final int id, final int status, final int _min, final int _max){
        return updateStatusWithMinMaxByKey(id, status, _min, _max, TABLENAME);
    }

    public int updateStatusWithMinMaxByKey(final int id, final int status, final int _min, final int _max, final String TABLENAME2){
        if( status < 0 ) {
            return updateStatusWithMinByKey(id, status, _min, TABLENAME2);
        } else {
            return updateStatusWithMaxByKey(id, status, _max, TABLENAME2);
        }
    }

    public int updateStatusWithMinMaxInKeys(final List<Integer> keys, final int status, final int _min, final int _max){
        return updateStatusWithMinMaxInKeys(keys, status, _min, _max, TABLENAME);
    }

    public int updateStatusWithMinMaxInKeys(final List<Integer> keys, final int status, final int _min, final int _max, final String TABLENAME2){
        if( status < 0 ) {
            return updateStatusWithMinInKeys(keys, status, _min, TABLENAME2);
        } else {
            return updateStatusWithMaxInKeys(keys, status, _max, TABLENAME2);
        }
    }

    public int updateGemByKey(final int gem, final int id){
        return updateGemByKey(gem, id, TABLENAME);
    }

    public int updateGemByKey(final int gem, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gem=gem+:gem WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("gem", gem);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGemWithMinByKey(final int id, final int gem, final int _min){
        return updateGemWithMinByKey(id, gem, _min, TABLENAME);
    }

    public int updateGemWithMinByKey(final int id, final int gem, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gem = (select case when gem+:gem<=:_min then :_min else gem+:gem end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("gem", gem);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGemWithMinInKeys(final List<Integer> keys, final int gem, final int _min){
        return updateGemWithMinInKeys(keys, gem, _min, TABLENAME);
    }

    public int updateGemWithMinInKeys(final List<Integer> keys, final int gem, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gem = (select case when gem+:gem<=:_min then :_min else gem+:gem end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("gem", gem);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGemWithMaxByKey(final int id, final int gem, final int _max){
        return updateGemWithMaxByKey(id, gem, _max, TABLENAME);
    }

    public int updateGemWithMaxByKey(final int id, final int gem, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gem = (select case when gem+:gem>=:_max then :_max else gem+:gem end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("gem", gem);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGemWithMaxInKeys(final List<Integer> keys, final int gem, final int _max){
        return updateGemWithMaxInKeys(keys, gem, _max, TABLENAME);
    }

    public int updateGemWithMaxInKeys(final List<Integer> keys, final int gem, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gem = (select case when gem+:gem>=:_max then :_max else gem+:gem end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("gem", gem);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGemWithMinMaxByKey(final int id, final int gem, final int _min, final int _max){
        return updateGemWithMinMaxByKey(id, gem, _min, _max, TABLENAME);
    }

    public int updateGemWithMinMaxByKey(final int id, final int gem, final int _min, final int _max, final String TABLENAME2){
        if( gem < 0 ) {
            return updateGemWithMinByKey(id, gem, _min, TABLENAME2);
        } else {
            return updateGemWithMaxByKey(id, gem, _max, TABLENAME2);
        }
    }

    public int updateGemWithMinMaxInKeys(final List<Integer> keys, final int gem, final int _min, final int _max){
        return updateGemWithMinMaxInKeys(keys, gem, _min, _max, TABLENAME);
    }

    public int updateGemWithMinMaxInKeys(final List<Integer> keys, final int gem, final int _min, final int _max, final String TABLENAME2){
        if( gem < 0 ) {
            return updateGemWithMinInKeys(keys, gem, _min, TABLENAME2);
        } else {
            return updateGemWithMaxInKeys(keys, gem, _max, TABLENAME2);
        }
    }

    public int updateFinishtimeByKey(final long finishtime, final int id){
        return updateFinishtimeByKey(finishtime, id, TABLENAME);
    }

    public int updateFinishtimeByKey(final long finishtime, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET finishtime=finishtime+:finishtime WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("finishtime", finishtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFinishtimeWithMinByKey(final int id, final long finishtime, final long _min){
        return updateFinishtimeWithMinByKey(id, finishtime, _min, TABLENAME);
    }

    public int updateFinishtimeWithMinByKey(final int id, final long finishtime, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET finishtime = (select case when finishtime+:finishtime<=:_min then :_min else finishtime+:finishtime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("finishtime", finishtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFinishtimeWithMinInKeys(final List<Integer> keys, final long finishtime, final long _min){
        return updateFinishtimeWithMinInKeys(keys, finishtime, _min, TABLENAME);
    }

    public int updateFinishtimeWithMinInKeys(final List<Integer> keys, final long finishtime, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET finishtime = (select case when finishtime+:finishtime<=:_min then :_min else finishtime+:finishtime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("finishtime", finishtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFinishtimeWithMaxByKey(final int id, final long finishtime, final long _max){
        return updateFinishtimeWithMaxByKey(id, finishtime, _max, TABLENAME);
    }

    public int updateFinishtimeWithMaxByKey(final int id, final long finishtime, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET finishtime = (select case when finishtime+:finishtime>=:_max then :_max else finishtime+:finishtime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("finishtime", finishtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFinishtimeWithMaxInKeys(final List<Integer> keys, final long finishtime, final long _max){
        return updateFinishtimeWithMaxInKeys(keys, finishtime, _max, TABLENAME);
    }

    public int updateFinishtimeWithMaxInKeys(final List<Integer> keys, final long finishtime, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET finishtime = (select case when finishtime+:finishtime>=:_max then :_max else finishtime+:finishtime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("finishtime", finishtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFinishtimeWithMinMaxByKey(final int id, final long finishtime, final long _min, final long _max){
        return updateFinishtimeWithMinMaxByKey(id, finishtime, _min, _max, TABLENAME);
    }

    public int updateFinishtimeWithMinMaxByKey(final int id, final long finishtime, final long _min, final long _max, final String TABLENAME2){
        if( finishtime < 0 ) {
            return updateFinishtimeWithMinByKey(id, finishtime, _min, TABLENAME2);
        } else {
            return updateFinishtimeWithMaxByKey(id, finishtime, _max, TABLENAME2);
        }
    }

    public int updateFinishtimeWithMinMaxInKeys(final List<Integer> keys, final long finishtime, final long _min, final long _max){
        return updateFinishtimeWithMinMaxInKeys(keys, finishtime, _min, _max, TABLENAME);
    }

    public int updateFinishtimeWithMinMaxInKeys(final List<Integer> keys, final long finishtime, final long _min, final long _max, final String TABLENAME2){
        if( finishtime < 0 ) {
            return updateFinishtimeWithMinInKeys(keys, finishtime, _min, TABLENAME2);
        } else {
            return updateFinishtimeWithMaxInKeys(keys, finishtime, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Payment> payments) {
        return updateByKey(payments, TABLENAME);
    }

    public int[] updateByKey (final List<Payment> payments, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(payments == null || payments.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET unid=:unid, chid=:chid, svcid=:svcid, pcid=:pcid, channel=:channel, money=:money, status=:status, gem=:gem, createtime=:createtime, finishtime=:finishtime, productUUID=:productUUID, query=:query, phoneinfo=:phoneinfo WHERE id=:id");
            return super.batchUpdate2(sql.toString(), payments);
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
                "	`unid`  VARCHAR(64) NOT NULL," +
                "	`chid`  VARCHAR(64) NOT NULL," +
                "	`svcid`  INT(11) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`channel`  VARCHAR(32) NOT NULL," +
                "	`money`  DOUBLE NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`gem`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`finishtime`  BIGINT(20) NOT NULL," +
                "	`productUUID`  VARCHAR(32) NOT NULL," +
                "	`query`  TEXT NOT NULL," +
                "	`phoneinfo`  TEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `unid` (`unid`, `svcid`)" +
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
                "	`unid`  VARCHAR(64) NOT NULL," +
                "	`chid`  VARCHAR(64) NOT NULL," +
                "	`svcid`  INT(11) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`channel`  VARCHAR(32) NOT NULL," +
                "	`money`  DOUBLE NOT NULL," +
                "	`status`  INT(11) NOT NULL," +
                "	`gem`  INT(11) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`finishtime`  BIGINT(20) NOT NULL," +
                "	`productUUID`  VARCHAR(32) NOT NULL," +
                "	`query`  TEXT NOT NULL," +
                "	`phoneinfo`  TEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `unid` (`unid`, `svcid`)" +
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
