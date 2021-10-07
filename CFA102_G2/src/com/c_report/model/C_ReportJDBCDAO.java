package com.c_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import util.Util;

public class C_ReportJDBCDAO implements C_ReportDAO_interface {
	private static final String INSERT = "INSERT INTO C_Report (reportNo,mNO,cNO,cReportState,cReportContent,cReportTime)VALUES(Default,?,?,?,?,NOW())";
	private static final String UPDATE_CONTENT = "UPDATE C_Report SET cReportContent=? ,cReportTime=NOW()  WHERE reportNo=?";
	private static final String UPDATE_STATE = "UPDATE C_Report SET cReporState=? ,cReportTime=NOW()  WHERE reportNo=?";
	private static final String DELETE = "DELETE FROM C_Report WHERE reportNo=?";
	private static final String GET_ONE_BY_PK = "SELECT * FROM C_Report WHERE reportNO=?";
	private static final String GET_ALL = "SELECT * FROM C_Report";
	private static final String GET_ALL_BY_MNO = "SELECT * FROM C_Report WHERE mNO=?";
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(C_ReportVO c_report) {
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
			pstmt.setInt(1, c_report.getMno());
			pstmt.setInt(2, c_report.getCno());
			pstmt.setInt(3, c_report.getCreportState());
			pstmt.setString(4, c_report.getCreportContent());
			
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
	public void updateContent(C_ReportVO c_report) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_CONTENT);
			pstmt.setString(1, c_report.getCreportContent());
			pstmt.setInt(2, c_report.getReportNo());
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
	public void updateState(C_ReportVO c_report) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_CONTENT);
			pstmt.setInt(1, c_report.getCreportState());
			pstmt.setInt(2, c_report.getReportNo());
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
	public void delete(C_ReportVO c_report) {
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
			pstmt.setInt(1, c_report.getReportNo());
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
	public C_ReportVO getOne(Integer reportNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		C_ReportVO repVO= null;
		
		try {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_BY_PK);
			pstmt.setInt(1, reportNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				repVO = new C_ReportVO();
				repVO.setReportNo(reportNo);
				repVO.setMno(rs.getInt("mNo"));
				repVO.setCno(rs.getInt("cNo"));
				repVO.setCreportState(rs.getInt("cReportState"));
				repVO.setCreportContent(rs.getString("cReportContent"));
				repVO.setCreportTime(rs.getTimestamp("cReportTime"));
				
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

		return repVO;
	}

	@Override
	public List<C_ReportVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<C_ReportVO> retList = new ArrayList<>();
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				C_ReportVO repVO = new C_ReportVO();
				repVO = new C_ReportVO();
				repVO.setReportNo(rs.getInt("reportNo"));
				repVO.setMno(rs.getInt("mNo"));
				repVO.setCno(rs.getInt("cNo"));
				repVO.setCreportState(rs.getInt("cReportState"));
				repVO.setCreportContent(rs.getString("cReportContent"));
				repVO.setCreportTime(rs.getTimestamp("cReportTime"));
				retList.add(repVO);
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

		return retList;
	}

	@Override
	public List<C_ReportVO> getAll(Integer mNo) {
		// TODO Auto-generated method stub
		return null;
	}
	

//	public static void main(String[] agrs) {
//		//insert
//		
//		C_ReportDAO_interface dao = new C_ReportJDBCDAO();
//		C_ReportVO VO = new C_ReportVO();
//		
//		
//		VO.setReportNo(2);
//		VO.setmNo(2);
//		VO.setcNo(3);
//		VO.setcReportState(3);
//		VO.setcReportContent("亂教耶1");
		

//		dao.insert(VO);
		// update
	//	dao.updateContent(VO);
//		dao.updateState(VO);
		// delete
//		dao.delete(VO);
		// getone
//		System.out.println(dao.getOne(1));
		// get all
//		List<C_ReportVO> List = (dao.getAll());
//		for (C_ReportVO t : List)
//			System.out.println(t);
//		System.out.println("成功");
//	}
}
