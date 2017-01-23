package br.com.treld.config.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * Created by rsouza on 16/07/16.
 */

/**
 * This annotation indicates that the endpoints requires an author authentication.
 */
@PreAuthorize("hasRole('ROLE_AUTHOR')")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface RequiresAuthorAuthentication {

}
