package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - player_producting
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Player_productingInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Player_productingDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Player_productingInternal(){}

    public static Player_productingDAO DAO(){
        return Player_productingEntity.Player_productingDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Player_producting[MAX];
    }
    private static Player_producting[] FIXED = new Player_producting[MAX];
    public static final Map<Integer, Player_producting> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByBcidPcid = newSortedMap();
    public static final Map<String, Integer> varsByGidBcidPcid = newSortedMap();

    private static final List<Player_producting> fixedCache = newList();

    public static void put(Player_producting player_producting){
        if(player_producting == null || player_producting.id <= 0) return ;

        int id = player_producting.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(player_producting))
                	FIXED[id - 1] = player_producting;
                if (!fixedCache.contains(player_producting))
                	fixedCache.add(player_producting);
            }
        } else {
            vars.put(id, player_producting);
        }

        { // building_id
            int vbcid = player_producting.getBcid();
            int vpcid = player_producting.getPcid();
            String vkey = vbcid+ "-" +vpcid;
            Set m1 = varsByBcidPcid.get(vkey);
            if(m1 == null){
                m1 = newSortedSet();
                varsByBcidPcid.put(vkey, m1);
            }
            m1.add(id);
        }

        { // gid
            int vgid = player_producting.getGid();
            int vbcid = player_producting.getBcid();
            int vpcid = player_producting.getPcid();
            String vkey = vgid + "-" + vbcid + "-" + vpcid;
            varsByGidBcidPcid.put(vkey, id);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByBcidPcid.clear();
        varsByGidBcidPcid.clear();
        FIXED = new Player_producting[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Player_productingDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Player_productingDAO DAO, String TABLENAME2){
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

    public static void relocate(Player_productingDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Player_productingDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Player_productingDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Player_productingDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Player_productingDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Player_productingDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Player_productingDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Player_productingDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Player_productingDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Player_productingDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Player_productingDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Player_productingDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Player_productingDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Player_producting[maxId(DAO)];

        List<Player_producting> player_productings = DAO.selectAll();
        for (Player_producting player_producting : player_productings) {
            player_producting.reset();
            put(player_producting);
        }
    }

    public static Map toMap(Player_producting player_producting){
        return player_producting.toMap();
    }

    public static List<Map> toMap(List<Player_producting> player_productings){
        List<Map> ret = new Vector<Map>();
        for (Player_producting player_producting : player_productings){
            Map e = player_producting.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Player_producting> sortZh(List<Player_producting> player_productings, final String key) {
        Collections.sort(player_productings, new Comparator<Player_producting>() {
            public int compare(Player_producting o1, Player_producting o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return player_productings;
    }

    public static List<Player_producting> sort(List<Player_producting> player_productings, final String key) {
        Collections.sort(player_productings, new Comparator<Player_producting>() {
            public int compare(Player_producting o1, Player_producting o2) {
                return o1.compareTo(o2, key);
            }
        });
        return player_productings;
    }

    public static List<Player_producting> sort(List<Player_producting> player_productings){
        Collections.sort(player_productings, new Comparator<Player_producting>(){
            public int compare(Player_producting o1, Player_producting o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return player_productings;
    }

    public static List<Player_producting> sortReverse(List<Player_producting> player_productings){
        Collections.sort(player_productings, new Comparator<Player_producting>(){
            public int compare(Player_producting o1, Player_producting o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return player_productings;
    }

    public static Player_producting insert(Player_producting player_producting) {
        Player_productingDAO DAO = DAO();
        return insert(DAO, player_producting, DAO.TABLENAME);
    }

    public static Player_producting insert(Player_productingDAO DAO, Player_producting player_producting) {
        return insert(DAO, player_producting, DAO.TABLENAME);
    }

    public static Player_producting insert(Player_producting player_producting, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return insert(DAO, player_producting, TABLENAME2);
    }

    public static Player_producting insert(Player_productingDAO DAO, Player_producting player_producting, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(player_producting, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_producting.id = n;
        if(cacheModel != NO_CACHE) put(player_producting);

        return player_producting;
    }

    public static Player_producting asynchronousInsert(Player_producting player_producting) {
        Player_productingDAO DAO = DAO();
        return asynchronousInsert(DAO, player_producting, DAO.TABLENAME);
    }
    public static Player_producting asynchronousInsert(Player_productingDAO DAO, Player_producting player_producting) {
        return asynchronousInsert(DAO, player_producting, DAO.TABLENAME);
    }
    public static Player_producting asynchronousInsert(Player_producting player_producting, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return asynchronousInsert(DAO, player_producting, TABLENAME2);
    }
    public static Player_producting asynchronousInsert(Player_productingDAO DAO, Player_producting player_producting, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(player_producting, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        player_producting.id = n;
        if(cacheModel != NO_CACHE) put(player_producting);
        return player_producting;
    }
    public static Player_producting insert2(Player_producting player_producting) {
        Player_productingDAO DAO = DAO();
        return insert2(DAO, player_producting, DAO.TABLENAME);
    }

    public static Player_producting insert2(Player_productingDAO DAO, Player_producting player_producting) {
        return insert2(DAO, player_producting, DAO.TABLENAME);
    }

    public static Player_producting insert2(Player_producting player_producting, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return insert2(DAO, player_producting, TABLENAME2);
    }

    public static Player_producting insert2(Player_productingDAO DAO, Player_producting player_producting, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(player_producting, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_producting.id = n;
        if(cacheModel != NO_CACHE) put(player_producting);

        return player_producting;
    }

    public static Player_producting asynchronousInsert2(Player_producting player_producting) {
        Player_productingDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_producting, DAO.TABLENAME);
    }
    public static Player_producting asynchronousInsert2(Player_productingDAO DAO, Player_producting player_producting) {
        return asynchronousInsert2(DAO, player_producting, DAO.TABLENAME);
    }
    public static Player_producting asynchronousInsert2(Player_producting player_producting, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_producting, TABLENAME2);
    }
    public static Player_producting asynchronousInsert2(Player_productingDAO DAO, Player_producting player_producting, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        player_producting.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(player_producting, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(player_producting);
        return player_producting;
    }
    public static int[] insert(List<Player_producting> player_productings) {
        Player_productingDAO DAO = DAO();
        return insert(DAO, player_productings, DAO.TABLENAME);
    }

    public static int[] insert(Player_productingDAO DAO, List<Player_producting> player_productings) {
        return insert(DAO, player_productings, DAO.TABLENAME);
    }

    public static int[] insert(List<Player_producting> player_productings, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return insert(DAO, player_productings, TABLENAME2);
    }

    public static int[] insert(Player_productingDAO DAO, List<Player_producting> player_productings, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(player_productings, TABLENAME2);
            int n = 0;
            for(Player_producting player_producting : player_productings){
                player_producting.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[player_productings.size()];
        int n = 0;
        for(Player_producting player_producting : player_productings){
            player_producting = insert(DAO, player_producting, TABLENAME2);
            ret[n++] = (player_producting == null) ? 0 : (int)player_producting.id;
        }
        return ret;
    }

    public static int delete(Player_producting player_producting) {
        int id = player_producting.id;
        Player_productingDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Player_productingDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Player_productingDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Player_productingDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Player_producting player_producting) {
        int id = player_producting.id;
        Player_productingDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Player_productingDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Player_productingDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Player_productingDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Player_productingDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Player_productingDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Player_productingDAO DAO, int[] ids,String TABLENAME2) {
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
        Player_productingDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Player_productingDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Player_productingDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Player_producting> beans) {
        Player_productingDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_producting> beans, Player_productingDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_producting> beans, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Player_producting> beans, final Player_productingDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Player_producting player_producting : beans){
                int id = player_producting.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Player_producting> getAll() {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_producting> getAll(Player_productingDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_producting> getAll(String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Player_producting> getAll(final Player_productingDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_producting> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_producting> getNoSortAll() {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_producting> getNoSortAll(Player_productingDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_producting> getNoSortAll(String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Player_producting> getNoSortAll(final Player_productingDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Player_producting> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_producting> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Player_producting player_producting = FIXED[i];
                if (player_producting != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Player_producting> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Player_producting> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Player_productingDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Player_productingDAO DAO, final String TABLENAME2) {
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
        Player_productingDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Player_productingDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Player_productingDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Player_producting> getIn(List<Integer> keys) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_producting> getIn(List<Integer> keys, Player_productingDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_producting> getIn(List<Integer> keys, String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_producting> getIn(final List<Integer> keys, final Player_productingDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_producting> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_producting> getNoSortIn(List<Integer> keys) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_producting> getNoSortIn(List<Integer> keys, Player_productingDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_producting> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_producting> getNoSortIn(final List<Integer> keys, final Player_productingDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Player_producting> result = newList();
            for (int key : keys) {
                Player_producting player_producting = getByKeyFromMemory(key);
                if( player_producting == null ) continue;
                result.add(player_producting);
            }
            return result;
        }
    }

    public static List<Player_producting> getLast(int num) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_producting> getLast(Player_productingDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_producting> getLast(int num, String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Player_producting> getLast(final Player_productingDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Player_producting> result = newList();
            List<Player_producting> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Player_producting last() {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_producting last(Player_productingDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_producting last(String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Player_producting last(final Player_productingDAO DAO, final String TABLENAME2) {
        Player_producting result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Player_productingDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Player_productingDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Player_productingDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Player_producting> getGtKey(int id) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_producting> getGtKey(Player_productingDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_producting> getGtKey(int id, String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Player_producting> getGtKey(final Player_productingDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_producting> result = newList();
            List<Player_producting> player_productings = getAll();
            for (Player_producting player_producting : player_productings) {
                if(player_producting.id <= id) continue;
                result.add(player_producting);
            }
            return result;
        }
    }

    public static Player_producting getByKey(int id) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_producting getByKey(Player_productingDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_producting getByKey(int id, String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Player_producting getByKey(final Player_productingDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Player_producting getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Player_producting deleteFromMemory(final int id) {
        Player_producting player_producting;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            player_producting = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(player_producting);
        } else {
            player_producting = vars.remove(id);
        }
        if(player_producting == null) return null;

        { // building_id
            int vbcid = player_producting.getBcid();
            int vpcid = player_producting.getPcid();
            String vkey = vbcid+ "-" +vpcid;
            Set m1 = varsByBcidPcid.get(vkey);
            if(m1 != null)
                m1.remove(id);
        }

        { // gid
            int vgid = player_producting.getGid();
            int vbcid = player_producting.getBcid();
            int vpcid = player_producting.getPcid();
            String vkey = vgid + "-" + vbcid + "-" + vpcid;
            varsByGidBcidPcid.remove(vkey);
        }

        return player_producting;
    }

    public static List<Player_producting> getByPage(int page, int size) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_producting> getByPage(Player_productingDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_producting> getByPage(int page, int size, String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Player_producting> getByPage(final Player_productingDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_producting> result = newList();
            List<Player_producting> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Player_productingDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Player_productingDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByBcidPcid(Integer bcid, Integer pcid) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByBcidPcid(DAO, bcid, pcid, DAO.TABLENAME);
    }

    public static int countByBcidPcid(Player_productingDAO DAO, Integer bcid, Integer pcid) {
        return countByBcidPcid(DAO, bcid, pcid, DAO.TABLENAME);
    }

    public static int countByBcidPcid(Integer bcid, Integer pcid, String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByBcidPcid(DAO, bcid, pcid, TABLENAME2);
    }

    public static int countByBcidPcid(final Player_productingDAO DAO, Integer bcid, Integer pcid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByBcidPcid(bcid, pcid, TABLENAME2);
        }
        List<Player_producting> player_productings = getByBcidPcid(bcid, pcid, TABLENAME2);
        return player_productings.size();
    }

    public static List<Player_producting> getByBcidPcid(Integer bcid, Integer pcid) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByBcidPcid(DAO, bcid, pcid, DAO.TABLENAME);
    }

    public static List<Player_producting> getByBcidPcid(Player_productingDAO DAO, Integer bcid, Integer pcid) {
        return getByBcidPcid(DAO, bcid, pcid, DAO.TABLENAME);
    }

    public static List<Player_producting> getByBcidPcid(Integer bcid, Integer pcid, String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByBcidPcid(DAO, bcid, pcid, TABLENAME2);
    }

    public static List<Player_producting> getByBcidPcid(final Player_productingDAO DAO, Integer bcid, Integer pcid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByBcidPcid(bcid, pcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player_producting> result = newList();
            String vkey = bcid+"-"+pcid;
            Set<Integer> m1 = varsByBcidPcid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Player_producting e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _bcid = e.getBcid();
                int _pcid = e.getPcid();
                String _key = _bcid + "-" + _pcid;
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Player_producting getByGidBcidPcid(Integer gid, Integer bcid, Integer pcid) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByGidBcidPcid(DAO, gid, bcid, pcid, DAO.TABLENAME);
    }

    public static Player_producting getByGidBcidPcid(Player_productingDAO DAO, Integer gid, Integer bcid, Integer pcid) {
        return getByGidBcidPcid(DAO, gid, bcid, pcid, DAO.TABLENAME);
    }

    public static Player_producting getByGidBcidPcid(Integer gid, Integer bcid, Integer pcid, String TABLENAME2) {
        Player_productingDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByGidBcidPcid(DAO, gid, bcid, pcid, TABLENAME2);
    }

    public static Player_producting getByGidBcidPcid(final Player_productingDAO DAO, Integer gid, Integer bcid, Integer pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByGidBcidPcid(gid, bcid, pcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = gid+"-"+bcid+"-"+pcid;
            Integer id = varsByGidBcidPcid.get(vkey);
            if(id == null) return null;
            Player_producting result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getGid() != gid){
                varsByGidBcidPcid.remove(vkey);
                return null;
            }
            if(result.getBcid() != bcid){
                varsByGidBcidPcid.remove(vkey);
                return null;
            }
            if(result.getPcid() != pcid){
                varsByGidBcidPcid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Player_producting update(Player_producting player_producting) {
        Player_productingDAO DAO = DAO();
        return update(DAO, player_producting, DAO.TABLENAME);
    }

    public static Player_producting update(Player_productingDAO DAO, Player_producting player_producting) {
        return update(DAO, player_producting, DAO.TABLENAME);
    }

    public static Player_producting update(Player_producting player_producting, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return update(DAO, player_producting, TABLENAME2);
    }

    public static Player_producting update(final Player_productingDAO DAO, final Player_producting player_producting,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(player_producting, TABLENAME2);
            if(n == -1) 
                return player_producting;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(player_producting);
        }
        return player_producting;
    }

    public static Player_producting asynchronousUpdate(Player_producting player_producting) {
        Player_productingDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_producting, DAO.TABLENAME);
    }

    public static Player_producting asynchronousUpdate(Player_productingDAO DAO, Player_producting player_producting) {
        return asynchronousUpdate(DAO, player_producting, DAO.TABLENAME);
    }

    public static Player_producting asynchronousUpdate(Player_producting player_producting, String TABLENAME2) {
        Player_productingDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_producting, TABLENAME2);
    }

    public static Player_producting asynchronousUpdate(final Player_productingDAO DAO, final Player_producting player_producting,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(player_producting, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(player_producting);
        }
        return player_producting;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Player_productingDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Player_productingDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Player_productingDAO DAO, final String TABLENAME2) {
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

        List<Player_producting> player_productings = getAll();
        for (Player_producting player_producting : player_productings) {
            int n = DAO.insert2(player_producting, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
