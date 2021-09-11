package com.devsuperior.movieflix.dtos;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.Review;

public class ReviewDTO {
	
	private Long id;
	
	@NotBlank(message = "A descrição da avaliação é obrigatória.")
	private String text;
	private Set<MovieDTO> movies = new HashSet<>();
	private Set<UserDTO> users = new HashSet<>();
	
	public ReviewDTO() {}
	
	public ReviewDTO(Long id, String name) {
		this.id = id;
		this.text = name;
	}
	
	public ReviewDTO(Review review) {
		this.id = review.getId();
		this.text = review.getName();
		review.getMovies().forEach(movie -> movies.add(new MovieDTO(movie)));
		review.getUsers().forEach(user -> users.add(new UserDTO(user)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<MovieDTO> getMovies() {
		return movies;
	}

	public Set<UserDTO> getUsers() {
		return users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewDTO other = (ReviewDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", name=" + text + ", movies=" + movies + ", users=" + users + "]";
	}

}
