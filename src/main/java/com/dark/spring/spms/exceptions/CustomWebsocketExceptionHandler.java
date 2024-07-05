package com.dark.spring.spms.exceptions;

import com.dark.spring.spms.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomWebsocketExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomWebsocketExceptionHandler.class);


    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @MessageExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExistException(Exception ex) {
        ErrorDTO errorDTO =  ErrorDTO.buildFromException(ex);
        LOG.error("UserAlreadyExistException Exception: {}", errorDTO);
        interceptOutboundMsgOnError(errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }


    @MessageExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(Exception ex) {
        ErrorDTO errorDTO =  ErrorDTO.buildFromException(ex);
        LOG.error("UserNotFoundException Exception: {}", errorDTO);
        interceptOutboundMsgOnError(errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @MessageExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleMessageExceptionHandler(Exception ex) {
        LOG.error("Calling CustomExceptionHandler: ", ex);
        ErrorDTO errorDTO =  ErrorDTO.buildFromException(ex);
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private void interceptOutboundMsgOnError(ErrorDTO errorDTO) {
        messagingTemplate.convertAndSend("/topic/booking_updates", errorDTO);
    }
}