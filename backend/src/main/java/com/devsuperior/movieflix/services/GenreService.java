package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.GenreDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository repository;

    public List<GenreDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(GenreDTO::new)
                .collect(Collectors.toList());
    }
}
