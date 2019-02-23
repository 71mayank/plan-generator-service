# plan-generator-service

**PREREQUISITE TO RUN THE plan-generator-service**

    1. Git version control software should be installed in the System
    2. Maven should be installed and setup correctly in environment variable.
    
**APPLICATION DEPENDENCIES**

    1. JDK 8
    2. Spring Boot 2.0
    3. Spring Restful Service
    4. Spring fox Swagger 2.0
    5. Lombok
    
**STEPS TO RUN ON LOCAL**

    1. Clone plan generator service in your system using git clone https://github.com/71mayank/plan-generator-service.git
    2. Navigate to plan-generator-service folder and run command mvn spring-boot:run
    3. Browse url http://localhost:8080/swagger-ui.html
    
**SAMPLE REQUEST**

    curl -X 
    POST "http://localhost:8080/generate-plan" 
    -H "accept: application/json" 
    -H "Content-Type: application/json" 
    -d "{ \"loanAmount\": 5000, \"nominalRate\": 5, \"duration\": 24, \"startDate\": \"2019-02-23T00:46:58.984Z\"}"
    
**SAMPLE RESPONSE**
  ```
  [
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-02-23T00:46:58.984",
    "initialOutstandingPrinciple": 5000,
    "interest": 20.83,
    "principle": 198.52,
    "remainingOutstandingPrinciple": 4801.48
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-03-23T00:46:58.984",
    "initialOutstandingPrinciple": 4801.48,
    "interest": 20.01,
    "principle": 199.35,
    "remainingOutstandingPrinciple": 4602.13
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-04-23T00:46:58.984",
    "initialOutstandingPrinciple": 4602.13,
    "interest": 19.18,
    "principle": 200.18,
    "remainingOutstandingPrinciple": 4401.95
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-05-23T00:46:58.984",
    "initialOutstandingPrinciple": 4401.95,
    "interest": 18.34,
    "principle": 201.02,
    "remainingOutstandingPrinciple": 4200.93
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-06-23T00:46:58.984",
    "initialOutstandingPrinciple": 4200.93,
    "interest": 17.5,
    "principle": 201.86,
    "remainingOutstandingPrinciple": 3999.07
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-07-23T00:46:58.984",
    "initialOutstandingPrinciple": 3999.07,
    "interest": 16.66,
    "principle": 202.7,
    "remainingOutstandingPrinciple": 3796.37
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-08-23T00:46:58.984",
    "initialOutstandingPrinciple": 3796.37,
    "interest": 15.82,
    "principle": 203.54,
    "remainingOutstandingPrinciple": 3592.83
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-09-23T00:46:58.984",
    "initialOutstandingPrinciple": 3592.83,
    "interest": 14.97,
    "principle": 204.39,
    "remainingOutstandingPrinciple": 3388.44
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-10-23T00:46:58.984",
    "initialOutstandingPrinciple": 3388.44,
    "interest": 14.12,
    "principle": 205.24,
    "remainingOutstandingPrinciple": 3183.2
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-11-23T00:46:58.984",
    "initialOutstandingPrinciple": 3183.2,
    "interest": 13.26,
    "principle": 206.1,
    "remainingOutstandingPrinciple": 2977.1
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2019-12-23T00:46:58.984",
    "initialOutstandingPrinciple": 2977.1,
    "interest": 12.4,
    "principle": 206.96,
    "remainingOutstandingPrinciple": 2770.14
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-01-23T00:46:58.984",
    "initialOutstandingPrinciple": 2770.14,
    "interest": 11.54,
    "principle": 207.82,
    "remainingOutstandingPrinciple": 2562.32
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-02-23T00:46:58.984",
    "initialOutstandingPrinciple": 2562.32,
    "interest": 10.68,
    "principle": 208.68,
    "remainingOutstandingPrinciple": 2353.64
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-03-23T00:46:58.984",
    "initialOutstandingPrinciple": 2353.64,
    "interest": 9.81,
    "principle": 209.55,
    "remainingOutstandingPrinciple": 2144.09
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-04-23T00:46:58.984",
    "initialOutstandingPrinciple": 2144.09,
    "interest": 8.93,
    "principle": 210.43,
    "remainingOutstandingPrinciple": 1933.66
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-05-23T00:46:58.984",
    "initialOutstandingPrinciple": 1933.66,
    "interest": 8.06,
    "principle": 211.3,
    "remainingOutstandingPrinciple": 1722.36
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-06-23T00:46:58.984",
    "initialOutstandingPrinciple": 1722.36,
    "interest": 7.18,
    "principle": 212.18,
    "remainingOutstandingPrinciple": 1510.18
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-07-23T00:46:58.984",
    "initialOutstandingPrinciple": 1510.18,
    "interest": 6.29,
    "principle": 213.07,
    "remainingOutstandingPrinciple": 1297.11
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-08-23T00:46:58.984",
    "initialOutstandingPrinciple": 1297.11,
    "interest": 5.4,
    "principle": 213.96,
    "remainingOutstandingPrinciple": 1083.15
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-09-23T00:46:58.984",
    "initialOutstandingPrinciple": 1083.15,
    "interest": 4.51,
    "principle": 214.85,
    "remainingOutstandingPrinciple": 868.3
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-10-23T00:46:58.984",
    "initialOutstandingPrinciple": 868.3,
    "interest": 3.62,
    "principle": 215.74,
    "remainingOutstandingPrinciple": 652.56
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-11-23T00:46:58.984",
    "initialOutstandingPrinciple": 652.56,
    "interest": 2.72,
    "principle": 216.64,
    "remainingOutstandingPrinciple": 435.92
  },
  {
    "borrowerPaymentAmount": 219.36,
    "date": "2020-12-23T00:46:58.984",
    "initialOutstandingPrinciple": 435.92,
    "interest": 1.82,
    "principle": 217.54,
    "remainingOutstandingPrinciple": 218.38
  },
  {
    "borrowerPaymentAmount": 219.29,
    "date": "2021-01-23T00:46:58.984",
    "initialOutstandingPrinciple": 218.38,
    "interest": 0.91,
    "principle": 218.38,
    "remainingOutstandingPrinciple": 0
  }
]