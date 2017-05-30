package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - attack_mail
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Attack_mailInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Attack_mailDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Attack_mailInternal(){}

    public static Attack_mailDAO DAO(){
        return Attack_mailEntity.Attack_mailDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Attack_mail[MAX];
    }
    private static Attack_mail[] FIXED = new Attack_mail[MAX];
    public static final Map<Integer, Attack_mail> vars = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByBeattpcid = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByAttpcid = newSortedMap();
    public static final Map<String, Integer> varsByAttcid = newSortedMap();

    private static final List<Attack_mail> fixedCache = newList();

    public static void put(Attack_mail attack_mail){
        if(attack_mail == null || attack_mail.id <= 0) return ;

        int id = attack_mail.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(attack_mail))
                	FIXED[id - 1] = attack_mail;
                if (!fixedCache.contains(attack_mail))
                	fixedCache.add(attack_mail);
            }
        } else {
            vars.put(id, attack_mail);
        }

        int beAttPcid = attack_mail.getBeattpcid();
        Set m1 = varsByBeattpcid.get(beAttPcid);
        if(m1 == null){
            m1 = newSortedSet();
            varsByBeattpcid.put(beAttPcid, m1);
        }
        m1.add(id);

        int attPcid = attack_mail.getAttpcid();
        Set m2 = varsByAttpcid.get(attPcid);
        if(m2 == null){
            m2 = newSortedSet();
            varsByAttpcid.put(attPcid, m2);
        }
        m2.add(id);

        String attcid = attack_mail.getAttcid();
        varsByAttcid.put(attcid, id);

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByBeattpcid.clear();
        varsByAttpcid.clear();
        varsByAttcid.clear();
        FIXED = new Attack_mail[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Attack_mailDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Attack_mailDAO DAO, String TABLENAME2){
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

    public static void relocate(Attack_mailDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Attack_mailDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Attack_mailDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Attack_mailDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Attack_mailDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Attack_mailDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Attack_mailDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Attack_mailDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Attack_mailDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Attack_mailDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Attack_mailDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Attack_mailDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Attack_mailDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Attack_mail[maxId(DAO)];

        List<Attack_mail> attack_mails = DAO.selectAll();
        for (Attack_mail attack_mail : attack_mails) {
            attack_mail.reset();
            put(attack_mail);
        }
    }

    public static Map toMap(Attack_mail attack_mail){
        return attack_mail.toMap();
    }

    public static List<Map> toMap(List<Attack_mail> attack_mails){
        List<Map> ret = new Vector<Map>();
        for (Attack_mail attack_mail : attack_mails){
            Map e = attack_mail.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Attack_mail> sortZh(List<Attack_mail> attack_mails, final String key) {
        Collections.sort(attack_mails, new Comparator<Attack_mail>() {
            public int compare(Attack_mail o1, Attack_mail o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return attack_mails;
    }

    public static List<Attack_mail> sort(List<Attack_mail> attack_mails, final String key) {
        Collections.sort(attack_mails, new Comparator<Attack_mail>() {
            public int compare(Attack_mail o1, Attack_mail o2) {
                return o1.compareTo(o2, key);
            }
        });
        return attack_mails;
    }

    public static List<Attack_mail> sort(List<Attack_mail> attack_mails){
        Collections.sort(attack_mails, new Comparator<Attack_mail>(){
            public int compare(Attack_mail o1, Attack_mail o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return attack_mails;
    }

    public static List<Attack_mail> sortReverse(List<Attack_mail> attack_mails){
        Collections.sort(attack_mails, new Comparator<Attack_mail>(){
            public int compare(Attack_mail o1, Attack_mail o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return attack_mails;
    }

    public static List<Attack_mail> sortBeattpcid(List<Attack_mail> attack_mails){
        Collections.sort(attack_mails, new Comparator<Attack_mail>(){
            public int compare(Attack_mail o1, Attack_mail o2) {
                Object v1 = o1.getBeattpcid();
                Object v2 = o2.getBeattpcid();
                return compareTo(v1, v2);
            }
        });
        return attack_mails;
    }

    public static List<Attack_mail> sortBeattpcidRo(List<Attack_mail> attack_mails){
        Collections.sort(attack_mails, new Comparator<Attack_mail>(){
            public int compare(Attack_mail o1, Attack_mail o2) {
                Object v1 = o1.getBeattpcid();
                Object v2 = o2.getBeattpcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return attack_mails;
    }

    public static List<Attack_mail> sortAttpcid(List<Attack_mail> attack_mails){
        Collections.sort(attack_mails, new Comparator<Attack_mail>(){
            public int compare(Attack_mail o1, Attack_mail o2) {
                Object v1 = o1.getAttpcid();
                Object v2 = o2.getAttpcid();
                return compareTo(v1, v2);
            }
        });
        return attack_mails;
    }

    public static List<Attack_mail> sortAttpcidRo(List<Attack_mail> attack_mails){
        Collections.sort(attack_mails, new Comparator<Attack_mail>(){
            public int compare(Attack_mail o1, Attack_mail o2) {
                Object v1 = o1.getAttpcid();
                Object v2 = o2.getAttpcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return attack_mails;
    }

    public static List<Attack_mail> sortAttcid(List<Attack_mail> attack_mails){
        Collections.sort(attack_mails, new Comparator<Attack_mail>(){
            public int compare(Attack_mail o1, Attack_mail o2) {
                Object v1 = o1.getAttcid();
                Object v2 = o2.getAttcid();
                return compareTo(v1, v2);
            }
        });
        return attack_mails;
    }

    public static List<Attack_mail> sortAttcidRo(List<Attack_mail> attack_mails){
        Collections.sort(attack_mails, new Comparator<Attack_mail>(){
            public int compare(Attack_mail o1, Attack_mail o2) {
                Object v1 = o1.getAttcid();
                Object v2 = o2.getAttcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return attack_mails;
    }

    public static Attack_mail insert(Attack_mail attack_mail) {
        Attack_mailDAO DAO = DAO();
        return insert(DAO, attack_mail, DAO.TABLENAME);
    }

    public static Attack_mail insert(Attack_mailDAO DAO, Attack_mail attack_mail) {
        return insert(DAO, attack_mail, DAO.TABLENAME);
    }

    public static Attack_mail insert(Attack_mail attack_mail, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return insert(DAO, attack_mail, TABLENAME2);
    }

    public static Attack_mail insert(Attack_mailDAO DAO, Attack_mail attack_mail, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(attack_mail, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        attack_mail.id = n;
        if(cacheModel != NO_CACHE) put(attack_mail);

        return attack_mail;
    }

    public static Attack_mail asynchronousInsert(Attack_mail attack_mail) {
        Attack_mailDAO DAO = DAO();
        return asynchronousInsert(DAO, attack_mail, DAO.TABLENAME);
    }
    public static Attack_mail asynchronousInsert(Attack_mailDAO DAO, Attack_mail attack_mail) {
        return asynchronousInsert(DAO, attack_mail, DAO.TABLENAME);
    }
    public static Attack_mail asynchronousInsert(Attack_mail attack_mail, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return asynchronousInsert(DAO, attack_mail, TABLENAME2);
    }
    public static Attack_mail asynchronousInsert(Attack_mailDAO DAO, Attack_mail attack_mail, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(attack_mail, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        attack_mail.id = n;
        if(cacheModel != NO_CACHE) put(attack_mail);
        return attack_mail;
    }
    public static Attack_mail insert2(Attack_mail attack_mail) {
        Attack_mailDAO DAO = DAO();
        return insert2(DAO, attack_mail, DAO.TABLENAME);
    }

    public static Attack_mail insert2(Attack_mailDAO DAO, Attack_mail attack_mail) {
        return insert2(DAO, attack_mail, DAO.TABLENAME);
    }

    public static Attack_mail insert2(Attack_mail attack_mail, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return insert2(DAO, attack_mail, TABLENAME2);
    }

    public static Attack_mail insert2(Attack_mailDAO DAO, Attack_mail attack_mail, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(attack_mail, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        attack_mail.id = n;
        if(cacheModel != NO_CACHE) put(attack_mail);

        return attack_mail;
    }

    public static Attack_mail asynchronousInsert2(Attack_mail attack_mail) {
        Attack_mailDAO DAO = DAO();
        return asynchronousInsert2(DAO, attack_mail, DAO.TABLENAME);
    }
    public static Attack_mail asynchronousInsert2(Attack_mailDAO DAO, Attack_mail attack_mail) {
        return asynchronousInsert2(DAO, attack_mail, DAO.TABLENAME);
    }
    public static Attack_mail asynchronousInsert2(Attack_mail attack_mail, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return asynchronousInsert2(DAO, attack_mail, TABLENAME2);
    }
    public static Attack_mail asynchronousInsert2(Attack_mailDAO DAO, Attack_mail attack_mail, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        attack_mail.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(attack_mail, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(attack_mail);
        return attack_mail;
    }
    public static int[] insert(List<Attack_mail> attack_mails) {
        Attack_mailDAO DAO = DAO();
        return insert(DAO, attack_mails, DAO.TABLENAME);
    }

    public static int[] insert(Attack_mailDAO DAO, List<Attack_mail> attack_mails) {
        return insert(DAO, attack_mails, DAO.TABLENAME);
    }

    public static int[] insert(List<Attack_mail> attack_mails, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return insert(DAO, attack_mails, TABLENAME2);
    }

    public static int[] insert(Attack_mailDAO DAO, List<Attack_mail> attack_mails, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(attack_mails, TABLENAME2);
            int n = 0;
            for(Attack_mail attack_mail : attack_mails){
                attack_mail.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[attack_mails.size()];
        int n = 0;
        for(Attack_mail attack_mail : attack_mails){
            attack_mail = insert(DAO, attack_mail, TABLENAME2);
            ret[n++] = (attack_mail == null) ? 0 : (int)attack_mail.id;
        }
        return ret;
    }

    public static int delete(Attack_mail attack_mail) {
        int id = attack_mail.id;
        Attack_mailDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Attack_mailDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Attack_mailDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Attack_mailDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Attack_mail attack_mail) {
        int id = attack_mail.id;
        Attack_mailDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Attack_mailDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Attack_mailDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Attack_mailDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Attack_mailDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Attack_mailDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Attack_mailDAO DAO, int[] ids,String TABLENAME2) {
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
        Attack_mailDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Attack_mailDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Attack_mailDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Attack_mail> beans) {
        Attack_mailDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Attack_mail> beans, Attack_mailDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Attack_mail> beans, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Attack_mail> beans, final Attack_mailDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Attack_mail attack_mail : beans){
                int id = attack_mail.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Attack_mail> getAll() {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Attack_mail> getAll(Attack_mailDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Attack_mail> getAll(String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Attack_mail> getAll(final Attack_mailDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Attack_mail> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Attack_mail> getNoSortAll() {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Attack_mail> getNoSortAll(Attack_mailDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Attack_mail> getNoSortAll(String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Attack_mail> getNoSortAll(final Attack_mailDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Attack_mail> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Attack_mail> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Attack_mail attack_mail = FIXED[i];
                if (attack_mail != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Attack_mail> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Attack_mail> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Attack_mailDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Attack_mailDAO DAO, final String TABLENAME2) {
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
        Attack_mailDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Attack_mailDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Attack_mailDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Attack_mail> getIn(List<Integer> keys) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Attack_mail> getIn(List<Integer> keys, Attack_mailDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Attack_mail> getIn(List<Integer> keys, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Attack_mail> getIn(final List<Integer> keys, final Attack_mailDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Attack_mail> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Attack_mail> getNoSortIn(List<Integer> keys) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Attack_mail> getNoSortIn(List<Integer> keys, Attack_mailDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Attack_mail> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Attack_mail> getNoSortIn(final List<Integer> keys, final Attack_mailDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Attack_mail> result = newList();
            for (int key : keys) {
                Attack_mail attack_mail = getByKeyFromMemory(key);
                if( attack_mail == null ) continue;
                result.add(attack_mail);
            }
            return result;
        }
    }

    public static List<Attack_mail> getLast(int num) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Attack_mail> getLast(Attack_mailDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Attack_mail> getLast(int num, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Attack_mail> getLast(final Attack_mailDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Attack_mail> result = newList();
            List<Attack_mail> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Attack_mail last() {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Attack_mail last(Attack_mailDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Attack_mail last(String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Attack_mail last(final Attack_mailDAO DAO, final String TABLENAME2) {
        Attack_mail result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Attack_mailDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Attack_mailDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Attack_mailDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Attack_mail> getGtKey(int id) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Attack_mail> getGtKey(Attack_mailDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Attack_mail> getGtKey(int id, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Attack_mail> getGtKey(final Attack_mailDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Attack_mail> result = newList();
            List<Attack_mail> attack_mails = getAll();
            for (Attack_mail attack_mail : attack_mails) {
                if(attack_mail.id <= id) continue;
                result.add(attack_mail);
            }
            return result;
        }
    }

    public static Attack_mail getByKey(int id) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Attack_mail getByKey(Attack_mailDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Attack_mail getByKey(int id, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Attack_mail getByKey(final Attack_mailDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Attack_mail getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Attack_mail deleteFromMemory(final int id) {
        Attack_mail attack_mail;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            attack_mail = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(attack_mail);
        } else {
            attack_mail = vars.remove(id);
        }
        if(attack_mail == null) return null;

        int beAttPcid = attack_mail.getBeattpcid();
        Set m1 = varsByBeattpcid.get(beAttPcid);
        if(m1 != null)
            m1.remove(id);

        int attPcid = attack_mail.getAttpcid();
        Set m2 = varsByAttpcid.get(attPcid);
        if(m2 != null)
            m2.remove(id);

        String attcid = attack_mail.getAttcid();
        varsByAttcid.remove(attcid);

        return attack_mail;
    }

    public static List<Attack_mail> getByPage(int page, int size) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Attack_mail> getByPage(Attack_mailDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Attack_mail> getByPage(int page, int size, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Attack_mail> getByPage(final Attack_mailDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Attack_mail> result = newList();
            List<Attack_mail> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Attack_mailDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Attack_mailDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByBeattpcid(Integer beAttPcid) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByBeattpcid(DAO, beAttPcid, DAO.TABLENAME);
    }

    public static int countByBeattpcid(Attack_mailDAO DAO, Integer beAttPcid) {
        return countByBeattpcid(DAO, beAttPcid, DAO.TABLENAME);
    }

    public static int countByBeattpcid(Integer beAttPcid, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByBeattpcid(DAO, beAttPcid, TABLENAME2);
    }

    public static int countByBeattpcid(final Attack_mailDAO DAO, final Integer beAttPcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByBeattpcid(beAttPcid, TABLENAME2);
        }
        List<Attack_mail> attack_mails = getByBeattpcid(DAO, beAttPcid, TABLENAME2);
        return attack_mails.size();
    }

    public static List<Attack_mail> getByBeattpcid(Integer beAttPcid) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByBeattpcid(DAO, beAttPcid, DAO.TABLENAME);
    }

    public static List<Attack_mail> getByBeattpcid(Attack_mailDAO DAO, Integer beAttPcid) {
        return getByBeattpcid(DAO, beAttPcid, DAO.TABLENAME);
    }

    public static List<Attack_mail> getByBeattpcid(Integer beAttPcid, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByBeattpcid(DAO, beAttPcid, TABLENAME2);
    }

    public static List<Attack_mail> getByBeattpcid(final Attack_mailDAO DAO, final Integer beAttPcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByBeattpcid(beAttPcid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Attack_mail> result = newList();
            Set<Integer> m1 = varsByBeattpcid.get(beAttPcid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Attack_mail e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _beAttPcid = e.getBeattpcid();
                if(_beAttPcid != beAttPcid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByAttpcid(Integer attPcid) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByAttpcid(DAO, attPcid, DAO.TABLENAME);
    }

    public static int countByAttpcid(Attack_mailDAO DAO, Integer attPcid) {
        return countByAttpcid(DAO, attPcid, DAO.TABLENAME);
    }

    public static int countByAttpcid(Integer attPcid, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByAttpcid(DAO, attPcid, TABLENAME2);
    }

    public static int countByAttpcid(final Attack_mailDAO DAO, final Integer attPcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByAttpcid(attPcid, TABLENAME2);
        }
        List<Attack_mail> attack_mails = getByAttpcid(DAO, attPcid, TABLENAME2);
        return attack_mails.size();
    }

    public static List<Attack_mail> getByAttpcid(Integer attPcid) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAttpcid(DAO, attPcid, DAO.TABLENAME);
    }

    public static List<Attack_mail> getByAttpcid(Attack_mailDAO DAO, Integer attPcid) {
        return getByAttpcid(DAO, attPcid, DAO.TABLENAME);
    }

    public static List<Attack_mail> getByAttpcid(Integer attPcid, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAttpcid(DAO, attPcid, TABLENAME2);
    }

    public static List<Attack_mail> getByAttpcid(final Attack_mailDAO DAO, final Integer attPcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAttpcid(attPcid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Attack_mail> result = newList();
            Set<Integer> m1 = varsByAttpcid.get(attPcid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Attack_mail e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _attPcid = e.getAttpcid();
                if(_attPcid != attPcid){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static Attack_mail getByAttcid(String attcid) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAttcid(DAO, attcid, DAO.TABLENAME);
    }

    public static Attack_mail getByAttcid(Attack_mailDAO DAO, String attcid) {
        return getByAttcid(DAO, attcid, DAO.TABLENAME);
    }

    public static Attack_mail getByAttcid(String attcid, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByAttcid(DAO, attcid, TABLENAME2);
    }

    public static Attack_mail getByAttcid(final Attack_mailDAO DAO, final String attcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByAttcid(attcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByAttcid.get(attcid);
            if(id == null) return null;
            Attack_mail result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getAttcid().equals(attcid)){
                varsByAttcid.remove(attcid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeAttcid(String attcid) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeAttcid(DAO, attcid, DAO.TABLENAME);
    }

    public static int countLikeAttcid(Attack_mailDAO DAO, String attcid) {
        return countLikeAttcid(DAO, attcid, DAO.TABLENAME);
    }

    public static int countLikeAttcid(String attcid, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeAttcid(DAO, attcid, TABLENAME2);
    }

    public static int countLikeAttcid(final Attack_mailDAO DAO, final String attcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeAttcid(attcid, TABLENAME2);
        }
        List<Attack_mail> attack_mails = getLikeAttcid(DAO, attcid, TABLENAME2);
        return attack_mails.size();
    }

    public static List<Attack_mail> getLikeAttcid(String attcid) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeAttcid(DAO, attcid, DAO.TABLENAME);
    }

    public static List<Attack_mail> getLikeAttcid(Attack_mailDAO DAO, String attcid) {
        return getLikeAttcid(DAO, attcid, DAO.TABLENAME);
    }

    public static List<Attack_mail> getLikeAttcid(String attcid, String TABLENAME2) {
        Attack_mailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeAttcid(DAO, attcid, TABLENAME2);
    }

    public static List<Attack_mail> getLikeAttcid(final Attack_mailDAO DAO, final String attcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeAttcid(attcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Attack_mail> result = newList();
            List<Attack_mail> attack_mails = getAll(DAO, TABLENAME2);
            for(Attack_mail e : attack_mails){
                String _var = e.getAttcid();
                if(_var.indexOf(attcid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Attack_mail update(Attack_mail attack_mail) {
        Attack_mailDAO DAO = DAO();
        return update(DAO, attack_mail, DAO.TABLENAME);
    }

    public static Attack_mail update(Attack_mailDAO DAO, Attack_mail attack_mail) {
        return update(DAO, attack_mail, DAO.TABLENAME);
    }

    public static Attack_mail update(Attack_mail attack_mail, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return update(DAO, attack_mail, TABLENAME2);
    }

    public static Attack_mail update(final Attack_mailDAO DAO, final Attack_mail attack_mail,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(attack_mail, TABLENAME2);
            if(n == -1) 
                return attack_mail;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(attack_mail);
        }
        return attack_mail;
    }

    public static Attack_mail asynchronousUpdate(Attack_mail attack_mail) {
        Attack_mailDAO DAO = DAO();
        return asynchronousUpdate(DAO, attack_mail, DAO.TABLENAME);
    }

    public static Attack_mail asynchronousUpdate(Attack_mailDAO DAO, Attack_mail attack_mail) {
        return asynchronousUpdate(DAO, attack_mail, DAO.TABLENAME);
    }

    public static Attack_mail asynchronousUpdate(Attack_mail attack_mail, String TABLENAME2) {
        Attack_mailDAO DAO = DAO();
        return asynchronousUpdate(DAO, attack_mail, TABLENAME2);
    }

    public static Attack_mail asynchronousUpdate(final Attack_mailDAO DAO, final Attack_mail attack_mail,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(attack_mail, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(attack_mail);
        }
        return attack_mail;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Attack_mailDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Attack_mailDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Attack_mailDAO DAO, final String TABLENAME2) {
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

        List<Attack_mail> attack_mails = getAll();
        for (Attack_mail attack_mail : attack_mails) {
            int n = DAO.insert2(attack_mail, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
