package com.cab.dbutil;

import java.sql.*;

public enum DBUtil {
	db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		String dbName = "cabdb";
		String url = "jdbc:mysql://localhost:3306/" + dbName + "?autoReconnect=true&useSSL=false";
		String userName = "rajesh";
		String password = "mysql123";
		try {
			con = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	public void closeResources() {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException s1) {
				System.out.println(s1.getMessage());
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException s1) {
				System.out.println(s1.getMessage());
			}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException s1) {
				System.out.println(s1.getMessage());
			}

		if (con != null)
			try {
				con.close();
			} catch (SQLException s1) {
				System.out.println(s1.getMessage());
			}
	}
}
