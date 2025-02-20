package com.gestione.blogging.autori;


import com.gestione.blogging.post.Post;
import com.gestione.blogging.post.PostRepository;


import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutoreRunner implements CommandLineRunner {
	private final AutoreRepository autoreRepository;
	private final PostRepository postRepository;
	private final Faker faker;

	@Override
	public void run (String... args) throws Exception {

		for (int i = 0; i < 20; i++) {
			Post post = new Post();
			post.setCategory(faker.book().genre());
			post.setTitle(faker.book().title());
			post.setContent(faker.lorem().sentence(50));
			post.setReadTime(faker.number().numberBetween(1, 10));
			post.setAutore(autoreRepository.findById(Math.round(Math.random() * 9)).orElse(null));

			postRepository.save(post);

		}

		for (int i = 0; i < 10; i++) {
			Autore autore = new Autore();
			autore.setNome(faker.company().name());
			autore.setCognome(faker.address().fullAddress());
			autore.setEmail(faker.internet().emailAddress());

			autoreRepository.save(autore);
		}
	}


}
