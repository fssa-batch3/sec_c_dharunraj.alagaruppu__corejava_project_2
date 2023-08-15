package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fssa.netbliz.error.CustomerDaoError;
import com.fssa.netbliz.exception.CustomerDaoException;
import com.fssa.netbliz.model.Customer;
import com.fssa.netbliz.util.ConnectionUtil;

public class CustomerDao {
	private CustomerDao() {
//		private constructor
	}

	public static boolean addCustomer(Customer customer) throws CustomerDaoException {

		final String query = "INSERT INTO customer (f_name,l_name,email,phone,password) VALUES (?,?,?,?,?)";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, customer.getfName());
				pst.setString(2, customer.getlName());
				pst.setString(3, customer.getEmail());
				pst.setString(4, customer.getPhoneNumber());
				pst.setString(5, customer.getPassword());
				pst.executeUpdate();
				System.out.println("Welcome to NETBLIZ");
			}

		}

		catch (SQLException e) {

			throw new CustomerDaoException(CustomerDaoError.INVALID_DATA);
		}
		return true;

	}
}
