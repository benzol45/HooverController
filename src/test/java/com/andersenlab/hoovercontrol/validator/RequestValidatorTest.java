package com.andersenlab.hoovercontrol.validator;

import com.andersenlab.hoovercontrol.dto.RequestDto;
import com.andersenlab.hoovercontrol.util.ObjectCreator;
import jakarta.validation.constraints.Max;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestValidatorTest {
    private RequestValidator requestValidator;

    @BeforeEach
    void setUp() {
        requestValidator = new RequestValidator();
    }

    @Test
    @DisplayName("Correct body")
    void isValid() {
        RequestDto requestDto = ObjectCreator.createDto();
        assertTrue(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Incorrect room size array")
    void isValid_incorrectRoom_tooMachValues() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setRoomSize(List.of(5,5,5));
        assertFalse(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Incorrect room size negative")
    void isValid_incorrectRoom_negative() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setRoomSize(List.of(5,-5));
        assertFalse(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Incorrect coords array")
    void isValid_incorrectCoords_array() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setCoords(List.of(0,0,0));
        assertFalse(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Incorrect coords negative")
    void isValid_incorrectCoords_negative() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setCoords(List.of(0,-1));
        assertFalse(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Incorrect coords out of room bound")
    void isValid_incorrectCoords_outOfBound() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setCoords(List.of(100,200));
        assertFalse(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Correct without patches")
    void isValid_correctWithoutPatches() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setPatches(Collections.emptyList());
        assertTrue(requestValidator.isValid(requestDto,null));
        requestDto.setPatches(null);
        assertTrue(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Incorrect patches array")
    void isValid_incorrectPatches_array() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setPatches(List.of(List.of(1,1,1),List.of(1,1)));
        assertFalse(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Incorrect patches negative")
    void isValid_incorrectPatches_negative() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setPatches(List.of(List.of(-1,1),List.of(1,1)));
        assertFalse(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Incorrect patches out of room bound")
    void isValid_incorrectPatches_outOfBound() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setPatches(List.of(List.of(100,200),List.of(1,1)));
        assertFalse(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Correct without instructions")
    void isValid_correctWithoutInstructions() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setInstructions("");
        assertTrue(requestValidator.isValid(requestDto,null));
    }

    @Test
    @DisplayName("Incorrect instructions - not allowed characters")
    void isValid_incorrectInstructions_permitedChar() {
        RequestDto requestDto = ObjectCreator.createDto();
        requestDto.setInstructions("Hello world !");
        assertFalse(requestValidator.isValid(requestDto,null));
    }


}