package com.org.pc1repaso.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.org.pc1repaso.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository
                    .findByEmail(username)
                    .orElseThrow();
        }

}