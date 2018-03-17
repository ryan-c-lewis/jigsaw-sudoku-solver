package com.ryanclewis.main.solver;

import com.ryanclewis.main.board.Board;
import com.ryanclewis.main.board.Cell;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchByRowStrategy implements ISolveStrategy {
    public boolean runStrategy(Board workingBoard) {
        for (int y = 0; y < workingBoard.getSize(); y++) {
            HashMap<Integer, ArrayList<Cell>> possibleCellsPerDigit = new HashMap<>();
            for (int d = 1; d <= workingBoard.getSize(); d++) {
                possibleCellsPerDigit.put(d, new ArrayList<Cell>());
            }
            for (int x = 0; x < workingBoard.getSize(); x++) {
                if (workingBoard.getCell(x, y).getNumber() != 0)
                    continue;
                ArrayList<Integer> validDigits = SolveHelpers.getValidDigitsForSquare(workingBoard, x, y);
                for (int d : validDigits) {
                    possibleCellsPerDigit.get(d).add(workingBoard.getCell(x, y));
                }
            }
            for (int d = 1; d <= workingBoard.getSize(); d++) {
                if (possibleCellsPerDigit.get(d).size() == 1) {
                    workingBoard.getCell(possibleCellsPerDigit.get(d).get(0).getLocation()).setNumber(d);
                    return true;
                }
            }
        }
        return false;
    }
}
