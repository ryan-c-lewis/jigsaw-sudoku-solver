package com.ryanclewis.main.solver;

import com.ryanclewis.main.board.Board;

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
                return workingBoard; // TODO: improve this. When it gets here, have it guess a square and go from there.

            workingBoard.setNoneJustAdded();
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
