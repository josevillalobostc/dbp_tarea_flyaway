package com.org.pc1repaso.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends RuntimeException{
    private final String errorCode;

    public BadRequestException(String mensaje, String errorCode){
        super(mensaje);
        this.errorCode = errorCode;
    }
	
}