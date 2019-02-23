package za.co.plan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlanGeneratorException extends Exception {
    private static final long serialVersionUID = 1L;
    public PlanGeneratorException(String message) {
        super(message);
    }
}