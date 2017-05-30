package com.sea.proto;

import static com.bowlong.netty.bio2g.B2G.SERVER;
import gen_b2g.serv.bean.NInt;
import gen_b2g.serv.bean.NInts;
import gen_b2g.serv.bean.NStrVal;
import gen_b2g.serv.bean.NStrings;
import gen_b2g.serv.bean.ReturnStatus;

import com.bowlong.io.FileEx;
import com.bowlong.netty.bio2g.B2Class;
import com.bowlong.netty.bio2g.B2Method;
import com.toosNets.netty.map.B2Param;
import com.toosNets.netty.map.Bio2GJava;

@B2Class(namespace = "gamegate")
public class GameGateBuilder {
	@B2Class(type = SERVER)
	interface GameGateI {

		// ===== web服务器端 向游戏服务器端下发数据实现 =====
		@B2Method(type = SERVER, params = { "substr", "content" }, remark = "下发公聊到游戏")
		ReturnStatus sendPubChat(
				@B2Param(oType = "NInt", isOut = true) NInt substr,
				@B2Param(oType = "NStrVal", isOut = true) NStrVal content);

		@B2Method(type = SERVER, params = { "pcids", "resType", "resVal" }, remark = "增加用户资源-pcid")
		ReturnStatus addGamePlayerResByPcids(
				@B2Param(oType = "NInts", isOut = true) NInts pcids,
				@B2Param(oType = "NStrVal", isOut = true) NStrVal resType,
				@B2Param(oType = "NInt", isOut = true) NInt resVal);

		@B2Method(type = SERVER, params = { "pnames", "resType", "resVal" }, remark = "增加用户资源-名字")
		ReturnStatus addGamePlayerResByNames(
				@B2Param(oType = "NStrings", isOut = true) NStrings pnames,
				@B2Param(oType = "NStrVal", isOut = true) NStrVal resType,
				@B2Param(oType = "NInt", isOut = true) NInt resVal);

		@B2Method(type = SERVER, params = { "substr", "title", "content" }, remark = "下发系统到游戏")
		ReturnStatus sendSystemMailToAll(
				@B2Param(oType = "NInt", isOut = true) NInt substr,
				@B2Param(oType = "NInt", isOut = true) NStrVal title,
				@B2Param(oType = "NStrVal", isOut = true) NStrVal content);

		@B2Method(type = SERVER, params = { "substr", "pnames", "title",
				"content" }, remark = "下发系统到游戏")
		ReturnStatus sendSystemMailToPlayers(
				@B2Param(oType = "NInt", isOut = true) NInt substr,
				@B2Param(oType = "NInt", isOut = true) NStrings pnames,
				@B2Param(oType = "NInt", isOut = true) NStrVal title,
				@B2Param(oType = "NStrVal", isOut = true) NStrVal content);

		@B2Method(type = SERVER, params = { "billNo", "svcid" }, remark = "充值通知到游戏")
		ReturnStatus paymentNotice(
				@B2Param(oType = "NStrVal", isOut = true) NStrVal billNo,
				@B2Param(oType = "NInt", isOut = true) NInt svcid);
	}

	// ///////////////////////////////////////////////////

	public static void main(String[] args) throws Exception {
		Class<?> c = GameGateBuilder.class;
		boolean src = FileEx.exists("src");
		Bio2GJava.b2g(c, src);
		// Bio2GCSharp.b2g(c, src);
	}

}