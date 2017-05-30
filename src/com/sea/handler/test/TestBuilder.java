package com.sea.handler.test;

import static com.bowlong.netty.bio2g.B2G.DATA;
import static com.bowlong.netty.bio2g.B2G.SERVER;
import gen_b2g.serv.bean.ReturnStatus;

import com.bowlong.io.FileEx;
import com.bowlong.netty.bio2g.B2Class;
import com.bowlong.netty.bio2g.B2Field;
import com.bowlong.netty.bio2g.B2Method;
import com.toosNets.netty.map.B2Param;
import com.toosNets.netty.map.Bio2GCSharp;
import com.toosNets.netty.map.Bio2GJava;

@B2Class(namespace = "test")
public class TestBuilder {

	@B2Class(type = DATA, remark = "Str")
	class NString {
		@B2Field(remark = "内容")
		String strV;
	}

	// ================================战斗的数据 end =====================

	@B2Class(type = SERVER)
	interface TestI {
		@B2Method(type = SERVER, params = { "paramet", "reslut" }, remark = "修改名称")
		ReturnStatus test(String paramet,
				@B2Param(oType = "NString", isOut = true) NString reslut);
	}

	// ///////////////////////////////////////////////////

	public static void main(String[] args) throws Exception {
		Class<?> c = TestBuilder.class;
		boolean src = FileEx.exists("src");
		Bio2GJava.b2g(c, src);
		Bio2GCSharp.b2g(c, src);
	}

}