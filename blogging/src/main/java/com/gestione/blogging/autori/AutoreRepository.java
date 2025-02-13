package com.gestione.blogging.autori;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoreRepository extends JpaRepository<Autore, Long> {
	public Autore findByEmail (String email);

	public Autore findByCognome (String cognome);

	public boolean existsByEmail (String email);
}
