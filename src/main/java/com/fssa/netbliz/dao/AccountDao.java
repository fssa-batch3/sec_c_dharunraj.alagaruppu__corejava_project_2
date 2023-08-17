package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.error.AccountDaoError;
import com.fssa.netbliz.exception.AccountDaoException;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.util.ConnectionUtil;

public class AccountDao {
	private AccountDao() {
//		private constructor
	}

	/**
	 * This account is working for user get details by give the account and it's
	 * give the result
	 */

	static final int ZERO = 0;

	public static boolean getAccountByNumber(String accNo) throws AccountDaoException {

		String query = "SELECT * FROM account WHERE acc_no = ?"; // Use parameterized query to prevent SQL injection

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, accNo);

				try (ResultSet rs = pst.executeQuery()) {

					boolean found = false;

					while (rs.next()) {
						found = true;
						Logger.info("id: " + rs.getInt("acc_id"));
						Logger.info("account Number: " + rs.getString("acc_no"));
						Logger.info("ifsc: " + rs.getString("ifsc"));
						Logger.info("phoneNumber: " + rs.getString("phone_number"));
						Logger.info("minBalance: " + rs.getDouble("min_balance"));
						Logger.info("Account type: " + rs.getString("account_type"));
						Logger.info("balance: " + rs.getDouble("avl_balance"));
						Logger.info("Active status: " + rs.getBoolean("is_active"));
					}
					if (!found) {
						Logger.info("The account does not exists. Please recheck the account number");
					}
				}

			}

		} catch (SQLException e) {

			throw new AccountDaoException(AccountDaoError.INVALID_ACCOUNT_NUMBER);
		}
		return true;

	}

	public static boolean addAccount(Account account) throws AccountDaoException {

		AccountBalanceCreater ac = new AccountBalanceCreater();
		// SQL query to insert the account details into the database
		final String query = "INSERT INTO account (acc_no, ifsc, phone_number, min_balance, account_type, avl_balance,customer_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				// Set the parameters for the prepared statement
				pst.setString(1, account.getAccountNumber());
				pst.setString(2, account.getIfsc());
				pst.setString(3, account.getPhoneNumber());
				pst.setDouble(4, account.getMinimumBalance());
				pst.setString(5, account.getCategory());
				pst.setDouble(6, ac.randomBalance());
				pst.setInt(7, getPrimaryCustomerId(account));

				// Execute the insert query
				int row = pst.executeUpdate();

				return (row > ZERO);
			}
		} catch (SQLException e) {

			throw new AccountDaoException(AccountDaoError.ERROR_ALREADY_EXITS);
		}
	}

	public static boolean exitsCheck(Account account) throws AccountDaoException {

		// Retrieve a list of all inactive account numbers
		List<String> inactiveAccountNumbers = getAllInactiveAccountNumber();
		// Check if the provided account number is in the list of inactive account
		for (String inactiveAccNumber : inactiveAccountNumbers) {
			if (inactiveAccNumber.equals(account.getAccountNumber())) {
				// Update the account status to active in the database
				String query = "UPDATE account SET is_active = 1 WHERE acc_no = ?";
				try (Connection con = ConnectionUtil.getConnection()) {
					try (PreparedStatement pst = con.prepareStatement(query)) {
						pst.setString(1, account.getAccountNumber());
						pst.executeUpdate();
						Logger.info("Your account has been activated successfully");
						return true;
					}
				}

				catch (SQLException e) {

					throw new AccountDaoException(AccountDaoError.ERROR_ALREADY_EXITS);
				}
			}
		}

		// If the account is not found among inactive account, add it as a new account
		addAccount(account);
		return true;
	}

	public static List<String> getAllInactiveAccountNumber() throws AccountDaoException {
		final String query = "SELECT acc_no FROM account WHERE is_active = 0";

		List<String> list = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			try (Statement pst = con.createStatement()) {
				try (ResultSet rs = pst.executeQuery(query)) {
					while (rs.next()) {
						list.add(rs.getString("acc_no"));
					}
					return list;
				}
			}
		} catch (SQLException e) {
			throw new AccountDaoException(AccountDaoError.INVALID_ACCOUNT_NUMBER);
		}
	}

	// Marks an account as inactive by account number
	public static boolean removeAccountByAccountNumber(String accNo) throws AccountDaoException {

		String query = "UPDATE account SET is_active = 0 WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, accNo);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw new AccountDaoException(AccountDaoError.INVALID_UPDATE);
		}
		return true;
	}

	public static int getPrimaryCustomerId(Account account) throws AccountDaoException {

		int id = 0;
		final String query = "SELECT customer_id FROM customer WHERE phone = ? ";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, account.getPhoneNumber());

				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {

						id = rs.getInt("customer_id");

					}
				}
			}
		} catch (SQLException e) {

			throw new AccountDaoException(AccountDaoError.INVALID_PHONE_NUMBER);
		}

		return id;

	}
}