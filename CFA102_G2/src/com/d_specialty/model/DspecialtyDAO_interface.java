package com.d_specialty.model;

import java.util.List;


public interface DspecialtyDAO_interface {

	
	void insert(DspecialtyVO dSpecialty); // 新增專長  營養師前台
	
	void delete(DspecialtyVO dSpecialty); //刪除專長
	
	List<DspecialtyVO> getAll(); // 找全部專長 
	
	List<DspecialtyVO> findByDno(int dno); // 營養師代號找專長 
	
	//////世柏增加
	List<DspecialtyVO> findByDno1(int specialtyNo); //營養師專長找營養師
	
	
	
	/////世柏增加
}
