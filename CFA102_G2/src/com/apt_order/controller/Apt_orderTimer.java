package com.apt_order.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import com.apt_order.model.Apt_orderService;
import com.apt_order.model.Apt_orderVO;
import com.member.model.*;

public class Apt_orderTimer extends TimerTask{

	@Override
	public void run() {

		Apt_orderService apt_orderSvc = new Apt_orderService();
		Long now = System.currentTimeMillis();
		MemberService memberSvc = new MemberService();
		LocalDate nowDate =LocalDate.now();

		Date date2 = Date.from(nowDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<Apt_orderVO>list=apt_orderSvc.getAll()
							  .stream()
							  .filter(f ->f.getOrderTime().after(date2))
							  .collect(Collectors.toList());
		
		
		for(Apt_orderVO one:list) {
			Long seconds=one.getOrderTime().getTime();

			Long aaa=seconds-now;
			Integer dno=one.getDno();
			Integer mno=one.getMno();
			
			if(seconds<now && now<seconds+3600000L) {
				memberSvc.reset_dieticianPk(mno, dno);
			}
			if(seconds+3600000L<now) {
				
				memberSvc.reset_dieticianPKNull(mno);

			}
			
		}
	}

	}
