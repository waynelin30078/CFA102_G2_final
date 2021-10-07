package com.activity.model;

import java.util.List;

public class ActivityService {

	ActivityDAO_interface dao = new ActivityDAO();

	public ActivityVO addActivity(String actName, Double calPerKgHr, Integer actState) {

		ActivityVO activity = new ActivityVO();

		activity.setActName(actName);
		activity.setCalPerKgHr(calPerKgHr);
		activity.setActState(actState);

		dao.insert(activity);

		return activity;
	}

	public ActivityVO updateActivity(Integer actNo, String actName, Double calPerKgHr, Integer actState) {

		ActivityVO activity = new ActivityVO();

		activity.setActNo(actNo);
		activity.setActName(actName);
		activity.setCalPerKgHr(calPerKgHr);
		activity.setActState(actState);

		dao.update(activity);

		return activity;
	}

	
	public ActivityVO findById(int actNo) {
		return dao.findById(actNo);
	}
	
	public List<ActivityVO> getAll() {
		return dao.getAll();
	}
	
	
	
}
