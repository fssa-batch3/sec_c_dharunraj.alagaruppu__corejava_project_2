package com.fssa.netbliz.model;

public class Transaction {

    private String accountHolderAccNo;
    private String remittanceAccNo;
    private String receiverIfscCode;
    private double transferAmount;
    private String remark;

    // Constructor with parameters to initialize Transaction object
    public Transaction(String accountHolderAccNo, String remittanceAccNo, String receiverIfscCode,
                       double transfer_amount, String remark) {
        this.accountHolderAccNo = accountHolderAccNo;
        this.remittanceAccNo = remittanceAccNo;
        this.receiverIfscCode = receiverIfscCode;
        this.transferAmount = transfer_amount;
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

    public void setTransferAmount(double transfer_amount) {
        this.transferAmount = transfer_amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    // Override toString() method to provide a string representation of the Transaction object
    @Override
    public String toString() {
        return "Transaction [accountHolderAccNo=" + accountHolderAccNo + ", remittanceAccNo=" + remittanceAccNo
                + ", receiverIfscCode=" + receiverIfscCode + ", transfer_amount=" + transferAmount + ", remark="
                + remark + "]";
    }

}
