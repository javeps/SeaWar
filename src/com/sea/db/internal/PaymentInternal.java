package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - payment
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class PaymentInternal extends InternalSupport{
    static Log log = LogFactory.getLog(PaymentDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public PaymentInternal(){}

    public static PaymentDAO DAO(){
        return PaymentEntity.PaymentDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Payment[MAX];
    }
    private static Payment[] FIXED = new Payment[MAX];
    public static final Map<Integer, Payment> vars = newSortedMap();
    public static final Map<String, Integer> varsByUnidSvcid = newSortedMap();

    private static final List<Payment> fixedCache = newList();

    public static void put(Payment payment){
        if(payment == null || payment.id <= 0) return ;

        int id = payment.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(payment))
                	FIXED[id - 1] = payment;
                if (!fixedCache.contains(payment))
                	fixedCache.add(payment);
            }
        } else {
            vars.put(id, payment);
        }

        { // unid
            String vunid = payment.getUnid();
            int vsvcid = payment.getSvcid();
            String vkey = vunid + "-" + vsvcid;
            varsByUnidSvcid.put(vkey, id);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByUnidSvcid.clear();
        FIXED = new Payment[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(PaymentDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(PaymentDAO DAO, String TABLENAME2){
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

    public static void relocate(PaymentDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        PaymentDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(PaymentDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        PaymentDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(PaymentDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        PaymentDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(PaymentDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        PaymentDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(PaymentDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(PaymentDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        PaymentDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(PaymentDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(PaymentDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        PaymentDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(PaymentDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Payment[maxId(DAO)];

        List<Payment> payments = DAO.selectAll();
        for (Payment payment : payments) {
            payment.reset();
            put(payment);
        }
    }

    public static Map toMap(Payment payment){
        return payment.toMap();
    }

    public static List<Map> toMap(List<Payment> payments){
        List<Map> ret = new Vector<Map>();
        for (Payment payment : payments){
            Map e = payment.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Payment> sortZh(List<Payment> payments, final String key) {
        Collections.sort(payments, new Comparator<Payment>() {
            public int compare(Payment o1, Payment o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return payments;
    }

    public static List<Payment> sort(List<Payment> payments, final String key) {
        Collections.sort(payments, new Comparator<Payment>() {
            public int compare(Payment o1, Payment o2) {
                return o1.compareTo(o2, key);
            }
        });
        return payments;
    }

    public static List<Payment> sort(List<Payment> payments){
        Collections.sort(payments, new Comparator<Payment>(){
            public int compare(Payment o1, Payment o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return payments;
    }

    public static List<Payment> sortReverse(List<Payment> payments){
        Collections.sort(payments, new Comparator<Payment>(){
            public int compare(Payment o1, Payment o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return payments;
    }

    public static Payment insert(Payment payment) {
        PaymentDAO DAO = DAO();
        return insert(DAO, payment, DAO.TABLENAME);
    }

    public static Payment insert(PaymentDAO DAO, Payment payment) {
        return insert(DAO, payment, DAO.TABLENAME);
    }

    public static Payment insert(Payment payment, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return insert(DAO, payment, TABLENAME2);
    }

    public static Payment insert(PaymentDAO DAO, Payment payment, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(payment, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        payment.id = n;
        if(cacheModel != NO_CACHE) put(payment);

        return payment;
    }

    public static Payment asynchronousInsert(Payment payment) {
        PaymentDAO DAO = DAO();
        return asynchronousInsert(DAO, payment, DAO.TABLENAME);
    }
    public static Payment asynchronousInsert(PaymentDAO DAO, Payment payment) {
        return asynchronousInsert(DAO, payment, DAO.TABLENAME);
    }
    public static Payment asynchronousInsert(Payment payment, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return asynchronousInsert(DAO, payment, TABLENAME2);
    }
    public static Payment asynchronousInsert(PaymentDAO DAO, Payment payment, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(payment, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        payment.id = n;
        if(cacheModel != NO_CACHE) put(payment);
        return payment;
    }
    public static Payment insert2(Payment payment) {
        PaymentDAO DAO = DAO();
        return insert2(DAO, payment, DAO.TABLENAME);
    }

    public static Payment insert2(PaymentDAO DAO, Payment payment) {
        return insert2(DAO, payment, DAO.TABLENAME);
    }

    public static Payment insert2(Payment payment, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return insert2(DAO, payment, TABLENAME2);
    }

    public static Payment insert2(PaymentDAO DAO, Payment payment, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(payment, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        payment.id = n;
        if(cacheModel != NO_CACHE) put(payment);

        return payment;
    }

    public static Payment asynchronousInsert2(Payment payment) {
        PaymentDAO DAO = DAO();
        return asynchronousInsert2(DAO, payment, DAO.TABLENAME);
    }
    public static Payment asynchronousInsert2(PaymentDAO DAO, Payment payment) {
        return asynchronousInsert2(DAO, payment, DAO.TABLENAME);
    }
    public static Payment asynchronousInsert2(Payment payment, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return asynchronousInsert2(DAO, payment, TABLENAME2);
    }
    public static Payment asynchronousInsert2(PaymentDAO DAO, Payment payment, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        payment.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(payment, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(payment);
        return payment;
    }
    public static int[] insert(List<Payment> payments) {
        PaymentDAO DAO = DAO();
        return insert(DAO, payments, DAO.TABLENAME);
    }

    public static int[] insert(PaymentDAO DAO, List<Payment> payments) {
        return insert(DAO, payments, DAO.TABLENAME);
    }

    public static int[] insert(List<Payment> payments, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return insert(DAO, payments, TABLENAME2);
    }

    public static int[] insert(PaymentDAO DAO, List<Payment> payments, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(payments, TABLENAME2);
            int n = 0;
            for(Payment payment : payments){
                payment.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[payments.size()];
        int n = 0;
        for(Payment payment : payments){
            payment = insert(DAO, payment, TABLENAME2);
            ret[n++] = (payment == null) ? 0 : (int)payment.id;
        }
        return ret;
    }

    public static int delete(Payment payment) {
        int id = payment.id;
        PaymentDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        PaymentDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(PaymentDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(PaymentDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Payment payment) {
        int id = payment.id;
        PaymentDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        PaymentDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(PaymentDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(PaymentDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        PaymentDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(PaymentDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(PaymentDAO DAO, int[] ids,String TABLENAME2) {
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
        PaymentDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, PaymentDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final PaymentDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Payment> beans) {
        PaymentDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Payment> beans, PaymentDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Payment> beans, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Payment> beans, final PaymentDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Payment payment : beans){
                int id = payment.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Payment> getAll() {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Payment> getAll(PaymentDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Payment> getAll(String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Payment> getAll(final PaymentDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Payment> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Payment> getNoSortAll() {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Payment> getNoSortAll(PaymentDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Payment> getNoSortAll(String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Payment> getNoSortAll(final PaymentDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Payment> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Payment> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Payment payment = FIXED[i];
                if (payment != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Payment> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Payment> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(PaymentDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final PaymentDAO DAO, final String TABLENAME2) {
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
        PaymentDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(PaymentDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final PaymentDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Payment> getIn(List<Integer> keys) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Payment> getIn(List<Integer> keys, PaymentDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Payment> getIn(List<Integer> keys, String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Payment> getIn(final List<Integer> keys, final PaymentDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Payment> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Payment> getNoSortIn(List<Integer> keys) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Payment> getNoSortIn(List<Integer> keys, PaymentDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Payment> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Payment> getNoSortIn(final List<Integer> keys, final PaymentDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Payment> result = newList();
            for (int key : keys) {
                Payment payment = getByKeyFromMemory(key);
                if( payment == null ) continue;
                result.add(payment);
            }
            return result;
        }
    }

    public static List<Payment> getLast(int num) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Payment> getLast(PaymentDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Payment> getLast(int num, String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Payment> getLast(final PaymentDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Payment> result = newList();
            List<Payment> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Payment last() {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Payment last(PaymentDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Payment last(String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Payment last(final PaymentDAO DAO, final String TABLENAME2) {
        Payment result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        PaymentDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(PaymentDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final PaymentDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Payment> getGtKey(int id) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Payment> getGtKey(PaymentDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Payment> getGtKey(int id, String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Payment> getGtKey(final PaymentDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Payment> result = newList();
            List<Payment> payments = getAll();
            for (Payment payment : payments) {
                if(payment.id <= id) continue;
                result.add(payment);
            }
            return result;
        }
    }

    public static Payment getByKey(int id) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Payment getByKey(PaymentDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Payment getByKey(int id, String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Payment getByKey(final PaymentDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Payment getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Payment deleteFromMemory(final int id) {
        Payment payment;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            payment = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(payment);
        } else {
            payment = vars.remove(id);
        }
        if(payment == null) return null;

        { // unid
            String vunid = payment.getUnid();
            int vsvcid = payment.getSvcid();
            String vkey = vunid + "-" + vsvcid;
            varsByUnidSvcid.remove(vkey);
        }

        return payment;
    }

    public static List<Payment> getByPage(int page, int size) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Payment> getByPage(PaymentDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Payment> getByPage(int page, int size, String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Payment> getByPage(final PaymentDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Payment> result = newList();
            List<Payment> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(PaymentDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final PaymentDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Payment getByUnidSvcid(String unid, Integer svcid) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnidSvcid(DAO, unid, svcid, DAO.TABLENAME);
    }

    public static Payment getByUnidSvcid(PaymentDAO DAO, String unid, Integer svcid) {
        return getByUnidSvcid(DAO, unid, svcid, DAO.TABLENAME);
    }

    public static Payment getByUnidSvcid(String unid, Integer svcid, String TABLENAME2) {
        PaymentDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnidSvcid(DAO, unid, svcid, TABLENAME2);
    }

    public static Payment getByUnidSvcid(final PaymentDAO DAO, String unid, Integer svcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUnidSvcid(unid, svcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = unid+"-"+svcid;
            Integer id = varsByUnidSvcid.get(vkey);
            if(id == null) return null;
            Payment result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getUnid().equals(unid)){
                varsByUnidSvcid.remove(vkey);
                return null;
            }
            if(result.getSvcid() != svcid){
                varsByUnidSvcid.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Payment update(Payment payment) {
        PaymentDAO DAO = DAO();
        return update(DAO, payment, DAO.TABLENAME);
    }

    public static Payment update(PaymentDAO DAO, Payment payment) {
        return update(DAO, payment, DAO.TABLENAME);
    }

    public static Payment update(Payment payment, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return update(DAO, payment, TABLENAME2);
    }

    public static Payment update(final PaymentDAO DAO, final Payment payment,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(payment, TABLENAME2);
            if(n == -1) 
                return payment;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(payment);
        }
        return payment;
    }

    public static Payment asynchronousUpdate(Payment payment) {
        PaymentDAO DAO = DAO();
        return asynchronousUpdate(DAO, payment, DAO.TABLENAME);
    }

    public static Payment asynchronousUpdate(PaymentDAO DAO, Payment payment) {
        return asynchronousUpdate(DAO, payment, DAO.TABLENAME);
    }

    public static Payment asynchronousUpdate(Payment payment, String TABLENAME2) {
        PaymentDAO DAO = DAO();
        return asynchronousUpdate(DAO, payment, TABLENAME2);
    }

    public static Payment asynchronousUpdate(final PaymentDAO DAO, final Payment payment,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(payment, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(payment);
        }
        return payment;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        PaymentDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(PaymentDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final PaymentDAO DAO, final String TABLENAME2) {
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

        List<Payment> payments = getAll();
        for (Payment payment : payments) {
            int n = DAO.insert2(payment, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
