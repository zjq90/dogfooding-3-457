@echo off
chcp 65001 >nul
echo ==========================================
echo    OA System Microservices Startup
echo ==========================================
echo.

:: Create data directory if not exists
if not exist "data" mkdir data

:: Build the project
echo Building project...
call mvn clean install -DskipTests
if errorlevel 1 (
    echo Build failed!
    pause
    exit /b 1
)
echo Build successful!
echo.

:: Start Employee Service
echo Starting Employee Service (Port: 8081)...
start "Employee Service" cmd /c "cd employee-service && mvn spring-boot:run"
timeout /t 5 /nobreak >nul

:: Start Claim Service
echo Starting Claim Service (Port: 8082)...
start "Claim Service" cmd /c "cd claim-service && mvn spring-boot:run"
timeout /t 5 /nobreak >nul

:: Start Auth Service
echo Starting Auth Service (Port: 8083)...
start "Auth Service" cmd /c "cd auth-service && mvn spring-boot:run"
timeout /t 5 /nobreak >nul

:: Start Gateway
echo Starting Gateway (Port: 8080)...
start "Gateway" cmd /c "cd gateway && mvn spring-boot:run"
timeout /t 3 /nobreak >nul

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
pause
