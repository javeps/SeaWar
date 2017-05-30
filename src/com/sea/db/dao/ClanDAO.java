package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - clan
@SuppressWarnings({"rawtypes", "unchecked"})
public class ClanDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(ClanDAO.class);

    public static final String TABLE = "clan";
    public static String TABLENAME = "clan";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "ccid", "clan_name", "icon", "lvl", "desc", "maxNum", "curNum", "create_time", "pointHP", "pointAtt", "renownAll", "renownWeek", "nextHPGold", "nextAttOil", "curDonateGold", "curDonateOil"};
    public static String coulmns = "id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil";
    public static String coulmns2 = "ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil";

    public ClanDAO(java.sql.Connection conn) {
        super(conn);
    }

    public ClanDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public ClanDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Clan clan) {
        return insert(clan, TABLENAME);
    }

    public int insert(final Clan clan, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            clan.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil) VALUES (:ccid, :clan_name, :icon, :lvl, :desc, :maxNum, :curNum, :create_time, :pointHP, :pointAtt, :renownAll, :renownWeek, :nextHPGold, :nextAttOil, :curDonateGold, :curDonateOil)");
            Map map = super.insert(sql.toString(), clan);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Clan clan) {
        asynchronousInsert(clan, TABLENAME);
    }

    public void asynchronousInsert(final Clan clan, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(clan, TABLENAME2);
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

    public int asynchronousInsert2(final Clan clan) {
        return asynchronousInsert2(clan, TABLENAME);
    }

    public int asynchronousInsert2(final Clan clan, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(clan, TABLENAME2);
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
        return clan.id;
    }

    public int insert2(final Clan clan) {
        return insert2(clan, TABLENAME);
    }

    public int insert2(final Clan clan, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            clan.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil) VALUES (:id, :ccid, :clan_name, :icon, :lvl, :desc, :maxNum, :curNum, :create_time, :pointHP, :pointAtt, :renownAll, :renownWeek, :nextHPGold, :nextAttOil, :curDonateGold, :curDonateOil)");
            Map map = super.insert(sql.toString(), clan);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Clan> clans) {
        return insert(clans, TABLENAME);
    }

    public int[] insert(final List<Clan> clans, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(clans == null || clans.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil) VALUES (:ccid, :clan_name, :icon, :lvl, :desc, :maxNum, :curNum, :create_time, :pointHP, :pointAtt, :renownAll, :renownWeek, :nextHPGold, :nextAttOil, :curDonateGold, :curDonateOil)");
            return super.batchInsert(sql.toString(), clans);
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

    public int deleteInBeans(final List<Clan> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Clan> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Clan clan = beans.get(i);
                int id = clan.id;
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

    public List<Clan> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Clan> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Clan.class);
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
            sql.append("SELECT id, ccid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Clan> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Clan.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Clan> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Clan.class);
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

    public List<Clan> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Clan> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Clan.class);
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

    public Clan last() {
        return last(TABLENAME);
    }

    public Clan last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Clan.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Clan> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Clan.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Clan> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Clan.class);
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

    public Clan selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Clan selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Clan.class);
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

    public Clan selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Clan selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Clan.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Clan selectByCcid(final String ccid) {
        return selectByCcid(ccid, TABLENAME);
    }

    public Clan selectByCcid(final String ccid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" WHERE ccid = :ccid");
            Map params = newMap();
            params.put("ccid", ccid);
            return super.queryForObject(sql.toString(), params, Clan.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeCcid(final String ccid) {
        return countLikeCcid(ccid, TABLENAME);
    }

    public int countLikeCcid(final String ccid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE ccid LIKE '%").append(ccid).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan> selectLikeCcid(final String ccid) {
        return selectLikeCcid(ccid, TABLENAME);
    }

    public List<Clan> selectLikeCcid(final String ccid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" WHERE ccid LIKE '%").append(ccid).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), Clan.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeCcidPKs(final String ccid) {
        return selectLikeCcidPKs(ccid, TABLENAME);
    }

    public List<Integer> selectLikeCcidPKs(final String ccid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE ccid LIKE '%").append(ccid).append("%' ORDER BY id ");
            Map params = newMap();
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

    public List<Clan> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Clan> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, clan_name, icon, lvl, desc, maxNum, curNum, create_time, pointHP, pointAtt, renownAll, renownWeek, nextHPGold, nextAttOil, curDonateGold, curDonateOil FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Clan.class);
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

    public int updateByKey(final Clan clan) {
        return updateByKey(clan, TABLENAME);
    }

    public int updateByKey(final Clan clan, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = clan.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), clan);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Clan clan) {
        asynchronousUpdate(clan, TABLENAME);
    }

    public void asynchronousUpdate(final Clan clan, final String TABLENAME2) {
        try {

            String _ustr = clan.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, clan);
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

    public int updateMaxnumByKey(final int maxNum, final int id){
        return updateMaxnumByKey(maxNum, id, TABLENAME);
    }

    public int updateMaxnumByKey(final int maxNum, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxNum=maxNum+:maxNum WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("maxNum", maxNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxnumWithMinByKey(final int id, final int maxNum, final int _min){
        return updateMaxnumWithMinByKey(id, maxNum, _min, TABLENAME);
    }

    public int updateMaxnumWithMinByKey(final int id, final int maxNum, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxNum = (select case when maxNum+:maxNum<=:_min then :_min else maxNum+:maxNum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("maxNum", maxNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxnumWithMinInKeys(final List<Integer> keys, final int maxNum, final int _min){
        return updateMaxnumWithMinInKeys(keys, maxNum, _min, TABLENAME);
    }

    public int updateMaxnumWithMinInKeys(final List<Integer> keys, final int maxNum, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxNum = (select case when maxNum+:maxNum<=:_min then :_min else maxNum+:maxNum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("maxNum", maxNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxnumWithMaxByKey(final int id, final int maxNum, final int _max){
        return updateMaxnumWithMaxByKey(id, maxNum, _max, TABLENAME);
    }

    public int updateMaxnumWithMaxByKey(final int id, final int maxNum, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxNum = (select case when maxNum+:maxNum>=:_max then :_max else maxNum+:maxNum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("maxNum", maxNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxnumWithMaxInKeys(final List<Integer> keys, final int maxNum, final int _max){
        return updateMaxnumWithMaxInKeys(keys, maxNum, _max, TABLENAME);
    }

    public int updateMaxnumWithMaxInKeys(final List<Integer> keys, final int maxNum, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxNum = (select case when maxNum+:maxNum>=:_max then :_max else maxNum+:maxNum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("maxNum", maxNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxnumWithMinMaxByKey(final int id, final int maxNum, final int _min, final int _max){
        return updateMaxnumWithMinMaxByKey(id, maxNum, _min, _max, TABLENAME);
    }

    public int updateMaxnumWithMinMaxByKey(final int id, final int maxNum, final int _min, final int _max, final String TABLENAME2){
        if( maxNum < 0 ) {
            return updateMaxnumWithMinByKey(id, maxNum, _min, TABLENAME2);
        } else {
            return updateMaxnumWithMaxByKey(id, maxNum, _max, TABLENAME2);
        }
    }

    public int updateMaxnumWithMinMaxInKeys(final List<Integer> keys, final int maxNum, final int _min, final int _max){
        return updateMaxnumWithMinMaxInKeys(keys, maxNum, _min, _max, TABLENAME);
    }

    public int updateMaxnumWithMinMaxInKeys(final List<Integer> keys, final int maxNum, final int _min, final int _max, final String TABLENAME2){
        if( maxNum < 0 ) {
            return updateMaxnumWithMinInKeys(keys, maxNum, _min, TABLENAME2);
        } else {
            return updateMaxnumWithMaxInKeys(keys, maxNum, _max, TABLENAME2);
        }
    }

    public int updateCurnumByKey(final int curNum, final int id){
        return updateCurnumByKey(curNum, id, TABLENAME);
    }

    public int updateCurnumByKey(final int curNum, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curNum=curNum+:curNum WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("curNum", curNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurnumWithMinByKey(final int id, final int curNum, final int _min){
        return updateCurnumWithMinByKey(id, curNum, _min, TABLENAME);
    }

    public int updateCurnumWithMinByKey(final int id, final int curNum, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curNum = (select case when curNum+:curNum<=:_min then :_min else curNum+:curNum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("curNum", curNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurnumWithMinInKeys(final List<Integer> keys, final int curNum, final int _min){
        return updateCurnumWithMinInKeys(keys, curNum, _min, TABLENAME);
    }

    public int updateCurnumWithMinInKeys(final List<Integer> keys, final int curNum, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curNum = (select case when curNum+:curNum<=:_min then :_min else curNum+:curNum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("curNum", curNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurnumWithMaxByKey(final int id, final int curNum, final int _max){
        return updateCurnumWithMaxByKey(id, curNum, _max, TABLENAME);
    }

    public int updateCurnumWithMaxByKey(final int id, final int curNum, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curNum = (select case when curNum+:curNum>=:_max then :_max else curNum+:curNum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("curNum", curNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurnumWithMaxInKeys(final List<Integer> keys, final int curNum, final int _max){
        return updateCurnumWithMaxInKeys(keys, curNum, _max, TABLENAME);
    }

    public int updateCurnumWithMaxInKeys(final List<Integer> keys, final int curNum, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curNum = (select case when curNum+:curNum>=:_max then :_max else curNum+:curNum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("curNum", curNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurnumWithMinMaxByKey(final int id, final int curNum, final int _min, final int _max){
        return updateCurnumWithMinMaxByKey(id, curNum, _min, _max, TABLENAME);
    }

    public int updateCurnumWithMinMaxByKey(final int id, final int curNum, final int _min, final int _max, final String TABLENAME2){
        if( curNum < 0 ) {
            return updateCurnumWithMinByKey(id, curNum, _min, TABLENAME2);
        } else {
            return updateCurnumWithMaxByKey(id, curNum, _max, TABLENAME2);
        }
    }

    public int updateCurnumWithMinMaxInKeys(final List<Integer> keys, final int curNum, final int _min, final int _max){
        return updateCurnumWithMinMaxInKeys(keys, curNum, _min, _max, TABLENAME);
    }

    public int updateCurnumWithMinMaxInKeys(final List<Integer> keys, final int curNum, final int _min, final int _max, final String TABLENAME2){
        if( curNum < 0 ) {
            return updateCurnumWithMinInKeys(keys, curNum, _min, TABLENAME2);
        } else {
            return updateCurnumWithMaxInKeys(keys, curNum, _max, TABLENAME2);
        }
    }

    public int updateCreate_timeByKey(final long create_time, final int id){
        return updateCreate_timeByKey(create_time, id, TABLENAME);
    }

    public int updateCreate_timeByKey(final long create_time, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET create_time=create_time+:create_time WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("create_time", create_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreate_timeWithMinByKey(final int id, final long create_time, final long _min){
        return updateCreate_timeWithMinByKey(id, create_time, _min, TABLENAME);
    }

    public int updateCreate_timeWithMinByKey(final int id, final long create_time, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET create_time = (select case when create_time+:create_time<=:_min then :_min else create_time+:create_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("create_time", create_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreate_timeWithMinInKeys(final List<Integer> keys, final long create_time, final long _min){
        return updateCreate_timeWithMinInKeys(keys, create_time, _min, TABLENAME);
    }

    public int updateCreate_timeWithMinInKeys(final List<Integer> keys, final long create_time, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET create_time = (select case when create_time+:create_time<=:_min then :_min else create_time+:create_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("create_time", create_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreate_timeWithMaxByKey(final int id, final long create_time, final long _max){
        return updateCreate_timeWithMaxByKey(id, create_time, _max, TABLENAME);
    }

    public int updateCreate_timeWithMaxByKey(final int id, final long create_time, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET create_time = (select case when create_time+:create_time>=:_max then :_max else create_time+:create_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("create_time", create_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreate_timeWithMaxInKeys(final List<Integer> keys, final long create_time, final long _max){
        return updateCreate_timeWithMaxInKeys(keys, create_time, _max, TABLENAME);
    }

    public int updateCreate_timeWithMaxInKeys(final List<Integer> keys, final long create_time, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET create_time = (select case when create_time+:create_time>=:_max then :_max else create_time+:create_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("create_time", create_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreate_timeWithMinMaxByKey(final int id, final long create_time, final long _min, final long _max){
        return updateCreate_timeWithMinMaxByKey(id, create_time, _min, _max, TABLENAME);
    }

    public int updateCreate_timeWithMinMaxByKey(final int id, final long create_time, final long _min, final long _max, final String TABLENAME2){
        if( create_time < 0 ) {
            return updateCreate_timeWithMinByKey(id, create_time, _min, TABLENAME2);
        } else {
            return updateCreate_timeWithMaxByKey(id, create_time, _max, TABLENAME2);
        }
    }

    public int updateCreate_timeWithMinMaxInKeys(final List<Integer> keys, final long create_time, final long _min, final long _max){
        return updateCreate_timeWithMinMaxInKeys(keys, create_time, _min, _max, TABLENAME);
    }

    public int updateCreate_timeWithMinMaxInKeys(final List<Integer> keys, final long create_time, final long _min, final long _max, final String TABLENAME2){
        if( create_time < 0 ) {
            return updateCreate_timeWithMinInKeys(keys, create_time, _min, TABLENAME2);
        } else {
            return updateCreate_timeWithMaxInKeys(keys, create_time, _max, TABLENAME2);
        }
    }

    public int updatePointhpByKey(final int pointHP, final int id){
        return updatePointhpByKey(pointHP, id, TABLENAME);
    }

    public int updatePointhpByKey(final int pointHP, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pointHP=pointHP+:pointHP WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("pointHP", pointHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePointhpWithMinByKey(final int id, final int pointHP, final int _min){
        return updatePointhpWithMinByKey(id, pointHP, _min, TABLENAME);
    }

    public int updatePointhpWithMinByKey(final int id, final int pointHP, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pointHP = (select case when pointHP+:pointHP<=:_min then :_min else pointHP+:pointHP end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("pointHP", pointHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePointhpWithMinInKeys(final List<Integer> keys, final int pointHP, final int _min){
        return updatePointhpWithMinInKeys(keys, pointHP, _min, TABLENAME);
    }

    public int updatePointhpWithMinInKeys(final List<Integer> keys, final int pointHP, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pointHP = (select case when pointHP+:pointHP<=:_min then :_min else pointHP+:pointHP end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("pointHP", pointHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePointhpWithMaxByKey(final int id, final int pointHP, final int _max){
        return updatePointhpWithMaxByKey(id, pointHP, _max, TABLENAME);
    }

    public int updatePointhpWithMaxByKey(final int id, final int pointHP, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pointHP = (select case when pointHP+:pointHP>=:_max then :_max else pointHP+:pointHP end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("pointHP", pointHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePointhpWithMaxInKeys(final List<Integer> keys, final int pointHP, final int _max){
        return updatePointhpWithMaxInKeys(keys, pointHP, _max, TABLENAME);
    }

    public int updatePointhpWithMaxInKeys(final List<Integer> keys, final int pointHP, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pointHP = (select case when pointHP+:pointHP>=:_max then :_max else pointHP+:pointHP end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("pointHP", pointHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePointhpWithMinMaxByKey(final int id, final int pointHP, final int _min, final int _max){
        return updatePointhpWithMinMaxByKey(id, pointHP, _min, _max, TABLENAME);
    }

    public int updatePointhpWithMinMaxByKey(final int id, final int pointHP, final int _min, final int _max, final String TABLENAME2){
        if( pointHP < 0 ) {
            return updatePointhpWithMinByKey(id, pointHP, _min, TABLENAME2);
        } else {
            return updatePointhpWithMaxByKey(id, pointHP, _max, TABLENAME2);
        }
    }

    public int updatePointhpWithMinMaxInKeys(final List<Integer> keys, final int pointHP, final int _min, final int _max){
        return updatePointhpWithMinMaxInKeys(keys, pointHP, _min, _max, TABLENAME);
    }

    public int updatePointhpWithMinMaxInKeys(final List<Integer> keys, final int pointHP, final int _min, final int _max, final String TABLENAME2){
        if( pointHP < 0 ) {
            return updatePointhpWithMinInKeys(keys, pointHP, _min, TABLENAME2);
        } else {
            return updatePointhpWithMaxInKeys(keys, pointHP, _max, TABLENAME2);
        }
    }

    public int updatePointattByKey(final int pointAtt, final int id){
        return updatePointattByKey(pointAtt, id, TABLENAME);
    }

    public int updatePointattByKey(final int pointAtt, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pointAtt=pointAtt+:pointAtt WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("pointAtt", pointAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePointattWithMinByKey(final int id, final int pointAtt, final int _min){
        return updatePointattWithMinByKey(id, pointAtt, _min, TABLENAME);
    }

    public int updatePointattWithMinByKey(final int id, final int pointAtt, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pointAtt = (select case when pointAtt+:pointAtt<=:_min then :_min else pointAtt+:pointAtt end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("pointAtt", pointAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePointattWithMinInKeys(final List<Integer> keys, final int pointAtt, final int _min){
        return updatePointattWithMinInKeys(keys, pointAtt, _min, TABLENAME);
    }

    public int updatePointattWithMinInKeys(final List<Integer> keys, final int pointAtt, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pointAtt = (select case when pointAtt+:pointAtt<=:_min then :_min else pointAtt+:pointAtt end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("pointAtt", pointAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePointattWithMaxByKey(final int id, final int pointAtt, final int _max){
        return updatePointattWithMaxByKey(id, pointAtt, _max, TABLENAME);
    }

    public int updatePointattWithMaxByKey(final int id, final int pointAtt, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pointAtt = (select case when pointAtt+:pointAtt>=:_max then :_max else pointAtt+:pointAtt end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("pointAtt", pointAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePointattWithMaxInKeys(final List<Integer> keys, final int pointAtt, final int _max){
        return updatePointattWithMaxInKeys(keys, pointAtt, _max, TABLENAME);
    }

    public int updatePointattWithMaxInKeys(final List<Integer> keys, final int pointAtt, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pointAtt = (select case when pointAtt+:pointAtt>=:_max then :_max else pointAtt+:pointAtt end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("pointAtt", pointAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePointattWithMinMaxByKey(final int id, final int pointAtt, final int _min, final int _max){
        return updatePointattWithMinMaxByKey(id, pointAtt, _min, _max, TABLENAME);
    }

    public int updatePointattWithMinMaxByKey(final int id, final int pointAtt, final int _min, final int _max, final String TABLENAME2){
        if( pointAtt < 0 ) {
            return updatePointattWithMinByKey(id, pointAtt, _min, TABLENAME2);
        } else {
            return updatePointattWithMaxByKey(id, pointAtt, _max, TABLENAME2);
        }
    }

    public int updatePointattWithMinMaxInKeys(final List<Integer> keys, final int pointAtt, final int _min, final int _max){
        return updatePointattWithMinMaxInKeys(keys, pointAtt, _min, _max, TABLENAME);
    }

    public int updatePointattWithMinMaxInKeys(final List<Integer> keys, final int pointAtt, final int _min, final int _max, final String TABLENAME2){
        if( pointAtt < 0 ) {
            return updatePointattWithMinInKeys(keys, pointAtt, _min, TABLENAME2);
        } else {
            return updatePointattWithMaxInKeys(keys, pointAtt, _max, TABLENAME2);
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

    public int updateNexthpgoldByKey(final long nextHPGold, final int id){
        return updateNexthpgoldByKey(nextHPGold, id, TABLENAME);
    }

    public int updateNexthpgoldByKey(final long nextHPGold, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET nextHPGold=nextHPGold+:nextHPGold WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("nextHPGold", nextHPGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNexthpgoldWithMinByKey(final int id, final long nextHPGold, final long _min){
        return updateNexthpgoldWithMinByKey(id, nextHPGold, _min, TABLENAME);
    }

    public int updateNexthpgoldWithMinByKey(final int id, final long nextHPGold, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET nextHPGold = (select case when nextHPGold+:nextHPGold<=:_min then :_min else nextHPGold+:nextHPGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("nextHPGold", nextHPGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNexthpgoldWithMinInKeys(final List<Integer> keys, final long nextHPGold, final long _min){
        return updateNexthpgoldWithMinInKeys(keys, nextHPGold, _min, TABLENAME);
    }

    public int updateNexthpgoldWithMinInKeys(final List<Integer> keys, final long nextHPGold, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET nextHPGold = (select case when nextHPGold+:nextHPGold<=:_min then :_min else nextHPGold+:nextHPGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("nextHPGold", nextHPGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNexthpgoldWithMaxByKey(final int id, final long nextHPGold, final long _max){
        return updateNexthpgoldWithMaxByKey(id, nextHPGold, _max, TABLENAME);
    }

    public int updateNexthpgoldWithMaxByKey(final int id, final long nextHPGold, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET nextHPGold = (select case when nextHPGold+:nextHPGold>=:_max then :_max else nextHPGold+:nextHPGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("nextHPGold", nextHPGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNexthpgoldWithMaxInKeys(final List<Integer> keys, final long nextHPGold, final long _max){
        return updateNexthpgoldWithMaxInKeys(keys, nextHPGold, _max, TABLENAME);
    }

    public int updateNexthpgoldWithMaxInKeys(final List<Integer> keys, final long nextHPGold, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET nextHPGold = (select case when nextHPGold+:nextHPGold>=:_max then :_max else nextHPGold+:nextHPGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("nextHPGold", nextHPGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNexthpgoldWithMinMaxByKey(final int id, final long nextHPGold, final long _min, final long _max){
        return updateNexthpgoldWithMinMaxByKey(id, nextHPGold, _min, _max, TABLENAME);
    }

    public int updateNexthpgoldWithMinMaxByKey(final int id, final long nextHPGold, final long _min, final long _max, final String TABLENAME2){
        if( nextHPGold < 0 ) {
            return updateNexthpgoldWithMinByKey(id, nextHPGold, _min, TABLENAME2);
        } else {
            return updateNexthpgoldWithMaxByKey(id, nextHPGold, _max, TABLENAME2);
        }
    }

    public int updateNexthpgoldWithMinMaxInKeys(final List<Integer> keys, final long nextHPGold, final long _min, final long _max){
        return updateNexthpgoldWithMinMaxInKeys(keys, nextHPGold, _min, _max, TABLENAME);
    }

    public int updateNexthpgoldWithMinMaxInKeys(final List<Integer> keys, final long nextHPGold, final long _min, final long _max, final String TABLENAME2){
        if( nextHPGold < 0 ) {
            return updateNexthpgoldWithMinInKeys(keys, nextHPGold, _min, TABLENAME2);
        } else {
            return updateNexthpgoldWithMaxInKeys(keys, nextHPGold, _max, TABLENAME2);
        }
    }

    public int updateNextattoilByKey(final long nextAttOil, final int id){
        return updateNextattoilByKey(nextAttOil, id, TABLENAME);
    }

    public int updateNextattoilByKey(final long nextAttOil, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET nextAttOil=nextAttOil+:nextAttOil WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("nextAttOil", nextAttOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNextattoilWithMinByKey(final int id, final long nextAttOil, final long _min){
        return updateNextattoilWithMinByKey(id, nextAttOil, _min, TABLENAME);
    }

    public int updateNextattoilWithMinByKey(final int id, final long nextAttOil, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET nextAttOil = (select case when nextAttOil+:nextAttOil<=:_min then :_min else nextAttOil+:nextAttOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("nextAttOil", nextAttOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNextattoilWithMinInKeys(final List<Integer> keys, final long nextAttOil, final long _min){
        return updateNextattoilWithMinInKeys(keys, nextAttOil, _min, TABLENAME);
    }

    public int updateNextattoilWithMinInKeys(final List<Integer> keys, final long nextAttOil, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET nextAttOil = (select case when nextAttOil+:nextAttOil<=:_min then :_min else nextAttOil+:nextAttOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("nextAttOil", nextAttOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNextattoilWithMaxByKey(final int id, final long nextAttOil, final long _max){
        return updateNextattoilWithMaxByKey(id, nextAttOil, _max, TABLENAME);
    }

    public int updateNextattoilWithMaxByKey(final int id, final long nextAttOil, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET nextAttOil = (select case when nextAttOil+:nextAttOil>=:_max then :_max else nextAttOil+:nextAttOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("nextAttOil", nextAttOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNextattoilWithMaxInKeys(final List<Integer> keys, final long nextAttOil, final long _max){
        return updateNextattoilWithMaxInKeys(keys, nextAttOil, _max, TABLENAME);
    }

    public int updateNextattoilWithMaxInKeys(final List<Integer> keys, final long nextAttOil, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET nextAttOil = (select case when nextAttOil+:nextAttOil>=:_max then :_max else nextAttOil+:nextAttOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("nextAttOil", nextAttOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNextattoilWithMinMaxByKey(final int id, final long nextAttOil, final long _min, final long _max){
        return updateNextattoilWithMinMaxByKey(id, nextAttOil, _min, _max, TABLENAME);
    }

    public int updateNextattoilWithMinMaxByKey(final int id, final long nextAttOil, final long _min, final long _max, final String TABLENAME2){
        if( nextAttOil < 0 ) {
            return updateNextattoilWithMinByKey(id, nextAttOil, _min, TABLENAME2);
        } else {
            return updateNextattoilWithMaxByKey(id, nextAttOil, _max, TABLENAME2);
        }
    }

    public int updateNextattoilWithMinMaxInKeys(final List<Integer> keys, final long nextAttOil, final long _min, final long _max){
        return updateNextattoilWithMinMaxInKeys(keys, nextAttOil, _min, _max, TABLENAME);
    }

    public int updateNextattoilWithMinMaxInKeys(final List<Integer> keys, final long nextAttOil, final long _min, final long _max, final String TABLENAME2){
        if( nextAttOil < 0 ) {
            return updateNextattoilWithMinInKeys(keys, nextAttOil, _min, TABLENAME2);
        } else {
            return updateNextattoilWithMaxInKeys(keys, nextAttOil, _max, TABLENAME2);
        }
    }

    public int updateCurdonategoldByKey(final long curDonateGold, final int id){
        return updateCurdonategoldByKey(curDonateGold, id, TABLENAME);
    }

    public int updateCurdonategoldByKey(final long curDonateGold, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDonateGold=curDonateGold+:curDonateGold WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("curDonateGold", curDonateGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdonategoldWithMinByKey(final int id, final long curDonateGold, final long _min){
        return updateCurdonategoldWithMinByKey(id, curDonateGold, _min, TABLENAME);
    }

    public int updateCurdonategoldWithMinByKey(final int id, final long curDonateGold, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDonateGold = (select case when curDonateGold+:curDonateGold<=:_min then :_min else curDonateGold+:curDonateGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("curDonateGold", curDonateGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdonategoldWithMinInKeys(final List<Integer> keys, final long curDonateGold, final long _min){
        return updateCurdonategoldWithMinInKeys(keys, curDonateGold, _min, TABLENAME);
    }

    public int updateCurdonategoldWithMinInKeys(final List<Integer> keys, final long curDonateGold, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDonateGold = (select case when curDonateGold+:curDonateGold<=:_min then :_min else curDonateGold+:curDonateGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("curDonateGold", curDonateGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdonategoldWithMaxByKey(final int id, final long curDonateGold, final long _max){
        return updateCurdonategoldWithMaxByKey(id, curDonateGold, _max, TABLENAME);
    }

    public int updateCurdonategoldWithMaxByKey(final int id, final long curDonateGold, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDonateGold = (select case when curDonateGold+:curDonateGold>=:_max then :_max else curDonateGold+:curDonateGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("curDonateGold", curDonateGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdonategoldWithMaxInKeys(final List<Integer> keys, final long curDonateGold, final long _max){
        return updateCurdonategoldWithMaxInKeys(keys, curDonateGold, _max, TABLENAME);
    }

    public int updateCurdonategoldWithMaxInKeys(final List<Integer> keys, final long curDonateGold, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDonateGold = (select case when curDonateGold+:curDonateGold>=:_max then :_max else curDonateGold+:curDonateGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("curDonateGold", curDonateGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdonategoldWithMinMaxByKey(final int id, final long curDonateGold, final long _min, final long _max){
        return updateCurdonategoldWithMinMaxByKey(id, curDonateGold, _min, _max, TABLENAME);
    }

    public int updateCurdonategoldWithMinMaxByKey(final int id, final long curDonateGold, final long _min, final long _max, final String TABLENAME2){
        if( curDonateGold < 0 ) {
            return updateCurdonategoldWithMinByKey(id, curDonateGold, _min, TABLENAME2);
        } else {
            return updateCurdonategoldWithMaxByKey(id, curDonateGold, _max, TABLENAME2);
        }
    }

    public int updateCurdonategoldWithMinMaxInKeys(final List<Integer> keys, final long curDonateGold, final long _min, final long _max){
        return updateCurdonategoldWithMinMaxInKeys(keys, curDonateGold, _min, _max, TABLENAME);
    }

    public int updateCurdonategoldWithMinMaxInKeys(final List<Integer> keys, final long curDonateGold, final long _min, final long _max, final String TABLENAME2){
        if( curDonateGold < 0 ) {
            return updateCurdonategoldWithMinInKeys(keys, curDonateGold, _min, TABLENAME2);
        } else {
            return updateCurdonategoldWithMaxInKeys(keys, curDonateGold, _max, TABLENAME2);
        }
    }

    public int updateCurdonateoilByKey(final long curDonateOil, final int id){
        return updateCurdonateoilByKey(curDonateOil, id, TABLENAME);
    }

    public int updateCurdonateoilByKey(final long curDonateOil, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDonateOil=curDonateOil+:curDonateOil WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("curDonateOil", curDonateOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdonateoilWithMinByKey(final int id, final long curDonateOil, final long _min){
        return updateCurdonateoilWithMinByKey(id, curDonateOil, _min, TABLENAME);
    }

    public int updateCurdonateoilWithMinByKey(final int id, final long curDonateOil, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDonateOil = (select case when curDonateOil+:curDonateOil<=:_min then :_min else curDonateOil+:curDonateOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("curDonateOil", curDonateOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdonateoilWithMinInKeys(final List<Integer> keys, final long curDonateOil, final long _min){
        return updateCurdonateoilWithMinInKeys(keys, curDonateOil, _min, TABLENAME);
    }

    public int updateCurdonateoilWithMinInKeys(final List<Integer> keys, final long curDonateOil, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDonateOil = (select case when curDonateOil+:curDonateOil<=:_min then :_min else curDonateOil+:curDonateOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("curDonateOil", curDonateOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdonateoilWithMaxByKey(final int id, final long curDonateOil, final long _max){
        return updateCurdonateoilWithMaxByKey(id, curDonateOil, _max, TABLENAME);
    }

    public int updateCurdonateoilWithMaxByKey(final int id, final long curDonateOil, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDonateOil = (select case when curDonateOil+:curDonateOil>=:_max then :_max else curDonateOil+:curDonateOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("curDonateOil", curDonateOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdonateoilWithMaxInKeys(final List<Integer> keys, final long curDonateOil, final long _max){
        return updateCurdonateoilWithMaxInKeys(keys, curDonateOil, _max, TABLENAME);
    }

    public int updateCurdonateoilWithMaxInKeys(final List<Integer> keys, final long curDonateOil, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDonateOil = (select case when curDonateOil+:curDonateOil>=:_max then :_max else curDonateOil+:curDonateOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("curDonateOil", curDonateOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdonateoilWithMinMaxByKey(final int id, final long curDonateOil, final long _min, final long _max){
        return updateCurdonateoilWithMinMaxByKey(id, curDonateOil, _min, _max, TABLENAME);
    }

    public int updateCurdonateoilWithMinMaxByKey(final int id, final long curDonateOil, final long _min, final long _max, final String TABLENAME2){
        if( curDonateOil < 0 ) {
            return updateCurdonateoilWithMinByKey(id, curDonateOil, _min, TABLENAME2);
        } else {
            return updateCurdonateoilWithMaxByKey(id, curDonateOil, _max, TABLENAME2);
        }
    }

    public int updateCurdonateoilWithMinMaxInKeys(final List<Integer> keys, final long curDonateOil, final long _min, final long _max){
        return updateCurdonateoilWithMinMaxInKeys(keys, curDonateOil, _min, _max, TABLENAME);
    }

    public int updateCurdonateoilWithMinMaxInKeys(final List<Integer> keys, final long curDonateOil, final long _min, final long _max, final String TABLENAME2){
        if( curDonateOil < 0 ) {
            return updateCurdonateoilWithMinInKeys(keys, curDonateOil, _min, TABLENAME2);
        } else {
            return updateCurdonateoilWithMaxInKeys(keys, curDonateOil, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Clan> clans) {
        return updateByKey(clans, TABLENAME);
    }

    public int[] updateByKey (final List<Clan> clans, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(clans == null || clans.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ccid=:ccid, clan_name=:clan_name, icon=:icon, lvl=:lvl, desc=:desc, maxNum=:maxNum, curNum=:curNum, create_time=:create_time, pointHP=:pointHP, pointAtt=:pointAtt, renownAll=:renownAll, renownWeek=:renownWeek, nextHPGold=:nextHPGold, nextAttOil=:nextAttOil, curDonateGold=:curDonateGold, curDonateOil=:curDonateOil WHERE id=:id");
            return super.batchUpdate2(sql.toString(), clans);
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
                "	`clan_name`  VARCHAR(32) NOT NULL," +
                "	`icon`  INT(11) NOT NULL," +
                "	`lvl`  INT(11) NOT NULL," +
                "	`desc`  TEXT NOT NULL," +
                "	`maxNum`  INT(11) NOT NULL," +
                "	`curNum`  INT(11) NOT NULL," +
                "	`create_time`  BIGINT(20) NOT NULL," +
                "	`pointHP`  INT(11) NOT NULL," +
                "	`pointAtt`  INT(11) NOT NULL," +
                "	`renownAll`  INT(11) NOT NULL," +
                "	`renownWeek`  INT(11) NOT NULL," +
                "	`nextHPGold`  BIGINT(20) NOT NULL," +
                "	`nextAttOil`  BIGINT(20) NOT NULL," +
                "	`curDonateGold`  BIGINT(20) NOT NULL," +
                "	`curDonateOil`  BIGINT(20) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `ccid` (`ccid`)" +
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
                "	`clan_name`  VARCHAR(32) NOT NULL," +
                "	`icon`  INT(11) NOT NULL," +
                "	`lvl`  INT(11) NOT NULL," +
                "	`desc`  TEXT NOT NULL," +
                "	`maxNum`  INT(11) NOT NULL," +
                "	`curNum`  INT(11) NOT NULL," +
                "	`create_time`  BIGINT(20) NOT NULL," +
                "	`pointHP`  INT(11) NOT NULL," +
                "	`pointAtt`  INT(11) NOT NULL," +
                "	`renownAll`  INT(11) NOT NULL," +
                "	`renownWeek`  INT(11) NOT NULL," +
                "	`nextHPGold`  BIGINT(20) NOT NULL," +
                "	`nextAttOil`  BIGINT(20) NOT NULL," +
                "	`curDonateGold`  BIGINT(20) NOT NULL," +
                "	`curDonateOil`  BIGINT(20) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `ccid` (`ccid`)" +
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
