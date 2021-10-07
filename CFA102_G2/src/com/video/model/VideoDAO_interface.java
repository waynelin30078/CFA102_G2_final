package com.video.model;

import java.util.List;

public interface VideoDAO_interface {
	void insert(VideoVO video);
	void update(VideoVO video);
	void delete(Integer vNo);
	VideoVO getOne(Integer vNo);
//	List <VideoVO> getAll();
	List <VideoVO> getAll(Integer cNo);
}
