package com.appointment.model;

import java.util.*;

public interface AppointmentDAO_interface {
	public void insert(AppointmentVO appointmentVO);
	public void update(AppointmentVO appointmentVO);
	public void delete(Integer aptNo);
	public AppointmentVO findByPrimaryKey(Integer aptNo);
	public List<AppointmentVO> getAll();
	public void createdDate();
	
	//交易  修改諮詢時間跟建立諮詢訂單
	
	
}
