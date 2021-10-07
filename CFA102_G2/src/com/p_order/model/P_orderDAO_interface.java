package com.p_order.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.p_order_detail.model.P_order_detailVO;

public interface P_orderDAO_interface {

	public void insert(P_orderVO p_orderVO);

	public void update(P_orderVO p_orderVO);
	
	public void updateOrderState(P_orderVO p_orderVO);

	public void delete(Integer pOrderNo);

	public P_orderVO findByPrimaryKey(Integer pOrderNo);

	public List<P_orderVO> getAll();
	
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<P_orderVO> getAll(Map<String, String[]> map);	
	
    //查詢某訂單的訂單明細(一對多)(回傳 Set)
    public Set<P_order_detailVO> getPorderDetailByPorderNo(Integer pOrderNo);
    
    //同時新增訂單與訂單明細
    public void insertWithPorderDetail(P_orderVO p_orderVO , List<P_order_detailVO> list);
	
	public List<P_orderVO> getAll_ByMno(Integer mNo);
	
//	public List<P_orderVO> getAll_ByOrderState(Integer pOrderState);
	
	

}
