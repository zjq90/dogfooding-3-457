@echo off
chcp 65001
echo ======================================
echo 测试H2数据库连接
echo ======================================

echo 清理旧数据库...
if exist oa-employee-service\oa_db.mv.db del /q oa-employee-service\oa_db.mv.db
if exist oa-employee-service\oa_db.trace.db del /q oa-employee-service\oa_db.trace.db

echo 编译项目...
set MAVEN_OPTS=-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
call "C:\Program Files\JetBrains\IntelliJ IDEA 2020.3\plugins\maven\lib\maven3\bin\mvn.cmd" compile -DskipTests

echo.
echo ======================================
echo 编译完成！请在IDEA中按以下顺序启动服务：
echo ======================================
echo 1. 员工服务: EmployeeServiceApplication (端口8081)
echo 2. 报销服务: ClaimServiceApplication (端口8082)
echo 3. 认证服务: AuthServiceApplication (端口8080)
echo.
echo H2控制台: http://localhost:8081/h2-console
echo JDBC URL: jdbc:h2:file:./oa_db;AUTO_SERVER=TRUE
echo 用户名: sa
echo 密码: (空)
echo ======================================
pause
