package com.gestione.blogging.autori;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestione.blogging.post.Post;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoreResponse {
	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private LocalDate dataNascita;

}
