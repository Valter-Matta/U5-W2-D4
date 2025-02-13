package com.gestione.blogging.autori;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestione.blogging.post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "autori")
public class Autore {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private LocalDate dataNascita;

	@OneToMany(mappedBy = "autore")
	@JsonIgnoreProperties ("autore")
	private List<Post> posts;



}