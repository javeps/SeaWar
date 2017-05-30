package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - player_hero
@SuppressWarnings({"rawtypes", "unchecked"})
public class Player_heroDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Player_heroDAO.class);

    public static final String TABLE = "player_hero";
    public static String TABLENAME = "player_hero";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "hcid", "pcid", "pname", "hname", "hgid", "addDamage", "maxDamage", "addHP", "maxHP", "addAttSpeed", "maxAttSpeed", "statues", "createTime", "deadTime", "skillGid", "fpos"};
    public static String coulmns = "id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos";
    public static String coulmns2 = "hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos";

    public Player_heroDAO(java.sql.Connection conn) {
        super(conn);
    }

    public Player_heroDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public Player_heroDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Player_hero player_hero) {
        return insert(player_hero, TABLENAME);
    }

    public int insert(final Player_hero player_hero, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            player_hero.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos) VALUES (:hcid, :pcid, :pname, :hname, :hgid, :addDamage, :maxDamage, :addHP, :maxHP, :addAttSpeed, :maxAttSpeed, :statues, :createTime, :deadTime, :skillGid, :fpos)");
            Map map = super.insert(sql.toString(), player_hero);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Player_hero player_hero) {
        asynchronousInsert(player_hero, TABLENAME);
    }

    public void asynchronousInsert(final Player_hero player_hero, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(player_hero, TABLENAME2);
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

    public int asynchronousInsert2(final Player_hero player_hero) {
        return asynchronousInsert2(player_hero, TABLENAME);
    }

    public int asynchronousInsert2(final Player_hero player_hero, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(player_hero, TABLENAME2);
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
        return player_hero.id;
    }

    public int insert2(final Player_hero player_hero) {
        return insert2(player_hero, TABLENAME);
    }

    public int insert2(final Player_hero player_hero, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            player_hero.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos) VALUES (:id, :hcid, :pcid, :pname, :hname, :hgid, :addDamage, :maxDamage, :addHP, :maxHP, :addAttSpeed, :maxAttSpeed, :statues, :createTime, :deadTime, :skillGid, :fpos)");
            Map map = super.insert(sql.toString(), player_hero);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Player_hero> player_heros) {
        return insert(player_heros, TABLENAME);
    }

    public int[] insert(final List<Player_hero> player_heros, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(player_heros == null || player_heros.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos) VALUES (:hcid, :pcid, :pname, :hname, :hgid, :addDamage, :maxDamage, :addHP, :maxHP, :addAttSpeed, :maxAttSpeed, :statues, :createTime, :deadTime, :skillGid, :fpos)");
            return super.batchInsert(sql.toString(), player_heros);
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

    public int deleteInBeans(final List<Player_hero> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Player_hero> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Player_hero player_hero = beans.get(i);
                int id = player_hero.id;
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

    public List<Player_hero> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Player_hero> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Player_hero.class);
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
            sql.append("SELECT id, hcid, pcid, hgid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_hero> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Player_hero> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Player_hero.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_hero> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Player_hero> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Player_hero.class);
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

    public List<Player_hero> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Player_hero> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Player_hero.class);
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

    public Player_hero last() {
        return last(TABLENAME);
    }

    public Player_hero last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Player_hero.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_hero> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Player_hero> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Player_hero.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_hero> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Player_hero> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Player_hero.class);
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

    public Player_hero selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Player_hero selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Player_hero.class);
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

    public Player_hero selectByHcid(final Integer hcid) {
        return selectByHcid(hcid, TABLENAME);
    }

    public Player_hero selectByHcid(final Integer hcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" WHERE hcid = :hcid");
            Map params = newMap();
            params.put("hcid", hcid);
            return super.queryForObject(sql.toString(), params, Player_hero.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player_hero selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Player_hero selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Player_hero.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player_hero selectByPcidHgid(final Integer pcid, Integer hgid) {
        return selectByPcidHgid(pcid, hgid, TABLENAME);
    }

    public Player_hero selectByPcidHgid(final Integer pcid, Integer hgid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" WHERE pcid=:pcid AND hgid=:hgid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("hgid", hgid);
            return super.queryForObject(sql.toString(), params, Player_hero.class);
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

    public List<Player_hero> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Player_hero> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, hcid, pcid, pname, hname, hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed, maxAttSpeed, statues, createTime, deadTime, skillGid, fpos FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Player_hero.class);
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

    public int updateByKey(final Player_hero player_hero) {
        return updateByKey(player_hero, TABLENAME);
    }

    public int updateByKey(final Player_hero player_hero, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = player_hero.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), player_hero);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Player_hero player_hero) {
        asynchronousUpdate(player_hero, TABLENAME);
    }

    public void asynchronousUpdate(final Player_hero player_hero, final String TABLENAME2) {
        try {

            String _ustr = player_hero.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, player_hero);
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

    public int updateHcidByKey(final int hcid, final int id){
        return updateHcidByKey(hcid, id, TABLENAME);
    }

    public int updateHcidByKey(final int hcid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hcid=hcid+:hcid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("hcid", hcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateHcidWithMinByKey(final int id, final int hcid, final int _min){
        return updateHcidWithMinByKey(id, hcid, _min, TABLENAME);
    }

    public int updateHcidWithMinByKey(final int id, final int hcid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hcid = (select case when hcid+:hcid<=:_min then :_min else hcid+:hcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("hcid", hcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateHcidWithMinInKeys(final List<Integer> keys, final int hcid, final int _min){
        return updateHcidWithMinInKeys(keys, hcid, _min, TABLENAME);
    }

    public int updateHcidWithMinInKeys(final List<Integer> keys, final int hcid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hcid = (select case when hcid+:hcid<=:_min then :_min else hcid+:hcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("hcid", hcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateHcidWithMaxByKey(final int id, final int hcid, final int _max){
        return updateHcidWithMaxByKey(id, hcid, _max, TABLENAME);
    }

    public int updateHcidWithMaxByKey(final int id, final int hcid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hcid = (select case when hcid+:hcid>=:_max then :_max else hcid+:hcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("hcid", hcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateHcidWithMaxInKeys(final List<Integer> keys, final int hcid, final int _max){
        return updateHcidWithMaxInKeys(keys, hcid, _max, TABLENAME);
    }

    public int updateHcidWithMaxInKeys(final List<Integer> keys, final int hcid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hcid = (select case when hcid+:hcid>=:_max then :_max else hcid+:hcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("hcid", hcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateHcidWithMinMaxByKey(final int id, final int hcid, final int _min, final int _max){
        return updateHcidWithMinMaxByKey(id, hcid, _min, _max, TABLENAME);
    }

    public int updateHcidWithMinMaxByKey(final int id, final int hcid, final int _min, final int _max, final String TABLENAME2){
        if( hcid < 0 ) {
            return updateHcidWithMinByKey(id, hcid, _min, TABLENAME2);
        } else {
            return updateHcidWithMaxByKey(id, hcid, _max, TABLENAME2);
        }
    }

    public int updateHcidWithMinMaxInKeys(final List<Integer> keys, final int hcid, final int _min, final int _max){
        return updateHcidWithMinMaxInKeys(keys, hcid, _min, _max, TABLENAME);
    }

    public int updateHcidWithMinMaxInKeys(final List<Integer> keys, final int hcid, final int _min, final int _max, final String TABLENAME2){
        if( hcid < 0 ) {
            return updateHcidWithMinInKeys(keys, hcid, _min, TABLENAME2);
        } else {
            return updateHcidWithMaxInKeys(keys, hcid, _max, TABLENAME2);
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

    public int updateHgidByKey(final int hgid, final int id){
        return updateHgidByKey(hgid, id, TABLENAME);
    }

    public int updateHgidByKey(final int hgid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hgid=hgid+:hgid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("hgid", hgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateHgidWithMinByKey(final int id, final int hgid, final int _min){
        return updateHgidWithMinByKey(id, hgid, _min, TABLENAME);
    }

    public int updateHgidWithMinByKey(final int id, final int hgid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hgid = (select case when hgid+:hgid<=:_min then :_min else hgid+:hgid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("hgid", hgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateHgidWithMinInKeys(final List<Integer> keys, final int hgid, final int _min){
        return updateHgidWithMinInKeys(keys, hgid, _min, TABLENAME);
    }

    public int updateHgidWithMinInKeys(final List<Integer> keys, final int hgid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hgid = (select case when hgid+:hgid<=:_min then :_min else hgid+:hgid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("hgid", hgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateHgidWithMaxByKey(final int id, final int hgid, final int _max){
        return updateHgidWithMaxByKey(id, hgid, _max, TABLENAME);
    }

    public int updateHgidWithMaxByKey(final int id, final int hgid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hgid = (select case when hgid+:hgid>=:_max then :_max else hgid+:hgid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("hgid", hgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateHgidWithMaxInKeys(final List<Integer> keys, final int hgid, final int _max){
        return updateHgidWithMaxInKeys(keys, hgid, _max, TABLENAME);
    }

    public int updateHgidWithMaxInKeys(final List<Integer> keys, final int hgid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hgid = (select case when hgid+:hgid>=:_max then :_max else hgid+:hgid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("hgid", hgid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateHgidWithMinMaxByKey(final int id, final int hgid, final int _min, final int _max){
        return updateHgidWithMinMaxByKey(id, hgid, _min, _max, TABLENAME);
    }

    public int updateHgidWithMinMaxByKey(final int id, final int hgid, final int _min, final int _max, final String TABLENAME2){
        if( hgid < 0 ) {
            return updateHgidWithMinByKey(id, hgid, _min, TABLENAME2);
        } else {
            return updateHgidWithMaxByKey(id, hgid, _max, TABLENAME2);
        }
    }

    public int updateHgidWithMinMaxInKeys(final List<Integer> keys, final int hgid, final int _min, final int _max){
        return updateHgidWithMinMaxInKeys(keys, hgid, _min, _max, TABLENAME);
    }

    public int updateHgidWithMinMaxInKeys(final List<Integer> keys, final int hgid, final int _min, final int _max, final String TABLENAME2){
        if( hgid < 0 ) {
            return updateHgidWithMinInKeys(keys, hgid, _min, TABLENAME2);
        } else {
            return updateHgidWithMaxInKeys(keys, hgid, _max, TABLENAME2);
        }
    }

    public int updateAdddamageByKey(final int addDamage, final int id){
        return updateAdddamageByKey(addDamage, id, TABLENAME);
    }

    public int updateAdddamageByKey(final int addDamage, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addDamage=addDamage+:addDamage WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("addDamage", addDamage);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAdddamageWithMinByKey(final int id, final int addDamage, final int _min){
        return updateAdddamageWithMinByKey(id, addDamage, _min, TABLENAME);
    }

    public int updateAdddamageWithMinByKey(final int id, final int addDamage, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addDamage = (select case when addDamage+:addDamage<=:_min then :_min else addDamage+:addDamage end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("addDamage", addDamage);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAdddamageWithMinInKeys(final List<Integer> keys, final int addDamage, final int _min){
        return updateAdddamageWithMinInKeys(keys, addDamage, _min, TABLENAME);
    }

    public int updateAdddamageWithMinInKeys(final List<Integer> keys, final int addDamage, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addDamage = (select case when addDamage+:addDamage<=:_min then :_min else addDamage+:addDamage end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("addDamage", addDamage);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAdddamageWithMaxByKey(final int id, final int addDamage, final int _max){
        return updateAdddamageWithMaxByKey(id, addDamage, _max, TABLENAME);
    }

    public int updateAdddamageWithMaxByKey(final int id, final int addDamage, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addDamage = (select case when addDamage+:addDamage>=:_max then :_max else addDamage+:addDamage end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("addDamage", addDamage);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAdddamageWithMaxInKeys(final List<Integer> keys, final int addDamage, final int _max){
        return updateAdddamageWithMaxInKeys(keys, addDamage, _max, TABLENAME);
    }

    public int updateAdddamageWithMaxInKeys(final List<Integer> keys, final int addDamage, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addDamage = (select case when addDamage+:addDamage>=:_max then :_max else addDamage+:addDamage end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("addDamage", addDamage);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAdddamageWithMinMaxByKey(final int id, final int addDamage, final int _min, final int _max){
        return updateAdddamageWithMinMaxByKey(id, addDamage, _min, _max, TABLENAME);
    }

    public int updateAdddamageWithMinMaxByKey(final int id, final int addDamage, final int _min, final int _max, final String TABLENAME2){
        if( addDamage < 0 ) {
            return updateAdddamageWithMinByKey(id, addDamage, _min, TABLENAME2);
        } else {
            return updateAdddamageWithMaxByKey(id, addDamage, _max, TABLENAME2);
        }
    }

    public int updateAdddamageWithMinMaxInKeys(final List<Integer> keys, final int addDamage, final int _min, final int _max){
        return updateAdddamageWithMinMaxInKeys(keys, addDamage, _min, _max, TABLENAME);
    }

    public int updateAdddamageWithMinMaxInKeys(final List<Integer> keys, final int addDamage, final int _min, final int _max, final String TABLENAME2){
        if( addDamage < 0 ) {
            return updateAdddamageWithMinInKeys(keys, addDamage, _min, TABLENAME2);
        } else {
            return updateAdddamageWithMaxInKeys(keys, addDamage, _max, TABLENAME2);
        }
    }

    public int updateMaxdamageByKey(final int maxDamage, final int id){
        return updateMaxdamageByKey(maxDamage, id, TABLENAME);
    }

    public int updateMaxdamageByKey(final int maxDamage, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxDamage=maxDamage+:maxDamage WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("maxDamage", maxDamage);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxdamageWithMinByKey(final int id, final int maxDamage, final int _min){
        return updateMaxdamageWithMinByKey(id, maxDamage, _min, TABLENAME);
    }

    public int updateMaxdamageWithMinByKey(final int id, final int maxDamage, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxDamage = (select case when maxDamage+:maxDamage<=:_min then :_min else maxDamage+:maxDamage end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("maxDamage", maxDamage);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxdamageWithMinInKeys(final List<Integer> keys, final int maxDamage, final int _min){
        return updateMaxdamageWithMinInKeys(keys, maxDamage, _min, TABLENAME);
    }

    public int updateMaxdamageWithMinInKeys(final List<Integer> keys, final int maxDamage, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxDamage = (select case when maxDamage+:maxDamage<=:_min then :_min else maxDamage+:maxDamage end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("maxDamage", maxDamage);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxdamageWithMaxByKey(final int id, final int maxDamage, final int _max){
        return updateMaxdamageWithMaxByKey(id, maxDamage, _max, TABLENAME);
    }

    public int updateMaxdamageWithMaxByKey(final int id, final int maxDamage, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxDamage = (select case when maxDamage+:maxDamage>=:_max then :_max else maxDamage+:maxDamage end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("maxDamage", maxDamage);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxdamageWithMaxInKeys(final List<Integer> keys, final int maxDamage, final int _max){
        return updateMaxdamageWithMaxInKeys(keys, maxDamage, _max, TABLENAME);
    }

    public int updateMaxdamageWithMaxInKeys(final List<Integer> keys, final int maxDamage, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxDamage = (select case when maxDamage+:maxDamage>=:_max then :_max else maxDamage+:maxDamage end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("maxDamage", maxDamage);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxdamageWithMinMaxByKey(final int id, final int maxDamage, final int _min, final int _max){
        return updateMaxdamageWithMinMaxByKey(id, maxDamage, _min, _max, TABLENAME);
    }

    public int updateMaxdamageWithMinMaxByKey(final int id, final int maxDamage, final int _min, final int _max, final String TABLENAME2){
        if( maxDamage < 0 ) {
            return updateMaxdamageWithMinByKey(id, maxDamage, _min, TABLENAME2);
        } else {
            return updateMaxdamageWithMaxByKey(id, maxDamage, _max, TABLENAME2);
        }
    }

    public int updateMaxdamageWithMinMaxInKeys(final List<Integer> keys, final int maxDamage, final int _min, final int _max){
        return updateMaxdamageWithMinMaxInKeys(keys, maxDamage, _min, _max, TABLENAME);
    }

    public int updateMaxdamageWithMinMaxInKeys(final List<Integer> keys, final int maxDamage, final int _min, final int _max, final String TABLENAME2){
        if( maxDamage < 0 ) {
            return updateMaxdamageWithMinInKeys(keys, maxDamage, _min, TABLENAME2);
        } else {
            return updateMaxdamageWithMaxInKeys(keys, maxDamage, _max, TABLENAME2);
        }
    }

    public int updateAddhpByKey(final int addHP, final int id){
        return updateAddhpByKey(addHP, id, TABLENAME);
    }

    public int updateAddhpByKey(final int addHP, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addHP=addHP+:addHP WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("addHP", addHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAddhpWithMinByKey(final int id, final int addHP, final int _min){
        return updateAddhpWithMinByKey(id, addHP, _min, TABLENAME);
    }

    public int updateAddhpWithMinByKey(final int id, final int addHP, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addHP = (select case when addHP+:addHP<=:_min then :_min else addHP+:addHP end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("addHP", addHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAddhpWithMinInKeys(final List<Integer> keys, final int addHP, final int _min){
        return updateAddhpWithMinInKeys(keys, addHP, _min, TABLENAME);
    }

    public int updateAddhpWithMinInKeys(final List<Integer> keys, final int addHP, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addHP = (select case when addHP+:addHP<=:_min then :_min else addHP+:addHP end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("addHP", addHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAddhpWithMaxByKey(final int id, final int addHP, final int _max){
        return updateAddhpWithMaxByKey(id, addHP, _max, TABLENAME);
    }

    public int updateAddhpWithMaxByKey(final int id, final int addHP, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addHP = (select case when addHP+:addHP>=:_max then :_max else addHP+:addHP end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("addHP", addHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAddhpWithMaxInKeys(final List<Integer> keys, final int addHP, final int _max){
        return updateAddhpWithMaxInKeys(keys, addHP, _max, TABLENAME);
    }

    public int updateAddhpWithMaxInKeys(final List<Integer> keys, final int addHP, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addHP = (select case when addHP+:addHP>=:_max then :_max else addHP+:addHP end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("addHP", addHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAddhpWithMinMaxByKey(final int id, final int addHP, final int _min, final int _max){
        return updateAddhpWithMinMaxByKey(id, addHP, _min, _max, TABLENAME);
    }

    public int updateAddhpWithMinMaxByKey(final int id, final int addHP, final int _min, final int _max, final String TABLENAME2){
        if( addHP < 0 ) {
            return updateAddhpWithMinByKey(id, addHP, _min, TABLENAME2);
        } else {
            return updateAddhpWithMaxByKey(id, addHP, _max, TABLENAME2);
        }
    }

    public int updateAddhpWithMinMaxInKeys(final List<Integer> keys, final int addHP, final int _min, final int _max){
        return updateAddhpWithMinMaxInKeys(keys, addHP, _min, _max, TABLENAME);
    }

    public int updateAddhpWithMinMaxInKeys(final List<Integer> keys, final int addHP, final int _min, final int _max, final String TABLENAME2){
        if( addHP < 0 ) {
            return updateAddhpWithMinInKeys(keys, addHP, _min, TABLENAME2);
        } else {
            return updateAddhpWithMaxInKeys(keys, addHP, _max, TABLENAME2);
        }
    }

    public int updateMaxhpByKey(final int maxHP, final int id){
        return updateMaxhpByKey(maxHP, id, TABLENAME);
    }

    public int updateMaxhpByKey(final int maxHP, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxHP=maxHP+:maxHP WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("maxHP", maxHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxhpWithMinByKey(final int id, final int maxHP, final int _min){
        return updateMaxhpWithMinByKey(id, maxHP, _min, TABLENAME);
    }

    public int updateMaxhpWithMinByKey(final int id, final int maxHP, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxHP = (select case when maxHP+:maxHP<=:_min then :_min else maxHP+:maxHP end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("maxHP", maxHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxhpWithMinInKeys(final List<Integer> keys, final int maxHP, final int _min){
        return updateMaxhpWithMinInKeys(keys, maxHP, _min, TABLENAME);
    }

    public int updateMaxhpWithMinInKeys(final List<Integer> keys, final int maxHP, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxHP = (select case when maxHP+:maxHP<=:_min then :_min else maxHP+:maxHP end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("maxHP", maxHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxhpWithMaxByKey(final int id, final int maxHP, final int _max){
        return updateMaxhpWithMaxByKey(id, maxHP, _max, TABLENAME);
    }

    public int updateMaxhpWithMaxByKey(final int id, final int maxHP, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxHP = (select case when maxHP+:maxHP>=:_max then :_max else maxHP+:maxHP end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("maxHP", maxHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxhpWithMaxInKeys(final List<Integer> keys, final int maxHP, final int _max){
        return updateMaxhpWithMaxInKeys(keys, maxHP, _max, TABLENAME);
    }

    public int updateMaxhpWithMaxInKeys(final List<Integer> keys, final int maxHP, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxHP = (select case when maxHP+:maxHP>=:_max then :_max else maxHP+:maxHP end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("maxHP", maxHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxhpWithMinMaxByKey(final int id, final int maxHP, final int _min, final int _max){
        return updateMaxhpWithMinMaxByKey(id, maxHP, _min, _max, TABLENAME);
    }

    public int updateMaxhpWithMinMaxByKey(final int id, final int maxHP, final int _min, final int _max, final String TABLENAME2){
        if( maxHP < 0 ) {
            return updateMaxhpWithMinByKey(id, maxHP, _min, TABLENAME2);
        } else {
            return updateMaxhpWithMaxByKey(id, maxHP, _max, TABLENAME2);
        }
    }

    public int updateMaxhpWithMinMaxInKeys(final List<Integer> keys, final int maxHP, final int _min, final int _max){
        return updateMaxhpWithMinMaxInKeys(keys, maxHP, _min, _max, TABLENAME);
    }

    public int updateMaxhpWithMinMaxInKeys(final List<Integer> keys, final int maxHP, final int _min, final int _max, final String TABLENAME2){
        if( maxHP < 0 ) {
            return updateMaxhpWithMinInKeys(keys, maxHP, _min, TABLENAME2);
        } else {
            return updateMaxhpWithMaxInKeys(keys, maxHP, _max, TABLENAME2);
        }
    }

    public int updateAddattspeedByKey(final int addAttSpeed, final int id){
        return updateAddattspeedByKey(addAttSpeed, id, TABLENAME);
    }

    public int updateAddattspeedByKey(final int addAttSpeed, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addAttSpeed=addAttSpeed+:addAttSpeed WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("addAttSpeed", addAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAddattspeedWithMinByKey(final int id, final int addAttSpeed, final int _min){
        return updateAddattspeedWithMinByKey(id, addAttSpeed, _min, TABLENAME);
    }

    public int updateAddattspeedWithMinByKey(final int id, final int addAttSpeed, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addAttSpeed = (select case when addAttSpeed+:addAttSpeed<=:_min then :_min else addAttSpeed+:addAttSpeed end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("addAttSpeed", addAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAddattspeedWithMinInKeys(final List<Integer> keys, final int addAttSpeed, final int _min){
        return updateAddattspeedWithMinInKeys(keys, addAttSpeed, _min, TABLENAME);
    }

    public int updateAddattspeedWithMinInKeys(final List<Integer> keys, final int addAttSpeed, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addAttSpeed = (select case when addAttSpeed+:addAttSpeed<=:_min then :_min else addAttSpeed+:addAttSpeed end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("addAttSpeed", addAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAddattspeedWithMaxByKey(final int id, final int addAttSpeed, final int _max){
        return updateAddattspeedWithMaxByKey(id, addAttSpeed, _max, TABLENAME);
    }

    public int updateAddattspeedWithMaxByKey(final int id, final int addAttSpeed, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addAttSpeed = (select case when addAttSpeed+:addAttSpeed>=:_max then :_max else addAttSpeed+:addAttSpeed end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("addAttSpeed", addAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAddattspeedWithMaxInKeys(final List<Integer> keys, final int addAttSpeed, final int _max){
        return updateAddattspeedWithMaxInKeys(keys, addAttSpeed, _max, TABLENAME);
    }

    public int updateAddattspeedWithMaxInKeys(final List<Integer> keys, final int addAttSpeed, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET addAttSpeed = (select case when addAttSpeed+:addAttSpeed>=:_max then :_max else addAttSpeed+:addAttSpeed end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("addAttSpeed", addAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAddattspeedWithMinMaxByKey(final int id, final int addAttSpeed, final int _min, final int _max){
        return updateAddattspeedWithMinMaxByKey(id, addAttSpeed, _min, _max, TABLENAME);
    }

    public int updateAddattspeedWithMinMaxByKey(final int id, final int addAttSpeed, final int _min, final int _max, final String TABLENAME2){
        if( addAttSpeed < 0 ) {
            return updateAddattspeedWithMinByKey(id, addAttSpeed, _min, TABLENAME2);
        } else {
            return updateAddattspeedWithMaxByKey(id, addAttSpeed, _max, TABLENAME2);
        }
    }

    public int updateAddattspeedWithMinMaxInKeys(final List<Integer> keys, final int addAttSpeed, final int _min, final int _max){
        return updateAddattspeedWithMinMaxInKeys(keys, addAttSpeed, _min, _max, TABLENAME);
    }

    public int updateAddattspeedWithMinMaxInKeys(final List<Integer> keys, final int addAttSpeed, final int _min, final int _max, final String TABLENAME2){
        if( addAttSpeed < 0 ) {
            return updateAddattspeedWithMinInKeys(keys, addAttSpeed, _min, TABLENAME2);
        } else {
            return updateAddattspeedWithMaxInKeys(keys, addAttSpeed, _max, TABLENAME2);
        }
    }

    public int updateMaxattspeedByKey(final int maxAttSpeed, final int id){
        return updateMaxattspeedByKey(maxAttSpeed, id, TABLENAME);
    }

    public int updateMaxattspeedByKey(final int maxAttSpeed, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxAttSpeed=maxAttSpeed+:maxAttSpeed WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("maxAttSpeed", maxAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxattspeedWithMinByKey(final int id, final int maxAttSpeed, final int _min){
        return updateMaxattspeedWithMinByKey(id, maxAttSpeed, _min, TABLENAME);
    }

    public int updateMaxattspeedWithMinByKey(final int id, final int maxAttSpeed, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxAttSpeed = (select case when maxAttSpeed+:maxAttSpeed<=:_min then :_min else maxAttSpeed+:maxAttSpeed end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("maxAttSpeed", maxAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxattspeedWithMinInKeys(final List<Integer> keys, final int maxAttSpeed, final int _min){
        return updateMaxattspeedWithMinInKeys(keys, maxAttSpeed, _min, TABLENAME);
    }

    public int updateMaxattspeedWithMinInKeys(final List<Integer> keys, final int maxAttSpeed, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxAttSpeed = (select case when maxAttSpeed+:maxAttSpeed<=:_min then :_min else maxAttSpeed+:maxAttSpeed end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("maxAttSpeed", maxAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxattspeedWithMaxByKey(final int id, final int maxAttSpeed, final int _max){
        return updateMaxattspeedWithMaxByKey(id, maxAttSpeed, _max, TABLENAME);
    }

    public int updateMaxattspeedWithMaxByKey(final int id, final int maxAttSpeed, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxAttSpeed = (select case when maxAttSpeed+:maxAttSpeed>=:_max then :_max else maxAttSpeed+:maxAttSpeed end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("maxAttSpeed", maxAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxattspeedWithMaxInKeys(final List<Integer> keys, final int maxAttSpeed, final int _max){
        return updateMaxattspeedWithMaxInKeys(keys, maxAttSpeed, _max, TABLENAME);
    }

    public int updateMaxattspeedWithMaxInKeys(final List<Integer> keys, final int maxAttSpeed, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxAttSpeed = (select case when maxAttSpeed+:maxAttSpeed>=:_max then :_max else maxAttSpeed+:maxAttSpeed end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("maxAttSpeed", maxAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxattspeedWithMinMaxByKey(final int id, final int maxAttSpeed, final int _min, final int _max){
        return updateMaxattspeedWithMinMaxByKey(id, maxAttSpeed, _min, _max, TABLENAME);
    }

    public int updateMaxattspeedWithMinMaxByKey(final int id, final int maxAttSpeed, final int _min, final int _max, final String TABLENAME2){
        if( maxAttSpeed < 0 ) {
            return updateMaxattspeedWithMinByKey(id, maxAttSpeed, _min, TABLENAME2);
        } else {
            return updateMaxattspeedWithMaxByKey(id, maxAttSpeed, _max, TABLENAME2);
        }
    }

    public int updateMaxattspeedWithMinMaxInKeys(final List<Integer> keys, final int maxAttSpeed, final int _min, final int _max){
        return updateMaxattspeedWithMinMaxInKeys(keys, maxAttSpeed, _min, _max, TABLENAME);
    }

    public int updateMaxattspeedWithMinMaxInKeys(final List<Integer> keys, final int maxAttSpeed, final int _min, final int _max, final String TABLENAME2){
        if( maxAttSpeed < 0 ) {
            return updateMaxattspeedWithMinInKeys(keys, maxAttSpeed, _min, TABLENAME2);
        } else {
            return updateMaxattspeedWithMaxInKeys(keys, maxAttSpeed, _max, TABLENAME2);
        }
    }

    public int updateStatuesByKey(final int statues, final int id){
        return updateStatuesByKey(statues, id, TABLENAME);
    }

    public int updateStatuesByKey(final int statues, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statues=statues+:statues WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("statues", statues);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatuesWithMinByKey(final int id, final int statues, final int _min){
        return updateStatuesWithMinByKey(id, statues, _min, TABLENAME);
    }

    public int updateStatuesWithMinByKey(final int id, final int statues, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statues = (select case when statues+:statues<=:_min then :_min else statues+:statues end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("statues", statues);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatuesWithMinInKeys(final List<Integer> keys, final int statues, final int _min){
        return updateStatuesWithMinInKeys(keys, statues, _min, TABLENAME);
    }

    public int updateStatuesWithMinInKeys(final List<Integer> keys, final int statues, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statues = (select case when statues+:statues<=:_min then :_min else statues+:statues end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("statues", statues);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatuesWithMaxByKey(final int id, final int statues, final int _max){
        return updateStatuesWithMaxByKey(id, statues, _max, TABLENAME);
    }

    public int updateStatuesWithMaxByKey(final int id, final int statues, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statues = (select case when statues+:statues>=:_max then :_max else statues+:statues end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("statues", statues);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatuesWithMaxInKeys(final List<Integer> keys, final int statues, final int _max){
        return updateStatuesWithMaxInKeys(keys, statues, _max, TABLENAME);
    }

    public int updateStatuesWithMaxInKeys(final List<Integer> keys, final int statues, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statues = (select case when statues+:statues>=:_max then :_max else statues+:statues end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("statues", statues);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatuesWithMinMaxByKey(final int id, final int statues, final int _min, final int _max){
        return updateStatuesWithMinMaxByKey(id, statues, _min, _max, TABLENAME);
    }

    public int updateStatuesWithMinMaxByKey(final int id, final int statues, final int _min, final int _max, final String TABLENAME2){
        if( statues < 0 ) {
            return updateStatuesWithMinByKey(id, statues, _min, TABLENAME2);
        } else {
            return updateStatuesWithMaxByKey(id, statues, _max, TABLENAME2);
        }
    }

    public int updateStatuesWithMinMaxInKeys(final List<Integer> keys, final int statues, final int _min, final int _max){
        return updateStatuesWithMinMaxInKeys(keys, statues, _min, _max, TABLENAME);
    }

    public int updateStatuesWithMinMaxInKeys(final List<Integer> keys, final int statues, final int _min, final int _max, final String TABLENAME2){
        if( statues < 0 ) {
            return updateStatuesWithMinInKeys(keys, statues, _min, TABLENAME2);
        } else {
            return updateStatuesWithMaxInKeys(keys, statues, _max, TABLENAME2);
        }
    }

    public int updateCreatetimeByKey(final long createTime, final int id){
        return updateCreatetimeByKey(createTime, id, TABLENAME);
    }

    public int updateCreatetimeByKey(final long createTime, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET createTime=createTime+:createTime WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("createTime", createTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreatetimeWithMinByKey(final int id, final long createTime, final long _min){
        return updateCreatetimeWithMinByKey(id, createTime, _min, TABLENAME);
    }

    public int updateCreatetimeWithMinByKey(final int id, final long createTime, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET createTime = (select case when createTime+:createTime<=:_min then :_min else createTime+:createTime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("createTime", createTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreatetimeWithMinInKeys(final List<Integer> keys, final long createTime, final long _min){
        return updateCreatetimeWithMinInKeys(keys, createTime, _min, TABLENAME);
    }

    public int updateCreatetimeWithMinInKeys(final List<Integer> keys, final long createTime, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET createTime = (select case when createTime+:createTime<=:_min then :_min else createTime+:createTime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("createTime", createTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreatetimeWithMaxByKey(final int id, final long createTime, final long _max){
        return updateCreatetimeWithMaxByKey(id, createTime, _max, TABLENAME);
    }

    public int updateCreatetimeWithMaxByKey(final int id, final long createTime, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET createTime = (select case when createTime+:createTime>=:_max then :_max else createTime+:createTime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("createTime", createTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreatetimeWithMaxInKeys(final List<Integer> keys, final long createTime, final long _max){
        return updateCreatetimeWithMaxInKeys(keys, createTime, _max, TABLENAME);
    }

    public int updateCreatetimeWithMaxInKeys(final List<Integer> keys, final long createTime, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET createTime = (select case when createTime+:createTime>=:_max then :_max else createTime+:createTime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("createTime", createTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreatetimeWithMinMaxByKey(final int id, final long createTime, final long _min, final long _max){
        return updateCreatetimeWithMinMaxByKey(id, createTime, _min, _max, TABLENAME);
    }

    public int updateCreatetimeWithMinMaxByKey(final int id, final long createTime, final long _min, final long _max, final String TABLENAME2){
        if( createTime < 0 ) {
            return updateCreatetimeWithMinByKey(id, createTime, _min, TABLENAME2);
        } else {
            return updateCreatetimeWithMaxByKey(id, createTime, _max, TABLENAME2);
        }
    }

    public int updateCreatetimeWithMinMaxInKeys(final List<Integer> keys, final long createTime, final long _min, final long _max){
        return updateCreatetimeWithMinMaxInKeys(keys, createTime, _min, _max, TABLENAME);
    }

    public int updateCreatetimeWithMinMaxInKeys(final List<Integer> keys, final long createTime, final long _min, final long _max, final String TABLENAME2){
        if( createTime < 0 ) {
            return updateCreatetimeWithMinInKeys(keys, createTime, _min, TABLENAME2);
        } else {
            return updateCreatetimeWithMaxInKeys(keys, createTime, _max, TABLENAME2);
        }
    }

    public int updateDeadtimeByKey(final long deadTime, final int id){
        return updateDeadtimeByKey(deadTime, id, TABLENAME);
    }

    public int updateDeadtimeByKey(final long deadTime, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET deadTime=deadTime+:deadTime WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("deadTime", deadTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDeadtimeWithMinByKey(final int id, final long deadTime, final long _min){
        return updateDeadtimeWithMinByKey(id, deadTime, _min, TABLENAME);
    }

    public int updateDeadtimeWithMinByKey(final int id, final long deadTime, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET deadTime = (select case when deadTime+:deadTime<=:_min then :_min else deadTime+:deadTime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("deadTime", deadTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDeadtimeWithMinInKeys(final List<Integer> keys, final long deadTime, final long _min){
        return updateDeadtimeWithMinInKeys(keys, deadTime, _min, TABLENAME);
    }

    public int updateDeadtimeWithMinInKeys(final List<Integer> keys, final long deadTime, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET deadTime = (select case when deadTime+:deadTime<=:_min then :_min else deadTime+:deadTime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("deadTime", deadTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDeadtimeWithMaxByKey(final int id, final long deadTime, final long _max){
        return updateDeadtimeWithMaxByKey(id, deadTime, _max, TABLENAME);
    }

    public int updateDeadtimeWithMaxByKey(final int id, final long deadTime, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET deadTime = (select case when deadTime+:deadTime>=:_max then :_max else deadTime+:deadTime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("deadTime", deadTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDeadtimeWithMaxInKeys(final List<Integer> keys, final long deadTime, final long _max){
        return updateDeadtimeWithMaxInKeys(keys, deadTime, _max, TABLENAME);
    }

    public int updateDeadtimeWithMaxInKeys(final List<Integer> keys, final long deadTime, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET deadTime = (select case when deadTime+:deadTime>=:_max then :_max else deadTime+:deadTime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("deadTime", deadTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDeadtimeWithMinMaxByKey(final int id, final long deadTime, final long _min, final long _max){
        return updateDeadtimeWithMinMaxByKey(id, deadTime, _min, _max, TABLENAME);
    }

    public int updateDeadtimeWithMinMaxByKey(final int id, final long deadTime, final long _min, final long _max, final String TABLENAME2){
        if( deadTime < 0 ) {
            return updateDeadtimeWithMinByKey(id, deadTime, _min, TABLENAME2);
        } else {
            return updateDeadtimeWithMaxByKey(id, deadTime, _max, TABLENAME2);
        }
    }

    public int updateDeadtimeWithMinMaxInKeys(final List<Integer> keys, final long deadTime, final long _min, final long _max){
        return updateDeadtimeWithMinMaxInKeys(keys, deadTime, _min, _max, TABLENAME);
    }

    public int updateDeadtimeWithMinMaxInKeys(final List<Integer> keys, final long deadTime, final long _min, final long _max, final String TABLENAME2){
        if( deadTime < 0 ) {
            return updateDeadtimeWithMinInKeys(keys, deadTime, _min, TABLENAME2);
        } else {
            return updateDeadtimeWithMaxInKeys(keys, deadTime, _max, TABLENAME2);
        }
    }

    public int updateSkillgidByKey(final int skillGid, final int id){
        return updateSkillgidByKey(skillGid, id, TABLENAME);
    }

    public int updateSkillgidByKey(final int skillGid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET skillGid=skillGid+:skillGid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("skillGid", skillGid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSkillgidWithMinByKey(final int id, final int skillGid, final int _min){
        return updateSkillgidWithMinByKey(id, skillGid, _min, TABLENAME);
    }

    public int updateSkillgidWithMinByKey(final int id, final int skillGid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET skillGid = (select case when skillGid+:skillGid<=:_min then :_min else skillGid+:skillGid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("skillGid", skillGid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSkillgidWithMinInKeys(final List<Integer> keys, final int skillGid, final int _min){
        return updateSkillgidWithMinInKeys(keys, skillGid, _min, TABLENAME);
    }

    public int updateSkillgidWithMinInKeys(final List<Integer> keys, final int skillGid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET skillGid = (select case when skillGid+:skillGid<=:_min then :_min else skillGid+:skillGid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("skillGid", skillGid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSkillgidWithMaxByKey(final int id, final int skillGid, final int _max){
        return updateSkillgidWithMaxByKey(id, skillGid, _max, TABLENAME);
    }

    public int updateSkillgidWithMaxByKey(final int id, final int skillGid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET skillGid = (select case when skillGid+:skillGid>=:_max then :_max else skillGid+:skillGid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("skillGid", skillGid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSkillgidWithMaxInKeys(final List<Integer> keys, final int skillGid, final int _max){
        return updateSkillgidWithMaxInKeys(keys, skillGid, _max, TABLENAME);
    }

    public int updateSkillgidWithMaxInKeys(final List<Integer> keys, final int skillGid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET skillGid = (select case when skillGid+:skillGid>=:_max then :_max else skillGid+:skillGid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("skillGid", skillGid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSkillgidWithMinMaxByKey(final int id, final int skillGid, final int _min, final int _max){
        return updateSkillgidWithMinMaxByKey(id, skillGid, _min, _max, TABLENAME);
    }

    public int updateSkillgidWithMinMaxByKey(final int id, final int skillGid, final int _min, final int _max, final String TABLENAME2){
        if( skillGid < 0 ) {
            return updateSkillgidWithMinByKey(id, skillGid, _min, TABLENAME2);
        } else {
            return updateSkillgidWithMaxByKey(id, skillGid, _max, TABLENAME2);
        }
    }

    public int updateSkillgidWithMinMaxInKeys(final List<Integer> keys, final int skillGid, final int _min, final int _max){
        return updateSkillgidWithMinMaxInKeys(keys, skillGid, _min, _max, TABLENAME);
    }

    public int updateSkillgidWithMinMaxInKeys(final List<Integer> keys, final int skillGid, final int _min, final int _max, final String TABLENAME2){
        if( skillGid < 0 ) {
            return updateSkillgidWithMinInKeys(keys, skillGid, _min, TABLENAME2);
        } else {
            return updateSkillgidWithMaxInKeys(keys, skillGid, _max, TABLENAME2);
        }
    }

    public int updateFposByKey(final int fpos, final int id){
        return updateFposByKey(fpos, id, TABLENAME);
    }

    public int updateFposByKey(final int fpos, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fpos=fpos+:fpos WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("fpos", fpos);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFposWithMinByKey(final int id, final int fpos, final int _min){
        return updateFposWithMinByKey(id, fpos, _min, TABLENAME);
    }

    public int updateFposWithMinByKey(final int id, final int fpos, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fpos = (select case when fpos+:fpos<=:_min then :_min else fpos+:fpos end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("fpos", fpos);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFposWithMinInKeys(final List<Integer> keys, final int fpos, final int _min){
        return updateFposWithMinInKeys(keys, fpos, _min, TABLENAME);
    }

    public int updateFposWithMinInKeys(final List<Integer> keys, final int fpos, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fpos = (select case when fpos+:fpos<=:_min then :_min else fpos+:fpos end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("fpos", fpos);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFposWithMaxByKey(final int id, final int fpos, final int _max){
        return updateFposWithMaxByKey(id, fpos, _max, TABLENAME);
    }

    public int updateFposWithMaxByKey(final int id, final int fpos, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fpos = (select case when fpos+:fpos>=:_max then :_max else fpos+:fpos end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("fpos", fpos);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFposWithMaxInKeys(final List<Integer> keys, final int fpos, final int _max){
        return updateFposWithMaxInKeys(keys, fpos, _max, TABLENAME);
    }

    public int updateFposWithMaxInKeys(final List<Integer> keys, final int fpos, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fpos = (select case when fpos+:fpos>=:_max then :_max else fpos+:fpos end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("fpos", fpos);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFposWithMinMaxByKey(final int id, final int fpos, final int _min, final int _max){
        return updateFposWithMinMaxByKey(id, fpos, _min, _max, TABLENAME);
    }

    public int updateFposWithMinMaxByKey(final int id, final int fpos, final int _min, final int _max, final String TABLENAME2){
        if( fpos < 0 ) {
            return updateFposWithMinByKey(id, fpos, _min, TABLENAME2);
        } else {
            return updateFposWithMaxByKey(id, fpos, _max, TABLENAME2);
        }
    }

    public int updateFposWithMinMaxInKeys(final List<Integer> keys, final int fpos, final int _min, final int _max){
        return updateFposWithMinMaxInKeys(keys, fpos, _min, _max, TABLENAME);
    }

    public int updateFposWithMinMaxInKeys(final List<Integer> keys, final int fpos, final int _min, final int _max, final String TABLENAME2){
        if( fpos < 0 ) {
            return updateFposWithMinInKeys(keys, fpos, _min, TABLENAME2);
        } else {
            return updateFposWithMaxInKeys(keys, fpos, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Player_hero> player_heros) {
        return updateByKey(player_heros, TABLENAME);
    }

    public int[] updateByKey (final List<Player_hero> player_heros, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(player_heros == null || player_heros.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET hcid=:hcid, pcid=:pcid, pname=:pname, hname=:hname, hgid=:hgid, addDamage=:addDamage, maxDamage=:maxDamage, addHP=:addHP, maxHP=:maxHP, addAttSpeed=:addAttSpeed, maxAttSpeed=:maxAttSpeed, statues=:statues, createTime=:createTime, deadTime=:deadTime, skillGid=:skillGid, fpos=:fpos WHERE id=:id");
            return super.batchUpdate2(sql.toString(), player_heros);
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
                "	`hcid`  INT(11) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`hname`  VARCHAR(32) NOT NULL," +
                "	`hgid`  INT(11) NOT NULL," +
                "	`addDamage`  INT(11) NOT NULL," +
                "	`maxDamage`  INT(11) NOT NULL," +
                "	`addHP`  INT(11) NOT NULL," +
                "	`maxHP`  INT(11) NOT NULL," +
                "	`addAttSpeed`  INT(11) NOT NULL," +
                "	`maxAttSpeed`  INT(11) NOT NULL," +
                "	`statues`  INT(11) NOT NULL," +
                "	`createTime`  BIGINT(20) NOT NULL," +
                "	`deadTime`  BIGINT(20) NOT NULL," +
                "	`skillGid`  INT(11) NOT NULL," +
                "	`fpos`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `hcid` (`hcid`)," +
                "	UNIQUE KEY `pcid` (`pcid`, `hgid`)" +
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
                "	`hcid`  INT(11) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`hname`  VARCHAR(32) NOT NULL," +
                "	`hgid`  INT(11) NOT NULL," +
                "	`addDamage`  INT(11) NOT NULL," +
                "	`maxDamage`  INT(11) NOT NULL," +
                "	`addHP`  INT(11) NOT NULL," +
                "	`maxHP`  INT(11) NOT NULL," +
                "	`addAttSpeed`  INT(11) NOT NULL," +
                "	`maxAttSpeed`  INT(11) NOT NULL," +
                "	`statues`  INT(11) NOT NULL," +
                "	`createTime`  BIGINT(20) NOT NULL," +
                "	`deadTime`  BIGINT(20) NOT NULL," +
                "	`skillGid`  INT(11) NOT NULL," +
                "	`fpos`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `hcid` (`hcid`)," +
                "	KEY `pcid` (`pcid`, `hgid`)" +
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
