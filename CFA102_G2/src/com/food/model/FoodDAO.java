package com.food.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO implements FoodDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";

	private static final String insert_SQL = "INSERT INTO food VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String update_SQL = "UPDATE food SET mno= ?,fdName= ?, wtPerPortion= ?, calPerWt= ?, choPerWt= ?, proPerWt= ?, fatPerWt= ?, fdBrand= ?, fdState= ? WHERE fdNo= ?;";
	private static final String findByFdNo_SQL = "SELECT * FROM food where fdNo =?;";
	private static final String findByFoodName_SQL = "SELECT * FROM food where fdName like ?;";
	private static final String findByBrandName_SQL = "SELECT * FROM food where fdBrand like ?;";
	private static final String getAll_SQL = "SELECT * FROM food;";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(FoodVO food) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(insert_SQL);

			pstmt.setInt(1, food.getMno());
			pstmt.setString(2, food.getFdName());
			pstmt.setInt(3, food.getWtPerPortion());
			pstmt.setDouble(4, food.getCalPerWt());
			pstmt.setDouble(5, food.getChoPerWt());
			pstmt.setDouble(6, food.getProPerWt());
			pstmt.setDouble(7, food.getFatPerWt());
			pstmt.setString(8, food.getFdBrand());
			pstmt.setInt(9, food.getFdState());

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
	public void update(FoodVO food) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(update_SQL);

			if (food.getMno() == 0) {
				pstmt.setNull(1, Types.NULL);
			} else {
				pstmt.setInt(1, food.getMno());
			}
			pstmt.setString(2, food.getFdName());
			pstmt.setInt(3, food.getWtPerPortion());
			pstmt.setDouble(4, food.getCalPerWt());
			pstmt.setDouble(5, food.getChoPerWt());
			pstmt.setDouble(6, food.getProPerWt());
			pstmt.setDouble(7, food.getFatPerWt());
			pstmt.setString(8, food.getFdBrand());
			pstmt.setInt(9, food.getFdState());
			pstmt.setInt(10, food.getFdNo());

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
	public FoodVO findByFdNo(int fdNo) {

		Connection con = null;
		FoodVO food = new FoodVO();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByFdNo_SQL);
			pstmt.setInt(1, fdNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				food.setFdNo(fdNo);
				food.setMno(rs.getInt("mno"));
				food.setFdName(rs.getString("fdName"));
				food.setWtPerPortion(rs.getInt("wtPerPortion"));
				food.setCalPerWt(rs.getDouble("calPerWt"));
				food.setChoPerWt(rs.getDouble("choPerWt"));
				food.setProPerWt(rs.getDouble("proPerWt"));
				food.setFatPerWt(rs.getDouble("fatPerWt"));
				food.setFdBrand(rs.getString("fdBrand"));
				food.setFdState(rs.getInt("fdState"));

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

		return food;

	}

	@Override
	public List<FoodVO> findByFoodName(String fdName) {
		// TODO Auto-generated method stub

		Connection con = null;
		List<FoodVO> foodList = new ArrayList<FoodVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByFoodName_SQL);

			pstmt.setString(1, "%" + fdName + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				FoodVO food = new FoodVO();

				food.setFdNo(rs.getInt("fdNo"));
				food.setMno(rs.getInt("mno"));
				food.setFdName(rs.getString("fdName"));
				food.setWtPerPortion(rs.getInt("wtPerPortion"));
				food.setCalPerWt(rs.getDouble("calPerWt"));
				food.setChoPerWt(rs.getDouble("choPerWt"));
				food.setProPerWt(rs.getDouble("proPerWt"));
				food.setFatPerWt(rs.getDouble("fatPerWt"));
				food.setFdBrand(rs.getString("fdBrand"));
				food.setFdState(rs.getInt("fdState"));

				foodList.add(food);

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

		return foodList;

	}

	@Override
	public List<FoodVO> findByBrandName(String fdBrand) {

		Connection con = null;
		List<FoodVO> foodList = new ArrayList<FoodVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByBrandName_SQL);

			pstmt.setString(1, "%" + fdBrand + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				FoodVO food = new FoodVO();

				food.setFdNo(rs.getInt("fdNo"));
				food.setMno(rs.getInt("mno"));
				food.setFdName(rs.getString("fdName"));
				food.setWtPerPortion(rs.getInt("wtPerPortion"));
				food.setCalPerWt(rs.getDouble("calPerWt"));
				food.setChoPerWt(rs.getDouble("choPerWt"));
				food.setProPerWt(rs.getDouble("proPerWt"));
				food.setFatPerWt(rs.getDouble("fatPerWt"));
				food.setFdBrand(rs.getString("fdBrand"));
				food.setFdState(rs.getInt("fdState"));

				foodList.add(food);

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

		return foodList;

	}

	public List<FoodVO> getAll(){
		
		Connection con = null;
		List<FoodVO> foodList = new ArrayList<FoodVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(getAll_SQL);


			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				FoodVO food = new FoodVO();

				food.setFdNo(rs.getInt("fdNo"));
				food.setMno(rs.getInt("mno"));
				food.setFdName(rs.getString("fdName"));
				food.setWtPerPortion(rs.getInt("wtPerPortion"));
				food.setCalPerWt(rs.getDouble("calPerWt"));
				food.setChoPerWt(rs.getDouble("choPerWt"));
				food.setProPerWt(rs.getDouble("proPerWt"));
				food.setFatPerWt(rs.getDouble("fatPerWt"));
				food.setFdBrand(rs.getString("fdBrand"));
				food.setFdState(rs.getInt("fdState"));

				foodList.add(food);

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

		return foodList;
		
	}
	
	public static void main(String[] args) {

		FoodDAO dao = new FoodDAO();

		FoodVO food = dao.findByFdNo(3);

		dao.update(food);

//		List<FoodVO> foodList = new ArrayList<FoodVO>();
//		
//		foodList = dao.findByBrandName("");
//		
//		for(FoodVO food : foodList) {
//			System.out.println(food.getFdName());
//		}

	}

}
