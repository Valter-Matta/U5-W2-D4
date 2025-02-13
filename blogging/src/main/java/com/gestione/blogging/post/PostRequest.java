package com.gestione.blogging.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
	private String category;
	private String title;
	private String content;
	private int readTime;
	private Long autoreId;
}
