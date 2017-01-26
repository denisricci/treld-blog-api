package br.com.treld.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by rsouza on 16/07/16.
 */
@Document(collection = "authors")
public class Author {

	@Id
	@NotEmpty(message = "the author most have a username")
	private String username;
	@JsonIgnore
	@NotEmpty(message = "the author most have a password")
	private String password;
	@NotEmpty(message = "the author most have a email")
	private String email;

	public Author(String username, String password) {
		this.username=username;
		this.password=password;
	}

	public Author() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
