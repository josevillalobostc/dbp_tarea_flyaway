package com.org.pc1repaso.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.pc1repaso.dto.UserRequestDTO;
import com.org.pc1repaso.dto.UserResponseDTO;
import com.org.pc1repaso.service.AuthService;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users/register")
public class UserController {
    private final AuthService authService;

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<UserResponseDTO> crearUsuario(@Valid @RequestBody UserRequestDTO userRequest){
        UserResponseDTO userResponse = authService.createUser(userRequest);
        return ResponseEntity.status(201).body(userResponse);
    }
}