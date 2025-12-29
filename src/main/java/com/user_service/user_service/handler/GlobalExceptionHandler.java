package com.user_service.user_service.handler;

import com.user_service.user_service.dto.ErrorDTO;
import com.user_service.user_service.exception.EmailAlreadyExistsException;
import com.user_service.user_service.exception.TokenInvalidException;
import com.user_service.user_service.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //400
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        ErrorDTO error = new ErrorDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD_REQUEST",
                message,
                ((ServletWebRequest) request).getRequest().getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //409
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleEmailExists(
            EmailAlreadyExistsException ex,
            HttpServletRequest request) {

        ErrorDTO error = new ErrorDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    //401
    @ExceptionHandler({UserNotFoundException.class, TokenInvalidException.class})
    public ResponseEntity<ErrorDTO> handleUserNotFound(
            UserNotFoundException ex,
            HttpServletRequest request) {

        ErrorDTO error = new ErrorDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        ErrorDTO error = new ErrorDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
