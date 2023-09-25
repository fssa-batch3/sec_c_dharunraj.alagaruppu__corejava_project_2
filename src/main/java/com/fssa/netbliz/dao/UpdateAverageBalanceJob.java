package com.fssa.netbliz.dao;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateAverageBalanceJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try (Connection con = ConnectionUtil.getConnection()) {

			try {
				updateAllAccountAverageBalance(con);
			} catch (DAOException e) {

				e.getMessage();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean updateAllAccountAverageBalance(Connection con) throws DAOException {

		final String query = "UPDATE accounts SET avg_balance = avg_balance + avl_balance";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			int rows = pst.executeUpdate();
			System.out.println("update succuss");
			return rows > 0;
		} catch (SQLException e) {

			throw new DAOException("updateAllAccountAverageBalance is failed");
		}

	}

}
