package com.p_favorite.model;

import java.util.List;

public interface P_favoriteDAO_interface {

	public void insert(P_favoriteVO p_favoriteVO);

	public void delete(Integer mno, Integer pno);

	public P_favoriteVO findByPrimaryKey(Integer mno, Integer pno);
	
	public List<P_favoriteVO> getAll();
	
	public List<P_favoriteVO> getAll_ByMno(Integer mno);

}
