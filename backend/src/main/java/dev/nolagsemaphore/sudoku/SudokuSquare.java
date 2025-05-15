package dev.nolagsemaphore.sudoku;


public class SudokuSquare {
    private int myValue;

    public SudokuSquare(int myValue)
    {
        super();
        this.myValue = myValue;
    }

    public int getMyValue() {
        return myValue;
    }

    public void setMyValue(int myValue) {
        this.myValue = myValue;
    }
}