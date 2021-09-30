package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.movieflix.dtos.ReviewDTOResponse;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/review")
public class ReviewResource {
	
	@Autowired
	private ReviewService service;
	
	@PostMapping
	public ResponseEntity<ReviewDTOResponse> create(@Validated @RequestBody ReviewDTO dto) {
		final var review = service.create(dto);
		return ResponseEntity.ok(review);
	}

	@GetMapping("/{movieId}")
	public ResponseEntity<ReviewDTOResponse> findByMovieId(@PathVariable Long movieId) {
		return ResponseEntity.ok(service.findByMovieId(movieId));
	}
	

}
