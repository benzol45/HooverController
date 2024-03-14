package com.andersenlab.hoovercontrol.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DirtPatch {
    private int coordinateX;
    private int coordinateY;
}
