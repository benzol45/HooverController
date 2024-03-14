package com.andersenlab.hoovercontrol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    @Schema(description = "A final hoover position as X and Y coordinates", example = "[1,1]")
    private int[] coords;

    @Schema(description = "A number of collected dirt patches", example = "1")
    private int patches;
}
