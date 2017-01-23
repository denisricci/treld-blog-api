package br.com.treld.config.security;

import br.com.treld.model.Author;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rsouza on 16/07/16.
 */
public class AuthorUserDetails extends User {

	private static final long serialVersionUID = 1L;
	
	private static final List<GrantedAuthority> AUTHORITIES = Arrays.asList(new AuthorGrantedAuthority());

    public AuthorUserDetails(Author author) {
        super(author.getUsername(), author.getPassword(), AUTHORITIES);
    }
}
