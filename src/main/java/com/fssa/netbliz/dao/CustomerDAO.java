package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.constants.NetblizConstants;
import com.fssa.netbliz.error.CustomerDAOError;
import com.fssa.netbliz.error.TransactionDAOError;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.model.Customer;
import com.fssa.netbliz.util.ConnectionUtil;

public class CustomerDAO {
	private CustomerDAO() {
//		private constructor 
	}

	/**
	 * Adds a new customer to the database using the provided Customer object.
	 *
	 * @param customer The Customer object containing the details of the customer to
	 *                 be added.
	 * @return {@code true} if the customer is successfully added, {@code false}
	 *         otherwise.
	 * @throws DAOException If there is an issue with the database operation while
	 *                      adding the customer.
	 */
	public static boolean addCustomer(Customer customer) throws DAOException {
		final String query = "INSERT INTO customers (first_name,last_name,email,phone,password) VALUES (?,?,?,?,?)";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, customer.getFirstName());
				pst.setString(2, customer.getLastName());
				pst.setString(3, customer.getEmail());
				pst.setLong(4, customer.getPhoneNumber());
				pst.setString(5, customer.getPassword());
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DAOException(CustomerDAOError.INVALID_DATA);
		}

		return true;
	}

	/**
	 * Attempts to log in a customer using the provided credentials (phone, email,
	 * and password).
	 *
	 * @param phone    The phone number of the customer.
	 * @param email    The email address of the customer.
	 * @param password The password of the customer.
	 * @return {@code true} if the login is successful, {@code false} otherwise.
	 * @throws DAOException If there is an issue with the database operation during
	 *                      login.
	 * @throws SQLException If there is an issue with executing the SQL query.
	 */
	public static boolean logInCustomer(long phone, String email, String password) throws DAOException, SQLException {
		final String query = "SELECT phone,email,password FROM customers WHERE phone = ? ";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setLong(1, phone);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next() && rs.getString("email").equals(email.trim())
							&& rs.getString("password").equals(password.trim()) && rs.getLong("phone") == phone) {
						return true;
					}

				}
			}
		} catch (SQLException e) {
			throw new DAOException(CustomerDAOError.INVALID_DATA);
		}
		return false;
	}

	/**
	 * Checks if a customer account is available by phone number.
	 *
	 * @param phone The phone number to check for customer availability.
	 * @return {@code true} if a customer account with the provided phone number
	 *         exists, {@code false} otherwise.
	 * @throws DAOException If there is an issue with the database operation while
	 *                      checking account availability.
	 */
	public static boolean isAvailableAccount(long phone) throws DAOException {
		final String query = "SELECT phone FROM customers WHERE phone = ?";
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setLong(1, phone);
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
	 * Checks if a customer account is active by phone number.
	 *
	 * @param phone The phone number to check for customer account activity.
	 * @return {@code true} if the customer account is active, {@code false}
	 *         otherwise.
	 * @throws DAOException If there is an issue with the database operation while
	 *                      checking account activity.
	 */
	public static boolean isActiveAccount(long phone) throws DAOException {
		final String query = "SELECT email,password FROM customers WHERE phone = ? AND is_active = ?";
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setLong(1, phone);
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
	 * Retrieves customer details based on the provided phone number.
	 *
	 * @param phone The phone number to search for.
	 * @return A list of Customer objects matching the provided phone number, or an
	 *         empty list if no matches are found.
	 * @throws DAOException If there is an issue with the database operation.
	 */
	public static List<Customer> getCustomerDetailsByPhoneNumber(long phone) throws DAOException {
		List<Customer> customerList = new ArrayList<>();
		final String query = "SELECT customer_id,first_name,last_name,phone,email,password,is_active FROM customers WHERE phone = ?";
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setLong(1, phone);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						Customer customer = new Customer();
						customer.setFirstName(rs.getString("first_name"));
						customer.setLastName(rs.getString("last_name"));
						customer.setPhoneNumber(rs.getLong("phone"));
						customer.setEmail(rs.getString("email"));
						customer.setPassword(rs.getString("password"));
						customer.setActive(rs.getBoolean("is_active"));
						customer.setCustomerId(rs.getInt("customer_id"));
						customerList.add(customer);
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException(CustomerDAOError.INVALID_DATA);
		}
		return customerList; 
	}
}
