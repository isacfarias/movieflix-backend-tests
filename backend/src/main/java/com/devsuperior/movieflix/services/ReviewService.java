package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.dtos.ReviewDTOResponse;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public ReviewDTOResponse create(ReviewDTO dto) {
		return new ReviewDTOResponse(repository.save(toEntity(dto)));
	}

	@Transactional(readOnly = true)
	public ReviewDTOResponse findByMovieId(Long movieId) {
		var review = repository.findByMovieId(movieId);
		return new ReviewDTOResponse(review);
	}

	private Review toEntity(ReviewDTO dto) {
		var movie = movieRepository.getOne(dto.getMovieId());
		var review = new Review();
		review.setName(dto.getText());
		review.setMovie(movie);

		return review;
	}
}
