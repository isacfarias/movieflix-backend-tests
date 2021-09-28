package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository; 

	public ReviewDTO create(ReviewDTO dto) {
		return new ReviewDTO(repository.save(toEntity(dto)));
	}

	private Review toEntity(ReviewDTO dto) {
		var review = new Review();
		review.getMovie().setId(dto.getMovieId());
		review.setName(dto.getText());
		review
		return review;
	}

}
