package com.org.pc1repaso.service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.org.pc1repaso.compontents.FlightSpecifications;
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
        if(vuelo.getEstDepartureTime().isAfter(vuelo.getEstArrivalTime())){
            throw new BadRequestException("La fecha de llegada debe ser luego de la fecha de salida.");
        }
        Flight nuevovuelo = flightRepository.save(vuelo);
        FlightResponseDTO elnuevo = modelMapper.map(nuevovuelo, FlightResponseDTO.class);
        return elnuevo;        
    }

    public List<FlightResponseDTO> buscarVuelos(
        String numeroVuelo, String aerolinea, LocalDateTime desde, LocalDateTime hasta
    ) {
        Specification<Flight> spec = Specification
                .where(FlightSpecifications.hasNumeroVuelo(numeroVuelo))
                .and(FlightSpecifications.hasAerolinea(aerolinea))
                .and(FlightSpecifications.isBetweenDates(desde, hasta));
    
        return flightRepository.findAll(spec).stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDTO.class))
                .toList();
    }

    public BookingResponseDTO reservarVuelo(Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuario = (User) auth.getPrincipal();
        Long userId = usuario.getId();
        String userFirstName = usuario.getFirstName();
        String userLastName = usuario.getLastName();
        
        Flight vuelo = flightRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("No existe dicho id de vuelo."));

        if(vuelo.getEstArrivalTime().isBefore(LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault()))){
            throw new BadRequestException("Dicho vuelo está en curso o ya finalizó.");
        }
        if(vuelo.getAvailableSeats() < 1){
            throw new BadRequestException("Dicho vuelo ya no tiene asientos.");
        }
        
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        for(Booking book : bookings){
            if(vuelo.getEstDepartureTime().isBefore(book.getFlightLlegada()) && 
                book.getFlightSalida().isAfter(vuelo.getEstDepartureTime())
            ) {throw new BadRequestException("Conflico de horarios con la reserva " + book.getId());}
        }                
        Booking newBooking = new Booking();
        vuelo.setAvailableSeats(vuelo.getAvailableSeats() - 1);
        flightRepository.save(vuelo);
        newBooking.setFlight(vuelo);
        newBooking.setUser(userRepository.findById(userId).orElseThrow());
        newBooking.setBookingDate(LocalDateTime.now());
        
        Booking booking = bookingRepository.save(newBooking);
        BookingResponseDTO response = modelMapper.map(booking, BookingResponseDTO.class);
        response.setCustomerId(userId);
        response.setCustomerFirstName(userFirstName);
        response.setCustomerLastName(userLastName);
        response.setEstArrivalTime(vuelo.getEstArrivalTime());
        response.setEstDepartureTime(vuelo.getEstDepartureTime());
        return response;
    }

    public BookingResponseDTO getBooking(Long id){
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("No existe una reserva con dicho id"));
        BookingResponseDTO response = modelMapper.map(booking, BookingResponseDTO.class);
        response.setCustomerId(booking.getUser().getId());
        response.setCustomerFirstName(booking.getUser().getFirstName());
        response.setCustomerLastName(booking.getUser().getLastName());
        response.setEstArrivalTime(booking.getFlight().getEstArrivalTime());
        response.setEstDepartureTime(booking.getFlight().getEstDepartureTime());
        return response;
    }
    
}