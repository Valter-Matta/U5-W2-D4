package com.gestione.blogging.autori;

import com.gestione.blogging.email.EmailService;
import com.gestione.blogging.response.GeneralResponse;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class AutoreService {
	private final AutoreRepository autoreRepository;
	private final EmailService emailService;
	@Value ("${messages.new.autore.subject}")
	private String newAutoreSubject;

	private String newAutoreBody="L'autore è stato registrato con successo";
	private String newAutoreHtmlBody;

	//GET /authors => ritorna la lista di autori
	public Page<Autore> findAll (Pageable pageable) {
		return autoreRepository.findAll(pageable);
	}


	//GET /authors => ritorna la lista di autori
	public List<AutoreResponse> findAll () {
		return autoreResponseListFromAutoreList(autoreRepository.findAll());
	}

	//GET /blogPosts /123 => ritorna un singolo blog post
	public Autore findById (Long id) {
		return autoreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Autore not found with id " + id));
	}

	//GET ritorna un autore con i suoi posts
	@Transactional
	public AutoreDettaglioResponse findPostsWithAutoreID (Long id) {
		Autore autore = findById(id);
		AutoreDettaglioResponse autoreDettagioResponse = new AutoreDettaglioResponse();
		BeanUtils.copyProperties(autore, autoreDettagioResponse);
		autoreDettagioResponse.setPosts(autore.getPosts());
		return autoreDettagioResponse;
	}

	//POST /blogPosts => crea un nuovo blog post

	public GeneralResponse save (@Valid AutoreRequest request) {
		if (autoreRepository.existsByEmail(request.getEmail())) {
			throw new EntityExistsException("Email già esistente");
		}
		Autore autore = new Autore();
		BeanUtils.copyProperties(request, autore);
		autoreRepository.save(autore);
		GeneralResponse resp = new GeneralResponse();
		BeanUtils.copyProperties(autore, resp);
		try {
			emailService.sendEmail(autore.getEmail(), newAutoreSubject, newAutoreBody + " " + autore.getEmail());
		} catch (MessagingException e) {
			System.out.println("Errore invio email");
		}
		return resp;
	}

	//PUT /blogPosts /123 => modifica lo specifico blog post
	public Autore update (Long id, AutoreRequest request) {
		Autore entity = findById(id);
		BeanUtils.copyProperties(request, entity);
		autoreRepository.save(entity);
		return entity;
	}

	//DELETE /blogPosts /123 => cancella lo specifico blog post
	public void delete (Long id) {
		Autore autore = findById(id);
		autoreRepository.deleteById(id);
	}

	//trasformo autore in AutoreResponse e lo metto in una lista
	public List<AutoreResponse> autoreResponseListFromAutoreList (List<Autore> autori) {
		return autori.stream().map(autore -> {
			AutoreResponse autoreResponse = new AutoreResponse();
			BeanUtils.copyProperties(autore, autoreResponse);
			return autoreResponse;
		}).toList();
	}
}
