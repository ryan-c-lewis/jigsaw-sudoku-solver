package com.ryanclewis.main.board;

public class CellLocation {
    private int _x;
    private int _y;

    public CellLocation(int x, int y) {
        _x = x;
        _y = y;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }
}
