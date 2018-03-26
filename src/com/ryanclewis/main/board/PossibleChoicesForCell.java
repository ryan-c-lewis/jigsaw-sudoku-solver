package com.ryanclewis.main.board;

import java.util.ArrayList;
import java.util.Comparator;

public class PossibleChoicesForCell {
    private Cell _cell;
    private ArrayList<Integer> _numbers;

    public static ArrayList<PossibleChoicesForCell> getForAllCells(Board workingBoard) {
        ArrayList<PossibleChoicesForCell> allDigits = new ArrayList<>();
        for (int x = 0; x < workingBoard.getSize(); x++) {
            for (int y = 0; y < workingBoard.getSize(); y++) {
                if (workingBoard.getCell(x, y).getNumber() != 0)
                    continue;
                allDigits.add(getForParticularCell(workingBoard, x, y));
            }
        }
        allDigits.sort(new PossibleChoicesForCell.SortByLowestPossibleChoices());
        return allDigits;
    }

    public static PossibleChoicesForCell getForParticularCell(Board workingBoard, int x, int y) {
        ArrayList<Integer> validDigits = new ArrayList<>();
        Board boardCopy = workingBoard.copy();
        for (int thisDigit = 1; thisDigit <= workingBoard.getSize(); thisDigit++) {
            boardCopy.getCell(x, y).setNumber(thisDigit);
            if (boardCopy.isValid()) {
                validDigits.add(thisDigit);
            }
        }
        return new PossibleChoicesForCell(workingBoard.getCell(x, y), validDigits);
    }

    private PossibleChoicesForCell(Cell cell, ArrayList<Integer> numbers) {
        _cell = cell;
        _numbers = numbers;
    }

    public Cell getCell() {
        return _cell;
    }

    public ArrayList<Integer> getNumbers() {
        return _numbers;
    }

    public static class SortByLowestPossibleChoices implements Comparator<PossibleChoicesForCell>
    {
        public int compare(PossibleChoicesForCell a, PossibleChoicesForCell b)
        {
            return a.getNumbers().size() - b.getNumbers().size();
        }
    }
}
