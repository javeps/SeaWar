package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - rank_clan
@SuppressWarnings({"rawtypes", "unchecked"})
public class Rank_clanDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Rank_clanDAO.class);

    public static final String TABLE = "rank_clan";
    public static String TABLENAME = "rank_clan";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "ccid", "icon", "cname", "currank", "renownAll", "renownWeek", "type", "curnum", "maxnum"};
    public static String coulmns = "id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum";
    public static String coulmns2 = "ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum";

    public Rank_clanDAO(java.sql.Connection conn) {
        super(conn);
    }

    public Rank_clanDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public Rank_clanDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Rank_clan rank_clan) {
        return insert(rank_clan, TABLENAME);
    }

    public int insert(final Rank_clan rank_clan, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            rank_clan.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum) VALUES (:ccid, :icon, :cname, :currank, :renownAll, :renownWeek, :type, :curnum, :maxnum)");
            Map map = super.insert(sql.toString(), rank_clan);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Rank_clan rank_clan) {
        asynchronousInsert(rank_clan, TABLENAME);
    }

    public void asynchronousInsert(final Rank_clan rank_clan, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(rank_clan, TABLENAME2);
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

    public int asynchronousInsert2(final Rank_clan rank_clan) {
        return asynchronousInsert2(rank_clan, TABLENAME);
    }

    public int asynchronousInsert2(final Rank_clan rank_clan, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(rank_clan, TABLENAME2);
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
        return rank_clan.id;
    }

    public int insert2(final Rank_clan rank_clan) {
        return insert2(rank_clan, TABLENAME);
    }

    public int insert2(final Rank_clan rank_clan, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            rank_clan.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum) VALUES (:id, :ccid, :icon, :cname, :currank, :renownAll, :renownWeek, :type, :curnum, :maxnum)");
            Map map = super.insert(sql.toString(), rank_clan);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Rank_clan> rank_clans) {
        return insert(rank_clans, TABLENAME);
    }

    public int[] insert(final List<Rank_clan> rank_clans, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(rank_clans == null || rank_clans.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum) VALUES (:ccid, :icon, :cname, :currank, :renownAll, :renownWeek, :type, :curnum, :maxnum)");
            return super.batchInsert(sql.toString(), rank_clans);
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

    public int deleteInBeans(final List<Rank_clan> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Rank_clan> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Rank_clan rank_clan = beans.get(i);
                int id = rank_clan.id;
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

    public List<Rank_clan> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Rank_clan> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Rank_clan.class);
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

    public List<Rank_clan> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Rank_clan> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Rank_clan.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Rank_clan> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Rank_clan> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Rank_clan.class);
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

    public List<Rank_clan> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Rank_clan> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Rank_clan.class);
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

    public Rank_clan last() {
        return last(TABLENAME);
    }

    public Rank_clan last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Rank_clan.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Rank_clan> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Rank_clan> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Rank_clan.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Rank_clan> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Rank_clan> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Rank_clan.class);
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

    public Rank_clan selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Rank_clan selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Rank_clan.class);
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

    public Rank_clan selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Rank_clan selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Rank_clan.class);
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

    public List<Rank_clan> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Rank_clan> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, icon, cname, currank, renownAll, renownWeek, type, curnum, maxnum FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Rank_clan.class);
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

    public int updateByKey(final Rank_clan rank_clan) {
        return updateByKey(rank_clan, TABLENAME);
    }

    public int updateByKey(final Rank_clan rank_clan, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = rank_clan.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), rank_clan);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Rank_clan rank_clan) {
        asynchronousUpdate(rank_clan, TABLENAME);
    }

    public void asynchronousUpdate(final Rank_clan rank_clan, final String TABLENAME2) {
        try {

            String _ustr = rank_clan.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, rank_clan);
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

    public int updateIconByKey(final int icon, final int id){
        return updateIconByKey(icon, id, TABLENAME);
    }

    public int updateIconByKey(final int icon, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET icon=icon+:icon WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("icon", icon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIconWithMinByKey(final int id, final int icon, final int _min){
        return updateIconWithMinByKey(id, icon, _min, TABLENAME);
    }

    public int updateIconWithMinByKey(final int id, final int icon, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET icon = (select case when icon+:icon<=:_min then :_min else icon+:icon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("icon", icon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIconWithMinInKeys(final List<Integer> keys, final int icon, final int _min){
        return updateIconWithMinInKeys(keys, icon, _min, TABLENAME);
    }

    public int updateIconWithMinInKeys(final List<Integer> keys, final int icon, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET icon = (select case when icon+:icon<=:_min then :_min else icon+:icon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("icon", icon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIconWithMaxByKey(final int id, final int icon, final int _max){
        return updateIconWithMaxByKey(id, icon, _max, TABLENAME);
    }

    public int updateIconWithMaxByKey(final int id, final int icon, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET icon = (select case when icon+:icon>=:_max then :_max else icon+:icon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("icon", icon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIconWithMaxInKeys(final List<Integer> keys, final int icon, final int _max){
        return updateIconWithMaxInKeys(keys, icon, _max, TABLENAME);
    }

    public int updateIconWithMaxInKeys(final List<Integer> keys, final int icon, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET icon = (select case when icon+:icon>=:_max then :_max else icon+:icon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("icon", icon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIconWithMinMaxByKey(final int id, final int icon, final int _min, final int _max){
        return updateIconWithMinMaxByKey(id, icon, _min, _max, TABLENAME);
    }

    public int updateIconWithMinMaxByKey(final int id, final int icon, final int _min, final int _max, final String TABLENAME2){
        if( icon < 0 ) {
            return updateIconWithMinByKey(id, icon, _min, TABLENAME2);
        } else {
            return updateIconWithMaxByKey(id, icon, _max, TABLENAME2);
        }
    }

    public int updateIconWithMinMaxInKeys(final List<Integer> keys, final int icon, final int _min, final int _max){
        return updateIconWithMinMaxInKeys(keys, icon, _min, _max, TABLENAME);
    }

    public int updateIconWithMinMaxInKeys(final List<Integer> keys, final int icon, final int _min, final int _max, final String TABLENAME2){
        if( icon < 0 ) {
            return updateIconWithMinInKeys(keys, icon, _min, TABLENAME2);
        } else {
            return updateIconWithMaxInKeys(keys, icon, _max, TABLENAME2);
        }
    }

    public int updateCurrankByKey(final int currank, final int id){
        return updateCurrankByKey(currank, id, TABLENAME);
    }

    public int updateCurrankByKey(final int currank, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET currank=currank+:currank WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("currank", currank);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurrankWithMinByKey(final int id, final int currank, final int _min){
        return updateCurrankWithMinByKey(id, currank, _min, TABLENAME);
    }

    public int updateCurrankWithMinByKey(final int id, final int currank, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET currank = (select case when currank+:currank<=:_min then :_min else currank+:currank end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("currank", currank);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurrankWithMinInKeys(final List<Integer> keys, final int currank, final int _min){
        return updateCurrankWithMinInKeys(keys, currank, _min, TABLENAME);
    }

    public int updateCurrankWithMinInKeys(final List<Integer> keys, final int currank, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET currank = (select case when currank+:currank<=:_min then :_min else currank+:currank end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("currank", currank);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurrankWithMaxByKey(final int id, final int currank, final int _max){
        return updateCurrankWithMaxByKey(id, currank, _max, TABLENAME);
    }

    public int updateCurrankWithMaxByKey(final int id, final int currank, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET currank = (select case when currank+:currank>=:_max then :_max else currank+:currank end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("currank", currank);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurrankWithMaxInKeys(final List<Integer> keys, final int currank, final int _max){
        return updateCurrankWithMaxInKeys(keys, currank, _max, TABLENAME);
    }

    public int updateCurrankWithMaxInKeys(final List<Integer> keys, final int currank, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET currank = (select case when currank+:currank>=:_max then :_max else currank+:currank end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("currank", currank);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurrankWithMinMaxByKey(final int id, final int currank, final int _min, final int _max){
        return updateCurrankWithMinMaxByKey(id, currank, _min, _max, TABLENAME);
    }

    public int updateCurrankWithMinMaxByKey(final int id, final int currank, final int _min, final int _max, final String TABLENAME2){
        if( currank < 0 ) {
            return updateCurrankWithMinByKey(id, currank, _min, TABLENAME2);
        } else {
            return updateCurrankWithMaxByKey(id, currank, _max, TABLENAME2);
        }
    }

    public int updateCurrankWithMinMaxInKeys(final List<Integer> keys, final int currank, final int _min, final int _max){
        return updateCurrankWithMinMaxInKeys(keys, currank, _min, _max, TABLENAME);
    }

    public int updateCurrankWithMinMaxInKeys(final List<Integer> keys, final int currank, final int _min, final int _max, final String TABLENAME2){
        if( currank < 0 ) {
            return updateCurrankWithMinInKeys(keys, currank, _min, TABLENAME2);
        } else {
            return updateCurrankWithMaxInKeys(keys, currank, _max, TABLENAME2);
        }
    }

    public int updateRenownallByKey(final int renownAll, final int id){
        return updateRenownallByKey(renownAll, id, TABLENAME);
    }

    public int updateRenownallByKey(final int renownAll, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renownAll=renownAll+:renownAll WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("renownAll", renownAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownallWithMinByKey(final int id, final int renownAll, final int _min){
        return updateRenownallWithMinByKey(id, renownAll, _min, TABLENAME);
    }

    public int updateRenownallWithMinByKey(final int id, final int renownAll, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renownAll = (select case when renownAll+:renownAll<=:_min then :_min else renownAll+:renownAll end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("renownAll", renownAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownallWithMinInKeys(final List<Integer> keys, final int renownAll, final int _min){
        return updateRenownallWithMinInKeys(keys, renownAll, _min, TABLENAME);
    }

    public int updateRenownallWithMinInKeys(final List<Integer> keys, final int renownAll, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renownAll = (select case when renownAll+:renownAll<=:_min then :_min else renownAll+:renownAll end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("renownAll", renownAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownallWithMaxByKey(final int id, final int renownAll, final int _max){
        return updateRenownallWithMaxByKey(id, renownAll, _max, TABLENAME);
    }

    public int updateRenownallWithMaxByKey(final int id, final int renownAll, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renownAll = (select case when renownAll+:renownAll>=:_max then :_max else renownAll+:renownAll end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("renownAll", renownAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownallWithMaxInKeys(final List<Integer> keys, final int renownAll, final int _max){
        return updateRenownallWithMaxInKeys(keys, renownAll, _max, TABLENAME);
    }

    public int updateRenownallWithMaxInKeys(final List<Integer> keys, final int renownAll, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renownAll = (select case when renownAll+:renownAll>=:_max then :_max else renownAll+:renownAll end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("renownAll", renownAll);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownallWithMinMaxByKey(final int id, final int renownAll, final int _min, final int _max){
        return updateRenownallWithMinMaxByKey(id, renownAll, _min, _max, TABLENAME);
    }

    public int updateRenownallWithMinMaxByKey(final int id, final int renownAll, final int _min, final int _max, final String TABLENAME2){
        if( renownAll < 0 ) {
            return updateRenownallWithMinByKey(id, renownAll, _min, TABLENAME2);
        } else {
            return updateRenownallWithMaxByKey(id, renownAll, _max, TABLENAME2);
        }
    }

    public int updateRenownallWithMinMaxInKeys(final List<Integer> keys, final int renownAll, final int _min, final int _max){
        return updateRenownallWithMinMaxInKeys(keys, renownAll, _min, _max, TABLENAME);
    }

    public int updateRenownallWithMinMaxInKeys(final List<Integer> keys, final int renownAll, final int _min, final int _max, final String TABLENAME2){
        if( renownAll < 0 ) {
            return updateRenownallWithMinInKeys(keys, renownAll, _min, TABLENAME2);
        } else {
            return updateRenownallWithMaxInKeys(keys, renownAll, _max, TABLENAME2);
        }
    }

    public int updateRenownweekByKey(final int renownWeek, final int id){
        return updateRenownweekByKey(renownWeek, id, TABLENAME);
    }

    public int updateRenownweekByKey(final int renownWeek, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renownWeek=renownWeek+:renownWeek WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("renownWeek", renownWeek);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownweekWithMinByKey(final int id, final int renownWeek, final int _min){
        return updateRenownweekWithMinByKey(id, renownWeek, _min, TABLENAME);
    }

    public int updateRenownweekWithMinByKey(final int id, final int renownWeek, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renownWeek = (select case when renownWeek+:renownWeek<=:_min then :_min else renownWeek+:renownWeek end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("renownWeek", renownWeek);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownweekWithMinInKeys(final List<Integer> keys, final int renownWeek, final int _min){
        return updateRenownweekWithMinInKeys(keys, renownWeek, _min, TABLENAME);
    }

    public int updateRenownweekWithMinInKeys(final List<Integer> keys, final int renownWeek, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renownWeek = (select case when renownWeek+:renownWeek<=:_min then :_min else renownWeek+:renownWeek end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("renownWeek", renownWeek);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownweekWithMaxByKey(final int id, final int renownWeek, final int _max){
        return updateRenownweekWithMaxByKey(id, renownWeek, _max, TABLENAME);
    }

    public int updateRenownweekWithMaxByKey(final int id, final int renownWeek, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renownWeek = (select case when renownWeek+:renownWeek>=:_max then :_max else renownWeek+:renownWeek end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("renownWeek", renownWeek);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownweekWithMaxInKeys(final List<Integer> keys, final int renownWeek, final int _max){
        return updateRenownweekWithMaxInKeys(keys, renownWeek, _max, TABLENAME);
    }

    public int updateRenownweekWithMaxInKeys(final List<Integer> keys, final int renownWeek, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renownWeek = (select case when renownWeek+:renownWeek>=:_max then :_max else renownWeek+:renownWeek end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("renownWeek", renownWeek);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownweekWithMinMaxByKey(final int id, final int renownWeek, final int _min, final int _max){
        return updateRenownweekWithMinMaxByKey(id, renownWeek, _min, _max, TABLENAME);
    }

    public int updateRenownweekWithMinMaxByKey(final int id, final int renownWeek, final int _min, final int _max, final String TABLENAME2){
        if( renownWeek < 0 ) {
            return updateRenownweekWithMinByKey(id, renownWeek, _min, TABLENAME2);
        } else {
            return updateRenownweekWithMaxByKey(id, renownWeek, _max, TABLENAME2);
        }
    }

    public int updateRenownweekWithMinMaxInKeys(final List<Integer> keys, final int renownWeek, final int _min, final int _max){
        return updateRenownweekWithMinMaxInKeys(keys, renownWeek, _min, _max, TABLENAME);
    }

    public int updateRenownweekWithMinMaxInKeys(final List<Integer> keys, final int renownWeek, final int _min, final int _max, final String TABLENAME2){
        if( renownWeek < 0 ) {
            return updateRenownweekWithMinInKeys(keys, renownWeek, _min, TABLENAME2);
        } else {
            return updateRenownweekWithMaxInKeys(keys, renownWeek, _max, TABLENAME2);
        }
    }

    public int updateTypeByKey(final int type, final int id){
        return updateTypeByKey(type, id, TABLENAME);
    }

    public int updateTypeByKey(final int type, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type=type+:type WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinByKey(final int id, final int type, final int _min){
        return updateTypeWithMinByKey(id, type, _min, TABLENAME);
    }

    public int updateTypeWithMinByKey(final int id, final int type, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type<=:_min then :_min else type+:type end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinInKeys(final List<Integer> keys, final int type, final int _min){
        return updateTypeWithMinInKeys(keys, type, _min, TABLENAME);
    }

    public int updateTypeWithMinInKeys(final List<Integer> keys, final int type, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type<=:_min then :_min else type+:type end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMaxByKey(final int id, final int type, final int _max){
        return updateTypeWithMaxByKey(id, type, _max, TABLENAME);
    }

    public int updateTypeWithMaxByKey(final int id, final int type, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type>=:_max then :_max else type+:type end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMaxInKeys(final List<Integer> keys, final int type, final int _max){
        return updateTypeWithMaxInKeys(keys, type, _max, TABLENAME);
    }

    public int updateTypeWithMaxInKeys(final List<Integer> keys, final int type, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type>=:_max then :_max else type+:type end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinMaxByKey(final int id, final int type, final int _min, final int _max){
        return updateTypeWithMinMaxByKey(id, type, _min, _max, TABLENAME);
    }

    public int updateTypeWithMinMaxByKey(final int id, final int type, final int _min, final int _max, final String TABLENAME2){
        if( type < 0 ) {
            return updateTypeWithMinByKey(id, type, _min, TABLENAME2);
        } else {
            return updateTypeWithMaxByKey(id, type, _max, TABLENAME2);
        }
    }

    public int updateTypeWithMinMaxInKeys(final List<Integer> keys, final int type, final int _min, final int _max){
        return updateTypeWithMinMaxInKeys(keys, type, _min, _max, TABLENAME);
    }

    public int updateTypeWithMinMaxInKeys(final List<Integer> keys, final int type, final int _min, final int _max, final String TABLENAME2){
        if( type < 0 ) {
            return updateTypeWithMinInKeys(keys, type, _min, TABLENAME2);
        } else {
            return updateTypeWithMaxInKeys(keys, type, _max, TABLENAME2);
        }
    }

    public int updateCurnumByKey(final int curnum, final int id){
        return updateCurnumByKey(curnum, id, TABLENAME);
    }

    public int updateCurnumByKey(final int curnum, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curnum=curnum+:curnum WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("curnum", curnum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurnumWithMinByKey(final int id, final int curnum, final int _min){
        return updateCurnumWithMinByKey(id, curnum, _min, TABLENAME);
    }

    public int updateCurnumWithMinByKey(final int id, final int curnum, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curnum = (select case when curnum+:curnum<=:_min then :_min else curnum+:curnum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("curnum", curnum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurnumWithMinInKeys(final List<Integer> keys, final int curnum, final int _min){
        return updateCurnumWithMinInKeys(keys, curnum, _min, TABLENAME);
    }

    public int updateCurnumWithMinInKeys(final List<Integer> keys, final int curnum, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curnum = (select case when curnum+:curnum<=:_min then :_min else curnum+:curnum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("curnum", curnum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurnumWithMaxByKey(final int id, final int curnum, final int _max){
        return updateCurnumWithMaxByKey(id, curnum, _max, TABLENAME);
    }

    public int updateCurnumWithMaxByKey(final int id, final int curnum, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curnum = (select case when curnum+:curnum>=:_max then :_max else curnum+:curnum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("curnum", curnum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurnumWithMaxInKeys(final List<Integer> keys, final int curnum, final int _max){
        return updateCurnumWithMaxInKeys(keys, curnum, _max, TABLENAME);
    }

    public int updateCurnumWithMaxInKeys(final List<Integer> keys, final int curnum, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curnum = (select case when curnum+:curnum>=:_max then :_max else curnum+:curnum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("curnum", curnum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurnumWithMinMaxByKey(final int id, final int curnum, final int _min, final int _max){
        return updateCurnumWithMinMaxByKey(id, curnum, _min, _max, TABLENAME);
    }

    public int updateCurnumWithMinMaxByKey(final int id, final int curnum, final int _min, final int _max, final String TABLENAME2){
        if( curnum < 0 ) {
            return updateCurnumWithMinByKey(id, curnum, _min, TABLENAME2);
        } else {
            return updateCurnumWithMaxByKey(id, curnum, _max, TABLENAME2);
        }
    }

    public int updateCurnumWithMinMaxInKeys(final List<Integer> keys, final int curnum, final int _min, final int _max){
        return updateCurnumWithMinMaxInKeys(keys, curnum, _min, _max, TABLENAME);
    }

    public int updateCurnumWithMinMaxInKeys(final List<Integer> keys, final int curnum, final int _min, final int _max, final String TABLENAME2){
        if( curnum < 0 ) {
            return updateCurnumWithMinInKeys(keys, curnum, _min, TABLENAME2);
        } else {
            return updateCurnumWithMaxInKeys(keys, curnum, _max, TABLENAME2);
        }
    }

    public int updateMaxnumByKey(final int maxnum, final int id){
        return updateMaxnumByKey(maxnum, id, TABLENAME);
    }

    public int updateMaxnumByKey(final int maxnum, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxnum=maxnum+:maxnum WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("maxnum", maxnum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxnumWithMinByKey(final int id, final int maxnum, final int _min){
        return updateMaxnumWithMinByKey(id, maxnum, _min, TABLENAME);
    }

    public int updateMaxnumWithMinByKey(final int id, final int maxnum, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxnum = (select case when maxnum+:maxnum<=:_min then :_min else maxnum+:maxnum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("maxnum", maxnum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxnumWithMinInKeys(final List<Integer> keys, final int maxnum, final int _min){
        return updateMaxnumWithMinInKeys(keys, maxnum, _min, TABLENAME);
    }

    public int updateMaxnumWithMinInKeys(final List<Integer> keys, final int maxnum, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxnum = (select case when maxnum+:maxnum<=:_min then :_min else maxnum+:maxnum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("maxnum", maxnum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxnumWithMaxByKey(final int id, final int maxnum, final int _max){
        return updateMaxnumWithMaxByKey(id, maxnum, _max, TABLENAME);
    }

    public int updateMaxnumWithMaxByKey(final int id, final int maxnum, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxnum = (select case when maxnum+:maxnum>=:_max then :_max else maxnum+:maxnum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("maxnum", maxnum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxnumWithMaxInKeys(final List<Integer> keys, final int maxnum, final int _max){
        return updateMaxnumWithMaxInKeys(keys, maxnum, _max, TABLENAME);
    }

    public int updateMaxnumWithMaxInKeys(final List<Integer> keys, final int maxnum, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxnum = (select case when maxnum+:maxnum>=:_max then :_max else maxnum+:maxnum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("maxnum", maxnum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxnumWithMinMaxByKey(final int id, final int maxnum, final int _min, final int _max){
        return updateMaxnumWithMinMaxByKey(id, maxnum, _min, _max, TABLENAME);
    }

    public int updateMaxnumWithMinMaxByKey(final int id, final int maxnum, final int _min, final int _max, final String TABLENAME2){
        if( maxnum < 0 ) {
            return updateMaxnumWithMinByKey(id, maxnum, _min, TABLENAME2);
        } else {
            return updateMaxnumWithMaxByKey(id, maxnum, _max, TABLENAME2);
        }
    }

    public int updateMaxnumWithMinMaxInKeys(final List<Integer> keys, final int maxnum, final int _min, final int _max){
        return updateMaxnumWithMinMaxInKeys(keys, maxnum, _min, _max, TABLENAME);
    }

    public int updateMaxnumWithMinMaxInKeys(final List<Integer> keys, final int maxnum, final int _min, final int _max, final String TABLENAME2){
        if( maxnum < 0 ) {
            return updateMaxnumWithMinInKeys(keys, maxnum, _min, TABLENAME2);
        } else {
            return updateMaxnumWithMaxInKeys(keys, maxnum, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Rank_clan> rank_clans) {
        return updateByKey(rank_clans, TABLENAME);
    }

    public int[] updateByKey (final List<Rank_clan> rank_clans, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(rank_clans == null || rank_clans.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ccid=:ccid, icon=:icon, cname=:cname, currank=:currank, renownAll=:renownAll, renownWeek=:renownWeek, type=:type, curnum=:curnum, maxnum=:maxnum WHERE id=:id");
            return super.batchUpdate2(sql.toString(), rank_clans);
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
                "	`icon`  INT(11) NOT NULL," +
                "	`cname`  VARCHAR(32) NOT NULL," +
                "	`currank`  INT(11) NOT NULL," +
                "	`renownAll`  INT(11) NOT NULL," +
                "	`renownWeek`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`curnum`  INT(11) NOT NULL," +
                "	`maxnum`  INT(11) NOT NULL," +
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
                "	`ccid`  VARCHAR(16) NOT NULL," +
                "	`icon`  INT(11) NOT NULL," +
                "	`cname`  VARCHAR(32) NOT NULL," +
                "	`currank`  INT(11) NOT NULL," +
                "	`renownAll`  INT(11) NOT NULL," +
                "	`renownWeek`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`curnum`  INT(11) NOT NULL," +
                "	`maxnum`  INT(11) NOT NULL," +
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
