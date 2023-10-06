# NETBLIZ Full Stack Java Project

## Problem Statement

This project aim is to address a real-time issue that affects all customers with existing bank accounts, particularly those who have savings and current accounts in major public and private banks. In today's world, it is common for individuals to hold at least one or two bank accounts, and most of these accounts require customers to maintain a Monthly Average Minimum Balance (MAB).Failure to maintain the MAB results in customers being fined, albeit by small amounts.From an individual perspective, these fines may seem insignificant, but from the bank's overall perspective, they accumulate into a substantial amount.

## Market Research

 According to 2020 statistics, both public and private sector banks in India collectively accrued more than 35,530 crores in penalties from customers due to non-compliance with MAB requirements.

## Solution

This project stands out from existing UPI apps as it allows customers to link their bank accounts to my application. Once linked, customers can transfer money to other accounts within the application. In every transaction, I check the customers monthly minimum balance, and if the sent amount exceeds this minimum balance, It notify the user before proceeding with the transaction. This ensures that customers maintain their monthly minimum balance properly.


# User Flow


## User Registration

1. **Visit Registration Page:**
   - Users navigate to the registration page.

2. **Enter Details:**
   - Users provide their first name, last name, email, phone number, and password.

3. **Submit Registration:**
   - Users submit the registration form.

4. **Validation:**
   - The system validates the input data, ensuring it meets the required criteria.

5. **Successful Registration:**
   - If the data is valid, a new user record is created in the `users` table.
   - Users receive a confirmation message.

6. **Failed Registration:**
   - If there are validation errors or other issues, users receive an error message.


## User Login

1. **Visit Login Page:**
   - Users go to the login page.

2. **Enter Phone Number and Password:**
   - Users input their phone number and password.

3. **Validation:**
   - The system checks if the provided credentials match any record in the `users` table.

4. **Successful Login:**
   - If the credentials are correct, users are redirected to the home page.
   - An authentication token may be issued for subsequent requests.

5. **Failed Login:**
   - If the credentials are incorrect, users remain on the login page with an error message.


## Linking an Existing Bank Account

1. **User Profile Home:**
   - Users are logged in and navigate to their home page within your application.

2. **Initiate Bank Account Linking:**
   - Users find and select the "Link Bank Account" option.

3. **Enter Bank Account Details:**
   - Users are presented with a form where they input their bank account number and IFSC code.

4. **Accept Terms and Conditions:**
   - Users review and accept any required terms and conditions related to linking their bank account by checking a box or clicking a button.

5. **Submit Request:**
   - Users click the "Link Account" button to submit the request.

6. **Validation:**
   - The system validates the provided account number and IFSC code.

7. **Check Bank Details Table:**
   - The system checks if the provided account number and IFSC code match an existing record in the `bank_details` table.

8. **Retrieve Bank Account Details:**
   - If the details match an existing record in the `bank_details` table, the system fetches the bank account details (available balance, phone number, minimum balance, account type, etc.) from the matched record.

9. **Create User Account Entry:**
   - The system creates a new entry in the `accounts` table, associating it with the user's profile.
   - The user's profile is successfully linked to the bank account.

10. **Confirmation:**
    - Users receive a confirmation message that their bank account has been successfully linked to the application.


## Transfer Money Using Account Number

1. **User Header Navigation:**
   - At the top of the page, there is a header with the name 'Transfer.' Clicking on it redirects the user to the money transfer page.

2. **Initiate Money Transfer:**
   - Users find and select the "Transfer Money" option.

3. **Select Sender Account:**
   - Users choose their own linked bank account from a dropdown list.

4. **Enter Receiver Account Details:**
   - Users input the receiver's bank account number, IFSC code, transfer amount, and an optional remark.

5. **Validation:**
   - The system validates the entered data, including the available balance in the sender's account.

6. **Minimum Balance Check:**
   - If the transfer amount affects the sender's account's minimum balance, users receive an alert.
   - Users can choose to proceed or cancel the transaction.

7. **Proceed with Transaction:**
   - If users decide to proceed, they confirm the transaction.

8. **Transaction Confirmation:**
   - The system initiates the money transfer, deducting the transfer amount from the sender's account balance.
   - A transaction record is created in the `transactions` table, reflecting the transfer.

9. **Receiver Account Update:**
   - The receiver's account is credited with the transferred amount.

10. **Confirmation:**
    - Users receive a confirmation message that the money transfer was successful.

11. **Transaction History Update:**
    - The transaction is added to the sender's and receiver's transaction histories.


## Viewing Transaction History

1. **Transaction Success:**
   - After a successful money transfer, users are redirected to the transaction history page.

2. **Transaction Listing:**
   - Users see a list of all their transactions, including both credits and debits.

## Filtering Transactions

1. **Initiate Filtering:**
   - Users find and click on the "Filter" option in the upper right corner.

2. **Select Bank Account Number:**
   - Users are presented with a dropdown menu listing their linked bank account numbers.

