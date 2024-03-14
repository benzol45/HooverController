package com.andersenlab.hoovercontrol.validator;

import com.andersenlab.hoovercontrol.dto.RequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator implements ConstraintValidator<RequestValid, RequestDto> {

    @Override
    public boolean isValid(RequestDto requestDto, ConstraintValidatorContext constraintValidatorContext) {
        boolean roomCorrect = requestDto.getRoomSize().size() == 2
                && requestDto.getRoomSize().get(0) > 0
                && requestDto.getRoomSize().get(1) > 0;
        if (!roomCorrect)
            return false;

        boolean coordsCorrect = requestDto.getCoords().size() == 2
                && requestDto.getCoords().get(0) >= 0
                && requestDto.getCoords().get(0) <= requestDto.getRoomSize().get(0) - 1
                && requestDto.getCoords().get(1) >= 0
                && requestDto.getCoords().get(1) <= requestDto.getRoomSize().get(1) - 1;
        if (!coordsCorrect)
            return false;

        boolean patchesCorrect = requestDto.getPatches() == null
                || requestDto.getPatches().isEmpty()
                || requestDto.getPatches().stream().noneMatch(path -> path.size() != 2
                || path.get(0) < 0
                || path.get(0) > requestDto.getRoomSize().get(0) - 1
                || path.get(1) < 0
                || path.get(1) > requestDto.getRoomSize().get(1) - 1);
        if (!patchesCorrect)
            return false;

        boolean instructionsCorrect = requestDto.getInstructions().isEmpty()
                || requestDto.getInstructions().matches("[nNwWsSeE]+");
        if (!instructionsCorrect)
            return false;

        return true;
    }
}
