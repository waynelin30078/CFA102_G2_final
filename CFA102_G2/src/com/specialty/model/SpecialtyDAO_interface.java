package com.specialty.model;

import java.util.List;


public interface SpecialtyDAO_interface {

	void insert(SpecialtyVO specialty); // 新增專長  管理員後台

	void update(SpecialtyVO specialty);	// 修改專長  管理員後台
	
	List<SpecialtyVO> getAll(); // 找全部專長 管理員後台, 營養師前台
/////世柏增加
	SpecialtyVO findBySpecialtyName(int specialtyNo);
/////世柏增加	
	
	
}
