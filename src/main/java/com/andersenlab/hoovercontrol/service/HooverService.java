package com.andersenlab.hoovercontrol.service;

import com.andersenlab.hoovercontrol.domain.Hoover;
import com.andersenlab.hoovercontrol.domain.Result;
import com.andersenlab.hoovercontrol.dto.RequestDto;
import com.andersenlab.hoovercontrol.dto.ResponseDto;
import com.andersenlab.hoovercontrol.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HooverService {
    private final RoomService roomService;
    private final ResultRepository resultRepository;

    public ResponseDto processRequest(RequestDto requestDto) {
        Hoover hoover = initHoover(requestDto);
        processInstructions(hoover, requestDto.getInstructions());

        Result result = storeHooverResult(hoover);

        return ResponseDto.builder()
                .id(result.getId())
                .coords(new int[]{result.getCoordinateX(), result.getCoordinateY()})
                .patches(result.getPatches())
                .build();
    }

    private Result storeHooverResult(Hoover hoover) {
        Result result = Result.builder()
                .date(LocalDateTime.now())
                .coordinateX(hoover.getCoordinateX())
                .coordinateY(hoover.getCoordinateY())
                .patches(hoover.getDirtPathCounter())
                .build();
        return resultRepository.save(result);
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
