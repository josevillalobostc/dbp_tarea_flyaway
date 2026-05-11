package com.org.pc1repaso.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    private final String errorCode;

    public ResourceNotFoundException(String mensaje, String errorCode){
        super(mensaje);
        this.errorCode = errorCode;
    }

	
}