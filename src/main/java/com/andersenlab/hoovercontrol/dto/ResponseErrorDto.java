package com.andersenlab.hoovercontrol.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseErrorDto {
    private LocalDateTime time;
    private String message;
}
