package com.c_favorite.model;

import java.util.List;

public class C_FavoriteService {
	private C_FavoriteDAO_interface dao;

	public C_FavoriteService() {
		dao = new C_FavoriteJDBCDAO();
	}
	public C_FavoriteVO addCourseFavorite(Integer mno ,Integer cno) {
		C_FavoriteVO  c_FavoriteVO = new C_FavoriteVO();
		c_FavoriteVO.setMno(mno);
		c_FavoriteVO.setCno(cno);
		dao.insert(c_FavoriteVO);
		return c_FavoriteVO;
	}
	public String deleteCourseFavorite(Integer mno ,Integer cno) {
		
		C_FavoriteVO  c_FavoriteVO = new C_FavoriteVO();
		c_FavoriteVO.setMno(mno);
		c_FavoriteVO.setCno(cno);
		dao.delete(c_FavoriteVO);
		return "刪除成功";
	}
	public C_FavoriteVO getOneCourseFavorite(Integer mno ,Integer cno){
		C_FavoriteVO  c_FavoriteVO = new C_FavoriteVO();
		c_FavoriteVO.setMno(mno);
		c_FavoriteVO.setCno(cno);
		
		return dao.getOne(c_FavoriteVO);
	}
	public List<C_FavoriteVO> getALLCourseFavorite(){
		return dao.getAll();
	}
	public List<C_FavoriteVO> getALLCourseFavoriteBymno(Integer mno){
		return dao.getAll(mno);
	}

}
