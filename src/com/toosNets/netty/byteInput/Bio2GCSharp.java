package com.toosNets.netty.byteInput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.List;

import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.netty.bio2g.B2Class;
import com.bowlong.util.NewList;
import com.bowlong.util.NewMap;
import com.bowlong.util.StrBuilder;

@SuppressWarnings({ "unused" })
public class Bio2GCSharp {

	/**
	 * @param args
	 * @return
	 */
	public static String b2g(Class<?> c, boolean src) {
		B2Class B2C = c.getAnnotation(B2Class.class);
		String namespace = "";
		if (B2C != null) {
			namespace = B2C.namespace();
		}

		String p = (src ? "src/" : "") + "gen_b2g";
		if (namespace != null && !namespace.isEmpty()) {
			p = p + "/" + namespace;
		}
		File path = new File(p);
		if (!path.exists())
			path.mkdirs();

		Class<?>[] classes = c.getDeclaredClasses();
		StrBuilder sb = new StrBuilder();

		sb.pn("using System;");
		sb.pn("using System.IO;");
		sb.pn("using System.Collections;");
		sb.pn("");

		sb.pn("using Toolkit;");
		sb.pn("using UnityEngine;");
		sb.pn("");

		sb.pn("namespace ${1} {", c.getSimpleName());
		sb.pn("");
		sb.pn("public interface TcpChannel {");
		sb.pn("    void send(String method,byte[] buff,bool isAsynchronous);");
		sb.pn("}");
		sb.pn("");
		sb.pn("public class GBean {");

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

		sb.pn("");
		sb.pn("    public static NewSet CMD = NewSet.create()${1};", s);
		sb.pn("");
		sb.pn("    public static bool withIn(int tag) {");
		sb.pn("        return CMD.Contains(tag);");
		sb.pn("    }");
		sb.pn("");
		/*
		sb.pn("    public static B2Serial newInstance(int tag) {");
		sb.pn("        switch (tag) {");
		for (Class<?> class1 : classes) {
			String sname = class1.getSimpleName();
			if (B2G.isData(class1)) {
				String cname = class1.getSimpleName();
				int hcname = cname.hashCode();
				sb.pn("        case ${1}: return new ${2} ();", hcname, cname);
			}
		}
		sb.pn("        }");
		sb.pn("        return null;");
		sb.pn("    }");
		*/
		sb.pn("}");
		sb.pn("");
		for (Class<?> class1 : classes) {
			String sname = class1.getSimpleName();
			if (B2G.isData(class1)) {
				String f = class1.getSimpleName();
				if (B2G.isConstant(class1)) {
					g2beanConstant(class1, namespace, sb);
				} else {
					g2bean(class1, namespace, sb);
				}
			}
		}

		for (Class<?> class1 : classes) {
			String sname = class1.getSimpleName();
			if (B2G.isServer(class1)) {
				g2s_call(class1, namespace, sb);
			}
		}

		sb.pn("}");
		String sname = c.getSimpleName();
		writeFile(p + "/" + sname + ".cs", sb.toString());

		System.out.println(sb);
		return sb.toString();
	}

