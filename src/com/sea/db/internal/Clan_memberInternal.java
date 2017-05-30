package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - clan_member
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Clan_memberInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Clan_memberDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Clan_memberInternal(){}

    public static Clan_memberDAO DAO(){
        return Clan_memberEntity.Clan_memberDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Clan_member[MAX];
    }
    private static Clan_member[] FIXED = new Clan_member[MAX];
    public static final Map<Integer, Clan_member> vars = newSortedMap();
    public static final Map<Integer, Integer> varsByPcid = newSortedMap();
    public static final Map<String, Set<Integer>> varsByCcid = newSortedMap();

    private static final List<Clan_member> fixedCache = newList();

    public static void put(Clan_member clan_member){
        if(clan_member == null || clan_member.id <= 0) return ;

        int id = clan_member.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(clan_member))
                	FIXED[id - 1] = clan_member;
                if (!fixedCache.contains(clan_member))
                	fixedCache.add(clan_member);
            }
        } else {
            vars.put(id, clan_member);
        }

        int pcid = clan_member.getPcid();
        varsByPcid.put(pcid, id);

        String ccid = clan_member.getCcid();
        Set m1 = varsByCcid.get(ccid);
        if(m1 == null){
            m1 = newSortedSet();
            varsByCcid.put(ccid, m1);
        }
        m1.add(id);

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByPcid.clear();
        varsByCcid.clear();
        FIXED = new Clan_member[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Clan_memberDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Clan_memberDAO DAO, String TABLENAME2){
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

    public static void relocate(Clan_memberDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Clan_memberDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Clan_memberDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Clan_memberDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Clan_memberDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Clan_memberDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Clan_memberDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Clan_memberDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Clan_memberDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Clan_memberDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Clan_memberDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Clan_memberDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Clan_memberDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Clan_member[maxId(DAO)];

        List<Clan_member> clan_members = DAO.selectAll();
        for (Clan_member clan_member : clan_members) {
            clan_member.reset();
            put(clan_member);
        }
    }

    public static Map toMap(Clan_member clan_member){
        return clan_member.toMap();
    }

    public static List<Map> toMap(List<Clan_member> clan_members){
        List<Map> ret = new Vector<Map>();
        for (Clan_member clan_member : clan_members){
            Map e = clan_member.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Clan_member> sortZh(List<Clan_member> clan_members, final String key) {
        Collections.sort(clan_members, new Comparator<Clan_member>() {
            public int compare(Clan_member o1, Clan_member o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return clan_members;
    }

    public static List<Clan_member> sort(List<Clan_member> clan_members, final String key) {
        Collections.sort(clan_members, new Comparator<Clan_member>() {
            public int compare(Clan_member o1, Clan_member o2) {
                return o1.compareTo(o2, key);
            }
        });
        return clan_members;
    }

    public static List<Clan_member> sort(List<Clan_member> clan_members){
        Collections.sort(clan_members, new Comparator<Clan_member>(){
            public int compare(Clan_member o1, Clan_member o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return clan_members;
    }

    public static List<Clan_member> sortReverse(List<Clan_member> clan_members){
        Collections.sort(clan_members, new Comparator<Clan_member>(){
            public int compare(Clan_member o1, Clan_member o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return clan_members;
    }

    public static List<Clan_member> sortPcid(List<Clan_member> clan_members){
        Collections.sort(clan_members, new Comparator<Clan_member>(){
            public int compare(Clan_member o1, Clan_member o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return compareTo(v1, v2);
            }
        });
        return clan_members;
    }

    public static List<Clan_member> sortPcidRo(List<Clan_member> clan_members){
        Collections.sort(clan_members, new Comparator<Clan_member>(){
            public int compare(Clan_member o1, Clan_member o2) {
                Object v1 = o1.getPcid();
                Object v2 = o2.getPcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return clan_members;
    }

    public static List<Clan_member> sortCcid(List<Clan_member> clan_members){
        Collections.sort(clan_members, new Comparator<Clan_member>(){
            public int compare(Clan_member o1, Clan_member o2) {
                Object v1 = o1.getCcid();
                Object v2 = o2.getCcid();
                return compareTo(v1, v2);
            }
        });
        return clan_members;
    }

    public static List<Clan_member> sortCcidRo(List<Clan_member> clan_members){
        Collections.sort(clan_members, new Comparator<Clan_member>(){
            public int compare(Clan_member o1, Clan_member o2) {
                Object v1 = o1.getCcid();
                Object v2 = o2.getCcid();
                return 0 - compareTo(v1, v2);
            };
        });
        return clan_members;
    }

    public static Clan_member insert(Clan_member clan_member) {
        Clan_memberDAO DAO = DAO();
        return insert(DAO, clan_member, DAO.TABLENAME);
    }

    public static Clan_member insert(Clan_memberDAO DAO, Clan_member clan_member) {
        return insert(DAO, clan_member, DAO.TABLENAME);
    }

    public static Clan_member insert(Clan_member clan_member, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return insert(DAO, clan_member, TABLENAME2);
    }

    public static Clan_member insert(Clan_memberDAO DAO, Clan_member clan_member, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(clan_member, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        clan_member.id = n;
        if(cacheModel != NO_CACHE) put(clan_member);

        return clan_member;
    }

    public static Clan_member asynchronousInsert(Clan_member clan_member) {
        Clan_memberDAO DAO = DAO();
        return asynchronousInsert(DAO, clan_member, DAO.TABLENAME);
    }
    public static Clan_member asynchronousInsert(Clan_memberDAO DAO, Clan_member clan_member) {
        return asynchronousInsert(DAO, clan_member, DAO.TABLENAME);
    }
    public static Clan_member asynchronousInsert(Clan_member clan_member, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return asynchronousInsert(DAO, clan_member, TABLENAME2);
    }
    public static Clan_member asynchronousInsert(Clan_memberDAO DAO, Clan_member clan_member, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(clan_member, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        clan_member.id = n;
        if(cacheModel != NO_CACHE) put(clan_member);
        return clan_member;
    }
    public static Clan_member insert2(Clan_member clan_member) {
        Clan_memberDAO DAO = DAO();
        return insert2(DAO, clan_member, DAO.TABLENAME);
    }

    public static Clan_member insert2(Clan_memberDAO DAO, Clan_member clan_member) {
        return insert2(DAO, clan_member, DAO.TABLENAME);
    }

    public static Clan_member insert2(Clan_member clan_member, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return insert2(DAO, clan_member, TABLENAME2);
    }

    public static Clan_member insert2(Clan_memberDAO DAO, Clan_member clan_member, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(clan_member, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        clan_member.id = n;
        if(cacheModel != NO_CACHE) put(clan_member);

        return clan_member;
    }

    public static Clan_member asynchronousInsert2(Clan_member clan_member) {
        Clan_memberDAO DAO = DAO();
        return asynchronousInsert2(DAO, clan_member, DAO.TABLENAME);
    }
    public static Clan_member asynchronousInsert2(Clan_memberDAO DAO, Clan_member clan_member) {
        return asynchronousInsert2(DAO, clan_member, DAO.TABLENAME);
    }
    public static Clan_member asynchronousInsert2(Clan_member clan_member, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return asynchronousInsert2(DAO, clan_member, TABLENAME2);
    }
    public static Clan_member asynchronousInsert2(Clan_memberDAO DAO, Clan_member clan_member, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        clan_member.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(clan_member, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(clan_member);
        return clan_member;
    }
    public static int[] insert(List<Clan_member> clan_members) {
        Clan_memberDAO DAO = DAO();
        return insert(DAO, clan_members, DAO.TABLENAME);
    }

    public static int[] insert(Clan_memberDAO DAO, List<Clan_member> clan_members) {
        return insert(DAO, clan_members, DAO.TABLENAME);
    }

    public static int[] insert(List<Clan_member> clan_members, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return insert(DAO, clan_members, TABLENAME2);
    }

    public static int[] insert(Clan_memberDAO DAO, List<Clan_member> clan_members, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(clan_members, TABLENAME2);
            int n = 0;
            for(Clan_member clan_member : clan_members){
                clan_member.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[clan_members.size()];
        int n = 0;
        for(Clan_member clan_member : clan_members){
            clan_member = insert(DAO, clan_member, TABLENAME2);
            ret[n++] = (clan_member == null) ? 0 : (int)clan_member.id;
        }
        return ret;
    }

    public static int delete(Clan_member clan_member) {
        int id = clan_member.id;
        Clan_memberDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Clan_memberDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Clan_memberDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Clan_memberDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Clan_member clan_member) {
        int id = clan_member.id;
        Clan_memberDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Clan_memberDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Clan_memberDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Clan_memberDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Clan_memberDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Clan_memberDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Clan_memberDAO DAO, int[] ids,String TABLENAME2) {
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
        Clan_memberDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Clan_memberDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Clan_memberDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Clan_member> beans) {
        Clan_memberDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Clan_member> beans, Clan_memberDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Clan_member> beans, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Clan_member> beans, final Clan_memberDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Clan_member clan_member : beans){
                int id = clan_member.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Clan_member> getAll() {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan_member> getAll(Clan_memberDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan_member> getAll(String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Clan_member> getAll(final Clan_memberDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan_member> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Clan_member> getNoSortAll() {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan_member> getNoSortAll(Clan_memberDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Clan_member> getNoSortAll(String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Clan_member> getNoSortAll(final Clan_memberDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Clan_member> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan_member> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Clan_member clan_member = FIXED[i];
                if (clan_member != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Clan_member> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Clan_member> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Clan_memberDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Clan_memberDAO DAO, final String TABLENAME2) {
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
        Clan_memberDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Clan_memberDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Clan_memberDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Clan_member> getIn(List<Integer> keys) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan_member> getIn(List<Integer> keys, Clan_memberDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan_member> getIn(List<Integer> keys, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Clan_member> getIn(final List<Integer> keys, final Clan_memberDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan_member> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Clan_member> getNoSortIn(List<Integer> keys) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan_member> getNoSortIn(List<Integer> keys, Clan_memberDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Clan_member> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Clan_member> getNoSortIn(final List<Integer> keys, final Clan_memberDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Clan_member> result = newList();
            for (int key : keys) {
                Clan_member clan_member = getByKeyFromMemory(key);
                if( clan_member == null ) continue;
                result.add(clan_member);
            }
            return result;
        }
    }

    public static List<Clan_member> getLast(int num) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Clan_member> getLast(Clan_memberDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Clan_member> getLast(int num, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Clan_member> getLast(final Clan_memberDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Clan_member> result = newList();
            List<Clan_member> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Clan_member last() {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Clan_member last(Clan_memberDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Clan_member last(String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Clan_member last(final Clan_memberDAO DAO, final String TABLENAME2) {
        Clan_member result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Clan_memberDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Clan_memberDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Clan_memberDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Clan_member> getGtKey(int id) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Clan_member> getGtKey(Clan_memberDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Clan_member> getGtKey(int id, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Clan_member> getGtKey(final Clan_memberDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan_member> result = newList();
            List<Clan_member> clan_members = getAll();
            for (Clan_member clan_member : clan_members) {
                if(clan_member.id <= id) continue;
                result.add(clan_member);
            }
            return result;
        }
    }

    public static Clan_member getByKey(int id) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Clan_member getByKey(Clan_memberDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Clan_member getByKey(int id, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Clan_member getByKey(final Clan_memberDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Clan_member getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Clan_member deleteFromMemory(final int id) {
        Clan_member clan_member;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            clan_member = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(clan_member);
        } else {
            clan_member = vars.remove(id);
        }
        if(clan_member == null) return null;

        int pcid = clan_member.getPcid();
        varsByPcid.remove(pcid);

        String ccid = clan_member.getCcid();
        Set m1 = varsByCcid.get(ccid);
        if(m1 != null)
            m1.remove(id);

        return clan_member;
    }

    public static List<Clan_member> getByPage(int page, int size) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Clan_member> getByPage(Clan_memberDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Clan_member> getByPage(int page, int size, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Clan_member> getByPage(final Clan_memberDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Clan_member> result = newList();
            List<Clan_member> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Clan_memberDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Clan_memberDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Clan_member getByPcid(Integer pcid) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static Clan_member getByPcid(Clan_memberDAO DAO, Integer pcid) {
        return getByPcid(DAO, pcid, DAO.TABLENAME);
    }

    public static Clan_member getByPcid(Integer pcid, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPcid(DAO, pcid, TABLENAME2);
    }

    public static Clan_member getByPcid(final Clan_memberDAO DAO, final int pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPcid(pcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByPcid.get(pcid);
            if(id == null) return null;
            Clan_member result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getPcid() != pcid){
                varsByPcid.remove(pcid);
                return null;
            }
            return result;
        }
    }

    public static int countByCcid(String ccid) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static int countByCcid(Clan_memberDAO DAO, String ccid) {
        return countByCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static int countByCcid(String ccid, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByCcid(DAO, ccid, TABLENAME2);
    }

    public static int countByCcid(final Clan_memberDAO DAO, final String ccid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByCcid(ccid, TABLENAME2);
        }
        List<Clan_member> clan_members = getByCcid(DAO, ccid, TABLENAME2);
        return clan_members.size();
    }

    public static List<Clan_member> getByCcid(String ccid) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static List<Clan_member> getByCcid(Clan_memberDAO DAO, String ccid) {
        return getByCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static List<Clan_member> getByCcid(String ccid, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByCcid(DAO, ccid, TABLENAME2);
    }

    public static List<Clan_member> getByCcid(final Clan_memberDAO DAO, final String ccid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByCcid(ccid, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Clan_member> result = newList();
            Set<Integer> m1 = varsByCcid.get(ccid);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Clan_member e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                String _ccid = e.getCcid();
                if(!_ccid.equals(ccid)){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countLikeCcid(String ccid) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static int countLikeCcid(Clan_memberDAO DAO, String ccid) {
        return countLikeCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static int countLikeCcid(String ccid, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeCcid(DAO, ccid, TABLENAME2);
    }

    public static int countLikeCcid(final Clan_memberDAO DAO, final String ccid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeCcid(ccid, TABLENAME2);
        }
        List<Clan_member> clan_members = getLikeCcid(DAO, ccid, TABLENAME2);
        return clan_members.size();
    }

    public static List<Clan_member> getLikeCcid(String ccid) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static List<Clan_member> getLikeCcid(Clan_memberDAO DAO, String ccid) {
        return getLikeCcid(DAO, ccid, DAO.TABLENAME);
    }

    public static List<Clan_member> getLikeCcid(String ccid, String TABLENAME2) {
        Clan_memberDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeCcid(DAO, ccid, TABLENAME2);
    }

    public static List<Clan_member> getLikeCcid(final Clan_memberDAO DAO, final String ccid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeCcid(ccid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Clan_member> result = newList();
            List<Clan_member> clan_members = getAll(DAO, TABLENAME2);
            for(Clan_member e : clan_members){
                String _var = e.getCcid();
                if(_var.indexOf(ccid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Clan_member update(Clan_member clan_member) {
        Clan_memberDAO DAO = DAO();
        return update(DAO, clan_member, DAO.TABLENAME);
    }

    public static Clan_member update(Clan_memberDAO DAO, Clan_member clan_member) {
        return update(DAO, clan_member, DAO.TABLENAME);
    }

    public static Clan_member update(Clan_member clan_member, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return update(DAO, clan_member, TABLENAME2);
    }

    public static Clan_member update(final Clan_memberDAO DAO, final Clan_member clan_member,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(clan_member, TABLENAME2);
            if(n == -1) 
                return clan_member;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(clan_member);
        }
        return clan_member;
    }

    public static Clan_member asynchronousUpdate(Clan_member clan_member) {
        Clan_memberDAO DAO = DAO();
        return asynchronousUpdate(DAO, clan_member, DAO.TABLENAME);
    }

    public static Clan_member asynchronousUpdate(Clan_memberDAO DAO, Clan_member clan_member) {
        return asynchronousUpdate(DAO, clan_member, DAO.TABLENAME);
    }

    public static Clan_member asynchronousUpdate(Clan_member clan_member, String TABLENAME2) {
        Clan_memberDAO DAO = DAO();
        return asynchronousUpdate(DAO, clan_member, TABLENAME2);
    }

    public static Clan_member asynchronousUpdate(final Clan_memberDAO DAO, final Clan_member clan_member,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(clan_member, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(clan_member);
        }
        return clan_member;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Clan_memberDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Clan_memberDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Clan_memberDAO DAO, final String TABLENAME2) {
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

        List<Clan_member> clan_members = getAll();
        for (Clan_member clan_member : clan_members) {
            int n = DAO.insert2(clan_member, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
