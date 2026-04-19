@echo off
echo ========================================
echo OA Microservices - Starting All Services
echo ========================================
echo.

echo [1/4] Starting Employee Service on port 8081...
start "OA Employee Service" cmd /c "cd /d %~dp0oa-employee-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo [2/4] Starting Claim Service on port 8082...
start "OA Claim Service" cmd /c "cd /d %~dp0oa-claim-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo [3/4] Starting Auth Service on port 8083...
start "OA Auth Service" cmd /c "cd /d %~dp0oa-auth-service && mvn spring-boot:run"
timeout /t 5 /nobreak > nul

echo [4/4] Starting Gateway Service on port 8080...
start "OA Gateway" cmd /c "cd /d %~dp0oa-gateway && mvn spring-boot:run"

echo.
echo ========================================
echo All services are starting...
echo ========================================
echo.
echo Services:
echo   - Gateway:       http://localhost:8080
echo   - Employee:      http://localhost:8081
echo   - Claim:         http://localhost:8082
echo   - Auth:          http://localhost:8083
echo.
echo API Endpoints (via Gateway):
echo   - Login:         POST http://localhost:8080/api/auth/login
echo   - Employees:     GET  http://localhost:8080/api/employee/list
echo   - Departments:   GET  http://localhost:8080/api/department/list
echo   - Claims:        GET  http://localhost:8080/api/claim/self/{employeeId}
echo.
echo Press any key to exit this window (services will continue running)
pause > nul
