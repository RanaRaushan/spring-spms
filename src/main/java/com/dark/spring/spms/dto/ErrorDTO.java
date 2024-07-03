package com.dark.spring.spms.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ErrorDTO {

    private String timeStamp;

    private String message;

    @Builder.Default
    private String details = "";
}
