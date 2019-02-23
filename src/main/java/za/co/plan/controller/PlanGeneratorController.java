package za.co.plan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.plan.request.PlanGeneratorRequest;
import za.co.plan.response.PlanGeneratorResponse;
import za.co.plan.service.PlanGeneratorServiceImpl;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/")
@Api(value = "Plan Generator Service ", description = "Operations related to Plan Generator Service")
@Validated
public class PlanGeneratorController {

    @Autowired
    PlanGeneratorServiceImpl planGeneratorServiceImpl;

    @ApiOperation(value = "View Repayment Plan", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Repayment Plan"),
            @ApiResponse(code = 401, message = "You are not authorized to view the Repayment Plan"),
            @ApiResponse(code = 403, message = "Access to the Repayment Plan you were trying is forbidden"),
            @ApiResponse(code = 404, message = "The Repayment Plan you were trying to check is not found")
    })
    @PostMapping(value = "/generate-plan", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlanGeneratorResponse>> generatePlan(@RequestBody PlanGeneratorRequest planGeneratorRequest) {
        if (isValid(planGeneratorRequest)) {
            return planGeneratorServiceImpl.generatePlan(planGeneratorRequest);
        } else {
            return new ResponseEntity<>((List<PlanGeneratorResponse>) null, HttpStatus.BAD_REQUEST);
        }
    }

    protected boolean isValid(PlanGeneratorRequest planGeneratorRequest) {
        boolean isValid = true;
        if (Objects.isNull(planGeneratorRequest) ||
                planGeneratorRequest.getStartDate() == null ||
                planGeneratorRequest.getLoanAmount() == null ||
                planGeneratorRequest.getDuration() == 0 ||
                planGeneratorRequest.getNominalRate() == 0.0) {
            isValid = false;
        }
        return isValid;
    }

}
