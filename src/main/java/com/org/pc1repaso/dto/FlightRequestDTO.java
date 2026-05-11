package com.org.pc1repaso.dto;


import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightRequestDTO {
    private String numeroVuelo;    
    private LocalDateTime horaSalida;    
    private LocalDateTime horaLlegada;

    @Min(1)
    private Integer asientos;
}