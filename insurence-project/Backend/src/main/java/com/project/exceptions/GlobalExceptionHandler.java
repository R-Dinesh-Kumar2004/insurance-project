package com.project.exceptions;

import com.project.dtos.response.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

     @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<ApiResponseDto<String>> handleResourceNotFound(ResourceNotFoundException ex) {
         ApiResponseDto<String> response = new ApiResponseDto<>(404, ex.getMessage(), null);
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
     }

     @ExceptionHandler(Exception.class)
     public ResponseEntity<ApiResponseDto<String>> handleGenericException(Exception ex) {
         ApiResponseDto<String> response = new ApiResponseDto<>(500, "An unexpected error occurred", null);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
     }

     @ExceptionHandler(InvalidUsernameOrPasswordException.class)
     public ResponseEntity<ApiResponseDto<String>> handleInvalidUsernameOrPassword(InvalidUsernameOrPasswordException ex) {
         ApiResponseDto<String> response = new ApiResponseDto<>(401, ex.getMessage(), null);
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
     }

     @ExceptionHandler(UserAlreadyExistException.class)
     public ResponseEntity<ApiResponseDto<String>> handleUserAlreadyExist(UserAlreadyExistException ex) {
         ApiResponseDto<String> response = new ApiResponseDto<>(409, ex.getMessage(), null);
         return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
     }

     @ExceptionHandler(IllegalArgumentException.class)
     public ResponseEntity<ApiResponseDto<String>> handleBadRequest(Exception ex) {
         ApiResponseDto<String> response = new ApiResponseDto<>(400, ex.getMessage(), null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
     }

     @ExceptionHandler(PolicyAlreadyExistException.class)
     public ResponseEntity<ApiResponseDto<String>> handlePolicyAlreadyExists(PolicyAlreadyExistException ex) {
         ApiResponseDto<String> response = new ApiResponseDto<>(409, ex.getMessage(), null);
         return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
     }

     @ExceptionHandler(InvalidCredentialsException.class)
     public ResponseEntity<ApiResponseDto<String>> handleInvalidCredentials(InvalidCredentialsException ex) {
         ApiResponseDto<String> response = new ApiResponseDto<>(401, ex.getMessage(), null);
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
     }
}
