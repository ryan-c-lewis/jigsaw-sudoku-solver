package com.ryanclewis.main.solver;

import com.ryanclewis.main.board.Board;

import java.util.ArrayList;

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
}
