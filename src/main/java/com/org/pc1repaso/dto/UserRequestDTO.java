package com.org.pc1repaso.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private Long id;

    @Email
    private String email;

    @Pattern(regexp = "(.*[A-Z]).*",message = "Debe contener una mayúscula")
    private String name;

    @Size(min=8)
    @Pattern(regexp = "(.*[0-9]).*", message = "Debe contener un número")
    @Pattern(regexp = "(.*[a-z]).*", message = "Debe contener una letra minúscula")
    @Pattern(regexp = "(.*[A-Z]).*", message = "Debe contener una letra mayúscula")
    private String password;

}