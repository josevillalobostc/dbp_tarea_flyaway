package com.org.pc1repaso.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.org.pc1repaso.dto.SignInRequest;
import com.org.pc1repaso.dto.TokenResponse;
import com.org.pc1repaso.service.AuthService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> signIn(@RequestBody @Valid SignInRequest request){
        TokenResponse tokenResponse = authService.signIn(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(tokenResponse);
    }
	
}