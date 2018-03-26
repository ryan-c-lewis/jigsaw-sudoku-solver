package com.ryanclewis.main.solver;

import com.ryanclewis.main.board.Board;
import com.ryanclewis.main.board.PossibleChoicesForCell;

import java.util.ArrayList;

public class Solver {
    private static ArrayList<ISolveStrategy> _strategies = new ArrayList<ISolveStrategy>() {{
        add(new FindACellWithOnlyOnePossibleChoiceStrategy());
        add(new FindDigitByRowStrategy());
        add(new FindDigitByColumnStrategy());
        add(new FindDigitByGroupStrategy());
    }};

    public static Board solve(Board initialBoard) {
        System.out.println("STARTING STATE:");
        System.out.println(initialBoard.toString());

        Board solvedBoard = doSolve(initialBoard);

        System.out.println("ENDING STATE:");
        System.out.println(solvedBoard.toString());

        System.out.println("SHORT:");
        System.out.println(solvedBoard.toShortString());

        return solvedBoard;
    }

    private static Board doSolve(Board initialBoard) {
        Board workingBoard = initialBoard.copy();

        while(!workingBoard.isComplete()) {
            boolean didFillAnyCellsThisIteration = tryToSolveAnyCell(workingBoard);
            if (!didFillAnyCellsThisIteration)
                return guessACell(workingBoard);

            workingBoard.setNoneJustAdded();
        }

        return workingBoard;
    }

    private static boolean tryToSolveAnyCell(Board workingBoard) {
        boolean didFillAnyCellsThisIteration = false;
        for (ISolveStrategy strategy : _strategies) {
            didFillAnyCellsThisIteration |= strategy.runStrategy(workingBoard);
        }
        return didFillAnyCellsThisIteration;
    }

    private static Board guessACell(Board workingBoard) {
        ArrayList<PossibleChoicesForCell> possibleGuesses = PossibleChoicesForCell.getForAllCells(workingBoard);
        for (PossibleChoicesForCell possibleGuess : possibleGuesses) {
            for (int possibleNumber : possibleGuess.getNumbers()) {
                Board boardCopy = workingBoard.copy();
                boardCopy.getCell(possibleGuess.getCell().getLocation()).setNumber(possibleNumber);
                Board maybeSolvedBoard = Solver.solve(boardCopy);
                if (maybeSolvedBoard.isComplete())
                    return maybeSolvedBoard;
            }
        }
        return workingBoard;
    }
}
