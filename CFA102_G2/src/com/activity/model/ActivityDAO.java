package com.activity.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ActivityDAO implements ActivityDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";

	private static final String insert_SQL = "INSERT INTO activity VALUES(default, ?, ?, ?);";
	private static final String update_SQL = "UPDATE activity SET actName=?, calPerKgHr=?, actState=? WHERE actNo=?;";
	private static final String findById_SQL = "SELECT * FROM activity WHERE actNo=?;";
	private static final String getAll_SQL = "SELECT * FROM activity";

	@Override
	public void insert(ActivityVO activity) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(insert_SQL);

			pstmt.setString(1, activity.getActName());
			pstmt.setDouble(2, activity.getCalPerKgHr());
			pstmt.setInt(3, activity.getActState());

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
	public void update(ActivityVO activity) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(update_SQL);

			pstmt.setString(1, activity.getActName());
			pstmt.setDouble(2, activity.getCalPerKgHr());
			pstmt.setInt(3, activity.getActState());
			pstmt.setInt(4, activity.getActNo());

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
	public ActivityVO findById(int actNo) {
		Connection con = null;
		ActivityVO activity = new ActivityVO();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findById_SQL);
			pstmt.setInt(1, actNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				activity.setActNo(actNo);
				activity.setActName(rs.getString("actName"));
				activity.setCalPerKgHr(rs.getDouble("calPerKgHr"));
				activity.setActState(rs.getInt("actState"));

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

		return activity;
	}

	@Override
	public List<ActivityVO> getAll() {
		Connection con = null;
		List<ActivityVO> activities = new ArrayList<ActivityVO>();
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(getAll_SQL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ActivityVO activity = new ActivityVO();
				
				activity.setActNo(rs.getInt("actNo"));
				activity.setActName(rs.getString("actName"));
				activity.setCalPerKgHr(rs.getDouble("calPerKgHr"));
				activity.setActState(rs.getInt("actState"));

				
				activities.add(activity);
				
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return activities;
	}

	public static void main(String[] args) {
		
		

	}

}
