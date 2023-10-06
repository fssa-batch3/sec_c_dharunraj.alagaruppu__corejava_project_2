package com.fssa.netbliz.service;

import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.dao.UpdateAverageBalanceJob;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.CronJob;
import com.fssa.netbliz.validator.AccountValidator;

public class UpdateAverageBalanceJobSerivice {

	public UpdateAverageBalanceJobSerivice() {
//		private constructor 
	}

	/**
	 * Retrieve a list of banking details for a given account number.
	 *
	 * @param accNo The account number for which banking details are to be
	 *              retrieved.
	 * @return A list of CronJob objects representing the banking details, or an
	 *         empty list if no data is found.
	 * @throws ServiceException If there is a service-level error, or if the
	 *                          provided account number is invalid.
	 */

	public List<CronJob> getBankDetails(String accNo) throws ServiceException {

		List<CronJob> list = new ArrayList<>();
		try {
			if (AccountValidator.validateAccountNumber(accNo)) {
				list = UpdateAverageBalanceJob.getListingDetails(accNo);
			} else {
				throw new ServiceException("No data ahead");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {

			e.getMessage();
		}
		return list;

	}
}
