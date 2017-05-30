package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - reward
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class RewardInternal extends InternalSupport{
    static Log log = LogFactory.getLog(RewardDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public RewardInternal(){}

    public static RewardDAO DAO(){
        return RewardEntity.RewardDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Reward[MAX];
    }
    private static Reward[] FIXED = new Reward[MAX];
    public static final Map<Integer, Reward> vars = newSortedMap();

    private static final List<Reward> fixedCache = newList();

    public static void put(Reward reward){
        if(reward == null || reward.id <= 0) return ;

        int id = reward.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(reward))
                	FIXED[id - 1] = reward;
                if (!fixedCache.contains(reward))
                	fixedCache.add(reward);
            }
        } else {
            vars.put(id, reward);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        FIXED = new Reward[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(RewardDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(RewardDAO DAO, String TABLENAME2){
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

    public static void relocate(RewardDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        RewardDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(RewardDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        RewardDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(RewardDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        RewardDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(RewardDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        RewardDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(RewardDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(RewardDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        RewardDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(RewardDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(RewardDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        RewardDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(RewardDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Reward[maxId(DAO)];

        List<Reward> rewards = DAO.selectAll();
        for (Reward reward : rewards) {
            reward.reset();
            put(reward);
        }
    }

    public static Map toMap(Reward reward){
        return reward.toMap();
    }

    public static List<Map> toMap(List<Reward> rewards){
        List<Map> ret = new Vector<Map>();
        for (Reward reward : rewards){
            Map e = reward.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Reward> sortZh(List<Reward> rewards, final String key) {
        Collections.sort(rewards, new Comparator<Reward>() {
            public int compare(Reward o1, Reward o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return rewards;
    }

    public static List<Reward> sort(List<Reward> rewards, final String key) {
        Collections.sort(rewards, new Comparator<Reward>() {
            public int compare(Reward o1, Reward o2) {
                return o1.compareTo(o2, key);
            }
        });
        return rewards;
    }

    public static List<Reward> sort(List<Reward> rewards){
        Collections.sort(rewards, new Comparator<Reward>(){
            public int compare(Reward o1, Reward o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return rewards;
    }

    public static List<Reward> sortReverse(List<Reward> rewards){
        Collections.sort(rewards, new Comparator<Reward>(){
            public int compare(Reward o1, Reward o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return rewards;
    }

    public static Reward insert(Reward reward) {
        RewardDAO DAO = DAO();
        return insert(DAO, reward, DAO.TABLENAME);
    }

    public static Reward insert(RewardDAO DAO, Reward reward) {
        return insert(DAO, reward, DAO.TABLENAME);
    }

    public static Reward insert(Reward reward, String TABLENAME2) {
        RewardDAO DAO = DAO();
        return insert(DAO, reward, TABLENAME2);
    }

    public static Reward insert(RewardDAO DAO, Reward reward, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(reward, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        reward.id = n;
        if(cacheModel != NO_CACHE) put(reward);

        return reward;
    }

    public static Reward asynchronousInsert(Reward reward) {
        RewardDAO DAO = DAO();
        return asynchronousInsert(DAO, reward, DAO.TABLENAME);
    }
    public static Reward asynchronousInsert(RewardDAO DAO, Reward reward) {
        return asynchronousInsert(DAO, reward, DAO.TABLENAME);
    }
    public static Reward asynchronousInsert(Reward reward, String TABLENAME2) {
        RewardDAO DAO = DAO();
        return asynchronousInsert(DAO, reward, TABLENAME2);
    }
    public static Reward asynchronousInsert(RewardDAO DAO, Reward reward, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(reward, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        reward.id = n;
        if(cacheModel != NO_CACHE) put(reward);
        return reward;
    }
    public static Reward insert2(Reward reward) {
        RewardDAO DAO = DAO();
        return insert2(DAO, reward, DAO.TABLENAME);
    }

    public static Reward insert2(RewardDAO DAO, Reward reward) {
        return insert2(DAO, reward, DAO.TABLENAME);
    }

    public static Reward insert2(Reward reward, String TABLENAME2) {
        RewardDAO DAO = DAO();
        return insert2(DAO, reward, TABLENAME2);
    }

    public static Reward insert2(RewardDAO DAO, Reward reward, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(reward, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        reward.id = n;
        if(cacheModel != NO_CACHE) put(reward);

        return reward;
    }

    public static Reward asynchronousInsert2(Reward reward) {
        RewardDAO DAO = DAO();
        return asynchronousInsert2(DAO, reward, DAO.TABLENAME);
    }
    public static Reward asynchronousInsert2(RewardDAO DAO, Reward reward) {
        return asynchronousInsert2(DAO, reward, DAO.TABLENAME);
    }
    public static Reward asynchronousInsert2(Reward reward, String TABLENAME2) {
        RewardDAO DAO = DAO();
        return asynchronousInsert2(DAO, reward, TABLENAME2);
    }
    public static Reward asynchronousInsert2(RewardDAO DAO, Reward reward, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        reward.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(reward, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(reward);
        return reward;
    }
    public static int[] insert(List<Reward> rewards) {
        RewardDAO DAO = DAO();
        return insert(DAO, rewards, DAO.TABLENAME);
    }

    public static int[] insert(RewardDAO DAO, List<Reward> rewards) {
        return insert(DAO, rewards, DAO.TABLENAME);
    }

    public static int[] insert(List<Reward> rewards, String TABLENAME2) {
        RewardDAO DAO = DAO();
        return insert(DAO, rewards, TABLENAME2);
    }

    public static int[] insert(RewardDAO DAO, List<Reward> rewards, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(rewards, TABLENAME2);
            int n = 0;
            for(Reward reward : rewards){
                reward.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[rewards.size()];
        int n = 0;
        for(Reward reward : rewards){
            reward = insert(DAO, reward, TABLENAME2);
            ret[n++] = (reward == null) ? 0 : (int)reward.id;
        }
        return ret;
    }

    public static int delete(Reward reward) {
        int id = reward.id;
        RewardDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        RewardDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(RewardDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        RewardDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(RewardDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Reward reward) {
        int id = reward.id;
        RewardDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        RewardDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(RewardDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        RewardDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(RewardDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        RewardDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(RewardDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        RewardDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(RewardDAO DAO, int[] ids,String TABLENAME2) {
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
        RewardDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, RewardDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        RewardDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final RewardDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Reward> beans) {
        RewardDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Reward> beans, RewardDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Reward> beans, String TABLENAME2) {
        RewardDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Reward> beans, final RewardDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Reward reward : beans){
                int id = reward.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Reward> getAll() {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Reward> getAll(RewardDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Reward> getAll(String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Reward> getAll(final RewardDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Reward> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Reward> getNoSortAll() {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Reward> getNoSortAll(RewardDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Reward> getNoSortAll(String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Reward> getNoSortAll(final RewardDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Reward> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Reward> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Reward reward = FIXED[i];
                if (reward != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Reward> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Reward> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(RewardDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final RewardDAO DAO, final String TABLENAME2) {
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
        RewardDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(RewardDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        RewardDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final RewardDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Reward> getIn(List<Integer> keys) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Reward> getIn(List<Integer> keys, RewardDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Reward> getIn(List<Integer> keys, String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Reward> getIn(final List<Integer> keys, final RewardDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Reward> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Reward> getNoSortIn(List<Integer> keys) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Reward> getNoSortIn(List<Integer> keys, RewardDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Reward> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Reward> getNoSortIn(final List<Integer> keys, final RewardDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Reward> result = newList();
            for (int key : keys) {
                Reward reward = getByKeyFromMemory(key);
                if( reward == null ) continue;
                result.add(reward);
            }
            return result;
        }
    }

    public static List<Reward> getLast(int num) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Reward> getLast(RewardDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Reward> getLast(int num, String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Reward> getLast(final RewardDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Reward> result = newList();
            List<Reward> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Reward last() {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Reward last(RewardDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Reward last(String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Reward last(final RewardDAO DAO, final String TABLENAME2) {
        Reward result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        RewardDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(RewardDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        RewardDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final RewardDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Reward> getGtKey(int id) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Reward> getGtKey(RewardDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Reward> getGtKey(int id, String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Reward> getGtKey(final RewardDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Reward> result = newList();
            List<Reward> rewards = getAll();
            for (Reward reward : rewards) {
                if(reward.id <= id) continue;
                result.add(reward);
            }
            return result;
        }
    }

    public static Reward getByKey(int id) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Reward getByKey(RewardDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Reward getByKey(int id, String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Reward getByKey(final RewardDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Reward getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Reward deleteFromMemory(final int id) {
        Reward reward;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            reward = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(reward);
        } else {
            reward = vars.remove(id);
        }
        if(reward == null) return null;

        return reward;
    }

    public static List<Reward> getByPage(int page, int size) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Reward> getByPage(RewardDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Reward> getByPage(int page, int size, String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Reward> getByPage(final RewardDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Reward> result = newList();
            List<Reward> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(RewardDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        RewardDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final RewardDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Reward update(Reward reward) {
        RewardDAO DAO = DAO();
        return update(DAO, reward, DAO.TABLENAME);
    }

    public static Reward update(RewardDAO DAO, Reward reward) {
        return update(DAO, reward, DAO.TABLENAME);
    }

    public static Reward update(Reward reward, String TABLENAME2) {
        RewardDAO DAO = DAO();
        return update(DAO, reward, TABLENAME2);
    }

    public static Reward update(final RewardDAO DAO, final Reward reward,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(reward, TABLENAME2);
            if(n == -1) 
                return reward;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(reward);
        }
        return reward;
    }

    public static Reward asynchronousUpdate(Reward reward) {
        RewardDAO DAO = DAO();
        return asynchronousUpdate(DAO, reward, DAO.TABLENAME);
    }

    public static Reward asynchronousUpdate(RewardDAO DAO, Reward reward) {
        return asynchronousUpdate(DAO, reward, DAO.TABLENAME);
    }

    public static Reward asynchronousUpdate(Reward reward, String TABLENAME2) {
        RewardDAO DAO = DAO();
        return asynchronousUpdate(DAO, reward, TABLENAME2);
    }

    public static Reward asynchronousUpdate(final RewardDAO DAO, final Reward reward,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(reward, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(reward);
        }
        return reward;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        RewardDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(RewardDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final RewardDAO DAO, final String TABLENAME2) {
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

        List<Reward> rewards = getAll();
        for (Reward reward : rewards) {
            int n = DAO.insert2(reward, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
