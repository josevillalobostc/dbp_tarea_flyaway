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
    private String flightNumber;
    private String airlineName;

    private LocalDateTime estDepartureTime;
    private LocalDateTime estArrivalTime;

    private Integer seats;
    private Integer availableSeats;

    @OneToMany(mappedBy = "flight")
    private List<Booking> bookings;
}