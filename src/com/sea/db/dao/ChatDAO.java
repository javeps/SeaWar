package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - chat
@SuppressWarnings({"rawtypes", "unchecked"})
public class ChatDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(ChatDAO.class);

    public static final String TABLE = "chat";
    public static String TABLENAME = "chat";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "ccid", "type", "content", "create_time", "fromId", "fromName", "toId", "toName", "clancid"};
    public static String coulmns = "id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid";
    public static String coulmns2 = "ccid, type, content, create_time, fromId, fromName, toId, toName, clancid";

    public ChatDAO(java.sql.Connection conn) {
        super(conn);
    }

    public ChatDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public ChatDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Chat chat) {
        return insert(chat, TABLENAME);
    }

    public int insert(final Chat chat, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            chat.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ccid, type, content, create_time, fromId, fromName, toId, toName, clancid) VALUES (:ccid, :type, :content, :create_time, :fromId, :fromName, :toId, :toName, :clancid)");
            Map map = super.insert(sql.toString(), chat);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Chat chat) {
        asynchronousInsert(chat, TABLENAME);
    }

    public void asynchronousInsert(final Chat chat, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(chat, TABLENAME2);
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

    public int asynchronousInsert2(final Chat chat) {
        return asynchronousInsert2(chat, TABLENAME);
    }

    public int asynchronousInsert2(final Chat chat, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(chat, TABLENAME2);
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
        return chat.id;
    }

    public int insert2(final Chat chat) {
        return insert2(chat, TABLENAME);
    }

    public int insert2(final Chat chat, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            chat.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid) VALUES (:id, :ccid, :type, :content, :create_time, :fromId, :fromName, :toId, :toName, :clancid)");
            Map map = super.insert(sql.toString(), chat);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Chat> chats) {
        return insert(chats, TABLENAME);
    }

    public int[] insert(final List<Chat> chats, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(chats == null || chats.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ccid, type, content, create_time, fromId, fromName, toId, toName, clancid) VALUES (:ccid, :type, :content, :create_time, :fromId, :fromName, :toId, :toName, :clancid)");
            return super.batchInsert(sql.toString(), chats);
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

    public int deleteInBeans(final List<Chat> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Chat> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Chat chat = beans.get(i);
                int id = chat.id;
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

    public List<Chat> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Chat> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Chat.class);
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
            sql.append("SELECT id, type, fromName, toName FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Chat> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Chat> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Chat.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Chat> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Chat> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Chat.class);
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

    public List<Chat> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Chat> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Chat.class);
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

    public Chat last() {
        return last(TABLENAME);
    }

    public Chat last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Chat.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Chat> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Chat> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Chat.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Chat> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Chat> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Chat.class);
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

    public Chat selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Chat selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Chat.class);
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

    public int countByTypeFromname(final Integer type, String fromName) {
        return  countByTypeFromname(type, fromName, TABLENAME);
    }

    public int countByTypeFromname(final Integer type, String fromName, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE type=:type AND fromName=:fromName ");
            Map params = newMap();
            params.put("type", type);
            params.put("fromName", fromName);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Chat> selectByTypeFromname(final Integer type, String fromName) {
        return selectByTypeFromname(type, fromName, TABLENAME);
    }

    public List<Chat> selectByTypeFromname(final Integer type, String fromName, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE type=:type AND fromName=:fromName ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("fromName", fromName);
            return super.queryForList(sql.toString(), params, Chat.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByTypeFromnamePKs(final Integer type, String fromName) {
        return selectByTypeFromnamePKs(type, fromName, TABLENAME);
    }

    public List<Integer> selectByTypeFromnamePKs(final Integer type, String fromName, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND fromName=:fromName ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("fromName", fromName);
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

    public List<Chat> selectPageByTypeFromname(final Integer type, String fromName, final int begin, final int num) {
        return selectPageByTypeFromname(type, fromName, begin, num, TABLENAME);
    }

    public List<Chat> selectPageByTypeFromname(final Integer type, String fromName, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE type=:type AND fromName=:fromName ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("fromName", fromName);
            return super.queryForList(sql.toString(), params, Chat.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByTypeFromnamePKs(final Integer type, String fromName, final int begin, final int num) {
        return selectPageByTypeFromnamePKs(type, fromName, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByTypeFromnamePKs(final Integer type, String fromName, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND fromName=:fromName ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("fromName", fromName);
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

    public Chat selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Chat selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Chat.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByTypeToname(final Integer type, String toName) {
        return  countByTypeToname(type, toName, TABLENAME);
    }

    public int countByTypeToname(final Integer type, String toName, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE type=:type AND toName=:toName ");
            Map params = newMap();
            params.put("type", type);
            params.put("toName", toName);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Chat> selectByTypeToname(final Integer type, String toName) {
        return selectByTypeToname(type, toName, TABLENAME);
    }

    public List<Chat> selectByTypeToname(final Integer type, String toName, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE type=:type AND toName=:toName ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("toName", toName);
            return super.queryForList(sql.toString(), params, Chat.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByTypeTonamePKs(final Integer type, String toName) {
        return selectByTypeTonamePKs(type, toName, TABLENAME);
    }

    public List<Integer> selectByTypeTonamePKs(final Integer type, String toName, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND toName=:toName ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            params.put("toName", toName);
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

    public List<Chat> selectPageByTypeToname(final Integer type, String toName, final int begin, final int num) {
        return selectPageByTypeToname(type, toName, begin, num, TABLENAME);
    }

    public List<Chat> selectPageByTypeToname(final Integer type, String toName, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE type=:type AND toName=:toName ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("toName", toName);
            return super.queryForList(sql.toString(), params, Chat.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByTypeTonamePKs(final Integer type, String toName, final int begin, final int num) {
        return selectPageByTypeTonamePKs(type, toName, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByTypeTonamePKs(final Integer type, String toName, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE type=:type AND toName=:toName ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            params.put("toName", toName);
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

    public List<Chat> selectByType(final Integer type) {
        return selectByType(type, TABLENAME);
    }

    public List<Chat> selectByType(final Integer type, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE type = :type ORDER BY id ");
            Map params = newMap();
            params.put("type", type);
            return super.queryForList(sql.toString(), params, Chat.class);
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

    public List<Chat> selectPageByType(final Integer type, final int begin, final int num) {
        return selectPageByType(type, begin, num, TABLENAME);
    }

    public List<Chat> selectPageByType(final Integer type, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" WHERE type = :type ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("type", type);
            return super.queryForList(sql.toString(), params, Chat.class);
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

    public List<Chat> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Chat> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ccid, type, content, create_time, fromId, fromName, toId, toName, clancid FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Chat.class);
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

    public int updateByKey(final Chat chat) {
        return updateByKey(chat, TABLENAME);
    }

    public int updateByKey(final Chat chat, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = chat.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), chat);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Chat chat) {
        asynchronousUpdate(chat, TABLENAME);
    }

    public void asynchronousUpdate(final Chat chat, final String TABLENAME2) {
        try {

            String _ustr = chat.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, chat);
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

    public int updateCcidByKey(final int ccid, final int id){
        return updateCcidByKey(ccid, id, TABLENAME);
    }

    public int updateCcidByKey(final int ccid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ccid=ccid+:ccid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("ccid", ccid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCcidWithMinByKey(final int id, final int ccid, final int _min){
        return updateCcidWithMinByKey(id, ccid, _min, TABLENAME);
    }

    public int updateCcidWithMinByKey(final int id, final int ccid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ccid = (select case when ccid+:ccid<=:_min then :_min else ccid+:ccid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("ccid", ccid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCcidWithMinInKeys(final List<Integer> keys, final int ccid, final int _min){
        return updateCcidWithMinInKeys(keys, ccid, _min, TABLENAME);
    }

    public int updateCcidWithMinInKeys(final List<Integer> keys, final int ccid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ccid = (select case when ccid+:ccid<=:_min then :_min else ccid+:ccid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("ccid", ccid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCcidWithMaxByKey(final int id, final int ccid, final int _max){
        return updateCcidWithMaxByKey(id, ccid, _max, TABLENAME);
    }

    public int updateCcidWithMaxByKey(final int id, final int ccid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ccid = (select case when ccid+:ccid>=:_max then :_max else ccid+:ccid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("ccid", ccid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCcidWithMaxInKeys(final List<Integer> keys, final int ccid, final int _max){
        return updateCcidWithMaxInKeys(keys, ccid, _max, TABLENAME);
    }

    public int updateCcidWithMaxInKeys(final List<Integer> keys, final int ccid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ccid = (select case when ccid+:ccid>=:_max then :_max else ccid+:ccid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("ccid", ccid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCcidWithMinMaxByKey(final int id, final int ccid, final int _min, final int _max){
        return updateCcidWithMinMaxByKey(id, ccid, _min, _max, TABLENAME);
    }

    public int updateCcidWithMinMaxByKey(final int id, final int ccid, final int _min, final int _max, final String TABLENAME2){
        if( ccid < 0 ) {
            return updateCcidWithMinByKey(id, ccid, _min, TABLENAME2);
        } else {
            return updateCcidWithMaxByKey(id, ccid, _max, TABLENAME2);
        }
    }

    public int updateCcidWithMinMaxInKeys(final List<Integer> keys, final int ccid, final int _min, final int _max){
        return updateCcidWithMinMaxInKeys(keys, ccid, _min, _max, TABLENAME);
    }

    public int updateCcidWithMinMaxInKeys(final List<Integer> keys, final int ccid, final int _min, final int _max, final String TABLENAME2){
        if( ccid < 0 ) {
            return updateCcidWithMinInKeys(keys, ccid, _min, TABLENAME2);
        } else {
            return updateCcidWithMaxInKeys(keys, ccid, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Chat> chats) {
        return updateByKey(chats, TABLENAME);
    }

    public int[] updateByKey (final List<Chat> chats, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(chats == null || chats.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ccid=:ccid, type=:type, content=:content, create_time=:create_time, fromId=:fromId, fromName=:fromName, toId=:toId, toName=:toName, clancid=:clancid WHERE id=:id");
            return super.batchUpdate2(sql.toString(), chats);
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
                "	`ccid`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`create_time`  BIGINT(20) NOT NULL," +
                "	`fromId`  INT(11) NOT NULL," +
                "	`fromName`  VARCHAR(32) NOT NULL," +
                "	`toId`  INT(11) NOT NULL," +
                "	`toName`  VARCHAR(32) NOT NULL," +
                "	`clancid`  VARCHAR(16) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `type` (`type`)," +
                "	KEY `type_2` (`type`, `toName`)," +
                "	KEY `type_3` (`type`, `fromName`)" +
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
                "	`ccid`  INT(11) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`create_time`  BIGINT(20) NOT NULL," +
                "	`fromId`  INT(11) NOT NULL," +
                "	`fromName`  VARCHAR(32) NOT NULL," +
                "	`toId`  INT(11) NOT NULL," +
                "	`toName`  VARCHAR(32) NOT NULL," +
                "	`clancid`  VARCHAR(16) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `type` (`type`)," +
                "	KEY `type_2` (`type`, `toName`)," +
                "	KEY `type_3` (`type`, `fromName`)" +
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
