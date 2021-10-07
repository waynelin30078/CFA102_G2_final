package com.c_order.model;

import java.util.List;

import com.c_order_detail.model.C_Order_DetailVO;

public class C_OrderService {
	private C_OrderDAO_interface dao;

	public C_OrderService() {
		dao = new C_OrderJDBCDAO();
	}

	public C_OrderVO addCourseOrder(Integer mno, Integer total, Integer paymentMethod, String paymentInfo) {
		C_OrderVO courseOrderVO = new C_OrderVO();
		courseOrderVO.setMno(mno);
		courseOrderVO.setTotal(total);
		courseOrderVO.setPaymentMethod(paymentMethod);
		courseOrderVO.setPaymentInfo(paymentInfo);
		dao.insert(courseOrderVO);
		return courseOrderVO;
	}

	public C_OrderVO updateCourseOrder(Integer total, Integer paymentMethod, String paymentInfo, Integer corderstate,
			Integer courseOrderno) {
		C_OrderVO courseOrderVO = new C_OrderVO();

		courseOrderVO.setTotal(total);
		courseOrderVO.setPaymentMethod(paymentMethod);
		courseOrderVO.setPaymentInfo(paymentInfo);
		courseOrderVO.setCorderno(corderstate);
		courseOrderVO.setCorderno(courseOrderno);
		dao.update(courseOrderVO);
		return getOneCourseOrder(courseOrderno);
	}

	public C_OrderVO getOneCourseOrder(Integer courseOrderno) {
		return dao.getOne(courseOrderno);
	}

	public List<C_OrderVO> getALLCourseOrder() {
		return dao.getAll();
	}

	public List<C_OrderVO> getALLCourseOrderByMno(Integer mno) {
		return dao.getAll(mno);
	}

	public C_OrderVO insertWithDetail(C_OrderVO c_OrderVO, List<C_Order_DetailVO> list) {
		dao.insertWithDetail(c_OrderVO, list);
		return c_OrderVO;
	}
}
