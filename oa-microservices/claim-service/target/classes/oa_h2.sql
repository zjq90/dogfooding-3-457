/*
H2 Database Schema for Claim Service
*/

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS deal_record;
DROP TABLE IF EXISTS claim_voucher_item;
DROP TABLE IF EXISTS claim_voucher;

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
