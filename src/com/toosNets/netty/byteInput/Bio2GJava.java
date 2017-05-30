package com.toosNets.netty.byteInput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.netty.bio2g.B2Class;
import com.bowlong.netty.bio2g.B2Field;
import com.bowlong.util.NewList;
import com.bowlong.util.NewMap;
import com.bowlong.util.StrBuilder;

@SuppressWarnings({ "unused" })
public class Bio2GJava {
	public static void b2g(Class<?> c, boolean src) throws Exception {
		B2Class B2C = c.getAnnotation(B2Class.class);
		String namespace = "";
		if (B2C != null) {
			namespace = B2C.namespace();
		}
		Class<?>[] classes = c.getDeclaredClasses();
		String p = (src ? "src/" : "") + "gen_b2g";
		if (namespace != null && !namespace.isEmpty()) {
			p = p + "/" + namespace;
		}

		{
			StrBuilder sb2 = new StrBuilder();

			for (Class<?> class1 : classes) {
				String sname = class1.getSimpleName();
				if (B2G.isData(class1)) {
					String cname = class1.getSimpleName();
					int hcname = cname.hashCode();
					sb2.ap(".Add(${1})", hcname);
				}
			}

			String s = sb2.toString();
			StrBuilder sb = new StrBuilder();
			sb.pn("package gen_b2g${1}.bean;", StrEx.isEmpty(namespace) ? ""
					: "." + namespace);

			sb.pn("import com.bowlong.util.*;");
			sb.pn("import com.bowlong.bio2.serial.*;");

			sb.pn("@SuppressWarnings(\"unchecked\")");
			sb.pn("");
			sb.pn("public class GBean {");
			sb.pn("    public static final NewSet<Integer> CMD = NewSet.create()${1};",
					s);
			sb.pn("");
			sb.pn("    public static boolean withIn(int tag) {");
			sb.pn("        return CMD.contains(tag);");
			sb.pn("    }");
			sb.pn("");
			/*
			 * sb.pn("    public static B2Serial newInstance(int tag) {");
			 * sb.pn("        switch (tag) {"); for (Class<?> class1 : classes)
			 * { String sname = class1.getSimpleName(); if (B2G.isData(class1))
			 * { String cname = class1.getSimpleName(); int hcname =
			 * cname.hashCode(); sb.pn("        case ${1}: return new ${2} ();",
			 * hcname, cname); } } sb.pn("        }");
			 * sb.pn("        return null;"); sb.pn("    }"); sb.pn("}");
			 * sb.pn("");
			 * 
			 * writeFile(p + "/bean/GBean.java", sb.toString());
			 */
		}

		for (Class<?> class1 : classes) {
			String sname = class1.getSimpleName();
			if (B2G.isServer(class1)) {
				File path = new File(p);
				if (!path.exists())
					path.mkdirs();

				{
					StrBuilder sb = new StrBuilder();
					g2s_service(class1, namespace, sb);
					writeFile(p + "/" + sname + ".java", sb.toString());
					System.out.println(sb);
				}
				{
					StrBuilder sb2 = new StrBuilder();
					g2s_call(class1, namespace, sb2);
					writeFile(p + "/" + "Call" + sname + ".java",
							sb2.toString());
					System.out.println(sb2);
				}
			} else if (B2G.isData(class1)) {
				String p2 = p + "/bean";
				File path = new File(p2);
				if (!path.exists())
					path.mkdirs();

				String f = class1.getSimpleName();
				StrBuilder sb = new StrBuilder();
				if (B2G.isConstant(class1)) {
					g2beanConstant(class1, namespace, sb);
				} else {
					g2bean(class1, namespace, sb);
				}
				writeFile(p2 + "/" + f + ".java", sb.toString());
				System.out.println(sb);
			}
		}
	}

