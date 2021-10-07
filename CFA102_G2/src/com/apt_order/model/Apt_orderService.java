package com.apt_order.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Apt_orderService {
	private Apt_orderDAO_interface dao;
	
	public Apt_orderService(){
		dao = new Apt_orderJDBCDAO();
	}
	
	//新增諮詢訂單
	public Apt_orderVO addApt_order(Integer mno, Integer dno , Timestamp orderTime , Timestamp orderDate , Integer clprice , Integer clstate , String aptreviews) {
		Apt_orderVO apt_orderVO = new Apt_orderVO();
		apt_orderVO.setMno(mno);
		apt_orderVO.setDno(dno);
		apt_orderVO.setOrderTime(orderTime);
		apt_orderVO.setOrderDate(orderDate);
		apt_orderVO.setClPrice(clprice);
		apt_orderVO.setClState(clstate);
		apt_orderVO.setAptReviews(aptreviews);
		dao.insert(apt_orderVO);
		
		return apt_orderVO;
	}
	//修改諮詢訂單
	public Apt_orderVO updateApt_order(Integer mno, Integer dno , Timestamp orderTime , Timestamp orderDate , Integer clprice , Integer clstate , String aptreviews,Integer aptorderno) {
		Apt_orderVO apt_orderVO  = new Apt_orderVO();
		apt_orderVO.setMno(mno);
		apt_orderVO.setDno(dno);
		apt_orderVO.setOrderTime(orderTime);
		apt_orderVO.setOrderDate(orderDate);
		apt_orderVO.setClPrice(clprice);
		apt_orderVO.setClState(clstate);
		apt_orderVO.setAptReviews(aptreviews);
		apt_orderVO.setAptOrderNo(aptorderno);
		dao.update(apt_orderVO);
		return apt_orderVO;
	}
	
	//查詢單一諮詢訂單
	public Apt_orderVO getOneApt_order(Integer aptorderno) {
		return dao.findByPrimaryKey(aptorderno);
	}
	//查詢全部諮詢訂單
	public List<Apt_orderVO> getAll(){
		return dao.getAll();
	}
	//用mno查詢
	public List<Apt_orderVO> findByMno(Integer mno) {
		return dao.findByMnoKey(mno);
	}
}
