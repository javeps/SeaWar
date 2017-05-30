package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - player_buildings
@SuppressWarnings({"rawtypes", "unchecked"})
public class Player_buildingsDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Player_buildingsDAO.class);

    public static final String TABLE = "player_buildings";
    public static String TABLENAME = "player_buildings";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "bcid", "building_name", "pcid", "player_name", "gid", "lvl", "cooldown_ms", "position_index", "state", "type", "cur_produce_res", "res_produce_begin_time", "cur_up_tech_gid", "end_tech_up_time", "begin_army_time"};
    public static String coulmns = "id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time";
    public static String coulmns2 = "bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time";

    public Player_buildingsDAO(java.sql.Connection conn) {
        super(conn);
    }

    public Player_buildingsDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public Player_buildingsDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Player_buildings player_buildings) {
        return insert(player_buildings, TABLENAME);
    }

    public int insert(final Player_buildings player_buildings, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            player_buildings.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time) VALUES (:bcid, :building_name, :pcid, :player_name, :gid, :lvl, :cooldown_ms, :position_index, :state, :type, :cur_produce_res, :res_produce_begin_time, :cur_up_tech_gid, :end_tech_up_time, :begin_army_time)");
            Map map = super.insert(sql.toString(), player_buildings);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Player_buildings player_buildings) {
        asynchronousInsert(player_buildings, TABLENAME);
    }

    public void asynchronousInsert(final Player_buildings player_buildings, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(player_buildings, TABLENAME2);
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

    public int asynchronousInsert2(final Player_buildings player_buildings) {
        return asynchronousInsert2(player_buildings, TABLENAME);
    }

    public int asynchronousInsert2(final Player_buildings player_buildings, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(player_buildings, TABLENAME2);
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
        return player_buildings.id;
    }

    public int insert2(final Player_buildings player_buildings) {
        return insert2(player_buildings, TABLENAME);
    }

    public int insert2(final Player_buildings player_buildings, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            player_buildings.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time) VALUES (:id, :bcid, :building_name, :pcid, :player_name, :gid, :lvl, :cooldown_ms, :position_index, :state, :type, :cur_produce_res, :res_produce_begin_time, :cur_up_tech_gid, :end_tech_up_time, :begin_army_time)");
            Map map = super.insert(sql.toString(), player_buildings);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Player_buildings> player_buildingss) {
        return insert(player_buildingss, TABLENAME);
    }

    public int[] insert(final List<Player_buildings> player_buildingss, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(player_buildingss == null || player_buildingss.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time) VALUES (:bcid, :building_name, :pcid, :player_name, :gid, :lvl, :cooldown_ms, :position_index, :state, :type, :cur_produce_res, :res_produce_begin_time, :cur_up_tech_gid, :end_tech_up_time, :begin_army_time)");
            return super.batchInsert(sql.toString(), player_buildingss);
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

    public int deleteInBeans(final List<Player_buildings> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Player_buildings> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Player_buildings player_buildings = beans.get(i);
                int id = player_buildings.id;
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

    public List<Player_buildings> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Player_buildings> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Player_buildings.class);
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

    public List<Player_buildings> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Player_buildings> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Player_buildings.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_buildings> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Player_buildings> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Player_buildings.class);
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

    public List<Player_buildings> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Player_buildings> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Player_buildings.class);
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

    public Player_buildings last() {
        return last(TABLENAME);
    }

    public Player_buildings last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Player_buildings.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_buildings> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Player_buildings> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Player_buildings.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_buildings> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Player_buildings> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Player_buildings.class);
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

    public Player_buildings selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Player_buildings selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Player_buildings.class);
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

    public List<Player_buildings> selectByPcidGid(final Integer pcid, Integer gid) {
        return selectByPcidGid(pcid, gid, TABLENAME);
    }

    public List<Player_buildings> selectByPcidGid(final Integer pcid, Integer gid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE pcid=:pcid AND gid=:gid ORDER BY id ");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("gid", gid);
            return super.queryForList(sql.toString(), params, Player_buildings.class);
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

    public List<Player_buildings> selectPageByPcidGid(final Integer pcid, Integer gid, final int begin, final int num) {
        return selectPageByPcidGid(pcid, gid, begin, num, TABLENAME);
    }

    public List<Player_buildings> selectPageByPcidGid(final Integer pcid, Integer gid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE pcid=:pcid AND gid=:gid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("gid", gid);
            return super.queryForList(sql.toString(), params, Player_buildings.class);
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

    public Player_buildings selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Player_buildings selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Player_buildings.class);
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

    public List<Player_buildings> selectByPcid(final Integer pcid) {
        return selectByPcid(pcid, TABLENAME);
    }

    public List<Player_buildings> selectByPcid(final Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid ORDER BY id ");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForList(sql.toString(), params, Player_buildings.class);
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

    public List<Player_buildings> selectPageByPcid(final Integer pcid, final int begin, final int num) {
        return selectPageByPcid(pcid, begin, num, TABLENAME);
    }

    public List<Player_buildings> selectPageByPcid(final Integer pcid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForList(sql.toString(), params, Player_buildings.class);
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

    public Player_buildings selectByBcidPcid(final Integer bcid, Integer pcid) {
        return selectByBcidPcid(bcid, pcid, TABLENAME);
    }

    public Player_buildings selectByBcidPcid(final Integer bcid, Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" WHERE bcid=:bcid AND pcid=:pcid");
            Map params = newMap();
            params.put("bcid", bcid);
            params.put("pcid", pcid);
            return super.queryForObject(sql.toString(), params, Player_buildings.class);
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

    public List<Player_buildings> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Player_buildings> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, bcid, building_name, pcid, player_name, gid, lvl, cooldown_ms, position_index, state, type, cur_produce_res, res_produce_begin_time, cur_up_tech_gid, end_tech_up_time, begin_army_time FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Player_buildings.class);
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

    public int updateByKey(final Player_buildings player_buildings) {
        return updateByKey(player_buildings, TABLENAME);
    }

    public int updateByKey(final Player_buildings player_buildings, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = player_buildings.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), player_buildings);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Player_buildings player_buildings) {
        asynchronousUpdate(player_buildings, TABLENAME);
    }

    public void asynchronousUpdate(final Player_buildings player_buildings, final String TABLENAME2) {
        try {

            String _ustr = player_buildings.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, player_buildings);
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

    public int updateCur_produce_resByKey(final long cur_produce_res, final int id){
        return updateCur_produce_resByKey(cur_produce_res, id, TABLENAME);
    }

    public int updateCur_produce_resByKey(final long cur_produce_res, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_produce_res=cur_produce_res+:cur_produce_res WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("cur_produce_res", cur_produce_res);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_produce_resWithMinByKey(final int id, final long cur_produce_res, final long _min){
        return updateCur_produce_resWithMinByKey(id, cur_produce_res, _min, TABLENAME);
    }

    public int updateCur_produce_resWithMinByKey(final int id, final long cur_produce_res, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_produce_res = (select case when cur_produce_res+:cur_produce_res<=:_min then :_min else cur_produce_res+:cur_produce_res end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("cur_produce_res", cur_produce_res);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_produce_resWithMinInKeys(final List<Integer> keys, final long cur_produce_res, final long _min){
        return updateCur_produce_resWithMinInKeys(keys, cur_produce_res, _min, TABLENAME);
    }

    public int updateCur_produce_resWithMinInKeys(final List<Integer> keys, final long cur_produce_res, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_produce_res = (select case when cur_produce_res+:cur_produce_res<=:_min then :_min else cur_produce_res+:cur_produce_res end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("cur_produce_res", cur_produce_res);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_produce_resWithMaxByKey(final int id, final long cur_produce_res, final long _max){
        return updateCur_produce_resWithMaxByKey(id, cur_produce_res, _max, TABLENAME);
    }

    public int updateCur_produce_resWithMaxByKey(final int id, final long cur_produce_res, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_produce_res = (select case when cur_produce_res+:cur_produce_res>=:_max then :_max else cur_produce_res+:cur_produce_res end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("cur_produce_res", cur_produce_res);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_produce_resWithMaxInKeys(final List<Integer> keys, final long cur_produce_res, final long _max){
        return updateCur_produce_resWithMaxInKeys(keys, cur_produce_res, _max, TABLENAME);
    }

    public int updateCur_produce_resWithMaxInKeys(final List<Integer> keys, final long cur_produce_res, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_produce_res = (select case when cur_produce_res+:cur_produce_res>=:_max then :_max else cur_produce_res+:cur_produce_res end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("cur_produce_res", cur_produce_res);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_produce_resWithMinMaxByKey(final int id, final long cur_produce_res, final long _min, final long _max){
        return updateCur_produce_resWithMinMaxByKey(id, cur_produce_res, _min, _max, TABLENAME);
    }

    public int updateCur_produce_resWithMinMaxByKey(final int id, final long cur_produce_res, final long _min, final long _max, final String TABLENAME2){
        if( cur_produce_res < 0 ) {
            return updateCur_produce_resWithMinByKey(id, cur_produce_res, _min, TABLENAME2);
        } else {
            return updateCur_produce_resWithMaxByKey(id, cur_produce_res, _max, TABLENAME2);
        }
    }

    public int updateCur_produce_resWithMinMaxInKeys(final List<Integer> keys, final long cur_produce_res, final long _min, final long _max){
        return updateCur_produce_resWithMinMaxInKeys(keys, cur_produce_res, _min, _max, TABLENAME);
    }

    public int updateCur_produce_resWithMinMaxInKeys(final List<Integer> keys, final long cur_produce_res, final long _min, final long _max, final String TABLENAME2){
        if( cur_produce_res < 0 ) {
            return updateCur_produce_resWithMinInKeys(keys, cur_produce_res, _min, TABLENAME2);
        } else {
            return updateCur_produce_resWithMaxInKeys(keys, cur_produce_res, _max, TABLENAME2);
        }
    }

    public int updateRes_produce_begin_timeByKey(final long res_produce_begin_time, final int id){
        return updateRes_produce_begin_timeByKey(res_produce_begin_time, id, TABLENAME);
    }

    public int updateRes_produce_begin_timeByKey(final long res_produce_begin_time, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET res_produce_begin_time=res_produce_begin_time+:res_produce_begin_time WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("res_produce_begin_time", res_produce_begin_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRes_produce_begin_timeWithMinByKey(final int id, final long res_produce_begin_time, final long _min){
        return updateRes_produce_begin_timeWithMinByKey(id, res_produce_begin_time, _min, TABLENAME);
    }

    public int updateRes_produce_begin_timeWithMinByKey(final int id, final long res_produce_begin_time, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET res_produce_begin_time = (select case when res_produce_begin_time+:res_produce_begin_time<=:_min then :_min else res_produce_begin_time+:res_produce_begin_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("res_produce_begin_time", res_produce_begin_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRes_produce_begin_timeWithMinInKeys(final List<Integer> keys, final long res_produce_begin_time, final long _min){
        return updateRes_produce_begin_timeWithMinInKeys(keys, res_produce_begin_time, _min, TABLENAME);
    }

    public int updateRes_produce_begin_timeWithMinInKeys(final List<Integer> keys, final long res_produce_begin_time, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET res_produce_begin_time = (select case when res_produce_begin_time+:res_produce_begin_time<=:_min then :_min else res_produce_begin_time+:res_produce_begin_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("res_produce_begin_time", res_produce_begin_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRes_produce_begin_timeWithMaxByKey(final int id, final long res_produce_begin_time, final long _max){
        return updateRes_produce_begin_timeWithMaxByKey(id, res_produce_begin_time, _max, TABLENAME);
    }

    public int updateRes_produce_begin_timeWithMaxByKey(final int id, final long res_produce_begin_time, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET res_produce_begin_time = (select case when res_produce_begin_time+:res_produce_begin_time>=:_max then :_max else res_produce_begin_time+:res_produce_begin_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("res_produce_begin_time", res_produce_begin_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRes_produce_begin_timeWithMaxInKeys(final List<Integer> keys, final long res_produce_begin_time, final long _max){
        return updateRes_produce_begin_timeWithMaxInKeys(keys, res_produce_begin_time, _max, TABLENAME);
    }

    public int updateRes_produce_begin_timeWithMaxInKeys(final List<Integer> keys, final long res_produce_begin_time, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET res_produce_begin_time = (select case when res_produce_begin_time+:res_produce_begin_time>=:_max then :_max else res_produce_begin_time+:res_produce_begin_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("res_produce_begin_time", res_produce_begin_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRes_produce_begin_timeWithMinMaxByKey(final int id, final long res_produce_begin_time, final long _min, final long _max){
        return updateRes_produce_begin_timeWithMinMaxByKey(id, res_produce_begin_time, _min, _max, TABLENAME);
    }

    public int updateRes_produce_begin_timeWithMinMaxByKey(final int id, final long res_produce_begin_time, final long _min, final long _max, final String TABLENAME2){
        if( res_produce_begin_time < 0 ) {
            return updateRes_produce_begin_timeWithMinByKey(id, res_produce_begin_time, _min, TABLENAME2);
        } else {
            return updateRes_produce_begin_timeWithMaxByKey(id, res_produce_begin_time, _max, TABLENAME2);
        }
    }

    public int updateRes_produce_begin_timeWithMinMaxInKeys(final List<Integer> keys, final long res_produce_begin_time, final long _min, final long _max){
        return updateRes_produce_begin_timeWithMinMaxInKeys(keys, res_produce_begin_time, _min, _max, TABLENAME);
    }

    public int updateRes_produce_begin_timeWithMinMaxInKeys(final List<Integer> keys, final long res_produce_begin_time, final long _min, final long _max, final String TABLENAME2){
        if( res_produce_begin_time < 0 ) {
            return updateRes_produce_begin_timeWithMinInKeys(keys, res_produce_begin_time, _min, TABLENAME2);
        } else {
            return updateRes_produce_begin_timeWithMaxInKeys(keys, res_produce_begin_time, _max, TABLENAME2);
        }
    }

    public int updateCur_up_tech_gidByKey(final int cur_up_tech_gid, final int id){
        return updateCur_up_tech_gidByKey(cur_up_tech_gid, id, TABLENAME);
    }

    public int updateCur_up_tech_gidByKey(final int cur_up_tech_gid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_up_tech_gid=cur_up_tech_gid+:cur_up_tech_gid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("cur_up_tech_gid", cur_up_tech_gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_up_tech_gidWithMinByKey(final int id, final int cur_up_tech_gid, final int _min){
        return updateCur_up_tech_gidWithMinByKey(id, cur_up_tech_gid, _min, TABLENAME);
    }

    public int updateCur_up_tech_gidWithMinByKey(final int id, final int cur_up_tech_gid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_up_tech_gid = (select case when cur_up_tech_gid+:cur_up_tech_gid<=:_min then :_min else cur_up_tech_gid+:cur_up_tech_gid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("cur_up_tech_gid", cur_up_tech_gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_up_tech_gidWithMinInKeys(final List<Integer> keys, final int cur_up_tech_gid, final int _min){
        return updateCur_up_tech_gidWithMinInKeys(keys, cur_up_tech_gid, _min, TABLENAME);
    }

    public int updateCur_up_tech_gidWithMinInKeys(final List<Integer> keys, final int cur_up_tech_gid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_up_tech_gid = (select case when cur_up_tech_gid+:cur_up_tech_gid<=:_min then :_min else cur_up_tech_gid+:cur_up_tech_gid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("cur_up_tech_gid", cur_up_tech_gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_up_tech_gidWithMaxByKey(final int id, final int cur_up_tech_gid, final int _max){
        return updateCur_up_tech_gidWithMaxByKey(id, cur_up_tech_gid, _max, TABLENAME);
    }

    public int updateCur_up_tech_gidWithMaxByKey(final int id, final int cur_up_tech_gid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_up_tech_gid = (select case when cur_up_tech_gid+:cur_up_tech_gid>=:_max then :_max else cur_up_tech_gid+:cur_up_tech_gid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("cur_up_tech_gid", cur_up_tech_gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_up_tech_gidWithMaxInKeys(final List<Integer> keys, final int cur_up_tech_gid, final int _max){
        return updateCur_up_tech_gidWithMaxInKeys(keys, cur_up_tech_gid, _max, TABLENAME);
    }

    public int updateCur_up_tech_gidWithMaxInKeys(final List<Integer> keys, final int cur_up_tech_gid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_up_tech_gid = (select case when cur_up_tech_gid+:cur_up_tech_gid>=:_max then :_max else cur_up_tech_gid+:cur_up_tech_gid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("cur_up_tech_gid", cur_up_tech_gid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_up_tech_gidWithMinMaxByKey(final int id, final int cur_up_tech_gid, final int _min, final int _max){
        return updateCur_up_tech_gidWithMinMaxByKey(id, cur_up_tech_gid, _min, _max, TABLENAME);
    }

    public int updateCur_up_tech_gidWithMinMaxByKey(final int id, final int cur_up_tech_gid, final int _min, final int _max, final String TABLENAME2){
        if( cur_up_tech_gid < 0 ) {
            return updateCur_up_tech_gidWithMinByKey(id, cur_up_tech_gid, _min, TABLENAME2);
        } else {
            return updateCur_up_tech_gidWithMaxByKey(id, cur_up_tech_gid, _max, TABLENAME2);
        }
    }

    public int updateCur_up_tech_gidWithMinMaxInKeys(final List<Integer> keys, final int cur_up_tech_gid, final int _min, final int _max){
        return updateCur_up_tech_gidWithMinMaxInKeys(keys, cur_up_tech_gid, _min, _max, TABLENAME);
    }

    public int updateCur_up_tech_gidWithMinMaxInKeys(final List<Integer> keys, final int cur_up_tech_gid, final int _min, final int _max, final String TABLENAME2){
        if( cur_up_tech_gid < 0 ) {
            return updateCur_up_tech_gidWithMinInKeys(keys, cur_up_tech_gid, _min, TABLENAME2);
        } else {
            return updateCur_up_tech_gidWithMaxInKeys(keys, cur_up_tech_gid, _max, TABLENAME2);
        }
    }

    public int updateEnd_tech_up_timeByKey(final long end_tech_up_time, final int id){
        return updateEnd_tech_up_timeByKey(end_tech_up_time, id, TABLENAME);
    }

    public int updateEnd_tech_up_timeByKey(final long end_tech_up_time, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET end_tech_up_time=end_tech_up_time+:end_tech_up_time WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("end_tech_up_time", end_tech_up_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEnd_tech_up_timeWithMinByKey(final int id, final long end_tech_up_time, final long _min){
        return updateEnd_tech_up_timeWithMinByKey(id, end_tech_up_time, _min, TABLENAME);
    }

    public int updateEnd_tech_up_timeWithMinByKey(final int id, final long end_tech_up_time, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET end_tech_up_time = (select case when end_tech_up_time+:end_tech_up_time<=:_min then :_min else end_tech_up_time+:end_tech_up_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("end_tech_up_time", end_tech_up_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEnd_tech_up_timeWithMinInKeys(final List<Integer> keys, final long end_tech_up_time, final long _min){
        return updateEnd_tech_up_timeWithMinInKeys(keys, end_tech_up_time, _min, TABLENAME);
    }

    public int updateEnd_tech_up_timeWithMinInKeys(final List<Integer> keys, final long end_tech_up_time, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET end_tech_up_time = (select case when end_tech_up_time+:end_tech_up_time<=:_min then :_min else end_tech_up_time+:end_tech_up_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("end_tech_up_time", end_tech_up_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEnd_tech_up_timeWithMaxByKey(final int id, final long end_tech_up_time, final long _max){
        return updateEnd_tech_up_timeWithMaxByKey(id, end_tech_up_time, _max, TABLENAME);
    }

    public int updateEnd_tech_up_timeWithMaxByKey(final int id, final long end_tech_up_time, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET end_tech_up_time = (select case when end_tech_up_time+:end_tech_up_time>=:_max then :_max else end_tech_up_time+:end_tech_up_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("end_tech_up_time", end_tech_up_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEnd_tech_up_timeWithMaxInKeys(final List<Integer> keys, final long end_tech_up_time, final long _max){
        return updateEnd_tech_up_timeWithMaxInKeys(keys, end_tech_up_time, _max, TABLENAME);
    }

    public int updateEnd_tech_up_timeWithMaxInKeys(final List<Integer> keys, final long end_tech_up_time, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET end_tech_up_time = (select case when end_tech_up_time+:end_tech_up_time>=:_max then :_max else end_tech_up_time+:end_tech_up_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("end_tech_up_time", end_tech_up_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEnd_tech_up_timeWithMinMaxByKey(final int id, final long end_tech_up_time, final long _min, final long _max){
        return updateEnd_tech_up_timeWithMinMaxByKey(id, end_tech_up_time, _min, _max, TABLENAME);
    }

    public int updateEnd_tech_up_timeWithMinMaxByKey(final int id, final long end_tech_up_time, final long _min, final long _max, final String TABLENAME2){
        if( end_tech_up_time < 0 ) {
            return updateEnd_tech_up_timeWithMinByKey(id, end_tech_up_time, _min, TABLENAME2);
        } else {
            return updateEnd_tech_up_timeWithMaxByKey(id, end_tech_up_time, _max, TABLENAME2);
        }
    }

    public int updateEnd_tech_up_timeWithMinMaxInKeys(final List<Integer> keys, final long end_tech_up_time, final long _min, final long _max){
        return updateEnd_tech_up_timeWithMinMaxInKeys(keys, end_tech_up_time, _min, _max, TABLENAME);
    }

    public int updateEnd_tech_up_timeWithMinMaxInKeys(final List<Integer> keys, final long end_tech_up_time, final long _min, final long _max, final String TABLENAME2){
        if( end_tech_up_time < 0 ) {
            return updateEnd_tech_up_timeWithMinInKeys(keys, end_tech_up_time, _min, TABLENAME2);
        } else {
            return updateEnd_tech_up_timeWithMaxInKeys(keys, end_tech_up_time, _max, TABLENAME2);
        }
    }

    public int updateBegin_army_timeByKey(final long begin_army_time, final int id){
        return updateBegin_army_timeByKey(begin_army_time, id, TABLENAME);
    }

    public int updateBegin_army_timeByKey(final long begin_army_time, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begin_army_time=begin_army_time+:begin_army_time WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("begin_army_time", begin_army_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegin_army_timeWithMinByKey(final int id, final long begin_army_time, final long _min){
        return updateBegin_army_timeWithMinByKey(id, begin_army_time, _min, TABLENAME);
    }

    public int updateBegin_army_timeWithMinByKey(final int id, final long begin_army_time, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begin_army_time = (select case when begin_army_time+:begin_army_time<=:_min then :_min else begin_army_time+:begin_army_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("begin_army_time", begin_army_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegin_army_timeWithMinInKeys(final List<Integer> keys, final long begin_army_time, final long _min){
        return updateBegin_army_timeWithMinInKeys(keys, begin_army_time, _min, TABLENAME);
    }

    public int updateBegin_army_timeWithMinInKeys(final List<Integer> keys, final long begin_army_time, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begin_army_time = (select case when begin_army_time+:begin_army_time<=:_min then :_min else begin_army_time+:begin_army_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("begin_army_time", begin_army_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegin_army_timeWithMaxByKey(final int id, final long begin_army_time, final long _max){
        return updateBegin_army_timeWithMaxByKey(id, begin_army_time, _max, TABLENAME);
    }

    public int updateBegin_army_timeWithMaxByKey(final int id, final long begin_army_time, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begin_army_time = (select case when begin_army_time+:begin_army_time>=:_max then :_max else begin_army_time+:begin_army_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("begin_army_time", begin_army_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegin_army_timeWithMaxInKeys(final List<Integer> keys, final long begin_army_time, final long _max){
        return updateBegin_army_timeWithMaxInKeys(keys, begin_army_time, _max, TABLENAME);
    }

    public int updateBegin_army_timeWithMaxInKeys(final List<Integer> keys, final long begin_army_time, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begin_army_time = (select case when begin_army_time+:begin_army_time>=:_max then :_max else begin_army_time+:begin_army_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("begin_army_time", begin_army_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegin_army_timeWithMinMaxByKey(final int id, final long begin_army_time, final long _min, final long _max){
        return updateBegin_army_timeWithMinMaxByKey(id, begin_army_time, _min, _max, TABLENAME);
    }

    public int updateBegin_army_timeWithMinMaxByKey(final int id, final long begin_army_time, final long _min, final long _max, final String TABLENAME2){
        if( begin_army_time < 0 ) {
            return updateBegin_army_timeWithMinByKey(id, begin_army_time, _min, TABLENAME2);
        } else {
            return updateBegin_army_timeWithMaxByKey(id, begin_army_time, _max, TABLENAME2);
        }
    }

    public int updateBegin_army_timeWithMinMaxInKeys(final List<Integer> keys, final long begin_army_time, final long _min, final long _max){
        return updateBegin_army_timeWithMinMaxInKeys(keys, begin_army_time, _min, _max, TABLENAME);
    }

    public int updateBegin_army_timeWithMinMaxInKeys(final List<Integer> keys, final long begin_army_time, final long _min, final long _max, final String TABLENAME2){
        if( begin_army_time < 0 ) {
            return updateBegin_army_timeWithMinInKeys(keys, begin_army_time, _min, TABLENAME2);
        } else {
            return updateBegin_army_timeWithMaxInKeys(keys, begin_army_time, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Player_buildings> player_buildingss) {
        return updateByKey(player_buildingss, TABLENAME);
    }

    public int[] updateByKey (final List<Player_buildings> player_buildingss, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(player_buildingss == null || player_buildingss.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET bcid=:bcid, building_name=:building_name, pcid=:pcid, player_name=:player_name, gid=:gid, lvl=:lvl, cooldown_ms=:cooldown_ms, position_index=:position_index, state=:state, type=:type, cur_produce_res=:cur_produce_res, res_produce_begin_time=:res_produce_begin_time, cur_up_tech_gid=:cur_up_tech_gid, end_tech_up_time=:end_tech_up_time, begin_army_time=:begin_army_time WHERE id=:id");
            return super.batchUpdate2(sql.toString(), player_buildingss);
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
                "	`cur_produce_res`  BIGINT(20) NOT NULL," +
                "	`res_produce_begin_time`  BIGINT(20) NOT NULL," +
                "	`cur_up_tech_gid`  INT(11) NOT NULL," +
                "	`end_tech_up_time`  BIGINT(20) NOT NULL," +
                "	`begin_army_time`  BIGINT(20) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `client_id` (`bcid`, `pcid`)," +
                "	KEY `player_id` (`pcid`)," +
                "	KEY `player_id_gid` (`pcid`, `gid`)" +
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
                "	`cur_produce_res`  BIGINT(20) NOT NULL," +
                "	`res_produce_begin_time`  BIGINT(20) NOT NULL," +
                "	`cur_up_tech_gid`  INT(11) NOT NULL," +
                "	`end_tech_up_time`  BIGINT(20) NOT NULL," +
                "	`begin_army_time`  BIGINT(20) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `client_id` (`bcid`, `pcid`)," +
                "	KEY `player_id` (`pcid`)," +
                "	KEY `player_id_gid` (`pcid`, `gid`)" +
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
