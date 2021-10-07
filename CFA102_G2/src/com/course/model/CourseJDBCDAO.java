package com.course.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class CourseJDBCDAO implements CourseDAO_interface {
	private static final String INSERT = "INSERT INTO COURSE(dNo,cName,cPrice,cShelfDate,cIntroduction,cType,cPic,cDescription)VALUES(?,?,?,NOW(),?,?,?,?)";
	private static final String UPDATE = "UPDATE COURSE SET cName=?,cPrice=?,cIntroduction=?,cPic=?,cDescription=?,cState= ? WHERE cNo=?";
	private static final String STATE = "UPDATE COURSE SET cState=? WHERE cNo=?";
	private static final String FIND_BY_CNO = "SELECT * FROM COURSE WHERE cNo=?";
	private static final String FIND_BY_DNO = "SELECT * FROM COURSE WHERE dNO=?";
	private static final String FIND_BY_CNAME = "SELECT * FROM COURSE WHERE cName LIKE ? ORDER BY cNO";
	private static final String FIND_BY_CTYPE = "SELECT * FROM COURSE WHERE cType=?";
//	private static final String FIND_BY_CTOTAL_SCORE="SELECT * FROM COURSE";
	private static final String GET_ALL = "SELECT * FROM COURSE";// v
	private static final String QUANTITY ="UPDATE COURSE SET QUANTITY=? WHERE cNo=?";
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(CourseVO courseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String[] cols= {"cNo"};
			pstmt = con.prepareStatement(INSERT,cols);//自增主鍵值
			pstmt.setInt(1, courseVO.getDno());
			pstmt.setString(2, courseVO.getCname());
			pstmt.setInt(3, courseVO.getCprice());
			pstmt.setString(4, courseVO.getCintroduction());
			pstmt.setInt(5, courseVO.getCtype());
			pstmt.setBytes(6, courseVO.getCpic());
			pstmt.setString(7, courseVO.getCdescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();// 錯誤
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
	public void update(CourseVO courseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, courseVO.getCname());
			pstmt.setInt(2, courseVO.getCprice());
			pstmt.setString(3, courseVO.getCintroduction());
			pstmt.setBytes(4, courseVO.getCpic());
			pstmt.setString(5, courseVO.getCdescription());
			pstmt.setInt(6, courseVO.getCstate());
			pstmt.setInt(7, courseVO.getCno());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
	public void cState(Integer cState, Integer cno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(STATE);
			pstmt.setInt(1, cState);
			pstmt.setInt(2, cno);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
	public CourseVO getOneBy_cNO(Integer cno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CourseVO courseVO = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_CNO);
			pstmt.setInt(1, cno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				courseVO = new CourseVO();
				courseVO.setCno(cno);
				courseVO.setDno(rs.getInt("dNo"));
				courseVO.setCname(rs.getString("cName"));
				courseVO.setCprice(rs.getInt("cPrice"));
				courseVO.setCstate(rs.getInt("cState"));
				courseVO.setCshelfDate(rs.getTimestamp("cShelfDate"));
				courseVO.setCintroduction(rs.getString("cIntroduction"));
				courseVO.setCtype(rs.getInt("cType"));
				courseVO.setQuantity(rs.getInt("quantity"));
				courseVO.setCpic(rs.getBytes("cPic"));
				courseVO.setCdescription(rs.getString("cDescription"));
				courseVO.setCtotalPeople(rs.getInt("cTotalPeople"));
				courseVO.setCtotalScore(rs.getInt("cTotalScore"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // TODO Auto-generated method stub

		}
		return courseVO;
	}

	@Override
	public List<CourseVO> getBy_dNo(Integer dno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> courseList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_DNO);
			pstmt.setInt(1, dno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CourseVO courseVO = new CourseVO();
				courseVO.setCno(rs.getInt("cNo"));
				courseVO.setDno(dno);
				courseVO.setCname(rs.getString("cName"));
				courseVO.setCprice(rs.getInt("cPrice"));
				courseVO.setCstate(rs.getInt("cState"));
				courseVO.setCshelfDate(rs.getTimestamp("cShelfDate"));
				courseVO.setCintroduction(rs.getString("cIntroduction"));
				courseVO.setCtype(rs.getInt("cType"));
				courseVO.setQuantity(rs.getInt("quantity"));
				courseVO.setCpic(rs.getBytes("cPic"));
				courseVO.setCdescription(rs.getString("cDescription"));
				courseVO.setCtotalPeople(rs.getInt("cTotalPeople"));
				courseVO.setCtotalScore(rs.getInt("cTotalScore"));
				courseList.add(courseVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // TODO Auto-generated method stub

		}

		return courseList;
	}

	@Override
	public List<CourseVO> getBy_cName(String cName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> courseList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_CNAME);
			pstmt.setString(1, "%" + cName + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CourseVO courseVO = new CourseVO();
				courseVO.setCno(rs.getInt("cNo"));
				courseVO.setDno(rs.getInt("dNo"));
				courseVO.setCname(rs.getString("cName"));
				courseVO.setCprice(rs.getInt("cPrice"));
				courseVO.setCstate(rs.getInt("cState"));
				courseVO.setCshelfDate(rs.getTimestamp("cShelfDate"));
				courseVO.setCintroduction(rs.getString("cIntroduction"));
				courseVO.setCtype(rs.getInt("cType"));
				courseVO.setQuantity(rs.getInt("quantity"));
				courseVO.setCpic(rs.getBytes("cPic"));
				courseVO.setCdescription(rs.getString("cDescription"));
				courseVO.setCtotalPeople(rs.getInt("cTotalPeople"));
				courseVO.setCtotalScore(rs.getInt("cTotalScore"));
				courseList.add(courseVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // TODO Auto-generated method stub

		}

		return courseList;
	}

	@Override
	public List<CourseVO> getBy_cTotalScore(Integer score) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> courseList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CourseVO courseVO = new CourseVO();
				courseVO.setCno(rs.getInt("cNo"));
				courseVO.setDno(rs.getInt("dNo"));
				courseVO.setCname(rs.getString("cName"));
				courseVO.setCprice(rs.getInt("cPrice"));
				courseVO.setCstate(rs.getInt("cState"));
				courseVO.setCshelfDate(rs.getTimestamp("cShelfDate"));
				courseVO.setCintroduction(rs.getString("cIntroduction"));
				courseVO.setCtype(rs.getInt("cType"));
				courseVO.setQuantity(rs.getInt("quantity"));
//				courseVO.setCpic(rs.getBytes("cPic"));
				courseVO.setCdescription(rs.getString("cDescription"));
				courseVO.setCtotalPeople(rs.getInt("cTotalPeople"));
				courseVO.setCtotalScore(rs.getInt("cTotalScore"));
				courseList.add(courseVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // TODO Auto-generated method stub

		}

		return courseList;
	}

	@Override
	public List<CourseVO> getBy_cType(Integer cType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> courseList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_CTYPE);
			pstmt.setInt(1, cType);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CourseVO courseVO = new CourseVO();
				courseVO.setCno(rs.getInt("cNo"));
				courseVO.setDno(rs.getInt("dNo"));
				courseVO.setCname(rs.getString("cName"));
				courseVO.setCprice(rs.getInt("cPrice"));
				courseVO.setCstate(rs.getInt("cState"));
				courseVO.setCshelfDate(rs.getTimestamp("cShelfDate"));
				courseVO.setCintroduction(rs.getString("cIntroduction"));
				courseVO.setCtype(cType);
				courseVO.setQuantity(rs.getInt("quantity"));
				courseVO.setCpic(rs.getBytes("cPic"));
				courseVO.setCdescription(rs.getString("cDescription"));
				courseVO.setCtotalPeople(rs.getInt("cTotalPeople"));
				courseVO.setCtotalScore(rs.getInt("cTotalScore"));
				courseList.add(courseVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // TODO Auto-generated method stub

		}

		return courseList;
	}
	public void updateQuantity(CourseVO courseVO) {
		
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				pstmt = con.prepareStatement(QUANTITY);
				pstmt.setInt(1, courseVO.getQuantity()+1);
				pstmt.setInt(2, courseVO.getCno());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
	public byte[] getImg(Integer cno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] cimg = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_CNO);
			pstmt.setInt(1, cno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cimg = rs.getBytes("cpic");
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
		return cimg;
	}
}
