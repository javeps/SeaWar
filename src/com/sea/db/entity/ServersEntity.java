package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.sea.db.bean.*;
import com.sea.db.dao.*;
import com.sea.db.internal.*;
//import com.sea.content.AppContext;

//seawar_design - servers
@SuppressWarnings({ "static-access" })
public class ServersEntity extends ServersInternal{
    static Log log = LogFactory.getLog(ServersEntity.class);

    public static final ServersEntity my = new ServersEntity();

    static ServersDAO ServersDAO = null;
    public static ServersDAO ServersDAO() {
        if( ServersDAO == null)
            ServersDAO = new ServersDAO(com.sea.content.AppContext.ds());
        return ServersDAO;
    }

//    static ServersDAO ServersDAO99 = null;
//    public static ServersDAO ServersDAO99() {
//        if( ServersDAO99 == null)
//            ServersDAO99 = new ServersDAO(com.sea.content.AppContext.ds99());
//        return ServersDAO99;
//    }

    public static void insertMmTry(final Servers servers) {
        ServersDAO DAO = ServersDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asynchronousInsert(servers, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