3. **Filter by Account Number:**
   - Users choose an account number to filter transactions associated with that account.

4. **Filter Applied:**
   - The system filters and displays only the transactions related to the selected account number.

## Searching Transactions

1. **Initiate Search:**
   - Users locate the search bar in the upper left corner.

2. **Enter Search Query:**
   - Users type in a search query (e.g., a keyword, transaction description).

3. **Search and Display:**
   - As users type, the system dynamically searches and displays transactions containing the entered word.

4. **Results Display:**
   - Transactions matching the search query are shown, while others are hidden.

5. **Clear Search:**
   - Users can clear the search query to revert to the full transaction history.


## Chart Page Overview

1. **MAB Explanation:**
   - The chart page provides information about the Minimum Average Balance (MAB).

2. **Dropdown Selection:**
   - Users encounter a powerful dropdown that lists their linked bank account numbers.

3. **Select Account:**
   - Users choose an account number from the dropdown.

4. **Automatic MAB Calculation:**
   - The system automatically calculates the MAB based on the closing balance recorded at the end of each day (EOD).

5. **Display MAB Data:**
   - Users can see a table displaying dates and corresponding EOD closing balances used in the MAB calculation.

6. **MAB Calculation:**
   - Below the table, the page provides details of how the MAB is calculated.

## Calculation Explanation

1. **Daily Closing Balances:**
   - The system records the closing balance of the selected bank account every day at 11:50 PM.

2. **Calculation Trigger:**
   - A trigger is set to automatically perform MAB calculations based on these daily closing balances.

3. **Average Calculation:**
   - The system calculates the average closing balance using the recorded EOD balances over a specific time period (e.g., a month).

4. **MAB Display:**
   - The resulting MAB value is displayed on the chart page, providing users with insights into their account's financial health.


