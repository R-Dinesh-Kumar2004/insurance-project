package com.project.services.impl;

import com.project.dtos.request.PolicyRequestDto;
import com.project.dtos.response.PolicyResponseDto;
import com.project.entities.Policy;
import com.project.exceptions.PolicyAlreadyExistException;
import com.project.mappers.PolicyMapper;
import com.project.repositories.PolicyRepository;
import com.project.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    PolicyRepository policyRepository;

    @Override
    public PolicyResponseDto create(PolicyRequestDto policyRequestDto) {
        String name = policyRequestDto.getName();
        Policy policy = policyRepository.findByName(name);

        if(policy != null){
            throw new PolicyAlreadyExistException("Policy with name "+name+" is already exist");
        }

        Policy newPolicy = PolicyMapper.toPolicy(policyRequestDto);
        policyRepository.save(newPolicy);

        return PolicyMapper.toPolicyResponseDto(newPolicy);
    }

    @Override
    public List<PolicyResponseDto> getAllPolicies() {
        List<Policy> policies = policyRepository.findAll();

        List<PolicyResponseDto> policyResponseDtos = policies.stream()
                .map(PolicyMapper::toPolicyResponseDto)
                .toList();

        return policyResponseDtos;
    }

    @Override
    public String deleteById(Long id) {
        Optional<Policy> policy = policyRepository.findById(id);

        if(policy.isEmpty()){
            throw new PolicyAlreadyExistException("Policy with id "+id+" is not found");
        }

        policyRepository.deleteById(id);

        return "Policy with id "+id+" is deleted successfully";
    }

    @Override
    public PolicyResponseDto updateById(Long id, PolicyRequestDto policyRequestDto) {
        Optional<Policy> policy = policyRepository.findById(id);

        if(policy.isEmpty()){
            throw new PolicyAlreadyExistException("Policy with id "+id+" is not found");
        }

        Policy updatedPolicy = policy.get();
        updatedPolicy.setName(policyRequestDto.getName());
        updatedPolicy.setPremium(policyRequestDto.getPremium());
        updatedPolicy.setCoverageAmount(policyRequestDto.getCoverageAmount());
        updatedPolicy.setWaitingPeriod(policyRequestDto.getWaitingPeriod());
        updatedPolicy.setBenifits(policyRequestDto.getBenifits());
        updatedPolicy.setClaimLimit(policyRequestDto.getClaimLimit());

        policyRepository.save(updatedPolicy);

        return PolicyMapper.toPolicyResponseDto(updatedPolicy);
    }
}