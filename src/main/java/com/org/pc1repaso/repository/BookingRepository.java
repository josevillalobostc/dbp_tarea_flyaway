package com.org.pc1repaso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.pc1repaso.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    public List<Booking> findByUserId(Long id);
}