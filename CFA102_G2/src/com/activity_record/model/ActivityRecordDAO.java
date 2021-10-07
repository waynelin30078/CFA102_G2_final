package com.activity_record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.activity.model.ActivityDAO;
import com.activity.model.ActivityVO;
import com.diary.model.DiaryDAO;
import com.diary.model.DiaryVO;

public class ActivityRecordDAO implements ActivityRecordDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";

	private static final String insert_SQL = "INSERT INTO act_record VALUES(?, ?, ?, ?, ?)";
	private static final String delete_SQL = "DELETE FROM act_record WHERE diaryNo=? AND actNo=?;";
	private static final String findByPrimaryKey_SQL = "SELECT * FROM act_record WHERE diaryNo=? AND actNo = ?;";
	private static final String findByDiaryNo_SQL = "SELECT * FROM act_record WHERE diaryNo=?;";

	
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(ActivityRecordVO actRecord) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(insert_SQL);

			pstmt.setInt(1, actRecord.getDiaryNo());
			pstmt.setInt(2, actRecord.getActNo());
			pstmt.setDouble(3, actRecord.getActHr());
			pstmt.setInt(4, actRecord.getWt());
			pstmt.setDouble(5, actRecord.getCalBurn());

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
	public void delete(ActivityRecordVO actRecord) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(delete_SQL);

			pstmt.setInt(1, actRecord.getDiaryNo());
			pstmt.setInt(2, actRecord.getActNo());

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
	public ActivityRecordVO findByPrimaryKey(int diaryNo, int actNo) {

		Connection con = null;
		ActivityRecordVO actRecord = new ActivityRecordVO();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByPrimaryKey_SQL);

			pstmt.setInt(1, diaryNo);
			pstmt.setInt(2, actNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				actRecord.setDiaryNo(rs.getInt("diaryNo"));
				actRecord.setActNo(rs.getInt("actNo"));
				actRecord.setActHr(rs.getDouble("actHr"));
				actRecord.setWt(rs.getInt("wt"));
				actRecord.setCalBurn(rs.getDouble("calBurn"));

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

		return actRecord;
	}

	@Override
	public List<ActivityRecordVO> findByDiaryNo(int diaryNo) {

		Connection con = null;
		List<ActivityRecordVO> actRecords = new ArrayList<ActivityRecordVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByDiaryNo_SQL);

			pstmt.setInt(1, diaryNo);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ActivityRecordVO actRecord = new ActivityRecordVO();

				actRecord.setDiaryNo(rs.getInt("diaryNo"));
				actRecord.setActNo(rs.getInt("actNo"));
				actRecord.setActHr(rs.getDouble("actHr"));
				actRecord.setWt(rs.getInt("wt"));
				actRecord.setCalBurn(rs.getDouble("calBurn"));

				actRecords.add(actRecord);

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

		return actRecords;

	}


	
	public void addActivity(int diaryNo, ActivityRecordVO activityRecord) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(insert_SQL);
			
			con.setAutoCommit(false);
			
			
			pstmt.setInt(1, diaryNo);
			pstmt.setInt(2, activityRecord.getActNo());
			pstmt.setDouble(3, activityRecord.getActHr());
			pstmt.setInt(4, activityRecord.getWt());
			pstmt.setDouble(5, activityRecord.getCalBurn());
			
			pstmt.executeUpdate();
			
			DiaryDAO diaryDAO = new DiaryDAO();
			
			DiaryVO diary = diaryDAO.findByDiaryNo(diaryNo);
			
			diaryDAO.updateActivity(diary, activityRecord, con);
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.println("activityRecord流程出現問題");
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
	
	public void deleteActivity(DiaryVO diary, ActivityRecordVO activityRecord) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(delete_SQL);
			
			con.setAutoCommit(false);
			
			DiaryDAO diaryDAO = new DiaryDAO();
			
			diaryDAO.deleteActivity(diary, activityRecord, con);
			
			
			pstmt.setInt(1, diary.getDiaryNo());
			pstmt.setInt(2, activityRecord.getActNo());
			
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			
		
		}catch (SQLException se) {
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
		// TODO Auto-generated method stub

		ActivityRecordDAO dao = new ActivityRecordDAO();
		
		ActivityRecordVO activityRecord = new ActivityRecordVO();
		
		DiaryDAO diaryDAO = new DiaryDAO();
		DiaryVO diary = diaryDAO.findByDiaryNo(1);
		
		
		ActivityDAO activityDAO = new ActivityDAO();
		
		ActivityVO activity1 = activityDAO.findById(5);
		
		activityRecord.setDiaryNo(diary.getDiaryNo());
		activityRecord.setActNo(activity1.getActNo());
		activityRecord.setWt(80);
		activityRecord.setActHr(0.5);
		activityRecord.setCalBurn(activityRecord.getActHr()*activityRecord.getWt()*activity1.getCalPerKgHr());
//		
//		dao.addActivity(1, activityRecord);
		
		
		
		
		dao.deleteActivity(diary, activityRecord);
		
		
	}

	
	
}
