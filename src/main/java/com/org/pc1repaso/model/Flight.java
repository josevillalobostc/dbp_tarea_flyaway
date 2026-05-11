package com.org.pc1repaso.model;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroVuelo;
    private String aerolina;

    private LocalDateTime horaSalida;
    private LocalDateTime horaLlegada;

    private Integer asientos;
}