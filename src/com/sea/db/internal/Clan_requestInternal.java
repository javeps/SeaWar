package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - clan_request
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Clan_requestInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Clan_requestDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Clan_requestInternal(){}

    public static Clan_requestDAO DAO(){
        return Clan_requestEntity.Clan_requestDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Clan_request[MAX];
    }
    private static Clan_request[] FIXED = new Clan_request[MAX];
    public static final Map<Integer, Clan_request> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByPcid = newSortedMap();
    public static final Map<String, Integer> varsByCcidPcid = newSortedMap();

    private static final List<Clan_request> fixedCache = newList();

    public static void put(Clan_request clan_request){
        if(clan_request == null || clan_request.id <= 0) return ;

        int id = clan_request.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(clan_request))
                	FIXED[id - 1] = clan_request;
                if (!fixedCache.contains(clan_request))
                	fixedCache.add(clan_request);
            }
        } else {
            vars.put(id, clan_request);
        }

        int pcid = clan_request.getPcid();
        Set m1 = varsByPcid.get(pcid);
        if(m1 == null){
            m1 = newSortedSet();
            varsByPcid.put(pcid, m1);
        }
        m1.add(id);

        { // ccid
            String vccid = clan_request.getCcid();
            int vpcid = clan_request.getPcid();
            String vkey = vccid + "-" + vpcid;
            varsByCcidPcid.put(vkey, id);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByPcid.clear();
        varsByCcidPcid.clear();
        FIXED = new Clan_request[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Clan_requestDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Clan_requestDAO DAO, String TABLENAME2){
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

    public static void relocate(Clan_requestDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Clan_requestDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Clan_requestDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Clan_requestDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Clan_requestDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Clan_requestDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Clan_requestDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Clan_requestDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Clan_requestDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Clan_requestDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Clan_requestDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Clan_requestDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Clan_requestDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Clan_request[maxId(DAO)];

        List<Clan_request> clan_requests = DAO.selectAll();
        for (Clan_request clan_request : clan_requests) {
            clan_request.reset();
            put(clan_request);
        }
    }

    public static Map toMap(Clan_request clan_request){
        return clan_request.toMap();
    }

    public static List<Map> toMap(List<Clan_request> clan_requests){
        List<Map> ret = new Vector<Map>();
        for (Clan_request clan_request : clan_requests){
            Map e = clan_request.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Clan_request> sortZh(List<Clan_request> clan_requests, final String key) {
        Collections.sort(clan_requests, new Comparator<Clan_request>() {
            public int compare(Clan_request o1, Clan_request o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return clan_requests;
    }

    public static List<Clan_request> sort(List<Clan_request> clan_requests, final String key) {
        Collections.sort(clan_requests, new Comparator<Clan_request>() {
            public int compare(Clan_request o1, Clan_request o2) {
                return o1.compareTo(o2, key);
            }
        });
        return clan_requests;
    }

    public static List<Clan_request> sort(List<Clan_request> clan_requests){
        Collections.sort(clan_requests, new Comparator<Clan_request>(){
            public int compare(Clan_request o1, Clan_request o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return clan_requests;
    }

    public static List<Clan_request> sortReverse(List<Clan_request> clan_requests){
        Collections.sort(clan_requests, new Comparator<Clan_request>(){
            public int compare(Clan_request o1, Clan_request o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return clan_requests;
    }

    public static List<Clan_request> sortPcid(List<Clan_request> clan_requests){
        Collections.sort(clan_requests, new Comparator<Clan_request>(){
            public int compare(Clan_request o1, Clan_request o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return compareTo(v1, v2);
            }
        });
        return clan_requests;
    }

    public static List<Clan_request> sortPcidRo(List<Clan_request> clan_requests){
        Collections.sort(clan_requests, new Comparator<Clan_request>(){
            public int compare(Clan_request o1, Clan_request o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return clan_requests;
    }

    public static Clan_request insert(Clan_request clan_request) {
        Clan_requestDAO DAO = DAO();
        return insert(DAO, clan_request, DAO.TABLENAME);
    }

    public static Clan_request insert(Clan_requestDAO DAO, Clan_request clan_request) {
        return insert(DAO, clan_request, DAO.TABLENAME);
    }

    public static Clan_request insert(Clan_request clan_request, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return insert(DAO, clan_request, TABLENAME2);
    }

    public static Clan_request insert(Clan_requestDAO DAO, Clan_request clan_request, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(clan_request, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        clan_request.id = n;
        if(cacheModel != NO_CACHE) put(clan_request);

        return clan_request;
    }

    public static Clan_request asynchronousInsert(Clan_request clan_request) {
        Clan_requestDAO DAO = DAO();
        return asynchronousInsert(DAO, clan_request, DAO.TABLENAME);
    }
    public static Clan_request asynchronousInsert(Clan_requestDAO DAO, Clan_request clan_request) {
        return asynchronousInsert(DAO, clan_request, DAO.TABLENAME);
    }
    public static Clan_request asynchronousInsert(Clan_request clan_request, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return asynchronousInsert(DAO, clan_request, TABLENAME2);
    }
    public static Clan_request asynchronousInsert(Clan_requestDAO DAO, Clan_request clan_request, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(clan_request, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        clan_request.id = n;
        if(cacheModel != NO_CACHE) put(clan_request);
        return clan_request;
    }
    public static Clan_request insert2(Clan_request clan_request) {
        Clan_requestDAO DAO = DAO();
        return insert2(DAO, clan_request, DAO.TABLENAME);
    }

    public static Clan_request insert2(Clan_requestDAO DAO, Clan_request clan_request) {
        return insert2(DAO, clan_request, DAO.TABLENAME);
    }

    public static Clan_request insert2(Clan_request clan_request, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return insert2(DAO, clan_request, TABLENAME2);
    }

    public static Clan_request insert2(Clan_requestDAO DAO, Clan_request clan_request, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(clan_request, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        clan_request.id = n;
        if(cacheModel != NO_CACHE) put(clan_request);

        return clan_request;
    }

    public static Clan_request asynchronousInsert2(Clan_request clan_request) {
        Clan_requestDAO DAO = DAO();
        return asynchronousInsert2(DAO, clan_request, DAO.TABLENAME);
    }
    public static Clan_request asynchronousInsert2(Clan_requestDAO DAO, Clan_request clan_request) {
        return asynchronousInsert2(DAO, clan_request, DAO.TABLENAME);
    }
    public static Clan_request asynchronousInsert2(Clan_request clan_request, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return asynchronousInsert2(DAO, clan_request, TABLENAME2);
    }
    public static Clan_request asynchronousInsert2(Clan_requestDAO DAO, Clan_request clan_request, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        clan_request.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(clan_request, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(clan_request);
        return clan_request;
    }
    public static int[] insert(List<Clan_request> clan_requests) {
        Clan_requestDAO DAO = DAO();
        return insert(DAO, clan_requests, DAO.TABLENAME);
    }

    public static int[] insert(Clan_requestDAO DAO, List<Clan_request> clan_requests) {
        return insert(DAO, clan_requests, DAO.TABLENAME);
    }

    public static int[] insert(List<Clan_request> clan_requests, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return insert(DAO, clan_requests, TABLENAME2);
    }

    public static int[] insert(Clan_requestDAO DAO, List<Clan_request> clan_requests, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(clan_requests, TABLENAME2);
            int n = 0;
            for(Clan_request clan_request : clan_requests){
                clan_request.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[clan_requests.size()];
        int n = 0;
        for(Clan_request clan_request : clan_requests){
            clan_request = insert(DAO, clan_request, TABLENAME2);
            ret[n++] = (clan_request == null) ? 0 : (int)clan_request.id;
        }
        return ret;
    }

    public static int delete(Clan_request clan_request) {
        int id = clan_request.id;
        Clan_requestDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Clan_requestDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Clan_requestDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Clan_requestDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Clan_request clan_request) {
        int id = clan_request.id;
        Clan_requestDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Clan_requestDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Clan_requestDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Clan_requestDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Clan_requestDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Clan_requestDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Clan_requestDAO DAO, int[] ids,String TABLENAME2) {
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
        Clan_requestDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Clan_requestDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Clan_requestDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Clan_request> beans) {
        Clan_requestDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Clan_request> beans, Clan_requestDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Clan_request> beans, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Clan_request> beans, final Clan_requestDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Clan_request clan_request : beans){
                int id = clan_request.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Clan_request> getAll() {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan_request> getAll(Clan_requestDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan_request> getAll(String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Clan_request> getAll(final Clan_requestDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan_request> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Clan_request> getNoSortAll() {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan_request> getNoSortAll(Clan_requestDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan_request> getNoSortAll(String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Clan_request> getNoSortAll(final Clan_requestDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Clan_request> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan_request> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Clan_request clan_request = FIXED[i];
                if (clan_request != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Clan_request> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Clan_request> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Clan_requestDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Clan_requestDAO DAO, final String TABLENAME2) {
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
        Clan_requestDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Clan_requestDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Clan_requestDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Clan_request> getIn(List<Integer> keys) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan_request> getIn(List<Integer> keys, Clan_requestDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan_request> getIn(List<Integer> keys, String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Clan_request> getIn(final List<Integer> keys, final Clan_requestDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan_request> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Clan_request> getNoSortIn(List<Integer> keys) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan_request> getNoSortIn(List<Integer> keys, Clan_requestDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan_request> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Clan_request> getNoSortIn(final List<Integer> keys, final Clan_requestDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Clan_request> result = newList();
            for (int key : keys) {
                Clan_request clan_request = getByKeyFromMemory(key);
                if( clan_request == null ) continue;
                result.add(clan_request);
            }
            return result;
        }
    }

    public static List<Clan_request> getLast(int num) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Clan_request> getLast(Clan_requestDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Clan_request> getLast(int num, String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Clan_request> getLast(final Clan_requestDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Clan_request> result = newList();
            List<Clan_request> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Clan_request last() {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Clan_request last(Clan_requestDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Clan_request last(String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Clan_request last(final Clan_requestDAO DAO, final String TABLENAME2) {
        Clan_request result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Clan_requestDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Clan_requestDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Clan_requestDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Clan_request> getGtKey(int id) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Clan_request> getGtKey(Clan_requestDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Clan_request> getGtKey(int id, String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Clan_request> getGtKey(final Clan_requestDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan_request> result = newList();
            List<Clan_request> clan_requests = getAll();
            for (Clan_request clan_request : clan_requests) {
                if(clan_request.id <= id) continue;
                result.add(clan_request);
            }
            return result;
        }
    }

    public static Clan_request getByKey(int id) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Clan_request getByKey(Clan_requestDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Clan_request getByKey(int id, String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Clan_request getByKey(final Clan_requestDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Clan_request getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Clan_request deleteFromMemory(final int id) {
        Clan_request clan_request;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            clan_request = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(clan_request);
        } else {
            clan_request = vars.remove(id);
        }
        if(clan_request == null) return null;

        int pcid = clan_request.getPcid();
        Set m1 = varsByPcid.get(pcid);
        if(m1 != null)
            m1.remove(id);

        { // ccid
            String vccid = clan_request.getCcid();
            int vpcid = clan_request.getPcid();
            String vkey = vccid + "-" + vpcid;
            varsByCcidPcid.remove(vkey);
        }

        return clan_request;
    }

    public static List<Clan_request> getByPage(int page, int size) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Clan_request> getByPage(Clan_requestDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Clan_request> getByPage(int page, int size, String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Clan_request> getByPage(final Clan_requestDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan_request> result = newList();
            List<Clan_request> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Clan_requestDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Clan_requestDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByPcid(Integer pcid) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static int countByPcid(Clan_requestDAO DAO, Integer pcid) {
        return countByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static int countByPcid(Integer pcid, String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcid(DAO, pcid, TABLENAME2);
    }

    public static int countByPcid(final Clan_requestDAO DAO, final Integer pcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByPcid(pcid, TABLENAME2);
        }
        List<Clan_request> clan_requests = getByPcid(DAO, pcid, TABLENAME2);
        return clan_requests.size();
    }

    public static List<Clan_request> getByPcid(Integer pcid) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Clan_request> getByPcid(Clan_requestDAO DAO, Integer pcid) {
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Clan_request> getByPcid(Integer pcid, String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, TABLENAME2);
    }

    public static List<Clan_request> getByPcid(final Clan_requestDAO DAO, final Integer pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcid(pcid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Clan_request> result = newList();
            Set<Integer> m1 = varsByPcid.get(pcid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Clan_request e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _pcid = e.getPcid();
                if(_pcid != pcid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Clan_request getByCcidPcid(String ccid, Integer pcid) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCcidPcid(DAO, ccid, pcid, DAO.TABLENAME);
    }

    public static Clan_request getByCcidPcid(Clan_requestDAO DAO, String ccid, Integer pcid) {
        return getByCcidPcid(DAO, ccid, pcid, DAO.TABLENAME);
    }

    public static Clan_request getByCcidPcid(String ccid, Integer pcid, String TABLENAME2) {
        Clan_requestDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCcidPcid(DAO, ccid, pcid, TABLENAME2);
    }

    public static Clan_request getByCcidPcid(final Clan_requestDAO DAO, String ccid, Integer pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCcidPcid(ccid, pcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = ccid+"-"+pcid;
            Integer id = varsByCcidPcid.get(vkey);
            if(id == null) return null;
            Clan_request result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getCcid().equals(ccid)){
                varsByCcidPcid.remove(vkey);
                return null;
            }
            if(result.getPcid() != pcid){
                varsByCcidPcid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Clan_request update(Clan_request clan_request) {
        Clan_requestDAO DAO = DAO();
        return update(DAO, clan_request, DAO.TABLENAME);
    }

    public static Clan_request update(Clan_requestDAO DAO, Clan_request clan_request) {
        return update(DAO, clan_request, DAO.TABLENAME);
    }

    public static Clan_request update(Clan_request clan_request, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return update(DAO, clan_request, TABLENAME2);
    }

    public static Clan_request update(final Clan_requestDAO DAO, final Clan_request clan_request,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(clan_request, TABLENAME2);
            if(n == -1) 
                return clan_request;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(clan_request);
        }
        return clan_request;
    }

    public static Clan_request asynchronousUpdate(Clan_request clan_request) {
        Clan_requestDAO DAO = DAO();
        return asynchronousUpdate(DAO, clan_request, DAO.TABLENAME);
    }

    public static Clan_request asynchronousUpdate(Clan_requestDAO DAO, Clan_request clan_request) {
        return asynchronousUpdate(DAO, clan_request, DAO.TABLENAME);
    }

    public static Clan_request asynchronousUpdate(Clan_request clan_request, String TABLENAME2) {
        Clan_requestDAO DAO = DAO();
        return asynchronousUpdate(DAO, clan_request, TABLENAME2);
    }

    public static Clan_request asynchronousUpdate(final Clan_requestDAO DAO, final Clan_request clan_request,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(clan_request, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(clan_request);
        }
        return clan_request;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Clan_requestDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Clan_requestDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Clan_requestDAO DAO, final String TABLENAME2) {
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

        List<Clan_request> clan_requests = getAll();
        for (Clan_request clan_request : clan_requests) {
            int n = DAO.insert2(clan_request, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
