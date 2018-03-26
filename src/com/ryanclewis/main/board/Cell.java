package com.ryanclewis.main.board;

import java.util.HashMap;

public class Cell {
    private CellLocation _location;
    private int _value = 0;

    private Board _board; // blah do i really want to do this? seems lazy
    private HashMap<Direction, Boolean> _isConnected = new HashMap<>();

    public Cell(Board board, int x, int y) {
        _board = board;
        _location = new CellLocation(x, y);
    }

    public Cell(Board board, CellLocation location) {
        _board = board;
        _location = location;
    }

    public Cell copy(Board newBoard) {
        Cell cellCopy = new Cell(newBoard, _location);
        cellCopy.setNumber(_value);
        cellCopy.setIsConnected(_isConnected);
        return cellCopy;
    }

    public CellLocation getLocation() {
        return _location;
    }

    public void setNumber(int value) {
        _value = value;
    }

    public int getNumber() {
        return _value;
    }

    public void setIsConnected(Direction direction, boolean isConnected) {
        _isConnected.put(direction, isConnected);
    }

    public void setIsConnected(HashMap<Direction, Boolean> isConnected) {
        _isConnected = isConnected;
    }

    public boolean getIsConnected(Direction direction) {
        return _isConnected.getOrDefault(direction, true);
    }

    public Cell getNeighbor(Direction direction) {
        int neighborX = _location.getX() + direction.getDX();
        int neighborY = _location.getY() + direction.getDY();
        if (neighborX < 0 || neighborX >= _board.getSize() || neighborY < 0 || neighborY >= _board.getSize())
            return null;
        return _board.getCell(neighborX, neighborY);
    }

    public String toString() {
        return "(" + _location.getX() + "," + _location.getY() + ") = " + _value;
    }
}
