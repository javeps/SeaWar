package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.internal.*;
//import com.sea.content.AppContext;

//seawar2_design - admin
@SuppressWarnings({ "static-access" })
public class AdminEntity extends AdminInternal{
    static Log log = LogFactory.getLog(AdminEntity.class);

    public static final AdminEntity my = new AdminEntity();

    static AdminDAO AdminDAO = null;
    public static AdminDAO AdminDAO() {
        if( AdminDAO == null)
            AdminDAO = new AdminDAO(com.sea.content.AppContext.ds());
        return AdminDAO;
    }

//    static AdminDAO AdminDAO99 = null;
//    public static AdminDAO AdminDAO99() {
//        if( AdminDAO99 == null)
//            AdminDAO99 = new AdminDAO(com.sea.content.AppContext.ds99());
//        return AdminDAO99;
//    }

    public static void insertMmTry(final Admin admin) {
        AdminDAO DAO = AdminDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asynchronousInsert(admin, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    
    // types end

}

