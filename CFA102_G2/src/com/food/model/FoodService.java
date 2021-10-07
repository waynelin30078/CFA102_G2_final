package com.food.model;

import java.util.List;

public class FoodService {

	FoodDAO_interface dao = new FoodDAO();
	
	public FoodVO addFood(Integer mno, String fdName, Integer wtPerPortion, Double calPerWt, Double choPerWt,
			Double proPerWt, Double fatPerWt, String fdBrand, Integer fdState) {
		
		FoodVO food = new FoodVO();
		
		food.setMno(mno);
		food.setFdName(fdName);
		food.setWtPerPortion(wtPerPortion);
		food.setCalPerWt(calPerWt);
		food.setChoPerWt(choPerWt);
		food.setProPerWt(proPerWt);
		food.setFatPerWt(fatPerWt);
		food.setFdBrand(fdBrand);
		food.setFdState(fdState);
		
		dao.insert(food);
		
		return food;
	}
	
	public FoodVO updateFood(Integer fdNo, Integer mno, String fdName, Integer wtPerPortion, Double calPerWt, Double choPerWt,
			Double proPerWt, Double fatPerWt, String fdBrand, Integer fdState) {
		
		FoodVO food = new FoodVO();
		
		food.setFdNo(fdNo);
		food.setMno(mno);
		food.setFdName(fdName);
		food.setWtPerPortion(wtPerPortion);
		food.setCalPerWt(calPerWt);
		food.setChoPerWt(choPerWt);
		food.setProPerWt(proPerWt);
		food.setFatPerWt(fatPerWt);
		food.setFdBrand(fdBrand);
		food.setFdState(fdState);
		
		dao.update(food);
		
		return food;
	}
	
	public FoodVO findByFdNo(int fdNo) {
		return dao.findByFdNo(fdNo);
	}
	
	public List<FoodVO> findByFoodName(String fdName) {
		return dao.findByFoodName(fdName);
	}
	
	public List<FoodVO> findByBrandName(String fdBrand) {
		return dao.findByBrandName(fdBrand);
	}
	
	public List<FoodVO> getAll(){
		return dao.getAll();
	}
	
}
