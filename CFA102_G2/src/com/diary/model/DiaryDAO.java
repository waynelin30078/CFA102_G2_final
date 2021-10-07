package com.diary.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.activity_record.model.ActivityRecordVO;
import com.dietician.model.DieticianVO;
import com.food_record.model.FoodRecordDAO;
import com.food_record.model.FoodRecordVO;
import com.meal.model.MealVO;

public class DiaryDAO implements DiaryDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";

	private static final String insert_SQL = "INSERT INTO diary VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String update_SQL = "UPDATE diary SET mno= ?, dno= ?, diaryDate= ?, ht=?, wt=?, bodyFat=?, wc=?, bodyPic=?, viewState=?, reply=?, totalCal=?, totalCho=?, totalPro=?, totalFat=?, totalCalBurn=? WHERE diaryNO = ?;";
	private static final String delete_SQL = "DELETE FROM diary WHERE diaryNo = ?;";
	private static final String findByMnoDate_SQL = "SELECT * FROM diary WHERE mno = ? AND diaryDate = ?;";
	private static final String findByMember_SQL = "SELECT * FROM diary WHERE mno = ?;";
	private static final String findByDiaryNo_SQL = "SELECT * FROM diary WHERE diaryno = ?;";
	private static final String findByDieticianState_SQL = "SELECT * FROM diary WHERE dno = ? AND viewState= ?;";
	private static final String updateNutrition_SQL = "UPDATE diary SET totalCal=?, totalCho=?, totalPro=?, totalFat=? WHERE diaryNO = ?;";
	private static final String updateActivity_SQL = "UPDATE diary SET totalCalBurn =? WHERE diaryNo =?";

	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(DiaryVO diary) {

		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(insert_SQL);

			pstmt.setInt(1, diary.getMno());
			
			if(diary.getDno() == null) {
				pstmt.setNull(2, Types.NULL);	
			}else {
			pstmt.setInt(2, diary.getDno());	
			}
			
			pstmt.setDate(3, diary.getDiaryDate());
			pstmt.setInt(4, diary.getHt());
			pstmt.setInt(5, diary.getWt());
			pstmt.setDouble(6, diary.getBodyFat());
			pstmt.setInt(7, diary.getWc());
			pstmt.setString(8, diary.getBodyPic());
			pstmt.setInt(9, diary.getViewState());
			pstmt.setString(10, diary.getReply());
			pstmt.setDouble(11, diary.getTotalCal());
			pstmt.setDouble(12, diary.getTotalCho());
			pstmt.setDouble(13, diary.getTotalPro());
			pstmt.setDouble(14, diary.getTotalFat());
			pstmt.setDouble(15, diary.getTotalCalBurn());

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
	public void update(DiaryVO diary) {

		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(update_SQL);
			
			pstmt.setInt(1, diary.getMno());
			
			if(diary.getDno() == null) {
				pstmt.setNull(2, Types.NULL);	
			}else {
			pstmt.setInt(2, diary.getDno());	
			}
			
			pstmt.setDate(3, diary.getDiaryDate());
			pstmt.setInt(4, diary.getHt());
			pstmt.setInt(5, diary.getWt());
			pstmt.setDouble(6, diary.getBodyFat());
			pstmt.setInt(7, diary.getWc());
			pstmt.setString(8, diary.getBodyPic());
			pstmt.setInt(9, diary.getViewState());
			pstmt.setString(10, diary.getReply());
			pstmt.setDouble(11, diary.getTotalCal());
			pstmt.setDouble(12, diary.getTotalCho());
			pstmt.setDouble(13, diary.getTotalPro());
			pstmt.setDouble(14, diary.getTotalFat());
			pstmt.setDouble(15, diary.getTotalCalBurn());
			
			
			pstmt.setInt(16, diary.getDiaryNo());

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
	public void delete(DiaryVO diary) {
		Connection con = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(delete_SQL);

			pstmt.setInt(1, diary.getDiaryNo());

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
	public DiaryVO findByDate(int mno, Date diaryDate) {

		Connection con = null;
		DiaryVO diary = new DiaryVO();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByMnoDate_SQL);
			pstmt.setInt(1, mno);
			pstmt.setDate(2, diaryDate);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				diary.setDiaryNo(rs.getInt("diaryNo"));
				diary.setMno(rs.getInt(mno));
				diary.setDno(rs.getInt("dno"));
				diary.setDiaryDate(rs.getDate(4)); // 用date還要轉成String
				diary.setHt(rs.getInt("ht"));
				diary.setWt(rs.getInt("wt"));
				diary.setBodyFat(rs.getDouble("bodyFat"));
				diary.setWc(rs.getInt("wc"));
				diary.setBodyPic(rs.getString("bodyPic"));
				diary.setViewState(rs.getInt("viewState"));
				diary.setReply(rs.getString("reply"));
				diary.setTotalCal(rs.getDouble("totalCal"));
				diary.setTotalCho(rs.getDouble("totalCho"));
				diary.setTotalPro(rs.getDouble("totalPro"));
				diary.setTotalFat(rs.getDouble("totalFat"));
				diary.setTotalCalBurn(rs.getDouble("totalCalBurn"));
;

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
		return diary;
	}
	
	@Override
	public DiaryVO findByDiaryNo(int diaryNo) {
		
		Connection con = null;
		DiaryVO diary = new DiaryVO();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByDiaryNo_SQL);
			pstmt.setInt(1, diaryNo);


			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				diary.setDiaryNo(diaryNo);
				diary.setMno(rs.getInt("mno"));
				diary.setDno(rs.getInt("dno"));
				diary.setDiaryDate(rs.getDate(4)); // 用date還要轉成String
				diary.setHt(rs.getInt("ht"));
				diary.setWt(rs.getInt("wt"));
				diary.setBodyFat(rs.getDouble("bodyFat"));
				diary.setWc(rs.getInt("wc"));
				diary.setBodyPic(rs.getString("bodyPic"));
				diary.setViewState(rs.getInt("viewState"));
				diary.setReply(rs.getString("reply"));
				diary.setTotalCal(rs.getDouble("totalCal"));
				diary.setTotalCho(rs.getDouble("totalCho"));
				diary.setTotalPro(rs.getDouble("totalPro"));
				diary.setTotalFat(rs.getDouble("totalFat"));
				diary.setTotalCalBurn(rs.getDouble("totalCalBurn"));
;

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
		return diary;
		
	}
	
	@Override
	public List<DiaryVO> findByMember(int mno) {

		Connection con = null;
		List<DiaryVO> diaries = new ArrayList<DiaryVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByMember_SQL);
			pstmt.setInt(1, mno);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				DiaryVO diary = new DiaryVO();

				diary.setDiaryNo(rs.getInt("diaryNo"));
				diary.setMno(rs.getInt("mno"));
				diary.setDno(rs.getInt("dno"));
				diary.setDiaryDate(rs.getDate("diaryDate"));
				diary.setHt(rs.getInt("ht"));
				diary.setWt(rs.getInt("wt"));
				diary.setBodyFat(rs.getDouble("bodyFat"));
				diary.setWc(rs.getInt("wc"));
				diary.setBodyPic(rs.getString("bodyPic"));
				diary.setViewState(rs.getInt("viewState"));
				diary.setReply(rs.getString("reply"));
				diary.setTotalCal(rs.getDouble("totalCal"));
				diary.setTotalCho(rs.getDouble("totalCho"));
				diary.setTotalPro(rs.getDouble("totalPro"));
				diary.setTotalFat(rs.getDouble("totalFat"));
				diary.setTotalCalBurn(rs.getDouble("totalCalBurn"));
				
				diaries.add(diary);
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

		return diaries;

	}

	@Override
	public List<DiaryVO> findByDieticianState(int dno, int viewState) {

		Connection con = null;
		List<DiaryVO> diaries = new ArrayList<DiaryVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByDieticianState_SQL);
			pstmt.setInt(1, dno);
			pstmt.setInt(2, viewState);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				DiaryVO diary = new DiaryVO();

				diary.setDiaryNo(rs.getInt("diaryNo"));
				diary.setMno(rs.getInt("mno"));
				diary.setDno(rs.getInt("dno"));
				diary.setDiaryDate(rs.getDate("diaryDate"));
				diary.setHt(rs.getInt("ht"));
				diary.setWt(rs.getInt("wt"));
				diary.setBodyFat(rs.getDouble("bodyFat"));
				diary.setWc(rs.getInt("wc"));
				diary.setBodyPic(rs.getString("bodyPic"));
				diary.setViewState(rs.getInt("viewState"));
				diary.setReply(rs.getString("reply"));
				diary.setTotalCal(rs.getDouble("totalCal"));
				diary.setTotalCho(rs.getDouble("totalCho"));
				diary.setTotalPro(rs.getDouble("totalPro"));
				diary.setTotalFat(rs.getDouble("totalFat"));

				diaries.add(diary);
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

		return diaries;

	}

	public void updateNutrition(DiaryVO diary, MealVO meal, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt =  con.prepareStatement(updateNutrition_SQL);
			
			Double updatedCal = diary.getTotalCal() + meal.getMealCal();
			pstmt.setDouble(1, updatedCal);
			
			Double updatedCho = diary.getTotalCho() + meal.getMealCho();
			pstmt.setDouble(2, updatedCho);
			
			Double updatedPro = diary.getTotalPro() + meal.getMealPro();
			pstmt.setDouble(3, updatedPro);
			
			Double updatedFat = diary.getTotalFat() + meal.getMealFat();
			pstmt.setDouble(4, updatedFat);
			
			pstmt.setInt(5, diary.getDiaryNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			if(con != null) {
				try {
					con.rollback();
				} catch(SQLException se) {
					throw new RuntimeException("更新總營養素失敗" + se.getMessage());
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
	
	public void deductNutrition(DiaryVO diary, MealVO meal, Connection con) {

		PreparedStatement pstmt = null;
		
		try {
			pstmt =  con.prepareStatement(updateNutrition_SQL);
			
			Double updatedCal = diary.getTotalCal() - meal.getMealCal();
			pstmt.setDouble(1, updatedCal);
			
			Double updatedCho = diary.getTotalCho() - meal.getMealCho();
			pstmt.setDouble(2, updatedCho);
			
			Double updatedPro = diary.getTotalPro() - meal.getMealPro();
			pstmt.setDouble(3, updatedPro);
			
			Double updatedFat = diary.getTotalFat() - meal.getMealFat();
			pstmt.setDouble(4, updatedFat);
			
			pstmt.setInt(5, diary.getDiaryNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			if(con != null) {
				try {
					con.rollback();
				} catch(SQLException se) {
					throw new RuntimeException("刪除總營養素失敗" + se.getMessage());
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
	
	public void updateActivity(DiaryVO diary, ActivityRecordVO activityRecord, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt =  con.prepareStatement(updateActivity_SQL);
			
			Double updatedCalBurn = diary.getTotalCalBurn() + activityRecord.getCalBurn();
			pstmt.setDouble(1, updatedCalBurn);
			
			pstmt.setInt(2, diary.getDiaryNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			if(con != null) {
				try {
					con.rollback();
				} catch(SQLException se) {
					throw new RuntimeException("更新總消耗熱量失敗" + se.getMessage());
				}
				
			}
			throw new RuntimeException("更新資料失敗" + e.getMessage());
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
	
	public void deleteActivity(DiaryVO diary,  ActivityRecordVO activityRecord, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt =  con.prepareStatement(updateActivity_SQL);
			
			
			Double updatedCalBurn = diary.getTotalCalBurn() - activityRecord.getCalBurn();
			pstmt.setDouble(1, updatedCalBurn);
			pstmt.setInt(2, diary.getDiaryNo());

		
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			if(con != null) {
				try {
					con.rollback();
				} catch(SQLException se) {
					throw new RuntimeException("刪除消耗熱量失敗" + se.getMessage());
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
	
	
	public void deleteDiaryWithAllRecords(int diaryNo) {
		
		Connection con = null;
		Statement stmt = null;
		
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			con.setAutoCommit(false);
			stmt = con.createStatement();;
			stmt.addBatch("SET FOREIGN_KEY_CHECKS = 0");
			stmt.addBatch("DELETE FROM food_record WHERE mealno in (select mealno from meal where diaryNo = " + diaryNo + ");");
			stmt.addBatch("DELETE FROM meal WHERE diaryNo in (" + diaryNo +" );");
			stmt.addBatch("DELETE FROM act_record WHERE diaryNo in (" + diaryNo + ");");
			stmt.addBatch("DELETE FROM diary WHERE diaryNo = "+ diaryNo +";");
			stmt.addBatch("SET FOREIGN_KEY_CHECKS = 1");
			stmt.executeBatch();

			con.commit();
			con.setAutoCommit(true);
			
		}
		catch (SQLException se) {
			if (con != null) {
				try {
					System.err.println("刪除流程出現問題");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("資料出現錯誤, 刪除失敗 "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("資料出現錯誤, 刪除失敗"
					+ se.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
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
		DiaryDAO dao = new DiaryDAO();
//		
//		String str="2021-02-04";
//		Date day = Date.valueOf(str);
//		
//		DiaryVO diary1 = dao.findByDate(2, day);
//		
//		diary1.setTotalCalBurn(300.5);
//		dao.update(diary1);
//		
//		System.out.println(diary1.getTotalCalBurn());
		
//		List<DiaryVO> diaries = dao.findByDieticianState(1, 1);
//		
//		
//		for(DiaryVO diary : diaries) {
//			System.out.println(diary.getReply());
//		}
		

//		dao.deleteDiaryWithAllRecords(4);
		
		

	}

}
