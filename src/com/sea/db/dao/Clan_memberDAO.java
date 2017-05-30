package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - clan_member
@SuppressWarnings({"rawtypes", "unchecked"})
public class Clan_memberDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Clan_memberDAO.class);

    public static final String TABLE = "clan_member";
    public static String TABLENAME = "clan_member";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "ccid", "cname", "ucid", "pcid", "pname", "post", "donateGold", "donateOil", "curDGold", "curDOil", "lastDGold", "lastDOil", "renownAll", "renownWeek", "daynumgold", "daynumoil", "maxdaynum"};
    public static String coulmns = "id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum";
    public static String coulmns2 = "ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum";

    public Clan_memberDAO(java.sql.Connection conn) {
        super(conn);
    }

    public Clan_memberDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public Clan_memberDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Clan_member clan_member) {
        return insert(clan_member, TABLENAME);
    }

    public int insert(final Clan_member clan_member, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            clan_member.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum) VALUES (:ccid, :cname, :ucid, :pcid, :pname, :post, :donateGold, :donateOil, :curDGold, :curDOil, :lastDGold, :lastDOil, :renownAll, :renownWeek, :daynumgold, :daynumoil, :maxdaynum)");
            Map map = super.insert(sql.toString(), clan_member);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Clan_member clan_member) {
        asynchronousInsert(clan_member, TABLENAME);
    }

    public void asynchronousInsert(final Clan_member clan_member, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(clan_member, TABLENAME2);
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

    public int asynchronousInsert2(final Clan_member clan_member) {
        return asynchronousInsert2(clan_member, TABLENAME);
    }

    public int asynchronousInsert2(final Clan_member clan_member, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(clan_member, TABLENAME2);
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
        return clan_member.id;
    }

    public int insert2(final Clan_member clan_member) {
        return insert2(clan_member, TABLENAME);
    }

    public int insert2(final Clan_member clan_member, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            clan_member.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum) VALUES (:id, :ccid, :cname, :ucid, :pcid, :pname, :post, :donateGold, :donateOil, :curDGold, :curDOil, :lastDGold, :lastDOil, :renownAll, :renownWeek, :daynumgold, :daynumoil, :maxdaynum)");
            Map map = super.insert(sql.toString(), clan_member);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Clan_member> clan_members) {
        return insert(clan_members, TABLENAME);
    }

    public int[] insert(final List<Clan_member> clan_members, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(clan_members == null || clan_members.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum) VALUES (:ccid, :cname, :ucid, :pcid, :pname, :post, :donateGold, :donateOil, :curDGold, :curDOil, :lastDGold, :lastDOil, :renownAll, :renownWeek, :daynumgold, :daynumoil, :maxdaynum)");
            return super.batchInsert(sql.toString(), clan_members);
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

    public int deleteInBeans(final List<Clan_member> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Clan_member> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Clan_member clan_member = beans.get(i);
                int id = clan_member.id;
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

    public List<Clan_member> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Clan_member> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Clan_member.class);
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

    public List<Clan_member> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Clan_member> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Clan_member.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan_member> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Clan_member> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Clan_member.class);
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

    public List<Clan_member> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Clan_member> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Clan_member.class);
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

    public Clan_member last() {
        return last(TABLENAME);
    }

    public Clan_member last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Clan_member.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan_member> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Clan_member> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Clan_member.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan_member> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Clan_member> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Clan_member.class);
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

    public Clan_member selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Clan_member selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Clan_member.class);
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

    public Clan_member selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Clan_member selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Clan_member.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Clan_member selectByPcid(final Integer pcid) {
        return selectByPcid(pcid, TABLENAME);
    }

    public Clan_member selectByPcid(final Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForObject(sql.toString(), params, Clan_member.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByCcid(final String ccid) {
        return countByCcid(ccid, TABLENAME);
    }

    public int countByCcid(final String ccid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE ccid = :ccid ");
            Map params = newMap();
            params.put("ccid", ccid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Clan_member> selectByCcid(final String ccid) {
        return selectByCcid(ccid, TABLENAME);
    }

    public List<Clan_member> selectByCcid(final String ccid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" WHERE ccid = :ccid ORDER BY id ");
            Map params = newMap();
            params.put("ccid", ccid);
            return super.queryForList(sql.toString(), params, Clan_member.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByCcidPKs(final String ccid) {
        return selectByCcidPKs(ccid, TABLENAME);
    }

    public List<Integer> selectByCcidPKs(final String ccid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE ccid = :ccid ORDER BY id ");
            Map params = newMap();
            params.put("ccid", ccid);
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

    public List<Clan_member> selectPageByCcid(final String ccid, final int begin, final int num) {
        return selectPageByCcid(ccid, begin, num, TABLENAME);
    }

    public List<Clan_member> selectPageByCcid(final String ccid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" WHERE ccid = :ccid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("ccid", ccid);
            return super.queryForList(sql.toString(), params, Clan_member.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByCcidPKs(final String ccid, final int begin, final int num) {
        return selectPageByCcidPKs(ccid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByCcidPKs(final String ccid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE ccid = :ccid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("ccid", ccid);
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

    public List<Clan_member> selectLikeCcid(final String ccid) {
        return selectLikeCcid(ccid, TABLENAME);
    }

    public List<Clan_member> selectLikeCcid(final String ccid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" WHERE ccid LIKE '%").append(ccid).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), Clan_member.class);
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

    public List<Clan_member> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Clan_member> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, cname, ucid, pcid, pname, post, donateGold, donateOil, curDGold, curDOil, lastDGold, lastDOil, renownAll, renownWeek, daynumgold, daynumoil, maxdaynum FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Clan_member.class);
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

    public int updateByKey(final Clan_member clan_member) {
        return updateByKey(clan_member, TABLENAME);
    }

    public int updateByKey(final Clan_member clan_member, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = clan_member.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), clan_member);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Clan_member clan_member) {
        asynchronousUpdate(clan_member, TABLENAME);
    }

    public void asynchronousUpdate(final Clan_member clan_member, final String TABLENAME2) {
        try {

            String _ustr = clan_member.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, clan_member);
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

    public int updatePostByKey(final int post, final int id){
        return updatePostByKey(post, id, TABLENAME);
    }

    public int updatePostByKey(final int post, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET post=post+:post WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("post", post);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePostWithMinByKey(final int id, final int post, final int _min){
        return updatePostWithMinByKey(id, post, _min, TABLENAME);
    }

    public int updatePostWithMinByKey(final int id, final int post, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET post = (select case when post+:post<=:_min then :_min else post+:post end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("post", post);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePostWithMinInKeys(final List<Integer> keys, final int post, final int _min){
        return updatePostWithMinInKeys(keys, post, _min, TABLENAME);
    }

    public int updatePostWithMinInKeys(final List<Integer> keys, final int post, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET post = (select case when post+:post<=:_min then :_min else post+:post end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("post", post);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePostWithMaxByKey(final int id, final int post, final int _max){
        return updatePostWithMaxByKey(id, post, _max, TABLENAME);
    }

    public int updatePostWithMaxByKey(final int id, final int post, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET post = (select case when post+:post>=:_max then :_max else post+:post end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("post", post);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePostWithMaxInKeys(final List<Integer> keys, final int post, final int _max){
        return updatePostWithMaxInKeys(keys, post, _max, TABLENAME);
    }

    public int updatePostWithMaxInKeys(final List<Integer> keys, final int post, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET post = (select case when post+:post>=:_max then :_max else post+:post end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("post", post);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePostWithMinMaxByKey(final int id, final int post, final int _min, final int _max){
        return updatePostWithMinMaxByKey(id, post, _min, _max, TABLENAME);
    }

    public int updatePostWithMinMaxByKey(final int id, final int post, final int _min, final int _max, final String TABLENAME2){
        if( post < 0 ) {
            return updatePostWithMinByKey(id, post, _min, TABLENAME2);
        } else {
            return updatePostWithMaxByKey(id, post, _max, TABLENAME2);
        }
    }

    public int updatePostWithMinMaxInKeys(final List<Integer> keys, final int post, final int _min, final int _max){
        return updatePostWithMinMaxInKeys(keys, post, _min, _max, TABLENAME);
    }

    public int updatePostWithMinMaxInKeys(final List<Integer> keys, final int post, final int _min, final int _max, final String TABLENAME2){
        if( post < 0 ) {
            return updatePostWithMinInKeys(keys, post, _min, TABLENAME2);
        } else {
            return updatePostWithMaxInKeys(keys, post, _max, TABLENAME2);
        }
    }

    public int updateDonategoldByKey(final int donateGold, final int id){
        return updateDonategoldByKey(donateGold, id, TABLENAME);
    }

    public int updateDonategoldByKey(final int donateGold, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET donateGold=donateGold+:donateGold WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("donateGold", donateGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDonategoldWithMinByKey(final int id, final int donateGold, final int _min){
        return updateDonategoldWithMinByKey(id, donateGold, _min, TABLENAME);
    }

    public int updateDonategoldWithMinByKey(final int id, final int donateGold, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET donateGold = (select case when donateGold+:donateGold<=:_min then :_min else donateGold+:donateGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("donateGold", donateGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDonategoldWithMinInKeys(final List<Integer> keys, final int donateGold, final int _min){
        return updateDonategoldWithMinInKeys(keys, donateGold, _min, TABLENAME);
    }

    public int updateDonategoldWithMinInKeys(final List<Integer> keys, final int donateGold, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET donateGold = (select case when donateGold+:donateGold<=:_min then :_min else donateGold+:donateGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("donateGold", donateGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDonategoldWithMaxByKey(final int id, final int donateGold, final int _max){
        return updateDonategoldWithMaxByKey(id, donateGold, _max, TABLENAME);
    }

    public int updateDonategoldWithMaxByKey(final int id, final int donateGold, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET donateGold = (select case when donateGold+:donateGold>=:_max then :_max else donateGold+:donateGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("donateGold", donateGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDonategoldWithMaxInKeys(final List<Integer> keys, final int donateGold, final int _max){
        return updateDonategoldWithMaxInKeys(keys, donateGold, _max, TABLENAME);
    }

    public int updateDonategoldWithMaxInKeys(final List<Integer> keys, final int donateGold, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET donateGold = (select case when donateGold+:donateGold>=:_max then :_max else donateGold+:donateGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("donateGold", donateGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDonategoldWithMinMaxByKey(final int id, final int donateGold, final int _min, final int _max){
        return updateDonategoldWithMinMaxByKey(id, donateGold, _min, _max, TABLENAME);
    }

    public int updateDonategoldWithMinMaxByKey(final int id, final int donateGold, final int _min, final int _max, final String TABLENAME2){
        if( donateGold < 0 ) {
            return updateDonategoldWithMinByKey(id, donateGold, _min, TABLENAME2);
        } else {
            return updateDonategoldWithMaxByKey(id, donateGold, _max, TABLENAME2);
        }
    }

    public int updateDonategoldWithMinMaxInKeys(final List<Integer> keys, final int donateGold, final int _min, final int _max){
        return updateDonategoldWithMinMaxInKeys(keys, donateGold, _min, _max, TABLENAME);
    }

    public int updateDonategoldWithMinMaxInKeys(final List<Integer> keys, final int donateGold, final int _min, final int _max, final String TABLENAME2){
        if( donateGold < 0 ) {
            return updateDonategoldWithMinInKeys(keys, donateGold, _min, TABLENAME2);
        } else {
            return updateDonategoldWithMaxInKeys(keys, donateGold, _max, TABLENAME2);
        }
    }

    public int updateDonateoilByKey(final int donateOil, final int id){
        return updateDonateoilByKey(donateOil, id, TABLENAME);
    }

    public int updateDonateoilByKey(final int donateOil, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET donateOil=donateOil+:donateOil WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("donateOil", donateOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDonateoilWithMinByKey(final int id, final int donateOil, final int _min){
        return updateDonateoilWithMinByKey(id, donateOil, _min, TABLENAME);
    }

    public int updateDonateoilWithMinByKey(final int id, final int donateOil, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET donateOil = (select case when donateOil+:donateOil<=:_min then :_min else donateOil+:donateOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("donateOil", donateOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDonateoilWithMinInKeys(final List<Integer> keys, final int donateOil, final int _min){
        return updateDonateoilWithMinInKeys(keys, donateOil, _min, TABLENAME);
    }

    public int updateDonateoilWithMinInKeys(final List<Integer> keys, final int donateOil, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET donateOil = (select case when donateOil+:donateOil<=:_min then :_min else donateOil+:donateOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("donateOil", donateOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDonateoilWithMaxByKey(final int id, final int donateOil, final int _max){
        return updateDonateoilWithMaxByKey(id, donateOil, _max, TABLENAME);
    }

    public int updateDonateoilWithMaxByKey(final int id, final int donateOil, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET donateOil = (select case when donateOil+:donateOil>=:_max then :_max else donateOil+:donateOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("donateOil", donateOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDonateoilWithMaxInKeys(final List<Integer> keys, final int donateOil, final int _max){
        return updateDonateoilWithMaxInKeys(keys, donateOil, _max, TABLENAME);
    }

    public int updateDonateoilWithMaxInKeys(final List<Integer> keys, final int donateOil, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET donateOil = (select case when donateOil+:donateOil>=:_max then :_max else donateOil+:donateOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("donateOil", donateOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDonateoilWithMinMaxByKey(final int id, final int donateOil, final int _min, final int _max){
        return updateDonateoilWithMinMaxByKey(id, donateOil, _min, _max, TABLENAME);
    }

    public int updateDonateoilWithMinMaxByKey(final int id, final int donateOil, final int _min, final int _max, final String TABLENAME2){
        if( donateOil < 0 ) {
            return updateDonateoilWithMinByKey(id, donateOil, _min, TABLENAME2);
        } else {
            return updateDonateoilWithMaxByKey(id, donateOil, _max, TABLENAME2);
        }
    }

    public int updateDonateoilWithMinMaxInKeys(final List<Integer> keys, final int donateOil, final int _min, final int _max){
        return updateDonateoilWithMinMaxInKeys(keys, donateOil, _min, _max, TABLENAME);
    }

    public int updateDonateoilWithMinMaxInKeys(final List<Integer> keys, final int donateOil, final int _min, final int _max, final String TABLENAME2){
        if( donateOil < 0 ) {
            return updateDonateoilWithMinInKeys(keys, donateOil, _min, TABLENAME2);
        } else {
            return updateDonateoilWithMaxInKeys(keys, donateOil, _max, TABLENAME2);
        }
    }

    public int updateCurdgoldByKey(final int curDGold, final int id){
        return updateCurdgoldByKey(curDGold, id, TABLENAME);
    }

    public int updateCurdgoldByKey(final int curDGold, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDGold=curDGold+:curDGold WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("curDGold", curDGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdgoldWithMinByKey(final int id, final int curDGold, final int _min){
        return updateCurdgoldWithMinByKey(id, curDGold, _min, TABLENAME);
    }

    public int updateCurdgoldWithMinByKey(final int id, final int curDGold, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDGold = (select case when curDGold+:curDGold<=:_min then :_min else curDGold+:curDGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("curDGold", curDGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdgoldWithMinInKeys(final List<Integer> keys, final int curDGold, final int _min){
        return updateCurdgoldWithMinInKeys(keys, curDGold, _min, TABLENAME);
    }

    public int updateCurdgoldWithMinInKeys(final List<Integer> keys, final int curDGold, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDGold = (select case when curDGold+:curDGold<=:_min then :_min else curDGold+:curDGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("curDGold", curDGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdgoldWithMaxByKey(final int id, final int curDGold, final int _max){
        return updateCurdgoldWithMaxByKey(id, curDGold, _max, TABLENAME);
    }

    public int updateCurdgoldWithMaxByKey(final int id, final int curDGold, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDGold = (select case when curDGold+:curDGold>=:_max then :_max else curDGold+:curDGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("curDGold", curDGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdgoldWithMaxInKeys(final List<Integer> keys, final int curDGold, final int _max){
        return updateCurdgoldWithMaxInKeys(keys, curDGold, _max, TABLENAME);
    }

    public int updateCurdgoldWithMaxInKeys(final List<Integer> keys, final int curDGold, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDGold = (select case when curDGold+:curDGold>=:_max then :_max else curDGold+:curDGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("curDGold", curDGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdgoldWithMinMaxByKey(final int id, final int curDGold, final int _min, final int _max){
        return updateCurdgoldWithMinMaxByKey(id, curDGold, _min, _max, TABLENAME);
    }

    public int updateCurdgoldWithMinMaxByKey(final int id, final int curDGold, final int _min, final int _max, final String TABLENAME2){
        if( curDGold < 0 ) {
            return updateCurdgoldWithMinByKey(id, curDGold, _min, TABLENAME2);
        } else {
            return updateCurdgoldWithMaxByKey(id, curDGold, _max, TABLENAME2);
        }
    }

    public int updateCurdgoldWithMinMaxInKeys(final List<Integer> keys, final int curDGold, final int _min, final int _max){
        return updateCurdgoldWithMinMaxInKeys(keys, curDGold, _min, _max, TABLENAME);
    }

    public int updateCurdgoldWithMinMaxInKeys(final List<Integer> keys, final int curDGold, final int _min, final int _max, final String TABLENAME2){
        if( curDGold < 0 ) {
            return updateCurdgoldWithMinInKeys(keys, curDGold, _min, TABLENAME2);
        } else {
            return updateCurdgoldWithMaxInKeys(keys, curDGold, _max, TABLENAME2);
        }
    }

    public int updateCurdoilByKey(final int curDOil, final int id){
        return updateCurdoilByKey(curDOil, id, TABLENAME);
    }

    public int updateCurdoilByKey(final int curDOil, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDOil=curDOil+:curDOil WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("curDOil", curDOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdoilWithMinByKey(final int id, final int curDOil, final int _min){
        return updateCurdoilWithMinByKey(id, curDOil, _min, TABLENAME);
    }

    public int updateCurdoilWithMinByKey(final int id, final int curDOil, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDOil = (select case when curDOil+:curDOil<=:_min then :_min else curDOil+:curDOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("curDOil", curDOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdoilWithMinInKeys(final List<Integer> keys, final int curDOil, final int _min){
        return updateCurdoilWithMinInKeys(keys, curDOil, _min, TABLENAME);
    }

    public int updateCurdoilWithMinInKeys(final List<Integer> keys, final int curDOil, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDOil = (select case when curDOil+:curDOil<=:_min then :_min else curDOil+:curDOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("curDOil", curDOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdoilWithMaxByKey(final int id, final int curDOil, final int _max){
        return updateCurdoilWithMaxByKey(id, curDOil, _max, TABLENAME);
    }

    public int updateCurdoilWithMaxByKey(final int id, final int curDOil, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDOil = (select case when curDOil+:curDOil>=:_max then :_max else curDOil+:curDOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("curDOil", curDOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdoilWithMaxInKeys(final List<Integer> keys, final int curDOil, final int _max){
        return updateCurdoilWithMaxInKeys(keys, curDOil, _max, TABLENAME);
    }

    public int updateCurdoilWithMaxInKeys(final List<Integer> keys, final int curDOil, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curDOil = (select case when curDOil+:curDOil>=:_max then :_max else curDOil+:curDOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("curDOil", curDOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurdoilWithMinMaxByKey(final int id, final int curDOil, final int _min, final int _max){
        return updateCurdoilWithMinMaxByKey(id, curDOil, _min, _max, TABLENAME);
    }

    public int updateCurdoilWithMinMaxByKey(final int id, final int curDOil, final int _min, final int _max, final String TABLENAME2){
        if( curDOil < 0 ) {
            return updateCurdoilWithMinByKey(id, curDOil, _min, TABLENAME2);
        } else {
            return updateCurdoilWithMaxByKey(id, curDOil, _max, TABLENAME2);
        }
    }

    public int updateCurdoilWithMinMaxInKeys(final List<Integer> keys, final int curDOil, final int _min, final int _max){
        return updateCurdoilWithMinMaxInKeys(keys, curDOil, _min, _max, TABLENAME);
    }

    public int updateCurdoilWithMinMaxInKeys(final List<Integer> keys, final int curDOil, final int _min, final int _max, final String TABLENAME2){
        if( curDOil < 0 ) {
            return updateCurdoilWithMinInKeys(keys, curDOil, _min, TABLENAME2);
        } else {
            return updateCurdoilWithMaxInKeys(keys, curDOil, _max, TABLENAME2);
        }
    }

    public int updateLastdgoldByKey(final long lastDGold, final int id){
        return updateLastdgoldByKey(lastDGold, id, TABLENAME);
    }

    public int updateLastdgoldByKey(final long lastDGold, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastDGold=lastDGold+:lastDGold WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lastDGold", lastDGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastdgoldWithMinByKey(final int id, final long lastDGold, final long _min){
        return updateLastdgoldWithMinByKey(id, lastDGold, _min, TABLENAME);
    }

    public int updateLastdgoldWithMinByKey(final int id, final long lastDGold, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastDGold = (select case when lastDGold+:lastDGold<=:_min then :_min else lastDGold+:lastDGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lastDGold", lastDGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastdgoldWithMinInKeys(final List<Integer> keys, final long lastDGold, final long _min){
        return updateLastdgoldWithMinInKeys(keys, lastDGold, _min, TABLENAME);
    }

    public int updateLastdgoldWithMinInKeys(final List<Integer> keys, final long lastDGold, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastDGold = (select case when lastDGold+:lastDGold<=:_min then :_min else lastDGold+:lastDGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lastDGold", lastDGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastdgoldWithMaxByKey(final int id, final long lastDGold, final long _max){
        return updateLastdgoldWithMaxByKey(id, lastDGold, _max, TABLENAME);
    }

    public int updateLastdgoldWithMaxByKey(final int id, final long lastDGold, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastDGold = (select case when lastDGold+:lastDGold>=:_max then :_max else lastDGold+:lastDGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lastDGold", lastDGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastdgoldWithMaxInKeys(final List<Integer> keys, final long lastDGold, final long _max){
        return updateLastdgoldWithMaxInKeys(keys, lastDGold, _max, TABLENAME);
    }

    public int updateLastdgoldWithMaxInKeys(final List<Integer> keys, final long lastDGold, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastDGold = (select case when lastDGold+:lastDGold>=:_max then :_max else lastDGold+:lastDGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lastDGold", lastDGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastdgoldWithMinMaxByKey(final int id, final long lastDGold, final long _min, final long _max){
        return updateLastdgoldWithMinMaxByKey(id, lastDGold, _min, _max, TABLENAME);
    }

    public int updateLastdgoldWithMinMaxByKey(final int id, final long lastDGold, final long _min, final long _max, final String TABLENAME2){
        if( lastDGold < 0 ) {
            return updateLastdgoldWithMinByKey(id, lastDGold, _min, TABLENAME2);
        } else {
            return updateLastdgoldWithMaxByKey(id, lastDGold, _max, TABLENAME2);
        }
    }

    public int updateLastdgoldWithMinMaxInKeys(final List<Integer> keys, final long lastDGold, final long _min, final long _max){
        return updateLastdgoldWithMinMaxInKeys(keys, lastDGold, _min, _max, TABLENAME);
    }

    public int updateLastdgoldWithMinMaxInKeys(final List<Integer> keys, final long lastDGold, final long _min, final long _max, final String TABLENAME2){
        if( lastDGold < 0 ) {
            return updateLastdgoldWithMinInKeys(keys, lastDGold, _min, TABLENAME2);
        } else {
            return updateLastdgoldWithMaxInKeys(keys, lastDGold, _max, TABLENAME2);
        }
    }

    public int updateLastdoilByKey(final long lastDOil, final int id){
        return updateLastdoilByKey(lastDOil, id, TABLENAME);
    }

    public int updateLastdoilByKey(final long lastDOil, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastDOil=lastDOil+:lastDOil WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lastDOil", lastDOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastdoilWithMinByKey(final int id, final long lastDOil, final long _min){
        return updateLastdoilWithMinByKey(id, lastDOil, _min, TABLENAME);
    }

    public int updateLastdoilWithMinByKey(final int id, final long lastDOil, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastDOil = (select case when lastDOil+:lastDOil<=:_min then :_min else lastDOil+:lastDOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lastDOil", lastDOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastdoilWithMinInKeys(final List<Integer> keys, final long lastDOil, final long _min){
        return updateLastdoilWithMinInKeys(keys, lastDOil, _min, TABLENAME);
    }

    public int updateLastdoilWithMinInKeys(final List<Integer> keys, final long lastDOil, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastDOil = (select case when lastDOil+:lastDOil<=:_min then :_min else lastDOil+:lastDOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lastDOil", lastDOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastdoilWithMaxByKey(final int id, final long lastDOil, final long _max){
        return updateLastdoilWithMaxByKey(id, lastDOil, _max, TABLENAME);
    }

    public int updateLastdoilWithMaxByKey(final int id, final long lastDOil, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastDOil = (select case when lastDOil+:lastDOil>=:_max then :_max else lastDOil+:lastDOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lastDOil", lastDOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastdoilWithMaxInKeys(final List<Integer> keys, final long lastDOil, final long _max){
        return updateLastdoilWithMaxInKeys(keys, lastDOil, _max, TABLENAME);
    }

    public int updateLastdoilWithMaxInKeys(final List<Integer> keys, final long lastDOil, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastDOil = (select case when lastDOil+:lastDOil>=:_max then :_max else lastDOil+:lastDOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lastDOil", lastDOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastdoilWithMinMaxByKey(final int id, final long lastDOil, final long _min, final long _max){
        return updateLastdoilWithMinMaxByKey(id, lastDOil, _min, _max, TABLENAME);
    }

    public int updateLastdoilWithMinMaxByKey(final int id, final long lastDOil, final long _min, final long _max, final String TABLENAME2){
        if( lastDOil < 0 ) {
            return updateLastdoilWithMinByKey(id, lastDOil, _min, TABLENAME2);
        } else {
            return updateLastdoilWithMaxByKey(id, lastDOil, _max, TABLENAME2);
        }
    }

    public int updateLastdoilWithMinMaxInKeys(final List<Integer> keys, final long lastDOil, final long _min, final long _max){
        return updateLastdoilWithMinMaxInKeys(keys, lastDOil, _min, _max, TABLENAME);
    }

    public int updateLastdoilWithMinMaxInKeys(final List<Integer> keys, final long lastDOil, final long _min, final long _max, final String TABLENAME2){
        if( lastDOil < 0 ) {
            return updateLastdoilWithMinInKeys(keys, lastDOil, _min, TABLENAME2);
        } else {
            return updateLastdoilWithMaxInKeys(keys, lastDOil, _max, TABLENAME2);
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

    public int updateDaynumgoldByKey(final int daynumgold, final int id){
        return updateDaynumgoldByKey(daynumgold, id, TABLENAME);
    }

    public int updateDaynumgoldByKey(final int daynumgold, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET daynumgold=daynumgold+:daynumgold WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("daynumgold", daynumgold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDaynumgoldWithMinByKey(final int id, final int daynumgold, final int _min){
        return updateDaynumgoldWithMinByKey(id, daynumgold, _min, TABLENAME);
    }

    public int updateDaynumgoldWithMinByKey(final int id, final int daynumgold, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET daynumgold = (select case when daynumgold+:daynumgold<=:_min then :_min else daynumgold+:daynumgold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("daynumgold", daynumgold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDaynumgoldWithMinInKeys(final List<Integer> keys, final int daynumgold, final int _min){
        return updateDaynumgoldWithMinInKeys(keys, daynumgold, _min, TABLENAME);
    }

    public int updateDaynumgoldWithMinInKeys(final List<Integer> keys, final int daynumgold, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET daynumgold = (select case when daynumgold+:daynumgold<=:_min then :_min else daynumgold+:daynumgold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("daynumgold", daynumgold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDaynumgoldWithMaxByKey(final int id, final int daynumgold, final int _max){
        return updateDaynumgoldWithMaxByKey(id, daynumgold, _max, TABLENAME);
    }

    public int updateDaynumgoldWithMaxByKey(final int id, final int daynumgold, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET daynumgold = (select case when daynumgold+:daynumgold>=:_max then :_max else daynumgold+:daynumgold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("daynumgold", daynumgold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDaynumgoldWithMaxInKeys(final List<Integer> keys, final int daynumgold, final int _max){
        return updateDaynumgoldWithMaxInKeys(keys, daynumgold, _max, TABLENAME);
    }

    public int updateDaynumgoldWithMaxInKeys(final List<Integer> keys, final int daynumgold, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET daynumgold = (select case when daynumgold+:daynumgold>=:_max then :_max else daynumgold+:daynumgold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("daynumgold", daynumgold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDaynumgoldWithMinMaxByKey(final int id, final int daynumgold, final int _min, final int _max){
        return updateDaynumgoldWithMinMaxByKey(id, daynumgold, _min, _max, TABLENAME);
    }

    public int updateDaynumgoldWithMinMaxByKey(final int id, final int daynumgold, final int _min, final int _max, final String TABLENAME2){
        if( daynumgold < 0 ) {
            return updateDaynumgoldWithMinByKey(id, daynumgold, _min, TABLENAME2);
        } else {
            return updateDaynumgoldWithMaxByKey(id, daynumgold, _max, TABLENAME2);
        }
    }

    public int updateDaynumgoldWithMinMaxInKeys(final List<Integer> keys, final int daynumgold, final int _min, final int _max){
        return updateDaynumgoldWithMinMaxInKeys(keys, daynumgold, _min, _max, TABLENAME);
    }

    public int updateDaynumgoldWithMinMaxInKeys(final List<Integer> keys, final int daynumgold, final int _min, final int _max, final String TABLENAME2){
        if( daynumgold < 0 ) {
            return updateDaynumgoldWithMinInKeys(keys, daynumgold, _min, TABLENAME2);
        } else {
            return updateDaynumgoldWithMaxInKeys(keys, daynumgold, _max, TABLENAME2);
        }
    }

    public int updateDaynumoilByKey(final int daynumoil, final int id){
        return updateDaynumoilByKey(daynumoil, id, TABLENAME);
    }

    public int updateDaynumoilByKey(final int daynumoil, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET daynumoil=daynumoil+:daynumoil WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("daynumoil", daynumoil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDaynumoilWithMinByKey(final int id, final int daynumoil, final int _min){
        return updateDaynumoilWithMinByKey(id, daynumoil, _min, TABLENAME);
    }

    public int updateDaynumoilWithMinByKey(final int id, final int daynumoil, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET daynumoil = (select case when daynumoil+:daynumoil<=:_min then :_min else daynumoil+:daynumoil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("daynumoil", daynumoil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDaynumoilWithMinInKeys(final List<Integer> keys, final int daynumoil, final int _min){
        return updateDaynumoilWithMinInKeys(keys, daynumoil, _min, TABLENAME);
    }

    public int updateDaynumoilWithMinInKeys(final List<Integer> keys, final int daynumoil, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET daynumoil = (select case when daynumoil+:daynumoil<=:_min then :_min else daynumoil+:daynumoil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("daynumoil", daynumoil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDaynumoilWithMaxByKey(final int id, final int daynumoil, final int _max){
        return updateDaynumoilWithMaxByKey(id, daynumoil, _max, TABLENAME);
    }

    public int updateDaynumoilWithMaxByKey(final int id, final int daynumoil, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET daynumoil = (select case when daynumoil+:daynumoil>=:_max then :_max else daynumoil+:daynumoil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("daynumoil", daynumoil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDaynumoilWithMaxInKeys(final List<Integer> keys, final int daynumoil, final int _max){
        return updateDaynumoilWithMaxInKeys(keys, daynumoil, _max, TABLENAME);
    }

    public int updateDaynumoilWithMaxInKeys(final List<Integer> keys, final int daynumoil, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET daynumoil = (select case when daynumoil+:daynumoil>=:_max then :_max else daynumoil+:daynumoil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("daynumoil", daynumoil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDaynumoilWithMinMaxByKey(final int id, final int daynumoil, final int _min, final int _max){
        return updateDaynumoilWithMinMaxByKey(id, daynumoil, _min, _max, TABLENAME);
    }

    public int updateDaynumoilWithMinMaxByKey(final int id, final int daynumoil, final int _min, final int _max, final String TABLENAME2){
        if( daynumoil < 0 ) {
            return updateDaynumoilWithMinByKey(id, daynumoil, _min, TABLENAME2);
        } else {
            return updateDaynumoilWithMaxByKey(id, daynumoil, _max, TABLENAME2);
        }
    }

    public int updateDaynumoilWithMinMaxInKeys(final List<Integer> keys, final int daynumoil, final int _min, final int _max){
        return updateDaynumoilWithMinMaxInKeys(keys, daynumoil, _min, _max, TABLENAME);
    }

    public int updateDaynumoilWithMinMaxInKeys(final List<Integer> keys, final int daynumoil, final int _min, final int _max, final String TABLENAME2){
        if( daynumoil < 0 ) {
            return updateDaynumoilWithMinInKeys(keys, daynumoil, _min, TABLENAME2);
        } else {
            return updateDaynumoilWithMaxInKeys(keys, daynumoil, _max, TABLENAME2);
        }
    }

    public int updateMaxdaynumByKey(final int maxdaynum, final int id){
        return updateMaxdaynumByKey(maxdaynum, id, TABLENAME);
    }

    public int updateMaxdaynumByKey(final int maxdaynum, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxdaynum=maxdaynum+:maxdaynum WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("maxdaynum", maxdaynum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxdaynumWithMinByKey(final int id, final int maxdaynum, final int _min){
        return updateMaxdaynumWithMinByKey(id, maxdaynum, _min, TABLENAME);
    }

    public int updateMaxdaynumWithMinByKey(final int id, final int maxdaynum, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxdaynum = (select case when maxdaynum+:maxdaynum<=:_min then :_min else maxdaynum+:maxdaynum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("maxdaynum", maxdaynum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxdaynumWithMinInKeys(final List<Integer> keys, final int maxdaynum, final int _min){
        return updateMaxdaynumWithMinInKeys(keys, maxdaynum, _min, TABLENAME);
    }

    public int updateMaxdaynumWithMinInKeys(final List<Integer> keys, final int maxdaynum, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxdaynum = (select case when maxdaynum+:maxdaynum<=:_min then :_min else maxdaynum+:maxdaynum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("maxdaynum", maxdaynum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxdaynumWithMaxByKey(final int id, final int maxdaynum, final int _max){
        return updateMaxdaynumWithMaxByKey(id, maxdaynum, _max, TABLENAME);
    }

    public int updateMaxdaynumWithMaxByKey(final int id, final int maxdaynum, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxdaynum = (select case when maxdaynum+:maxdaynum>=:_max then :_max else maxdaynum+:maxdaynum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("maxdaynum", maxdaynum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxdaynumWithMaxInKeys(final List<Integer> keys, final int maxdaynum, final int _max){
        return updateMaxdaynumWithMaxInKeys(keys, maxdaynum, _max, TABLENAME);
    }

    public int updateMaxdaynumWithMaxInKeys(final List<Integer> keys, final int maxdaynum, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxdaynum = (select case when maxdaynum+:maxdaynum>=:_max then :_max else maxdaynum+:maxdaynum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("maxdaynum", maxdaynum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxdaynumWithMinMaxByKey(final int id, final int maxdaynum, final int _min, final int _max){
        return updateMaxdaynumWithMinMaxByKey(id, maxdaynum, _min, _max, TABLENAME);
    }

    public int updateMaxdaynumWithMinMaxByKey(final int id, final int maxdaynum, final int _min, final int _max, final String TABLENAME2){
        if( maxdaynum < 0 ) {
            return updateMaxdaynumWithMinByKey(id, maxdaynum, _min, TABLENAME2);
        } else {
            return updateMaxdaynumWithMaxByKey(id, maxdaynum, _max, TABLENAME2);
        }
    }

    public int updateMaxdaynumWithMinMaxInKeys(final List<Integer> keys, final int maxdaynum, final int _min, final int _max){
        return updateMaxdaynumWithMinMaxInKeys(keys, maxdaynum, _min, _max, TABLENAME);
    }

    public int updateMaxdaynumWithMinMaxInKeys(final List<Integer> keys, final int maxdaynum, final int _min, final int _max, final String TABLENAME2){
        if( maxdaynum < 0 ) {
            return updateMaxdaynumWithMinInKeys(keys, maxdaynum, _min, TABLENAME2);
        } else {
            return updateMaxdaynumWithMaxInKeys(keys, maxdaynum, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Clan_member> clan_members) {
        return updateByKey(clan_members, TABLENAME);
    }

    public int[] updateByKey (final List<Clan_member> clan_members, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(clan_members == null || clan_members.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ccid=:ccid, cname=:cname, ucid=:ucid, pcid=:pcid, pname=:pname, post=:post, donateGold=:donateGold, donateOil=:donateOil, curDGold=:curDGold, curDOil=:curDOil, lastDGold=:lastDGold, lastDOil=:lastDOil, renownAll=:renownAll, renownWeek=:renownWeek, daynumgold=:daynumgold, daynumoil=:daynumoil, maxdaynum=:maxdaynum WHERE id=:id");
            return super.batchUpdate2(sql.toString(), clan_members);
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
                "	`cname`  VARCHAR(32) NOT NULL," +
                "	`ucid`  INT(11) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`post`  INT(11) NOT NULL," +
                "	`donateGold`  INT(11) NOT NULL," +
                "	`donateOil`  INT(11) NOT NULL," +
                "	`curDGold`  INT(11) NOT NULL," +
                "	`curDOil`  INT(11) NOT NULL," +
                "	`lastDGold`  BIGINT(20) NOT NULL," +
                "	`lastDOil`  BIGINT(20) NOT NULL," +
                "	`renownAll`  INT(11) NOT NULL," +
                "	`renownWeek`  INT(11) NOT NULL," +
                "	`daynumgold`  INT(11) NOT NULL," +
                "	`daynumoil`  INT(11) NOT NULL," +
                "	`maxdaynum`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `pcid` (`pcid`)," +
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

    public void createNoUniqueTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`ccid`  VARCHAR(16) NOT NULL," +
                "	`cname`  VARCHAR(32) NOT NULL," +
                "	`ucid`  INT(11) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`post`  INT(11) NOT NULL," +
                "	`donateGold`  INT(11) NOT NULL," +
                "	`donateOil`  INT(11) NOT NULL," +
                "	`curDGold`  INT(11) NOT NULL," +
                "	`curDOil`  INT(11) NOT NULL," +
                "	`lastDGold`  BIGINT(20) NOT NULL," +
                "	`lastDOil`  BIGINT(20) NOT NULL," +
                "	`renownAll`  INT(11) NOT NULL," +
                "	`renownWeek`  INT(11) NOT NULL," +
                "	`daynumgold`  INT(11) NOT NULL," +
                "	`daynumoil`  INT(11) NOT NULL," +
                "	`maxdaynum`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `pcid` (`pcid`)," +
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
