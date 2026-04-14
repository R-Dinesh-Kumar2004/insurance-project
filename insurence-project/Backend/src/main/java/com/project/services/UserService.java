package com.project.services;

import com.project.dtos.request.UpdateRequestDto;
import com.project.dtos.response.PolicyResponseDto;
import com.project.dtos.response.UserResponseDto;

import java.util.List;

public interface UserService {
    public UserResponseDto viewProfile(Long id);
    public UserResponseDto updateProfile(Long id, UpdateRequestDto registerRequestDto);
    public PolicyResponseDto getPolicyById(Long policyId);
    public List<PolicyResponseDto> getPoliciesByType(String type);
    public List<PolicyResponseDto> getPoliciesByPremiumRange(double minPremium, double maxPremium);
    public List<PolicyResponseDto> getPoliciesByCoverageAmountRange(double minCoverageAmount, double maxCoverageAmount);
    public List<PolicyResponseDto> getAllPolicies();
    public PolicyResponseDto comparePolicies(List<Long> policyIds);
    public String comparePoliciesWithAi(List<Long> policyIds);
}
