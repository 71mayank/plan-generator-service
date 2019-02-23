
package za.co.plan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.plan.request.PlanGeneratorRequest;
import za.co.plan.response.PlanGeneratorResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanGeneratorServiceImplTest {

    @Autowired
    private PlanGeneratorServiceImpl planGeneratorServiceImpl;

    private PlanGeneratorRequest buildPlanGeneratorRequest() {
        return PlanGeneratorRequest.builder().
                startDate(LocalDateTime.now()).
                duration(24).
                nominalRate(5).
                loanAmount(BigDecimal.valueOf(5000.00)).build();
    }

    @Test
    public void testGeneratePlanSuccess() {
        ResponseEntity<List<PlanGeneratorResponse>> listResponseEntity = planGeneratorServiceImpl.generatePlan(buildPlanGeneratorRequest());
        assertThat(listResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testGeneratePlanFailure() {
        ResponseEntity<List<PlanGeneratorResponse>> listResponseEntity = planGeneratorServiceImpl.generatePlan(null);
        assertThat(listResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

