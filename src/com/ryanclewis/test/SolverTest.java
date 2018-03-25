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
    void nineByNine_noGuessing_1() throws Exception {
        Board board = BoardParser.parse(TestHelper.loadResource("board2.txt"));
        Board solvedBoard = new Solver().solve(board);
        assertEquals(
                "631492587518376924984725136743968215295187463127654398459813672862531749376249851",
                solvedBoard.toShortString());
    }

    @Test
    void nineByNine_noGuessing_2() throws Exception {
        Board board = BoardParser.parse(TestHelper.loadResource("board3.txt"));
        Board solvedBoard = new Solver().solve(board);
        assertEquals(
                "247358916138697254956142378519863742863274591724915863492581637375426189681739425",
                solvedBoard.toShortString());
    }

    @Test
    void nineByNine_guessingRequired() throws Exception {
        Board board = BoardParser.parse(TestHelper.loadResource("board4.txt"));
        Board solvedBoard = new Solver().solve(board);
        assertEquals(
                "892345176261479538754861329473586291915234867328917654536198742147652983689723415",
                solvedBoard.toShortString());
    }
}
