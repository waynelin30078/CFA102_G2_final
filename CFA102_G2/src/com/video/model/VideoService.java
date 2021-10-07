package com.video.model;

import java.util.List;

public class VideoService {
	private VideoDAO_interface dao;

	public VideoService() {
		dao = new VideoJDBCDAO();
	}

	public VideoVO addVideo(Integer cno,String vname, String vfile, java.sql.Time vlength) {
		VideoVO videoVO = new VideoVO();
		videoVO.setCno(cno);
		videoVO.setVname(vname);
		videoVO.setVfile(vfile);
		videoVO.setVlength(vlength);
		dao.insert(videoVO);
		return videoVO;
	}

	public VideoVO updateVideo(Integer vno, String vfile, java.sql.Time vlength) {
		VideoVO videoVO = new VideoVO();
		videoVO.setVno(vno);
		videoVO.setVfile(vfile);
		videoVO.setVlength(vlength);
		dao.update(videoVO);
		return videoVO;
	}

	public void deleteVideo(Integer vNo) {
		dao.delete(vNo);
	}

	public VideoVO getOneVidoe(Integer vno) {
		return dao.getOne(vno);
	}

	public List<VideoVO> getAll(Integer cNo) {
		return dao.getAll(cNo);
	}
}