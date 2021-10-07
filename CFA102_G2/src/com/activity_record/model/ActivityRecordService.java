package com.activity_record.model;

import java.util.List;

import com.diary.model.DiaryVO;

public class ActivityRecordService {

	ActivityRecordDAO_interface dao = new ActivityRecordDAO();

	public ActivityRecordVO addActRecord(Integer diaryNo, Integer actNo, Double actHr, Integer wt, Double calBurn) {

		ActivityRecordVO actRecord = new ActivityRecordVO();

		actRecord.setDiaryNo(diaryNo);
		actRecord.setActNo(actNo);
		actRecord.setActHr(actHr);
		actRecord.setWt(wt);
		actRecord.setCalBurn(calBurn);

		dao.insert(actRecord);

		return actRecord;
	}

	public void deleteActRecord(Integer diaryNo, Integer actNo) {

		ActivityRecordVO actRecord = new ActivityRecordVO();

		actRecord.setDiaryNo(diaryNo);
		actRecord.setActNo(actNo);

		dao.delete(actRecord);
	}

	public ActivityRecordVO findByPrimaryKey(int diaryNo, int actNo) {
		return dao.findByPrimaryKey(diaryNo, actNo);
	}

	public List<ActivityRecordVO> findByDiaryNo(int diaryNo) {
		return dao.findByDiaryNo(diaryNo);
	}

	public void addActivity(int diaryNo, ActivityRecordVO activityRecord) {
		dao.addActivity(diaryNo, activityRecord);
	}
	
	public void deleteActivity(DiaryVO diary, ActivityRecordVO activityRecord) {
		dao.deleteActivity(diary, activityRecord);
	}
	
	
}
