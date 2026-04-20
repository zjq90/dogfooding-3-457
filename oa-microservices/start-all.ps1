# OA Microservices Startup Script
# This script starts all microservices

Write-Host "Starting OA Microservices..." -ForegroundColor Green

# Function to start a service
function Start-Service {
    param(
        [string]$ServiceName,
        [string]$ServicePath,
        [int]$Port
    )

    Write-Host "Starting $ServiceName on port $Port..." -ForegroundColor Yellow

    # Change to the service directory and start it
    $job = Start-Job -ScriptBlock {
        param($path, $name)
        Set-Location $path
        & mvn spring-boot:run -q
    } -ArgumentList $ServicePath, $ServiceName

    return $job
}

# Get the root directory
$rootDir = Split-Path -Parent $MyInvocation.MyCommand.Path

# Start Employee Service (Port 8081)
$employeeServiceJob = Start-Service -ServiceName "Employee Service" -ServicePath "$rootDir\employee-service" -Port 8081
Start-Sleep -Seconds 5

# Start Claim Service (Port 8082)
$claimServiceJob = Start-Service -ServiceName "Claim Service" -ServicePath "$rootDir\claim-service" -Port 8082
Start-Sleep -Seconds 5

# Start Auth Service (Port 8083)
$authServiceJob = Start-Service -ServiceName "Auth Service" -ServicePath "$rootDir\auth-service" -Port 8083

Write-Host "`nAll services are starting..." -ForegroundColor Green
Write-Host "Employee Service: http://localhost:8081" -ForegroundColor Cyan
Write-Host "Claim Service: http://localhost:8082" -ForegroundColor Cyan
Write-Host "Auth Service: http://localhost:8083" -ForegroundColor Cyan
Write-Host "`nPress Ctrl+C to stop all services" -ForegroundColor Red

# Keep the script running
while ($true) {
    Start-Sleep -Seconds 1
}