	public static void g2bean(Class<?> c, String namespace, StrBuilder sb) {
		Field[] fs = c.getDeclaredFields();
		String cname = c.getSimpleName();
		int hcname = cname.hashCode();
		// sb.pn("package gen_b2g${1}.bean;", StrEx.isEmpty(namespace) ? "" :
		// "."
		// + namespace);
		// sb.pn("");
		// sb.pn("import java.util.*;");
		// sb.pn("");
		// sb.pn("import com.bowlong.util.*;");
		sb.pn("public class ${1} : B2Serial {", cname);
		sb.pn("    public const int _CID = ${1};", hcname);
		sb.pn("    public static ${1} _G = new ${1}();", cname);
		sb.pn("");
		for (Field field : fs) {
			String t = B2G.getCsType(field);
			String s = field.getName();

			if (s.contains("$"))
				continue;

			String remark = B2G.getRemark(field);
			if (field.getType().equals(List.class)) {
				String gtype = B2G.getListType(field);
				if (gtype != null && !gtype.isEmpty()) {
					sb.pn("    public ${1} ${2}; ${3}", t, s, remark);
				}
			} else {
				if (t.contains("string")) {
					sb.pn("    public ${1} ${2} = \"\"; ${3}", t, s, remark);

				} else {
					sb.pn("    public ${1} ${2}; ${3}", t, s, remark);
				}
			}
		}

		sb.pn("");
		sb.pn("    public override void writeObject (B2Output _out) {");
//		sb.pn("        writeInt(_out, _CID);", B2G.BEAN);
		for (Field field : fs) {
			String t = B2G.getType(field);
			String gm = B2G.getMapType(t);
			String s = field.getName();
			int hs = s.hashCode();
			if (s.contains("$"))
				continue;

			String wop = B2G.getBioW(field);
			sb.pn("        ${1}(_out, ${2});", wop, s);
		}
		sb.pn("    }");
		sb.pn("");

		sb.pn("    public override void readObject(B2Input _in) {");
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

		sb.pn("    public static ${1} readInstance(byte[] _in) {", cname);
		sb.pn("        return readInstance(new B2Input(_in));");
		sb.pn("    }");
		sb.pn("");
		
		sb.pn("    public static ${1} readInstance(Stream _in) {", cname);
		sb.pn("        return readInstance(new B2Input(_in));");
		sb.pn("    }");
		sb.pn("");

		sb.pn("    public static ${1} readInstance(B2Input _in) {", cname);
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
		sb.pn("    public String ToString() {");
		sb.pn("        return ${1};", sbts);
		sb.pn("    }");
		sb.pn("");

		// sb.pn("}");

		sb.pn("}");
	}

