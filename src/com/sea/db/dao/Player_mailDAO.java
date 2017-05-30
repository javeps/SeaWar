package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - player_mail
@SuppressWarnings({"rawtypes", "unchecked"})
public class Player_mailDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Player_mailDAO.class);

    public static final String TABLE = "player_mail";
    public static String TABLENAME = "player_mail";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "mcid", "type", "title", "content", "create_time", "fromId", "fromName", "toId", "toName", "isRead"};
    public static String coulmns = "id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead";
    public static String coulmns2 = "mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead";

    public Player_mailDAO(java.sql.Connection conn) {
        super(conn);
    }

    public Player_mailDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public Player_mailDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Player_mail player_mail) {
        return insert(player_mail, TABLENAME);
    }

    public int insert(final Player_mail player_mail, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            player_mail.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead) VALUES (:mcid, :type, :title, :content, :create_time, :fromId, :fromName, :toId, :toName, :isRead)");
            Map map = super.insert(sql.toString(), player_mail);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Player_mail player_mail) {
        asynchronousInsert(player_mail, TABLENAME);
    }

    public void asynchronousInsert(final Player_mail player_mail, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(player_mail, TABLENAME2);
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

    public int asynchronousInsert2(final Player_mail player_mail) {
        return asynchronousInsert2(player_mail, TABLENAME);
    }

    public int asynchronousInsert2(final Player_mail player_mail, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(player_mail, TABLENAME2);
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
        return player_mail.id;
    }

    public int insert2(final Player_mail player_mail) {
        return insert2(player_mail, TABLENAME);
    }

    public int insert2(final Player_mail player_mail, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            player_mail.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead) VALUES (:id, :mcid, :type, :title, :content, :create_time, :fromId, :fromName, :toId, :toName, :isRead)");
            Map map = super.insert(sql.toString(), player_mail);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Player_mail> player_mails) {
        return insert(player_mails, TABLENAME);
    }

    public int[] insert(final List<Player_mail> player_mails, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(player_mails == null || player_mails.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead) VALUES (:mcid, :type, :title, :content, :create_time, :fromId, :fromName, :toId, :toName, :isRead)");
            return super.batchInsert(sql.toString(), player_mails);
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

    public int deleteInBeans(final List<Player_mail> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Player_mail> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Player_mail player_mail = beans.get(i);
                int id = player_mail.id;
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

    public List<Player_mail> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Player_mail> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Player_mail.class);
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
            sql.append("SELECT id, mcid, type, fromId, toId FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_mail> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Player_mail> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Player_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_mail> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Player_mail> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Player_mail.class);
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

    public List<Player_mail> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Player_mail> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Player_mail.class);
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

    public Player_mail last() {
        return last(TABLENAME);
    }

    public Player_mail last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Player_mail.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_mail> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Player_mail> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Player_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_mail> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Player_mail> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Player_mail.class);
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

    public Player_mail selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Player_mail selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Player_mail.class);
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

    public int countByTypeToid(final Integer type, Integer toId) {
        return  countByTypeToid(type, toId, TABLENAME);
    }

    public int countByTypeToid(final Integer type, Integer toId, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE type=:type AND toId=:toId ");
            Map params = newMap();
            params.put("type", type);
            params.put("toId", toId);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_mail> selectByTypeToid(final Integer type, Integer toId) {
        return selectByTypeToid(type, toId, TABLENAME);
    }

    public List<Player_mail> selectByTypeToid(final Integer type, Integer toId, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE type=:type AND toId=:toId ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("toId", toId);
            return super.queryForList(sql.toString(), params, Player_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByTypeToidPKs(final Integer type, Integer toId) {
        return selectByTypeToidPKs(type, toId, TABLENAME);
    }

    public List<Integer> selectByTypeToidPKs(final Integer type, Integer toId, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND toId=:toId ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("toId", toId);
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

    public List<Player_mail> selectPageByTypeToid(final Integer type, Integer toId, final int begin, final int num) {
        return selectPageByTypeToid(type, toId, begin, num, TABLENAME);
    }

    public List<Player_mail> selectPageByTypeToid(final Integer type, Integer toId, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE type=:type AND toId=:toId ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("toId", toId);
            return super.queryForList(sql.toString(), params, Player_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByTypeToidPKs(final Integer type, Integer toId, final int begin, final int num) {
        return selectPageByTypeToidPKs(type, toId, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByTypeToidPKs(final Integer type, Integer toId, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND toId=:toId ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("toId", toId);
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

    public Player_mail selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Player_mail selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Player_mail.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByTypeFromid(final Integer type, Integer fromId) {
        return  countByTypeFromid(type, fromId, TABLENAME);
    }

    public int countByTypeFromid(final Integer type, Integer fromId, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE type=:type AND fromId=:fromId ");
            Map params = newMap();
            params.put("type", type);
            params.put("fromId", fromId);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_mail> selectByTypeFromid(final Integer type, Integer fromId) {
        return selectByTypeFromid(type, fromId, TABLENAME);
    }

    public List<Player_mail> selectByTypeFromid(final Integer type, Integer fromId, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE type=:type AND fromId=:fromId ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("fromId", fromId);
            return super.queryForList(sql.toString(), params, Player_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByTypeFromidPKs(final Integer type, Integer fromId) {
        return selectByTypeFromidPKs(type, fromId, TABLENAME);
    }

    public List<Integer> selectByTypeFromidPKs(final Integer type, Integer fromId, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND fromId=:fromId ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("fromId", fromId);
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

    public List<Player_mail> selectPageByTypeFromid(final Integer type, Integer fromId, final int begin, final int num) {
        return selectPageByTypeFromid(type, fromId, begin, num, TABLENAME);
    }

    public List<Player_mail> selectPageByTypeFromid(final Integer type, Integer fromId, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE type=:type AND fromId=:fromId ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("fromId", fromId);
            return super.queryForList(sql.toString(), params, Player_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByTypeFromidPKs(final Integer type, Integer fromId, final int begin, final int num) {
        return selectPageByTypeFromidPKs(type, fromId, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByTypeFromidPKs(final Integer type, Integer fromId, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND fromId=:fromId ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("fromId", fromId);
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

    public int countByType(final Integer type) {
        return countByType(type, TABLENAME);
    }

    public int countByType(final Integer type, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE type = :type ");
            Map params = newMap();
            params.put("type", type);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player_mail> selectByType(final Integer type) {
        return selectByType(type, TABLENAME);
    }

    public List<Player_mail> selectByType(final Integer type, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE type = :type ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            return super.queryForList(sql.toString(), params, Player_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByTypePKs(final Integer type) {
        return selectByTypePKs(type, TABLENAME);
    }

    public List<Integer> selectByTypePKs(final Integer type, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type = :type ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
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

    public List<Player_mail> selectPageByType(final Integer type, final int begin, final int num) {
        return selectPageByType(type, begin, num, TABLENAME);
    }

    public List<Player_mail> selectPageByType(final Integer type, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE type = :type ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            return super.queryForList(sql.toString(), params, Player_mail.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByTypePKs(final Integer type, final int begin, final int num) {
        return selectPageByTypePKs(type, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByTypePKs(final Integer type, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type = :type ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
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

    public Player_mail selectByMcid(final Integer mcid) {
        return selectByMcid(mcid, TABLENAME);
    }

    public Player_mail selectByMcid(final Integer mcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" WHERE mcid = :mcid");
            Map params = newMap();
            params.put("mcid", mcid);
            return super.queryForObject(sql.toString(), params, Player_mail.class);
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

    public List<Player_mail> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Player_mail> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, mcid, type, title, content, create_time, fromId, fromName, toId, toName, isRead FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Player_mail.class);
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

    public int updateByKey(final Player_mail player_mail) {
        return updateByKey(player_mail, TABLENAME);
    }

    public int updateByKey(final Player_mail player_mail, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = player_mail.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), player_mail);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Player_mail player_mail) {
        asynchronousUpdate(player_mail, TABLENAME);
    }

    public void asynchronousUpdate(final Player_mail player_mail, final String TABLENAME2) {
        try {

            String _ustr = player_mail.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, player_mail);
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

    public int updateMcidByKey(final int mcid, final int id){
        return updateMcidByKey(mcid, id, TABLENAME);
    }

    public int updateMcidByKey(final int mcid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET mcid=mcid+:mcid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("mcid", mcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMcidWithMinByKey(final int id, final int mcid, final int _min){
        return updateMcidWithMinByKey(id, mcid, _min, TABLENAME);
    }

    public int updateMcidWithMinByKey(final int id, final int mcid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET mcid = (select case when mcid+:mcid<=:_min then :_min else mcid+:mcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("mcid", mcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMcidWithMinInKeys(final List<Integer> keys, final int mcid, final int _min){
        return updateMcidWithMinInKeys(keys, mcid, _min, TABLENAME);
    }

    public int updateMcidWithMinInKeys(final List<Integer> keys, final int mcid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET mcid = (select case when mcid+:mcid<=:_min then :_min else mcid+:mcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("mcid", mcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMcidWithMaxByKey(final int id, final int mcid, final int _max){
        return updateMcidWithMaxByKey(id, mcid, _max, TABLENAME);
    }

    public int updateMcidWithMaxByKey(final int id, final int mcid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET mcid = (select case when mcid+:mcid>=:_max then :_max else mcid+:mcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("mcid", mcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMcidWithMaxInKeys(final List<Integer> keys, final int mcid, final int _max){
        return updateMcidWithMaxInKeys(keys, mcid, _max, TABLENAME);
    }

    public int updateMcidWithMaxInKeys(final List<Integer> keys, final int mcid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET mcid = (select case when mcid+:mcid>=:_max then :_max else mcid+:mcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("mcid", mcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMcidWithMinMaxByKey(final int id, final int mcid, final int _min, final int _max){
        return updateMcidWithMinMaxByKey(id, mcid, _min, _max, TABLENAME);
    }

    public int updateMcidWithMinMaxByKey(final int id, final int mcid, final int _min, final int _max, final String TABLENAME2){
        if( mcid < 0 ) {
            return updateMcidWithMinByKey(id, mcid, _min, TABLENAME2);
        } else {
            return updateMcidWithMaxByKey(id, mcid, _max, TABLENAME2);
        }
    }

    public int updateMcidWithMinMaxInKeys(final List<Integer> keys, final int mcid, final int _min, final int _max){
        return updateMcidWithMinMaxInKeys(keys, mcid, _min, _max, TABLENAME);
    }

    public int updateMcidWithMinMaxInKeys(final List<Integer> keys, final int mcid, final int _min, final int _max, final String TABLENAME2){
        if( mcid < 0 ) {
            return updateMcidWithMinInKeys(keys, mcid, _min, TABLENAME2);
        } else {
            return updateMcidWithMaxInKeys(keys, mcid, _max, TABLENAME2);
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

    public int updateFromidByKey(final int fromId, final int id){
        return updateFromidByKey(fromId, id, TABLENAME);
    }

    public int updateFromidByKey(final int fromId, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fromId=fromId+:fromId WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("fromId", fromId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFromidWithMinByKey(final int id, final int fromId, final int _min){
        return updateFromidWithMinByKey(id, fromId, _min, TABLENAME);
    }

    public int updateFromidWithMinByKey(final int id, final int fromId, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fromId = (select case when fromId+:fromId<=:_min then :_min else fromId+:fromId end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("fromId", fromId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFromidWithMinInKeys(final List<Integer> keys, final int fromId, final int _min){
        return updateFromidWithMinInKeys(keys, fromId, _min, TABLENAME);
    }

    public int updateFromidWithMinInKeys(final List<Integer> keys, final int fromId, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fromId = (select case when fromId+:fromId<=:_min then :_min else fromId+:fromId end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("fromId", fromId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFromidWithMaxByKey(final int id, final int fromId, final int _max){
        return updateFromidWithMaxByKey(id, fromId, _max, TABLENAME);
    }

    public int updateFromidWithMaxByKey(final int id, final int fromId, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fromId = (select case when fromId+:fromId>=:_max then :_max else fromId+:fromId end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("fromId", fromId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFromidWithMaxInKeys(final List<Integer> keys, final int fromId, final int _max){
        return updateFromidWithMaxInKeys(keys, fromId, _max, TABLENAME);
    }

    public int updateFromidWithMaxInKeys(final List<Integer> keys, final int fromId, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fromId = (select case when fromId+:fromId>=:_max then :_max else fromId+:fromId end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("fromId", fromId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFromidWithMinMaxByKey(final int id, final int fromId, final int _min, final int _max){
        return updateFromidWithMinMaxByKey(id, fromId, _min, _max, TABLENAME);
    }

    public int updateFromidWithMinMaxByKey(final int id, final int fromId, final int _min, final int _max, final String TABLENAME2){
        if( fromId < 0 ) {
            return updateFromidWithMinByKey(id, fromId, _min, TABLENAME2);
        } else {
            return updateFromidWithMaxByKey(id, fromId, _max, TABLENAME2);
        }
    }

    public int updateFromidWithMinMaxInKeys(final List<Integer> keys, final int fromId, final int _min, final int _max){
        return updateFromidWithMinMaxInKeys(keys, fromId, _min, _max, TABLENAME);
    }

    public int updateFromidWithMinMaxInKeys(final List<Integer> keys, final int fromId, final int _min, final int _max, final String TABLENAME2){
        if( fromId < 0 ) {
            return updateFromidWithMinInKeys(keys, fromId, _min, TABLENAME2);
        } else {
            return updateFromidWithMaxInKeys(keys, fromId, _max, TABLENAME2);
        }
    }

    public int updateToidByKey(final int toId, final int id){
        return updateToidByKey(toId, id, TABLENAME);
    }

    public int updateToidByKey(final int toId, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET toId=toId+:toId WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("toId", toId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateToidWithMinByKey(final int id, final int toId, final int _min){
        return updateToidWithMinByKey(id, toId, _min, TABLENAME);
    }

    public int updateToidWithMinByKey(final int id, final int toId, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET toId = (select case when toId+:toId<=:_min then :_min else toId+:toId end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("toId", toId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateToidWithMinInKeys(final List<Integer> keys, final int toId, final int _min){
        return updateToidWithMinInKeys(keys, toId, _min, TABLENAME);
    }

    public int updateToidWithMinInKeys(final List<Integer> keys, final int toId, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET toId = (select case when toId+:toId<=:_min then :_min else toId+:toId end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("toId", toId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateToidWithMaxByKey(final int id, final int toId, final int _max){
        return updateToidWithMaxByKey(id, toId, _max, TABLENAME);
    }

    public int updateToidWithMaxByKey(final int id, final int toId, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET toId = (select case when toId+:toId>=:_max then :_max else toId+:toId end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("toId", toId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateToidWithMaxInKeys(final List<Integer> keys, final int toId, final int _max){
        return updateToidWithMaxInKeys(keys, toId, _max, TABLENAME);
    }

    public int updateToidWithMaxInKeys(final List<Integer> keys, final int toId, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET toId = (select case when toId+:toId>=:_max then :_max else toId+:toId end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("toId", toId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateToidWithMinMaxByKey(final int id, final int toId, final int _min, final int _max){
        return updateToidWithMinMaxByKey(id, toId, _min, _max, TABLENAME);
    }

    public int updateToidWithMinMaxByKey(final int id, final int toId, final int _min, final int _max, final String TABLENAME2){
        if( toId < 0 ) {
            return updateToidWithMinByKey(id, toId, _min, TABLENAME2);
        } else {
            return updateToidWithMaxByKey(id, toId, _max, TABLENAME2);
        }
    }

    public int updateToidWithMinMaxInKeys(final List<Integer> keys, final int toId, final int _min, final int _max){
        return updateToidWithMinMaxInKeys(keys, toId, _min, _max, TABLENAME);
    }

    public int updateToidWithMinMaxInKeys(final List<Integer> keys, final int toId, final int _min, final int _max, final String TABLENAME2){
        if( toId < 0 ) {
            return updateToidWithMinInKeys(keys, toId, _min, TABLENAME2);
        } else {
            return updateToidWithMaxInKeys(keys, toId, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Player_mail> player_mails) {
        return updateByKey(player_mails, TABLENAME);
    }

    public int[] updateByKey (final List<Player_mail> player_mails, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(player_mails == null || player_mails.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET mcid=:mcid, type=:type, title=:title, content=:content, create_time=:create_time, fromId=:fromId, fromName=:fromName, toId=:toId, toName=:toName, isRead=:isRead WHERE id=:id");
            return super.batchUpdate2(sql.toString(), player_mails);
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
                "	`mcid`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`title`  VARCHAR(126) NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`create_time`  BIGINT(20) NOT NULL," +
                "	`fromId`  INT(11) NOT NULL," +
                "	`fromName`  VARCHAR(32) NOT NULL," +
                "	`toId`  INT(11) NOT NULL," +
                "	`toName`  VARCHAR(32) NOT NULL," +
                "	`isRead`  BIT(1) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `mcid` (`mcid`)," +
                "	KEY `type` (`type`)," +
                "	KEY `type_2` (`type`, `fromId`)," +
                "	KEY `type_3` (`type`, `toId`)" +
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
                "	`mcid`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`title`  VARCHAR(126) NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`create_time`  BIGINT(20) NOT NULL," +
                "	`fromId`  INT(11) NOT NULL," +
                "	`fromName`  VARCHAR(32) NOT NULL," +
                "	`toId`  INT(11) NOT NULL," +
                "	`toName`  VARCHAR(32) NOT NULL," +
                "	`isRead`  BIT(1) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `mcid` (`mcid`)," +
                "	KEY `type` (`type`)," +
                "	KEY `type_2` (`type`, `fromId`)," +
                "	KEY `type_3` (`type`, `toId`)" +
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
