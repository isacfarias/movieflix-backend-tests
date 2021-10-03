package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.movieflix.dtos.ReviewDTOResponse;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {
	
	@Autowired
	private ReviewService service;

	@PreAuthorize("hasAnyRole('MEMBER')")
	@PostMapping
	public ResponseEntity<ReviewDTOResponse> create(@Validated @RequestBody ReviewDTO dto) {
		final var review = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(review.getId())
				.toUri();
		return ResponseEntity.created(uri).body(review);
	}

	@GetMapping("/{movieId}")
	public ResponseEntity<List<ReviewDTOResponse>> findByMovieId(@PathVariable Long movieId) {
		return ResponseEntity.ok(service.findByMovieId(movieId));
	}
	

}
