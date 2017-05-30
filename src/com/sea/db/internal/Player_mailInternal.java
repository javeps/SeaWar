package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - player_mail
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Player_mailInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Player_mailDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Player_mailInternal(){}

    public static Player_mailDAO DAO(){
        return Player_mailEntity.Player_mailDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Player_mail[MAX];
    }
    private static Player_mail[] FIXED = new Player_mail[MAX];
    public static final Map<Integer, Player_mail> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByTypeToid = newSortedMap();
    public static final Map<String, Set<Integer>> varsByTypeFromid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByType = newSortedMap();
    public static final Map<Integer, Integer> varsByMcid = newSortedMap();

    private static final List<Player_mail> fixedCache = newList();

    public static void put(Player_mail player_mail){
        if(player_mail == null || player_mail.id <= 0) return ;

        int id = player_mail.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(player_mail))
                	FIXED[id - 1] = player_mail;
                if (!fixedCache.contains(player_mail))
                	fixedCache.add(player_mail);
            }
        } else {
            vars.put(id, player_mail);
        }

        { // type_3
            int vtype = player_mail.getType();
            int vtoId = player_mail.getToid();
            String vkey = vtype+ "-" +vtoId;
            Set m1 = varsByTypeToid.get(vkey);
            if(m1 == null){
                m1 = newSortedSet();
                varsByTypeToid.put(vkey, m1);
            }
            m1.add(id);
        }

        { // type_2
            int vtype = player_mail.getType();
            int vfromId = player_mail.getFromid();
            String vkey = vtype+ "-" +vfromId;
            Set m2 = varsByTypeFromid.get(vkey);
            if(m2 == null){
                m2 = newSortedSet();
                varsByTypeFromid.put(vkey, m2);
            }
            m2.add(id);
        }

        int type = player_mail.getType();
        Set m3 = varsByType.get(type);
        if(m3 == null){
            m3 = newSortedSet();
            varsByType.put(type, m3);
        }
        m3.add(id);

        int mcid = player_mail.getMcid();
        varsByMcid.put(mcid, id);

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByTypeToid.clear();
        varsByTypeFromid.clear();
        varsByType.clear();
        varsByMcid.clear();
        FIXED = new Player_mail[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Player_mailDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Player_mailDAO DAO, String TABLENAME2){
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

    public static void relocate(Player_mailDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Player_mailDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Player_mailDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Player_mailDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Player_mailDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Player_mailDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Player_mailDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Player_mailDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Player_mailDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Player_mailDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Player_mailDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Player_mailDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Player_mailDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Player_mail[maxId(DAO)];

        List<Player_mail> player_mails = DAO.selectAll();
        for (Player_mail player_mail : player_mails) {
            player_mail.reset();
            put(player_mail);
        }
    }

    public static Map toMap(Player_mail player_mail){
        return player_mail.toMap();
    }

    public static List<Map> toMap(List<Player_mail> player_mails){
        List<Map> ret = new Vector<Map>();
        for (Player_mail player_mail : player_mails){
            Map e = player_mail.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Player_mail> sortZh(List<Player_mail> player_mails, final String key) {
        Collections.sort(player_mails, new Comparator<Player_mail>() {
            public int compare(Player_mail o1, Player_mail o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return player_mails;
    }

    public static List<Player_mail> sort(List<Player_mail> player_mails, final String key) {
        Collections.sort(player_mails, new Comparator<Player_mail>() {
            public int compare(Player_mail o1, Player_mail o2) {
                return o1.compareTo(o2, key);
            }
        });
        return player_mails;
    }

    public static List<Player_mail> sort(List<Player_mail> player_mails){
        Collections.sort(player_mails, new Comparator<Player_mail>(){
            public int compare(Player_mail o1, Player_mail o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return player_mails;
    }

    public static List<Player_mail> sortReverse(List<Player_mail> player_mails){
        Collections.sort(player_mails, new Comparator<Player_mail>(){
            public int compare(Player_mail o1, Player_mail o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return player_mails;
    }

    public static List<Player_mail> sortType(List<Player_mail> player_mails){
        Collections.sort(player_mails, new Comparator<Player_mail>(){
            public int compare(Player_mail o1, Player_mail o2) {
                Object v1 = o1.getType();
                Object v2 = o2.getType();
                return compareTo(v1, v2);
            }
        });
        return player_mails;
    }

    public static List<Player_mail> sortTypeRo(List<Player_mail> player_mails){
        Collections.sort(player_mails, new Comparator<Player_mail>(){
            public int compare(Player_mail o1, Player_mail o2) {
                Object v1 = o1.getType();
                Object v2 = o2.getType();
                return 0 - compareTo(v1, v2);
            };
        });
        return player_mails;
    }

    public static List<Player_mail> sortMcid(List<Player_mail> player_mails){
        Collections.sort(player_mails, new Comparator<Player_mail>(){
            public int compare(Player_mail o1, Player_mail o2) {
                Object v1 = o1.getMcid();
                Object v2 = o2.getMcid();
                return compareTo(v1, v2);
            }
        });
        return player_mails;
    }

    public static List<Player_mail> sortMcidRo(List<Player_mail> player_mails){
        Collections.sort(player_mails, new Comparator<Player_mail>(){
            public int compare(Player_mail o1, Player_mail o2) {
                Object v1 = o1.getMcid();
                Object v2 = o2.getMcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return player_mails;
    }

    public static Player_mail insert(Player_mail player_mail) {
        Player_mailDAO DAO = DAO();
        return insert(DAO, player_mail, DAO.TABLENAME);
    }

    public static Player_mail insert(Player_mailDAO DAO, Player_mail player_mail) {
        return insert(DAO, player_mail, DAO.TABLENAME);
    }

    public static Player_mail insert(Player_mail player_mail, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return insert(DAO, player_mail, TABLENAME2);
    }

    public static Player_mail insert(Player_mailDAO DAO, Player_mail player_mail, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(player_mail, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_mail.id = n;
        if(cacheModel != NO_CACHE) put(player_mail);

        return player_mail;
    }

    public static Player_mail asynchronousInsert(Player_mail player_mail) {
        Player_mailDAO DAO = DAO();
        return asynchronousInsert(DAO, player_mail, DAO.TABLENAME);
    }
    public static Player_mail asynchronousInsert(Player_mailDAO DAO, Player_mail player_mail) {
        return asynchronousInsert(DAO, player_mail, DAO.TABLENAME);
    }
    public static Player_mail asynchronousInsert(Player_mail player_mail, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return asynchronousInsert(DAO, player_mail, TABLENAME2);
    }
    public static Player_mail asynchronousInsert(Player_mailDAO DAO, Player_mail player_mail, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(player_mail, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        player_mail.id = n;
        if(cacheModel != NO_CACHE) put(player_mail);
        return player_mail;
    }
    public static Player_mail insert2(Player_mail player_mail) {
        Player_mailDAO DAO = DAO();
        return insert2(DAO, player_mail, DAO.TABLENAME);
    }

    public static Player_mail insert2(Player_mailDAO DAO, Player_mail player_mail) {
        return insert2(DAO, player_mail, DAO.TABLENAME);
    }

    public static Player_mail insert2(Player_mail player_mail, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return insert2(DAO, player_mail, TABLENAME2);
    }

    public static Player_mail insert2(Player_mailDAO DAO, Player_mail player_mail, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(player_mail, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player_mail.id = n;
        if(cacheModel != NO_CACHE) put(player_mail);

        return player_mail;
    }

    public static Player_mail asynchronousInsert2(Player_mail player_mail) {
        Player_mailDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_mail, DAO.TABLENAME);
    }
    public static Player_mail asynchronousInsert2(Player_mailDAO DAO, Player_mail player_mail) {
        return asynchronousInsert2(DAO, player_mail, DAO.TABLENAME);
    }
    public static Player_mail asynchronousInsert2(Player_mail player_mail, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return asynchronousInsert2(DAO, player_mail, TABLENAME2);
    }
    public static Player_mail asynchronousInsert2(Player_mailDAO DAO, Player_mail player_mail, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        player_mail.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(player_mail, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(player_mail);
        return player_mail;
    }
    public static int[] insert(List<Player_mail> player_mails) {
        Player_mailDAO DAO = DAO();
        return insert(DAO, player_mails, DAO.TABLENAME);
    }

    public static int[] insert(Player_mailDAO DAO, List<Player_mail> player_mails) {
        return insert(DAO, player_mails, DAO.TABLENAME);
    }

    public static int[] insert(List<Player_mail> player_mails, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return insert(DAO, player_mails, TABLENAME2);
    }

    public static int[] insert(Player_mailDAO DAO, List<Player_mail> player_mails, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(player_mails, TABLENAME2);
            int n = 0;
            for(Player_mail player_mail : player_mails){
                player_mail.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[player_mails.size()];
        int n = 0;
        for(Player_mail player_mail : player_mails){
            player_mail = insert(DAO, player_mail, TABLENAME2);
            ret[n++] = (player_mail == null) ? 0 : (int)player_mail.id;
        }
        return ret;
    }

    public static int delete(Player_mail player_mail) {
        int id = player_mail.id;
        Player_mailDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Player_mailDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Player_mailDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Player_mailDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Player_mail player_mail) {
        int id = player_mail.id;
        Player_mailDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Player_mailDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Player_mailDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Player_mailDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Player_mailDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Player_mailDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Player_mailDAO DAO, int[] ids,String TABLENAME2) {
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
        Player_mailDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Player_mailDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Player_mailDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Player_mail> beans) {
        Player_mailDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_mail> beans, Player_mailDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player_mail> beans, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Player_mail> beans, final Player_mailDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Player_mail player_mail : beans){
                int id = player_mail.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Player_mail> getAll() {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_mail> getAll(Player_mailDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_mail> getAll(String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Player_mail> getAll(final Player_mailDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_mail> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_mail> getNoSortAll() {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_mail> getNoSortAll(Player_mailDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player_mail> getNoSortAll(String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Player_mail> getNoSortAll(final Player_mailDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Player_mail> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_mail> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Player_mail player_mail = FIXED[i];
                if (player_mail != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Player_mail> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Player_mail> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Player_mailDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Player_mailDAO DAO, final String TABLENAME2) {
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
        Player_mailDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Player_mailDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Player_mailDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Player_mail> getIn(List<Integer> keys) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_mail> getIn(List<Integer> keys, Player_mailDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_mail> getIn(List<Integer> keys, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_mail> getIn(final List<Integer> keys, final Player_mailDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_mail> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player_mail> getNoSortIn(List<Integer> keys) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_mail> getNoSortIn(List<Integer> keys, Player_mailDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player_mail> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Player_mail> getNoSortIn(final List<Integer> keys, final Player_mailDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Player_mail> result = newList();
            for (int key : keys) {
                Player_mail player_mail = getByKeyFromMemory(key);
                if( player_mail == null ) continue;
                result.add(player_mail);
            }
            return result;
        }
    }

    public static List<Player_mail> getLast(int num) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_mail> getLast(Player_mailDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player_mail> getLast(int num, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Player_mail> getLast(final Player_mailDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Player_mail> result = newList();
            List<Player_mail> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Player_mail last() {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_mail last(Player_mailDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Player_mail last(String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Player_mail last(final Player_mailDAO DAO, final String TABLENAME2) {
        Player_mail result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Player_mailDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Player_mailDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Player_mailDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Player_mail> getGtKey(int id) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_mail> getGtKey(Player_mailDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Player_mail> getGtKey(int id, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Player_mail> getGtKey(final Player_mailDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_mail> result = newList();
            List<Player_mail> player_mails = getAll();
            for (Player_mail player_mail : player_mails) {
                if(player_mail.id <= id) continue;
                result.add(player_mail);
            }
            return result;
        }
    }

    public static Player_mail getByKey(int id) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_mail getByKey(Player_mailDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Player_mail getByKey(int id, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Player_mail getByKey(final Player_mailDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Player_mail getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Player_mail deleteFromMemory(final int id) {
        Player_mail player_mail;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            player_mail = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(player_mail);
        } else {
            player_mail = vars.remove(id);
        }
        if(player_mail == null) return null;

        { // type_3
            int vtype = player_mail.getType();
            int vtoId = player_mail.getToid();
            String vkey = vtype+ "-" +vtoId;
            Set m1 = varsByTypeToid.get(vkey);
            if(m1 != null)
                m1.remove(id);
        }

        { // type_2
            int vtype = player_mail.getType();
            int vfromId = player_mail.getFromid();
            String vkey = vtype+ "-" +vfromId;
            Set m2 = varsByTypeFromid.get(vkey);
            if(m2 != null)
                m2.remove(id);
        }

        int type = player_mail.getType();
        Set m3 = varsByType.get(type);
        if(m3 != null)
            m3.remove(id);

        int mcid = player_mail.getMcid();
        varsByMcid.remove(mcid);

        return player_mail;
    }

    public static List<Player_mail> getByPage(int page, int size) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_mail> getByPage(Player_mailDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player_mail> getByPage(int page, int size, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Player_mail> getByPage(final Player_mailDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player_mail> result = newList();
            List<Player_mail> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Player_mailDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Player_mailDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByTypeToid(Integer type, Integer toId) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeToid(DAO, type, toId, DAO.TABLENAME);
    }

    public static int countByTypeToid(Player_mailDAO DAO, Integer type, Integer toId) {
        return countByTypeToid(DAO, type, toId, DAO.TABLENAME);
    }

    public static int countByTypeToid(Integer type, Integer toId, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeToid(DAO, type, toId, TABLENAME2);
    }

    public static int countByTypeToid(final Player_mailDAO DAO, Integer type, Integer toId, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByTypeToid(type, toId, TABLENAME2);
        }
        List<Player_mail> player_mails = getByTypeToid(type, toId, TABLENAME2);
        return player_mails.size();
    }

    public static List<Player_mail> getByTypeToid(Integer type, Integer toId) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeToid(DAO, type, toId, DAO.TABLENAME);
    }

    public static List<Player_mail> getByTypeToid(Player_mailDAO DAO, Integer type, Integer toId) {
        return getByTypeToid(DAO, type, toId, DAO.TABLENAME);
    }

    public static List<Player_mail> getByTypeToid(Integer type, Integer toId, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeToid(DAO, type, toId, TABLENAME2);
    }

    public static List<Player_mail> getByTypeToid(final Player_mailDAO DAO, Integer type, Integer toId, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTypeToid(type, toId, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player_mail> result = newList();
            String vkey = type+"-"+toId;
            Set<Integer> m1 = varsByTypeToid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Player_mail e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _type = e.getType();
                int _toId = e.getToid();
                String _key = _type + "-" + _toId;
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByTypeFromid(Integer type, Integer fromId) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeFromid(DAO, type, fromId, DAO.TABLENAME);
    }

    public static int countByTypeFromid(Player_mailDAO DAO, Integer type, Integer fromId) {
        return countByTypeFromid(DAO, type, fromId, DAO.TABLENAME);
    }

    public static int countByTypeFromid(Integer type, Integer fromId, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeFromid(DAO, type, fromId, TABLENAME2);
    }

    public static int countByTypeFromid(final Player_mailDAO DAO, Integer type, Integer fromId, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByTypeFromid(type, fromId, TABLENAME2);
        }
        List<Player_mail> player_mails = getByTypeFromid(type, fromId, TABLENAME2);
        return player_mails.size();
    }

    public static List<Player_mail> getByTypeFromid(Integer type, Integer fromId) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeFromid(DAO, type, fromId, DAO.TABLENAME);
    }

    public static List<Player_mail> getByTypeFromid(Player_mailDAO DAO, Integer type, Integer fromId) {
        return getByTypeFromid(DAO, type, fromId, DAO.TABLENAME);
    }

    public static List<Player_mail> getByTypeFromid(Integer type, Integer fromId, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeFromid(DAO, type, fromId, TABLENAME2);
    }

    public static List<Player_mail> getByTypeFromid(final Player_mailDAO DAO, Integer type, Integer fromId, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTypeFromid(type, fromId, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player_mail> result = newList();
            String vkey = type+"-"+fromId;
            Set<Integer> m1 = varsByTypeFromid.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Player_mail e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _type = e.getType();
                int _fromId = e.getFromid();
                String _key = _type + "-" + _fromId;
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByType(Integer type) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByType(DAO, type, DAO.TABLENAME);
    }

    public static int countByType(Player_mailDAO DAO, Integer type) {
        return countByType(DAO, type, DAO.TABLENAME);
    }

    public static int countByType(Integer type, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByType(DAO, type, TABLENAME2);
    }

    public static int countByType(final Player_mailDAO DAO, final Integer type,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByType(type, TABLENAME2);
        }
        List<Player_mail> player_mails = getByType(DAO, type, TABLENAME2);
        return player_mails.size();
    }

    public static List<Player_mail> getByType(Integer type) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByType(DAO, type, DAO.TABLENAME);
    }

    public static List<Player_mail> getByType(Player_mailDAO DAO, Integer type) {
        return getByType(DAO, type, DAO.TABLENAME);
    }

    public static List<Player_mail> getByType(Integer type, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByType(DAO, type, TABLENAME2);
    }

    public static List<Player_mail> getByType(final Player_mailDAO DAO, final Integer type,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByType(type, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Player_mail> result = newList();
            Set<Integer> m1 = varsByType.get(type);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Player_mail e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _type = e.getType();
                if(_type != type){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Player_mail getByMcid(Integer mcid) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByMcid(DAO, mcid, DAO.TABLENAME);
    }

    public static Player_mail getByMcid(Player_mailDAO DAO, Integer mcid) {
        return getByMcid(DAO, mcid, DAO.TABLENAME);
    }

    public static Player_mail getByMcid(Integer mcid, String TABLENAME2) {
        Player_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByMcid(DAO, mcid, TABLENAME2);
    }

    public static Player_mail getByMcid(final Player_mailDAO DAO, final int mcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByMcid(mcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByMcid.get(mcid);
            if(id == null) return null;
            Player_mail result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getMcid() != mcid){
                varsByMcid.remove(mcid);
                return null;
            }
            return result;
        }
    }

    public static Player_mail update(Player_mail player_mail) {
        Player_mailDAO DAO = DAO();
        return update(DAO, player_mail, DAO.TABLENAME);
    }

    public static Player_mail update(Player_mailDAO DAO, Player_mail player_mail) {
        return update(DAO, player_mail, DAO.TABLENAME);
    }

    public static Player_mail update(Player_mail player_mail, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return update(DAO, player_mail, TABLENAME2);
    }

    public static Player_mail update(final Player_mailDAO DAO, final Player_mail player_mail,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(player_mail, TABLENAME2);
            if(n == -1) 
                return player_mail;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(player_mail);
        }
        return player_mail;
    }

    public static Player_mail asynchronousUpdate(Player_mail player_mail) {
        Player_mailDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_mail, DAO.TABLENAME);
    }

    public static Player_mail asynchronousUpdate(Player_mailDAO DAO, Player_mail player_mail) {
        return asynchronousUpdate(DAO, player_mail, DAO.TABLENAME);
    }

    public static Player_mail asynchronousUpdate(Player_mail player_mail, String TABLENAME2) {
        Player_mailDAO DAO = DAO();
        return asynchronousUpdate(DAO, player_mail, TABLENAME2);
    }

    public static Player_mail asynchronousUpdate(final Player_mailDAO DAO, final Player_mail player_mail,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(player_mail, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(player_mail);
        }
        return player_mail;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Player_mailDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Player_mailDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Player_mailDAO DAO, final String TABLENAME2) {
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

        List<Player_mail> player_mails = getAll();
        for (Player_mail player_mail : player_mails) {
            int n = DAO.insert2(player_mail, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
