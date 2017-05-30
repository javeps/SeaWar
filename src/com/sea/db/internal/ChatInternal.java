package com.sea.db.internal;

import org.apache.commons.logging.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import com.bowlong.sql.*;
import static com.bowlong.sql.CacheModel.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.entity.*;

//seawar2_design - chat
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class ChatInternal extends InternalSupport{
    static Log log = LogFactory.getLog(ChatDAO.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public ChatInternal(){}

    public static ChatDAO DAO(){
        return ChatEntity.ChatDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Chat[MAX];
    }
    private static Chat[] FIXED = new Chat[MAX];
    public static final Map<Integer, Chat> vars = newSortedMap();
    public static final Map<String, Set<Integer>> varsByTypeFromname = newSortedMap();
    public static final Map<String, Set<Integer>> varsByTypeToname = newSortedMap();
    public static final Map<Integer, Set<Integer>> varsByType = newSortedMap();

    private static final List<Chat> fixedCache = newList();

    public static void put(Chat chat){
        if(chat == null || chat.id <= 0) return ;

        int id = chat.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(chat))
                	FIXED[id - 1] = chat;
                if (!fixedCache.contains(chat))
                	fixedCache.add(chat);
            }
        } else {
            vars.put(id, chat);
        }

        { // type_3
            int vtype = chat.getType();
            String vfromName = chat.getFromname();
            String vkey = vtype+ "-" +vfromName;
            Set m1 = varsByTypeFromname.get(vkey);
            if(m1 == null){
                m1 = newSortedSet();
                varsByTypeFromname.put(vkey, m1);
            }
            m1.add(id);
        }

        { // type_2
            int vtype = chat.getType();
            String vtoName = chat.getToname();
            String vkey = vtype+ "-" +vtoName;
            Set m2 = varsByTypeToname.get(vkey);
            if(m2 == null){
                m2 = newSortedSet();
                varsByTypeToname.put(vkey, m2);
            }
            m2.add(id);
        }

        int type = chat.getType();
        Set m3 = varsByType.get(type);
        if(m3 == null){
            m3 = newSortedSet();
            varsByType.put(type, m3);
        }
        m3.add(id);

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByTypeFromname.clear();
        varsByTypeToname.clear();
        varsByType.clear();
        FIXED = new Chat[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
        // LASTID = 0;
    }

    public static int count(){
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(ChatDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(ChatDAO DAO, String TABLENAME2){
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

    public static void relocate(ChatDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        ChatDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(ChatDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        ChatDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(ChatDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        ChatDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(ChatDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        ChatDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(ChatDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(ChatDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        ChatDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(ChatDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(ChatDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        ChatDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(ChatDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Chat[maxId(DAO)];

        List<Chat> chats = DAO.selectAll();
        for (Chat chat : chats) {
            chat.reset();
            put(chat);
        }
    }

    public static Map toMap(Chat chat){
        return chat.toMap();
    }

    public static List<Map> toMap(List<Chat> chats){
        List<Map> ret = new Vector<Map>();
        for (Chat chat : chats){
            Map e = chat.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Chat> sortZh(List<Chat> chats, final String key) {
        Collections.sort(chats, new Comparator<Chat>() {
            public int compare(Chat o1, Chat o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return chats;
    }

    public static List<Chat> sort(List<Chat> chats, final String key) {
        Collections.sort(chats, new Comparator<Chat>() {
            public int compare(Chat o1, Chat o2) {
                return o1.compareTo(o2, key);
            }
        });
        return chats;
    }

    public static List<Chat> sort(List<Chat> chats){
        Collections.sort(chats, new Comparator<Chat>(){
            public int compare(Chat o1, Chat o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return chats;
    }

    public static List<Chat> sortReverse(List<Chat> chats){
        Collections.sort(chats, new Comparator<Chat>(){
            public int compare(Chat o1, Chat o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return chats;
    }

    public static List<Chat> sortType(List<Chat> chats){
        Collections.sort(chats, new Comparator<Chat>(){
            public int compare(Chat o1, Chat o2) {
                Object v1 = o1.getType();
                Object v2 = o2.getType();
                return compareTo(v1, v2);
            }
        });
        return chats;
    }

    public static List<Chat> sortTypeRo(List<Chat> chats){
        Collections.sort(chats, new Comparator<Chat>(){
            public int compare(Chat o1, Chat o2) {
                Object v1 = o1.getType();
                Object v2 = o2.getType();
                return 0 - compareTo(v1, v2);
            };
        });
        return chats;
    }

    public static Chat insert(Chat chat) {
        ChatDAO DAO = DAO();
        return insert(DAO, chat, DAO.TABLENAME);
    }

    public static Chat insert(ChatDAO DAO, Chat chat) {
        return insert(DAO, chat, DAO.TABLENAME);
    }

    public static Chat insert(Chat chat, String TABLENAME2) {
        ChatDAO DAO = DAO();
        return insert(DAO, chat, TABLENAME2);
    }

    public static Chat insert(ChatDAO DAO, Chat chat, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(chat, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        chat.id = n;
        if(cacheModel != NO_CACHE) put(chat);

        return chat;
    }

    public static Chat asynchronousInsert(Chat chat) {
        ChatDAO DAO = DAO();
        return asynchronousInsert(DAO, chat, DAO.TABLENAME);
    }
    public static Chat asynchronousInsert(ChatDAO DAO, Chat chat) {
        return asynchronousInsert(DAO, chat, DAO.TABLENAME);
    }
    public static Chat asynchronousInsert(Chat chat, String TABLENAME2) {
        ChatDAO DAO = DAO();
        return asynchronousInsert(DAO, chat, TABLENAME2);
    }
    public static Chat asynchronousInsert(ChatDAO DAO, Chat chat, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        Integer n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert(chat, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        chat.id = n;
        if(cacheModel != NO_CACHE) put(chat);
        return chat;
    }
    public static Chat insert2(Chat chat) {
        ChatDAO DAO = DAO();
        return insert2(DAO, chat, DAO.TABLENAME);
    }

    public static Chat insert2(ChatDAO DAO, Chat chat) {
        return insert2(DAO, chat, DAO.TABLENAME);
    }

    public static Chat insert2(Chat chat, String TABLENAME2) {
        ChatDAO DAO = DAO();
        return insert2(DAO, chat, TABLENAME2);
    }

    public static Chat insert2(ChatDAO DAO, Chat chat, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(chat, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        chat.id = n;
        if(cacheModel != NO_CACHE) put(chat);

        return chat;
    }

    public static Chat asynchronousInsert2(Chat chat) {
        ChatDAO DAO = DAO();
        return asynchronousInsert2(DAO, chat, DAO.TABLENAME);
    }
    public static Chat asynchronousInsert2(ChatDAO DAO, Chat chat) {
        return asynchronousInsert2(DAO, chat, DAO.TABLENAME);
    }
    public static Chat asynchronousInsert2(Chat chat, String TABLENAME2) {
        ChatDAO DAO = DAO();
        return asynchronousInsert2(DAO, chat, TABLENAME2);
    }
    public static Chat asynchronousInsert2(ChatDAO DAO, Chat chat, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        chat.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asynchronousInsert2(chat, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(chat);
        return chat;
    }
    public static int[] insert(List<Chat> chats) {
        ChatDAO DAO = DAO();
        return insert(DAO, chats, DAO.TABLENAME);
    }

    public static int[] insert(ChatDAO DAO, List<Chat> chats) {
        return insert(DAO, chats, DAO.TABLENAME);
    }

    public static int[] insert(List<Chat> chats, String TABLENAME2) {
        ChatDAO DAO = DAO();
        return insert(DAO, chats, TABLENAME2);
    }

    public static int[] insert(ChatDAO DAO, List<Chat> chats, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(chats, TABLENAME2);
            int n = 0;
            for(Chat chat : chats){
                chat.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[chats.size()];
        int n = 0;
        for(Chat chat : chats){
            chat = insert(DAO, chat, TABLENAME2);
            ret[n++] = (chat == null) ? 0 : (int)chat.id;
        }
        return ret;
    }

    public static int delete(Chat chat) {
        int id = chat.id;
        ChatDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        ChatDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(ChatDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        ChatDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(ChatDAO DAO, int id, String TABLENAME2) {
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

    public static void asynchronousDelete(Chat chat) {
        int id = chat.id;
        ChatDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id) {
        ChatDAO DAO = DAO();
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(ChatDAO DAO, int id) {
        asynchronousDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asynchronousDelete(int id, String TABLENAME2) {
        ChatDAO DAO = DAO();
        asynchronousDelete(DAO, id, TABLENAME2);
    }

    public static void asynchronousDelete(ChatDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        ChatDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(ChatDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        ChatDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(ChatDAO DAO, int[] ids,String TABLENAME2) {
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
        ChatDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, ChatDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        ChatDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final ChatDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Chat> beans) {
        ChatDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Chat> beans, ChatDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Chat> beans, String TABLENAME2) {
        ChatDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Chat> beans, final ChatDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Chat chat : beans){
                int id = chat.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Chat> getAll() {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Chat> getAll(ChatDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Chat> getAll(String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Chat> getAll(final ChatDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Chat> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Chat> getNoSortAll() {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Chat> getNoSortAll(ChatDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Chat> getNoSortAll(String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Chat> getNoSortAll(final ChatDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Chat> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Chat> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Chat chat = FIXED[i];
                if (chat != null) result.add(i + 1);
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Chat> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Chat> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(ChatDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final ChatDAO DAO, final String TABLENAME2) {
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
        ChatDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(ChatDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        ChatDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final ChatDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Chat> getIn(List<Integer> keys) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Chat> getIn(List<Integer> keys, ChatDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Chat> getIn(List<Integer> keys, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Chat> getIn(final List<Integer> keys, final ChatDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Chat> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Chat> getNoSortIn(List<Integer> keys) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Chat> getNoSortIn(List<Integer> keys, ChatDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Chat> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Chat> getNoSortIn(final List<Integer> keys, final ChatDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Chat> result = newList();
            for (int key : keys) {
                Chat chat = getByKeyFromMemory(key);
                if( chat == null ) continue;
                result.add(chat);
            }
            return result;
        }
    }

    public static List<Chat> getLast(int num) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Chat> getLast(ChatDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Chat> getLast(int num, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Chat> getLast(final ChatDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Chat> result = newList();
            List<Chat> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Chat last() {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Chat last(ChatDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Chat last(String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Chat last(final ChatDAO DAO, final String TABLENAME2) {
        Chat result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        ChatDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(ChatDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        ChatDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final ChatDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Chat> getGtKey(int id) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Chat> getGtKey(ChatDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Chat> getGtKey(int id, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Chat> getGtKey(final ChatDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Chat> result = newList();
            List<Chat> chats = getAll();
            for (Chat chat : chats) {
                if(chat.id <= id) continue;
                result.add(chat);
            }
            return result;
        }
    }

    public static Chat getByKey(int id) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Chat getByKey(ChatDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Chat getByKey(int id, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Chat getByKey(final ChatDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Chat getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Chat deleteFromMemory(final int id) {
        Chat chat;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            chat = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(chat);
        } else {
            chat = vars.remove(id);
        }
        if(chat == null) return null;

        { // type_3
            int vtype = chat.getType();
            String vfromName = chat.getFromname();
            String vkey = vtype+ "-" +vfromName;
            Set m1 = varsByTypeFromname.get(vkey);
            if(m1 != null)
                m1.remove(id);
        }

        { // type_2
            int vtype = chat.getType();
            String vtoName = chat.getToname();
            String vkey = vtype+ "-" +vtoName;
            Set m2 = varsByTypeToname.get(vkey);
            if(m2 != null)
                m2.remove(id);
        }

        int type = chat.getType();
        Set m3 = varsByType.get(type);
        if(m3 != null)
            m3.remove(id);

        return chat;
    }

    public static List<Chat> getByPage(int page, int size) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Chat> getByPage(ChatDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Chat> getByPage(int page, int size, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Chat> getByPage(final ChatDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Chat> result = newList();
            List<Chat> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(ChatDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final ChatDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static int countByTypeFromname(Integer type, String fromName) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeFromname(DAO, type, fromName, DAO.TABLENAME);
    }

    public static int countByTypeFromname(ChatDAO DAO, Integer type, String fromName) {
        return countByTypeFromname(DAO, type, fromName, DAO.TABLENAME);
    }

    public static int countByTypeFromname(Integer type, String fromName, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeFromname(DAO, type, fromName, TABLENAME2);
    }

    public static int countByTypeFromname(final ChatDAO DAO, Integer type, String fromName, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByTypeFromname(type, fromName, TABLENAME2);
        }
        List<Chat> chats = getByTypeFromname(type, fromName, TABLENAME2);
        return chats.size();
    }

    public static List<Chat> getByTypeFromname(Integer type, String fromName) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeFromname(DAO, type, fromName, DAO.TABLENAME);
    }

    public static List<Chat> getByTypeFromname(ChatDAO DAO, Integer type, String fromName) {
        return getByTypeFromname(DAO, type, fromName, DAO.TABLENAME);
    }

    public static List<Chat> getByTypeFromname(Integer type, String fromName, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeFromname(DAO, type, fromName, TABLENAME2);
    }

    public static List<Chat> getByTypeFromname(final ChatDAO DAO, Integer type, String fromName, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTypeFromname(type, fromName, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Chat> result = newList();
            String vkey = type+"-"+fromName;
            Set<Integer> m1 = varsByTypeFromname.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Chat e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _type = e.getType();
                String _fromName = e.getFromname();
                String _key = _type + "-" + _fromName;
                if(!_key.equals(vkey)){
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countByTypeToname(Integer type, String toName) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeToname(DAO, type, toName, DAO.TABLENAME);
    }

    public static int countByTypeToname(ChatDAO DAO, Integer type, String toName) {
        return countByTypeToname(DAO, type, toName, DAO.TABLENAME);
    }

    public static int countByTypeToname(Integer type, String toName, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByTypeToname(DAO, type, toName, TABLENAME2);
    }

    public static int countByTypeToname(final ChatDAO DAO, Integer type, String toName, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByTypeToname(type, toName, TABLENAME2);
        }
        List<Chat> chats = getByTypeToname(type, toName, TABLENAME2);
        return chats.size();
    }

    public static List<Chat> getByTypeToname(Integer type, String toName) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeToname(DAO, type, toName, DAO.TABLENAME);
    }

    public static List<Chat> getByTypeToname(ChatDAO DAO, Integer type, String toName) {
        return getByTypeToname(DAO, type, toName, DAO.TABLENAME);
    }

    public static List<Chat> getByTypeToname(Integer type, String toName, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByTypeToname(DAO, type, toName, TABLENAME2);
    }

    public static List<Chat> getByTypeToname(final ChatDAO DAO, Integer type, String toName, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByTypeToname(type, toName, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Chat> result = newList();
            String vkey = type+"-"+toName;
            Set<Integer> m1 = varsByTypeToname.get(vkey);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {
                Chat e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                int _type = e.getType();
                String _toName = e.getToname();
                String _key = _type + "-" + _toName;
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
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByType(DAO, type, DAO.TABLENAME);
    }

    public static int countByType(ChatDAO DAO, Integer type) {
        return countByType(DAO, type, DAO.TABLENAME);
    }

    public static int countByType(Integer type, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByType(DAO, type, TABLENAME2);
    }

    public static int countByType(final ChatDAO DAO, final Integer type,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByType(type, TABLENAME2);
        }
        List<Chat> chats = getByType(DAO, type, TABLENAME2);
        return chats.size();
    }

    public static List<Chat> getByType(Integer type) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByType(DAO, type, DAO.TABLENAME);
    }

    public static List<Chat> getByType(ChatDAO DAO, Integer type) {
        return getByType(DAO, type, DAO.TABLENAME);
    }

    public static List<Chat> getByType(Integer type, String TABLENAME2) {
        ChatDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByType(DAO, type, TABLENAME2);
    }

    public static List<Chat> getByType(final ChatDAO DAO, final Integer type,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByType(type, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Chat> result = newList();
            Set<Integer> m1 = varsByType.get(type);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Chat e = getByKey(DAO, key, TABLENAME2);
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

    public static Chat update(Chat chat) {
        ChatDAO DAO = DAO();
        return update(DAO, chat, DAO.TABLENAME);
    }

    public static Chat update(ChatDAO DAO, Chat chat) {
        return update(DAO, chat, DAO.TABLENAME);
    }

    public static Chat update(Chat chat, String TABLENAME2) {
        ChatDAO DAO = DAO();
        return update(DAO, chat, TABLENAME2);
    }

    public static Chat update(final ChatDAO DAO, final Chat chat,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(chat, TABLENAME2);
            if(n == -1) 
                return chat;
            else if(n <= 0) 
                return null;
        }
        if(cacheModel != NO_CACHE){
            put(chat);
        }
        return chat;
    }

    public static Chat asynchronousUpdate(Chat chat) {
        ChatDAO DAO = DAO();
        return asynchronousUpdate(DAO, chat, DAO.TABLENAME);
    }

    public static Chat asynchronousUpdate(ChatDAO DAO, Chat chat) {
        return asynchronousUpdate(DAO, chat, DAO.TABLENAME);
    }

    public static Chat asynchronousUpdate(Chat chat, String TABLENAME2) {
        ChatDAO DAO = DAO();
        return asynchronousUpdate(DAO, chat, TABLENAME2);
    }

    public static Chat asynchronousUpdate(final ChatDAO DAO, final Chat chat,final String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asynchronousUpdate(chat, TABLENAME2);
        }
        if(cacheModel != NO_CACHE){
            put(chat);
        }
        return chat;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        ChatDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(ChatDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final ChatDAO DAO, final String TABLENAME2) {
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

        List<Chat> chats = getAll();
        for (Chat chat : chats) {
            int n = DAO.insert2(chat, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
