package com.fssa.netbliz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private ConnectionUtil() {
//		private constructor
	}

	public static Connection getConnection() throws SQLException {
		Connection con = null;

		String url;
		String userName;
		String passWord;
		
//		url = "jdbc:mysql://localhost:3306/netbliz";
//		userName = "root";
//		passWord = "root";

		url = System.getenv("DATABASE_HOST");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			Logger.info("Connection success");
		} catch (Exception e) {
			throw new RuntimeException("Unable to connect to the database");
		}

		return con;
	}

}
