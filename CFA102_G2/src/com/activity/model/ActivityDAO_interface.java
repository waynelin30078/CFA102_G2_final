package com.activity.model;

import java.util.List;

public interface ActivityDAO_interface {

	void insert(ActivityVO activity);   //新增運動  後台
	void update(ActivityVO activity);	//修改運動 後台   
	ActivityVO findById(int actNo);
	List<ActivityVO> getAll();

}
