package com.ryanclewis.main.board;

public class Direction {
    public static final Direction UP = new Direction(0, -1);
    public static final Direction DOWN = new Direction(0, 1);
    public static final Direction LEFT = new Direction(-1, 0);
    public static final Direction RIGHT = new Direction(1, 0);

    private int _dX = 0;
    private int _dY = 0;

    private Direction(int dX, int dY) {
        _dX = dX;
        _dY = dY;
    }

    public String toString() {
        if (this == UP) return "UP";
        if (this == DOWN) return "DOWN";
        if (this == LEFT) return "LEFT";
        if (this == RIGHT) return "RIGHT";
        return "???";
    }

    public int getDX() {
        return _dX;
    }

    public int getDY() {
        return _dY;
    }
}
