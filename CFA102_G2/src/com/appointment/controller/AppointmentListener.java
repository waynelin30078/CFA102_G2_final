package com.appointment.controller;



import java.util.Timer;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;


public class AppointmentListener implements ServletContextListener{
	Timer timer;
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();
	}
	public void contextInitialized(ServletContextEvent sec) {
		
		timer=new Timer();
		timer.schedule(new AppointmentTimer(), 1000L,86400000L);
	}
	

}
