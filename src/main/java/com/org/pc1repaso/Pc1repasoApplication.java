package com.org.pc1repaso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Pc1repasoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pc1repasoApplication.class, args);
	}



}
