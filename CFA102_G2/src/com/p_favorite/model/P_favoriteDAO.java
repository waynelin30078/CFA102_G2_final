package com.p_favorite.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class P_favoriteDAO implements P_favoriteDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 新增收藏
	private static final String INSERT_STMT = "REPLACE INTO p_favorite (mNo,pNo) VALUES (?,?)";
	// 刪除收藏
	private static final String DELETE = "DELETE FROM p_favorite WHERE mNo=? AND pNo=?";
	// 查詢收藏(用PrimaryKey)
	private static final String GET_ONE_STMT = "SELECT * FROM p_favorite WHERE mNo =? AND pNo=?";
	// 查詢所有收藏
	private static final String GET_ALL_STMT = "SELECT * FROM p_favorite ORDER BY mNo";
	// 查詢收藏(用會員編號)
	private static final String GET_ALL_ByMno_STMT = "SELECT * FROM p_favorite WHERE mNo =? ORDER BY mNo AND pNo";

	@Override
	public void insert(P_favoriteVO p_favoriteVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, p_favoriteVO.getMno());
			pstmt.setInt(2, p_favoriteVO.getPno());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer mno, Integer pno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mno);
			pstmt.setInt(2, pno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public P_favoriteVO findByPrimaryKey(Integer mno, Integer pno) {

		P_favoriteVO p_favoriteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mno);
			pstmt.setInt(2, pno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				p_favoriteVO = new P_favoriteVO();
				p_favoriteVO.setMno(rs.getInt("mNo"));
				p_favoriteVO.setPno(rs.getInt("pNo"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return p_favoriteVO;
	}

	@Override
	public List<P_favoriteVO> getAll() {

		List<P_favoriteVO> list = new ArrayList<P_favoriteVO>();
		P_favoriteVO p_favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				p_favoriteVO = new P_favoriteVO();
				p_favoriteVO.setMno(rs.getInt("mNo"));
				p_favoriteVO.setPno(rs.getInt("pNo"));

				list.add(p_favoriteVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<P_favoriteVO> getAll_ByMno(Integer mno) {

		List<P_favoriteVO> list = new ArrayList<P_favoriteVO>();
		P_favoriteVO p_favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ByMno_STMT);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				p_favoriteVO = new P_favoriteVO();
				p_favoriteVO.setMno(rs.getInt("mNo"));
				p_favoriteVO.setPno(rs.getInt("pNo"));

				list.add(p_favoriteVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
