package com.ryanclewis.main.solver;

import com.ryanclewis.main.board.Board;
import com.ryanclewis.main.board.Cell;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;

class SolveHelpers {
    static boolean checkIfSquareOnlyHasOneValidChoice(Board workingBoard, int x, int y) {
        ArrayList<Integer> validChoicesForSquare = getValidDigitsForSquare(workingBoard, x, y);
        if (validChoicesForSquare.size() == 1) {
            workingBoard.getCell(x, y).setNumber(validChoicesForSquare.get(0));
            return true;
        }
        return false;
    }

    static ArrayList<Integer> getValidDigitsForSquare(Board workingBoard, int x, int y) {
        ArrayList<Integer> validDigits = new ArrayList<>();
        Board boardCopy = workingBoard.copy();
        for (int thisDigit = 1; thisDigit <= workingBoard.getSize(); thisDigit++) {
            boardCopy.getCell(x, y).setNumber(thisDigit);
            if (boardCopy.isValid()) {
                validDigits.add(thisDigit);
            }
        }
        return validDigits;
    }

    static ArrayList<Pair<Cell, ArrayList<Integer>>> getValidDigitsForAllSquares(Board workingBoard) {
        ArrayList<Pair<Cell, ArrayList<Integer>>> allDigits = new ArrayList<>();
        for (int x = 0; x < workingBoard.getSize(); x++) {
            for (int y = 0; y < workingBoard.getSize(); y++) {
                if (workingBoard.getCell(x, y).getNumber() != 0)
                    continue;
                allDigits.add(new Pair<>(workingBoard.getCell(x, y), getValidDigitsForSquare(workingBoard, x, y)));
            }
        }
        allDigits.sort(new SortByLowestPossibleChoices());
        return allDigits;
    }

    static class SortByLowestPossibleChoices implements Comparator<Pair<Cell, ArrayList<Integer>>>
    {
        public int compare(Pair<Cell, ArrayList<Integer>> a, Pair<Cell, ArrayList<Integer>> b)
        {
            return a.getValue().size() - b.getValue().size();
        }
    }
}
