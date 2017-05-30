package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - player_buildings
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Player_buildingsInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Player_buildingsDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Player_buildingsInternal(){}

    public static Player_buildingsDAO DAO(){
        return Player_buildingsEntity.Player_buildingsDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Player_buildings[MAX];
    }
    private static Player_buildings[] FIXED = new Player_buildings[MAX];
    public static final Map<Integer, Player_buildings> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByPcidGid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByPcid = newSortedMap();
    public static final Map<String, Integer> varsByBcidPcid = newSortedMap();

    private static final List<Player_buildings> fixedCache = newList();

    public static void put(Player_buildings player_buildings){
        if(player_buildings == null || player_buildings.id <= 0) return ;

        int id = player_buildings.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(player_buildings))
                	FIXED[id - 1] = player_buildings;
                if (!fixedCache.contains(player_buildings))
                	fixedCache.add(player_buildings);
            }
        } else {
            vars.put(id, player_buildings);
        }

        { // player_id_gid
            int vpcid = player_buildings.getPcid();
            int vgid = player_buildings.getGid();
            String vkey = vpcid+ "-" +vgid;
            Set m1 = varsByPcidGid.get(vkey);
            if(m1 == null){
                m1 = newSortedSet();
                varsByPcidGid.put(vkey, m1);
            }
            m1.add(id);
        }

        int pcid = player_buildings.getPcid();
        Set m2 = varsByPcid.get(pcid);
        if(m2 == null){
            m2 = newSortedSet();
            varsByPcid.put(pcid, m2);
        }
        m2.add(id);

        { // client_id
            int vbcid = player_buildings.getBcid();
            int vpcid = player_buildings.getPcid();
            String vkey = vbcid + "-" + vpcid;
            varsByBcidPcid.put(vkey, id);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByPcidGid.clear();
        varsByPcid.clear();
        varsByBcidPcid.clear();
        FIXED = new Player_buildings[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Player_buildingsDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Player_buildingsDAO DAO, String TABLENAME2){
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

    public static void relocate(Player_buildingsDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Player_buildingsDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Player_buildingsDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Player_buildingsDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Player_buildingsDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Player_buildingsDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Player_buildingsDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Player_buildingsDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Player_buildingsDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Player_buildingsDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Player_buildingsDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Player_buildingsDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Player_buildingsDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Player_buildings[maxId(DAO)];

        List<Player_buildings> player_buildingss = DAO.selectAll();
        for (Player_buildings player_buildings : player_buildingss) {
            player_buildings.reset();
            put(player_buildings);
        }
    }

    public static Map toMap(Player_buildings player_buildings){
        return player_buildings.toMap();
    }

    public static List<Map> toMap(List<Player_buildings> player_buildingss){
        List<Map> ret = new Vector<Map>();
        for (Player_buildings player_buildings : player_buildingss){
            Map e = player_buildings.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Player_buildings> sortZh(List<Player_buildings> player_buildingss, final String key) {
        Collections.sort(player_buildingss, new Comparator<Player_buildings>() {
            public int compare(Player_buildings o1, Player_buildings o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return player_buildingss;
    }

    public static List<Player_buildings> sort(List<Player_buildings> player_buildingss, final String key) {
        Collections.sort(player_buildingss, new Comparator<Player_buildings>() {
            public int compare(Player_buildings o1, Player_buildings o2) {
                return o1.compareTo(o2, key);
            }
        });
        return player_buildingss;
    }

    public static List<Player_buildings> sort(List<Player_buildings> player_buildingss){
        Collections.sort(player_buildingss, new Comparator<Player_buildings>(){
            public int compare(Player_buildings o1, Player_buildings o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return player_buildingss;
    }

    public static List<Player_buildings> sortReverse(List<Player_buildings> player_buildingss){
        Collections.sort(player_buildingss, new Comparator<Player_buildings>(){
            public int compare(Player_buildings o1, Player_buildings o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return player_buildingss;
    }

    public static List<Player_buildings> sortPcid(List<Player_buildings> player_buildingss){
        Collections.sort(player_buildingss, new Comparator<Player_buildings>(){
            public int compare(Player_buildings o1, Player_buildings o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return compareTo(v1, v2);
            }
        });
        return player_buildingss;
    }

    public static List<Player_buildings> sortPcidRo(List<Player_buildings> player_buildingss){
        Collections.sort(player_buildingss, new Comparator<Player_buildings>(){
            public int compare(Player_buildings o1, Player_buildings o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return player_buildingss;
    }

    public static Player_buildings insert(Player_buildings player_buildings) {
        Player_buildingsDAO DAO = DAO();
        return insert(DAO, player_buildings, DAO.TABLENAME);
    }

    public static Player_buildings insert(Player_buildingsDAO DAO, Player_buildings player_buildings) {
        return insert(DAO, player_buildings, DAO.TABLENAME);
    }

    public static Player_buildings insert(Player_buildings player_buildings, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return insert(DAO, player_buildings, TABLENAME2);
    }

    public static Player_buildings insert(Player_buildingsDAO DAO, Player_buildings player_buildings, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(player_buildings, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_buildings.id = n;
        if(cacheModel != NO_CACHE) put(player_buildings);

        return player_buildings;
    }

    public static Player_buildings asynchronousInsert(Player_buildings player_buildings) {
        Player_buildingsDAO DAO = DAO();
        return asynchronousInsert(DAO, player_buildings, DAO.TABLENAME);
    }
    public static Player_buildings asynchronousInsert(Player_buildingsDAO DAO, Player_buildings player_buildings) {
        return asynchronousInsert(DAO, player_buildings, DAO.TABLENAME);
    }
    public static Player_buildings asynchronousInsert(Player_buildings player_buildings, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return asynchronousInsert(DAO, player_buildings, TABLENAME2);
    }
    public static Player_buildings asynchronousInsert(Player_buildingsDAO DAO, Player_buildings player_buildings, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(player_buildings, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        player_buildings.id = n;
        if(cacheModel != NO_CACHE) put(player_buildings);
        return player_buildings;
    }
    public static Player_buildings insert2(Player_buildings player_buildings) {
        Player_buildingsDAO DAO = DAO();
        return insert2(DAO, player_buildings, DAO.TABLENAME);
    }

    public static Player_buildings insert2(Player_buildingsDAO DAO, Player_buildings player_buildings) {
        return insert2(DAO, player_buildings, DAO.TABLENAME);
    }

    public static Player_buildings insert2(Player_buildings player_buildings, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return insert2(DAO, player_buildings, TABLENAME2);
    }

    public static Player_buildings insert2(Player_buildingsDAO DAO, Player_buildings player_buildings, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(player_buildings, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_buildings.id = n;
        if(cacheModel != NO_CACHE) put(player_buildings);

        return player_buildings;
    }

    public static Player_buildings asynchronousInsert2(Player_buildings player_buildings) {
        Player_buildingsDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_buildings, DAO.TABLENAME);
    }
    public static Player_buildings asynchronousInsert2(Player_buildingsDAO DAO, Player_buildings player_buildings) {
        return asynchronousInsert2(DAO, player_buildings, DAO.TABLENAME);
    }
    public static Player_buildings asynchronousInsert2(Player_buildings player_buildings, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_buildings, TABLENAME2);
    }
    public static Player_buildings asynchronousInsert2(Player_buildingsDAO DAO, Player_buildings player_buildings, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        player_buildings.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(player_buildings, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(player_buildings);
        return player_buildings;
    }
    public static int[] insert(List<Player_buildings> player_buildingss) {
        Player_buildingsDAO DAO = DAO();
        return insert(DAO, player_buildingss, DAO.TABLENAME);
    }

    public static int[] insert(Player_buildingsDAO DAO, List<Player_buildings> player_buildingss) {
        return insert(DAO, player_buildingss, DAO.TABLENAME);
    }

    public static int[] insert(List<Player_buildings> player_buildingss, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return insert(DAO, player_buildingss, TABLENAME2);
    }

    public static int[] insert(Player_buildingsDAO DAO, List<Player_buildings> player_buildingss, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(player_buildingss, TABLENAME2);
            int n = 0;
            for(Player_buildings player_buildings : player_buildingss){
                player_buildings.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[player_buildingss.size()];
        int n = 0;
        for(Player_buildings player_buildings : player_buildingss){
            player_buildings = insert(DAO, player_buildings, TABLENAME2);
            ret[n++] = (player_buildings == null) ? 0 : (int)player_buildings.id;
        }
        return ret;
    }

    public static int delete(Player_buildings player_buildings) {
        int id = player_buildings.id;
        Player_buildingsDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Player_buildingsDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Player_buildingsDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Player_buildingsDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Player_buildings player_buildings) {
        int id = player_buildings.id;
        Player_buildingsDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Player_buildingsDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Player_buildingsDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Player_buildingsDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Player_buildingsDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Player_buildingsDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Player_buildingsDAO DAO, int[] ids,String TABLENAME2) {
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
        Player_buildingsDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Player_buildingsDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Player_buildingsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Player_buildings> beans) {
        Player_buildingsDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_buildings> beans, Player_buildingsDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_buildings> beans, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Player_buildings> beans, final Player_buildingsDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Player_buildings player_buildings : beans){
                int id = player_buildings.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Player_buildings> getAll() {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_buildings> getAll(Player_buildingsDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_buildings> getAll(String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Player_buildings> getAll(final Player_buildingsDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_buildings> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_buildings> getNoSortAll() {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_buildings> getNoSortAll(Player_buildingsDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_buildings> getNoSortAll(String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Player_buildings> getNoSortAll(final Player_buildingsDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Player_buildings> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_buildings> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Player_buildings player_buildings = FIXED[i];
                if (player_buildings != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Player_buildings> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Player_buildings> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Player_buildingsDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Player_buildingsDAO DAO, final String TABLENAME2) {
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
        Player_buildingsDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Player_buildingsDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Player_buildingsDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Player_buildings> getIn(List<Integer> keys) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_buildings> getIn(List<Integer> keys, Player_buildingsDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_buildings> getIn(List<Integer> keys, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_buildings> getIn(final List<Integer> keys, final Player_buildingsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_buildings> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_buildings> getNoSortIn(List<Integer> keys) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_buildings> getNoSortIn(List<Integer> keys, Player_buildingsDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_buildings> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_buildings> getNoSortIn(final List<Integer> keys, final Player_buildingsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Player_buildings> result = newList();
            for (int key : keys) {
                Player_buildings player_buildings = getByKeyFromMemory(key);
                if( player_buildings == null ) continue;
                result.add(player_buildings);
            }
            return result;
        }
    }

    public static List<Player_buildings> getLast(int num) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_buildings> getLast(Player_buildingsDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_buildings> getLast(int num, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Player_buildings> getLast(final Player_buildingsDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Player_buildings> result = newList();
            List<Player_buildings> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Player_buildings last() {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_buildings last(Player_buildingsDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_buildings last(String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Player_buildings last(final Player_buildingsDAO DAO, final String TABLENAME2) {
        Player_buildings result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Player_buildingsDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Player_buildingsDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Player_buildingsDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Player_buildings> getGtKey(int id) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_buildings> getGtKey(Player_buildingsDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_buildings> getGtKey(int id, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Player_buildings> getGtKey(final Player_buildingsDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_buildings> result = newList();
            List<Player_buildings> player_buildingss = getAll();
            for (Player_buildings player_buildings : player_buildingss) {
                if(player_buildings.id <= id) continue;
                result.add(player_buildings);
            }
            return result;
        }
    }

    public static Player_buildings getByKey(int id) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_buildings getByKey(Player_buildingsDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_buildings getByKey(int id, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Player_buildings getByKey(final Player_buildingsDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Player_buildings getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Player_buildings deleteFromMemory(final int id) {
        Player_buildings player_buildings;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            player_buildings = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(player_buildings);
        } else {
            player_buildings = vars.remove(id);
        }
        if(player_buildings == null) return null;

        { // player_id_gid
            int vpcid = player_buildings.getPcid();
            int vgid = player_buildings.getGid();
            String vkey = vpcid+ "-" +vgid;
            Set m1 = varsByPcidGid.get(vkey);
            if(m1 != null)
                m1.remove(id);
        }

        int pcid = player_buildings.getPcid();
        Set m2 = varsByPcid.get(pcid);
        if(m2 != null)
            m2.remove(id);

        { // client_id
            int vbcid = player_buildings.getBcid();
            int vpcid = player_buildings.getPcid();
            String vkey = vbcid + "-" + vpcid;
            varsByBcidPcid.remove(vkey);
        }

        return player_buildings;
    }

    public static List<Player_buildings> getByPage(int page, int size) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_buildings> getByPage(Player_buildingsDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_buildings> getByPage(int page, int size, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Player_buildings> getByPage(final Player_buildingsDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_buildings> result = newList();
            List<Player_buildings> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Player_buildingsDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Player_buildingsDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByPcidGid(Integer pcid, Integer gid) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcidGid(DAO, pcid, gid, DAO.TABLENAME);
    }

    public static int countByPcidGid(Player_buildingsDAO DAO, Integer pcid, Integer gid) {
        return countByPcidGid(DAO, pcid, gid, DAO.TABLENAME);
    }

    public static int countByPcidGid(Integer pcid, Integer gid, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcidGid(DAO, pcid, gid, TABLENAME2);
    }

    public static int countByPcidGid(final Player_buildingsDAO DAO, Integer pcid, Integer gid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByPcidGid(pcid, gid, TABLENAME2);
        }
        List<Player_buildings> player_buildingss = getByPcidGid(pcid, gid, TABLENAME2);
        return player_buildingss.size();
    }

    public static List<Player_buildings> getByPcidGid(Integer pcid, Integer gid) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcidGid(DAO, pcid, gid, DAO.TABLENAME);
    }

    public static List<Player_buildings> getByPcidGid(Player_buildingsDAO DAO, Integer pcid, Integer gid) {
        return getByPcidGid(DAO, pcid, gid, DAO.TABLENAME);
    }

    public static List<Player_buildings> getByPcidGid(Integer pcid, Integer gid, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcidGid(DAO, pcid, gid, TABLENAME2);
    }

    public static List<Player_buildings> getByPcidGid(final Player_buildingsDAO DAO, Integer pcid, Integer gid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcidGid(pcid, gid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player_buildings> result = newList();
            String vkey = pcid+"-"+gid;
            Set<Integer> m1 = varsByPcidGid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Player_buildings e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _pcid = e.getPcid();
                int _gid = e.getGid();
                String _key = _pcid + "-" + _gid;
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByPcid(Integer pcid) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static int countByPcid(Player_buildingsDAO DAO, Integer pcid) {
        return countByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static int countByPcid(Integer pcid, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcid(DAO, pcid, TABLENAME2);
    }

    public static int countByPcid(final Player_buildingsDAO DAO, final Integer pcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByPcid(pcid, TABLENAME2);
        }
        List<Player_buildings> player_buildingss = getByPcid(DAO, pcid, TABLENAME2);
        return player_buildingss.size();
    }

    public static List<Player_buildings> getByPcid(Integer pcid) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Player_buildings> getByPcid(Player_buildingsDAO DAO, Integer pcid) {
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Player_buildings> getByPcid(Integer pcid, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, TABLENAME2);
    }

    public static List<Player_buildings> getByPcid(final Player_buildingsDAO DAO, final Integer pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcid(pcid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Player_buildings> result = newList();
            Set<Integer> m1 = varsByPcid.get(pcid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Player_buildings e = getByKey(DAO, key, TABLENAME2);
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

    public static Player_buildings getByBcidPcid(Integer bcid, Integer pcid) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByBcidPcid(DAO, bcid, pcid, DAO.TABLENAME);
    }

    public static Player_buildings getByBcidPcid(Player_buildingsDAO DAO, Integer bcid, Integer pcid) {
        return getByBcidPcid(DAO, bcid, pcid, DAO.TABLENAME);
    }

    public static Player_buildings getByBcidPcid(Integer bcid, Integer pcid, String TABLENAME2) {
        Player_buildingsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByBcidPcid(DAO, bcid, pcid, TABLENAME2);
    }

    public static Player_buildings getByBcidPcid(final Player_buildingsDAO DAO, Integer bcid, Integer pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByBcidPcid(bcid, pcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = bcid+"-"+pcid;
            Integer id = varsByBcidPcid.get(vkey);
            if(id == null) return null;
            Player_buildings result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getBcid() != bcid){
                varsByBcidPcid.remove(vkey);
                return null;
            }
            if(result.getPcid() != pcid){
                varsByBcidPcid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Player_buildings update(Player_buildings player_buildings) {
        Player_buildingsDAO DAO = DAO();
        return update(DAO, player_buildings, DAO.TABLENAME);
    }

    public static Player_buildings update(Player_buildingsDAO DAO, Player_buildings player_buildings) {
        return update(DAO, player_buildings, DAO.TABLENAME);
    }

    public static Player_buildings update(Player_buildings player_buildings, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return update(DAO, player_buildings, TABLENAME2);
    }

    public static Player_buildings update(final Player_buildingsDAO DAO, final Player_buildings player_buildings,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(player_buildings, TABLENAME2);
            if(n == -1) 
                return player_buildings;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(player_buildings);
        }
        return player_buildings;
    }

    public static Player_buildings asynchronousUpdate(Player_buildings player_buildings) {
        Player_buildingsDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_buildings, DAO.TABLENAME);
    }

    public static Player_buildings asynchronousUpdate(Player_buildingsDAO DAO, Player_buildings player_buildings) {
        return asynchronousUpdate(DAO, player_buildings, DAO.TABLENAME);
    }

    public static Player_buildings asynchronousUpdate(Player_buildings player_buildings, String TABLENAME2) {
        Player_buildingsDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_buildings, TABLENAME2);
    }

    public static Player_buildings asynchronousUpdate(final Player_buildingsDAO DAO, final Player_buildings player_buildings,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(player_buildings, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(player_buildings);
        }
        return player_buildings;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Player_buildingsDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Player_buildingsDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Player_buildingsDAO DAO, final String TABLENAME2) {
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

        List<Player_buildings> player_buildingss = getAll();
        for (Player_buildings player_buildings : player_buildingss) {
            int n = DAO.insert2(player_buildings, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
