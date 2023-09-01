package com.fssa.netbliz.model;

public class Transaction {

	private String accountHolderAccNo;
	private String remittanceAccNo;
	private String receiverIfscCode;
	private double transferAmount;
	private String remark;
	private String transStatus;
	private double avlAmount;
	private String paidDateTime;
	private String debitedDateTime;

	// Constructor with parameters to initialize Transaction object
	public Transaction(String accountHolderAccNo, String remittanceAccNo, String receiverIfscCode,
			double transferAmount, String remark) {
		this.accountHolderAccNo = accountHolderAccNo;
		this.remittanceAccNo = remittanceAccNo;
		this.receiverIfscCode = receiverIfscCode;
		this.transferAmount = transferAmount;
		this.remark = remark;
	}

	public Transaction(String accountHolderAccNo, String remittanceAccNo, String receiverIfscCode,
			double transferAmount, String remark, String transStatus, double avlAmount, String paidDateTime,
			String debitedDateTime) {
		super();
		this.accountHolderAccNo = accountHolderAccNo;
		this.remittanceAccNo = remittanceAccNo;
		this.receiverIfscCode = receiverIfscCode;
		this.transferAmount = transferAmount;
		this.remark = remark;
		this.transStatus = transStatus;
		this.avlAmount = avlAmount;
		this.paidDateTime = paidDateTime;
		this.debitedDateTime = debitedDateTime;
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

	public void setTransferAmount(double transfer_amount) {
		this.transferAmount = transfer_amount;
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

	public String getPaidDateTime() {
		return paidDateTime;
	}

	public void setPaidDateTime(String paidDateTime) {
		this.paidDateTime = paidDateTime;
	}

	public String getDebitedDateTime() {
		return debitedDateTime;
	}

	public void setDebitedDateTime(String debitedDateTime) {
		this.debitedDateTime = debitedDateTime;
	}

	// Override toString() method to provide a string representation of the
	// Transaction object
	@Override 
	public String toString() {
		return "Transaction [accountHolderAccNo=" + accountHolderAccNo + ", remittanceAccNo=" + remittanceAccNo
				+ ", receiverIfscCode=" + receiverIfscCode + ", transfer_amount=" + transferAmount + ", remark="
				+ remark + "]";
	}

}
