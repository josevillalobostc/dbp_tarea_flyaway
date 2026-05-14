package com.org.pc1repaso.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.pc1repaso.dto.BookingResponseDTO;
import com.org.pc1repaso.dto.FlightRequestDTO;
import com.org.pc1repaso.dto.FlightResponseDTO;
import com.org.pc1repaso.service.FlightService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;

    @PreAuthorize("permitAll()")
    @PostMapping("/create")
    public ResponseEntity<FlightResponseDTO> crearVuelo(@RequestBody @Valid FlightRequestDTO vuelo){
        FlightResponseDTO nuevovuelo = flightService.createVuelo(vuelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevovuelo);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightResponseDTO>> 
    getFlights(
    @RequestParam(required = false) String numeroVuelo, 
    @RequestParam(required = false) String aerolinea,
    @RequestParam(required = false) LocalDateTime desde,
    @RequestParam(required = false) LocalDateTime hasta
    ) 
    {
        List<FlightResponseDTO> response = flightService.buscarVuelos(numeroVuelo, aerolinea, desde, hasta);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/book")
    public ResponseEntity<BookingResponseDTO> reservarVuelo(@RequestBody @Valid BookingResponseDTO request){
        BookingResponseDTO response = flightService.reservarVuelo(request.getFlightId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("book/{id}")
    public ResponseEntity<BookingResponseDTO> verVuelo(@PathVariable Long id){
        BookingResponseDTO response = flightService.getBooking(id);
        return ResponseEntity.ok(response);
    }
    
}