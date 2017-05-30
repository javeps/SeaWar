package gen_b2g.gamegate;

import gen_b2g.serv.bean.NInt;
import gen_b2g.serv.bean.NInts;
import gen_b2g.serv.bean.NStrVal;
import gen_b2g.serv.bean.NStrings;
import gen_b2g.serv.bean.ReturnStatus;

import java.util.Set;

import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewMap;
import com.bowlong.util.NewSet;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class CallGameGateI {

    public static int __pid;
    public TcpChannel chn;
    public CallGameGateI(TcpChannel chn) {
        this.chn = chn;
    }

    // 充值通知到游戏
    public void paymentNotice() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1283496062);  // cmd:paymentNotice
        chn.send(_map);
    }

    // 下发系统到游戏
    public void sendSystemMailToAll() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 1649851288);  // cmd:sendSystemMailToAll
        chn.send(_map);
    }

    // 下发系统到游戏
    public void sendSystemMailToPlayers() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -1275449303);  // cmd:sendSystemMailToPlayers
        chn.send(_map);
    }

    // 增加用户资源-pcid
    public void addGamePlayerResByPcids() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 847215906);  // cmd:addGamePlayerResByPcids
        chn.send(_map);
    }

    // 增加用户资源-名字
    public void addGamePlayerResByNames() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 845313157);  // cmd:addGamePlayerResByNames
        chn.send(_map);
    }

    // 下发公聊到游戏
    public void sendPubChat() throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, -500059315);  // cmd:sendPubChat
        chn.send(_map);
    }


    public static final Set<Integer> CMD = NewSet.create().Add(1283496062).Add(1649851288).Add(-1275449303).Add(847215906).Add(845313157).Add(-500059315);

    public static boolean in(NewMap map) throws Exception {
        int cmd = MapEx.getInt(map, 0);
        return CMD.contains(cmd);
    }

    // //////////////////////////////////////////////
    // 逻辑分发
    // //////////////////////////////////////////////

    public void disp(NewMap map) throws Exception {
        int cmd = MapEx.getInt(map, 0);
        disp(cmd, map);
    }
    public void disp(int cmd, NewMap map) throws Exception {
        switch (cmd) {
            case 1283496062: { //  充值通知到游戏
                __onCallback_paymentNotice(map);
                return;
            }
            case 1649851288: { //  下发系统到游戏
                __onCallback_sendSystemMailToAll(map);
                return;
            }
            case -1275449303: { //  下发系统到游戏
                __onCallback_sendSystemMailToPlayers(map);
                return;
            }
            case 847215906: { //  增加用户资源-pcid
                __onCallback_addGamePlayerResByPcids(map);
                return;
            }
            case 845313157: { //  增加用户资源-名字
                __onCallback_addGamePlayerResByNames(map);
                return;
            }
            case -500059315: { //  下发公聊到游戏
                __onCallback_sendPubChat(map);
                return;
            }
        }
        throw new Exception(" cmd: " + cmd + ":" + map + " not found processor.");
    }


    // //////////////////////////////////////////////
    // 参数解析
    // //////////////////////////////////////////////

    // 充值通知到游戏
    private void __onCallback_paymentNotice(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrVal billNo = NStrVal.parse(map2.getNewMap(-1389017048));
        NInt svcid = NInt.parse(map2.getNewMap(109818747));

        onPaymentNotice(billNo, svcid, rst);
    }

    // 下发系统到游戏
    private void __onCallback_sendSystemMailToAll(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NInt substr = NInt.parse(map2.getNewMap(-891529231));
        NStrVal title = NStrVal.parse(map2.getNewMap(110371416));
        NStrVal content = NStrVal.parse(map2.getNewMap(951530617));

        onSendSystemMailToAll(substr, title, content, rst);
    }

    // 下发系统到游戏
    private void __onCallback_sendSystemMailToPlayers(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NInt substr = NInt.parse(map2.getNewMap(-891529231));
        NStrings pnames = NStrings.parse(map2.getNewMap(-983917352));
        NStrVal title = NStrVal.parse(map2.getNewMap(110371416));
        NStrVal content = NStrVal.parse(map2.getNewMap(951530617));

        onSendSystemMailToPlayers(substr, pnames, title, content, rst);
    }

    // 增加用户资源-pcid
    private void __onCallback_addGamePlayerResByPcids(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NInts pcids = NInts.parse(map2.getNewMap(106487781));
        NStrVal resType = NStrVal.parse(map2.getNewMap(1096575994));
        NInt resVal = NInt.parse(map2.getNewMap(-934456735));

        onAddGamePlayerResByPcids(pcids, resType, resVal, rst);
    }

    // 增加用户资源-名字
    private void __onCallback_addGamePlayerResByNames(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NStrings pnames = NStrings.parse(map2.getNewMap(-983917352));
        NStrVal resType = NStrVal.parse(map2.getNewMap(1096575994));
        NInt resVal = NInt.parse(map2.getNewMap(-934456735));

        onAddGamePlayerResByNames(pnames, resType, resVal, rst);
    }

    // 下发公聊到游戏
    private void __onCallback_sendPubChat(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NInt substr = NInt.parse(map2.getNewMap(-891529231));
        NStrVal content = NStrVal.parse(map2.getNewMap(951530617));

        onSendPubChat(substr, content, rst);
    }


    // //////////////////////////////////////////////
    // 需要实现的接口
    // //////////////////////////////////////////////

    // 充值通知到游戏
    public void onPaymentNotice(NStrVal billNo, NInt svcid, ReturnStatus val) throws Exception {};

    // 下发系统到游戏
    public void onSendSystemMailToAll(NInt substr, NStrVal title, NStrVal content, ReturnStatus val) throws Exception {};

    // 下发系统到游戏
    public void onSendSystemMailToPlayers(NInt substr, NStrings pnames, NStrVal title, NStrVal content, ReturnStatus val) throws Exception {};

    // 增加用户资源-pcid
    public void onAddGamePlayerResByPcids(NInts pcids, NStrVal resType, NInt resVal, ReturnStatus val) throws Exception {};

    // 增加用户资源-名字
    public void onAddGamePlayerResByNames(NStrings pnames, NStrVal resType, NInt resVal, ReturnStatus val) throws Exception {};

    // 下发公聊到游戏
    public void onSendPubChat(NInt substr, NStrVal content, ReturnStatus val) throws Exception {};

}
