package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.errors.AccountDaoErrors;
import com.fssa.netbliz.exception.AccountValidatorExceptions;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.validator.AccountValidator;

public class AccountDao { 

	/*
	 * This account is working for user get details by give the account and it's
	 * give the result
	 */

	public static boolean getAccountByNumber(String accNo) throws SQLException { 

		String query = "SELECT * FROM accounts WHERE acc_no = ?"; // Use parameterized query to prevent SQL injection 

		try (Connection con = ConnectionUtil.getConnection();

				PreparedStatement pst = con.prepareStatement(query)) {
 
			pst.setString(1, accNo); 

			try (ResultSet rs = pst.executeQuery()) {

				boolean found = false;

				while (rs.next()) {
					found = true;
					System.out.println("id: " + rs.getInt("acc_id"));
					System.out.println("account Number: " + rs.getString("acc_no"));
					System.out.println("ifsc: " + rs.getString("ifsc"));
					System.out.println("phoneNumber: " + rs.getString("phone_number"));
					System.out.println("minBalance: " + rs.getDouble("min_balance"));
					System.out.println("Account type: " + rs.getString("account_type"));
					System.out.println("balance: " + rs.getDouble("avl_balance"));
					System.out.println("Active status: " + rs.getBoolean("is_active"));
				}
// EXCEPTION
				if (!found) {
					System.out.println("The account does not exists. Please recheck the account number");
				}
			}
		}
		return true;
	}

	public static boolean updateAccount(Account account) throws SQLException, AccountValidatorExceptions {
		// Validate the account using AccountValidator
		AccountValidator.validate(account);

		// SQL query to update the phone number of the account
		final String query = "UPDATE accounts SET phone_number = ? WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				// Set the parameters for the prepared statement
				pst.setString(1, account.getPhoneNumber());
				pst.setString(2, account.getAccountNumber());

				// Execute the update query
				pst.executeUpdate();

				System.out.println("Updated your account successfully");
			}
		} catch (SQLException e) {

			throw new SQLException(AccountDaoErrors.INVALID_UPDATE, e.getMessage());
		}
		return true;
	}

	public static boolean addAccount(Account account) throws SQLException, AccountValidatorExceptions {
		// Validate the account using AccountValidator
		AccountValidator.validate(account);

		// SQL query to insert the account details into the database
		final String query = "INSERT INTO accounts (acc_no, ifsc, phone_number, min_balance, account_type, avl_balance, is_active) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				// Set the parameters for the prepared statement
				pst.setString(1, account.getAccountNumber());
				pst.setString(2, account.getIfsc());
				pst.setString(3, account.getPhoneNumber());
				pst.setDouble(4, account.getMinimumBalance());
				pst.setString(5, account.getCategory());
				pst.setDouble(6, AccountBalanceCreater.randomBalance());
				pst.setBoolean(7, true);

				// Execute the insert query
				int row = pst.executeUpdate();

				// Print confirmation and return true if insertion was successful
				System.out.println("Account Added Successfully");
				return (row > 0);
			}
		} catch (SQLException e) { 

			throw new SQLException(AccountDaoErrors.ERROR_ALREADY_EXITS, e.getMessage());
		}
	}

	public static boolean exitsCheck(Account account) throws SQLException, AccountValidatorExceptions {
		// Validate the account using AccountValidator
		AccountValidator.validate(account);

		// Retrieve a list of all inactive account numbers
		ArrayList<String> inactiveAccountNumbers = getAllInactiveAccountNumber();
		// Check if the provided account number is in the list of inactive accounts
		for (String inactiveAccNumber : inactiveAccountNumbers) {
			if (inactiveAccNumber.equals(account.getAccountNumber())) {
				// Update the account status to active in the database
				String query = "UPDATE accounts SET is_active = 1 WHERE acc_no = ?";
				try (Connection con = ConnectionUtil.getConnection()) {
					try (PreparedStatement pst = con.prepareStatement(query)) {
						pst.setString(1, account.getAccountNumber());
						pst.executeUpdate();
						System.out.println("Your account has been activated successfully");
						return true;
					} 
				}
			}
		}

		// If the account is not found among inactive accounts, add it as a new account
		addAccount(account);
		return true;
	}

	public static ArrayList<String> getAllInactiveAccountNumber() throws SQLException {

		final String query = "SELECT acc_no FROM accounts WHERE is_active = 0";

		ArrayList<String> list = new ArrayList<String>();
		try (Connection con = ConnectionUtil.getConnection()) {

			try (Statement pst = con.createStatement()) {

				try (ResultSet rs = pst.executeQuery(query)) {
					while (rs.next()) {
						list.add(rs.getString("acc_no"));
					}
					return list;
				}

			}
		}

	}

	public static boolean removeAccountByAccountNumber(String accNo) throws SQLException, AccountValidatorExceptions {

		AccountValidator.validateAccountNumber(accNo);

		String query = "UPDATE accounts SET is_active = 0 WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, accNo);
				pst.executeUpdate();
				System.out.println("Your account is removed successfully");

			}
		}
		return true;
	}

//	public static void deleteAccount(String accNo) throws SQLException {
//		int rs = 0;
//		final String query = "DELETE FROM accounts WHERE acc_no = ?";
//
//		try (Connection con = ConnectionUtil.getConnection()) {
//			try (PreparedStatement pst = con.prepareStatement(query)) {
//				pst.setString(1, accNo);
//				rs = pst.executeUpdate();
//			}
//
//			// Check if any rows were affected by the delete query
//			if (rs == 0) {
//				throw new SQLException("Can't delete the account. Account number not found.");
//			}
//		}
//
//		System.out.println("Delete successful");
//	}

	public static ArrayList<String> getAllAccountNumber() throws SQLException {

		final String query = "SELECT acc_no FROM accounts";

		ArrayList<String> list = new ArrayList<String>();
		try (Connection con = ConnectionUtil.getConnection()) {

			try (Statement pst = con.createStatement()) {

				try (ResultSet rs = pst.executeQuery(query)) {
					while (rs.next()) {
						list.add(rs.getString("acc_no"));
					}
					return list;
				}

			}
		}

	}

// done
	public static ArrayList<String> getAllActiveAccountNumber() throws SQLException {

		final String query = "SELECT acc_no FROM accounts WHERE is_active = 1";

		ArrayList<String> list = new ArrayList<String>();
		try (Connection con = ConnectionUtil.getConnection()) {

			try (Statement pst = con.createStatement()) {

				try (ResultSet rs = pst.executeQuery(query)) {
					while (rs.next()) {
						list.add(rs.getString("acc_no"));
					}
					return list;
				}

			}
		}

	}

//	public static void main(String[] args) throws SQLException, validatorExceptions {
//
//		Account account = new Account("1234567891123457", "IDIB000K132", "9361320511", 1000.0, "savings");
//
//		// getAccountByNumber("1234567891123456");
//
//		// updateAccount(account);
//
//		// removeAccountByAccountNumber("0987654321123456");
//
//		// exitsCheck(account);
//	}

}
