package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - player_tech
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Player_techInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Player_techDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Player_techInternal(){}

    public static Player_techDAO DAO(){
        return Player_techEntity.Player_techDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Player_tech[MAX];
    }
    private static Player_tech[] FIXED = new Player_tech[MAX];
    public static final Map<Integer, Player_tech> vars = newSortedMap();
    public static final Map<String, Integer> varsByPcidGid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByPcid = newSortedMap();

    private static final List<Player_tech> fixedCache = newList();

    public static void put(Player_tech player_tech){
        if(player_tech == null || player_tech.id <= 0) return ;

        int id = player_tech.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(player_tech))
                	FIXED[id - 1] = player_tech;
                if (!fixedCache.contains(player_tech))
                	fixedCache.add(player_tech);
            }
        } else {
            vars.put(id, player_tech);
        }

        { // player_id_2
            int vpcid = player_tech.getPcid();
            int vgid = player_tech.getGid();
            String vkey = vpcid + "-" + vgid;
            varsByPcidGid.put(vkey, id);
        }

        int pcid = player_tech.getPcid();
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
        varsByPcidGid.clear();
        varsByPcid.clear();
        FIXED = new Player_tech[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Player_techDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Player_techDAO DAO, String TABLENAME2){
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

    public static void relocate(Player_techDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Player_techDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Player_techDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Player_techDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Player_techDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Player_techDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Player_techDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Player_techDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Player_techDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Player_techDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Player_techDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Player_techDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Player_techDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Player_techDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Player_techDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Player_tech[maxId(DAO)];

        List<Player_tech> player_techs = DAO.selectAll();
        for (Player_tech player_tech : player_techs) {
            player_tech.reset();
            put(player_tech);
        }
    }

    public static Map toMap(Player_tech player_tech){
        return player_tech.toMap();
    }

    public static List<Map> toMap(List<Player_tech> player_techs){
        List<Map> ret = new Vector<Map>();
        for (Player_tech player_tech : player_techs){
            Map e = player_tech.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Player_tech> sortZh(List<Player_tech> player_techs, final String key) {
        Collections.sort(player_techs, new Comparator<Player_tech>() {
            public int compare(Player_tech o1, Player_tech o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return player_techs;
    }

    public static List<Player_tech> sort(List<Player_tech> player_techs, final String key) {
        Collections.sort(player_techs, new Comparator<Player_tech>() {
            public int compare(Player_tech o1, Player_tech o2) {
                return o1.compareTo(o2, key);
            }
        });
        return player_techs;
    }

    public static List<Player_tech> sort(List<Player_tech> player_techs){
        Collections.sort(player_techs, new Comparator<Player_tech>(){
            public int compare(Player_tech o1, Player_tech o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return player_techs;
    }

    public static List<Player_tech> sortReverse(List<Player_tech> player_techs){
        Collections.sort(player_techs, new Comparator<Player_tech>(){
            public int compare(Player_tech o1, Player_tech o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return player_techs;
    }

    public static List<Player_tech> sortPcid(List<Player_tech> player_techs){
        Collections.sort(player_techs, new Comparator<Player_tech>(){
            public int compare(Player_tech o1, Player_tech o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return compareTo(v1, v2);
            }
        });
        return player_techs;
    }

    public static List<Player_tech> sortPcidRo(List<Player_tech> player_techs){
        Collections.sort(player_techs, new Comparator<Player_tech>(){
            public int compare(Player_tech o1, Player_tech o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return player_techs;
    }

    public static Player_tech insert(Player_tech player_tech) {
        Player_techDAO DAO = DAO();
        return insert(DAO, player_tech, DAO.TABLENAME);
    }

    public static Player_tech insert(Player_techDAO DAO, Player_tech player_tech) {
        return insert(DAO, player_tech, DAO.TABLENAME);
    }

    public static Player_tech insert(Player_tech player_tech, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return insert(DAO, player_tech, TABLENAME2);
    }

    public static Player_tech insert(Player_techDAO DAO, Player_tech player_tech, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(player_tech, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_tech.id = n;
        if(cacheModel != NO_CACHE) put(player_tech);

        return player_tech;
    }

    public static Player_tech asynchronousInsert(Player_tech player_tech) {
        Player_techDAO DAO = DAO();
        return asynchronousInsert(DAO, player_tech, DAO.TABLENAME);
    }
    public static Player_tech asynchronousInsert(Player_techDAO DAO, Player_tech player_tech) {
        return asynchronousInsert(DAO, player_tech, DAO.TABLENAME);
    }
    public static Player_tech asynchronousInsert(Player_tech player_tech, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return asynchronousInsert(DAO, player_tech, TABLENAME2);
    }
    public static Player_tech asynchronousInsert(Player_techDAO DAO, Player_tech player_tech, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(player_tech, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        player_tech.id = n;
        if(cacheModel != NO_CACHE) put(player_tech);
        return player_tech;
    }
    public static Player_tech insert2(Player_tech player_tech) {
        Player_techDAO DAO = DAO();
        return insert2(DAO, player_tech, DAO.TABLENAME);
    }

    public static Player_tech insert2(Player_techDAO DAO, Player_tech player_tech) {
        return insert2(DAO, player_tech, DAO.TABLENAME);
    }

    public static Player_tech insert2(Player_tech player_tech, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return insert2(DAO, player_tech, TABLENAME2);
    }

    public static Player_tech insert2(Player_techDAO DAO, Player_tech player_tech, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(player_tech, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_tech.id = n;
        if(cacheModel != NO_CACHE) put(player_tech);

        return player_tech;
    }

    public static Player_tech asynchronousInsert2(Player_tech player_tech) {
        Player_techDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_tech, DAO.TABLENAME);
    }
    public static Player_tech asynchronousInsert2(Player_techDAO DAO, Player_tech player_tech) {
        return asynchronousInsert2(DAO, player_tech, DAO.TABLENAME);
    }
    public static Player_tech asynchronousInsert2(Player_tech player_tech, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_tech, TABLENAME2);
    }
    public static Player_tech asynchronousInsert2(Player_techDAO DAO, Player_tech player_tech, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        player_tech.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(player_tech, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(player_tech);
        return player_tech;
    }
    public static int[] insert(List<Player_tech> player_techs) {
        Player_techDAO DAO = DAO();
        return insert(DAO, player_techs, DAO.TABLENAME);
    }

    public static int[] insert(Player_techDAO DAO, List<Player_tech> player_techs) {
        return insert(DAO, player_techs, DAO.TABLENAME);
    }

    public static int[] insert(List<Player_tech> player_techs, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return insert(DAO, player_techs, TABLENAME2);
    }

    public static int[] insert(Player_techDAO DAO, List<Player_tech> player_techs, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(player_techs, TABLENAME2);
            int n = 0;
            for(Player_tech player_tech : player_techs){
                player_tech.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[player_techs.size()];
        int n = 0;
        for(Player_tech player_tech : player_techs){
            player_tech = insert(DAO, player_tech, TABLENAME2);
            ret[n++] = (player_tech == null) ? 0 : (int)player_tech.id;
        }
        return ret;
    }

    public static int delete(Player_tech player_tech) {
        int id = player_tech.id;
        Player_techDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Player_techDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Player_techDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Player_techDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Player_tech player_tech) {
        int id = player_tech.id;
        Player_techDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Player_techDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Player_techDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Player_techDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Player_techDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Player_techDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Player_techDAO DAO, int[] ids,String TABLENAME2) {
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
        Player_techDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Player_techDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Player_techDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Player_tech> beans) {
        Player_techDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_tech> beans, Player_techDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_tech> beans, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Player_tech> beans, final Player_techDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Player_tech player_tech : beans){
                int id = player_tech.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Player_tech> getAll() {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_tech> getAll(Player_techDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_tech> getAll(String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Player_tech> getAll(final Player_techDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_tech> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_tech> getNoSortAll() {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_tech> getNoSortAll(Player_techDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_tech> getNoSortAll(String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Player_tech> getNoSortAll(final Player_techDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Player_tech> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_tech> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Player_tech player_tech = FIXED[i];
                if (player_tech != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Player_tech> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Player_tech> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Player_techDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Player_techDAO DAO, final String TABLENAME2) {
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
        Player_techDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Player_techDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Player_techDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Player_tech> getIn(List<Integer> keys) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_tech> getIn(List<Integer> keys, Player_techDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_tech> getIn(List<Integer> keys, String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_tech> getIn(final List<Integer> keys, final Player_techDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_tech> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_tech> getNoSortIn(List<Integer> keys) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_tech> getNoSortIn(List<Integer> keys, Player_techDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_tech> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_tech> getNoSortIn(final List<Integer> keys, final Player_techDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Player_tech> result = newList();
            for (int key : keys) {
                Player_tech player_tech = getByKeyFromMemory(key);
                if( player_tech == null ) continue;
                result.add(player_tech);
            }
            return result;
        }
    }

    public static List<Player_tech> getLast(int num) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_tech> getLast(Player_techDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_tech> getLast(int num, String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Player_tech> getLast(final Player_techDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Player_tech> result = newList();
            List<Player_tech> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Player_tech last() {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_tech last(Player_techDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_tech last(String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Player_tech last(final Player_techDAO DAO, final String TABLENAME2) {
        Player_tech result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Player_techDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Player_techDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Player_techDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Player_tech> getGtKey(int id) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_tech> getGtKey(Player_techDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_tech> getGtKey(int id, String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Player_tech> getGtKey(final Player_techDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_tech> result = newList();
            List<Player_tech> player_techs = getAll();
            for (Player_tech player_tech : player_techs) {
                if(player_tech.id <= id) continue;
                result.add(player_tech);
            }
            return result;
        }
    }

    public static Player_tech getByKey(int id) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_tech getByKey(Player_techDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_tech getByKey(int id, String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Player_tech getByKey(final Player_techDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Player_tech getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Player_tech deleteFromMemory(final int id) {
        Player_tech player_tech;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            player_tech = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(player_tech);
        } else {
            player_tech = vars.remove(id);
        }
        if(player_tech == null) return null;

        { // player_id_2
            int vpcid = player_tech.getPcid();
            int vgid = player_tech.getGid();
            String vkey = vpcid + "-" + vgid;
            varsByPcidGid.remove(vkey);
        }

        int pcid = player_tech.getPcid();
        Set m1 = varsByPcid.get(pcid);
        if(m1 != null)
            m1.remove(id);

        return player_tech;
    }

    public static List<Player_tech> getByPage(int page, int size) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_tech> getByPage(Player_techDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_tech> getByPage(int page, int size, String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Player_tech> getByPage(final Player_techDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_tech> result = newList();
            List<Player_tech> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Player_techDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Player_techDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Player_tech getByPcidGid(Integer pcid, Integer gid) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcidGid(DAO, pcid, gid, DAO.TABLENAME);
    }

    public static Player_tech getByPcidGid(Player_techDAO DAO, Integer pcid, Integer gid) {
        return getByPcidGid(DAO, pcid, gid, DAO.TABLENAME);
    }

    public static Player_tech getByPcidGid(Integer pcid, Integer gid, String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcidGid(DAO, pcid, gid, TABLENAME2);
    }

    public static Player_tech getByPcidGid(final Player_techDAO DAO, Integer pcid, Integer gid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcidGid(pcid, gid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = pcid+"-"+gid;
            Integer id = varsByPcidGid.get(vkey);
            if(id == null) return null;
            Player_tech result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getPcid() != pcid){
                varsByPcidGid.remove(vkey);
                return null;
            }
            if(result.getGid() != gid){
                varsByPcidGid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static int countByPcid(Integer pcid) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static int countByPcid(Player_techDAO DAO, Integer pcid) {
        return countByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static int countByPcid(Integer pcid, String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByPcid(DAO, pcid, TABLENAME2);
    }

    public static int countByPcid(final Player_techDAO DAO, final Integer pcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByPcid(pcid, TABLENAME2);
        }
        List<Player_tech> player_techs = getByPcid(DAO, pcid, TABLENAME2);
        return player_techs.size();
    }

    public static List<Player_tech> getByPcid(Integer pcid) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Player_tech> getByPcid(Player_techDAO DAO, Integer pcid) {
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Player_tech> getByPcid(Integer pcid, String TABLENAME2) {
        Player_techDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, TABLENAME2);
    }

    public static List<Player_tech> getByPcid(final Player_techDAO DAO, final Integer pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcid(pcid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Player_tech> result = newList();
            Set<Integer> m1 = varsByPcid.get(pcid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Player_tech e = getByKey(DAO, key, TABLENAME2);
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

    public static Player_tech update(Player_tech player_tech) {
        Player_techDAO DAO = DAO();
        return update(DAO, player_tech, DAO.TABLENAME);
    }

    public static Player_tech update(Player_techDAO DAO, Player_tech player_tech) {
        return update(DAO, player_tech, DAO.TABLENAME);
    }

    public static Player_tech update(Player_tech player_tech, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return update(DAO, player_tech, TABLENAME2);
    }

    public static Player_tech update(final Player_techDAO DAO, final Player_tech player_tech,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(player_tech, TABLENAME2);
            if(n == -1) 
                return player_tech;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(player_tech);
        }
        return player_tech;
    }

    public static Player_tech asynchronousUpdate(Player_tech player_tech) {
        Player_techDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_tech, DAO.TABLENAME);
    }

    public static Player_tech asynchronousUpdate(Player_techDAO DAO, Player_tech player_tech) {
        return asynchronousUpdate(DAO, player_tech, DAO.TABLENAME);
    }

    public static Player_tech asynchronousUpdate(Player_tech player_tech, String TABLENAME2) {
        Player_techDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_tech, TABLENAME2);
    }

    public static Player_tech asynchronousUpdate(final Player_techDAO DAO, final Player_tech player_tech,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(player_tech, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(player_tech);
        }
        return player_tech;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Player_techDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Player_techDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Player_techDAO DAO, final String TABLENAME2) {
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

        List<Player_tech> player_techs = getAll();
        for (Player_tech player_tech : player_techs) {
            int n = DAO.insert2(player_tech, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
