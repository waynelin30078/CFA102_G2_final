package com.admin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AdminJDBCDAO implements AdminDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	String user = "David";
	String password = "123456";

	private static final String insert = "INSERT INTO ADMIN (aName,aId,aPsw)VALUES(?,?,?)";
	private static final String update = "UPDATE ADMIN set aName=?, aId=?, aPsw=? where aNo=?";
	private static final String delete = "delete from ADMIN where aNo=?";
	private static final String findByPrimaryKey = "select * from ADMIN where aNo=?";
	private static final String getAll = "SELECT * FROM ADMIN order by aNo";
	private static final String findByAid = "SELECT * FROM ADMIN WHERE `aId` = ?;";

	@Override
	public void insert(AdminVO adminVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(insert);

			pstmt.setString(1, adminVO.getAname());
			pstmt.setString(2, adminVO.getAid());
			pstmt.setString(3, adminVO.getApsw());
			pstmt.executeUpdate();
			System.out.println("新增成功!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void update(AdminVO adminVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(update);

			pstmt.setString(1, adminVO.getAname());
			pstmt.setString(2, adminVO.getAid());
			pstmt.setString(3, adminVO.getApsw());
			pstmt.setInt(4, adminVO.getAno());
			pstmt.executeUpdate();
			System.out.println("修改成功!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void delete(Integer ano) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(delete);

			pstmt.setInt(1, ano);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	@Override
	public AdminVO findByPrimaryKey(Integer ano) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVO adminVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(findByPrimaryKey);

			pstmt.setInt(1, ano);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAno(rs.getInt("aNo"));
				adminVO.setAname(rs.getString("aName"));
				adminVO.setAid(rs.getString("aId"));
				adminVO.setApsw(rs.getString("aPsw"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return adminVO;
	}

	@Override
	public List<AdminVO> getAll() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<AdminVO> list = new ArrayList<AdminVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(getAll);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminVO adminVO = new AdminVO();
				adminVO.setAno(rs.getInt("aNo"));
				adminVO.setAname(rs.getString("aName"));
				adminVO.setAid(rs.getString("aId"));
				adminVO.setApsw(rs.getString("aPsw"));
				list.add(adminVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public AdminVO findByAid(String aid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminVO adminVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(findByAid);

			pstmt.setString(1, aid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAno(rs.getInt("aNo"));
				adminVO.setAname(rs.getString("aName"));
				adminVO.setAid(rs.getString("aId"));
				adminVO.setApsw(rs.getString("aPsw"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return adminVO;
	}

	public static void main(String[] args) {
		AdminJDBCDAO dao = new AdminJDBCDAO();
//		// 新增
//		AdminVO adminVO1 = new AdminVO();
//
//		for (int i = 85; i > 65; i--) {
//			Scanner sc = new Scanner(System.in);
//			adminVO1.setAname(sc.next());
//			adminVO1.setAid("aa" + (char) i + "bb" + i);
//			adminVO1.setApsw("bb" + (char) i + "aa" + i);
//			dao.insert(adminVO1);
//		}
//		 修改

//		AdminVO adminVO2 = new AdminVO();
//		adminVO2.setAname("offfo");
//		adminVO2.setAid("ppffp");
//		adminVO2.setApsw("lffll");
//		adminVO2.setAno(25);
//		dao.update(adminVO2);
////		 刪除
//		AdminVO adminVO3 = new AdminVO();
//		adminVO3.setAno(3);
//		dao.delete(adminVO3.getAno());
//		 依管理員編號查詢
//		AdminVO adminVO4 = dao.findByPrimaryKey(2);
//		System.out.print(adminVO4.getAno() + ",");
//		System.out.print(adminVO4.getAname() + ",");
//		System.out.print(adminVO4.getAid() + ",");
//		System.out.print(adminVO4.getApsw()  + ",");

//		// 查全部
//		List<AdminVO> list =new ArrayList<AdminVO>();
//		list=dao.getAll();
//		for (AdminVO str : list)
//			System.out.println(str.getAno()+","+str.getAname()+","+str.getAid()+","+str.getApsw());
//		System.out.println();
		
		AdminVO adminVO4 = dao.findByAid("aaUbb85");
		System.out.print(adminVO4.getAno() + ",");
		System.out.print(adminVO4.getAname() + ",");
		System.out.print(adminVO4.getAid() + ",");
		System.out.print(adminVO4.getApsw()  + ",");

		
	}

}
