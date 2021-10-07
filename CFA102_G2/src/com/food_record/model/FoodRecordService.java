package com.food_record.model;

import java.util.List;

public class FoodRecordService {
	
	FoodRecordDAO_interface dao = new FoodRecordDAO();
	
	public FoodRecordVO addFoodRecord(Integer mealNo, Integer fdNo, Integer fdPortion, Integer fdWt, Double singlelCal,
			Double singleCho, Double singlePro, Double singleFat) {
		
		FoodRecordVO foodRecord = new FoodRecordVO();
		
		foodRecord.setMealNo(mealNo);
		foodRecord.setFdNo(fdNo);
		foodRecord.setFdPortion(fdPortion);
		foodRecord.setFdWt(fdWt);
		foodRecord.setSinglelCal(singlelCal);
		foodRecord.setSingleCho(singleCho);
		foodRecord.setSinglePro(singlePro);
		foodRecord.setSingleFat(singleFat);
		
		dao.insert(foodRecord);
		
		return foodRecord;
	}
	
	public FoodRecordVO updateFoodRecord(Integer mealNo, Integer fdNo, Integer fdPortion, Integer fdWt, Double singlelCal,
			Double singleCho, Double singlePro, Double singleFat) {
		
		FoodRecordVO foodRecord = new FoodRecordVO();
		
		foodRecord.setMealNo(mealNo);
		foodRecord.setFdNo(fdNo);
		foodRecord.setFdPortion(fdPortion);
		foodRecord.setFdWt(fdWt);
		foodRecord.setSinglelCal(singlelCal);
		foodRecord.setSingleCho(singleCho);
		foodRecord.setSinglePro(singlePro);
		foodRecord.setSingleFat(singleFat);
		
		dao.update(foodRecord);
		
		return foodRecord;
	}
	
	public List<FoodRecordVO> findByMealNo(int mealNo){
		return dao.findByMealNo(mealNo);

	}
	
	
//	public void deleteFoodRecord(Integer mealNo, Integer fdNo) {
//		
//		FoodRecordVO foodRecord = new FoodRecordVO();
//		
//		foodRecord.setMealNo(mealNo);
//		foodRecord.setFdNo(fdNo);
//		
//		dao.delete(foodRecord);
//	}
	
	
}
