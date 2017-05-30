package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - player_armys
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Player_armysInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Player_armysDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Player_armysInternal(){}

    public static Player_armysDAO DAO(){
        return Player_armysEntity.Player_armysDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Player_armys[MAX];
    }
    private static Player_armys[] FIXED = new Player_armys[MAX];
    public static final Map<Integer, Player_armys> vars = newSortedMap();
    public static final Map<String, Integer> varsByPcidGidBcid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByPcid = newSortedMap();

    private static final List<Player_armys> fixedCache = newList();

    public static void put(Player_armys player_armys){
        if(player_armys == null || player_armys.id <= 0) return ;

        int id = player_armys.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(player_armys))
                	FIXED[id - 1] = player_armys;
                if (!fixedCache.contains(player_armys))
                	fixedCache.add(player_armys);
            }
        } else {
            vars.put(id, player_armys);
        }

        { // player_id_2
            int vpcid = player_armys.getPcid();
            int vgid = player_armys.getGid();
            int vbcid = player_armys.getBcid();
            String vkey = vpcid + "-" + vgid + "-" + vbcid;
            varsByPcidGidBcid.put(vkey, id);
        }

        int pcid = player_armys.getPcid();
        Set m1 = varsByPcid.get(pcid);
        if(m1 == null){
            m1 = newSortedSet();
            varsByPcid.put(pcid, m1);
        }
        m1.add(id);

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByPcidGidBcid.clear();
        varsByPcid.clear();
        FIXED = new Player_armys[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Player_armysDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Player_armysDAO DAO, String TABLENAME2){
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

    public static void relocate(Player_armysDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Player_armysDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Player_armysDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Player_armysDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Player_armysDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Player_armysDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Player_armysDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Player_armysDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Player_armysDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Player_armysDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Player_armysDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Player_armysDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Player_armysDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Player_armys[maxId(DAO)];

        List<Player_armys> player_armyss = DAO.selectAll();
        for (Player_armys player_armys : player_armyss) {
            player_armys.reset();
            put(player_armys);
        }
    }

    public static Map toMap(Player_armys player_armys){
        return player_armys.toMap();
    }

    public static List<Map> toMap(List<Player_armys> player_armyss){
        List<Map> ret = new Vector<Map>();
        for (Player_armys player_armys : player_armyss){
            Map e = player_armys.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Player_armys> sortZh(List<Player_armys> player_armyss, final String key) {
        Collections.sort(player_armyss, new Comparator<Player_armys>() {
            public int compare(Player_armys o1, Player_armys o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return player_armyss;
    }

    public static List<Player_armys> sort(List<Player_armys> player_armyss, final String key) {
        Collections.sort(player_armyss, new Comparator<Player_armys>() {
            public int compare(Player_armys o1, Player_armys o2) {
                return o1.compareTo(o2, key);
            }
        });
        return player_armyss;
    }

    public static List<Player_armys> sort(List<Player_armys> player_armyss){
        Collections.sort(player_armyss, new Comparator<Player_armys>(){
            public int compare(Player_armys o1, Player_armys o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return player_armyss;
    }

    public static List<Player_armys> sortReverse(List<Player_armys> player_armyss){
        Collections.sort(player_armyss, new Comparator<Player_armys>(){
            public int compare(Player_armys o1, Player_armys o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return player_armyss;
    }

    public static List<Player_armys> sortPcid(List<Player_armys> player_armyss){
        Collections.sort(player_armyss, new Comparator<Player_armys>(){
            public int compare(Player_armys o1, Player_armys o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return compareTo(v1, v2);
            }
        });
        return player_armyss;
    }

    public static List<Player_armys> sortPcidRo(List<Player_armys> player_armyss){
        Collections.sort(player_armyss, new Comparator<Player_armys>(){
            public int compare(Player_armys o1, Player_armys o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return player_armyss;
    }

    public static Player_armys insert(Player_armys player_armys) {
        Player_armysDAO DAO = DAO();
        return insert(DAO, player_armys, DAO.TABLENAME);
    }

    public static Player_armys insert(Player_armysDAO DAO, Player_armys player_armys) {
        return insert(DAO, player_armys, DAO.TABLENAME);
    }

    public static Player_armys insert(Player_armys player_armys, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return insert(DAO, player_armys, TABLENAME2);
    }

    public static Player_armys insert(Player_armysDAO DAO, Player_armys player_armys, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(player_armys, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_armys.id = n;
        if(cacheModel != NO_CACHE) put(player_armys);

        return player_armys;
    }

    public static Player_armys asynchronousInsert(Player_armys player_armys) {
        Player_armysDAO DAO = DAO();
        return asynchronousInsert(DAO, player_armys, DAO.TABLENAME);
    }
    public static Player_armys asynchronousInsert(Player_armysDAO DAO, Player_armys player_armys) {
        return asynchronousInsert(DAO, player_armys, DAO.TABLENAME);
    }
    public static Player_armys asynchronousInsert(Player_armys player_armys, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return asynchronousInsert(DAO, player_armys, TABLENAME2);
    }
    public static Player_armys asynchronousInsert(Player_armysDAO DAO, Player_armys player_armys, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(player_armys, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        player_armys.id = n;
        if(cacheModel != NO_CACHE) put(player_armys);
        return player_armys;
    }
    public static Player_armys insert2(Player_armys player_armys) {
        Player_armysDAO DAO = DAO();
        return insert2(DAO, player_armys, DAO.TABLENAME);
    }

    public static Player_armys insert2(Player_armysDAO DAO, Player_armys player_armys) {
        return insert2(DAO, player_armys, DAO.TABLENAME);
    }

    public static Player_armys insert2(Player_armys player_armys, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return insert2(DAO, player_armys, TABLENAME2);
    }

    public static Player_armys insert2(Player_armysDAO DAO, Player_armys player_armys, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(player_armys, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_armys.id = n;
        if(cacheModel != NO_CACHE) put(player_armys);

        return player_armys;
    }

    public static Player_armys asynchronousInsert2(Player_armys player_armys) {
        Player_armysDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_armys, DAO.TABLENAME);
    }
    public static Player_armys asynchronousInsert2(Player_armysDAO DAO, Player_armys player_armys) {
        return asynchronousInsert2(DAO, player_armys, DAO.TABLENAME);
    }
    public static Player_armys asynchronousInsert2(Player_armys player_armys, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_armys, TABLENAME2);
    }
    public static Player_armys asynchronousInsert2(Player_armysDAO DAO, Player_armys player_armys, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        player_armys.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(player_armys, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(player_armys);
        return player_armys;
    }
    public static int[] insert(List<Player_armys> player_armyss) {
        Player_armysDAO DAO = DAO();
        return insert(DAO, player_armyss, DAO.TABLENAME);
    }

    public static int[] insert(Player_armysDAO DAO, List<Player_armys> player_armyss) {
        return insert(DAO, player_armyss, DAO.TABLENAME);
    }

    public static int[] insert(List<Player_armys> player_armyss, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return insert(DAO, player_armyss, TABLENAME2);
    }

    public static int[] insert(Player_armysDAO DAO, List<Player_armys> player_armyss, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(player_armyss, TABLENAME2);
            int n = 0;
            for(Player_armys player_armys : player_armyss){
                player_armys.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[player_armyss.size()];
        int n = 0;
        for(Player_armys player_armys : player_armyss){
            player_armys = insert(DAO, player_armys, TABLENAME2);
            ret[n++] = (player_armys == null) ? 0 : (int)player_armys.id;
        }
        return ret;
    }

    public static int delete(Player_armys player_armys) {
        int id = player_armys.id;
        Player_armysDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Player_armysDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Player_armysDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Player_armysDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Player_armys player_armys) {
        int id = player_armys.id;
        Player_armysDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Player_armysDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Player_armysDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Player_armysDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Player_armysDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Player_armysDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Player_armysDAO DAO, int[] ids,String TABLENAME2) {
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
        Player_armysDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Player_armysDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Player_armysDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Player_armys> beans) {
        Player_armysDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_armys> beans, Player_armysDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_armys> beans, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Player_armys> beans, final Player_armysDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Player_armys player_armys : beans){
                int id = player_armys.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Player_armys> getAll() {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_armys> getAll(Player_armysDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_armys> getAll(String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Player_armys> getAll(final Player_armysDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_armys> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_armys> getNoSortAll() {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_armys> getNoSortAll(Player_armysDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_armys> getNoSortAll(String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Player_armys> getNoSortAll(final Player_armysDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Player_armys> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_armys> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Player_armys player_armys = FIXED[i];
                if (player_armys != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Player_armys> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Player_armys> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Player_armysDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Player_armysDAO DAO, final String TABLENAME2) {
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
        Player_armysDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Player_armysDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Player_armysDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Player_armys> getIn(List<Integer> keys) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_armys> getIn(List<Integer> keys, Player_armysDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_armys> getIn(List<Integer> keys, String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_armys> getIn(final List<Integer> keys, final Player_armysDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_armys> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_armys> getNoSortIn(List<Integer> keys) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_armys> getNoSortIn(List<Integer> keys, Player_armysDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_armys> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_armys> getNoSortIn(final List<Integer> keys, final Player_armysDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Player_armys> result = newList();
            for (int key : keys) {
                Player_armys player_armys = getByKeyFromMemory(key);
                if( player_armys == null ) continue;
                result.add(player_armys);
            }
            return result;
        }
    }

    public static List<Player_armys> getLast(int num) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_armys> getLast(Player_armysDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_armys> getLast(int num, String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Player_armys> getLast(final Player_armysDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Player_armys> result = newList();
            List<Player_armys> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Player_armys last() {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_armys last(Player_armysDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_armys last(String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Player_armys last(final Player_armysDAO DAO, final String TABLENAME2) {
        Player_armys result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Player_armysDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Player_armysDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Player_armysDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Player_armys> getGtKey(int id) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_armys> getGtKey(Player_armysDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_armys> getGtKey(int id, String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Player_armys> getGtKey(final Player_armysDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_armys> result = newList();
            List<Player_armys> player_armyss = getAll();
            for (Player_armys player_armys : player_armyss) {
                if(player_armys.id <= id) continue;
                result.add(player_armys);
            }
            return result;
        }
    }

    public static Player_armys getByKey(int id) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_armys getByKey(Player_armysDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_armys getByKey(int id, String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Player_armys getByKey(final Player_armysDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Player_armys getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Player_armys deleteFromMemory(final int id) {
        Player_armys player_armys;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            player_armys = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(player_armys);
        } else {
            player_armys = vars.remove(id);
        }
        if(player_armys == null) return null;

        { // player_id_2
            int vpcid = player_armys.getPcid();
            int vgid = player_armys.getGid();
            int vbcid = player_armys.getBcid();
            String vkey = vpcid + "-" + vgid + "-" + vbcid;
            varsByPcidGidBcid.remove(vkey);
        }

        int pcid = player_armys.getPcid();
        Set m1 = varsByPcid.get(pcid);
        if(m1 != null)
            m1.remove(id);

        return player_armys;
    }

    public static List<Player_armys> getByPage(int page, int size) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_armys> getByPage(Player_armysDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_armys> getByPage(int page, int size, String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Player_armys> getByPage(final Player_armysDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_armys> result = newList();
            List<Player_armys> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Player_armysDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Player_armysDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Player_armys getByPcidGidBcid(Integer pcid, Integer gid, Integer bcid) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcidGidBcid(DAO, pcid, gid, bcid, DAO.TABLENAME);
    }

    public static Player_armys getByPcidGidBcid(Player_armysDAO DAO, Integer pcid, Integer gid, Integer bcid) {
        return getByPcidGidBcid(DAO, pcid, gid, bcid, DAO.TABLENAME);
    }

    public static Player_armys getByPcidGidBcid(Integer pcid, Integer gid, Integer bcid, String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcidGidBcid(DAO, pcid, gid, bcid, TABLENAME2);
    }

    public static Player_armys getByPcidGidBcid(final Player_armysDAO DAO, Integer pcid, Integer gid, Integer bcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcidGidBcid(pcid, gid, bcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = pcid+"-"+gid+"-"+bcid;
            Integer id = varsByPcidGidBcid.get(vkey);
            if(id == null) return null;
            Player_armys result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getPcid() != pcid){
                varsByPcidGidBcid.remove(vkey);
                return null;
            }
            if(result.getGid() != gid){
                varsByPcidGidBcid.remove(vkey);
                return null;
            }
            if(result.getBcid() != bcid){
                varsByPcidGidBcid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static int countByPcid(Integer pcid) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static int countByPcid(Player_armysDAO DAO, Integer pcid) {
        return countByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static int countByPcid(Integer pcid, String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcid(DAO, pcid, TABLENAME2);
    }

    public static int countByPcid(final Player_armysDAO DAO, final Integer pcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByPcid(pcid, TABLENAME2);
        }
        List<Player_armys> player_armyss = getByPcid(DAO, pcid, TABLENAME2);
        return player_armyss.size();
    }

    public static List<Player_armys> getByPcid(Integer pcid) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Player_armys> getByPcid(Player_armysDAO DAO, Integer pcid) {
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Player_armys> getByPcid(Integer pcid, String TABLENAME2) {
        Player_armysDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, TABLENAME2);
    }

    public static List<Player_armys> getByPcid(final Player_armysDAO DAO, final Integer pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcid(pcid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Player_armys> result = newList();
            Set<Integer> m1 = varsByPcid.get(pcid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Player_armys e = getByKey(DAO, key, TABLENAME2);
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

    public static Player_armys update(Player_armys player_armys) {
        Player_armysDAO DAO = DAO();
        return update(DAO, player_armys, DAO.TABLENAME);
    }

    public static Player_armys update(Player_armysDAO DAO, Player_armys player_armys) {
        return update(DAO, player_armys, DAO.TABLENAME);
    }

    public static Player_armys update(Player_armys player_armys, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return update(DAO, player_armys, TABLENAME2);
    }

    public static Player_armys update(final Player_armysDAO DAO, final Player_armys player_armys,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(player_armys, TABLENAME2);
            if(n == -1) 
                return player_armys;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(player_armys);
        }
        return player_armys;
    }

    public static Player_armys asynchronousUpdate(Player_armys player_armys) {
        Player_armysDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_armys, DAO.TABLENAME);
    }

    public static Player_armys asynchronousUpdate(Player_armysDAO DAO, Player_armys player_armys) {
        return asynchronousUpdate(DAO, player_armys, DAO.TABLENAME);
    }

    public static Player_armys asynchronousUpdate(Player_armys player_armys, String TABLENAME2) {
        Player_armysDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_armys, TABLENAME2);
    }

    public static Player_armys asynchronousUpdate(final Player_armysDAO DAO, final Player_armys player_armys,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(player_armys, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(player_armys);
        }
        return player_armys;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Player_armysDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Player_armysDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Player_armysDAO DAO, final String TABLENAME2) {
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

        List<Player_armys> player_armyss = getAll();
        for (Player_armys player_armys : player_armyss) {
            int n = DAO.insert2(player_armys, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
