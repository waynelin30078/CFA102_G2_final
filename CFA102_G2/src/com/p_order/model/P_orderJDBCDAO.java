package com.p_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.p_order_detail.model.P_order_detailJDBCDAO;
import com.p_order_detail.model.P_order_detailVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_P_order;

public class P_orderJDBCDAO implements P_orderDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	// 新增商品訂單
	private static final String INSERT_STMT = "INSERT INTO p_order (mNo, pOrderDate, pOrderTotal, pOrderName, pOrderPhone, pOrderAddress, "
			+ "pPayment, pCreditCard, pCreditCardCVV, pShipping, pOrderState) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 更新商品訂單
	private static final String UPDATE = "UPDATE p_order SET mno=?, porderDate=?, porderTotal=?, pOrderName=?, pOrderPhone=?, pOrderAddress=?, ppayment=?,"
			+ " pcreditCard=?, pcreditCardCVV=?, pshipping=?, pOrderState=? WHERE pOrderNo =?";
	// 更新商品訂單(狀態)
	private static final String UPDATE_OrderState = "UPDATE p_order SET pOrderState=? WHERE pOrderNo =?";
	// 刪除商品訂單
	private static final String DELETE = "DELETE FROM p_order WHERE pOrderNo =?";
	// 查詢商品訂單(findByPrimaryKey)
	private static final String GET_ONE_STMT = "SELECT * FROM p_order WHERE pOrderNo =? ORDER BY pOrderNo";
	// 查詢所有商品訂單
	private static final String GET_ALL_STMT = "SELECT * FROM p_order ORDER BY pOrderNo";	
	//查詢某訂單的訂單明細(一對多)(回傳 Set)
	private static final String GET_PorderDetail_ByPorderNo_STMT = "SELECT * FROM p_order_detail WHERE pOrderNo = ? ORDER BY pOrderNo";
	// 查詢所有商品訂單(Bymno)
	private static final String GET_ALL_ByMno_STMT = "SELECT * FROM p_order WHERE mno =?  ORDER BY pOrderNo";	
	
	@Override
	public void insert(P_orderVO p_orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, p_orderVO.getMno());
			pstmt.setInt(2, p_orderVO.getPorderTotal());
			pstmt.setString(3, p_orderVO.getPorderName());
			pstmt.setString(4, p_orderVO.getPorderPhone());
			pstmt.setString(5, p_orderVO.getPorderAddress());
			pstmt.setInt(6, p_orderVO.getPpayment());
			pstmt.setString(7, p_orderVO.getPcreditCard());
			pstmt.setString(8, p_orderVO.getPcreditCardCVV());
			pstmt.setInt(9, p_orderVO.getPshipping());

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
	public void update(P_orderVO p_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, p_orderVO.getMno());
			pstmt.setTimestamp(2, p_orderVO.getPorderDate());
			pstmt.setInt(3, p_orderVO.getPorderTotal());
			pstmt.setString(4, p_orderVO.getPorderName());
			pstmt.setString(5, p_orderVO.getPorderPhone());
			pstmt.setString(6, p_orderVO.getPorderAddress());
			pstmt.setInt(7, p_orderVO.getPpayment());
			pstmt.setString(8, p_orderVO.getPcreditCard());
			pstmt.setString(9, p_orderVO.getPcreditCardCVV());
			pstmt.setInt(10, p_orderVO.getPshipping());
			pstmt.setInt(11, p_orderVO.getPorderState());
			pstmt.setInt(12, p_orderVO.getPorderNo());

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
	public void updateOrderState(P_orderVO p_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_OrderState);

			pstmt.setInt(1, p_orderVO.getPorderState());
			pstmt.setInt(2, p_orderVO.getPorderNo());

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
	public void delete(Integer porderNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, porderNo);

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
	public P_orderVO findByPrimaryKey(Integer porderNo) {

		P_orderVO p_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, porderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				p_orderVO = new P_orderVO();
				p_orderVO.setPorderNo(rs.getInt("pOrderNo"));
				p_orderVO.setMno(rs.getInt("mNo"));
				p_orderVO.setPorderDate(rs.getTimestamp("pOrderDate"));
				p_orderVO.setPorderTotal(rs.getInt("pOrderTotal"));
				p_orderVO.setPorderName(rs.getString("pOrderName"));
				p_orderVO.setPorderPhone(rs.getString("pOrderPhone"));
				p_orderVO.setPorderAddress(rs.getString("pOrderAddress"));
				p_orderVO.setPpayment(rs.getInt("pPayment"));
				p_orderVO.setPcreditCard(rs.getString("pCreditCard"));
				p_orderVO.setPcreditCardCVV(rs.getString("pCreditCardCVV"));
				p_orderVO.setPshipping(rs.getInt("pShipping"));
				p_orderVO.setPorderState(rs.getInt("pOrderState"));

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
		return p_orderVO;
	}
	
	@Override
	public List<P_orderVO> getAll() {

		List<P_orderVO> list = new ArrayList<P_orderVO>();
		P_orderVO p_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				p_orderVO = new P_orderVO();
				p_orderVO.setPorderNo(rs.getInt("pOrderNo"));
				p_orderVO.setMno(rs.getInt("mNo"));
				p_orderVO.setPorderDate(rs.getTimestamp("pOrderDate"));
				p_orderVO.setPorderTotal(rs.getInt("pOrderTotal"));
				p_orderVO.setPorderName(rs.getString("pOrderName"));
				p_orderVO.setPorderPhone(rs.getString("pOrderPhone"));
				p_orderVO.setPorderAddress(rs.getString("pOrderAddress"));
				p_orderVO.setPpayment(rs.getInt("pPayment"));
				p_orderVO.setPcreditCard(rs.getString("pCreditCard"));
				p_orderVO.setPcreditCardCVV(rs.getString("pCreditCardCVV"));
				p_orderVO.setPshipping(rs.getInt("pShipping"));
				p_orderVO.setPorderState(rs.getInt("pOrderState"));
				list.add(p_orderVO);
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
	public List<P_orderVO> getAll(Map<String, String[]> map) {
		
		List<P_orderVO> list = new ArrayList<P_orderVO>();
		P_orderVO p_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from p_order "
			          + jdbcUtil_CompositeQuery_P_order.get_WhereCondition(map)
			          + "order by pOrderNo";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
				rs = pstmt.executeQuery();

			while (rs.next()) {

				p_orderVO = new P_orderVO();
				p_orderVO.setPorderNo(rs.getInt("pOrderNo"));
				p_orderVO.setMno(rs.getInt("mNo"));
				p_orderVO.setPorderDate(rs.getTimestamp("pOrderDate"));
				p_orderVO.setPorderTotal(rs.getInt("pOrderTotal"));
				p_orderVO.setPorderName(rs.getString("pOrderName"));
				p_orderVO.setPorderPhone(rs.getString("pOrderPhone"));
				p_orderVO.setPorderAddress(rs.getString("pOrderAddress"));
				p_orderVO.setPpayment(rs.getInt("pPayment"));
				p_orderVO.setPcreditCard(rs.getString("pCreditCard"));
				p_orderVO.setPcreditCardCVV(rs.getString("pCreditCardCVV"));
				p_orderVO.setPshipping(rs.getInt("pShipping"));
				p_orderVO.setPorderState(rs.getInt("pOrderState"));
				list.add(p_orderVO);
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
	public Set<P_order_detailVO> getPorderDetailByPorderNo(Integer pOrderNo) {
		
		Set<P_order_detailVO> set = new HashSet<P_order_detailVO>();
		P_order_detailVO p_order_detailVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PorderDetail_ByPorderNo_STMT);
			pstmt.setInt(1, pOrderNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				p_order_detailVO = new P_order_detailVO();
				p_order_detailVO.setPorderNo(rs.getInt("porderNo"));
				p_order_detailVO.setPno(rs.getInt("pno"));
				p_order_detailVO.setPorderQuantity(rs.getInt("porderQuantity"));
				p_order_detailVO.setPprice(rs.getInt("pprice"));
				p_order_detailVO.setPratings(rs.getInt("pratings"));
				set.add(p_order_detailVO);
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}

	@Override
	public void insertWithPorderDetail(P_orderVO p_orderVO, List<P_order_detailVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單
			String cols[] = {"pOrderNo"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setInt(1, p_orderVO.getMno());
			pstmt.setTimestamp(2, p_orderVO.getPorderDate());
			pstmt.setInt(3, p_orderVO.getPorderTotal());
			pstmt.setString(4, p_orderVO.getPorderName());
			pstmt.setString(5, p_orderVO.getPorderPhone());
			pstmt.setString(6, p_orderVO.getPorderAddress());
			pstmt.setInt(7, p_orderVO.getPpayment());
			pstmt.setString(8, p_orderVO.getPcreditCard());
			pstmt.setString(9, p_orderVO.getPcreditCardCVV());
			pstmt.setInt(10, p_orderVO.getPshipping());
//			pstmt.setInt(11, p_orderVO.getPorderState());
			pstmt.setInt(11, 0);
			
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_porderNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_porderNo = rs.getString(1);
				System.out.println("自增主鍵值= " + next_porderNo +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			P_order_detailJDBCDAO dao = new P_order_detailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (P_order_detailVO aP_order_detail : list) {
				aP_order_detail.setPorderNo(new Integer(next_porderNo)) ;
				dao.insert2(aP_order_detail,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_porderNo + "時,共有訂單明細" + list.size()
					+ "筆同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-P_order");
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
	public List<P_orderVO> getAll_ByMno(Integer mno) {

		List<P_orderVO> list = new ArrayList<P_orderVO>();
		P_orderVO p_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ByMno_STMT);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				p_orderVO = new P_orderVO();
				p_orderVO.setPorderNo(rs.getInt("pOrderNo"));
				p_orderVO.setMno(rs.getInt("mNo"));
				p_orderVO.setPorderDate(rs.getTimestamp("pOrderDate"));
				p_orderVO.setPorderTotal(rs.getInt("pOrderTotal"));
				p_orderVO.setPorderName(rs.getString("pOrderName"));
				p_orderVO.setPorderPhone(rs.getString("pOrderPhone"));
				p_orderVO.setPorderAddress(rs.getString("pOrderAddress"));
				p_orderVO.setPpayment(rs.getInt("pPayment"));
//				p_orderVO.setPcreditCard(rs.getString("pCreditCard"));
//				p_orderVO.setPcreditCardCVV(rs.getString("pCreditCardCVV"));
				p_orderVO.setPshipping(rs.getInt("pShipping"));
				p_orderVO.setPorderState(rs.getInt("pOrderState"));
				list.add(p_orderVO);
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

		P_orderJDBCDAO dao = new P_orderJDBCDAO();
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		
		P_orderVO p_orderVO = new P_orderVO();
		p_orderVO.setMno(1);
		p_orderVO.setPorderDate(ts);
		p_orderVO.setPorderTotal(5000);
		p_orderVO.setPorderName("陳大華");
		p_orderVO.setPorderPhone("0900854123");
		p_orderVO.setPorderAddress("桃園市桃園區中正路61號");
		p_orderVO.setPpayment(1);
		p_orderVO.setPcreditCard("3210963025807531");
		p_orderVO.setPcreditCardCVV("321");
		p_orderVO.setPshipping(1);
		p_orderVO.setPorderState(2);
		
		List<P_order_detailVO> testList = new ArrayList<P_order_detailVO>(); // 準備置入訂單明細
		P_order_detailVO p_order_detailXX = new P_order_detailVO();   // 訂單明細
		p_order_detailXX.setPno(2);
		p_order_detailXX.setPorderQuantity(3);
		p_order_detailXX.setPprice(1500);
		p_order_detailXX.setPratings(4);


		P_order_detailVO p_order_detailYY = new P_order_detailVO();   // 訂單明細
		p_order_detailYY.setPno(5);
		p_order_detailYY.setPorderQuantity(3);
		p_order_detailYY.setPprice(2400);
		p_order_detailYY.setPratings(3);

		testList.add(p_order_detailXX);
		testList.add(p_order_detailYY);
		
		dao.insertWithPorderDetail(p_orderVO , testList);		
		

		// INSERT_STMT
//		P_orderVO p_orderVO1 = new P_orderVO();
//		p_orderVO1.setMno(11);
//		p_orderVO1.setPorderTotal(8000);
//		p_orderVO1.setPorderName("林零七");
//		p_orderVO1.setPorderPhone("0913365605");
//		p_orderVO1.setPorderAddress("桃園市桃園區中正路61號");
//		p_orderVO1.setPpayment(1);
//		p_orderVO1.setPcreditCard("3210963025807531");
//		p_orderVO1.setPcreditCardCVV("321");
//		p_orderVO1.setPshipping(1);
//		dao.insert(p_orderVO1);

		// UPDATE
//		P_orderVO p_orderVO2 = new P_orderVO();
//		p_orderVO2.setPorderTotal(9500);
//		p_orderVO2.setPorderName("林八");
//		p_orderVO2.setPorderPhone("0913365605");
//		p_orderVO2.setPorderAddress("桃園市桃園區中正路61號");
//		p_orderVO2.setPpayment(1);
//		p_orderVO2.setPcreditCard("3210963025807531");
//		p_orderVO2.setPcreditCardCVV("321");
//		p_orderVO2.setPshipping(1);
//		p_orderVO2.setPorderNo(13);
//		dao.update(p_orderVO2);

		// UPDATE_OrderState
//		P_orderVO p_orderVO3 = new P_orderVO();
//		p_orderVO3.setPorderState(4);
//		p_orderVO3.setPorderNo(13);
//		dao.updateOrderState(p_orderVO3);

		// DELETE
//		dao.delete(13);

		// GET_ONE_STMT
//		P_orderVO p_orderVO4 = dao.findByPrimaryKey(2);
//		System.out.print(p_orderVO4.getPorderNo() + ",");
//		System.out.print(p_orderVO4.getMno() + ",");
//		System.out.print(p_orderVO4.getPorderDate() + ",");
//		System.out.print(p_orderVO4.getPorderTotal() + ",");
//		System.out.print(p_orderVO4.getPorderName() + ",");
//		System.out.print(p_orderVO4.getPorderPhone() + ",");
//		System.out.print(p_orderVO4.getPorderAddress() + ",");
//		System.out.print(p_orderVO4.getPpayment() + ",");
//		System.out.print(p_orderVO4.getPcreditCard() + ",");
//		System.out.print(p_orderVO4.getPcreditCardCVV() + ",");
//		System.out.print(p_orderVO4.getPshipping() + ",");
//		System.out.println(p_orderVO4.getPorderState());
//		System.out.println("-----------------------------------------------------------------------------------------------------------");
	
		// GET_ALL_STMT
//		List<P_orderVO> list2 = dao.getAll();
//		for (P_orderVO aP_order7 : list2) {
//			System.out.print(aP_order7.getPorderNo() + ",");
//			System.out.print(aP_order7.getMno() + ",");
//			System.out.print(aP_order7.getPorderDate() + ",");
//			System.out.print(aP_order7.getPorderTotal() + ",");
//			System.out.print(aP_order7.getPorderName() + ",");
//			System.out.print(aP_order7.getPorderPhone() + ",");
//			System.out.print(aP_order7.getPorderAddress() + ",");
//			System.out.print(aP_order7.getPpayment() + ",");
//			System.out.print(aP_order7.getPcreditCard() + ",");
//			System.out.print(aP_order7.getPcreditCardCVV() + ",");
//			System.out.print(aP_order7.getPshipping() + ",");
//			System.out.println(aP_order7.getPorderState());
//			System.out.println();
//		}		
//		System.out.println("-----------------------------------------------------------------------------------------------------------");		
//		// GET_ALL_ByMno_STMT
//		List<P_orderVO> list = dao.getAll_ByMno(1);
//		for (P_orderVO aP_order5 : list) {
//			System.out.print(aP_order5.getPorderNo() + ",");
//			System.out.print(aP_order5.getMno() + ",");
//			System.out.print(aP_order5.getPorderDate() + ",");
//			System.out.print(aP_order5.getPorderTotal() + ",");
//			System.out.print(aP_order5.getPorderName() + ",");
//			System.out.print(aP_order5.getPorderPhone() + ",");
//			System.out.print(aP_order5.getPorderAddress() + ",");
//			System.out.print(aP_order5.getPpayment() + ",");
//			System.out.print(aP_order5.getPcreditCard() + ",");
//			System.out.print(aP_order5.getPcreditCardCVV() + ",");
//			System.out.print(aP_order5.getPshipping() + ",");
//			System.out.println(aP_order5.getPorderState());
//			System.out.println();
//		}
//		System.out.println("-----------------------------------------------------------------------------------------------------------");
//
//		// GET_ALL_BY_ORDERSTATE
//		List<P_orderVO> list1 = dao.getAll_ByOrderState(0);
//		for (P_orderVO aP_order6 : list1) {
//			System.out.print(aP_order6.getPorderNo() + ",");
//			System.out.print(aP_order6.getMno() + ",");
//			System.out.print(aP_order6.getPorderDate() + ",");
//			System.out.print(aP_order6.getPorderTotal() + ",");
//			System.out.print(aP_order6.getPorderName() + ",");
//			System.out.print(aP_order6.getPorderPhone() + ",");
//			System.out.print(aP_order6.getPorderAddress() + ",");
//			System.out.print(aP_order6.getPpayment() + ",");
//			System.out.print(aP_order6.getPcreditCard() + ",");
//			System.out.print(aP_order6.getPcreditCardCVV() + ",");
//			System.out.print(aP_order6.getPshipping() + ",");
//			System.out.println(aP_order6.getPorderState());
//			System.out.println();
//		}
	}

}
