# API 测试指南

## 服务地址

- **Employee Service**: http://localhost:8081
- **Claim Service**: http://localhost:8082
- **Auth Service**: http://localhost:8083

## 测试步骤

### 1. 登录获取 Token

**请求：**
```bash
POST http://localhost:8083/api/auth/login
Content-Type: application/json

{
  "id": "y1004",
  "password": "123456"
}
```

**预期响应：**
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "employee": {
      "id": "y1004",
      "name": "朱元璋",
      "departmentId": "10003",
      "post": "员工"
    }
  }
}
```

### 2. 测试员工服务

#### 2.1 获取所有员工

**请求：**
```bash
GET http://localhost:8081/api/employees
Authorization: Bearer {token}
```

**预期响应：**
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": "y1004",
      "name": "朱元璋",
      "department": {
        "id": "10003",
        "name": "研发部"
      },
      "post": "员工"
    }
  ]
}
```

#### 2.2 获取单个员工

**请求：**
```bash
GET http://localhost:8081/api/employees/y1004
Authorization: Bearer {token}
```

#### 2.3 获取所有部门

**请求：**
```bash
GET http://localhost:8081/api/departments
Authorization: Bearer {token}
```

**预期响应：**
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": "10001",
      "name": "总经理办公室",
      "address": "梦幻大厦c1201"
    },
    {
      "id": "10002",
      "name": "财务部",
      "address": "梦幻大厦a1103"
    },
    {
      "id": "10003",
      "name": "研发部",
      "address": "蔡氏大夏a7001"
    },
    {
      "id": "10004",
      "name": "销售部",
      "address": "永恒大夏b7005"
    }
  ]
}
```

#### 2.4 获取职位列表

**请求：**
```bash
GET http://localhost:8081/api/employees/posts
Authorization: Bearer {token}
```

**预期响应：**
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "posts": ["员工", "部门经理", "总经理", "财务"]
  }
}
```

### 3. 测试报销服务

#### 3.1 获取报销项类型

**请求：**
```bash
GET http://localhost:8082/api/claim-vouchers/items
Authorization: Bearer {token}
```

**预期响应：**
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "items": ["交通", "住宿", "饮食", "办公"]
  }
}
```

#### 3.2 获取自己的报销单

**请求：**
```bash
GET http://localhost:8082/api/claim-vouchers/self
Authorization: Bearer {token}
```

#### 3.3 获取待处理的报销单

**请求：**
```bash
GET http://localhost:8082/api/claim-vouchers/deal
Authorization: Bearer {token}
```

#### 3.4 获取报销单详情

**请求：**
```bash
GET http://localhost:8082/api/claim-vouchers/31
Authorization: Bearer {token}
```

**预期响应：**
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "claimVoucher": {
      "id": 31,
      "cause": "出差",
      "createId": "y1004",
      "createTime": "2019-09-21T17:08:16",
      "nextDealId": "c1002",
      "totalAmount": 780,
      "status": "已审核"
    },
    "items": [
      {
        "id": 72,
        "claimVoucherId": 31,
        "item": "交通",
        "amount": 780,
        "comment": "来回高铁票"
      }
    ],
    "records": [
      {
        "id": 17,
        "claimVoucherId": 31,
        "dealId": "y1004",
        "dealTime": "2019-09-21T17:08:16",
        "dealType": "创建",
        "dealResult": "新创建",
        "comment": "无"
      }
    ]
  }
}
```

#### 3.5 创建报销单

**请求：**
```bash
POST http://localhost:8082/api/claim-vouchers
Authorization: Bearer {token}
Content-Type: application/json

{
  "claimVoucher": {
    "cause": "出差报销",
    "totalAmount": 1500
  },
  "items": [
    {
      "item": "交通",
      "amount": 800,
      "comment": "高铁票"
    },
    {
      "item": "住宿",
      "amount": 700,
      "comment": "酒店费用"
    }
  ]
}
```

#### 3.6 提交报销单

**请求：**
```bash
POST http://localhost:8082/api/claim-vouchers/31/submit
Authorization: Bearer {token}
```

#### 3.7 处理报销单

**请求：**
```bash
POST http://localhost:8082/api/claim-vouchers/deal
Authorization: Bearer {token}
Content-Type: application/json

{
  "claimVoucherId": 31,
  "dealType": "通过",
  "comment": "同意报销"
}
```

### 4. 测试认证服务

#### 4.1 修改密码

**请求：**
```bash
POST http://localhost:8083/api/auth/change-password
Authorization: Bearer {token}
Content-Type: application/json

{
  "oldPassword": "123456",
  "newPassword": "654321",
  "confirmPassword": "654321"
}
```

#### 4.2 获取当前用户信息

**请求：**
```bash
GET http://localhost:8083/api/auth/info
Authorization: Bearer {token}
```

#### 4.3 获取操作日志

**请求：**
```bash
GET http://localhost:8083/api/logs
Authorization: Bearer {token}
```

**预期响应：**
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "id": 1,
      "employeeId": "y1004",
      "operationTime": "2019-09-22T16:51:13",
      "operation": "login"
    }
  ]
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

## H2 数据库控制台

- **Employee Service**: http://localhost:8081/h2-console
- **Claim Service**: http://localhost:8082/h2-console
- **Auth Service**: http://localhost:8083/h2-console

连接配置：
- JDBC URL: `jdbc:h2:mem:oa`
- 用户名: `sa`
- 密码: (留空)

## 注意事项

1. **Token 有效期**: JWT Token 默认有效期为 24 小时
2. **数据库**: 所有服务使用独立的 H2 内存数据库
3. **端口冲突**: 如果端口被占用，请修改对应服务的 `application.yml`
4. **跨域**: 服务已配置支持跨域请求
