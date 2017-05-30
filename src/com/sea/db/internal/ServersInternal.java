package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - servers
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class ServersInternal extends InternalSupport{
    static Log log = LogFactory.getLog(ServersDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public ServersInternal(){}

    public static ServersDAO DAO(){
        return ServersEntity.ServersDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Servers[MAX];
    }
    private static Servers[] FIXED = new Servers[MAX];
    public static final Map<Integer, Servers> vars = newSortedMap();

    private static final List<Servers> fixedCache = newList();

    public static void put(Servers servers){
        if(servers == null || servers.id <= 0) return ;

        int id = servers.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(servers))
                	FIXED[id - 1] = servers;
                if (!fixedCache.contains(servers))
                	fixedCache.add(servers);
            }
        } else {
            vars.put(id, servers);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        FIXED = new Servers[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(ServersDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(ServersDAO DAO, String TABLENAME2){
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

    public static void relocate(ServersDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        ServersDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(ServersDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        ServersDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(ServersDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        ServersDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(ServersDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        ServersDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(ServersDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(ServersDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        ServersDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(ServersDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(ServersDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        ServersDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(ServersDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Servers[maxId(DAO)];

        List<Servers> serverss = DAO.selectAll();
        for (Servers servers : serverss) {
            servers.reset();
            put(servers);
        }
    }

    public static Map toMap(Servers servers){
        return servers.toMap();
    }

    public static List<Map> toMap(List<Servers> serverss){
        List<Map> ret = new Vector<Map>();
        for (Servers servers : serverss){
            Map e = servers.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Servers> sortZh(List<Servers> serverss, final String key) {
        Collections.sort(serverss, new Comparator<Servers>() {
            public int compare(Servers o1, Servers o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return serverss;
    }

    public static List<Servers> sort(List<Servers> serverss, final String key) {
        Collections.sort(serverss, new Comparator<Servers>() {
            public int compare(Servers o1, Servers o2) {
                return o1.compareTo(o2, key);
            }
        });
        return serverss;
    }

    public static List<Servers> sort(List<Servers> serverss){
        Collections.sort(serverss, new Comparator<Servers>(){
            public int compare(Servers o1, Servers o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return serverss;
    }

    public static List<Servers> sortReverse(List<Servers> serverss){
        Collections.sort(serverss, new Comparator<Servers>(){
            public int compare(Servers o1, Servers o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return serverss;
    }

    public static Servers insert(Servers servers) {
        ServersDAO DAO = DAO();
        return insert(DAO, servers, DAO.TABLENAME);
    }

    public static Servers insert(ServersDAO DAO, Servers servers) {
        return insert(DAO, servers, DAO.TABLENAME);
    }

    public static Servers insert(Servers servers, String TABLENAME2) {
        ServersDAO DAO = DAO();
        return insert(DAO, servers, TABLENAME2);
    }

    public static Servers insert(ServersDAO DAO, Servers servers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(servers, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        servers.id = n;
        if(cacheModel != NO_CACHE) put(servers);

        return servers;
    }

    public static Servers asynchronousInsert(Servers servers) {
        ServersDAO DAO = DAO();
        return asynchronousInsert(DAO, servers, DAO.TABLENAME);
    }
    public static Servers asynchronousInsert(ServersDAO DAO, Servers servers) {
        return asynchronousInsert(DAO, servers, DAO.TABLENAME);
    }
    public static Servers asynchronousInsert(Servers servers, String TABLENAME2) {
        ServersDAO DAO = DAO();
        return asynchronousInsert(DAO, servers, TABLENAME2);
    }
    public static Servers asynchronousInsert(ServersDAO DAO, Servers servers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(servers, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        servers.id = n;
        if(cacheModel != NO_CACHE) put(servers);
        return servers;
    }
    public static Servers insert2(Servers servers) {
        ServersDAO DAO = DAO();
        return insert2(DAO, servers, DAO.TABLENAME);
    }

    public static Servers insert2(ServersDAO DAO, Servers servers) {
        return insert2(DAO, servers, DAO.TABLENAME);
    }

    public static Servers insert2(Servers servers, String TABLENAME2) {
        ServersDAO DAO = DAO();
        return insert2(DAO, servers, TABLENAME2);
    }

    public static Servers insert2(ServersDAO DAO, Servers servers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(servers, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        servers.id = n;
        if(cacheModel != NO_CACHE) put(servers);

        return servers;
    }

    public static Servers asynchronousInsert2(Servers servers) {
        ServersDAO DAO = DAO();
        return asynchronousInsert2(DAO, servers, DAO.TABLENAME);
    }
    public static Servers asynchronousInsert2(ServersDAO DAO, Servers servers) {
        return asynchronousInsert2(DAO, servers, DAO.TABLENAME);
    }
    public static Servers asynchronousInsert2(Servers servers, String TABLENAME2) {
        ServersDAO DAO = DAO();
        return asynchronousInsert2(DAO, servers, TABLENAME2);
    }
    public static Servers asynchronousInsert2(ServersDAO DAO, Servers servers, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        servers.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(servers, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(servers);
        return servers;
    }
    public static int[] insert(List<Servers> serverss) {
        ServersDAO DAO = DAO();
        return insert(DAO, serverss, DAO.TABLENAME);
    }

    public static int[] insert(ServersDAO DAO, List<Servers> serverss) {
        return insert(DAO, serverss, DAO.TABLENAME);
    }

    public static int[] insert(List<Servers> serverss, String TABLENAME2) {
        ServersDAO DAO = DAO();
        return insert(DAO, serverss, TABLENAME2);
    }

    public static int[] insert(ServersDAO DAO, List<Servers> serverss, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(serverss, TABLENAME2);
            int n = 0;
            for(Servers servers : serverss){
                servers.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[serverss.size()];
        int n = 0;
        for(Servers servers : serverss){
            servers = insert(DAO, servers, TABLENAME2);
            ret[n++] = (servers == null) ? 0 : (int)servers.id;
        }
        return ret;
    }

    public static int delete(Servers servers) {
        int id = servers.id;
        ServersDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        ServersDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(ServersDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        ServersDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(ServersDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Servers servers) {
        int id = servers.id;
        ServersDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        ServersDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(ServersDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        ServersDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(ServersDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        ServersDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(ServersDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        ServersDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(ServersDAO DAO, int[] ids,String TABLENAME2) {
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
        ServersDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, ServersDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        ServersDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final ServersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Servers> beans) {
        ServersDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Servers> beans, ServersDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Servers> beans, String TABLENAME2) {
        ServersDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Servers> beans, final ServersDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Servers servers : beans){
                int id = servers.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Servers> getAll() {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Servers> getAll(ServersDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Servers> getAll(String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Servers> getAll(final ServersDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Servers> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Servers> getNoSortAll() {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Servers> getNoSortAll(ServersDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Servers> getNoSortAll(String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Servers> getNoSortAll(final ServersDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Servers> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Servers> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Servers servers = FIXED[i];
                if (servers != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Servers> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Servers> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(ServersDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final ServersDAO DAO, final String TABLENAME2) {
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
        ServersDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(ServersDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        ServersDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final ServersDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Servers> getIn(List<Integer> keys) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Servers> getIn(List<Integer> keys, ServersDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Servers> getIn(List<Integer> keys, String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Servers> getIn(final List<Integer> keys, final ServersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Servers> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Servers> getNoSortIn(List<Integer> keys) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Servers> getNoSortIn(List<Integer> keys, ServersDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Servers> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Servers> getNoSortIn(final List<Integer> keys, final ServersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Servers> result = newList();
            for (int key : keys) {
                Servers servers = getByKeyFromMemory(key);
                if( servers == null ) continue;
                result.add(servers);
            }
            return result;
        }
    }

    public static List<Servers> getLast(int num) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Servers> getLast(ServersDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Servers> getLast(int num, String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Servers> getLast(final ServersDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Servers> result = newList();
            List<Servers> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Servers last() {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Servers last(ServersDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Servers last(String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Servers last(final ServersDAO DAO, final String TABLENAME2) {
        Servers result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        ServersDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(ServersDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        ServersDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final ServersDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Servers> getGtKey(int id) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Servers> getGtKey(ServersDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Servers> getGtKey(int id, String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Servers> getGtKey(final ServersDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Servers> result = newList();
            List<Servers> serverss = getAll();
            for (Servers servers : serverss) {
                if(servers.id <= id) continue;
                result.add(servers);
            }
            return result;
        }
    }

    public static Servers getByKey(int id) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Servers getByKey(ServersDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Servers getByKey(int id, String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Servers getByKey(final ServersDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Servers getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Servers deleteFromMemory(final int id) {
        Servers servers;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            servers = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(servers);
        } else {
            servers = vars.remove(id);
        }
        if(servers == null) return null;

        return servers;
    }

    public static List<Servers> getByPage(int page, int size) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Servers> getByPage(ServersDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Servers> getByPage(int page, int size, String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Servers> getByPage(final ServersDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Servers> result = newList();
            List<Servers> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(ServersDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        ServersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final ServersDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Servers update(Servers servers) {
        ServersDAO DAO = DAO();
        return update(DAO, servers, DAO.TABLENAME);
    }

    public static Servers update(ServersDAO DAO, Servers servers) {
        return update(DAO, servers, DAO.TABLENAME);
    }

    public static Servers update(Servers servers, String TABLENAME2) {
        ServersDAO DAO = DAO();
        return update(DAO, servers, TABLENAME2);
    }

    public static Servers update(final ServersDAO DAO, final Servers servers,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(servers, TABLENAME2);
            if(n == -1) 
                return servers;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(servers);
        }
        return servers;
    }

    public static Servers asynchronousUpdate(Servers servers) {
        ServersDAO DAO = DAO();
        return asynchronousUpdate(DAO, servers, DAO.TABLENAME);
    }

    public static Servers asynchronousUpdate(ServersDAO DAO, Servers servers) {
        return asynchronousUpdate(DAO, servers, DAO.TABLENAME);
    }

    public static Servers asynchronousUpdate(Servers servers, String TABLENAME2) {
        ServersDAO DAO = DAO();
        return asynchronousUpdate(DAO, servers, TABLENAME2);
    }

    public static Servers asynchronousUpdate(final ServersDAO DAO, final Servers servers,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(servers, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(servers);
        }
        return servers;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        ServersDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(ServersDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final ServersDAO DAO, final String TABLENAME2) {
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

        List<Servers> serverss = getAll();
        for (Servers servers : serverss) {
            int n = DAO.insert2(servers, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
