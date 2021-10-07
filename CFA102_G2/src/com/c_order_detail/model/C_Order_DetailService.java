package com.c_order_detail.model;

import java.util.List;

public class C_Order_DetailService {
	private C_Order_DetailDAO_interface dao;
	public C_Order_DetailService() {
		dao = new C_Order_DetailJDBCDAO();
	}
	public C_Order_DetailVO addCourseOrderDetail(Integer corderno,Integer cno,Integer cprice) {
		C_Order_DetailVO courseOrderDetailVO = new C_Order_DetailVO();
		courseOrderDetailVO.setCorderno(corderno);
		courseOrderDetailVO.setCno(cno);
		courseOrderDetailVO.setCprice(cprice);
		dao.insert(courseOrderDetailVO);
		return courseOrderDetailVO;
		
	}
	
	public C_Order_DetailVO updateCourseOrderDetail(Integer cevaluation ,String creviews,Integer cprogress,Integer corderno,Integer cno) {
		C_Order_DetailVO courseOrderDetailVO = new C_Order_DetailVO();
		courseOrderDetailVO.setCevaluation(cevaluation);
		courseOrderDetailVO.setCreviews(creviews);
		courseOrderDetailVO.setCprogress(cprogress);
		courseOrderDetailVO.setCorderno(corderno);
		courseOrderDetailVO.setCno(cno);
		dao.update(courseOrderDetailVO);
		return getOneCourseOrderDetail(corderno,cno);

		
	}
	public C_Order_DetailVO getOneCourseOrderDetail(Integer corderno ,Integer cno) {
		C_Order_DetailVO courseOrderDetailVO = new C_Order_DetailVO();
		courseOrderDetailVO.setCorderno(corderno);
		courseOrderDetailVO.setCno(cno);
		dao.getOne(corderno, cno);
		return courseOrderDetailVO;
		
	}
	public List<C_Order_DetailVO>getALLCourseOrderDetailByCorderno(Integer corderno){
		return dao.getAll(corderno);
	}
}
