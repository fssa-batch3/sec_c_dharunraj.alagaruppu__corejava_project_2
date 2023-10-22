CREATE TABLE IF NOT EXISTS bank_details (
    bank_id BIGINT PRIMARY KEY,
    acc_no VARCHAR(16) NOT NULL UNIQUE ,
    ifsc VARCHAR(11) NOT NULL,
    avl_balance DOUBLE NOT NULL,
    phone_number BIGINT NOT NULL,
    min_balance DOUBLE NOT NULL,
    account_type VARCHAR(40) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    month_interval INT NOT NULL
);


CREATE TABLE IF NOT EXISTS customers (
customer_id BIGINT AUTO_INCREMENT PRIMARY KEY ,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
email  VARCHAR(50) NOT NULL ,
phone_number BIGINT NOT NULL UNIQUE,
password VARCHAR(30) NOT NULL,
is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS accounts (
	acc_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	acc_no VARCHAR(16) NOT NULL UNIQUE ,
    ifsc VARCHAR(11) NOT NULL,
    avl_balance DOUBLE NOT NULL, 
    phone_number BIGINT NOT NULL,
    min_balance DOUBLE NOT NULL,
    account_type VARCHAR(40) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    date_of_joining TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    month_interval INT NOT NULL,
    avg_balance DOUBLE NOT NULL DEFAULT 0,
    customer_id BIGINT,
    bank_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    FOREIGN KEY (acc_no) REFERENCES  bank_details (acc_no)
    );

CREATE TABLE IF NOT EXISTS transactions (
trans_id BIGINT AUTO_INCREMENT PRIMARY KEY,
acc_holder VARCHAR(16) NOT NULL,
remittance VARCHAR(16) NOT NULL,
trans_status VARCHAR(10) NOT NULL,
trans_amount DOUBLE NOT NULL,
avl_balance DOUBLE NOT NULL,
paid_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
debited_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
remark VARCHAR(50) NULL,
holder_id BIGINT NOT NULL,
remittance_id BIGINT NOT NULL,
FOREIGN KEY (holder_id) REFERENCES customers (customer_id)
);

CREATE TABLE IF NOT EXISTS date_table (

date_id BIGINT AUTO_INCREMENT PRIMARY KEY,
updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS closing_balance (
    closing_balance_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    acc_no VARCHAR(16) NOT NULL,
    date_id BIGINT NOT NULL,
    eod_balance DOUBLE NOT NULL,
    FOREIGN KEY (date_id) REFERENCES date_table (date_id),
    FOREIGN KEY (acc_no) REFERENCES accounts (acc_no)
);

CREATE TABLE IF NOT EXISTS backup_date_table (

date_id BIGINT AUTO_INCREMENT PRIMARY KEY,
updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS backup_closing_balance (
    closing_balance_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    acc_no VARCHAR(16) NOT NULL,
    date_id BIGINT NOT NULL,
    eod_balance DOUBLE NOT NULL,
    FOREIGN KEY (date_id) REFERENCES backup_date_table (date_id)
);

SELECT * FROM customers;
SELECT * FROM accounts;
SELECT * FROM transactions;
SELECT * FROM bank_details;
SELECT * FROM date_table;
SELECT * FROM closing_balance;
SELECT * FROM backup_date_table;
SELECT * FROM backup_closing_balance;



-- select closing_balance.acc_number,closing_balance.closing_balance ,date_table.date_of_update from closing_balance inner join date_table on closing_balance.date_of_update = date_table.date_of_update;
-- SELECT date_id FROM date_table ORDER BY date_id DESC LIMIT 1;

-- SELECT date_id FROM date_table ORDER BY date_id DESC LIMIT 1;
-- update updated_date  set date_of_update = now();
-- INSERT INTO updated_date (date_of_update) VALUES (NOW());  

-- INSERT INTO closing_balance (date_id,acc_number,closing_balance) VALUES (2 , 1234567890123456 , 75000.0);

drop table transactions;
drop table backup_closing_balance;
drop table closing_balance;
drop table date_table;
drop table accounts;
drop table bank_details;
drop table customers;
drop table backup_date_table;

INSERT INTO bank_details (bank_id, acc_no, ifsc, avl_balance, phone_number, min_balance, account_type, is_active, month_interval)
VALUES
    (1, '1234567890123456', 'IDIB000K132', 20000.00, '9361320511', 500.00, 'SAVINGS', true, 1),
	(7, '0987654321123456', 'HDFC0001858', 31000.00, '9600678232', 1000.00, 'SAVINGS', true, 1),
    (2, '1234567890123455', 'IDIB000K132', 45000.00, '9361320511', 25000.00, 'SAVINGS', true, 1),
    (3, '1234567890123454', 'IDIB000K132', 2000.00, '9361320511', 10000.00, 'SAVINGS', true, 1),
    (4, '1234567890123453', 'IDIB000K132', 5000.00, '9361320511', 1000.00, 'SAVINGS', true, 1),
    (5, '1234567890123452', 'IDIB000K132', 60000.00, '9361320511', 2500.00, 'SAVINGS', true, 1),
    (6, '1234567890123451', 'IDIB000K132', 85000.00, '9361320511', 5000.00, 'SAVINGS', true, 1),
    (8, '0987654321123455', 'HDFC0001858', 70000.00, '9600678232', 10000.00, 'SAVINGS', true, 1),
    (9, '0987654321123454', 'HDFC0001858', 8000.00, '9600678232', 2500.00, 'SAVINGS', true, 1),
    (10, '0987654321123453', 'HDFC0001858', 95000.00, '9600678232', 5000.00, 'SAVINGS', true, 1),
    (11, '0987654321123452', 'HDFC0001858', 72000.00, '9600678232', 10000.00, 'SAVINGS', true, 1),
    (12, '0987654321123451', 'HDFC0001858', 8000.00, '9600678232', 2500.00, 'SAVINGS', true, 1),
    (13, '7890123456789012', 'SBIN0071060', 3000.00, '7402473347', 500.00, 'SAVINGS', true, 1),
    (14, '7890123456789013', 'SBIN0071060', 60000.00, '7402473347', 10000.00, 'SAVINGS', true, 1),
    (15, '7890123456789014', 'SBIN0071060', 85000.00, '7402473347', 2500.00, 'SAVINGS', true, 1),
    (16, '7890123456789015', 'SBIN0071060', 92000.00, '7402473347', 5000.00, 'SAVINGS', true, 1),
    (17, '7890123456789016', 'SBIN0071060', 70000.00, '7402473347', 10000.00, 'SAVINGS', true, 1),
    (18, '7890123456789017', 'SBIN0071060', 89000.00, '7402473347', 2500.00, 'SAVINGS', true, 1),
    (19, '8901234567890123', 'TMBL0000008', 95000.00, '7904486648', 5000.00, 'SAVINGS', true, 1),
    (20, '8901234567890124', 'TMBL0000008', 72000.00, '7904486648', 10000.00, 'SAVINGS', true, 1),
    (21, '8901234567890125', 'TMBL0000008', 83000.00, '7904486648', 2500.00, 'SAVINGS', true, 1),
    (22, '8901234567890126', 'TMBL0000008', 95000.00, '7904486648', 5000.00, 'SAVINGS', false, 1),
    (23, '8901234567890127', 'TMBL0000008', 72000.00, '7904486648', 10000.00, 'SAVINGS', true, 1),
    (24, '8901234567890128', 'TMBL0000008', 83000.00, '7904486648', 2500.00, 'SAVINGS', true, 1),
    (25, '8901234567890129', 'TMBL0000008', 92000.00, '7904486648', 5000.00, 'SAVINGS', true, 1),
    (99, '9912345678901289', 'IDIB000K132', 9000.00, '9361320513', 5000.00, 'SAVINGS', true, 1),
    (100, '1001234567890124', 'IDIB000K132', 5000.00, '6380409632', 500.00, 'SAVINGS', true, 1),
    (26, '26123456789012', 'UNIN0012345', 70000.00, '2109876543', 10000.00, 'SAVINGS', true, 1),
    (27, '27123456789012', 'SBIN0015678', 89000.00, '1098765432', 2500.00, 'SAVINGS', true, 1),
    (28, '28123456789012', 'TMBL0019876', 95000.00, '9876543210', 5000.00, 'CURRENT', true, 1),
    (29, '29123456789012', 'HDFC0012345', 75000.00, '8765432109', 10000.00, 'SAVINGS', true, 1),
    (30, '30123456789012', 'UNIN0012345', 60000.00, '7654321098', 2500.00, 'SAVINGS', true, 1),
    (31, '31123456789012', 'SBIN0015678', 89000.00, '6543210987', 5000.00, 'CURRENT', true, 1),
    (32, '32123456789012', 'TMBL0019876', 72000.00, '5432109876', 10000.00, 'SAVINGS', true, 1),
    (33, '33123456789012', 'HDFC0012345', 83000.00, '4321098765', 2500.00, 'SAVINGS', true, 1),
    (34, '34123456789012', 'UNIN0012345', 95000.00, '3210987654', 5000.00, 'CURRENT', true, 1),
    (35, '35123456789012', 'SBIN0015678', 72000.00, '2109876543', 10000.00, 'SAVINGS', true, 1),
    (36, '36123456789012', 'TMBL0019876', 83000.00, '1098765432', 2500.00, 'SAVINGS', true, 1),
    (37, '37123456789012', 'HDFC0012345', 92000.00, '9876543210', 5000.00, 'CURRENT', true, 1),
    (38, '38123456789012', 'UNIN0012345', 70000.00, '8765432109', 10000.00, 'SAVINGS', true, 1),
    (39, '39123456789012', 'SBIN0015678', 89000.00, '7654321098', 2500.00, 'SAVINGS', true, 1),
    (40, '40123456789012', 'TMBL0019876', 95000.00, '6543210987', 5000.00, 'CURRENT', true, 1),
    (41, '41123456789012', 'HDFC0012345', 72000.00, '5432109876', 10000.00, 'SAVINGS', true, 1),
    (42, '42123456789012', 'UNIN0012345', 83000.00, '4321098765', 2500.00, 'SAVINGS', true, 1),
    (43, '43123456789012', 'SBIN0015678', 95000.00, '3210987654', 5000.00, 'CURRENT', true, 1),
    (44, '44123456789012', 'TMBL0019876', 60000.00, '2109876543', 10000.00, 'SAVINGS', true, 1),
    (45, '45123456789012', 'HDFC0012345', 85000.00, '1098765432', 2500.00, 'SAVINGS', true, 1),
    (46, '46123456789012', 'UNIN0012345', 95000.00, '9876543210', 5000.00, 'CURRENT', false, 1),
    (47, '47123456789012', 'SBIN0015678', 72000.00, '8765432109', 10000.00, 'SAVINGS', true, 1),
    (48, '48123456789012', 'TMBL0019876', 83000.00, '7654321098', 2500.00, 'SAVINGS', true, 1),
    (49, '49123456789012', 'HDFC0012345', 95000.00, '6543210987', 5000.00, 'CURRENT', true, 1),
    (50, '50123456789012', 'UNIN0012345', 60000.00, '5432109876', 10000.00, 'SAVINGS', true, 1),
    (51, '51123456789012', 'SBIN0015678', 85000.00, '4321098765', 2500.00, 'SAVINGS', true, 1),
    (52, '52123456789012', 'TMBL0019876', 92000.00, '3210987654', 5000.00, 'CURRENT', true, 1),
    (53, '53123456789012', 'HDFC0012345', 70000.00, '2109876543', 10000.00, 'SAVINGS', true, 1),
    (54, '54123456789012', 'UNIN0012345', 89000.00, '1098765432', 2500.00, 'SAVINGS', true, 1),
    (55, '55123456789012', 'SBIN0015678', 95000.00, '9876543210', 5000.00, 'CURRENT', true, 1),
    (56, '56123456789012', 'TMBL0019876', 72000.00, '8765432109', 10000.00, 'SAVINGS', true, 1),
    (57, '57123456789012', 'HDFC0012345', 83000.00, '7654321098', 2500.00, 'SAVINGS', true, 1),
    (58, '58123456789012', 'UNIN0012345', 95000.00, '6543210987', 5000.00, 'CURRENT', true, 1),
    (59, '59123456789012', 'SBIN0015678', 60000.00, '5432109876', 10000.00, 'SAVINGS', true, 1),
    (60, '60123456789012', 'TMBL0019876', 85000.00, '4321098765', 2500.00, 'SAVINGS', true, 1),
    (61, '61123456789012', 'HDFC0012345', 92000.00, '3210987654', 5000.00, 'CURRENT', true, 1),
    (62, '62123456789012', 'UNIN0012345', 70000.00, '2109876543', 10000.00, 'SAVINGS', true, 1),
    (63, '63123456789012', 'SBIN0015678', 89000.00, '1098765432', 2500.00, 'SAVINGS', true, 1),
    (64, '64123456789012', 'TMBL0019876', 95000.00, '9876543210', 5000.00, 'CURRENT', true, 1),
    (65, '65123456789012', 'HDFC0012345', 72000.00, '8765432109', 10000.00, 'SAVINGS', true, 1),
    (66, '66123456789012', 'UNIN0012345', 83000.00, '7654321098', 2500.00, 'SAVINGS', true, 1),
    (67, '67123456789012', 'SBIN0015678', 95000.00, '6543210987', 5000.00, 'CURRENT', true, 1),
    (68, '68123456789012', 'TMBL0019876', 60000.00, '5432109876', 10000.00, 'SAVINGS', true, 1),
    (69, '69123456789012', 'HDFC0012345', 85000.00, '4321098765', 2500.00, 'SAVINGS', true, 1),
    (70, '70123456789012', 'UNIN0012345', 95000.00, '3210987654', 5000.00, 'CURRENT', true, 1),
    (71, '71123456789012', 'SBIN0015678', 70000.00, '2109876543', 10000.00, 'SAVINGS', true, 1),
    (72, '72123456789012', 'TMBL0019876', 89000.00, '1098765432', 2500.00, 'SAVINGS', true, 1),
    (73, '73123456789012', 'HDFC0012345', 95000.00, '9876543210', 5000.00, 'CURRENT', true, 1),
    (74, '74123456789012', 'UNIN0012345', 72000.00, '8765432109', 10000.00, 'SAVINGS', true, 1),
    (75, '75123456789012', 'SBIN0015678', 83000.00, '7654321098', 2500.00, 'SAVINGS', true, 1),
    (76, '76123456789012', 'TMBL0019876', 95000.00, '6543210987', 5000.00, 'CURRENT', true, 1),
    (77, '77123456789012', 'HDFC0012345', 60000.00, '5432109876', 10000.00, 'SAVINGS', true, 1),
    (78, '78123456789012', 'UNIN0012345', 85000.00, '4321098765', 2500.00, 'SAVINGS', true, 1),
    (79, '79123456789012', 'SBIN0015678', 92000.00, '3210987654', 5000.00, 'CURRENT', true, 1),
    (80, '80123456789012', 'HDFC0012345', 70000.00, '2109876543', 10000.00, 'SAVINGS', true, 1),
    (81, '81123456789012', 'UNIN0012345', 89000.00, '1098765432', 2500.00, 'SAVINGS', true, 1),
    (82, '82123456789012', 'TMBL0019876', 95000.00, '9876543210', 5000.00, 'CURRENT', true, 1),
    (83, '83123456789012', 'HDFC0012345', 72000.00, '8765432109', 10000.00, 'SAVINGS', true, 1),
    (84, '84123456789012', 'UNIN0012345', 83000.00, '7654321098', 2500.00, 'SAVINGS', true, 1),
    (85, '85123456789012', 'SBIN0015678', 95000.00, '6543210987', 5000.00, 'CURRENT', true, 1),
    (86, '86123456789012', 'TMBL0019876', 60000.00, '5432109876', 10000.00, 'SAVINGS', true, 1),
    (87, '87123456789012', 'HDFC0012345', 85000.00, '4321098765', 2500.00, 'SAVINGS', true, 1),
    (88, '88123456789012', 'UNIN0012345', 95000.00, '3210987654', 5000.00, 'CURRENT', true, 1),
    (89, '89123456789012', 'SBIN0015678', 70000.00, '2109876543', 10000.00, 'SAVINGS', true, 1),
    (90, '90123456789012', 'TMBL0019876', 89000.00, '1098765432', 2500.00, 'SAVINGS', true, 1),
    (91, '91123456789012', 'HDFC0012345', 95000.00, '9876543210', 5000.00, 'CURRENT', true, 1),
    (92, '92123456789012', 'UNIN0012345', 72000.00, '8765432109', 10000.00, 'SAVINGS', true, 1),
    (93, '93123456789012', 'SBIN0015678', 83000.00, '7654321098', 2500.00, 'SAVINGS', true, 1),
    (94, '94123456789012', 'TMBL0019876', 95000.00, '6543210987', 5000.00, 'CURRENT', true, 1),
    (95, '95123456789012', 'HDFC0012345', 60000.00, '5432109876', 10000.00, 'SAVINGS', true, 1),
    (96, '96123456789012', 'UNIN0012345', 85000.00, '4321098765', 2500.00, 'SAVINGS', true, 1),
    (97, '97123456789012', 'SBIN0015678', 92000.00, '3210987654', 5000.00, 'CURRENT', true, 1),
    (98, '98123456789012', 'HDFC0012345', 70000.00, '2109876543', 10000.00, 'SAVINGS', true, 1);

    
------    Customer DAO Query     ---------

    	final String query = "INSERT INTO customers (first_name,last_name,email,phone_number,password) VALUES (?,?,?,?,?)";
 
    	final String query = "SELECT phone_number,password FROM customers WHERE phone_number = ? ";

    	final String query = "SELECT phone_number FROM customers WHERE phone_number = ?";

    	final String query = "SELECT email,password FROM customers WHERE phone_number = ? AND is_active = ?";

    	final String query = "SELECT customer_id,first_name,last_name,phone_number,email,password,is_active FROM customers WHERE phone_number = ?";

    	final String query = "SELECT first_name,last_name FROM customers WHERE id = ? ";

--------    	Account DAO Query     ---------------

    	
    	final String query = "SELECT bank_id,acc_no,ifsc,avl_balance,phone_number,min_balance,account_type,is_active,month_interval FROM bank_details WHERE acc_no = ? AND ifsc = ? AND phone_number = ?";

    	final String query = "INSERT INTO accounts (acc_no, ifsc, avl_balance ,phone_number, min_balance, account_type,month_interval,customer_id,bank_id) VALUES (?, ?, ?, ?, ?, ?, ? ,?,?)";

    	final String query = "UPDATE accounts SET is_active = ? WHERE acc_no = ?";

    	final String query = "SELECT customer_id FROM customers WHERE phone_number = ? ";
    	
    	final String query = "UPDATE accounts SET is_active = ? WHERE acc_no = ?";

    	final String query = "SELECT acc_no,ifsc,account_type,min_balance,customer_id,is_active,avl_balance,date_of_joining,phone_number FROM accounts WHERE phone_number = ? AND is_active = ?";

    	final String query = "SELECT acc_no, avl_balance FROM accounts WHERE acc_no = ? AND is_active = ?";

    	final String query = "SELECT acc_no FROM accounts WHERE acc_no = ?";

    	final String query = "SELECT acc_no,ifsc,avl_balance,phone_number,min_balance,account_type,is_active FROM accounts WHERE acc_no = ? ";

    	
--------    	Transaction DAO        ---------
    	
    	
    	final String query = "SELECT phone_number FROM accounts WHERE acc_no = ?";

    	final String query = "SELECT avl_balance FROM accounts WHERE acc_no = ? AND is_active = ? AND avl_balance >= ?";

    	final String query = "SELECT avl_balance FROM accounts WHERE acc_no = ? AND ifsc = ? AND is_active = ?";

    	final String query = "UPDATE accounts SET avl_balance = ? WHERE acc_no = ?";

    	final String query = "UPDATE accounts SET avl_balance = ? WHERE acc_no = ?";
    	
    	final String query = "INSERT INTO transactions (acc_holder, remittance, trans_status, trans_amount, avl_balance, remark,holder_id,remittance_id) VALUES (?, ?, ?, ?, ?, ?,?,?)";
    	
    	final String query = "INSERT INTO transactions (acc_holder, remittance, trans_status, trans_amount, avl_balance, remark,holder_id,remittance_id) VALUES (?, ?, ?, ?, ?, ?,?,?)";
    	
    	final String query = "UPDATE bank_details SET avl_balance = ? WHERE acc_no = ?";
    	
    	final String query = "UPDATE bank_details SET avl_balance = ? WHERE acc_no = ?";

    	final String query = "SELECT acc_no,is_active,avl_balance FROM accounts WHERE acc_no = ? AND is_active = ? AND avl_balance >= ?";

    	final String query = "SELECT acc_no,ifsc,is_active FROM accounts WHERE acc_no = ? AND ifsc = ? AND is_active = ?";
    	
    	final String query = "SELECT acc_holder,remittance,trans_status,trans_amount,avl_balance,paid_time,debited_time,remark,holder_id,remittance_id FROM transactions WHERE holder_id = ?";

    	final String query = "SELECT acc_no,avl_balance,min_balance FROM accounts WHERE acc_no = ?";

    	final String query = "SELECT first_name FROM customers WHERE customer_id = ?";

    	
---------    	Update Cron DAO     ----------

    	
    	final String query = "UPDATE accounts SET avg_balance = avg_balance + avl_balance WHERE is_active = ?";

    	final String query = "INSERT INTO date_table (updated_date) VALUES (NOW())";

    	final String query = "INSERT INTO closing_balance (date_id,acc_no,eod_balance) VALUES (? , ? , ? )";

    	final String query = "SELECT acc_no,avl_balance FROM accounts";

    	final String query = "SELECT date_id FROM date_table ORDER BY date_id DESC LIMIT 1";

    	final String query = "SELECT c.acc_no,c.eod_balance,d.updated_date FROM closing_balance AS c INNER JOIN date_table AS d ON c.date_id = d.date_id WHERE acc_no = ?";

    	final String dataTransferDateTable = "INSERT INTO backup_date_table SELECT * FROM date_table";
    	
    	final String dataTransferClosingBalance = "INSERT INTO backup_closing_balance SELECT * FROM closing_balance";

		final String clearDateTable = "DELETE FROM date_table";

		final String clearClosingBalance = "DELETE FROM closing_balance";


    	



    	
    	

    	
    	
    	
    	
    	
    	
    	
    	

    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    