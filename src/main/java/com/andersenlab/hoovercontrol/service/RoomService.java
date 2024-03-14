package com.andersenlab.hoovercontrol.service;

import com.andersenlab.hoovercontrol.domain.DirtPatch;
import com.andersenlab.hoovercontrol.domain.Room;
import com.andersenlab.hoovercontrol.dto.RequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    public Room initRoom(RequestDto requestDto) {
        List<DirtPatch> dirtPatches = new ArrayList<>();
        if (requestDto.getPatches() != null) {
            for (List<Integer> dirtPatch : requestDto.getPatches()) {
                dirtPatches.add(new DirtPatch(dirtPatch.get(0), dirtPatch.get(1)));
            }
        }

        return Room.builder()
                .dimensionsX(requestDto.getRoomSize().get(0))
                .dimensionsY(requestDto.getRoomSize().get(1))
                .dirtPatches(dirtPatches)
                .build();
    }
}
