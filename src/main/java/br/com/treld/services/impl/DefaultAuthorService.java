package br.com.treld.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treld.model.Author;
import br.com.treld.repository.AuthorRepository;
import br.com.treld.services.AuthorService;
import br.com.treld.utils.CryptographyUtils;

/**
 * Created by edubranquinho on 25/07/16.
 */
@Component
public class DefaultAuthorService implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Author save(Author author) {
		author.setPassword(CryptographyUtils.encrypt(author.getPassword()));
		return authorRepository.save(author);
	}

	@Override
	public Author findByUrl(String username) {
		return authorRepository.findByUsername(username);
	}

}
