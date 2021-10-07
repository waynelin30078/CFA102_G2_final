package com.c_order.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.c_order_detail.model.C_Order_DetailJDBCDAO;
import com.c_order_detail.model.C_Order_DetailVO;


import util.Util;

public class C_OrderJDBCDAO implements C_OrderDAO_interface {
	private static final String INSERT = "INSERT INTO C_ORDER(mNo,orderDate,total,paymentMethod,paymentInfo) VALUES(?,NOW(),?,?,?)";
	private static final String UPDATE = "UPDATE C_ORDER SET total=?,paymentMethod=?, paymentInfo=?, orderState=? WHERE cOrderNo=?";
//	private static final String UPDATE_PAYMENT = "UPDATE C_ORDER SET paymentMethod=?,paymentInfo=? WHERE cOrderNo=?";
	private static final String GETONE = "SELECT * FROM C_ORDER WHERE cOrderNo=?";
	private static final String GETALL = "SELECT * FROM C_ORDER";
	private static final String GETALL_BYMNO = "SELECT * FROM C_ORDER WHERE mNO=?";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}
	}

	@Override
	public void insert(C_OrderVO c_order) {
		Connection con = null;
		PreparedStatement pstmt = null;
		// paymentMethod,paymentInfo
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, c_order.getMno());
			pstmt.setInt(2, c_order.getTotal());
			pstmt.setInt(3, c_order.getPaymentMethod());
			pstmt.setString(4, c_order.getPaymentInfo());
			pstmt.execute();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(C_OrderVO c_order) {
		Connection con = null;
		PreparedStatement pstmt = null;
		// paymentMethod,paymentInfo
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, c_order.getTotal());
			pstmt.setInt(2, c_order.getPaymentMethod());
			pstmt.setString(3, c_order.getPaymentInfo());
			pstmt.setInt(4, c_order.getOrderState());
			pstmt.setInt(5, c_order.getCorderno());
			pstmt.execute();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

//	@Override
//	public void updatePayment(C_OrderVO c_order) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		// paymentMethod,paymentInfo
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE_PAYMENT);
//
//			pstmt.setInt(1, c_order.getPaymentMethod());
//			pstmt.setString(2, c_order.getPaymentInfo());
//			pstmt.setInt(3, c_order.getCorderno());
//
//			pstmt.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}

	@Override
	public C_OrderVO getOne(Integer cOrderno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		C_OrderVO cOrderVO = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GETONE);
			pstmt.setInt(1, cOrderno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cOrderVO = new C_OrderVO();
				cOrderVO.setCorderno(cOrderno);
				cOrderVO.setMno(rs.getInt("mNo"));
				cOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
				;
				cOrderVO.setTotal(rs.getInt("total"));
				cOrderVO.setOrderState(rs.getInt("orderState"));

				cOrderVO.setPaymentMethod(rs.getInt("paymentMethod"));
				cOrderVO.setPaymentInfo(rs.getString("paymentInfo"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return cOrderVO;
	}

	@Override
	public List<C_OrderVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<C_OrderVO> cOrderList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				C_OrderVO cOrderVO = new C_OrderVO();
				cOrderVO.setCorderno(rs.getInt("cOrderNo"));
				cOrderVO.setMno(rs.getInt("mNo"));
				cOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
				;
				cOrderVO.setTotal(rs.getInt("total"));
				cOrderVO.setOrderState(rs.getInt("orderState"));
				cOrderVO.setPaymentMethod(rs.getInt("paymentMethod"));
				cOrderVO.setPaymentInfo(rs.getString("paymentInfo"));
				cOrderList.add(cOrderVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return cOrderList;
	}

	@Override
	public List<C_OrderVO> getAll(Integer mNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<C_OrderVO> cOrderList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GETALL_BYMNO);
			pstmt.setInt(1, mNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				C_OrderVO cOrderVO = new C_OrderVO();
				cOrderVO.setCorderno(rs.getInt("cOrderNo"));
				cOrderVO.setMno(rs.getInt(mNo));
				cOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
				cOrderVO.setTotal(rs.getInt("total"));
				cOrderVO.setOrderState(rs.getInt("orderState"));
				cOrderVO.setPaymentMethod(rs.getInt("paymentMethod"));
				cOrderVO.setPaymentInfo(rs.getString("paymentInfo"));
				cOrderList.add(cOrderVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return cOrderList;
		
		
		
	}

	@Override
	public void insertWithDetail(C_OrderVO c_OrderVO, List<C_Order_DetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		 try {
			 //取得連線
		//已於前面取得	 Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL,Util.USER,Util.PASSWORD);
			//關閉自動交易
			con.setAutoCommit(false);
			//綁定自增主鍵值
			int total = 0 ;
			for(C_Order_DetailVO corderDetail:list) {
				total +=  corderDetail.getCprice();
			}
			
			String clos[] = {"cOrderNo"};
			pstmt = con.prepareStatement(INSERT,clos);
			pstmt.setInt(1, c_OrderVO.getMno());
			pstmt.setInt(2,total);
			pstmt.setInt(3, c_OrderVO.getPaymentMethod());
			pstmt.setString(4, c_OrderVO.getPaymentInfo());
			pstmt.executeUpdate();
			//取得剛剛建立的主鍵值並設定給detail的FK
			String next_orderno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_orderno = rs.getString(1);
				System.out.println("自增主鍵值= " + next_orderno +"(剛新增成功的訂單編號)");
			}else {
				System.out.println("未取得新增的主鍵值");
			}
			rs.close();
			//再同時新增明細
			C_Order_DetailJDBCDAO dao = new C_Order_DetailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for(C_Order_DetailVO detailVO:list) {
				detailVO.setCorderno(new Integer(next_orderno));
				dao.insert(detailVO, con);
			}
			//新增完明細後commit
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_orderno + "時,共有" + list.size()
					+ "個明細同時被新增");
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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


//	public static void main(String[] args) {
//		C_OrderJDBCDAO dao = new C_OrderJDBCDAO();
//		C_OrderVO VO = new C_OrderVO();
//
//		VO.setCorderno(2);
//		VO.setMno(1);
//		VO.setOrderDate((java.sql.Timestamp.valueOf("9999-12-31 23:59:59")));
//		VO.setTotal(5000);
//		VO.setOrderState(3);
//		VO.setPaymentMethod(2);
//		VO.setPaymentInfo("1221-1111-1111");
//		
//		List<C_Order_DetailVO>testList = new ArrayList<C_Order_DetailVO>();
//		C_Order_DetailVO detail1 = new C_Order_DetailVO();
//		detail1.setCno(1);
//		detail1.setCprice(new Integer(2000));
//		
//		C_Order_DetailVO detail2 = new C_Order_DetailVO();
//		detail2.setCno(2);
//		detail2.setCprice(new Integer(3000));
//		
//		testList.add(detail1);
//		testList.add(detail2);
//		System.out.println(testList);
//		dao.insertWithDetail(VO, testList);
		
//		dao.insert(VO);
	

//		dao.updateOrderState(VO);
//		dao.updatePayment(VO);
//		dao.getOne(1);
//		List<C_OrderVO> vList =dao.getAll(1);
//		for(C_OrderVO v:vList)
//			System.out.println(v);
//
//		System.out.println(dao);

//	}

}
