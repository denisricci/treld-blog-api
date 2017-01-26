package br.com.treld.services;

import br.com.treld.model.Author;

/**
 * Created by edubranquinho on 25/07/16.
 */
public interface AuthorService {

	Author save(Author author);

	public Author findByUsername(String username);

}
