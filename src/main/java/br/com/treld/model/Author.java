package br.com.treld.model;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.treld.enums.Role;

@Document(collection = "superclass")
public class Author extends User {

	private String about;
	private FileLocation avatar;

	public Author(String username, String password) {
		super(username, password);
		addRole(Role.ROLE_AUTHOR);
	}

	public String getAbout() {
		return about;
	}

	public FileLocation getAvatar() {
		return avatar;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setAvatar(FileLocation avatar) {
		this.avatar = avatar;
	}

}
