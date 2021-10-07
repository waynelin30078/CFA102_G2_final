package com.promotion.model;

import java.util.List;
import java.util.Map;

public interface PromotionDAO_interface {

	public void insert(PromotionVO promotionVO);

	public void update(PromotionVO promotionVO);

	public void delete(Integer promNo);

	public PromotionVO findByPrimaryKey(Integer promNo);

	public List<PromotionVO> getAll();
	
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<PromotionVO> getAll(Map<String, String[]> map);	

	public List<PromotionVO> getAll_ByPromName(String promName);

}