	public static void g2bean(Class<?> c, String namespace, StrBuilder sb) {
		Field[] fs = c.getDeclaredFields();
		String cname = c.getSimpleName();
		int hcname = cname.hashCode();
		sb.pn("package gen_b2g${1}.bean;", StrEx.isEmpty(namespace) ? "" : "."
				+ namespace);
		sb.pn("");
		sb.pn("import java.io.*;");
		sb.pn("import java.util.*;");
		sb.pn("import java.lang.*;");
		sb.pn("");
		sb.pn("import com.bowlong.util.*;");
		sb.pn("import com.bowlong.bio2.serial.*;");
		sb.pn("");
		sb.pn("@SuppressWarnings({ \"rawtypes\", \"unchecked\", \"serial\", \"unused\" })");
		sb.pn("");
		sb.pn("public class ${1} extends B2Serial {", cname);
		sb.pn("    public static final int _CID = ${1};", hcname);
		sb.pn("    public static final ${1} _G = new ${1}();", cname);
		sb.pn("    public static final int ESTIMATE_SIZE = ${1};",
				estimateSize(fs));
		sb.pn("");
		for (Field field : fs) {
			B2Field a = field.getAnnotation(B2Field.class);
			String t = B2G.getType(field);
			String s = field.getName();
			if (s.contains("$"))
				continue;

			String remark = B2G.getRemark(field);
			if (field.getType().equals(List.class)) {
				String gtype = B2G.getListType(field);
				if (gtype != null && !gtype.isEmpty()) {
					sb.pn("    public ${1}<${4}> ${2} = new NewList(); ${3}",
							t, s, remark, gtype);
				}
			} else {
				if (t.contains("String")) {
					sb.pn("    public ${1} ${2} = \"\"; ${3}", t, s, remark);
				} else if (t.contains("Map")) {
					sb.pn("    public ${1} ${2} = new NewMap(); ${3}", t, s,
							remark);
				} else {
					sb.pn("    public ${1} ${2}; ${3}", t, s, remark);
				}
			}
		}

		// ///////
		sb.pn("");
		sb.pn("    @Override");
		sb.pn("    public void writeObject(B2Output _out) throws Exception {");
		// sb.pn("        writeInt(_out, _CID);");
		for (Field field : fs) {
			String s = field.getName();
			String t = B2G.getType(field);
			if (s.contains("$"))
				continue;

			String wop = B2G.getBioW(field);
			if (wop.equals(t)) {
				sb.pn("        ${1}.writeObject(_out);", s);
			} else
				sb.pn("        ${1}(_out, ${2});", wop, s);

			// sb.pn("        ${1}(_out, ${2});", wop, s);
		}
		sb.pn("    }");
		sb.pn("");

		sb.pn("");
		sb.pn("    @Override");
		sb.pn("    public void readObject(B2Input _in) throws Exception {");
		for (Field field : fs) {
			String s = field.getName();
			if (s.contains("$"))
				continue;

			String rop = B2G.getBioR(field);
			if (rop.equals("readList")) {
				String gv = B2G.getGval(B2G.getListType(field));
				sb.pn("        this.${1} = ${2}(_in, ${3});", s, rop, gv);
			} else {
				sb.pn("        this.${1} = ${2}(_in);", s, rop);
			}
		}
		sb.pn("    }");
		sb.pn("");

		sb.pn("    public static ${1} readInstance(byte[] _in) throws Exception {",cname);
		sb.pn("        return readInstance(new B2Input(_in));");
		sb.pn("    }");
		sb.pn("");

		sb.pn("    public static ${1} readInstance(InputStream _in) throws Exception {",cname);
		sb.pn("        return readInstance(new B2Input(_in));");
		sb.pn("    }");
		sb.pn("");

		sb.pn("    public static ${1} readInstance(B2Input _in) throws Exception {",
				cname);
		sb.pn("        ${1} r2 = new ${1}();", cname);
		sb.pn("        r2.readObject(_in);");
		sb.pn("        return r2;");
		sb.pn("    }");
		sb.pn("");

		StrBuilder sbts = new StrBuilder();
		sbts.a("\"").a(cname).a("[");
		for (Field field : fs) {
			String t = B2G.getType(field);
			String gm = B2G.getMapType(t);
			String s = field.getName();
			if (s.contains("$"))
				continue;
			sbts.a("").a(s).a("=\" + ").a(s).a(" + \", ");
		}
		sbts.removeRight(2);
		sbts.a("]\"");
		sb.pn("    public String toString() {");
		sb.pn("        return ${1};", sbts);
		sb.pn("    }");
		sb.pn("");

		sb.pn("}");
	}

	public static void g2beanConstant(Class<?> c, String namespace,
			StrBuilder sb) {
		Field[] fs = c.getDeclaredFields();
		String cname = c.getSimpleName();
		sb.pn("package gen_b2g${1}.bean;", StrEx.isEmpty(namespace) ? "" : "."
				+ namespace);
		sb.pn("");
		sb.pn("public class ${1} {", cname);
		sb.pn("");
		for (Field field : fs) {
			B2Field a = field.getAnnotation(B2Field.class);
			String t = B2G.getType(field);
			String s = field.getName();
			if (s.contains("$"))
				continue;

			String remark = B2G.getRemark(field);
			String def = B2G.getDef(field);
			if ("".equals(def)) {
				continue;
			}
			if (field.getType().equals(List.class)) {
				continue;
			} else {
				if (t.contains("String")) {
					sb.pn("    public static final ${1} ${2} = \"${4}\"; ${3}",
							t, s, remark, def);

				} else {
					sb.pn("    public static final ${1} ${2} = ${4}; ${3}", t,
							s, remark, def);
				}
			}
		}
		sb.pn("}");
	}

