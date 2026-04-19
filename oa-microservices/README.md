# OA System Microservices

OA系统微服务架构重构项目

## 项目结构

```
oa-microservices/
├── common/                 # 公共模块（工具类、常量、JWT等）
├── employee-service/       # 员工服务（端口：8081）
│   ├── 员工管理模块
│   └── 部门管理模块
├── claim-service/          # 报销服务（端口：8082）
│   └── 报销单模块
├── auth-service/           # 认证服务（端口：8083）
│   ├── 认证模块
│   └── 日志模块
└── gateway/                # 网关服务（端口：8080）
```

## 技术栈

- Spring Boot 2.7.18
- MyBatis-Plus 3.5.3.1
- H2 Database（文件模式，共享数据）
- JWT 认证
- Spring Cloud Gateway

## 启动方式

### 方式一：使用启动脚本（推荐）

```bash
start-services.bat
```

### 方式二：手动启动

1. 编译项目
```bash
mvn clean install -DskipTests
```

2. 启动各个服务（每个服务单独终端）
```bash
# 员工服务
cd employee-service
mvn spring-boot:run

# 报销服务
cd claim-service
mvn spring-boot:run

# 认证服务
cd auth-service
mvn spring-boot:run

# 网关
cd gateway
mvn spring-boot:run
```

## 服务端口

| 服务 | 端口 |
|------|------|
| Gateway | 8080 |
| Employee Service | 8081 |
| Claim Service | 8082 |
| Auth Service | 8083 |

## API 接口

### 认证服务 (Auth Service)
- POST `/api/auth/login` - 登录
- POST `/api/auth/change-password` - 修改密码
- GET `/api/auth/info` - 获取当前用户信息
- GET `/api/logs` - 获取日志列表
- DELETE `/api/logs/{id}` - 删除日志

### 员工服务 (Employee Service)
- GET `/api/employees` - 获取员工列表
- GET `/api/employees/{id}` - 获取员工详情
- POST `/api/employees` - 创建员工
- PUT `/api/employees` - 更新员工
- DELETE `/api/employees/{id}` - 删除员工
- GET `/api/employees/posts` - 获取职位列表
- GET `/api/departments` - 获取部门列表
- GET `/api/departments/{id}` - 获取部门详情
- POST `/api/departments` - 创建部门
- PUT `/api/departments` - 更新部门
- DELETE `/api/departments/{id}` - 删除部门

### 报销服务 (Claim Service)
- GET `/api/claim-vouchers/items` - 获取报销项目列表
- POST `/api/claim-vouchers` - 创建报销单
- PUT `/api/claim-vouchers` - 更新报销单
- GET `/api/claim-vouchers/{id}` - 获取报销单详情
- GET `/api/claim-vouchers/self` - 获取自己的报销单
- GET `/api/claim-vouchers/deal` - 获取待处理的报销单
- POST `/api/claim-vouchers/{id}/submit` - 提交报销单
- POST `/api/claim-vouchers/deal` - 处理报销单

## 数据库

使用H2文件数据库，所有服务共享同一数据库文件（`./data/oa`）

H2 Console访问地址：http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:file:./data/oa`
- Username: `sa`
- Password: (空)

## 测试账号

| 工号 | 密码 | 姓名 | 职位 | 部门 |
|------|------|------|------|------|
| z1001 | 123456 | 李世民 | 总经理 | 总经理办公室 |
| c1002 | 123456 | 赵匡胤 | 财务 | 财务部 |
| y1003 | 123456 | 忽必烈 | 部门经理 | 研发部 |
| y1004 | 123456 | 朱元璋 | 员工 | 研发部 |
| x1005 | 123456 | 爱新觉罗.福临 | 部门经理 | 销售部 |
