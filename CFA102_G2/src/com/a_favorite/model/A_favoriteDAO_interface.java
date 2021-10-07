package com.a_favorite.model;

import java.util.List;


public interface A_favoriteDAO_interface {
	
	public void insert(A_favoriteVO a_facoriteVO);
	public void delete(Integer mNOarticNo);
	
	public List<A_favoriteVO> findbymNO(Integer mNO);
	public List<A_favoriteVO> findbyarticNo(Integer articNo);
	public List<A_favoriteVO> getall();
	
	public int countfac(Integer articNo);

}
