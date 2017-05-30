package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - clan_request
@SuppressWarnings({"rawtypes", "unchecked"})
public class Clan_requestDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Clan_requestDAO.class);

    public static final String TABLE = "clan_request";
    public static String TABLENAME = "clan_request";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "ccid", "pcid", "pname", "renown"};
    public static String coulmns = "id, ccid, pcid, pname, renown";
    public static String coulmns2 = "ccid, pcid, pname, renown";

    public Clan_requestDAO(java.sql.Connection conn) {
        super(conn);
    }

    public Clan_requestDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public Clan_requestDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Clan_request clan_request) {
        return insert(clan_request, TABLENAME);
    }

    public int insert(final Clan_request clan_request, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            clan_request.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ccid, pcid, pname, renown) VALUES (:ccid, :pcid, :pname, :renown)");
            Map map = super.insert(sql.toString(), clan_request);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Clan_request clan_request) {
        asynchronousInsert(clan_request, TABLENAME);
    }

    public void asynchronousInsert(final Clan_request clan_request, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(clan_request, TABLENAME2);
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

    public int asynchronousInsert2(final Clan_request clan_request) {
        return asynchronousInsert2(clan_request, TABLENAME);
    }

    public int asynchronousInsert2(final Clan_request clan_request, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(clan_request, TABLENAME2);
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
        return clan_request.id;
    }

    public int insert2(final Clan_request clan_request) {
        return insert2(clan_request, TABLENAME);
    }

    public int insert2(final Clan_request clan_request, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            clan_request.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, ccid, pcid, pname, renown) VALUES (:id, :ccid, :pcid, :pname, :renown)");
            Map map = super.insert(sql.toString(), clan_request);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Clan_request> clan_requests) {
        return insert(clan_requests, TABLENAME);
    }

    public int[] insert(final List<Clan_request> clan_requests, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(clan_requests == null || clan_requests.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ccid, pcid, pname, renown) VALUES (:ccid, :pcid, :pname, :renown)");
            return super.batchInsert(sql.toString(), clan_requests);
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

    public int deleteInBeans(final List<Clan_request> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Clan_request> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Clan_request clan_request = beans.get(i);
                int id = clan_request.id;
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

    public List<Clan_request> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Clan_request> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Clan_request.class);
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
            sql.append("SELECT id, ccid, pcid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan_request> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Clan_request> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Clan_request.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan_request> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Clan_request> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Clan_request.class);
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

    public List<Clan_request> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Clan_request> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Clan_request.class);
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

    public Clan_request last() {
        return last(TABLENAME);
    }

    public Clan_request last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Clan_request.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan_request> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Clan_request> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Clan_request.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan_request> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Clan_request> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Clan_request.class);
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

    public Clan_request selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Clan_request selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Clan_request.class);
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

    public Clan_request selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Clan_request selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Clan_request.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByPcid(final Integer pcid) {
        return countByPcid(pcid, TABLENAME);
    }

    public int countByPcid(final Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid ");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan_request> selectByPcid(final Integer pcid) {
        return selectByPcid(pcid, TABLENAME);
    }

    public List<Clan_request> selectByPcid(final Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid ORDER BY id ");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForList(sql.toString(), params, Clan_request.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByPcidPKs(final Integer pcid) {
        return selectByPcidPKs(pcid, TABLENAME);
    }

    public List<Integer> selectByPcidPKs(final Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid ORDER BY id ");
            Map params = newMap();
            params.put("pcid", pcid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan_request> selectPageByPcid(final Integer pcid, final int begin, final int num) {
        return selectPageByPcid(pcid, begin, num, TABLENAME);
    }

    public List<Clan_request> selectPageByPcid(final Integer pcid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForList(sql.toString(), params, Clan_request.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByPcidPKs(final Integer pcid, final int begin, final int num) {
        return selectPageByPcidPKs(pcid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByPcidPKs(final Integer pcid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("pcid", pcid);
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

    public Clan_request selectByCcidPcid(final String ccid, Integer pcid) {
        return selectByCcidPcid(ccid, pcid, TABLENAME);
    }

    public Clan_request selectByCcidPcid(final String ccid, Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" WHERE ccid=:ccid AND pcid=:pcid");
            Map params = newMap();
            params.put("ccid", ccid);
            params.put("pcid", pcid);
            return super.queryForObject(sql.toString(), params, Clan_request.class);
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

    public List<Clan_request> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Clan_request> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, pcid, pname, renown FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Clan_request.class);
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

    public int updateByKey(final Clan_request clan_request) {
        return updateByKey(clan_request, TABLENAME);
    }

    public int updateByKey(final Clan_request clan_request, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = clan_request.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), clan_request);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Clan_request clan_request) {
        asynchronousUpdate(clan_request, TABLENAME);
    }

    public void asynchronousUpdate(final Clan_request clan_request, final String TABLENAME2) {
        try {

            String _ustr = clan_request.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, clan_request);
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

    public int updateRenownByKey(final int renown, final int id){
        return updateRenownByKey(renown, id, TABLENAME);
    }

    public int updateRenownByKey(final int renown, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renown=renown+:renown WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("renown", renown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownWithMinByKey(final int id, final int renown, final int _min){
        return updateRenownWithMinByKey(id, renown, _min, TABLENAME);
    }

    public int updateRenownWithMinByKey(final int id, final int renown, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renown = (select case when renown+:renown<=:_min then :_min else renown+:renown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("renown", renown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownWithMinInKeys(final List<Integer> keys, final int renown, final int _min){
        return updateRenownWithMinInKeys(keys, renown, _min, TABLENAME);
    }

    public int updateRenownWithMinInKeys(final List<Integer> keys, final int renown, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renown = (select case when renown+:renown<=:_min then :_min else renown+:renown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("renown", renown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownWithMaxByKey(final int id, final int renown, final int _max){
        return updateRenownWithMaxByKey(id, renown, _max, TABLENAME);
    }

    public int updateRenownWithMaxByKey(final int id, final int renown, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renown = (select case when renown+:renown>=:_max then :_max else renown+:renown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("renown", renown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownWithMaxInKeys(final List<Integer> keys, final int renown, final int _max){
        return updateRenownWithMaxInKeys(keys, renown, _max, TABLENAME);
    }

    public int updateRenownWithMaxInKeys(final List<Integer> keys, final int renown, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renown = (select case when renown+:renown>=:_max then :_max else renown+:renown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("renown", renown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownWithMinMaxByKey(final int id, final int renown, final int _min, final int _max){
        return updateRenownWithMinMaxByKey(id, renown, _min, _max, TABLENAME);
    }

    public int updateRenownWithMinMaxByKey(final int id, final int renown, final int _min, final int _max, final String TABLENAME2){
        if( renown < 0 ) {
            return updateRenownWithMinByKey(id, renown, _min, TABLENAME2);
        } else {
            return updateRenownWithMaxByKey(id, renown, _max, TABLENAME2);
        }
    }

    public int updateRenownWithMinMaxInKeys(final List<Integer> keys, final int renown, final int _min, final int _max){
        return updateRenownWithMinMaxInKeys(keys, renown, _min, _max, TABLENAME);
    }

    public int updateRenownWithMinMaxInKeys(final List<Integer> keys, final int renown, final int _min, final int _max, final String TABLENAME2){
        if( renown < 0 ) {
            return updateRenownWithMinInKeys(keys, renown, _min, TABLENAME2);
        } else {
            return updateRenownWithMaxInKeys(keys, renown, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Clan_request> clan_requests) {
        return updateByKey(clan_requests, TABLENAME);
    }

    public int[] updateByKey (final List<Clan_request> clan_requests, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(clan_requests == null || clan_requests.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ccid=:ccid, pcid=:pcid, pname=:pname, renown=:renown WHERE id=:id");
            return super.batchUpdate2(sql.toString(), clan_requests);
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
                "	`ccid`  VARCHAR(16) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`renown`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `ccid` (`ccid`, `pcid`)," +
                "	KEY `pcid` (`pcid`)" +
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
                "	`ccid`  VARCHAR(16) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`renown`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `ccid` (`ccid`, `pcid`)," +
                "	KEY `pcid` (`pcid`)" +
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
