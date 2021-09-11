package com.devsuperior.movieflix.dtos;

import com.devsuperior.movieflix.entities.Genre;

public class GenreDTO {
	
	private Long id;
	private String name;
	
	public GenreDTO() {	}

	public GenreDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public GenreDTO(Genre genre) {
		this.id = genre.getId();
		this.name = genre.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		GenreDTO other = (GenreDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenreDTO [id=" + id + ", name=" + name + "]";
	}
	

}