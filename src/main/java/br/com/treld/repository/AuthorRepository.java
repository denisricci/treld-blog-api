package br.com.treld.repository;

import br.com.treld.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rsouza on 16/07/16.
 */
public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByUsername(String username);
}
