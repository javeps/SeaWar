package com.sea.handler.gate.tcp;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NInt;
import gen_b2g.serv.bean.NInts;
import gen_b2g.serv.bean.NStrVal;
import gen_b2g.serv.bean.NStrings;
import gen_b2g.serv.bean.ReturnStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.NewMap;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.response.ResponseTcp;
import com.sea.json.originData.SVJson;

/**
 * web 服务 通知游戏服务器，该做什么处理了 异步请求
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class NotifyGateGame implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(NotifyGateGame.class);

	static ExecutorService pool = Executors.newCachedThreadPool();

	private NotifyGateGame() {
	}

	static NotifyGateGame _self;

	public static NotifyGateGame getInstance() {
		if (_self == null)
			_self = new NotifyGateGame();

		return _self;
	}

	Map<Integer, Channel> gameChannelMap = new HashMap<Integer, Channel>();

	public void addGameChannel(final ChannelHandlerContext ctx)
			throws Exception {
		if (ctx == null)
			return;
		Channel chn = ctx.getChannel();
		boolean isCon = chn.isConnected();
		if (!isCon)
			return;
		int cid = chn.getId();
		gameChannelMap.put(cid, chn);
	}

	public void removeKey(int key) {
		boolean isCon = gameChannelMap.containsKey(key);
		if (isCon) {
			gameChannelMap.remove(key);
		}
	}

	public int GmMapSize() {
		return gameChannelMap.size();
	}

	private TcpChannel getTcpByChannnel(final Channel channel) throws Exception {

		TcpChannel tcp = new TcpChannel() {

			@Override
			public void send(byte[] buff) throws Exception {
				ResponseTcp.send(channel, buff);
			}

			@Override
			public void send(Map<Object, Object> params) throws Exception {
				ResponseTcp.send(channel, params);
			}
		};
		return tcp;
	}

	public void sendToGame(final Object... objs) {
		synchronized (gameChannelMap) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					List<Integer> keys = new ArrayList<Integer>();
					try {
						for (Entry<Integer, Channel> item : gameChannelMap
								.entrySet()) {
							Channel chn = item.getValue();
							boolean isCon = chn.isConnected();
							if (!isCon) {
								int cid = chn.getId();
								keys.add(cid);
								continue;
							}
							_dispIssue(chn, objs);
						}
					} catch (Exception e) {
					}
					try {
						for (Integer k : keys) {
							gameChannelMap.remove(k);
						}
					} catch (Exception e) {

					}
				}
			});
		}
	}

	void _dispIssue(Channel chn, final Object... objs) throws Exception {
		if (objs == null || objs.length <= 0)
			return;
		String chnHost = chn.getRemoteAddress().toString();
		int chnSvcid = 0;
		if (chnHost.length() > 0) {
			int index = chnHost.indexOf(":");
			chnHost = chnHost.substring(1, index);
			chnSvcid = SVJson.getSvcidByHost(chnHost);
		}
		int ggType = (int) objs[0];
		int noticeSvcid = 0;
		String billNo = "";

		int parmeteType = -1;
		int sub = 0;
		String strTit = "";
		String strCon = "";

		String strList = "";

		boolean isCanExce = false;

		switch (ggType) {
		case GateConfig.TGG_Chat:
			noticeSvcid = (int) objs[1];
			sub = (int) objs[2];
			strCon = (String) objs[3];
			isCanExce = (noticeSvcid == 0) || (chnSvcid == noticeSvcid);
			if (isCanExce) {
				sendPubChat(chn, sub, strCon);
			}
			break;
		case GateConfig.TGG_AddPlayerRes:
			parmeteType = (int) objs[1];
			strList = (String) objs[2];
			String strResType = (String) objs[3];
			int intResVal = (int) objs[4];
			noticeSvcid = (int) objs[5];
			if (chnSvcid == noticeSvcid) {
				if (parmeteType == 1) {
					addGamePlayerResByNames(chn, strList, strResType, intResVal);
				} else {
					addGamePlayerResByPcids(chn, strList, strResType, intResVal);
				}
			}
			break;
		case GateConfig.TGG_Payment_25PP:
		case GateConfig.TGG_Payment_Ios:
		case GateConfig.TGG_Payment_MMY:
		case GateConfig.TGG_Payment_GP:
		case GateConfig.TGG_Payment_QH360:
		case GateConfig.TGG_Payment_Downjoy:
		case GateConfig.TGG_Payment_Mi:
		case GateConfig.TGG_Payment_Nd91:
		case GateConfig.TGG_Payment_UC:
		case GateConfig.TGG_Payment_MM:
		case GateConfig.TGG_Payment_Famv:
		case GateConfig.TGG_Payment_Ty:
		case GateConfig.TGG_Payment_He:
		case GateConfig.TGG_Payment_Tykj:
		case GateConfig.TGG_Payment_Wo:
		case GateConfig.TGG_Payment_Appvnios:
			noticeSvcid = (int) objs[1];
			billNo = (String) objs[2];
			if (chnSvcid == noticeSvcid) {
				paymentNotice(chn, billNo, noticeSvcid);
			}
			break;
		case GateConfig.TGG_Mail:
			noticeSvcid = (int) objs[1];
			parmeteType = (int) objs[2];
			sub = (int) objs[3];
			strTit = (String) objs[4];
			strCon = (String) objs[5];
			isCanExce = (noticeSvcid == 0) || (chnSvcid == noticeSvcid);
			if (isCanExce) {
				if (parmeteType == ConstantType.Type_Mail_System_Player) {
					strList = (String) objs[5];
					sendSystemMailToPlayers(chn, sub, strTit, strCon, strList);
				} else {
					sendSystemMailToAll(chn, sub, strTit, strCon);
				}
			}
			break;
		default:
			break;
		}
	}

	private void sendPubChat(Channel channnel, int sub, String strCont)
			throws Exception {

		if (channnel == null)
			return;

		NInt substr = new NInt();
		NStrVal content = new NStrVal();
		TcpChannel chn = getTcpByChannnel(channnel);
		ReturnStatus rst = new ReturnStatus();
		substr.val = sub;
		content.val = strCont;
		Map result = new NewMap();

		result.put(0, -500059315);
		result.put(1, rst.toMap());
		result.put(-891529231, substr.toMap());
		result.put(951530617, content.toMap());
		chn.send(result);
	}

	private void addGamePlayerResByNames(Channel channnel, String strList,
			String strResType, int intResVal) throws Exception {

		if (channnel == null)
			return;
		if (strList == null || "".equals(strList.trim()))
			return;

		List<String> pnameList = new ArrayList<String>();
		String[] lpnes = strList.split(",");
		for (String pn : lpnes) {
			if (pn == null || "".equals(pn.trim()))
				continue;
			pnameList.add(pn);
		}

		TcpChannel chn = getTcpByChannnel(channnel);

		NStrings pnames = new NStrings();
		NStrVal resType = new NStrVal();
		NInt resVal = new NInt();

		ReturnStatus rst = new ReturnStatus();

		pnames.val = pnameList;

		resType.val = strResType;
		resVal.val = intResVal;
		Map result = new NewMap();
		result.put(0, 845313157);
		result.put(1, rst.toMap());
		result.put(-983917352, pnames.toMap());
		result.put(1096575994, resType.toMap());
		result.put(-934456735, resVal.toMap());
		chn.send(result);
	}

	private void addGamePlayerResByPcids(Channel channnel, String strList,
			String strResType, int intResVal) throws Exception {

		if (channnel == null)
			return;
		if (strList == null || "".equals(strList.trim()))
			return;

		List<NInt> pcidList = new ArrayList<NInt>();
		String[] lpnes = strList.split(",");
		for (String pn : lpnes) {
			if (pn == null || "".equals(pn.trim()))
				continue;
			int inv = 0;
			try {
				inv = Integer.parseInt(pn);
			} catch (Exception e) {
			}
			if (inv == 0)
				continue;
			NInt v = new NInt();
			v.val = inv;
			pcidList.add(v);
		}

		TcpChannel chn = getTcpByChannnel(channnel);

		NInts pcids = new NInts();
		NStrVal resType = new NStrVal();
		NInt resVal = new NInt();
		ReturnStatus rst = new ReturnStatus();
		Map result = new NewMap();

		pcids.intes = pcidList;
		resType.val = strResType;
		resVal.val = intResVal;

		result.put(0, 847215906);
		result.put(1, rst.toMap());
		result.put(106487781, pcids.toMap());
		result.put(1096575994, resType.toMap());
		result.put(-934456735, resVal.toMap());
		chn.send(result);
	}

	private void paymentNotice(Channel channnel, String strBillNo, int svcid)
			throws Exception {

		NStrVal billNo = new NStrVal();
		NInt svcidInt = new NInt();

		ReturnStatus rst = new ReturnStatus();
		TcpChannel chn = getTcpByChannnel(channnel);

		billNo.val = strBillNo;
		svcidInt.val = svcid;

		Map result = new NewMap();
		result.put(0, 1283496062);
		result.put(1, rst.toMap());
		result.put(-1389017048, billNo.toMap());
		result.put(109818747, svcidInt.toMap());
		chn.send(result);

	}

	private void sendSystemMailToPlayers(Channel channnel, int sub,
			String strTit, String strCon, String strList) throws Exception {

		if (channnel == null)
			return;

		if (strList == null || "".equals(strList.trim()))
			return;

		List<String> pnameList = new ArrayList<String>();
		String[] lpnes = strList.split(",");
		for (String pn : lpnes) {
			if (pn == null || "".equals(pn.trim()))
				continue;
			pnameList.add(pn);
		}

		if (strCon == null || "".equals(strCon.trim()))
			return;

		if (strTit == null)
			strTit = "";

		TcpChannel chn = getTcpByChannnel(channnel);

		NInt substr = new NInt();
		NStrings pnames = new NStrings();
		NStrVal title = new NStrVal();
		NStrVal content = new NStrVal();
		ReturnStatus rst = new ReturnStatus();

		substr.val = sub;
		pnames.val = pnameList;
		title.val = strTit;
		content.val = strCon;

		Map result = new NewMap();
		result.put(0, -1275449303);
		result.put(1, rst.toMap());
		result.put(-891529231, substr.toMap());
		result.put(-983917352, pnames.toMap());
		result.put(110371416, title.toMap());
		result.put(951530617, content.toMap());
		chn.send(result);
	}

	private void sendSystemMailToAll(Channel channnel, int sub, String strTit,
			String strCon) throws Exception {
		if (channnel == null)
			return;

		if (strCon == null || "".equals(strCon.trim()))
			return;

		if (strTit == null)
			strTit = "";

		TcpChannel chn = getTcpByChannnel(channnel);

		NInt substr = new NInt();
		NStrVal title = new NStrVal();
		NStrVal content = new NStrVal();
		ReturnStatus rst = new ReturnStatus();

		substr.val = sub;
		title.val = strTit;
		content.val = strCon;

		Map result = new NewMap();
		result.put(0, 1649851288);
		result.put(1, rst.toMap());
		result.put(-891529231, substr.toMap());
		result.put(110371416, title.toMap());
		result.put(951530617, content.toMap());
		chn.send(result);
	}
}
