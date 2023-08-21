package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fssa.netbliz.error.CustomerDAOError;
import com.fssa.netbliz.exception.CustomerDAOException;
import com.fssa.netbliz.model.Customer;
import com.fssa.netbliz.util.ConnectionUtil;

public class CustomerDAO {
	private CustomerDAO() {
//		private constructor 
	}

	public static boolean addCustomer(Customer customer) throws CustomerDAOException {

		final String query = "INSERT INTO customers (f_name,l_name,email,phone,password) VALUES (?,?,?,?,?)";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, customer.getfName());
				pst.setString(2, customer.getlName());
				pst.setString(3, customer.getEmail());
				pst.setString(4, customer.getPhoneNumber());
				pst.setString(5, customer.getPassword());
				pst.executeUpdate();

			}

		}

		catch (SQLException e) {

			throw new CustomerDAOException(CustomerDAOError.INVALID_DATA);
		}
		return true;

	}
}
