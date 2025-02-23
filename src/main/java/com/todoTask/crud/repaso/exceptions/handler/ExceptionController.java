package com.todoTask.crud.repaso.exceptions.handler;

import com.todoTask.crud.repaso.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.todoTask.crud.repaso.exceptions.layouts.Exception> handleException(Exception ex, HttpServletRequest request) {
        com.todoTask.crud.repaso.exceptions.layouts.Exception errorResponse = new com.todoTask.crud.repaso.exceptions.layouts.Exception(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CantCreateShiftException.class, ConflictDatesException.class, DateOverTheHourException.class})
    public ResponseEntity<com.todoTask.crud.repaso.exceptions.layouts.Exception> handleBadRequest(Exception ex, HttpServletRequest request) {
        com.todoTask.crud.repaso.exceptions.layouts.Exception errorResponse = new com.todoTask.crud.repaso.exceptions.layouts.Exception(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DateNotFoundException.class, DoctorNotFoundException.class, PatientNotFoundException.class, ShiftNotFoundException.class, SpecialityNotFoundException.class})
    public ResponseEntity<com.todoTask.crud.repaso.exceptions.layouts.Exception> handleNotFound(Exception ex, HttpServletRequest request) {
        com.todoTask.crud.repaso.exceptions.layouts.Exception errorResponse = new com.todoTask.crud.repaso.exceptions.layouts.Exception(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public com.todoTask.crud.repaso.exceptions.layouts.Exception handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        return com.todoTask.crud.repaso.exceptions.layouts.Exception.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error("Access Denied")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public com.todoTask.crud.repaso.exceptions.layouts.Exception handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        return com.todoTask.crud.repaso.exceptions.layouts.Exception.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Invalid Credentials")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public com.todoTask.crud.repaso.exceptions.layouts.Exception handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        return com.todoTask.crud.repaso.exceptions.layouts.Exception.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Authentication Error")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public com.todoTask.crud.repaso.exceptions.layouts.Exception handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return com.todoTask.crud.repaso.exceptions.layouts.Exception.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Error")
                .message(errorMessage)
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public com.todoTask.crud.repaso.exceptions.layouts.Exception handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return com.todoTask.crud.repaso.exceptions.layouts.Exception.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Request Format Error")
                .message("Invalid request body format")
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public com.todoTask.crud.repaso.exceptions.layouts.Exception handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        return com.todoTask.crud.repaso.exceptions.layouts.Exception.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .error("Method Not Allowed")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
