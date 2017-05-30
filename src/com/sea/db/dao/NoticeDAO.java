package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - notice
@SuppressWarnings({"rawtypes", "unchecked"})
public class NoticeDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(NoticeDAO.class);

    public static final String TABLE = "notice";
    public static String TABLENAME = "notice";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "svid", "content", "begintime", "endtime"};
    public static String coulmns = "id, svid, content, begintime, endtime";
    public static String coulmns2 = "svid, content, begintime, endtime";

    public NoticeDAO(java.sql.Connection conn) {
        super(conn);
    }

    public NoticeDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public NoticeDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Notice notice) {
        return insert(notice, TABLENAME);
    }

    public int insert(final Notice notice, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            notice.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (svid, content, begintime, endtime) VALUES (:svid, :content, :begintime, :endtime)");
            Map map = super.insert(sql.toString(), notice);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Notice notice) {
        asynchronousInsert(notice, TABLENAME);
    }

    public void asynchronousInsert(final Notice notice, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(notice, TABLENAME2);
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

    public int asynchronousInsert2(final Notice notice) {
        return asynchronousInsert2(notice, TABLENAME);
    }

    public int asynchronousInsert2(final Notice notice, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(notice, TABLENAME2);
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
        return notice.id;
    }

    public int insert2(final Notice notice) {
        return insert2(notice, TABLENAME);
    }

    public int insert2(final Notice notice, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            notice.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, svid, content, begintime, endtime) VALUES (:id, :svid, :content, :begintime, :endtime)");
            Map map = super.insert(sql.toString(), notice);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Notice> notices) {
        return insert(notices, TABLENAME);
    }

    public int[] insert(final List<Notice> notices, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(notices == null || notices.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (svid, content, begintime, endtime) VALUES (:svid, :content, :begintime, :endtime)");
            return super.batchInsert(sql.toString(), notices);
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

    public int deleteInBeans(final List<Notice> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Notice> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Notice notice = beans.get(i);
                int id = notice.id;
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

    public List<Notice> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Notice> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svid, content, begintime, endtime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Notice.class);
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
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Notice> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Notice> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, svid, content, begintime, endtime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Notice.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Notice> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Notice> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, svid, content, begintime, endtime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Notice.class);
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

    public List<Notice> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Notice> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svid, content, begintime, endtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Notice.class);
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

    public Notice last() {
        return last(TABLENAME);
    }

    public Notice last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svid, content, begintime, endtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Notice.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Notice> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Notice> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svid, content, begintime, endtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Notice.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Notice> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Notice> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svid, content, begintime, endtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Notice.class);
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

    public Notice selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Notice selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svid, content, begintime, endtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Notice.class);
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

    public Notice selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Notice selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svid, content, begintime, endtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Notice.class);
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

    public List<Notice> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Notice> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, svid, content, begintime, endtime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Notice.class);
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

    public int updateByKey(final Notice notice) {
        return updateByKey(notice, TABLENAME);
    }

    public int updateByKey(final Notice notice, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = notice.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), notice);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Notice notice) {
        asynchronousUpdate(notice, TABLENAME);
    }

    public void asynchronousUpdate(final Notice notice, final String TABLENAME2) {
        try {

            String _ustr = notice.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, notice);
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

    public int updateSvidByKey(final int svid, final int id){
        return updateSvidByKey(svid, id, TABLENAME);
    }

    public int updateSvidByKey(final int svid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svid=svid+:svid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("svid", svid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvidWithMinByKey(final int id, final int svid, final int _min){
        return updateSvidWithMinByKey(id, svid, _min, TABLENAME);
    }

    public int updateSvidWithMinByKey(final int id, final int svid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svid = (select case when svid+:svid<=:_min then :_min else svid+:svid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("svid", svid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvidWithMinInKeys(final List<Integer> keys, final int svid, final int _min){
        return updateSvidWithMinInKeys(keys, svid, _min, TABLENAME);
    }

    public int updateSvidWithMinInKeys(final List<Integer> keys, final int svid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svid = (select case when svid+:svid<=:_min then :_min else svid+:svid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("svid", svid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvidWithMaxByKey(final int id, final int svid, final int _max){
        return updateSvidWithMaxByKey(id, svid, _max, TABLENAME);
    }

    public int updateSvidWithMaxByKey(final int id, final int svid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svid = (select case when svid+:svid>=:_max then :_max else svid+:svid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("svid", svid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvidWithMaxInKeys(final List<Integer> keys, final int svid, final int _max){
        return updateSvidWithMaxInKeys(keys, svid, _max, TABLENAME);
    }

    public int updateSvidWithMaxInKeys(final List<Integer> keys, final int svid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svid = (select case when svid+:svid>=:_max then :_max else svid+:svid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("svid", svid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvidWithMinMaxByKey(final int id, final int svid, final int _min, final int _max){
        return updateSvidWithMinMaxByKey(id, svid, _min, _max, TABLENAME);
    }

    public int updateSvidWithMinMaxByKey(final int id, final int svid, final int _min, final int _max, final String TABLENAME2){
        if( svid < 0 ) {
            return updateSvidWithMinByKey(id, svid, _min, TABLENAME2);
        } else {
            return updateSvidWithMaxByKey(id, svid, _max, TABLENAME2);
        }
    }

    public int updateSvidWithMinMaxInKeys(final List<Integer> keys, final int svid, final int _min, final int _max){
        return updateSvidWithMinMaxInKeys(keys, svid, _min, _max, TABLENAME);
    }

    public int updateSvidWithMinMaxInKeys(final List<Integer> keys, final int svid, final int _min, final int _max, final String TABLENAME2){
        if( svid < 0 ) {
            return updateSvidWithMinInKeys(keys, svid, _min, TABLENAME2);
        } else {
            return updateSvidWithMaxInKeys(keys, svid, _max, TABLENAME2);
        }
    }

    public int updateBegintimeByKey(final long begintime, final int id){
        return updateBegintimeByKey(begintime, id, TABLENAME);
    }

    public int updateBegintimeByKey(final long begintime, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begintime=begintime+:begintime WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("begintime", begintime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegintimeWithMinByKey(final int id, final long begintime, final long _min){
        return updateBegintimeWithMinByKey(id, begintime, _min, TABLENAME);
    }

    public int updateBegintimeWithMinByKey(final int id, final long begintime, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begintime = (select case when begintime+:begintime<=:_min then :_min else begintime+:begintime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("begintime", begintime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegintimeWithMinInKeys(final List<Integer> keys, final long begintime, final long _min){
        return updateBegintimeWithMinInKeys(keys, begintime, _min, TABLENAME);
    }

    public int updateBegintimeWithMinInKeys(final List<Integer> keys, final long begintime, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begintime = (select case when begintime+:begintime<=:_min then :_min else begintime+:begintime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("begintime", begintime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegintimeWithMaxByKey(final int id, final long begintime, final long _max){
        return updateBegintimeWithMaxByKey(id, begintime, _max, TABLENAME);
    }

    public int updateBegintimeWithMaxByKey(final int id, final long begintime, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begintime = (select case when begintime+:begintime>=:_max then :_max else begintime+:begintime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("begintime", begintime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegintimeWithMaxInKeys(final List<Integer> keys, final long begintime, final long _max){
        return updateBegintimeWithMaxInKeys(keys, begintime, _max, TABLENAME);
    }

    public int updateBegintimeWithMaxInKeys(final List<Integer> keys, final long begintime, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begintime = (select case when begintime+:begintime>=:_max then :_max else begintime+:begintime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("begintime", begintime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegintimeWithMinMaxByKey(final int id, final long begintime, final long _min, final long _max){
        return updateBegintimeWithMinMaxByKey(id, begintime, _min, _max, TABLENAME);
    }

    public int updateBegintimeWithMinMaxByKey(final int id, final long begintime, final long _min, final long _max, final String TABLENAME2){
        if( begintime < 0 ) {
            return updateBegintimeWithMinByKey(id, begintime, _min, TABLENAME2);
        } else {
            return updateBegintimeWithMaxByKey(id, begintime, _max, TABLENAME2);
        }
    }

    public int updateBegintimeWithMinMaxInKeys(final List<Integer> keys, final long begintime, final long _min, final long _max){
        return updateBegintimeWithMinMaxInKeys(keys, begintime, _min, _max, TABLENAME);
    }

    public int updateBegintimeWithMinMaxInKeys(final List<Integer> keys, final long begintime, final long _min, final long _max, final String TABLENAME2){
        if( begintime < 0 ) {
            return updateBegintimeWithMinInKeys(keys, begintime, _min, TABLENAME2);
        } else {
            return updateBegintimeWithMaxInKeys(keys, begintime, _max, TABLENAME2);
        }
    }

    public int updateEndtimeByKey(final long endtime, final int id){
        return updateEndtimeByKey(endtime, id, TABLENAME);
    }

    public int updateEndtimeByKey(final long endtime, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET endtime=endtime+:endtime WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("endtime", endtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEndtimeWithMinByKey(final int id, final long endtime, final long _min){
        return updateEndtimeWithMinByKey(id, endtime, _min, TABLENAME);
    }

    public int updateEndtimeWithMinByKey(final int id, final long endtime, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET endtime = (select case when endtime+:endtime<=:_min then :_min else endtime+:endtime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("endtime", endtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEndtimeWithMinInKeys(final List<Integer> keys, final long endtime, final long _min){
        return updateEndtimeWithMinInKeys(keys, endtime, _min, TABLENAME);
    }

    public int updateEndtimeWithMinInKeys(final List<Integer> keys, final long endtime, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET endtime = (select case when endtime+:endtime<=:_min then :_min else endtime+:endtime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("endtime", endtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEndtimeWithMaxByKey(final int id, final long endtime, final long _max){
        return updateEndtimeWithMaxByKey(id, endtime, _max, TABLENAME);
    }

    public int updateEndtimeWithMaxByKey(final int id, final long endtime, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET endtime = (select case when endtime+:endtime>=:_max then :_max else endtime+:endtime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("endtime", endtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEndtimeWithMaxInKeys(final List<Integer> keys, final long endtime, final long _max){
        return updateEndtimeWithMaxInKeys(keys, endtime, _max, TABLENAME);
    }

    public int updateEndtimeWithMaxInKeys(final List<Integer> keys, final long endtime, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET endtime = (select case when endtime+:endtime>=:_max then :_max else endtime+:endtime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("endtime", endtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEndtimeWithMinMaxByKey(final int id, final long endtime, final long _min, final long _max){
        return updateEndtimeWithMinMaxByKey(id, endtime, _min, _max, TABLENAME);
    }

    public int updateEndtimeWithMinMaxByKey(final int id, final long endtime, final long _min, final long _max, final String TABLENAME2){
        if( endtime < 0 ) {
            return updateEndtimeWithMinByKey(id, endtime, _min, TABLENAME2);
        } else {
            return updateEndtimeWithMaxByKey(id, endtime, _max, TABLENAME2);
        }
    }

    public int updateEndtimeWithMinMaxInKeys(final List<Integer> keys, final long endtime, final long _min, final long _max){
        return updateEndtimeWithMinMaxInKeys(keys, endtime, _min, _max, TABLENAME);
    }

    public int updateEndtimeWithMinMaxInKeys(final List<Integer> keys, final long endtime, final long _min, final long _max, final String TABLENAME2){
        if( endtime < 0 ) {
            return updateEndtimeWithMinInKeys(keys, endtime, _min, TABLENAME2);
        } else {
            return updateEndtimeWithMaxInKeys(keys, endtime, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Notice> notices) {
        return updateByKey(notices, TABLENAME);
    }

    public int[] updateByKey (final List<Notice> notices, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(notices == null || notices.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svid=:svid, content=:content, begintime=:begintime, endtime=:endtime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), notices);
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
                "	`svid`  INT(11) NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`begintime`  BIGINT(20) NOT NULL," +
                "	`endtime`  BIGINT(20) NOT NULL," +
                "	PRIMARY KEY (`id`)" +
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
                "	`svid`  INT(11) NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`begintime`  BIGINT(20) NOT NULL," +
                "	`endtime`  BIGINT(20) NOT NULL," +
                "	PRIMARY KEY (`id`)" +
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
