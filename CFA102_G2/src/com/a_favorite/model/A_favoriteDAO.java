package com.a_favorite.model;

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

public class A_favoriteDAO implements A_favoriteDAO_interface {
	
//	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?severTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	private static final String INSERT_SQL = 
			"INSERT INTO A_FAVORITE(MNO , ARTICNO) VALUES(? , ?)";
	private static final String DELETE_SQL = 
			"DELETE FROM A_FAVORITE WHERE ARTICNO=? ";
	private static final String GET_BYMNO_SQL = 
			"SELECT * FROM A_FAVORITE WHERE mNo=?";
	private static final String GET_BYARTICNO_SQL=
			"SELECT * FROM A_FAVORITE WHERE articNo=?";
	private static final String GET_ALL_SQL = 
			"SELECT * FROM A_FAVORITE ORDER BY articNo";
	private static final String GET_COUNT_SQL = 
			"SELECT COUNT(articNo) AS collect FROM A_FAVORITE WHERE articNo=?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(A_favoriteVO a_favoriteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setInt(1, a_favoriteVO.getMno());
			pstmt.setInt(2, a_favoriteVO.getArticNo());
			
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
	public List<A_favoriteVO> findbymNO(Integer Mno) {
		List<A_favoriteVO> list = new ArrayList<A_favoriteVO>();
		A_favoriteVO afVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BYMNO_SQL);
			
			pstmt.setInt(1, Mno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				afVO = new A_favoriteVO();
				afVO.setMno(Mno);
				afVO.setArticNo(rs.getInt("articNo"));
				list.add(afVO);
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
	public List<A_favoriteVO> findbyarticNo(Integer articNo) {
		List<A_favoriteVO> list = new ArrayList<A_favoriteVO>();
		A_favoriteVO afVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_BYARTICNO_SQL);
			
			pstmt.setInt(1, articNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				afVO = new A_favoriteVO();
				afVO.setArticNo(rs.getInt("articNo"));
				afVO.setMno(rs.getInt("Mno"));
				list.add(afVO);
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
	public List<A_favoriteVO> getall() {
		List<A_favoriteVO>  list = new ArrayList<A_favoriteVO>();
		A_favoriteVO a_fav = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				a_fav = new A_favoriteVO();
				a_fav.setArticNo(rs.getInt("articNo"));
				a_fav.setMno(rs.getInt("Mno"));
				list.add(a_fav);
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
	public int countfac(Integer articNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int collect = 0;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_COUNT_SQL);
			pstmt.setInt(1, articNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				collect = rs.getInt("collect");
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
		return collect;
	}
	
	
}
