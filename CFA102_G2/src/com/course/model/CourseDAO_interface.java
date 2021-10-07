package com.course.model;

import java.util.List;

public interface CourseDAO_interface {
	void insert(CourseVO courseVO);//新增 營養師 v
	void update(CourseVO courseVO);//更新 營養師
	void cState(Integer cState, Integer cno);//變更狀態 營養師 後台
	CourseVO getOneBy_cNO(Integer cno); //依照課程編號查詢 後台 v
	List<CourseVO> getBy_dNo(Integer dno); //依照講師編號查詢 會員 訪客  v
	List<CourseVO> getBy_cName(String cName); //依照課程名稱查詢	  會員 訪客		x							
	List<CourseVO> getBy_cType(Integer cType);//依照類別 訪客 會員 v
	List<CourseVO> getBy_cTotalScore(Integer score);//依照評價  會員 訪客	
	List<CourseVO> getAll();//
	byte[] getImg(Integer cno);
	public void updateQuantity(CourseVO courseVO);//購買後增加數量
}
