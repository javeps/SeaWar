package gen_b2g.gamegate;

import gen_b2g.serv.bean.NInt;
import gen_b2g.serv.bean.NInts;
import gen_b2g.serv.bean.NStrVal;
import gen_b2g.serv.bean.NStrings;
import gen_b2g.serv.bean.ReturnStatus;

import java.util.Map;
import java.util.Set;

import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewMap;
import com.bowlong.util.NewSet;

@SuppressWarnings({ "all"})
public abstract class GameGateI {

public abstract TcpChannel chn(int XID) throws Exception;


    public static final Set<Integer> CMD = NewSet.create().Add(1283496062).Add(1649851288).Add(-1275449303).Add(847215906).Add(845313157).Add(-500059315);

    public static boolean in(NewMap map) throws Exception {
        int cmd = MapEx.getInt(map, 0);
        return CMD.contains(cmd);
    }

    public static boolean in(int cmd) throws Exception {
        return CMD.contains(cmd);
    }

    public abstract java.io.ByteArrayOutputStream getOutStream();
    public abstract void freeOutStream(java.io.ByteArrayOutputStream baos);

    // //////////////////////////////////////////////
    // 逻辑分发
    // //////////////////////////////////////////////

    public String disp(TcpChannel chn, NewMap map) throws Exception {
        if(chn == null) return "";
        int cmd = MapEx.getInt(map, 0);
        return disp(chn, cmd, map);
    }
    public String disp(TcpChannel chn, int cmd, NewMap map) throws Exception {
        if(chn == null) return "";
        switch (cmd) {
            case 1283496062: { //  充值通知到游戏
                __paymentNotice(chn, map);
                return "paymentNotice";
            }
            case 1649851288: { //  下发系统到游戏
                __sendSystemMailToAll(chn, map);
                return "sendSystemMailToAll";
            }
            case -1275449303: { //  下发系统到游戏
                __sendSystemMailToPlayers(chn, map);
                return "sendSystemMailToPlayers";
            }
            case 847215906: { //  增加用户资源-pcid
                __addGamePlayerResByPcids(chn, map);
                return "addGamePlayerResByPcids";
            }
            case 845313157: { //  增加用户资源-名字
                __addGamePlayerResByNames(chn, map);
                return "addGamePlayerResByNames";
            }
            case -500059315: { //  下发公聊到游戏
                __sendPubChat(chn, map);
                return "sendPubChat";
            }
        }
        throw new Exception(" cmd: " + cmd + ":" + map + " not found processor.");
    }

    // //////////////////////////////////////////////
    // 解析参数
    // //////////////////////////////////////////////

    // 充值通知到游戏
    private void __paymentNotice(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NStrVal billNo = new NStrVal();
        NInt svcid = new NInt();

        ReturnStatus rst = new ReturnStatus();
        onPaymentNotice(chn, billNo, svcid, rst);
        Map result = new NewMap();
        result.put(0, 1283496062);
        result.put(1, rst.toMap());
        result.put(-1389017048, billNo.toMap());
        result.put(109818747, svcid.toMap());
        chn.send(result);
    }

    // 下发系统到游戏
    private void __sendSystemMailToAll(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NInt substr = new NInt();
        NStrVal title = new NStrVal();
        NStrVal content = new NStrVal();

        ReturnStatus rst = new ReturnStatus();
        onSendSystemMailToAll(chn, substr, title, content, rst);
        Map result = new NewMap();
        result.put(0, 1649851288);
        result.put(1, rst.toMap());
        result.put(-891529231, substr.toMap());
        result.put(110371416, title.toMap());
        result.put(951530617, content.toMap());
        chn.send(result);
    }

    // 下发系统到游戏
    private void __sendSystemMailToPlayers(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NInt substr = new NInt();
        NStrings pnames = new NStrings();
        NStrVal title = new NStrVal();
        NStrVal content = new NStrVal();

        ReturnStatus rst = new ReturnStatus();
        onSendSystemMailToPlayers(chn, substr, pnames, title, content, rst);
        Map result = new NewMap();
        result.put(0, -1275449303);
        result.put(1, rst.toMap());
        result.put(-891529231, substr.toMap());
        result.put(-983917352, pnames.toMap());
        result.put(110371416, title.toMap());
        result.put(951530617, content.toMap());
        chn.send(result);
    }

