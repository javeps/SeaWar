package com.sea.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.sea.db.bean.*;

//seawar2_design - player
@SuppressWarnings({"rawtypes", "unchecked"})
public class PlayerDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(PlayerDAO.class);

    public static final String TABLE = "player";
    public static String TABLENAME = "player";

    public static String TABLEYY() {
        return TABLE + sdfYy.format(new Date());
    }

    public static String TABLEMM() {
        return TABLE + sdfMm.format(new Date());
    }

    public static String TABLEDD() {
        return TABLE + sdfDd.format(new Date());
    }

    public static String[] carrays ={"id", "pcid", "ucid", "svcid", "pname", "type", "state", "channel", "icon", "exp", "crystal", "renown", "weekRenown", "cur_npc", "npcs", "all_money", "last_money", "last_pay_time", "guid_step", "clancid", "clanPost", "cname", "cicon", "maxBuidId", "maxObstId", "stored_oil", "stored_gold", "isOnline", "protectTime", "maxAttMCId", "maxBONum", "curBONum", "spells", "lastAddObst", "mark", "curtownlvl", "loginDay", "lastLoginTime", "isRewardDay", "firstPayStatus", "pieceHPNum", "pieceDamNum", "pieceAttSpeed", "dayTasks", "lastLeaveClan", "monthCode", "isMonCode", "builds", "obstes", "teches", "heroes", "share", "moneyActivity", "moneyActivityType"};
    public static String coulmns = "id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType";
    public static String coulmns2 = "pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType";

    public PlayerDAO(java.sql.Connection conn) {
        super(conn);
    }

    public PlayerDAO(javax.sql.DataSource ds) {
        super(ds);
    }

    public PlayerDAO(javax.sql.DataSource ds_r, javax.sql.DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Player player) {
        return insert(player, TABLENAME);
    }

    public int insert(final Player player, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            player.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType) VALUES (:pcid, :ucid, :svcid, :pname, :type, :state, :channel, :icon, :exp, :crystal, :renown, :weekRenown, :cur_npc, :npcs, :all_money, :last_money, :last_pay_time, :guid_step, :clancid, :clanPost, :cname, :cicon, :maxBuidId, :maxObstId, :stored_oil, :stored_gold, :isOnline, :protectTime, :maxAttMCId, :maxBONum, :curBONum, :spells, :lastAddObst, :mark, :curtownlvl, :loginDay, :lastLoginTime, :isRewardDay, :firstPayStatus, :pieceHPNum, :pieceDamNum, :pieceAttSpeed, :dayTasks, :lastLeaveClan, :monthCode, :isMonCode, :builds, :obstes, :teches, :heroes, :share, :moneyActivity, :moneyActivityType)");
            Map map = super.insert(sql.toString(), player);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousInsert(final Player player) {
        asynchronousInsert(player, TABLENAME);
    }

    public void asynchronousInsert(final Player player, final String TABLENAME2) {
        try {
            incrementAndGet();
            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert(player, TABLENAME2);
                   } catch (Exception e) {
                       log.error(e2s(e));
                   } finally {
                       decrementAndGet();
                   }
                }
            });
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public int asynchronousInsert2(final Player player) {
        return asynchronousInsert2(player, TABLENAME);
    }

    public int asynchronousInsert2(final Player player, final String TABLENAME2) {
        try {
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                   try {
                       insert2(player, TABLENAME2);
                   } catch (Exception e) {
                       log.error(e2s(e));
                   } finally {
                       decrementAndGet();
                   }
                }
            });
        } catch(Exception e) {
            log.info(e2s(e));
        }
        return player.id;
    }

    public int insert2(final Player player) {
        return insert2(player, TABLENAME);
    }

    public int insert2(final Player player, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            player.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType) VALUES (:id, :pcid, :ucid, :svcid, :pname, :type, :state, :channel, :icon, :exp, :crystal, :renown, :weekRenown, :cur_npc, :npcs, :all_money, :last_money, :last_pay_time, :guid_step, :clancid, :clanPost, :cname, :cicon, :maxBuidId, :maxObstId, :stored_oil, :stored_gold, :isOnline, :protectTime, :maxAttMCId, :maxBONum, :curBONum, :spells, :lastAddObst, :mark, :curtownlvl, :loginDay, :lastLoginTime, :isRewardDay, :firstPayStatus, :pieceHPNum, :pieceDamNum, :pieceAttSpeed, :dayTasks, :lastLeaveClan, :monthCode, :isMonCode, :builds, :obstes, :teches, :heroes, :share, :moneyActivity, :moneyActivityType)");
            Map map = super.insert(sql.toString(), player);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Player> players) {
        return insert(players, TABLENAME);
    }

    public int[] insert(final List<Player> players, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(players == null || players.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType) VALUES (:pcid, :ucid, :svcid, :pname, :type, :state, :channel, :icon, :exp, :crystal, :renown, :weekRenown, :cur_npc, :npcs, :all_money, :last_money, :last_pay_time, :guid_step, :clancid, :clanPost, :cname, :cicon, :maxBuidId, :maxObstId, :stored_oil, :stored_gold, :isOnline, :protectTime, :maxAttMCId, :maxBONum, :curBONum, :spells, :lastAddObst, :mark, :curtownlvl, :loginDay, :lastLoginTime, :isRewardDay, :firstPayStatus, :pieceHPNum, :pieceDamNum, :pieceAttSpeed, :dayTasks, :lastLeaveClan, :monthCode, :isMonCode, :builds, :obstes, :teches, :heroes, :share, :moneyActivity, :moneyActivityType)");
            return super.batchInsert(sql.toString(), players);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int id) {
        return deleteByKey(id, TABLENAME);
    }

    public int deleteByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousDeleteByKey(final int id) {
        asynchronousDeleteByKey(id, TABLENAME);
    }

    public void asynchronousDeleteByKey(final int id, final String TABLENAME2) {
        try{
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        deleteByKey(id, TABLENAME2);
                    } catch (Exception e) {
                       log.info(e2s(e));
                    } finally {
                        decrementAndGet();
                    }
                }
            });
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public int[] deleteByKey(final int[] ids) {
        return deleteByKey(ids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            List list = newList();
            for (int id : keys) {
                Map params = newMap();
                params.put("id", id);
                list.add(params);
            }
            return super.batchUpdate(sql.toString(), list);
        } catch(Exception e) {
            log.info(e2s(e));
            return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInKeys(final List<Integer> keys) {
        return deleteInKeys(keys, TABLENAME);
    }

    public int deleteInKeys(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Player> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Player> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Player player = beans.get(i);
                int id = player.id;
                sb.append(id);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Player> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPKs() {
        return selectPKs(TABLENAME);
    }

    public List<Integer> selectPKs(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Map> selectInIndex() {
        return selectInIndex(TABLENAME);
    }

    public List<Map> selectInIndex(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Player> selectIn(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Player> selectIn2(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectInPKs(final List<Integer> keys) {
        return selectInPKs(keys, TABLENAME);
    }

    public List<Integer> selectInPKs(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            List<Integer> result = newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Player> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLastPKs(final int num) {
        return selectLastPKs(num, TABLENAME);
    }

    public List<Integer> selectLastPKs(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player last() {
        return last(TABLENAME);
    }

    public Player last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Player.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Player> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Player> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int id) {
        return selectGtKeyPKs(id, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Player selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Player.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int maxId() {
        return maxId(TABLENAME);
    }

    public int maxId(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT MAX(id) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByUcidSvcid(final Integer ucid, Integer svcid) {
        return  countByUcidSvcid(ucid, svcid, TABLENAME);
    }

    public int countByUcidSvcid(final Integer ucid, Integer svcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE ucid=:ucid AND svcid=:svcid ");
            Map params = newMap();
            params.put("ucid", ucid);
            params.put("svcid", svcid);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectByUcidSvcid(final Integer ucid, Integer svcid) {
        return selectByUcidSvcid(ucid, svcid, TABLENAME);
    }

    public List<Player> selectByUcidSvcid(final Integer ucid, Integer svcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE ucid=:ucid AND svcid=:svcid ORDER BY id ");
            Map params = newMap();
            params.put("ucid", ucid);
            params.put("svcid", svcid);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByUcidSvcidPKs(final Integer ucid, Integer svcid) {
        return selectByUcidSvcidPKs(ucid, svcid, TABLENAME);
    }

    public List<Integer> selectByUcidSvcidPKs(final Integer ucid, Integer svcid, final String TABLENAME2) {
        List<Integer> result = newList();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE ucid=:ucid AND svcid=:svcid ORDER BY id ");
            Map params = newMap();
            params.put("ucid", ucid);
            params.put("svcid", svcid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectPageByUcidSvcid(final Integer ucid, Integer svcid, final int begin, final int num) {
        return selectPageByUcidSvcid(ucid, svcid, begin, num, TABLENAME);
    }

    public List<Player> selectPageByUcidSvcid(final Integer ucid, Integer svcid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE ucid=:ucid AND svcid=:svcid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("ucid", ucid);
            params.put("svcid", svcid);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByUcidSvcidPKs(final Integer ucid, Integer svcid, final int begin, final int num) {
        return selectPageByUcidSvcidPKs(ucid, svcid, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByUcidSvcidPKs(final Integer ucid, Integer svcid, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE ucid=:ucid AND svcid=:svcid ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("ucid", ucid);
            params.put("svcid", svcid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Player selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Player.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player selectByPname(final String pname) {
        return selectByPname(pname, TABLENAME);
    }

    public Player selectByPname(final String pname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE pname = :pname");
            Map params = newMap();
            params.put("pname", pname);
            return super.queryForObject(sql.toString(), params, Player.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikePname(final String pname) {
        return countLikePname(pname, TABLENAME);
    }

    public int countLikePname(final String pname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE pname LIKE '%").append(pname).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectLikePname(final String pname) {
        return selectLikePname(pname, TABLENAME);
    }

    public List<Player> selectLikePname(final String pname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE pname LIKE '%").append(pname).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikePnamePKs(final String pname) {
        return selectLikePnamePKs(pname, TABLENAME);
    }

    public List<Integer> selectLikePnamePKs(final String pname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE pname LIKE '%").append(pname).append("%' ORDER BY id ");
            Map params = newMap();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player selectByPcid(final Integer pcid) {
        return selectByPcid(pcid, TABLENAME);
    }

    public Player selectByPcid(final Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForObject(sql.toString(), params, Player.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int count() {
        return count(TABLENAME);
    }

    public int count(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append("");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Player> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, pcid, ucid, svcid, pname, type, state, channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs, all_money, last_money, last_pay_time, guid_step, clancid, clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil, stored_gold, isOnline, protectTime, maxAttMCId, maxBONum, curBONum, spells, lastAddObst, mark, curtownlvl, loginDay, lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum, pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode, isMonCode, builds, obstes, teches, heroes, share, moneyActivity, moneyActivityType FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByPagePKs(final int begin, final int num) {
        return selectByPagePKs(begin, num, TABLENAME);
    }

    public List<Integer> selectByPagePKs(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Player player) {
        return updateByKey(player, TABLENAME);
    }

    public int updateByKey(final Player player, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = player.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), player);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void asynchronousUpdate(final Player player) {
        asynchronousUpdate(player, TABLENAME);
    }

    public void asynchronousUpdate(final Player player, final String TABLENAME2) {
        try {

            String _ustr = player.ustr();
            if( _ustr.length() <= 0 ) return;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();

            executor().execute(new Runnable() {
                public void run() {
                    try {
                        update(szSql, player);
                    } catch (Exception e) {
                        log.error(e2s(e));
                    } finally {
                        decrementAndGet();
                    }
                }
            });
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public int updatePcidByKey(final int pcid, final int id){
        return updatePcidByKey(pcid, id, TABLENAME);
    }

    public int updatePcidByKey(final int pcid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid=pcid+:pcid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMinByKey(final int id, final int pcid, final int _min){
        return updatePcidWithMinByKey(id, pcid, _min, TABLENAME);
    }

    public int updatePcidWithMinByKey(final int id, final int pcid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid<=:_min then :_min else pcid+:pcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMinInKeys(final List<Integer> keys, final int pcid, final int _min){
        return updatePcidWithMinInKeys(keys, pcid, _min, TABLENAME);
    }

    public int updatePcidWithMinInKeys(final List<Integer> keys, final int pcid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid<=:_min then :_min else pcid+:pcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMaxByKey(final int id, final int pcid, final int _max){
        return updatePcidWithMaxByKey(id, pcid, _max, TABLENAME);
    }

    public int updatePcidWithMaxByKey(final int id, final int pcid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid>=:_max then :_max else pcid+:pcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMaxInKeys(final List<Integer> keys, final int pcid, final int _max){
        return updatePcidWithMaxInKeys(keys, pcid, _max, TABLENAME);
    }

    public int updatePcidWithMaxInKeys(final List<Integer> keys, final int pcid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid>=:_max then :_max else pcid+:pcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMinMaxByKey(final int id, final int pcid, final int _min, final int _max){
        return updatePcidWithMinMaxByKey(id, pcid, _min, _max, TABLENAME);
    }

    public int updatePcidWithMinMaxByKey(final int id, final int pcid, final int _min, final int _max, final String TABLENAME2){
        if( pcid < 0 ) {
            return updatePcidWithMinByKey(id, pcid, _min, TABLENAME2);
        } else {
            return updatePcidWithMaxByKey(id, pcid, _max, TABLENAME2);
        }
    }

    public int updatePcidWithMinMaxInKeys(final List<Integer> keys, final int pcid, final int _min, final int _max){
        return updatePcidWithMinMaxInKeys(keys, pcid, _min, _max, TABLENAME);
    }

    public int updatePcidWithMinMaxInKeys(final List<Integer> keys, final int pcid, final int _min, final int _max, final String TABLENAME2){
        if( pcid < 0 ) {
            return updatePcidWithMinInKeys(keys, pcid, _min, TABLENAME2);
        } else {
            return updatePcidWithMaxInKeys(keys, pcid, _max, TABLENAME2);
        }
    }

    public int updateUcidByKey(final int ucid, final int id){
        return updateUcidByKey(ucid, id, TABLENAME);
    }

    public int updateUcidByKey(final int ucid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid=ucid+:ucid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("ucid", ucid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateUcidWithMinByKey(final int id, final int ucid, final int _min){
        return updateUcidWithMinByKey(id, ucid, _min, TABLENAME);
    }

    public int updateUcidWithMinByKey(final int id, final int ucid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid = (select case when ucid+:ucid<=:_min then :_min else ucid+:ucid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("ucid", ucid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateUcidWithMinInKeys(final List<Integer> keys, final int ucid, final int _min){
        return updateUcidWithMinInKeys(keys, ucid, _min, TABLENAME);
    }

    public int updateUcidWithMinInKeys(final List<Integer> keys, final int ucid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid = (select case when ucid+:ucid<=:_min then :_min else ucid+:ucid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("ucid", ucid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateUcidWithMaxByKey(final int id, final int ucid, final int _max){
        return updateUcidWithMaxByKey(id, ucid, _max, TABLENAME);
    }

    public int updateUcidWithMaxByKey(final int id, final int ucid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid = (select case when ucid+:ucid>=:_max then :_max else ucid+:ucid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("ucid", ucid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateUcidWithMaxInKeys(final List<Integer> keys, final int ucid, final int _max){
        return updateUcidWithMaxInKeys(keys, ucid, _max, TABLENAME);
    }

    public int updateUcidWithMaxInKeys(final List<Integer> keys, final int ucid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ucid = (select case when ucid+:ucid>=:_max then :_max else ucid+:ucid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("ucid", ucid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateUcidWithMinMaxByKey(final int id, final int ucid, final int _min, final int _max){
        return updateUcidWithMinMaxByKey(id, ucid, _min, _max, TABLENAME);
    }

    public int updateUcidWithMinMaxByKey(final int id, final int ucid, final int _min, final int _max, final String TABLENAME2){
        if( ucid < 0 ) {
            return updateUcidWithMinByKey(id, ucid, _min, TABLENAME2);
        } else {
            return updateUcidWithMaxByKey(id, ucid, _max, TABLENAME2);
        }
    }

    public int updateUcidWithMinMaxInKeys(final List<Integer> keys, final int ucid, final int _min, final int _max){
        return updateUcidWithMinMaxInKeys(keys, ucid, _min, _max, TABLENAME);
    }

    public int updateUcidWithMinMaxInKeys(final List<Integer> keys, final int ucid, final int _min, final int _max, final String TABLENAME2){
        if( ucid < 0 ) {
            return updateUcidWithMinInKeys(keys, ucid, _min, TABLENAME2);
        } else {
            return updateUcidWithMaxInKeys(keys, ucid, _max, TABLENAME2);
        }
    }

    public int updateSvcidByKey(final int svcid, final int id){
        return updateSvcidByKey(svcid, id, TABLENAME);
    }

    public int updateSvcidByKey(final int svcid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid=svcid+:svcid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("svcid", svcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvcidWithMinByKey(final int id, final int svcid, final int _min){
        return updateSvcidWithMinByKey(id, svcid, _min, TABLENAME);
    }

    public int updateSvcidWithMinByKey(final int id, final int svcid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid = (select case when svcid+:svcid<=:_min then :_min else svcid+:svcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("svcid", svcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvcidWithMinInKeys(final List<Integer> keys, final int svcid, final int _min){
        return updateSvcidWithMinInKeys(keys, svcid, _min, TABLENAME);
    }

    public int updateSvcidWithMinInKeys(final List<Integer> keys, final int svcid, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid = (select case when svcid+:svcid<=:_min then :_min else svcid+:svcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("svcid", svcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvcidWithMaxByKey(final int id, final int svcid, final int _max){
        return updateSvcidWithMaxByKey(id, svcid, _max, TABLENAME);
    }

    public int updateSvcidWithMaxByKey(final int id, final int svcid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid = (select case when svcid+:svcid>=:_max then :_max else svcid+:svcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("svcid", svcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvcidWithMaxInKeys(final List<Integer> keys, final int svcid, final int _max){
        return updateSvcidWithMaxInKeys(keys, svcid, _max, TABLENAME);
    }

    public int updateSvcidWithMaxInKeys(final List<Integer> keys, final int svcid, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET svcid = (select case when svcid+:svcid>=:_max then :_max else svcid+:svcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("svcid", svcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSvcidWithMinMaxByKey(final int id, final int svcid, final int _min, final int _max){
        return updateSvcidWithMinMaxByKey(id, svcid, _min, _max, TABLENAME);
    }

    public int updateSvcidWithMinMaxByKey(final int id, final int svcid, final int _min, final int _max, final String TABLENAME2){
        if( svcid < 0 ) {
            return updateSvcidWithMinByKey(id, svcid, _min, TABLENAME2);
        } else {
            return updateSvcidWithMaxByKey(id, svcid, _max, TABLENAME2);
        }
    }

    public int updateSvcidWithMinMaxInKeys(final List<Integer> keys, final int svcid, final int _min, final int _max){
        return updateSvcidWithMinMaxInKeys(keys, svcid, _min, _max, TABLENAME);
    }

    public int updateSvcidWithMinMaxInKeys(final List<Integer> keys, final int svcid, final int _min, final int _max, final String TABLENAME2){
        if( svcid < 0 ) {
            return updateSvcidWithMinInKeys(keys, svcid, _min, TABLENAME2);
        } else {
            return updateSvcidWithMaxInKeys(keys, svcid, _max, TABLENAME2);
        }
    }

    public int updateTypeByKey(final int type, final int id){
        return updateTypeByKey(type, id, TABLENAME);
    }

    public int updateTypeByKey(final int type, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type=type+:type WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinByKey(final int id, final int type, final int _min){
        return updateTypeWithMinByKey(id, type, _min, TABLENAME);
    }

    public int updateTypeWithMinByKey(final int id, final int type, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type<=:_min then :_min else type+:type end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinInKeys(final List<Integer> keys, final int type, final int _min){
        return updateTypeWithMinInKeys(keys, type, _min, TABLENAME);
    }

    public int updateTypeWithMinInKeys(final List<Integer> keys, final int type, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type<=:_min then :_min else type+:type end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMaxByKey(final int id, final int type, final int _max){
        return updateTypeWithMaxByKey(id, type, _max, TABLENAME);
    }

    public int updateTypeWithMaxByKey(final int id, final int type, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type>=:_max then :_max else type+:type end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMaxInKeys(final List<Integer> keys, final int type, final int _max){
        return updateTypeWithMaxInKeys(keys, type, _max, TABLENAME);
    }

    public int updateTypeWithMaxInKeys(final List<Integer> keys, final int type, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET type = (select case when type+:type>=:_max then :_max else type+:type end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("type", type);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTypeWithMinMaxByKey(final int id, final int type, final int _min, final int _max){
        return updateTypeWithMinMaxByKey(id, type, _min, _max, TABLENAME);
    }

    public int updateTypeWithMinMaxByKey(final int id, final int type, final int _min, final int _max, final String TABLENAME2){
        if( type < 0 ) {
            return updateTypeWithMinByKey(id, type, _min, TABLENAME2);
        } else {
            return updateTypeWithMaxByKey(id, type, _max, TABLENAME2);
        }
    }

    public int updateTypeWithMinMaxInKeys(final List<Integer> keys, final int type, final int _min, final int _max){
        return updateTypeWithMinMaxInKeys(keys, type, _min, _max, TABLENAME);
    }

    public int updateTypeWithMinMaxInKeys(final List<Integer> keys, final int type, final int _min, final int _max, final String TABLENAME2){
        if( type < 0 ) {
            return updateTypeWithMinInKeys(keys, type, _min, TABLENAME2);
        } else {
            return updateTypeWithMaxInKeys(keys, type, _max, TABLENAME2);
        }
    }

    public int updateStateByKey(final int state, final int id){
        return updateStateByKey(state, id, TABLENAME);
    }

    public int updateStateByKey(final int state, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET state=state+:state WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("state", state);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStateWithMinByKey(final int id, final int state, final int _min){
        return updateStateWithMinByKey(id, state, _min, TABLENAME);
    }

    public int updateStateWithMinByKey(final int id, final int state, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET state = (select case when state+:state<=:_min then :_min else state+:state end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("state", state);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStateWithMinInKeys(final List<Integer> keys, final int state, final int _min){
        return updateStateWithMinInKeys(keys, state, _min, TABLENAME);
    }

    public int updateStateWithMinInKeys(final List<Integer> keys, final int state, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET state = (select case when state+:state<=:_min then :_min else state+:state end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("state", state);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStateWithMaxByKey(final int id, final int state, final int _max){
        return updateStateWithMaxByKey(id, state, _max, TABLENAME);
    }

    public int updateStateWithMaxByKey(final int id, final int state, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET state = (select case when state+:state>=:_max then :_max else state+:state end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("state", state);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStateWithMaxInKeys(final List<Integer> keys, final int state, final int _max){
        return updateStateWithMaxInKeys(keys, state, _max, TABLENAME);
    }

    public int updateStateWithMaxInKeys(final List<Integer> keys, final int state, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET state = (select case when state+:state>=:_max then :_max else state+:state end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("state", state);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStateWithMinMaxByKey(final int id, final int state, final int _min, final int _max){
        return updateStateWithMinMaxByKey(id, state, _min, _max, TABLENAME);
    }

    public int updateStateWithMinMaxByKey(final int id, final int state, final int _min, final int _max, final String TABLENAME2){
        if( state < 0 ) {
            return updateStateWithMinByKey(id, state, _min, TABLENAME2);
        } else {
            return updateStateWithMaxByKey(id, state, _max, TABLENAME2);
        }
    }

    public int updateStateWithMinMaxInKeys(final List<Integer> keys, final int state, final int _min, final int _max){
        return updateStateWithMinMaxInKeys(keys, state, _min, _max, TABLENAME);
    }

    public int updateStateWithMinMaxInKeys(final List<Integer> keys, final int state, final int _min, final int _max, final String TABLENAME2){
        if( state < 0 ) {
            return updateStateWithMinInKeys(keys, state, _min, TABLENAME2);
        } else {
            return updateStateWithMaxInKeys(keys, state, _max, TABLENAME2);
        }
    }

    public int updateIconByKey(final int icon, final int id){
        return updateIconByKey(icon, id, TABLENAME);
    }

    public int updateIconByKey(final int icon, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET icon=icon+:icon WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("icon", icon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIconWithMinByKey(final int id, final int icon, final int _min){
        return updateIconWithMinByKey(id, icon, _min, TABLENAME);
    }

    public int updateIconWithMinByKey(final int id, final int icon, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET icon = (select case when icon+:icon<=:_min then :_min else icon+:icon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("icon", icon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIconWithMinInKeys(final List<Integer> keys, final int icon, final int _min){
        return updateIconWithMinInKeys(keys, icon, _min, TABLENAME);
    }

    public int updateIconWithMinInKeys(final List<Integer> keys, final int icon, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET icon = (select case when icon+:icon<=:_min then :_min else icon+:icon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("icon", icon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIconWithMaxByKey(final int id, final int icon, final int _max){
        return updateIconWithMaxByKey(id, icon, _max, TABLENAME);
    }

    public int updateIconWithMaxByKey(final int id, final int icon, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET icon = (select case when icon+:icon>=:_max then :_max else icon+:icon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("icon", icon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIconWithMaxInKeys(final List<Integer> keys, final int icon, final int _max){
        return updateIconWithMaxInKeys(keys, icon, _max, TABLENAME);
    }

    public int updateIconWithMaxInKeys(final List<Integer> keys, final int icon, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET icon = (select case when icon+:icon>=:_max then :_max else icon+:icon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("icon", icon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIconWithMinMaxByKey(final int id, final int icon, final int _min, final int _max){
        return updateIconWithMinMaxByKey(id, icon, _min, _max, TABLENAME);
    }

    public int updateIconWithMinMaxByKey(final int id, final int icon, final int _min, final int _max, final String TABLENAME2){
        if( icon < 0 ) {
            return updateIconWithMinByKey(id, icon, _min, TABLENAME2);
        } else {
            return updateIconWithMaxByKey(id, icon, _max, TABLENAME2);
        }
    }

    public int updateIconWithMinMaxInKeys(final List<Integer> keys, final int icon, final int _min, final int _max){
        return updateIconWithMinMaxInKeys(keys, icon, _min, _max, TABLENAME);
    }

    public int updateIconWithMinMaxInKeys(final List<Integer> keys, final int icon, final int _min, final int _max, final String TABLENAME2){
        if( icon < 0 ) {
            return updateIconWithMinInKeys(keys, icon, _min, TABLENAME2);
        } else {
            return updateIconWithMaxInKeys(keys, icon, _max, TABLENAME2);
        }
    }

    public int updateExpByKey(final int exp, final int id){
        return updateExpByKey(exp, id, TABLENAME);
    }

    public int updateExpByKey(final int exp, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET exp=exp+:exp WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("exp", exp);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExpWithMinByKey(final int id, final int exp, final int _min){
        return updateExpWithMinByKey(id, exp, _min, TABLENAME);
    }

    public int updateExpWithMinByKey(final int id, final int exp, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET exp = (select case when exp+:exp<=:_min then :_min else exp+:exp end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("exp", exp);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExpWithMinInKeys(final List<Integer> keys, final int exp, final int _min){
        return updateExpWithMinInKeys(keys, exp, _min, TABLENAME);
    }

    public int updateExpWithMinInKeys(final List<Integer> keys, final int exp, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET exp = (select case when exp+:exp<=:_min then :_min else exp+:exp end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("exp", exp);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExpWithMaxByKey(final int id, final int exp, final int _max){
        return updateExpWithMaxByKey(id, exp, _max, TABLENAME);
    }

    public int updateExpWithMaxByKey(final int id, final int exp, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET exp = (select case when exp+:exp>=:_max then :_max else exp+:exp end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("exp", exp);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExpWithMaxInKeys(final List<Integer> keys, final int exp, final int _max){
        return updateExpWithMaxInKeys(keys, exp, _max, TABLENAME);
    }

    public int updateExpWithMaxInKeys(final List<Integer> keys, final int exp, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET exp = (select case when exp+:exp>=:_max then :_max else exp+:exp end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("exp", exp);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateExpWithMinMaxByKey(final int id, final int exp, final int _min, final int _max){
        return updateExpWithMinMaxByKey(id, exp, _min, _max, TABLENAME);
    }

    public int updateExpWithMinMaxByKey(final int id, final int exp, final int _min, final int _max, final String TABLENAME2){
        if( exp < 0 ) {
            return updateExpWithMinByKey(id, exp, _min, TABLENAME2);
        } else {
            return updateExpWithMaxByKey(id, exp, _max, TABLENAME2);
        }
    }

    public int updateExpWithMinMaxInKeys(final List<Integer> keys, final int exp, final int _min, final int _max){
        return updateExpWithMinMaxInKeys(keys, exp, _min, _max, TABLENAME);
    }

    public int updateExpWithMinMaxInKeys(final List<Integer> keys, final int exp, final int _min, final int _max, final String TABLENAME2){
        if( exp < 0 ) {
            return updateExpWithMinInKeys(keys, exp, _min, TABLENAME2);
        } else {
            return updateExpWithMaxInKeys(keys, exp, _max, TABLENAME2);
        }
    }

    public int updateCrystalByKey(final int crystal, final int id){
        return updateCrystalByKey(crystal, id, TABLENAME);
    }

    public int updateCrystalByKey(final int crystal, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET crystal=crystal+:crystal WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("crystal", crystal);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCrystalWithMinByKey(final int id, final int crystal, final int _min){
        return updateCrystalWithMinByKey(id, crystal, _min, TABLENAME);
    }

    public int updateCrystalWithMinByKey(final int id, final int crystal, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET crystal = (select case when crystal+:crystal<=:_min then :_min else crystal+:crystal end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("crystal", crystal);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCrystalWithMinInKeys(final List<Integer> keys, final int crystal, final int _min){
        return updateCrystalWithMinInKeys(keys, crystal, _min, TABLENAME);
    }

    public int updateCrystalWithMinInKeys(final List<Integer> keys, final int crystal, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET crystal = (select case when crystal+:crystal<=:_min then :_min else crystal+:crystal end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("crystal", crystal);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCrystalWithMaxByKey(final int id, final int crystal, final int _max){
        return updateCrystalWithMaxByKey(id, crystal, _max, TABLENAME);
    }

    public int updateCrystalWithMaxByKey(final int id, final int crystal, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET crystal = (select case when crystal+:crystal>=:_max then :_max else crystal+:crystal end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("crystal", crystal);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCrystalWithMaxInKeys(final List<Integer> keys, final int crystal, final int _max){
        return updateCrystalWithMaxInKeys(keys, crystal, _max, TABLENAME);
    }

    public int updateCrystalWithMaxInKeys(final List<Integer> keys, final int crystal, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET crystal = (select case when crystal+:crystal>=:_max then :_max else crystal+:crystal end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("crystal", crystal);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCrystalWithMinMaxByKey(final int id, final int crystal, final int _min, final int _max){
        return updateCrystalWithMinMaxByKey(id, crystal, _min, _max, TABLENAME);
    }

    public int updateCrystalWithMinMaxByKey(final int id, final int crystal, final int _min, final int _max, final String TABLENAME2){
        if( crystal < 0 ) {
            return updateCrystalWithMinByKey(id, crystal, _min, TABLENAME2);
        } else {
            return updateCrystalWithMaxByKey(id, crystal, _max, TABLENAME2);
        }
    }

    public int updateCrystalWithMinMaxInKeys(final List<Integer> keys, final int crystal, final int _min, final int _max){
        return updateCrystalWithMinMaxInKeys(keys, crystal, _min, _max, TABLENAME);
    }

    public int updateCrystalWithMinMaxInKeys(final List<Integer> keys, final int crystal, final int _min, final int _max, final String TABLENAME2){
        if( crystal < 0 ) {
            return updateCrystalWithMinInKeys(keys, crystal, _min, TABLENAME2);
        } else {
            return updateCrystalWithMaxInKeys(keys, crystal, _max, TABLENAME2);
        }
    }

    public int updateRenownByKey(final int renown, final int id){
        return updateRenownByKey(renown, id, TABLENAME);
    }

    public int updateRenownByKey(final int renown, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renown=renown+:renown WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("renown", renown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownWithMinByKey(final int id, final int renown, final int _min){
        return updateRenownWithMinByKey(id, renown, _min, TABLENAME);
    }

    public int updateRenownWithMinByKey(final int id, final int renown, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renown = (select case when renown+:renown<=:_min then :_min else renown+:renown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("renown", renown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownWithMinInKeys(final List<Integer> keys, final int renown, final int _min){
        return updateRenownWithMinInKeys(keys, renown, _min, TABLENAME);
    }

    public int updateRenownWithMinInKeys(final List<Integer> keys, final int renown, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renown = (select case when renown+:renown<=:_min then :_min else renown+:renown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("renown", renown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownWithMaxByKey(final int id, final int renown, final int _max){
        return updateRenownWithMaxByKey(id, renown, _max, TABLENAME);
    }

    public int updateRenownWithMaxByKey(final int id, final int renown, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renown = (select case when renown+:renown>=:_max then :_max else renown+:renown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("renown", renown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownWithMaxInKeys(final List<Integer> keys, final int renown, final int _max){
        return updateRenownWithMaxInKeys(keys, renown, _max, TABLENAME);
    }

    public int updateRenownWithMaxInKeys(final List<Integer> keys, final int renown, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET renown = (select case when renown+:renown>=:_max then :_max else renown+:renown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("renown", renown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateRenownWithMinMaxByKey(final int id, final int renown, final int _min, final int _max){
        return updateRenownWithMinMaxByKey(id, renown, _min, _max, TABLENAME);
    }

    public int updateRenownWithMinMaxByKey(final int id, final int renown, final int _min, final int _max, final String TABLENAME2){
        if( renown < 0 ) {
            return updateRenownWithMinByKey(id, renown, _min, TABLENAME2);
        } else {
            return updateRenownWithMaxByKey(id, renown, _max, TABLENAME2);
        }
    }

    public int updateRenownWithMinMaxInKeys(final List<Integer> keys, final int renown, final int _min, final int _max){
        return updateRenownWithMinMaxInKeys(keys, renown, _min, _max, TABLENAME);
    }

    public int updateRenownWithMinMaxInKeys(final List<Integer> keys, final int renown, final int _min, final int _max, final String TABLENAME2){
        if( renown < 0 ) {
            return updateRenownWithMinInKeys(keys, renown, _min, TABLENAME2);
        } else {
            return updateRenownWithMaxInKeys(keys, renown, _max, TABLENAME2);
        }
    }

    public int updateWeekrenownByKey(final int weekRenown, final int id){
        return updateWeekrenownByKey(weekRenown, id, TABLENAME);
    }

    public int updateWeekrenownByKey(final int weekRenown, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET weekRenown=weekRenown+:weekRenown WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("weekRenown", weekRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWeekrenownWithMinByKey(final int id, final int weekRenown, final int _min){
        return updateWeekrenownWithMinByKey(id, weekRenown, _min, TABLENAME);
    }

    public int updateWeekrenownWithMinByKey(final int id, final int weekRenown, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET weekRenown = (select case when weekRenown+:weekRenown<=:_min then :_min else weekRenown+:weekRenown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("weekRenown", weekRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWeekrenownWithMinInKeys(final List<Integer> keys, final int weekRenown, final int _min){
        return updateWeekrenownWithMinInKeys(keys, weekRenown, _min, TABLENAME);
    }

    public int updateWeekrenownWithMinInKeys(final List<Integer> keys, final int weekRenown, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET weekRenown = (select case when weekRenown+:weekRenown<=:_min then :_min else weekRenown+:weekRenown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("weekRenown", weekRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWeekrenownWithMaxByKey(final int id, final int weekRenown, final int _max){
        return updateWeekrenownWithMaxByKey(id, weekRenown, _max, TABLENAME);
    }

    public int updateWeekrenownWithMaxByKey(final int id, final int weekRenown, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET weekRenown = (select case when weekRenown+:weekRenown>=:_max then :_max else weekRenown+:weekRenown end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("weekRenown", weekRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWeekrenownWithMaxInKeys(final List<Integer> keys, final int weekRenown, final int _max){
        return updateWeekrenownWithMaxInKeys(keys, weekRenown, _max, TABLENAME);
    }

    public int updateWeekrenownWithMaxInKeys(final List<Integer> keys, final int weekRenown, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET weekRenown = (select case when weekRenown+:weekRenown>=:_max then :_max else weekRenown+:weekRenown end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("weekRenown", weekRenown);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWeekrenownWithMinMaxByKey(final int id, final int weekRenown, final int _min, final int _max){
        return updateWeekrenownWithMinMaxByKey(id, weekRenown, _min, _max, TABLENAME);
    }

    public int updateWeekrenownWithMinMaxByKey(final int id, final int weekRenown, final int _min, final int _max, final String TABLENAME2){
        if( weekRenown < 0 ) {
            return updateWeekrenownWithMinByKey(id, weekRenown, _min, TABLENAME2);
        } else {
            return updateWeekrenownWithMaxByKey(id, weekRenown, _max, TABLENAME2);
        }
    }

    public int updateWeekrenownWithMinMaxInKeys(final List<Integer> keys, final int weekRenown, final int _min, final int _max){
        return updateWeekrenownWithMinMaxInKeys(keys, weekRenown, _min, _max, TABLENAME);
    }

    public int updateWeekrenownWithMinMaxInKeys(final List<Integer> keys, final int weekRenown, final int _min, final int _max, final String TABLENAME2){
        if( weekRenown < 0 ) {
            return updateWeekrenownWithMinInKeys(keys, weekRenown, _min, TABLENAME2);
        } else {
            return updateWeekrenownWithMaxInKeys(keys, weekRenown, _max, TABLENAME2);
        }
    }

    public int updateCur_npcByKey(final int cur_npc, final int id){
        return updateCur_npcByKey(cur_npc, id, TABLENAME);
    }

    public int updateCur_npcByKey(final int cur_npc, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_npc=cur_npc+:cur_npc WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("cur_npc", cur_npc);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_npcWithMinByKey(final int id, final int cur_npc, final int _min){
        return updateCur_npcWithMinByKey(id, cur_npc, _min, TABLENAME);
    }

    public int updateCur_npcWithMinByKey(final int id, final int cur_npc, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_npc = (select case when cur_npc+:cur_npc<=:_min then :_min else cur_npc+:cur_npc end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("cur_npc", cur_npc);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_npcWithMinInKeys(final List<Integer> keys, final int cur_npc, final int _min){
        return updateCur_npcWithMinInKeys(keys, cur_npc, _min, TABLENAME);
    }

    public int updateCur_npcWithMinInKeys(final List<Integer> keys, final int cur_npc, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_npc = (select case when cur_npc+:cur_npc<=:_min then :_min else cur_npc+:cur_npc end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("cur_npc", cur_npc);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_npcWithMaxByKey(final int id, final int cur_npc, final int _max){
        return updateCur_npcWithMaxByKey(id, cur_npc, _max, TABLENAME);
    }

    public int updateCur_npcWithMaxByKey(final int id, final int cur_npc, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_npc = (select case when cur_npc+:cur_npc>=:_max then :_max else cur_npc+:cur_npc end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("cur_npc", cur_npc);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_npcWithMaxInKeys(final List<Integer> keys, final int cur_npc, final int _max){
        return updateCur_npcWithMaxInKeys(keys, cur_npc, _max, TABLENAME);
    }

    public int updateCur_npcWithMaxInKeys(final List<Integer> keys, final int cur_npc, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cur_npc = (select case when cur_npc+:cur_npc>=:_max then :_max else cur_npc+:cur_npc end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("cur_npc", cur_npc);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCur_npcWithMinMaxByKey(final int id, final int cur_npc, final int _min, final int _max){
        return updateCur_npcWithMinMaxByKey(id, cur_npc, _min, _max, TABLENAME);
    }

    public int updateCur_npcWithMinMaxByKey(final int id, final int cur_npc, final int _min, final int _max, final String TABLENAME2){
        if( cur_npc < 0 ) {
            return updateCur_npcWithMinByKey(id, cur_npc, _min, TABLENAME2);
        } else {
            return updateCur_npcWithMaxByKey(id, cur_npc, _max, TABLENAME2);
        }
    }

    public int updateCur_npcWithMinMaxInKeys(final List<Integer> keys, final int cur_npc, final int _min, final int _max){
        return updateCur_npcWithMinMaxInKeys(keys, cur_npc, _min, _max, TABLENAME);
    }

    public int updateCur_npcWithMinMaxInKeys(final List<Integer> keys, final int cur_npc, final int _min, final int _max, final String TABLENAME2){
        if( cur_npc < 0 ) {
            return updateCur_npcWithMinInKeys(keys, cur_npc, _min, TABLENAME2);
        } else {
            return updateCur_npcWithMaxInKeys(keys, cur_npc, _max, TABLENAME2);
        }
    }

    public int updateAll_moneyByKey(final int all_money, final int id){
        return updateAll_moneyByKey(all_money, id, TABLENAME);
    }

    public int updateAll_moneyByKey(final int all_money, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET all_money=all_money+:all_money WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("all_money", all_money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAll_moneyWithMinByKey(final int id, final int all_money, final int _min){
        return updateAll_moneyWithMinByKey(id, all_money, _min, TABLENAME);
    }

    public int updateAll_moneyWithMinByKey(final int id, final int all_money, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET all_money = (select case when all_money+:all_money<=:_min then :_min else all_money+:all_money end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("all_money", all_money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAll_moneyWithMinInKeys(final List<Integer> keys, final int all_money, final int _min){
        return updateAll_moneyWithMinInKeys(keys, all_money, _min, TABLENAME);
    }

    public int updateAll_moneyWithMinInKeys(final List<Integer> keys, final int all_money, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET all_money = (select case when all_money+:all_money<=:_min then :_min else all_money+:all_money end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("all_money", all_money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAll_moneyWithMaxByKey(final int id, final int all_money, final int _max){
        return updateAll_moneyWithMaxByKey(id, all_money, _max, TABLENAME);
    }

    public int updateAll_moneyWithMaxByKey(final int id, final int all_money, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET all_money = (select case when all_money+:all_money>=:_max then :_max else all_money+:all_money end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("all_money", all_money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAll_moneyWithMaxInKeys(final List<Integer> keys, final int all_money, final int _max){
        return updateAll_moneyWithMaxInKeys(keys, all_money, _max, TABLENAME);
    }

    public int updateAll_moneyWithMaxInKeys(final List<Integer> keys, final int all_money, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET all_money = (select case when all_money+:all_money>=:_max then :_max else all_money+:all_money end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("all_money", all_money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateAll_moneyWithMinMaxByKey(final int id, final int all_money, final int _min, final int _max){
        return updateAll_moneyWithMinMaxByKey(id, all_money, _min, _max, TABLENAME);
    }

    public int updateAll_moneyWithMinMaxByKey(final int id, final int all_money, final int _min, final int _max, final String TABLENAME2){
        if( all_money < 0 ) {
            return updateAll_moneyWithMinByKey(id, all_money, _min, TABLENAME2);
        } else {
            return updateAll_moneyWithMaxByKey(id, all_money, _max, TABLENAME2);
        }
    }

    public int updateAll_moneyWithMinMaxInKeys(final List<Integer> keys, final int all_money, final int _min, final int _max){
        return updateAll_moneyWithMinMaxInKeys(keys, all_money, _min, _max, TABLENAME);
    }

    public int updateAll_moneyWithMinMaxInKeys(final List<Integer> keys, final int all_money, final int _min, final int _max, final String TABLENAME2){
        if( all_money < 0 ) {
            return updateAll_moneyWithMinInKeys(keys, all_money, _min, TABLENAME2);
        } else {
            return updateAll_moneyWithMaxInKeys(keys, all_money, _max, TABLENAME2);
        }
    }

    public int updateLast_moneyByKey(final int last_money, final int id){
        return updateLast_moneyByKey(last_money, id, TABLENAME);
    }

    public int updateLast_moneyByKey(final int last_money, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET last_money=last_money+:last_money WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("last_money", last_money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLast_moneyWithMinByKey(final int id, final int last_money, final int _min){
        return updateLast_moneyWithMinByKey(id, last_money, _min, TABLENAME);
    }

    public int updateLast_moneyWithMinByKey(final int id, final int last_money, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET last_money = (select case when last_money+:last_money<=:_min then :_min else last_money+:last_money end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("last_money", last_money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLast_moneyWithMinInKeys(final List<Integer> keys, final int last_money, final int _min){
        return updateLast_moneyWithMinInKeys(keys, last_money, _min, TABLENAME);
    }

    public int updateLast_moneyWithMinInKeys(final List<Integer> keys, final int last_money, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET last_money = (select case when last_money+:last_money<=:_min then :_min else last_money+:last_money end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("last_money", last_money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLast_moneyWithMaxByKey(final int id, final int last_money, final int _max){
        return updateLast_moneyWithMaxByKey(id, last_money, _max, TABLENAME);
    }

    public int updateLast_moneyWithMaxByKey(final int id, final int last_money, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET last_money = (select case when last_money+:last_money>=:_max then :_max else last_money+:last_money end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("last_money", last_money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLast_moneyWithMaxInKeys(final List<Integer> keys, final int last_money, final int _max){
        return updateLast_moneyWithMaxInKeys(keys, last_money, _max, TABLENAME);
    }

    public int updateLast_moneyWithMaxInKeys(final List<Integer> keys, final int last_money, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET last_money = (select case when last_money+:last_money>=:_max then :_max else last_money+:last_money end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("last_money", last_money);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLast_moneyWithMinMaxByKey(final int id, final int last_money, final int _min, final int _max){
        return updateLast_moneyWithMinMaxByKey(id, last_money, _min, _max, TABLENAME);
    }

    public int updateLast_moneyWithMinMaxByKey(final int id, final int last_money, final int _min, final int _max, final String TABLENAME2){
        if( last_money < 0 ) {
            return updateLast_moneyWithMinByKey(id, last_money, _min, TABLENAME2);
        } else {
            return updateLast_moneyWithMaxByKey(id, last_money, _max, TABLENAME2);
        }
    }

    public int updateLast_moneyWithMinMaxInKeys(final List<Integer> keys, final int last_money, final int _min, final int _max){
        return updateLast_moneyWithMinMaxInKeys(keys, last_money, _min, _max, TABLENAME);
    }

    public int updateLast_moneyWithMinMaxInKeys(final List<Integer> keys, final int last_money, final int _min, final int _max, final String TABLENAME2){
        if( last_money < 0 ) {
            return updateLast_moneyWithMinInKeys(keys, last_money, _min, TABLENAME2);
        } else {
            return updateLast_moneyWithMaxInKeys(keys, last_money, _max, TABLENAME2);
        }
    }

    public int updateLast_pay_timeByKey(final long last_pay_time, final int id){
        return updateLast_pay_timeByKey(last_pay_time, id, TABLENAME);
    }

    public int updateLast_pay_timeByKey(final long last_pay_time, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET last_pay_time=last_pay_time+:last_pay_time WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("last_pay_time", last_pay_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLast_pay_timeWithMinByKey(final int id, final long last_pay_time, final long _min){
        return updateLast_pay_timeWithMinByKey(id, last_pay_time, _min, TABLENAME);
    }

    public int updateLast_pay_timeWithMinByKey(final int id, final long last_pay_time, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET last_pay_time = (select case when last_pay_time+:last_pay_time<=:_min then :_min else last_pay_time+:last_pay_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("last_pay_time", last_pay_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLast_pay_timeWithMinInKeys(final List<Integer> keys, final long last_pay_time, final long _min){
        return updateLast_pay_timeWithMinInKeys(keys, last_pay_time, _min, TABLENAME);
    }

    public int updateLast_pay_timeWithMinInKeys(final List<Integer> keys, final long last_pay_time, final long _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET last_pay_time = (select case when last_pay_time+:last_pay_time<=:_min then :_min else last_pay_time+:last_pay_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("last_pay_time", last_pay_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLast_pay_timeWithMaxByKey(final int id, final long last_pay_time, final long _max){
        return updateLast_pay_timeWithMaxByKey(id, last_pay_time, _max, TABLENAME);
    }

    public int updateLast_pay_timeWithMaxByKey(final int id, final long last_pay_time, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET last_pay_time = (select case when last_pay_time+:last_pay_time>=:_max then :_max else last_pay_time+:last_pay_time end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("last_pay_time", last_pay_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLast_pay_timeWithMaxInKeys(final List<Integer> keys, final long last_pay_time, final long _max){
        return updateLast_pay_timeWithMaxInKeys(keys, last_pay_time, _max, TABLENAME);
    }

    public int updateLast_pay_timeWithMaxInKeys(final List<Integer> keys, final long last_pay_time, final long _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET last_pay_time = (select case when last_pay_time+:last_pay_time>=:_max then :_max else last_pay_time+:last_pay_time end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("last_pay_time", last_pay_time);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLast_pay_timeWithMinMaxByKey(final int id, final long last_pay_time, final long _min, final long _max){
        return updateLast_pay_timeWithMinMaxByKey(id, last_pay_time, _min, _max, TABLENAME);
    }

    public int updateLast_pay_timeWithMinMaxByKey(final int id, final long last_pay_time, final long _min, final long _max, final String TABLENAME2){
        if( last_pay_time < 0 ) {
            return updateLast_pay_timeWithMinByKey(id, last_pay_time, _min, TABLENAME2);
        } else {
            return updateLast_pay_timeWithMaxByKey(id, last_pay_time, _max, TABLENAME2);
        }
    }

    public int updateLast_pay_timeWithMinMaxInKeys(final List<Integer> keys, final long last_pay_time, final long _min, final long _max){
        return updateLast_pay_timeWithMinMaxInKeys(keys, last_pay_time, _min, _max, TABLENAME);
    }

    public int updateLast_pay_timeWithMinMaxInKeys(final List<Integer> keys, final long last_pay_time, final long _min, final long _max, final String TABLENAME2){
        if( last_pay_time < 0 ) {
            return updateLast_pay_timeWithMinInKeys(keys, last_pay_time, _min, TABLENAME2);
        } else {
            return updateLast_pay_timeWithMaxInKeys(keys, last_pay_time, _max, TABLENAME2);
        }
    }

    public int updateGuid_stepByKey(final int guid_step, final int id){
        return updateGuid_stepByKey(guid_step, id, TABLENAME);
    }

    public int updateGuid_stepByKey(final int guid_step, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET guid_step=guid_step+:guid_step WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("guid_step", guid_step);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGuid_stepWithMinByKey(final int id, final int guid_step, final int _min){
        return updateGuid_stepWithMinByKey(id, guid_step, _min, TABLENAME);
    }

    public int updateGuid_stepWithMinByKey(final int id, final int guid_step, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET guid_step = (select case when guid_step+:guid_step<=:_min then :_min else guid_step+:guid_step end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("guid_step", guid_step);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGuid_stepWithMinInKeys(final List<Integer> keys, final int guid_step, final int _min){
        return updateGuid_stepWithMinInKeys(keys, guid_step, _min, TABLENAME);
    }

    public int updateGuid_stepWithMinInKeys(final List<Integer> keys, final int guid_step, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET guid_step = (select case when guid_step+:guid_step<=:_min then :_min else guid_step+:guid_step end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("guid_step", guid_step);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGuid_stepWithMaxByKey(final int id, final int guid_step, final int _max){
        return updateGuid_stepWithMaxByKey(id, guid_step, _max, TABLENAME);
    }

    public int updateGuid_stepWithMaxByKey(final int id, final int guid_step, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET guid_step = (select case when guid_step+:guid_step>=:_max then :_max else guid_step+:guid_step end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("guid_step", guid_step);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGuid_stepWithMaxInKeys(final List<Integer> keys, final int guid_step, final int _max){
        return updateGuid_stepWithMaxInKeys(keys, guid_step, _max, TABLENAME);
    }

    public int updateGuid_stepWithMaxInKeys(final List<Integer> keys, final int guid_step, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET guid_step = (select case when guid_step+:guid_step>=:_max then :_max else guid_step+:guid_step end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("guid_step", guid_step);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateGuid_stepWithMinMaxByKey(final int id, final int guid_step, final int _min, final int _max){
        return updateGuid_stepWithMinMaxByKey(id, guid_step, _min, _max, TABLENAME);
    }

    public int updateGuid_stepWithMinMaxByKey(final int id, final int guid_step, final int _min, final int _max, final String TABLENAME2){
        if( guid_step < 0 ) {
            return updateGuid_stepWithMinByKey(id, guid_step, _min, TABLENAME2);
        } else {
            return updateGuid_stepWithMaxByKey(id, guid_step, _max, TABLENAME2);
        }
    }

    public int updateGuid_stepWithMinMaxInKeys(final List<Integer> keys, final int guid_step, final int _min, final int _max){
        return updateGuid_stepWithMinMaxInKeys(keys, guid_step, _min, _max, TABLENAME);
    }

    public int updateGuid_stepWithMinMaxInKeys(final List<Integer> keys, final int guid_step, final int _min, final int _max, final String TABLENAME2){
        if( guid_step < 0 ) {
            return updateGuid_stepWithMinInKeys(keys, guid_step, _min, TABLENAME2);
        } else {
            return updateGuid_stepWithMaxInKeys(keys, guid_step, _max, TABLENAME2);
        }
    }

    public int updateClanpostByKey(final int clanPost, final int id){
        return updateClanpostByKey(clanPost, id, TABLENAME);
    }

    public int updateClanpostByKey(final int clanPost, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET clanPost=clanPost+:clanPost WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("clanPost", clanPost);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateClanpostWithMinByKey(final int id, final int clanPost, final int _min){
        return updateClanpostWithMinByKey(id, clanPost, _min, TABLENAME);
    }

    public int updateClanpostWithMinByKey(final int id, final int clanPost, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET clanPost = (select case when clanPost+:clanPost<=:_min then :_min else clanPost+:clanPost end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("clanPost", clanPost);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateClanpostWithMinInKeys(final List<Integer> keys, final int clanPost, final int _min){
        return updateClanpostWithMinInKeys(keys, clanPost, _min, TABLENAME);
    }

    public int updateClanpostWithMinInKeys(final List<Integer> keys, final int clanPost, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET clanPost = (select case when clanPost+:clanPost<=:_min then :_min else clanPost+:clanPost end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("clanPost", clanPost);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateClanpostWithMaxByKey(final int id, final int clanPost, final int _max){
        return updateClanpostWithMaxByKey(id, clanPost, _max, TABLENAME);
    }

    public int updateClanpostWithMaxByKey(final int id, final int clanPost, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET clanPost = (select case when clanPost+:clanPost>=:_max then :_max else clanPost+:clanPost end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("clanPost", clanPost);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateClanpostWithMaxInKeys(final List<Integer> keys, final int clanPost, final int _max){
        return updateClanpostWithMaxInKeys(keys, clanPost, _max, TABLENAME);
    }

    public int updateClanpostWithMaxInKeys(final List<Integer> keys, final int clanPost, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET clanPost = (select case when clanPost+:clanPost>=:_max then :_max else clanPost+:clanPost end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("clanPost", clanPost);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateClanpostWithMinMaxByKey(final int id, final int clanPost, final int _min, final int _max){
        return updateClanpostWithMinMaxByKey(id, clanPost, _min, _max, TABLENAME);
    }

    public int updateClanpostWithMinMaxByKey(final int id, final int clanPost, final int _min, final int _max, final String TABLENAME2){
        if( clanPost < 0 ) {
            return updateClanpostWithMinByKey(id, clanPost, _min, TABLENAME2);
        } else {
            return updateClanpostWithMaxByKey(id, clanPost, _max, TABLENAME2);
        }
    }

    public int updateClanpostWithMinMaxInKeys(final List<Integer> keys, final int clanPost, final int _min, final int _max){
        return updateClanpostWithMinMaxInKeys(keys, clanPost, _min, _max, TABLENAME);
    }

    public int updateClanpostWithMinMaxInKeys(final List<Integer> keys, final int clanPost, final int _min, final int _max, final String TABLENAME2){
        if( clanPost < 0 ) {
            return updateClanpostWithMinInKeys(keys, clanPost, _min, TABLENAME2);
        } else {
            return updateClanpostWithMaxInKeys(keys, clanPost, _max, TABLENAME2);
        }
    }

    public int updateCiconByKey(final int cicon, final int id){
        return updateCiconByKey(cicon, id, TABLENAME);
    }

    public int updateCiconByKey(final int cicon, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cicon=cicon+:cicon WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("cicon", cicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCiconWithMinByKey(final int id, final int cicon, final int _min){
        return updateCiconWithMinByKey(id, cicon, _min, TABLENAME);
    }

    public int updateCiconWithMinByKey(final int id, final int cicon, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cicon = (select case when cicon+:cicon<=:_min then :_min else cicon+:cicon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("cicon", cicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCiconWithMinInKeys(final List<Integer> keys, final int cicon, final int _min){
        return updateCiconWithMinInKeys(keys, cicon, _min, TABLENAME);
    }

    public int updateCiconWithMinInKeys(final List<Integer> keys, final int cicon, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cicon = (select case when cicon+:cicon<=:_min then :_min else cicon+:cicon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("cicon", cicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCiconWithMaxByKey(final int id, final int cicon, final int _max){
        return updateCiconWithMaxByKey(id, cicon, _max, TABLENAME);
    }

    public int updateCiconWithMaxByKey(final int id, final int cicon, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cicon = (select case when cicon+:cicon>=:_max then :_max else cicon+:cicon end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("cicon", cicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCiconWithMaxInKeys(final List<Integer> keys, final int cicon, final int _max){
        return updateCiconWithMaxInKeys(keys, cicon, _max, TABLENAME);
    }

    public int updateCiconWithMaxInKeys(final List<Integer> keys, final int cicon, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET cicon = (select case when cicon+:cicon>=:_max then :_max else cicon+:cicon end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("cicon", cicon);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCiconWithMinMaxByKey(final int id, final int cicon, final int _min, final int _max){
        return updateCiconWithMinMaxByKey(id, cicon, _min, _max, TABLENAME);
    }

    public int updateCiconWithMinMaxByKey(final int id, final int cicon, final int _min, final int _max, final String TABLENAME2){
        if( cicon < 0 ) {
            return updateCiconWithMinByKey(id, cicon, _min, TABLENAME2);
        } else {
            return updateCiconWithMaxByKey(id, cicon, _max, TABLENAME2);
        }
    }

    public int updateCiconWithMinMaxInKeys(final List<Integer> keys, final int cicon, final int _min, final int _max){
        return updateCiconWithMinMaxInKeys(keys, cicon, _min, _max, TABLENAME);
    }

    public int updateCiconWithMinMaxInKeys(final List<Integer> keys, final int cicon, final int _min, final int _max, final String TABLENAME2){
        if( cicon < 0 ) {
            return updateCiconWithMinInKeys(keys, cicon, _min, TABLENAME2);
        } else {
            return updateCiconWithMaxInKeys(keys, cicon, _max, TABLENAME2);
        }
    }

    public int updateMaxbuididByKey(final int maxBuidId, final int id){
        return updateMaxbuididByKey(maxBuidId, id, TABLENAME);
    }

    public int updateMaxbuididByKey(final int maxBuidId, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxBuidId=maxBuidId+:maxBuidId WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("maxBuidId", maxBuidId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxbuididWithMinByKey(final int id, final int maxBuidId, final int _min){
        return updateMaxbuididWithMinByKey(id, maxBuidId, _min, TABLENAME);
    }

    public int updateMaxbuididWithMinByKey(final int id, final int maxBuidId, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxBuidId = (select case when maxBuidId+:maxBuidId<=:_min then :_min else maxBuidId+:maxBuidId end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("maxBuidId", maxBuidId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxbuididWithMinInKeys(final List<Integer> keys, final int maxBuidId, final int _min){
        return updateMaxbuididWithMinInKeys(keys, maxBuidId, _min, TABLENAME);
    }

    public int updateMaxbuididWithMinInKeys(final List<Integer> keys, final int maxBuidId, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxBuidId = (select case when maxBuidId+:maxBuidId<=:_min then :_min else maxBuidId+:maxBuidId end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("maxBuidId", maxBuidId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxbuididWithMaxByKey(final int id, final int maxBuidId, final int _max){
        return updateMaxbuididWithMaxByKey(id, maxBuidId, _max, TABLENAME);
    }

    public int updateMaxbuididWithMaxByKey(final int id, final int maxBuidId, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxBuidId = (select case when maxBuidId+:maxBuidId>=:_max then :_max else maxBuidId+:maxBuidId end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("maxBuidId", maxBuidId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxbuididWithMaxInKeys(final List<Integer> keys, final int maxBuidId, final int _max){
        return updateMaxbuididWithMaxInKeys(keys, maxBuidId, _max, TABLENAME);
    }

    public int updateMaxbuididWithMaxInKeys(final List<Integer> keys, final int maxBuidId, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxBuidId = (select case when maxBuidId+:maxBuidId>=:_max then :_max else maxBuidId+:maxBuidId end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("maxBuidId", maxBuidId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxbuididWithMinMaxByKey(final int id, final int maxBuidId, final int _min, final int _max){
        return updateMaxbuididWithMinMaxByKey(id, maxBuidId, _min, _max, TABLENAME);
    }

    public int updateMaxbuididWithMinMaxByKey(final int id, final int maxBuidId, final int _min, final int _max, final String TABLENAME2){
        if( maxBuidId < 0 ) {
            return updateMaxbuididWithMinByKey(id, maxBuidId, _min, TABLENAME2);
        } else {
            return updateMaxbuididWithMaxByKey(id, maxBuidId, _max, TABLENAME2);
        }
    }

    public int updateMaxbuididWithMinMaxInKeys(final List<Integer> keys, final int maxBuidId, final int _min, final int _max){
        return updateMaxbuididWithMinMaxInKeys(keys, maxBuidId, _min, _max, TABLENAME);
    }

    public int updateMaxbuididWithMinMaxInKeys(final List<Integer> keys, final int maxBuidId, final int _min, final int _max, final String TABLENAME2){
        if( maxBuidId < 0 ) {
            return updateMaxbuididWithMinInKeys(keys, maxBuidId, _min, TABLENAME2);
        } else {
            return updateMaxbuididWithMaxInKeys(keys, maxBuidId, _max, TABLENAME2);
        }
    }

    public int updateMaxobstidByKey(final int maxObstId, final int id){
        return updateMaxobstidByKey(maxObstId, id, TABLENAME);
    }

    public int updateMaxobstidByKey(final int maxObstId, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxObstId=maxObstId+:maxObstId WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("maxObstId", maxObstId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxobstidWithMinByKey(final int id, final int maxObstId, final int _min){
        return updateMaxobstidWithMinByKey(id, maxObstId, _min, TABLENAME);
    }

    public int updateMaxobstidWithMinByKey(final int id, final int maxObstId, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxObstId = (select case when maxObstId+:maxObstId<=:_min then :_min else maxObstId+:maxObstId end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("maxObstId", maxObstId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxobstidWithMinInKeys(final List<Integer> keys, final int maxObstId, final int _min){
        return updateMaxobstidWithMinInKeys(keys, maxObstId, _min, TABLENAME);
    }

    public int updateMaxobstidWithMinInKeys(final List<Integer> keys, final int maxObstId, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxObstId = (select case when maxObstId+:maxObstId<=:_min then :_min else maxObstId+:maxObstId end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("maxObstId", maxObstId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxobstidWithMaxByKey(final int id, final int maxObstId, final int _max){
        return updateMaxobstidWithMaxByKey(id, maxObstId, _max, TABLENAME);
    }

    public int updateMaxobstidWithMaxByKey(final int id, final int maxObstId, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxObstId = (select case when maxObstId+:maxObstId>=:_max then :_max else maxObstId+:maxObstId end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("maxObstId", maxObstId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxobstidWithMaxInKeys(final List<Integer> keys, final int maxObstId, final int _max){
        return updateMaxobstidWithMaxInKeys(keys, maxObstId, _max, TABLENAME);
    }

    public int updateMaxobstidWithMaxInKeys(final List<Integer> keys, final int maxObstId, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxObstId = (select case when maxObstId+:maxObstId>=:_max then :_max else maxObstId+:maxObstId end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("maxObstId", maxObstId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxobstidWithMinMaxByKey(final int id, final int maxObstId, final int _min, final int _max){
        return updateMaxobstidWithMinMaxByKey(id, maxObstId, _min, _max, TABLENAME);
    }

    public int updateMaxobstidWithMinMaxByKey(final int id, final int maxObstId, final int _min, final int _max, final String TABLENAME2){
        if( maxObstId < 0 ) {
            return updateMaxobstidWithMinByKey(id, maxObstId, _min, TABLENAME2);
        } else {
            return updateMaxobstidWithMaxByKey(id, maxObstId, _max, TABLENAME2);
        }
    }

    public int updateMaxobstidWithMinMaxInKeys(final List<Integer> keys, final int maxObstId, final int _min, final int _max){
        return updateMaxobstidWithMinMaxInKeys(keys, maxObstId, _min, _max, TABLENAME);
    }

    public int updateMaxobstidWithMinMaxInKeys(final List<Integer> keys, final int maxObstId, final int _min, final int _max, final String TABLENAME2){
        if( maxObstId < 0 ) {
            return updateMaxobstidWithMinInKeys(keys, maxObstId, _min, TABLENAME2);
        } else {
            return updateMaxobstidWithMaxInKeys(keys, maxObstId, _max, TABLENAME2);
        }
    }

    public int updateStored_oilByKey(final int stored_oil, final int id){
        return updateStored_oilByKey(stored_oil, id, TABLENAME);
    }

    public int updateStored_oilByKey(final int stored_oil, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET stored_oil=stored_oil+:stored_oil WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("stored_oil", stored_oil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStored_oilWithMinByKey(final int id, final int stored_oil, final int _min){
        return updateStored_oilWithMinByKey(id, stored_oil, _min, TABLENAME);
    }

    public int updateStored_oilWithMinByKey(final int id, final int stored_oil, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET stored_oil = (select case when stored_oil+:stored_oil<=:_min then :_min else stored_oil+:stored_oil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("stored_oil", stored_oil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStored_oilWithMinInKeys(final List<Integer> keys, final int stored_oil, final int _min){
        return updateStored_oilWithMinInKeys(keys, stored_oil, _min, TABLENAME);
    }

    public int updateStored_oilWithMinInKeys(final List<Integer> keys, final int stored_oil, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET stored_oil = (select case when stored_oil+:stored_oil<=:_min then :_min else stored_oil+:stored_oil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("stored_oil", stored_oil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStored_oilWithMaxByKey(final int id, final int stored_oil, final int _max){
        return updateStored_oilWithMaxByKey(id, stored_oil, _max, TABLENAME);
    }

    public int updateStored_oilWithMaxByKey(final int id, final int stored_oil, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET stored_oil = (select case when stored_oil+:stored_oil>=:_max then :_max else stored_oil+:stored_oil end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("stored_oil", stored_oil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStored_oilWithMaxInKeys(final List<Integer> keys, final int stored_oil, final int _max){
        return updateStored_oilWithMaxInKeys(keys, stored_oil, _max, TABLENAME);
    }

    public int updateStored_oilWithMaxInKeys(final List<Integer> keys, final int stored_oil, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET stored_oil = (select case when stored_oil+:stored_oil>=:_max then :_max else stored_oil+:stored_oil end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("stored_oil", stored_oil);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStored_oilWithMinMaxByKey(final int id, final int stored_oil, final int _min, final int _max){
        return updateStored_oilWithMinMaxByKey(id, stored_oil, _min, _max, TABLENAME);
    }

    public int updateStored_oilWithMinMaxByKey(final int id, final int stored_oil, final int _min, final int _max, final String TABLENAME2){
        if( stored_oil < 0 ) {
            return updateStored_oilWithMinByKey(id, stored_oil, _min, TABLENAME2);
        } else {
            return updateStored_oilWithMaxByKey(id, stored_oil, _max, TABLENAME2);
        }
    }

    public int updateStored_oilWithMinMaxInKeys(final List<Integer> keys, final int stored_oil, final int _min, final int _max){
        return updateStored_oilWithMinMaxInKeys(keys, stored_oil, _min, _max, TABLENAME);
    }

    public int updateStored_oilWithMinMaxInKeys(final List<Integer> keys, final int stored_oil, final int _min, final int _max, final String TABLENAME2){
        if( stored_oil < 0 ) {
            return updateStored_oilWithMinInKeys(keys, stored_oil, _min, TABLENAME2);
        } else {
            return updateStored_oilWithMaxInKeys(keys, stored_oil, _max, TABLENAME2);
        }
    }

    public int updateStored_goldByKey(final int stored_gold, final int id){
        return updateStored_goldByKey(stored_gold, id, TABLENAME);
    }

    public int updateStored_goldByKey(final int stored_gold, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET stored_gold=stored_gold+:stored_gold WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("stored_gold", stored_gold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStored_goldWithMinByKey(final int id, final int stored_gold, final int _min){
        return updateStored_goldWithMinByKey(id, stored_gold, _min, TABLENAME);
    }

    public int updateStored_goldWithMinByKey(final int id, final int stored_gold, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET stored_gold = (select case when stored_gold+:stored_gold<=:_min then :_min else stored_gold+:stored_gold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("stored_gold", stored_gold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStored_goldWithMinInKeys(final List<Integer> keys, final int stored_gold, final int _min){
        return updateStored_goldWithMinInKeys(keys, stored_gold, _min, TABLENAME);
    }

    public int updateStored_goldWithMinInKeys(final List<Integer> keys, final int stored_gold, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET stored_gold = (select case when stored_gold+:stored_gold<=:_min then :_min else stored_gold+:stored_gold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("stored_gold", stored_gold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStored_goldWithMaxByKey(final int id, final int stored_gold, final int _max){
        return updateStored_goldWithMaxByKey(id, stored_gold, _max, TABLENAME);
    }

    public int updateStored_goldWithMaxByKey(final int id, final int stored_gold, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET stored_gold = (select case when stored_gold+:stored_gold>=:_max then :_max else stored_gold+:stored_gold end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("stored_gold", stored_gold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStored_goldWithMaxInKeys(final List<Integer> keys, final int stored_gold, final int _max){
        return updateStored_goldWithMaxInKeys(keys, stored_gold, _max, TABLENAME);
    }

    public int updateStored_goldWithMaxInKeys(final List<Integer> keys, final int stored_gold, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET stored_gold = (select case when stored_gold+:stored_gold>=:_max then :_max else stored_gold+:stored_gold end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("stored_gold", stored_gold);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStored_goldWithMinMaxByKey(final int id, final int stored_gold, final int _min, final int _max){
        return updateStored_goldWithMinMaxByKey(id, stored_gold, _min, _max, TABLENAME);
    }

    public int updateStored_goldWithMinMaxByKey(final int id, final int stored_gold, final int _min, final int _max, final String TABLENAME2){
        if( stored_gold < 0 ) {
            return updateStored_goldWithMinByKey(id, stored_gold, _min, TABLENAME2);
        } else {
            return updateStored_goldWithMaxByKey(id, stored_gold, _max, TABLENAME2);
        }
    }

    public int updateStored_goldWithMinMaxInKeys(final List<Integer> keys, final int stored_gold, final int _min, final int _max){
        return updateStored_goldWithMinMaxInKeys(keys, stored_gold, _min, _max, TABLENAME);
    }

    public int updateStored_goldWithMinMaxInKeys(final List<Integer> keys, final int stored_gold, final int _min, final int _max, final String TABLENAME2){
        if( stored_gold < 0 ) {
            return updateStored_goldWithMinInKeys(keys, stored_gold, _min, TABLENAME2);
        } else {
            return updateStored_goldWithMaxInKeys(keys, stored_gold, _max, TABLENAME2);
        }
    }

    public int updateProtecttimeByKey(final long protectTime, final int id){
        return updateProtecttimeByKey(protectTime, id, TABLENAME);
    }

    public int updateProtecttimeByKey(final long protectTime, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET protectTime=protectTime+:protectTime WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("protectTime", protectTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProtecttimeWithMinByKey(final int id, final long protectTime, final long _min){
        return updateProtecttimeWithMinByKey(id, protectTime, _min, TABLENAME);
    }

    public int updateProtecttimeWithMinByKey(final int id, final long protectTime, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET protectTime = (select case when protectTime+:protectTime<=:_min then :_min else protectTime+:protectTime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("protectTime", protectTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProtecttimeWithMinInKeys(final List<Integer> keys, final long protectTime, final long _min){
        return updateProtecttimeWithMinInKeys(keys, protectTime, _min, TABLENAME);
    }

    public int updateProtecttimeWithMinInKeys(final List<Integer> keys, final long protectTime, final long _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET protectTime = (select case when protectTime+:protectTime<=:_min then :_min else protectTime+:protectTime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("protectTime", protectTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProtecttimeWithMaxByKey(final int id, final long protectTime, final long _max){
        return updateProtecttimeWithMaxByKey(id, protectTime, _max, TABLENAME);
    }

    public int updateProtecttimeWithMaxByKey(final int id, final long protectTime, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET protectTime = (select case when protectTime+:protectTime>=:_max then :_max else protectTime+:protectTime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("protectTime", protectTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProtecttimeWithMaxInKeys(final List<Integer> keys, final long protectTime, final long _max){
        return updateProtecttimeWithMaxInKeys(keys, protectTime, _max, TABLENAME);
    }

    public int updateProtecttimeWithMaxInKeys(final List<Integer> keys, final long protectTime, final long _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET protectTime = (select case when protectTime+:protectTime>=:_max then :_max else protectTime+:protectTime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("protectTime", protectTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateProtecttimeWithMinMaxByKey(final int id, final long protectTime, final long _min, final long _max){
        return updateProtecttimeWithMinMaxByKey(id, protectTime, _min, _max, TABLENAME);
    }

    public int updateProtecttimeWithMinMaxByKey(final int id, final long protectTime, final long _min, final long _max, final String TABLENAME2){
        if( protectTime < 0 ) {
            return updateProtecttimeWithMinByKey(id, protectTime, _min, TABLENAME2);
        } else {
            return updateProtecttimeWithMaxByKey(id, protectTime, _max, TABLENAME2);
        }
    }

    public int updateProtecttimeWithMinMaxInKeys(final List<Integer> keys, final long protectTime, final long _min, final long _max){
        return updateProtecttimeWithMinMaxInKeys(keys, protectTime, _min, _max, TABLENAME);
    }

    public int updateProtecttimeWithMinMaxInKeys(final List<Integer> keys, final long protectTime, final long _min, final long _max, final String TABLENAME2){
        if( protectTime < 0 ) {
            return updateProtecttimeWithMinInKeys(keys, protectTime, _min, TABLENAME2);
        } else {
            return updateProtecttimeWithMaxInKeys(keys, protectTime, _max, TABLENAME2);
        }
    }

    public int updateMaxattmcidByKey(final int maxAttMCId, final int id){
        return updateMaxattmcidByKey(maxAttMCId, id, TABLENAME);
    }

    public int updateMaxattmcidByKey(final int maxAttMCId, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxAttMCId=maxAttMCId+:maxAttMCId WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("maxAttMCId", maxAttMCId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxattmcidWithMinByKey(final int id, final int maxAttMCId, final int _min){
        return updateMaxattmcidWithMinByKey(id, maxAttMCId, _min, TABLENAME);
    }

    public int updateMaxattmcidWithMinByKey(final int id, final int maxAttMCId, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxAttMCId = (select case when maxAttMCId+:maxAttMCId<=:_min then :_min else maxAttMCId+:maxAttMCId end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("maxAttMCId", maxAttMCId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxattmcidWithMinInKeys(final List<Integer> keys, final int maxAttMCId, final int _min){
        return updateMaxattmcidWithMinInKeys(keys, maxAttMCId, _min, TABLENAME);
    }

    public int updateMaxattmcidWithMinInKeys(final List<Integer> keys, final int maxAttMCId, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxAttMCId = (select case when maxAttMCId+:maxAttMCId<=:_min then :_min else maxAttMCId+:maxAttMCId end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("maxAttMCId", maxAttMCId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxattmcidWithMaxByKey(final int id, final int maxAttMCId, final int _max){
        return updateMaxattmcidWithMaxByKey(id, maxAttMCId, _max, TABLENAME);
    }

    public int updateMaxattmcidWithMaxByKey(final int id, final int maxAttMCId, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxAttMCId = (select case when maxAttMCId+:maxAttMCId>=:_max then :_max else maxAttMCId+:maxAttMCId end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("maxAttMCId", maxAttMCId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxattmcidWithMaxInKeys(final List<Integer> keys, final int maxAttMCId, final int _max){
        return updateMaxattmcidWithMaxInKeys(keys, maxAttMCId, _max, TABLENAME);
    }

    public int updateMaxattmcidWithMaxInKeys(final List<Integer> keys, final int maxAttMCId, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxAttMCId = (select case when maxAttMCId+:maxAttMCId>=:_max then :_max else maxAttMCId+:maxAttMCId end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("maxAttMCId", maxAttMCId);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxattmcidWithMinMaxByKey(final int id, final int maxAttMCId, final int _min, final int _max){
        return updateMaxattmcidWithMinMaxByKey(id, maxAttMCId, _min, _max, TABLENAME);
    }

    public int updateMaxattmcidWithMinMaxByKey(final int id, final int maxAttMCId, final int _min, final int _max, final String TABLENAME2){
        if( maxAttMCId < 0 ) {
            return updateMaxattmcidWithMinByKey(id, maxAttMCId, _min, TABLENAME2);
        } else {
            return updateMaxattmcidWithMaxByKey(id, maxAttMCId, _max, TABLENAME2);
        }
    }

    public int updateMaxattmcidWithMinMaxInKeys(final List<Integer> keys, final int maxAttMCId, final int _min, final int _max){
        return updateMaxattmcidWithMinMaxInKeys(keys, maxAttMCId, _min, _max, TABLENAME);
    }

    public int updateMaxattmcidWithMinMaxInKeys(final List<Integer> keys, final int maxAttMCId, final int _min, final int _max, final String TABLENAME2){
        if( maxAttMCId < 0 ) {
            return updateMaxattmcidWithMinInKeys(keys, maxAttMCId, _min, TABLENAME2);
        } else {
            return updateMaxattmcidWithMaxInKeys(keys, maxAttMCId, _max, TABLENAME2);
        }
    }

    public int updateMaxbonumByKey(final int maxBONum, final int id){
        return updateMaxbonumByKey(maxBONum, id, TABLENAME);
    }

    public int updateMaxbonumByKey(final int maxBONum, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxBONum=maxBONum+:maxBONum WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("maxBONum", maxBONum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxbonumWithMinByKey(final int id, final int maxBONum, final int _min){
        return updateMaxbonumWithMinByKey(id, maxBONum, _min, TABLENAME);
    }

    public int updateMaxbonumWithMinByKey(final int id, final int maxBONum, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxBONum = (select case when maxBONum+:maxBONum<=:_min then :_min else maxBONum+:maxBONum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("maxBONum", maxBONum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxbonumWithMinInKeys(final List<Integer> keys, final int maxBONum, final int _min){
        return updateMaxbonumWithMinInKeys(keys, maxBONum, _min, TABLENAME);
    }

    public int updateMaxbonumWithMinInKeys(final List<Integer> keys, final int maxBONum, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxBONum = (select case when maxBONum+:maxBONum<=:_min then :_min else maxBONum+:maxBONum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("maxBONum", maxBONum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxbonumWithMaxByKey(final int id, final int maxBONum, final int _max){
        return updateMaxbonumWithMaxByKey(id, maxBONum, _max, TABLENAME);
    }

    public int updateMaxbonumWithMaxByKey(final int id, final int maxBONum, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxBONum = (select case when maxBONum+:maxBONum>=:_max then :_max else maxBONum+:maxBONum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("maxBONum", maxBONum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxbonumWithMaxInKeys(final List<Integer> keys, final int maxBONum, final int _max){
        return updateMaxbonumWithMaxInKeys(keys, maxBONum, _max, TABLENAME);
    }

    public int updateMaxbonumWithMaxInKeys(final List<Integer> keys, final int maxBONum, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET maxBONum = (select case when maxBONum+:maxBONum>=:_max then :_max else maxBONum+:maxBONum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("maxBONum", maxBONum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMaxbonumWithMinMaxByKey(final int id, final int maxBONum, final int _min, final int _max){
        return updateMaxbonumWithMinMaxByKey(id, maxBONum, _min, _max, TABLENAME);
    }

    public int updateMaxbonumWithMinMaxByKey(final int id, final int maxBONum, final int _min, final int _max, final String TABLENAME2){
        if( maxBONum < 0 ) {
            return updateMaxbonumWithMinByKey(id, maxBONum, _min, TABLENAME2);
        } else {
            return updateMaxbonumWithMaxByKey(id, maxBONum, _max, TABLENAME2);
        }
    }

    public int updateMaxbonumWithMinMaxInKeys(final List<Integer> keys, final int maxBONum, final int _min, final int _max){
        return updateMaxbonumWithMinMaxInKeys(keys, maxBONum, _min, _max, TABLENAME);
    }

    public int updateMaxbonumWithMinMaxInKeys(final List<Integer> keys, final int maxBONum, final int _min, final int _max, final String TABLENAME2){
        if( maxBONum < 0 ) {
            return updateMaxbonumWithMinInKeys(keys, maxBONum, _min, TABLENAME2);
        } else {
            return updateMaxbonumWithMaxInKeys(keys, maxBONum, _max, TABLENAME2);
        }
    }

    public int updateCurbonumByKey(final int curBONum, final int id){
        return updateCurbonumByKey(curBONum, id, TABLENAME);
    }

    public int updateCurbonumByKey(final int curBONum, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curBONum=curBONum+:curBONum WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("curBONum", curBONum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurbonumWithMinByKey(final int id, final int curBONum, final int _min){
        return updateCurbonumWithMinByKey(id, curBONum, _min, TABLENAME);
    }

    public int updateCurbonumWithMinByKey(final int id, final int curBONum, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curBONum = (select case when curBONum+:curBONum<=:_min then :_min else curBONum+:curBONum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("curBONum", curBONum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurbonumWithMinInKeys(final List<Integer> keys, final int curBONum, final int _min){
        return updateCurbonumWithMinInKeys(keys, curBONum, _min, TABLENAME);
    }

    public int updateCurbonumWithMinInKeys(final List<Integer> keys, final int curBONum, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curBONum = (select case when curBONum+:curBONum<=:_min then :_min else curBONum+:curBONum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("curBONum", curBONum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurbonumWithMaxByKey(final int id, final int curBONum, final int _max){
        return updateCurbonumWithMaxByKey(id, curBONum, _max, TABLENAME);
    }

    public int updateCurbonumWithMaxByKey(final int id, final int curBONum, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curBONum = (select case when curBONum+:curBONum>=:_max then :_max else curBONum+:curBONum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("curBONum", curBONum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurbonumWithMaxInKeys(final List<Integer> keys, final int curBONum, final int _max){
        return updateCurbonumWithMaxInKeys(keys, curBONum, _max, TABLENAME);
    }

    public int updateCurbonumWithMaxInKeys(final List<Integer> keys, final int curBONum, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curBONum = (select case when curBONum+:curBONum>=:_max then :_max else curBONum+:curBONum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("curBONum", curBONum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurbonumWithMinMaxByKey(final int id, final int curBONum, final int _min, final int _max){
        return updateCurbonumWithMinMaxByKey(id, curBONum, _min, _max, TABLENAME);
    }

    public int updateCurbonumWithMinMaxByKey(final int id, final int curBONum, final int _min, final int _max, final String TABLENAME2){
        if( curBONum < 0 ) {
            return updateCurbonumWithMinByKey(id, curBONum, _min, TABLENAME2);
        } else {
            return updateCurbonumWithMaxByKey(id, curBONum, _max, TABLENAME2);
        }
    }

    public int updateCurbonumWithMinMaxInKeys(final List<Integer> keys, final int curBONum, final int _min, final int _max){
        return updateCurbonumWithMinMaxInKeys(keys, curBONum, _min, _max, TABLENAME);
    }

    public int updateCurbonumWithMinMaxInKeys(final List<Integer> keys, final int curBONum, final int _min, final int _max, final String TABLENAME2){
        if( curBONum < 0 ) {
            return updateCurbonumWithMinInKeys(keys, curBONum, _min, TABLENAME2);
        } else {
            return updateCurbonumWithMaxInKeys(keys, curBONum, _max, TABLENAME2);
        }
    }

    public int updateLastaddobstByKey(final long lastAddObst, final int id){
        return updateLastaddobstByKey(lastAddObst, id, TABLENAME);
    }

    public int updateLastaddobstByKey(final long lastAddObst, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastAddObst=lastAddObst+:lastAddObst WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lastAddObst", lastAddObst);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastaddobstWithMinByKey(final int id, final long lastAddObst, final long _min){
        return updateLastaddobstWithMinByKey(id, lastAddObst, _min, TABLENAME);
    }

    public int updateLastaddobstWithMinByKey(final int id, final long lastAddObst, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastAddObst = (select case when lastAddObst+:lastAddObst<=:_min then :_min else lastAddObst+:lastAddObst end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lastAddObst", lastAddObst);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastaddobstWithMinInKeys(final List<Integer> keys, final long lastAddObst, final long _min){
        return updateLastaddobstWithMinInKeys(keys, lastAddObst, _min, TABLENAME);
    }

    public int updateLastaddobstWithMinInKeys(final List<Integer> keys, final long lastAddObst, final long _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastAddObst = (select case when lastAddObst+:lastAddObst<=:_min then :_min else lastAddObst+:lastAddObst end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lastAddObst", lastAddObst);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastaddobstWithMaxByKey(final int id, final long lastAddObst, final long _max){
        return updateLastaddobstWithMaxByKey(id, lastAddObst, _max, TABLENAME);
    }

    public int updateLastaddobstWithMaxByKey(final int id, final long lastAddObst, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastAddObst = (select case when lastAddObst+:lastAddObst>=:_max then :_max else lastAddObst+:lastAddObst end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lastAddObst", lastAddObst);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastaddobstWithMaxInKeys(final List<Integer> keys, final long lastAddObst, final long _max){
        return updateLastaddobstWithMaxInKeys(keys, lastAddObst, _max, TABLENAME);
    }

    public int updateLastaddobstWithMaxInKeys(final List<Integer> keys, final long lastAddObst, final long _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastAddObst = (select case when lastAddObst+:lastAddObst>=:_max then :_max else lastAddObst+:lastAddObst end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lastAddObst", lastAddObst);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastaddobstWithMinMaxByKey(final int id, final long lastAddObst, final long _min, final long _max){
        return updateLastaddobstWithMinMaxByKey(id, lastAddObst, _min, _max, TABLENAME);
    }

    public int updateLastaddobstWithMinMaxByKey(final int id, final long lastAddObst, final long _min, final long _max, final String TABLENAME2){
        if( lastAddObst < 0 ) {
            return updateLastaddobstWithMinByKey(id, lastAddObst, _min, TABLENAME2);
        } else {
            return updateLastaddobstWithMaxByKey(id, lastAddObst, _max, TABLENAME2);
        }
    }

    public int updateLastaddobstWithMinMaxInKeys(final List<Integer> keys, final long lastAddObst, final long _min, final long _max){
        return updateLastaddobstWithMinMaxInKeys(keys, lastAddObst, _min, _max, TABLENAME);
    }

    public int updateLastaddobstWithMinMaxInKeys(final List<Integer> keys, final long lastAddObst, final long _min, final long _max, final String TABLENAME2){
        if( lastAddObst < 0 ) {
            return updateLastaddobstWithMinInKeys(keys, lastAddObst, _min, TABLENAME2);
        } else {
            return updateLastaddobstWithMaxInKeys(keys, lastAddObst, _max, TABLENAME2);
        }
    }

    public int updateCurtownlvlByKey(final int curtownlvl, final int id){
        return updateCurtownlvlByKey(curtownlvl, id, TABLENAME);
    }

    public int updateCurtownlvlByKey(final int curtownlvl, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curtownlvl=curtownlvl+:curtownlvl WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("curtownlvl", curtownlvl);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurtownlvlWithMinByKey(final int id, final int curtownlvl, final int _min){
        return updateCurtownlvlWithMinByKey(id, curtownlvl, _min, TABLENAME);
    }

    public int updateCurtownlvlWithMinByKey(final int id, final int curtownlvl, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curtownlvl = (select case when curtownlvl+:curtownlvl<=:_min then :_min else curtownlvl+:curtownlvl end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("curtownlvl", curtownlvl);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurtownlvlWithMinInKeys(final List<Integer> keys, final int curtownlvl, final int _min){
        return updateCurtownlvlWithMinInKeys(keys, curtownlvl, _min, TABLENAME);
    }

    public int updateCurtownlvlWithMinInKeys(final List<Integer> keys, final int curtownlvl, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curtownlvl = (select case when curtownlvl+:curtownlvl<=:_min then :_min else curtownlvl+:curtownlvl end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("curtownlvl", curtownlvl);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurtownlvlWithMaxByKey(final int id, final int curtownlvl, final int _max){
        return updateCurtownlvlWithMaxByKey(id, curtownlvl, _max, TABLENAME);
    }

    public int updateCurtownlvlWithMaxByKey(final int id, final int curtownlvl, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curtownlvl = (select case when curtownlvl+:curtownlvl>=:_max then :_max else curtownlvl+:curtownlvl end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("curtownlvl", curtownlvl);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurtownlvlWithMaxInKeys(final List<Integer> keys, final int curtownlvl, final int _max){
        return updateCurtownlvlWithMaxInKeys(keys, curtownlvl, _max, TABLENAME);
    }

    public int updateCurtownlvlWithMaxInKeys(final List<Integer> keys, final int curtownlvl, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET curtownlvl = (select case when curtownlvl+:curtownlvl>=:_max then :_max else curtownlvl+:curtownlvl end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("curtownlvl", curtownlvl);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCurtownlvlWithMinMaxByKey(final int id, final int curtownlvl, final int _min, final int _max){
        return updateCurtownlvlWithMinMaxByKey(id, curtownlvl, _min, _max, TABLENAME);
    }

    public int updateCurtownlvlWithMinMaxByKey(final int id, final int curtownlvl, final int _min, final int _max, final String TABLENAME2){
        if( curtownlvl < 0 ) {
            return updateCurtownlvlWithMinByKey(id, curtownlvl, _min, TABLENAME2);
        } else {
            return updateCurtownlvlWithMaxByKey(id, curtownlvl, _max, TABLENAME2);
        }
    }

    public int updateCurtownlvlWithMinMaxInKeys(final List<Integer> keys, final int curtownlvl, final int _min, final int _max){
        return updateCurtownlvlWithMinMaxInKeys(keys, curtownlvl, _min, _max, TABLENAME);
    }

    public int updateCurtownlvlWithMinMaxInKeys(final List<Integer> keys, final int curtownlvl, final int _min, final int _max, final String TABLENAME2){
        if( curtownlvl < 0 ) {
            return updateCurtownlvlWithMinInKeys(keys, curtownlvl, _min, TABLENAME2);
        } else {
            return updateCurtownlvlWithMaxInKeys(keys, curtownlvl, _max, TABLENAME2);
        }
    }

    public int updateLogindayByKey(final int loginDay, final int id){
        return updateLogindayByKey(loginDay, id, TABLENAME);
    }

    public int updateLogindayByKey(final int loginDay, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET loginDay=loginDay+:loginDay WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("loginDay", loginDay);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLogindayWithMinByKey(final int id, final int loginDay, final int _min){
        return updateLogindayWithMinByKey(id, loginDay, _min, TABLENAME);
    }

    public int updateLogindayWithMinByKey(final int id, final int loginDay, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET loginDay = (select case when loginDay+:loginDay<=:_min then :_min else loginDay+:loginDay end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("loginDay", loginDay);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLogindayWithMinInKeys(final List<Integer> keys, final int loginDay, final int _min){
        return updateLogindayWithMinInKeys(keys, loginDay, _min, TABLENAME);
    }

    public int updateLogindayWithMinInKeys(final List<Integer> keys, final int loginDay, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET loginDay = (select case when loginDay+:loginDay<=:_min then :_min else loginDay+:loginDay end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("loginDay", loginDay);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLogindayWithMaxByKey(final int id, final int loginDay, final int _max){
        return updateLogindayWithMaxByKey(id, loginDay, _max, TABLENAME);
    }

    public int updateLogindayWithMaxByKey(final int id, final int loginDay, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET loginDay = (select case when loginDay+:loginDay>=:_max then :_max else loginDay+:loginDay end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("loginDay", loginDay);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLogindayWithMaxInKeys(final List<Integer> keys, final int loginDay, final int _max){
        return updateLogindayWithMaxInKeys(keys, loginDay, _max, TABLENAME);
    }

    public int updateLogindayWithMaxInKeys(final List<Integer> keys, final int loginDay, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET loginDay = (select case when loginDay+:loginDay>=:_max then :_max else loginDay+:loginDay end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("loginDay", loginDay);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLogindayWithMinMaxByKey(final int id, final int loginDay, final int _min, final int _max){
        return updateLogindayWithMinMaxByKey(id, loginDay, _min, _max, TABLENAME);
    }

    public int updateLogindayWithMinMaxByKey(final int id, final int loginDay, final int _min, final int _max, final String TABLENAME2){
        if( loginDay < 0 ) {
            return updateLogindayWithMinByKey(id, loginDay, _min, TABLENAME2);
        } else {
            return updateLogindayWithMaxByKey(id, loginDay, _max, TABLENAME2);
        }
    }

    public int updateLogindayWithMinMaxInKeys(final List<Integer> keys, final int loginDay, final int _min, final int _max){
        return updateLogindayWithMinMaxInKeys(keys, loginDay, _min, _max, TABLENAME);
    }

    public int updateLogindayWithMinMaxInKeys(final List<Integer> keys, final int loginDay, final int _min, final int _max, final String TABLENAME2){
        if( loginDay < 0 ) {
            return updateLogindayWithMinInKeys(keys, loginDay, _min, TABLENAME2);
        } else {
            return updateLogindayWithMaxInKeys(keys, loginDay, _max, TABLENAME2);
        }
    }

    public int updateLastlogintimeByKey(final long lastLoginTime, final int id){
        return updateLastlogintimeByKey(lastLoginTime, id, TABLENAME);
    }

    public int updateLastlogintimeByKey(final long lastLoginTime, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastLoginTime=lastLoginTime+:lastLoginTime WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lastLoginTime", lastLoginTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastlogintimeWithMinByKey(final int id, final long lastLoginTime, final long _min){
        return updateLastlogintimeWithMinByKey(id, lastLoginTime, _min, TABLENAME);
    }

    public int updateLastlogintimeWithMinByKey(final int id, final long lastLoginTime, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastLoginTime = (select case when lastLoginTime+:lastLoginTime<=:_min then :_min else lastLoginTime+:lastLoginTime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lastLoginTime", lastLoginTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastlogintimeWithMinInKeys(final List<Integer> keys, final long lastLoginTime, final long _min){
        return updateLastlogintimeWithMinInKeys(keys, lastLoginTime, _min, TABLENAME);
    }

    public int updateLastlogintimeWithMinInKeys(final List<Integer> keys, final long lastLoginTime, final long _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastLoginTime = (select case when lastLoginTime+:lastLoginTime<=:_min then :_min else lastLoginTime+:lastLoginTime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lastLoginTime", lastLoginTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastlogintimeWithMaxByKey(final int id, final long lastLoginTime, final long _max){
        return updateLastlogintimeWithMaxByKey(id, lastLoginTime, _max, TABLENAME);
    }

    public int updateLastlogintimeWithMaxByKey(final int id, final long lastLoginTime, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastLoginTime = (select case when lastLoginTime+:lastLoginTime>=:_max then :_max else lastLoginTime+:lastLoginTime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lastLoginTime", lastLoginTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastlogintimeWithMaxInKeys(final List<Integer> keys, final long lastLoginTime, final long _max){
        return updateLastlogintimeWithMaxInKeys(keys, lastLoginTime, _max, TABLENAME);
    }

    public int updateLastlogintimeWithMaxInKeys(final List<Integer> keys, final long lastLoginTime, final long _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastLoginTime = (select case when lastLoginTime+:lastLoginTime>=:_max then :_max else lastLoginTime+:lastLoginTime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lastLoginTime", lastLoginTime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastlogintimeWithMinMaxByKey(final int id, final long lastLoginTime, final long _min, final long _max){
        return updateLastlogintimeWithMinMaxByKey(id, lastLoginTime, _min, _max, TABLENAME);
    }

    public int updateLastlogintimeWithMinMaxByKey(final int id, final long lastLoginTime, final long _min, final long _max, final String TABLENAME2){
        if( lastLoginTime < 0 ) {
            return updateLastlogintimeWithMinByKey(id, lastLoginTime, _min, TABLENAME2);
        } else {
            return updateLastlogintimeWithMaxByKey(id, lastLoginTime, _max, TABLENAME2);
        }
    }

    public int updateLastlogintimeWithMinMaxInKeys(final List<Integer> keys, final long lastLoginTime, final long _min, final long _max){
        return updateLastlogintimeWithMinMaxInKeys(keys, lastLoginTime, _min, _max, TABLENAME);
    }

    public int updateLastlogintimeWithMinMaxInKeys(final List<Integer> keys, final long lastLoginTime, final long _min, final long _max, final String TABLENAME2){
        if( lastLoginTime < 0 ) {
            return updateLastlogintimeWithMinInKeys(keys, lastLoginTime, _min, TABLENAME2);
        } else {
            return updateLastlogintimeWithMaxInKeys(keys, lastLoginTime, _max, TABLENAME2);
        }
    }

    public int updateFirstpaystatusByKey(final int firstPayStatus, final int id){
        return updateFirstpaystatusByKey(firstPayStatus, id, TABLENAME);
    }

    public int updateFirstpaystatusByKey(final int firstPayStatus, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET firstPayStatus=firstPayStatus+:firstPayStatus WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("firstPayStatus", firstPayStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFirstpaystatusWithMinByKey(final int id, final int firstPayStatus, final int _min){
        return updateFirstpaystatusWithMinByKey(id, firstPayStatus, _min, TABLENAME);
    }

    public int updateFirstpaystatusWithMinByKey(final int id, final int firstPayStatus, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET firstPayStatus = (select case when firstPayStatus+:firstPayStatus<=:_min then :_min else firstPayStatus+:firstPayStatus end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("firstPayStatus", firstPayStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFirstpaystatusWithMinInKeys(final List<Integer> keys, final int firstPayStatus, final int _min){
        return updateFirstpaystatusWithMinInKeys(keys, firstPayStatus, _min, TABLENAME);
    }

    public int updateFirstpaystatusWithMinInKeys(final List<Integer> keys, final int firstPayStatus, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET firstPayStatus = (select case when firstPayStatus+:firstPayStatus<=:_min then :_min else firstPayStatus+:firstPayStatus end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("firstPayStatus", firstPayStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFirstpaystatusWithMaxByKey(final int id, final int firstPayStatus, final int _max){
        return updateFirstpaystatusWithMaxByKey(id, firstPayStatus, _max, TABLENAME);
    }

    public int updateFirstpaystatusWithMaxByKey(final int id, final int firstPayStatus, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET firstPayStatus = (select case when firstPayStatus+:firstPayStatus>=:_max then :_max else firstPayStatus+:firstPayStatus end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("firstPayStatus", firstPayStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFirstpaystatusWithMaxInKeys(final List<Integer> keys, final int firstPayStatus, final int _max){
        return updateFirstpaystatusWithMaxInKeys(keys, firstPayStatus, _max, TABLENAME);
    }

    public int updateFirstpaystatusWithMaxInKeys(final List<Integer> keys, final int firstPayStatus, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET firstPayStatus = (select case when firstPayStatus+:firstPayStatus>=:_max then :_max else firstPayStatus+:firstPayStatus end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("firstPayStatus", firstPayStatus);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFirstpaystatusWithMinMaxByKey(final int id, final int firstPayStatus, final int _min, final int _max){
        return updateFirstpaystatusWithMinMaxByKey(id, firstPayStatus, _min, _max, TABLENAME);
    }

    public int updateFirstpaystatusWithMinMaxByKey(final int id, final int firstPayStatus, final int _min, final int _max, final String TABLENAME2){
        if( firstPayStatus < 0 ) {
            return updateFirstpaystatusWithMinByKey(id, firstPayStatus, _min, TABLENAME2);
        } else {
            return updateFirstpaystatusWithMaxByKey(id, firstPayStatus, _max, TABLENAME2);
        }
    }

    public int updateFirstpaystatusWithMinMaxInKeys(final List<Integer> keys, final int firstPayStatus, final int _min, final int _max){
        return updateFirstpaystatusWithMinMaxInKeys(keys, firstPayStatus, _min, _max, TABLENAME);
    }

    public int updateFirstpaystatusWithMinMaxInKeys(final List<Integer> keys, final int firstPayStatus, final int _min, final int _max, final String TABLENAME2){
        if( firstPayStatus < 0 ) {
            return updateFirstpaystatusWithMinInKeys(keys, firstPayStatus, _min, TABLENAME2);
        } else {
            return updateFirstpaystatusWithMaxInKeys(keys, firstPayStatus, _max, TABLENAME2);
        }
    }

    public int updatePiecehpnumByKey(final int pieceHPNum, final int id){
        return updatePiecehpnumByKey(pieceHPNum, id, TABLENAME);
    }

    public int updatePiecehpnumByKey(final int pieceHPNum, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceHPNum=pieceHPNum+:pieceHPNum WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("pieceHPNum", pieceHPNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePiecehpnumWithMinByKey(final int id, final int pieceHPNum, final int _min){
        return updatePiecehpnumWithMinByKey(id, pieceHPNum, _min, TABLENAME);
    }

    public int updatePiecehpnumWithMinByKey(final int id, final int pieceHPNum, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceHPNum = (select case when pieceHPNum+:pieceHPNum<=:_min then :_min else pieceHPNum+:pieceHPNum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("pieceHPNum", pieceHPNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePiecehpnumWithMinInKeys(final List<Integer> keys, final int pieceHPNum, final int _min){
        return updatePiecehpnumWithMinInKeys(keys, pieceHPNum, _min, TABLENAME);
    }

    public int updatePiecehpnumWithMinInKeys(final List<Integer> keys, final int pieceHPNum, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceHPNum = (select case when pieceHPNum+:pieceHPNum<=:_min then :_min else pieceHPNum+:pieceHPNum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("pieceHPNum", pieceHPNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePiecehpnumWithMaxByKey(final int id, final int pieceHPNum, final int _max){
        return updatePiecehpnumWithMaxByKey(id, pieceHPNum, _max, TABLENAME);
    }

    public int updatePiecehpnumWithMaxByKey(final int id, final int pieceHPNum, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceHPNum = (select case when pieceHPNum+:pieceHPNum>=:_max then :_max else pieceHPNum+:pieceHPNum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("pieceHPNum", pieceHPNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePiecehpnumWithMaxInKeys(final List<Integer> keys, final int pieceHPNum, final int _max){
        return updatePiecehpnumWithMaxInKeys(keys, pieceHPNum, _max, TABLENAME);
    }

    public int updatePiecehpnumWithMaxInKeys(final List<Integer> keys, final int pieceHPNum, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceHPNum = (select case when pieceHPNum+:pieceHPNum>=:_max then :_max else pieceHPNum+:pieceHPNum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("pieceHPNum", pieceHPNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePiecehpnumWithMinMaxByKey(final int id, final int pieceHPNum, final int _min, final int _max){
        return updatePiecehpnumWithMinMaxByKey(id, pieceHPNum, _min, _max, TABLENAME);
    }

    public int updatePiecehpnumWithMinMaxByKey(final int id, final int pieceHPNum, final int _min, final int _max, final String TABLENAME2){
        if( pieceHPNum < 0 ) {
            return updatePiecehpnumWithMinByKey(id, pieceHPNum, _min, TABLENAME2);
        } else {
            return updatePiecehpnumWithMaxByKey(id, pieceHPNum, _max, TABLENAME2);
        }
    }

    public int updatePiecehpnumWithMinMaxInKeys(final List<Integer> keys, final int pieceHPNum, final int _min, final int _max){
        return updatePiecehpnumWithMinMaxInKeys(keys, pieceHPNum, _min, _max, TABLENAME);
    }

    public int updatePiecehpnumWithMinMaxInKeys(final List<Integer> keys, final int pieceHPNum, final int _min, final int _max, final String TABLENAME2){
        if( pieceHPNum < 0 ) {
            return updatePiecehpnumWithMinInKeys(keys, pieceHPNum, _min, TABLENAME2);
        } else {
            return updatePiecehpnumWithMaxInKeys(keys, pieceHPNum, _max, TABLENAME2);
        }
    }

    public int updatePiecedamnumByKey(final int pieceDamNum, final int id){
        return updatePiecedamnumByKey(pieceDamNum, id, TABLENAME);
    }

    public int updatePiecedamnumByKey(final int pieceDamNum, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceDamNum=pieceDamNum+:pieceDamNum WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("pieceDamNum", pieceDamNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePiecedamnumWithMinByKey(final int id, final int pieceDamNum, final int _min){
        return updatePiecedamnumWithMinByKey(id, pieceDamNum, _min, TABLENAME);
    }

    public int updatePiecedamnumWithMinByKey(final int id, final int pieceDamNum, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceDamNum = (select case when pieceDamNum+:pieceDamNum<=:_min then :_min else pieceDamNum+:pieceDamNum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("pieceDamNum", pieceDamNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePiecedamnumWithMinInKeys(final List<Integer> keys, final int pieceDamNum, final int _min){
        return updatePiecedamnumWithMinInKeys(keys, pieceDamNum, _min, TABLENAME);
    }

    public int updatePiecedamnumWithMinInKeys(final List<Integer> keys, final int pieceDamNum, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceDamNum = (select case when pieceDamNum+:pieceDamNum<=:_min then :_min else pieceDamNum+:pieceDamNum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("pieceDamNum", pieceDamNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePiecedamnumWithMaxByKey(final int id, final int pieceDamNum, final int _max){
        return updatePiecedamnumWithMaxByKey(id, pieceDamNum, _max, TABLENAME);
    }

    public int updatePiecedamnumWithMaxByKey(final int id, final int pieceDamNum, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceDamNum = (select case when pieceDamNum+:pieceDamNum>=:_max then :_max else pieceDamNum+:pieceDamNum end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("pieceDamNum", pieceDamNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePiecedamnumWithMaxInKeys(final List<Integer> keys, final int pieceDamNum, final int _max){
        return updatePiecedamnumWithMaxInKeys(keys, pieceDamNum, _max, TABLENAME);
    }

    public int updatePiecedamnumWithMaxInKeys(final List<Integer> keys, final int pieceDamNum, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceDamNum = (select case when pieceDamNum+:pieceDamNum>=:_max then :_max else pieceDamNum+:pieceDamNum end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("pieceDamNum", pieceDamNum);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePiecedamnumWithMinMaxByKey(final int id, final int pieceDamNum, final int _min, final int _max){
        return updatePiecedamnumWithMinMaxByKey(id, pieceDamNum, _min, _max, TABLENAME);
    }

    public int updatePiecedamnumWithMinMaxByKey(final int id, final int pieceDamNum, final int _min, final int _max, final String TABLENAME2){
        if( pieceDamNum < 0 ) {
            return updatePiecedamnumWithMinByKey(id, pieceDamNum, _min, TABLENAME2);
        } else {
            return updatePiecedamnumWithMaxByKey(id, pieceDamNum, _max, TABLENAME2);
        }
    }

    public int updatePiecedamnumWithMinMaxInKeys(final List<Integer> keys, final int pieceDamNum, final int _min, final int _max){
        return updatePiecedamnumWithMinMaxInKeys(keys, pieceDamNum, _min, _max, TABLENAME);
    }

    public int updatePiecedamnumWithMinMaxInKeys(final List<Integer> keys, final int pieceDamNum, final int _min, final int _max, final String TABLENAME2){
        if( pieceDamNum < 0 ) {
            return updatePiecedamnumWithMinInKeys(keys, pieceDamNum, _min, TABLENAME2);
        } else {
            return updatePiecedamnumWithMaxInKeys(keys, pieceDamNum, _max, TABLENAME2);
        }
    }

    public int updatePieceattspeedByKey(final int pieceAttSpeed, final int id){
        return updatePieceattspeedByKey(pieceAttSpeed, id, TABLENAME);
    }

    public int updatePieceattspeedByKey(final int pieceAttSpeed, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceAttSpeed=pieceAttSpeed+:pieceAttSpeed WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("pieceAttSpeed", pieceAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePieceattspeedWithMinByKey(final int id, final int pieceAttSpeed, final int _min){
        return updatePieceattspeedWithMinByKey(id, pieceAttSpeed, _min, TABLENAME);
    }

    public int updatePieceattspeedWithMinByKey(final int id, final int pieceAttSpeed, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceAttSpeed = (select case when pieceAttSpeed+:pieceAttSpeed<=:_min then :_min else pieceAttSpeed+:pieceAttSpeed end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("pieceAttSpeed", pieceAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePieceattspeedWithMinInKeys(final List<Integer> keys, final int pieceAttSpeed, final int _min){
        return updatePieceattspeedWithMinInKeys(keys, pieceAttSpeed, _min, TABLENAME);
    }

    public int updatePieceattspeedWithMinInKeys(final List<Integer> keys, final int pieceAttSpeed, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceAttSpeed = (select case when pieceAttSpeed+:pieceAttSpeed<=:_min then :_min else pieceAttSpeed+:pieceAttSpeed end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("pieceAttSpeed", pieceAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePieceattspeedWithMaxByKey(final int id, final int pieceAttSpeed, final int _max){
        return updatePieceattspeedWithMaxByKey(id, pieceAttSpeed, _max, TABLENAME);
    }

    public int updatePieceattspeedWithMaxByKey(final int id, final int pieceAttSpeed, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceAttSpeed = (select case when pieceAttSpeed+:pieceAttSpeed>=:_max then :_max else pieceAttSpeed+:pieceAttSpeed end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("pieceAttSpeed", pieceAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePieceattspeedWithMaxInKeys(final List<Integer> keys, final int pieceAttSpeed, final int _max){
        return updatePieceattspeedWithMaxInKeys(keys, pieceAttSpeed, _max, TABLENAME);
    }

    public int updatePieceattspeedWithMaxInKeys(final List<Integer> keys, final int pieceAttSpeed, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pieceAttSpeed = (select case when pieceAttSpeed+:pieceAttSpeed>=:_max then :_max else pieceAttSpeed+:pieceAttSpeed end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("pieceAttSpeed", pieceAttSpeed);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePieceattspeedWithMinMaxByKey(final int id, final int pieceAttSpeed, final int _min, final int _max){
        return updatePieceattspeedWithMinMaxByKey(id, pieceAttSpeed, _min, _max, TABLENAME);
    }

    public int updatePieceattspeedWithMinMaxByKey(final int id, final int pieceAttSpeed, final int _min, final int _max, final String TABLENAME2){
        if( pieceAttSpeed < 0 ) {
            return updatePieceattspeedWithMinByKey(id, pieceAttSpeed, _min, TABLENAME2);
        } else {
            return updatePieceattspeedWithMaxByKey(id, pieceAttSpeed, _max, TABLENAME2);
        }
    }

    public int updatePieceattspeedWithMinMaxInKeys(final List<Integer> keys, final int pieceAttSpeed, final int _min, final int _max){
        return updatePieceattspeedWithMinMaxInKeys(keys, pieceAttSpeed, _min, _max, TABLENAME);
    }

    public int updatePieceattspeedWithMinMaxInKeys(final List<Integer> keys, final int pieceAttSpeed, final int _min, final int _max, final String TABLENAME2){
        if( pieceAttSpeed < 0 ) {
            return updatePieceattspeedWithMinInKeys(keys, pieceAttSpeed, _min, TABLENAME2);
        } else {
            return updatePieceattspeedWithMaxInKeys(keys, pieceAttSpeed, _max, TABLENAME2);
        }
    }

    public int updateLastleaveclanByKey(final long lastLeaveClan, final int id){
        return updateLastleaveclanByKey(lastLeaveClan, id, TABLENAME);
    }

    public int updateLastleaveclanByKey(final long lastLeaveClan, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastLeaveClan=lastLeaveClan+:lastLeaveClan WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("lastLeaveClan", lastLeaveClan);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastleaveclanWithMinByKey(final int id, final long lastLeaveClan, final long _min){
        return updateLastleaveclanWithMinByKey(id, lastLeaveClan, _min, TABLENAME);
    }

    public int updateLastleaveclanWithMinByKey(final int id, final long lastLeaveClan, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastLeaveClan = (select case when lastLeaveClan+:lastLeaveClan<=:_min then :_min else lastLeaveClan+:lastLeaveClan end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("lastLeaveClan", lastLeaveClan);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastleaveclanWithMinInKeys(final List<Integer> keys, final long lastLeaveClan, final long _min){
        return updateLastleaveclanWithMinInKeys(keys, lastLeaveClan, _min, TABLENAME);
    }

    public int updateLastleaveclanWithMinInKeys(final List<Integer> keys, final long lastLeaveClan, final long _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastLeaveClan = (select case when lastLeaveClan+:lastLeaveClan<=:_min then :_min else lastLeaveClan+:lastLeaveClan end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("lastLeaveClan", lastLeaveClan);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastleaveclanWithMaxByKey(final int id, final long lastLeaveClan, final long _max){
        return updateLastleaveclanWithMaxByKey(id, lastLeaveClan, _max, TABLENAME);
    }

    public int updateLastleaveclanWithMaxByKey(final int id, final long lastLeaveClan, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastLeaveClan = (select case when lastLeaveClan+:lastLeaveClan>=:_max then :_max else lastLeaveClan+:lastLeaveClan end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("lastLeaveClan", lastLeaveClan);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastleaveclanWithMaxInKeys(final List<Integer> keys, final long lastLeaveClan, final long _max){
        return updateLastleaveclanWithMaxInKeys(keys, lastLeaveClan, _max, TABLENAME);
    }

    public int updateLastleaveclanWithMaxInKeys(final List<Integer> keys, final long lastLeaveClan, final long _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET lastLeaveClan = (select case when lastLeaveClan+:lastLeaveClan>=:_max then :_max else lastLeaveClan+:lastLeaveClan end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("lastLeaveClan", lastLeaveClan);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateLastleaveclanWithMinMaxByKey(final int id, final long lastLeaveClan, final long _min, final long _max){
        return updateLastleaveclanWithMinMaxByKey(id, lastLeaveClan, _min, _max, TABLENAME);
    }

    public int updateLastleaveclanWithMinMaxByKey(final int id, final long lastLeaveClan, final long _min, final long _max, final String TABLENAME2){
        if( lastLeaveClan < 0 ) {
            return updateLastleaveclanWithMinByKey(id, lastLeaveClan, _min, TABLENAME2);
        } else {
            return updateLastleaveclanWithMaxByKey(id, lastLeaveClan, _max, TABLENAME2);
        }
    }

    public int updateLastleaveclanWithMinMaxInKeys(final List<Integer> keys, final long lastLeaveClan, final long _min, final long _max){
        return updateLastleaveclanWithMinMaxInKeys(keys, lastLeaveClan, _min, _max, TABLENAME);
    }

    public int updateLastleaveclanWithMinMaxInKeys(final List<Integer> keys, final long lastLeaveClan, final long _min, final long _max, final String TABLENAME2){
        if( lastLeaveClan < 0 ) {
            return updateLastleaveclanWithMinInKeys(keys, lastLeaveClan, _min, TABLENAME2);
        } else {
            return updateLastleaveclanWithMaxInKeys(keys, lastLeaveClan, _max, TABLENAME2);
        }
    }

    public int updateMonthcodeByKey(final long monthCode, final int id){
        return updateMonthcodeByKey(monthCode, id, TABLENAME);
    }

    public int updateMonthcodeByKey(final long monthCode, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monthCode=monthCode+:monthCode WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("monthCode", monthCode);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonthcodeWithMinByKey(final int id, final long monthCode, final long _min){
        return updateMonthcodeWithMinByKey(id, monthCode, _min, TABLENAME);
    }

    public int updateMonthcodeWithMinByKey(final int id, final long monthCode, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monthCode = (select case when monthCode+:monthCode<=:_min then :_min else monthCode+:monthCode end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("monthCode", monthCode);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonthcodeWithMinInKeys(final List<Integer> keys, final long monthCode, final long _min){
        return updateMonthcodeWithMinInKeys(keys, monthCode, _min, TABLENAME);
    }

    public int updateMonthcodeWithMinInKeys(final List<Integer> keys, final long monthCode, final long _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monthCode = (select case when monthCode+:monthCode<=:_min then :_min else monthCode+:monthCode end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("monthCode", monthCode);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonthcodeWithMaxByKey(final int id, final long monthCode, final long _max){
        return updateMonthcodeWithMaxByKey(id, monthCode, _max, TABLENAME);
    }

    public int updateMonthcodeWithMaxByKey(final int id, final long monthCode, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monthCode = (select case when monthCode+:monthCode>=:_max then :_max else monthCode+:monthCode end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("monthCode", monthCode);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonthcodeWithMaxInKeys(final List<Integer> keys, final long monthCode, final long _max){
        return updateMonthcodeWithMaxInKeys(keys, monthCode, _max, TABLENAME);
    }

    public int updateMonthcodeWithMaxInKeys(final List<Integer> keys, final long monthCode, final long _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET monthCode = (select case when monthCode+:monthCode>=:_max then :_max else monthCode+:monthCode end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("monthCode", monthCode);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMonthcodeWithMinMaxByKey(final int id, final long monthCode, final long _min, final long _max){
        return updateMonthcodeWithMinMaxByKey(id, monthCode, _min, _max, TABLENAME);
    }

    public int updateMonthcodeWithMinMaxByKey(final int id, final long monthCode, final long _min, final long _max, final String TABLENAME2){
        if( monthCode < 0 ) {
            return updateMonthcodeWithMinByKey(id, monthCode, _min, TABLENAME2);
        } else {
            return updateMonthcodeWithMaxByKey(id, monthCode, _max, TABLENAME2);
        }
    }

    public int updateMonthcodeWithMinMaxInKeys(final List<Integer> keys, final long monthCode, final long _min, final long _max){
        return updateMonthcodeWithMinMaxInKeys(keys, monthCode, _min, _max, TABLENAME);
    }

    public int updateMonthcodeWithMinMaxInKeys(final List<Integer> keys, final long monthCode, final long _min, final long _max, final String TABLENAME2){
        if( monthCode < 0 ) {
            return updateMonthcodeWithMinInKeys(keys, monthCode, _min, TABLENAME2);
        } else {
            return updateMonthcodeWithMaxInKeys(keys, monthCode, _max, TABLENAME2);
        }
    }

    public int updateShareByKey(final int share, final int id){
        return updateShareByKey(share, id, TABLENAME);
    }

    public int updateShareByKey(final int share, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET share=share+:share WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("share", share);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateShareWithMinByKey(final int id, final int share, final int _min){
        return updateShareWithMinByKey(id, share, _min, TABLENAME);
    }

    public int updateShareWithMinByKey(final int id, final int share, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET share = (select case when share+:share<=:_min then :_min else share+:share end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("share", share);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateShareWithMinInKeys(final List<Integer> keys, final int share, final int _min){
        return updateShareWithMinInKeys(keys, share, _min, TABLENAME);
    }

    public int updateShareWithMinInKeys(final List<Integer> keys, final int share, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET share = (select case when share+:share<=:_min then :_min else share+:share end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("share", share);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateShareWithMaxByKey(final int id, final int share, final int _max){
        return updateShareWithMaxByKey(id, share, _max, TABLENAME);
    }

    public int updateShareWithMaxByKey(final int id, final int share, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET share = (select case when share+:share>=:_max then :_max else share+:share end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("share", share);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateShareWithMaxInKeys(final List<Integer> keys, final int share, final int _max){
        return updateShareWithMaxInKeys(keys, share, _max, TABLENAME);
    }

    public int updateShareWithMaxInKeys(final List<Integer> keys, final int share, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET share = (select case when share+:share>=:_max then :_max else share+:share end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("share", share);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateShareWithMinMaxByKey(final int id, final int share, final int _min, final int _max){
        return updateShareWithMinMaxByKey(id, share, _min, _max, TABLENAME);
    }

    public int updateShareWithMinMaxByKey(final int id, final int share, final int _min, final int _max, final String TABLENAME2){
        if( share < 0 ) {
            return updateShareWithMinByKey(id, share, _min, TABLENAME2);
        } else {
            return updateShareWithMaxByKey(id, share, _max, TABLENAME2);
        }
    }

    public int updateShareWithMinMaxInKeys(final List<Integer> keys, final int share, final int _min, final int _max){
        return updateShareWithMinMaxInKeys(keys, share, _min, _max, TABLENAME);
    }

    public int updateShareWithMinMaxInKeys(final List<Integer> keys, final int share, final int _min, final int _max, final String TABLENAME2){
        if( share < 0 ) {
            return updateShareWithMinInKeys(keys, share, _min, TABLENAME2);
        } else {
            return updateShareWithMaxInKeys(keys, share, _max, TABLENAME2);
        }
    }

    public int updateMoneyactivityByKey(final double moneyActivity, final int id){
        return updateMoneyactivityByKey(moneyActivity, id, TABLENAME);
    }

    public int updateMoneyactivityByKey(final double moneyActivity, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyActivity=moneyActivity+:moneyActivity WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("moneyActivity", moneyActivity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyactivityWithMinByKey(final int id, final double moneyActivity, final double _min){
        return updateMoneyactivityWithMinByKey(id, moneyActivity, _min, TABLENAME);
    }

    public int updateMoneyactivityWithMinByKey(final int id, final double moneyActivity, final double _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyActivity = (select case when moneyActivity+:moneyActivity<=:_min then :_min else moneyActivity+:moneyActivity end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("moneyActivity", moneyActivity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyactivityWithMinInKeys(final List<Integer> keys, final double moneyActivity, final double _min){
        return updateMoneyactivityWithMinInKeys(keys, moneyActivity, _min, TABLENAME);
    }

    public int updateMoneyactivityWithMinInKeys(final List<Integer> keys, final double moneyActivity, final double _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyActivity = (select case when moneyActivity+:moneyActivity<=:_min then :_min else moneyActivity+:moneyActivity end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("moneyActivity", moneyActivity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyactivityWithMaxByKey(final int id, final double moneyActivity, final double _max){
        return updateMoneyactivityWithMaxByKey(id, moneyActivity, _max, TABLENAME);
    }

    public int updateMoneyactivityWithMaxByKey(final int id, final double moneyActivity, final double _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyActivity = (select case when moneyActivity+:moneyActivity>=:_max then :_max else moneyActivity+:moneyActivity end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("moneyActivity", moneyActivity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyactivityWithMaxInKeys(final List<Integer> keys, final double moneyActivity, final double _max){
        return updateMoneyactivityWithMaxInKeys(keys, moneyActivity, _max, TABLENAME);
    }

    public int updateMoneyactivityWithMaxInKeys(final List<Integer> keys, final double moneyActivity, final double _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyActivity = (select case when moneyActivity+:moneyActivity>=:_max then :_max else moneyActivity+:moneyActivity end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("moneyActivity", moneyActivity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyactivityWithMinMaxByKey(final int id, final double moneyActivity, final double _min, final double _max){
        return updateMoneyactivityWithMinMaxByKey(id, moneyActivity, _min, _max, TABLENAME);
    }

    public int updateMoneyactivityWithMinMaxByKey(final int id, final double moneyActivity, final double _min, final double _max, final String TABLENAME2){
        if( moneyActivity < 0 ) {
            return updateMoneyactivityWithMinByKey(id, moneyActivity, _min, TABLENAME2);
        } else {
            return updateMoneyactivityWithMaxByKey(id, moneyActivity, _max, TABLENAME2);
        }
    }

    public int updateMoneyactivityWithMinMaxInKeys(final List<Integer> keys, final double moneyActivity, final double _min, final double _max){
        return updateMoneyactivityWithMinMaxInKeys(keys, moneyActivity, _min, _max, TABLENAME);
    }

    public int updateMoneyactivityWithMinMaxInKeys(final List<Integer> keys, final double moneyActivity, final double _min, final double _max, final String TABLENAME2){
        if( moneyActivity < 0 ) {
            return updateMoneyactivityWithMinInKeys(keys, moneyActivity, _min, TABLENAME2);
        } else {
            return updateMoneyactivityWithMaxInKeys(keys, moneyActivity, _max, TABLENAME2);
        }
    }

    public int updateMoneyactivitytypeByKey(final int moneyActivityType, final int id){
        return updateMoneyactivitytypeByKey(moneyActivityType, id, TABLENAME);
    }

    public int updateMoneyactivitytypeByKey(final int moneyActivityType, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyActivityType=moneyActivityType+:moneyActivityType WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("moneyActivityType", moneyActivityType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyactivitytypeWithMinByKey(final int id, final int moneyActivityType, final int _min){
        return updateMoneyactivitytypeWithMinByKey(id, moneyActivityType, _min, TABLENAME);
    }

    public int updateMoneyactivitytypeWithMinByKey(final int id, final int moneyActivityType, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyActivityType = (select case when moneyActivityType+:moneyActivityType<=:_min then :_min else moneyActivityType+:moneyActivityType end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("moneyActivityType", moneyActivityType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyactivitytypeWithMinInKeys(final List<Integer> keys, final int moneyActivityType, final int _min){
        return updateMoneyactivitytypeWithMinInKeys(keys, moneyActivityType, _min, TABLENAME);
    }

    public int updateMoneyactivitytypeWithMinInKeys(final List<Integer> keys, final int moneyActivityType, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyActivityType = (select case when moneyActivityType+:moneyActivityType<=:_min then :_min else moneyActivityType+:moneyActivityType end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("moneyActivityType", moneyActivityType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyactivitytypeWithMaxByKey(final int id, final int moneyActivityType, final int _max){
        return updateMoneyactivitytypeWithMaxByKey(id, moneyActivityType, _max, TABLENAME);
    }

    public int updateMoneyactivitytypeWithMaxByKey(final int id, final int moneyActivityType, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyActivityType = (select case when moneyActivityType+:moneyActivityType>=:_max then :_max else moneyActivityType+:moneyActivityType end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("moneyActivityType", moneyActivityType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyactivitytypeWithMaxInKeys(final List<Integer> keys, final int moneyActivityType, final int _max){
        return updateMoneyactivitytypeWithMaxInKeys(keys, moneyActivityType, _max, TABLENAME);
    }

    public int updateMoneyactivitytypeWithMaxInKeys(final List<Integer> keys, final int moneyActivityType, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET moneyActivityType = (select case when moneyActivityType+:moneyActivityType>=:_max then :_max else moneyActivityType+:moneyActivityType end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("moneyActivityType", moneyActivityType);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateMoneyactivitytypeWithMinMaxByKey(final int id, final int moneyActivityType, final int _min, final int _max){
        return updateMoneyactivitytypeWithMinMaxByKey(id, moneyActivityType, _min, _max, TABLENAME);
    }

    public int updateMoneyactivitytypeWithMinMaxByKey(final int id, final int moneyActivityType, final int _min, final int _max, final String TABLENAME2){
        if( moneyActivityType < 0 ) {
            return updateMoneyactivitytypeWithMinByKey(id, moneyActivityType, _min, TABLENAME2);
        } else {
            return updateMoneyactivitytypeWithMaxByKey(id, moneyActivityType, _max, TABLENAME2);
        }
    }

    public int updateMoneyactivitytypeWithMinMaxInKeys(final List<Integer> keys, final int moneyActivityType, final int _min, final int _max){
        return updateMoneyactivitytypeWithMinMaxInKeys(keys, moneyActivityType, _min, _max, TABLENAME);
    }

    public int updateMoneyactivitytypeWithMinMaxInKeys(final List<Integer> keys, final int moneyActivityType, final int _min, final int _max, final String TABLENAME2){
        if( moneyActivityType < 0 ) {
            return updateMoneyactivitytypeWithMinInKeys(keys, moneyActivityType, _min, TABLENAME2);
        } else {
            return updateMoneyactivitytypeWithMaxInKeys(keys, moneyActivityType, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Player> players) {
        return updateByKey(players, TABLENAME);
    }

    public int[] updateByKey (final List<Player> players, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(players == null || players.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid=:pcid, ucid=:ucid, svcid=:svcid, pname=:pname, type=:type, state=:state, channel=:channel, icon=:icon, exp=:exp, crystal=:crystal, renown=:renown, weekRenown=:weekRenown, cur_npc=:cur_npc, npcs=:npcs, all_money=:all_money, last_money=:last_money, last_pay_time=:last_pay_time, guid_step=:guid_step, clancid=:clancid, clanPost=:clanPost, cname=:cname, cicon=:cicon, maxBuidId=:maxBuidId, maxObstId=:maxObstId, stored_oil=:stored_oil, stored_gold=:stored_gold, isOnline=:isOnline, protectTime=:protectTime, maxAttMCId=:maxAttMCId, maxBONum=:maxBONum, curBONum=:curBONum, spells=:spells, lastAddObst=:lastAddObst, mark=:mark, curtownlvl=:curtownlvl, loginDay=:loginDay, lastLoginTime=:lastLoginTime, isRewardDay=:isRewardDay, firstPayStatus=:firstPayStatus, pieceHPNum=:pieceHPNum, pieceDamNum=:pieceDamNum, pieceAttSpeed=:pieceAttSpeed, dayTasks=:dayTasks, lastLeaveClan=:lastLeaveClan, monthCode=:monthCode, isMonCode=:isMonCode, builds=:builds, obstes=:obstes, teches=:teches, heroes=:heroes, share=:share, moneyActivity=:moneyActivity, moneyActivityType=:moneyActivityType WHERE id=:id");
            return super.batchUpdate2(sql.toString(), players);
        } catch(Exception e) {
            log.info(e2s(e));
            return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void createTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`ucid`  INT(11) NOT NULL," +
                "	`svcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`state`  INT(11) NOT NULL," +
                "	`channel`  VARCHAR(16) NOT NULL," +
                "	`icon`  INT(11) NOT NULL," +
                "	`exp`  INT(11) NOT NULL," +
                "	`crystal`  INT(11) NOT NULL," +
                "	`renown`  INT(11) NOT NULL," +
                "	`weekRenown`  INT(11) NOT NULL," +
                "	`cur_npc`  INT(11) NOT NULL," +
                "	`npcs`  TEXT NOT NULL," +
                "	`all_money`  INT(11) NOT NULL," +
                "	`last_money`  INT(11) NOT NULL," +
                "	`last_pay_time`  BIGINT(20) NOT NULL," +
                "	`guid_step`  INT(11) NOT NULL," +
                "	`clancid`  VARCHAR(16) NOT NULL," +
                "	`clanPost`  INT(11) NOT NULL," +
                "	`cname`  VARCHAR(32) NOT NULL," +
                "	`cicon`  INT(11) NOT NULL," +
                "	`maxBuidId`  INT(11) NOT NULL," +
                "	`maxObstId`  INT(11) NOT NULL," +
                "	`stored_oil`  INT(11) NOT NULL," +
                "	`stored_gold`  INT(11) NOT NULL," +
                "	`isOnline`  BIT(1) NOT NULL," +
                "	`protectTime`  BIGINT(20) NOT NULL," +
                "	`maxAttMCId`  INT(11) NOT NULL," +
                "	`maxBONum`  INT(11) NOT NULL," +
                "	`curBONum`  INT(11) NOT NULL," +
                "	`spells`  TEXT NOT NULL," +
                "	`lastAddObst`  BIGINT(20) NOT NULL," +
                "	`mark`  TEXT NOT NULL," +
                "	`curtownlvl`  INT(11) NOT NULL," +
                "	`loginDay`  INT(11) NOT NULL," +
                "	`lastLoginTime`  BIGINT(20) NOT NULL," +
                "	`isRewardDay`  BIT(1) NOT NULL," +
                "	`firstPayStatus`  INT(11) NOT NULL," +
                "	`pieceHPNum`  INT(11) NOT NULL," +
                "	`pieceDamNum`  INT(11) NOT NULL," +
                "	`pieceAttSpeed`  INT(11) NOT NULL," +
                "	`dayTasks`  TEXT NOT NULL," +
                "	`lastLeaveClan`  BIGINT(20) NOT NULL," +
                "	`monthCode`  BIGINT(20) NOT NULL," +
                "	`isMonCode`  BIT(1) NOT NULL," +
                "	`builds`  TEXT NOT NULL," +
                "	`obstes`  TEXT NOT NULL," +
                "	`teches`  TEXT NOT NULL," +
                "	`heroes`  TEXT NOT NULL," +
                "	`share`  INT(11) NOT NULL," +
                "	`moneyActivity`  DOUBLE NOT NULL," +
                "	`moneyActivityType`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `pname` (`pname`)," +
                "	UNIQUE KEY `pcid` (`pcid`)," +
                "	KEY `uid` (`ucid`, `svcid`)" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";

            Map params = newMap();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            super.update(sql);
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public void createNoUniqueTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`ucid`  INT(11) NOT NULL," +
                "	`svcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`type`  INT(11) NOT NULL," +
                "	`state`  INT(11) NOT NULL," +
                "	`channel`  VARCHAR(16) NOT NULL," +
                "	`icon`  INT(11) NOT NULL," +
                "	`exp`  INT(11) NOT NULL," +
                "	`crystal`  INT(11) NOT NULL," +
                "	`renown`  INT(11) NOT NULL," +
                "	`weekRenown`  INT(11) NOT NULL," +
                "	`cur_npc`  INT(11) NOT NULL," +
                "	`npcs`  TEXT NOT NULL," +
                "	`all_money`  INT(11) NOT NULL," +
                "	`last_money`  INT(11) NOT NULL," +
                "	`last_pay_time`  BIGINT(20) NOT NULL," +
                "	`guid_step`  INT(11) NOT NULL," +
                "	`clancid`  VARCHAR(16) NOT NULL," +
                "	`clanPost`  INT(11) NOT NULL," +
                "	`cname`  VARCHAR(32) NOT NULL," +
                "	`cicon`  INT(11) NOT NULL," +
                "	`maxBuidId`  INT(11) NOT NULL," +
                "	`maxObstId`  INT(11) NOT NULL," +
                "	`stored_oil`  INT(11) NOT NULL," +
                "	`stored_gold`  INT(11) NOT NULL," +
                "	`isOnline`  BIT(1) NOT NULL," +
                "	`protectTime`  BIGINT(20) NOT NULL," +
                "	`maxAttMCId`  INT(11) NOT NULL," +
                "	`maxBONum`  INT(11) NOT NULL," +
                "	`curBONum`  INT(11) NOT NULL," +
                "	`spells`  TEXT NOT NULL," +
                "	`lastAddObst`  BIGINT(20) NOT NULL," +
                "	`mark`  TEXT NOT NULL," +
                "	`curtownlvl`  INT(11) NOT NULL," +
                "	`loginDay`  INT(11) NOT NULL," +
                "	`lastLoginTime`  BIGINT(20) NOT NULL," +
                "	`isRewardDay`  BIT(1) NOT NULL," +
                "	`firstPayStatus`  INT(11) NOT NULL," +
                "	`pieceHPNum`  INT(11) NOT NULL," +
                "	`pieceDamNum`  INT(11) NOT NULL," +
                "	`pieceAttSpeed`  INT(11) NOT NULL," +
                "	`dayTasks`  TEXT NOT NULL," +
                "	`lastLeaveClan`  BIGINT(20) NOT NULL," +
                "	`monthCode`  BIGINT(20) NOT NULL," +
                "	`isMonCode`  BIT(1) NOT NULL," +
                "	`builds`  TEXT NOT NULL," +
                "	`obstes`  TEXT NOT NULL," +
                "	`teches`  TEXT NOT NULL," +
                "	`heroes`  TEXT NOT NULL," +
                "	`share`  INT(11) NOT NULL," +
                "	`moneyActivity`  DOUBLE NOT NULL," +
                "	`moneyActivityType`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `pname` (`pname`)," +
                "	KEY `pcid` (`pcid`)," +
                "	KEY `uid` (`ucid`, `svcid`)" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";

            Map params = newMap();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            super.update(sql);
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public void truncate(){
        try {
            super.truncate(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void repair(){
        try {
            super.repair(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void optimize(){
        try {
            super.optimize(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

}
