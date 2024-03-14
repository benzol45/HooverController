package com.andersenlab.hoovercontrol.util;

import com.andersenlab.hoovercontrol.dto.RequestDto;

import java.util.List;

public class ObjectCreator {
    public static RequestDto createDto() {
        return RequestDto.builder()
                .roomSize(List.of(5,5))
                .coords(List.of(0,0))
                .patches(List.of(List.of(0,2),List.of(0,3),List.of(0,4)))
                .instructions("WNNES")
                .build();
    }
}
