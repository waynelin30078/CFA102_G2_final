package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Product;

public class ProductJDBCDAO implements ProductDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA102_G2?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	// 新增商品
	private static final String INSERT_STMT = 
			"INSERT INTO product (categoryName, pName, pPrice, pInfo, pQuantity, pOnDate, pOffDate, pImage1, pImage2, pImage3, pState) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 更新商品資訊
	private static final String UPDATE = 
			"UPDATE product SET categoryName=?, pName=?, pPrice=?, pInfo=?, pQuantity=?, pOnDate=?, pOffDate=?, pImage1=?, pImage2=?, pImage3=?,"
			+ " pRatingsQuantity=?, pTotalRatings=?, pState=? WHERE pNo =?";
	// 刪除商品
	private static final String DELETE = 
			"DELETE FROM product WHERE pNo =?";
	// 查詢商品(用商品編號)
	private static final String GET_ONE_STMT = 
			"SELECT pNo, categoryName, pName, pPrice, pInfo, pQuantity, pOnDate, pOffDate, pImage1, pImage2, pImage3,"
			+ " pRatingsQuantity, pTotalRatings, pState FROM product WHERE pNo =?";
	// 查詢所有商品
	private static final String GET_ALL_STMT = 
			"SELECT pNo, categoryName, pName, pPrice, pInfo, pQuantity, pOnDate, pOffDate, pImage1, pImage2, pImage3,"
			+ " pRatingsQuantity, pTotalRatings, pState FROM product ORDER BY pNo";
	// 查詢商品類別
	private static final String GET_ALL_Category_STMT = 
			"SELECT DISTINCT categoryName FROM product ORDER BY categoryName";
	// 查詢所有商品(ByPstate)
	private static final String GET_ALL_ByPstate_STMT = 
			"SELECT pNo, categoryName, pName, pPrice, pInfo, pQuantity, pOnDate, pOffDate, pImage1, pImage2, pImage3,"
			+ " pRatingsQuantity, pTotalRatings, pState FROM product WHERE pState=? ORDER BY pNo";	
	// 查詢所有商品(ByCategory)
	private static final String GET_ALL_ByCategory_STMT = 
			"SELECT pNo, categoryName, pName, pPrice, pInfo, pQuantity, pOnDate, pOffDate, pImage1, pImage2, pImage3,"
			+ " pRatingsQuantity, pTotalRatings, pState FROM product WHERE categoryName=? AND pState=1 ORDER BY pNo";
	
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getCategoryName());
			pstmt.setString(2, productVO.getPname());
			pstmt.setInt(3, productVO.getPprice());
			pstmt.setString(4, productVO.getPinfo());
			pstmt.setInt(5, productVO.getPquantity());
			pstmt.setDate(6, productVO.getPonDate());
			pstmt.setDate(7, productVO.getPoffDate());
			pstmt.setString(8, productVO.getPimage1());
			pstmt.setString(9, productVO.getPimage2());
			pstmt.setString(10, productVO.getPimage3());
			pstmt.setInt(11, productVO.getPstate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getCategoryName());
			pstmt.setString(2, productVO.getPname());
			pstmt.setInt(3, productVO.getPprice());
			pstmt.setString(4, productVO.getPinfo());
			pstmt.setInt(5, productVO.getPquantity());
			pstmt.setDate(6, productVO.getPonDate());
			pstmt.setDate(7, productVO.getPoffDate());
			pstmt.setString(8, productVO.getPimage1());
			pstmt.setString(9, productVO.getPimage2());
			pstmt.setString(10, productVO.getPimage3());
			pstmt.setInt(11, productVO.getPratingsQuantity());
			pstmt.setInt(12, productVO.getPtotalRatings());
			pstmt.setInt(13, productVO.getPstate());
			pstmt.setInt(14, productVO.getPno());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(Integer Pno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, Pno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public ProductVO findByPrimaryKey(Integer Pno) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, Pno);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setPno(rs.getInt("pNo"));
				productVO.setCategoryName(rs.getString("categoryName"));
				productVO.setPname(rs.getString("pName"));
				productVO.setPprice(rs.getInt("pPrice"));
				productVO.setPinfo(rs.getString("pInfo"));
				productVO.setPquantity(rs.getInt("pQuantity"));
				productVO.setPonDate(rs.getDate("pOnDate"));
				productVO.setPoffDate(rs.getDate("pOffDate"));
				productVO.setPimage1(rs.getString("pImage1"));
				productVO.setPimage2(rs.getString("pImage2"));
				productVO.setPimage3(rs.getString("pImage3"));
				productVO.setPratingsQuantity(rs.getInt("pRatingsQuantity"));
				productVO.setPtotalRatings(rs.getInt("pTotalRatings"));
				productVO.setPstate(rs.getInt("pState"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setPno(rs.getInt("pNo"));
				productVO.setCategoryName(rs.getString("categoryName"));
				productVO.setPname(rs.getString("pName"));
				productVO.setPprice(rs.getInt("pPrice"));
				productVO.setPinfo(rs.getString("pInfo"));
				productVO.setPquantity(rs.getInt("pQuantity"));
				productVO.setPonDate(rs.getDate("pOnDate"));
				productVO.setPoffDate(rs.getDate("pOffDate"));
				productVO.setPimage1(rs.getString("pImage1"));
				productVO.setPimage2(rs.getString("pImage2"));
				productVO.setPimage3(rs.getString("pImage3"));
				productVO.setPratingsQuantity(rs.getInt("pRatingsQuantity"));
				productVO.setPtotalRatings(rs.getInt("pTotalRatings"));
				productVO.setPstate(rs.getInt("pState"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
	
	@Override
	 public List<ProductVO> getAll(Map<String, String[]> map) {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from product "
			          + jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
			          + "order by pno";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
				rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setPno(rs.getInt("pNo"));
				productVO.setCategoryName(rs.getString("categoryName"));
				productVO.setPname(rs.getString("pName"));
				productVO.setPprice(rs.getInt("pPrice"));
				productVO.setPinfo(rs.getString("pInfo"));
				productVO.setPquantity(rs.getInt("pQuantity"));
				productVO.setPonDate(rs.getDate("pOnDate"));
				productVO.setPoffDate(rs.getDate("pOffDate"));
				productVO.setPimage1(rs.getString("pImage1"));
				productVO.setPimage2(rs.getString("pImage2"));
				productVO.setPimage3(rs.getString("pImage3"));
				productVO.setPratingsQuantity(rs.getInt("pRatingsQuantity"));
				productVO.setPtotalRatings(rs.getInt("pTotalRatings"));
				productVO.setPstate(rs.getInt("pState"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}	


	@Override
	public List<ProductVO> getCategory() {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_Category_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setCategoryName(rs.getString("categoryName"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
	
	@Override
	public List<ProductVO> getAll_ByPstate(Integer pstate) {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ByPstate_STMT);
			pstmt.setInt(1, pstate);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				productVO = new ProductVO();
				productVO.setPno(rs.getInt("pNo"));
				productVO.setCategoryName(rs.getString("categoryName"));
				productVO.setPname(rs.getString("pName"));
				productVO.setPprice(rs.getInt("pPrice"));
				productVO.setPinfo(rs.getString("pInfo"));
				productVO.setPquantity(rs.getInt("pQuantity"));
				productVO.setPonDate(rs.getDate("pOnDate"));
				productVO.setPoffDate(rs.getDate("pOffDate"));
				productVO.setPimage1(rs.getString("pImage1"));
				productVO.setPimage2(rs.getString("pImage2"));
				productVO.setPimage3(rs.getString("pImage3"));
				productVO.setPratingsQuantity(rs.getInt("pRatingsQuantity"));
				productVO.setPtotalRatings(rs.getInt("pTotalRatings"));
				productVO.setPstate(rs.getInt("pState"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
	

	public static void main(String[] args) {

		ProductJDBCDAO dao = new ProductJDBCDAO();

		// INSERT_STMT
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setCategoryName("魚油/DHA");
//		productVO1.setPname("澳佳寶Blackmores無腥味濃縮深海魚油");
//		productVO1.setPprice(1980);
//		productVO1.setPinfo("Info");
//		productVO1.setPquantity(250);
//		productVO1.setPimage1("images/4.jpg");
//		productVO1.setPimage2("images/5.jpg");
//		productVO1.setPimage3("images/6.jpg");
//		productVO1.setPratingsQuantity(8);
//		productVO1.setPtotalRatings(32);
//		dao.insert(productVO1);

		// UPDATE
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setCategoryName("魚油/DHA");
//		productVO2.setPname("澳佳寶Blackmores無腥味濃縮深海魚油");
//		productVO2.setPprice(550);
//		productVO2.setPinfo("澳佳寶Blackmores無腥味濃縮深海魚油");
//		productVO2.setPquantity(100);
//		productVO2.setPonDate(java.sql.Date.valueOf("2021-08-22"));
//		productVO2.setPoffDate(java.sql.Date.valueOf("9999-12-31"));
//		productVO2.setPimage1("images/1.jpg");
//		productVO2.setPimage2("images/2.jpg");
//		productVO2.setPimage3("images/3.jpg");
//		productVO2.setPstate(1);
//		productVO2.setPno(1);
//		dao.update(productVO2);

		// DELETE
//		dao.delete(2);

		// GET_ONE_STMT
		ProductVO productVO3 = dao.findByPrimaryKey(1);
		System.out.print(productVO3.getPno() + ",");
		System.out.print(productVO3.getCategoryName() + ",");
		System.out.print(productVO3.getPname() + ",");
		System.out.print(productVO3.getPprice() + ",");
		System.out.print(productVO3.getPinfo() + ",");
		System.out.print(productVO3.getPquantity() + ",");
		System.out.print(productVO3.getPonDate() + ",");
		System.out.print(productVO3.getPoffDate() + ",");
		System.out.print(productVO3.getPimage1() + ",");
		System.out.print(productVO3.getPimage2() + ",");
		System.out.print(productVO3.getPimage3() + ",");
		System.out.print(productVO3.getPratingsQuantity() + ",");
		System.out.print(productVO3.getPtotalRatings() + ",");
		System.out.println(productVO3.getPstate());

		System.out.println("--------------------------------------------------------");

		// GET_ALL_STMT
		List<ProductVO> list3 = dao.getAll();
		for (ProductVO aProduct6 : list3) {
			System.out.print(aProduct6.getPno() + ",");
			System.out.print(aProduct6.getCategoryName() + ",");
			System.out.print(aProduct6.getPname() + ",");
			System.out.print(aProduct6.getPprice() + ",");
			System.out.print(aProduct6.getPinfo() + ",");
			System.out.print(aProduct6.getPquantity() + ",");
			System.out.print(aProduct6.getPonDate() + ",");
			System.out.print(aProduct6.getPoffDate() + ",");
			System.out.print(aProduct6.getPimage1() + ",");
			System.out.print(aProduct6.getPimage2() + ",");
			System.out.print(aProduct6.getPimage3() + ",");
			System.out.print(aProduct6.getPratingsQuantity() + ",");
			System.out.print(aProduct6.getPtotalRatings() + ",");
			System.out.println(aProduct6.getPstate());
			System.out.println();
		}

		System.out.println("--------------------------------------------------------");

	}





}
