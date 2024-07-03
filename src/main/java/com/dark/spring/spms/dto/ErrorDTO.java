package com.dark.spring.spms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@ToString
public class ErrorDTO {

    private LocalDateTime timeStamp;

    private String message;

    private String details;
}
