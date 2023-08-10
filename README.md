# sec_c_dharunraj.alagaruppu__corejava_project_2

<div align="center">
  <img src="er_image/netbliz_module.png" alt="Account Database Design" width="60%">
</div>



# Core Java Project Database Schema

This repository contains the SQL schema definition for a Core Java project's database. It includes tables for managing customer information, accounts, and transactions.

## Table of Contents

- [Account Table](#account-table)
- [Transaction Table](#transaction-table)

## Account Table

The `account` table stores information about customer accounts.

| Column Name       | Data Type   | Description                                 |
|-------------------|-------------|---------------------------------------------|
| acc_id            | BIGINT      | Primary key                                 |
| acc_no            | VARCHAR(16) | Account number (unique)                    |
| ifsc              | VARCHAR(11) | IFSC code                                   |
| phone_number      | VARCHAR(11) | Phone number                               |
| min_balance       | DOUBLE      | Minimum balance required                   |
| date_of_joining   | TIMESTAMP   | Date of joining account                    |
| account_type      | VARCHAR(40) | Type of account (e.g., Savings, Current)   |
| avl_balance       | DOUBLE      | Available balance                          |
| is_active         | BOOLEAN     | Account status (active or not)             |

## Transaction Table

The `transaction` table stores information about transactions.

| Column Name   | Data Type   | Description                               |
|---------------|-------------|-------------------------------------------|
| trans_id      | BIGINT      | Primary key                               |
| acc_holder    | VARCHAR(16) | Account number of the account holder      |
| remittance    | VARCHAR(16) | Account number of the remittance          |
| trans_status  | VARCHAR(10) | Transaction status (e.g., Success, Failed)|
| trans_amount  | DOUBLE      | Transaction amount                        |
| avl_balance   | DOUBLE      | Available balance after the transaction  |
| paid_time     | TIMESTAMP   | Timestamp of payment                      |
| debited_time  | TIMESTAMP   | Timestamp of debit (if applicable)        |
| remark        | VARCHAR(30) | Remark (optional)                         |

