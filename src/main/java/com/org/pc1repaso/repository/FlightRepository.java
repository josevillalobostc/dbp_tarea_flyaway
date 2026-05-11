package com.org.pc1repaso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.pc1repaso.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	
}