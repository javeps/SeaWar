package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - attack_mail
@SuppressWarnings({"rawtypes", "unchecked"})
public class Attack_mailDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Attack_mailDAO.class);

    public static final String TABLE = "attack_mail";
    public static String TABLENAME = "attack_mail";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "attcid", "attPcid", "attPname", "beAttPcid", "beAttPname", "begin_time", "end_time", "star", "preGold", "preOil", "preAttRenown", "attRenown", "attGold", "attOil", "isHitBack", "clancid", "cname", "cicon", "offenHP", "offenAtt", "defccid", "defcname", "defcicon", "defenseHP", "defenseAtt", "attInfos", "beRewon", "egid", "num", "attHeros"};
    public static String coulmns = "id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros";
    public static String coulmns2 = "attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros";

    public Attack_mailDAO(java.sql.Connection conn) {
        super(conn);
    }

    public Attack_mailDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public Attack_mailDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Attack_mail attack_mail) {
        return insert(attack_mail, TABLENAME);
    }

    public int insert(final Attack_mail attack_mail, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            attack_mail.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros) VALUES (:attcid, :attPcid, :attPname, :beAttPcid, :beAttPname, :begin_time, :end_time, :star, :preGold, :preOil, :preAttRenown, :attRenown, :attGold, :attOil, :isHitBack, :clancid, :cname, :cicon, :offenHP, :offenAtt, :defccid, :defcname, :defcicon, :defenseHP, :defenseAtt, :attInfos, :beRewon, :egid, :num, :attHeros)");
            Map map = super.insert(sql.toString(), attack_mail);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Attack_mail attack_mail) {
        asynchronousInsert(attack_mail, TABLENAME);
    }

    public void asynchronousInsert(final Attack_mail attack_mail, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(attack_mail, TABLENAME2);
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

    public int asynchronousInsert2(final Attack_mail attack_mail) {
        return asynchronousInsert2(attack_mail, TABLENAME);
    }

    public int asynchronousInsert2(final Attack_mail attack_mail, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(attack_mail, TABLENAME2);
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
        return attack_mail.id;
    }

    public int insert2(final Attack_mail attack_mail) {
        return insert2(attack_mail, TABLENAME);
    }

    public int insert2(final Attack_mail attack_mail, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            attack_mail.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros) VALUES (:id, :attcid, :attPcid, :attPname, :beAttPcid, :beAttPname, :begin_time, :end_time, :star, :preGold, :preOil, :preAttRenown, :attRenown, :attGold, :attOil, :isHitBack, :clancid, :cname, :cicon, :offenHP, :offenAtt, :defccid, :defcname, :defcicon, :defenseHP, :defenseAtt, :attInfos, :beRewon, :egid, :num, :attHeros)");
            Map map = super.insert(sql.toString(), attack_mail);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Attack_mail> attack_mails) {
        return insert(attack_mails, TABLENAME);
    }

    public int[] insert(final List<Attack_mail> attack_mails, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(attack_mails == null || attack_mails.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros) VALUES (:attcid, :attPcid, :attPname, :beAttPcid, :beAttPname, :begin_time, :end_time, :star, :preGold, :preOil, :preAttRenown, :attRenown, :attGold, :attOil, :isHitBack, :clancid, :cname, :cicon, :offenHP, :offenAtt, :defccid, :defcname, :defcicon, :defenseHP, :defenseAtt, :attInfos, :beRewon, :egid, :num, :attHeros)");
            return super.batchInsert(sql.toString(), attack_mails);
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

    public int deleteInBeans(final List<Attack_mail> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Attack_mail> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Attack_mail attack_mail = beans.get(i);
                int id = attack_mail.id;
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

    public List<Attack_mail> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Attack_mail> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Attack_mail.class);
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
            sql.append("SELECT id, attcid, attPcid, beAttPcid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Attack_mail> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Attack_mail> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Attack_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Attack_mail> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Attack_mail> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Attack_mail.class);
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

    public List<Attack_mail> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Attack_mail> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Attack_mail.class);
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

    public Attack_mail last() {
        return last(TABLENAME);
    }

    public Attack_mail last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Attack_mail.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Attack_mail> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Attack_mail> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Attack_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Attack_mail> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Attack_mail> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Attack_mail.class);
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

    public Attack_mail selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Attack_mail selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Attack_mail.class);
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

    public Attack_mail selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Attack_mail selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Attack_mail.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByBeattpcid(final Integer beAttPcid) {
        return countByBeattpcid(beAttPcid, TABLENAME);
    }

    public int countByBeattpcid(final Integer beAttPcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE beAttPcid = :beAttPcid ");
            Map params = newMap();
            params.put("beAttPcid", beAttPcid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Attack_mail> selectByBeattpcid(final Integer beAttPcid) {
        return selectByBeattpcid(beAttPcid, TABLENAME);
    }

    public List<Attack_mail> selectByBeattpcid(final Integer beAttPcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE beAttPcid = :beAttPcid ORDER BY id ");
            Map params = newMap();
            params.put("beAttPcid", beAttPcid);
            return super.queryForList(sql.toString(), params, Attack_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByBeattpcidPKs(final Integer beAttPcid) {
        return selectByBeattpcidPKs(beAttPcid, TABLENAME);
    }

    public List<Integer> selectByBeattpcidPKs(final Integer beAttPcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE beAttPcid = :beAttPcid ORDER BY id ");
            Map params = newMap();
            params.put("beAttPcid", beAttPcid);
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

    public List<Attack_mail> selectPageByBeattpcid(final Integer beAttPcid, final int begin, final int num) {
        return selectPageByBeattpcid(beAttPcid, begin, num, TABLENAME);
    }

    public List<Attack_mail> selectPageByBeattpcid(final Integer beAttPcid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE beAttPcid = :beAttPcid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("beAttPcid", beAttPcid);
            return super.queryForList(sql.toString(), params, Attack_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByBeattpcidPKs(final Integer beAttPcid, final int begin, final int num) {
        return selectPageByBeattpcidPKs(beAttPcid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByBeattpcidPKs(final Integer beAttPcid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE beAttPcid = :beAttPcid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("beAttPcid", beAttPcid);
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

    public int countByAttpcid(final Integer attPcid) {
        return countByAttpcid(attPcid, TABLENAME);
    }

    public int countByAttpcid(final Integer attPcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE attPcid = :attPcid ");
            Map params = newMap();
            params.put("attPcid", attPcid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Attack_mail> selectByAttpcid(final Integer attPcid) {
        return selectByAttpcid(attPcid, TABLENAME);
    }

    public List<Attack_mail> selectByAttpcid(final Integer attPcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE attPcid = :attPcid ORDER BY id ");
            Map params = newMap();
            params.put("attPcid", attPcid);
            return super.queryForList(sql.toString(), params, Attack_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByAttpcidPKs(final Integer attPcid) {
        return selectByAttpcidPKs(attPcid, TABLENAME);
    }

    public List<Integer> selectByAttpcidPKs(final Integer attPcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE attPcid = :attPcid ORDER BY id ");
            Map params = newMap();
            params.put("attPcid", attPcid);
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

    public List<Attack_mail> selectPageByAttpcid(final Integer attPcid, final int begin, final int num) {
        return selectPageByAttpcid(attPcid, begin, num, TABLENAME);
    }

    public List<Attack_mail> selectPageByAttpcid(final Integer attPcid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE attPcid = :attPcid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("attPcid", attPcid);
            return super.queryForList(sql.toString(), params, Attack_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByAttpcidPKs(final Integer attPcid, final int begin, final int num) {
        return selectPageByAttpcidPKs(attPcid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByAttpcidPKs(final Integer attPcid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE attPcid = :attPcid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("attPcid", attPcid);
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

    public Attack_mail selectByAttcid(final String attcid) {
        return selectByAttcid(attcid, TABLENAME);
    }

    public Attack_mail selectByAttcid(final String attcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE attcid = :attcid");
            Map params = newMap();
            params.put("attcid", attcid);
            return super.queryForObject(sql.toString(), params, Attack_mail.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeAttcid(final String attcid) {
        return countLikeAttcid(attcid, TABLENAME);
    }

    public int countLikeAttcid(final String attcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE attcid LIKE '%").append(attcid).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Attack_mail> selectLikeAttcid(final String attcid) {
        return selectLikeAttcid(attcid, TABLENAME);
    }

    public List<Attack_mail> selectLikeAttcid(final String attcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" WHERE attcid LIKE '%").append(attcid).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), Attack_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeAttcidPKs(final String attcid) {
        return selectLikeAttcidPKs(attcid, TABLENAME);
    }

    public List<Integer> selectLikeAttcidPKs(final String attcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE attcid LIKE '%").append(attcid).append("%' ORDER BY id ");
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

    public List<Attack_mail> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Attack_mail> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, attcid, attPcid, attPname, beAttPcid, beAttPname, begin_time, end_time, star, preGold, preOil, preAttRenown, attRenown, attGold, attOil, isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid, defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num, attHeros FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Attack_mail.class);
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

    public int updateByKey(final Attack_mail attack_mail) {
        return updateByKey(attack_mail, TABLENAME);
    }

    public int updateByKey(final Attack_mail attack_mail, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = attack_mail.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), attack_mail);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Attack_mail attack_mail) {
        asynchronousUpdate(attack_mail, TABLENAME);
    }

    public void asynchronousUpdate(final Attack_mail attack_mail, final String TABLENAME2) {
        try {

            String _ustr = attack_mail.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, attack_mail);
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

    public int updateAttpcidByKey(final int attPcid, final int id){
        return updateAttpcidByKey(attPcid, id, TABLENAME);
    }

    public int updateAttpcidByKey(final int attPcid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attPcid=attPcid+:attPcid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("attPcid", attPcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttpcidWithMinByKey(final int id, final int attPcid, final int _min){
        return updateAttpcidWithMinByKey(id, attPcid, _min, TABLENAME);
    }

    public int updateAttpcidWithMinByKey(final int id, final int attPcid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attPcid = (select case when attPcid+:attPcid<=:_min then :_min else attPcid+:attPcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("attPcid", attPcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttpcidWithMinInKeys(final List<Integer> keys, final int attPcid, final int _min){
        return updateAttpcidWithMinInKeys(keys, attPcid, _min, TABLENAME);
    }

    public int updateAttpcidWithMinInKeys(final List<Integer> keys, final int attPcid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attPcid = (select case when attPcid+:attPcid<=:_min then :_min else attPcid+:attPcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("attPcid", attPcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttpcidWithMaxByKey(final int id, final int attPcid, final int _max){
        return updateAttpcidWithMaxByKey(id, attPcid, _max, TABLENAME);
    }

    public int updateAttpcidWithMaxByKey(final int id, final int attPcid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attPcid = (select case when attPcid+:attPcid>=:_max then :_max else attPcid+:attPcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("attPcid", attPcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttpcidWithMaxInKeys(final List<Integer> keys, final int attPcid, final int _max){
        return updateAttpcidWithMaxInKeys(keys, attPcid, _max, TABLENAME);
    }

    public int updateAttpcidWithMaxInKeys(final List<Integer> keys, final int attPcid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attPcid = (select case when attPcid+:attPcid>=:_max then :_max else attPcid+:attPcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("attPcid", attPcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttpcidWithMinMaxByKey(final int id, final int attPcid, final int _min, final int _max){
        return updateAttpcidWithMinMaxByKey(id, attPcid, _min, _max, TABLENAME);
    }

    public int updateAttpcidWithMinMaxByKey(final int id, final int attPcid, final int _min, final int _max, final String TABLENAME2){
        if( attPcid < 0 ) {
            return updateAttpcidWithMinByKey(id, attPcid, _min, TABLENAME2);
        } else {
            return updateAttpcidWithMaxByKey(id, attPcid, _max, TABLENAME2);
        }
    }

    public int updateAttpcidWithMinMaxInKeys(final List<Integer> keys, final int attPcid, final int _min, final int _max){
        return updateAttpcidWithMinMaxInKeys(keys, attPcid, _min, _max, TABLENAME);
    }

    public int updateAttpcidWithMinMaxInKeys(final List<Integer> keys, final int attPcid, final int _min, final int _max, final String TABLENAME2){
        if( attPcid < 0 ) {
            return updateAttpcidWithMinInKeys(keys, attPcid, _min, TABLENAME2);
        } else {
            return updateAttpcidWithMaxInKeys(keys, attPcid, _max, TABLENAME2);
        }
    }

    public int updateBeattpcidByKey(final int beAttPcid, final int id){
        return updateBeattpcidByKey(beAttPcid, id, TABLENAME);
    }

    public int updateBeattpcidByKey(final int beAttPcid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET beAttPcid=beAttPcid+:beAttPcid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("beAttPcid", beAttPcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBeattpcidWithMinByKey(final int id, final int beAttPcid, final int _min){
        return updateBeattpcidWithMinByKey(id, beAttPcid, _min, TABLENAME);
    }

    public int updateBeattpcidWithMinByKey(final int id, final int beAttPcid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET beAttPcid = (select case when beAttPcid+:beAttPcid<=:_min then :_min else beAttPcid+:beAttPcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("beAttPcid", beAttPcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBeattpcidWithMinInKeys(final List<Integer> keys, final int beAttPcid, final int _min){
        return updateBeattpcidWithMinInKeys(keys, beAttPcid, _min, TABLENAME);
    }

    public int updateBeattpcidWithMinInKeys(final List<Integer> keys, final int beAttPcid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET beAttPcid = (select case when beAttPcid+:beAttPcid<=:_min then :_min else beAttPcid+:beAttPcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("beAttPcid", beAttPcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBeattpcidWithMaxByKey(final int id, final int beAttPcid, final int _max){
        return updateBeattpcidWithMaxByKey(id, beAttPcid, _max, TABLENAME);
    }

    public int updateBeattpcidWithMaxByKey(final int id, final int beAttPcid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET beAttPcid = (select case when beAttPcid+:beAttPcid>=:_max then :_max else beAttPcid+:beAttPcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("beAttPcid", beAttPcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBeattpcidWithMaxInKeys(final List<Integer> keys, final int beAttPcid, final int _max){
        return updateBeattpcidWithMaxInKeys(keys, beAttPcid, _max, TABLENAME);
    }

    public int updateBeattpcidWithMaxInKeys(final List<Integer> keys, final int beAttPcid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET beAttPcid = (select case when beAttPcid+:beAttPcid>=:_max then :_max else beAttPcid+:beAttPcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("beAttPcid", beAttPcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBeattpcidWithMinMaxByKey(final int id, final int beAttPcid, final int _min, final int _max){
        return updateBeattpcidWithMinMaxByKey(id, beAttPcid, _min, _max, TABLENAME);
    }

    public int updateBeattpcidWithMinMaxByKey(final int id, final int beAttPcid, final int _min, final int _max, final String TABLENAME2){
        if( beAttPcid < 0 ) {
            return updateBeattpcidWithMinByKey(id, beAttPcid, _min, TABLENAME2);
        } else {
            return updateBeattpcidWithMaxByKey(id, beAttPcid, _max, TABLENAME2);
        }
    }

    public int updateBeattpcidWithMinMaxInKeys(final List<Integer> keys, final int beAttPcid, final int _min, final int _max){
        return updateBeattpcidWithMinMaxInKeys(keys, beAttPcid, _min, _max, TABLENAME);
    }

    public int updateBeattpcidWithMinMaxInKeys(final List<Integer> keys, final int beAttPcid, final int _min, final int _max, final String TABLENAME2){
        if( beAttPcid < 0 ) {
            return updateBeattpcidWithMinInKeys(keys, beAttPcid, _min, TABLENAME2);
        } else {
            return updateBeattpcidWithMaxInKeys(keys, beAttPcid, _max, TABLENAME2);
        }
    }

    public int updateBegin_timeByKey(final long begin_time, final int id){
        return updateBegin_timeByKey(begin_time, id, TABLENAME);
    }

    public int updateBegin_timeByKey(final long begin_time, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begin_time=begin_time+:begin_time WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("begin_time", begin_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegin_timeWithMinByKey(final int id, final long begin_time, final long _min){
        return updateBegin_timeWithMinByKey(id, begin_time, _min, TABLENAME);
    }

    public int updateBegin_timeWithMinByKey(final int id, final long begin_time, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begin_time = (select case when begin_time+:begin_time<=:_min then :_min else begin_time+:begin_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("begin_time", begin_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegin_timeWithMinInKeys(final List<Integer> keys, final long begin_time, final long _min){
        return updateBegin_timeWithMinInKeys(keys, begin_time, _min, TABLENAME);
    }

    public int updateBegin_timeWithMinInKeys(final List<Integer> keys, final long begin_time, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begin_time = (select case when begin_time+:begin_time<=:_min then :_min else begin_time+:begin_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("begin_time", begin_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegin_timeWithMaxByKey(final int id, final long begin_time, final long _max){
        return updateBegin_timeWithMaxByKey(id, begin_time, _max, TABLENAME);
    }

    public int updateBegin_timeWithMaxByKey(final int id, final long begin_time, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begin_time = (select case when begin_time+:begin_time>=:_max then :_max else begin_time+:begin_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("begin_time", begin_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegin_timeWithMaxInKeys(final List<Integer> keys, final long begin_time, final long _max){
        return updateBegin_timeWithMaxInKeys(keys, begin_time, _max, TABLENAME);
    }

    public int updateBegin_timeWithMaxInKeys(final List<Integer> keys, final long begin_time, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET begin_time = (select case when begin_time+:begin_time>=:_max then :_max else begin_time+:begin_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("begin_time", begin_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBegin_timeWithMinMaxByKey(final int id, final long begin_time, final long _min, final long _max){
        return updateBegin_timeWithMinMaxByKey(id, begin_time, _min, _max, TABLENAME);
    }

    public int updateBegin_timeWithMinMaxByKey(final int id, final long begin_time, final long _min, final long _max, final String TABLENAME2){
        if( begin_time < 0 ) {
            return updateBegin_timeWithMinByKey(id, begin_time, _min, TABLENAME2);
        } else {
            return updateBegin_timeWithMaxByKey(id, begin_time, _max, TABLENAME2);
        }
    }

    public int updateBegin_timeWithMinMaxInKeys(final List<Integer> keys, final long begin_time, final long _min, final long _max){
        return updateBegin_timeWithMinMaxInKeys(keys, begin_time, _min, _max, TABLENAME);
    }

    public int updateBegin_timeWithMinMaxInKeys(final List<Integer> keys, final long begin_time, final long _min, final long _max, final String TABLENAME2){
        if( begin_time < 0 ) {
            return updateBegin_timeWithMinInKeys(keys, begin_time, _min, TABLENAME2);
        } else {
            return updateBegin_timeWithMaxInKeys(keys, begin_time, _max, TABLENAME2);
        }
    }

    public int updateEnd_timeByKey(final long end_time, final int id){
        return updateEnd_timeByKey(end_time, id, TABLENAME);
    }

    public int updateEnd_timeByKey(final long end_time, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET end_time=end_time+:end_time WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("end_time", end_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEnd_timeWithMinByKey(final int id, final long end_time, final long _min){
        return updateEnd_timeWithMinByKey(id, end_time, _min, TABLENAME);
    }

    public int updateEnd_timeWithMinByKey(final int id, final long end_time, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET end_time = (select case when end_time+:end_time<=:_min then :_min else end_time+:end_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("end_time", end_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEnd_timeWithMinInKeys(final List<Integer> keys, final long end_time, final long _min){
        return updateEnd_timeWithMinInKeys(keys, end_time, _min, TABLENAME);
    }

    public int updateEnd_timeWithMinInKeys(final List<Integer> keys, final long end_time, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET end_time = (select case when end_time+:end_time<=:_min then :_min else end_time+:end_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("end_time", end_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEnd_timeWithMaxByKey(final int id, final long end_time, final long _max){
        return updateEnd_timeWithMaxByKey(id, end_time, _max, TABLENAME);
    }

    public int updateEnd_timeWithMaxByKey(final int id, final long end_time, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET end_time = (select case when end_time+:end_time>=:_max then :_max else end_time+:end_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("end_time", end_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEnd_timeWithMaxInKeys(final List<Integer> keys, final long end_time, final long _max){
        return updateEnd_timeWithMaxInKeys(keys, end_time, _max, TABLENAME);
    }

    public int updateEnd_timeWithMaxInKeys(final List<Integer> keys, final long end_time, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET end_time = (select case when end_time+:end_time>=:_max then :_max else end_time+:end_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("end_time", end_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEnd_timeWithMinMaxByKey(final int id, final long end_time, final long _min, final long _max){
        return updateEnd_timeWithMinMaxByKey(id, end_time, _min, _max, TABLENAME);
    }

    public int updateEnd_timeWithMinMaxByKey(final int id, final long end_time, final long _min, final long _max, final String TABLENAME2){
        if( end_time < 0 ) {
            return updateEnd_timeWithMinByKey(id, end_time, _min, TABLENAME2);
        } else {
            return updateEnd_timeWithMaxByKey(id, end_time, _max, TABLENAME2);
        }
    }

    public int updateEnd_timeWithMinMaxInKeys(final List<Integer> keys, final long end_time, final long _min, final long _max){
        return updateEnd_timeWithMinMaxInKeys(keys, end_time, _min, _max, TABLENAME);
    }

    public int updateEnd_timeWithMinMaxInKeys(final List<Integer> keys, final long end_time, final long _min, final long _max, final String TABLENAME2){
        if( end_time < 0 ) {
            return updateEnd_timeWithMinInKeys(keys, end_time, _min, TABLENAME2);
        } else {
            return updateEnd_timeWithMaxInKeys(keys, end_time, _max, TABLENAME2);
        }
    }

    public int updateStarByKey(final int star, final int id){
        return updateStarByKey(star, id, TABLENAME);
    }

    public int updateStarByKey(final int star, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET star=star+:star WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("star", star);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStarWithMinByKey(final int id, final int star, final int _min){
        return updateStarWithMinByKey(id, star, _min, TABLENAME);
    }

    public int updateStarWithMinByKey(final int id, final int star, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET star = (select case when star+:star<=:_min then :_min else star+:star end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("star", star);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStarWithMinInKeys(final List<Integer> keys, final int star, final int _min){
        return updateStarWithMinInKeys(keys, star, _min, TABLENAME);
    }

    public int updateStarWithMinInKeys(final List<Integer> keys, final int star, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET star = (select case when star+:star<=:_min then :_min else star+:star end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("star", star);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStarWithMaxByKey(final int id, final int star, final int _max){
        return updateStarWithMaxByKey(id, star, _max, TABLENAME);
    }

    public int updateStarWithMaxByKey(final int id, final int star, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET star = (select case when star+:star>=:_max then :_max else star+:star end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("star", star);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStarWithMaxInKeys(final List<Integer> keys, final int star, final int _max){
        return updateStarWithMaxInKeys(keys, star, _max, TABLENAME);
    }

    public int updateStarWithMaxInKeys(final List<Integer> keys, final int star, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET star = (select case when star+:star>=:_max then :_max else star+:star end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("star", star);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStarWithMinMaxByKey(final int id, final int star, final int _min, final int _max){
        return updateStarWithMinMaxByKey(id, star, _min, _max, TABLENAME);
    }

    public int updateStarWithMinMaxByKey(final int id, final int star, final int _min, final int _max, final String TABLENAME2){
        if( star < 0 ) {
            return updateStarWithMinByKey(id, star, _min, TABLENAME2);
        } else {
            return updateStarWithMaxByKey(id, star, _max, TABLENAME2);
        }
    }

    public int updateStarWithMinMaxInKeys(final List<Integer> keys, final int star, final int _min, final int _max){
        return updateStarWithMinMaxInKeys(keys, star, _min, _max, TABLENAME);
    }

    public int updateStarWithMinMaxInKeys(final List<Integer> keys, final int star, final int _min, final int _max, final String TABLENAME2){
        if( star < 0 ) {
            return updateStarWithMinInKeys(keys, star, _min, TABLENAME2);
        } else {
            return updateStarWithMaxInKeys(keys, star, _max, TABLENAME2);
        }
    }

    public int updatePregoldByKey(final int preGold, final int id){
        return updatePregoldByKey(preGold, id, TABLENAME);
    }

    public int updatePregoldByKey(final int preGold, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preGold=preGold+:preGold WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("preGold", preGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePregoldWithMinByKey(final int id, final int preGold, final int _min){
        return updatePregoldWithMinByKey(id, preGold, _min, TABLENAME);
    }

    public int updatePregoldWithMinByKey(final int id, final int preGold, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preGold = (select case when preGold+:preGold<=:_min then :_min else preGold+:preGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("preGold", preGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePregoldWithMinInKeys(final List<Integer> keys, final int preGold, final int _min){
        return updatePregoldWithMinInKeys(keys, preGold, _min, TABLENAME);
    }

    public int updatePregoldWithMinInKeys(final List<Integer> keys, final int preGold, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preGold = (select case when preGold+:preGold<=:_min then :_min else preGold+:preGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("preGold", preGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePregoldWithMaxByKey(final int id, final int preGold, final int _max){
        return updatePregoldWithMaxByKey(id, preGold, _max, TABLENAME);
    }

    public int updatePregoldWithMaxByKey(final int id, final int preGold, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preGold = (select case when preGold+:preGold>=:_max then :_max else preGold+:preGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("preGold", preGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePregoldWithMaxInKeys(final List<Integer> keys, final int preGold, final int _max){
        return updatePregoldWithMaxInKeys(keys, preGold, _max, TABLENAME);
    }

    public int updatePregoldWithMaxInKeys(final List<Integer> keys, final int preGold, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preGold = (select case when preGold+:preGold>=:_max then :_max else preGold+:preGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("preGold", preGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePregoldWithMinMaxByKey(final int id, final int preGold, final int _min, final int _max){
        return updatePregoldWithMinMaxByKey(id, preGold, _min, _max, TABLENAME);
    }

    public int updatePregoldWithMinMaxByKey(final int id, final int preGold, final int _min, final int _max, final String TABLENAME2){
        if( preGold < 0 ) {
            return updatePregoldWithMinByKey(id, preGold, _min, TABLENAME2);
        } else {
            return updatePregoldWithMaxByKey(id, preGold, _max, TABLENAME2);
        }
    }

    public int updatePregoldWithMinMaxInKeys(final List<Integer> keys, final int preGold, final int _min, final int _max){
        return updatePregoldWithMinMaxInKeys(keys, preGold, _min, _max, TABLENAME);
    }

    public int updatePregoldWithMinMaxInKeys(final List<Integer> keys, final int preGold, final int _min, final int _max, final String TABLENAME2){
        if( preGold < 0 ) {
            return updatePregoldWithMinInKeys(keys, preGold, _min, TABLENAME2);
        } else {
            return updatePregoldWithMaxInKeys(keys, preGold, _max, TABLENAME2);
        }
    }

    public int updatePreoilByKey(final int preOil, final int id){
        return updatePreoilByKey(preOil, id, TABLENAME);
    }

    public int updatePreoilByKey(final int preOil, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preOil=preOil+:preOil WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("preOil", preOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePreoilWithMinByKey(final int id, final int preOil, final int _min){
        return updatePreoilWithMinByKey(id, preOil, _min, TABLENAME);
    }

    public int updatePreoilWithMinByKey(final int id, final int preOil, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preOil = (select case when preOil+:preOil<=:_min then :_min else preOil+:preOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("preOil", preOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePreoilWithMinInKeys(final List<Integer> keys, final int preOil, final int _min){
        return updatePreoilWithMinInKeys(keys, preOil, _min, TABLENAME);
    }

    public int updatePreoilWithMinInKeys(final List<Integer> keys, final int preOil, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preOil = (select case when preOil+:preOil<=:_min then :_min else preOil+:preOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("preOil", preOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePreoilWithMaxByKey(final int id, final int preOil, final int _max){
        return updatePreoilWithMaxByKey(id, preOil, _max, TABLENAME);
    }

    public int updatePreoilWithMaxByKey(final int id, final int preOil, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preOil = (select case when preOil+:preOil>=:_max then :_max else preOil+:preOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("preOil", preOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePreoilWithMaxInKeys(final List<Integer> keys, final int preOil, final int _max){
        return updatePreoilWithMaxInKeys(keys, preOil, _max, TABLENAME);
    }

    public int updatePreoilWithMaxInKeys(final List<Integer> keys, final int preOil, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preOil = (select case when preOil+:preOil>=:_max then :_max else preOil+:preOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("preOil", preOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePreoilWithMinMaxByKey(final int id, final int preOil, final int _min, final int _max){
        return updatePreoilWithMinMaxByKey(id, preOil, _min, _max, TABLENAME);
    }

    public int updatePreoilWithMinMaxByKey(final int id, final int preOil, final int _min, final int _max, final String TABLENAME2){
        if( preOil < 0 ) {
            return updatePreoilWithMinByKey(id, preOil, _min, TABLENAME2);
        } else {
            return updatePreoilWithMaxByKey(id, preOil, _max, TABLENAME2);
        }
    }

    public int updatePreoilWithMinMaxInKeys(final List<Integer> keys, final int preOil, final int _min, final int _max){
        return updatePreoilWithMinMaxInKeys(keys, preOil, _min, _max, TABLENAME);
    }

    public int updatePreoilWithMinMaxInKeys(final List<Integer> keys, final int preOil, final int _min, final int _max, final String TABLENAME2){
        if( preOil < 0 ) {
            return updatePreoilWithMinInKeys(keys, preOil, _min, TABLENAME2);
        } else {
            return updatePreoilWithMaxInKeys(keys, preOil, _max, TABLENAME2);
        }
    }

    public int updatePreattrenownByKey(final int preAttRenown, final int id){
        return updatePreattrenownByKey(preAttRenown, id, TABLENAME);
    }

    public int updatePreattrenownByKey(final int preAttRenown, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preAttRenown=preAttRenown+:preAttRenown WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("preAttRenown", preAttRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePreattrenownWithMinByKey(final int id, final int preAttRenown, final int _min){
        return updatePreattrenownWithMinByKey(id, preAttRenown, _min, TABLENAME);
    }

    public int updatePreattrenownWithMinByKey(final int id, final int preAttRenown, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preAttRenown = (select case when preAttRenown+:preAttRenown<=:_min then :_min else preAttRenown+:preAttRenown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("preAttRenown", preAttRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePreattrenownWithMinInKeys(final List<Integer> keys, final int preAttRenown, final int _min){
        return updatePreattrenownWithMinInKeys(keys, preAttRenown, _min, TABLENAME);
    }

    public int updatePreattrenownWithMinInKeys(final List<Integer> keys, final int preAttRenown, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preAttRenown = (select case when preAttRenown+:preAttRenown<=:_min then :_min else preAttRenown+:preAttRenown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("preAttRenown", preAttRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePreattrenownWithMaxByKey(final int id, final int preAttRenown, final int _max){
        return updatePreattrenownWithMaxByKey(id, preAttRenown, _max, TABLENAME);
    }

    public int updatePreattrenownWithMaxByKey(final int id, final int preAttRenown, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preAttRenown = (select case when preAttRenown+:preAttRenown>=:_max then :_max else preAttRenown+:preAttRenown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("preAttRenown", preAttRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePreattrenownWithMaxInKeys(final List<Integer> keys, final int preAttRenown, final int _max){
        return updatePreattrenownWithMaxInKeys(keys, preAttRenown, _max, TABLENAME);
    }

    public int updatePreattrenownWithMaxInKeys(final List<Integer> keys, final int preAttRenown, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET preAttRenown = (select case when preAttRenown+:preAttRenown>=:_max then :_max else preAttRenown+:preAttRenown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("preAttRenown", preAttRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePreattrenownWithMinMaxByKey(final int id, final int preAttRenown, final int _min, final int _max){
        return updatePreattrenownWithMinMaxByKey(id, preAttRenown, _min, _max, TABLENAME);
    }

    public int updatePreattrenownWithMinMaxByKey(final int id, final int preAttRenown, final int _min, final int _max, final String TABLENAME2){
        if( preAttRenown < 0 ) {
            return updatePreattrenownWithMinByKey(id, preAttRenown, _min, TABLENAME2);
        } else {
            return updatePreattrenownWithMaxByKey(id, preAttRenown, _max, TABLENAME2);
        }
    }

    public int updatePreattrenownWithMinMaxInKeys(final List<Integer> keys, final int preAttRenown, final int _min, final int _max){
        return updatePreattrenownWithMinMaxInKeys(keys, preAttRenown, _min, _max, TABLENAME);
    }

    public int updatePreattrenownWithMinMaxInKeys(final List<Integer> keys, final int preAttRenown, final int _min, final int _max, final String TABLENAME2){
        if( preAttRenown < 0 ) {
            return updatePreattrenownWithMinInKeys(keys, preAttRenown, _min, TABLENAME2);
        } else {
            return updatePreattrenownWithMaxInKeys(keys, preAttRenown, _max, TABLENAME2);
        }
    }

    public int updateAttrenownByKey(final int attRenown, final int id){
        return updateAttrenownByKey(attRenown, id, TABLENAME);
    }

    public int updateAttrenownByKey(final int attRenown, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attRenown=attRenown+:attRenown WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("attRenown", attRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttrenownWithMinByKey(final int id, final int attRenown, final int _min){
        return updateAttrenownWithMinByKey(id, attRenown, _min, TABLENAME);
    }

    public int updateAttrenownWithMinByKey(final int id, final int attRenown, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attRenown = (select case when attRenown+:attRenown<=:_min then :_min else attRenown+:attRenown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("attRenown", attRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttrenownWithMinInKeys(final List<Integer> keys, final int attRenown, final int _min){
        return updateAttrenownWithMinInKeys(keys, attRenown, _min, TABLENAME);
    }

    public int updateAttrenownWithMinInKeys(final List<Integer> keys, final int attRenown, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attRenown = (select case when attRenown+:attRenown<=:_min then :_min else attRenown+:attRenown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("attRenown", attRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttrenownWithMaxByKey(final int id, final int attRenown, final int _max){
        return updateAttrenownWithMaxByKey(id, attRenown, _max, TABLENAME);
    }

    public int updateAttrenownWithMaxByKey(final int id, final int attRenown, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attRenown = (select case when attRenown+:attRenown>=:_max then :_max else attRenown+:attRenown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("attRenown", attRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttrenownWithMaxInKeys(final List<Integer> keys, final int attRenown, final int _max){
        return updateAttrenownWithMaxInKeys(keys, attRenown, _max, TABLENAME);
    }

    public int updateAttrenownWithMaxInKeys(final List<Integer> keys, final int attRenown, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attRenown = (select case when attRenown+:attRenown>=:_max then :_max else attRenown+:attRenown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("attRenown", attRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttrenownWithMinMaxByKey(final int id, final int attRenown, final int _min, final int _max){
        return updateAttrenownWithMinMaxByKey(id, attRenown, _min, _max, TABLENAME);
    }

    public int updateAttrenownWithMinMaxByKey(final int id, final int attRenown, final int _min, final int _max, final String TABLENAME2){
        if( attRenown < 0 ) {
            return updateAttrenownWithMinByKey(id, attRenown, _min, TABLENAME2);
        } else {
            return updateAttrenownWithMaxByKey(id, attRenown, _max, TABLENAME2);
        }
    }

    public int updateAttrenownWithMinMaxInKeys(final List<Integer> keys, final int attRenown, final int _min, final int _max){
        return updateAttrenownWithMinMaxInKeys(keys, attRenown, _min, _max, TABLENAME);
    }

    public int updateAttrenownWithMinMaxInKeys(final List<Integer> keys, final int attRenown, final int _min, final int _max, final String TABLENAME2){
        if( attRenown < 0 ) {
            return updateAttrenownWithMinInKeys(keys, attRenown, _min, TABLENAME2);
        } else {
            return updateAttrenownWithMaxInKeys(keys, attRenown, _max, TABLENAME2);
        }
    }

    public int updateAttgoldByKey(final int attGold, final int id){
        return updateAttgoldByKey(attGold, id, TABLENAME);
    }

    public int updateAttgoldByKey(final int attGold, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attGold=attGold+:attGold WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("attGold", attGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttgoldWithMinByKey(final int id, final int attGold, final int _min){
        return updateAttgoldWithMinByKey(id, attGold, _min, TABLENAME);
    }

    public int updateAttgoldWithMinByKey(final int id, final int attGold, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attGold = (select case when attGold+:attGold<=:_min then :_min else attGold+:attGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("attGold", attGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttgoldWithMinInKeys(final List<Integer> keys, final int attGold, final int _min){
        return updateAttgoldWithMinInKeys(keys, attGold, _min, TABLENAME);
    }

    public int updateAttgoldWithMinInKeys(final List<Integer> keys, final int attGold, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attGold = (select case when attGold+:attGold<=:_min then :_min else attGold+:attGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("attGold", attGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttgoldWithMaxByKey(final int id, final int attGold, final int _max){
        return updateAttgoldWithMaxByKey(id, attGold, _max, TABLENAME);
    }

    public int updateAttgoldWithMaxByKey(final int id, final int attGold, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attGold = (select case when attGold+:attGold>=:_max then :_max else attGold+:attGold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("attGold", attGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttgoldWithMaxInKeys(final List<Integer> keys, final int attGold, final int _max){
        return updateAttgoldWithMaxInKeys(keys, attGold, _max, TABLENAME);
    }

    public int updateAttgoldWithMaxInKeys(final List<Integer> keys, final int attGold, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attGold = (select case when attGold+:attGold>=:_max then :_max else attGold+:attGold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("attGold", attGold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttgoldWithMinMaxByKey(final int id, final int attGold, final int _min, final int _max){
        return updateAttgoldWithMinMaxByKey(id, attGold, _min, _max, TABLENAME);
    }

    public int updateAttgoldWithMinMaxByKey(final int id, final int attGold, final int _min, final int _max, final String TABLENAME2){
        if( attGold < 0 ) {
            return updateAttgoldWithMinByKey(id, attGold, _min, TABLENAME2);
        } else {
            return updateAttgoldWithMaxByKey(id, attGold, _max, TABLENAME2);
        }
    }

    public int updateAttgoldWithMinMaxInKeys(final List<Integer> keys, final int attGold, final int _min, final int _max){
        return updateAttgoldWithMinMaxInKeys(keys, attGold, _min, _max, TABLENAME);
    }

    public int updateAttgoldWithMinMaxInKeys(final List<Integer> keys, final int attGold, final int _min, final int _max, final String TABLENAME2){
        if( attGold < 0 ) {
            return updateAttgoldWithMinInKeys(keys, attGold, _min, TABLENAME2);
        } else {
            return updateAttgoldWithMaxInKeys(keys, attGold, _max, TABLENAME2);
        }
    }

    public int updateAttoilByKey(final int attOil, final int id){
        return updateAttoilByKey(attOil, id, TABLENAME);
    }

    public int updateAttoilByKey(final int attOil, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attOil=attOil+:attOil WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("attOil", attOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttoilWithMinByKey(final int id, final int attOil, final int _min){
        return updateAttoilWithMinByKey(id, attOil, _min, TABLENAME);
    }

    public int updateAttoilWithMinByKey(final int id, final int attOil, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attOil = (select case when attOil+:attOil<=:_min then :_min else attOil+:attOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("attOil", attOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttoilWithMinInKeys(final List<Integer> keys, final int attOil, final int _min){
        return updateAttoilWithMinInKeys(keys, attOil, _min, TABLENAME);
    }

    public int updateAttoilWithMinInKeys(final List<Integer> keys, final int attOil, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attOil = (select case when attOil+:attOil<=:_min then :_min else attOil+:attOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("attOil", attOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttoilWithMaxByKey(final int id, final int attOil, final int _max){
        return updateAttoilWithMaxByKey(id, attOil, _max, TABLENAME);
    }

    public int updateAttoilWithMaxByKey(final int id, final int attOil, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attOil = (select case when attOil+:attOil>=:_max then :_max else attOil+:attOil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("attOil", attOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttoilWithMaxInKeys(final List<Integer> keys, final int attOil, final int _max){
        return updateAttoilWithMaxInKeys(keys, attOil, _max, TABLENAME);
    }

    public int updateAttoilWithMaxInKeys(final List<Integer> keys, final int attOil, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attOil = (select case when attOil+:attOil>=:_max then :_max else attOil+:attOil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("attOil", attOil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAttoilWithMinMaxByKey(final int id, final int attOil, final int _min, final int _max){
        return updateAttoilWithMinMaxByKey(id, attOil, _min, _max, TABLENAME);
    }

    public int updateAttoilWithMinMaxByKey(final int id, final int attOil, final int _min, final int _max, final String TABLENAME2){
        if( attOil < 0 ) {
            return updateAttoilWithMinByKey(id, attOil, _min, TABLENAME2);
        } else {
            return updateAttoilWithMaxByKey(id, attOil, _max, TABLENAME2);
        }
    }

    public int updateAttoilWithMinMaxInKeys(final List<Integer> keys, final int attOil, final int _min, final int _max){
        return updateAttoilWithMinMaxInKeys(keys, attOil, _min, _max, TABLENAME);
    }

    public int updateAttoilWithMinMaxInKeys(final List<Integer> keys, final int attOil, final int _min, final int _max, final String TABLENAME2){
        if( attOil < 0 ) {
            return updateAttoilWithMinInKeys(keys, attOil, _min, TABLENAME2);
        } else {
            return updateAttoilWithMaxInKeys(keys, attOil, _max, TABLENAME2);
        }
    }

    public int updateCiconByKey(final int cicon, final int id){
        return updateCiconByKey(cicon, id, TABLENAME);
    }

    public int updateCiconByKey(final int cicon, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cicon=cicon+:cicon WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("cicon", cicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCiconWithMinByKey(final int id, final int cicon, final int _min){
        return updateCiconWithMinByKey(id, cicon, _min, TABLENAME);
    }

    public int updateCiconWithMinByKey(final int id, final int cicon, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cicon = (select case when cicon+:cicon<=:_min then :_min else cicon+:cicon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("cicon", cicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCiconWithMinInKeys(final List<Integer> keys, final int cicon, final int _min){
        return updateCiconWithMinInKeys(keys, cicon, _min, TABLENAME);
    }

    public int updateCiconWithMinInKeys(final List<Integer> keys, final int cicon, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cicon = (select case when cicon+:cicon<=:_min then :_min else cicon+:cicon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("cicon", cicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCiconWithMaxByKey(final int id, final int cicon, final int _max){
        return updateCiconWithMaxByKey(id, cicon, _max, TABLENAME);
    }

    public int updateCiconWithMaxByKey(final int id, final int cicon, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cicon = (select case when cicon+:cicon>=:_max then :_max else cicon+:cicon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("cicon", cicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCiconWithMaxInKeys(final List<Integer> keys, final int cicon, final int _max){
        return updateCiconWithMaxInKeys(keys, cicon, _max, TABLENAME);
    }

    public int updateCiconWithMaxInKeys(final List<Integer> keys, final int cicon, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cicon = (select case when cicon+:cicon>=:_max then :_max else cicon+:cicon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("cicon", cicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCiconWithMinMaxByKey(final int id, final int cicon, final int _min, final int _max){
        return updateCiconWithMinMaxByKey(id, cicon, _min, _max, TABLENAME);
    }

    public int updateCiconWithMinMaxByKey(final int id, final int cicon, final int _min, final int _max, final String TABLENAME2){
        if( cicon < 0 ) {
            return updateCiconWithMinByKey(id, cicon, _min, TABLENAME2);
        } else {
            return updateCiconWithMaxByKey(id, cicon, _max, TABLENAME2);
        }
    }

    public int updateCiconWithMinMaxInKeys(final List<Integer> keys, final int cicon, final int _min, final int _max){
        return updateCiconWithMinMaxInKeys(keys, cicon, _min, _max, TABLENAME);
    }

    public int updateCiconWithMinMaxInKeys(final List<Integer> keys, final int cicon, final int _min, final int _max, final String TABLENAME2){
        if( cicon < 0 ) {
            return updateCiconWithMinInKeys(keys, cicon, _min, TABLENAME2);
        } else {
            return updateCiconWithMaxInKeys(keys, cicon, _max, TABLENAME2);
        }
    }

    public int updateOffenhpByKey(final int offenHP, final int id){
        return updateOffenhpByKey(offenHP, id, TABLENAME);
    }

    public int updateOffenhpByKey(final int offenHP, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET offenHP=offenHP+:offenHP WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("offenHP", offenHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOffenhpWithMinByKey(final int id, final int offenHP, final int _min){
        return updateOffenhpWithMinByKey(id, offenHP, _min, TABLENAME);
    }

    public int updateOffenhpWithMinByKey(final int id, final int offenHP, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET offenHP = (select case when offenHP+:offenHP<=:_min then :_min else offenHP+:offenHP end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("offenHP", offenHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOffenhpWithMinInKeys(final List<Integer> keys, final int offenHP, final int _min){
        return updateOffenhpWithMinInKeys(keys, offenHP, _min, TABLENAME);
    }

    public int updateOffenhpWithMinInKeys(final List<Integer> keys, final int offenHP, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET offenHP = (select case when offenHP+:offenHP<=:_min then :_min else offenHP+:offenHP end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("offenHP", offenHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOffenhpWithMaxByKey(final int id, final int offenHP, final int _max){
        return updateOffenhpWithMaxByKey(id, offenHP, _max, TABLENAME);
    }

    public int updateOffenhpWithMaxByKey(final int id, final int offenHP, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET offenHP = (select case when offenHP+:offenHP>=:_max then :_max else offenHP+:offenHP end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("offenHP", offenHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOffenhpWithMaxInKeys(final List<Integer> keys, final int offenHP, final int _max){
        return updateOffenhpWithMaxInKeys(keys, offenHP, _max, TABLENAME);
    }

    public int updateOffenhpWithMaxInKeys(final List<Integer> keys, final int offenHP, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET offenHP = (select case when offenHP+:offenHP>=:_max then :_max else offenHP+:offenHP end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("offenHP", offenHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOffenhpWithMinMaxByKey(final int id, final int offenHP, final int _min, final int _max){
        return updateOffenhpWithMinMaxByKey(id, offenHP, _min, _max, TABLENAME);
    }

    public int updateOffenhpWithMinMaxByKey(final int id, final int offenHP, final int _min, final int _max, final String TABLENAME2){
        if( offenHP < 0 ) {
            return updateOffenhpWithMinByKey(id, offenHP, _min, TABLENAME2);
        } else {
            return updateOffenhpWithMaxByKey(id, offenHP, _max, TABLENAME2);
        }
    }

    public int updateOffenhpWithMinMaxInKeys(final List<Integer> keys, final int offenHP, final int _min, final int _max){
        return updateOffenhpWithMinMaxInKeys(keys, offenHP, _min, _max, TABLENAME);
    }

    public int updateOffenhpWithMinMaxInKeys(final List<Integer> keys, final int offenHP, final int _min, final int _max, final String TABLENAME2){
        if( offenHP < 0 ) {
            return updateOffenhpWithMinInKeys(keys, offenHP, _min, TABLENAME2);
        } else {
            return updateOffenhpWithMaxInKeys(keys, offenHP, _max, TABLENAME2);
        }
    }

    public int updateOffenattByKey(final int offenAtt, final int id){
        return updateOffenattByKey(offenAtt, id, TABLENAME);
    }

    public int updateOffenattByKey(final int offenAtt, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET offenAtt=offenAtt+:offenAtt WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("offenAtt", offenAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOffenattWithMinByKey(final int id, final int offenAtt, final int _min){
        return updateOffenattWithMinByKey(id, offenAtt, _min, TABLENAME);
    }

    public int updateOffenattWithMinByKey(final int id, final int offenAtt, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET offenAtt = (select case when offenAtt+:offenAtt<=:_min then :_min else offenAtt+:offenAtt end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("offenAtt", offenAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOffenattWithMinInKeys(final List<Integer> keys, final int offenAtt, final int _min){
        return updateOffenattWithMinInKeys(keys, offenAtt, _min, TABLENAME);
    }

    public int updateOffenattWithMinInKeys(final List<Integer> keys, final int offenAtt, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET offenAtt = (select case when offenAtt+:offenAtt<=:_min then :_min else offenAtt+:offenAtt end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("offenAtt", offenAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOffenattWithMaxByKey(final int id, final int offenAtt, final int _max){
        return updateOffenattWithMaxByKey(id, offenAtt, _max, TABLENAME);
    }

    public int updateOffenattWithMaxByKey(final int id, final int offenAtt, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET offenAtt = (select case when offenAtt+:offenAtt>=:_max then :_max else offenAtt+:offenAtt end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("offenAtt", offenAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOffenattWithMaxInKeys(final List<Integer> keys, final int offenAtt, final int _max){
        return updateOffenattWithMaxInKeys(keys, offenAtt, _max, TABLENAME);
    }

    public int updateOffenattWithMaxInKeys(final List<Integer> keys, final int offenAtt, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET offenAtt = (select case when offenAtt+:offenAtt>=:_max then :_max else offenAtt+:offenAtt end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("offenAtt", offenAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateOffenattWithMinMaxByKey(final int id, final int offenAtt, final int _min, final int _max){
        return updateOffenattWithMinMaxByKey(id, offenAtt, _min, _max, TABLENAME);
    }

    public int updateOffenattWithMinMaxByKey(final int id, final int offenAtt, final int _min, final int _max, final String TABLENAME2){
        if( offenAtt < 0 ) {
            return updateOffenattWithMinByKey(id, offenAtt, _min, TABLENAME2);
        } else {
            return updateOffenattWithMaxByKey(id, offenAtt, _max, TABLENAME2);
        }
    }

    public int updateOffenattWithMinMaxInKeys(final List<Integer> keys, final int offenAtt, final int _min, final int _max){
        return updateOffenattWithMinMaxInKeys(keys, offenAtt, _min, _max, TABLENAME);
    }

    public int updateOffenattWithMinMaxInKeys(final List<Integer> keys, final int offenAtt, final int _min, final int _max, final String TABLENAME2){
        if( offenAtt < 0 ) {
            return updateOffenattWithMinInKeys(keys, offenAtt, _min, TABLENAME2);
        } else {
            return updateOffenattWithMaxInKeys(keys, offenAtt, _max, TABLENAME2);
        }
    }

    public int updateDefciconByKey(final int defcicon, final int id){
        return updateDefciconByKey(defcicon, id, TABLENAME);
    }

    public int updateDefciconByKey(final int defcicon, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defcicon=defcicon+:defcicon WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("defcicon", defcicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefciconWithMinByKey(final int id, final int defcicon, final int _min){
        return updateDefciconWithMinByKey(id, defcicon, _min, TABLENAME);
    }

    public int updateDefciconWithMinByKey(final int id, final int defcicon, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defcicon = (select case when defcicon+:defcicon<=:_min then :_min else defcicon+:defcicon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("defcicon", defcicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefciconWithMinInKeys(final List<Integer> keys, final int defcicon, final int _min){
        return updateDefciconWithMinInKeys(keys, defcicon, _min, TABLENAME);
    }

    public int updateDefciconWithMinInKeys(final List<Integer> keys, final int defcicon, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defcicon = (select case when defcicon+:defcicon<=:_min then :_min else defcicon+:defcicon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("defcicon", defcicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefciconWithMaxByKey(final int id, final int defcicon, final int _max){
        return updateDefciconWithMaxByKey(id, defcicon, _max, TABLENAME);
    }

    public int updateDefciconWithMaxByKey(final int id, final int defcicon, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defcicon = (select case when defcicon+:defcicon>=:_max then :_max else defcicon+:defcicon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("defcicon", defcicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefciconWithMaxInKeys(final List<Integer> keys, final int defcicon, final int _max){
        return updateDefciconWithMaxInKeys(keys, defcicon, _max, TABLENAME);
    }

    public int updateDefciconWithMaxInKeys(final List<Integer> keys, final int defcicon, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defcicon = (select case when defcicon+:defcicon>=:_max then :_max else defcicon+:defcicon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("defcicon", defcicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefciconWithMinMaxByKey(final int id, final int defcicon, final int _min, final int _max){
        return updateDefciconWithMinMaxByKey(id, defcicon, _min, _max, TABLENAME);
    }

    public int updateDefciconWithMinMaxByKey(final int id, final int defcicon, final int _min, final int _max, final String TABLENAME2){
        if( defcicon < 0 ) {
            return updateDefciconWithMinByKey(id, defcicon, _min, TABLENAME2);
        } else {
            return updateDefciconWithMaxByKey(id, defcicon, _max, TABLENAME2);
        }
    }

    public int updateDefciconWithMinMaxInKeys(final List<Integer> keys, final int defcicon, final int _min, final int _max){
        return updateDefciconWithMinMaxInKeys(keys, defcicon, _min, _max, TABLENAME);
    }

    public int updateDefciconWithMinMaxInKeys(final List<Integer> keys, final int defcicon, final int _min, final int _max, final String TABLENAME2){
        if( defcicon < 0 ) {
            return updateDefciconWithMinInKeys(keys, defcicon, _min, TABLENAME2);
        } else {
            return updateDefciconWithMaxInKeys(keys, defcicon, _max, TABLENAME2);
        }
    }

    public int updateDefensehpByKey(final int defenseHP, final int id){
        return updateDefensehpByKey(defenseHP, id, TABLENAME);
    }

    public int updateDefensehpByKey(final int defenseHP, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defenseHP=defenseHP+:defenseHP WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("defenseHP", defenseHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefensehpWithMinByKey(final int id, final int defenseHP, final int _min){
        return updateDefensehpWithMinByKey(id, defenseHP, _min, TABLENAME);
    }

    public int updateDefensehpWithMinByKey(final int id, final int defenseHP, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defenseHP = (select case when defenseHP+:defenseHP<=:_min then :_min else defenseHP+:defenseHP end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("defenseHP", defenseHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefensehpWithMinInKeys(final List<Integer> keys, final int defenseHP, final int _min){
        return updateDefensehpWithMinInKeys(keys, defenseHP, _min, TABLENAME);
    }

    public int updateDefensehpWithMinInKeys(final List<Integer> keys, final int defenseHP, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defenseHP = (select case when defenseHP+:defenseHP<=:_min then :_min else defenseHP+:defenseHP end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("defenseHP", defenseHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefensehpWithMaxByKey(final int id, final int defenseHP, final int _max){
        return updateDefensehpWithMaxByKey(id, defenseHP, _max, TABLENAME);
    }

    public int updateDefensehpWithMaxByKey(final int id, final int defenseHP, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defenseHP = (select case when defenseHP+:defenseHP>=:_max then :_max else defenseHP+:defenseHP end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("defenseHP", defenseHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefensehpWithMaxInKeys(final List<Integer> keys, final int defenseHP, final int _max){
        return updateDefensehpWithMaxInKeys(keys, defenseHP, _max, TABLENAME);
    }

    public int updateDefensehpWithMaxInKeys(final List<Integer> keys, final int defenseHP, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defenseHP = (select case when defenseHP+:defenseHP>=:_max then :_max else defenseHP+:defenseHP end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("defenseHP", defenseHP);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefensehpWithMinMaxByKey(final int id, final int defenseHP, final int _min, final int _max){
        return updateDefensehpWithMinMaxByKey(id, defenseHP, _min, _max, TABLENAME);
    }

    public int updateDefensehpWithMinMaxByKey(final int id, final int defenseHP, final int _min, final int _max, final String TABLENAME2){
        if( defenseHP < 0 ) {
            return updateDefensehpWithMinByKey(id, defenseHP, _min, TABLENAME2);
        } else {
            return updateDefensehpWithMaxByKey(id, defenseHP, _max, TABLENAME2);
        }
    }

    public int updateDefensehpWithMinMaxInKeys(final List<Integer> keys, final int defenseHP, final int _min, final int _max){
        return updateDefensehpWithMinMaxInKeys(keys, defenseHP, _min, _max, TABLENAME);
    }

    public int updateDefensehpWithMinMaxInKeys(final List<Integer> keys, final int defenseHP, final int _min, final int _max, final String TABLENAME2){
        if( defenseHP < 0 ) {
            return updateDefensehpWithMinInKeys(keys, defenseHP, _min, TABLENAME2);
        } else {
            return updateDefensehpWithMaxInKeys(keys, defenseHP, _max, TABLENAME2);
        }
    }

    public int updateDefenseattByKey(final int defenseAtt, final int id){
        return updateDefenseattByKey(defenseAtt, id, TABLENAME);
    }

    public int updateDefenseattByKey(final int defenseAtt, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defenseAtt=defenseAtt+:defenseAtt WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("defenseAtt", defenseAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefenseattWithMinByKey(final int id, final int defenseAtt, final int _min){
        return updateDefenseattWithMinByKey(id, defenseAtt, _min, TABLENAME);
    }

    public int updateDefenseattWithMinByKey(final int id, final int defenseAtt, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defenseAtt = (select case when defenseAtt+:defenseAtt<=:_min then :_min else defenseAtt+:defenseAtt end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("defenseAtt", defenseAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefenseattWithMinInKeys(final List<Integer> keys, final int defenseAtt, final int _min){
        return updateDefenseattWithMinInKeys(keys, defenseAtt, _min, TABLENAME);
    }

    public int updateDefenseattWithMinInKeys(final List<Integer> keys, final int defenseAtt, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defenseAtt = (select case when defenseAtt+:defenseAtt<=:_min then :_min else defenseAtt+:defenseAtt end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("defenseAtt", defenseAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefenseattWithMaxByKey(final int id, final int defenseAtt, final int _max){
        return updateDefenseattWithMaxByKey(id, defenseAtt, _max, TABLENAME);
    }

    public int updateDefenseattWithMaxByKey(final int id, final int defenseAtt, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defenseAtt = (select case when defenseAtt+:defenseAtt>=:_max then :_max else defenseAtt+:defenseAtt end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("defenseAtt", defenseAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefenseattWithMaxInKeys(final List<Integer> keys, final int defenseAtt, final int _max){
        return updateDefenseattWithMaxInKeys(keys, defenseAtt, _max, TABLENAME);
    }

    public int updateDefenseattWithMaxInKeys(final List<Integer> keys, final int defenseAtt, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET defenseAtt = (select case when defenseAtt+:defenseAtt>=:_max then :_max else defenseAtt+:defenseAtt end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("defenseAtt", defenseAtt);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateDefenseattWithMinMaxByKey(final int id, final int defenseAtt, final int _min, final int _max){
        return updateDefenseattWithMinMaxByKey(id, defenseAtt, _min, _max, TABLENAME);
    }

    public int updateDefenseattWithMinMaxByKey(final int id, final int defenseAtt, final int _min, final int _max, final String TABLENAME2){
        if( defenseAtt < 0 ) {
            return updateDefenseattWithMinByKey(id, defenseAtt, _min, TABLENAME2);
        } else {
            return updateDefenseattWithMaxByKey(id, defenseAtt, _max, TABLENAME2);
        }
    }

    public int updateDefenseattWithMinMaxInKeys(final List<Integer> keys, final int defenseAtt, final int _min, final int _max){
        return updateDefenseattWithMinMaxInKeys(keys, defenseAtt, _min, _max, TABLENAME);
    }

    public int updateDefenseattWithMinMaxInKeys(final List<Integer> keys, final int defenseAtt, final int _min, final int _max, final String TABLENAME2){
        if( defenseAtt < 0 ) {
            return updateDefenseattWithMinInKeys(keys, defenseAtt, _min, TABLENAME2);
        } else {
            return updateDefenseattWithMaxInKeys(keys, defenseAtt, _max, TABLENAME2);
        }
    }

    public int updateBerewonByKey(final int beRewon, final int id){
        return updateBerewonByKey(beRewon, id, TABLENAME);
    }

    public int updateBerewonByKey(final int beRewon, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET beRewon=beRewon+:beRewon WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("beRewon", beRewon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBerewonWithMinByKey(final int id, final int beRewon, final int _min){
        return updateBerewonWithMinByKey(id, beRewon, _min, TABLENAME);
    }

    public int updateBerewonWithMinByKey(final int id, final int beRewon, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET beRewon = (select case when beRewon+:beRewon<=:_min then :_min else beRewon+:beRewon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("beRewon", beRewon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBerewonWithMinInKeys(final List<Integer> keys, final int beRewon, final int _min){
        return updateBerewonWithMinInKeys(keys, beRewon, _min, TABLENAME);
    }

    public int updateBerewonWithMinInKeys(final List<Integer> keys, final int beRewon, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET beRewon = (select case when beRewon+:beRewon<=:_min then :_min else beRewon+:beRewon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("beRewon", beRewon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBerewonWithMaxByKey(final int id, final int beRewon, final int _max){
        return updateBerewonWithMaxByKey(id, beRewon, _max, TABLENAME);
    }

    public int updateBerewonWithMaxByKey(final int id, final int beRewon, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET beRewon = (select case when beRewon+:beRewon>=:_max then :_max else beRewon+:beRewon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("beRewon", beRewon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBerewonWithMaxInKeys(final List<Integer> keys, final int beRewon, final int _max){
        return updateBerewonWithMaxInKeys(keys, beRewon, _max, TABLENAME);
    }

    public int updateBerewonWithMaxInKeys(final List<Integer> keys, final int beRewon, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET beRewon = (select case when beRewon+:beRewon>=:_max then :_max else beRewon+:beRewon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("beRewon", beRewon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateBerewonWithMinMaxByKey(final int id, final int beRewon, final int _min, final int _max){
        return updateBerewonWithMinMaxByKey(id, beRewon, _min, _max, TABLENAME);
    }

    public int updateBerewonWithMinMaxByKey(final int id, final int beRewon, final int _min, final int _max, final String TABLENAME2){
        if( beRewon < 0 ) {
            return updateBerewonWithMinByKey(id, beRewon, _min, TABLENAME2);
        } else {
            return updateBerewonWithMaxByKey(id, beRewon, _max, TABLENAME2);
        }
    }

    public int updateBerewonWithMinMaxInKeys(final List<Integer> keys, final int beRewon, final int _min, final int _max){
        return updateBerewonWithMinMaxInKeys(keys, beRewon, _min, _max, TABLENAME);
    }

    public int updateBerewonWithMinMaxInKeys(final List<Integer> keys, final int beRewon, final int _min, final int _max, final String TABLENAME2){
        if( beRewon < 0 ) {
            return updateBerewonWithMinInKeys(keys, beRewon, _min, TABLENAME2);
        } else {
            return updateBerewonWithMaxInKeys(keys, beRewon, _max, TABLENAME2);
        }
    }

    public int updateEgidByKey(final int egid, final int id){
        return updateEgidByKey(egid, id, TABLENAME);
    }

    public int updateEgidByKey(final int egid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET egid=egid+:egid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("egid", egid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEgidWithMinByKey(final int id, final int egid, final int _min){
        return updateEgidWithMinByKey(id, egid, _min, TABLENAME);
    }

    public int updateEgidWithMinByKey(final int id, final int egid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET egid = (select case when egid+:egid<=:_min then :_min else egid+:egid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("egid", egid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEgidWithMinInKeys(final List<Integer> keys, final int egid, final int _min){
        return updateEgidWithMinInKeys(keys, egid, _min, TABLENAME);
    }

    public int updateEgidWithMinInKeys(final List<Integer> keys, final int egid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET egid = (select case when egid+:egid<=:_min then :_min else egid+:egid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("egid", egid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEgidWithMaxByKey(final int id, final int egid, final int _max){
        return updateEgidWithMaxByKey(id, egid, _max, TABLENAME);
    }

    public int updateEgidWithMaxByKey(final int id, final int egid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET egid = (select case when egid+:egid>=:_max then :_max else egid+:egid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("egid", egid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEgidWithMaxInKeys(final List<Integer> keys, final int egid, final int _max){
        return updateEgidWithMaxInKeys(keys, egid, _max, TABLENAME);
    }

    public int updateEgidWithMaxInKeys(final List<Integer> keys, final int egid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET egid = (select case when egid+:egid>=:_max then :_max else egid+:egid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("egid", egid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateEgidWithMinMaxByKey(final int id, final int egid, final int _min, final int _max){
        return updateEgidWithMinMaxByKey(id, egid, _min, _max, TABLENAME);
    }

    public int updateEgidWithMinMaxByKey(final int id, final int egid, final int _min, final int _max, final String TABLENAME2){
        if( egid < 0 ) {
            return updateEgidWithMinByKey(id, egid, _min, TABLENAME2);
        } else {
            return updateEgidWithMaxByKey(id, egid, _max, TABLENAME2);
        }
    }

    public int updateEgidWithMinMaxInKeys(final List<Integer> keys, final int egid, final int _min, final int _max){
        return updateEgidWithMinMaxInKeys(keys, egid, _min, _max, TABLENAME);
    }

    public int updateEgidWithMinMaxInKeys(final List<Integer> keys, final int egid, final int _min, final int _max, final String TABLENAME2){
        if( egid < 0 ) {
            return updateEgidWithMinInKeys(keys, egid, _min, TABLENAME2);
        } else {
            return updateEgidWithMaxInKeys(keys, egid, _max, TABLENAME2);
        }
    }

    public int updateNumByKey(final int num, final int id){
        return updateNumByKey(num, id, TABLENAME);
    }

    public int updateNumByKey(final int num, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num=num+:num WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMinByKey(final int id, final int num, final int _min){
        return updateNumWithMinByKey(id, num, _min, TABLENAME);
    }

    public int updateNumWithMinByKey(final int id, final int num, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num<=:_min then :_min else num+:num end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMinInKeys(final List<Integer> keys, final int num, final int _min){
        return updateNumWithMinInKeys(keys, num, _min, TABLENAME);
    }

    public int updateNumWithMinInKeys(final List<Integer> keys, final int num, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num<=:_min then :_min else num+:num end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMaxByKey(final int id, final int num, final int _max){
        return updateNumWithMaxByKey(id, num, _max, TABLENAME);
    }

    public int updateNumWithMaxByKey(final int id, final int num, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num>=:_max then :_max else num+:num end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMaxInKeys(final List<Integer> keys, final int num, final int _max){
        return updateNumWithMaxInKeys(keys, num, _max, TABLENAME);
    }

    public int updateNumWithMaxInKeys(final List<Integer> keys, final int num, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET num = (select case when num+:num>=:_max then :_max else num+:num end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("num", num);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNumWithMinMaxByKey(final int id, final int num, final int _min, final int _max){
        return updateNumWithMinMaxByKey(id, num, _min, _max, TABLENAME);
    }

    public int updateNumWithMinMaxByKey(final int id, final int num, final int _min, final int _max, final String TABLENAME2){
        if( num < 0 ) {
            return updateNumWithMinByKey(id, num, _min, TABLENAME2);
        } else {
            return updateNumWithMaxByKey(id, num, _max, TABLENAME2);
        }
    }

    public int updateNumWithMinMaxInKeys(final List<Integer> keys, final int num, final int _min, final int _max){
        return updateNumWithMinMaxInKeys(keys, num, _min, _max, TABLENAME);
    }

    public int updateNumWithMinMaxInKeys(final List<Integer> keys, final int num, final int _min, final int _max, final String TABLENAME2){
        if( num < 0 ) {
            return updateNumWithMinInKeys(keys, num, _min, TABLENAME2);
        } else {
            return updateNumWithMaxInKeys(keys, num, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Attack_mail> attack_mails) {
        return updateByKey(attack_mails, TABLENAME);
    }

    public int[] updateByKey (final List<Attack_mail> attack_mails, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(attack_mails == null || attack_mails.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET attcid=:attcid, attPcid=:attPcid, attPname=:attPname, beAttPcid=:beAttPcid, beAttPname=:beAttPname, begin_time=:begin_time, end_time=:end_time, star=:star, preGold=:preGold, preOil=:preOil, preAttRenown=:preAttRenown, attRenown=:attRenown, attGold=:attGold, attOil=:attOil, isHitBack=:isHitBack, clancid=:clancid, cname=:cname, cicon=:cicon, offenHP=:offenHP, offenAtt=:offenAtt, defccid=:defccid, defcname=:defcname, defcicon=:defcicon, defenseHP=:defenseHP, defenseAtt=:defenseAtt, attInfos=:attInfos, beRewon=:beRewon, egid=:egid, num=:num, attHeros=:attHeros WHERE id=:id");
            return super.batchUpdate2(sql.toString(), attack_mails);
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
                "	`attcid`  VARCHAR(32) NOT NULL," +
                "	`attPcid`  INT(11) NOT NULL," +
                "	`attPname`  VARCHAR(32) NOT NULL," +
                "	`beAttPcid`  INT(11) NOT NULL," +
                "	`beAttPname`  VARCHAR(32) NOT NULL," +
                "	`begin_time`  BIGINT(20) NOT NULL," +
                "	`end_time`  BIGINT(20) NOT NULL," +
                "	`star`  INT(11) NOT NULL," +
                "	`preGold`  INT(11) NOT NULL," +
                "	`preOil`  INT(11) NOT NULL," +
                "	`preAttRenown`  INT(11) NOT NULL," +
                "	`attRenown`  INT(11) NOT NULL," +
                "	`attGold`  INT(11) NOT NULL," +
                "	`attOil`  INT(11) NOT NULL," +
                "	`isHitBack`  BIT(1) NOT NULL," +
                "	`clancid`  VARCHAR(16) NOT NULL," +
                "	`cname`  VARCHAR(32) NOT NULL," +
                "	`cicon`  INT(11) NOT NULL," +
                "	`offenHP`  INT(11) NOT NULL," +
                "	`offenAtt`  INT(11) NOT NULL," +
                "	`defccid`  VARCHAR(16) NOT NULL," +
                "	`defcname`  VARCHAR(32) NOT NULL," +
                "	`defcicon`  INT(11) NOT NULL," +
                "	`defenseHP`  INT(11) NOT NULL," +
                "	`defenseAtt`  INT(11) NOT NULL," +
                "	`attInfos`  TEXT NOT NULL," +
                "	`beRewon`  INT(11) NOT NULL," +
                "	`egid`  INT(11) NOT NULL," +
                "	`num`  INT(11) NOT NULL," +
                "	`attHeros`  TEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `attcid` (`attcid`)," +
                "	KEY `beAttPid` (`beAttPcid`)," +
                "	KEY `attPid` (`attPcid`)" +
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
                "	`attcid`  VARCHAR(32) NOT NULL," +
                "	`attPcid`  INT(11) NOT NULL," +
                "	`attPname`  VARCHAR(32) NOT NULL," +
                "	`beAttPcid`  INT(11) NOT NULL," +
                "	`beAttPname`  VARCHAR(32) NOT NULL," +
                "	`begin_time`  BIGINT(20) NOT NULL," +
                "	`end_time`  BIGINT(20) NOT NULL," +
                "	`star`  INT(11) NOT NULL," +
                "	`preGold`  INT(11) NOT NULL," +
                "	`preOil`  INT(11) NOT NULL," +
                "	`preAttRenown`  INT(11) NOT NULL," +
                "	`attRenown`  INT(11) NOT NULL," +
                "	`attGold`  INT(11) NOT NULL," +
                "	`attOil`  INT(11) NOT NULL," +
                "	`isHitBack`  BIT(1) NOT NULL," +
                "	`clancid`  VARCHAR(16) NOT NULL," +
                "	`cname`  VARCHAR(32) NOT NULL," +
                "	`cicon`  INT(11) NOT NULL," +
                "	`offenHP`  INT(11) NOT NULL," +
                "	`offenAtt`  INT(11) NOT NULL," +
                "	`defccid`  VARCHAR(16) NOT NULL," +
                "	`defcname`  VARCHAR(32) NOT NULL," +
                "	`defcicon`  INT(11) NOT NULL," +
                "	`defenseHP`  INT(11) NOT NULL," +
                "	`defenseAtt`  INT(11) NOT NULL," +
                "	`attInfos`  TEXT NOT NULL," +
                "	`beRewon`  INT(11) NOT NULL," +
                "	`egid`  INT(11) NOT NULL," +
                "	`num`  INT(11) NOT NULL," +
                "	`attHeros`  TEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `attcid` (`attcid`)," +
                "	KEY `beAttPid` (`beAttPcid`)," +
                "	KEY `attPid` (`attPcid`)" +
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
