package com.devsuperior.movieflix.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.devsuperior.movieflix.entities.Movie;

public class MovieDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String subTitle;
	private Integer year;
	private String imgUrl;
	private String synopsis;
	private Set<GenreDTO> generes = new HashSet<>();
	
	public MovieDTO() {}

	public MovieDTO(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis) {
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.year = year;
		this.imgUrl = imgUrl;
		this.synopsis = synopsis;
	}

	public MovieDTO(Movie movie) {
		this.id = movie.getId();
		this.title = movie.getTitle();
		this.subTitle = movie.getTitle();
		this.year = movie.getYear();
		this.imgUrl = movie.getImgUrl();
		this.synopsis = movie.getSynopsis();
		movie.getGeneres().forEach(genre -> this.generes.add(new GenreDTO(genre)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Set<GenreDTO> getGeneres() {
		return generes;
	}

	public void setGeneres(Set<GenreDTO> generes) {
		this.generes = generes;
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
		MovieDTO other = (MovieDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", title=" + title + ", subTitle=" + subTitle + ", year=" + year + ", imgUrl="
				+ imgUrl + ", synopsis=" + synopsis + ", generes=" + generes + "]";
	}

}
