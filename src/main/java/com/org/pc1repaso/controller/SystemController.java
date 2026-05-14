package com.org.pc1repaso.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.pc1repaso.repository.BookingRepository;
import com.org.pc1repaso.repository.FlightRepository;
import com.org.pc1repaso.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cleanup")
@RequiredArgsConstructor
public class SystemController {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    @DeleteMapping
    public ResponseEntity<Void> cleanup(){
        bookingRepository.deleteAll();
        flightRepository.deleteAll();
        userRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}