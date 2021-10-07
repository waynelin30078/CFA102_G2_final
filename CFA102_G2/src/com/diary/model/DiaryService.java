package com.diary.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.activity_record.model.ActivityRecordVO;
import com.meal.model.MealVO;

public class DiaryService {

	private DiaryDAO_interface dao = new DiaryDAO();
	
	
	public DiaryVO addDiary(DiaryVO diary) {
		
		dao.insert(diary);
		
		return diary;
	}
	
	public DiaryVO updateDiary(DiaryVO diary) {
				
		dao.update(diary);
		
		return diary;
	}
	
	public void deleteDiary(Integer diaryNo) {
		
		DiaryVO diary = new DiaryVO();
		
		diary.setDiaryNo(diaryNo);
		
		dao.delete(diary);
	}
	
	public DiaryVO findByDate(int mno, Date diaryDate) {
		
		return dao.findByDate(mno, diaryDate);
		
	}
	
	public DiaryVO findByDiaryNo(int diaryNo) {
		return dao.findByDiaryNo(diaryNo);
	}
	

	public List<DiaryVO> findByMember(int mno) {
		return dao.findByMember(mno);
	}
	
	public List<DiaryVO> findByDieticianState(int dno, int viewState) {
		return dao.findByDieticianState(dno, viewState);
	}
	
	public void updateNutrition(DiaryVO diary, MealVO meal, Connection con) {
		dao.updateNutrition(diary, meal, con);
	}
	
	public void deductNutrition(DiaryVO diary, MealVO meal, Connection con) {
		dao.deductNutrition(diary, meal, con);
	}
	
	public void updateActivity(DiaryVO diary, ActivityRecordVO activityRecord, Connection con) {
		dao.updateActivity(diary, activityRecord, con);
	}
	
	public void deleteActivity(DiaryVO diary,  ActivityRecordVO activityRecord, Connection con) {
		dao.deleteActivity(diary, activityRecord, con);
	}
	
	public void deleteDiaryWithAllRecords(int diaryNo) {
		dao.deleteDiaryWithAllRecords(diaryNo);
	}
	
	
	
}
