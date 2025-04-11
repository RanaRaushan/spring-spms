package com.dark.spring.spms.exceptions;

import com.dark.spring.spms.dto.ErrorDTO;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(Exception ex) {
        ErrorDTO errorDTO =  ErrorDTO.buildFromException(ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.FORBIDDEN);
    }
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
        LOG.error("Calling CustomExceptionHandler: ", ex);
        ErrorDTO errorDTO =  ErrorDTO.buildFromException(ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(IllegalArgumentException ex) {
        ErrorDTO errorDTO = ErrorDTO.buildFromException(ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.FORBIDDEN);
    }
}