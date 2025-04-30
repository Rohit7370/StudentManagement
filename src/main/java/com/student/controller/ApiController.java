package com.student.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    // Simple test endpoint
    @Operation(summary = "Test endpoint", description = "Simple endpoint to test API connectivity")
    @ApiResponse(responseCode = "200", description = "Successful test response")
    @GetMapping("/test")
    public Map<String, String> test() {
        return Collections.singletonMap("message", "Test endpoint works!");
    }

    // OAuth2 user info endpoint
    @Operation(summary = "OAuth2 User Info", description = "Get details of authenticated OAuth2 user")
    @ApiResponse(responseCode = "200", description = "OAuth2 user details")
    @ApiResponse(responseCode = "401", description = "Not authenticated via OAuth2")
    @GetMapping("/oauth-user")
    public Map<String, Object> oauthUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return Collections.singletonMap("error", "No OAuth2 user authenticated");
        }
        return Collections.singletonMap("oauth2User", principal.getAttributes());
    }
}
