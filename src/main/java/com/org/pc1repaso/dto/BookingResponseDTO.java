package com.org.pc1repaso.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingResponseDTO {
	private Long userId;
	private Long flightId;
	private String userName;
	private LocalDateTime horaSalida;
	private LocalDateTime horaLlegada;
}