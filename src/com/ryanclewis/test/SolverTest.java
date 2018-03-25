package com.ryanclewis.test;

import com.ryanclewis.main.board.Board;
import com.ryanclewis.main.board.BoardParser;
import com.ryanclewis.main.solver.Solver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    private void assertSolutionIs(String resourceFileName, String solutionAsShortString) throws Exception {
        Board board = BoardParser.parse(TestHelper.loadResource(resourceFileName));
        Board solvedBoard = new Solver().solve(board);
        assertEquals(
                solutionAsShortString,
                solvedBoard.toShortString());
    }

    @Test
    void fourByFour() throws Exception {
        assertSolutionIs("board1.txt",
                "1234341223414123");
    }

    @Test
    void nineByNine_noGuessing_1() throws Exception {
        assertSolutionIs("board2.txt",
                "631492587518376924984725136743968215295187463127654398459813672862531749376249851");
    }

    @Test
    void nineByNine_noGuessing_2() throws Exception {
        assertSolutionIs("board3.txt",
                "247358916138697254956142378519863742863274591724915863492581637375426189681739425");
    }

    @Test
    void nineByNine_guessingRequired() throws Exception {
        assertSolutionIs("board4.txt",
                "892345176261479538754861329473586291915234867328917654536198742147652983689723415");
    }
}
