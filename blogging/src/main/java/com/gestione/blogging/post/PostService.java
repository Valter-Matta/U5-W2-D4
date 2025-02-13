package com.gestione.blogging.post;

import com.gestione.blogging.autori.Autore;
import com.gestione.blogging.autori.AutoreRequest;
import com.gestione.blogging.response.GeneralResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class PostService {
	private final PostRepository repository;


	//GET /authors => ritorna la lista di autori
	public List<Post> findAll () {
		return repository.findAll();
	}

	//GET /blogPosts /123 => ritorna un singolo blog post
	public Post findById (Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found with id " + id));
	}

	//POST /blogPosts => crea un nuovo blog post
	public GeneralResponse save (PostRequest request) {

		Post post = postFromPostRequest(request);

		GeneralResponse resp = new GeneralResponse();
		repository.save(post);
		BeanUtils.copyProperties(post, resp);
		return resp;
	}

	//PUT /blogPosts /123 => modifica lo specifico blog post
	public Post update (Long id, PostRequest request) {
		Post entity = findById(id);
		BeanUtils.copyProperties(request, entity);
		repository.save(entity);
		return entity;
	}

	//DELETE /blogPosts /123 => cancella lo specifico blog post
	public void delete (Long id) {
		Post autore = findById(id);
		repository.deleteById(id);
	}

	public Post postFromPostRequest (PostRequest request) {
		Post post = new Post();
		BeanUtils.copyProperties(request, post);
		return post;
	}
}
