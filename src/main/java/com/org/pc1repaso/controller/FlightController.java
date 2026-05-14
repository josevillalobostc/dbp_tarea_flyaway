package com.org.pc1repaso.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
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
        return ResponseEntity.ok(nuevovuelo);
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
        Long id = 44L;
        LocalDateTime salida = LocalDateTime.now();
        LocalDateTime llegada = LocalDateTime.now();
        FlightResponseDTO responsePrueba = new FlightResponseDTO(id,"Avon","123-aa",salida,llegada,200);
        List<FlightResponseDTO> responseVuelo = new ArrayList<>();
        responseVuelo.add(responsePrueba);
        return ResponseEntity.ok(responseVuelo);
    }

    @PostMapping("/book/{id}")
    public ResponseEntity<BookingResponseDTO> reservarVuelo(@PathVariable Long id){
        BookingResponseDTO response = flightService.reservarVuelo(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}