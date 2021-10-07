package com.diary.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.activity_record.model.ActivityRecordVO;
import com.meal.model.MealVO;


public interface DiaryDAO_interface {

	void insert(DiaryVO diary);   //新增飲食日記  會員前台
	void update(DiaryVO diary);	 //修改飲食日記內容   會員前台   營養師前台
	void delete(DiaryVO diary);
	DiaryVO findByDate(int mNo, Date diaryDate);  //找某會員某天日記
	DiaryVO findByDiaryNo(int diaryNo);  //找某會員某天日記
	List<DiaryVO> findByMember(int mno);   //會員編號找日記
	List<DiaryVO> findByDieticianState(int dno, int viewState);   //營養師找日記狀態
	
	void updateNutrition(DiaryVO diary, MealVO meal, Connection con);   //新增餐次飲食後, 連動更新每日總營養素
	void deductNutrition(DiaryVO diary,  MealVO meal, Connection con);
	void updateActivity(DiaryVO diary, ActivityRecordVO activityRecord, Connection con);
	void deleteActivity(DiaryVO diary,  ActivityRecordVO activityRecord, Connection con);
	void deleteDiaryWithAllRecords(int diaryNo);
	
	
}
