package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - clan
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class ClanInternal extends InternalSupport{
    static Log log = LogFactory.getLog(ClanDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public ClanInternal(){}

    public static ClanDAO DAO(){
        return ClanEntity.ClanDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Clan[MAX];
    }
    private static Clan[] FIXED = new Clan[MAX];
    public static final Map<Integer, Clan> vars = newSortedMap();
    public static final Map<String, Integer> varsByCcid = newSortedMap();

    private static final List<Clan> fixedCache = newList();

    public static void put(Clan clan){
        if(clan == null || clan.id <= 0) return ;

        int id = clan.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(clan))
                	FIXED[id - 1] = clan;
                if (!fixedCache.contains(clan))
                	fixedCache.add(clan);
            }
        } else {
            vars.put(id, clan);
        }

        String ccid = clan.getCcid();
        varsByCcid.put(ccid, id);

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByCcid.clear();
        FIXED = new Clan[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(ClanDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(ClanDAO DAO, String TABLENAME2){
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

    public static void relocate(ClanDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        ClanDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(ClanDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        ClanDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(ClanDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        ClanDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(ClanDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        ClanDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(ClanDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(ClanDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        ClanDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(ClanDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(ClanDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        ClanDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(ClanDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Clan[maxId(DAO)];

        List<Clan> clans = DAO.selectAll();
        for (Clan clan : clans) {
            clan.reset();
            put(clan);
        }
    }

    public static Map toMap(Clan clan){
        return clan.toMap();
    }

    public static List<Map> toMap(List<Clan> clans){
        List<Map> ret = new Vector<Map>();
        for (Clan clan : clans){
            Map e = clan.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Clan> sortZh(List<Clan> clans, final String key) {
        Collections.sort(clans, new Comparator<Clan>() {
            public int compare(Clan o1, Clan o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return clans;
    }

    public static List<Clan> sort(List<Clan> clans, final String key) {
        Collections.sort(clans, new Comparator<Clan>() {
            public int compare(Clan o1, Clan o2) {
                return o1.compareTo(o2, key);
            }
        });
        return clans;
    }

    public static List<Clan> sort(List<Clan> clans){
        Collections.sort(clans, new Comparator<Clan>(){
            public int compare(Clan o1, Clan o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return clans;
    }

    public static List<Clan> sortReverse(List<Clan> clans){
        Collections.sort(clans, new Comparator<Clan>(){
            public int compare(Clan o1, Clan o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return clans;
    }

    public static List<Clan> sortCcid(List<Clan> clans){
        Collections.sort(clans, new Comparator<Clan>(){
            public int compare(Clan o1, Clan o2) {
                Object v1 = o1.getCcid();
                Object v2 = o2.getCcid();
                return compareTo(v1, v2);
            }
        });
        return clans;
    }

    public static List<Clan> sortCcidRo(List<Clan> clans){
        Collections.sort(clans, new Comparator<Clan>(){
            public int compare(Clan o1, Clan o2) {
                Object v1 = o1.getCcid();
                Object v2 = o2.getCcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return clans;
    }

    public static Clan insert(Clan clan) {
        ClanDAO DAO = DAO();
        return insert(DAO, clan, DAO.TABLENAME);
    }

    public static Clan insert(ClanDAO DAO, Clan clan) {
        return insert(DAO, clan, DAO.TABLENAME);
    }

    public static Clan insert(Clan clan, String TABLENAME2) {
        ClanDAO DAO = DAO();
        return insert(DAO, clan, TABLENAME2);
    }

    public static Clan insert(ClanDAO DAO, Clan clan, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(clan, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        clan.id = n;
        if(cacheModel != NO_CACHE) put(clan);

        return clan;
    }

    public static Clan asynchronousInsert(Clan clan) {
        ClanDAO DAO = DAO();
        return asynchronousInsert(DAO, clan, DAO.TABLENAME);
    }
    public static Clan asynchronousInsert(ClanDAO DAO, Clan clan) {
        return asynchronousInsert(DAO, clan, DAO.TABLENAME);
    }
    public static Clan asynchronousInsert(Clan clan, String TABLENAME2) {
        ClanDAO DAO = DAO();
        return asynchronousInsert(DAO, clan, TABLENAME2);
    }
    public static Clan asynchronousInsert(ClanDAO DAO, Clan clan, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(clan, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        clan.id = n;
        if(cacheModel != NO_CACHE) put(clan);
        return clan;
    }
    public static Clan insert2(Clan clan) {
        ClanDAO DAO = DAO();
        return insert2(DAO, clan, DAO.TABLENAME);
    }

    public static Clan insert2(ClanDAO DAO, Clan clan) {
        return insert2(DAO, clan, DAO.TABLENAME);
    }

    public static Clan insert2(Clan clan, String TABLENAME2) {
        ClanDAO DAO = DAO();
        return insert2(DAO, clan, TABLENAME2);
    }

    public static Clan insert2(ClanDAO DAO, Clan clan, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(clan, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        clan.id = n;
        if(cacheModel != NO_CACHE) put(clan);

        return clan;
    }

    public static Clan asynchronousInsert2(Clan clan) {
        ClanDAO DAO = DAO();
        return asynchronousInsert2(DAO, clan, DAO.TABLENAME);
    }
    public static Clan asynchronousInsert2(ClanDAO DAO, Clan clan) {
        return asynchronousInsert2(DAO, clan, DAO.TABLENAME);
    }
    public static Clan asynchronousInsert2(Clan clan, String TABLENAME2) {
        ClanDAO DAO = DAO();
        return asynchronousInsert2(DAO, clan, TABLENAME2);
    }
    public static Clan asynchronousInsert2(ClanDAO DAO, Clan clan, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        clan.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(clan, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(clan);
        return clan;
    }
    public static int[] insert(List<Clan> clans) {
        ClanDAO DAO = DAO();
        return insert(DAO, clans, DAO.TABLENAME);
    }

    public static int[] insert(ClanDAO DAO, List<Clan> clans) {
        return insert(DAO, clans, DAO.TABLENAME);
    }

    public static int[] insert(List<Clan> clans, String TABLENAME2) {
        ClanDAO DAO = DAO();
        return insert(DAO, clans, TABLENAME2);
    }

    public static int[] insert(ClanDAO DAO, List<Clan> clans, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(clans, TABLENAME2);
            int n = 0;
            for(Clan clan : clans){
                clan.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[clans.size()];
        int n = 0;
        for(Clan clan : clans){
            clan = insert(DAO, clan, TABLENAME2);
            ret[n++] = (clan == null) ? 0 : (int)clan.id;
        }
        return ret;
    }

    public static int delete(Clan clan) {
        int id = clan.id;
        ClanDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        ClanDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(ClanDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        ClanDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(ClanDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Clan clan) {
        int id = clan.id;
        ClanDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        ClanDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(ClanDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        ClanDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(ClanDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        ClanDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(ClanDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        ClanDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(ClanDAO DAO, int[] ids,String TABLENAME2) {
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
        ClanDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, ClanDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        ClanDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final ClanDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Clan> beans) {
        ClanDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Clan> beans, ClanDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Clan> beans, String TABLENAME2) {
        ClanDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Clan> beans, final ClanDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Clan clan : beans){
                int id = clan.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Clan> getAll() {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan> getAll(ClanDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan> getAll(String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Clan> getAll(final ClanDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Clan> getNoSortAll() {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan> getNoSortAll(ClanDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan> getNoSortAll(String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Clan> getNoSortAll(final ClanDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Clan> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Clan clan = FIXED[i];
                if (clan != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Clan> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Clan> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(ClanDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final ClanDAO DAO, final String TABLENAME2) {
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
        ClanDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(ClanDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        ClanDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final ClanDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Clan> getIn(List<Integer> keys) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan> getIn(List<Integer> keys, ClanDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan> getIn(List<Integer> keys, String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Clan> getIn(final List<Integer> keys, final ClanDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Clan> getNoSortIn(List<Integer> keys) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan> getNoSortIn(List<Integer> keys, ClanDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Clan> getNoSortIn(final List<Integer> keys, final ClanDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Clan> result = newList();
            for (int key : keys) {
                Clan clan = getByKeyFromMemory(key);
                if( clan == null ) continue;
                result.add(clan);
            }
            return result;
        }
    }

    public static List<Clan> getLast(int num) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Clan> getLast(ClanDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Clan> getLast(int num, String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Clan> getLast(final ClanDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Clan> result = newList();
            List<Clan> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Clan last() {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Clan last(ClanDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Clan last(String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Clan last(final ClanDAO DAO, final String TABLENAME2) {
        Clan result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        ClanDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(ClanDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        ClanDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final ClanDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Clan> getGtKey(int id) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Clan> getGtKey(ClanDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Clan> getGtKey(int id, String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Clan> getGtKey(final ClanDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan> result = newList();
            List<Clan> clans = getAll();
            for (Clan clan : clans) {
                if(clan.id <= id) continue;
                result.add(clan);
            }
            return result;
        }
    }

    public static Clan getByKey(int id) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Clan getByKey(ClanDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Clan getByKey(int id, String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Clan getByKey(final ClanDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Clan getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Clan deleteFromMemory(final int id) {
        Clan clan;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            clan = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(clan);
        } else {
            clan = vars.remove(id);
        }
        if(clan == null) return null;

        String ccid = clan.getCcid();
        varsByCcid.remove(ccid);

        return clan;
    }

    public static List<Clan> getByPage(int page, int size) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Clan> getByPage(ClanDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Clan> getByPage(int page, int size, String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Clan> getByPage(final ClanDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan> result = newList();
            List<Clan> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(ClanDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final ClanDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Clan getByCcid(String ccid) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static Clan getByCcid(ClanDAO DAO, String ccid) {
        return getByCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static Clan getByCcid(String ccid, String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCcid(DAO, ccid, TABLENAME2);
    }

    public static Clan getByCcid(final ClanDAO DAO, final String ccid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCcid(ccid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByCcid.get(ccid);
            if(id == null) return null;
            Clan result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getCcid().equals(ccid)){
                varsByCcid.remove(ccid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeCcid(String ccid) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static int countLikeCcid(ClanDAO DAO, String ccid) {
        return countLikeCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static int countLikeCcid(String ccid, String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeCcid(DAO, ccid, TABLENAME2);
    }

    public static int countLikeCcid(final ClanDAO DAO, final String ccid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeCcid(ccid, TABLENAME2);
        }
        List<Clan> clans = getLikeCcid(DAO, ccid, TABLENAME2);
        return clans.size();
    }

    public static List<Clan> getLikeCcid(String ccid) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static List<Clan> getLikeCcid(ClanDAO DAO, String ccid) {
        return getLikeCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static List<Clan> getLikeCcid(String ccid, String TABLENAME2) {
        ClanDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeCcid(DAO, ccid, TABLENAME2);
    }

    public static List<Clan> getLikeCcid(final ClanDAO DAO, final String ccid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeCcid(ccid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Clan> result = newList();
            List<Clan> clans = getAll(DAO, TABLENAME2);
            for(Clan e : clans){
                String _var = e.getCcid();
                if(_var.indexOf(ccid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Clan update(Clan clan) {
        ClanDAO DAO = DAO();
        return update(DAO, clan, DAO.TABLENAME);
    }

    public static Clan update(ClanDAO DAO, Clan clan) {
        return update(DAO, clan, DAO.TABLENAME);
    }

    public static Clan update(Clan clan, String TABLENAME2) {
        ClanDAO DAO = DAO();
        return update(DAO, clan, TABLENAME2);
    }

    public static Clan update(final ClanDAO DAO, final Clan clan,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(clan, TABLENAME2);
            if(n == -1) 
                return clan;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(clan);
        }
        return clan;
    }

    public static Clan asynchronousUpdate(Clan clan) {
        ClanDAO DAO = DAO();
        return asynchronousUpdate(DAO, clan, DAO.TABLENAME);
    }

    public static Clan asynchronousUpdate(ClanDAO DAO, Clan clan) {
        return asynchronousUpdate(DAO, clan, DAO.TABLENAME);
    }

    public static Clan asynchronousUpdate(Clan clan, String TABLENAME2) {
        ClanDAO DAO = DAO();
        return asynchronousUpdate(DAO, clan, TABLENAME2);
    }

    public static Clan asynchronousUpdate(final ClanDAO DAO, final Clan clan,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(clan, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(clan);
        }
        return clan;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        ClanDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(ClanDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final ClanDAO DAO, final String TABLENAME2) {
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

        List<Clan> clans = getAll();
        for (Clan clan : clans) {
            int n = DAO.insert2(clan, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
