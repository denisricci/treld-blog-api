package br.com.treld.validator.post;

import br.com.treld.model.Post;
import br.com.treld.validator.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author rsouza on 25/02/17.
 */
@Component
public class PostTagsValidator implements PostValidator {

    @Override
    public ValidationResult apply(Post post) {
        if(CollectionUtils.isEmpty(post.getTags())) return ValidationResult.ok();

        for (String tag : post.getTags()) {
            if(StringUtils.isEmpty(tag)) return new ValidationResult("Empty tags");

            if(tag.contains(",")) return new ValidationResult("Tags contains invalid characters");
        }


        return ValidationResult.ok();
    }

}
