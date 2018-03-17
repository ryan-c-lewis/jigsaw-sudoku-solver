package com.ryanclewis.main.board;

import java.util.HashSet;

public class Group {
    private HashSet<Cell> _cells;

    public Group(HashSet<Cell> cells) {
        _cells = cells;
    }

    public HashSet<Cell> getCells() {
        return _cells;
    }

    public boolean isValid() {
        HashSet<Integer> digitsAlreadySeen = new HashSet<>();
        for(Cell cell : _cells) {
            int thisNumber = cell.getNumber();
            if (thisNumber < 1)
                continue;
            if (digitsAlreadySeen.contains(thisNumber))
                return false;
            digitsAlreadySeen.add(thisNumber);
        }
        return true;
    }
}
