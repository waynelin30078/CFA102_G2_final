package com.promotion.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class PromotionService {

	private PromotionDAO_interface dao;

	public PromotionService() {
		dao = new PromotionDAO();
	}

	public PromotionVO addPromotion(String promName, Date promStartDate, Date promEndDate) {

		PromotionVO promotionVO = new PromotionVO();

		promotionVO.setPromName(promName);
		promotionVO.setPromStartDate(promStartDate);
		promotionVO.setPromEndDate(promEndDate);
		dao.insert(promotionVO);

		return promotionVO;
	}

	public PromotionVO updatePromotion(Integer promNo, String promName, Date promStartDate, Date promEndDate) {

		PromotionVO promotionVO = new PromotionVO();

		promotionVO.setPromNo(promNo);
		promotionVO.setPromName(promName);
		promotionVO.setPromStartDate(promStartDate);
		promotionVO.setPromEndDate(promEndDate);
		dao.update(promotionVO);

		return promotionVO;
	}

	public void deletePromotion(Integer promNo) {
		dao.delete(promNo);
	}

	public PromotionVO getOnePromotion(Integer promNo) {
		return dao.findByPrimaryKey(promNo);
	}

	public List<PromotionVO> getAll() {
		return dao.getAll();
	}
	
    public List<PromotionVO> getAll(Map<String, String[]> map) {
    	return dao.getAll(map);
    }
}
