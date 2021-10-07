package com.a_favorite.model;

import java.util.List;

public class A_favoriteService {

	private A_favoriteDAO_interface dao;
	
	public A_favoriteService() {
		dao = new A_favoriteDAO();
	}

	public void insert(A_favoriteVO a_facoriteVO) {
		dao.insert(a_facoriteVO);
	}

	public void delete(Integer mNOarticNo) {
		dao.delete(mNOarticNo);
	}

	public List<A_favoriteVO> findbymNO(Integer mNO) {
		return dao.findbymNO(mNO);
	}

	public List<A_favoriteVO> findbyarticNo(Integer articNo) {
		return dao.findbyarticNo(articNo);
	}

	public List<A_favoriteVO> getall() {
		return dao.getall();
	}

	public int countfac(Integer articNo) {
		return dao.countfac(articNo);
	}
	
	
	
}
