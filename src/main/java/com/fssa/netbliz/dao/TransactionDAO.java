package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.error.TransactionDAOError;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.util.ConnectionUtil;
import com.fssa.netbliz.util.Logger;

public class TransactionDAO {

	private TransactionDAO() {
		// Private constructor to prevent instantiation
	}

	public static final int INITIALIZE_ZERO = 0;
	public static final String CRIDIT_DENOTES = "credited";
	public static final String DEBIT_DENOTES = "debited";

	static double holderBalance = INITIALIZE_ZERO;
	static double remittanceBalance = INITIALIZE_ZERO;

	/**
	 * Retrieves the phone number associated with the given account number from the
	 * database.
	 *
	 * @param con           The database connection.
	 * @param accountNumber The account number for which to retrieve the associated
	 *                      phone number.
	 * @return The phone number associated with the account number, or {@code null}
	 *         if not found.
	 * @throws DAOException If there is an issue with the database operation while
	 *                      retrieving the phone number.
	 */

	public static String phoneNumberCheck(Connection con, String accountNumber) throws DAOException {

		final String query = "SELECT phone_number FROM accounts WHERE acc_no = ?";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, accountNumber);

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {

					return rs.getString("phone_number");

				}

			}
		} catch (SQLException e) {

			throw new DAOException(TransactionDAOError.DISMATCH_PHONE_NUMBER);
		}

		return null;
	}

	/**
	 * Validates the account holder's account conditions for a transaction.
	 * Retrieves the account balance of the account holder, ensures that the account
	 * is active, and the available balance is sufficient for the transaction
	 * amount.
	 *
	 * @param trans The Transaction object containing transaction details.
	 * @param con   The Connection object for database access.
	 * @return The updated available balance of the account holder after the
	 *         transaction.
	 * @throws DAOException If there are issues with the transaction data access.
	 */

	public static double accountHolderConditions(Transaction trans, Connection con) throws DAOException {

		String query = "SELECT avl_balance FROM accounts WHERE acc_no = ? AND is_active = true AND avl_balance >= ?";

		double avlBalance = INITIALIZE_ZERO;

		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, trans.getAccountHolderAccNo());
			pst.setDouble(2, trans.getTransferAmount());

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					avlBalance = rs.getDouble("avl_balance") - trans.getTransferAmount();
				}
			}
		} catch (SQLException e) {
			throw new DAOException(TransactionDAOError.INVALID_ACCOUNT_NUMBER);
		}
 
		return avlBalance;
	}

	/**
	 * Validates the remittance account's conditions for a transaction. Retrieves
	 * the account balance of the remittance account, ensures that the account is
	 * active, and updates the available balance after adding the transaction
	 * amount.
	 *
	 * @param trans The Transaction object containing transaction details.
	 * @param con   The Connection object for database access.
	 * @return The updated available balance of the remittance account after the
	 *         transaction.
	 * @throws DAOException If there are issues with the transaction data access.
	 */

	public static double remittanceAccountConditions(Transaction trans, Connection con) throws DAOException {
		String query = "SELECT avl_balance FROM accounts WHERE acc_no = ? AND ifsc = ? AND is_active = true";

		double avlBalance = INITIALIZE_ZERO;

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, trans.getRemittanceAccNo());
			pst.setString(2, trans.getReceiverIfscCode());
			System.out.println(pst);
			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					avlBalance = rs.getDouble("avl_balance") + trans.getTransferAmount();
				}
			}
		} catch (SQLException e) {
			throw new DAOException(TransactionDAOError.INVALID_ACCOUNT_NUMBER);
		}

		return avlBalance;
	}

	/**
	 * Updates the account holder's account balance after a successful transaction.
	 * Also updates the remittance account's balance by invoking the appropriate
	 * method.
	 *
	 * @param trans The Transaction object containing transaction details.
	 * @return True if the update is successful, otherwise false.
	 * @throws DAOException If there are issues with the transaction data access.
	 */

	public static boolean updateHolderAccount(Transaction trans) throws DAOException {
		String query = "UPDATE accounts SET avl_balance = ? WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {

				if (!trans.getAccountHolderAccNo().equals(trans.getRemittanceAccNo())) {

					String senderPhoneNumber = phoneNumberCheck(con, trans.getAccountHolderAccNo());
					System.out.println(senderPhoneNumber);
					String receiverPhoneNumber = phoneNumberCheck(con, trans.getRemittanceAccNo());
					System.out.println(receiverPhoneNumber);
					if (!senderPhoneNumber.equals(receiverPhoneNumber)) {

						holderBalance = accountHolderConditions(trans, con);
						remittanceBalance = remittanceAccountConditions(trans, con);
						pst.setDouble(1, holderBalance);
						pst.setString(2, trans.getAccountHolderAccNo());
						updateRemittanceAccount(trans, con);
						pst.executeUpdate();
					} else {

						throw new DAOException(TransactionDAOError.DISMATCH_PHONE_NUMBER);
					}

				}

			}
		} catch (SQLException e) {
			throw new DAOException(TransactionDAOError.INVALID_ACCOUNT_NUMBER);
		}

		return true;
	}

	/**
	 * Updates the remittance account's balance after a successful transaction.
	 * Invoked by the updateHolderAccount method.
	 *
	 * @param trans The Transaction object containing transaction details.
	 * @param con   The Connection object for database access.
	 * @return True if the update is successful, otherwise false.
	 * @throws DAOException If there are issues with the transaction data access.
	 */

	public static boolean updateRemittanceAccount(Transaction trans, Connection con) throws DAOException {
		String query = "UPDATE accounts SET avl_balance = ? WHERE acc_no = ?";

		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setDouble(1, remittanceBalance);
			pst.setString(2, trans.getRemittanceAccNo());
			pst.executeUpdate();
			insertAccountHolderDetails(trans, con);

		} catch (SQLException e) {
			throw new DAOException(TransactionDAOError.INVALID_ACCOUNT_NUMBER);
		}
		return true;
	}

	/**
	 * Inserts transaction details related to the account holder's account. Updates
	 * the transaction table with details of the outgoing transaction.
	 *
	 * @param trans The Transaction object containing transaction details.
	 * @param con   The Connection object for database access.
	 * @return True if the insertion is successful, otherwise false.
	 * @throws DAOException If there are issues with the transaction data access.
	 */

	public static boolean insertAccountHolderDetails(Transaction trans, Connection con) throws DAOException {
		String query = "INSERT INTO transactions (acc_holder, remittance, trans_status, trans_amount, avl_balance, remark) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, trans.getAccountHolderAccNo());
			pst.setString(2, trans.getRemittanceAccNo());
			pst.setString(3, CRIDIT_DENOTES);
			pst.setDouble(4, trans.getTransferAmount());
			pst.setDouble(5, holderBalance);
			pst.setString(6, trans.getRemark());
			pst.executeUpdate();
			insertRemittanceAccountDetails(trans, con);
		} catch (SQLException e) {
			throw new DAOException(TransactionDAOError.INVALID_ACCOUNT_NUMBER);
		}

		return true;
	}

	/**
	 * Inserts transaction details related to the remittance account. Updates the
	 * transaction table with details of the incoming transaction.
	 *
	 * @param trans The Transaction object containing transaction details.
	 * @param con   The Connection object for database access.
	 * @return True if the insertion is successful, otherwise false.
	 * @throws DAOException If there are issues with the transaction data access.
	 */

	public static boolean insertRemittanceAccountDetails(Transaction trans, Connection con) throws DAOException {
		String query = "INSERT INTO transactions (acc_holder, remittance, trans_status, trans_amount, avl_balance, remark) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, trans.getRemittanceAccNo());
			pst.setString(2, trans.getAccountHolderAccNo());
			pst.setString(3, DEBIT_DENOTES);
			pst.setDouble(4, trans.getTransferAmount());
			pst.setDouble(5, remittanceBalance);
			pst.setString(6, trans.getRemark());
			pst.executeUpdate();
			Logger.info("Check DataBase"); // It's assumed that Logger is a valid logging mechanism
		} catch (SQLException e) {
			throw new DAOException(TransactionDAOError.INVALID_ACCOUNT_NUMBER);
		}

		return true;
	}

	/**
	 * Retrieves a list of transactions associated with the specified account
	 * number. The list contains Transaction objects with details such as account
	 * numbers, transaction amount, and remark.
	 *
	 * @param accNo The account number for which transactions are to be retrieved.
	 * @return A list of Transaction objects representing the transactions.
	 * @throws DAOException If there are issues with the transaction data access.
	 */

	public static List<Transaction> listTransaction(String accNo) throws DAOException {
		List<Transaction> list = new ArrayList<>();

		String query = "SELECT acc_holder,remittance,trans_status,trans_amount,avl_balance,paid_time,debited_time,remark FROM transactions WHERE acc_holder = ? OR remittance = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, accNo);
				pst.setString(2, accNo);

				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						Transaction trans = new Transaction();
						trans.setAccountHolderAccNo(rs.getString("acc_holder"));
						trans.setRemittanceAccNo(rs.getString("remittance"));
						trans.setTransferAmount(rs.getDouble("trans_amount"));
						trans.setTransStatus(rs.getString("trans_status"));
						trans.setAvlAmount(rs.getDouble("avl_balance"));
						trans.setPaidDateTime(rs.getString("paid_time"));
						trans.setDebitedDateTime(rs.getString("debited_time"));
						trans.setRemark(rs.getString("remark"));
						list.add(trans);
					} 
				}
			}
		} catch (SQLException e) {
			throw new DAOException(TransactionDAOError.NON_TRANSACTION);
		}

		return list;
	}

	/**
	 * Prints a list of transactions associated with the specified account number.
	 *
	 * @param accNo The account number for which transactions are to be printed.
	 * @return True if the printing is successful, otherwise false.
	 * @throws DAOException If there are issues with the transaction data access.
	 */

	public static boolean printTransactions(String accNo) throws DAOException {
		List<Transaction> transList = listTransaction(accNo);

		if (transList.isEmpty()) {
			throw new DAOException(TransactionDAOError.NON_TRANSACTION);
		}

		for (Object list : transList) {
			Logger.info(list); // It's assumed that Logger is a valid logging mechanism
		}

		return true;
	}

}
