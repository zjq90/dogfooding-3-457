$ErrorActionPreference = "Stop"

Write-Host "======================================" -ForegroundColor Cyan
Write-Host "OA微服务启动脚本" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

$mavenRepo = "C:\Users\20807\.m2\repository"

$cp = @()
$cp += "$PSScriptRoot + "\oa-common\target\classes"
$cp += "$PSScriptRoot + "\oa-employee-service\target\classes"
$cp += "$mavenRepo + "\org\springframework\boot\spring-boot-starter-web\2.7.18\*"
$cp += "$mavenRepo + "\org\springframework\boot\spring-boot-starter-aop\2.7.18\*"
$cp += "$mavenRepo + "\org\springframework\boot\spring-boot-starter-validation\2.7.18\*"
$cp += "$mavenRepo + "\org\springframework\boot\spring-boot-starter-jdbc\2.7.18\*"
$cp += "$mavenRepo + "\com\baomidou\mybatis-plus-boot-starter\3.5.3.1\*"
$cp += "$mavenRepo + "\com\h2database\h2\2.1.214\*"
$cp += "$mavenRepo + "\org\projectlombok\lombok\1.18.30\*"
$cp += "$mavenRepo + "\cn\hutool\hutool-all\5.8.18\*"
$cp += "$mavenRepo + "\org\springframework\boot\spring-boot\2.7.18\*"
$cp += "$mavenRepo + "\org\springframework\spring-context\5.3.31\*"
$cp += "$mavenRepo + "\com\zaxxer\HikariCP\4.0.3\*"

$classpath = $cp -join ";"

Write-Host "启动员工服务 (端口: 8081)..." -ForegroundColor Green
Start-Process -FilePath "java" -ArgumentList "-cp", $classpath, "com.oa.employee.EmployeeServiceApplication" -WorkingDirectory "$PSScriptRoot\oa-employee-service" -WindowStyle Normal
Start-Sleep -Seconds 20

Write-Host "启动报销服务 (端口: 8082)..." -ForegroundColor Green
$cp2 = @()
$cp2 += "$PSScriptRoot + "\oa-common\target\classes"
$cp2 += "$PSScriptRoot + "\oa-claim-service\target\classes"
$classpath2 = $cp2 -join ";"
Start-Process -FilePath "java" -ArgumentList "-cp", $classpath2, "com.oa.claim.ClaimServiceApplication" -WorkingDirectory "$PSScriptRoot\oa-claim-service" -WindowStyle Normal
Start-Sleep -Seconds 15

Write-Host "启动认证服务 (端口: 8080)..." -ForegroundColor Green
$cp3 = @()
$cp3 += "$PSScriptRoot + "\oa-common\target\classes"
$cp3 += "$PSScriptRoot + "\oa-auth-service\target\classes"
$cp3 += "$mavenRepo + "\io\jsonwebtoken\jjwt-api\0.11.5\*"
$cp3 += "$mavenRepo + "\io\jsonwebtoken\jjwt-impl\0.11.5\*"
$cp3 += "$mavenRepo + "\io\jsonwebtoken\jjwt-jackson\0.11.5\*"
$classpath3 = $cp3 -join ";"
Start-Process -FilePath "java" -ArgumentList "-cp", $classpath3, "com.oa.auth.AuthServiceApplication" -WorkingDirectory "$PSScriptRoot\oa-auth-service" -WindowStyle Normal

Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "所有服务启动中!" -ForegroundColor Green
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "认证服务: http://localhost:8080" -ForegroundColor Yellow
Write-Host "员工服务: http://localhost:8081" -ForegroundColor Yellow
Write-Host "报销服务: http://localhost:8082" -ForegroundColor Yellow
Write-Host "H2控制台: http://localhost:8081/h2-console" -ForegroundColor Yellow
Write-Host "JDBC URL: jdbc:h2:file:./oa_db;AUTO_SERVER=TRUE" -ForegroundColor Gray
Write-Host "======================================" -ForegroundColor Cyan
