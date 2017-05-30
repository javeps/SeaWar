package gen_b2g.test;

import gen_b2g.serv.bean.ReturnStatus;
import gen_b2g.test.bean.NString;

import java.util.Map;
import java.util.Set;

import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewMap;
import com.bowlong.util.NewSet;

@SuppressWarnings({ "all"})
public abstract class TestI {

public abstract TcpChannel chn(int XID) throws Exception;


    public static final Set<Integer> CMD = NewSet.create().Add(3556498);

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
            case 3556498: { //  修改名称
                __test(chn, map);
                return "test";
            }
        }
        throw new Exception(" cmd: " + cmd + ":" + map + " not found processor.");
    }

    // //////////////////////////////////////////////
    // 解析参数
    // //////////////////////////////////////////////

    // 修改名称
    private void __test(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String paramet = map2.getString(-793496068);
        NString reslut = new NString();

        ReturnStatus rst = new ReturnStatus();
        onTest(chn, paramet, reslut, rst);
        Map result = new NewMap();
        result.put(0, 3556498);
        result.put(1, rst.toMap());
        result.put(-934434965, reslut.toMap());
        chn.send(result);
    }


    // //////////////////////////////////////////////
    // 需要实现的接口
    // //////////////////////////////////////////////

    // 修改名称
    public abstract ReturnStatus onTest(TcpChannel chn , String paramet, NString reslut, ReturnStatus ret) throws Exception;


    // //////////////////////////////////////////////
    // PV操作 - 逻辑分发
    // //////////////////////////////////////////////

    public String _params(NewMap map) throws Exception {
        int cmd = map.getInt(0);
        return _params(cmd, map);
    }
    public String _params(int cmd, NewMap map) throws Exception {
        switch (cmd) {
            case 3556498: { //  修改名称
                return __test_params(map);
            }
        }
        throw new Exception(" cmd: " + cmd + ":" + map + " not found processor.");
    }

    // //////////////////////////////////////////////
    // PV操作 - 解析参数 X
    // //////////////////////////////////////////////

    // 修改名称
    private String __test_params(NewMap map2) throws Exception {
        String paramet = map2.getString(-793496068);
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("test(");
            sb.append("\"paramet\":").append(paramet).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
}
