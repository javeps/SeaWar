package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - player_hero
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Player_heroInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Player_heroDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Player_heroInternal(){}

    public static Player_heroDAO DAO(){
        return Player_heroEntity.Player_heroDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Player_hero[MAX];
    }
    private static Player_hero[] FIXED = new Player_hero[MAX];
    public static final Map<Integer, Player_hero> vars = newSortedMap();
    public static final Map<Integer, Integer> varsByHcid = newSortedMap();
    public static final Map<String, Integer> varsByPcidHgid = newSortedMap();

    private static final List<Player_hero> fixedCache = newList();

    public static void put(Player_hero player_hero){
        if(player_hero == null || player_hero.id <= 0) return ;

        int id = player_hero.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(player_hero))
                	FIXED[id - 1] = player_hero;
                if (!fixedCache.contains(player_hero))
                	fixedCache.add(player_hero);
            }
        } else {
            vars.put(id, player_hero);
        }

        int hcid = player_hero.getHcid();
        varsByHcid.put(hcid, id);

        { // pcid
            int vpcid = player_hero.getPcid();
            int vhgid = player_hero.getHgid();
            String vkey = vpcid + "-" + vhgid;
            varsByPcidHgid.put(vkey, id);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByHcid.clear();
        varsByPcidHgid.clear();
        FIXED = new Player_hero[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Player_heroDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Player_heroDAO DAO, String TABLENAME2){
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

    public static void relocate(Player_heroDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Player_heroDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Player_heroDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Player_heroDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Player_heroDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Player_heroDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Player_heroDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Player_heroDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Player_heroDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Player_heroDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Player_heroDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Player_heroDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Player_heroDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Player_hero[maxId(DAO)];

        List<Player_hero> player_heros = DAO.selectAll();
        for (Player_hero player_hero : player_heros) {
            player_hero.reset();
            put(player_hero);
        }
    }

    public static Map toMap(Player_hero player_hero){
        return player_hero.toMap();
    }

    public static List<Map> toMap(List<Player_hero> player_heros){
        List<Map> ret = new Vector<Map>();
        for (Player_hero player_hero : player_heros){
            Map e = player_hero.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Player_hero> sortZh(List<Player_hero> player_heros, final String key) {
        Collections.sort(player_heros, new Comparator<Player_hero>() {
            public int compare(Player_hero o1, Player_hero o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return player_heros;
    }

    public static List<Player_hero> sort(List<Player_hero> player_heros, final String key) {
        Collections.sort(player_heros, new Comparator<Player_hero>() {
            public int compare(Player_hero o1, Player_hero o2) {
                return o1.compareTo(o2, key);
            }
        });
        return player_heros;
    }

    public static List<Player_hero> sort(List<Player_hero> player_heros){
        Collections.sort(player_heros, new Comparator<Player_hero>(){
            public int compare(Player_hero o1, Player_hero o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return player_heros;
    }

    public static List<Player_hero> sortReverse(List<Player_hero> player_heros){
        Collections.sort(player_heros, new Comparator<Player_hero>(){
            public int compare(Player_hero o1, Player_hero o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return player_heros;
    }

    public static List<Player_hero> sortHcid(List<Player_hero> player_heros){
        Collections.sort(player_heros, new Comparator<Player_hero>(){
            public int compare(Player_hero o1, Player_hero o2) {
                Object v1 = o1.getHcid();
                Object v2 = o2.getHcid();
                return compareTo(v1, v2);
            }
        });
        return player_heros;
    }

    public static List<Player_hero> sortHcidRo(List<Player_hero> player_heros){
        Collections.sort(player_heros, new Comparator<Player_hero>(){
            public int compare(Player_hero o1, Player_hero o2) {
                Object v1 = o1.getHcid();
                Object v2 = o2.getHcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return player_heros;
    }

    public static Player_hero insert(Player_hero player_hero) {
        Player_heroDAO DAO = DAO();
        return insert(DAO, player_hero, DAO.TABLENAME);
    }

    public static Player_hero insert(Player_heroDAO DAO, Player_hero player_hero) {
        return insert(DAO, player_hero, DAO.TABLENAME);
    }

    public static Player_hero insert(Player_hero player_hero, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return insert(DAO, player_hero, TABLENAME2);
    }

    public static Player_hero insert(Player_heroDAO DAO, Player_hero player_hero, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(player_hero, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_hero.id = n;
        if(cacheModel != NO_CACHE) put(player_hero);

        return player_hero;
    }

    public static Player_hero asynchronousInsert(Player_hero player_hero) {
        Player_heroDAO DAO = DAO();
        return asynchronousInsert(DAO, player_hero, DAO.TABLENAME);
    }
    public static Player_hero asynchronousInsert(Player_heroDAO DAO, Player_hero player_hero) {
        return asynchronousInsert(DAO, player_hero, DAO.TABLENAME);
    }
    public static Player_hero asynchronousInsert(Player_hero player_hero, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return asynchronousInsert(DAO, player_hero, TABLENAME2);
    }
    public static Player_hero asynchronousInsert(Player_heroDAO DAO, Player_hero player_hero, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(player_hero, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        player_hero.id = n;
        if(cacheModel != NO_CACHE) put(player_hero);
        return player_hero;
    }
    public static Player_hero insert2(Player_hero player_hero) {
        Player_heroDAO DAO = DAO();
        return insert2(DAO, player_hero, DAO.TABLENAME);
    }

    public static Player_hero insert2(Player_heroDAO DAO, Player_hero player_hero) {
        return insert2(DAO, player_hero, DAO.TABLENAME);
    }

    public static Player_hero insert2(Player_hero player_hero, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return insert2(DAO, player_hero, TABLENAME2);
    }

    public static Player_hero insert2(Player_heroDAO DAO, Player_hero player_hero, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(player_hero, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_hero.id = n;
        if(cacheModel != NO_CACHE) put(player_hero);

        return player_hero;
    }

    public static Player_hero asynchronousInsert2(Player_hero player_hero) {
        Player_heroDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_hero, DAO.TABLENAME);
    }
    public static Player_hero asynchronousInsert2(Player_heroDAO DAO, Player_hero player_hero) {
        return asynchronousInsert2(DAO, player_hero, DAO.TABLENAME);
    }
    public static Player_hero asynchronousInsert2(Player_hero player_hero, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_hero, TABLENAME2);
    }
    public static Player_hero asynchronousInsert2(Player_heroDAO DAO, Player_hero player_hero, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        player_hero.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(player_hero, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(player_hero);
        return player_hero;
    }
    public static int[] insert(List<Player_hero> player_heros) {
        Player_heroDAO DAO = DAO();
        return insert(DAO, player_heros, DAO.TABLENAME);
    }

    public static int[] insert(Player_heroDAO DAO, List<Player_hero> player_heros) {
        return insert(DAO, player_heros, DAO.TABLENAME);
    }

    public static int[] insert(List<Player_hero> player_heros, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return insert(DAO, player_heros, TABLENAME2);
    }

    public static int[] insert(Player_heroDAO DAO, List<Player_hero> player_heros, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(player_heros, TABLENAME2);
            int n = 0;
            for(Player_hero player_hero : player_heros){
                player_hero.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[player_heros.size()];
        int n = 0;
        for(Player_hero player_hero : player_heros){
            player_hero = insert(DAO, player_hero, TABLENAME2);
            ret[n++] = (player_hero == null) ? 0 : (int)player_hero.id;
        }
        return ret;
    }

    public static int delete(Player_hero player_hero) {
        int id = player_hero.id;
        Player_heroDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Player_heroDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Player_heroDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Player_heroDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Player_hero player_hero) {
        int id = player_hero.id;
        Player_heroDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Player_heroDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Player_heroDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Player_heroDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Player_heroDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Player_heroDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Player_heroDAO DAO, int[] ids,String TABLENAME2) {
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
        Player_heroDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Player_heroDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Player_heroDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Player_hero> beans) {
        Player_heroDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_hero> beans, Player_heroDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_hero> beans, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Player_hero> beans, final Player_heroDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Player_hero player_hero : beans){
                int id = player_hero.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Player_hero> getAll() {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_hero> getAll(Player_heroDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_hero> getAll(String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Player_hero> getAll(final Player_heroDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_hero> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_hero> getNoSortAll() {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_hero> getNoSortAll(Player_heroDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_hero> getNoSortAll(String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Player_hero> getNoSortAll(final Player_heroDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Player_hero> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_hero> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Player_hero player_hero = FIXED[i];
                if (player_hero != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Player_hero> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Player_hero> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Player_heroDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Player_heroDAO DAO, final String TABLENAME2) {
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
        Player_heroDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Player_heroDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Player_heroDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Player_hero> getIn(List<Integer> keys) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_hero> getIn(List<Integer> keys, Player_heroDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_hero> getIn(List<Integer> keys, String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_hero> getIn(final List<Integer> keys, final Player_heroDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_hero> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_hero> getNoSortIn(List<Integer> keys) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_hero> getNoSortIn(List<Integer> keys, Player_heroDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_hero> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_hero> getNoSortIn(final List<Integer> keys, final Player_heroDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Player_hero> result = newList();
            for (int key : keys) {
                Player_hero player_hero = getByKeyFromMemory(key);
                if( player_hero == null ) continue;
                result.add(player_hero);
            }
            return result;
        }
    }

    public static List<Player_hero> getLast(int num) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_hero> getLast(Player_heroDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_hero> getLast(int num, String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Player_hero> getLast(final Player_heroDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Player_hero> result = newList();
            List<Player_hero> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Player_hero last() {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_hero last(Player_heroDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_hero last(String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Player_hero last(final Player_heroDAO DAO, final String TABLENAME2) {
        Player_hero result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Player_heroDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Player_heroDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Player_heroDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Player_hero> getGtKey(int id) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_hero> getGtKey(Player_heroDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_hero> getGtKey(int id, String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Player_hero> getGtKey(final Player_heroDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_hero> result = newList();
            List<Player_hero> player_heros = getAll();
            for (Player_hero player_hero : player_heros) {
                if(player_hero.id <= id) continue;
                result.add(player_hero);
            }
            return result;
        }
    }

    public static Player_hero getByKey(int id) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_hero getByKey(Player_heroDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_hero getByKey(int id, String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Player_hero getByKey(final Player_heroDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Player_hero getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Player_hero deleteFromMemory(final int id) {
        Player_hero player_hero;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            player_hero = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(player_hero);
        } else {
            player_hero = vars.remove(id);
        }
        if(player_hero == null) return null;

        int hcid = player_hero.getHcid();
        varsByHcid.remove(hcid);

        { // pcid
            int vpcid = player_hero.getPcid();
            int vhgid = player_hero.getHgid();
            String vkey = vpcid + "-" + vhgid;
            varsByPcidHgid.remove(vkey);
        }

        return player_hero;
    }

    public static List<Player_hero> getByPage(int page, int size) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_hero> getByPage(Player_heroDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_hero> getByPage(int page, int size, String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Player_hero> getByPage(final Player_heroDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_hero> result = newList();
            List<Player_hero> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Player_heroDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Player_heroDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Player_hero getByHcid(Integer hcid) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByHcid(DAO, hcid, DAO.TABLENAME);
    }

    public static Player_hero getByHcid(Player_heroDAO DAO, Integer hcid) {
        return getByHcid(DAO, hcid, DAO.TABLENAME);
    }

    public static Player_hero getByHcid(Integer hcid, String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByHcid(DAO, hcid, TABLENAME2);
    }

    public static Player_hero getByHcid(final Player_heroDAO DAO, final int hcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByHcid(hcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByHcid.get(hcid);
            if(id == null) return null;
            Player_hero result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getHcid() != hcid){
                varsByHcid.remove(hcid);
                return null;
            }
            return result;
        }
    }

    public static Player_hero getByPcidHgid(Integer pcid, Integer hgid) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcidHgid(DAO, pcid, hgid, DAO.TABLENAME);
    }

    public static Player_hero getByPcidHgid(Player_heroDAO DAO, Integer pcid, Integer hgid) {
        return getByPcidHgid(DAO, pcid, hgid, DAO.TABLENAME);
    }

    public static Player_hero getByPcidHgid(Integer pcid, Integer hgid, String TABLENAME2) {
        Player_heroDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcidHgid(DAO, pcid, hgid, TABLENAME2);
    }

    public static Player_hero getByPcidHgid(final Player_heroDAO DAO, Integer pcid, Integer hgid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcidHgid(pcid, hgid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = pcid+"-"+hgid;
            Integer id = varsByPcidHgid.get(vkey);
            if(id == null) return null;
            Player_hero result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getPcid() != pcid){
                varsByPcidHgid.remove(vkey);
                return null;
            }
            if(result.getHgid() != hgid){
                varsByPcidHgid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Player_hero update(Player_hero player_hero) {
        Player_heroDAO DAO = DAO();
        return update(DAO, player_hero, DAO.TABLENAME);
    }

    public static Player_hero update(Player_heroDAO DAO, Player_hero player_hero) {
        return update(DAO, player_hero, DAO.TABLENAME);
    }

    public static Player_hero update(Player_hero player_hero, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return update(DAO, player_hero, TABLENAME2);
    }

    public static Player_hero update(final Player_heroDAO DAO, final Player_hero player_hero,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(player_hero, TABLENAME2);
            if(n == -1) 
                return player_hero;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(player_hero);
        }
        return player_hero;
    }

    public static Player_hero asynchronousUpdate(Player_hero player_hero) {
        Player_heroDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_hero, DAO.TABLENAME);
    }

    public static Player_hero asynchronousUpdate(Player_heroDAO DAO, Player_hero player_hero) {
        return asynchronousUpdate(DAO, player_hero, DAO.TABLENAME);
    }

    public static Player_hero asynchronousUpdate(Player_hero player_hero, String TABLENAME2) {
        Player_heroDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_hero, TABLENAME2);
    }

    public static Player_hero asynchronousUpdate(final Player_heroDAO DAO, final Player_hero player_hero,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(player_hero, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(player_hero);
        }
        return player_hero;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Player_heroDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Player_heroDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Player_heroDAO DAO, final String TABLENAME2) {
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

        List<Player_hero> player_heros = getAll();
        for (Player_hero player_hero : player_heros) {
            int n = DAO.insert2(player_hero, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
