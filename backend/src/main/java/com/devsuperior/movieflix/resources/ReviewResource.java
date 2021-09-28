package com.devsuperior.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/review")
public class ReviewResource {
	
	@Autowired
	private ReviewService service;
	
	@PostMapping
	public ResponseEntity<ReviewDTO> create(@Validated @RequestBody ReviewDTO dto) {
		final var review = service.create(dto);
		return ResponseEntity.ok(review);
	}
	

}
