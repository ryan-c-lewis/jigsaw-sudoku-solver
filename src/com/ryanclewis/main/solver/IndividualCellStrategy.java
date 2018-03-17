package com.ryanclewis.main.solver;

import com.ryanclewis.main.board.Board;

public class IndividualCellStrategy implements ISolveStrategy {
    public boolean runStrategy(Board workingBoard) {
        for(int x = 0; x < workingBoard.getSize(); x++) {
            for (int y = 0; y < workingBoard.getSize(); y++) {
                if (workingBoard.getCell(x, y).getNumber() >= 1)
                    continue;
                if (SolveHelpers.checkIfSquareOnlyHasOneValidChoice(workingBoard, x, y))
                    return true;
            }
        }
        return false;
    }
}
