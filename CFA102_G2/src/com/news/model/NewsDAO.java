package com.news.model;

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

public class NewsDAO implements NewsDAO_interface{
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?severTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	private static final String INSERT_SQL = "INSERT INTO NEWS(newsTitle , newsContent , newsDate , newsState) value(? , ? , CURRENT_TIMESTAMP  , default)";
	private static final String UPDATE_SQL = "UPDATE NEWS SET newsTitle=? , newsContent=? , newsDate=CURRENT_TIMESTAMP , newsState=default  WHERE newsNo=?";
	private static final String FIND_ONE_SQL = "SELECT * FROM NEWS WHERE newsNo=?";
	private static final String GET_ALL_SQL = "SELECT * FROM NEWS order by newsDate DESC";
	public static final String DELETE_SQL = 
			"DELETE FROM NEWS WHERE newsNo=?";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, newsVO.getNewsTitle());
			pstmt.setString(2, newsVO.getNewsContent());
//			pstmt.setInt(3, newsVO.getNewsNo());
//			pstmt.setInt(4, newsVO.getNewsType());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	public void update(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setString(1, newsVO.getNewsTitle());
			pstmt.setString(2, newsVO.getNewsContent());
			pstmt.setInt(3, newsVO.getNewsNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	public NewsVO findByPrimaryKey(Integer newsNo) {
		NewsVO NewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(FIND_ONE_SQL);
			pstmt.setInt(1, newsNo);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				NewsVO = new NewsVO();
				NewsVO.setNewsNo(rs.getInt("newsNo"));
				NewsVO.setNewsTitle(rs.getString("newsTitle"));
				NewsVO.setNewsContent(rs.getString("newsContent"));
				NewsVO.setNewsDate(rs.getTimestamp("newsDate"));
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
		return NewsVO;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO NewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NewsVO = new NewsVO();
				NewsVO.setNewsNo(rs.getInt("newsNO"));
				NewsVO.setNewsTitle(rs.getString("newsTitle"));
				NewsVO.setNewsContent(rs.getString("newsContent"));
				NewsVO.setNewsDate(rs.getTimestamp("newsDate"));
				
				list.add(NewsVO);
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

	@Override
	public void delete(Integer newsNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);
			
			pstmt.setInt(1, newsNo);
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
}
