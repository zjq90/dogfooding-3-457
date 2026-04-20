# OA微服务架构系统

## 项目结构

```
oa-microservices/
├── oa-common/              # 公共模块
│   ├── entity/            # 实体类
│   ├── dto/               # DTO数据传输对象
│   ├── common/            # 通用工具类
│   └── config/            # 公共配置
├── oa-auth-service/       # 认证服务 (端口: 8080)
│   ├── 登录认证
│   ├── 修改密码
│   └── 获取用户信息
├── oa-employee-service/   # 员工服务 (端口: 8081)
│   ├── 员工管理
│   └── 部门管理
└── oa-claim-service/      # 报销服务 (端口: 8082)
    ├── 报销单管理
    ├── 审核流程
    └── 操作日志
```

## 服务端口

| 服务 | 端口 | 说明 |
|------|------|------|
| 认证服务 | 8080 | /api/auth/** |
| 员工服务 | 8081 | /api/employees/**, /api/departments/** |
| 报销服务 | 8082 | /api/claim-vouchers/**, /api/logs/** |

## 快速启动

```bash
# Windows
start-all.bat

# 或者分别启动
# 1. 编译
mvn clean package -DskipTests

# 2. 启动员工服务 (优先启动，初始化数据库)
cd oa-employee-service
java -jar target/oa-employee-service-1.0.0.jar

# 3. 启动报销服务
cd oa-claim-service
java -jar target/oa-claim-service-1.0.0.jar

# 4. 启动认证服务
cd oa-auth-service
java -jar target/oa-auth-service-1.0.0.jar
```

## 测试账号

| 工号 | 密码 | 职位 |
|------|------|------|
| z1001 | 123456 | 总经理 |
| y1003 | 123456 | 部门经理 |
| c1002 | 123456 | 财务 |
| y1004 | 123456 | 员工 |

## 数据库

所有服务共用一个H2文件数据库，使用AUTO_SERVER模式支持多连接。
