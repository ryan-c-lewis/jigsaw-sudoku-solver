package com.ryanclewis.main.board;

import java.util.ArrayList;
import java.util.HashSet;

public class Board {
    private int _size;
    private Cell[][] _cells;
    private ArrayList<Group> _groups = new ArrayList<>();

    public Board() {

    }

    // Tells us whether the board is *filled out* or not. Doesn't care whether or not it's *correct*
    public boolean isComplete() {
        for (int x = 0; x < _size; x++)
            for (int y = 0; y < _size; y++)
                if (getCell(x, y).getNumber() < 1)
                    return false;
        return true;
    }

    // Tells us whether or not the numbers contradict each other
    public boolean isValid() {
        for (int a = 0; a < _size; a++) {
            if (!isRowValid(a)) return false;
            if (!isColumnValid(a)) return false;
        }
        for (Group group : _groups)
            if (!group.isValid()) return false;
        return true;
    }

    private boolean isColumnValid(int x) {
        HashSet<Integer> digitsAlreadySeen = new HashSet<>();
        for (int y = 0; y < _size; y++) {
            int thisNumber = getCell(x, y).getNumber();
            if (thisNumber < 1)
                continue;
            if (digitsAlreadySeen.contains(thisNumber))
                return false;
            digitsAlreadySeen.add(thisNumber);
        }
        return true;
    }

    private boolean isRowValid(int y) {
        HashSet<Integer> digitsAlreadySeen = new HashSet<>();
        for (int x = 0; x < _size; x++) {
            int thisNumber = getCell(x, y).getNumber();
            if (thisNumber < 1)
                continue;
            if (digitsAlreadySeen.contains(thisNumber))
                return false;
            digitsAlreadySeen.add(thisNumber);
        }
        return true;
    }

    public Board copy() {
        Board boardCopy = new Board();
        boardCopy.setSize(_size);
        for (int x = 0; x < _size; x++) {
            for (int y = 0; y < _size; y++) {
                boardCopy.setCell(x, y, this.getCell(x, y).copy(boardCopy));
            }
        }
        boardCopy.populateGroups();
        return boardCopy;
    }

    public int getSize() {
        return _size;
    }

    public void setSize(int size) {
        _size = size;
        createCells();
    }

    private void createCells() {
        _cells = new Cell[_size][_size];
        for (int x = 0; x < _size; x++) {
            for (int y = 0; y < _size; y++) {
                _cells[y][x] = new Cell(this, x, y);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= getSize() || y < 0 || y >= getSize()) {
            int a = 1;
        }
        return _cells[y][x];
    }

    public Cell getCell(CellLocation location) {
        return _cells[location.getY()][location.getX()];
    }

    private void setCell(int x, int y, Cell cell) {
        _cells[y][x] = cell;
    }

    public ArrayList<Group> getGroups() {
        return _groups;
    }

    void populateGroups() {
        HashSet<Cell> remainingCells = getAllCells();
        while(!remainingCells.isEmpty()) {
            HashSet<Cell> cellsInGroup = new HashSet<>();
            populateGroup(remainingCells.iterator().next(), cellsInGroup, new HashSet<>());
            remainingCells.removeAll(cellsInGroup);
            _groups.add(new Group(cellsInGroup));
        }
    }

    private HashSet<Cell> getAllCells() {
        HashSet<Cell> allCells = new HashSet<>();
        for (int x = 0; x < _size; x++)
            for (int y = 0; y < _size; y++)
                allCells.add(getCell(x, y));
        return allCells;
    }

    private void populateGroup(Cell thisCell, HashSet<Cell> cellsInGroup, HashSet<Cell> cellsTried) {
        cellsInGroup.add(thisCell);
        cellsTried.add(thisCell);

        populateGroupInDirection(thisCell, Direction.LEFT, cellsInGroup, cellsTried);
        populateGroupInDirection(thisCell, Direction.RIGHT, cellsInGroup, cellsTried);
        populateGroupInDirection(thisCell, Direction.UP, cellsInGroup, cellsTried);
        populateGroupInDirection(thisCell, Direction.DOWN, cellsInGroup, cellsTried);
    }

    private void populateGroupInDirection(Cell thisCell, Direction direction, HashSet<Cell> cellsInGroup, HashSet<Cell> cellsTried) {
        if (thisCell.getNeighbor(direction) != null
                && thisCell.getIsConnected(direction)
                && !cellsTried.contains(thisCell.getNeighbor(direction))) {
            populateGroup(thisCell.getNeighbor(direction), cellsInGroup, cellsTried);
        } else {
            cellsTried.add(thisCell.getNeighbor(direction));
        }
    }

    public void setNoneJustAdded() {
        for (Cell[] row : _cells) {
            for (Cell cell : row) {
                cell.setJustAdded(false);
            }
        }
    }

    public String toShortString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < _size; y++) {
            for (int x = 0; x < _size; x++) {
                builder.append(getCell(x, y).getNumber());
            }
        }
        return builder.toString();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        // top row
        for (int topRowNum = 0; topRowNum < _size; topRowNum++)
            builder.append(" ---");
        builder.append("\n");

        for (int y = 0; y < _size; y++) {
            // number row
            builder.append("|");
            for (int x = 0; x < _size; x++) {
                int number = getCell(x, y).getNumber();
                if (number > 0)
                    if (getCell(x, y).getJustAdded())
                        builder.append(">").append(number).append("<");
                    else
                        builder.append(" ").append(number).append(" ");
                else
                    builder.append("   ");

                if (getCell(x, y).getIsConnected(Direction.RIGHT))
                    builder.append(" ");
                else
                    builder.append("|");
            }
            builder.append("\n");

            // horizontal divider row
            builder.append(" ");
            for (int x = 0; x < _size; x++) {
                if (getCell(x, y).getIsConnected(Direction.DOWN))
                    builder.append("    ");
                else
                    builder.append("--- ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
