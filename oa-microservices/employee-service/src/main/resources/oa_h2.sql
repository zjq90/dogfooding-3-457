/*
H2 Database Schema for Employee Service
*/

SET FOREIGN_KEY_CHECKS=0;

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

ALTER TABLE employee ADD CONSTRAINT fk_did FOREIGN KEY (department_id) REFERENCES department(id);
