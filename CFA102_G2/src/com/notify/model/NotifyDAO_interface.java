package com.notify.model;

import java.util.List;


public interface NotifyDAO_interface {
	
	public void insert(NotifyVO notifyVO);
	public void delete(Integer notiNo);
	public void update(NotifyVO notifyVO);
	public NotifyVO findbyPK(Integer notiNo);
	public List<NotifyVO> getall();

}
