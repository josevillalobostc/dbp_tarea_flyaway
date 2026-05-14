package com.org.pc1repaso.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {


    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "(.*[A-Z]).*",message = "Debe contener una mayúscula")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "(.*[A-Z]).*",message = "Debe contener una mayúscula")
    private String lastName;

    @NotBlank
    @Size(min=8, message = "Debe tener como mínimo 8 carácteres")
    @Pattern(regexp = "(.*[0-9]).*", message = "Debe contener un número")
    //@Pattern(regexp = "(.*[a-z]).*", message = "Debe contener una letra minúscula")
    @Pattern(regexp = "(.*[A-Z]).*", message = "Debe contener una letra mayúscula")
    private String password;

}