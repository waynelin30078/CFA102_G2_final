package com.p_order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class P_order_detailJDBCDAO implements P_order_detailDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	// 商品訂單明細
	private static final String INSERT_STMT = "INSERT INTO p_order_detail (pNo, pOrderQuantity, pPrice, pratings, porderNo) VALUES (?, ?, ?, ?, ?)";
	// 更新商品訂單明細
	private static final String UPDATE = "UPDATE p_order_detail SET pOrderQuantity=?, pPrice=?, pRatings=? WHERE pOrderNo =? AND pNo =?";
	// 更新商品訂單明細(訂購數量)
	private static final String UPDATE_QUANTITY = "UPDATE p_order_detail SET pOrderQuantity=? WHERE pOrderNo =? AND pNo =?";
	// 更新商品訂單明細(商品單價)
	private static final String UPDATE_PRICE = "UPDATE p_order_detail SET pPrice=? WHERE pOrderNo =? AND pNo =?";
	// 更新商品訂單明細(商品評價)
	private static final String UPDATE_RATINGS = "UPDATE p_order_detail SET pRatings=? WHERE pOrderNo =? AND pNo =?";
	// 刪除商品訂單明細
	private static final String DELETE = "DELETE FROM p_order_detail WHERE pOrderNo =? AND pNo =?";
	// 查詢商品訂單明細(用PrimaryKey)
	private static final String GET_ONE_STMT = "SELECT * FROM p_order_detail WHERE pOrderNo =? AND pNo =?";
	// 查詢所有商品訂單
	private static final String GET_ALL_STMT = "SELECT * FROM p_order_detail ORDER BY pOrderNo";
