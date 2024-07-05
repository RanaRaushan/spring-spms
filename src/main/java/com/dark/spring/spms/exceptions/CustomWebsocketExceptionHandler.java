package com.dark.spring.spms.exceptions;

import com.dark.spring.spms.dto.ErrorDTO;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomWebsocketExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomWebsocketExceptionHandler.class);

    // TODO: Need to handle global handling
    @MessageExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleMessageExceptionHandler(Exception ex) {
        LOG.error("Calling CustomExceptionHandler: ", ex);
        ErrorDTO errorDTO =  ErrorDTO.buildFromException(ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}