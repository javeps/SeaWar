package gen_b2g.test;

import gen_b2g.serv.bean.ReturnStatus;
import gen_b2g.test.bean.NString;

import java.util.Set;

import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewMap;
import com.bowlong.util.NewSet;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class CallTestI {

    public static int __pid;
    public TcpChannel chn;
    public CallTestI(TcpChannel chn) {
        this.chn = chn;
    }

    // 修改名称
    public void test(String paramet) throws Exception {
        NewMap _map = new NewMap();
        _map.put(-100, __pid);  // _pid
        _map.put(0, 3556498);  // cmd:test
        _map.put(-793496068, paramet);
        chn.send(_map);
    }


    public static final Set<Integer> CMD = NewSet.create().Add(3556498);

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
            case 3556498: { //  修改名称
                __onCallback_test(map);
                return;
            }
        }
        throw new Exception(" cmd: " + cmd + ":" + map + " not found processor.");
    }


    // //////////////////////////////////////////////
    // 参数解析
    // //////////////////////////////////////////////

    // 修改名称
    private void __onCallback_test(NewMap map2) throws Exception {
        NewMap retVal = map2.getNewMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NString reslut = NString.parse(map2.getNewMap(-934434965));

        onTest(reslut, rst);
    }


    // //////////////////////////////////////////////
    // 需要实现的接口
    // //////////////////////////////////////////////

    // 修改名称
    public void onTest(NString reslut, ReturnStatus val) throws Exception {};

}
