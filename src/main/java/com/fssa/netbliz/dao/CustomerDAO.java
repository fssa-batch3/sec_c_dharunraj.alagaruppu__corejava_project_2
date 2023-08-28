package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fssa.netbliz.error.CustomerDAOError;
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

		}

		catch (SQLException e) {

			throw new DAOException(CustomerDAOError.INVALID_DATA);
		}

		System.out.println("Registered successfully");

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

					if (rs.next()) {
						System.out.println(rs.getLong("phone"));
						System.out.println(rs.getString("email"));
						System.out.println(rs.getString("password"));

						if (rs.getString("email").equals(email.trim())
								&& rs.getString("password").equals(password.trim())) {

							return true;
						}
					}
				}
			}
		}

		catch (SQLException e) {

			throw new DAOException(CustomerDAOError.INVALID_DATA);
		}

		return false;
	}
}
