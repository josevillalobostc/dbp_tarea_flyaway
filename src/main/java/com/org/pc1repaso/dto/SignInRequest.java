package com.org.pc1repaso.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    @Email(message = "Debe ser un correo válido")
	private String email;	
	private String password;
}