# IntelliJ IDEA 项目配置指南

## 导入项目

1. 打开 IntelliJ IDEA
2. 选择 `File` -> `Open`
3. 选择 `oa-microservices` 文件夹
4. 等待 IDEA 自动导入 Maven 项目

## 配置模块

确保以下模块已正确识别：
- `common` - 公共模块
- `employee-service` - 员工服务
- `claim-service` - 报销服务
- `auth-service` - 认证服务

## 启动服务

### 方式1: 使用 IDEA 的 Run Configuration

1. **启动 Employee Service (员工服务)**
   - 打开 `employee-service/src/main/java/com/oa/employee/EmployeeServiceApplication.java`
   - 右键点击类名，选择 `Run 'EmployeeServiceApplication'`
   - 服务将在端口 8081 启动

2. **启动 Claim Service (报销服务)**
   - 打开 `claim-service/src/main/java/com/oa/claim/ClaimServiceApplication.java`
   - 右键点击类名，选择 `Run 'ClaimServiceApplication'`
   - 服务将在端口 8082 启动

3. **启动 Auth Service (认证服务)**
   - 打开 `auth-service/src/main/java/com/oa/auth/AuthServiceApplication.java`
   - 右键点击类名，选择 `Run 'AuthServiceApplication'`
   - 服务将在端口 8083 启动

### 方式2: 使用 Maven 插件

1. 打开 IDEA 右侧的 Maven 工具窗口
2. 展开 `oa-microservices` -> `Lifecycle`
3. 先执行 `clean`，然后执行 `install`
4. 分别进入各个服务的 `Plugins` -> `spring-boot` -> `spring-boot:run`

### 方式3: 构建 JAR 后运行

```bash
# 在项目根目录执行
mvn clean install -DskipTests

# 分别运行各个服务
cd employee-service/target
java -jar employee-service-1.0.0.jar

cd claim-service/target
java -jar claim-service-1.0.0.jar

cd auth-service/target
java -jar auth-service-1.0.0.jar
```

## 验证服务启动

服务启动后，可以通过以下地址验证：

- **Employee Service**: http://localhost:8081/api/employees
- **Claim Service**: http://localhost:8082/api/claim-vouchers
- **Auth Service**: http://localhost:8083/api/auth/login

## H2 数据库控制台

- **Employee Service**: http://localhost:8081/h2-console
- **Claim Service**: http://localhost:8082/h2-console
- **Auth Service**: http://localhost:8083/h2-console

连接配置：
- JDBC URL: `jdbc:h2:mem:oa`
- 用户名: `sa`
- 密码: (留空)

## 端口占用检查

如果端口被占用，可以修改 `application.yml` 中的端口配置：

```yaml
server:
  port: 8081  # 修改为其他端口
```

## 常见问题

### 1. Maven 依赖下载失败
- 检查网络连接
- 检查 Maven 配置（settings.xml）
- 尝试更换 Maven 镜像源

### 2. 编译错误
- 确保 JDK 版本为 1.8
- 执行 `mvn clean` 后重新编译
- 检查 Lombok 插件是否安装

### 3. 数据库连接失败
- 检查 H2 依赖是否正确引入
- 检查 SQL 初始化脚本路径
- 查看控制台错误日志

### 4. 端口冲突
- 修改对应服务的 `application.yml` 中的端口
- 检查是否有其他程序占用端口

## 测试 API

### 1. 登录获取 Token
```bash
POST http://localhost:8083/api/auth/login
Content-Type: application/json

{
  "id": "y1004",
  "password": "123456"
}
```

### 2. 使用 Token 访问受保护接口
```bash
GET http://localhost:8081/api/employees
Authorization: Bearer {token}
```

## 项目结构说明

```
oa-microservices/
├── common/                    # 公共模块
│   ├── Result.java           # 统一响应结果
│   ├── Constants.java        # 常量定义
│   └── JwtUtil.java          # JWT工具类
├── employee-service/          # 员工服务 (8081)
│   ├── entity/               # 实体类
│   ├── mapper/               # 数据访问层
│   ├── service/              # 业务逻辑层
│   ├── controller/           # 控制器层
│   └── resources/            # 配置文件
├── claim-service/             # 报销服务 (8082)
│   └── ...
└── auth-service/              # 认证服务 (8083)
    └── ...
```
