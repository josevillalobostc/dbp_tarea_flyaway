package com.org.pc1repaso.dto;


import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightRequestDTO {

    @Pattern(regexp = "(.*[A-Z]).*")
    @Pattern(regexp = "(.*[0-9]).*")
    @Pattern(regexp="^[A-Z]{2,3}[0-9]{3}")
    @Length(max = 6)
    @NotBlank
    private String flightNumber;
    @NotBlank
    private String airlineName;
    @NotNull
    private LocalDateTime estDepartureTime;    
    @NotNull
    private LocalDateTime estArrivalTime;

    @NotNull
    @Min(1)
    private Integer availableSeats;
}