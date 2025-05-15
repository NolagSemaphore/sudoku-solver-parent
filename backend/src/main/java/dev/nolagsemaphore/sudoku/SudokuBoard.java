package dev.nolagsemaphore.sudoku;

import java.util.*;

public class SudokuBoard {

    private class SudokuMove
    {
        int[] myLocation;

        int choice;

        SudokuMove()
        {
            myLocation = new int[]{-1,-1};
            choice = 10;
        }
    }


    private SudokuSquare[][] ourBoard;

    private boolean FinalSuccess;



    private Stack myChangeStack = new Stack();

    public boolean isInitialBoardValid() {
        for (int row = 1; row < 10; row++) {
            for (int col = 1; col < 10; col++) {
                if(ourBoard[row][col] == null){
                    continue;
                }
                int value = ourBoard[row][col].getMyValue();

                if (value != 0) {
                    ourBoard[row][col].setMyValue(0); // Temporarily clear to avoid self-check
                    if (!isValidChoice(value, row, col)) {
                        ourBoard[row][col].setMyValue(value); // Restore
                        return false;
                    }
                    ourBoard[row][col].setMyValue(value); // Restore
                }
            }
        }
        return true;
    }

    public void makeSquareChange(int x,int y , int myValue)
    {
        ourBoard[x][y].setMyValue(myValue);
    }


    public void drawBoard()
    {

        // Probably should
        // seperate this implementation from the SodokuBoard class, and just take the object as a parameter.
    }

    private dev.nolagsemaphore.sudoku.model.SudokuBoard createReturnSolutionBoard(){
        dev.nolagsemaphore.sudoku.model.SudokuBoard returnBoard = new dev.nolagsemaphore.sudoku.model.SudokuBoard();

        List<List<Integer>> board = new ArrayList<>();

        // accounting for old 1 based arrays here
        for(int row = 0; row < 9; row++){
            List<Integer> rowList = new ArrayList<>();

            for(int column = 0; column < 9; column++){
                int value = ourBoard[row+1][column+1].getMyValue();
                rowList.add(value);
            }
            board.add(rowList);
        }
        returnBoard.setBoard(board);
        return returnBoard;
    }

    private void printResults()
    {
        for(int row = 1; row < 10; row++)
        {
            StringBuffer myBuffer = new StringBuffer();

            for(int column = 1; column < 10; column ++)
            {

                myBuffer.append(ourBoard[row][column].getMyValue());
                myBuffer.append(" ");
            }
            System.out.println(myBuffer.toString());
            if((row %3) == 0)
            {
                System.out.println("");
            }
            myBuffer = null;
        }
    }


    private boolean populateNextSquare(int lastHighestChoice)
    {
        boolean populateSuccess = false;
        // look for the first empty square...

        int[] newSquareCoordinates = this.findOpenSquare();

        int rowCoord = newSquareCoordinates[0];
        int colCoord = newSquareCoordinates[1];

        // There are no empty squares, therefore it's solved....
        if (rowCoord == -1)
        {
            FinalSuccess = true;
            return true;

        }

        // start looking from the last highest choice.
        for(int x = (lastHighestChoice + 1); x < 10; x++)
        {

            if(this.isValidChoice(x, rowCoord, colCoord))
            {
                if(ourBoard[rowCoord][colCoord] == null){
                    ourBoard[rowCoord][colCoord] = new SudokuSquare(x);
                }else{
                    ourBoard[rowCoord][colCoord].setMyValue(x);
                }


                // in case this choice doesn't work, we don't try this one again
                populateSuccess = true;

                // since we were able to populate square we can return.

                SudokuMove ourMove = new SudokuMove();
                ourMove.choice = x;
                ourMove.myLocation = newSquareCoordinates;

                myChangeStack.push(ourMove);

                return populateSuccess;
            }
        }
        // returning false here, not good move was found for this square, we have to backtrack.
        return populateSuccess;
    }

    public dev.nolagsemaphore.sudoku.model.SudokuBoard solveWithSolutionReturned(){
        dev.nolagsemaphore.sudoku.model.SudokuBoard returnBoard = null;

        while(!FinalSuccess)
        {
            boolean populateResult = false;

            populateResult = populateNextSquare(0);

            if (FinalSuccess)
            {
                // instead of printing results to sys out, create return object and return it

                returnBoard = createReturnSolutionBoard();

                return returnBoard;
            }

            // have to backtrack
            while(!populateResult)
            {


                SudokuMove theLastMove =  new SudokuMove();


                theLastMove = (SudokuMove)myChangeStack.pop();



                //FinalSuccess = true; // temporary to keep out infinity
                // now that I have the last move that was a false success,
                // we need to try the next possible move in this square.
                int myLastMoveRow = theLastMove.myLocation[0];
                int myLastMoveCol = theLastMove.myLocation[1];

                int myLastChoice = theLastMove.choice;


                // try to move with a move that is higher than previous choice.
                // if nothing works we pop again and repeat.
                if (myLastChoice == 9)
                {
                    // we have tried all we can with this, and must go back one more move.

                    SudokuMove theLastLastMove =  new SudokuMove();

                    // before I pop twice, I need to reset the 9's square to 10 so it can be chosen again, and added to stack.
                    ourBoard[myLastMoveRow][myLastMoveCol].setMyValue(10);


                    theLastLastMove = (SudokuMove)myChangeStack.pop();

                    // now that I have the last move that was a false success,
                    // we need to try the next possible move in this square.
                    int myLastLastMoveRow = theLastLastMove.myLocation[0];
                    int myLastLastMoveCol = theLastLastMove.myLocation[1];

                    int myLastLastChoice = theLastLastMove.choice;


                    // reset old position to so the open square will be found
                    ourBoard[myLastLastMoveRow][myLastLastMoveCol].setMyValue(10);
                    populateResult = populateNextSquare(myLastLastChoice);
                    theLastLastMove = null;
                }
                // if not go back to trying a new move with the last highest number tried.
                else
                {
                    // reset old position to so the open square will be found
                    ourBoard[myLastMoveRow][myLastMoveCol].setMyValue(10);

                    populateResult = populateNextSquare(myLastChoice);
                    theLastMove = null;
                }
            } // end while(!populateResult)
        } //end while



        return null;
    }


