package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fssa.netbliz.exception.DaoException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {
	private ConnectionUtil() {
//		private constructor
	}

	public static Connection getConnection() throws SQLException {
		Connection con = null;

		String url;
		String userName;
		String passWord;

		if (System.getenv("CI") != null) {
			url = System.getenv("DATABASE_HOST");
			userName = System.getenv("DATABASE_USERNAME");
			passWord = System.getenv("DATABASE_PASSWORD");
		} else {
			Dotenv env = Dotenv.load();
			url = env.get("DATABASE_HOST");
			userName = env.get("DATABASE_USERNAME");
			passWord = env.get("DATABASE_PASSWORD");
			Logger.info("env success");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			Logger.info("Connection success");
		} catch (Exception e) {
			throw new RuntimeException("Unable to connect to the database");
		}
		finally {
			con.close();
		}
		
		return con;
	}

}
