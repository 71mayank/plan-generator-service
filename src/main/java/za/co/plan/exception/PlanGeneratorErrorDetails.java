package za.co.plan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class PlanGeneratorErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
