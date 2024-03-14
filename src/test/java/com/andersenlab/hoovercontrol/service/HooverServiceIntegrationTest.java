package com.andersenlab.hoovercontrol.service;

import com.andersenlab.hoovercontrol.dto.RequestDto;
import com.andersenlab.hoovercontrol.dto.ResponseDto;
import com.andersenlab.hoovercontrol.util.ObjectCreator;
import com.andersenlab.hoovercontrol.validator.RequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HooverServiceIntegrationTest {
    private HooverService hooverService;

    @BeforeEach
    void setUp() {
        hooverService = new HooverService(new RoomService());
    }

    @Test
    @DisplayName("Customer example")
    void processRequest() {
        RequestDto requestDto = ObjectCreator.createDto();

        ResponseDto responseDto = hooverService.processRequest(requestDto);

        assertArrayEquals(new int[]{1,1}, responseDto.getCoords());
        assertEquals(1, responseDto.getPatches());
    }

    @Test
    @DisplayName("Processing full dirty room")
    void processRequest_fullRoom() {
        RequestDto requestDto = RequestDto.builder()
                .roomSize(List.of(2,2))
                .coords(List.of(0,0))
                .patches(List.of(List.of(0,0),List.of(0,1),List.of(1,0),List.of(1,1)))
                .instructions("NES")
                .build();

        ResponseDto responseDto = hooverService.processRequest(requestDto);

        assertArrayEquals(new int[]{1,0}, responseDto.getCoords());
        assertEquals(4, responseDto.getPatches());
    }

    @Test
    @DisplayName("Empty instructions - no changing coordinates")
    void processRequest_emptyInstructions() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setInstructions("");

        ResponseDto responseDto = hooverService.processRequest(requestDto);

        assertArrayEquals(new int[]{0,0}, responseDto.getCoords());
        assertEquals(0, responseDto.getPatches());
    }

    @Test
    @DisplayName("Strange instructions - all moving only to bound")
    void processRequest_bounds() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setInstructions("WSWSWSWSSSSSWWWW");

        ResponseDto responseDto = hooverService.processRequest(requestDto);

        assertArrayEquals(new int[]{0,0}, responseDto.getCoords());
        assertEquals(0, responseDto.getPatches());
    }

    @Test
    @DisplayName("Moving on same cells - should be counted only one time")
    void processRequest_sameCells() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setInstructions("NNSNSNSNSNSNSNSNSNS");

        ResponseDto responseDto = hooverService.processRequest(requestDto);

        assertArrayEquals(new int[]{0,1}, responseDto.getCoords());
        assertEquals(1, responseDto.getPatches());
    }

    @Test
    @DisplayName("Finishing on dirty cells - should be counted")
    void processRequest_finishOnDirty() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setInstructions("NN");

        ResponseDto responseDto = hooverService.processRequest(requestDto);

        assertArrayEquals(new int[]{0,2}, responseDto.getCoords());
        assertEquals(1, responseDto.getPatches());
    }

    @Test
    @DisplayName("Starting on dirty cells - should be counted")
    void processRequest_startingOnDirty() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setPatches(List.of(List.of(0,0),List.of(0,2),List.of(2,0)));
        requestDto.setInstructions("E");

        ResponseDto responseDto = hooverService.processRequest(requestDto);

        assertArrayEquals(new int[]{1,0}, responseDto.getCoords());
        assertEquals(1, responseDto.getPatches());
    }
}