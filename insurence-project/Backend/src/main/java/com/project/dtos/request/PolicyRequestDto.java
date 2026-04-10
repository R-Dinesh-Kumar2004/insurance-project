package com.project.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyRequestDto {
    private String name;
    private String type;
    private double premium;
    private double coverageAmount;
    private  int waitingPeriod;
    private String benifits;
    private double claimLimit;
}
