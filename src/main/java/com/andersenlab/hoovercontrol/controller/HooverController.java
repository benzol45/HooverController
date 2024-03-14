package com.andersenlab.hoovercontrol.controller;

import com.andersenlab.hoovercontrol.dto.RequestDto;
import com.andersenlab.hoovercontrol.dto.ResponseDto;
import com.andersenlab.hoovercontrol.dto.ResponseErrorDto;
import com.andersenlab.hoovercontrol.service.HooverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/hoover")
public class HooverController {
    private final HooverService hooverService;

    @PostMapping("/task")
    @Operation(summary = "Create new task for the hoover")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task was processed, returned a result",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request's values",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorDto.class)) }) })
    public ResponseDto processRequest(@Valid @RequestBody RequestDto requestDto) {
        return hooverService.processRequest(requestDto);
    }
}
