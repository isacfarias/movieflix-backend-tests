package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Transactional(readOnly = true)
	public Page<MovieDTO> findAllPaged(Long genreId, PageRequest pageRequest) {
		var genre = genreId != 0 ? genreRepository.getOne(genreId) : null;
		return repository.find(genre, pageRequest).map(MovieDTO::new);
	}

	@Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
		var movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		var reviews = reviewRepository.findByMovieId(id);
		return new MovieDTO(movie, reviews);
    }

}
