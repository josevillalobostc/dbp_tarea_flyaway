package com.org.pc1repaso.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.org.pc1repaso.dto.FlightRequestDTO;
import com.org.pc1repaso.dto.FlightResponseDTO;
import com.org.pc1repaso.model.Flight;
import com.org.pc1repaso.repository.FlightRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;
    
    public FlightResponseDTO createVuelo(FlightRequestDTO request){
        Flight vuelo = modelMapper.map(request, Flight.class);
        Flight nuevovuelo = flightRepository.save(vuelo);
        FlightResponseDTO elnuevo = modelMapper.map(nuevovuelo, FlightResponseDTO.class);
        return elnuevo;        
    }
    
}