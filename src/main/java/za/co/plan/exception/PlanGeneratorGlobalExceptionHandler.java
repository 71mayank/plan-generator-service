package za.co.plan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@ControllerAdvice
public class PlanGeneratorGlobalExceptionHandler {

    @ExceptionHandler(PlanGeneratorException.class)
    public ResponseEntity<?> resourceNotFoundException(PlanGeneratorException ex, WebRequest request) {
        PlanGeneratorErrorDetails planGeneratorErrorDetails = new PlanGeneratorErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(planGeneratorErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        PlanGeneratorErrorDetails planGeneratorErrorDetails = new PlanGeneratorErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(planGeneratorErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

