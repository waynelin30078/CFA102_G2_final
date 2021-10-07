package com.admin.model;

import java.util.*;

public interface AdminDAO_interface {

	public void insert(AdminVO adminVO); 
	public void update(AdminVO adminVO);
	public void delete(Integer ano);
	public AdminVO findByPrimaryKey(Integer ano);
	public List<AdminVO> getAll();
	public AdminVO findByAid(String aid);
}