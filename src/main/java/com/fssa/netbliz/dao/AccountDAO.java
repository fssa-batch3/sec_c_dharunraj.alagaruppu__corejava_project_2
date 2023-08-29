package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.constants.NetblizConstants;
import com.fssa.netbliz.enums.AccountEnum;
import com.fssa.netbliz.error.AccountDAOError;
import com.fssa.netbliz.error.TransactionDAOError;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.util.ConnectionUtil;

/**
 * This class provides data access methods for interacting with the account
 * database table.
 */

public class AccountDAO {
	private AccountDAO() {
//		private constructor
	}

	/**
	 * Adds a new account to the database.
	 *
	 * @param account The account object to be added
	 * @return True if the account is successfully added, false otherwise
	 * @throws DAOException If a database error occurs during the operation
	 */

	public static boolean addAccount(Account account) throws DAOException {

		// SQL query to insert the account details into the database
		final String query = "INSERT INTO accounts (acc_no, ifsc, phone_number, min_balance, account_type, avl_balance,customer_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				// Set the parameters for the prepared statement
				pst.setString(1, account.getAccountNumber());
				pst.setString(2, account.getIfsc());
				pst.setLong(3, account.getPhoneNumber());
				pst.setDouble(4, account.getMinimumBalance());
				pst.setString(5, account.getCategory().toString());
				pst.setDouble(6, Account.CONSTANT_AVL_BALANCE);
				pst.setInt(7, getPrimaryCustomerId(account));

				// Execute the insert query
				int row = pst.executeUpdate();

				return (row > Account.ZERO);
			}
		} catch (SQLException e) {

			throw new DAOException(AccountDAOError.ERROR_ALREADY_EXITS);
		}
	}

	/**
	 * Retrieves a list of Account objects based on the provided account number.
	 *
	 * @param accNo The account number to search for.
	 * @return A list of Account objects matching the provided account number, or an
	 *         empty list if no matches are found.
	 * @throws DAOException If there is an issue with the database operation.
	 */

	public static List<Account> getAccountByNumber(String accNo) throws DAOException {

		List<Account> list = new ArrayList<>();   

		final String query = "SELECT acc_no,ifsc,phone_number,min_balance,account_type FROM accounts WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, accNo);

				try (ResultSet rs = pst.executeQuery()) {

					if (rs.next()) {

						Account account = new Account();
						account.setAccountNumber(rs.getString("acc_no"));
						account.setIfsc(rs.getString("ifsc"));
						account.setPhoneNumber(rs.getLong("phone_number"));
						account.setMinimumBalance(rs.getDouble("min_balance"));
						String type = rs.getString("account_type");
						AccountEnum enumType = AccountEnum.valueOf(type);
						account.setCategory(enumType);
						list.add(account);
						return list;
					}  

				}

			}

		} catch (SQLException e) {

			throw new DAOException(AccountDAOError.INVALID_ACCOUNT_NUMBER); // Handle SQLException by throwing a
																			// DAOException with an appropriate error																// code
		}
		return null; // Return null or an empty list if no matching accounts are found

	}

	/**
	 * Marks an account as inactive by account number.
	 *
	 * @param accNo The account number of the account to be removed
	 * @return True if the account is successfully marked as inactive, false
	 *         otherwise
	 * @throws DAOException If a database error occurs during the operation
	 */

	public static boolean removeAccountByAccountNumber(String accNo) throws DAOException {

		final String query = "UPDATE accounts SET is_active = ? WHERE acc_no = ?"; 

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setBoolean(1, NetblizConstants.STATIC_IS_ACTIVE_FALSE);
				pst.setString(2, accNo);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DAOException(AccountDAOError.INVALID_UPDATE);
		}
		return true;
	}

	/**
	 * Checks if an account is active by its account number.
	 *
	 * @param holder The account number to be checked for activity
	 * @return True if the account is active, false otherwise
	 * @throws DAOException If a database error occurs during the operation
	 */

	public static boolean isActiveAccount(String accNo) throws DAOException {
		final String query = "SELECT acc_no, avl_balance FROM accounts WHERE acc_no = ? AND is_active = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, accNo);
				pst.setBoolean(2, NetblizConstants.STATIC_IS_ACTIVE_TRUE);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException(TransactionDAOError.INVALID_ACCOUNT_NUMBER);
		}
		return false;
	}

	/**
	 * Checks the availability of a bank account based on the provided account
	 * number.
	 *
	 * @param accNo The account number to check for availability.
	 * @return {@code true} if the account with the provided number is available,
	 *         {@code false} otherwise.
	 * @throws DAOException If there is an error while interacting with the database
	 *                      or checking availability.
	 */

	public static boolean isAvailableAccount(String accNo) throws DAOException {

		final String query = "SELECT acc_no FROM accounts WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, accNo);

				try (ResultSet rs = pst.executeQuery()) {

					if (rs.next()) {

						return true;
					}
				}
			}
		} catch (SQLException e) {

			throw new DAOException(TransactionDAOError.UN_AVAILABLE_ACCOUNT);
		}

		return false; 
	}

	/**
	 * Checks if the provided account is inactive and activates it if necessary. If
	 * the account is not found, adds it as a new account.
	 *
	 * @param account The account object to be checked and activated
	 * @return True if the account is successfully activated or added, false
	 *         otherwise
	 * @throws DAOException If a database error occurs during the operation
	 */

	public static boolean existsCheck(Account account) throws DAOException {

		// Update the account status to active in the database
		final String query = "UPDATE accounts SET is_active = ? WHERE acc_no = ?";
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setBoolean(1, NetblizConstants.STATIC_IS_ACTIVE_TRUE); // true
				pst.setString(2, account.getAccountNumber());
				pst.executeUpdate();
				return true;
			}
		}

		catch (SQLException e) {

			throw new DAOException(AccountDAOError.ERROR_ALREADY_EXITS);
		}

	}

	/**
	 * Retrieves the primary customer ID associated with the provided account.
	 *
	 * @param account The account for which the primary customer ID is to be
	 *                retrieved
	 * @return The primary customer ID associated with the account
	 * @throws DAOException If a database error occurs during the operation
	 */

	public static int getPrimaryCustomerId(Account account) throws DAOException {

		int id = 0; // initialize the id number
		final String query = "SELECT customer_id FROM customers WHERE phone = ? ";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setLong(1, account.getPhoneNumber());

				try (ResultSet rs = pst.executeQuery()) {

					if (rs.next()) {

						id = rs.getInt("customer_id");

					}
				}
			}
		} catch (SQLException e) {

			throw new DAOException(AccountDAOError.INVALID_PHONE_NUMBER);
		}

		return id;

	}
	
	public static void main(String[] args) throws DAOException {
		
	System.out.println(getAccountByNumber("1234567890123456"));	
	}
	

}