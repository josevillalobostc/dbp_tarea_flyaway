package com.org.pc1repaso.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponseDTO {
    private Long id;
    private String airlineName;
    private String flightNumber;
    private LocalDateTime estDepartureTime;
    private LocalDateTime estArrivalTime;
    private Integer availableSeats;
}