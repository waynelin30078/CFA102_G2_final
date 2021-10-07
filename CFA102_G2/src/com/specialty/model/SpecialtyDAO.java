package com.specialty.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.admin.model.AdminVO;


public class SpecialtyDAO implements SpecialtyDAO_interface {

	

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";

	private static final String insert_SQL = "INSERT INTO specialty VALUES(default, ?);";
	private static final String update_SQL = "UPDATE specialty SET specialtyName = ? WHERE specialtyNo = ?;";
	private static final String getAll_SQL = "SELECT * FROM specialty;";
	
	
	/////世柏增加
	private static final String findBySpecialtyName ="SELECT * FROM specialty WHERE specialtyNo=?";
	
	///////世柏增加
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insert(SpecialtyVO specialty) {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(insert_SQL);

			pstmt.setString(1, specialty.getSpecialtyName());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void update(SpecialtyVO specialty) {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(update_SQL);

			pstmt.setString(1, specialty.getSpecialtyName());
			pstmt.setInt(2, specialty.getSpecialtyNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public List<SpecialtyVO> getAll() {
		Connection con = null;
		
		List<SpecialtyVO> specialties = new ArrayList<SpecialtyVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(getAll_SQL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				SpecialtyVO specialty = new SpecialtyVO();

				specialty.setSpecialtyNo(rs.getInt("specialtyNo"));

				specialty.setSpecialtyName(rs.getString("specialtyName"));

				specialties.add(specialty);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

		return specialties;
	}
	/////////世柏增加
	public SpecialtyVO findBySpecialtyName(int specialtyNo){
		Connection con = null;
		SpecialtyVO specialty=null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findBySpecialtyName);
			pstmt.setInt(1,specialtyNo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				specialty= new SpecialtyVO();
				specialty.setSpecialtyNo(rs.getInt("specialtyNo"));
				specialty.setSpecialtyName(rs.getString("specialtyName"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return specialty;
	}
	
	
	
	
	///////世柏增加
	
	

	public static void main(String[] args) {

		SpecialtyDAO dao = new SpecialtyDAO();
		
		SpecialtyVO	specialty = new SpecialtyVO();
		
		specialty.setSpecialtyNo(5);
		specialty.setSpecialtyName("肝臟病飲食控制");
		
		dao.update(specialty);
		
		
		List<SpecialtyVO> specialties = dao.getAll();
		
		for (SpecialtyVO specialty1 : specialties) {

			System.out.println(specialty1.getSpecialtyNo());
			System.out.println(specialty1.getSpecialtyName());
			}
			

			
			
			
			
			
			
		
		
		
		
		
		
	}

}
