package com.d_order.controller;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DorderListener implements ServletContextListener{
	Timer timer;
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();
	}
	
	
	public void contextInitialized(ServletContextEvent sce) {
		timer=new Timer();
		timer.schedule(new DorderTimer(), 1000L, 86400000L);//1000延遲啟動時間,每60000執行一次
	}
}
	
