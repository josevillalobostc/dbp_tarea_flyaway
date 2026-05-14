package com.org.pc1repaso.compontents;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.org.pc1repaso.model.Flight;

public class FlightSpecifications {
    public static Specification<Flight> hasNumeroVuelo(String numeroVuelo) {
        return (root, query, cb) -> !StringUtils.hasText(numeroVuelo) ? null : 
            cb.like(cb.lower(root.get("flightNumber")), "%" + numeroVuelo.toLowerCase() + "%");
    }

    public static Specification<Flight> hasAerolinea(String aerolinea) {
        return (root, query, cb) -> !StringUtils.hasText(aerolinea) ? null : 
            cb.like(cb.lower(root.get("airlineName")), "%" + aerolinea.toLowerCase() + "%");
    }

    public static Specification<Flight> isBetweenDates(LocalDateTime desde, LocalDateTime hasta) {
        return (root, query, cb) -> {
            if (desde == null && hasta == null) return null;
            if (desde != null && hasta != null) return cb.between(root.get("estDepartureTime"), desde, hasta);
            if (desde != null) return cb.greaterThanOrEqualTo(root.get("estDepartureTime"), desde);
            return cb.lessThanOrEqualTo(root.get("estDepartureTime"), hasta);
        };
    }
}