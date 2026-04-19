# OA微服务启动脚本
# 使用说明：
# 1. 进入 oa-microservices 目录：cd oa-microservices
# 2. 先编译整个项目：mvn clean install -DskipTests
# 3. 按顺序启动以下服务：

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "OA 微服务启动脚本" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "目录结构：" -ForegroundColor Yellow
Write-Host "  seed/"
Write-Host "  ├── oasestemApi/          (原单体项目)"
Write-Host "  └── oa-microservices/     (微服务重构 - 本目录)"
Write-Host "      ├── pom.xml           (父工程POM)"
Write-Host "      ├── oa-common/        (公共模块)"
Write-Host "      ├── oa-employee-service/"
Write-Host "      ├── oa-claim-service/"
Write-Host "      └── oa-auth-service/"
Write-Host ""
Write-Host "服务列表：" -ForegroundColor Yellow
Write-Host "  1. 员工服务 (oa-employee-service) - 端口 8081"
Write-Host "     - 员工管理 /api/employees"
Write-Host "     - 部门管理 /api/departments"
Write-Host ""
Write-Host "  2. 报销服务 (oa-claim-service) - 端口 8082"
Write-Host "     - 报销单管理 /api/claim-vouchers"
Write-Host ""
Write-Host "  3. 认证服务 (oa-auth-service) - 端口 8083"
Write-Host "     - 认证 /api/auth"
Write-Host "     - 日志 /api/logs"
Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "启动命令（请先 cd 到 oa-microservices 目录）：" -ForegroundColor Yellow
Write-Host ""
Write-Host "  # 编译整个项目"
Write-Host "  mvn clean install -DskipTests"
Write-Host ""
Write-Host "  # 1. 启动员工服务（优先启动，初始化数据库）"
Write-Host "  cd oa-employee-service; mvn spring-boot:run"
Write-Host ""
Write-Host "  # 2. 启动报销服务（新开终端）"
Write-Host "  cd oa-claim-service; mvn spring-boot:run"
Write-Host ""
Write-Host "  # 3. 启动认证服务（新开终端）"
Write-Host "  cd oa-auth-service; mvn spring-boot:run"
Write-Host ""
Write-Host "测试账号：y1004 / 123456" -ForegroundColor Green
Write-Host "H2控制台：http://localhost:8081/h2-console" -ForegroundColor Green
Write-Host "JDBC URL：jdbc:h2:tcp://localhost/./oa-db" -ForegroundColor Green
