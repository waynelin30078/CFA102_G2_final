package com.promotion_detail.model;

import java.util.List;
import java.util.Map;

public interface Promotion_detailDAO_interface {

	public void insert(Promotion_detailVO promotion_detailVO);

	public void update(Promotion_detailVO promotion_detailVO);

	public void delete(Integer promNo, Integer pno);

	public Promotion_detailVO findByPrimaryKey(Integer promNo, Integer pno);

	public List<Promotion_detailVO> getAll();
	
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<Promotion_detailVO> getAll(Map<String, String[]> map);	
	
    //找符合現在這個時間點檔期的產品
    public List<Promotion_detailVO> getPromotionProduct();	
    
//	public List<Promotion_detailVO> getAll_ByPrice(Integer pprice, Integer pprice1);
//	
//	public List<Promotion_detailVO> getAll_ByPromNo(Integer promNo);
//	
//	public List<Promotion_detailVO> getAll_ByPno(Integer pno);
	
}
