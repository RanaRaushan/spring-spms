package com.dark.spring.spms.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Data
public class AccessDTO {

    private String token_type;

    private String expires_in;

    private String token;
}
