package com.student.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/force404")
    public void forceNotFound() throws NoHandlerFoundException {
        throw new NoHandlerFoundException("GET", "/force404", null);
    }

//    // For both basic auth & Google sign-in
//    @GetMapping("/api/test1")
//    public String testEndpoint(Authentication authentication) {
//        StringBuilder response = new StringBuilder();
//
//        response.append("✅ Authenticated User Details:\n");
//        response.append("Principal: ").append(authentication.getPrincipal()).append("\n");
//        response.append("Authorities: ").append(authentication.getAuthorities()).append("\n");
//        response.append("Name: ").append(authentication.getName()).append("\n");
//
//        return response.toString();
//    }
//
//    // Optional: dedicated Google login user info (only for OAuth2)
//    @GetMapping("/api/oauth-user1")
//    public Map<String, Object> oauthUserDetails(@AuthenticationPrincipal OAuth2User oAuth2User) {
//        return oAuth2User.getAttributes(); // Contains name, email, picture, etc.
//    }
}


