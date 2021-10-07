package com.course.model;

import java.util.Date;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;

public class CourseDAOtest {
	
	public static void main(String[] args) {
		
		

		Integer cNo = 1;
		Integer dNo = 3;// 講師編號
		String cName = "sl";// 課程名稱
		Integer cPrice = 3000;
		Integer cState = 3;
		Timestamp cShelfDate = ((java.sql.Timestamp.valueOf("9999-12-31 23:59:59")));// 上架日期
		String cIntroduction = "sl";// 課程介紹
		Integer cType = 1;
		Integer quantity = 1;// 購買人數
	
		byte[] cPic = null;
		try {
			cPic = getPictureByteArray("C:\\Users\\Tibame_T14\\Desktop\\tJwGtpC.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 預覽圖cDescrpition cDescription
		String cDescription = "睡好睡滿精神好";// 預覽介紹
		Integer cTotalPeople = 0;
		Integer cTotalScore = 0;

		// 新增測試ok
		CourseVO course = new CourseVO(cNo, dNo, cName, cPrice, cState, cShelfDate, cIntroduction, cType, quantity,
				cPic, cDescription, cTotalPeople, cTotalScore);
		CourseDAO_interface dao = new CourseJDBCDAO();
		dao.insert(course);
		//更新測試
//    dao.cState(2,3);
//		dao.update(course);
		// 單一查詢結果測試ok
//		CourseVO course= dao.findBy_cNO(cNo);
//		System.out.println(course);
		// List查詢測試
		List<CourseVO> courseList = dao.getBy_cName(cName);
		for(CourseVO c:courseList)
			System.out.println(c);
	}
public static byte[] getPictureByteArray(String path) throws IOException{
	FileInputStream fis =new FileInputStream(path);
	byte[] buffer = new byte[fis.available()];
	fis.read(buffer);
	fis.close();
	return buffer;
	
} 
}
