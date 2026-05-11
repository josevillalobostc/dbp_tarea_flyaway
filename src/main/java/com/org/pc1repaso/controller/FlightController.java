package com.org.pc1repaso.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.pc1repaso.dto.FlightRequestDTO;
import com.org.pc1repaso.dto.FlightResponseDTO;
import com.org.pc1repaso.service.FlightService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;

    @PreAuthorize("permitAll()")
    @PostMapping("/create")
    public ResponseEntity<FlightResponseDTO> crearVuelo(@RequestBody FlightRequestDTO vuelo){
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
        FlightResponseDTO responsePrueba = new FlightResponseDTO(id,"123-aa",salida,llegada,200);
        List<FlightResponseDTO> responseVuelo = new ArrayList<>();
        responseVuelo.add(responsePrueba);
        return ResponseEntity.ok(responseVuelo);
    }
}