    // 增加用户资源-pcid
    private void __addGamePlayerResByPcids(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NInts pcids = new NInts();
        NStrVal resType = new NStrVal();
        NInt resVal = new NInt();

        ReturnStatus rst = new ReturnStatus();
        onAddGamePlayerResByPcids(chn, pcids, resType, resVal, rst);
        Map result = new NewMap();
        result.put(0, 847215906);
        result.put(1, rst.toMap());
        result.put(106487781, pcids.toMap());
        result.put(1096575994, resType.toMap());
        result.put(-934456735, resVal.toMap());
        chn.send(result);
    }

    // 增加用户资源-名字
    private void __addGamePlayerResByNames(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NStrings pnames = new NStrings();
        NStrVal resType = new NStrVal();
        NInt resVal = new NInt();

        ReturnStatus rst = new ReturnStatus();
        onAddGamePlayerResByNames(chn, pnames, resType, resVal, rst);
        Map result = new NewMap();
        result.put(0, 845313157);
        result.put(1, rst.toMap());
        result.put(-983917352, pnames.toMap());
        result.put(1096575994, resType.toMap());
        result.put(-934456735, resVal.toMap());
        chn.send(result);
    }

    // 下发公聊到游戏
    private void __sendPubChat(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NInt substr = new NInt();
        NStrVal content = new NStrVal();

        ReturnStatus rst = new ReturnStatus();
        onSendPubChat(chn, substr, content, rst);
        Map result = new NewMap();
        result.put(0, -500059315);
        result.put(1, rst.toMap());
        result.put(-891529231, substr.toMap());
        result.put(951530617, content.toMap());
        chn.send(result);
    }


    // //////////////////////////////////////////////
    // 需要实现的接口
    // //////////////////////////////////////////////

    // 充值通知到游戏
    public abstract ReturnStatus onPaymentNotice(TcpChannel chn , NStrVal billNo, NInt svcid, ReturnStatus ret) throws Exception;
    // 下发系统到游戏
    public abstract ReturnStatus onSendSystemMailToAll(TcpChannel chn , NInt substr, NStrVal title, NStrVal content, ReturnStatus ret) throws Exception;
    // 下发系统到游戏
    public abstract ReturnStatus onSendSystemMailToPlayers(TcpChannel chn , NInt substr, NStrings pnames, NStrVal title, NStrVal content, ReturnStatus ret) throws Exception;
    // 增加用户资源-pcid
    public abstract ReturnStatus onAddGamePlayerResByPcids(TcpChannel chn , NInts pcids, NStrVal resType, NInt resVal, ReturnStatus ret) throws Exception;
    // 增加用户资源-名字
    public abstract ReturnStatus onAddGamePlayerResByNames(TcpChannel chn , NStrings pnames, NStrVal resType, NInt resVal, ReturnStatus ret) throws Exception;
    // 下发公聊到游戏
    public abstract ReturnStatus onSendPubChat(TcpChannel chn , NInt substr, NStrVal content, ReturnStatus ret) throws Exception;


    // //////////////////////////////////////////////
    // PV操作 - 逻辑分发
    // //////////////////////////////////////////////

    public String _params(NewMap map) throws Exception {
        int cmd = map.getInt(0);
        return _params(cmd, map);
    }
    public String _params(int cmd, NewMap map) throws Exception {
        switch (cmd) {
            case 1283496062: { //  充值通知到游戏
                return __paymentNotice_params(map);
            }
            case 1649851288: { //  下发系统到游戏
                return __sendSystemMailToAll_params(map);
            }
            case -1275449303: { //  下发系统到游戏
                return __sendSystemMailToPlayers_params(map);
            }
            case 847215906: { //  增加用户资源-pcid
                return __addGamePlayerResByPcids_params(map);
            }
            case 845313157: { //  增加用户资源-名字
                return __addGamePlayerResByNames_params(map);
            }
            case -500059315: { //  下发公聊到游戏
                return __sendPubChat_params(map);
            }
        }
        throw new Exception(" cmd: " + cmd + ":" + map + " not found processor.");
    }

    // //////////////////////////////////////////////
    // PV操作 - 解析参数 X
    // //////////////////////////////////////////////

    // 充值通知到游戏
    private String __paymentNotice_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("paymentNotice(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 下发系统到游戏
    private String __sendSystemMailToAll_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("sendSystemMailToAll(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 下发系统到游戏
    private String __sendSystemMailToPlayers_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("sendSystemMailToPlayers(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 增加用户资源-pcid
    private String __addGamePlayerResByPcids_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("addGamePlayerResByPcids(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 增加用户资源-名字
    private String __addGamePlayerResByNames_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("addGamePlayerResByNames(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 下发公聊到游戏
    private String __sendPubChat_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("sendPubChat(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
}
