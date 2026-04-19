@echo off
echo ========================================
echo OA Microservices - Stopping All Services
echo ========================================
echo.

echo Stopping Employee Service...
taskkill /FI "WINDOWTITLE eq OA Employee Service*" /F 2>nul

echo Stopping Claim Service...
taskkill /FI "WINDOWTITLE eq OA Claim Service*" /F 2>nul

echo Stopping Auth Service...
taskkill /FI "WINDOWTITLE eq OA Auth Service*" /F 2>nul

echo Stopping Gateway...
taskkill /FI "WINDOWTITLE eq OA Gateway*" /F 2>nul

echo.
echo All services stopped.
pause
