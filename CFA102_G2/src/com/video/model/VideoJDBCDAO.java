package com.video.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.course.model.CourseVO;

import util.Util;

public class VideoJDBCDAO implements VideoDAO_interface {

	private static final String INSERT = "INSERT INTO VIDEO(vNo,cNo,vName,vFile,vUpdateTime,vLength)VALUES(null,?,?,?,Now(),?)";
	private static final String UPDATE_VIDEO = "UPDATE VIDEO SET vFile=?, vUpdateTime=Now(),vLength=? WHERE vNO=?";
	private static final String DELETE_VIDEO = "DELETE FROM VIDEO WHERE vNo=?";
	private static final String GET_ONE_BY_VNO = "SELECT * FROM VIDEO WHERE vNO=?";
	private static final String GET_ALL_BY_CNO = "SELECT * FROM VIDEO WHERE cNO=?";
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(VideoVO video) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, video.getCno());
			pstmt.setString(2, video.getVname());
			pstmt.setString(3, video.getVfile());
			pstmt.setTime(4, video.getVlength());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(VideoVO video) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_VIDEO);

			pstmt.setString(1, video.getVfile());
			pstmt.setString(2, video.getVname());
			pstmt.setTime(3 ,video.getVlength());
			
			pstmt.setInt(4, video.getCno());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(Integer vNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_VIDEO);
			pstmt.setInt(1, vNo);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public VideoVO getOne(Integer vNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VideoVO videoVO = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_BY_VNO);
			pstmt.setInt(1, vNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				videoVO = new VideoVO();
				videoVO.setVno(vNo);
				videoVO.setCno(rs.getInt("cNo"));
				videoVO.setVfile(rs.getString("vFile"));
				videoVO.setVupdateTime(rs.getTimestamp("vUpdateTime"));
				videoVO.setVlength(rs.getTime("vLength"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return videoVO;
	}

	@Override
	public List<VideoVO> getAll(Integer cno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VideoVO> videoList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_BY_CNO);
			pstmt.setInt(1, cno);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				VideoVO videoVO = new VideoVO();
				videoVO.setVno(rs.getInt("vNo"));
				videoVO.setCno(rs.getInt("cNo"));
				videoVO.setVname(rs.getString("vName"));
				videoVO.setVfile(rs.getString("vFile"));
				videoVO.setVupdateTime(rs.getTimestamp("vUpdateTime"));
				videoVO.setVlength(rs.getTime("vLength"));
				videoList.add(videoVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return videoList;
	}
//cNo,vFile,vUpdateTime,vLength
//	
	public static void main(String[] agrs) {
//		//insert
//		Time t = new Time(11,11,11);
		VideoDAO_interface vdao=new VideoJDBCDAO();
//		VideoVO VideoVO1 = new VideoVO();
//		VideoVO1.setVno(3);
//		VideoVO1.setCno(2);
//		VideoVO1.setVname("ddddd");
//		VideoVO1.setVfile("video/aa.mp4");
//		VideoVO1.setVupdateTime((java.sql.Timestamp.valueOf("9999-12-31 23:59:59")));
//		VideoVO1.setVlength(t);
//	
//		vdao.insert(VideoVO1);
//	}
//		System.out.println(t);
//		//update
////		vdao.update(VideoVO1);
//		
//		//delete
////		vdao.delete(6);
		//getone
		System.out.println(vdao.getOne(6).getVlength());
//		//get all
//		List<VideoVO> vList =vdao.gelAll();
//		for(VideoVO v:vList)
//			System.out.println(v);
//		
	}

	

	
}
