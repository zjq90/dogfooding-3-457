@echo off
chcp 65001 >nul
echo ==========================================
echo    OA System Microservices Startup
echo ==========================================
echo.

:: Create data directory if not exists
if not exist "data" mkdir data

:: Set Maven path
set MAVEN="C:\Program Files\JetBrains\IntelliJ IDEA 2020.3\plugins\maven\lib\maven3\bin\mvn.cmd"

echo Starting services in separate windows...
echo.

:: Start Employee Service
echo [1/4] Starting Employee Service (Port: 8081)...
start "Employee Service - Port 8081" cmd /k "cd employee-service && %MAVEN% spring-boot:run -o"
timeout /t 8 /nobreak >nul

:: Start Claim Service
echo [2/4] Starting Claim Service (Port: 8082)...
start "Claim Service - Port 8082" cmd /k "cd claim-service && %MAVEN% spring-boot:run -o"
timeout /t 8 /nobreak >nul

:: Start Auth Service
echo [3/4] Starting Auth Service (Port: 8083)...
start "Auth Service - Port 8083" cmd /k "cd auth-service && %MAVEN% spring-boot:run -o"
timeout /t 8 /nobreak >nul

:: Start Gateway
echo [4/4] Starting Gateway (Port: 8080)...
start "Gateway - Port 8080" cmd /k "cd gateway && %MAVEN% spring-boot:run -o"
timeout /t 5 /nobreak >nul

echo.
echo ==========================================
echo All services started!
echo.
echo Gateway:        http://localhost:8080
echo Employee:       http://localhost:8081
echo Claim:          http://localhost:8082
echo Auth:           http://localhost:8083
echo H2 Console:     http://localhost:8080/h2-console
echo ==========================================
echo.
echo Close the service windows to stop the services.
echo.
pause
