package com.d_order.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.TimerTask;

import com.d_order.model.DorderService;
import com.d_order.model.DorderVO;

public class DorderTimer extends TimerTask{
	
	
	public void run() {
		Long now=System.currentTimeMillis();
		Timestamp checkSubEnd = new Timestamp(now);
		Timestamp NewSubStart = new Timestamp(now+86400000L);
		Timestamp NewSubEnd = new Timestamp(now+1000L*60*60*24*30+86400000L);
		 
		DorderService dorderSvc =new DorderService();
		List<DorderVO> Order =dorderSvc.getAll();
		for(DorderVO OldOrder:Order) {
			Long OldOrderTime= OldOrder.getSubEnd().getTime();
			if((OldOrderTime-checkSubEnd.getTime())<=86400000L) {
			dorderSvc.addDorder(OldOrder.getDno(),OldOrder.getMno(),NewSubStart,NewSubEnd ,OldOrder.getMthFee(),OldOrder.getAutoSubs());

			}
		}  
	}
}

