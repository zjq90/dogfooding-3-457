package com.oa.gateway.controller;

import com.oa.common.common.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class GatewayController {

    @Resource
    private RestTemplate restTemplate;

    private static final String EMPLOYEE_SERVICE = "http://localhost:8081";
    private static final String CLAIM_SERVICE = "http://localhost:8082";
    private static final String AUTH_SERVICE = "http://localhost:8083";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/api/employee/**")
    public ResponseEntity<String> forwardEmployee(HttpServletRequest request, @RequestBody(required = false) String body) {
        String path = request.getRequestURI().replace("/api", "");
        String queryString = request.getQueryString();
        String url = EMPLOYEE_SERVICE + path + (queryString != null ? "?" + queryString : "");
        return forwardRequest(url, request, body);
    }

    @RequestMapping("/api/department/**")
    public ResponseEntity<String> forwardDepartment(HttpServletRequest request, @RequestBody(required = false) String body) {
        String path = request.getRequestURI().replace("/api", "");
        String queryString = request.getQueryString();
        String url = EMPLOYEE_SERVICE + path + (queryString != null ? "?" + queryString : "");
        return forwardRequest(url, request, body);
    }

    @RequestMapping("/api/log/**")
    public ResponseEntity<String> forwardLog(HttpServletRequest request, @RequestBody(required = false) String body) {
        String path = request.getRequestURI().replace("/api", "");
        String queryString = request.getQueryString();
        String url = EMPLOYEE_SERVICE + path + (queryString != null ? "?" + queryString : "");
        return forwardRequest(url, request, body);
    }

    @RequestMapping("/api/claim/**")
    public ResponseEntity<String> forwardClaim(HttpServletRequest request, @RequestBody(required = false) String body) {
        String path = request.getRequestURI().replace("/api", "");
        String queryString = request.getQueryString();
        String url = CLAIM_SERVICE + path + (queryString != null ? "?" + queryString : "");
        return forwardRequest(url, request, body);
    }

    @RequestMapping("/api/auth/**")
    public ResponseEntity<String> forwardAuth(HttpServletRequest request, @RequestBody(required = false) String body) {
        String path = request.getRequestURI().replace("/api", "");
        String queryString = request.getQueryString();
        String url = AUTH_SERVICE + path + (queryString != null ? "?" + queryString : "");
        return forwardRequest(url, request, body);
    }

    private ResponseEntity<String> forwardRequest(String url, HttpServletRequest request, String body) {
        try {
            HttpHeaders headers = new HttpHeaders();
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                headers.set("Authorization", authHeader);
            }
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpMethod method = HttpMethod.valueOf(request.getMethod());
            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class);
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"code\":500,\"message\":\"服务调用失败: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/api/health")
    public Result<String> health() {
        return Result.success("OK");
    }
}
