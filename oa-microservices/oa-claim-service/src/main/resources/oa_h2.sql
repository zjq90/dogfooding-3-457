/*
H2 Database Data Transfer
Source: MySQL oa.sql
Converted for H2 Database
Date: 2026-04-17
*/

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS deal_record;
DROP TABLE IF EXISTS claim_voucher_item;
DROP TABLE IF EXISTS claim_voucher;
DROP TABLE IF EXISTS log;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department (
  id CHAR(10) NOT NULL,
  name VARCHAR(20) DEFAULT NULL,
  address VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO department VALUES ('10001', '总经理办公室', '梦幻大厦c1201');
INSERT INTO department VALUES ('10002', '财务部', '梦幻大厦a1103');
INSERT INTO department VALUES ('10003', '研发部', '蔡氏大夏a7001');
INSERT INTO department VALUES ('10004', '销售部', '永恒大夏b7005');

CREATE TABLE employee (
  id CHAR(10) NOT NULL,
  password VARCHAR(20) DEFAULT NULL,
  name VARCHAR(20) DEFAULT NULL,
  department_id CHAR(10) DEFAULT NULL,
  post VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO employee VALUES ('c1002', '123456', '赵匡胤', '10002', '财务');
INSERT INTO employee VALUES ('x1005', '123456', '爱新觉罗.福临', '10004', '部门经理');
INSERT INTO employee VALUES ('y1003', '123456', '忽必烈', '10003', '部门经理');
INSERT INTO employee VALUES ('y1004', '123456', '朱元璋', '10003', '员工');
INSERT INTO employee VALUES ('z1001', '123456', '李世民', '10001', '总经理');

CREATE TABLE claim_voucher (
  id INT NOT NULL AUTO_INCREMENT,
  cause VARCHAR(100) DEFAULT NULL,
  create_id CHAR(10) DEFAULT NULL,
  create_time TIMESTAMP DEFAULT NULL,
  next_deal_id CHAR(10) DEFAULT NULL,
  total_amount DOUBLE DEFAULT NULL,
  status VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO claim_voucher VALUES (31, '出差', 'y1004', '2019-09-21 17:08:16', 'c1002', 780, '已审核');

CREATE TABLE claim_voucher_item (
  id INT NOT NULL AUTO_INCREMENT,
  claim_voucher_id INT DEFAULT NULL,
  item VARCHAR(20) DEFAULT NULL,
  amount DOUBLE DEFAULT NULL,
  comment VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO claim_voucher_item VALUES (72, 31, '交通', 780, '来回高铁票');

CREATE TABLE deal_record (
  id INT NOT NULL AUTO_INCREMENT,
  claim_voucher_id INT DEFAULT NULL,
  deal_id CHAR(10) DEFAULT NULL,
  deal_time TIMESTAMP DEFAULT NULL,
  deal_type VARCHAR(20) DEFAULT NULL,
  deal_result VARCHAR(20) DEFAULT NULL,
  comment VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO deal_record VALUES (17, 31, 'y1004', '2019-09-21 17:08:16', '创建', '新创建', '无');
INSERT INTO deal_record VALUES (18, 31, 'y1004', '2019-09-21 17:22:45', '提交', '已提交', '无');
INSERT INTO deal_record VALUES (19, 31, 'y1003', '2019-09-21 17:23:39', '打回', '已打回', '');
INSERT INTO deal_record VALUES (20, 31, 'y1004', '2019-09-21 17:24:00', '提交', '已提交', '无');
INSERT INTO deal_record VALUES (21, 31, 'y1003', '2019-09-21 17:24:17', '通过', '已审核', '允许报销');

CREATE TABLE log (
  id INT NOT NULL AUTO_INCREMENT,
  employee_id CHAR(10) NOT NULL,
  operation_time TIMESTAMP DEFAULT NULL,
  operation VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO log VALUES (1, 'y1004', '2019-09-22 16:51:13', 'login');
INSERT INTO log VALUES (2, 'y1003', '2019-09-22 16:59:29', 'login');

ALTER TABLE employee ADD CONSTRAINT fk_did FOREIGN KEY (department_id) REFERENCES department(id);
ALTER TABLE claim_voucher ADD CONSTRAINT fk_cid FOREIGN KEY (create_id) REFERENCES employee(id);
ALTER TABLE claim_voucher ADD CONSTRAINT fk_ndid FOREIGN KEY (next_deal_id) REFERENCES employee(id);
ALTER TABLE claim_voucher_item ADD CONSTRAINT fk_cvid FOREIGN KEY (claim_voucher_id) REFERENCES claim_voucher(id);
ALTER TABLE deal_record ADD CONSTRAINT fk_cv_id FOREIGN KEY (claim_voucher_id) REFERENCES claim_voucher(id);
ALTER TABLE deal_record ADD CONSTRAINT fk_deal_id FOREIGN KEY (deal_id) REFERENCES employee(id);
ALTER TABLE log ADD CONSTRAINT fk_eid FOREIGN KEY (employee_id) REFERENCES employee(id);

SET FOREIGN_KEY_CHECKS=1;
