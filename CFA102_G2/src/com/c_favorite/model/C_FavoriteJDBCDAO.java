package com.c_favorite.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class C_FavoriteJDBCDAO implements C_FavoriteDAO_interface {
	private static final String INSERT = "INSERT INTO C_FAVORITE (mNO,cNO)VALUES(?,?)";

	private static final String DELETE = "DELETE FROM C_FAVORITE WHERE mNo =? AND cNo=?";
	private static final String GET_ONE_BY_PK = "SELECT * FROM C_FAVORITE WHERE mNO=? AND cNO=?";
	private static final String GET_ALL = "SELECT * FROM C_FAVORITE";
	private static final String GET_ALL_BY_MNO = "SELECT * FROM C_FAVORITE WHERE mNO=?";
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(C_FavoriteVO c_favoriteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1,c_favoriteVO.getMno());
			pstmt.setInt(2,c_favoriteVO.getCno());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();// 錯誤
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
	public void delete(C_FavoriteVO c_favorite) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, c_favorite.getMno());
			pstmt.setInt(2, c_favorite.getCno());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();// 錯誤
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
	public C_FavoriteVO getOne(C_FavoriteVO c_favorite) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		C_FavoriteVO c_favoriteVO = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_BY_PK);
			pstmt.setInt(1, c_favorite.getMno());
			pstmt.setInt(2, c_favorite.getCno());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				c_favoriteVO = new C_FavoriteVO();
				c_favoriteVO.setMno(rs.getInt("mNo"));
				c_favoriteVO.setCno(rs.getInt("cNo"));

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
		return c_favoriteVO;
	}

	@Override
	public List<C_FavoriteVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<C_FavoriteVO> c_favoritelist = new ArrayList<>();
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				C_FavoriteVO c_favoriteVO = new C_FavoriteVO();
				c_favoriteVO.setMno(rs.getInt("mNo"));
				c_favoriteVO.setCno(rs.getInt("cNo"));
				c_favoritelist.add(c_favoriteVO);
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
		return c_favoritelist;
	}

	@Override
	public List<C_FavoriteVO> getAll(Integer mNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<C_FavoriteVO> c_favoritelist = new ArrayList<>();
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_BY_MNO);
			pstmt.setInt(1, mNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				C_FavoriteVO c_favoriteVO = new C_FavoriteVO();
				c_favoriteVO.setMno(rs.getInt("mNo"));
				c_favoriteVO.setCno(rs.getInt("cNo"));
				c_favoritelist.add(c_favoriteVO);
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
		return c_favoritelist;
	}

//	public static void main(String[] agrs) {
//		//insert
//		
//		C_FavoriteDAO_interface dao = new C_FavoriteJDBCDAO();
//		C_FavoriteVO VO = new C_FavoriteVO();
//		VO.setMno(1);
//		VO.setCno(1);
//		System.out.println(dao.getOne(VO));
		
//		dao.insert(VO);
	// update
//		dao.update(VO);

	// delete
//		dao.delete(VO);
	// getone
//		System.out.println(VO);
	// get all
//		List<C_FavoriteVO> List = (dao.getAll(VO.getMno()));
//		for (C_FavoriteVO t : List)
//			System.out.println(t);
//		System.out.println("成功");
//	}
}
