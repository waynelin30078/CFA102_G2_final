package com.apt_order.controller;



import java.util.Timer;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

public class Apt_orderListener implements ServletContextListener{
	Timer timer;
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();
	}
	public void contextInitialized(ServletContextEvent sec) {
		timer=new Timer();
		timer.schedule(new Apt_orderTimer(), 1000L,300000L);
	}
}
//18000000L 半小時
