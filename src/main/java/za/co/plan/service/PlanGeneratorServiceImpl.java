package za.co.plan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.plan.request.PlanGeneratorRequest;
import za.co.plan.response.PlanGeneratorResponse;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlanGeneratorServiceImpl implements PlanGeneratorService {

    @Override
    public ResponseEntity<List<PlanGeneratorResponse>> generatePlan(PlanGeneratorRequest planGeneratorRequest) {
        try {
            Map<Long, PlanGeneratorResponse> paymentBeanMap = buildRepaymentPlan(planGeneratorRequest);
            List<PlanGeneratorResponse> planGeneratorResponses = paymentBeanMap.values().stream().collect(Collectors.toList());
            printRepaymentPlan(planGeneratorResponses);
            return new ResponseEntity<>(planGeneratorResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<PlanGeneratorResponse>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void printRepaymentPlan(List<PlanGeneratorResponse> planGeneratorResponses) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        log.info(ow.writeValueAsString(planGeneratorResponses));
    }

    private BigDecimal calculateAnnuity(double loanAmount, int termInMonths, double interestRate) {
        // Convert interest rate into a decimal
        interestRate /= 100.0;
        // Monthly interest rate
        // Yearly rate divided by 12 months
        double monthlyRate = interestRate / 12.0;
        // Calculate the monthly payment
        // this formula is provided
        double monthlyPayment =
                (loanAmount * monthlyRate) /
                        (1 - Math.pow(1 + monthlyRate, -termInMonths));
        return new BigDecimal(monthlyPayment, MathContext.DECIMAL64);
    }

    private Map<Long, PlanGeneratorResponse> buildRepaymentPlan(PlanGeneratorRequest planGeneratorRequest) {
        Map<Long, PlanGeneratorResponse> paymentBeanMap = new HashMap<>();
        BigDecimal annuity = calculateAnnuity(planGeneratorRequest.getLoanAmount().doubleValue(), planGeneratorRequest.getDuration(), planGeneratorRequest.getNominalRate());
        double installmentInterestRate = calculateInterest(planGeneratorRequest.getNominalRate(), planGeneratorRequest.getLoanAmount().doubleValue());
        BigDecimal remainingOutstandingPrinciple = annuity.subtract(new BigDecimal(installmentInterestRate));

        PlanGeneratorResponse firstPayment = buildPaymentBean(planGeneratorRequest.getStartDate(),
                annuity,
                remainingOutstandingPrinciple,
                BigDecimal.valueOf(installmentInterestRate),
                BigDecimal.valueOf(planGeneratorRequest.getLoanAmount().doubleValue()),
                BigDecimal.valueOf(planGeneratorRequest.getLoanAmount().doubleValue() - remainingOutstandingPrinciple.doubleValue()));
        paymentBeanMap.put(1L, firstPayment);

        for (int i = 1; i < planGeneratorRequest.getDuration(); i++) {

            PlanGeneratorResponse basePlanGeneratorResponse = paymentBeanMap.get(new Long(i));
            double updatedInterest = calculateInterest(planGeneratorRequest.getNominalRate(), basePlanGeneratorResponse.getRemainingOutstandingPrinciple().doubleValue());
            BigDecimal updatedPrincipal = calculatePrincipal(basePlanGeneratorResponse.getBorrowerPaymentAmount(), new BigDecimal(updatedInterest));

            PlanGeneratorResponse planGeneratorResponse = buildPaymentBean(
                    basePlanGeneratorResponse.getDate().plusMonths(1),
                    annuity,
                    updatedPrincipal,
                    BigDecimal.valueOf(updatedInterest),
                    basePlanGeneratorResponse.getRemainingOutstandingPrinciple(),
                    basePlanGeneratorResponse.getRemainingOutstandingPrinciple().subtract(updatedPrincipal));

            // Last Month Payment - Exit condition
            if (planGeneratorResponse.getRemainingOutstandingPrinciple().doubleValue() < 0) {
                planGeneratorResponse.setRemainingOutstandingPrinciple(BigDecimal.ZERO);
                planGeneratorResponse.setBorrowerPaymentAmount(planGeneratorResponse.getInitialOutstandingPrinciple().add(planGeneratorResponse.getInterest()));
                planGeneratorResponse.setPrinciple(planGeneratorResponse.getInitialOutstandingPrinciple());
            }
            paymentBeanMap.put(new Long(i + 1), planGeneratorResponse);
        }
        log.info("Repayment Plan Build Successfully for {} months ", planGeneratorRequest.getDuration());
        return paymentBeanMap;
    }

    private double calculateInterest(double interestRate, double outstandingPrinciple) {
        return (interestRate * 30 * outstandingPrinciple) / 36000;
    }

    private BigDecimal calculatePrincipal(BigDecimal annuity, BigDecimal interest) {
        return annuity.subtract(interest);
    }

    private PlanGeneratorResponse buildPaymentBean(LocalDateTime paymentDate,
                                                   BigDecimal annuity,
                                                   BigDecimal principle,
                                                   BigDecimal interestRate,
                                                   BigDecimal initialOutstandingPrinciple,
                                                   BigDecimal remainingOutstandingPrinciple) {
        return PlanGeneratorResponse.builder()
                .date(paymentDate)
                .borrowerPaymentAmount(annuity.setScale(2, BigDecimal.ROUND_HALF_EVEN))
                .principle(principle.setScale(2, BigDecimal.ROUND_HALF_EVEN))
                .interest(interestRate.setScale(2, BigDecimal.ROUND_HALF_EVEN))
                .initialOutstandingPrinciple(initialOutstandingPrinciple.setScale(2, BigDecimal.ROUND_HALF_EVEN))
                .remainingOutstandingPrinciple(remainingOutstandingPrinciple.setScale(2, BigDecimal.ROUND_HALF_EVEN)).build();
    }


}
