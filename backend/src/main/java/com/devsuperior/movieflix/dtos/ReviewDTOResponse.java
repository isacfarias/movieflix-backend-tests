package com.devsuperior.movieflix.dtos;

import com.devsuperior.movieflix.entities.Review;

import javax.validation.constraints.NotBlank;

public class ReviewDTOResponse {
	
	private Long id;
	
	@NotBlank(message = "A descrição da avaliação é obrigatória.")
	private String text;
	private MovieDTO movie;
	private String userName;

	public ReviewDTOResponse() {}

	public ReviewDTOResponse(Review review) {
		this.id = review.getId();
		this.text = review.getName();
		this.movie = new MovieDTO(review.getMovie());
		this.userName = review.getUser().getUsername();
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

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
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
		ReviewDTOResponse other = (ReviewDTOResponse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", name=" + text + ", movie=" + movie + ", userName=" + userName + "]";
	}

}