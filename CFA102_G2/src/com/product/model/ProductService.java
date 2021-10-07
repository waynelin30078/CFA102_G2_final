package com.product.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	public ProductVO addProduct(String categoryName, String pname, Integer pprice, String pinfo, Integer pquantity,
			Date ponDate, Date poffDate, String pimage1, String pimage2, String pimage3, Integer pstate) {

		ProductVO productVO = new ProductVO();

		productVO.setCategoryName(categoryName);
		productVO.setPname(pname);
		productVO.setPprice(pprice);
		productVO.setPinfo(pinfo);
		productVO.setPquantity(pquantity);
		productVO.setPonDate(ponDate);
		productVO.setPoffDate(poffDate);
		productVO.setPimage1(pimage1);
		productVO.setPimage2(pimage2);
		productVO.setPimage3(pimage3);
		productVO.setPstate(pstate);
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer pno, String categoryName, String pname, Integer pprice, String pinfo,
			Integer pquantity, Date ponDate, Date poffDate, String pimage1, String pimage2, String pimage3, 
			Integer pstate) {

		ProductVO productVO = new ProductVO();

		productVO.setPno(pno);
		productVO.setCategoryName(categoryName);
		productVO.setPname(pname);
		productVO.setPprice(pprice);
		productVO.setPinfo(pinfo);
		productVO.setPquantity(pquantity);
		productVO.setPonDate(ponDate);
		productVO.setPoffDate(poffDate);
		productVO.setPimage1(pimage1);
		productVO.setPimage2(pimage2);
		productVO.setPimage3(pimage3);
//		productVO.setPratingsQuantity(pratingsQuantity);
//		productVO.setPtotalRatings(ptotalRatings);
		productVO.setPstate(pstate);
		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(Integer pno) {
		dao.delete(pno);
	}

	public ProductVO getOneProduct(Integer pno) {
		return dao.findByPrimaryKey(pno);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

	public List<ProductVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}	

	public List<ProductVO> getCategory() {
		return dao.getCategory();
	}
	
	public List<ProductVO> getAll_ByPstate(Integer pstate) {
		return dao.getAll_ByPstate(pstate);
	}


}
