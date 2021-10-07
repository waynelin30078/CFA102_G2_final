package com.d_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dietician.model.DieticianVO;
import com.member.model.MemberVO;

public class DorderDAO implements DorderDAO_interface {

	

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";

	private static final String insert_SQL = "INSERT INTO D_order (dOrderNo,dNo,mNo,subStart,subEnd,mthFee,autoSubs)VALUES(default, ?, ?, ?, ?, ?, ? );";
	private static final String update_SQL = "UPDATE d_order SET dno = ?, mno = ?, "
			+ "subStart = ?, subEnd = ?, mthFee = ?, dreview = ?, dscore = ?, autoSubs = ? WHERE dorderNo = ?";
	private static final String findByPrimaryKey_SQL = "SELECT * FROM d_Order WHERE dorderNo = ?";
	private static final String getAll_SQL = "SELECT * FROM d_order;";
	private static final String getActiveOrder_SQL = "SELECT * FROM d_Order WHERE subEnd > current_timestamp;";
	private static final String getActiveOrderByDNo_SQL = "SELECT * FROM d_order WHERE dno = ? AND subEnd > current_timestamp;";
	private static final String update_autoSubs = "UPDATE d_order SET autoSubs = ? WHERE dno = ? and mno=?";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(DorderVO dOrder) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(insert_SQL);

			pstmt.setInt(1, dOrder.getDno());
			pstmt.setInt(2, dOrder.getMno());
			pstmt.setTimestamp(3, dOrder.getSubStart());
			pstmt.setTimestamp(4, dOrder.getSubEnd());
			pstmt.setInt(5, dOrder.getMthFee());
			pstmt.setInt(6, dOrder.getAutoSubs());

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
	public void update(DorderVO dOrder) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(update_SQL);

			pstmt.setInt(1, dOrder.getDno());
			pstmt.setInt(2, dOrder.getMno());
			pstmt.setTimestamp(3, dOrder.getSubStart());
			pstmt.setTimestamp(4, dOrder.getSubEnd());
			pstmt.setInt(5, dOrder.getMthFee());
			pstmt.setString(6, dOrder.getDreview());
			pstmt.setInt(7, dOrder.getDscore());
			pstmt.setInt(8, dOrder.getAutoSubs());
			pstmt.setInt(9, dOrder.getDorderNo());

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
	public DorderVO findByPrimaryKey(int dorderNo) {
		Connection con = null;
		DorderVO dOrder = new DorderVO();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(findByPrimaryKey_SQL);
			pstmt.setInt(1, dorderNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				dOrder.setDorderNo(rs.getInt("dorderNo"));
				dOrder.setDno(rs.getInt("dno"));
				dOrder.setMno(rs.getInt("mno"));
				dOrder.setSubStart(rs.getTimestamp("subStart"));
				dOrder.setSubEnd(rs.getTimestamp("subEnd"));
				dOrder.setMthFee(rs.getInt("mthFee"));
				dOrder.setDreview(rs.getString("dreview"));
				dOrder.setDscore(rs.getInt("dscore"));
				dOrder.setAutoSubs(rs.getInt("autoSubs"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dOrder;
	}

	@Override
	public List<DorderVO> getAll() {
		Connection con = null;
		List<DorderVO> dOrders = new ArrayList<DorderVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(getAll_SQL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				DorderVO dOrder = new DorderVO();
				dOrder.setDorderNo(rs.getInt("dorderNo"));
				dOrder.setDno(rs.getInt("dno"));
				dOrder.setMno(rs.getInt("mno"));
				dOrder.setSubStart(rs.getTimestamp("subStart"));
				dOrder.setSubEnd(rs.getTimestamp("subEnd"));
				dOrder.setMthFee(rs.getInt("mthFee"));
				dOrder.setDreview(rs.getString("dreview"));
				dOrder.setDscore(rs.getInt("dscore"));
				dOrder.setAutoSubs(rs.getInt("autoSubs"));

				dOrders.add(dOrder);

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

		return dOrders;
	}

	@Override
	public List<DorderVO> getActiveOrder() {
		Connection con = null;
		List<DorderVO> dOrders = new ArrayList<DorderVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(getActiveOrder_SQL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				DorderVO dOrder = new DorderVO();
				dOrder.setDorderNo(rs.getInt("dorderNo"));
				dOrder.setDno(rs.getInt("dno"));
				dOrder.setMno(rs.getInt("mno"));
				dOrder.setSubStart(rs.getTimestamp("subStart"));
				dOrder.setSubEnd(rs.getTimestamp("subEnd"));
				dOrder.setMthFee(rs.getInt("mthFee"));
				dOrder.setDreview(rs.getString("dreview"));
				dOrder.setDscore(rs.getInt("dscore"));
				dOrder.setAutoSubs(rs.getInt("autoSubs"));

				dOrders.add(dOrder);

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

		return dOrders;

	}

	@Override
	public List<DorderVO> getActiveOrderByDNo(int dno) {
		Connection con = null;
		List<DorderVO> dOrders = new ArrayList<DorderVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(getActiveOrderByDNo_SQL);
			pstmt.setInt(1, dno);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				DorderVO dOrder = new DorderVO();
				dOrder.setDorderNo(rs.getInt("dorderNo"));
				dOrder.setDno(rs.getInt("dno"));
				dOrder.setMno(rs.getInt("mno"));
				dOrder.setSubStart(rs.getTimestamp("subStart"));
				dOrder.setSubEnd(rs.getTimestamp("subEnd"));
				dOrder.setMthFee(rs.getInt("mthFee"));
				dOrder.setDreview(rs.getString("dreview"));
				dOrder.setDscore(rs.getInt("dscore"));
				dOrder.setAutoSubs(rs.getInt("autoSubs"));

				dOrders.add(dOrder);

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

		return dOrders;

	}
	public void update_autoSubs(DorderVO dOrder) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(update_autoSubs);
			pstmt.setInt(1, dOrder.getAutoSubs());
			pstmt.setInt(2, dOrder.getMno());
			pstmt.setInt(3, dOrder.getDno());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		
		DorderDAO dao = new DorderDAO();
		DorderVO dOrder = dao.findByPrimaryKey(2);
		
		dOrder.setDreview("");
		
		dao.update(dOrder);


//		
//		long subStart = order.getSubStart().getTime(); //sql.TimeStamp to long
//		long subEnd = order.getSubEnd().getTime();
//		System.out.println((subEnd-subStart)/(1000*60*60*24));

	}

}
