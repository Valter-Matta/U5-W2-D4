package com.gestione.blogging.autori;

import com.gestione.blogging.response.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/autore")
@RequiredArgsConstructor
public class AutoreController {
	private final AutoreService autoreService;

	@GetMapping
	@ResponseStatus (HttpStatus.OK)
	public Page<Autore> findAll (@RequestParam int page, @RequestParam int recordPerPagina, @RequestParam String sortBy) {
		Pageable pageable = PageRequest.of(page, recordPerPagina, Sort.by(sortBy));
		return autoreService.findAll(pageable);
	}



	@GetMapping ("/{id}")
	public AutoreDettaglioResponse findAutoreWithPosts (@PathVariable Long id) {
		return autoreService.findPostsWithAutoreID(id);
	}

	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public GeneralResponse create (@RequestBody AutoreRequest request) {
		return autoreService.save(request);
	}

	@DeleteMapping ("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void delete (Long id) {
		autoreService.delete(id);
	}


}
