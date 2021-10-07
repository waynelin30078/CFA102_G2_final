package com.member.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class MemberJDBCDAO implements MemberDAO_interface{
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	public static final String userid = "David";
	public static final String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO MEMBER (mName ,mId ,mPsw ,mMail ,mPhone ,mSex )"
			+ "VALUES (?, ?, ?, ?, ?, ? )";
	private static final String GET_ALL_STMT = "SELECT mNo ,mName ,mId ,mPsw ,mMail ,mPhone ,mImg ,mBday ,mSex ,mIntro ,mState ,cardID ,cardDate ,cardNum ,dNo ,dailyCal ,dailyCho ,dailyPro ,dailyFat ,dietPlan FROM MEMBER ORDER BY mNo";
	private static final String GET_ONE_STMT = "SELECT mNo ,mName ,mId ,mPsw ,mMail ,mPhone ,mImg ,mBday ,mSex ,mIntro ,mState ,cardID ,cardDate ,cardNum ,dNo ,dailyCal ,dailyCho ,dailyPro ,dailyFat ,dietPlan FROM MEMBER where mNo = ?";
	private static final String UPDATE = "UPDATE MEMBER set mName=? ,mPsw=? ,mMail=? ,mPhone=? ,mImg=? ,mBday=? ,mSex=? ,mIntro=?  WHERE mNo = ?";
	private static final String GET_IMG = "SELECT MIMG FROM MEMBER where mno = ?";
	private static final String GetUser = "SELECT * FROM MEMBER WHERE `MID`= ? AND `MPSW`= ?";
	private static final String GET_ONE_STMT_BY_MID = "SELECT * FROM MEMBER WHERE `MID` = ?";
	private static final String UPDATEMSTATE="update member set mstate=? where mno = ?";
	private static final String UPDATEPASSWORD = "update member set mpsw=? where mno = ?";
	private static final String GET_IMG_MID ="SELECT MIMG FROM MEMBER where `mid` = ?";
	private static final String UPDATEPK="update member set dno=? where mno = ?";
	private static final String UPDATEPKNULL="update member set dno=? where mno = ?";
	 private static final String updateDieticianNo="update member set dno=? where mno=?";
	private static final String GET_DNO_NOT_NULL_ALL_STMT ="SELECT * FROM MEMBER WHERE dno is not null;";
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}
		
	}
	
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memberVO.getMname());
			pstmt.setString(2, memberVO.getMid());
			pstmt.setString(3, memberVO.getMpsw());
			pstmt.setString(4, memberVO.getMmail());
			pstmt.setString(5, memberVO.getMphone());
			pstmt.setInt(6, memberVO.getMsex());

			
			pstmt.executeUpdate();
			

			
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
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memberVO.getMname());
			
			pstmt.setString(2, memberVO.getMpsw());
			pstmt.setString(3, memberVO.getMmail());
			pstmt.setString(4, memberVO.getMphone());
			pstmt.setBytes(5, memberVO.getMimg());
			pstmt.setDate(6, memberVO.getMbday());
			pstmt.setInt(7, memberVO.getMsex());
			pstmt.setString(8, memberVO.getMintro());
			
			pstmt.setInt(9, memberVO.getMno());
			
			pstmt.executeUpdate();
			
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
	public MemberVO findByPrimaryKey(Integer mNo) {
		
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, mNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMno(rs.getInt("mno"));
				memberVO.setMname(rs.getString("mname"));
				memberVO.setMid(rs.getString("mid"));
				memberVO.setMpsw(rs.getString("mPsw"));
				memberVO.setMmail(rs.getString("mMail"));
				memberVO.setMphone(rs.getString("mPhone"));
				memberVO.setMimg(rs.getBytes("mImg"));
				memberVO.setMbday(rs.getDate("mbday"));
				memberVO.setMsex(rs.getInt("msex"));
				memberVO.setMintro(rs.getString("mintro"));
				memberVO.setMstate(rs.getInt("mState"));
				memberVO.setCardID(rs.getString("CardID"));
				memberVO.setCardDate(rs.getInt("CardDate"));
				memberVO.setCardNum(rs.getInt("CardNum"));
				memberVO.setDno(rs.getInt("dNo"));
				memberVO.setDailyCal(rs.getInt("DailyCal"));
				memberVO.setDailyCho(rs.getInt("DailyCho"));
				memberVO.setDailyPro(rs.getInt("DailyPro"));
				memberVO.setDailyFat(rs.getInt("DailyFat"));
				memberVO.setDietPlan(rs.getString("dietplan"));
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
			}
		}
		return memberVO;
	}
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMno(rs.getInt("mno"));
				memberVO.setMname(rs.getString("mname"));
				memberVO.setMid(rs.getString("mid"));
				memberVO.setMpsw(rs.getString("mPsw"));
				memberVO.setMmail(rs.getString("mMail"));
				memberVO.setMphone(rs.getString("mPhone"));
				memberVO.setMimg(rs.getBytes("mImg"));
				memberVO.setMbday(rs.getDate("mbday"));
				memberVO.setMsex(rs.getInt("msex"));
				memberVO.setMintro(rs.getString("mintro"));
				memberVO.setMstate(rs.getInt("mState"));
				memberVO.setCardID(rs.getString("CardID"));
				memberVO.setCardDate(rs.getInt("CardDate"));
				memberVO.setCardNum(rs.getInt("CardNum"));
				memberVO.setDno(rs.getInt("dNo"));
				memberVO.setDailyCal(rs.getInt("DailyCal"));
				memberVO.setDailyCho(rs.getInt("DailyCho"));
				memberVO.setDailyPro(rs.getInt("DailyPro"));
				memberVO.setDailyFat(rs.getInt("DailyFat"));
				memberVO.setDietPlan(rs.getString("dietplan"));
				list.add(memberVO);
				
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
			}
		}
		
		return list;
	}
	public List<MemberVO> getDnoNotNull() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_DNO_NOT_NULL_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMno(rs.getInt("mno"));
				memberVO.setMname(rs.getString("mname"));
				memberVO.setMid(rs.getString("mid"));
				memberVO.setMpsw(rs.getString("mPsw"));
				memberVO.setMmail(rs.getString("mMail"));
				memberVO.setMphone(rs.getString("mPhone"));
				memberVO.setMimg(rs.getBytes("mImg"));
				memberVO.setMbday(rs.getDate("mbday"));
				memberVO.setMsex(rs.getInt("msex"));
				memberVO.setMintro(rs.getString("mintro"));
				memberVO.setMstate(rs.getInt("mState"));
				memberVO.setCardID(rs.getString("CardID"));
				memberVO.setCardDate(rs.getInt("CardDate"));
				memberVO.setCardNum(rs.getInt("CardNum"));
				memberVO.setDno(rs.getInt("dNo"));
				memberVO.setDailyCal(rs.getInt("DailyCal"));
				memberVO.setDailyCho(rs.getInt("DailyCho"));
				memberVO.setDailyPro(rs.getInt("DailyPro"));
				memberVO.setDailyFat(rs.getInt("DailyFat"));
				memberVO.setDietPlan(rs.getString("dietplan"));
				list.add(memberVO);
			}
		} catch (SQLException e) {
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
	public byte[] getImg(Integer mno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] mimg = null;
		
			try {
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_IMG);
				pstmt.setInt(1, mno);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					mimg = rs.getBytes("mimg");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs!=null) {
					try {
						rs.close();
					}catch(SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if(pstmt!=null) {
					try {
						pstmt.close();
					}catch(SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if(con != null) {
					try {
						con.close();
					}catch(Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return mimg;
	}
	public byte[] getImgByMid(String mid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] mimg = null;
			try {
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_IMG_MID);
				pstmt.setString(1, mid);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					mimg = rs.getBytes("mimg");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(rs!=null) {
					try {
						rs.close();
					}catch(SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if(pstmt!=null) {
					try {
						pstmt.close();
					}catch(SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if(con != null) {
					try {
						con.close();
					}catch(Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return mimg;

			
		
	}
	public MemberVO isUser(String mid, String mpsw) {
		
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt= con.prepareStatement(GetUser);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpsw);
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMno(rs.getInt("mno"));
				memberVO.setMname(rs.getString("mname"));
				memberVO.setMid(rs.getString("mid"));
				memberVO.setMpsw(rs.getString("mPsw"));
				memberVO.setMmail(rs.getString("mMail"));
				memberVO.setMphone(rs.getString("mPhone"));
				memberVO.setMimg(rs.getBytes("mImg"));
				memberVO.setMbday(rs.getDate("mbday"));
				memberVO.setMsex(rs.getInt("msex"));
				memberVO.setMintro(rs.getString("mintro"));
				memberVO.setMstate(rs.getInt("mState"));
				memberVO.setCardID(rs.getString("CardID"));
				memberVO.setCardDate(rs.getInt("CardDate"));
				memberVO.setCardNum(rs.getInt("CardNum"));
				memberVO.setDno(rs.getInt("dNo"));
				memberVO.setDailyCal(rs.getInt("DailyCal"));
				memberVO.setDailyCho(rs.getInt("DailyCho"));
				memberVO.setDailyPro(rs.getInt("DailyPro"));
				memberVO.setDailyFat(rs.getInt("DailyFat"));
				memberVO.setDietPlan(rs.getString("dietplan"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return memberVO;
		
	}
	
	
	@Override
	public MemberVO findByMid(String mid) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt= con.prepareStatement(GET_ONE_STMT_BY_MID);
			pstmt.setString(1, mid);
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMno(rs.getInt("mno"));
				memberVO.setMname(rs.getString("mname"));
				memberVO.setMid(rs.getString("mid"));
				memberVO.setMpsw(rs.getString("mPsw"));
				memberVO.setMmail(rs.getString("mMail"));
				memberVO.setMphone(rs.getString("mPhone"));
				memberVO.setMimg(rs.getBytes("mImg"));
				memberVO.setMbday(rs.getDate("mbday"));
				memberVO.setMsex(rs.getInt("msex"));
				memberVO.setMintro(rs.getString("mintro"));
				memberVO.setMstate(rs.getInt("mState"));
				memberVO.setCardID(rs.getString("CardID"));
				memberVO.setCardDate(rs.getInt("CardDate"));
				memberVO.setCardNum(rs.getInt("CardNum"));
				memberVO.setDno(rs.getInt("dNo"));
				memberVO.setDailyCal(rs.getInt("DailyCal"));
				memberVO.setDailyCho(rs.getInt("DailyCho"));
				memberVO.setDailyPro(rs.getInt("DailyPro"));
				memberVO.setDailyFat(rs.getInt("DailyFat"));
				memberVO.setDietPlan(rs.getString("dietplan"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return memberVO;
		
	}
	public void disable(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
			try {
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATEMSTATE);
				Integer state =memberVO.getMstate();
				if (state==1) {
					pstmt.setInt(1, 0);
				}else {
					pstmt.setInt(1, 1);
				}
				pstmt.setInt(2, memberVO.getMno());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
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
	public void resetPassword (MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEPASSWORD);
			Integer mno = memberVO.getMno();
			String mpsw=memberVO.getMpsw();
			pstmt.setString(1,mpsw );
			pstmt.setInt(2, mno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
	public void reset_dieticianPk(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
			try {
				con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEPK);
			Integer mno = memberVO.getMno();
			Integer dno=memberVO.getDno();
			pstmt.setInt(1,dno );
			pstmt.setInt(2, mno);
			pstmt.executeUpdate();
			} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
	 public void updateDieticianNo(Integer dNo,Integer mNo) {
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  
		   try {
		    con = DriverManager.getConnection(url, userid, passwd);
		    pstmt = con.prepareStatement(updateDieticianNo);
		    pstmt.setInt(1, dNo);
		    pstmt.setInt(2, mNo);
		    pstmt.execute();
		   } catch (SQLException e) {
		    e.printStackTrace();
		   }finally {
		    if(con!=null)
		     try {
		      con.close();
		     } catch (SQLException e) {
		      e.printStackTrace();
		     }
		   }
		   
		 }
	@Override
	public void reset_dieticianPKNull(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEPKNULL);
			Integer mno = memberVO.getMno();
			pstmt.setNull(1, Types.INTEGER);
			pstmt.setInt(2, mno);
			pstmt.executeUpdate();
					} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
	
	public static void main(String[] args) throws IOException {
		MemberJDBCDAO dao = new MemberJDBCDAO();
		//新增
//		MemberVO memberVO1 = new MemberVO();
//		memberVO1.setMname("蟹老闆2");
//		memberVO1.setMid("shi1232");
//		memberVO1.setMpsw("88882");
//		memberVO1.setMmail("shi2@gmail.com");
//		memberVO1.setMphone("080999417332");
//		memberVO1.setMsex(1); //(1:Male / 2:Female)
//		dao.insert(memberVO1);
//		

		
		//修改
//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setMname("皮皮皮老闆");
//		memberVO2.setMid("123");
//		memberVO2.setMpsw("123");
//		memberVO2.setMmail("1234");
//		memberVO2.setMphone("1234");
//		byte[] pic1 = getPictureByteArray("items/Plankton.jpg");
//		memberVO2.setMimg(pic1);
//		memberVO2.setMbday(java.sql.Date.valueOf("2005-01-01"));
//		memberVO2.setMsex(1); //(1:Male / 2:Female)
//		memberVO2.setMintro("213");
//		memberVO2.setMstate(1); //(0:停權 / 1:正常 / 預設:1)
//		memberVO2.setCardID(123);
//		memberVO2.setCardDate(123);
//		memberVO2.setCardNum(123);
//		memberVO2.setDno(123);
//		memberVO2.setDailyCal(123);
//		memberVO2.setDailyCho(213);
//		memberVO2.setDailyPro(123);
//		memberVO2.setDailyFat(2132);
//		memberVO2.setDietPlan("喔喔喔喔");
//		memberVO2.setMno(1);
//		dao.update(memberVO2);
		
		//查詢
//		MemberVO memberVO3 = dao.findByMid("pi123");
//		System.out.println(memberVO3.getMno());
//		System.out.println(memberVO3.getMname());
//		System.out.println("==============");
		
		MemberVO memberVO3 = dao.isUser("pi123", "123");
		System.out.println(memberVO3.getMno());
		System.out.println(memberVO3.getMname());
		System.out.println("==============");
		//查詢all
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO memb : list ) {
//			System.out.print(memb.getMno()+",");
//			System.out.print(memb.getMname());
//			System.out.println();
//		}
		
		
		
		
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	
}
