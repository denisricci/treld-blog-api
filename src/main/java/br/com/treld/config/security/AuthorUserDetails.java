package br.com.treld.config.security;

import java.util.stream.Collectors;

import br.com.treld.model.User;

/**
 * Created by rsouza on 16/07/16.
 */
public class AuthorUserDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;

	public AuthorUserDetails(User user) {
		super(user.getUsername(), user.getPassword(),
				user.getRoles().stream().
				map(role -> new AuthorGrantedAuthority(role)).
				collect(Collectors.toList()));
	}
}
