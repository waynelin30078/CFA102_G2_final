package com.appointment.model;

import java.util.*;
import java.sql.*;

public class AppointmentJDBCDAO implements AppointmentDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO APPOINTMENT (DNO,RDATE,RSTATE) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT APTNO,DNO,RDATE,RSTATE FROM APPOINTMENT ORDER BY APTNO";
	private static final String GET_ONE_STMT = "SELECT APTNO,DNO,RDATE,RSTATE FROM APPOINTMENT where aptno = ?";
	private static final String DELETE = "DELETE FROM APPOINTMENT where APTNO = ?";
	private static final String UPDATE = "UPDATE APPOINTMENT set DNO=?, RDATE=?, RSTATE=? WHERE APTNO = ?";
	private static final String creatDate = " INSERT INTO appointment(dno, rdate, rstate)\r\n" + 
			"WITH RECURSIVE dates (serchdate) AS\r\n" + 
			"(\r\n" + 
			"   SELECT CURDATE()\r\n" + 
			"   UNION ALL\r\n" + 
			"   SELECT serchdate + INTERVAL 1 DAY \r\n" + 
			"   FROM dates\r\n" + 
			"   WHERE serchdate + INTERVAL 1 DAY <= ADDDATE(CURDATE(), INTERVAL 15 DAY)\r\n" + 
			") \r\n" + 
			"SELECT b.dno, d.serchdate, IF(substr(b.offday, weekday(d.serchdate)+1,1) ='1', b.opttime, repeat('2',24))  rstate \r\n" + 
			"   FROM dates d \r\n" + 
			"   JOIN dietician b\r\n" + 
			"   LEFT JOIN appointment r on (d.serchdate = r.rdate AND b.dno = r.dno)\r\n" + 
			"   WHERE r.rdate IS NULL";
	@Override
	public void insert(AppointmentVO appointmentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, appointmentVO.getDno());
			pstmt.setDate(2, appointmentVO.getRdate());
			pstmt.setString(3, appointmentVO.getRstate());

			pstmt.executeUpdate();
		}catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}catch (SQLException se) {
			System.out.println(se.getMessage());
		}finally {
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
	public void update(AppointmentVO appointmentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setInt(1, appointmentVO.getDno());
				pstmt.setDate(2, appointmentVO.getRdate());
				pstmt.setString(3, appointmentVO.getRstate());
				pstmt.setInt(4, appointmentVO.getAptNo());
				
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
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
	public void delete(Integer aptNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, aptNo);
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
	public AppointmentVO findByPrimaryKey(Integer aptNo) {
		
		AppointmentVO appointmentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, aptNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				appointmentVO = new AppointmentVO();
				appointmentVO.setAptNo(rs.getInt("aptNo"));
				appointmentVO.setDno(rs.getInt("dNo"));
				appointmentVO.setRdate(rs.getDate("rDate"));
				appointmentVO.setRstate(rs.getString("rState"));
				
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
		
		
		return appointmentVO;
	}

	@Override
	public List<AppointmentVO> getAll() {
		List<AppointmentVO> list = new ArrayList<AppointmentVO>();
		AppointmentVO appointmentVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				appointmentVO = new AppointmentVO();
				appointmentVO.setAptNo(rs.getInt("aptNo"));
				appointmentVO.setDno(rs.getInt("dNo"));
				appointmentVO.setRdate(rs.getDate("rDate"));
				appointmentVO.setRstate(rs.getString("rState"));
				list.add(appointmentVO);
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
	public void createdDate(){
		List<AppointmentVO> list = new ArrayList<AppointmentVO>();
		AppointmentVO appointmentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(creatDate);
			pstmt.executeUpdate();
			
				

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
		
	}
	
	
	public static void main(String[] args) {
		AppointmentJDBCDAO dao = new AppointmentJDBCDAO();
		//新增
//		AppointmentVO appointMent1 = new AppointmentVO();
//		appointMent1.setDno(1);;
//		appointMent1.setRdate(java.sql.Date.valueOf("2021-09-08"));
//		appointMent1.setRstate("11111222223333333");
//		dao.insert(appointMent1);
//		
		//修改
//		AppointmentVO appointMent2 = new AppointmentVO();
//		appointMent2.setAptNo(1);
//		appointMent2.setDno(2);;
//		appointMent2.setRdate(java.sql.Date.valueOf("2022-01-01"));
//		appointMent2.setRstate("222222222222");
//		dao.update(appointMent2);
		//刪除
//		dao.delete(2);
		//查詢
//		AppointmentVO appointMent3 = dao.findByPrimaryKey(1);
//		System.out.print(appointMent3.getAptNo() + ",");
//		System.out.print(appointMent3.getDno() + ",");
//		System.out.print(appointMent3.getRdate() + ",");
//		System.out.println(appointMent3.getRstate() + ",");
//		System.out.println("------------------");
		//查詢
//		List<AppointmentVO> list = dao.getAll();
//		for (AppointmentVO aapp : list) {
//			System.out.print(aapp.getAptNo() + ",");
//			System.out.print(aapp.getDno() + ",");
//			System.out.print(aapp.getRdate() + ",");
//			System.out.println(aapp.getRstate() + ",");
//		}
		
//		List<AppointmentVO> list = dao.createdDate();
//		for (AppointmentVO aapp : list) {
//
//			System.out.print(aapp.getDno() + ",");
//			System.out.print(aapp.getSerchdate() + ",");
//			System.out.println(aapp.getRstate() + ",");
//		}
//		
	}



}
