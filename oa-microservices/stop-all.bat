@echo off
echo Stopping OA Microservices...
taskkill /FI "WINDOWTITLE eq OA Employee Service*" /F
taskkill /FI "WINDOWTITLE eq OA Claim Service*" /F
taskkill /FI "WINDOWTITLE eq OA Auth Service*" /F
echo All services stopped.
pause
