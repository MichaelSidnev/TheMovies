package com.example.TheMovies;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity // This tells Hibernate to make a table out of this class
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Size(min = 1, max = 120)
	private String name;

	@NotNull
	@Min(1895)
	private Integer kapibara;
	
	private Blob image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getKapibara() {
		return kapibara;
	}

	public void setKapibara(Integer kapibara) {
		this.kapibara = kapibara;
	}
	
	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
}