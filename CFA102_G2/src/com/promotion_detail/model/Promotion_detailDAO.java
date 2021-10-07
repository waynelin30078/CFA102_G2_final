package com.promotion_detail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Promotion_detail;

public class Promotion_detailDAO implements Promotion_detailDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 新增優惠活動明細
	private static final String INSERT_STMT = "INSERT INTO promotion_detail (promNo, pNo, pPrice) VALUES (?, ?, ?)";
	// 更新優惠活動明細
	private static final String UPDATE = "UPDATE promotion_detail SET pPrice=? WHERE promNo =? AND pNo=?";
	// 刪除優惠活動明細
	private static final String DELETE = "DELETE FROM promotion_detail WHERE promNo =? AND pNo=?";
	// 查詢優惠活動明細(用PK)
	private static final String GET_ONE_STMT = "SELECT * FROM promotion_detail WHERE promNo =? AND pNo=?";
	// 查詢所有優惠活動明細
	private static final String GET_ALL_STMT = "SELECT * FROM promotion_detail ORDER BY promNo AND pNo";
//	// 查詢所有優惠活動明細(用價格)
//	private static final String GET_ALL_ByPrice_STMT = "SELECT * FROM promotion_detail WHERE pPrice BETWEEN ? AND ? ORDER BY promNo, pNo";
//	// 查詢所有優惠活動明細(用優惠活動編號)
//	private static final String GET_ALL_ByPromno_STMT = "SELECT * FROM promotion_detail WHERE promNo =?  ORDER BY promNo, pNo";	
//	// 查詢所有優惠活動明細(用商品編號)
//	private static final String GET_ALL_ByPno_STMT = "SELECT * FROM promotion_detail WHERE pNo=? ORDER BY promNo, pNo";
	
	private static final String GET_PROMO_PRODUCT_STMT = "(SELECT * from PROMOTION_DETAIL where promNo = (SELECT promNo FROM PROMOTION where CURDATE() between promStartDate and promEndDate))";
	

	@Override
	public void insert(Promotion_detailVO promotion_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, promotion_detailVO.getPromNo());
			pstmt.setInt(2, promotion_detailVO.getPno());
			pstmt.setInt(3, promotion_detailVO.getPprice());

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
	public void update(Promotion_detailVO promotion_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, promotion_detailVO.getPprice());
			pstmt.setInt(2, promotion_detailVO.getPromNo());
			pstmt.setInt(3, promotion_detailVO.getPno());

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
	public void delete(Integer promNo, Integer pno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promNo);
			pstmt.setInt(2, pno);

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
	public Promotion_detailVO findByPrimaryKey(Integer promNo, Integer pno) {

		Promotion_detailVO promotion_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, promNo);
			pstmt.setInt(2, pno);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotion_detailVO = new Promotion_detailVO();
				promotion_detailVO.setPromNo(rs.getInt("promNo"));
				promotion_detailVO.setPno(rs.getInt("pNo"));
				promotion_detailVO.setPprice(rs.getInt("pPrice"));

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
		return promotion_detailVO;
	}

	@Override
	public List<Promotion_detailVO> getAll() {

		List<Promotion_detailVO> list = new ArrayList<Promotion_detailVO>();
		Promotion_detailVO promotion_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotion_detailVO = new Promotion_detailVO();
				promotion_detailVO.setPromNo(rs.getInt("promNo"));
				promotion_detailVO.setPno(rs.getInt("pNo"));
				promotion_detailVO.setPprice(rs.getInt("pPrice"));

				list.add(promotion_detailVO); // Store the row in the list
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

//	@Override
//	public List<Promotion_detailVO> getAll_ByPrice(Integer pprice, Integer pprice1) {
//		
//		List<Promotion_detailVO> list = new ArrayList<Promotion_detailVO>();
//		Promotion_detailVO promotion_detailVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_ByPrice_STMT);
//			pstmt.setInt(1, pprice);
//			pstmt.setInt(2, pprice1);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				promotion_detailVO = new Promotion_detailVO();
//				promotion_detailVO.setPromNo(rs.getInt("promNo"));
//				promotion_detailVO.setPno(rs.getInt("pNo"));
//				promotion_detailVO.setPprice(rs.getInt("pPrice"));
//
//				list.add(promotion_detailVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

//	@Override
//	public List<Promotion_detailVO> getAll_ByPromNo(Integer promNo) {
//		
//		List<Promotion_detailVO> list = new ArrayList<Promotion_detailVO>();
//		Promotion_detailVO promotion_detailVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_ByPromno_STMT);
//			pstmt.setInt(1, promNo);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				promotion_detailVO = new Promotion_detailVO();
//				promotion_detailVO.setPromNo(rs.getInt("promNo"));
//				promotion_detailVO.setPno(rs.getInt("pNo"));
//				promotion_detailVO.setPprice(rs.getInt("pPrice"));
//
//				list.add(promotion_detailVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

//	@Override
//	public List<Promotion_detailVO> getAll_ByPno(Integer pno) {
//		
//		List<Promotion_detailVO> list = new ArrayList<Promotion_detailVO>();
//		Promotion_detailVO promotion_detailVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_ByPno_STMT);
//			pstmt.setInt(1, pno);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				promotion_detailVO = new Promotion_detailVO();
//				promotion_detailVO.setPromNo(rs.getInt("promNo"));
//				promotion_detailVO.setPno(rs.getInt("pNo"));
//				promotion_detailVO.setPprice(rs.getInt("pPrice"));
//
//				list.add(promotion_detailVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

	@Override
	public List<Promotion_detailVO> getAll(Map<String, String[]> map) {

		List<Promotion_detailVO> list = new ArrayList<Promotion_detailVO>();
		Promotion_detailVO promotion_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "select * from promotion_detail "
			          + jdbcUtil_CompositeQuery_Promotion_detail.get_WhereCondition(map)
			          + "order by promNo and pno";			
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotion_detailVO = new Promotion_detailVO();
				promotion_detailVO.setPromNo(rs.getInt("promNo"));
				promotion_detailVO.setPno(rs.getInt("pNo"));
				promotion_detailVO.setPprice(rs.getInt("pPrice"));

				list.add(promotion_detailVO); // Store the row in the list
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
	public List<Promotion_detailVO> getPromotionProduct() {

		List<Promotion_detailVO> discountProducts = new ArrayList<Promotion_detailVO>();
		Promotion_detailVO promotion_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROMO_PRODUCT_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotion_detailVO = new Promotion_detailVO();
				promotion_detailVO.setPromNo(rs.getInt("promNo"));
				promotion_detailVO.setPno(rs.getInt("pNo"));
				promotion_detailVO.setPprice(rs.getInt("pPrice"));

				discountProducts.add(promotion_detailVO); // Store the row in the list
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
		return discountProducts;
	}	
	
	
	
}
