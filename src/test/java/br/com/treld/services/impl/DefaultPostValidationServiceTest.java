package br.com.treld.services.impl;

import br.com.treld.model.Post;
import br.com.treld.validator.ValidationResult;
import br.com.treld.validator.post.PostValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author rsouza on 25/02/17.
 */
public class DefaultPostValidationServiceTest {

    private DefaultPostValidationService postValidationService;

    @Before
    public void setUp(){
        postValidationService = new DefaultPostValidationService();
    }

    @Test
    public void validateTest() throws Exception {
        ArrayList<PostValidator> validators = new ArrayList<>();

        validators.add(p -> ValidationResult.ok());

        ReflectionTestUtils.setField(postValidationService, "postValidatorList", validators);

        ValidationResult result = postValidationService.validate(new Post());
        assertEquals(ValidationResult.ok(), result);
    }

    @Test
    public void validateErrorTest() throws Exception {
        ArrayList<PostValidator> validators = new ArrayList<>();

        String errorMessage = "ERROR TEST";

        validators.add(p -> ValidationResult.ok());
        validators.add(p -> new ValidationResult(errorMessage));

        ReflectionTestUtils.setField(postValidationService, "postValidatorList", validators);

        ValidationResult result = postValidationService.validate(new Post());
        assertTrue(result.hasErrors());
        assertEquals(1, result.getErrors().size());
        assertEquals(errorMessage, result.getErrors().get(0));
    }

}