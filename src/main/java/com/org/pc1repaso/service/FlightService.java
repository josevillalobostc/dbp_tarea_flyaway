package com.org.pc1repaso.service;


import java.net.http.HttpRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.org.pc1repaso.dto.BookingResponseDTO;
import com.org.pc1repaso.dto.FlightRequestDTO;
import com.org.pc1repaso.dto.FlightResponseDTO;
import com.org.pc1repaso.exceptions.BadRequestException;
import com.org.pc1repaso.model.Booking;
import com.org.pc1repaso.model.Flight;
import com.org.pc1repaso.model.User;
import com.org.pc1repaso.repository.BookingRepository;
import com.org.pc1repaso.repository.FlightRepository;
import com.org.pc1repaso.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    
    public FlightResponseDTO createVuelo(FlightRequestDTO request){
        Flight vuelo = modelMapper.map(request, Flight.class);
        if(vuelo.getHoraSalida().isAfter(vuelo.getHoraLlegada())){
            throw new BadRequestException("La fecha de llegada debe ser luego de la fecha de salida.");
        }
        vuelo.setAsientosDisponibles(vuelo.getAsientos());
        Flight nuevovuelo = flightRepository.save(vuelo);
        FlightResponseDTO elnuevo = modelMapper.map(nuevovuelo, FlightResponseDTO.class);
        return elnuevo;        
    }

    public BookingResponseDTO reservarVuelo(Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuario = (User) auth.getPrincipal();
        Long userId = usuario.getId();
        String userName = usuario.getName();
        
        Flight vuelo = flightRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("No existe dicho id de vuelo."));

        if(vuelo.getHoraLlegada().isBefore(LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault()))){
            throw new BadRequestException("Dicho vuelo está en curso o ya finalizó.");
        }
        if(vuelo.getAsientosDisponibles() < 1){
            throw new BadRequestException("Dicho vuelo ya no tiene asientos.");
        }
        
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        for(Booking book : bookings){
            if(vuelo.getHoraSalida().isBefore(vuelo.getHoraLlegada()) && 
                vuelo.getHoraLlegada().isAfter(vuelo.getHoraSalida())
            ) {throw new BadRequestException("Conflico de horarios con la reserva " + book.getId());}
        }
                
        vuelo.setAsientosDisponibles(vuelo.getAsientosDisponibles() - 1);
        flightRepository.save(vuelo);
        Booking newBooking = new Booking();
        newBooking.setFlight(vuelo);
        newBooking.setUser(userRepository.findById(userId).orElseThrow());
        
        
        Booking booking = bookingRepository.save(newBooking);
        BookingResponseDTO response = modelMapper.map(booking,BookingResponseDTO.class);
        response.setUserName(userName);
        response.setHoraSalida(vuelo.getHoraSalida());
        response.setHoraLlegada(vuelo.getHoraLlegada());
        return response;
    }
    
}