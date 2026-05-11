package com.org.pc1repaso.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.pc1repaso.dto.UserRequestDTO;
import com.org.pc1repaso.dto.UserResponseDTO;
import com.org.pc1repaso.model.User;
import com.org.pc1repaso.repository.UserRepository;

import lombok.NoArgsConstructor;
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

    /*
    public UserResponseDTO createUser(UserRequestDTO userRequest){
        User newUser = modelMapper.map(userRequest,User.class);
        User addedUser = userRepository.save(newUser);        
        return modelMapper.map(addedUser, UserResponseDTO.class);
    }
     */
}