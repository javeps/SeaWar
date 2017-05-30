package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - player
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class PlayerInternal extends InternalSupport{
    static Log log = LogFactory.getLog(PlayerDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public PlayerInternal(){}

    public static PlayerDAO DAO(){
        return PlayerEntity.PlayerDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Player[MAX];
    }
    private static Player[] FIXED = new Player[MAX];
    public static final Map<Integer, Player> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByUcidSvcid = newSortedMap();
    public static final Map<String, Integer> varsByPname = newSortedMap();
    public static final Map<Integer, Integer> varsByPcid = newSortedMap();

    private static final List<Player> fixedCache = newList();

    public static void put(Player player){
        if(player == null || player.id <= 0) return ;

        int id = player.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(player))
                	FIXED[id - 1] = player;
                if (!fixedCache.contains(player))
                	fixedCache.add(player);
            }
        } else {
            vars.put(id, player);
        }

        { // uid
            int vucid = player.getUcid();
            int vsvcid = player.getSvcid();
            String vkey = vucid+ "-" +vsvcid;
            Set m1 = varsByUcidSvcid.get(vkey);
            if(m1 == null){
                m1 = newSortedSet();
                varsByUcidSvcid.put(vkey, m1);
            }
            m1.add(id);
        }

        String pname = player.getPname();
        varsByPname.put(pname, id);

        int pcid = player.getPcid();
        varsByPcid.put(pcid, id);

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByUcidSvcid.clear();
        varsByPname.clear();
        varsByPcid.clear();
        FIXED = new Player[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(PlayerDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(PlayerDAO DAO, String TABLENAME2){
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

    public static void relocate(PlayerDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        PlayerDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(PlayerDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        PlayerDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(PlayerDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        PlayerDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(PlayerDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        PlayerDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(PlayerDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(PlayerDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        PlayerDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(PlayerDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(PlayerDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        PlayerDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(PlayerDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Player[maxId(DAO)];

        List<Player> players = DAO.selectAll();
        for (Player player : players) {
            player.reset();
            put(player);
        }
    }

    public static Map toMap(Player player){
        return player.toMap();
    }

    public static List<Map> toMap(List<Player> players){
        List<Map> ret = new Vector<Map>();
        for (Player player : players){
            Map e = player.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Player> sortZh(List<Player> players, final String key) {
        Collections.sort(players, new Comparator<Player>() {
            public int compare(Player o1, Player o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return players;
    }

    public static List<Player> sort(List<Player> players, final String key) {
        Collections.sort(players, new Comparator<Player>() {
            public int compare(Player o1, Player o2) {
                return o1.compareTo(o2, key);
            }
        });
        return players;
    }

    public static List<Player> sort(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortReverse(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortPname(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getPname();
                Object v2 = o2.getPname();
                return compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortPnameRo(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getPname();
                Object v2 = o2.getPname();
                return 0 - compareTo(v1, v2);
            };
        });
        return players;
    }

    public static List<Player> sortPcid(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortPcidRo(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return players;
    }

    public static Player insert(Player player) {
        PlayerDAO DAO = DAO();
        return insert(DAO, player, DAO.TABLENAME);
    }

    public static Player insert(PlayerDAO DAO, Player player) {
        return insert(DAO, player, DAO.TABLENAME);
    }

    public static Player insert(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return insert(DAO, player, TABLENAME2);
    }

    public static Player insert(PlayerDAO DAO, Player player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(player, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player.id = n;
        if(cacheModel != NO_CACHE) put(player);

        return player;
    }

    public static Player asynchronousInsert(Player player) {
        PlayerDAO DAO = DAO();
        return asynchronousInsert(DAO, player, DAO.TABLENAME);
    }
    public static Player asynchronousInsert(PlayerDAO DAO, Player player) {
        return asynchronousInsert(DAO, player, DAO.TABLENAME);
    }
    public static Player asynchronousInsert(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return asynchronousInsert(DAO, player, TABLENAME2);
    }
    public static Player asynchronousInsert(PlayerDAO DAO, Player player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(player, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        player.id = n;
        if(cacheModel != NO_CACHE) put(player);
        return player;
    }
    public static Player insert2(Player player) {
        PlayerDAO DAO = DAO();
        return insert2(DAO, player, DAO.TABLENAME);
    }

    public static Player insert2(PlayerDAO DAO, Player player) {
        return insert2(DAO, player, DAO.TABLENAME);
    }

    public static Player insert2(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return insert2(DAO, player, TABLENAME2);
    }

    public static Player insert2(PlayerDAO DAO, Player player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(player, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player.id = n;
        if(cacheModel != NO_CACHE) put(player);

        return player;
    }

    public static Player asynchronousInsert2(Player player) {
        PlayerDAO DAO = DAO();
        return asynchronousInsert2(DAO, player, DAO.TABLENAME);
    }
    public static Player asynchronousInsert2(PlayerDAO DAO, Player player) {
        return asynchronousInsert2(DAO, player, DAO.TABLENAME);
    }
    public static Player asynchronousInsert2(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return asynchronousInsert2(DAO, player, TABLENAME2);
    }
    public static Player asynchronousInsert2(PlayerDAO DAO, Player player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        player.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(player, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(player);
        return player;
    }
    public static int[] insert(List<Player> players) {
        PlayerDAO DAO = DAO();
        return insert(DAO, players, DAO.TABLENAME);
    }

    public static int[] insert(PlayerDAO DAO, List<Player> players) {
        return insert(DAO, players, DAO.TABLENAME);
    }

    public static int[] insert(List<Player> players, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return insert(DAO, players, TABLENAME2);
    }

    public static int[] insert(PlayerDAO DAO, List<Player> players, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(players, TABLENAME2);
            int n = 0;
            for(Player player : players){
                player.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[players.size()];
        int n = 0;
        for(Player player : players){
            player = insert(DAO, player, TABLENAME2);
            ret[n++] = (player == null) ? 0 : (int)player.id;
        }
        return ret;
    }

    public static int delete(Player player) {
        int id = player.id;
        PlayerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        PlayerDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(PlayerDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(PlayerDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Player player) {
        int id = player.id;
        PlayerDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        PlayerDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(PlayerDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(PlayerDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        PlayerDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(PlayerDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(PlayerDAO DAO, int[] ids,String TABLENAME2) {
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
        PlayerDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, PlayerDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final PlayerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Player> beans) {
        PlayerDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player> beans, PlayerDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player> beans, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Player> beans, final PlayerDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Player player : beans){
                int id = player.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Player> getAll() {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player> getAll(PlayerDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player> getAll(String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Player> getAll(final PlayerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player> getNoSortAll() {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player> getNoSortAll(PlayerDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player> getNoSortAll(String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Player> getNoSortAll(final PlayerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Player> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Player player = FIXED[i];
                if (player != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Player> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Player> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(PlayerDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final PlayerDAO DAO, final String TABLENAME2) {
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
        PlayerDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(PlayerDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final PlayerDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Player> getIn(List<Integer> keys) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player> getIn(List<Integer> keys, PlayerDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player> getIn(List<Integer> keys, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Player> getIn(final List<Integer> keys, final PlayerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player> getNoSortIn(List<Integer> keys) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player> getNoSortIn(List<Integer> keys, PlayerDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Player> getNoSortIn(final List<Integer> keys, final PlayerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Player> result = newList();
            for (int key : keys) {
                Player player = getByKeyFromMemory(key);
                if( player == null ) continue;
                result.add(player);
            }
            return result;
        }
    }

    public static List<Player> getLast(int num) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player> getLast(PlayerDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player> getLast(int num, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Player> getLast(final PlayerDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Player> result = newList();
            List<Player> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Player last() {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Player last(PlayerDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Player last(String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Player last(final PlayerDAO DAO, final String TABLENAME2) {
        Player result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        PlayerDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(PlayerDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final PlayerDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Player> getGtKey(int id) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player> getGtKey(PlayerDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player> getGtKey(int id, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Player> getGtKey(final PlayerDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player> result = newList();
            List<Player> players = getAll();
            for (Player player : players) {
                if(player.id <= id) continue;
                result.add(player);
            }
            return result;
        }
    }

    public static Player getByKey(int id) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player getByKey(PlayerDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player getByKey(int id, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Player getByKey(final PlayerDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Player getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Player deleteFromMemory(final int id) {
        Player player;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            player = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(player);
        } else {
            player = vars.remove(id);
        }
        if(player == null) return null;

        { // uid
            int vucid = player.getUcid();
            int vsvcid = player.getSvcid();
            String vkey = vucid+ "-" +vsvcid;
            Set m1 = varsByUcidSvcid.get(vkey);
            if(m1 != null)
                m1.remove(id);
        }

        String pname = player.getPname();
        varsByPname.remove(pname);

        int pcid = player.getPcid();
        varsByPcid.remove(pcid);

        return player;
    }

    public static List<Player> getByPage(int page, int size) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player> getByPage(PlayerDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player> getByPage(int page, int size, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Player> getByPage(final PlayerDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player> result = newList();
            List<Player> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(PlayerDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final PlayerDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByUcidSvcid(Integer ucid, Integer svcid) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByUcidSvcid(DAO, ucid, svcid, DAO.TABLENAME);
    }

    public static int countByUcidSvcid(PlayerDAO DAO, Integer ucid, Integer svcid) {
        return countByUcidSvcid(DAO, ucid, svcid, DAO.TABLENAME);
    }

    public static int countByUcidSvcid(Integer ucid, Integer svcid, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByUcidSvcid(DAO, ucid, svcid, TABLENAME2);
    }

    public static int countByUcidSvcid(final PlayerDAO DAO, Integer ucid, Integer svcid, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByUcidSvcid(ucid, svcid, TABLENAME2);
        }
        List<Player> players = getByUcidSvcid(ucid, svcid, TABLENAME2);
        return players.size();
    }

    public static List<Player> getByUcidSvcid(Integer ucid, Integer svcid) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUcidSvcid(DAO, ucid, svcid, DAO.TABLENAME);
    }

    public static List<Player> getByUcidSvcid(PlayerDAO DAO, Integer ucid, Integer svcid) {
        return getByUcidSvcid(DAO, ucid, svcid, DAO.TABLENAME);
    }

    public static List<Player> getByUcidSvcid(Integer ucid, Integer svcid, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUcidSvcid(DAO, ucid, svcid, TABLENAME2);
    }

    public static List<Player> getByUcidSvcid(final PlayerDAO DAO, Integer ucid, Integer svcid, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUcidSvcid(ucid, svcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player> result = newList();
            String vkey = ucid+"-"+svcid;
            Set<Integer> m1 = varsByUcidSvcid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Player e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _ucid = e.getUcid();
                int _svcid = e.getSvcid();
                String _key = _ucid + "-" + _svcid;
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Player getByPname(String pname) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPname(DAO, pname, DAO.TABLENAME);
    }

    public static Player getByPname(PlayerDAO DAO, String pname) {
        return getByPname(DAO, pname, DAO.TABLENAME);
    }

    public static Player getByPname(String pname, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPname(DAO, pname, TABLENAME2);
    }

    public static Player getByPname(final PlayerDAO DAO, final String pname,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPname(pname, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByPname.get(pname);
            if(id == null) return null;
            Player result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getPname().equals(pname)){
                varsByPname.remove(pname);
                return null;
            }
            return result;
        }
    }

    public static int countLikePname(String pname) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikePname(DAO, pname, DAO.TABLENAME);
    }

    public static int countLikePname(PlayerDAO DAO, String pname) {
        return countLikePname(DAO, pname, DAO.TABLENAME);
    }

    public static int countLikePname(String pname, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikePname(DAO, pname, TABLENAME2);
    }

    public static int countLikePname(final PlayerDAO DAO, final String pname,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikePname(pname, TABLENAME2);
        }
        List<Player> players = getLikePname(DAO, pname, TABLENAME2);
        return players.size();
    }

    public static List<Player> getLikePname(String pname) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikePname(DAO, pname, DAO.TABLENAME);
    }

    public static List<Player> getLikePname(PlayerDAO DAO, String pname) {
        return getLikePname(DAO, pname, DAO.TABLENAME);
    }

    public static List<Player> getLikePname(String pname, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikePname(DAO, pname, TABLENAME2);
    }

    public static List<Player> getLikePname(final PlayerDAO DAO, final String pname,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikePname(pname, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player> result = newList();
            List<Player> players = getAll(DAO, TABLENAME2);
            for(Player e : players){
                String _var = e.getPname();
                if(_var.indexOf(pname) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Player getByPcid(Integer pcid) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static Player getByPcid(PlayerDAO DAO, Integer pcid) {
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static Player getByPcid(Integer pcid, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, TABLENAME2);
    }

    public static Player getByPcid(final PlayerDAO DAO, final int pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcid(pcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByPcid.get(pcid);
            if(id == null) return null;
            Player result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getPcid() != pcid){
                varsByPcid.remove(pcid);
                return null;
            }
            return result;
        }
    }

    public static Player update(Player player) {
        PlayerDAO DAO = DAO();
        return update(DAO, player, DAO.TABLENAME);
    }

    public static Player update(PlayerDAO DAO, Player player) {
        return update(DAO, player, DAO.TABLENAME);
    }

    public static Player update(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return update(DAO, player, TABLENAME2);
    }

    public static Player update(final PlayerDAO DAO, final Player player,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(player, TABLENAME2);
            if(n == -1) 
                return player;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(player);
        }
        return player;
    }

    public static Player asynchronousUpdate(Player player) {
        PlayerDAO DAO = DAO();
        return asynchronousUpdate(DAO, player, DAO.TABLENAME);
    }

    public static Player asynchronousUpdate(PlayerDAO DAO, Player player) {
        return asynchronousUpdate(DAO, player, DAO.TABLENAME);
    }

    public static Player asynchronousUpdate(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return asynchronousUpdate(DAO, player, TABLENAME2);
    }

    public static Player asynchronousUpdate(final PlayerDAO DAO, final Player player,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(player, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(player);
        }
        return player;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        PlayerDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(PlayerDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final PlayerDAO DAO, final String TABLENAME2) {
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

        List<Player> players = getAll();
        for (Player player : players) {
            int n = DAO.insert2(player, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
