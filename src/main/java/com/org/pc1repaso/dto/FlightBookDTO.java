package com.org.pc1repaso.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FlightBookDTO {
    private Long flightId;
    private Long userId;
    private String userName;
    private LocalDateTime fechaReserva;
}