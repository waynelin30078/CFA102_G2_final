package com.p_favorite.model;

import java.util.List;

public class P_favoriteService {

	private P_favoriteDAO_interface dao;

	public P_favoriteService() {
		dao = new P_favoriteDAO();
	}

	public P_favoriteVO addP_favorite(Integer mno, Integer pno) {

		P_favoriteVO p_favoriteVO = new P_favoriteVO();

		p_favoriteVO.setMno(mno);
		p_favoriteVO.setPno(pno);
		dao.insert(p_favoriteVO);

		return p_favoriteVO;
	}

	public void deleteP_favorite(Integer mno, Integer pno) {
		dao.delete(mno, pno);
	}

	public P_favoriteVO getOneP_favorite(Integer mno, Integer pno) {
		return dao.findByPrimaryKey(mno, pno);
	}

	public List<P_favoriteVO> getAll() {
		return dao.getAll();
	}

	public List<P_favoriteVO> getAll_ByMno(Integer mno) {
		return dao.getAll_ByMno(mno);
	}

}
