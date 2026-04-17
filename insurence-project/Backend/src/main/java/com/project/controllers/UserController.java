package com.project.controllers;

import com.project.dtos.request.UpdateRequestDto;
import com.project.dtos.response.ApiResponseDto;
import com.project.dtos.response.PolicyResponseDto;
import com.project.dtos.response.UserResponseDto;
import com.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> viewProfile(@PathVariable Long id) {
        UserResponseDto user = userService.viewProfile(id);

        ApiResponseDto<UserResponseDto> response =
                new ApiResponseDto<>(200, "User profile fetched successfully", user);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> updateProfile(
            @PathVariable Long id,
            @RequestBody UpdateRequestDto updateRequestDto) {

        UserResponseDto updatedUser = userService.updateProfile(id, updateRequestDto);

        ApiResponseDto<UserResponseDto> response =
                new ApiResponseDto<>(200, "User profile updated successfully", updatedUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/policy/{policyId}")
    public ResponseEntity<ApiResponseDto<PolicyResponseDto>> getPolicyById(@PathVariable Long policyId) {
        PolicyResponseDto policy = userService.getPolicyById(policyId);

        ApiResponseDto<PolicyResponseDto> response =
                new ApiResponseDto<>(200, "Policy fetched successfully", policy);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/policies/type")
    public ResponseEntity<ApiResponseDto<List<PolicyResponseDto>>> getPoliciesByType(
            @RequestParam String type) {

        List<PolicyResponseDto> policies = userService.getPoliciesByType(type);

        ApiResponseDto<List<PolicyResponseDto>> response =
                new ApiResponseDto<>(200, "Policies fetched by type", policies);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/policies/premium")
    public ResponseEntity<ApiResponseDto<List<PolicyResponseDto>>> getPoliciesByPremiumRange(
            @RequestParam double min,
            @RequestParam double max) {

        List<PolicyResponseDto> policies =
                userService.getPoliciesByPremiumRange(min, max);

        ApiResponseDto<List<PolicyResponseDto>> response =
                new ApiResponseDto<>(200, "Policies fetched by premium range", policies);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/policies/coverage")
    public ResponseEntity<ApiResponseDto<List<PolicyResponseDto>>> getPoliciesByCoverageRange(
            @RequestParam double min,
            @RequestParam double max) {

        List<PolicyResponseDto> policies =
                userService.getPoliciesByCoverageAmountRange(min, max);

        ApiResponseDto<List<PolicyResponseDto>> response =
                new ApiResponseDto<>(200, "Policies fetched by coverage range", policies);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/policies")
    public ResponseEntity<ApiResponseDto<List<PolicyResponseDto>>> getAllPolicies() {
        List<PolicyResponseDto> policies = userService.getAllPolicies();

        ApiResponseDto<List<PolicyResponseDto>> response =
                new ApiResponseDto<>(200, "All policies fetched successfully", policies);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/policies/compare")
    public ResponseEntity<ApiResponseDto<PolicyResponseDto>> comparePolicies(
            @RequestBody List<Long> policyIds) {

        PolicyResponseDto bestPolicy = userService.comparePolicies(policyIds);

        ApiResponseDto<PolicyResponseDto> response =
                new ApiResponseDto<>(200, "Best policy selected", bestPolicy);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/policies/compare/ai")
    public ResponseEntity<ApiResponseDto<String>> comparePoliciesWithAi(
            @RequestBody List<Long> policyIds) {

        String result = userService.comparePoliciesWithAi(policyIds);

        ApiResponseDto<String> response =
                new ApiResponseDto<>(200, "AI comparison result", result);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/purchase/{policyId}")
    public ResponseEntity<ApiResponseDto<String>> purchasePolicy(@PathVariable Long policyId) {
        String result = userService.purchasePolicy(policyId);

        ApiResponseDto<String> response =
                new ApiResponseDto<>(200, "Policy purchase result", result);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-policies")
    public ResponseEntity<ApiResponseDto<List<PolicyResponseDto>>> getMyPolicies() {
        List<PolicyResponseDto> policies = userService.getMyPolicies();

        ApiResponseDto<List<PolicyResponseDto>> response =
                new ApiResponseDto<>(200, "Policies fetched for user", policies);

        return ResponseEntity.ok(response);
    }
}