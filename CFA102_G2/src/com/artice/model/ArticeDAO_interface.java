package com.artice.model;

import java.util.List;


public interface ArticeDAO_interface {

	public void insert(ArticeVO articeVO);
	public void delete(Integer articNo);
	public void	update(ArticeVO articeVO);
	
	public ArticeVO findbyarticNo(Integer articNo);
	public ArticeVO findbykeyword(String articTitle);
	public List<ArticeVO> getall();
	
	

}
