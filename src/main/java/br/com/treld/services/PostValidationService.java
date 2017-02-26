package br.com.treld.services;

import br.com.treld.model.Post;
import br.com.treld.validator.ValidationResult;

/**
 * @author rsouza on 25/02/17.
 */
public interface PostValidationService {

    ValidationResult validate(Post post);

}
