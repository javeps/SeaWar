package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - admin
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class AdminInternal extends InternalSupport{
    static Log log = LogFactory.getLog(AdminDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public AdminInternal(){}

    public static AdminDAO DAO(){
        return AdminEntity.AdminDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Admin[MAX];
    }
    private static Admin[] FIXED = new Admin[MAX];
    public static final Map<Integer, Admin> vars = newSortedMap();
    public static final Map<String, Integer> varsByLgid = newSortedMap();

    private static final List<Admin> fixedCache = newList();

    public static void put(Admin admin){
        if(admin == null || admin.id <= 0) return ;

        int id = admin.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(admin))
                	FIXED[id - 1] = admin;
                if (!fixedCache.contains(admin))
                	fixedCache.add(admin);
            }
        } else {
            vars.put(id, admin);
        }

        String lgID = admin.getLgid();
        varsByLgid.put(lgID, id);

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByLgid.clear();
        FIXED = new Admin[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(AdminDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(AdminDAO DAO, String TABLENAME2){
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

    public static void relocate(AdminDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        AdminDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(AdminDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        AdminDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(AdminDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        AdminDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(AdminDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        AdminDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(AdminDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(AdminDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        AdminDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(AdminDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(AdminDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        AdminDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(AdminDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Admin[maxId(DAO)];

        List<Admin> admins = DAO.selectAll();
        for (Admin admin : admins) {
            admin.reset();
            put(admin);
        }
    }

    public static Map toMap(Admin admin){
        return admin.toMap();
    }

    public static List<Map> toMap(List<Admin> admins){
        List<Map> ret = new Vector<Map>();
        for (Admin admin : admins){
            Map e = admin.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Admin> sortZh(List<Admin> admins, final String key) {
        Collections.sort(admins, new Comparator<Admin>() {
            public int compare(Admin o1, Admin o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return admins;
    }

    public static List<Admin> sort(List<Admin> admins, final String key) {
        Collections.sort(admins, new Comparator<Admin>() {
            public int compare(Admin o1, Admin o2) {
                return o1.compareTo(o2, key);
            }
        });
        return admins;
    }

    public static List<Admin> sort(List<Admin> admins){
        Collections.sort(admins, new Comparator<Admin>(){
            public int compare(Admin o1, Admin o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return admins;
    }

    public static List<Admin> sortReverse(List<Admin> admins){
        Collections.sort(admins, new Comparator<Admin>(){
            public int compare(Admin o1, Admin o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return admins;
    }

    public static List<Admin> sortLgid(List<Admin> admins){
        Collections.sort(admins, new Comparator<Admin>(){
            public int compare(Admin o1, Admin o2) {
                Object v1 = o1.getLgid();
                Object v2 = o2.getLgid();
                return compareTo(v1, v2);
            }
        });
        return admins;
    }

    public static List<Admin> sortLgidRo(List<Admin> admins){
        Collections.sort(admins, new Comparator<Admin>(){
            public int compare(Admin o1, Admin o2) {
                Object v1 = o1.getLgid();
                Object v2 = o2.getLgid();
                return 0 - compareTo(v1, v2);
            };
        });
        return admins;
    }

    public static Admin insert(Admin admin) {
        AdminDAO DAO = DAO();
        return insert(DAO, admin, DAO.TABLENAME);
    }

    public static Admin insert(AdminDAO DAO, Admin admin) {
        return insert(DAO, admin, DAO.TABLENAME);
    }

    public static Admin insert(Admin admin, String TABLENAME2) {
        AdminDAO DAO = DAO();
        return insert(DAO, admin, TABLENAME2);
    }

    public static Admin insert(AdminDAO DAO, Admin admin, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(admin, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        admin.id = n;
        if(cacheModel != NO_CACHE) put(admin);

        return admin;
    }

    public static Admin asynchronousInsert(Admin admin) {
        AdminDAO DAO = DAO();
        return asynchronousInsert(DAO, admin, DAO.TABLENAME);
    }
    public static Admin asynchronousInsert(AdminDAO DAO, Admin admin) {
        return asynchronousInsert(DAO, admin, DAO.TABLENAME);
    }
    public static Admin asynchronousInsert(Admin admin, String TABLENAME2) {
        AdminDAO DAO = DAO();
        return asynchronousInsert(DAO, admin, TABLENAME2);
    }
    public static Admin asynchronousInsert(AdminDAO DAO, Admin admin, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(admin, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        admin.id = n;
        if(cacheModel != NO_CACHE) put(admin);
        return admin;
    }
    public static Admin insert2(Admin admin) {
        AdminDAO DAO = DAO();
        return insert2(DAO, admin, DAO.TABLENAME);
    }

    public static Admin insert2(AdminDAO DAO, Admin admin) {
        return insert2(DAO, admin, DAO.TABLENAME);
    }

    public static Admin insert2(Admin admin, String TABLENAME2) {
        AdminDAO DAO = DAO();
        return insert2(DAO, admin, TABLENAME2);
    }

    public static Admin insert2(AdminDAO DAO, Admin admin, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(admin, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        admin.id = n;
        if(cacheModel != NO_CACHE) put(admin);

        return admin;
    }

    public static Admin asynchronousInsert2(Admin admin) {
        AdminDAO DAO = DAO();
        return asynchronousInsert2(DAO, admin, DAO.TABLENAME);
    }
    public static Admin asynchronousInsert2(AdminDAO DAO, Admin admin) {
        return asynchronousInsert2(DAO, admin, DAO.TABLENAME);
    }
    public static Admin asynchronousInsert2(Admin admin, String TABLENAME2) {
        AdminDAO DAO = DAO();
        return asynchronousInsert2(DAO, admin, TABLENAME2);
    }
    public static Admin asynchronousInsert2(AdminDAO DAO, Admin admin, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        admin.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(admin, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(admin);
        return admin;
    }
    public static int[] insert(List<Admin> admins) {
        AdminDAO DAO = DAO();
        return insert(DAO, admins, DAO.TABLENAME);
    }

    public static int[] insert(AdminDAO DAO, List<Admin> admins) {
        return insert(DAO, admins, DAO.TABLENAME);
    }

    public static int[] insert(List<Admin> admins, String TABLENAME2) {
        AdminDAO DAO = DAO();
        return insert(DAO, admins, TABLENAME2);
    }

    public static int[] insert(AdminDAO DAO, List<Admin> admins, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(admins, TABLENAME2);
            int n = 0;
            for(Admin admin : admins){
                admin.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[admins.size()];
        int n = 0;
        for(Admin admin : admins){
            admin = insert(DAO, admin, TABLENAME2);
            ret[n++] = (admin == null) ? 0 : (int)admin.id;
        }
        return ret;
    }

    public static int delete(Admin admin) {
        int id = admin.id;
        AdminDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        AdminDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(AdminDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        AdminDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(AdminDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Admin admin) {
        int id = admin.id;
        AdminDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        AdminDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(AdminDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        AdminDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(AdminDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        AdminDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(AdminDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        AdminDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(AdminDAO DAO, int[] ids,String TABLENAME2) {
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
        AdminDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, AdminDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        AdminDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final AdminDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Admin> beans) {
        AdminDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Admin> beans, AdminDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Admin> beans, String TABLENAME2) {
        AdminDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Admin> beans, final AdminDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Admin admin : beans){
                int id = admin.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Admin> getAll() {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Admin> getAll(AdminDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Admin> getAll(String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Admin> getAll(final AdminDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Admin> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Admin> getNoSortAll() {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Admin> getNoSortAll(AdminDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Admin> getNoSortAll(String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Admin> getNoSortAll(final AdminDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Admin> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Admin> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Admin admin = FIXED[i];
                if (admin != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Admin> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Admin> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(AdminDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final AdminDAO DAO, final String TABLENAME2) {
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
        AdminDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(AdminDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        AdminDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final AdminDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Admin> getIn(List<Integer> keys) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Admin> getIn(List<Integer> keys, AdminDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Admin> getIn(List<Integer> keys, String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Admin> getIn(final List<Integer> keys, final AdminDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Admin> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Admin> getNoSortIn(List<Integer> keys) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Admin> getNoSortIn(List<Integer> keys, AdminDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Admin> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Admin> getNoSortIn(final List<Integer> keys, final AdminDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Admin> result = newList();
            for (int key : keys) {
                Admin admin = getByKeyFromMemory(key);
                if( admin == null ) continue;
                result.add(admin);
            }
            return result;
        }
    }

    public static List<Admin> getLast(int num) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Admin> getLast(AdminDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Admin> getLast(int num, String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Admin> getLast(final AdminDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Admin> result = newList();
            List<Admin> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Admin last() {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Admin last(AdminDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Admin last(String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Admin last(final AdminDAO DAO, final String TABLENAME2) {
        Admin result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        AdminDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(AdminDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        AdminDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final AdminDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Admin> getGtKey(int id) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Admin> getGtKey(AdminDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Admin> getGtKey(int id, String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Admin> getGtKey(final AdminDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Admin> result = newList();
            List<Admin> admins = getAll();
            for (Admin admin : admins) {
                if(admin.id <= id) continue;
                result.add(admin);
            }
            return result;
        }
    }

    public static Admin getByKey(int id) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Admin getByKey(AdminDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Admin getByKey(int id, String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Admin getByKey(final AdminDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Admin getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Admin deleteFromMemory(final int id) {
        Admin admin;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            admin = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(admin);
        } else {
            admin = vars.remove(id);
        }
        if(admin == null) return null;

        String lgID = admin.getLgid();
        varsByLgid.remove(lgID);

        return admin;
    }

    public static List<Admin> getByPage(int page, int size) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Admin> getByPage(AdminDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Admin> getByPage(int page, int size, String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Admin> getByPage(final AdminDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Admin> result = newList();
            List<Admin> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(AdminDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final AdminDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Admin getByLgid(String lgID) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLgid(DAO, lgID, DAO.TABLENAME);
    }

    public static Admin getByLgid(AdminDAO DAO, String lgID) {
        return getByLgid(DAO, lgID, DAO.TABLENAME);
    }

    public static Admin getByLgid(String lgID, String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByLgid(DAO, lgID, TABLENAME2);
    }

    public static Admin getByLgid(final AdminDAO DAO, final String lgID,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByLgid(lgID, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByLgid.get(lgID);
            if(id == null) return null;
            Admin result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getLgid().equals(lgID)){
                varsByLgid.remove(lgID);
                return null;
            }
            return result;
        }
    }

    public static int countLikeLgid(String lgID) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeLgid(DAO, lgID, DAO.TABLENAME);
    }

    public static int countLikeLgid(AdminDAO DAO, String lgID) {
        return countLikeLgid(DAO, lgID, DAO.TABLENAME);
    }

    public static int countLikeLgid(String lgID, String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeLgid(DAO, lgID, TABLENAME2);
    }

    public static int countLikeLgid(final AdminDAO DAO, final String lgID,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeLgid(lgID, TABLENAME2);
        }
        List<Admin> admins = getLikeLgid(DAO, lgID, TABLENAME2);
        return admins.size();
    }

    public static List<Admin> getLikeLgid(String lgID) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeLgid(DAO, lgID, DAO.TABLENAME);
    }

    public static List<Admin> getLikeLgid(AdminDAO DAO, String lgID) {
        return getLikeLgid(DAO, lgID, DAO.TABLENAME);
    }

    public static List<Admin> getLikeLgid(String lgID, String TABLENAME2) {
        AdminDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeLgid(DAO, lgID, TABLENAME2);
    }

    public static List<Admin> getLikeLgid(final AdminDAO DAO, final String lgID,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeLgid(lgID, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Admin> result = newList();
            List<Admin> admins = getAll(DAO, TABLENAME2);
            for(Admin e : admins){
                String _var = e.getLgid();
                if(_var.indexOf(lgID) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Admin update(Admin admin) {
        AdminDAO DAO = DAO();
        return update(DAO, admin, DAO.TABLENAME);
    }

    public static Admin update(AdminDAO DAO, Admin admin) {
        return update(DAO, admin, DAO.TABLENAME);
    }

    public static Admin update(Admin admin, String TABLENAME2) {
        AdminDAO DAO = DAO();
        return update(DAO, admin, TABLENAME2);
    }

    public static Admin update(final AdminDAO DAO, final Admin admin,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(admin, TABLENAME2);
            if(n == -1) 
                return admin;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(admin);
        }
        return admin;
    }

    public static Admin asynchronousUpdate(Admin admin) {
        AdminDAO DAO = DAO();
        return asynchronousUpdate(DAO, admin, DAO.TABLENAME);
    }

    public static Admin asynchronousUpdate(AdminDAO DAO, Admin admin) {
        return asynchronousUpdate(DAO, admin, DAO.TABLENAME);
    }

    public static Admin asynchronousUpdate(Admin admin, String TABLENAME2) {
        AdminDAO DAO = DAO();
        return asynchronousUpdate(DAO, admin, TABLENAME2);
    }

    public static Admin asynchronousUpdate(final AdminDAO DAO, final Admin admin,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(admin, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(admin);
        }
        return admin;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        AdminDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(AdminDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final AdminDAO DAO, final String TABLENAME2) {
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

        List<Admin> admins = getAll();
        for (Admin admin : admins) {
            int n = DAO.insert2(admin, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
