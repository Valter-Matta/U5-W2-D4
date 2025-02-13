package com.gestione.blogging.autori;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoreRequest {
	@NotBlank(message = "Il nome non può essere vuoto")
	private String nome;
	@NotBlank(message = "Il cognome non può essere vuoto")
	private String cognome;
	@NotBlank(message = "l'email non può essere vuota")
	@Email(message = "Email non valida")
	private String email;
	private LocalDate dataNascita;


}
