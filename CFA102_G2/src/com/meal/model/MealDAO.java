package com.meal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diary.model.*;
import com.dietician.model.*;
import com.food.model.*;
import com.food_record.model.*;

public class MealDAO implements MealDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";

	private static final String insert_SQL = "INSERT INTO meal VALUES(default, ?, ?, ?, ?, ?, ?);";
	private static final String update_SQL = "UPDATE meal SET diaryNo= ?, mealName= ?, mealCal= ?, mealCho= ?, mealPro= ?,	mealFat= ? WHERE mealNo = ?;";
	private static final String delete_SQL = "DELETE FROM meal WHERE mealNo = ?;";
	private static final String findByDiaryNo_SQL = "SELECT * FROM meal WHERE diaryNo = ?;";
	private static final String findByMealNo_SQL = "SELECT * FROM meal WHERE mealNo = ?;";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(MealVO meal) {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(insert_SQL);

	
			
			pstmt.setInt(1, meal.getDiaryNo());
			pstmt.setString(2, meal.getMealName());
			pstmt.setDouble(3, meal.getMealCal());
			pstmt.setDouble(4, meal.getMealCho());
			pstmt.setDouble(5, meal.getMealPro());
			pstmt.setDouble(6, meal.getMealFat());

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
	public void update(MealVO meal) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(update_SQL);

			pstmt.setInt(1, meal.getDiaryNo());
			pstmt.setString(2, meal.getMealName());
			pstmt.setDouble(3, meal.getMealCal());
			pstmt.setDouble(4, meal.getMealCho());
			pstmt.setDouble(5, meal.getMealPro());
			pstmt.setDouble(6, meal.getMealFat());
			pstmt.setInt(7, meal.getMealNo());

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
	public void delete(int mealNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			con.setAutoCommit(false);
			
			MealDAO mealDAO = new MealDAO();
			MealVO meal = mealDAO.findByMealNo(mealNo);
			
			DiaryDAO diaryDAO = new DiaryDAO();
			DiaryVO diary = diaryDAO.findByDiaryNo(meal.getDiaryNo());
			
			diaryDAO.deductNutrition(diary, meal, con);
			
			FoodRecordDAO foodRecordDAO = new FoodRecordDAO();
			
			foodRecordDAO.deleteWithMeal(mealNo, con);
			
			pstmt = con.prepareStatement(delete_SQL);

			pstmt.setInt(1, mealNo);
			
			
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			
		}
		catch (SQLException se) {
			if (con != null) {
				try {
					System.err.println("meal流程出現問題");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("資料出現錯誤, 新增失敗 "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("資料出現錯誤, 新增失敗"
					+ se.getMessage());
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
	public List<MealVO> findByDiaryNo(int diaryNo) {

		Connection con = null;
		List<MealVO> meals = new ArrayList<MealVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByDiaryNo_SQL);
			pstmt.setInt(1, diaryNo);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				MealVO meal = new MealVO();

				meal.setMealNo(rs.getInt("mealNo"));
				meal.setDiaryNo(rs.getInt("diaryNo"));
				meal.setMealName(rs.getString("mealName"));
				meal.setMealCal(rs.getDouble("mealCal"));
				meal.setMealCho(rs.getDouble("mealCho"));
				meal.setMealPro(rs.getDouble("mealPro"));
				meal.setMealFat(rs.getDouble("mealFat"));
				
				meals.add(meal);
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

		return meals;
	}
	
	public MealVO findByMealNo(int mealNo) {
		
		Connection con = null;
		MealVO meal = new MealVO();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByMealNo_SQL);
			pstmt.setInt(1, mealNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				meal.setMealNo(mealNo);
				meal.setDiaryNo(rs.getInt("diaryNo"));
				meal.setMealName(rs.getString("mealName"));
				meal.setMealCal(rs.getDouble("mealCal"));
				meal.setMealCho(rs.getDouble("mealCho"));
				meal.setMealPro(rs.getDouble("mealPro"));
				meal.setMealFat(rs.getDouble("mealFat"));

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

		return meal;
		
		
	}
	
	public void insertMeal(MealVO meal, List<FoodRecordVO> foodRecordList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			con.setAutoCommit(false);
			
			String[] cols = {"mealNo"};
			pstmt = con.prepareStatement(insert_SQL, cols);
		
			double mealCal = 0;
			double mealCho = 0;
			double mealPro = 0;
			double mealFat = 0;
			
			for(FoodRecordVO foodRecord : foodRecordList) {
				mealCal += foodRecord.getSinglelCal();
				mealCho += foodRecord.getSingleCho();
				mealPro += foodRecord.getSinglePro();
				mealFat += foodRecord.getSingleFat();
			}
			
			meal.setMealCal(mealCal);
			meal.setMealCho(mealCho);
			meal.setMealPro(mealPro);
			meal.setMealFat(mealFat);
			
			pstmt.setInt(1, meal.getDiaryNo());
			pstmt.setString(2, meal.getMealName());
			pstmt.setDouble(3, mealCal);
			pstmt.setDouble(4, mealCho);
			pstmt.setDouble(5, mealPro);
			pstmt.setDouble(6, mealFat);
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			
			Integer mealNo = null; 
			
			if (rs.next()) {
				mealNo = new Integer(rs.getString(1));
			} 
			
			rs.close();
			
			FoodRecordDAO foodRecordDAO = new FoodRecordDAO();
			
			for(FoodRecordVO foodRecord : foodRecordList) {
			
				foodRecord.setMealNo(mealNo);
				System.out.println(foodRecord.getMealNo());
				
				foodRecordDAO.insertWithMeal(foodRecord, con);
			}
			
			DiaryDAO diaryDAO = new DiaryDAO();
			
			DiaryVO diary = diaryDAO.findByDiaryNo(meal.getDiaryNo());
			diaryDAO.updateNutrition(diary, meal, con);
			
			con.commit();
			con.setAutoCommit(true);
			
		}
		catch (SQLException se) {
			if (con != null) {
				try {
					System.err.println("meal流程出現問題");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("資料出現錯誤, 新增失敗 "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("資料出現錯誤, 新增失敗"
					+ se.getMessage());
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
	

	
	
	
	public static void main(String[] args) {
		
		MealDAO dao = new MealDAO();
		
//		MealVO meal = new MealVO(2, 3, 	643.0 ,	444.0 ,	777.0 ,	111.0);
//		
//		System.out.println(meal.getMealPro());
//		
//		
//		dao.insert(meal);


		MealVO meal = new MealVO();
		
		meal.setDiaryNo(5);
		meal.setMealName("早餐");
		
		List<FoodRecordVO> foodRecordList = new ArrayList<FoodRecordVO>();
		System.out.println(meal.getDiaryNo());
//		
		FoodRecordVO foodRecord1 = new FoodRecordVO();
		FoodRecordVO foodRecord2 = new FoodRecordVO();
		
		FoodDAO foodDAO = new FoodDAO(); 
		
		FoodVO food1 = foodDAO.findByFdNo(10);
		FoodVO food2 = foodDAO.findByFdNo(50);
		
		foodRecord1.setFdNo(food1.getFdNo()); //fdNo
		foodRecord1.setFdPortion(1); //食物份數
		foodRecord1.setFdWt(0); //食物重量
		foodRecord1.setSinglelCal(food1.getCalPerWt()); //mealNo, 從generatedkey來
		foodRecord1.setSingleCho(food1.getChoPerWt()); //fdNo
		foodRecord1.setSinglePro(food1.getProPerWt()); //食物份數
		foodRecord1.setSingleFat(food1.getFatPerWt()); //食物重量
		
		foodRecordList.add(foodRecord1);
	
		foodRecord2.setFdNo(food2.getFdNo()); //fdNo
		foodRecord2.setFdPortion(2); //食物份數
		foodRecord2.setFdWt(0); //食物重量
		foodRecord2.setSinglelCal(food2.getCalPerWt()); //mealNo, 從generatedkey來
		foodRecord2.setSingleCho(food2.getChoPerWt()); //fdNo
		foodRecord2.setSinglePro(food2.getProPerWt()); //食物份數
		foodRecord2.setSingleFat(food2.getFatPerWt()); //食物重量

		foodRecordList.add(foodRecord2);
		
		System.out.println(foodRecord1.getFdNo());
		
		dao.insertMeal(meal, foodRecordList);
//		dao.delete(13);
//		
		
		
	}
	
	
	
	
	
	

}
