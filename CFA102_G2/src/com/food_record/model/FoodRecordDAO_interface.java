package com.food_record.model;

import java.sql.Connection;
import java.util.List;

import com.food.model.FoodVO;

public interface FoodRecordDAO_interface {
	
	void insert(FoodRecordVO foodRecord);   //新增食物紀錄 會員前台
	void update(FoodRecordVO foodRecord);	//修改食物紀錄   會員前台   
	void deleteWithMeal(int mealNo, Connection con);	//刪除食物紀錄   會員前台   
	
	void insertWithMeal(FoodRecordVO foodRecord, Connection con);
	
	List<FoodRecordVO> findByMealNo(int mealNo);
}
