package br.com.treld.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.treld.enums.Role;

/**
 * Created by rsouza on 16/07/16.
 */
@Document(collection = "users")
@JsonInclude(Include.NON_NULL)
public class User {

	@Id
	@NotEmpty(message = "the user must have a username")
	private String username;
	@JsonIgnore
	@NotEmpty(message = "the author must have a password")
	private String password;
	@NotEmpty(message = "the author must have a email")
	private String email;
	private List<Role> roles;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User() {
	}
	
	public void addRole(Role role){
		if(roles == null){
			roles= new ArrayList<>();
		}
		roles.add(role);
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
	
	public List<Role> getRoles() {
		return roles;
	}
}
