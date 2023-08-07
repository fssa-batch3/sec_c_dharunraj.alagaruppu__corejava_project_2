CREATE DATABASE netbliz;
USE netbliz;

CREATE TABLE IF NOT EXISTS customers (
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
f_name VARCHAR(30) NOT NULL,
l_name VARCHAR(30) NOT NULL,
email  VARCHAR(30) NOT NULL UNIQUE,
phone VARCHAR(10) NOT NULL,
pass  VARCHAR(30) NOT NULL
);
 
SELECT * FROM customers;

-- Account module table
-- TINY INT 
CREATE TABLE IF NOT EXISTS accounts (
	acc_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	acc_no VARCHAR(16) NOT NULL UNIQUE,
    ifsc VARCHAR(11) NOT NULL,
    phone_number VARCHAR(11) NOT NULL,
    min_balance DOUBLE NOT NULL,
    date_of_joining TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    account_type VARCHAR(40) NOT NULL,
    avl_balance DOUBLE NOT NULL,
    is_active BOOLEAN NOT NULL 
);

SELECT * FROM accounts;
DESCRIBE accounts;

CREATE TABLE IF NOT EXISTS transactions(

trans_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
acc_holder VARCHAR(16) NOT NULL,
remittance VARCHAR(16) NOT NULL,
trans_status VARCHAR(10) NOT NULL,
trans_amount DOUBLE NOT NULL,
avl_balance DOUBLE NOT NULL,
paid_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
debited_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
remarks VARCHAR(30) NULL,
FOREIGN KEY (acc_holder) REFERENCES accounts(acc_no)
);

SELECT * FROM transactions;
-- drop table transactions;
 DESCRIBE transactions;
 
 /*
  * AccountDao layer using querys
  */
 
 	String query = "SELECT * FROM accounts WHERE acc_no = ?"; // Use parameterized query to prevent SQL injection 
 	
 	final String query = "UPDATE accounts SET phone_number = ? WHERE acc_no = ?";
 	
 	final String query = "INSERT INTO accounts (acc_no, ifsc, phone_number, min_balance, account_type, avl_balance, is_active) VALUES (?, ?, ?, ?, ?, ?, ?)";

		final String query = "SELECT acc_no FROM accounts WHERE is_active = 0";
		
		String query = "UPDATE accounts SET is_active = 0 WHERE acc_no = ?";
		
		final String query = "SELECT acc_no FROM accounts";
		
		final String query = "SELECT acc_no FROM accounts WHERE is_active = 1";
		
  /*
  * TransactionDao layer using querys
  */
		
	String query = "SELECT acc_no,avl_balance FROM accounts WHERE acc_no = ? AND is_active = true";
	
	String query = "SELECT acc_no,avl_balance FROM accounts WHERE acc_no = ? AND is_active = true AND avl_balance >= ?";

	String query = "SELECT acc_no,avl_balance FROM accounts WHERE acc_no = ? AND ifsc = ? AND is_active = true";

	String query = "UPDATE accounts SET avl_balance = ? WHERE acc_no = ?";

	String query = "UPDATE accounts SET avl_balance = ? WHERE acc_no = ?";

	String query = "INSERT INTO transactions VALUE(? , ? , ? , ? , ? , ? )";

	String query = "INSERT INTO transactions VALUE(? , ? , ? , ? , ? , ? )";

						
 