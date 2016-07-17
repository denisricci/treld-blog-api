package br.com.treld.config.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by rsouza on 16/07/16.
 */
public class AuthorGrantedAuthority implements GrantedAuthority {

    public static final String AUTHOR_AUTHORITY = "ROLE_AUTHOR";

    @Override
    public String getAuthority() {
        return AUTHOR_AUTHORITY;
    }
}
