package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - rank_player
@SuppressWarnings({"rawtypes", "unchecked"})
public class Rank_playerDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Rank_playerDAO.class);

    public static final String TABLE = "rank_player";
    public static String TABLENAME = "rank_player";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "ucid", "pcid", "pname", "pexp", "clancid", "clanIcon", "clanName", "currank", "renown", "weekAddRenown", "type"};
    public static String coulmns = "id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type";
    public static String coulmns2 = "ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type";

    public Rank_playerDAO(java.sql.Connection conn) {
        super(conn);
    }

    public Rank_playerDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public Rank_playerDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Rank_player rank_player) {
        return insert(rank_player, TABLENAME);
    }

    public int insert(final Rank_player rank_player, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            rank_player.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type) VALUES (:ucid, :pcid, :pname, :pexp, :clancid, :clanIcon, :clanName, :currank, :renown, :weekAddRenown, :type)");
            Map map = super.insert(sql.toString(), rank_player);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Rank_player rank_player) {
        asynchronousInsert(rank_player, TABLENAME);
    }

    public void asynchronousInsert(final Rank_player rank_player, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(rank_player, TABLENAME2);
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

    public int asynchronousInsert2(final Rank_player rank_player) {
        return asynchronousInsert2(rank_player, TABLENAME);
    }

    public int asynchronousInsert2(final Rank_player rank_player, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(rank_player, TABLENAME2);
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
        return rank_player.id;
    }

    public int insert2(final Rank_player rank_player) {
        return insert2(rank_player, TABLENAME);
    }

    public int insert2(final Rank_player rank_player, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            rank_player.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type) VALUES (:id, :ucid, :pcid, :pname, :pexp, :clancid, :clanIcon, :clanName, :currank, :renown, :weekAddRenown, :type)");
            Map map = super.insert(sql.toString(), rank_player);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Rank_player> rank_players) {
        return insert(rank_players, TABLENAME);
    }

    public int[] insert(final List<Rank_player> rank_players, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(rank_players == null || rank_players.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type) VALUES (:ucid, :pcid, :pname, :pexp, :clancid, :clanIcon, :clanName, :currank, :renown, :weekAddRenown, :type)");
            return super.batchInsert(sql.toString(), rank_players);
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

    public int deleteInBeans(final List<Rank_player> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Rank_player> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Rank_player rank_player = beans.get(i);
                int id = rank_player.id;
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

    public List<Rank_player> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Rank_player> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Rank_player.class);
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

    public List<Rank_player> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Rank_player> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Rank_player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Rank_player> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Rank_player> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Rank_player.class);
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

    public List<Rank_player> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Rank_player> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Rank_player.class);
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

    public Rank_player last() {
        return last(TABLENAME);
    }

    public Rank_player last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Rank_player.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Rank_player> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Rank_player> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Rank_player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Rank_player> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Rank_player> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Rank_player.class);
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

    public Rank_player selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Rank_player selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Rank_player.class);
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

    public Rank_player selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Rank_player selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Rank_player.class);
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

    public List<Rank_player> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Rank_player> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, pcid, pname, pexp, clancid, clanIcon, clanName, currank, renown, weekAddRenown, type FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Rank_player.class);
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

    public int updateByKey(final Rank_player rank_player) {
        return updateByKey(rank_player, TABLENAME);
    }

    public int updateByKey(final Rank_player rank_player, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = rank_player.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), rank_player);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Rank_player rank_player) {
        asynchronousUpdate(rank_player, TABLENAME);
    }

    public void asynchronousUpdate(final Rank_player rank_player, final String TABLENAME2) {
        try {

            String _ustr = rank_player.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, rank_player);
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

    public int updateUcidByKey(final int ucid, final int id){
        return updateUcidByKey(ucid, id, TABLENAME);
    }

    public int updateUcidByKey(final int ucid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid=ucid+:ucid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("ucid", ucid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateUcidWithMinByKey(final int id, final int ucid, final int _min){
        return updateUcidWithMinByKey(id, ucid, _min, TABLENAME);
    }

    public int updateUcidWithMinByKey(final int id, final int ucid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid = (select case when ucid+:ucid<=:_min then :_min else ucid+:ucid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("ucid", ucid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateUcidWithMinInKeys(final List<Integer> keys, final int ucid, final int _min){
        return updateUcidWithMinInKeys(keys, ucid, _min, TABLENAME);
    }

    public int updateUcidWithMinInKeys(final List<Integer> keys, final int ucid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid = (select case when ucid+:ucid<=:_min then :_min else ucid+:ucid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("ucid", ucid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateUcidWithMaxByKey(final int id, final int ucid, final int _max){
        return updateUcidWithMaxByKey(id, ucid, _max, TABLENAME);
    }

    public int updateUcidWithMaxByKey(final int id, final int ucid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid = (select case when ucid+:ucid>=:_max then :_max else ucid+:ucid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("ucid", ucid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateUcidWithMaxInKeys(final List<Integer> keys, final int ucid, final int _max){
        return updateUcidWithMaxInKeys(keys, ucid, _max, TABLENAME);
    }

    public int updateUcidWithMaxInKeys(final List<Integer> keys, final int ucid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid = (select case when ucid+:ucid>=:_max then :_max else ucid+:ucid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("ucid", ucid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateUcidWithMinMaxByKey(final int id, final int ucid, final int _min, final int _max){
        return updateUcidWithMinMaxByKey(id, ucid, _min, _max, TABLENAME);
    }

    public int updateUcidWithMinMaxByKey(final int id, final int ucid, final int _min, final int _max, final String TABLENAME2){
        if( ucid < 0 ) {
            return updateUcidWithMinByKey(id, ucid, _min, TABLENAME2);
        } else {
            return updateUcidWithMaxByKey(id, ucid, _max, TABLENAME2);
        }
    }

    public int updateUcidWithMinMaxInKeys(final List<Integer> keys, final int ucid, final int _min, final int _max){
        return updateUcidWithMinMaxInKeys(keys, ucid, _min, _max, TABLENAME);
    }

    public int updateUcidWithMinMaxInKeys(final List<Integer> keys, final int ucid, final int _min, final int _max, final String TABLENAME2){
        if( ucid < 0 ) {
            return updateUcidWithMinInKeys(keys, ucid, _min, TABLENAME2);
        } else {
            return updateUcidWithMaxInKeys(keys, ucid, _max, TABLENAME2);
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

    public int updatePexpByKey(final int pexp, final int id){
        return updatePexpByKey(pexp, id, TABLENAME);
    }

    public int updatePexpByKey(final int pexp, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pexp=pexp+:pexp WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("pexp", pexp);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePexpWithMinByKey(final int id, final int pexp, final int _min){
        return updatePexpWithMinByKey(id, pexp, _min, TABLENAME);
    }

    public int updatePexpWithMinByKey(final int id, final int pexp, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pexp = (select case when pexp+:pexp<=:_min then :_min else pexp+:pexp end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("pexp", pexp);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePexpWithMinInKeys(final List<Integer> keys, final int pexp, final int _min){
        return updatePexpWithMinInKeys(keys, pexp, _min, TABLENAME);
    }

    public int updatePexpWithMinInKeys(final List<Integer> keys, final int pexp, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pexp = (select case when pexp+:pexp<=:_min then :_min else pexp+:pexp end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("pexp", pexp);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePexpWithMaxByKey(final int id, final int pexp, final int _max){
        return updatePexpWithMaxByKey(id, pexp, _max, TABLENAME);
    }

    public int updatePexpWithMaxByKey(final int id, final int pexp, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pexp = (select case when pexp+:pexp>=:_max then :_max else pexp+:pexp end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("pexp", pexp);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePexpWithMaxInKeys(final List<Integer> keys, final int pexp, final int _max){
        return updatePexpWithMaxInKeys(keys, pexp, _max, TABLENAME);
    }

    public int updatePexpWithMaxInKeys(final List<Integer> keys, final int pexp, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pexp = (select case when pexp+:pexp>=:_max then :_max else pexp+:pexp end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("pexp", pexp);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePexpWithMinMaxByKey(final int id, final int pexp, final int _min, final int _max){
        return updatePexpWithMinMaxByKey(id, pexp, _min, _max, TABLENAME);
    }

    public int updatePexpWithMinMaxByKey(final int id, final int pexp, final int _min, final int _max, final String TABLENAME2){
        if( pexp < 0 ) {
            return updatePexpWithMinByKey(id, pexp, _min, TABLENAME2);
        } else {
            return updatePexpWithMaxByKey(id, pexp, _max, TABLENAME2);
        }
    }

    public int updatePexpWithMinMaxInKeys(final List<Integer> keys, final int pexp, final int _min, final int _max){
        return updatePexpWithMinMaxInKeys(keys, pexp, _min, _max, TABLENAME);
    }

    public int updatePexpWithMinMaxInKeys(final List<Integer> keys, final int pexp, final int _min, final int _max, final String TABLENAME2){
        if( pexp < 0 ) {
            return updatePexpWithMinInKeys(keys, pexp, _min, TABLENAME2);
        } else {
            return updatePexpWithMaxInKeys(keys, pexp, _max, TABLENAME2);
        }
    }

    public int updateClaniconByKey(final int clanIcon, final int id){
        return updateClaniconByKey(clanIcon, id, TABLENAME);
    }

    public int updateClaniconByKey(final int clanIcon, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET clanIcon=clanIcon+:clanIcon WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("clanIcon", clanIcon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateClaniconWithMinByKey(final int id, final int clanIcon, final int _min){
        return updateClaniconWithMinByKey(id, clanIcon, _min, TABLENAME);
    }

    public int updateClaniconWithMinByKey(final int id, final int clanIcon, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET clanIcon = (select case when clanIcon+:clanIcon<=:_min then :_min else clanIcon+:clanIcon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("clanIcon", clanIcon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateClaniconWithMinInKeys(final List<Integer> keys, final int clanIcon, final int _min){
        return updateClaniconWithMinInKeys(keys, clanIcon, _min, TABLENAME);
    }

    public int updateClaniconWithMinInKeys(final List<Integer> keys, final int clanIcon, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET clanIcon = (select case when clanIcon+:clanIcon<=:_min then :_min else clanIcon+:clanIcon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("clanIcon", clanIcon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateClaniconWithMaxByKey(final int id, final int clanIcon, final int _max){
        return updateClaniconWithMaxByKey(id, clanIcon, _max, TABLENAME);
    }

    public int updateClaniconWithMaxByKey(final int id, final int clanIcon, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET clanIcon = (select case when clanIcon+:clanIcon>=:_max then :_max else clanIcon+:clanIcon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("clanIcon", clanIcon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateClaniconWithMaxInKeys(final List<Integer> keys, final int clanIcon, final int _max){
        return updateClaniconWithMaxInKeys(keys, clanIcon, _max, TABLENAME);
    }

    public int updateClaniconWithMaxInKeys(final List<Integer> keys, final int clanIcon, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET clanIcon = (select case when clanIcon+:clanIcon>=:_max then :_max else clanIcon+:clanIcon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("clanIcon", clanIcon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateClaniconWithMinMaxByKey(final int id, final int clanIcon, final int _min, final int _max){
        return updateClaniconWithMinMaxByKey(id, clanIcon, _min, _max, TABLENAME);
    }

    public int updateClaniconWithMinMaxByKey(final int id, final int clanIcon, final int _min, final int _max, final String TABLENAME2){
        if( clanIcon < 0 ) {
            return updateClaniconWithMinByKey(id, clanIcon, _min, TABLENAME2);
        } else {
            return updateClaniconWithMaxByKey(id, clanIcon, _max, TABLENAME2);
        }
    }

    public int updateClaniconWithMinMaxInKeys(final List<Integer> keys, final int clanIcon, final int _min, final int _max){
        return updateClaniconWithMinMaxInKeys(keys, clanIcon, _min, _max, TABLENAME);
    }

    public int updateClaniconWithMinMaxInKeys(final List<Integer> keys, final int clanIcon, final int _min, final int _max, final String TABLENAME2){
        if( clanIcon < 0 ) {
            return updateClaniconWithMinInKeys(keys, clanIcon, _min, TABLENAME2);
        } else {
            return updateClaniconWithMaxInKeys(keys, clanIcon, _max, TABLENAME2);
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

    public int updateWeekaddrenownByKey(final int weekAddRenown, final int id){
        return updateWeekaddrenownByKey(weekAddRenown, id, TABLENAME);
    }

    public int updateWeekaddrenownByKey(final int weekAddRenown, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET weekAddRenown=weekAddRenown+:weekAddRenown WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("weekAddRenown", weekAddRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWeekaddrenownWithMinByKey(final int id, final int weekAddRenown, final int _min){
        return updateWeekaddrenownWithMinByKey(id, weekAddRenown, _min, TABLENAME);
    }

    public int updateWeekaddrenownWithMinByKey(final int id, final int weekAddRenown, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET weekAddRenown = (select case when weekAddRenown+:weekAddRenown<=:_min then :_min else weekAddRenown+:weekAddRenown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("weekAddRenown", weekAddRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWeekaddrenownWithMinInKeys(final List<Integer> keys, final int weekAddRenown, final int _min){
        return updateWeekaddrenownWithMinInKeys(keys, weekAddRenown, _min, TABLENAME);
    }

    public int updateWeekaddrenownWithMinInKeys(final List<Integer> keys, final int weekAddRenown, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET weekAddRenown = (select case when weekAddRenown+:weekAddRenown<=:_min then :_min else weekAddRenown+:weekAddRenown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("weekAddRenown", weekAddRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWeekaddrenownWithMaxByKey(final int id, final int weekAddRenown, final int _max){
        return updateWeekaddrenownWithMaxByKey(id, weekAddRenown, _max, TABLENAME);
    }

    public int updateWeekaddrenownWithMaxByKey(final int id, final int weekAddRenown, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET weekAddRenown = (select case when weekAddRenown+:weekAddRenown>=:_max then :_max else weekAddRenown+:weekAddRenown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("weekAddRenown", weekAddRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWeekaddrenownWithMaxInKeys(final List<Integer> keys, final int weekAddRenown, final int _max){
        return updateWeekaddrenownWithMaxInKeys(keys, weekAddRenown, _max, TABLENAME);
    }

    public int updateWeekaddrenownWithMaxInKeys(final List<Integer> keys, final int weekAddRenown, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET weekAddRenown = (select case when weekAddRenown+:weekAddRenown>=:_max then :_max else weekAddRenown+:weekAddRenown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("weekAddRenown", weekAddRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWeekaddrenownWithMinMaxByKey(final int id, final int weekAddRenown, final int _min, final int _max){
        return updateWeekaddrenownWithMinMaxByKey(id, weekAddRenown, _min, _max, TABLENAME);
    }

    public int updateWeekaddrenownWithMinMaxByKey(final int id, final int weekAddRenown, final int _min, final int _max, final String TABLENAME2){
        if( weekAddRenown < 0 ) {
            return updateWeekaddrenownWithMinByKey(id, weekAddRenown, _min, TABLENAME2);
        } else {
            return updateWeekaddrenownWithMaxByKey(id, weekAddRenown, _max, TABLENAME2);
        }
    }

    public int updateWeekaddrenownWithMinMaxInKeys(final List<Integer> keys, final int weekAddRenown, final int _min, final int _max){
        return updateWeekaddrenownWithMinMaxInKeys(keys, weekAddRenown, _min, _max, TABLENAME);
    }

    public int updateWeekaddrenownWithMinMaxInKeys(final List<Integer> keys, final int weekAddRenown, final int _min, final int _max, final String TABLENAME2){
        if( weekAddRenown < 0 ) {
            return updateWeekaddrenownWithMinInKeys(keys, weekAddRenown, _min, TABLENAME2);
        } else {
            return updateWeekaddrenownWithMaxInKeys(keys, weekAddRenown, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Rank_player> rank_players) {
        return updateByKey(rank_players, TABLENAME);
    }

    public int[] updateByKey (final List<Rank_player> rank_players, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(rank_players == null || rank_players.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid=:ucid, pcid=:pcid, pname=:pname, pexp=:pexp, clancid=:clancid, clanIcon=:clanIcon, clanName=:clanName, currank=:currank, renown=:renown, weekAddRenown=:weekAddRenown, type=:type WHERE id=:id");
            return super.batchUpdate2(sql.toString(), rank_players);
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
                "	`ucid`  INT(11) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`pexp`  INT(11) NOT NULL," +
                "	`clancid`  VARCHAR(16) NOT NULL," +
                "	`clanIcon`  INT(11) NOT NULL," +
                "	`clanName`  VARCHAR(32) NOT NULL," +
                "	`currank`  INT(11) NOT NULL," +
                "	`renown`  INT(11) NOT NULL," +
                "	`weekAddRenown`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
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
                "	`ucid`  INT(11) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`pexp`  INT(11) NOT NULL," +
                "	`clancid`  VARCHAR(16) NOT NULL," +
                "	`clanIcon`  INT(11) NOT NULL," +
                "	`clanName`  VARCHAR(32) NOT NULL," +
                "	`currank`  INT(11) NOT NULL," +
                "	`renown`  INT(11) NOT NULL," +
                "	`weekAddRenown`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
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
