package com.c_favorite.model;

import java.util.List;

public interface C_FavoriteDAO_interface {
	void insert(C_FavoriteVO c_favorite);

	void delete(C_FavoriteVO c_favorite);

	C_FavoriteVO getOne(C_FavoriteVO c_favorite);

	List<C_FavoriteVO> getAll();

	List<C_FavoriteVO> getAll(Integer mNo);

}