## GitHub Issue - Milestone - 1,2,3:
Link to GitHub Issue: [Milestone -1,2,3 GitHub Issue](https://github.com/fssa-batch3/sec_c_dharunraj.alagaruppu__corejava_project_2/issues)

## Code Quality Analysis

We have integrated our project with SonarCloud to perform code quality analysis and static code analysis. You can find the overall analysis report on the SonarCloud platform:

- [SonarCloud Analysis](https://sonarcloud.io/summary/new_code?id=fssa-batch3_sec_c_dharunraj.alagaruppu__corejava_project_2)



## Reverse Engineering

<div align="center">
  <img src="er_image/netbliz_module.png" alt="Account Database Design" width="65%">
</div>


## Table of Contents

1. [Customers Table](#customers-table)
2. [Accounts Table](#accounts-table)
3.[Bank Details Table](#bank-details-table)
4. [Transactions Table](#transactions-table)
5. [Date Table](#date-table) 
6. [Closing Balance Table](#closing-balance-table)
7. [Backup Date Table](#backup-date-table)
8. [Backup Closing Balance Table](#backup-closing-balance-table)
  

## Milestone -1 

### Customers Table

This table is used to store information about customers.

### Table Schema

| Column Name   | Data Type     | Constraints         | Description                                 |
| ------------- | ------------- | ------------------- | ------------------------------------------- |
| `customer_id` | BIGINT        | AUTO_INCREMENT, PRIMARY KEY | Unique identifier for each customer. |
| `first_name`  | VARCHAR(30)   | NOT NULL            | First name of the customer.                 |
| `last_name`   | VARCHAR(30)   | NOT NULL            | Last name of the customer.                  |
| `email`       | VARCHAR(50)   | NOT NULL            | Email address of the customer.              |
| `phone_number`| BIGINT        | NOT NULL, UNIQUE    | Phone number of the customer.               |
| `password`    | VARCHAR(30)   | NOT NULL            | Password for customer authentication.       |
| `is_active`   | BOOLEAN       | NOT NULL, DEFAULT TRUE | Indicates whether the customer is active. |


## Milestone -2

### Accounts Table

This table is used to store information about bank accounts.

### Table Schema

| Column Name        | Data Type     | Constraints                                         | Description                                    |
| -------------------| ------------- | --------------------------------------------------- | ---------------------------------------------- |
| `acc_id`           | BIGINT        | AUTO_INCREMENT, PRIMARY KEY                         | Unique identifier for each bank account.       |
| `acc_no`           | VARCHAR(16)   | NOT NULL, UNIQUE                                    | Bank account number.                           |
| `ifsc`             | VARCHAR(11)   | NOT NULL                                            | Indian Financial System Code (IFSC).          |
| `avl_balance`      | DOUBLE        | NOT NULL                                            | Available balance in the account.              |
| `phone_number`     | BIGINT        | NOT NULL                                            | Phone number associated with the account.      |
| `min_balance`      | DOUBLE        | NOT NULL                                            | Minimum balance required for the account.      |
| `account_type`     | VARCHAR(40)   | NOT NULL                                            | Type of bank account.                          |
| `is_active`        | BOOLEAN       | NOT NULL, DEFAULT TRUE                             | Indicates whether the account is active.       |
| `date_of_joining`  | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL | Date of joining the bank.           |
| `month_interval`   | INT           | NOT NULL                                            | Interval for monthly account maintenance.       |
| `avg_balance`      | DOUBLE        | NOT NULL, DEFAULT 0                                 | Average balance in the account.                |
| `customer_id`      | BIGINT        |                                                    | Foreign key referencing the customer.           |
| `bank_id`          | BIGINT        |                                                    | Foreign key referencing the bank details.      |

## Bank Details Table

The `bank_details` table stores information about bank accounts linked to the application. Here's the table schema:

| Column Name    | Data Type     | Description                                      |
| -------------- | ------------- | ------------------------------------------------ |
| bank_id        | BIGINT        | Primary key for the bank account record.        |
| acc_no         | VARCHAR(16)   | Bank account number (unique).                   |
| ifsc           | VARCHAR(11)   | IFSC code of the bank branch.                   |
| avl_balance    | DOUBLE        | Available balance in the account.               |
| phone_number   | BIGINT        | Phone number associated with the account.       |
| min_balance    | DOUBLE        | Minimum balance required for the account.       |
| account_type   | VARCHAR(40)   | Type of bank account (e.g., savings, current).  |
| is_active      | BOOLEAN       | Indicates whether the account is active or not. |
| month_interval | INT           | Time interval for any specific operations.      |

This table is crucial for managing and tracking bank account details within the application.

## Milestone -3

### Transactions Table

This table is used to store information about financial transactions.

### Table Schema

| Column Name     | Data Type     | Constraints                                         | Description                                    |
| ----------------| ------------- | --------------------------------------------------- | ---------------------------------------------- |
| `trans_id`      | BIGINT        | AUTO_INCREMENT, PRIMARY KEY                         | Unique identifier for each transaction.        |
| `acc_holder`    | VARCHAR(16)   | NOT NULL                                            | Account holder's name.                         |
| `remittance`    | VARCHAR(16)   | NOT NULL                                            | Recipient or remittance details.               |
| `trans_status`  | VARCHAR(10)   | NOT NULL                                            | Transaction status (e.g., "completed").        |
| `trans_amount`  | DOUBLE        | NOT NULL                                            | Transaction amount.                             |
| `avl_balance`   | DOUBLE        | NOT NULL                                            | Available balance after the transaction.       |
| `paid_time`     | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP NOT NULL                 | Timestamp when the transaction was paid.       |
| `debited_time`  | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP NOT NULL                 | Timestamp when the account was debited.        |
| `remark`        | VARCHAR(50)   | NULL                                                | Optional remark or note for the transaction.   |
| `holder_id`     | BIGINT        | NOT NULL                                           | Foreign key referencing the customer.          |
| `remittance_id` | BIGINT        | NOT NULL                                           | Foreign key referencing the remittance details. |

## Milestone -4

## Date Table

The `date_table` is a simple table used for tracking dates and timestamps. Below is the table schema:

| Column Name   | Data Type             | Description                           |
| ------------- | --------------------- | ------------------------------------- |
| date_id       | BIGINT                | Primary key for date records.         |
| updated_date  | TIMESTAMP             | Timestamp of when the record was updated. |


## Closing Balance Table

The `closing_balance` table is used to store daily closing balance records associated with bank accounts. Below is the table schema:

| Column Name         | Data Type             | Description                                      |
| ------------------- | --------------------- | ------------------------------------------------ |
| closing_balance_id  | BIGINT                | Primary key for closing balance records.         |
| acc_no              | VARCHAR(16)           | Bank account number (linked to `accounts`).      |
| date_id             | BIGINT                | Reference to the date associated with the record (linked to `date_table`). |
| eod_balance         | DOUBLE                | End-of-day (EOD) closing balance for the account. |


## Backup Date Table

The `backup_date_table` is a table used for tracking dates and timestamps, often used for backup purposes. Below is the table schema:

| Column Name   | Data Type             | Description                           |
| ------------- | --------------------- | ------------------------------------- |
| date_id       | BIGINT                | Primary key for date records.         |
| updated_date  | TIMESTAMP             | Timestamp of when the record was updated. |


## Backup Closing Balance Table

The `backup_closing_balance` table is used to store backup records of daily closing balance information associated with bank accounts. Below is the table schema:

| Column Name         | Data Type             | Description                                      |
| ------------------- | --------------------- | ------------------------------------------------ |
| closing_balance_id  | BIGINT                | Primary key for closing balance backup records.  |
| acc_no              | VARCHAR(16)           | Bank account number (linked to `closing_balance`).|
| date_id             | BIGINT                | Reference to the date associated with the backup record (linked to `date_table`). |
| eod_balance         | DOUBLE                | End-of-day (EOD) closing balance backup for the account. |
