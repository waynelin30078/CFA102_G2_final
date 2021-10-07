package com.c_order.model;

import java.util.List;

import com.c_order_detail.model.C_Order_DetailVO;

public interface C_OrderDAO_interface {
	void insert(C_OrderVO c_order);

	void update(C_OrderVO c_order);

	
//	void updatePayment(C_OrderVO c_order); 

	C_OrderVO getOne(Integer cOrderno);

	List<C_OrderVO> getAll();

	List<C_OrderVO> getAll(Integer mNo);
	//同時新增訂單及明細
	public void insertWithDetail(C_OrderVO c_OrderVO,List<C_Order_DetailVO> list);

}
