package br.com.treld.validator.post;

import br.com.treld.model.Post;
import br.com.treld.validator.ValidationResult;

import java.util.function.Function;

/**
 * @author rsouza on 25/02/17.
 */
public interface PostValidator extends Function<Post, ValidationResult> {
}
