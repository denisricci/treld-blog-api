package br.com.treld.config.security;

import org.springframework.security.core.GrantedAuthority;

import br.com.treld.enums.Role;

/**
 * Created by rsouza on 16/07/16.
 */
public class AuthorGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
		
	private Role role;
	
	public AuthorGrantedAuthority(Role role) {
		this.role = role;
	}

    @Override
    public String getAuthority() {
        return role.name();
    }
}
