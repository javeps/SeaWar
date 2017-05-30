package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - attack_defense_info
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Attack_defense_infoInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Attack_defense_infoDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Attack_defense_infoInternal(){}

    public static Attack_defense_infoDAO DAO(){
        return Attack_defense_infoEntity.Attack_defense_infoDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Attack_defense_info[MAX];
    }
    private static Attack_defense_info[] FIXED = new Attack_defense_info[MAX];
    public static final Map<Integer, Attack_defense_info> vars = newSortedMap();
    public static final Map<String, Integer> varsByAttmcid = newSortedMap();

    private static final List<Attack_defense_info> fixedCache = newList();

    public static void put(Attack_defense_info attack_defense_info){
        if(attack_defense_info == null || attack_defense_info.id <= 0) return ;

        int id = attack_defense_info.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(attack_defense_info))
                	FIXED[id - 1] = attack_defense_info;
                if (!fixedCache.contains(attack_defense_info))
                	fixedCache.add(attack_defense_info);
            }
        } else {
            vars.put(id, attack_defense_info);
        }

        String attmcid = attack_defense_info.getAttmcid();
        varsByAttmcid.put(attmcid, id);

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByAttmcid.clear();
        FIXED = new Attack_defense_info[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Attack_defense_infoDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Attack_defense_infoDAO DAO, String TABLENAME2){
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

    public static void relocate(Attack_defense_infoDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Attack_defense_infoDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Attack_defense_infoDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Attack_defense_infoDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Attack_defense_infoDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Attack_defense_infoDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Attack_defense_infoDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Attack_defense_infoDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Attack_defense_infoDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Attack_defense_infoDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Attack_defense_infoDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Attack_defense_infoDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Attack_defense_infoDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Attack_defense_info[maxId(DAO)];

        List<Attack_defense_info> attack_defense_infos = DAO.selectAll();
        for (Attack_defense_info attack_defense_info : attack_defense_infos) {
            attack_defense_info.reset();
            put(attack_defense_info);
        }
    }

    public static Map toMap(Attack_defense_info attack_defense_info){
        return attack_defense_info.toMap();
    }

    public static List<Map> toMap(List<Attack_defense_info> attack_defense_infos){
        List<Map> ret = new Vector<Map>();
        for (Attack_defense_info attack_defense_info : attack_defense_infos){
            Map e = attack_defense_info.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Attack_defense_info> sortZh(List<Attack_defense_info> attack_defense_infos, final String key) {
        Collections.sort(attack_defense_infos, new Comparator<Attack_defense_info>() {
            public int compare(Attack_defense_info o1, Attack_defense_info o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return attack_defense_infos;
    }

    public static List<Attack_defense_info> sort(List<Attack_defense_info> attack_defense_infos, final String key) {
        Collections.sort(attack_defense_infos, new Comparator<Attack_defense_info>() {
            public int compare(Attack_defense_info o1, Attack_defense_info o2) {
                return o1.compareTo(o2, key);
            }
        });
        return attack_defense_infos;
    }

    public static List<Attack_defense_info> sort(List<Attack_defense_info> attack_defense_infos){
        Collections.sort(attack_defense_infos, new Comparator<Attack_defense_info>(){
            public int compare(Attack_defense_info o1, Attack_defense_info o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return attack_defense_infos;
    }

    public static List<Attack_defense_info> sortReverse(List<Attack_defense_info> attack_defense_infos){
        Collections.sort(attack_defense_infos, new Comparator<Attack_defense_info>(){
            public int compare(Attack_defense_info o1, Attack_defense_info o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return attack_defense_infos;
    }

    public static List<Attack_defense_info> sortAttmcid(List<Attack_defense_info> attack_defense_infos){
        Collections.sort(attack_defense_infos, new Comparator<Attack_defense_info>(){
            public int compare(Attack_defense_info o1, Attack_defense_info o2) {
                Object v1 = o1.getAttmcid();
                Object v2 = o2.getAttmcid();
                return compareTo(v1, v2);
            }
        });
        return attack_defense_infos;
    }

    public static List<Attack_defense_info> sortAttmcidRo(List<Attack_defense_info> attack_defense_infos){
        Collections.sort(attack_defense_infos, new Comparator<Attack_defense_info>(){
            public int compare(Attack_defense_info o1, Attack_defense_info o2) {
                Object v1 = o1.getAttmcid();
                Object v2 = o2.getAttmcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return attack_defense_infos;
    }

    public static Attack_defense_info insert(Attack_defense_info attack_defense_info) {
        Attack_defense_infoDAO DAO = DAO();
        return insert(DAO, attack_defense_info, DAO.TABLENAME);
    }

    public static Attack_defense_info insert(Attack_defense_infoDAO DAO, Attack_defense_info attack_defense_info) {
        return insert(DAO, attack_defense_info, DAO.TABLENAME);
    }

    public static Attack_defense_info insert(Attack_defense_info attack_defense_info, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return insert(DAO, attack_defense_info, TABLENAME2);
    }

    public static Attack_defense_info insert(Attack_defense_infoDAO DAO, Attack_defense_info attack_defense_info, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(attack_defense_info, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        attack_defense_info.id = n;
        if(cacheModel != NO_CACHE) put(attack_defense_info);

        return attack_defense_info;
    }

    public static Attack_defense_info asynchronousInsert(Attack_defense_info attack_defense_info) {
        Attack_defense_infoDAO DAO = DAO();
        return asynchronousInsert(DAO, attack_defense_info, DAO.TABLENAME);
    }
    public static Attack_defense_info asynchronousInsert(Attack_defense_infoDAO DAO, Attack_defense_info attack_defense_info) {
        return asynchronousInsert(DAO, attack_defense_info, DAO.TABLENAME);
    }
    public static Attack_defense_info asynchronousInsert(Attack_defense_info attack_defense_info, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return asynchronousInsert(DAO, attack_defense_info, TABLENAME2);
    }
    public static Attack_defense_info asynchronousInsert(Attack_defense_infoDAO DAO, Attack_defense_info attack_defense_info, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(attack_defense_info, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        attack_defense_info.id = n;
        if(cacheModel != NO_CACHE) put(attack_defense_info);
        return attack_defense_info;
    }
    public static Attack_defense_info insert2(Attack_defense_info attack_defense_info) {
        Attack_defense_infoDAO DAO = DAO();
        return insert2(DAO, attack_defense_info, DAO.TABLENAME);
    }

    public static Attack_defense_info insert2(Attack_defense_infoDAO DAO, Attack_defense_info attack_defense_info) {
        return insert2(DAO, attack_defense_info, DAO.TABLENAME);
    }

    public static Attack_defense_info insert2(Attack_defense_info attack_defense_info, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return insert2(DAO, attack_defense_info, TABLENAME2);
    }

    public static Attack_defense_info insert2(Attack_defense_infoDAO DAO, Attack_defense_info attack_defense_info, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(attack_defense_info, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        attack_defense_info.id = n;
        if(cacheModel != NO_CACHE) put(attack_defense_info);

        return attack_defense_info;
    }

    public static Attack_defense_info asynchronousInsert2(Attack_defense_info attack_defense_info) {
        Attack_defense_infoDAO DAO = DAO();
        return asynchronousInsert2(DAO, attack_defense_info, DAO.TABLENAME);
    }
    public static Attack_defense_info asynchronousInsert2(Attack_defense_infoDAO DAO, Attack_defense_info attack_defense_info) {
        return asynchronousInsert2(DAO, attack_defense_info, DAO.TABLENAME);
    }
    public static Attack_defense_info asynchronousInsert2(Attack_defense_info attack_defense_info, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return asynchronousInsert2(DAO, attack_defense_info, TABLENAME2);
    }
    public static Attack_defense_info asynchronousInsert2(Attack_defense_infoDAO DAO, Attack_defense_info attack_defense_info, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        attack_defense_info.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(attack_defense_info, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(attack_defense_info);
        return attack_defense_info;
    }
    public static int[] insert(List<Attack_defense_info> attack_defense_infos) {
        Attack_defense_infoDAO DAO = DAO();
        return insert(DAO, attack_defense_infos, DAO.TABLENAME);
    }

    public static int[] insert(Attack_defense_infoDAO DAO, List<Attack_defense_info> attack_defense_infos) {
        return insert(DAO, attack_defense_infos, DAO.TABLENAME);
    }

    public static int[] insert(List<Attack_defense_info> attack_defense_infos, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return insert(DAO, attack_defense_infos, TABLENAME2);
    }

    public static int[] insert(Attack_defense_infoDAO DAO, List<Attack_defense_info> attack_defense_infos, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(attack_defense_infos, TABLENAME2);
            int n = 0;
            for(Attack_defense_info attack_defense_info : attack_defense_infos){
                attack_defense_info.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[attack_defense_infos.size()];
        int n = 0;
        for(Attack_defense_info attack_defense_info : attack_defense_infos){
            attack_defense_info = insert(DAO, attack_defense_info, TABLENAME2);
            ret[n++] = (attack_defense_info == null) ? 0 : (int)attack_defense_info.id;
        }
        return ret;
    }

    public static int delete(Attack_defense_info attack_defense_info) {
        int id = attack_defense_info.id;
        Attack_defense_infoDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Attack_defense_infoDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Attack_defense_infoDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Attack_defense_infoDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Attack_defense_info attack_defense_info) {
        int id = attack_defense_info.id;
        Attack_defense_infoDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Attack_defense_infoDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Attack_defense_infoDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Attack_defense_infoDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Attack_defense_infoDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Attack_defense_infoDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Attack_defense_infoDAO DAO, int[] ids,String TABLENAME2) {
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
        Attack_defense_infoDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Attack_defense_infoDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Attack_defense_infoDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Attack_defense_info> beans) {
        Attack_defense_infoDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Attack_defense_info> beans, Attack_defense_infoDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Attack_defense_info> beans, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Attack_defense_info> beans, final Attack_defense_infoDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Attack_defense_info attack_defense_info : beans){
                int id = attack_defense_info.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Attack_defense_info> getAll() {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getAll(Attack_defense_infoDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getAll(String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Attack_defense_info> getAll(final Attack_defense_infoDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Attack_defense_info> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Attack_defense_info> getNoSortAll() {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getNoSortAll(Attack_defense_infoDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getNoSortAll(String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Attack_defense_info> getNoSortAll(final Attack_defense_infoDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Attack_defense_info> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Attack_defense_info> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Attack_defense_info attack_defense_info = FIXED[i];
                if (attack_defense_info != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Attack_defense_info> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Attack_defense_info> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Attack_defense_infoDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Attack_defense_infoDAO DAO, final String TABLENAME2) {
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
        Attack_defense_infoDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Attack_defense_infoDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Attack_defense_infoDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Attack_defense_info> getIn(List<Integer> keys) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getIn(List<Integer> keys, Attack_defense_infoDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getIn(List<Integer> keys, String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Attack_defense_info> getIn(final List<Integer> keys, final Attack_defense_infoDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Attack_defense_info> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Attack_defense_info> getNoSortIn(List<Integer> keys) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getNoSortIn(List<Integer> keys, Attack_defense_infoDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Attack_defense_info> getNoSortIn(final List<Integer> keys, final Attack_defense_infoDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Attack_defense_info> result = newList();
            for (int key : keys) {
                Attack_defense_info attack_defense_info = getByKeyFromMemory(key);
                if( attack_defense_info == null ) continue;
                result.add(attack_defense_info);
            }
            return result;
        }
    }

    public static List<Attack_defense_info> getLast(int num) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getLast(Attack_defense_infoDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getLast(int num, String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Attack_defense_info> getLast(final Attack_defense_infoDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Attack_defense_info> result = newList();
            List<Attack_defense_info> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Attack_defense_info last() {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Attack_defense_info last(Attack_defense_infoDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Attack_defense_info last(String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Attack_defense_info last(final Attack_defense_infoDAO DAO, final String TABLENAME2) {
        Attack_defense_info result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Attack_defense_infoDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Attack_defense_infoDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Attack_defense_infoDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Attack_defense_info> getGtKey(int id) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getGtKey(Attack_defense_infoDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getGtKey(int id, String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Attack_defense_info> getGtKey(final Attack_defense_infoDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Attack_defense_info> result = newList();
            List<Attack_defense_info> attack_defense_infos = getAll();
            for (Attack_defense_info attack_defense_info : attack_defense_infos) {
                if(attack_defense_info.id <= id) continue;
                result.add(attack_defense_info);
            }
            return result;
        }
    }

    public static Attack_defense_info getByKey(int id) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Attack_defense_info getByKey(Attack_defense_infoDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Attack_defense_info getByKey(int id, String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Attack_defense_info getByKey(final Attack_defense_infoDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Attack_defense_info getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Attack_defense_info deleteFromMemory(final int id) {
        Attack_defense_info attack_defense_info;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            attack_defense_info = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(attack_defense_info);
        } else {
            attack_defense_info = vars.remove(id);
        }
        if(attack_defense_info == null) return null;

        String attmcid = attack_defense_info.getAttmcid();
        varsByAttmcid.remove(attmcid);

        return attack_defense_info;
    }

    public static List<Attack_defense_info> getByPage(int page, int size) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getByPage(Attack_defense_infoDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getByPage(int page, int size, String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Attack_defense_info> getByPage(final Attack_defense_infoDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Attack_defense_info> result = newList();
            List<Attack_defense_info> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Attack_defense_infoDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Attack_defense_infoDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Attack_defense_info getByAttmcid(String attmcid) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAttmcid(DAO, attmcid, DAO.TABLENAME);
    }

    public static Attack_defense_info getByAttmcid(Attack_defense_infoDAO DAO, String attmcid) {
        return getByAttmcid(DAO, attmcid, DAO.TABLENAME);
    }

    public static Attack_defense_info getByAttmcid(String attmcid, String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAttmcid(DAO, attmcid, TABLENAME2);
    }

    public static Attack_defense_info getByAttmcid(final Attack_defense_infoDAO DAO, final String attmcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAttmcid(attmcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByAttmcid.get(attmcid);
            if(id == null) return null;
            Attack_defense_info result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getAttmcid().equals(attmcid)){
                varsByAttmcid.remove(attmcid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeAttmcid(String attmcid) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeAttmcid(DAO, attmcid, DAO.TABLENAME);
    }

    public static int countLikeAttmcid(Attack_defense_infoDAO DAO, String attmcid) {
        return countLikeAttmcid(DAO, attmcid, DAO.TABLENAME);
    }

    public static int countLikeAttmcid(String attmcid, String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeAttmcid(DAO, attmcid, TABLENAME2);
    }

    public static int countLikeAttmcid(final Attack_defense_infoDAO DAO, final String attmcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeAttmcid(attmcid, TABLENAME2);
        }
        List<Attack_defense_info> attack_defense_infos = getLikeAttmcid(DAO, attmcid, TABLENAME2);
        return attack_defense_infos.size();
    }

    public static List<Attack_defense_info> getLikeAttmcid(String attmcid) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeAttmcid(DAO, attmcid, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getLikeAttmcid(Attack_defense_infoDAO DAO, String attmcid) {
        return getLikeAttmcid(DAO, attmcid, DAO.TABLENAME);
    }

    public static List<Attack_defense_info> getLikeAttmcid(String attmcid, String TABLENAME2) {
        Attack_defense_infoDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeAttmcid(DAO, attmcid, TABLENAME2);
    }

    public static List<Attack_defense_info> getLikeAttmcid(final Attack_defense_infoDAO DAO, final String attmcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeAttmcid(attmcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Attack_defense_info> result = newList();
            List<Attack_defense_info> attack_defense_infos = getAll(DAO, TABLENAME2);
            for(Attack_defense_info e : attack_defense_infos){
                String _var = e.getAttmcid();
                if(_var.indexOf(attmcid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Attack_defense_info update(Attack_defense_info attack_defense_info) {
        Attack_defense_infoDAO DAO = DAO();
        return update(DAO, attack_defense_info, DAO.TABLENAME);
    }

    public static Attack_defense_info update(Attack_defense_infoDAO DAO, Attack_defense_info attack_defense_info) {
        return update(DAO, attack_defense_info, DAO.TABLENAME);
    }

    public static Attack_defense_info update(Attack_defense_info attack_defense_info, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return update(DAO, attack_defense_info, TABLENAME2);
    }

    public static Attack_defense_info update(final Attack_defense_infoDAO DAO, final Attack_defense_info attack_defense_info,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(attack_defense_info, TABLENAME2);
            if(n == -1) 
                return attack_defense_info;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(attack_defense_info);
        }
        return attack_defense_info;
    }

    public static Attack_defense_info asynchronousUpdate(Attack_defense_info attack_defense_info) {
        Attack_defense_infoDAO DAO = DAO();
        return asynchronousUpdate(DAO, attack_defense_info, DAO.TABLENAME);
    }

    public static Attack_defense_info asynchronousUpdate(Attack_defense_infoDAO DAO, Attack_defense_info attack_defense_info) {
        return asynchronousUpdate(DAO, attack_defense_info, DAO.TABLENAME);
    }

    public static Attack_defense_info asynchronousUpdate(Attack_defense_info attack_defense_info, String TABLENAME2) {
        Attack_defense_infoDAO DAO = DAO();
        return asynchronousUpdate(DAO, attack_defense_info, TABLENAME2);
    }

    public static Attack_defense_info asynchronousUpdate(final Attack_defense_infoDAO DAO, final Attack_defense_info attack_defense_info,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(attack_defense_info, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(attack_defense_info);
        }
        return attack_defense_info;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Attack_defense_infoDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Attack_defense_infoDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Attack_defense_infoDAO DAO, final String TABLENAME2) {
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

        List<Attack_defense_info> attack_defense_infos = getAll();
        for (Attack_defense_info attack_defense_info : attack_defense_infos) {
            int n = DAO.insert2(attack_defense_info, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
