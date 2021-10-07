package com.c_order_detail.model;

import java.util.List;

public interface C_Order_DetailDAO_interface {
	void insert(C_Order_DetailVO cOrderDetail ) ;
	
	void update(C_Order_DetailVO cOrderDetail);
	void delete(C_Order_DetailVO cOrderDetail);
	C_Order_DetailVO getOne(Integer corderno,Integer cNo);
	List<C_Order_DetailVO> getAll(Integer corderno);
	void insert(C_Order_DetailVO cOrderDetail ,java.sql.Connection con) ;
}
