package com.project.dtos.response;

import com.project.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyResponseDto {
    private long id;
    private String name;
    private String type;
    private double premium;
    private double coverageAmount;
    private  int waitingPeriod;
    private String benifits;
    private double claimLimit;
}
