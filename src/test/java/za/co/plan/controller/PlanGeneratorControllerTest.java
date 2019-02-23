package za.co.plan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import za.co.plan.request.PlanGeneratorRequest;
import za.co.plan.response.PlanGeneratorResponse;
import za.co.plan.service.PlanGeneratorServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlanGeneratorControllerTest {

    MockMvc mockMvc;

    @MockBean
    protected WebApplicationContext webApplicationContext;

    @MockBean
    PlanGeneratorController planGeneratorController;

    @MockBean
    PlanGeneratorServiceImpl planGeneratorServiceImpl;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.planGeneratorController).build();
    }


    private String buildPlanGeneratorRequestJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(PlanGeneratorRequest.builder().
                startDate(LocalDateTime.now()).
                duration(24).
                nominalRate(5).
                loanAmount(BigDecimal.valueOf(5000.00)));
    }

    @Test
    public void testGeneratePlanOk() throws Exception {
        ArrayList<PlanGeneratorResponse> planGeneratorResponses = new ArrayList<>();
        PlanGeneratorResponse planGeneratorResponse = new PlanGeneratorResponse();
        planGeneratorResponses.add(planGeneratorResponse);
        ResponseEntity<List<PlanGeneratorResponse>> planGeneratorResponseResponseEntity =
                new ResponseEntity<>(planGeneratorResponses, HttpStatus.OK);
        when(planGeneratorServiceImpl.generatePlan(new PlanGeneratorRequest(new BigDecimal(5000), 5, 24, LocalDateTime.now()))).
                thenReturn(planGeneratorResponseResponseEntity);
        mockMvc.perform(post("/generate-plan")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(buildPlanGeneratorRequestJsonString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGeneratePlanBadRequest() throws Exception {
        ResponseEntity<List<PlanGeneratorResponse>> planGeneratorResponseResponseEntity =
                new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        when(planGeneratorServiceImpl.generatePlan(null)).
                thenReturn(planGeneratorResponseResponseEntity);
        mockMvc.perform(post("/generate-plan")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(""))
                .andExpect(status().isBadRequest());
    }




}


