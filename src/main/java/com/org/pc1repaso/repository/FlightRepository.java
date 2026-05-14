package com.org.pc1repaso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.org.pc1repaso.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight>{
	
}