	public static void g2beanConstant(Class<?> c, String namespace,
			StrBuilder sb) {
		Field[] fs = c.getDeclaredFields();
		String cname = c.getSimpleName();
		sb.pn("public class ${1} {", cname);
		for (Field field : fs) {
			String t = B2G.getCsType(field);
			String s = field.getName();

			if (s.contains("$"))
				continue;

			String remark = B2G.getRemark(field);
			String def = B2G.getDef(field);
			if (field.getType().equals(List.class)) {
				String gtype = B2G.getListType(field);
				if (gtype != null && !gtype.isEmpty()) {
					continue;
				}
			} else {
				if (t.contains("string")) {
					sb.pn("    public const ${1} ${2} = \"${4}\"; ${3}", t, s,
							remark, def);

				} else {
					sb.pn("    public const ${1} ${2} = ${4}; ${3}", t, s,
							remark, def);
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
		sb.pn("public abstract class Call${1} : B2Serial {", cname);

		sb.pn("");
		sb.pn("    public static int __uid;");
		sb.pn("");
		sb.pn("    public TcpChannel chn;");
		sb.pn("    public Call${1}(TcpChannel chn) {", sname);
		sb.pn("        this.chn = chn;");
		sb.pn("    }");
		sb.pn("    public abstract MemoryStream getOutStream();");
		sb.pn("    public abstract void freeOutStream(MemoryStream baos);");
		sb.pn("");
		for (Method m : methods) {
			if (!B2G.isServer(m))
				continue;

			String remark = B2G.getRemark(m);
			String srtype = B2G.getReturnType(m);
			String mname = B2G.getNethodName(m);
			boolean isAsynchronous=B2G.getIsAsynchronous(m);
			int hmname = mname.hashCode();
			NewList<NewMap<String, String>> params = B2G.getParameters(m);
			StrBuilder sb1 = new StrBuilder();
			for (NewMap<String, String> m1 : params) {
				String mykey = (String) (m1.getKey().equals("boolean") ? "bool"
						: m1.getKey());
				mykey = (String) (mykey.equals("List") ? "ArrayList" : mykey);
				mykey = (String) (mykey.equals("Map") ? "Hashtable" : mykey);
				String myvar = (String) m1.getValue();
				boolean isOut = B2G.isOut(m, myvar);
				if (isOut) {

				} else {
					sb1.ap("${1} ${2}, ", mykey, myvar);
				}
			}
			if (sb1.length() > 2) {
				sb1.removeRight(2);
			}

			// 需要实现的逻辑函数
			sb.pn("    // ${1}", remark);
			sb.pn("    public void ${1}(${2}) {", mname, sb1);
			sb.pn("        MemoryStream stream = getOutStream ();");
//			sb.pn("        stream.Position = 0;");
			sb.pn("        B2Output output  = new B2Output (stream);");
			sb.pn("        writeInt(output, __uid);  // uid");
			sb.pn("        writeInt(output, ${1});  // cmd:${2}", hmname, mname);
//			sb.pn("        Debug.Log($[1]);  // cmd:${2}", mname, hmname);
			for (NewMap<String, String> m1 : params) {
				String key = (String) m1.getKey();
				String val = (String) m1.getValue();
				String p = B2G.getMapType(key);
				String biow = B2G.getBioW(key);
				boolean isOut = B2G.isOut(m, val);
				if (isOut) {

				} else {
					if (biow.equals(key)) {
						sb.pn("        ${1}.writeObject(output);", val);
					} else
						sb.pn("        ${1}(output, ${2});", biow, val);
				}
			}
//			sb.pn("        int _dataLength = (int)stream.Position;");
//			sb.pn("        stream.Position = 0;");
//			sb.pn("        byte[] _buffer = new byte[_dataLength];");
//			sb.pn("        stream.Read(_buffer, 0, _dataLength);");
//			
//			if(isAsynchronous){
//				sb.pn("        chn.send(_buffer,${1});","true");
//			}else{
//				sb.pn("        chn.send(_buffer,${1});","false");
//			}
			if (isAsynchronous) {
				sb.pn("        chn.send($[1],stream.ToArray(), ${2});",mname, "true");
			} else {
				sb.pn("        chn.send($[1],stream.ToArray(), ${2});",mname, "false");
			}
			sb.pn("        freeOutStream(stream);");
			//sb.pn("        byte[] buff = new byte[stream.Position];");
			//sb.pn("        stream.Read(buff, 0, buff.Length);");
			//sb.pn("        chn.send(buff);");
			sb.pn("    }");
			sb.pn("");
		}

		StrBuilder sb2 = new StrBuilder();
		for (Method m : methods) {
			String rtype = B2G.getReturnType(m);
			if (B2G.isServer(m) && rtype.equals("void"))
				continue;

			String mname = B2G.getNethodName(m);
			int hmname = mname.hashCode();
			sb2.ap(".Add(${1})", hmname);
		}
		String s = sb2.toString();

		sb.pn("");
		sb.pn("    public static NewSet CMD = NewSet.create()${1};", s);
		sb.pn("");
		sb.pn("    public static bool withIn(Hashtable map) {");
		sb.pn("        int cmd = MapEx.getInt(map, ${1});", B2G.METHOD);
		sb.pn("        return CMD.Contains(cmd);");
		sb.pn("    }");
		sb.pn("");

		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("    // 逻辑分发");
		sb.pn("    // //////////////////////////////////////////////");
		sb.pn("");
		sb.pn("    public void disp(int cmd, B2Input _in) {");
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
				sb.pn("                __onCall_${1}(map);", mname);
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
			int hmname = mname.hashCode();
			NewList<NewMap<String, String>> params = B2G.getParameters(m);

			// 解析参数函数
			if (B2G.isServer(m)) {
				if (!srtype.equals("void")) {
					sb.pn("    // ${1}", remark);
					sb.pn("    private void __onCallback_${1}(B2Input _in) {",
							mname);
					String mx = B2G.getCsMapType(srtype);
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
					sb.pn("        on${1}(${2}rst);", upper1(mname), msb);
					sb.pn("    }");
				}
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

					sb.pn("    public abstract void on${1}(${2}${3} val);",
							upper1(mname), msb, srtype);
				}

			}
			sb.pn("");
		}
		sb.pn("    }");
		//sb.pn("}");
	}

	public static String upper1(String s) {
		if (s == null || s.isEmpty())
			return s;
		int len = s.length();
		return s.substring(0, 1).toUpperCase() + s.substring(1, len);
	}

	public static void writeFile(String f, String str) {
		try {
			File fn = new File(f);
			FileOutputStream out = new FileOutputStream(fn);
			OutputStreamWriter osw = new OutputStreamWriter(out,
					Charset.forName("UTF8"));
			osw.write(str, 0, str.length());
			osw.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}