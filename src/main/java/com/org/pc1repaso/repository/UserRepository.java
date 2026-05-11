package com.org.pc1repaso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.pc1repaso.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {
    Optional<User> findByEmail(String email);   	
}