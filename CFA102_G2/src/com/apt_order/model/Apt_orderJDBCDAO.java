package com.apt_order.model;

import java.util.List;

import com.appointment.model.AppointmentVO;

import java.util.*;
import java.sql.*;

public class Apt_orderJDBCDAO implements Apt_orderDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO APT_ORDER (MNO,DNO,ORDERTIME,ORDERDATE,CLPRICE,CLSTATE,APTREVIEWS) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT APTORDERNO,MNO,DNO,ORDERTIME,ORDERDATE,CLPRICE,CLSTATE,APTREVIEWS FROM APT_ORDER ORDER BY APTORDERNO";
	private static final String GET_ONE_STMT = "SELECT APTORDERNO,MNO,DNO,ORDERTIME,ORDERDATE,CLPRICE,CLSTATE,APTREVIEWS FROM APT_ORDER where APTORDERNO = ?";
	private static final String DELETE = "DELETE FROM APT_ORDER where APTORDERNO = ?";
	private static final String UPDATE = "UPDATE APT_ORDER set MNO=?, DNO=?, ORDERTIME=?, ORDERDATE=?, CLPRICE=?, CLSTATE=?, APTREVIEWS=? WHERE APTORDERNO = ?";
	private static final String FIND_BY_MNO = "SELECT APTORDERNO,MNO,DNO,ORDERTIME,ORDERDATE,CLPRICE,CLSTATE,APTREVIEWS FROM APT_ORDER where MNO = ?";
	@Override
	public void insert(Apt_orderVO apt_orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, apt_orderVO.getMno());
			pstmt.setInt(2, apt_orderVO.getDno());
			pstmt.setTimestamp(3, apt_orderVO.getOrderTime());
			pstmt.setTimestamp(4, apt_orderVO.getOrderDate());
			pstmt.setInt(5, apt_orderVO.getClPrice());
			pstmt.setInt(6, apt_orderVO.getClState());
			pstmt.setString(7, apt_orderVO.getAptReviews());
			

			pstmt.executeUpdate();
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(Apt_orderVO apt_orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, apt_orderVO.getMno());
			pstmt.setInt(2, apt_orderVO.getDno());
			pstmt.setTimestamp(3, apt_orderVO.getOrderTime());
			pstmt.setTimestamp(4, apt_orderVO.getOrderDate());
			pstmt.setInt(5, apt_orderVO.getClPrice());
			pstmt.setInt(6, apt_orderVO.getClState());
			pstmt.setString(7, apt_orderVO.getAptReviews());
			pstmt.setInt(8, apt_orderVO.getAptOrderNo());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
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
	public void delete(Integer aptOrderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, aptOrderNo);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public Apt_orderVO findByPrimaryKey(Integer aptOrderNo) {
		Apt_orderVO apt_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, aptOrderNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				apt_orderVO = new Apt_orderVO();
				apt_orderVO.setAptOrderNo(rs.getInt("aptOrderNo"));
				apt_orderVO.setMno(rs.getInt("mNo"));
				apt_orderVO.setDno(rs.getInt("dNo"));
				apt_orderVO.setOrderTime(rs.getTimestamp("orderTime"));
				apt_orderVO.setOrderDate(rs.getTimestamp("orderDate"));
				apt_orderVO.setClPrice(rs.getInt("clPrice"));
				apt_orderVO.setClState(rs.getInt("clState"));
				apt_orderVO.setAptReviews(rs.getString("aptReviews"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return apt_orderVO;
	}
	@Override
	public List<Apt_orderVO> getAll() {
		List<Apt_orderVO> list = new ArrayList<Apt_orderVO>();
		Apt_orderVO apt_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				apt_orderVO = new Apt_orderVO();
				apt_orderVO.setAptOrderNo(rs.getInt("aptOrderNo"));
				apt_orderVO.setMno(rs.getInt("mNo"));
				apt_orderVO.setDno(rs.getInt("dNo"));
				apt_orderVO.setOrderTime(rs.getTimestamp("orderTime"));
				apt_orderVO.setOrderDate(rs.getTimestamp("orderDate"));
				apt_orderVO.setClPrice(rs.getInt("clPrice"));
				apt_orderVO.setClState(rs.getInt("clState"));
				apt_orderVO.setAptReviews(rs.getString("aptReviews"));
				list.add(apt_orderVO);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<Apt_orderVO> findByMnoKey(Integer mno) {
		List<Apt_orderVO> list=new ArrayList<Apt_orderVO>();
		Apt_orderVO apt_orderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt= con.prepareStatement(FIND_BY_MNO);
			pstmt.setInt(1, mno);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				apt_orderVO = new Apt_orderVO();
				apt_orderVO.setAptOrderNo(rs.getInt("aptOrderNo"));
				apt_orderVO.setMno(rs.getInt("mNo"));
				apt_orderVO.setDno(rs.getInt("dNo"));
				apt_orderVO.setOrderTime(rs.getTimestamp("orderTime"));
				apt_orderVO.setOrderDate(rs.getTimestamp("orderDate"));
				apt_orderVO.setClPrice(rs.getInt("clPrice"));
				apt_orderVO.setClState(rs.getInt("clState"));
				apt_orderVO.setAptReviews(rs.getString("aptReviews"));
				list.add(apt_orderVO);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			}}
		return list;
	}
	public static void main(String[] args) {
		Apt_orderJDBCDAO dao = new Apt_orderJDBCDAO();
		
		//�s�W
//		Apt_orderVO apt_orderVO1 = new Apt_orderVO();
//		apt_orderVO1.setMno(1);
//		apt_orderVO1.setDno(2);
//		apt_orderVO1.setOrderTime(java.sql.Timestamp.valueOf("2005-01-01 14:20:00"));
//		apt_orderVO1.setOrderDate(java.sql.Timestamp.valueOf("2005-01-01 14:20:00"));
//		apt_orderVO1.setClPrice(1000);
//		apt_orderVO1.setClState(1);;
//		apt_orderVO1.setAptReviews("�W��");
//		dao.insert(apt_orderVO1);
//		
//		
//		
//		//�ק�
//		Apt_orderVO apt_orderVO2 = new Apt_orderVO();
//		apt_orderVO2.setMno(1);
//		apt_orderVO2.setDno(2);
//		apt_orderVO2.setOrderTime(java.sql.Timestamp.valueOf("2005-01-01 14:20:00"));
//		apt_orderVO2.setOrderDate(java.sql.Timestamp.valueOf("2005-01-01 14:20:00"));
//		apt_orderVO2.setClPrice(1000);
//		apt_orderVO2.setClState(1);;
//		apt_orderVO2.setAptReviews("��L");
//		apt_orderVO2.setAptOrderNo(1);
//		dao.update(apt_orderVO2);
//		//�R��
//		dao.delete(1);
//		//�d��
//		Apt_orderVO apt_orderVO3 = dao.findByPrimaryKey(2);
//		System.out.print(apt_orderVO3.getAptOrderNo() + ",");
//		System.out.print(apt_orderVO3.getMno() + ",");
//		System.out.print(apt_orderVO3.getDno() + ",");
//		System.out.print(apt_orderVO3.getOrderTime() + ",");
//		System.out.print(apt_orderVO3.getOrderDate() + ",");
//		System.out.print(apt_orderVO3.getClPrice() + ",");
//		System.out.print(apt_orderVO3.getClState() + ",");
//		System.out.println(apt_orderVO3.getAptReviews());
//		System.out.println("----------------------");
//		
//		//�d��
		List<Apt_orderVO> list = dao.getAll();
		for (Apt_orderVO apt_orderVO3 : list) {
			System.out.print(apt_orderVO3.getAptOrderNo() + ",");
			System.out.print(apt_orderVO3.getMno() + ",");
			System.out.print(apt_orderVO3.getDno() + ",");
			System.out.print(apt_orderVO3.getOrderTime() + ",");
			System.out.print(apt_orderVO3.getOrderDate() + ",");
			System.out.print(apt_orderVO3.getClPrice() + ",");
			System.out.print(apt_orderVO3.getClState() + ",");
			System.out.print(apt_orderVO3.getAptReviews());
			System.out.println();
		}
	}


		
	
}
