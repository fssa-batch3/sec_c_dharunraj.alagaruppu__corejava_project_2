 package com.fssa.account.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() {

		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/netbliz";
		String userName = "root"; 
		String passWord = "root";

//		String url = "jdbc:mysql://aws.connect.psdb.cloud:3306/core_java";
//		String userName = "tp7ax1chq5j66dxhoti0";
//		String passWord = "pscale_pw_i6L56hnzz5VtS5E91CoWZT2esIrfarM5AemnbiMhnfx";

		// Connection conn = DriverManager.getConnection(url, userName, password);
		try {
			// Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connection successfull");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to connect to the database");
		}
		return con; 
	}

}


