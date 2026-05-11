package com.org.pc1repaso.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.pc1repaso.compontents.JwtService;
import com.org.pc1repaso.dto.TokenResponse;
import com.org.pc1repaso.dto.UserRequestDTO;
import com.org.pc1repaso.dto.UserResponseDTO;
import com.org.pc1repaso.model.User;
import com.org.pc1repaso.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public TokenResponse signIn(String email, String password){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                email ,password
            )            
        );
        var user = userRepository.findByEmail(email).orElseThrow();
        var token = jwtService.generateToken(user);
        return new TokenResponse(token);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequest){
            User newUser = modelMapper.map(userRequest, User.class);
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            User addedUser = userRepository.save(newUser);        
            return modelMapper.map(addedUser, UserResponseDTO.class);
        }
}