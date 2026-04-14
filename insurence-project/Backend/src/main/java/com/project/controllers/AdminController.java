package com.project.controllers;

import com.project.dtos.request.PolicyRequestDto;
import com.project.dtos.response.ApiResponseDto;
import com.project.dtos.response.PolicyResponseDto;
import com.project.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/policies")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<PolicyResponseDto>> createPolicy(
            @RequestBody PolicyRequestDto policyRequestDto) {

        PolicyResponseDto policy = adminService.create(policyRequestDto);

        ApiResponseDto<PolicyResponseDto> response =
                new ApiResponseDto<>(200, "Policy created successfully", policy);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<PolicyResponseDto>>> getAllPolicies() {

        List<PolicyResponseDto> policies = adminService.getAllPolicies();

        ApiResponseDto<List<PolicyResponseDto>> response =
                new ApiResponseDto<>(200, "All policies fetched successfully", policies);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deletePolicy(@PathVariable Long id) {

        String message = adminService.deleteById(id);

        ApiResponseDto<String> response =
                new ApiResponseDto<>(200, message, null);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<PolicyResponseDto>> updatePolicy(
            @PathVariable Long id,
            @RequestBody PolicyRequestDto policyRequestDto) {

        PolicyResponseDto updatedPolicy =
                adminService.updateById(id, policyRequestDto);

        ApiResponseDto<PolicyResponseDto> response =
                new ApiResponseDto<>(200, "Policy updated successfully", updatedPolicy);

        return ResponseEntity.ok(response);
    }

}
