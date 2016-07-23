package br.com.treld.repository;

import br.com.treld.model.FileLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rsouza on 23/07/16.
 */
public interface FileLocationRepository extends MongoRepository<FileLocation, String> {

}
