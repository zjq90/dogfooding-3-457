@echo off
chcp 65001
echo ======================================
echo OA微服务启动脚本
echo ======================================
echo.

echo [1/4] 清理旧数据库...
if exist oa_db.mv.db del /q oa_db.mv.db
if exist oa_db.trace.db del /q oa_db.trace.db
echo 数据库清理完成!
echo.

echo [2/4] 编译项目...
call mvn clean package -DskipTests
if %errorlevel% neq 0 (
    echo 编译失败!
    pause
    exit /b 1
)
echo 编译完成!
echo.

echo [3/4] 启动员工服务 (端口: 8081)...
start "员工服务" cmd /k "cd oa-employee-service && java -jar target\oa-employee-service-1.0.0.jar"
timeout /t 15 /nobreak >nul

echo [4/4] 启动报销服务 (端口: 8082)...
start "报销服务" cmd /k "cd oa-claim-service && java -jar target\oa-claim-service-1.0.0.jar"
timeout /t 10 /nobreak >nul

echo [5/4] 启动认证服务 (端口: 8080)...
start "认证服务" cmd /k "cd oa-auth-service && java -jar target\oa-auth-service-1.0.0.jar"

echo.
echo ======================================
echo 所有服务启动中!
echo ======================================
echo 认证服务: http://localhost:8080
echo 员工服务: http://localhost:8081
echo 报销服务: http://localhost:8082
echo H2控制台: http://localhost:8081/h2-console
echo ======================================
pause
