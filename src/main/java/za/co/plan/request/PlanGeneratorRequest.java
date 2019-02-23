package za.co.plan.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PlanGeneratorRequest {
    BigDecimal loanAmount;
    double nominalRate;
    int duration;
    LocalDateTime startDate;
}
