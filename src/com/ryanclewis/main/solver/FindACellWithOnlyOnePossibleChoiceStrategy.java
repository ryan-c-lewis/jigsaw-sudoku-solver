package com.ryanclewis.main.solver;

import com.ryanclewis.main.board.Board;
import com.ryanclewis.main.board.PossibleChoicesForCell;

public class FindACellWithOnlyOnePossibleChoiceStrategy implements ISolveStrategy {
    public boolean runStrategy(Board workingBoard) {
        for(int x = 0; x < workingBoard.getSize(); x++) {
            for (int y = 0; y < workingBoard.getSize(); y++) {
                if (workingBoard.getCell(x, y).getNumber() >= 1)
                    continue;
                PossibleChoicesForCell validChoicesForCell = PossibleChoicesForCell.getForParticularCell(workingBoard, x, y);
                if (validChoicesForCell.getNumbers().size() == 1) {
                    workingBoard.getCell(x, y).setNumber(validChoicesForCell.getNumbers().get(0));
                    return true;
                }
            }
        }
        return false;
    }
}