	// 生成服务器接口
	public static void g2s_service(Class<?> c, String namespace, StrBuilder sb) {
		Method[] methods = c.getMethods();
		String cname = c.getSimpleName();
		sb.pn("package gen_b2g${1};", StrEx.isEmpty(namespace) ? "" : "."
				+ namespace);
		// sb.pn("package gen_b2g;");
		sb.pn("");
		sb.pn("import java.io.*;");
		sb.pn("import java.util.*;");
		sb.pn("");
		sb.pn("import com.bowlong.util.*;");
		sb.pn("import com.bowlong.netty.bio2.*;");
		sb.pn("import com.bowlong.bio2.serial.*;");
		sb.pn("import org.apache.commons.logging.*;");
		sb.pn("");
		sb.pn("import gen_b2g${1}.bean.*;", StrEx.isEmpty(namespace) ? "" : "."
				+ namespace);
		sb.pn("");
		sb.pn("@SuppressWarnings({ \"rawtypes\", \"unchecked\", \"unused\", \"serial\" })");
		sb.pn("public abstract class ${1} extends B2Serial {", cname);
		sb.pn("    static Log log=LogFactory.getLog(${1}.class);",cname);
		sb.pn("");

		sb.pn("@Override");
		sb.pn("public void writeObject(B2Output output) throws Exception {");
		sb.pn("}");

		sb.pn("@Override");
		sb.pn("public void readObject(B2Input input) throws Exception {");
		sb.pn("}");

		sb.pn("    public abstract TcpChannel chn(int XID) throws Exception;");
		sb.pn("    public abstract ByteArrayOutputStream getOutStream();");
		sb.pn("    public abstract void freeOutStream(ByteArrayOutputStream baos);");
		sb.pn("");
		for (Method m : methods) { // 向客户端主动发送
			if (!B2G.isClient(m))
				continue;
			String remark = B2G.getRemark(m);
			// String oType = B2G.getOType(m);
			String srtype = B2G.getReturnType(m);
			String mname = B2G.getNethodName(m);
			int hmname = mname.hashCode();
			NewList<NewMap<String, String>> params = B2G.getParameters(m);

			StrBuilder sb1 = new StrBuilder();
			StrBuilder sb2 = new StrBuilder();
			for (NewMap<String, String> m1 : params) {
				String key = (String) m1.getKey();
				String val = (String) m1.getValue();
				// StrBuilder sb0 = new StrBuilder();
				if (B2G.getMapType(key).equals("getList")) {
					key = PStr.str("${1}<${2}>", key, B2G.getOType(m, val));
					// sb0.ap("${1}<${2}>", key, B2G.getOType(m, val));
					// key = sb0.toString();
				}
				sb1.ap(", ${1} ${2}", key, val);
				sb2.ap(", ${1}", val);
			}

			sb.pn("    // //////////////////////////////////////////////");
			sb.pn("    // 逻辑调用");
			sb.pn("    // //////////////////////////////////////////////");
			sb.pn("");
			sb.pn("    // ${1}", remark);
			sb.pn("    public void ${1}(int XID ${2}) throws Exception {",
					mname, sb1);
			sb.pn("        TcpChannel chn = chn(XID);");
			sb.pn("        ${1}(chn${2});", mname, sb2);
			sb.pn("    }");
			sb.pn("    public void ${1}(int XID, List<Integer> xids2 ${2}) throws Exception {",
					mname, sb1);
			sb.pn("        TcpChannel chn = chn(XID);");
			sb.pn("        List<TcpChannel> chn2 = new NewList<TcpChannel>();");
			sb.pn("        if (xids2 != null) {");
			sb.pn("        	for (Integer _xid2 : xids2) {");
			sb.pn("        		TcpChannel _chn = chn(XID);");
			sb.pn("        		if(_chn != null) chn2.add(_chn);");
			sb.pn("        	}");
			sb.pn("        }");
			sb.pn("        ${1}(chn,chn2${2});", mname, sb2);
			sb.pn("    }");
			sb.pn("    public void ${1}(TcpChannel chn ${2}) throws Exception {",
					mname, sb1);
			sb.pn("        ${1}(chn, null ${2});", mname, sb2);
			sb.pn("    }");
			sb.pn("    // ${1}", remark);
			sb.pn("    public void ${1}(TcpChannel chn, List<TcpChannel> chn2 ${2}) throws Exception {",
					mname, sb1);
			sb.pn("        if(chn == null) return;");
			sb.pn("        NewMap _params = new NewMap();");
			sb.pn("        _params.put(${1}, ${2}); // cmd:${3}", B2G.METHOD,
					hmname, mname);
			int i = 0;
			for (NewMap<String, String> m1 : params) {
				String key = (String) m1.getKey();
				String val = (String) m1.getValue();
				int hval = val.hashCode();
				if (B2G.getMapType(key).equals("getObject")) {
					sb.pn("        _params.put(${1}, ${2}.toMap());", hval, val);
				} else {
					if (B2G.getMapType(key).equals("getList")) {
						sb.pn("		{");
						sb.pn("			// Lsit对象(${1})", val);
						sb.pn("			List _list = new NewList();");
						String oType = B2G.getOType(m, val);
						String mType = B2G.getMapType(oType);
						sb.pn("			for (${1} object : ${2}) {", oType, val);
						if (mType.equals("getObject")) {
							sb.pn("            _list.add(object.toMap());");
						} else {
							sb.pn("            _list.add(object);");
						}
						sb.pn("			}");
						sb.pn("			_params.put(${1}, _list);", hval);
						sb.pn("		}");
					} else {
						sb.pn("        _params.put(${1}, ${2});", hval, val);
					}
				}
				i++;
			}
			// sb.pn("        chn.send(_params);");
			sb.pn("        byte[] buff = B2Helper.toBytes(_params);");
			sb.pn("        chn.send(buff);");
			sb.pn("        if( chn2 != null) {");
			sb.pn("            for(TcpChannel _chn : chn2) ");
			sb.pn("               _chn.send(buff);");
			sb.pn("        }");
			sb.pn("    }");
			sb.pn("");
		}

		StrBuilder sb2 = new StrBuilder();
		for (Method m : methods) {
			String rtype = B2G.getReturnType(m);
			if (B2G.isClient(m) && rtype.equals("void"))
				continue;
			String mname = B2G.getNethodName(m);
			int hmname = mname.hashCode();
			sb2.ap(".Add(${1})", hmname);
		}
		String s = sb2.toString();

		// sb.pn("");
		sb.pn("    public static final Set<Integer> CMD = NewSet.create()${1};",
				s);
		sb.pn("");
		sb.pn("    public static boolean in(int cmd) throws Exception {");
		sb.pn("        return CMD.contains(cmd);");
		sb.pn("    }");
		sb.pn("");

		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("    // 逻辑分发");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("");
		sb.pn("    public String disp(TcpChannel chn, int cmd, B2Input _in) throws Exception {");
		sb.pn("        if(chn == null) return \"\";");
		sb.pn("        switch (cmd) {");
		for (Method m : methods) {
			String remark = B2G.getRemark(m);
			String srtype = B2G.getReturnType(m);
			String mname = B2G.getNethodName(m);
			int hmname = mname.hashCode();
			if (B2G.isServer(m)) {
				sb.pn("            case ${1}: { //  ${2}", hmname, remark);
//				sb.pn("                long begin=System.currentTimeMillis();");
				sb.pn("                __${1}(chn, _in);", mname);
//				sb.pn("                log.debug( \"${1} \"+(System.currentTimeMillis()-begin)+\"ms\");", mname);
				sb.pn("                return \"${1}\";", mname);
				sb.pn("            }");
			}
		}
		sb.pn("        }");
		sb.pn("        throw new Exception(\" cmd: \" + cmd + \" not found processor.\");");
		sb.pn("    }");
		sb.pn("");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("    // 解析参数");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("");
		for (Method m : methods) {
			String remark = B2G.getRemark(m);
			// String oType = B2G.getOType(m);
			String srtype = B2G.getReturnType(m);
			String mname = B2G.getNethodName(m);
			String hmname = mname.hashCode() + "";
			NewList<NewMap<String, String>> params = B2G.getParameters(m);

			StrBuilder sb1 = new StrBuilder();

			// 解析参数函数
			sb.pn("    // ${1}", remark);
			if (B2G.isServer(m)) {
				sb.pn("    private void __${1}(TcpChannel chn, B2Input _in) throws Exception {",
						mname);
				sb.pn("        if(chn == null) return;");
				String parame="";
				boolean needAdd=false;
				for (NewMap<String, String> m1 : params) {
					String key = (String) m1.getKey();
					String val = (String) m1.getValue();
					String bior = B2G.getBioR(key);
					boolean isOut = B2G.isOut(m, val);
					if (isOut) {
						sb.pn("        ${1} ${2} = new ${1}();", key, val);
					} else {
						sb.pn("        ${1} ${2} = ${3}(_in);", key, val, bior);
						if(needAdd){
							parame+=("+");
						}
						needAdd=true;
						parame+= String.format("\"%s=\"+%s+\":\"",val,val);
						
					}
					sb1.ap(", ${1}", val);
				}
				
//				if(needAdd){
					//sb.ap(parame.toString());
//				}
				if(parame.equals("")){
					parame="\"Exception\"";
				}
				sb.pn("");
				
				if (srtype.equals("void")) {
					sb.pn("        try{");
					sb.pn("        on${1}(chn${2});", up1(mname), sb1);
					sb.pn("        }catch(Exception e){");
					sb.pn("            throw new RuntimeException(${1},e);",parame);
					sb.pn("        }");
				} else {
					sb.pn("        ${1} rst = new ${1}();", srtype);
					sb.pn("");
					sb.pn("        try{");
					sb.pn("        on${1}(chn${2}, rst);", up1(mname),sb1);
					sb.pn("        }catch(Exception e){");
					sb.pn("            coc.base.AbstractChannelContext context = (coc.base.AbstractChannelContext) chn;");
					
					sb.pn("            throw new RuntimeException(${1},e);","\"uid=\"+context.uid+\":\"+"+parame);
					sb.pn("        }");
					sb.pn("");
					sb.pn("        ByteArrayOutputStream baos = getOutStream();");
					sb.pn("        B2Output output = new B2Output(baos);");
					sb.pn("        writeInt(output, ${1});", hmname);
					sb.pn("        rst.writeObject(output);");
					
					for (NewMap<String, String> m1 : params) {
						String key = (String) m1.getKey();
						String val = (String) m1.getValue();
						String bior = B2G.getBioR(key);
						boolean isOut = B2G.isOut(m, val);
						if (isOut) {
							sb.pn("        ${1}.writeObject(output);", val);
						}
						sb1.ap(", ${1}", val);
					}

					sb.pn("        chn.send(baos.toByteArray());");
					sb.pn("        freeOutStream(baos);");
					sb.pn("        if(rst.succ==-1)");
					sb.pn("            log.debug(rst.msg);");
				}
				sb.pn("    }");

				if (B2G.isServer(m)) {
					if (!srtype.equals("void")) {
						StrBuilder msb = new StrBuilder();
						for (NewMap<String, String> m1 : params) {
							String key = (String) m1.getKey();
							String val = (String) m1.getValue();
							String hval = val.hashCode() + "";
							String p = B2G.getMapType(key);
							boolean isOut = B2G.isOut(m, val);
							if (isOut) {
								if (p.equals("getObject")) {
									msb.ap(", ${1} ${2}", key, val);
								}
							}
						}

						// sb.pn("    public void on${1}(${2}${3} val) throws Exception {};",
						// upper1(mname), msb, srtype);
						// sb.pn("    public void snd${1}(TcpChannel chn${2}) {",
						// upper1(mname), msb);
						// if (srtype.equals("void")) {
						// sb.pn("        on${1}(chn${2});", upper1(mname),
						// sb1);
						// } else {
						// sb.pn("        ${1} rst = new ${1}();", srtype);
						// sb.pn("        rst = on${1}(chn${2}, rst);",
						// upper1(mname),
						// sb1);
						// sb.pn("        Map result = new NewMap();");
						// sb.pn("        result.put(${1}, ${2});", B2G.METHOD,
						// hmname);
						// sb.pn("        result.put(${1}, rst.toMap());",
						// B2G.RETURN_STAT);
						// for (NewMap<String, String> m1 : params) {
						// String key = (String) m1.getKey();
						// String val = (String) m1.getValue();
						// String hval = val.hashCode() + "";
						// String p = B2G.getMapType(key);
						// boolean isOut = B2G.isOut(m, val);
						// if (isOut) {
						// sb.pn("        result.put(${1}, ${2}.toMap());",
						// hval, val);
						// }
						// }
						//
						// sb.pn("        chn.send(result);");
						//
						// sb.pn("    }");
					}
					sb.pn("");
				}
			}
		}

		sb.pn("");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("    // 需要实现的接口");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("");
		for (Method m : methods) {
			String remark = B2G.getRemark(m);
			// String oType = B2G.getOType(m);
			String srtype = B2G.getReturnType(m);
			String mname = B2G.getNethodName(m);
			NewList<NewMap<String, String>> params = B2G.getParameters(m);

			StrBuilder sb1 = new StrBuilder();
			for (NewMap<String, String> m1 : params) {
				String key = (String) m1.getKey();
				String val = (String) m1.getValue();
				String p = B2G.getMapType(key);
				if (p.equals("getList") && !"".equals(B2G.getOType(m, val))) {
					key = PStr.str("${1}<${2}>", key, B2G.getOType(m, val));
				}
				sb1.ap(", ${1} ${2}", key, val);
			}

			// 需要实现的逻辑函数
			if (B2G.isServer(m)) {
				sb.pn("    // ${1}", remark);
				if (!srtype.equals("void")) {
					sb.pn("    public abstract ${1} on${2}(TcpChannel chn ${3}, ReturnStatus ret) throws Exception;",
							srtype, up1(mname), sb1);
				} else {
					sb.pn("    public abstract ${1} on${2}(TcpChannel chn ${3}) throws Exception;",
							srtype, up1(mname), sb1);
				}
			} else {
				if (!srtype.equals("void")) {
					sb.pn("    // ${1}", remark);
					sb.pn("    public void on${1}(TcpChannel chn, ${2} val) throws Exception { };",
							up1(mname), srtype);
				}
			}
		}
		sb.pn("}");
	}

