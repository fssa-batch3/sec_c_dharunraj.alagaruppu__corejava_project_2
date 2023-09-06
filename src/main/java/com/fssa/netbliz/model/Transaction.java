package com.fssa.netbliz.model;

import java.time.LocalDateTime;

public class Transaction {

	public static final int INITIALIZE_ZERO = 0;
	public static final String CRIDIT_DENOTES = "credited";
	public static final String DEBIT_DENOTES = "debited";

	public static double holderBalance = INITIALIZE_ZERO;
	public static double remittanceBalance = INITIALIZE_ZERO;

	private String accountHolderAccNo;
	private String remittanceAccNo;
	private String receiverIfscCode;
	private double transferAmount;
	private String remark;
	private String transStatus;
	private double avlAmount;
	private LocalDateTime paidDateTime;
	private LocalDateTime debitedDateTime;
	private int customerId;

	// Constructor with parameters to initialize Transaction object
	public Transaction(String accountHolderAccNo, String remittanceAccNo, String receiverIfscCode,
			double transferAmount, String remark) {
		this.accountHolderAccNo = accountHolderAccNo;
		this.remittanceAccNo = remittanceAccNo;
		this.receiverIfscCode = receiverIfscCode;
		this.transferAmount = transferAmount;
		this.remark = remark;
	}

	// Default constructor
	public Transaction() {

	}

	public String getAccountHolderAccNo() { 
		return accountHolderAccNo;
	}

	public void setAccountHolderAccNo(String accountHolderAccNo) {
		this.accountHolderAccNo = accountHolderAccNo;
	}

	public String getRemittanceAccNo() {
		return remittanceAccNo;
	}

	public void setRemittanceAccNo(String remittanceAccNo) {
		this.remittanceAccNo = remittanceAccNo;
	}

	public String getReceiverIfscCode() {
		return receiverIfscCode;
	}

	public void setReceiverIfscCode(String receiverIfscCode) {
		this.receiverIfscCode = receiverIfscCode;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public double getAvlAmount() {
		return avlAmount;
	}

	public void setAvlAmount(double avlAmount) {
		this.avlAmount = avlAmount;
	}

	public LocalDateTime getPaidDateTime() {
		return paidDateTime;
	}

	public void setPaidDateTime(LocalDateTime paidDateTime) {
		this.paidDateTime = paidDateTime;
	}

	public LocalDateTime getDebitedDateTime() {
		return debitedDateTime;
	}

	public void setDebitedDateTime(LocalDateTime debitedDateTime) {
		this.debitedDateTime = debitedDateTime;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Transaction [accountHolderAccNo=" + accountHolderAccNo + ", remittanceAccNo=" + remittanceAccNo
				+ ", receiverIfscCode=" + receiverIfscCode + ", transferAmount=" + transferAmount + ", remark=" + remark
				+ ", transStatus=" + transStatus + ", avlAmount=" + avlAmount + ", paidDateTime=" + paidDateTime
				+ ", debitedDateTime=" + debitedDateTime + ", customerId=" + customerId + "]";
	}

}
