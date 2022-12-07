package com.example.TheMovies;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

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
	private Integer premiere;
	
	
	private String type;
	
	@Lob
	private byte[] data;

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

	public Integer getPremiere() {
		return premiere;
	}

	public void setPremiere(Integer premiere) {
		this.premiere = premiere;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	
}