package com.ryanclewis.test;

import com.ryanclewis.main.board.Board;
import com.ryanclewis.main.board.BoardParser;
import com.ryanclewis.main.solver.Solver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void fourByFour() throws Exception {
        Board board = BoardParser.parse(TestHelper.loadResource("board1.txt"));
        Board solvedBoard = new Solver().solve(board);
        assertEquals(
                "1234341223414123",
                solvedBoard.toShortString());
    }

    @Test
    void nineByNine() throws Exception {
        Board board = BoardParser.parse(TestHelper.loadResource("board2.txt"));
        Board solvedBoard = new Solver().solve(board);
        assertEquals(
                "631492587518376924984725136743968215295187463127654398459813672862531749376249851",
                solvedBoard.toShortString());
    }
}
