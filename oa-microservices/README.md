# OA 微服务架构

## 项目结构

```
oa-microservices/
├── common/                  # 公共模块
│   ├── Result.java         # 统一响应结果
│   ├── Constants.java      # 常量定义
│   └── JwtUtil.java        # JWT工具类
├── employee-service/        # 员工服务 (端口: 8081)
│   ├── 员工管理模块
│   └── 部门管理模块
├── claim-service/           # 报销服务 (端口: 8082)
│   └── 报销单模块
└── auth-service/            # 认证服务 (端口: 8083)
    ├── 认证模块
    └── 日志模块
```

## 服务说明

### 1. 员工服务 (employee-service)
- **端口**: 8081
- **功能**:
  - 员工CRUD操作
  - 部门CRUD操作
  - 职位管理
- **API端点**:
  - `GET /api/employees` - 获取所有员工
  - `GET /api/employees/{id}` - 获取员工详情
  - `POST /api/employees` - 创建员工
  - `PUT /api/employees` - 更新员工
  - `DELETE /api/employees/{id}` - 删除员工
  - `GET /api/employees/posts` - 获取职位列表
  - `GET /api/departments` - 获取所有部门
  - `GET /api/departments/{id}` - 获取部门详情
  - `POST /api/departments` - 创建部门
  - `PUT /api/departments` - 更新部门
  - `DELETE /api/departments/{id}` - 删除部门

### 2. 报销服务 (claim-service)
- **端口**: 8082
- **功能**:
  - 报销单管理
  - 报销项管理
  - 处理记录管理
- **API端点**:
  - `GET /api/claim-vouchers/items` - 获取报销项类型
  - `GET /api/claim-vouchers` - 获取所有报销单
  - `GET /api/claim-vouchers/{id}` - 获取报销单详情
  - `POST /api/claim-vouchers` - 创建报销单
  - `PUT /api/claim-vouchers` - 更新报销单
  - `GET /api/claim-vouchers/self` - 获取自己的报销单
  - `GET /api/claim-vouchers/deal` - 获取待处理的报销单
  - `POST /api/claim-vouchers/{id}/submit` - 提交报销单
  - `POST /api/claim-vouchers/deal` - 处理报销单

### 3. 认证服务 (auth-service)
- **端口**: 8083
- **功能**:
  - 用户登录认证
  - 密码修改
  - 操作日志管理
- **API端点**:
  - `POST /api/auth/login` - 用户登录
  - `POST /api/auth/change-password` - 修改密码
  - `GET /api/auth/info` - 获取当前用户信息
  - `GET /api/logs` - 获取操作日志
  - `DELETE /api/logs/{id}` - 删除日志

## 启动方式

### 方式1: 使用PowerShell脚本一键启动
```powershell
.\start-all.ps1
```

### 方式2: 分别启动各个服务
```bash
# 编译公共模块
cd common
mvn clean install

# 启动员工服务
cd ../employee-service
mvn spring-boot:run

# 启动报销服务
cd ../claim-service
mvn spring-boot:run

# 启动认证服务
cd ../auth-service
mvn spring-boot:run
```

### 方式3: 使用Maven构建后启动
```bash
# 在根目录构建所有模块
mvn clean install

# 分别启动各个服务
cd employee-service/target
java -jar employee-service-1.0.0.jar

cd claim-service/target
java -jar claim-service-1.0.0.jar

cd auth-service/target
java -jar auth-service-1.0.0.jar
```

## 数据库

所有服务使用统一的H2内存数据库，数据库配置：
- **URL**: jdbc:h2:mem:oa
- **用户名**: sa
- **密码**: (空)
- **H2 Console**: http://localhost:{port}/h2-console

## 认证方式

所有受保护的API都需要在请求头中携带JWT Token：
```
Authorization: Bearer {token}
```

Token通过登录接口获取：
```bash
POST http://localhost:8083/api/auth/login
Content-Type: application/json

{
  "id": "y1004",
  "password": "123456"
}
```

## 测试账号

| 工号 | 姓名 | 部门 | 职位 | 密码 |
|------|------|------|------|------|
| z1001 | 李世民 | 总经理办公室 | 总经理 | 123456 |
| c1002 | 赵匡胤 | 财务部 | 财务 | 123456 |
| y1003 | 忽必烈 | 研发部 | 部门经理 | 123456 |
| y1004 | 朱元璋 | 研发部 | 员工 | 123456 |
| x1005 | 爱新觉罗.福临 | 销售部 | 部门经理 | 123456 |
