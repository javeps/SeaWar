package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - user
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class UserInternal extends InternalSupport{
    static Log log = LogFactory.getLog(UserDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public UserInternal(){}

    public static UserDAO DAO(){
        return UserEntity.UserDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new User[MAX];
    }
    private static User[] FIXED = new User[MAX];
    public static final Map<Integer, User> vars = newSortedMap();
    public static final Map<Integer, Integer> varsByUcid = newSortedMap();
    public static final Map<String, Integer> varsByLogin_uid = newSortedMap();
    public static final Map<String, Integer> varsByLogin_pwdLogin_uid = newSortedMap();

    private static final List<User> fixedCache = newList();

    public static void put(User user){
        if(user == null || user.id <= 0) return ;

        int id = user.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(user))
                	FIXED[id - 1] = user;
                if (!fixedCache.contains(user))
                	fixedCache.add(user);
            }
        } else {
            vars.put(id, user);
        }

        int ucid = user.getUcid();
        varsByUcid.put(ucid, id);

        String login_uid = user.getLogin_uid();
        varsByLogin_uid.put(login_uid, id);

        { // login_pwd
            String vlogin_pwd = user.getLogin_pwd();
            String vlogin_uid = user.getLogin_uid();
            String vkey = vlogin_pwd + "-" + vlogin_uid;
            varsByLogin_pwdLogin_uid.put(vkey, id);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByUcid.clear();
        varsByLogin_uid.clear();
        varsByLogin_pwdLogin_uid.clear();
        FIXED = new User[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(UserDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(UserDAO DAO, String TABLENAME2){
        if( cacheModel == NO_CACHE ){
            return DAO.count(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            return fixedCache.size();
        } else {  // FULL_CACHE || FULL_MEMORY
            return vars.size();
        }
    }

    public static void relocate(String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static void relocate(UserDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        UserDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(UserDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        UserDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(UserDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        UserDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(UserDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        UserDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(UserDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(UserDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        UserDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(UserDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(UserDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        UserDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(UserDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new User[maxId(DAO)];

        List<User> users = DAO.selectAll();
        for (User user : users) {
            user.reset();
            put(user);
        }
    }

    public static Map toMap(User user){
        return user.toMap();
    }

    public static List<Map> toMap(List<User> users){
        List<Map> ret = new Vector<Map>();
        for (User user : users){
            Map e = user.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<User> sortZh(List<User> users, final String key) {
        Collections.sort(users, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return users;
    }

    public static List<User> sort(List<User> users, final String key) {
        Collections.sort(users, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o1.compareTo(o2, key);
            }
        });
        return users;
    }

    public static List<User> sort(List<User> users){
        Collections.sort(users, new Comparator<User>(){
            public int compare(User o1, User o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return users;
    }

    public static List<User> sortReverse(List<User> users){
        Collections.sort(users, new Comparator<User>(){
            public int compare(User o1, User o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return users;
    }

    public static List<User> sortUcid(List<User> users){
        Collections.sort(users, new Comparator<User>(){
            public int compare(User o1, User o2) {
                Object v1 = o1.getUcid();
                Object v2 = o2.getUcid();
                return compareTo(v1, v2);
            }
        });
        return users;
    }

    public static List<User> sortUcidRo(List<User> users){
        Collections.sort(users, new Comparator<User>(){
            public int compare(User o1, User o2) {
                Object v1 = o1.getUcid();
                Object v2 = o2.getUcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return users;
    }

    public static List<User> sortLogin_uid(List<User> users){
        Collections.sort(users, new Comparator<User>(){
            public int compare(User o1, User o2) {
                Object v1 = o1.getLogin_uid();
                Object v2 = o2.getLogin_uid();
                return compareTo(v1, v2);
            }
        });
        return users;
    }

    public static List<User> sortLogin_uidRo(List<User> users){
        Collections.sort(users, new Comparator<User>(){
            public int compare(User o1, User o2) {
                Object v1 = o1.getLogin_uid();
                Object v2 = o2.getLogin_uid();
                return 0 - compareTo(v1, v2);
            };
        });
        return users;
    }

    public static User insert(User user) {
        UserDAO DAO = DAO();
        return insert(DAO, user, DAO.TABLENAME);
    }

    public static User insert(UserDAO DAO, User user) {
        return insert(DAO, user, DAO.TABLENAME);
    }

    public static User insert(User user, String TABLENAME2) {
        UserDAO DAO = DAO();
        return insert(DAO, user, TABLENAME2);
    }

    public static User insert(UserDAO DAO, User user, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(user, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        user.id = n;
        if(cacheModel != NO_CACHE) put(user);

        return user;
    }

    public static User asynchronousInsert(User user) {
        UserDAO DAO = DAO();
        return asynchronousInsert(DAO, user, DAO.TABLENAME);
    }
    public static User asynchronousInsert(UserDAO DAO, User user) {
        return asynchronousInsert(DAO, user, DAO.TABLENAME);
    }
    public static User asynchronousInsert(User user, String TABLENAME2) {
        UserDAO DAO = DAO();
        return asynchronousInsert(DAO, user, TABLENAME2);
    }
    public static User asynchronousInsert(UserDAO DAO, User user, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(user, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        user.id = n;
        if(cacheModel != NO_CACHE) put(user);
        return user;
    }
    public static User insert2(User user) {
        UserDAO DAO = DAO();
        return insert2(DAO, user, DAO.TABLENAME);
    }

    public static User insert2(UserDAO DAO, User user) {
        return insert2(DAO, user, DAO.TABLENAME);
    }

    public static User insert2(User user, String TABLENAME2) {
        UserDAO DAO = DAO();
        return insert2(DAO, user, TABLENAME2);
    }

    public static User insert2(UserDAO DAO, User user, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(user, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        user.id = n;
        if(cacheModel != NO_CACHE) put(user);

        return user;
    }

    public static User asynchronousInsert2(User user) {
        UserDAO DAO = DAO();
        return asynchronousInsert2(DAO, user, DAO.TABLENAME);
    }
    public static User asynchronousInsert2(UserDAO DAO, User user) {
        return asynchronousInsert2(DAO, user, DAO.TABLENAME);
    }
    public static User asynchronousInsert2(User user, String TABLENAME2) {
        UserDAO DAO = DAO();
        return asynchronousInsert2(DAO, user, TABLENAME2);
    }
    public static User asynchronousInsert2(UserDAO DAO, User user, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        user.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(user, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(user);
        return user;
    }
    public static int[] insert(List<User> users) {
        UserDAO DAO = DAO();
        return insert(DAO, users, DAO.TABLENAME);
    }

    public static int[] insert(UserDAO DAO, List<User> users) {
        return insert(DAO, users, DAO.TABLENAME);
    }

    public static int[] insert(List<User> users, String TABLENAME2) {
        UserDAO DAO = DAO();
        return insert(DAO, users, TABLENAME2);
    }

    public static int[] insert(UserDAO DAO, List<User> users, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(users, TABLENAME2);
            int n = 0;
            for(User user : users){
                user.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[users.size()];
        int n = 0;
        for(User user : users){
            user = insert(DAO, user, TABLENAME2);
            ret[n++] = (user == null) ? 0 : (int)user.id;
        }
        return ret;
    }

    public static int delete(User user) {
        int id = user.id;
        UserDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        UserDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(UserDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        UserDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(UserDAO DAO, int id, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(id, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
        return n;
    }

    public static void asynchronousDelete(User user) {
        int id = user.id;
        UserDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        UserDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(UserDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        UserDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(UserDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        UserDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(UserDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        UserDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(UserDAO DAO, int[] ids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(ids, TABLENAME2);
        }
        int[] ret = new int[ids.length];
        int n = 0;
        for(int id : ids){
            ret[n++] = delete(DAO, id, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        UserDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, UserDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        UserDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final UserDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<User> beans) {
        UserDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<User> beans, UserDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<User> beans, String TABLENAME2) {
        UserDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<User> beans, final UserDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(User user : beans){
                int id = user.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<User> getAll() {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<User> getAll(UserDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<User> getAll(String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<User> getAll(final UserDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<User> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<User> getNoSortAll() {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<User> getNoSortAll(UserDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<User> getNoSortAll(String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<User> getNoSortAll(final UserDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<User> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<User> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                User user = FIXED[i];
                if (user != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<User> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<User> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(UserDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final UserDAO DAO, final String TABLENAME2) {
        if ( cacheModel == NO_CACHE) { 
            return DAO.selectPKs(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Integer> result = newList();
            result.addAll(memoryKeys());
            return result;
        } else {
            List<Integer> result = newList();
            result.addAll(vars.keySet());
            return result;
        }
    }

    public static List<Map> getInIndex() {
        UserDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(UserDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        UserDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final UserDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<User> getIn(List<Integer> keys) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<User> getIn(List<Integer> keys, UserDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<User> getIn(List<Integer> keys, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<User> getIn(final List<Integer> keys, final UserDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<User> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<User> getNoSortIn(List<Integer> keys) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<User> getNoSortIn(List<Integer> keys, UserDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<User> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<User> getNoSortIn(final List<Integer> keys, final UserDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<User> result = newList();
            for (int key : keys) {
                User user = getByKeyFromMemory(key);
                if( user == null ) continue;
                result.add(user);
            }
            return result;
        }
    }

    public static List<User> getLast(int num) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<User> getLast(UserDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<User> getLast(int num, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<User> getLast(final UserDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<User> result = newList();
            List<User> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static User last() {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static User last(UserDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static User last(String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static User last(final UserDAO DAO, final String TABLENAME2) {
        User result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        UserDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(UserDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        UserDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final UserDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<User> getGtKey(int id) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<User> getGtKey(UserDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<User> getGtKey(int id, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<User> getGtKey(final UserDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<User> result = newList();
            List<User> users = getAll();
            for (User user : users) {
                if(user.id <= id) continue;
                result.add(user);
            }
            return result;
        }
    }

    public static User getByKey(int id) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static User getByKey(UserDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static User getByKey(int id, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static User getByKey(final UserDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static User getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static User deleteFromMemory(final int id) {
        User user;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            user = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(user);
        } else {
            user = vars.remove(id);
        }
        if(user == null) return null;

        int ucid = user.getUcid();
        varsByUcid.remove(ucid);

        String login_uid = user.getLogin_uid();
        varsByLogin_uid.remove(login_uid);

        { // login_pwd
            String vlogin_pwd = user.getLogin_pwd();
            String vlogin_uid = user.getLogin_uid();
            String vkey = vlogin_pwd + "-" + vlogin_uid;
            varsByLogin_pwdLogin_uid.remove(vkey);
        }

        return user;
    }

    public static List<User> getByPage(int page, int size) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<User> getByPage(UserDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<User> getByPage(int page, int size, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<User> getByPage(final UserDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<User> result = newList();
            List<User> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(UserDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final UserDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static User getByUcid(Integer ucid) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUcid(DAO, ucid, DAO.TABLENAME);
    }

    public static User getByUcid(UserDAO DAO, Integer ucid) {
        return getByUcid(DAO, ucid, DAO.TABLENAME);
    }

    public static User getByUcid(Integer ucid, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUcid(DAO, ucid, TABLENAME2);
    }

    public static User getByUcid(final UserDAO DAO, final int ucid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUcid(ucid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByUcid.get(ucid);
            if(id == null) return null;
            User result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getUcid() != ucid){
                varsByUcid.remove(ucid);
                return null;
            }
            return result;
        }
    }

    public static User getByLogin_uid(String login_uid) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLogin_uid(DAO, login_uid, DAO.TABLENAME);
    }

    public static User getByLogin_uid(UserDAO DAO, String login_uid) {
        return getByLogin_uid(DAO, login_uid, DAO.TABLENAME);
    }

    public static User getByLogin_uid(String login_uid, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLogin_uid(DAO, login_uid, TABLENAME2);
    }

    public static User getByLogin_uid(final UserDAO DAO, final String login_uid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLogin_uid(login_uid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByLogin_uid.get(login_uid);
            if(id == null) return null;
            User result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getLogin_uid().equals(login_uid)){
                varsByLogin_uid.remove(login_uid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeLogin_uid(String login_uid) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeLogin_uid(DAO, login_uid, DAO.TABLENAME);
    }

    public static int countLikeLogin_uid(UserDAO DAO, String login_uid) {
        return countLikeLogin_uid(DAO, login_uid, DAO.TABLENAME);
    }

    public static int countLikeLogin_uid(String login_uid, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeLogin_uid(DAO, login_uid, TABLENAME2);
    }

    public static int countLikeLogin_uid(final UserDAO DAO, final String login_uid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeLogin_uid(login_uid, TABLENAME2);
        }
        List<User> users = getLikeLogin_uid(DAO, login_uid, TABLENAME2);
        return users.size();
    }

    public static List<User> getLikeLogin_uid(String login_uid) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeLogin_uid(DAO, login_uid, DAO.TABLENAME);
    }

    public static List<User> getLikeLogin_uid(UserDAO DAO, String login_uid) {
        return getLikeLogin_uid(DAO, login_uid, DAO.TABLENAME);
    }

    public static List<User> getLikeLogin_uid(String login_uid, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeLogin_uid(DAO, login_uid, TABLENAME2);
    }

    public static List<User> getLikeLogin_uid(final UserDAO DAO, final String login_uid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeLogin_uid(login_uid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<User> result = newList();
            List<User> users = getAll(DAO, TABLENAME2);
            for(User e : users){
                String _var = e.getLogin_uid();
                if(_var.indexOf(login_uid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static User getByLogin_pwdLogin_uid(String login_pwd, String login_uid) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLogin_pwdLogin_uid(DAO, login_pwd, login_uid, DAO.TABLENAME);
    }

    public static User getByLogin_pwdLogin_uid(UserDAO DAO, String login_pwd, String login_uid) {
        return getByLogin_pwdLogin_uid(DAO, login_pwd, login_uid, DAO.TABLENAME);
    }

    public static User getByLogin_pwdLogin_uid(String login_pwd, String login_uid, String TABLENAME2) {
        UserDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLogin_pwdLogin_uid(DAO, login_pwd, login_uid, TABLENAME2);
    }

    public static User getByLogin_pwdLogin_uid(final UserDAO DAO, String login_pwd, String login_uid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLogin_pwdLogin_uid(login_pwd, login_uid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = login_pwd+"-"+login_uid;
            Integer id = varsByLogin_pwdLogin_uid.get(vkey);
            if(id == null) return null;
            User result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getLogin_pwd().equals(login_pwd)){
                varsByLogin_pwdLogin_uid.remove(vkey);
                return null;
            }
            if(!result.getLogin_uid().equals(login_uid)){
                varsByLogin_pwdLogin_uid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static User update(User user) {
        UserDAO DAO = DAO();
        return update(DAO, user, DAO.TABLENAME);
    }

    public static User update(UserDAO DAO, User user) {
        return update(DAO, user, DAO.TABLENAME);
    }

    public static User update(User user, String TABLENAME2) {
        UserDAO DAO = DAO();
        return update(DAO, user, TABLENAME2);
    }

    public static User update(final UserDAO DAO, final User user,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(user, TABLENAME2);
            if(n == -1) 
                return user;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(user);
        }
        return user;
    }

    public static User asynchronousUpdate(User user) {
        UserDAO DAO = DAO();
        return asynchronousUpdate(DAO, user, DAO.TABLENAME);
    }

    public static User asynchronousUpdate(UserDAO DAO, User user) {
        return asynchronousUpdate(DAO, user, DAO.TABLENAME);
    }

    public static User asynchronousUpdate(User user, String TABLENAME2) {
        UserDAO DAO = DAO();
        return asynchronousUpdate(DAO, user, TABLENAME2);
    }

    public static User asynchronousUpdate(final UserDAO DAO, final User user,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(user, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(user);
        }
        return user;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        UserDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(UserDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final UserDAO DAO, final String TABLENAME2) {
        int result = 0;
        if(cacheModel != FULL_MEMORY)
            return result;
        try {
            DAO.truncate(TABLENAME2);
            DAO.repair(TABLENAME2);
            DAO.optimize(TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }

        List<User> users = getAll();
        for (User user : users) {
            int n = DAO.insert2(user, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
