package com.dark.spring.spms.dto;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public class ErrorDTO {

    private LocalDateTime timeStamp;

    private String message;

    private String details;
}
