package com.andersenlab.hoovercontrol.service;

import com.andersenlab.hoovercontrol.domain.Hoover;
import com.andersenlab.hoovercontrol.dto.RequestDto;
import com.andersenlab.hoovercontrol.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HooverService {
    private final RoomService roomService;

    public ResponseDto processRequest(RequestDto requestDto) {
        Hoover hoover = initHoover(requestDto);
        processInstructions(hoover, requestDto.getInstructions());

        return ResponseDto.builder()
                .coords(new int[]{hoover.getCoordinateX(), hoover.getCoordinateY()})
                .patches(hoover.getDirtPathCounter())
                .build();
    }

    private Hoover initHoover(RequestDto requestDto) {
        return new Hoover(
                roomService.initRoom(requestDto),
                requestDto.getCoords().get(0),
                requestDto.getCoords().get(1));
    }

    private void processInstructions(Hoover hoover, String instructions) {
        instructions = instructions.toLowerCase();
        for (int i=0; i<instructions.length(); i++) {
            char instruction = instructions.charAt(i);
            hoover.processInstruction(instruction);
        }
    }
}
