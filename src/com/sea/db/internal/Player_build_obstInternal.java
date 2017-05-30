package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - player_build_obst
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Player_build_obstInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Player_build_obstDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Player_build_obstInternal(){}

    public static Player_build_obstDAO DAO(){
        return Player_build_obstEntity.Player_build_obstDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Player_build_obst[MAX];
    }
    private static Player_build_obst[] FIXED = new Player_build_obst[MAX];
    public static final Map<Integer, Player_build_obst> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByPcidGid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByPcid = newSortedMap();
    public static final Map<String, Integer> varsByBcidPcid = newSortedMap();

    private static final List<Player_build_obst> fixedCache = newList();

    public static void put(Player_build_obst player_build_obst){
        if(player_build_obst == null || player_build_obst.id <= 0) return ;

        int id = player_build_obst.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(player_build_obst))
                	FIXED[id - 1] = player_build_obst;
                if (!fixedCache.contains(player_build_obst))
                	fixedCache.add(player_build_obst);
            }
        } else {
            vars.put(id, player_build_obst);
        }

        { // player_id_2
            int vpcid = player_build_obst.getPcid();
            int vgid = player_build_obst.getGid();
            String vkey = vpcid+ "-" +vgid;
            Set m1 = varsByPcidGid.get(vkey);
            if(m1 == null){
                m1 = newSortedSet();
                varsByPcidGid.put(vkey, m1);
            }
            m1.add(id);
        }

        int pcid = player_build_obst.getPcid();
        Set m2 = varsByPcid.get(pcid);
        if(m2 == null){
            m2 = newSortedSet();
            varsByPcid.put(pcid, m2);
        }
        m2.add(id);

        { // client_id
            int vbcid = player_build_obst.getBcid();
            int vpcid = player_build_obst.getPcid();
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
        FIXED = new Player_build_obst[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Player_build_obstDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Player_build_obstDAO DAO, String TABLENAME2){
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

    public static void relocate(Player_build_obstDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Player_build_obstDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Player_build_obstDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Player_build_obstDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Player_build_obstDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Player_build_obstDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Player_build_obstDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Player_build_obstDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Player_build_obstDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Player_build_obstDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Player_build_obstDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Player_build_obstDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Player_build_obstDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Player_build_obst[maxId(DAO)];

        List<Player_build_obst> player_build_obsts = DAO.selectAll();
        for (Player_build_obst player_build_obst : player_build_obsts) {
            player_build_obst.reset();
            put(player_build_obst);
        }
    }

    public static Map toMap(Player_build_obst player_build_obst){
        return player_build_obst.toMap();
    }

    public static List<Map> toMap(List<Player_build_obst> player_build_obsts){
        List<Map> ret = new Vector<Map>();
        for (Player_build_obst player_build_obst : player_build_obsts){
            Map e = player_build_obst.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Player_build_obst> sortZh(List<Player_build_obst> player_build_obsts, final String key) {
        Collections.sort(player_build_obsts, new Comparator<Player_build_obst>() {
            public int compare(Player_build_obst o1, Player_build_obst o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return player_build_obsts;
    }

    public static List<Player_build_obst> sort(List<Player_build_obst> player_build_obsts, final String key) {
        Collections.sort(player_build_obsts, new Comparator<Player_build_obst>() {
            public int compare(Player_build_obst o1, Player_build_obst o2) {
                return o1.compareTo(o2, key);
            }
        });
        return player_build_obsts;
    }

    public static List<Player_build_obst> sort(List<Player_build_obst> player_build_obsts){
        Collections.sort(player_build_obsts, new Comparator<Player_build_obst>(){
            public int compare(Player_build_obst o1, Player_build_obst o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return player_build_obsts;
    }

    public static List<Player_build_obst> sortReverse(List<Player_build_obst> player_build_obsts){
        Collections.sort(player_build_obsts, new Comparator<Player_build_obst>(){
            public int compare(Player_build_obst o1, Player_build_obst o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return player_build_obsts;
    }

    public static List<Player_build_obst> sortPcid(List<Player_build_obst> player_build_obsts){
        Collections.sort(player_build_obsts, new Comparator<Player_build_obst>(){
            public int compare(Player_build_obst o1, Player_build_obst o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return compareTo(v1, v2);
            }
        });
        return player_build_obsts;
    }

    public static List<Player_build_obst> sortPcidRo(List<Player_build_obst> player_build_obsts){
        Collections.sort(player_build_obsts, new Comparator<Player_build_obst>(){
            public int compare(Player_build_obst o1, Player_build_obst o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return player_build_obsts;
    }

    public static Player_build_obst insert(Player_build_obst player_build_obst) {
        Player_build_obstDAO DAO = DAO();
        return insert(DAO, player_build_obst, DAO.TABLENAME);
    }

    public static Player_build_obst insert(Player_build_obstDAO DAO, Player_build_obst player_build_obst) {
        return insert(DAO, player_build_obst, DAO.TABLENAME);
    }

    public static Player_build_obst insert(Player_build_obst player_build_obst, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return insert(DAO, player_build_obst, TABLENAME2);
    }

    public static Player_build_obst insert(Player_build_obstDAO DAO, Player_build_obst player_build_obst, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(player_build_obst, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_build_obst.id = n;
        if(cacheModel != NO_CACHE) put(player_build_obst);

        return player_build_obst;
    }

    public static Player_build_obst asynchronousInsert(Player_build_obst player_build_obst) {
        Player_build_obstDAO DAO = DAO();
        return asynchronousInsert(DAO, player_build_obst, DAO.TABLENAME);
    }
    public static Player_build_obst asynchronousInsert(Player_build_obstDAO DAO, Player_build_obst player_build_obst) {
        return asynchronousInsert(DAO, player_build_obst, DAO.TABLENAME);
    }
    public static Player_build_obst asynchronousInsert(Player_build_obst player_build_obst, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return asynchronousInsert(DAO, player_build_obst, TABLENAME2);
    }
    public static Player_build_obst asynchronousInsert(Player_build_obstDAO DAO, Player_build_obst player_build_obst, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(player_build_obst, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        player_build_obst.id = n;
        if(cacheModel != NO_CACHE) put(player_build_obst);
        return player_build_obst;
    }
    public static Player_build_obst insert2(Player_build_obst player_build_obst) {
        Player_build_obstDAO DAO = DAO();
        return insert2(DAO, player_build_obst, DAO.TABLENAME);
    }

    public static Player_build_obst insert2(Player_build_obstDAO DAO, Player_build_obst player_build_obst) {
        return insert2(DAO, player_build_obst, DAO.TABLENAME);
    }

    public static Player_build_obst insert2(Player_build_obst player_build_obst, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return insert2(DAO, player_build_obst, TABLENAME2);
    }

    public static Player_build_obst insert2(Player_build_obstDAO DAO, Player_build_obst player_build_obst, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(player_build_obst, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_build_obst.id = n;
        if(cacheModel != NO_CACHE) put(player_build_obst);

        return player_build_obst;
    }

    public static Player_build_obst asynchronousInsert2(Player_build_obst player_build_obst) {
        Player_build_obstDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_build_obst, DAO.TABLENAME);
    }
    public static Player_build_obst asynchronousInsert2(Player_build_obstDAO DAO, Player_build_obst player_build_obst) {
        return asynchronousInsert2(DAO, player_build_obst, DAO.TABLENAME);
    }
    public static Player_build_obst asynchronousInsert2(Player_build_obst player_build_obst, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_build_obst, TABLENAME2);
    }
    public static Player_build_obst asynchronousInsert2(Player_build_obstDAO DAO, Player_build_obst player_build_obst, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        player_build_obst.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(player_build_obst, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(player_build_obst);
        return player_build_obst;
    }
    public static int[] insert(List<Player_build_obst> player_build_obsts) {
        Player_build_obstDAO DAO = DAO();
        return insert(DAO, player_build_obsts, DAO.TABLENAME);
    }

    public static int[] insert(Player_build_obstDAO DAO, List<Player_build_obst> player_build_obsts) {
        return insert(DAO, player_build_obsts, DAO.TABLENAME);
    }

    public static int[] insert(List<Player_build_obst> player_build_obsts, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return insert(DAO, player_build_obsts, TABLENAME2);
    }

    public static int[] insert(Player_build_obstDAO DAO, List<Player_build_obst> player_build_obsts, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(player_build_obsts, TABLENAME2);
            int n = 0;
            for(Player_build_obst player_build_obst : player_build_obsts){
                player_build_obst.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[player_build_obsts.size()];
        int n = 0;
        for(Player_build_obst player_build_obst : player_build_obsts){
            player_build_obst = insert(DAO, player_build_obst, TABLENAME2);
            ret[n++] = (player_build_obst == null) ? 0 : (int)player_build_obst.id;
        }
        return ret;
    }

    public static int delete(Player_build_obst player_build_obst) {
        int id = player_build_obst.id;
        Player_build_obstDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Player_build_obstDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Player_build_obstDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Player_build_obstDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Player_build_obst player_build_obst) {
        int id = player_build_obst.id;
        Player_build_obstDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Player_build_obstDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Player_build_obstDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Player_build_obstDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Player_build_obstDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Player_build_obstDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Player_build_obstDAO DAO, int[] ids,String TABLENAME2) {
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
        Player_build_obstDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Player_build_obstDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Player_build_obstDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Player_build_obst> beans) {
        Player_build_obstDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_build_obst> beans, Player_build_obstDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_build_obst> beans, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Player_build_obst> beans, final Player_build_obstDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Player_build_obst player_build_obst : beans){
                int id = player_build_obst.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Player_build_obst> getAll() {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getAll(Player_build_obstDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getAll(String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Player_build_obst> getAll(final Player_build_obstDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_build_obst> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_build_obst> getNoSortAll() {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getNoSortAll(Player_build_obstDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getNoSortAll(String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Player_build_obst> getNoSortAll(final Player_build_obstDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Player_build_obst> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_build_obst> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Player_build_obst player_build_obst = FIXED[i];
                if (player_build_obst != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Player_build_obst> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Player_build_obst> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Player_build_obstDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Player_build_obstDAO DAO, final String TABLENAME2) {
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
        Player_build_obstDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Player_build_obstDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Player_build_obstDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Player_build_obst> getIn(List<Integer> keys) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getIn(List<Integer> keys, Player_build_obstDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getIn(List<Integer> keys, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_build_obst> getIn(final List<Integer> keys, final Player_build_obstDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_build_obst> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_build_obst> getNoSortIn(List<Integer> keys) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getNoSortIn(List<Integer> keys, Player_build_obstDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_build_obst> getNoSortIn(final List<Integer> keys, final Player_build_obstDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Player_build_obst> result = newList();
            for (int key : keys) {
                Player_build_obst player_build_obst = getByKeyFromMemory(key);
                if( player_build_obst == null ) continue;
                result.add(player_build_obst);
            }
            return result;
        }
    }

    public static List<Player_build_obst> getLast(int num) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getLast(Player_build_obstDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getLast(int num, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Player_build_obst> getLast(final Player_build_obstDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Player_build_obst> result = newList();
            List<Player_build_obst> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Player_build_obst last() {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_build_obst last(Player_build_obstDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_build_obst last(String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Player_build_obst last(final Player_build_obstDAO DAO, final String TABLENAME2) {
        Player_build_obst result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Player_build_obstDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Player_build_obstDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Player_build_obstDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Player_build_obst> getGtKey(int id) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getGtKey(Player_build_obstDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getGtKey(int id, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Player_build_obst> getGtKey(final Player_build_obstDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_build_obst> result = newList();
            List<Player_build_obst> player_build_obsts = getAll();
            for (Player_build_obst player_build_obst : player_build_obsts) {
                if(player_build_obst.id <= id) continue;
                result.add(player_build_obst);
            }
            return result;
        }
    }

    public static Player_build_obst getByKey(int id) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_build_obst getByKey(Player_build_obstDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_build_obst getByKey(int id, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Player_build_obst getByKey(final Player_build_obstDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Player_build_obst getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Player_build_obst deleteFromMemory(final int id) {
        Player_build_obst player_build_obst;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            player_build_obst = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(player_build_obst);
        } else {
            player_build_obst = vars.remove(id);
        }
        if(player_build_obst == null) return null;

        { // player_id_2
            int vpcid = player_build_obst.getPcid();
            int vgid = player_build_obst.getGid();
            String vkey = vpcid+ "-" +vgid;
            Set m1 = varsByPcidGid.get(vkey);
            if(m1 != null)
                m1.remove(id);
        }

        int pcid = player_build_obst.getPcid();
        Set m2 = varsByPcid.get(pcid);
        if(m2 != null)
            m2.remove(id);

        { // client_id
            int vbcid = player_build_obst.getBcid();
            int vpcid = player_build_obst.getPcid();
            String vkey = vbcid + "-" + vpcid;
            varsByBcidPcid.remove(vkey);
        }

        return player_build_obst;
    }

    public static List<Player_build_obst> getByPage(int page, int size) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getByPage(Player_build_obstDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getByPage(int page, int size, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Player_build_obst> getByPage(final Player_build_obstDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_build_obst> result = newList();
            List<Player_build_obst> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Player_build_obstDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Player_build_obstDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByPcidGid(Integer pcid, Integer gid) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcidGid(DAO, pcid, gid, DAO.TABLENAME);
    }

    public static int countByPcidGid(Player_build_obstDAO DAO, Integer pcid, Integer gid) {
        return countByPcidGid(DAO, pcid, gid, DAO.TABLENAME);
    }

    public static int countByPcidGid(Integer pcid, Integer gid, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcidGid(DAO, pcid, gid, TABLENAME2);
    }

    public static int countByPcidGid(final Player_build_obstDAO DAO, Integer pcid, Integer gid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByPcidGid(pcid, gid, TABLENAME2);
        }
        List<Player_build_obst> player_build_obsts = getByPcidGid(pcid, gid, TABLENAME2);
        return player_build_obsts.size();
    }

    public static List<Player_build_obst> getByPcidGid(Integer pcid, Integer gid) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcidGid(DAO, pcid, gid, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getByPcidGid(Player_build_obstDAO DAO, Integer pcid, Integer gid) {
        return getByPcidGid(DAO, pcid, gid, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getByPcidGid(Integer pcid, Integer gid, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcidGid(DAO, pcid, gid, TABLENAME2);
    }

    public static List<Player_build_obst> getByPcidGid(final Player_build_obstDAO DAO, Integer pcid, Integer gid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcidGid(pcid, gid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player_build_obst> result = newList();
            String vkey = pcid+"-"+gid;
            Set<Integer> m1 = varsByPcidGid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Player_build_obst e = getByKey(DAO, key, TABLENAME2);
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
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static int countByPcid(Player_build_obstDAO DAO, Integer pcid) {
        return countByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static int countByPcid(Integer pcid, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcid(DAO, pcid, TABLENAME2);
    }

    public static int countByPcid(final Player_build_obstDAO DAO, final Integer pcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByPcid(pcid, TABLENAME2);
        }
        List<Player_build_obst> player_build_obsts = getByPcid(DAO, pcid, TABLENAME2);
        return player_build_obsts.size();
    }

    public static List<Player_build_obst> getByPcid(Integer pcid) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getByPcid(Player_build_obstDAO DAO, Integer pcid) {
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Player_build_obst> getByPcid(Integer pcid, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, TABLENAME2);
    }

    public static List<Player_build_obst> getByPcid(final Player_build_obstDAO DAO, final Integer pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcid(pcid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Player_build_obst> result = newList();
            Set<Integer> m1 = varsByPcid.get(pcid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Player_build_obst e = getByKey(DAO, key, TABLENAME2);
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

    public static Player_build_obst getByBcidPcid(Integer bcid, Integer pcid) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByBcidPcid(DAO, bcid, pcid, DAO.TABLENAME);
    }

    public static Player_build_obst getByBcidPcid(Player_build_obstDAO DAO, Integer bcid, Integer pcid) {
        return getByBcidPcid(DAO, bcid, pcid, DAO.TABLENAME);
    }

    public static Player_build_obst getByBcidPcid(Integer bcid, Integer pcid, String TABLENAME2) {
        Player_build_obstDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByBcidPcid(DAO, bcid, pcid, TABLENAME2);
    }

    public static Player_build_obst getByBcidPcid(final Player_build_obstDAO DAO, Integer bcid, Integer pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByBcidPcid(bcid, pcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = bcid+"-"+pcid;
            Integer id = varsByBcidPcid.get(vkey);
            if(id == null) return null;
            Player_build_obst result = getByKey(DAO, id, TABLENAME2);
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

    public static Player_build_obst update(Player_build_obst player_build_obst) {
        Player_build_obstDAO DAO = DAO();
        return update(DAO, player_build_obst, DAO.TABLENAME);
    }

    public static Player_build_obst update(Player_build_obstDAO DAO, Player_build_obst player_build_obst) {
        return update(DAO, player_build_obst, DAO.TABLENAME);
    }

    public static Player_build_obst update(Player_build_obst player_build_obst, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return update(DAO, player_build_obst, TABLENAME2);
    }

    public static Player_build_obst update(final Player_build_obstDAO DAO, final Player_build_obst player_build_obst,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(player_build_obst, TABLENAME2);
            if(n == -1) 
                return player_build_obst;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(player_build_obst);
        }
        return player_build_obst;
    }

    public static Player_build_obst asynchronousUpdate(Player_build_obst player_build_obst) {
        Player_build_obstDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_build_obst, DAO.TABLENAME);
    }

    public static Player_build_obst asynchronousUpdate(Player_build_obstDAO DAO, Player_build_obst player_build_obst) {
        return asynchronousUpdate(DAO, player_build_obst, DAO.TABLENAME);
    }

    public static Player_build_obst asynchronousUpdate(Player_build_obst player_build_obst, String TABLENAME2) {
        Player_build_obstDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_build_obst, TABLENAME2);
    }

    public static Player_build_obst asynchronousUpdate(final Player_build_obstDAO DAO, final Player_build_obst player_build_obst,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(player_build_obst, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(player_build_obst);
        }
        return player_build_obst;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Player_build_obstDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Player_build_obstDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Player_build_obstDAO DAO, final String TABLENAME2) {
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

        List<Player_build_obst> player_build_obsts = getAll();
        for (Player_build_obst player_build_obst : player_build_obsts) {
            int n = DAO.insert2(player_build_obst, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
