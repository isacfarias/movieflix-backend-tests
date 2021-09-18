package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	@Autowired
	private GenreRepository genreRepository;

	public Page<MovieDTO> findAllPaged(Long genreId, PageRequest pageRequest) {
		var genre = genreRepository.getOne(genreId);
		return repository.find(genre, pageRequest).map(MovieDTO::new);
	}

}
