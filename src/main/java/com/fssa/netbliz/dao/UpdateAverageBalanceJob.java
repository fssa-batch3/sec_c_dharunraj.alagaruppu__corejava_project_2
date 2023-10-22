package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fssa.netbliz.constants.NetblizConstants;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.model.CronJob;
import com.fssa.netbliz.util.ConnectionUtil;
import com.fssa.netbliz.util.Logger;

public class UpdateAverageBalanceJob implements Job {

	public static final int STARTING_DATE_OF_MONTH = 1;

	/**
	 * Executes the scheduled job for updating average balances and related tasks.
	 *
	 * @param context The JobExecutionContext for the Quartz job.
	 * @throws JobExecutionException If there are issues with job execution.
	 */

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try (Connection con = ConnectionUtil.getConnection()) {

			LocalDate currentDate = LocalDate.now();

			boolean date = currentDate.getDayOfMonth() == STARTING_DATE_OF_MONTH;

			if (date) {
				dataTransfer();
			}

			updateAllAccountAverageBalance(con);
			updateClosingBalanceTable(con);

		} catch (SQLException | DAOException e) {
			e.getMessage();
		}
	}

	/**
	 * Updates the average balance for all active accounts.
	 *
	 * @param con The database connection.
	 * @return True if the update is successful, otherwise false.
	 * @throws DAOException If there are issues with data access.
	 */

	public static boolean updateAllAccountAverageBalance(Connection con) throws DAOException {

		final String query = "UPDATE accounts SET avg_balance = avg_balance + avl_balance WHERE is_active = ?";

		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setBoolean(1, NetblizConstants.STATIC_IS_ACTIVE_TRUE);
			int rows = pst.executeUpdate();
			Logger.info("cron update succuss");
			updateDateTable(con);
			return rows > 0;
		} catch (SQLException e) {
			throw new DAOException("updateAllAccountAverageBalance is failed");
		}

	}

	/**
	 * Updates the date table with the current date.
	 *
	 * @param con The database connection.
	 * @return True if the update is successful, otherwise false.
	 * @throws DAOException If there are issues with data access.
	 */

	public static boolean updateDateTable(Connection con) throws DAOException {

		final String query = "INSERT INTO date_table (updated_date) VALUES (NOW())";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			int row = pst.executeUpdate();
			Logger.info("Today date update succuss");
			return row == 1;
		} catch (SQLException e) {
			throw new DAOException("updateDateTable is failed");
		}
	}

	/**
	 * Updates the closing balance table with the end-of-day balances of all
	 * accounts.
	 *
	 * @param con The database connection.
	 * @throws DAOException If there are issues with data access.
	 */

	public static void updateClosingBalanceTable(Connection con) throws DAOException {

		final String query = "INSERT INTO closing_balance (date_id,acc_no,eod_balance) VALUES (? , ? , ? )";

		List<Account> list = getAllAccountClosingBalance(con);

		int id = getDateIdFromDateTable(con);

		for (Account acc : list) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setInt(1, id);
				pst.setString(2, acc.getAccountNumber());
				pst.setDouble(3, acc.getAvailableBalance());
				pst.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("updateClosingBalanceTable is failed");
			}

		}

	}

	/**
	 * Retrieves a list of all accounts with their available balances.
	 *
	 * @param con The database connection.
	 * @return A list of Account objects representing account details.
	 * @throws DAOException If there are issues with data access.
	 */

	public static List<Account> getAllAccountClosingBalance(Connection con) throws DAOException {

		List<Account> list = new ArrayList<>();
		final String query = "SELECT acc_no,avl_balance FROM accounts";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {

					Account acc = new Account();

					acc.setAccountNumber(rs.getString("acc_no"));
					acc.setAvailableBalance(rs.getDouble("avl_balance"));
					list.add(acc);
				}
			}

		} catch (SQLException e) {
			throw new DAOException("getAllAccountClosingBalance is failed");
		}
		Logger.info("Account details collected from database");
		return list;
	}

	/**
	 * Retrieves the latest date ID from the date table.
	 *
	 * @param con The database connection.
	 * @return The latest date ID, or 0 if not found.
	 * @throws DAOException If there are issues with data access.
	 */

	public static int getDateIdFromDateTable(Connection con) throws DAOException {

		int id = 0;

		final String query = "SELECT date_id FROM date_table ORDER BY date_id DESC LIMIT 1";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					id = rs.getInt("date_id");
					Logger.info("date id get successfully");

				}
			}

		} catch (SQLException e) {
			throw new DAOException("getDateIdFromDateTable is failed");
		}

		return id;
	}

	/**
	 * Retrieves a list of CronJob objects containing account closing balance
	 * details.
	 *
	 * @param accNo The account number to retrieve details for.
	 * @return A list of CronJob objects representing closing balance details.
	 * @throws DAOException If there are issues with data access.
	 */

	public static List<CronJob> getListingDetails(String accNo) throws DAOException {

		List<CronJob> list = new ArrayList<>();

		final String query = "SELECT c.acc_no,c.eod_balance,d.updated_date FROM closing_balance AS c INNER JOIN date_table AS d ON c.date_id = d.date_id WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, accNo);
				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {

						CronJob cron = new CronJob();

						cron.setAccountNumber(rs.getString("acc_no"));
						cron.setAvailableBalance(rs.getDouble("eod_balance"));
						java.sql.Date date = rs.getDate("updated_date");
						LocalDate localDate = date.toLocalDate();
						cron.setDate(localDate);
						list.add(cron);

					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException("no list ahead");
		}

		return list;
	}

	/**
	 * Performs data transfer operations, including backup and clearing of date and
	 * closing balance tables.
	 *
	 * @throws DAOException If there are issues with data transfer.
	 */

	public static void dataTransfer(){

		try (Connection con = ConnectionUtil.getConnection()) {

			try (Statement st = con.createStatement()) {

				final String dataTransferDateTable = "INSERT INTO backup_date_table SELECT * FROM date_table";
				st.executeUpdate(dataTransferDateTable);

				final String dataTransferClosingBalance = "INSERT INTO backup_closing_balance SELECT * FROM closing_balance";
				st.executeUpdate(dataTransferClosingBalance);

				final String clearDateTable = "DELETE FROM date_table";
				st.executeUpdate(clearDateTable);

				final String clearClosingBalance = "DELETE FROM closing_balance";
				st.executeUpdate(clearClosingBalance);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
