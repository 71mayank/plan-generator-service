package za.co.plan.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "borrowerPaymentAmount",
        "date",
        "initialOutstandingPrinciple",
        "interest",
        "principle",
        "remainingOutstandingPrinciple"
})
public class PlanGeneratorResponse {
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime date;
    BigDecimal borrowerPaymentAmount;
    BigDecimal principle;
    BigDecimal interest;
    BigDecimal initialOutstandingPrinciple;
    BigDecimal remainingOutstandingPrinciple;
}
