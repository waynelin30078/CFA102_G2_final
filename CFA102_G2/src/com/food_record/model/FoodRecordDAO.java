package com.food_record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.diary.model.DiaryVO;
import com.food.model.FoodVO;

public class FoodRecordDAO implements FoodRecordDAO_interface{

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	
	private static final String insert_SQL = "INSERT INTO food_record VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String update_SQL = "UPDATE food_record mealNo=?, fdNo=?, fdPortion=?, fdWt=?, singlelCal=?, singleCho=?, singlePro=?, singleFat=? WHERE mealNo=?, fdNo=?;";
	private static final String delete_SQL = "DELETE FROM food_record WHERE mealNo=? ;";
	private static final String insertWithMeal_SQL = "INSERT INTO food_record VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String findByMealNo_SQL = "SELECT * FROM food_record WHERE mealNo = ?;";
	
	@Override
	public void insert(FoodRecordVO foodRecord) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(insert_SQL);
			
			pstmt.setInt(1 ,foodRecord.getMealNo());
			pstmt.setInt(2 ,foodRecord.getFdNo());
			pstmt.setInt(3 ,foodRecord.getFdPortion());
			pstmt.setInt(4 ,foodRecord.getFdWt());
			pstmt.setDouble(5 ,foodRecord.getSinglelCal());
			pstmt.setDouble(6 ,foodRecord.getSingleCho());
			pstmt.setDouble(7 ,foodRecord.getSinglePro());
			pstmt.setDouble(8 ,foodRecord.getSingleFat());


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
	public void update(FoodRecordVO foodRecord) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(update_SQL);

			pstmt.setInt(1 ,foodRecord.getMealNo());
			pstmt.setInt(2 ,foodRecord.getFdNo());
			pstmt.setInt(3 ,foodRecord.getFdPortion());
			pstmt.setInt(4 ,foodRecord.getFdWt());
			pstmt.setDouble(5 ,foodRecord.getSinglelCal());
			pstmt.setDouble(6 ,foodRecord.getSingleCho());
			pstmt.setDouble(7 ,foodRecord.getSinglePro());
			pstmt.setDouble(8 ,foodRecord.getSingleFat());
			
			pstmt.setInt(9 ,foodRecord.getMealNo());
			pstmt.setInt(10 ,foodRecord.getFdNo());

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
	public void deleteWithMeal(int mealNo, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = con.prepareStatement(delete_SQL);

			pstmt.setInt(1 ,mealNo);

			pstmt.executeUpdate();

		} catch (SQLException e ) {
			if(con != null) {
				try {
					con.rollback();
				} catch(SQLException se) {
					throw new RuntimeException("刪除食物失敗" + se.getMessage());
				}
				
			}
			throw new RuntimeException("刪除資料失敗" + e.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}	
			}
		}
	}
	
	public void insertWithMeal(FoodRecordVO foodRecord, Connection con) {
		
		PreparedStatement pstmt= null;
		try {
		
			pstmt = con.prepareStatement(insertWithMeal_SQL);
		
		
			pstmt.setInt(1, foodRecord.getMealNo()); //mealNo, 從generatedkey來
			pstmt.setInt(2, foodRecord.getFdNo()); //fdNo
			pstmt.setInt(3, foodRecord.getFdPortion()); //食物份數
			pstmt.setInt(4, foodRecord.getFdWt()); //食物重量
			pstmt.setDouble(5, foodRecord.getSinglelCal()); //
			pstmt.setDouble(6, foodRecord.getSingleCho()); //
			pstmt.setDouble(7, foodRecord.getSinglePro()); //
			pstmt.setDouble(8, foodRecord.getSingleFat()); //
			
			//下面這些留在servlet寫
//			if(foodRecord.getFdPortion() != 0) { //html radio 空值送出來會是null, 到時要讓null轉成0
//				pstmt.setDouble(5, food.getCalPerWt()*foodRecord.getFdPortion());
//				pstmt.setDouble(6, food.getChoPerWt()*foodRecord.getFdPortion());
//				pstmt.setDouble(7, food.getProPerWt()*foodRecord.getFdPortion());
//				pstmt.setDouble(8, food.getFatPerWt()*foodRecord.getFdPortion());
//			} else { 
//				pstmt.setDouble(5, (food.getCalPerWt()/food.getWtPerPortion())*foodRecord.getFdWt()); //  換算成每克幾大卡在乘以多少克 
//				pstmt.setDouble(6, (food.getChoPerWt()/food.getWtPerPortion())*foodRecord.getFdWt());
//				pstmt.setDouble(7, (food.getProPerWt()/food.getWtPerPortion())*foodRecord.getFdWt());
//				pstmt.setDouble(8, (food.getFatPerWt()/food.getWtPerPortion())*foodRecord.getFdWt());
//			}   
			
			pstmt.executeUpdate();
		
		} catch (SQLException e ) {
				if(con != null) {
					try {
						con.rollback();
					} catch(SQLException se) {
						throw new RuntimeException("新增食物失敗" + se.getMessage());
					}
					
				}
				throw new RuntimeException("新增資料失敗" + e.getMessage());
			} finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch (SQLException se) {
						se.printStackTrace();
					}	
				}
			}
		}
		
	
		public List<FoodRecordVO> findByMealNo(int mealNo) {
				
				Connection con = null;
				List<FoodRecordVO> foodRecords = new ArrayList<FoodRecordVO>();

				try {
					con = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement pstmt = con.prepareStatement(findByMealNo_SQL);
					pstmt.setInt(1, mealNo);

					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						FoodRecordVO foodRecord = new FoodRecordVO();

						foodRecord.setMealNo(rs.getInt("mealNo"));
						foodRecord.setFdNo(rs.getInt("fdNo"));
						foodRecord.setFdPortion(rs.getInt("fdPortion"));
						foodRecord.setFdWt(rs.getInt("fdWt"));
						foodRecord.setSinglelCal(rs.getDouble("singlelCal"));
						foodRecord.setSingleCho(rs.getDouble("singleCho"));
						foodRecord.setSinglePro(rs.getDouble("singlePro"));
						foodRecord.setSingleFat(rs.getDouble("singleFat"));

						
						foodRecords.add(foodRecord);
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

				return foodRecords;

				
				
				
				
			}
	

		public static void main(String[] args) {
			
//			FoodRecordDAO dao = new FoodRecordDAO();
//			
//			List<FoodRecordVO> FoodRecords = dao.findByMealNo(1);
//			
//			for (FoodRecordVO FoodRecord : FoodRecords) {
//		
//			}
//			
			
			
			
			
			
			
		}




}

	