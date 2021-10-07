package com.material.model;

import java.util.List;


public interface MaterialDAO_interface {
	void insert(MaterialVO mat);
	void update(MaterialVO mat);
	void delete(Integer matNo);
	MaterialVO getOne(Integer matNo);
	List <MaterialVO> getAll();

}