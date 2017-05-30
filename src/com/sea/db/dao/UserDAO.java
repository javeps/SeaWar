package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - user
@SuppressWarnings({"rawtypes", "unchecked"})
public class UserDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(UserDAO.class);

    public static final String TABLE = "user";
    public static String TABLENAME = "user";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "ucid", "login_pwd", "name", "login_uid", "uuid", "login_time", "model", "version", "remain", "pcids"};
    public static String coulmns = "id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids";
    public static String coulmns2 = "ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids";

    public UserDAO(java.sql.Connection conn) {
        super(conn);
    }

    public UserDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public UserDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final User user) {
        return insert(user, TABLENAME);
    }

    public int insert(final User user, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            user.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids) VALUES (:ucid, :login_pwd, :name, :login_uid, :uuid, :login_time, :model, :version, :remain, :pcids)");
            Map map = super.insert(sql.toString(), user);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final User user) {
        asynchronousInsert(user, TABLENAME);
    }

    public void asynchronousInsert(final User user, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(user, TABLENAME2);
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

    public int asynchronousInsert2(final User user) {
        return asynchronousInsert2(user, TABLENAME);
    }

    public int asynchronousInsert2(final User user, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(user, TABLENAME2);
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
        return user.id;
    }

    public int insert2(final User user) {
        return insert2(user, TABLENAME);
    }

    public int insert2(final User user, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            user.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids) VALUES (:id, :ucid, :login_pwd, :name, :login_uid, :uuid, :login_time, :model, :version, :remain, :pcids)");
            Map map = super.insert(sql.toString(), user);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<User> users) {
        return insert(users, TABLENAME);
    }

    public int[] insert(final List<User> users, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(users == null || users.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids) VALUES (:ucid, :login_pwd, :name, :login_uid, :uuid, :login_time, :model, :version, :remain, :pcids)");
            return super.batchInsert(sql.toString(), users);
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

    public int deleteInBeans(final List<User> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<User> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                User user = beans.get(i);
                int id = user.id;
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

    public List<User> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<User> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), User.class);
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
            sql.append("SELECT id, ucid, login_pwd, login_uid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<User> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<User> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), User.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<User> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<User> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, User.class);
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

    public List<User> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<User> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), User.class);
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

    public User last() {
        return last(TABLENAME);
    }

    public User last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), User.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<User> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<User> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, User.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<User> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<User> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, User.class);
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

    public User selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public User selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, User.class);
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

    public User selectByUcid(final Integer ucid) {
        return selectByUcid(ucid, TABLENAME);
    }

    public User selectByUcid(final Integer ucid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" WHERE ucid = :ucid");
            Map params = newMap();
            params.put("ucid", ucid);
            return super.queryForObject(sql.toString(), params, User.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public User selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public User selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, User.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public User selectByLogin_uid(final String login_uid) {
        return selectByLogin_uid(login_uid, TABLENAME);
    }

    public User selectByLogin_uid(final String login_uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" WHERE login_uid = :login_uid");
            Map params = newMap();
            params.put("login_uid", login_uid);
            return super.queryForObject(sql.toString(), params, User.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeLogin_uid(final String login_uid) {
        return countLikeLogin_uid(login_uid, TABLENAME);
    }

    public int countLikeLogin_uid(final String login_uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE login_uid LIKE '%").append(login_uid).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<User> selectLikeLogin_uid(final String login_uid) {
        return selectLikeLogin_uid(login_uid, TABLENAME);
    }

    public List<User> selectLikeLogin_uid(final String login_uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" WHERE login_uid LIKE '%").append(login_uid).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), User.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeLogin_uidPKs(final String login_uid) {
        return selectLikeLogin_uidPKs(login_uid, TABLENAME);
    }

    public List<Integer> selectLikeLogin_uidPKs(final String login_uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE login_uid LIKE '%").append(login_uid).append("%' ORDER BY id ");
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

    public User selectByLogin_pwdLogin_uid(final String login_pwd, String login_uid) {
        return selectByLogin_pwdLogin_uid(login_pwd, login_uid, TABLENAME);
    }

    public User selectByLogin_pwdLogin_uid(final String login_pwd, String login_uid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" WHERE login_pwd=:login_pwd AND login_uid=:login_uid");
            Map params = newMap();
            params.put("login_pwd", login_pwd);
            params.put("login_uid", login_uid);
            return super.queryForObject(sql.toString(), params, User.class);
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

    public List<User> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<User> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, ucid, login_pwd, name, login_uid, uuid, login_time, model, version, remain, pcids FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), User.class);
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

    public int updateByKey(final User user) {
        return updateByKey(user, TABLENAME);
    }

    public int updateByKey(final User user, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = user.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), user);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final User user) {
        asynchronousUpdate(user, TABLENAME);
    }

    public void asynchronousUpdate(final User user, final String TABLENAME2) {
        try {

            String _ustr = user.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, user);
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

    public int updateLogin_timeByKey(final long login_time, final int id){
        return updateLogin_timeByKey(login_time, id, TABLENAME);
    }

    public int updateLogin_timeByKey(final long login_time, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET login_time=login_time+:login_time WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("login_time", login_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLogin_timeWithMinByKey(final int id, final long login_time, final long _min){
        return updateLogin_timeWithMinByKey(id, login_time, _min, TABLENAME);
    }

    public int updateLogin_timeWithMinByKey(final int id, final long login_time, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET login_time = (select case when login_time+:login_time<=:_min then :_min else login_time+:login_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("login_time", login_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLogin_timeWithMinInKeys(final List<Integer> keys, final long login_time, final long _min){
        return updateLogin_timeWithMinInKeys(keys, login_time, _min, TABLENAME);
    }

    public int updateLogin_timeWithMinInKeys(final List<Integer> keys, final long login_time, final long _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET login_time = (select case when login_time+:login_time<=:_min then :_min else login_time+:login_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("login_time", login_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLogin_timeWithMaxByKey(final int id, final long login_time, final long _max){
        return updateLogin_timeWithMaxByKey(id, login_time, _max, TABLENAME);
    }

    public int updateLogin_timeWithMaxByKey(final int id, final long login_time, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET login_time = (select case when login_time+:login_time>=:_max then :_max else login_time+:login_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("login_time", login_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLogin_timeWithMaxInKeys(final List<Integer> keys, final long login_time, final long _max){
        return updateLogin_timeWithMaxInKeys(keys, login_time, _max, TABLENAME);
    }

    public int updateLogin_timeWithMaxInKeys(final List<Integer> keys, final long login_time, final long _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET login_time = (select case when login_time+:login_time>=:_max then :_max else login_time+:login_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("login_time", login_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLogin_timeWithMinMaxByKey(final int id, final long login_time, final long _min, final long _max){
        return updateLogin_timeWithMinMaxByKey(id, login_time, _min, _max, TABLENAME);
    }

    public int updateLogin_timeWithMinMaxByKey(final int id, final long login_time, final long _min, final long _max, final String TABLENAME2){
        if( login_time < 0 ) {
            return updateLogin_timeWithMinByKey(id, login_time, _min, TABLENAME2);
        } else {
            return updateLogin_timeWithMaxByKey(id, login_time, _max, TABLENAME2);
        }
    }

    public int updateLogin_timeWithMinMaxInKeys(final List<Integer> keys, final long login_time, final long _min, final long _max){
        return updateLogin_timeWithMinMaxInKeys(keys, login_time, _min, _max, TABLENAME);
    }

    public int updateLogin_timeWithMinMaxInKeys(final List<Integer> keys, final long login_time, final long _min, final long _max, final String TABLENAME2){
        if( login_time < 0 ) {
            return updateLogin_timeWithMinInKeys(keys, login_time, _min, TABLENAME2);
        } else {
            return updateLogin_timeWithMaxInKeys(keys, login_time, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<User> users) {
        return updateByKey(users, TABLENAME);
    }

    public int[] updateByKey (final List<User> users, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(users == null || users.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid=:ucid, login_pwd=:login_pwd, name=:name, login_uid=:login_uid, uuid=:uuid, login_time=:login_time, model=:model, version=:version, remain=:remain, pcids=:pcids WHERE id=:id");
            return super.batchUpdate2(sql.toString(), users);
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
                "	`login_pwd`  VARCHAR(64) NOT NULL," +
                "	`name`  VARCHAR(32) NOT NULL," +
                "	`login_uid`  VARCHAR(64) NOT NULL," +
                "	`uuid`  VARCHAR(64) NOT NULL," +
                "	`login_time`  BIGINT(20) NOT NULL," +
                "	`model`  VARCHAR(128) NOT NULL," +
                "	`version`  VARCHAR(32) NOT NULL," +
                "	`remain`  VARCHAR(32) NOT NULL," +
                "	`pcids`  TEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `login_uid` (`login_uid`)," +
                "	UNIQUE KEY `login_pwd` (`login_pwd`, `login_uid`)," +
                "	UNIQUE KEY `ucid` (`ucid`)" +
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
                "	`login_pwd`  VARCHAR(64) NOT NULL," +
                "	`name`  VARCHAR(32) NOT NULL," +
                "	`login_uid`  VARCHAR(64) NOT NULL," +
                "	`uuid`  VARCHAR(64) NOT NULL," +
                "	`login_time`  BIGINT(20) NOT NULL," +
                "	`model`  VARCHAR(128) NOT NULL," +
                "	`version`  VARCHAR(32) NOT NULL," +
                "	`remain`  VARCHAR(32) NOT NULL," +
                "	`pcids`  TEXT NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `login_uid` (`login_uid`)," +
                "	KEY `login_pwd` (`login_pwd`, `login_uid`)," +
                "	KEY `ucid` (`ucid`)" +
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
