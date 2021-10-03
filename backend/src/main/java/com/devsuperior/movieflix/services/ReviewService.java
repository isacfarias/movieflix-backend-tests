package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.dtos.ReviewDTOResponse;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
	public List<ReviewDTOResponse> findByMovieId(Long movieId) {
		var review = repository.findByMovieId(movieId);
		return review.stream().map(ReviewDTOResponse::new).collect(Collectors.toList());
	}

	private Review toEntity(ReviewDTO dto) {
		var movie = movieRepository.getOne(dto.getMovieId());
		var review = new Review();
		review.setText(dto.getText());
		review.setMovie(movie);

		return review;
	}
}
