package com.ryanclewis.main.solver;

import com.ryanclewis.main.board.Board;
import com.ryanclewis.main.board.Cell;
import com.ryanclewis.main.board.Group;

import java.util.ArrayList;

public class FindDigitByGroupStrategy implements ISolveStrategy {
    public boolean runStrategy(Board workingBoard) {
        return checkDigitsForGroups(workingBoard);
    }

    private static boolean checkDigitsForGroups(Board workingBoard) {
        for (Group group : workingBoard.getGroups()) {
            for (int thisDigit = 1; thisDigit <= workingBoard.getSize(); thisDigit++) {
                if (checkThisDigitForGroup(workingBoard, group, thisDigit))
                    return true;
            }
        }
        return false;
    }

    private static boolean checkThisDigitForGroup(Board workingBoard, Group group, int thisDigit) {
        ArrayList<Cell> possibleCells = new ArrayList<>();
        for (Cell cell : group.getCells()) {
            if (cell.getNumber() != 0)
                continue;
            cell.setNumber(thisDigit);
            if (workingBoard.isValid())
                possibleCells.add(cell);
            cell.setNumber(0);
        }

        if (possibleCells.size() == 1) {
            workingBoard.getCell(possibleCells.get(0).getLocation().getX(), possibleCells.get(0).getLocation().getY()).setNumber(thisDigit);
            return true;
        }
        return false;
    }
}
