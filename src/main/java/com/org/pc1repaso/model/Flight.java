package com.org.pc1repaso.model;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroVuelo;
    private String aerolinea;

    private LocalDateTime horaSalida;
    private LocalDateTime horaLlegada;

    private Integer asientos;
    private Integer asientosDisponibles;

    @OneToMany(mappedBy = "flight")
    private List<Booking> bookings;
}