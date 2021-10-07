package com.a_favorite.model;

import java.util.List;

public class test {

	
	public static void main (String args[]) {
		
		A_favoriteDAO dao = new A_favoriteDAO();
		
		A_favoriteVO a1 = new A_favoriteVO();
		
//		a1.setmNO(333);
//		a1.setArticNo(111);
//		dao.insert(a1);
//		dao.delete(222);
		
		
//		List<a_favoriteVO> list = dao.findbymNO(222);
//		for(a_favoriteVO aVO : list) {
//			
//			System.out.println(aVO.getArticNo());
//		}
//		List<a_favoriteVO> list = dao.getall();
//		for(a_favoriteVO aVO : list) {
//			System.out.println(aVO.getArticNo());
//			System.out.println(aVO.getMno());
//		}
		System.out.println(dao.countfac(111));
		
		
		
		
		
	}
}
