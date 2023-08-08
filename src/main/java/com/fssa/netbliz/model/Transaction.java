package com.fssa.netbliz.model;

public class Transaction {

	private String accountHolderAccNo;
	private String remittanceAccNo;
	private String receiverIfscCode;
	private double transfer_amount;
	private String remark; 

	public Transaction(String accountHolderAccNo, String remittanceAccNo, String receiverIfscCode,
			double transfer_amount, String remark) { 

		this.accountHolderAccNo = accountHolderAccNo;
		this.remittanceAccNo = remittanceAccNo;
		this.receiverIfscCode = receiverIfscCode;
		this.transfer_amount = transfer_amount;
		this.remark = remark; 
	}

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

	public double getTransfer_amount() {
		return transfer_amount;
	}

	public void setTransfer_amount(double transfer_amount) {   
		this.transfer_amount = transfer_amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
