package com.ryanclewis.main.solver;

import com.ryanclewis.main.board.Board;
import com.ryanclewis.main.board.PossibleChoicesForCell;

class SolveHelpers {
    static boolean checkIfSquareOnlyHasOneValidChoice(Board workingBoard, int x, int y) {
        PossibleChoicesForCell validChoicesForCell = PossibleChoicesForCell.getForParticularCell(workingBoard, x, y);
        if (validChoicesForCell.getNumbers().size() == 1) {
            workingBoard.getCell(x, y).setNumber(validChoicesForCell.getNumbers().get(0));
            return true;
        }
        return false;
    }
}
