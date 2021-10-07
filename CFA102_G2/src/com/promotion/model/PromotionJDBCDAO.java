package com.promotion.model;

import java.util.*;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Promotion;

import java.sql.*;

public class PromotionJDBCDAO implements PromotionDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promotionVO.getPromName());
			pstmt.setDate(2, promotionVO.getPromStartDate());
			pstmt.setDate(3, promotionVO.getPromEndDate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promotionVO.getPromName());
			pstmt.setDate(2, promotionVO.getPromStartDate());
			pstmt.setDate(3, promotionVO.getPromEndDate());
			pstmt.setInt(4, promotionVO.getPromNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(Integer promNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, promNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public PromotionVO findByPrimaryKey(Integer promNo) {

		PromotionVO promotionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from promotion "
			          + jdbcUtil_CompositeQuery_Promotion.get_WhereCondition(map)
			          + "order by promNo";			
			pstmt = con.prepareStatement(finalSQL);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {
		PromotionJDBCDAO dao = new PromotionJDBCDAO();
		// INSERT
//		PromotionVO promotionVO1 = new PromotionVO();
//		promotionVO1.setPromName("測試活動555");
//		promotionVO1.setPromStartDate(java.sql.Date.valueOf("2021-08-23"));
//		promotionVO1.setPromEndDate(java.sql.Date.valueOf("2021-08-23"));
//		dao.insert(promotionVO1);

//		UPDATE
//		PromotionVO promotionVO2 = new PromotionVO();
//		promotionVO2.setPromName("測試活動3");
//		promotionVO2.setPromStartDate(java.sql.Date.valueOf("2021-09-23"));
//		promotionVO2.setPromEndDate(java.sql.Date.valueOf("2021-09-23"));
//		promotionVO2.setPromNo(6);
//		dao.update(promotionVO2);

		// DELETE
//		dao.delete(6);

		// GET_ONE_STMT
		PromotionVO promotionVO3 = dao.findByPrimaryKey(2);
		System.out.print(promotionVO3.getPromNo() + ",");
		System.out.print(promotionVO3.getPromName() + ",");
		System.out.print(promotionVO3.getPromStartDate() + ",");
		System.out.println(promotionVO3.getPromEndDate());
		System.out.println("----------------------------------------------------");

		// GET_ALL_BY_PROMNAME
		List<PromotionVO> list = dao.getAll_ByPromName("父");
		for (PromotionVO aPromotion : list) {
			System.out.print(aPromotion.getPromNo() + ",");
			System.out.print(aPromotion.getPromName() + ",");
			System.out.print(aPromotion.getPromStartDate() + ",");
			System.out.println(aPromotion.getPromEndDate());
			System.out.println();
		}

		System.out.println("----------------------------------------------------");

		// GET_ALL_STMT
		List<PromotionVO> list1 = dao.getAll();
		for (PromotionVO aPromotion1 : list1) {
			System.out.print(aPromotion1.getPromNo() + ",");
			System.out.print(aPromotion1.getPromName() + ",");
			System.out.print(aPromotion1.getPromStartDate() + ",");
			System.out.println(aPromotion1.getPromEndDate());
			System.out.println();
		}
	}

}
