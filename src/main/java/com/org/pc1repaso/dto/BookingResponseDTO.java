package com.org.pc1repaso.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingResponseDTO {
    private Long id;
    private LocalDateTime bookingDate;
	private Long flightId;
	private String flightNumber;
	private Long customerId;
	private String customerFirstName;
	private String customerLastName;
}