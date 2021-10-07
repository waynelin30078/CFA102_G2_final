package com.food.model;

import java.util.List;

public interface FoodDAO_interface {

	
	void insert(FoodVO food);   //新增餐次飲食  會員前台
	void update(FoodVO food);	 //修改餐次飲食內容   會員前台   營養師前台
	FoodVO  findByFdNo(int fdNo);
	List<FoodVO> findByFoodName(String fdName);   //食物名稱找食物
	List<FoodVO> findByBrandName(String fdBrand);   //食物廠牌找食物
	List<FoodVO> getAll();
}