    // here's where the magic takes place...
    public void solve()
    {
        while(!FinalSuccess)
        {
            boolean populateResult = false;

            populateResult = populateNextSquare(0);

            if (FinalSuccess)
            {
                this.printResults();
                return;
            }

            // have to backtrack
            while(!populateResult)
            {


                SudokuMove theLastMove =  new SudokuMove();


                theLastMove = (SudokuMove)myChangeStack.pop();



                //FinalSuccess = true; // temporary to keep out infinity
                // now that I have the last move that was a false success,
                // we need to try the next possible move in this square.
                int myLastMoveRow = theLastMove.myLocation[0];
                int myLastMoveCol = theLastMove.myLocation[1];

                int myLastChoice = theLastMove.choice;


                // try to move with a move that is higher than previous choice.
                // if nothing works we pop again and repeat.
                if (myLastChoice == 9)
                {
                    // we have tried all we can with this, and must go back one more move.

                    SudokuMove theLastLastMove =  new SudokuMove();

                    // before I pop twice, I need to reset the 9's square to 10 so it can be chosen again, and added to stack.
                    ourBoard[myLastMoveRow][myLastMoveCol].setMyValue(10);


                    theLastLastMove = (SudokuMove)myChangeStack.pop();

                    // now that I have the last move that was a false success,
                    // we need to try the next possible move in this square.
                    int myLastLastMoveRow = theLastLastMove.myLocation[0];
                    int myLastLastMoveCol = theLastLastMove.myLocation[1];

                    int myLastLastChoice = theLastLastMove.choice;


                    // reset old position to so the open square will be found
                    ourBoard[myLastLastMoveRow][myLastLastMoveCol].setMyValue(10);
                    populateResult = populateNextSquare(myLastLastChoice);
                    theLastLastMove = null;
                }
                // if not go back to trying a new move with the last highest number tried.
                else
                {
                    // reset old position to so the open square will be found
                    ourBoard[myLastMoveRow][myLastMoveCol].setMyValue(10);

                    populateResult = populateNextSquare(myLastChoice);
                    theLastMove = null;
                }
            } // end while(!populateResult)
        } //end while



        return;
    }


    public void setInitialSquareValue(int x, int y, int myValue)
    {
        SudokuSquare currentSquare = new SudokuSquare(myValue);
        ourBoard[x][y] = currentSquare;
    }


    private boolean isInRow(int value, int rowNumber)
    {
        for(int x = 1; x<10; x++)
        {
            if ( ourBoard[rowNumber][x] != null && ourBoard[rowNumber][x].getMyValue() == value)
            {
                return true;
            }
        }
        return false;
    }

    // when you find the first one, try the first working choice. Then find the next and the next working one.
    // Then if you don't find a working choice, make you current choice back to 10 and go back to the last one you changed before this.

    // This int array will contain the first available row in the first
    // index(0) and the first available column in the second index(1)
    public int[] findOpenSquare()
    {
        int[] ourOpenSquare = new int[]{-1,-1};


        int currentValue = -1;

        for (int row = 1; row < 10; row++)
        {
            for(int column = 1; column < 10; column++)
            {
                // populating with nulls now instead of "10"
                if(ourBoard[row][column] == null || (ourBoard[row][column].getMyValue()) == 0){
                    currentValue = 10;
                }else{
                    currentValue = (ourBoard[row][column].getMyValue());
                }


                if(currentValue == 10)
                {
                    ourOpenSquare[0] = row;
                    ourOpenSquare[1] = column;
                    return ourOpenSquare;
                }
            }
        }
        return ourOpenSquare;
    }



    private boolean isInColumn(int value, int columnNumber)
    {
        for(int row = 1;row<10;row++)
        {
            if( ourBoard[row][columnNumber] != null && ourBoard[row][columnNumber].getMyValue() == value)
            {
                return true;
            }
        }
        return false;
    }

    // this will find if there is the given value in the 3 by 3 grid that belongs to the x y
    private boolean isInGrid(int value, int row, int column)
    {
        int[] startingPoint = new int[]{0,1,1,1,4,4,4,7,7,7};

        // loop through row first
        for(int looprow = startingPoint[row]; looprow < (startingPoint[row]+3) ; looprow++)
        {
            for(int loopcolumn = startingPoint[column]; loopcolumn <  (startingPoint[column]+3); loopcolumn++)
            {

                if(ourBoard[looprow][loopcolumn] != null && ourBoard[looprow][loopcolumn].getMyValue() == value)
                {
                    return true;
                }
            }
        }
        return false;
    }

    // This method consolidates all the individual checking methods into one.
    public boolean isValidChoice(int value, int row, int column)
    {
        if(!isInGrid(value, row, column))
        {
            if(!isInColumn(value,column))
            {
                if(!isInRow(value, row))
                {
                    return true;
                }
            }
        }
        return false;
    }


    public int getSquareValue(int x, int y)
    {
        int returnValue = 0;
        if(ourBoard[x][y] == null){
            returnValue = 10;
        }else{
            returnValue = ourBoard[x][y].getMyValue();
        }

        return returnValue;
    }


    // constructor
    public SudokuBoard()
    {
        super();
        FinalSuccess = false;
        ourBoard = new SudokuSquare[10][10];
    }
}