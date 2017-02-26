package br.com.treld.services.impl;

import br.com.treld.model.Post;
import br.com.treld.services.PostValidationService;
import br.com.treld.validator.ValidationResult;
import br.com.treld.validator.post.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rsouza on 25/02/17.
 */
@Component
public class DefaultPostValidationService implements PostValidationService {

    @Autowired
    private List<PostValidator> postValidatorList;

    public ValidationResult validate(Post post){
        for (PostValidator postValidator : postValidatorList) {
            ValidationResult result = postValidator.apply(post);

            if(result.hasErrors()) return result;
        }

        return ValidationResult.ok();
    }

}
