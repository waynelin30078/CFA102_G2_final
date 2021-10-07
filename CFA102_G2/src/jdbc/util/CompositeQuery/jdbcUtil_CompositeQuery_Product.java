/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Product {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;
		String ppriceValue = null;
		String ppriceColumnName = null;
		String pprice1Value = null;
		String pprice1ColumnName = null;

		switch(columnName) {
		case "pprice":
			ppriceColumnName = columnName;
			ppriceValue = value;
			break;
		case "pprice1":
			pprice1ColumnName = columnName;
			pprice1Value = value;
			break;		
		}		
		
		
		if ("pno".equals(columnName) || "pstate".equals(columnName)) { // 用於其他
			aCondition = columnName + "=" + value;
		}
		else if ("pname".equals(columnName)) { // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		}
		else if ("categoryName".equals(columnName)) { 
			aCondition = columnName + "=" + "'"+ value +"'";
		}
		else if (ppriceColumnName != null && pprice1ColumnName != null) {
			aCondition = ppriceColumnName + "between" + ppriceValue + "and" + pprice1Value;
		}
		else if (ppriceColumnName == null && pprice1ColumnName == null) {
			aCondition = "";
		}
		else if (ppriceColumnName != null && pprice1ColumnName == null) {
			aCondition = ppriceColumnName + ">=" + ppriceValue;
		}
		else if (ppriceColumnName == null && pprice1ColumnName != null) {
			aCondition = "pprice" + "<=" + pprice1Value;
		}	
		
//		else if ("".equals(columnName))                          // 用於date
//			aCondition = columnName + "=" + "'"+ value +"'";                          //for 其它DB  的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		return whereCondition.toString();
	}

//	public static void main(String argv[]) {
//
//		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("pno", new String[] { "7001" });
//		map.put("categoryName", new String[] { "魚油" });
//		map.put("pname", new String[] { "好立善純淨深海鮭魚油(120粒)" });
//		map.put("pprice", new String[] { "1000" });
//		map.put("pstate", new String[] { "15000.5" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
//
//		String finalSQL = "select * from product "
//				          + jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
//				          + "order by pno";
//		System.out.println("●●finalSQL = " + finalSQL);
//
//	}
}
