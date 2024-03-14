package com.andersenlab.hoovercontrol.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class Room {
    private int dimensionsX;
    private int dimensionsY;
    private List<DirtPatch> dirtPatches;

    public int cleanCell(int coordinateX, int coordinateY) {
        Optional<DirtPatch> optionalDirtPatch = dirtPatches.stream()
                .filter(dirtPatch -> dirtPatch.getCoordinateX() == coordinateX && dirtPatch.getCoordinateY() == coordinateY)
                .findFirst();

        if (optionalDirtPatch.isEmpty()) {
            return 0;
        } else {
            dirtPatches.remove(optionalDirtPatch.get());
            return 1;
        }
    }
}
