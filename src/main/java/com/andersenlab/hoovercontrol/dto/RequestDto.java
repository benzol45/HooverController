package com.andersenlab.hoovercontrol.dto;

import com.andersenlab.hoovercontrol.validator.RequestValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@RequestValid
public class RequestDto {
    @NotNull
    @Size(min = 2, max = 2)
    @Schema(description = "Room dimensions as X and Y coordinates", example = "[5,5]")
    private List<Integer> roomSize;

    @NotNull
    @Size(min = 2, max = 2)
    @Schema(description = "An initial hoover position as X and Y coordinates", example = "[0,0]")
    private List<Integer> coords;

    @Schema(description = "Locations of patches of dirt, as list of X and Y coordinates", example = "[[0,2],[0,3],[0,4]]")
    private List<List<Integer>> patches;

    @NotNull
    @Schema(description = "Driving instructions (as cardinal directions N, W, S, E like 'go 1 step North', 'go 1 step West' ...)", example = "WNNES")
    private String instructions;
}
