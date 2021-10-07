package com.artice.model;

import java.util.List;

public class ArticeService {
	
	private ArticeDAO_interface dao;
	
	public ArticeService() {
		dao = new ArticeDAO();
	}
	
	public ArticeVO update(Integer articNo , String articTitle , String articContent , byte[] articPhoto , Integer articState , Integer articType) {
		ArticeVO articeVO = new ArticeVO();
		articeVO.setArticNo(articNo);
		articeVO.setArticTitle(articTitle);
		articeVO.setArticContent(articContent);
		articeVO.setArticPhoto(articPhoto);
		articeVO.setArticState(articState);
		articeVO.setArticType(articType);
		
		dao.update(articeVO);
		return articeVO;
	}

	public ArticeVO insert(Integer articType , String articTitle , String articContent , byte[] articPhoto ) {
		ArticeVO articeVO = new ArticeVO();
		articeVO.setArticType(articType);
		articeVO.setArticTitle(articTitle);
		articeVO.setArticContent(articContent);
		articeVO.setArticPhoto(articPhoto);
		dao.insert(articeVO);
		return articeVO;
	}

	public void delete(Integer articNo) {
		dao.delete(articNo);
	}

	public ArticeVO findbyarticNo(Integer articNo) {
		return dao.findbyarticNo(articNo);
	}

	public ArticeVO findbykeyword(String articTitle) {
		return dao.findbykeyword(articTitle);
	}

	public List<ArticeVO> getall() {
		return dao.getall();
	}
	
	

}
