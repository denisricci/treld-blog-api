package br.com.treld.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.treld.model.Author;

/**
 * Created by rsouza on 16/07/16.
 */
public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByUsername(String username);
}
