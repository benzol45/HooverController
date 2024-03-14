package com.andersenlab.hoovercontrol.exception_handler;

import com.andersenlab.hoovercontrol.dto.ResponseErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors()
                .stream().map(field->field.getDefaultMessage()).collect(Collectors.joining("; "));

        return ResponseEntity.badRequest()
                .body(ResponseErrorDto.builder()
                        .time(LocalDateTime.now())
                        .message(errors)
                        .build());
    }

}
