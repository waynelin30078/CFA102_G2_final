/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_P_order {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;
		
		String porderDateColumnName = null;
		String porderDateValue = null;
		String porderDate1ColumnName = null;
		String porderDate1Value = null;
		
		String porderTotalColumnName = null;
		String porderTotalValue = null;
		String porderTotal1ColumnName = null;
		String porderTotal1Value = null;
		
		
		switch(columnName) {
		case "porderDate":
			porderDateColumnName = columnName;
			porderDateValue = value;
			break;
		case "porderDate1":
			porderDate1ColumnName = columnName;
			porderDate1Value = value;
			break;
		case "porderTotal":
			porderTotalColumnName = columnName;
			porderTotalValue = value;
			break;	
		case "porderTotal1":
			porderTotal1ColumnName = columnName;
			porderTotal1Value = value;
			break;				
		}
		
		if (porderDateColumnName != null) {
			porderDateValue.substring(0, 10);
		}
		else if(porderDate1ColumnName != null) {
			porderDate1Value.substring(0, 10);
		}
		
		if ("porderNo".equals(columnName) || "mno".equals(columnName) || "porderState".equals(columnName)) { // 用於其他
			aCondition = columnName + "=" + value;
		}
		//日期
		else if (porderDateColumnName != null && porderDate1ColumnName != null) {
			aCondition = porderDateColumnName + "between " + "'"+ porderDateValue+ "'"+ " and " + "'"+ porderDate1Value + "'";
		}
		else if (porderDateColumnName == null && porderDate1ColumnName == null) {
			aCondition = "";
		}
		else if (porderDateColumnName != null && porderDate1ColumnName == null) {
			aCondition = porderDateColumnName + " >= "+ "'"+ porderDateValue + "'";
		}
		else if (porderDateColumnName == null && porderDate1ColumnName != null) {
			aCondition = "porderDate" + " <= "+ "'"+ porderDate1Value + "'";
		}
		//價格
		else if (porderTotalColumnName != null && porderTotal1ColumnName != null) {
			aCondition = porderTotalColumnName + "between" + porderTotalValue + "and" + porderTotal1Value;
		}
		else if (porderTotalColumnName == null && porderTotal1ColumnName == null) {
			aCondition = "";
		}
		else if (porderTotalColumnName != null && porderTotal1ColumnName == null) {
			aCondition = porderTotalColumnName + ">=" + porderTotalValue;
		}
		else if (porderTotalColumnName == null && porderTotal1ColumnName != null) {
			aCondition = "porderTotal" + "<=" + porderTotal1Value;
		}			
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
}
