package za.co.plan.service;

import org.springframework.http.ResponseEntity;
import za.co.plan.request.PlanGeneratorRequest;
import za.co.plan.response.PlanGeneratorResponse;

import java.util.List;

public interface PlanGeneratorService {
    ResponseEntity<List<PlanGeneratorResponse>> generatePlan(PlanGeneratorRequest planGeneratorRequest);
}