	// 生成客户端接口
	public static void g2s_call(Class<?> c, String namespace, StrBuilder sb) {
		String sname = c.getSimpleName();
		Method[] methods = c.getMethods();
		String cname = c.getSimpleName();
		sb.pn("package gen_b2g${1};", StrEx.isEmpty(namespace) ? "" : "."
				+ namespace);
		// sb.pn("package gen_b2g;");
		sb.pn("");
		sb.pn("import java.io.*;");
		sb.pn("import java.util.*;");
		sb.pn("");
		sb.pn("import com.bowlong.util.*;");
		sb.pn("import com.bowlong.netty.bio2.*;");
		sb.pn("import com.bowlong.bio2.serial.*;");

		sb.pn("");
		sb.pn("import gen_b2g${1}.bean.*;", StrEx.isEmpty(namespace) ? "" : "."
				+ namespace);
		sb.pn("");
		sb.pn("@SuppressWarnings({ \"rawtypes\", \"unchecked\", \"serial\" })");
		sb.pn("public abstract class Call${1} extends B2Serial {", cname);

		sb.pn("");
		sb.pn("    public static int __uid;");
		sb.pn("");
		sb.pn("    public TcpChannel chn;");
		sb.pn("    public Call${1}(TcpChannel chn) {", sname);
		sb.pn("        this.chn = chn;");
		sb.pn("    }");
		sb.pn("    public abstract ByteArrayOutputStream getOutStream();");
		sb.pn("    public abstract void freeOutStream(ByteArrayOutputStream baos);");

		sb.pn("");
		for (Method m : methods) {
			if (!B2G.isServer(m))
				continue;

			String remark = B2G.getRemark(m);
			// String oType = B2G.getOType(m);
			String srtype = B2G.getReturnType(m);
			String mname = B2G.getNethodName(m);
			String hhmname = mname.hashCode() + "";
			NewList<NewMap<String, String>> params = B2G.getParameters(m);

			StrBuilder sb1 = new StrBuilder();
			for (NewMap<String, String> m1 : params) {
				String mykey = (String) m1.getKey();
				String myvar = (String) m1.getValue();
				String hval = myvar.hashCode() + "";
				boolean isOut = B2G.isOut(m, myvar);
				if (isOut) {

				} else {
					if (mykey.equals("List")
							&& !"".equals(B2G.getOType(m, myvar))) {
						mykey = PStr.str("${1}<${2}>", mykey,
								B2G.getOType(m, myvar));
					}
					sb1.ap("${1} ${2}, ", mykey, myvar);
				}
			}
			if (sb1.length() > 2) {
				sb1.removeRight(2);
			}

			// 需要实现的逻辑函数
			sb.pn("    // ${1}", remark);
			sb.pn("    public void ${1}(${2}) throws Exception {", mname, sb1);

			sb.pn("        ByteArrayOutputStream baos = getOutStream();");
			sb.pn("        B2Output output = new B2Output(baos);");
			sb.pn("        writeInt(output, __uid);  // uid");
			sb.pn("        writeInt(output, ${1});  // cmd:${2}", hhmname,
					mname);
			for (NewMap<String, String> m1 : params) {
				String key = (String) m1.getKey();
				String val = (String) m1.getValue();
				String p = B2G.getMapType(key);
				boolean isOut = B2G.isOut(m, val);
				String biow = B2G.getBioW(key);
				if (isOut) {

				} else {
					if (biow.equals(key)) {
						sb.pn("        ${1}.writeObject(output);", val);
					} else
						sb.pn("        ${1}(output, ${2});", biow, val);
				}
			}
			sb.pn("        chn.send(baos.toByteArray());");
			sb.pn("        freeOutStream(baos);");
			sb.pn("    }");
			sb.pn("");
		}

		StrBuilder sb2 = new StrBuilder();
		for (Method m : methods) {
			String rtype = B2G.getReturnType(m);
			if (B2G.isServer(m) && rtype.equals("void"))
				continue;
			String mname = B2G.getNethodName(m);
			String hmane = mname.hashCode() + "";
			sb2.ap(".Add(${1})", hmane);
		}
		String s = sb2.toString();

		sb.pn("");
		sb.pn("    public static final Set<Integer> CMD = NewSet.create()${1};",
				s);
		sb.pn("");
		sb.pn("    public static boolean in(Map map) throws Exception {");
		sb.pn("        int cmd = MapEx.getInt(map, ${1});", B2G.METHOD);
		sb.pn("        return CMD.contains(cmd);");
		sb.pn("    }");
		sb.pn("");

		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("    // 逻辑分发");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("");
		sb.pn("    public void disp(int cmd, B2Input _in) throws Exception {");
		sb.pn("        switch (cmd) {");
		for (Method m : methods) {
			String remark = B2G.getRemark(m);
			String srtype = B2G.getReturnType(m);
			String mname = B2G.getNethodName(m);
			int hmname = mname.hashCode();
			if (B2G.isServer(m)) {
				if (!srtype.equals("void")) {
					sb.pn("            case ${1}: { //  ${2}", hmname, remark);
					sb.pn("                __onCallback_${1}(_in);", mname);
					sb.pn("                return;");
					sb.pn("            }");
				}
			} else {
				sb.pn("            case ${1}: { //  ${2}", hmname, remark);
				sb.pn("                __onCall_${1}(_in);", mname);
				sb.pn("                return;");
				sb.pn("            }");
			}
		}
		sb.pn("        }");
		sb.pn("        throw new Exception(\" cmd: \" + cmd + \" not found processor.\");");
		sb.pn("    }");
		sb.pn("");
		sb.pn("");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("    // 参数解析");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("");
		for (Method m : methods) {
			String remark = B2G.getRemark(m);
			// String oType = B2G.getOType(m);
			String srtype = B2G.getReturnType(m);
			String mname = B2G.getNethodName(m);
			String hmname = mname.hashCode() + "";
			NewList<NewMap<String, String>> params = B2G.getParameters(m);

			// 解析参数函数
			if (B2G.isServer(m)) {
				if (!srtype.equals("void")) {
					sb.pn("    // ${1}", remark);
					sb.pn("    private void __onCallback_${1}(B2Input _in) throws Exception {",
							mname);
					String mx = B2G.getMapType(srtype);
					//sb.pn("        _in.readInt();");
					sb.pn("        ReturnStatus rst = ReturnStatus.readInstance(_in);");

					StrBuilder msb = new StrBuilder();
					for (NewMap<String, String> m1 : params) {
						String key = (String) m1.getKey();
						String val = (String) m1.getValue();
						String hval = val.hashCode() + "";
						String p = B2G.getMapType(key);
						boolean isOut = B2G.isOut(m, val);
						if (isOut) {
							if (p.equals("getObject")) {
								sb.pn("        ${1} ${2} = ${1}.readInstance(_in);",
										key, val);
								msb.ap("${1}, ", val);
							}
						}
					}

					sb.pn("");
					sb.pn("        on${1}(${2}rst);", up1(mname), msb);
					sb.pn("    }");
				}
			} else {
				sb.pn("    // ${1}", remark);
				sb.pn("    private void __onCall_${1}(B2Input _in) throws Exception {",
						mname);
				sb.pn("        NewMap map2 = NewMap.create(map);");
				sb.pn("");
				StrBuilder sb1 = new StrBuilder();
				for (NewMap<String, String> m1 : params) {
					String key = (String) m1.getKey();
					String val = (String) m1.getValue();
					String hval = val.hashCode() + "";
					String p = B2G.getMapType(key);
					boolean isOut = B2G.isOut(m, val);

					if (p.equals("getObject")) {
						sb.pn("        ${1} ${2} = ${1}.parse(map2.getNewMap(${3}));",
								key, val, hval);
					} else {
						sb.pn("        ${1} ${2} = map2.${3}(${4});", key, val,
								p, hval);
						if (p.equals("getList")) {
							String oType = B2G.getOType(m, val);
							String mType = B2G.getMapType(oType);
							sb.pn("		List<${1}> ${2}_list = new NewList<${3}>();",
									oType, val, oType);
							sb.pn("		{");
							sb.pn("			// Lsit对象(${1})", val);
							sb.pn("			for(Object obj : ${1}) {", val);
							if (mType.equals("getObject"))
								sb.pn("				${1}_list.add(${2}.parse((Map)obj));",
										val, oType);
							else
								sb.pn("				${1}_list.add((${2})obj);", val,
										oType);

							sb.pn("			}");
							sb.pn("		}");
							val += "_list";
						}
					}
					sb1.ap("${1}, ", val);
				}
				if (sb1.length() > 2)
					sb1.removeRight(2);

				sb.pn("");
				if (srtype.equals("void")) {
					sb.pn("        on${1}(${2});", up1(mname), sb1);
				} else {

					sb.pn("        ReturnStatus rst = on${1}(${2});",
							up1(mname), sb1, srtype);

					sb.pn("        Map result = new NewMap();");
					sb.pn("        result.put(${1}, ${2});", B2G.METHOD, hmname);
					sb.pn("        result.put(${1}, rst.toMap());",
							B2G.RETURN_STAT);
					sb.pn("        chn.send(result);");
				}
				sb.pn("    }");
			}
			sb.pn("");
		}

		sb.pn("");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("    // 需要实现的接口");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("");
		for (Method m : methods) {
			String remark = B2G.getRemark(m);
			// String oType = B2G.getOType(m);
			String srtype = B2G.getReturnType(m);
			String mname = B2G.getNethodName(m);
			NewList<NewMap<String, String>> params = B2G.getParameters(m);
			// 解析参数函数
			sb.pn("    // ${1}", remark);
			if (B2G.isServer(m)) {
				if (!srtype.equals("void")) {
					StrBuilder msb = new StrBuilder();
					for (NewMap<String, String> m1 : params) {
						String key = (String) m1.getKey();
						String val = (String) m1.getValue();
						String hval = val.hashCode() + "";
						String p = B2G.getMapType(key);
						boolean isOut = B2G.isOut(m, val);
						if (isOut) {
							if (p.equals("getObject")) {
								msb.ap("${1} ${2}, ", key, val);
							}
						}
					}

					sb.pn("    public void on${1}(${2}${3} val) throws Exception {};",
							up1(mname), msb, srtype);
				}
			} else {

				StrBuilder sb1 = new StrBuilder();
				for (NewMap<String, String> m1 : params) {
					String key = (String) m1.getKey();
					String val = (String) m1.getValue();
					if (B2G.getMapType(key).equals("getList")) {
						key = PStr.str("${1}<${2}>", key, B2G.getOType(m, val));
					}
					sb1.ap("${1} ${2}, ", key, val);
				}
				if (sb1.length() > 2) {
					sb1.removeRight(2);
				}

				// 需要实现的逻辑函数
				sb.pn("    public abstract ${1} on${2}(${3}) throws Exception;",
						srtype, up1(mname), sb1);

			}
			sb.pn("");
		}
		sb.pn("}");
	}

