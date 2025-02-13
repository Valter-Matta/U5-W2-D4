package com.gestione.blogging.post;

import com.gestione.blogging.autori.AutoreService;
import com.gestione.blogging.response.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/post")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@GetMapping
	@ResponseStatus (HttpStatus.OK)
	public List<Post> findAll () {
		return postService.findAll();
	}

	@GetMapping ("/{id}")
	public Post findById (@PathVariable Long id) {
		return postService.findById(id);
	}

	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public GeneralResponse create (@RequestBody PostRequest request) {
		return postService.save(request);
	}

	@DeleteMapping ("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void delete (Long id) {
		postService.delete(id);
	}
}
