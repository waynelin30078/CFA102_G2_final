package com.p_order.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.p_order_detail.model.P_order_detailVO;

public class P_orderService {

	private P_orderDAO_interface dao;

	public P_orderService() {
		dao = new P_orderJDBCDAO();
	}

	public P_orderVO addP_order(Integer porderNo, Integer mno, Timestamp porderDate, Integer porderTotal,
			String porderName, String porderPhone, String porderAddress, Integer ppayment, String pcreditCard,
			String pcreditCardCVV, Integer pshipping, Integer porderState) {

		P_orderVO p_orderVO = new P_orderVO();

		p_orderVO.setPorderNo(porderNo);
		p_orderVO.setMno(mno);
		p_orderVO.setPorderDate(porderDate);
		p_orderVO.setPorderTotal(porderTotal);
		p_orderVO.setPorderName(porderName);
		p_orderVO.setPorderPhone(porderPhone);
		p_orderVO.setPorderAddress(porderAddress);
		p_orderVO.setPpayment(ppayment);
		p_orderVO.setPcreditCard(pcreditCardCVV);
		p_orderVO.setPcreditCardCVV(pcreditCardCVV);
		p_orderVO.setPshipping(pshipping);
		p_orderVO.setPorderState(porderState);
		dao.insert(p_orderVO);

		return p_orderVO;

	}

	public P_orderVO updateP_order(Integer porderNo, Integer mno, Timestamp porderDate, Integer porderTotal, String porderName,
			String porderPhone, String porderAddress, Integer ppayment, String pcreditCard, String pcreditCardCVV,
			Integer pshipping, Integer porderState) {

		P_orderVO p_orderVO = new P_orderVO();

		p_orderVO.setPorderNo(porderNo);
		p_orderVO.setMno(mno);
		p_orderVO.setPorderDate(porderDate);
		p_orderVO.setPorderTotal(porderTotal);
		p_orderVO.setPorderName(porderName);
		p_orderVO.setPorderPhone(porderPhone);
		p_orderVO.setPorderAddress(porderAddress);
		p_orderVO.setPpayment(ppayment);
		p_orderVO.setPcreditCard(pcreditCardCVV);
		p_orderVO.setPcreditCardCVV(pcreditCardCVV);
		p_orderVO.setPshipping(pshipping);
		p_orderVO.setPorderState(porderState);
		dao.update(p_orderVO);

		return p_orderVO;
	}

	public P_orderVO updateOrderState(Integer porderNo, Integer porderState) {

		P_orderVO p_orderVO = new P_orderVO();

		p_orderVO.setPorderNo(porderNo);
		p_orderVO.setPorderState(porderState);
		dao.update(p_orderVO);

		return p_orderVO;
	}

	public void deleteP_order(Integer porderNo) {
		dao.delete(porderNo);
	}

	public P_orderVO getOneP_order(Integer porderNo) {
		return dao.findByPrimaryKey(porderNo);
	}

	public List<P_orderVO> getAll() {
		return dao.getAll();
	}
	
	public List<P_orderVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public void insertWithPorderDetail(P_orderVO p_orderVO, List<P_order_detailVO> list) {
		dao.insertWithPorderDetail(p_orderVO, list);
	}

	public List<P_orderVO> getAll_ByMno(Integer mNo) {
		return dao.getAll_ByMno(mNo);
	}
	
	public Set<P_order_detailVO> getPorderDetailByPorderNo(Integer porderNo) {
		return dao.getPorderDetailByPorderNo(porderNo);
	}	
//
//	public List<P_orderVO> getAll_ByOrderState(Integer pOrderState) {
//		return dao.getAll_ByOrderState(pOrderState);
//	}
}
