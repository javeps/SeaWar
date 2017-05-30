package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - rank_clan
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Rank_clanInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Rank_clanDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Rank_clanInternal(){}

    public static Rank_clanDAO DAO(){
        return Rank_clanEntity.Rank_clanDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Rank_clan[MAX];
    }
    private static Rank_clan[] FIXED = new Rank_clan[MAX];
    public static final Map<Integer, Rank_clan> vars = newSortedMap();

    private static final List<Rank_clan> fixedCache = newList();

    public static void put(Rank_clan rank_clan){
        if(rank_clan == null || rank_clan.id <= 0) return ;

        int id = rank_clan.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(rank_clan))
                	FIXED[id - 1] = rank_clan;
                if (!fixedCache.contains(rank_clan))
                	fixedCache.add(rank_clan);
            }
        } else {
            vars.put(id, rank_clan);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        FIXED = new Rank_clan[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Rank_clanDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Rank_clanDAO DAO, String TABLENAME2){
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

    public static void relocate(Rank_clanDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Rank_clanDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Rank_clanDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Rank_clanDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Rank_clanDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Rank_clanDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Rank_clanDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Rank_clanDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Rank_clanDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Rank_clanDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Rank_clanDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Rank_clanDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Rank_clanDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Rank_clan[maxId(DAO)];

        List<Rank_clan> rank_clans = DAO.selectAll();
        for (Rank_clan rank_clan : rank_clans) {
            rank_clan.reset();
            put(rank_clan);
        }
    }

    public static Map toMap(Rank_clan rank_clan){
        return rank_clan.toMap();
    }

    public static List<Map> toMap(List<Rank_clan> rank_clans){
        List<Map> ret = new Vector<Map>();
        for (Rank_clan rank_clan : rank_clans){
            Map e = rank_clan.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Rank_clan> sortZh(List<Rank_clan> rank_clans, final String key) {
        Collections.sort(rank_clans, new Comparator<Rank_clan>() {
            public int compare(Rank_clan o1, Rank_clan o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return rank_clans;
    }

    public static List<Rank_clan> sort(List<Rank_clan> rank_clans, final String key) {
        Collections.sort(rank_clans, new Comparator<Rank_clan>() {
            public int compare(Rank_clan o1, Rank_clan o2) {
                return o1.compareTo(o2, key);
            }
        });
        return rank_clans;
    }

    public static List<Rank_clan> sort(List<Rank_clan> rank_clans){
        Collections.sort(rank_clans, new Comparator<Rank_clan>(){
            public int compare(Rank_clan o1, Rank_clan o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return rank_clans;
    }

    public static List<Rank_clan> sortReverse(List<Rank_clan> rank_clans){
        Collections.sort(rank_clans, new Comparator<Rank_clan>(){
            public int compare(Rank_clan o1, Rank_clan o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return rank_clans;
    }

    public static Rank_clan insert(Rank_clan rank_clan) {
        Rank_clanDAO DAO = DAO();
        return insert(DAO, rank_clan, DAO.TABLENAME);
    }

    public static Rank_clan insert(Rank_clanDAO DAO, Rank_clan rank_clan) {
        return insert(DAO, rank_clan, DAO.TABLENAME);
    }

    public static Rank_clan insert(Rank_clan rank_clan, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return insert(DAO, rank_clan, TABLENAME2);
    }

    public static Rank_clan insert(Rank_clanDAO DAO, Rank_clan rank_clan, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(rank_clan, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rank_clan.id = n;
        if(cacheModel != NO_CACHE) put(rank_clan);

        return rank_clan;
    }

    public static Rank_clan asynchronousInsert(Rank_clan rank_clan) {
        Rank_clanDAO DAO = DAO();
        return asynchronousInsert(DAO, rank_clan, DAO.TABLENAME);
    }
    public static Rank_clan asynchronousInsert(Rank_clanDAO DAO, Rank_clan rank_clan) {
        return asynchronousInsert(DAO, rank_clan, DAO.TABLENAME);
    }
    public static Rank_clan asynchronousInsert(Rank_clan rank_clan, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return asynchronousInsert(DAO, rank_clan, TABLENAME2);
    }
    public static Rank_clan asynchronousInsert(Rank_clanDAO DAO, Rank_clan rank_clan, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(rank_clan, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        rank_clan.id = n;
        if(cacheModel != NO_CACHE) put(rank_clan);
        return rank_clan;
    }
    public static Rank_clan insert2(Rank_clan rank_clan) {
        Rank_clanDAO DAO = DAO();
        return insert2(DAO, rank_clan, DAO.TABLENAME);
    }

    public static Rank_clan insert2(Rank_clanDAO DAO, Rank_clan rank_clan) {
        return insert2(DAO, rank_clan, DAO.TABLENAME);
    }

    public static Rank_clan insert2(Rank_clan rank_clan, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return insert2(DAO, rank_clan, TABLENAME2);
    }

    public static Rank_clan insert2(Rank_clanDAO DAO, Rank_clan rank_clan, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(rank_clan, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rank_clan.id = n;
        if(cacheModel != NO_CACHE) put(rank_clan);

        return rank_clan;
    }

    public static Rank_clan asynchronousInsert2(Rank_clan rank_clan) {
        Rank_clanDAO DAO = DAO();
        return asynchronousInsert2(DAO, rank_clan, DAO.TABLENAME);
    }
    public static Rank_clan asynchronousInsert2(Rank_clanDAO DAO, Rank_clan rank_clan) {
        return asynchronousInsert2(DAO, rank_clan, DAO.TABLENAME);
    }
    public static Rank_clan asynchronousInsert2(Rank_clan rank_clan, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return asynchronousInsert2(DAO, rank_clan, TABLENAME2);
    }
    public static Rank_clan asynchronousInsert2(Rank_clanDAO DAO, Rank_clan rank_clan, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        rank_clan.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(rank_clan, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(rank_clan);
        return rank_clan;
    }
    public static int[] insert(List<Rank_clan> rank_clans) {
        Rank_clanDAO DAO = DAO();
        return insert(DAO, rank_clans, DAO.TABLENAME);
    }

    public static int[] insert(Rank_clanDAO DAO, List<Rank_clan> rank_clans) {
        return insert(DAO, rank_clans, DAO.TABLENAME);
    }

    public static int[] insert(List<Rank_clan> rank_clans, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return insert(DAO, rank_clans, TABLENAME2);
    }

    public static int[] insert(Rank_clanDAO DAO, List<Rank_clan> rank_clans, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(rank_clans, TABLENAME2);
            int n = 0;
            for(Rank_clan rank_clan : rank_clans){
                rank_clan.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[rank_clans.size()];
        int n = 0;
        for(Rank_clan rank_clan : rank_clans){
            rank_clan = insert(DAO, rank_clan, TABLENAME2);
            ret[n++] = (rank_clan == null) ? 0 : (int)rank_clan.id;
        }
        return ret;
    }

    public static int delete(Rank_clan rank_clan) {
        int id = rank_clan.id;
        Rank_clanDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Rank_clanDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Rank_clanDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Rank_clanDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Rank_clan rank_clan) {
        int id = rank_clan.id;
        Rank_clanDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Rank_clanDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Rank_clanDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Rank_clanDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Rank_clanDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Rank_clanDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Rank_clanDAO DAO, int[] ids,String TABLENAME2) {
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
        Rank_clanDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Rank_clanDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Rank_clanDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Rank_clan> beans) {
        Rank_clanDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rank_clan> beans, Rank_clanDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rank_clan> beans, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Rank_clan> beans, final Rank_clanDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Rank_clan rank_clan : beans){
                int id = rank_clan.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Rank_clan> getAll() {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rank_clan> getAll(Rank_clanDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rank_clan> getAll(String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Rank_clan> getAll(final Rank_clanDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rank_clan> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rank_clan> getNoSortAll() {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rank_clan> getNoSortAll(Rank_clanDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rank_clan> getNoSortAll(String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Rank_clan> getNoSortAll(final Rank_clanDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Rank_clan> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rank_clan> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Rank_clan rank_clan = FIXED[i];
                if (rank_clan != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Rank_clan> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Rank_clan> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Rank_clanDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Rank_clanDAO DAO, final String TABLENAME2) {
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
        Rank_clanDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Rank_clanDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Rank_clanDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Rank_clan> getIn(List<Integer> keys) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rank_clan> getIn(List<Integer> keys, Rank_clanDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rank_clan> getIn(List<Integer> keys, String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Rank_clan> getIn(final List<Integer> keys, final Rank_clanDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rank_clan> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rank_clan> getNoSortIn(List<Integer> keys) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rank_clan> getNoSortIn(List<Integer> keys, Rank_clanDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rank_clan> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Rank_clan> getNoSortIn(final List<Integer> keys, final Rank_clanDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Rank_clan> result = newList();
            for (int key : keys) {
                Rank_clan rank_clan = getByKeyFromMemory(key);
                if( rank_clan == null ) continue;
                result.add(rank_clan);
            }
            return result;
        }
    }

    public static List<Rank_clan> getLast(int num) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rank_clan> getLast(Rank_clanDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rank_clan> getLast(int num, String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Rank_clan> getLast(final Rank_clanDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Rank_clan> result = newList();
            List<Rank_clan> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Rank_clan last() {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Rank_clan last(Rank_clanDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Rank_clan last(String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Rank_clan last(final Rank_clanDAO DAO, final String TABLENAME2) {
        Rank_clan result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Rank_clanDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Rank_clanDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Rank_clanDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Rank_clan> getGtKey(int id) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rank_clan> getGtKey(Rank_clanDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rank_clan> getGtKey(int id, String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Rank_clan> getGtKey(final Rank_clanDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rank_clan> result = newList();
            List<Rank_clan> rank_clans = getAll();
            for (Rank_clan rank_clan : rank_clans) {
                if(rank_clan.id <= id) continue;
                result.add(rank_clan);
            }
            return result;
        }
    }

    public static Rank_clan getByKey(int id) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Rank_clan getByKey(Rank_clanDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Rank_clan getByKey(int id, String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Rank_clan getByKey(final Rank_clanDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Rank_clan getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Rank_clan deleteFromMemory(final int id) {
        Rank_clan rank_clan;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            rank_clan = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(rank_clan);
        } else {
            rank_clan = vars.remove(id);
        }
        if(rank_clan == null) return null;

        return rank_clan;
    }

    public static List<Rank_clan> getByPage(int page, int size) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rank_clan> getByPage(Rank_clanDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rank_clan> getByPage(int page, int size, String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Rank_clan> getByPage(final Rank_clanDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rank_clan> result = newList();
            List<Rank_clan> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Rank_clanDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Rank_clanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Rank_clanDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Rank_clan update(Rank_clan rank_clan) {
        Rank_clanDAO DAO = DAO();
        return update(DAO, rank_clan, DAO.TABLENAME);
    }

    public static Rank_clan update(Rank_clanDAO DAO, Rank_clan rank_clan) {
        return update(DAO, rank_clan, DAO.TABLENAME);
    }

    public static Rank_clan update(Rank_clan rank_clan, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return update(DAO, rank_clan, TABLENAME2);
    }

    public static Rank_clan update(final Rank_clanDAO DAO, final Rank_clan rank_clan,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(rank_clan, TABLENAME2);
            if(n == -1) 
                return rank_clan;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(rank_clan);
        }
        return rank_clan;
    }

    public static Rank_clan asynchronousUpdate(Rank_clan rank_clan) {
        Rank_clanDAO DAO = DAO();
        return asynchronousUpdate(DAO, rank_clan, DAO.TABLENAME);
    }

    public static Rank_clan asynchronousUpdate(Rank_clanDAO DAO, Rank_clan rank_clan) {
        return asynchronousUpdate(DAO, rank_clan, DAO.TABLENAME);
    }

    public static Rank_clan asynchronousUpdate(Rank_clan rank_clan, String TABLENAME2) {
        Rank_clanDAO DAO = DAO();
        return asynchronousUpdate(DAO, rank_clan, TABLENAME2);
    }

    public static Rank_clan asynchronousUpdate(final Rank_clanDAO DAO, final Rank_clan rank_clan,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(rank_clan, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(rank_clan);
        }
        return rank_clan;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Rank_clanDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Rank_clanDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Rank_clanDAO DAO, final String TABLENAME2) {
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

        List<Rank_clan> rank_clans = getAll();
        for (Rank_clan rank_clan : rank_clans) {
            int n = DAO.insert2(rank_clan, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
