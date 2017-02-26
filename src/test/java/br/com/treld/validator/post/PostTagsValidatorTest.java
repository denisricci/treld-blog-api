package br.com.treld.validator.post;

import br.com.treld.model.Post;
import br.com.treld.validator.ValidationResult;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author rsouza on 25/02/17.
 */
public class PostTagsValidatorTest {

    private PostTagsValidator postTagsValidator = new PostTagsValidator();

    @Test
    public void emptyTagsCollectionValidationTest(){
        ValidationResult result = postTagsValidator.apply(new Post());

        assertEquals(ValidationResult.ok(), result);
    }

    @Test
    public void validTagsValidationTest(){
        Post post = new Post();
        post.setTags(Arrays.asList("java", "mongod", "javad"));
        ValidationResult result = postTagsValidator.apply(post);

        assertEquals(ValidationResult.ok(), result);
    }

    @Test
    public void invalidCharacterOnTagsValidationTest(){
        Post post = new Post();
        post.setTags(Arrays.asList("Java,", "scala"));
        ValidationResult result = postTagsValidator.apply(post);

        assertTrue(result.hasErrors());
    }

    @Test
    public void invalidValueForTagValidationTest(){
        Post post = new Post();
        post.setTags(Arrays.asList(""));
        ValidationResult result = postTagsValidator.apply(post);

        assertTrue(result.hasErrors());
    }

}