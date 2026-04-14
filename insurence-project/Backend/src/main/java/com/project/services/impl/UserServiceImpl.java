package com.project.services.impl;

import com.project.dtos.request.UpdateRequestDto;
import com.project.dtos.response.PolicyResponseDto;
import com.project.dtos.response.UserResponseDto;
import com.project.entities.Policy;
import com.project.entities.User;
import com.project.mappers.PolicyMapper;
import com.project.mappers.UserMapper;
import com.project.repositories.PolicyRepository;
import com.project.repositories.UserRepository;
import com.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final double PREMIUM_WEIGHT = 0.3;
    private static final double COVERAGE_WEIGHT = 0.3;
    private static final double WAITING_WEIGHT = 0.2;
    private static final double CLAIM_WEIGHT = 0.2;

    @Autowired
    PolicyRepository policyRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto viewProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.toUserResponseDto(user);
    }

    @Override
    public UserResponseDto updateProfile(Long id, UpdateRequestDto registerRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(registerRequestDto.getName());
        user.setEmail(registerRequestDto.getEmail());

        userRepository.save(user);

        return UserMapper.toUserResponseDto(user);
    }

    @Override
    public PolicyResponseDto getPolicyById(Long policyId) {
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        return PolicyMapper.toPolicyResponseDto(policy);
    }

    @Override
    public List<PolicyResponseDto> getPoliciesByType(String type) {
        List<Policy> policies = policyRepository.findByType(type);

        List<PolicyResponseDto> policyResponseDtos = policies.stream()
                .map(PolicyMapper::toPolicyResponseDto)
                .toList();

        return policyResponseDtos;
    }

    @Override
    public List<PolicyResponseDto> getPoliciesByPremiumRange(double minPremium, double maxPremium) {
        List<Policy> policies = policyRepository.findAllByPremiumBetween(minPremium, maxPremium);

        List<PolicyResponseDto> policyResponseDtos = policies.stream()
                .map(PolicyMapper::toPolicyResponseDto)
                .toList();

        return policyResponseDtos;
    }

    @Override
    public List<PolicyResponseDto> getPoliciesByCoverageAmountRange(double minCoverageAmount, double maxCoverageAmount) {
        List<Policy> policies = policyRepository.findAllByCoverageAmountBetween(minCoverageAmount, maxCoverageAmount);

        List<PolicyResponseDto> policyResponseDtos = policies.stream()
                .map(PolicyMapper::toPolicyResponseDto)
                .toList();

        return policyResponseDtos;
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
    public PolicyResponseDto comparePolicies(List<Long> policyIds) {
        List<Policy> policies = policyRepository.findAllById(policyIds);
        return getBestPolicy(policies);
    }

    @Override
    public String comparePoliciesWithAi(List<Long> policyIds) {
        return null;
    }


    public PolicyResponseDto getBestPolicy(List<Policy> policies) {

        double maxPremium = policies.stream().mapToDouble(Policy::getPremium).max().orElse(1);
        double minPremium = policies.stream().mapToDouble(Policy::getPremium).min().orElse(0);

        double maxCoverage = policies.stream().mapToDouble(Policy::getCoverageAmount).max().orElse(1);
        double minCoverage = policies.stream().mapToDouble(Policy::getCoverageAmount).min().orElse(0);

        double maxWaiting = policies.stream().mapToDouble(Policy::getWaitingPeriod).max().orElse(1);
        double minWaiting = policies.stream().mapToDouble(Policy::getWaitingPeriod).min().orElse(0);

        double maxClaim = policies.stream().mapToDouble(Policy::getClaimLimit).max().orElse(1);
        double minClaim = policies.stream().mapToDouble(Policy::getClaimLimit).min().orElse(0);

        Policy bestPolicy = null;
        double bestScore = -1;

        for (Policy p : policies) {

            double premiumScore = 1 - ((p.getPremium() - minPremium) / (maxPremium - minPremium));
            double coverageScore = (p.getCoverageAmount() - minCoverage) / (maxCoverage - minCoverage);
            double waitingScore = 1 - ((p.getWaitingPeriod() - minWaiting) / (maxWaiting - minWaiting));
            double claimScore = (p.getClaimLimit() - minClaim) / (maxClaim - minClaim);

            double totalScore =
                    premiumScore * PREMIUM_WEIGHT +
                            coverageScore * COVERAGE_WEIGHT +
                            waitingScore * WAITING_WEIGHT +
                            claimScore * CLAIM_WEIGHT;

            if (totalScore > bestScore) {
                bestScore = totalScore;
                bestPolicy = p;
            }
        }

        return PolicyMapper.toPolicyResponseDto(bestPolicy);
    }
}