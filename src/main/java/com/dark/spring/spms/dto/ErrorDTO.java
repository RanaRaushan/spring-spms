package com.dark.spring.spms.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class ErrorDTO {

    private String timeStamp;

    private String message;

    private String event;

    @Builder.Default
    private String details = "";

    public static ErrorDTO buildFromException(Exception ex) {
        return ErrorDTO.builder()
                .timeStamp(LocalDateTime.now().toString())
                .message(ex.getMessage())
                .event("Error")
                .build();
    }
}
