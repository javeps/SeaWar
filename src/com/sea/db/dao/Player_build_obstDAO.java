package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - player_build_obst
@SuppressWarnings({"rawtypes", "unchecked"})
public class Player_build_obstDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Player_build_obstDAO.class);

    public static final String TABLE = "player_build_obst";
    public static String TABLENAME = "player_build_obst";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "bcid", "building_name", "pcid", "player_name", "gid", "lvl", "cooldown_ms", "position_index", "state", "type"};
    public static String coulmns = "id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type";
    public static String coulmns2 = "bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type";

    public Player_build_obstDAO(java.sql.Connection conn) {
        super(conn);
    }

    public Player_build_obstDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public Player_build_obstDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Player_build_obst player_build_obst) {
        return insert(player_build_obst, TABLENAME);
    }

    public int insert(final Player_build_obst player_build_obst, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            player_build_obst.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type) VALUES (:bcid, :building_name, :pcid, :player_name, :gid, :lvl, :cooldown_ms, :position_index, :state, :type)");
            Map map = super.insert(sql.toString(), player_build_obst);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Player_build_obst player_build_obst) {
        asynchronousInsert(player_build_obst, TABLENAME);
    }

    public void asynchronousInsert(final Player_build_obst player_build_obst, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(player_build_obst, TABLENAME2);
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

    public int asynchronousInsert2(final Player_build_obst player_build_obst) {
        return asynchronousInsert2(player_build_obst, TABLENAME);
    }

    public int asynchronousInsert2(final Player_build_obst player_build_obst, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(player_build_obst, TABLENAME2);
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
        return player_build_obst.id;
    }

    public int insert2(final Player_build_obst player_build_obst) {
        return insert2(player_build_obst, TABLENAME);
    }

    public int insert2(final Player_build_obst player_build_obst, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            player_build_obst.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type) VALUES (:id, :bcid, :building_name, :pcid, :player_name, :gid, :lvl, :cooldown_ms, :position_index, :state, :type)");
            Map map = super.insert(sql.toString(), player_build_obst);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Player_build_obst> player_build_obsts) {
        return insert(player_build_obsts, TABLENAME);
    }

    public int[] insert(final List<Player_build_obst> player_build_obsts, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(player_build_obsts == null || player_build_obsts.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type) VALUES (:bcid, :building_name, :pcid, :player_name, :gid, :lvl, :cooldown_ms, :position_index, :state, :type)");
            return super.batchInsert(sql.toString(), player_build_obsts);
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

    public int deleteInBeans(final List<Player_build_obst> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Player_build_obst> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Player_build_obst player_build_obst = beans.get(i);
                int id = player_build_obst.id;
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

    public List<Player_build_obst> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Player_build_obst> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Player_build_obst.class);
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
            sql.append("SELECT id, bcid, pcid, gid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_build_obst> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Player_build_obst> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Player_build_obst.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_build_obst> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Player_build_obst> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Player_build_obst.class);
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

    public List<Player_build_obst> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Player_build_obst> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Player_build_obst.class);
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

    public Player_build_obst last() {
        return last(TABLENAME);
    }

    public Player_build_obst last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Player_build_obst.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_build_obst> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Player_build_obst> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Player_build_obst.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_build_obst> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Player_build_obst> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Player_build_obst.class);
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

    public Player_build_obst selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Player_build_obst selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Player_build_obst.class);
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

    public Player_build_obst selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Player_build_obst selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Player_build_obst.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByPcidGid(final Integer pcid, Integer gid) {
        return  countByPcidGid(pcid, gid, TABLENAME);
    }

    public int countByPcidGid(final Integer pcid, Integer gid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE pcid=:pcid AND gid=:gid ");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("gid", gid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_build_obst> selectByPcidGid(final Integer pcid, Integer gid) {
        return selectByPcidGid(pcid, gid, TABLENAME);
    }

    public List<Player_build_obst> selectByPcidGid(final Integer pcid, Integer gid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE pcid=:pcid AND gid=:gid ORDER BY id ");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("gid", gid);
            return super.queryForList(sql.toString(), params, Player_build_obst.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByPcidGidPKs(final Integer pcid, Integer gid) {
        return selectByPcidGidPKs(pcid, gid, TABLENAME);
    }

    public List<Integer> selectByPcidGidPKs(final Integer pcid, Integer gid, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE pcid=:pcid AND gid=:gid ORDER BY id ");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("gid", gid);
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

    public List<Player_build_obst> selectPageByPcidGid(final Integer pcid, Integer gid, final int begin, final int num) {
        return selectPageByPcidGid(pcid, gid, begin, num, TABLENAME);
    }

    public List<Player_build_obst> selectPageByPcidGid(final Integer pcid, Integer gid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE pcid=:pcid AND gid=:gid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("gid", gid);
            return super.queryForList(sql.toString(), params, Player_build_obst.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByPcidGidPKs(final Integer pcid, Integer gid, final int begin, final int num) {
        return selectPageByPcidGidPKs(pcid, gid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByPcidGidPKs(final Integer pcid, Integer gid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE pcid=:pcid AND gid=:gid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("gid", gid);
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

    public List<Player_build_obst> selectByPcid(final Integer pcid) {
        return selectByPcid(pcid, TABLENAME);
    }

    public List<Player_build_obst> selectByPcid(final Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid ORDER BY id ");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForList(sql.toString(), params, Player_build_obst.class);
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

    public List<Player_build_obst> selectPageByPcid(final Integer pcid, final int begin, final int num) {
        return selectPageByPcid(pcid, begin, num, TABLENAME);
    }

    public List<Player_build_obst> selectPageByPcid(final Integer pcid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForList(sql.toString(), params, Player_build_obst.class);
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

    public Player_build_obst selectByBcidPcid(final Integer bcid, Integer pcid) {
        return selectByBcidPcid(bcid, pcid, TABLENAME);
    }

    public Player_build_obst selectByBcidPcid(final Integer bcid, Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" WHERE bcid=:bcid AND pcid=:pcid");
            Map params = newMap();
            params.put("bcid", bcid);
            params.put("pcid", pcid);
            return super.queryForObject(sql.toString(), params, Player_build_obst.class);
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

    public List<Player_build_obst> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Player_build_obst> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Player_build_obst.class);
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

    public int updateByKey(final Player_build_obst player_build_obst) {
        return updateByKey(player_build_obst, TABLENAME);
    }

    public int updateByKey(final Player_build_obst player_build_obst, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = player_build_obst.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), player_build_obst);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Player_build_obst player_build_obst) {
        asynchronousUpdate(player_build_obst, TABLENAME);
    }

    public void asynchronousUpdate(final Player_build_obst player_build_obst, final String TABLENAME2) {
        try {

            String _ustr = player_build_obst.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, player_build_obst);
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

    public int updateBcidByKey(final int bcid, final int id){
        return updateBcidByKey(bcid, id, TABLENAME);
    }

    public int updateBcidByKey(final int bcid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bcid=bcid+:bcid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("bcid", bcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBcidWithMinByKey(final int id, final int bcid, final int _min){
        return updateBcidWithMinByKey(id, bcid, _min, TABLENAME);
    }

    public int updateBcidWithMinByKey(final int id, final int bcid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bcid = (select case when bcid+:bcid<=:_min then :_min else bcid+:bcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("bcid", bcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBcidWithMinInKeys(final List<Integer> keys, final int bcid, final int _min){
        return updateBcidWithMinInKeys(keys, bcid, _min, TABLENAME);
    }

    public int updateBcidWithMinInKeys(final List<Integer> keys, final int bcid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bcid = (select case when bcid+:bcid<=:_min then :_min else bcid+:bcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("bcid", bcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBcidWithMaxByKey(final int id, final int bcid, final int _max){
        return updateBcidWithMaxByKey(id, bcid, _max, TABLENAME);
    }

    public int updateBcidWithMaxByKey(final int id, final int bcid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bcid = (select case when bcid+:bcid>=:_max then :_max else bcid+:bcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("bcid", bcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBcidWithMaxInKeys(final List<Integer> keys, final int bcid, final int _max){
        return updateBcidWithMaxInKeys(keys, bcid, _max, TABLENAME);
    }

    public int updateBcidWithMaxInKeys(final List<Integer> keys, final int bcid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bcid = (select case when bcid+:bcid>=:_max then :_max else bcid+:bcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("bcid", bcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBcidWithMinMaxByKey(final int id, final int bcid, final int _min, final int _max){
        return updateBcidWithMinMaxByKey(id, bcid, _min, _max, TABLENAME);
    }

    public int updateBcidWithMinMaxByKey(final int id, final int bcid, final int _min, final int _max, final String TABLENAME2){
        if( bcid < 0 ) {
            return updateBcidWithMinByKey(id, bcid, _min, TABLENAME2);
        } else {
            return updateBcidWithMaxByKey(id, bcid, _max, TABLENAME2);
        }
    }

    public int updateBcidWithMinMaxInKeys(final List<Integer> keys, final int bcid, final int _min, final int _max){
        return updateBcidWithMinMaxInKeys(keys, bcid, _min, _max, TABLENAME);
    }

    public int updateBcidWithMinMaxInKeys(final List<Integer> keys, final int bcid, final int _min, final int _max, final String TABLENAME2){
        if( bcid < 0 ) {
            return updateBcidWithMinInKeys(keys, bcid, _min, TABLENAME2);
        } else {
            return updateBcidWithMaxInKeys(keys, bcid, _max, TABLENAME2);
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

    public int updateGidByKey(final int gid, final int id){
        return updateGidByKey(gid, id, TABLENAME);
    }

    public int updateGidByKey(final int gid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid=gid+:gid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMinByKey(final int id, final int gid, final int _min){
        return updateGidWithMinByKey(id, gid, _min, TABLENAME);
    }

    public int updateGidWithMinByKey(final int id, final int gid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid<=:_min then :_min else gid+:gid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMinInKeys(final List<Integer> keys, final int gid, final int _min){
        return updateGidWithMinInKeys(keys, gid, _min, TABLENAME);
    }

    public int updateGidWithMinInKeys(final List<Integer> keys, final int gid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid<=:_min then :_min else gid+:gid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMaxByKey(final int id, final int gid, final int _max){
        return updateGidWithMaxByKey(id, gid, _max, TABLENAME);
    }

    public int updateGidWithMaxByKey(final int id, final int gid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid>=:_max then :_max else gid+:gid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMaxInKeys(final List<Integer> keys, final int gid, final int _max){
        return updateGidWithMaxInKeys(keys, gid, _max, TABLENAME);
    }

    public int updateGidWithMaxInKeys(final List<Integer> keys, final int gid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET gid = (select case when gid+:gid>=:_max then :_max else gid+:gid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("gid", gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGidWithMinMaxByKey(final int id, final int gid, final int _min, final int _max){
        return updateGidWithMinMaxByKey(id, gid, _min, _max, TABLENAME);
    }

    public int updateGidWithMinMaxByKey(final int id, final int gid, final int _min, final int _max, final String TABLENAME2){
        if( gid < 0 ) {
            return updateGidWithMinByKey(id, gid, _min, TABLENAME2);
        } else {
            return updateGidWithMaxByKey(id, gid, _max, TABLENAME2);
        }
    }

    public int updateGidWithMinMaxInKeys(final List<Integer> keys, final int gid, final int _min, final int _max){
        return updateGidWithMinMaxInKeys(keys, gid, _min, _max, TABLENAME);
    }

    public int updateGidWithMinMaxInKeys(final List<Integer> keys, final int gid, final int _min, final int _max, final String TABLENAME2){
        if( gid < 0 ) {
            return updateGidWithMinInKeys(keys, gid, _min, TABLENAME2);
        } else {
            return updateGidWithMaxInKeys(keys, gid, _max, TABLENAME2);
        }
    }

    public int updateLvlByKey(final int lvl, final int id){
        return updateLvlByKey(lvl, id, TABLENAME);
    }

    public int updateLvlByKey(final int lvl, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lvl=lvl+:lvl WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lvl", lvl);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLvlWithMinByKey(final int id, final int lvl, final int _min){
        return updateLvlWithMinByKey(id, lvl, _min, TABLENAME);
    }

    public int updateLvlWithMinByKey(final int id, final int lvl, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lvl = (select case when lvl+:lvl<=:_min then :_min else lvl+:lvl end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lvl", lvl);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLvlWithMinInKeys(final List<Integer> keys, final int lvl, final int _min){
        return updateLvlWithMinInKeys(keys, lvl, _min, TABLENAME);
    }

    public int updateLvlWithMinInKeys(final List<Integer> keys, final int lvl, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lvl = (select case when lvl+:lvl<=:_min then :_min else lvl+:lvl end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lvl", lvl);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLvlWithMaxByKey(final int id, final int lvl, final int _max){
        return updateLvlWithMaxByKey(id, lvl, _max, TABLENAME);
    }

    public int updateLvlWithMaxByKey(final int id, final int lvl, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lvl = (select case when lvl+:lvl>=:_max then :_max else lvl+:lvl end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lvl", lvl);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLvlWithMaxInKeys(final List<Integer> keys, final int lvl, final int _max){
        return updateLvlWithMaxInKeys(keys, lvl, _max, TABLENAME);
    }

    public int updateLvlWithMaxInKeys(final List<Integer> keys, final int lvl, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lvl = (select case when lvl+:lvl>=:_max then :_max else lvl+:lvl end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lvl", lvl);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLvlWithMinMaxByKey(final int id, final int lvl, final int _min, final int _max){
        return updateLvlWithMinMaxByKey(id, lvl, _min, _max, TABLENAME);
    }

    public int updateLvlWithMinMaxByKey(final int id, final int lvl, final int _min, final int _max, final String TABLENAME2){
        if( lvl < 0 ) {
            return updateLvlWithMinByKey(id, lvl, _min, TABLENAME2);
        } else {
            return updateLvlWithMaxByKey(id, lvl, _max, TABLENAME2);
        }
    }

    public int updateLvlWithMinMaxInKeys(final List<Integer> keys, final int lvl, final int _min, final int _max){
        return updateLvlWithMinMaxInKeys(keys, lvl, _min, _max, TABLENAME);
    }

    public int updateLvlWithMinMaxInKeys(final List<Integer> keys, final int lvl, final int _min, final int _max, final String TABLENAME2){
        if( lvl < 0 ) {
            return updateLvlWithMinInKeys(keys, lvl, _min, TABLENAME2);
        } else {
            return updateLvlWithMaxInKeys(keys, lvl, _max, TABLENAME2);
        }
    }

    public int updateCooldown_msByKey(final long cooldown_ms, final int id){
        return updateCooldown_msByKey(cooldown_ms, id, TABLENAME);
    }

    public int updateCooldown_msByKey(final long cooldown_ms, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cooldown_ms=cooldown_ms+:cooldown_ms WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("cooldown_ms", cooldown_ms);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCooldown_msWithMinByKey(final int id, final long cooldown_ms, final long _min){
        return updateCooldown_msWithMinByKey(id, cooldown_ms, _min, TABLENAME);
    }

    public int updateCooldown_msWithMinByKey(final int id, final long cooldown_ms, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cooldown_ms = (select case when cooldown_ms+:cooldown_ms<=:_min then :_min else cooldown_ms+:cooldown_ms end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("cooldown_ms", cooldown_ms);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCooldown_msWithMinInKeys(final List<Integer> keys, final long cooldown_ms, final long _min){
        return updateCooldown_msWithMinInKeys(keys, cooldown_ms, _min, TABLENAME);
    }

    public int updateCooldown_msWithMinInKeys(final List<Integer> keys, final long cooldown_ms, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cooldown_ms = (select case when cooldown_ms+:cooldown_ms<=:_min then :_min else cooldown_ms+:cooldown_ms end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("cooldown_ms", cooldown_ms);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCooldown_msWithMaxByKey(final int id, final long cooldown_ms, final long _max){
        return updateCooldown_msWithMaxByKey(id, cooldown_ms, _max, TABLENAME);
    }

    public int updateCooldown_msWithMaxByKey(final int id, final long cooldown_ms, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cooldown_ms = (select case when cooldown_ms+:cooldown_ms>=:_max then :_max else cooldown_ms+:cooldown_ms end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("cooldown_ms", cooldown_ms);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCooldown_msWithMaxInKeys(final List<Integer> keys, final long cooldown_ms, final long _max){
        return updateCooldown_msWithMaxInKeys(keys, cooldown_ms, _max, TABLENAME);
    }

    public int updateCooldown_msWithMaxInKeys(final List<Integer> keys, final long cooldown_ms, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cooldown_ms = (select case when cooldown_ms+:cooldown_ms>=:_max then :_max else cooldown_ms+:cooldown_ms end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("cooldown_ms", cooldown_ms);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCooldown_msWithMinMaxByKey(final int id, final long cooldown_ms, final long _min, final long _max){
        return updateCooldown_msWithMinMaxByKey(id, cooldown_ms, _min, _max, TABLENAME);
    }

    public int updateCooldown_msWithMinMaxByKey(final int id, final long cooldown_ms, final long _min, final long _max, final String TABLENAME2){
        if( cooldown_ms < 0 ) {
            return updateCooldown_msWithMinByKey(id, cooldown_ms, _min, TABLENAME2);
        } else {
            return updateCooldown_msWithMaxByKey(id, cooldown_ms, _max, TABLENAME2);
        }
    }

    public int updateCooldown_msWithMinMaxInKeys(final List<Integer> keys, final long cooldown_ms, final long _min, final long _max){
        return updateCooldown_msWithMinMaxInKeys(keys, cooldown_ms, _min, _max, TABLENAME);
    }

    public int updateCooldown_msWithMinMaxInKeys(final List<Integer> keys, final long cooldown_ms, final long _min, final long _max, final String TABLENAME2){
        if( cooldown_ms < 0 ) {
            return updateCooldown_msWithMinInKeys(keys, cooldown_ms, _min, TABLENAME2);
        } else {
            return updateCooldown_msWithMaxInKeys(keys, cooldown_ms, _max, TABLENAME2);
        }
    }

    public int updatePosition_indexByKey(final int position_index, final int id){
        return updatePosition_indexByKey(position_index, id, TABLENAME);
    }

    public int updatePosition_indexByKey(final int position_index, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET position_index=position_index+:position_index WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("position_index", position_index);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePosition_indexWithMinByKey(final int id, final int position_index, final int _min){
        return updatePosition_indexWithMinByKey(id, position_index, _min, TABLENAME);
    }

    public int updatePosition_indexWithMinByKey(final int id, final int position_index, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET position_index = (select case when position_index+:position_index<=:_min then :_min else position_index+:position_index end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("position_index", position_index);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePosition_indexWithMinInKeys(final List<Integer> keys, final int position_index, final int _min){
        return updatePosition_indexWithMinInKeys(keys, position_index, _min, TABLENAME);
    }

    public int updatePosition_indexWithMinInKeys(final List<Integer> keys, final int position_index, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET position_index = (select case when position_index+:position_index<=:_min then :_min else position_index+:position_index end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("position_index", position_index);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePosition_indexWithMaxByKey(final int id, final int position_index, final int _max){
        return updatePosition_indexWithMaxByKey(id, position_index, _max, TABLENAME);
    }

    public int updatePosition_indexWithMaxByKey(final int id, final int position_index, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET position_index = (select case when position_index+:position_index>=:_max then :_max else position_index+:position_index end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("position_index", position_index);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePosition_indexWithMaxInKeys(final List<Integer> keys, final int position_index, final int _max){
        return updatePosition_indexWithMaxInKeys(keys, position_index, _max, TABLENAME);
    }

    public int updatePosition_indexWithMaxInKeys(final List<Integer> keys, final int position_index, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET position_index = (select case when position_index+:position_index>=:_max then :_max else position_index+:position_index end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("position_index", position_index);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePosition_indexWithMinMaxByKey(final int id, final int position_index, final int _min, final int _max){
        return updatePosition_indexWithMinMaxByKey(id, position_index, _min, _max, TABLENAME);
    }

    public int updatePosition_indexWithMinMaxByKey(final int id, final int position_index, final int _min, final int _max, final String TABLENAME2){
        if( position_index < 0 ) {
            return updatePosition_indexWithMinByKey(id, position_index, _min, TABLENAME2);
        } else {
            return updatePosition_indexWithMaxByKey(id, position_index, _max, TABLENAME2);
        }
    }

    public int updatePosition_indexWithMinMaxInKeys(final List<Integer> keys, final int position_index, final int _min, final int _max){
        return updatePosition_indexWithMinMaxInKeys(keys, position_index, _min, _max, TABLENAME);
    }

    public int updatePosition_indexWithMinMaxInKeys(final List<Integer> keys, final int position_index, final int _min, final int _max, final String TABLENAME2){
        if( position_index < 0 ) {
            return updatePosition_indexWithMinInKeys(keys, position_index, _min, TABLENAME2);
        } else {
            return updatePosition_indexWithMaxInKeys(keys, position_index, _max, TABLENAME2);
        }
    }

    public int updateStateByKey(final int state, final int id){
        return updateStateByKey(state, id, TABLENAME);
    }

    public int updateStateByKey(final int state, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET state=state+:state WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("state", state);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStateWithMinByKey(final int id, final int state, final int _min){
        return updateStateWithMinByKey(id, state, _min, TABLENAME);
    }

    public int updateStateWithMinByKey(final int id, final int state, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET state = (select case when state+:state<=:_min then :_min else state+:state end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("state", state);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStateWithMinInKeys(final List<Integer> keys, final int state, final int _min){
        return updateStateWithMinInKeys(keys, state, _min, TABLENAME);
    }

    public int updateStateWithMinInKeys(final List<Integer> keys, final int state, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET state = (select case when state+:state<=:_min then :_min else state+:state end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("state", state);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStateWithMaxByKey(final int id, final int state, final int _max){
        return updateStateWithMaxByKey(id, state, _max, TABLENAME);
    }

    public int updateStateWithMaxByKey(final int id, final int state, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET state = (select case when state+:state>=:_max then :_max else state+:state end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("state", state);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStateWithMaxInKeys(final List<Integer> keys, final int state, final int _max){
        return updateStateWithMaxInKeys(keys, state, _max, TABLENAME);
    }

    public int updateStateWithMaxInKeys(final List<Integer> keys, final int state, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET state = (select case when state+:state>=:_max then :_max else state+:state end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("state", state);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStateWithMinMaxByKey(final int id, final int state, final int _min, final int _max){
        return updateStateWithMinMaxByKey(id, state, _min, _max, TABLENAME);
    }

    public int updateStateWithMinMaxByKey(final int id, final int state, final int _min, final int _max, final String TABLENAME2){
        if( state < 0 ) {
            return updateStateWithMinByKey(id, state, _min, TABLENAME2);
        } else {
            return updateStateWithMaxByKey(id, state, _max, TABLENAME2);
        }
    }

    public int updateStateWithMinMaxInKeys(final List<Integer> keys, final int state, final int _min, final int _max){
        return updateStateWithMinMaxInKeys(keys, state, _min, _max, TABLENAME);
    }

    public int updateStateWithMinMaxInKeys(final List<Integer> keys, final int state, final int _min, final int _max, final String TABLENAME2){
        if( state < 0 ) {
            return updateStateWithMinInKeys(keys, state, _min, TABLENAME2);
        } else {
            return updateStateWithMaxInKeys(keys, state, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Player_build_obst> player_build_obsts) {
        return updateByKey(player_build_obsts, TABLENAME);
    }

    public int[] updateByKey (final List<Player_build_obst> player_build_obsts, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(player_build_obsts == null || player_build_obsts.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bcid=:bcid, building_name=:building_name, pcid=:pcid, player_name=:player_name, gid=:gid, lvl=:lvl, cooldown_ms=:cooldown_ms, position_index=:position_index, state=:state, type=:type WHERE id=:id");
            return super.batchUpdate2(sql.toString(), player_build_obsts);
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
                "	`bcid`  INT(11) NOT NULL," +
                "	`building_name`  VARCHAR(32) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`player_name`  VARCHAR(32) NOT NULL," +
                "	`gid`  INT(11) NOT NULL," +
                "	`lvl`  INT(11) NOT NULL," +
                "	`cooldown_ms`  BIGINT(20) NOT NULL," +
                "	`position_index`  INT(11) NOT NULL," +
                "	`state`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `client_id` (`bcid`, `pcid`)," +
                "	KEY `player_id` (`pcid`)," +
                "	KEY `player_id_2` (`pcid`, `gid`)" +
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
                "	`bcid`  INT(11) NOT NULL," +
                "	`building_name`  VARCHAR(32) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`player_name`  VARCHAR(32) NOT NULL," +
                "	`gid`  INT(11) NOT NULL," +
                "	`lvl`  INT(11) NOT NULL," +
                "	`cooldown_ms`  BIGINT(20) NOT NULL," +
                "	`position_index`  INT(11) NOT NULL," +
                "	`state`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `client_id` (`bcid`, `pcid`)," +
                "	KEY `player_id` (`pcid`)," +
                "	KEY `player_id_2` (`pcid`, `gid`)" +
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
