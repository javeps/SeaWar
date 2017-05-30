package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - notice
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class NoticeInternal extends InternalSupport{
    static Log log = LogFactory.getLog(NoticeDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public NoticeInternal(){}

    public static NoticeDAO DAO(){
        return NoticeEntity.NoticeDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Notice[MAX];
    }
    private static Notice[] FIXED = new Notice[MAX];
    public static final Map<Integer, Notice> vars = newSortedMap();

    private static final List<Notice> fixedCache = newList();

    public static void put(Notice notice){
        if(notice == null || notice.id <= 0) return ;

        int id = notice.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(notice))
                	FIXED[id - 1] = notice;
                if (!fixedCache.contains(notice))
                	fixedCache.add(notice);
            }
        } else {
            vars.put(id, notice);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        FIXED = new Notice[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(NoticeDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(NoticeDAO DAO, String TABLENAME2){
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

    public static void relocate(NoticeDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        NoticeDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(NoticeDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        NoticeDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(NoticeDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        NoticeDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(NoticeDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        NoticeDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(NoticeDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(NoticeDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        NoticeDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(NoticeDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(NoticeDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        NoticeDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(NoticeDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Notice[maxId(DAO)];

        List<Notice> notices = DAO.selectAll();
        for (Notice notice : notices) {
            notice.reset();
            put(notice);
        }
    }

    public static Map toMap(Notice notice){
        return notice.toMap();
    }

    public static List<Map> toMap(List<Notice> notices){
        List<Map> ret = new Vector<Map>();
        for (Notice notice : notices){
            Map e = notice.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Notice> sortZh(List<Notice> notices, final String key) {
        Collections.sort(notices, new Comparator<Notice>() {
            public int compare(Notice o1, Notice o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return notices;
    }

    public static List<Notice> sort(List<Notice> notices, final String key) {
        Collections.sort(notices, new Comparator<Notice>() {
            public int compare(Notice o1, Notice o2) {
                return o1.compareTo(o2, key);
            }
        });
        return notices;
    }

    public static List<Notice> sort(List<Notice> notices){
        Collections.sort(notices, new Comparator<Notice>(){
            public int compare(Notice o1, Notice o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return notices;
    }

    public static List<Notice> sortReverse(List<Notice> notices){
        Collections.sort(notices, new Comparator<Notice>(){
            public int compare(Notice o1, Notice o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return notices;
    }

    public static Notice insert(Notice notice) {
        NoticeDAO DAO = DAO();
        return insert(DAO, notice, DAO.TABLENAME);
    }

    public static Notice insert(NoticeDAO DAO, Notice notice) {
        return insert(DAO, notice, DAO.TABLENAME);
    }

    public static Notice insert(Notice notice, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return insert(DAO, notice, TABLENAME2);
    }

    public static Notice insert(NoticeDAO DAO, Notice notice, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(notice, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        notice.id = n;
        if(cacheModel != NO_CACHE) put(notice);

        return notice;
    }

    public static Notice asynchronousInsert(Notice notice) {
        NoticeDAO DAO = DAO();
        return asynchronousInsert(DAO, notice, DAO.TABLENAME);
    }
    public static Notice asynchronousInsert(NoticeDAO DAO, Notice notice) {
        return asynchronousInsert(DAO, notice, DAO.TABLENAME);
    }
    public static Notice asynchronousInsert(Notice notice, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return asynchronousInsert(DAO, notice, TABLENAME2);
    }
    public static Notice asynchronousInsert(NoticeDAO DAO, Notice notice, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(notice, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        notice.id = n;
        if(cacheModel != NO_CACHE) put(notice);
        return notice;
    }
    public static Notice insert2(Notice notice) {
        NoticeDAO DAO = DAO();
        return insert2(DAO, notice, DAO.TABLENAME);
    }

    public static Notice insert2(NoticeDAO DAO, Notice notice) {
        return insert2(DAO, notice, DAO.TABLENAME);
    }

    public static Notice insert2(Notice notice, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return insert2(DAO, notice, TABLENAME2);
    }

    public static Notice insert2(NoticeDAO DAO, Notice notice, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(notice, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        notice.id = n;
        if(cacheModel != NO_CACHE) put(notice);

        return notice;
    }

    public static Notice asynchronousInsert2(Notice notice) {
        NoticeDAO DAO = DAO();
        return asynchronousInsert2(DAO, notice, DAO.TABLENAME);
    }
    public static Notice asynchronousInsert2(NoticeDAO DAO, Notice notice) {
        return asynchronousInsert2(DAO, notice, DAO.TABLENAME);
    }
    public static Notice asynchronousInsert2(Notice notice, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return asynchronousInsert2(DAO, notice, TABLENAME2);
    }
    public static Notice asynchronousInsert2(NoticeDAO DAO, Notice notice, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        notice.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(notice, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(notice);
        return notice;
    }
    public static int[] insert(List<Notice> notices) {
        NoticeDAO DAO = DAO();
        return insert(DAO, notices, DAO.TABLENAME);
    }

    public static int[] insert(NoticeDAO DAO, List<Notice> notices) {
        return insert(DAO, notices, DAO.TABLENAME);
    }

    public static int[] insert(List<Notice> notices, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return insert(DAO, notices, TABLENAME2);
    }

    public static int[] insert(NoticeDAO DAO, List<Notice> notices, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(notices, TABLENAME2);
            int n = 0;
            for(Notice notice : notices){
                notice.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[notices.size()];
        int n = 0;
        for(Notice notice : notices){
            notice = insert(DAO, notice, TABLENAME2);
            ret[n++] = (notice == null) ? 0 : (int)notice.id;
        }
        return ret;
    }

    public static int delete(Notice notice) {
        int id = notice.id;
        NoticeDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        NoticeDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(NoticeDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(NoticeDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Notice notice) {
        int id = notice.id;
        NoticeDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        NoticeDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(NoticeDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(NoticeDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        NoticeDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(NoticeDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(NoticeDAO DAO, int[] ids,String TABLENAME2) {
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
        NoticeDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, NoticeDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final NoticeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Notice> beans) {
        NoticeDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Notice> beans, NoticeDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Notice> beans, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Notice> beans, final NoticeDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Notice notice : beans){
                int id = notice.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Notice> getAll() {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Notice> getAll(NoticeDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Notice> getAll(String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Notice> getAll(final NoticeDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Notice> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Notice> getNoSortAll() {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Notice> getNoSortAll(NoticeDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Notice> getNoSortAll(String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Notice> getNoSortAll(final NoticeDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Notice> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Notice> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Notice notice = FIXED[i];
                if (notice != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Notice> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Notice> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(NoticeDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final NoticeDAO DAO, final String TABLENAME2) {
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
        NoticeDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(NoticeDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final NoticeDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Notice> getIn(List<Integer> keys) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Notice> getIn(List<Integer> keys, NoticeDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Notice> getIn(List<Integer> keys, String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Notice> getIn(final List<Integer> keys, final NoticeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Notice> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Notice> getNoSortIn(List<Integer> keys) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Notice> getNoSortIn(List<Integer> keys, NoticeDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Notice> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Notice> getNoSortIn(final List<Integer> keys, final NoticeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Notice> result = newList();
            for (int key : keys) {
                Notice notice = getByKeyFromMemory(key);
                if( notice == null ) continue;
                result.add(notice);
            }
            return result;
        }
    }

    public static List<Notice> getLast(int num) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Notice> getLast(NoticeDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Notice> getLast(int num, String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Notice> getLast(final NoticeDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Notice> result = newList();
            List<Notice> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Notice last() {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Notice last(NoticeDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Notice last(String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Notice last(final NoticeDAO DAO, final String TABLENAME2) {
        Notice result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        NoticeDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(NoticeDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final NoticeDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Notice> getGtKey(int id) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Notice> getGtKey(NoticeDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Notice> getGtKey(int id, String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Notice> getGtKey(final NoticeDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Notice> result = newList();
            List<Notice> notices = getAll();
            for (Notice notice : notices) {
                if(notice.id <= id) continue;
                result.add(notice);
            }
            return result;
        }
    }

    public static Notice getByKey(int id) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Notice getByKey(NoticeDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Notice getByKey(int id, String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Notice getByKey(final NoticeDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Notice getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Notice deleteFromMemory(final int id) {
        Notice notice;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            notice = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(notice);
        } else {
            notice = vars.remove(id);
        }
        if(notice == null) return null;

        return notice;
    }

    public static List<Notice> getByPage(int page, int size) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Notice> getByPage(NoticeDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Notice> getByPage(int page, int size, String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Notice> getByPage(final NoticeDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Notice> result = newList();
            List<Notice> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(NoticeDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        NoticeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final NoticeDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Notice update(Notice notice) {
        NoticeDAO DAO = DAO();
        return update(DAO, notice, DAO.TABLENAME);
    }

    public static Notice update(NoticeDAO DAO, Notice notice) {
        return update(DAO, notice, DAO.TABLENAME);
    }

    public static Notice update(Notice notice, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return update(DAO, notice, TABLENAME2);
    }

    public static Notice update(final NoticeDAO DAO, final Notice notice,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(notice, TABLENAME2);
            if(n == -1) 
                return notice;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(notice);
        }
        return notice;
    }

    public static Notice asynchronousUpdate(Notice notice) {
        NoticeDAO DAO = DAO();
        return asynchronousUpdate(DAO, notice, DAO.TABLENAME);
    }

    public static Notice asynchronousUpdate(NoticeDAO DAO, Notice notice) {
        return asynchronousUpdate(DAO, notice, DAO.TABLENAME);
    }

    public static Notice asynchronousUpdate(Notice notice, String TABLENAME2) {
        NoticeDAO DAO = DAO();
        return asynchronousUpdate(DAO, notice, TABLENAME2);
    }

    public static Notice asynchronousUpdate(final NoticeDAO DAO, final Notice notice,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(notice, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(notice);
        }
        return notice;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        NoticeDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(NoticeDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final NoticeDAO DAO, final String TABLENAME2) {
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

        List<Notice> notices = getAll();
        for (Notice notice : notices) {
            int n = DAO.insert2(notice, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
