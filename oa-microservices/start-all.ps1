#!/usr/bin/env pwsh
# OA System Microservices Startup Script

Write-Host "==========================================" -ForegroundColor Cyan
Write-Host "    OA System Microservices Startup" -ForegroundColor Cyan
Write-Host "==========================================" -ForegroundColor Cyan
Write-Host ""

# Create data directory
if (!(Test-Path "data")) {
    New-Item -ItemType Directory -Path "data" | Out-Null
}

# Function to start a service
function Start-ServiceProcess {
    param(
        [string]$Name,
        [string]$JarPath,
        [int]$Port
    )

    Write-Host "Starting $Name (Port: $Port)..." -ForegroundColor Green

    $proc = Start-Process -FilePath "java" -ArgumentList "-jar", $JarPath -WorkingDirectory (Get-Location) -PassThru -WindowStyle Hidden

    Start-Sleep -Seconds 3

    # Check if service is running
    try {
        $response = Invoke-WebRequest -Uri "http://localhost:$Port/actuator/health" -Method GET -ErrorAction SilentlyContinue -TimeoutSec 2
        if ($response.StatusCode -eq 200) {
            Write-Host "$Name started successfully!" -ForegroundColor Green
            return $true
        }
    } catch {
        # Service might not have actuator, try a simple connection
        try {
            $tcp = New-Object System.Net.Sockets.TcpClient
            $tcp.Connect("localhost", $Port)
            $tcp.Close()
            Write-Host "$Name started successfully!" -ForegroundColor Green
            return $true
        } catch {
            Write-Host "$Name may not be fully started yet, continuing..." -ForegroundColor Yellow
            return $true
        }
    }
    return $true
}

# Start services
$services = @(
    @{ Name = "Employee Service"; Jar = "employee-service\target\employee-service-1.0.0.jar"; Port = 8081 },
    @{ Name = "Claim Service"; Jar = "claim-service\target\claim-service-1.0.0.jar"; Port = 8082 },
    @{ Name = "Auth Service"; Jar = "auth-service\target\auth-service-1.0.0.jar"; Port = 8083 },
    @{ Name = "Gateway"; Jar = "gateway\target\gateway-1.0.0.jar"; Port = 8080 }
)

foreach ($service in $services) {
    Start-ServiceProcess -Name $service.Name -JarPath $service.Jar -Port $service.Port
    Start-Sleep -Seconds 2
}

Write-Host ""
Write-Host "==========================================" -ForegroundColor Cyan
Write-Host "All services started!" -ForegroundColor Green
Write-Host ""
Write-Host "Gateway:        http://localhost:8080" -ForegroundColor Yellow
Write-Host "Employee:       http://localhost:8081" -ForegroundColor Yellow
Write-Host "Claim:          http://localhost:8082" -ForegroundColor Yellow
Write-Host "Auth:           http://localhost:8083" -ForegroundColor Yellow
Write-Host "H2 Console:     http://localhost:8080/h2-console" -ForegroundColor Yellow
Write-Host "==========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Press any key to stop all services..." -ForegroundColor Magenta
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")

# Stop all Java processes
Get-Process -Name "java" -ErrorAction SilentlyContinue | Stop-Process -Force
Write-Host "All services stopped." -ForegroundColor Green
