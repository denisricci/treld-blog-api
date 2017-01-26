package br.com.treld.services;

import br.com.treld.model.User;

/**
 * Created by edubranquinho on 25/07/16.
 */
public interface AuthorService {

	User save(User author);

	public User findByUsername(String username);

}
