package com.d_specialty.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.specialty.model.SpecialtyVO;

public class DspecialtyDAO implements DspecialtyDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";

	private static final String insert_SQL = "INSERT INTO d_specialty (specialtyNo, dno) VALUES(?, ?);";
	private static final String delete_SQL = "DELETE FROM d_specialty WHERE specialtyNo = ? AND dno = ?;";
	private static final String getAll_SQL = "SELECT * FROM d_specialty;";
	private static final String findByDno_SQL = "SELECT * FROM d_specialty WHERE dno = ?;";
	
	/////世柏增加
	
	private static final String findByDno = "SELECT * FROM d_specialty WHERE specialtyNo = ?";
	/////世柏增加

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insert(DspecialtyVO dSpecialty) {

		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(insert_SQL);

			pstmt.setInt(1, dSpecialty.getSpecialtyNo());
			pstmt.setInt(2, dSpecialty.getDno());

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

	public void delete(DspecialtyVO dSpecialty) {

		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(delete_SQL);

			pstmt.setInt(1, dSpecialty.getSpecialtyNo());
			pstmt.setInt(2, dSpecialty.getDno());

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
	public List<DspecialtyVO> getAll() {

		Connection con = null;
		List<DspecialtyVO> dSpecialties = new ArrayList<DspecialtyVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(getAll_SQL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				DspecialtyVO dSpecialty = new DspecialtyVO();

				dSpecialty.setSpecialtyNo(rs.getInt("specialtyNo"));

				dSpecialty.setDno(rs.getInt("dno"));

				dSpecialties.add(dSpecialty);
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
		return dSpecialties;
	}

	@Override
	public List<DspecialtyVO> findByDno(int dno) {

		Connection con = null;
		List<DspecialtyVO> dSpecialties = new ArrayList<DspecialtyVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByDno_SQL);
			pstmt.setInt(1, dno);
			
			ResultSet rs = pstmt.executeQuery();

			

			while (rs.next()) {

				DspecialtyVO dSpecialty = new DspecialtyVO();

				dSpecialty.setSpecialtyNo(rs.getInt("specialtyNo"));

				dSpecialty.setDno(rs.getInt("dno"));

				dSpecialties.add(dSpecialty);
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
		return dSpecialties;
	}

	
	////////世柏增加
	public List<DspecialtyVO> findByDno1(int specialtyNo) {

		Connection con = null;
		List<DspecialtyVO> dSpecialties = new ArrayList<DspecialtyVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByDno);
			pstmt.setInt(1, specialtyNo);
			
			ResultSet rs = pstmt.executeQuery();

			

			while (rs.next()) {

				DspecialtyVO dSpecialty = new DspecialtyVO();

				dSpecialty.setDno(rs.getInt("dno"));

				dSpecialties.add(dSpecialty);
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
		return dSpecialties;
	}
	
	
	
	
	
	////////世柏增加
	
	public static void main(String[] args) {

		DspecialtyDAO dao = new DspecialtyDAO();
		List<DspecialtyVO> dSpecialties = new ArrayList<DspecialtyVO>();
		
		dSpecialties= dao.findByDno(1);
		
		for(DspecialtyVO dSpecialty: dSpecialties) {
			
			System.out.println(dSpecialty.getSpecialtyNo());
		}
		
//		DspecialtyVO Dspecialty1 = new DspecialtyVO(3, 5);
//		dao.delete(Dspecialty1);
		
		
		
	}
}
