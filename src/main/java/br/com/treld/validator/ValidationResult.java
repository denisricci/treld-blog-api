package br.com.treld.validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author rsouza on 25/02/17.
 */
public final class ValidationResult {

    private static final ValidationResult okResult;

    private boolean ok;
    private List<String> errors;

    static {
        ValidationResult result = new ValidationResult();
        result.ok = true;

        okResult = result;
    }

    private ValidationResult(){
        // ok
    }

    public ValidationResult (String... errors){
        this(Arrays.asList(errors));
    }

    public static ValidationResult ok(){
        return okResult;
    }

    public ValidationResult(List<String> errors){
        this.errors = Collections.unmodifiableList(errors);
        this.ok = false;
    }

    public boolean hasErrors(){
        return !isOk();
    }

    public boolean isOk() {
        return ok;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
