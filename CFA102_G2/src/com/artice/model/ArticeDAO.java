package com.artice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticeDAO implements ArticeDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?severTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	public static final String INSERT_SQL = 
			"INSERT INTO ARTICLE(articType , articTitle , articContent1 , articPhoto1 , articDate , articState ) value( ? , ? , ? , ? , CURRENT_TIMESTAMP , default )";
	public static final String DELETE_SQL = 
			"DELETE FROM ARTICLE WHERE articNo=?";
	public static final String UPDATE_SQL = 
			"UPDATE ARTICLE SET articType=? , articTitle=? , articContent1=? , articPhoto1=? , articState=? , articDate=CURRENT_TIMESTAMP WHERE articNo=? ";
	public static final String GET_BYARTICNO = 
			"SELECT * FROM ARTICLE WHERE articNo=?  ";
	public static final String GET_BYKEYWORD = 
			"SELECT * FROM ARTICLE " + " WHERE articTitle like ?";
	public static final String GET_ALL = 
			"SELECT * FROM ARTICLE order by articDate DESC";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	
	@Override
	public void insert(ArticeVO articeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setInt(1, articeVO.getArticType());
			pstmt.setString(2, articeVO.getArticTitle());
			pstmt.setString(3, articeVO.getArticContent());
			pstmt.setBytes(4, articeVO.getArticPhoto());
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
	public void delete(Integer articNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);
			
			pstmt.setInt(1, articNo);
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
	public void update(ArticeVO articeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setInt(1, articeVO.getArticType());
			pstmt.setString(2, articeVO.getArticTitle());
			pstmt.setString(3, articeVO.getArticContent());
			pstmt.setBytes(4, articeVO.getArticPhoto());
			pstmt.setInt(5, articeVO.getArticState());
			pstmt.setInt(6, articeVO.getArticNo());
			
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
	public ArticeVO findbyarticNo(Integer articNo) {
		ArticeVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BYARTICNO);
			
			pstmt.setInt(1, articNo);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				artVO = new ArticeVO();
				artVO.setArticNo(rs.getInt("articNo"));
				artVO.setArticTitle(rs.getString("articTitle"));
				artVO.setArticContent(rs.getString("articContent1"));
				artVO.setArticPhoto(rs.getBytes("articPhoto1"));
				artVO.setArticDate(rs.getTimestamp("articDate"));
				artVO.setArticState(rs.getInt("articState"));
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
		return artVO;
	}

	@Override
	public ArticeVO findbykeyword(String articTitle) {
		ArticeVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BYKEYWORD);
			
			pstmt.setString(1, "%" + articTitle + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artVO = new ArticeVO();
				artVO.setArticNo(rs.getInt("articNo"));
				artVO.setArticTitle(rs.getString("articTitle"));
				artVO.setArticDate(rs.getTimestamp("articDate"));
				artVO.setArticType(rs.getInt("articType"));
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
		return artVO;
	}

	@Override
	public List<ArticeVO> getall() {
		List<ArticeVO> list = new ArrayList<ArticeVO>();
		ArticeVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artVO = new ArticeVO();
				artVO.setArticNo(rs.getInt("articNo"));
				artVO.setArticTitle(rs.getString("articTitle"));
				artVO.setArticContent(rs.getString("articContent1"));
				artVO.setArticType(rs.getInt("articType"));
				artVO.setArticDate(rs.getTimestamp("articDate"));
				artVO.setArticPhoto(rs.getBytes("articPhoto1"));
				artVO.setArticState(rs.getInt("articState"));
				list.add(artVO);
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
