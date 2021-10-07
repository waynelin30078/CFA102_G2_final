package com.promotion.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Promotion;

public class PromotionDAO implements PromotionDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 新增商品優惠活動
	private static final String INSERT_STMT = "INSERT INTO promotion (promNo, promName, promStartDate, promEndDate) VALUES (null, ?, ?, ?)";
	// 更新商品優惠活動
	private static final String UPDATE = "UPDATE promotion SET promName=?, promStartDate=?, promEndDate=? WHERE promNo =?";
	// 刪除商品優惠活動
	private static final String DELETE = "DELETE FROM promotion WHERE promNo =?";
	// 查詢商品優惠活動(用優惠活動編號)
	private static final String GET_ONE_STMT = "SELECT promNo, promName, promStartDate, promEndDate FROM promotion WHERE promNo =?";
	// 查詢所有商品優惠活動
	private static final String GET_ALL_STMT = "SELECT promNo, promName, promStartDate, promEndDate FROM promotion ORDER BY promNo";
	// 查詢商品優惠活動(用優惠活動名稱)
	private static final String GET_ALL_ByPromName_STMT = "SELECT promNo, promName, promStartDate, promEndDate FROM promotion WHERE promName LIKE ? ORDER BY promNo";

	@Override
	public void insert(PromotionVO promotionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promotionVO.getPromName());
			pstmt.setDate(2, promotionVO.getPromStartDate());
			pstmt.setDate(3, promotionVO.getPromEndDate());

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
	public void update(PromotionVO promotionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promotionVO.getPromName());
			pstmt.setDate(2, promotionVO.getPromStartDate());
			pstmt.setDate(3, promotionVO.getPromEndDate());
			pstmt.setInt(4, promotionVO.getPromNo());

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
	public void delete(Integer promNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promNo);

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
	public PromotionVO findByPrimaryKey(Integer promNo) {

		PromotionVO promotionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotionVO = new PromotionVO();
				promotionVO.setPromNo(rs.getInt("promNo"));
				promotionVO.setPromName(rs.getString("promName"));
				promotionVO.setPromStartDate(rs.getDate("promStartDate"));
				promotionVO.setPromEndDate(rs.getDate("promEndDate"));

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
		return promotionVO;
	}

	@Override
	public List<PromotionVO> getAll() {

		List<PromotionVO> list = new ArrayList<PromotionVO>();
		PromotionVO promotionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotionVO = new PromotionVO();
				promotionVO.setPromNo(rs.getInt("promNo"));
				promotionVO.setPromName(rs.getString("promName"));
				promotionVO.setPromStartDate(rs.getDate("promStartDate"));
				promotionVO.setPromEndDate(rs.getDate("promEndDate"));
				list.add(promotionVO);
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
	public List<PromotionVO> getAll(Map<String, String[]> map) {

		List<PromotionVO> list = new ArrayList<PromotionVO>();
		PromotionVO promotionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "select * from promotion "
			          + jdbcUtil_CompositeQuery_Promotion.get_WhereCondition(map)
			          + "order by promNo";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotionVO = new PromotionVO();
				promotionVO.setPromNo(rs.getInt("promNo"));
				promotionVO.setPromName(rs.getString("promName"));
				promotionVO.setPromStartDate(rs.getDate("promStartDate"));
				promotionVO.setPromEndDate(rs.getDate("promEndDate"));
				list.add(promotionVO);
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
	public List<PromotionVO> getAll_ByPromName(String promName) {

		List<PromotionVO> list = new ArrayList<PromotionVO>();
		PromotionVO promotionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ByPromName_STMT);
			pstmt.setString(1, "%" + promName + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotionVO = new PromotionVO();
				promotionVO.setPromNo(rs.getInt("promNo"));
				promotionVO.setPromName(rs.getString("promName"));
				promotionVO.setPromStartDate(rs.getDate("promStartDate"));
				promotionVO.setPromEndDate(rs.getDate("promEndDate"));
				list.add(promotionVO);
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
