package com.appointment.controller;
import java.sql.Timestamp;
import java.util.List;
import java.util.TimerTask;
import com.appointment.model.*;
import com.apt_order.model.*;

import com.appointment.model.AppointmentService;
public class AppointmentTimer extends TimerTask{

	@Override
	public void run() {
		
		AppointmentService appointmentSvc =new AppointmentService();
		appointmentSvc.createDate();//呼叫新增表單
		
		
	
			
		}
		
		
		
	}
	

