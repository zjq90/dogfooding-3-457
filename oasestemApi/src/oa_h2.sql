/*
H2 Database Data Transfer
Source: MySQL oa.sql
Converted for H2 Database
Date: 2026-04-17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS department;
CREATE TABLE department (
  id CHAR(10) NOT NULL COMMENT '部门编号',
  name VARCHAR(20) DEFAULT NULL COMMENT '部门名称',
  address VARCHAR(100) DEFAULT NULL COMMENT '部门地址',
  PRIMARY KEY (id)
);

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO department VALUES ('10001', '总经理办公室', '梦幻大厦c1201');
INSERT INTO department VALUES ('10002', '财务部', '梦幻大厦a1103');
INSERT INTO department VALUES ('10003', '研发部', '蔡氏大夏a7001');
INSERT INTO department VALUES ('10004', '销售部', '永恒大夏b7005');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  id CHAR(10) NOT NULL COMMENT '员工编号',
  password VARCHAR(20) DEFAULT NULL COMMENT '密码',
  name VARCHAR(20) DEFAULT NULL COMMENT '员工姓名',
  department_id CHAR(10) DEFAULT NULL COMMENT '所属部门',
  post VARCHAR(20) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (id)
);

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO employee VALUES ('c1002', '123456', '赵匡胤', '10002', '财务');
INSERT INTO employee VALUES ('x1005', '123456', '爱新觉罗.福临', '10004', '部门经理');
INSERT INTO employee VALUES ('y1003', '123456', '忽必烈', '10003', '部门经理');
INSERT INTO employee VALUES ('y1004', '123456', '朱元璋', '10003', '员工');
INSERT INTO employee VALUES ('z1001', '123456', '李世民', '10001', '总经理');

-- ----------------------------
-- Table structure for claim_voucher
-- ----------------------------
DROP TABLE IF EXISTS claim_voucher;
CREATE TABLE claim_voucher (
  id INT NOT NULL AUTO_INCREMENT COMMENT '报销单编号',
  cause VARCHAR(100) DEFAULT NULL COMMENT '报销原由',
  create_id CHAR(10) DEFAULT NULL COMMENT '创建人',
  create_time TIMESTAMP DEFAULT NULL COMMENT '创建时间',
  next_deal_id CHAR(10) DEFAULT NULL COMMENT '待处理人',
  total_amount DOUBLE DEFAULT NULL COMMENT '总金额',
  status VARCHAR(20) DEFAULT NULL COMMENT '处理状态',
  PRIMARY KEY (id)
);

-- ----------------------------
-- Records of claim_voucher
-- ----------------------------
INSERT INTO claim_voucher VALUES (31, '出差', 'y1004', '2019-09-21 17:08:16', 'c1002', 780, '已审核');

-- ----------------------------
-- Table structure for claim_voucher_item
-- ----------------------------
DROP TABLE IF EXISTS claim_voucher_item;
CREATE TABLE claim_voucher_item (
  id INT NOT NULL AUTO_INCREMENT COMMENT '编号',
  claim_voucher_id INT DEFAULT NULL COMMENT '报销单',
  item VARCHAR(20) DEFAULT NULL COMMENT '费用类型',
  amount DOUBLE DEFAULT NULL COMMENT '金额',
  comment VARCHAR(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (id)
);

-- ----------------------------
-- Records of claim_voucher_item
-- ----------------------------
INSERT INTO claim_voucher_item VALUES (72, 31, '交通', 780, '来回高铁票');

-- ----------------------------
-- Table structure for deal_record
-- ----------------------------
DROP TABLE IF EXISTS deal_record;
CREATE TABLE deal_record (
  id INT NOT NULL AUTO_INCREMENT COMMENT '报销单处理编号',
  claim_voucher_id INT DEFAULT NULL COMMENT '报销单',
  deal_id CHAR(10) DEFAULT NULL COMMENT '处理人',
  deal_time TIMESTAMP DEFAULT NULL COMMENT '处理时间',
  deal_type VARCHAR(20) DEFAULT NULL COMMENT '处理类型',
  deal_result VARCHAR(20) DEFAULT NULL COMMENT '处理结果',
  comment VARCHAR(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (id)
);

-- ----------------------------
-- Records of deal_record
-- ----------------------------
INSERT INTO deal_record VALUES (17, 31, 'y1004', '2019-09-21 17:08:16', '创建', '新创建', '无');
INSERT INTO deal_record VALUES (18, 31, 'y1004', '2019-09-21 17:22:45', '提交', '已提交', '无');
INSERT INTO deal_record VALUES (19, 31, 'y1003', '2019-09-21 17:23:39', '打回', '已打回', '');
INSERT INTO deal_record VALUES (20, 31, 'y1004', '2019-09-21 17:24:00', '提交', '已提交', '无');
INSERT INTO deal_record VALUES (21, 31, 'y1003', '2019-09-21 17:24:17', '通过', '已审核', '允许报销');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS log;
CREATE TABLE log (
  id INT NOT NULL AUTO_INCREMENT,
  employee_id CHAR(10) NOT NULL,
  operation_time TIMESTAMP DEFAULT NULL,
  operation VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO log VALUES (1, 'y1004', '2019-09-22 16:51:13', 'login');
INSERT INTO log VALUES (2, 'y1003', '2019-09-22 16:59:29', 'login');

-- ----------------------------
-- Add Foreign Keys
-- ----------------------------
ALTER TABLE employee ADD CONSTRAINT fk_did FOREIGN KEY (department_id) REFERENCES department(id);
ALTER TABLE claim_voucher ADD CONSTRAINT fk_cid FOREIGN KEY (create_id) REFERENCES employee(id);
ALTER TABLE claim_voucher ADD CONSTRAINT fk_ndid FOREIGN KEY (next_deal_id) REFERENCES employee(id);
ALTER TABLE claim_voucher_item ADD CONSTRAINT fk_cvid FOREIGN KEY (claim_voucher_id) REFERENCES claim_voucher(id);
ALTER TABLE deal_record ADD CONSTRAINT fk_cv_id FOREIGN KEY (claim_voucher_id) REFERENCES claim_voucher(id);
ALTER TABLE deal_record ADD CONSTRAINT fk_deal_id FOREIGN KEY (deal_id) REFERENCES employee(id);
ALTER TABLE log ADD CONSTRAINT fk_eid FOREIGN KEY (employee_id) REFERENCES employee(id);

SET FOREIGN_KEY_CHECKS=1;
