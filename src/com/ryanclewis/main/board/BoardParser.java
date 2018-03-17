package com.ryanclewis.main.board;

public class BoardParser {
    private Board _board = new Board();

    public static Board parse(String boardAsAscii) {
        return new BoardParser().toBoard(boardAsAscii);
    }

    private Board toBoard(String boardAsAscii) {
        String[] rowsAsAscii = boardAsAscii.split("\n");
        _board.setSize(rowsAsAscii.length / 2);
        int asciiRowNumber = 0;
        for (String rowAsAscii : rowsAsAscii)
        {
            parseAsciiRow(rowAsAscii, asciiRowNumber);
            asciiRowNumber++;
        }

        _board.populateGroups();
        _board.setNoneJustAdded();
        return _board;
    }

    private void parseAsciiRow(String rowAsAscii, int asciiRowNumber) {
        if (asciiRowNumber % 2 == 1) {
            parseAsciiSudokuNumberRow(rowAsAscii, asciiRowNumber);
        }
        else {
            parseAsciiHorizontalBarrierRow(rowAsAscii, asciiRowNumber);
        }
    }

    // For rows that looks like this:
    // | 6  3 |   4     2    |      |
    private void parseAsciiSudokuNumberRow(String rowAsAscii, int asciiRowNumber) {
        for (int asciiColumnNumber = 0; asciiColumnNumber < rowAsAscii.length(); asciiColumnNumber += 2) {
            char columnValue = rowAsAscii.charAt(asciiColumnNumber);
            if (asciiColumnNumber % 4 == 2) {
                parseAsciiSudokuNumber(columnValue, asciiRowNumber, asciiColumnNumber);
            }
            else {
                parseAsciiVerticalBarrier(columnValue, asciiRowNumber, asciiColumnNumber);
            }
        }
    }

    private void parseAsciiSudokuNumber(char columnValue, int asciiRowNumber, int asciiColumnNumber) {
        int columnValueAsInt = (int)columnValue;
        if (columnValueAsInt >= (int)'1' && columnValueAsInt <= (int)'9') {
            int sudokuValue = columnValueAsInt - (int)'1' + 1;
            _board.getCell(asciiColumnNumber / 4, asciiRowNumber / 2).setNumber(sudokuValue);
        }
    }

    private void parseAsciiVerticalBarrier(char value, int asciiRowNumber, int asciiColumnNumber) {
        if (value != '|')
            return;
        if (asciiColumnNumber > 0)
            _board.getCell((asciiColumnNumber - 2) / 4, asciiRowNumber / 2)
                    .setIsConnected(Direction.RIGHT, false);
        if ((asciiColumnNumber + 2) / 4 < _board.getSize())
            _board.getCell((asciiColumnNumber + 2) / 4, asciiRowNumber / 2)
                    .setIsConnected(Direction.LEFT, false);
    }

    // For rows that just have the horizontal barriers, like this:
    //    ---       ---
    private void parseAsciiHorizontalBarrierRow(String rowAsAscii, int asciiRowNumber) {
        for (int asciiColumnNumber = 0; asciiColumnNumber < rowAsAscii.length(); asciiColumnNumber += 2) {
            char columnValue = rowAsAscii.charAt(asciiColumnNumber);
            if (asciiColumnNumber % 4 == 2) {
                parseAsciiHorizontalBarrier(columnValue, asciiRowNumber, asciiColumnNumber);
            }
        }
    }

    private void parseAsciiHorizontalBarrier(char value, int asciiRowNumber, int asciiColumnNumber) {
        if (value != '-')
            return;
        if (asciiRowNumber > 0)
            _board.getCell(asciiColumnNumber / 4, (asciiRowNumber - 1) / 2)
                    .setIsConnected(Direction.DOWN, false);
        if ((asciiRowNumber + 1) / 2 < _board.getSize())
            _board.getCell(asciiColumnNumber / 4, (asciiRowNumber + 1) / 2)
                    .setIsConnected(Direction.UP, false);
    }
}
