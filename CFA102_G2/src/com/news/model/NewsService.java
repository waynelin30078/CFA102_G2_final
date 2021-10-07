package com.news.model;

import java.sql.Timestamp;
import java.util.List;

public class NewsService {

	private NewsDAO_interface dao;
	
	public NewsService() {
		dao = new NewsDAO();
	}

	public NewsVO addNews( String newsTitle , String newsContent ) {
		
		NewsVO newsVO = new NewsVO();
		newsVO.setNewsTitle(newsTitle);
		newsVO.setNewsContent(newsContent);
//		newsVO.setNewsType(newsType);
		dao.insert(newsVO);
		return newsVO;
	}
	public NewsVO update(String newsTitle , String newsContent  , Integer newsNo ) {

		NewsVO newsVO = new NewsVO();
		newsVO.setNewsNo(newsNo);
		newsVO.setNewsTitle(newsTitle);
		newsVO.setNewsContent(newsContent);
//		newsVO.setNewsType(newsType);
		
		dao.update(newsVO);
		return newsVO;
	}
	
	public void deletenews(Integer newsNo) {
		dao.delete(newsNo);
	}


	public NewsVO find(Integer newsNO) {
		return dao.findByPrimaryKey(newsNO);
	}

	public List<NewsVO> getAll() {
		return dao.getAll();
	}
	
	
	
	
}
