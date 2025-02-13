package com.gestione.blogging.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestione.blogging.autori.Autore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "post")
public class Post {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(length = 500)
	private String category;

	@Column(length = 500)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String cover = "https://picsum.photos/200/300";

	@Column(length = 500)
	private String content;

	private int readTime;

	@ManyToOne
	@JsonIgnoreProperties("posts")
	private Autore autore;

}