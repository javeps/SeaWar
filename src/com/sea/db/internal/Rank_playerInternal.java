package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - rank_player
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Rank_playerInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Rank_playerDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Rank_playerInternal(){}

    public static Rank_playerDAO DAO(){
        return Rank_playerEntity.Rank_playerDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Rank_player[MAX];
    }
    private static Rank_player[] FIXED = new Rank_player[MAX];
    public static final Map<Integer, Rank_player> vars = newSortedMap();

    private static final List<Rank_player> fixedCache = newList();

    public static void put(Rank_player rank_player){
        if(rank_player == null || rank_player.id <= 0) return ;

        int id = rank_player.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(rank_player))
                	FIXED[id - 1] = rank_player;
                if (!fixedCache.contains(rank_player))
                	fixedCache.add(rank_player);
            }
        } else {
            vars.put(id, rank_player);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        FIXED = new Rank_player[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Rank_playerDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Rank_playerDAO DAO, String TABLENAME2){
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

    public static void relocate(Rank_playerDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Rank_playerDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Rank_playerDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Rank_playerDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Rank_playerDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Rank_playerDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Rank_playerDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Rank_playerDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Rank_playerDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Rank_playerDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Rank_playerDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Rank_playerDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Rank_playerDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Rank_player[maxId(DAO)];

        List<Rank_player> rank_players = DAO.selectAll();
        for (Rank_player rank_player : rank_players) {
            rank_player.reset();
            put(rank_player);
        }
    }

    public static Map toMap(Rank_player rank_player){
        return rank_player.toMap();
    }

    public static List<Map> toMap(List<Rank_player> rank_players){
        List<Map> ret = new Vector<Map>();
        for (Rank_player rank_player : rank_players){
            Map e = rank_player.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Rank_player> sortZh(List<Rank_player> rank_players, final String key) {
        Collections.sort(rank_players, new Comparator<Rank_player>() {
            public int compare(Rank_player o1, Rank_player o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return rank_players;
    }

    public static List<Rank_player> sort(List<Rank_player> rank_players, final String key) {
        Collections.sort(rank_players, new Comparator<Rank_player>() {
            public int compare(Rank_player o1, Rank_player o2) {
                return o1.compareTo(o2, key);
            }
        });
        return rank_players;
    }

    public static List<Rank_player> sort(List<Rank_player> rank_players){
        Collections.sort(rank_players, new Comparator<Rank_player>(){
            public int compare(Rank_player o1, Rank_player o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return rank_players;
    }

    public static List<Rank_player> sortReverse(List<Rank_player> rank_players){
        Collections.sort(rank_players, new Comparator<Rank_player>(){
            public int compare(Rank_player o1, Rank_player o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return rank_players;
    }

    public static Rank_player insert(Rank_player rank_player) {
        Rank_playerDAO DAO = DAO();
        return insert(DAO, rank_player, DAO.TABLENAME);
    }

    public static Rank_player insert(Rank_playerDAO DAO, Rank_player rank_player) {
        return insert(DAO, rank_player, DAO.TABLENAME);
    }

    public static Rank_player insert(Rank_player rank_player, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return insert(DAO, rank_player, TABLENAME2);
    }

    public static Rank_player insert(Rank_playerDAO DAO, Rank_player rank_player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(rank_player, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rank_player.id = n;
        if(cacheModel != NO_CACHE) put(rank_player);

        return rank_player;
    }

    public static Rank_player asynchronousInsert(Rank_player rank_player) {
        Rank_playerDAO DAO = DAO();
        return asynchronousInsert(DAO, rank_player, DAO.TABLENAME);
    }
    public static Rank_player asynchronousInsert(Rank_playerDAO DAO, Rank_player rank_player) {
        return asynchronousInsert(DAO, rank_player, DAO.TABLENAME);
    }
    public static Rank_player asynchronousInsert(Rank_player rank_player, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return asynchronousInsert(DAO, rank_player, TABLENAME2);
    }
    public static Rank_player asynchronousInsert(Rank_playerDAO DAO, Rank_player rank_player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(rank_player, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        rank_player.id = n;
        if(cacheModel != NO_CACHE) put(rank_player);
        return rank_player;
    }
    public static Rank_player insert2(Rank_player rank_player) {
        Rank_playerDAO DAO = DAO();
        return insert2(DAO, rank_player, DAO.TABLENAME);
    }

    public static Rank_player insert2(Rank_playerDAO DAO, Rank_player rank_player) {
        return insert2(DAO, rank_player, DAO.TABLENAME);
    }

    public static Rank_player insert2(Rank_player rank_player, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return insert2(DAO, rank_player, TABLENAME2);
    }

    public static Rank_player insert2(Rank_playerDAO DAO, Rank_player rank_player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(rank_player, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rank_player.id = n;
        if(cacheModel != NO_CACHE) put(rank_player);

        return rank_player;
    }

    public static Rank_player asynchronousInsert2(Rank_player rank_player) {
        Rank_playerDAO DAO = DAO();
        return asynchronousInsert2(DAO, rank_player, DAO.TABLENAME);
    }
    public static Rank_player asynchronousInsert2(Rank_playerDAO DAO, Rank_player rank_player) {
        return asynchronousInsert2(DAO, rank_player, DAO.TABLENAME);
    }
    public static Rank_player asynchronousInsert2(Rank_player rank_player, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return asynchronousInsert2(DAO, rank_player, TABLENAME2);
    }
    public static Rank_player asynchronousInsert2(Rank_playerDAO DAO, Rank_player rank_player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        rank_player.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(rank_player, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(rank_player);
        return rank_player;
    }
    public static int[] insert(List<Rank_player> rank_players) {
        Rank_playerDAO DAO = DAO();
        return insert(DAO, rank_players, DAO.TABLENAME);
    }

    public static int[] insert(Rank_playerDAO DAO, List<Rank_player> rank_players) {
        return insert(DAO, rank_players, DAO.TABLENAME);
    }

    public static int[] insert(List<Rank_player> rank_players, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return insert(DAO, rank_players, TABLENAME2);
    }

    public static int[] insert(Rank_playerDAO DAO, List<Rank_player> rank_players, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(rank_players, TABLENAME2);
            int n = 0;
            for(Rank_player rank_player : rank_players){
                rank_player.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[rank_players.size()];
        int n = 0;
        for(Rank_player rank_player : rank_players){
            rank_player = insert(DAO, rank_player, TABLENAME2);
            ret[n++] = (rank_player == null) ? 0 : (int)rank_player.id;
        }
        return ret;
    }

    public static int delete(Rank_player rank_player) {
        int id = rank_player.id;
        Rank_playerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Rank_playerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Rank_playerDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Rank_playerDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Rank_player rank_player) {
        int id = rank_player.id;
        Rank_playerDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Rank_playerDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Rank_playerDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Rank_playerDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Rank_playerDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Rank_playerDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Rank_playerDAO DAO, int[] ids,String TABLENAME2) {
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
        Rank_playerDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Rank_playerDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Rank_playerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Rank_player> beans) {
        Rank_playerDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rank_player> beans, Rank_playerDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rank_player> beans, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Rank_player> beans, final Rank_playerDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Rank_player rank_player : beans){
                int id = rank_player.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Rank_player> getAll() {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rank_player> getAll(Rank_playerDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rank_player> getAll(String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Rank_player> getAll(final Rank_playerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rank_player> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rank_player> getNoSortAll() {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rank_player> getNoSortAll(Rank_playerDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rank_player> getNoSortAll(String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Rank_player> getNoSortAll(final Rank_playerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Rank_player> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rank_player> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Rank_player rank_player = FIXED[i];
                if (rank_player != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Rank_player> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Rank_player> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Rank_playerDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Rank_playerDAO DAO, final String TABLENAME2) {
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
        Rank_playerDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Rank_playerDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Rank_playerDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Rank_player> getIn(List<Integer> keys) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rank_player> getIn(List<Integer> keys, Rank_playerDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rank_player> getIn(List<Integer> keys, String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Rank_player> getIn(final List<Integer> keys, final Rank_playerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rank_player> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rank_player> getNoSortIn(List<Integer> keys) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rank_player> getNoSortIn(List<Integer> keys, Rank_playerDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rank_player> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Rank_player> getNoSortIn(final List<Integer> keys, final Rank_playerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Rank_player> result = newList();
            for (int key : keys) {
                Rank_player rank_player = getByKeyFromMemory(key);
                if( rank_player == null ) continue;
                result.add(rank_player);
            }
            return result;
        }
    }

    public static List<Rank_player> getLast(int num) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rank_player> getLast(Rank_playerDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rank_player> getLast(int num, String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Rank_player> getLast(final Rank_playerDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Rank_player> result = newList();
            List<Rank_player> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Rank_player last() {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Rank_player last(Rank_playerDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Rank_player last(String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Rank_player last(final Rank_playerDAO DAO, final String TABLENAME2) {
        Rank_player result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Rank_playerDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Rank_playerDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Rank_playerDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Rank_player> getGtKey(int id) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rank_player> getGtKey(Rank_playerDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rank_player> getGtKey(int id, String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Rank_player> getGtKey(final Rank_playerDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rank_player> result = newList();
            List<Rank_player> rank_players = getAll();
            for (Rank_player rank_player : rank_players) {
                if(rank_player.id <= id) continue;
                result.add(rank_player);
            }
            return result;
        }
    }

    public static Rank_player getByKey(int id) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Rank_player getByKey(Rank_playerDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Rank_player getByKey(int id, String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Rank_player getByKey(final Rank_playerDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Rank_player getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Rank_player deleteFromMemory(final int id) {
        Rank_player rank_player;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            rank_player = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(rank_player);
        } else {
            rank_player = vars.remove(id);
        }
        if(rank_player == null) return null;

        return rank_player;
    }

    public static List<Rank_player> getByPage(int page, int size) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rank_player> getByPage(Rank_playerDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rank_player> getByPage(int page, int size, String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Rank_player> getByPage(final Rank_playerDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rank_player> result = newList();
            List<Rank_player> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Rank_playerDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Rank_playerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Rank_playerDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Rank_player update(Rank_player rank_player) {
        Rank_playerDAO DAO = DAO();
        return update(DAO, rank_player, DAO.TABLENAME);
    }

    public static Rank_player update(Rank_playerDAO DAO, Rank_player rank_player) {
        return update(DAO, rank_player, DAO.TABLENAME);
    }

    public static Rank_player update(Rank_player rank_player, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return update(DAO, rank_player, TABLENAME2);
    }

    public static Rank_player update(final Rank_playerDAO DAO, final Rank_player rank_player,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(rank_player, TABLENAME2);
            if(n == -1) 
                return rank_player;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(rank_player);
        }
        return rank_player;
    }

    public static Rank_player asynchronousUpdate(Rank_player rank_player) {
        Rank_playerDAO DAO = DAO();
        return asynchronousUpdate(DAO, rank_player, DAO.TABLENAME);
    }

    public static Rank_player asynchronousUpdate(Rank_playerDAO DAO, Rank_player rank_player) {
        return asynchronousUpdate(DAO, rank_player, DAO.TABLENAME);
    }

    public static Rank_player asynchronousUpdate(Rank_player rank_player, String TABLENAME2) {
        Rank_playerDAO DAO = DAO();
        return asynchronousUpdate(DAO, rank_player, TABLENAME2);
    }

    public static Rank_player asynchronousUpdate(final Rank_playerDAO DAO, final Rank_player rank_player,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(rank_player, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(rank_player);
        }
        return rank_player;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Rank_playerDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Rank_playerDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Rank_playerDAO DAO, final String TABLENAME2) {
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

        List<Rank_player> rank_players = getAll();
        for (Rank_player rank_player : rank_players) {
            int n = DAO.insert2(rank_player, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
