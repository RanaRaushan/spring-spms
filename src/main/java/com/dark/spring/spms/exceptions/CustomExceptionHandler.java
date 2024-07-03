package com.dark.spring.spms.exceptions;

import com.dark.spring.spms.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExistException(Exception ex) {
        System.out.println("Calling UserAlreadyExistException handler");
        ErrorDTO errorDTO = ErrorDTO.builder()
                .timeStamp(LocalDateTime.now().toString())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
//        System.out.println("Calling CustomExceptionHandler for all Exception.class");
//        ErrorDTO errorDTO = ErrorDTO.builder()
//                .timeStamp(LocalDateTime.now().toString())
//                .message(ex.getMessage())
//                .build();
//        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
//    public ResponseEntity<ProblemDetail> handleMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
////        System.out.println(Arrays.toString(ex.getStackTrace()));
//
//        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, "Not acceptable media type:"));
//    }
}