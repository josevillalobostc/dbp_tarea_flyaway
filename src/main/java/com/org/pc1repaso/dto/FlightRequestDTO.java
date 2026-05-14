package com.org.pc1repaso.dto;


import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightRequestDTO {

    @Pattern(regexp = "(.*[A-Z]).*")
    @Pattern(regexp = "(.*[0-9]).*")
    @Length(max = 6)
    private String flightNumber;
    @NotBlank
    private String airlineName;
    @NotBlank
    private LocalDateTime estDepartureTime;    
    @NotBlank
    private LocalDateTime estArrivalTime;

    @Min(1)
    private Integer seats;
}