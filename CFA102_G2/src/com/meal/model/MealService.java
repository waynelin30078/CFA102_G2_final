package com.meal.model;

import java.util.List;

import com.food_record.model.FoodRecordVO;

public class MealService {

	MealDAO_interface dao = new MealDAO();

	public MealVO addMeal(MealVO meal) {

		dao.insert(meal);
		return meal;
	}

	public MealVO updateMeal(Integer mealNo, Integer diaryNo, String mealName, Double mealCal, Double mealCho,
			Double mealPro, Double mealFat) {

		MealVO meal = new MealVO();

		meal.setMealNo(mealNo);
		meal.setDiaryNo(diaryNo);
		meal.setMealName(mealName);
		meal.setMealCal(mealCal);
		meal.setMealCho(mealCho);
		meal.setMealPro(mealPro);
		meal.setMealFat(mealFat);

		dao.update(meal);
		return meal;
	}

	public MealVO insertMeal(MealVO meal, List<FoodRecordVO> foodRecordList) {
		dao.insertMeal(meal, foodRecordList);
		
		return meal;
	}
	
	public void deleteMeal(int mealNo) {
		dao.delete(mealNo);
	}
	
	public List<MealVO> findByDiaryNo(int diaryNo) {

		return dao.findByDiaryNo(diaryNo);
	}

	public MealVO findByMealNo(int mealNo) {

		return dao.findByMealNo(mealNo);
	}
	
}
