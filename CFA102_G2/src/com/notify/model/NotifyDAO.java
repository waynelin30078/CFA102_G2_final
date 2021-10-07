package com.notify.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class NotifyDAO implements NotifyDAO_interface{
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?severTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	private static final String INSERT_SQL= "INSERT INTO notify(notiNO , notiType , notiContent , notiTime , mNO , dNO) value(? , ? , ? , ? , ? ,?)";
	private static final String DELETE_SQL = "DELETE FROM notify WHERE notiNO=? ";
	private static final String UPDATE_SQL = "UPDATE notify SET mNO=? , dNO=? , notiContent=? , notiTime=? WHERE notiNO=?";
	private static final String FINDBYPK_SQL = "SELECT * FROM notify WHERE notiNO=?";
	private static final String GETALL_SQL = "SELECT * FROM notify";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(NotifyVO notifyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			
			pstmt.setInt(1, notifyVO.getdNO());
			pstmt.setInt(2, notifyVO.getNotiType());
			pstmt.setString(3, notifyVO.getNotiContent());
			pstmt.setTimestamp(4, notifyVO.getNotiTime());
			pstmt.setInt(5, notifyVO.getmNO());
			pstmt.setInt(6, notifyVO.getdNO());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer notiNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL , USER , PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);
			
			pstmt.setInt(1, notiNo);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(NotifyVO notifyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setInt(1, notifyVO.getmNO());
			pstmt.setInt(2, notifyVO.getdNO());
			pstmt.setString(3, notifyVO.getNotiContent());
			pstmt.setTimestamp(4, notifyVO.getNotiTime());
			pstmt.setInt(5, notifyVO.getNotiNO());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public NotifyVO findbyPK(Integer notiNO) {
		NotifyVO NotifyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FINDBYPK_SQL);
			pstmt.setInt(1, notiNO);
			rs = pstmt.executeQuery();
			while(rs.next()){
				NotifyVO = new NotifyVO();
				NotifyVO.setdNO(rs.getInt("dNO"));
				NotifyVO.setmNO(rs.getInt("mNO"));
				NotifyVO.setNotiType(rs.getInt("notiType"));
				NotifyVO.setNotiContent(rs.getString("notiContent"));
				NotifyVO.setNotiTime(rs.getTimestamp("notiTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs!= null) {
				try {
					rs.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return NotifyVO;
	}

	@Override
	public List<NotifyVO> getall() {
		List<NotifyVO> list = new ArrayList<NotifyVO>();
		NotifyVO NotifyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt= con.prepareStatement(GETALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NotifyVO = new NotifyVO();
				NotifyVO.setdNO(rs.getInt("dNO"));
				NotifyVO.setmNO(rs.getInt("mNO"));
				NotifyVO.setNotiNO(rs.getInt("notiNO"));
				NotifyVO.setNotiContent(rs.getString("notiContent"));
				NotifyVO.setNotiType(rs.getInt("notiType"));
				NotifyVO.setNotiTime(rs.getTimestamp("notiTime"));
				list.add(NotifyVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs!= null) {
				try {
					rs.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return list;
	}
}
