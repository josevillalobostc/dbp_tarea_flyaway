package com.org.pc1repaso.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime bookingDate;
    
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LocalDateTime getFlightSalida(){
        return flight.getEstDepartureTime();
    }

    public LocalDateTime getFlightLlegada(){
        return flight.getEstArrivalTime();
    }
}