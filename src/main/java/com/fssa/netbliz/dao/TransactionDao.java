package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.error.TransactionDaoErrors;
import com.fssa.netbliz.exception.DaoException;
import com.fssa.netbliz.exception.TransactionDaoException;
import com.fssa.netbliz.model.Transaction;

public class TransactionDao {
	private TransactionDao() {
//		private constructor
	}

	public static final int INITIALIZE_ZERO = 0;

	static double holderBalance = INITIALIZE_ZERO;
	static double remittanceBalance = INITIALIZE_ZERO;

	public static boolean isActiveAccount(String holder) throws TransactionDaoException, DaoException {

		String query = "SELECT acc_no,avl_balance FROM account WHERE acc_no = ? AND is_active = true";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, holder);
				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {

						return true;
					}
				}

			}
		} catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);
		}
		return false;
	}

	public static double accountHolderConditions(Transaction trans, Connection con) throws TransactionDaoException {

		String query = "SELECT acc_no,avl_balance FROM account WHERE acc_no = ? AND is_active = true AND avl_balance >= ?";

		double avlBalance = INITIALIZE_ZERO;

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, trans.getAccountHolderAccNo());
			pst.setDouble(2, trans.getTransferAmount());

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {

					avlBalance = rs.getDouble("avl_balance") - trans.getTransferAmount();
					Logger.info("accountHolderConditions true");

				}
			}

		}

		catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}

		return avlBalance;

	}

	public static double remittanceAccountConditions(Transaction trans, Connection con) throws TransactionDaoException {

		String query = "SELECT acc_no,avl_balance FROM account WHERE acc_no = ? AND ifsc = ? AND is_active = true";

		double avlBalance = INITIALIZE_ZERO;

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, trans.getRemittanceAccNo());
			pst.setString(2, trans.getReceiverIfscCode());

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {

					avlBalance = rs.getDouble("avl_balance") + trans.getTransferAmount();
					Logger.info("remittanceAccountConditions true");
				}
			}

		} catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}

		return avlBalance;

	}

	public static boolean updateHolderAccount(Transaction trans) throws TransactionDaoException, DaoException {

		String query = "UPDATE account SET avl_balance = ? WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				holderBalance = accountHolderConditions(trans, con);
				remittanceBalance = remittanceAccountConditions(trans, con);
				pst.setDouble(1, holderBalance);
				pst.setString(2, trans.getAccountHolderAccNo());
				pst.executeUpdate();
				updateRemittanceAccount(trans, con);

				Logger.info("updateHolderAccount true");
			}
		}

		catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}

		return true;

	}

	public static boolean updateRemittanceAccount(Transaction trans, Connection con) throws TransactionDaoException {

		String query = "UPDATE account SET avl_balance = ? WHERE acc_no = ?";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setDouble(1, remittanceBalance);
			pst.setString(2, trans.getRemittanceAccNo());
			pst.executeUpdate();
			insertAccountHolderDetails(trans, con);
			Logger.info("updateRemittanceAccount true");

		} catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}
		return true;
	}

	public static boolean insertAccountHolderDetails(Transaction trans, Connection con) throws TransactionDaoException {

		String query = "INSERT INTO transaction (acc_holder,remittance,trans_status,trans_amount,avl_balance,remark ) VALUES (? , ? , ? , ? , ? , ? )";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, trans.getAccountHolderAccNo());
			pst.setString(2, trans.getRemittanceAccNo());
			pst.setString(3, "credited");
			pst.setDouble(4, trans.getTransferAmount());
			pst.setDouble(5, holderBalance);
			pst.setString(6, trans.getRemark());
			pst.executeUpdate();
			Logger.info("insertAccountHolderDetails true");
			insertRemittanceAccountDetails(trans, con);

		}

		catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}

		return true;
	}

	public static boolean insertRemittanceAccountDetails(Transaction trans, Connection con)
			throws TransactionDaoException {

		String query = "INSERT INTO transaction (acc_holder,remittance,trans_status,trans_amount,avl_balance,remark ) VALUES (? , ? , ? , ? , ? , ? )";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, trans.getRemittanceAccNo());
			pst.setString(2, trans.getAccountHolderAccNo());
			pst.setString(3, "debited");
			pst.setDouble(4, trans.getTransferAmount());
			pst.setDouble(5, remittanceBalance);
			pst.setString(6, trans.getRemark());
			pst.executeUpdate();
			Logger.info("insertRemittanceAccountDetails true");
			Logger.info("Check DataBase");

		}

		catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}

		return true;

	}

	public static List<Object> listTransaction(String accNo) throws TransactionDaoException, DaoException {

		ArrayList list = new ArrayList<>();

		String query = "SELECT * FROM transaction WHERE acc_holder = ?  OR remittance = ? ";

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
						trans.setRemark(rs.getString("remark"));
						list.add(trans);

					}
				}
			}
		} catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.NON_TRANSACTION);
		}

		return list;
	}

	public static boolean printTransactions(String accNo) throws TransactionDaoException, DaoException {

		List transList = listTransaction(accNo);

		if (transList.isEmpty()) {

			throw new TransactionDaoException(TransactionDaoErrors.NON_TRANSACTION);
		}

		for (Object list : transList) {

			Logger.info(list);
		}

		return true;
	}
}
