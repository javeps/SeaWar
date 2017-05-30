package com.sea.channel.he;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.channel.ChnPayVerify;
import com.sea.channel.mm.JaxbReadXml;
import com.sea.handler.gate.GateConfig;
import com.sea.tools.UtileTools;

public class PayMoblieHe {

	static Log log = LogFactory.getLog(PayMoblieHe.class);

	static int svcid = 3000;

	static int code_pc_success = 3012;// 3012 PC验证码购买道具成功
	static int code_success = 1800;// 1800 计费成功
	static int code_safe_fails = 1900;// 1900
										// 包体安全性校验失败(使用移动游戏SDK接入网游业务，在自测阶段，出现此付费结果状态码是正常现象)

	public static String checkPay(String xml) {
		MoblieHePostResp result = new MoblieHePostResp();
		result.sethRet(0);
		result.setMessage("successful");

		boolean isMustInfo = true;
		try {
			MoblieHePost post = JaxbReadXml.readXmlString(MoblieHePost.class,
					xml);

			String chnBillNo = post.getContentId();
			String billNo = post.getCpparam();

			if (chnBillNo != null && !"".equals(chnBillNo.trim())
					&& billNo != null && !"".equals(billNo.trim())) {
				chnBillNo = chnBillNo.trim();
				billNo = billNo.trim();

				int hRet = post.gethRet();
				int status = post.getStatus();
				if (hRet == 0) {
					if (status == code_pc_success || status == code_success
							|| status == code_safe_fails) {
						boolean isOkey = ChnPayVerify.issuePayToGame(
								GateConfig.TGG_Payment_He, svcid, billNo, 0,
								chnBillNo, xml);
						isMustInfo = !isOkey;
					}
				}
			}
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}

		if (isMustInfo) {
			log.info(xml);
		}

		try {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response><hRet>0</hRet><message>successful</message></response>";
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		return "";
	}

	public static String loginHe(String strUri) {
		return "0";
	}
}
