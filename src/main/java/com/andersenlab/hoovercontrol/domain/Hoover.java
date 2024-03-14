package com.andersenlab.hoovercontrol.domain;

import lombok.Getter;

@Getter
public class Hoover {
    private Room room;
    private int coordinateX;
    private int coordinateY;
    private int dirtPathCounter = 0;

    public Hoover(Room room, int coordinateX, int coordinateY) {
        this.room = room;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;

        dirtPathCounter += room.cleanCell(coordinateX,coordinateY);
    }

    public void processInstruction(char instruction) {
        switch (instruction) {
            case 'n' -> processMoving(0,1);
            case 's' -> processMoving(0,-1);
            case 'e' -> processMoving(1,0);
            case 'w' -> processMoving(-1,0);
            default -> throw new IllegalArgumentException("incorrect instruction " + instruction);
        }

        dirtPathCounter += room.cleanCell(coordinateX,coordinateY);
    }

    private boolean processMoving(int x, int y) {
        int newX = coordinateX + x;
        int newY = coordinateY + y;
        if ((newX >= 0 && newX <= room.getDimensionsX()-1)
                && (newY >=0 && newX <= room.getDimensionsY()-1)) {
            coordinateX = newX;
            coordinateY = newY;
            return true;
        } else {
            return false;
        }
    }
}
