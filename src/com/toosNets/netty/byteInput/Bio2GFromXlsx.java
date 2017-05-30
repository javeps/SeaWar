package com.toosNets.netty.byteInput;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bowlong.util.MapEx;
import com.bowlong.util.StrBuilder;
import com.bowlong.third.xss.XSS;

public class Bio2GFromXlsx {

	static String path = "/Users/zhangrenzhi/Documents/workspace.java/coc/documents/";

	public static void main(String[] args) throws Exception {

		StrBuilder sb = new StrBuilder();

		{
			String f = path + "Clash of Clans 3.25 Revision.xlsx";
			
			sb.pn("import org.apache.poi.xssf.usermodel.XSSFSheet;");
			sb.pn("import com.bowlong.xss.XSS;");
			sb.pn("");
			
			sb.pn("public final class ClashOfClans {");
			proto(f, sb);
			sb.pn("}");
		}

		System.out.println(sb);

		System.exit(1);
	}

	private static void proto(String xlsx, StrBuilder sb) throws Exception {
		File fp = new File(xlsx);
		if (!fp.exists()) {
			System.out.println("fp not exists");
			return;
		}

		OPCPackage pkg = OPCPackage.open(fp);
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		int sheetNum = wb.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			XSSFSheet sheet = wb.getSheetAt(i);
			if (sheet == null)
				break;

			protoSheet(sheet, sb);
		}
	}

	private static void protoSheet(XSSFSheet sheet, StrBuilder sb)
			throws Exception {

		String sheetName = sheet.getSheetName();
		StrBuilder sb2 = new StrBuilder();
		List<Map<String, String>> headers = XSS.readHeaders(sheet);
		List<String> indexs = XSS.readIndexs(sheet);
		System.out.println(indexs);
		for (Map<String, String> map : headers) {
			String sName = MapEx.getKey(map);
			String sType = MapEx.getValue(map);

			sb2.pn("        public ${1} ${2};", sType, sName);
		}

		if (sb2.len() > 0) {
			int estimateSize = XSS.estimateSize(sheet);
			sb.pn("    public static class ${1} {", sheetName);
			sb.pn("        static final int BASE_ROW = 2;");
			sb.pn("        public static final int ESTIMATE_SIZE = ${1};", estimateSize);
			sb.pn("");
			
			sb.a(sb2.toString());

			sb.pn("");
			
			sb.pn("        public static final ${1}[] readFrom(final XSSFWorkbook wb) {", sheetName);
			sb.pn("            final XSSFSheet sheet = wb.getSheet(\"${1}\");", sheetName);
			sb.pn("            return readFrom(sheet);");
			sb.pn("        }");
			sb.pn("");
			
			List<List<Map<String, Object>>> datas = XSS.readData(sheet, headers);
			sb.pn("        public static final ${1}[] readFrom(final XSSFSheet sheet) {", sheetName);
			sb.pn("            final ${1}[] r2 = new ${1}[${2}];", sheetName, datas.size());
			sb.pn("            for(int row = 0; row < ${1}; row ++) {", datas.size());
			sb.pn("                r2[row] = readFrom(sheet, BASE_ROW + row);", sheetName);
			sb.pn("            }");
			sb.pn("            return r2;");
			sb.pn("        }");
			sb.pn("");
			sb.pn("        public static final ${1} readFrom(final XSSFSheet sheet, final int row) {", sheetName);
			sb.pn("            final ${1} r2 = new ${1}();", sheetName);
			sb.pn("");
			int colunm = 0;
			for (Map<String, String> map : headers) {
				String sName = XSS.getKey(map);
				String sType = XSS.getValue(map);
				
				if (sType.equals("int")) {
					sb.pn("            r2.${1} = XSS.getInt(sheet, row, ${2});", sName, colunm);
				} else if (sType.equals("double")) {
					sb.pn("            r2.${1} = XSS.getDouble(sheet, row, ${2});", sName, colunm);
				} else if (sType.equals("long")) {
					sb.pn("            r2.${1} = XSS.getLong(sheet, row, ${2});", sName, colunm);
				} else if (sType.equals("boolean")) {
					sb.pn("            r2.${1} = XSS.getBool(sheet, row, ${2});", sName, colunm);
				} else if (sType.equals("String")) {
					sb.pn("            r2.${1} = XSS.getString(sheet, row, ${2});", sName, colunm);
				}

				colunm++;
			}
			sb.pn("");
			sb.pn("            return r2;");
			sb.pn("        }");
			
			sb.pn("        public final String toString() {");
			sb.a("            return ");
			for (Map<String, String> map : headers) {
				String sName = MapEx.getKey(map);
				
				sb.ap("\"${1}=\"+${1}+\",\"+", sName);
			}
			sb.removeRight(5);
			sb.pn(";");
			sb.pn("        }");
			
			sb.pn("    }");
		}

	}

}