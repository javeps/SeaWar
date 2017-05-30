package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - payment_total
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Payment_totalInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Payment_totalDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Payment_totalInternal(){}

    public static Payment_totalDAO DAO(){
        return Payment_totalEntity.Payment_totalDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Payment_total[MAX];
    }
    private static Payment_total[] FIXED = new Payment_total[MAX];
    public static final Map<Integer, Payment_total> vars = newSortedMap();
    public static final Map<String, Integer> varsBySvcidChannel = newSortedMap();

    private static final List<Payment_total> fixedCache = newList();

    public static void put(Payment_total payment_total){
        if(payment_total == null || payment_total.id <= 0) return ;

        int id = payment_total.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(payment_total))
                	FIXED[id - 1] = payment_total;
                if (!fixedCache.contains(payment_total))
                	fixedCache.add(payment_total);
            }
        } else {
            vars.put(id, payment_total);
        }

        { // svcid
            int vsvcid = payment_total.getSvcid();
            String vchannel = payment_total.getChannel();
            String vkey = vsvcid + "-" + vchannel;
            varsBySvcidChannel.put(vkey, id);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsBySvcidChannel.clear();
        FIXED = new Payment_total[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Payment_totalDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Payment_totalDAO DAO, String TABLENAME2){
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

    public static void relocate(Payment_totalDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Payment_totalDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Payment_totalDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Payment_totalDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Payment_totalDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Payment_totalDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Payment_totalDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Payment_totalDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Payment_totalDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Payment_totalDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Payment_totalDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Payment_totalDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Payment_totalDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Payment_total[maxId(DAO)];

        List<Payment_total> payment_totals = DAO.selectAll();
        for (Payment_total payment_total : payment_totals) {
            payment_total.reset();
            put(payment_total);
        }
    }

    public static Map toMap(Payment_total payment_total){
        return payment_total.toMap();
    }

    public static List<Map> toMap(List<Payment_total> payment_totals){
        List<Map> ret = new Vector<Map>();
        for (Payment_total payment_total : payment_totals){
            Map e = payment_total.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Payment_total> sortZh(List<Payment_total> payment_totals, final String key) {
        Collections.sort(payment_totals, new Comparator<Payment_total>() {
            public int compare(Payment_total o1, Payment_total o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return payment_totals;
    }

    public static List<Payment_total> sort(List<Payment_total> payment_totals, final String key) {
        Collections.sort(payment_totals, new Comparator<Payment_total>() {
            public int compare(Payment_total o1, Payment_total o2) {
                return o1.compareTo(o2, key);
            }
        });
        return payment_totals;
    }

    public static List<Payment_total> sort(List<Payment_total> payment_totals){
        Collections.sort(payment_totals, new Comparator<Payment_total>(){
            public int compare(Payment_total o1, Payment_total o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return payment_totals;
    }

    public static List<Payment_total> sortReverse(List<Payment_total> payment_totals){
        Collections.sort(payment_totals, new Comparator<Payment_total>(){
            public int compare(Payment_total o1, Payment_total o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return payment_totals;
    }

    public static Payment_total insert(Payment_total payment_total) {
        Payment_totalDAO DAO = DAO();
        return insert(DAO, payment_total, DAO.TABLENAME);
    }

    public static Payment_total insert(Payment_totalDAO DAO, Payment_total payment_total) {
        return insert(DAO, payment_total, DAO.TABLENAME);
    }

    public static Payment_total insert(Payment_total payment_total, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return insert(DAO, payment_total, TABLENAME2);
    }

    public static Payment_total insert(Payment_totalDAO DAO, Payment_total payment_total, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(payment_total, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        payment_total.id = n;
        if(cacheModel != NO_CACHE) put(payment_total);

        return payment_total;
    }

    public static Payment_total asynchronousInsert(Payment_total payment_total) {
        Payment_totalDAO DAO = DAO();
        return asynchronousInsert(DAO, payment_total, DAO.TABLENAME);
    }
    public static Payment_total asynchronousInsert(Payment_totalDAO DAO, Payment_total payment_total) {
        return asynchronousInsert(DAO, payment_total, DAO.TABLENAME);
    }
    public static Payment_total asynchronousInsert(Payment_total payment_total, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return asynchronousInsert(DAO, payment_total, TABLENAME2);
    }
    public static Payment_total asynchronousInsert(Payment_totalDAO DAO, Payment_total payment_total, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(payment_total, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        payment_total.id = n;
        if(cacheModel != NO_CACHE) put(payment_total);
        return payment_total;
    }
    public static Payment_total insert2(Payment_total payment_total) {
        Payment_totalDAO DAO = DAO();
        return insert2(DAO, payment_total, DAO.TABLENAME);
    }

    public static Payment_total insert2(Payment_totalDAO DAO, Payment_total payment_total) {
        return insert2(DAO, payment_total, DAO.TABLENAME);
    }

    public static Payment_total insert2(Payment_total payment_total, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return insert2(DAO, payment_total, TABLENAME2);
    }

    public static Payment_total insert2(Payment_totalDAO DAO, Payment_total payment_total, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(payment_total, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        payment_total.id = n;
        if(cacheModel != NO_CACHE) put(payment_total);

        return payment_total;
    }

    public static Payment_total asynchronousInsert2(Payment_total payment_total) {
        Payment_totalDAO DAO = DAO();
        return asynchronousInsert2(DAO, payment_total, DAO.TABLENAME);
    }
    public static Payment_total asynchronousInsert2(Payment_totalDAO DAO, Payment_total payment_total) {
        return asynchronousInsert2(DAO, payment_total, DAO.TABLENAME);
    }
    public static Payment_total asynchronousInsert2(Payment_total payment_total, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return asynchronousInsert2(DAO, payment_total, TABLENAME2);
    }
    public static Payment_total asynchronousInsert2(Payment_totalDAO DAO, Payment_total payment_total, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        payment_total.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(payment_total, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(payment_total);
        return payment_total;
    }
    public static int[] insert(List<Payment_total> payment_totals) {
        Payment_totalDAO DAO = DAO();
        return insert(DAO, payment_totals, DAO.TABLENAME);
    }

    public static int[] insert(Payment_totalDAO DAO, List<Payment_total> payment_totals) {
        return insert(DAO, payment_totals, DAO.TABLENAME);
    }

    public static int[] insert(List<Payment_total> payment_totals, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return insert(DAO, payment_totals, TABLENAME2);
    }

    public static int[] insert(Payment_totalDAO DAO, List<Payment_total> payment_totals, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(payment_totals, TABLENAME2);
            int n = 0;
            for(Payment_total payment_total : payment_totals){
                payment_total.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[payment_totals.size()];
        int n = 0;
        for(Payment_total payment_total : payment_totals){
            payment_total = insert(DAO, payment_total, TABLENAME2);
            ret[n++] = (payment_total == null) ? 0 : (int)payment_total.id;
        }
        return ret;
    }

    public static int delete(Payment_total payment_total) {
        int id = payment_total.id;
        Payment_totalDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Payment_totalDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Payment_totalDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Payment_totalDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Payment_total payment_total) {
        int id = payment_total.id;
        Payment_totalDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        Payment_totalDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(Payment_totalDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(Payment_totalDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Payment_totalDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Payment_totalDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Payment_totalDAO DAO, int[] ids,String TABLENAME2) {
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
        Payment_totalDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Payment_totalDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Payment_totalDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Payment_total> beans) {
        Payment_totalDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Payment_total> beans, Payment_totalDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Payment_total> beans, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Payment_total> beans, final Payment_totalDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Payment_total payment_total : beans){
                int id = payment_total.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Payment_total> getAll() {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Payment_total> getAll(Payment_totalDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Payment_total> getAll(String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Payment_total> getAll(final Payment_totalDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Payment_total> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Payment_total> getNoSortAll() {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Payment_total> getNoSortAll(Payment_totalDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Payment_total> getNoSortAll(String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Payment_total> getNoSortAll(final Payment_totalDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Payment_total> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Payment_total> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Payment_total payment_total = FIXED[i];
                if (payment_total != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Payment_total> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Payment_total> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Payment_totalDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Payment_totalDAO DAO, final String TABLENAME2) {
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
        Payment_totalDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Payment_totalDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Payment_totalDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Payment_total> getIn(List<Integer> keys) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Payment_total> getIn(List<Integer> keys, Payment_totalDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Payment_total> getIn(List<Integer> keys, String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Payment_total> getIn(final List<Integer> keys, final Payment_totalDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Payment_total> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Payment_total> getNoSortIn(List<Integer> keys) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Payment_total> getNoSortIn(List<Integer> keys, Payment_totalDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Payment_total> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Payment_total> getNoSortIn(final List<Integer> keys, final Payment_totalDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Payment_total> result = newList();
            for (int key : keys) {
                Payment_total payment_total = getByKeyFromMemory(key);
                if( payment_total == null ) continue;
                result.add(payment_total);
            }
            return result;
        }
    }

    public static List<Payment_total> getLast(int num) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Payment_total> getLast(Payment_totalDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Payment_total> getLast(int num, String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Payment_total> getLast(final Payment_totalDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Payment_total> result = newList();
            List<Payment_total> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Payment_total last() {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Payment_total last(Payment_totalDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Payment_total last(String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Payment_total last(final Payment_totalDAO DAO, final String TABLENAME2) {
        Payment_total result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Payment_totalDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Payment_totalDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Payment_totalDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Payment_total> getGtKey(int id) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Payment_total> getGtKey(Payment_totalDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Payment_total> getGtKey(int id, String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Payment_total> getGtKey(final Payment_totalDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Payment_total> result = newList();
            List<Payment_total> payment_totals = getAll();
            for (Payment_total payment_total : payment_totals) {
                if(payment_total.id <= id) continue;
                result.add(payment_total);
            }
            return result;
        }
    }

    public static Payment_total getByKey(int id) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Payment_total getByKey(Payment_totalDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Payment_total getByKey(int id, String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Payment_total getByKey(final Payment_totalDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Payment_total getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Payment_total deleteFromMemory(final int id) {
        Payment_total payment_total;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            payment_total = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(payment_total);
        } else {
            payment_total = vars.remove(id);
        }
        if(payment_total == null) return null;

        { // svcid
            int vsvcid = payment_total.getSvcid();
            String vchannel = payment_total.getChannel();
            String vkey = vsvcid + "-" + vchannel;
            varsBySvcidChannel.remove(vkey);
        }

        return payment_total;
    }

    public static List<Payment_total> getByPage(int page, int size) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Payment_total> getByPage(Payment_totalDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Payment_total> getByPage(int page, int size, String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Payment_total> getByPage(final Payment_totalDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Payment_total> result = newList();
            List<Payment_total> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Payment_totalDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Payment_totalDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Payment_total getBySvcidChannel(Integer svcid, String channel) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getBySvcidChannel(DAO, svcid, channel, DAO.TABLENAME);
    }

    public static Payment_total getBySvcidChannel(Payment_totalDAO DAO, Integer svcid, String channel) {
        return getBySvcidChannel(DAO, svcid, channel, DAO.TABLENAME);
    }

    public static Payment_total getBySvcidChannel(Integer svcid, String channel, String TABLENAME2) {
        Payment_totalDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getBySvcidChannel(DAO, svcid, channel, TABLENAME2);
    }

    public static Payment_total getBySvcidChannel(final Payment_totalDAO DAO, Integer svcid, String channel,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectBySvcidChannel(svcid, channel, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = svcid+"-"+channel;
            Integer id = varsBySvcidChannel.get(vkey);
            if(id == null) return null;
            Payment_total result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(result.getSvcid() != svcid){
                varsBySvcidChannel.remove(vkey);
                return null;
            }
            if(!result.getChannel().equals(channel)){
                varsBySvcidChannel.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Payment_total update(Payment_total payment_total) {
        Payment_totalDAO DAO = DAO();
        return update(DAO, payment_total, DAO.TABLENAME);
    }

    public static Payment_total update(Payment_totalDAO DAO, Payment_total payment_total) {
        return update(DAO, payment_total, DAO.TABLENAME);
    }

    public static Payment_total update(Payment_total payment_total, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return update(DAO, payment_total, TABLENAME2);
    }

    public static Payment_total update(final Payment_totalDAO DAO, final Payment_total payment_total,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(payment_total, TABLENAME2);
            if(n == -1) 
                return payment_total;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(payment_total);
        }
        return payment_total;
    }

    public static Payment_total asynchronousUpdate(Payment_total payment_total) {
        Payment_totalDAO DAO = DAO();
        return asynchronousUpdate(DAO, payment_total, DAO.TABLENAME);
    }

    public static Payment_total asynchronousUpdate(Payment_totalDAO DAO, Payment_total payment_total) {
        return asynchronousUpdate(DAO, payment_total, DAO.TABLENAME);
    }

    public static Payment_total asynchronousUpdate(Payment_total payment_total, String TABLENAME2) {
        Payment_totalDAO DAO = DAO();
        return asynchronousUpdate(DAO, payment_total, TABLENAME2);
    }

    public static Payment_total asynchronousUpdate(final Payment_totalDAO DAO, final Payment_total payment_total,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(payment_total, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(payment_total);
        }
        return payment_total;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Payment_totalDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Payment_totalDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Payment_totalDAO DAO, final String TABLENAME2) {
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

        List<Payment_total> payment_totals = getAll();
        for (Payment_total payment_total : payment_totals) {
            int n = DAO.insert2(payment_total, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