	public static void writeFile(String f, String str) {
		try {
			File fn = new File(f);
			FileOutputStream out = new FileOutputStream(fn);
			OutputStreamWriter osw = new OutputStreamWriter(out,
					Charset.forName("GBK"));
			osw.write(str, 0, str.length());
			osw.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String up1(String s) {
		if (s == null || s.isEmpty())
			return s;
		int len = s.length();
		return s.substring(0, 1).toUpperCase() + s.substring(1, len);
	}

	public static final int estimateSize(Field[] fs) {
		int r2 = 8;
		for (Field f : fs) {
			if (f.getType().equals(Boolean.class)) {
				r2 += 1;
			} else if (f.getType().equals(Byte.class)) {
				r2 += 2;
			} else if (f.getType().equals(Short.class)) {
				r2 += 3;
			} else if (f.getType().equals(Integer.class)) {
				r2 += 5;
			} else if (f.getType().equals(Long.class)) {
				r2 += 9;
			} else if (f.getType().equals(Float.class)) {
				r2 += 5;
			} else if (f.getType().equals(Double.class)) {
				r2 += 9;
			} else if (f.getType().equals(Date.class)) {
				r2 += 9;
			} else if (f.getType().equals(String.class)) {
				r2 += 65;
			} else {
				r2 += 65;
			}
		}

		return r2;
	}
}