//	// 查詢商品訂單明細(用商品訂單編號)
	private static final String GET_ALL_BY_ORDERNO = "SELECT * FROM p_order_detail WHERE pOrderNo =? ORDER BY pOrderNo and pno";

	@Override
	public void insert(P_order_detailVO p_order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, p_order_detailVO.getPorderNo());
			pstmt.setInt(2, p_order_detailVO.getPno());
			pstmt.setInt(3, p_order_detailVO.getPorderQuantity());
			pstmt.setInt(4, p_order_detailVO.getPprice());
			pstmt.setInt(5, p_order_detailVO.getPratings());

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
	public void update(P_order_detailVO p_order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, p_order_detailVO.getPorderQuantity());
			pstmt.setInt(2, p_order_detailVO.getPprice());
			pstmt.setInt(3, p_order_detailVO.getPratings());
			pstmt.setInt(4, p_order_detailVO.getPorderNo());
			pstmt.setInt(5, p_order_detailVO.getPno());

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
	public void updateQuantity(P_order_detailVO p_order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_QUANTITY);

			pstmt.setInt(1, p_order_detailVO.getPorderQuantity());
			pstmt.setInt(2, p_order_detailVO.getPorderNo());
			pstmt.setInt(3, p_order_detailVO.getPno());

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
	public void updatePrice(P_order_detailVO p_order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_PRICE);

			pstmt.setInt(1, p_order_detailVO.getPprice());
			pstmt.setInt(2, p_order_detailVO.getPorderNo());
			pstmt.setInt(3, p_order_detailVO.getPno());

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
	public void updateRatings(P_order_detailVO p_order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_RATINGS);

			pstmt.setInt(1, p_order_detailVO.getPratings());
			pstmt.setInt(2, p_order_detailVO.getPorderNo());
			pstmt.setInt(3, p_order_detailVO.getPno());

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
	public void delete(Integer porderNo, Integer pno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, porderNo);
			pstmt.setInt(2, pno);

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
	public P_order_detailVO findByPrimaryKey(Integer porderNo, Integer pno) {

		P_order_detailVO p_order_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, porderNo);
			pstmt.setInt(2, pno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				p_order_detailVO = new P_order_detailVO();
				p_order_detailVO.setPorderNo(rs.getInt("pOrderNo"));
				p_order_detailVO.setPno(rs.getInt("pNo"));
				p_order_detailVO.setPorderQuantity(rs.getInt("pOrderQuantity"));
				p_order_detailVO.setPprice(rs.getInt("pPrice"));
				p_order_detailVO.setPratings(rs.getInt("pRatings"));
			}

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
		return p_order_detailVO;
	}

	@Override
	public List<P_order_detailVO> getAll() {

		List<P_order_detailVO> list = new ArrayList<P_order_detailVO>();
		P_order_detailVO p_order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				p_order_detailVO = new P_order_detailVO();
				p_order_detailVO.setPorderNo(rs.getInt("pOrderNo"));
				p_order_detailVO.setPno(rs.getInt("pNo"));
				p_order_detailVO.setPorderQuantity(rs.getInt("pOrderQuantity"));
				p_order_detailVO.setPprice(rs.getInt("pPrice"));
				p_order_detailVO.setPratings(rs.getInt("pRatings"));

				list.add(p_order_detailVO); // Store the row in the list
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
	public List<P_order_detailVO> getAll_ByOrderNo(Integer porderNo) {

		List<P_order_detailVO> list = new ArrayList<P_order_detailVO>();
		P_order_detailVO p_order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_ORDERNO);
			pstmt.setInt(1, porderNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				p_order_detailVO = new P_order_detailVO();
				p_order_detailVO.setPorderNo(rs.getInt("pOrderNo"));
				p_order_detailVO.setPno(rs.getInt("pNo"));
				p_order_detailVO.setPorderQuantity(rs.getInt("pOrderQuantity"));
				p_order_detailVO.setPprice(rs.getInt("pPrice"));
				p_order_detailVO.setPratings(rs.getInt("pRatings"));

				list.add(p_order_detailVO); // Store the row in the list
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
	public void insert2(P_order_detailVO p_order_detailVO, Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, p_order_detailVO.getPno());
			pstmt.setInt(2, p_order_detailVO.getPorderQuantity());
			pstmt.setInt(3, p_order_detailVO.getPprice());
			pstmt.setInt(4, p_order_detailVO.getPratings());
			pstmt.setInt(5, p_order_detailVO.getPorderNo());

			Statement stmt=	con.createStatement();
			//stmt.executeUpdate("set auto_increment_offset=7001;"); //自增主鍵-初始值
			stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-p_order_detail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}	
	
	public static void main(String[] args) {

		P_order_detailJDBCDAO dao = new P_order_detailJDBCDAO();

		// INSERT_STMT
//		P_order_detailVO p_order_detailVO1 = new P_order_detailVO();
//		p_order_detailVO1.setPorderNo(1);
//		p_order_detailVO1.setPno(5);
//		p_order_detailVO1.setPorderQuantity(1);
//		p_order_detailVO1.setPprice(699);
//		p_order_detailVO1.setPratings(3);
//		dao.insert(p_order_detailVO1);

		// UPDATE
//		P_order_detailVO p_order_detailVO2 = new P_order_detailVO();
//		p_order_detailVO2.setPorderQuantity(100);
//		p_order_detailVO2.setPprice(500);
//		p_order_detailVO2.setPratings(5);
//		p_order_detailVO2.setPorderNo(1);
//		p_order_detailVO2.setPno(5);		
//		dao.update(p_order_detailVO2);

		// UPDATE_QUANTITY
//		P_order_detailVO p_order_detailVO3 = new P_order_detailVO();
//		p_order_detailVO3.setPorderNo(1);
//		p_order_detailVO3.setPno(5);
//		p_order_detailVO3.setPorderQuantity(1);
//		dao.updateQuantity(p_order_detailVO3);	

		// UPDATE_PRICE
//		P_order_detailVO p_order_detailVO4 = new P_order_detailVO();
//		p_order_detailVO4.setPorderNo(1);
//		p_order_detailVO4.setPno(5);
//		p_order_detailVO4.setPprice(699);
//		dao.updatePrice(p_order_detailVO4);

		// UPDATE_RATINGS
//		P_order_detailVO p_order_detailVO5 = new P_order_detailVO();
//		p_order_detailVO5.setPorderNo(1);
//		p_order_detailVO5.setPno(5);
//		p_order_detailVO5.setPratings(3);
//		dao.updateRatings(p_order_detailVO5);

		// DELETE
//		dao.delete(1,5);

		// GET_ONE_STMT
//		P_order_detailVO p_order_detailVO6 = dao.findByPrimaryKey(1, 5);
//		System.out.print(p_order_detailVO6.getPorderNo() + ",");
//		System.out.print(p_order_detailVO6.getPno() + ",");
//		System.out.print(p_order_detailVO6.getPorderQuantity() + ",");
//		System.out.print(p_order_detailVO6.getPprice() + ",");
//		System.out.print(p_order_detailVO6.getPratings());
//		System.out.println();
//		System.out.println("--------------------------");

		// GET_ALL_BY_ORDERNO
//		List<P_order_detailVO> list = dao.getAll_ByOrderNo(7);
//		for (P_order_detailVO aP_order_detail : list) {
//			System.out.print(aP_order_detail.getPorderNo() + ",");
//			System.out.print(aP_order_detail.getPno() + ",");
//			System.out.print(aP_order_detail.getPorderQuantity() + ",");
//			System.out.print(aP_order_detail.getPprice() + ",");
//			System.out.print(aP_order_detail.getPratings());
//			System.out.println();
//		}
//		System.out.println("--------------------------");
		// GET_ALL_STMT
//		List<P_order_detailVO> list1 = dao.getAll();
//		for (P_order_detailVO aP_order_detail : list1) {
//			System.out.print(aP_order_detail.getPorderNo() + ",");
//			System.out.print(aP_order_detail.getPno() + ",");
//			System.out.print(aP_order_detail.getPorderQuantity() + ",");
//			System.out.print(aP_order_detail.getPprice() + ",");
//			System.out.print(aP_order_detail.getPratings());
//			System.out.println();
//		}

	}
	


}
