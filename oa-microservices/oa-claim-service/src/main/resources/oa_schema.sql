DROP TABLE IF EXISTS deal_record;
DROP TABLE IF EXISTS claim_voucher_item;
DROP TABLE IF EXISTS claim_voucher;
DROP TABLE IF EXISTS log;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department (
    id VARCHAR(32) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE employee (
    id VARCHAR(32) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    department_id VARCHAR(32),
    post VARCHAR(20)
);

CREATE TABLE log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id VARCHAR(32),
    operation_time DATETIME,
    operation VARCHAR(50)
);

CREATE TABLE claim_voucher (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cause VARCHAR(100),
    create_id VARCHAR(32),
    create_time DATETIME,
    next_deal_id VARCHAR(32),
    total_amount DOUBLE,
    status VARCHAR(20)
);

CREATE TABLE claim_voucher_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    claim_voucher_id INT,
    item VARCHAR(50),
    amount DOUBLE,
    comment VARCHAR(100)
);

CREATE TABLE deal_record (
    id INT AUTO_INCREMENT PRIMARY KEY,
    claim_voucher_id INT,
    deal_id VARCHAR(32),
    deal_time DATETIME,
    deal_type VARCHAR(20),
    deal_result VARCHAR(20),
    comment VARCHAR(100)
);

ALTER TABLE employee ADD CONSTRAINT fk_did FOREIGN KEY (department_id) REFERENCES department(id);
ALTER TABLE claim_voucher ADD CONSTRAINT fk_cid FOREIGN KEY (create_id) REFERENCES employee(id);
ALTER TABLE claim_voucher ADD CONSTRAINT fk_ndid FOREIGN KEY (next_deal_id) REFERENCES employee(id);
ALTER TABLE claim_voucher_item ADD CONSTRAINT fk_cvid FOREIGN KEY (claim_voucher_id) REFERENCES claim_voucher(id);
ALTER TABLE deal_record ADD CONSTRAINT fk_cv_id FOREIGN KEY (claim_voucher_id) REFERENCES claim_voucher(id);
ALTER TABLE deal_record ADD CONSTRAINT fk_deal_id FOREIGN KEY (deal_id) REFERENCES employee(id);
ALTER TABLE log ADD CONSTRAINT fk_eid FOREIGN KEY (employee_id) REFERENCES employee(id);
