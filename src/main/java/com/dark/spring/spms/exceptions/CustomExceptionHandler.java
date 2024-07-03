package com.dark.spring.spms.exceptions;

import com.dark.spring.spms.dto.ErrorDTO;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExistException(Exception ex) {
        ErrorDTO errorDTO =  ErrorDTO.buildFromException(ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(Exception ex) {
        ErrorDTO errorDTO =  ErrorDTO.buildFromException(ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorDTO> handleJwtException(Exception ex) {
        ErrorDTO errorDTO = ErrorDTO.buildFromException(ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        System.out.println("TEST Calling CustomExceptionHandler for all Exception.class");
        // TODO: Add logs
        ex.printStackTrace();
        ErrorDTO errorDTO =  ErrorDTO.buildFromException(ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}