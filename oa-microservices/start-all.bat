@echo off
echo Starting OA Microservices...
echo.

echo Building all services...
call mvnw.cmd clean install -DskipTests
if errorlevel 1 (
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo Starting Employee Service on port 8081...
start "OA Employee Service" cmd /c "cd /d %~dp0oa-employee-service && ..\mvnw.cmd spring-boot:run"

timeout /t 10 /nobreak > nul

echo Starting Claim Service on port 8082...
start "OA Claim Service" cmd /c "cd /d %~dp0oa-claim-service && ..\mvnw.cmd spring-boot:run"

timeout /t 10 /nobreak > nul

echo Starting Auth Service on port 8083...
start "OA Auth Service" cmd /c "cd /d %~dp0oa-auth-service && ..\mvnw.cmd spring-boot:run"

echo.
echo All services are starting...
echo Employee Service: http://localhost:8081
echo Claim Service: http://localhost:8082
echo Auth Service: http://localhost:8083
echo.
echo H2 Console: http://localhost:8081/h2-console (JDBC URL: jdbc:h2:mem:oa)
echo.
pause
