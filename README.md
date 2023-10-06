# sec_c_dharunraj.alagaruppu__corejava_project_2

<div align="center">
  <img src="er_image/netbliz_module.png" alt="Account Database Design" width="80%">
</div>


# Database Design - NETBLIZ

This repository contains the SQL schema definition for a Core Java project's database. It includes tables for managing customer information, accounts, and transactions.

## Table of Contents

- [Customers Table](#Customers-Table)
- [Accounts Table](#accounts-table)
- [Transactions Table](#transactions-table)

### Customers Table

This table stores information about customers.

| Column       | Data Type          | Constraints                  | Description                           |
|--------------|--------------------|------------------------------|---------------------------------------|
| customer_id  | BIGINT             | AUTO_INCREMENT, PRIMARY KEY  | Unique identifier for each customer.  |
| first_name   | VARCHAR(30)        | NOT NULL                     | First name of the customer.           |
| last_name    | VARCHAR(30)        | NOT NULL                     | Last name of the customer.            |
| email        | VARCHAR(50)        | NOT NULL                     | Email address of the customer.        |
| phone        | BIGINT             | NOT NULL, UNIQUE             | Phone number of the customer.         |
| password     | VARCHAR(30)        | NOT NULL                     | Password for customer authentication. |
| is_active    | BOOLEAN            | NOT NULL, DEFAULT TRUE       | Indicates if the customer is active.  |



### Accounts Table

This table stores information about customer accounts.

| Column           | Data Type         | Constraints                                 | Description                          |
|------------------|-------------------|---------------------------------------------|--------------------------------------|
| acc_id           | BIGINT            | AUTO_INCREMENT, PRIMARY KEY                 | Unique identifier for each account.  |
| acc_no           | VARCHAR(16)       | NOT NULL, UNIQUE                            | Account number.                      |
| ifsc             | VARCHAR(11)       | NOT NULL                                    | IFSC code of the bank branch.        |
| avl_balance      | DOUBLE            | NOT NULL                                    | Available balance in the account.    |
| phone_number     | BIGINT            | NOT NULL                                    | Phone number associated with account.|
| min_balance      | DOUBLE            | NOT NULL                                    | Minimum required balance in the account.|
| is_active        | BOOLEAN           | NOT NULL                                    | Indicates if the account is active.  |
| account_type     | VARCHAR(40)       | NOT NULL                                    | Type of the account (e.g., savings, current, etc.). |
| date_of_joining  | TIMESTAMP         | DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, NOT NULL | Date of joining the bank.       |
| customer_id      | BIGINT            | NOT NULL REFERENCES customers (customer_id) | Foreign key referencing the customer.|


## Transactions Table

This table stores information about transactions.

| Column         | Data Type     | Constraints                                 | Description                                      |
|----------------|---------------|---------------------------------------------|--------------------------------------------------|
| trans_id       | BIGINT        | AUTO_INCREMENT, PRIMARY KEY                 | Unique identifier for each transaction.           |
| acc_holder     | VARCHAR(16)   | NOT NULL                                    | Account number of the account holder.             |
| remittance     | VARCHAR(16)   | NOT NULL                                    | Account number of the remittance recipient.       |
| trans_status   | VARCHAR(10)   | NOT NULL                                    | Status of the transaction (e.g., successful, failed). |
| trans_amount   | DOUBLE        | NOT NULL                                    | Amount of the transaction.                        |
| avl_balance    | DOUBLE        | NOT NULL                                    | Available balance after the transaction.          |
| paid_time      | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP, NOT NULL         | Timestamp of when the transaction was initiated.  |
| debited_time   | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP, NOT NULL         | Timestamp of when the amount was debited.         |
| remark         | VARCHAR(30)   | NULL                                        | Additional remarks or notes about the transaction.|
| acc_holder     | FOREIGN KEY   | REFERENCES accounts(acc_no)                 | Foreign key referencing the account holder's account number.|
| customer_id    | BIGINT        | NOT NULL REFERENCES customers (customer_id) | Foreign key referencing the customer.|
