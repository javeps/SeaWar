package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.internal.*;
//import com.sea.content.AppContext;

//seawar2_design - notice
@SuppressWarnings({ "static-access" })
public class NoticeEntity extends NoticeInternal{
    static Log log = LogFactory.getLog(NoticeEntity.class);

    public static final NoticeEntity my = new NoticeEntity();

    static NoticeDAO NoticeDAO = null;
    public static NoticeDAO NoticeDAO() {
        if( NoticeDAO == null)
            NoticeDAO = new NoticeDAO(com.sea.content.AppContext.ds());
        return NoticeDAO;
    }

//    static NoticeDAO NoticeDAO99 = null;
//    public static NoticeDAO NoticeDAO99() {
//        if( NoticeDAO99 == null)
//            NoticeDAO99 = new NoticeDAO(com.sea.content.AppContext.ds99());
//        return NoticeDAO99;
//    }

    public static void insertMmTry(final Notice notice) {
        NoticeDAO DAO = NoticeDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asynchronousInsert(notice, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    
    // types end

}

