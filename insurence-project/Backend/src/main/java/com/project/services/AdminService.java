package com.project.services;

import com.project.dtos.request.PolicyRequestDto;
import com.project.dtos.response.PolicyResponseDto;
import com.project.entities.Policy;

import java.util.List;

public interface AdminService {
    public PolicyResponseDto create(PolicyRequestDto policyRequestDto);
    public List<PolicyResponseDto> getAllPolicies();
    public String deleteById(Long id);
    public PolicyResponseDto updateById(Long id,PolicyRequestDto policyRequestDto);
}
