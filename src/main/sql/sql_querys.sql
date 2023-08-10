-- Create 'customers' table
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    f_name VARCHAR(30) NOT NULL,
    l_name VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    phone VARCHAR(10) NOT NULL,
    pass VARCHAR(30) NOT NULL
);

-- Select all records from 'customers' table
SELECT * FROM customers;

-- Create 'account' table
CREATE TABLE IF NOT EXISTS account (
    acc_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    acc_no VARCHAR(16) NOT NULL UNIQUE,
    ifsc VARCHAR(11) NOT NULL,
    phone_number VARCHAR(11) NOT NULL,
    min_balance DOUBLE NOT NULL,
    date_of_joining TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    account_type VARCHAR(40) NOT NULL,
    avl_balance DOUBLE NOT NULL,
    is_active BOOLEAN NOT NULL
);

-- Select all records from 'account' table
SELECT * FROM account;

-- Describe the structure of 'account' table
DESCRIBE account;

-- Create 'transaction' table
CREATE TABLE IF NOT EXISTS transaction (
    trans_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    acc_holder VARCHAR(16) NOT NULL,
    remittance VARCHAR(16) NOT NULL,
    trans_status VARCHAR(10) NOT NULL,
    trans_amount DOUBLE NOT NULL,
    avl_balance DOUBLE NOT NULL,
    paid_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    debited_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    remarks VARCHAR(30) NULL,
    FOREIGN KEY (acc_holder) REFERENCES account(acc_no)
);

-- Select all records from 'transaction' table
SELECT * FROM transaction;

DESCRIBE transaction;

String query = "SELECT * FROM account WHERE acc_no = ?";

final String query = "UPDATE account SET phone_number = ? WHERE acc_no = ?";

final String query = "INSERT INTO account (acc_no, ifsc, phone_number, min_balance, account_type, avl_balance, is_active) VALUES (?, ?, ?, ?, ?, ?, ?)";

final String query = "SELECT acc_no FROM account WHERE is_active = 0";

String query = "UPDATE account SET is_active = 0 WHERE acc_no = ?";

-- Select all account numbers
final String query = "SELECT acc_no FROM account";

-- Select active account numbers
final String query = "SELECT acc_no FROM account WHERE is_active = 1";

String query = "SELECT acc_no, avl_balance FROM account WHERE acc_no = ? AND is_active = true";

String query = "SELECT acc_no, avl_balance FROM account WHERE acc_no = ? AND is_active = true AND avl_balance >= ?";

String query = "SELECT acc_no, avl_balance FROM account WHERE acc_no = ? AND ifsc = ? AND is_active = true";

String query = "UPDATE account SET avl_balance = ? WHERE acc_no = ?";

String query = "UPDATE account SET avl_balance = ? WHERE acc_no = ?";

String query = "INSERT INTO transaction (acc_holder, remittance, trans_status, trans_amount, avl_balance, remark) VALUES (?, ?, ?, ?, ?, ?)";

String query = "INSERT INTO transaction (acc_holder, remittance, trans_status, trans_amount, avl_balance, remark) VALUES (?, ?, ?, ?, ?, ?)";


-- Rename 'transactions' table to 'transaction'
ALTER TABLE transactions RENAME TO transaction;
