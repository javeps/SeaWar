using System;
using System.Collections;
using Toolkit;

namespace TestBuilder {

public interface TcpChannel {
    void send(Hashtable map);
}

public class NString {
    public const int _CID = -1302308353;

    public string strV = ""; // 内容


    public Hashtable toMap() {
        Hashtable r = new Hashtable();
        r.Add(-1, _CID);
        r.Add(3541061, strV);
        return r;
    }


    public static NString parse(Hashtable map) {
        if(map == null) return null;

        NewMap map2 = NewMap.create(map);
        NString r = new NString();
        r.strV = map2.getString(3541061);
        return r;
    }

}
public abstract class CallTestI {

    public static int __uid;
    public TcpChannel chn;
    public CallTestI(TcpChannel chn) {
        this.chn = chn;
    }

    // 修改名称
    public void test(String paramet) {
        Hashtable _map = new Hashtable();
        _map.Add(-100, __uid);  // __uid
        _map.Add(0, 3556498);  // cmd:test
        _map.Add(-793496068, paramet);
        chn.send(_map);
    }


    public static NewSet CMD = NewSet.create().Add(3556498);

    public static bool withIn(Hashtable map) {
        int cmd = MapEx.getInt(map, 0);
        return CMD.Contains(cmd);
    }

    // //////////////////////////////////////////////
    // 逻辑分发
    // //////////////////////////////////////////////

    public void disp(Hashtable map) {
        int cmd = MapEx.getInt(map, 0);
        disp(cmd, map);
    }
    public void disp(int cmd, Hashtable map) {
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
    private void __onCallback_test(Hashtable map) {
        NewMap map2 = NewMap.create(map);
        Hashtable retVal = map2.getMap(1);
        ReturnStatus rst = ReturnStatus.parse(retVal);
        NString reslut = NString.parse(map2.getMap(-934434965));

        onTest(reslut, rst);
    }


    // //////////////////////////////////////////////
    // 需要实现的接口
    // //////////////////////////////////////////////

    // 修改名称
    public const string const_Test = "onTest";
    public abstract void onTest(NString reslut, ReturnStatus val);

    }
}
