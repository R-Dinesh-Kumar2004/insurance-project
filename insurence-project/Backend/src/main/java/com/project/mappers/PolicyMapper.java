package com.project.mappers;

import com.project.dtos.request.PolicyRequestDto;
import com.project.dtos.response.PolicyResponseDto;
import com.project.entities.Policy;
import com.project.enums.Type;

public class PolicyMapper {

    public Policy policyRequestDtoToPolicy(PolicyRequestDto policyRequestDto){
        Policy policy = new Policy();

        policy.setName(policyRequestDto.getName());
        policy.setPremium(policyRequestDto.getPremium());
        policy.setBenifits(policyRequestDto.getBenifits());
        policy.setClaimLimit(policyRequestDto.getClaimLimit());
        policy.setCoverageAmount(policyRequestDto.getCoverageAmount());
        policy.setWaitingPeriod(policyRequestDto.getWaitingPeriod());

        switch (policyRequestDto.getType()) {
            case "HEALTH" -> policy.setType(Type.HEALTH);
            case "LIFE" -> policy.setType(Type.LIFE);
            case "VEHICLE" -> policy.setType(Type.VEHICLE);
        }

        return policy;
    }

    public PolicyResponseDto policyToPolicyResponseDto(Policy policy){
        PolicyResponseDto dto = new PolicyResponseDto();

        dto.setId(policy.getId());
        dto.setName(policy.getName());
        dto.setPremium(policy.getPremium());
        dto.setCoverageAmount(policy.getCoverageAmount());
        dto.setClaimLimit(policy.getClaimLimit());
        dto.setWaitingPeriod(policy.getWaitingPeriod());
        dto.setBenifits(policy.getBenifits());

        switch (policy.getType()) {
            case HEALTH -> dto.setType("HEALTH");
            case LIFE -> dto.setType("LIFE");
            case VEHICLE -> dto.setType("VEHICLE");
        }
        return dto;
    }

}