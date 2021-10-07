package com.c_order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class C_Order_DetailJDBCDAO implements C_Order_DetailDAO_interface {
	private static final String INSERT = "INSERT INTO C_ORDER_DETAIL(cOrderNo,cNo,cPrice) VALUES(?,?,?)";
	private static final String UPDATE = "UPDATE C_ORDER_DETAIL SET cEvaluation=?,cReviews=?,cProgress=? WHERE cOrderNo=? AND cNo=?";
	private static final String DELETE = "DELETE FROM C_ORDER_DETAIL WHERE cOrderNo=? AND cNo=?";
	private static final String GETONE = "SELECT * FROM C_ORDER_DETAIL WHERE cOrderNo=? AND cNo=?";
	private static final String GETALL_BYCORDERNO = "SELECT*FROM C_ORDER_DETAIL WHERE cOrderNo=?";
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(C_Order_DetailVO cOrderDetail) {
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
			pstmt.setInt(1, cOrderDetail.getCorderno());
			pstmt.setInt(2, cOrderDetail.getCno());
			pstmt.setInt(3, cOrderDetail.getCprice());

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
	public void update(C_Order_DetailVO cOrderDetail) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, cOrderDetail.getCevaluation());
			pstmt.setString(2, cOrderDetail.getCreviews());
			pstmt.setInt(3, cOrderDetail.getCprogress());
			pstmt.setInt(4, cOrderDetail.getCorderno());
			pstmt.setInt(5, cOrderDetail.getCno());
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
	public void delete(C_Order_DetailVO cOrderDetail) {
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
			pstmt.setInt(1, cOrderDetail.getCorderno());
			pstmt.setInt(2, cOrderDetail.getCno());

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
	public C_Order_DetailVO getOne(Integer corderno, Integer cno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		C_Order_DetailVO codVO = null;

		try {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GETONE);
			pstmt.setInt(1, corderno);
			pstmt.setInt(2, cno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				codVO = new C_Order_DetailVO();
				codVO.setCorderno(corderno);
				codVO.setCno(rs.getInt(cno));
				codVO.setCprice(rs.getInt("cPrice"));
				codVO.setCevaluation(rs.getInt("cEvaluation"));
				codVO.setCreviews(rs.getString("cReviews"));
				codVO.setCprogress(rs.getInt("cProgress"));

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

		return codVO;
	}

	@Override
	public List<C_Order_DetailVO> getAll(Integer corderno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<C_Order_DetailVO> codVOList = new ArrayList<>();

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GETALL_BYCORDERNO);
			pstmt.setInt(1, corderno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				C_Order_DetailVO codVO = new C_Order_DetailVO();
				codVO.setCorderno(rs.getInt("cOrderNo"));
				codVO.setCno(rs.getInt("cNo"));
				codVO.setCprice(rs.getInt("cPrice"));
				codVO.setCevaluation(rs.getInt("cEvaluation"));
				codVO.setCreviews(rs.getString("cReviews"));
				codVO.setCprogress(rs.getInt("cProgress"));
				codVOList.add(codVO);
				
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

		return codVOList;
	}

	public static void main(String[] args) {
		C_Order_DetailDAO_interface dao = new C_Order_DetailJDBCDAO();
		C_Order_DetailVO VO = new C_Order_DetailVO();

		VO.setCorderno(2);
		VO.setCno(1);
		VO.setCprice(1000);
		VO.setCevaluation(2);
		VO.setCreviews("教很好");
		VO.setCprogress(100);

//	dao.insert(VO);
//		dao.update(VO);
//		dao.delete(VO);
//		dao.getOne(2,1);
//		dao.getAll(10);
		System.out.println(dao);

	}

	@Override
	public void insert(C_Order_DetailVO cOrderDetail, Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, cOrderDetail.getCorderno());
			pstmt.setInt(2, cOrderDetail.getCno());
			pstmt.setInt(3, cOrderDetail.getCprice());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-detail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}

	}

}
