package com.promotion_detail.model;

import java.util.List;
import java.util.Map;

public class Promotion_detailService {

	
		private Promotion_detailDAO_interface dao;
		
		public Promotion_detailService() {
			dao = new Promotion_detailDAO();
		}
		
		public Promotion_detailVO addPromotion_detail(Integer promNo, Integer pno, Integer pprice) {
			
			Promotion_detailVO promotion_detailVO = new Promotion_detailVO();
			
			promotion_detailVO.setPromNo(promNo);
			promotion_detailVO.setPno(pno);
			promotion_detailVO.setPprice(pprice);
			dao.insert(promotion_detailVO);
			
			return promotion_detailVO;
		}
		
		public Promotion_detailVO updatePromotion_detail(Integer promNo, Integer pno, Integer pprice) {
			
			Promotion_detailVO promotion_detailVO = new Promotion_detailVO();
			
			promotion_detailVO.setPromNo(promNo);
			promotion_detailVO.setPno(pno);
			promotion_detailVO.setPprice(pprice);
			dao.update(promotion_detailVO);
			
			return promotion_detailVO;
		}
		
		public void deletePromotion_detail(Integer promNo, Integer pno) {
			dao.delete(promNo, pno);
		}
		
		public Promotion_detailVO getOnePromotion_detail(Integer promNo, Integer pno) {
			return dao.findByPrimaryKey(promNo, pno);
		}
		
		public List<Promotion_detailVO> getAll() {
			return dao.getAll();
		}
		
		public List<Promotion_detailVO> getAll(Map<String, String[]> map) {
			return dao.getAll(map);
		}
		
		public List<Promotion_detailVO> getPromotionProduct() {
			return dao.getPromotionProduct();
		}
		
//		public List<Promotion_detailVO> getAll_ByPrice(Integer pprice, Integer pprice1) {
//			return dao.getAll_ByPrice(pprice, pprice1);
//		}
		
//		public List<Promotion_detailVO> getAll_ByPromNo(Integer promNo) {
//			return dao.getAll_ByPromNo(promNo);
//		}
		
//		public List<Promotion_detailVO> getAll_ByPno(Integer pno) {
//			return dao.getAll_ByPno(pno);
//		}		
						
}
