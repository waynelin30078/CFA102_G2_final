package com.notify.model;

import java.sql.Timestamp;
import java.util.List;

public class NotifyService {
	
	private NotifyDAO_interface dao;
	
	public NotifyService() {
		 dao = new NotifyDAO();
	}

	public void insert(Integer notiNO , Integer notiType , String notiContent , Timestamp notiTime , Integer mNo , Integer dNo) {
		NotifyVO notifyVO = new NotifyVO();
		notifyVO.setNotiNO(notiNO);
		notifyVO.setNotiType(notiType);
		notifyVO.setNotiContent(notiContent);
		notifyVO.setNotiTime(notiTime);
		notifyVO.setmNO(mNo);
		notifyVO.setdNO(dNo);
		dao.insert(notifyVO);
	}

	public void delete(Integer notiNo) {
		dao.delete(notiNo);
	}

	public void update(Integer notiNO , Integer notiType , String notiContent , Timestamp notiTime , Integer mNo , Integer dNo) {
		NotifyVO notifyVO = new NotifyVO();
		notifyVO.setNotiNO(notiNO);
		notifyVO.setNotiType(notiType);
		notifyVO.setNotiContent(notiContent);
		notifyVO.setNotiTime(notiTime);
		notifyVO.setmNO(mNo);
		notifyVO.setdNO(dNo);
		
		dao.update(notifyVO);
	}

	public NotifyVO findbyPK(Integer notiNo) {
		return dao.findbyPK(notiNo);
	}

	public List<NotifyVO> getall() {
		return dao.getall();
	}
	
	

}
