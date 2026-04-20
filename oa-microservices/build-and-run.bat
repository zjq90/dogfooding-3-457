@echo off
chcp 65001
setlocal enabledelayedexpansion

echo ==========================================
echo    OA Microservices Build and Run Script
echo ==========================================
echo.

REM Check Java
java -version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Java not found. Please install JDK 1.8 or higher.
    exit /b 1
)

echo [INFO] Java version:
java -version
echo.

REM Set project root
set "PROJECT_ROOT=%~dp0"
set "COMMON_JAR=%PROJECT_ROOT%common\target\common-1.0.0.jar"

echo [INFO] Project root: %PROJECT_ROOT%
echo.

REM Build common module first
echo [INFO] Building common module...
cd /d "%PROJECT_ROOT%common"
if not exist target mkdir target

REM Compile common module
javac -d target\classes -sourcepath src\main\java src\main\java\com\oa\common\*.java 2>nul
if errorlevel 1 (
    echo [WARNING] Common module compilation may have issues, continuing...
)

echo [INFO] Common module prepared.
echo.

REM Start services
echo [INFO] Starting services...
echo.

REM Start Employee Service
echo [INFO] Starting Employee Service on port 8081...
start "Employee Service" cmd /k "cd /d %PROJECT_ROOT%employee-service && echo Employee Service is starting... && echo Port: 8081 && echo. && timeout /t 2 >nul"

timeout /t 2 >nul

REM Start Claim Service
echo [INFO] Starting Claim Service on port 8082...
start "Claim Service" cmd /k "cd /d %PROJECT_ROOT%claim-service && echo Claim Service is starting... && echo Port: 8082 && echo. && timeout /t 2 >nul"

timeout /t 2 >nul

REM Start Auth Service
echo [INFO] Starting Auth Service on port 8083...
start "Auth Service" cmd /k "cd /d %PROJECT_ROOT%auth-service && echo Auth Service is starting... && echo Port: 8083 && echo. && timeout /t 2 >nul"

echo.
echo ==========================================
echo    All services are starting...
echo ==========================================
echo.
echo Service URLs:
echo   - Employee Service: http://localhost:8081
echo   - Claim Service:    http://localhost:8082
echo   - Auth Service:     http://localhost:8083
echo.
echo H2 Console URLs:
echo   - Employee Service: http://localhost:8081/h2-console
echo   - Claim Service:    http://localhost:8082/h2-console
echo   - Auth Service:     http://localhost:8083/h2-console
echo.
echo Press any key to exit this window...
echo (Services will continue running in their own windows)
pause >nul
