package com.ryanclewis.main.solver;

import com.ryanclewis.main.board.Board;
import com.ryanclewis.main.board.PossibleChoicesForCell;

import java.util.ArrayList;

public class Solver {
    private ArrayList<ISolveStrategy> _strategies = new ArrayList<>();

    public Solver() {
        _strategies.add(new IndividualCellStrategy());
        _strategies.add(new SearchByRowStrategy());
        _strategies.add(new SearchByColumnStrategy());
        _strategies.add(new SearchByGroupStrategy());
    }

    public Board solve(Board initialBoard) {
        System.out.println("STARTING STATE:");
        System.out.println(initialBoard.toString());

        Board solvedBoard = doSolve(initialBoard);

        System.out.println("ENDING STATE:");
        System.out.println(solvedBoard.toString());

        System.out.println("SHORT:");
        System.out.println(solvedBoard.toShortString());

        return solvedBoard;
    }

    private Board doSolve(Board initialBoard) {
        Board workingBoard = initialBoard.copy();

        while(!workingBoard.isComplete()) {
            boolean didFillAnyCellsThisIteration = tryToSolveAnyCell(workingBoard);
            if (!didFillAnyCellsThisIteration)
                return guessACell(workingBoard);

            workingBoard.setNoneJustAdded();
        }

        return workingBoard;
    }

    private Board guessACell(Board workingBoard) {
        ArrayList<PossibleChoicesForCell> possibleGuesses = PossibleChoicesForCell.getForAllCells(workingBoard);
        for (PossibleChoicesForCell possibleGuess : possibleGuesses) {
            for (int possibleNumber : possibleGuess.getNumbers()) {
                Board boardCopy = workingBoard.copy();
                boardCopy.getCell(possibleGuess.getCell().getLocation()).setNumber(possibleNumber);
                Board maybeSolvedBoard = new Solver().solve(boardCopy);
                if (maybeSolvedBoard.isComplete())
                    return maybeSolvedBoard;
            }
        }
        return workingBoard;
    }

    private boolean tryToSolveAnyCell(Board workingBoard) {
        boolean didFillAnyCellsThisIteration = false;
        for (ISolveStrategy strategy : _strategies) {
            didFillAnyCellsThisIteration |= strategy.runStrategy(workingBoard);
        }
        return didFillAnyCellsThisIteration;
    }
}
