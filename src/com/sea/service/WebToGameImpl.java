package com.sea.service;

import gen_b2g.gamegate.CallGameGateI;
import gen_b2g.serv.bean.NInt;
import gen_b2g.serv.bean.NInts;
import gen_b2g.serv.bean.NStrVal;
import gen_b2g.serv.bean.NStrings;
import gen_b2g.serv.bean.ReturnStatus;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.netty.bio2.TcpChannel;
import com.sea.logic.logicOperate.LogicChat;
import com.sea.logic.logicOperate.LogicMail;
import com.sea.logic.logicOperate.LogicOrder;
import com.sea.logic.logicOperate.LogicPlayer;

/**
 * web 服务器端 向游戏服务器端下发数据
 * 
 * @author Administrator
 * 
 */
public class WebToGameImpl extends CallGameGateI {

	static Log log = LogFactory.getLog(WebToGameImpl.class);

	public WebToGameImpl(TcpChannel chn) {
		super(chn);
	}

	// 发送公聊到游戏
	@Override
	public void onSendPubChat(NInt substr, NStrVal content, ReturnStatus val)
			throws Exception {
		String strCont = content.val;
		LogicChat.sendSystemChatToPub("System", strCont);
	}

	// 游戏 增加用户资源 --名字
	@Override
	public void onAddGamePlayerResByNames(NStrings pnames, NStrVal resType,
			NInt resVal, ReturnStatus val) throws Exception {
		List<String> names = pnames.val;
		String strRes = resType.val;
		int intRes = resVal.val;
		if (names == null || names.isEmpty())
			return;
		for (String n : names) {
			LogicPlayer.changeResByName(n, strRes, intRes);
		}
	}

	// 游戏 增加用户资源 --pcid
	@Override
	public void onAddGamePlayerResByPcids(NInts pcids, NStrVal resType,
			NInt resVal, ReturnStatus val) throws Exception {
		List<NInt> pcidlist = pcids.intes;
		String strRes = resType.val;
		int intRes = resVal.val;
		if (pcidlist == null || pcidlist.isEmpty())
			return;
		for (NInt nt : pcidlist) {
			int pcid = nt.val;
			if (pcid == 0)
				continue;
			LogicPlayer.changeResByPcid(pcid, strRes, intRes);
		}
	}

	// 发邮件给一些人
	@Override
	public void onSendSystemMailToPlayers(NInt substr, NStrings pnames,
			NStrVal title, NStrVal content, ReturnStatus val) throws Exception {
		String strTit = title.val;
		String strCon = content.val;
		List<String> names = pnames.val;
		if (names != null && names.size() > 0) {
			for (String pname : names) {
				LogicMail.sendSystemMailToPlayer(pname, strTit, strCon);
			}
		}
	}

	// 发邮件给所有人
	@Override
	public void onSendSystemMailToAll(NInt substr, NStrVal title,
			NStrVal content, ReturnStatus val) throws Exception {
		String strTit = title.val;
		String strCon = content.val;
		LogicMail.sendSystemMailToAllPlayer(strTit, strCon);
	}

	// 充值成功后通知用户
	@Override
	public void onPaymentNotice(NStrVal billNo, NInt svcid, ReturnStatus val)
			throws Exception {
		String strBillNo = billNo.val;
		int svcidInt = svcid.val;
		LogicOrder.handleOrder(strBillNo, svcidInt);
	}
}
