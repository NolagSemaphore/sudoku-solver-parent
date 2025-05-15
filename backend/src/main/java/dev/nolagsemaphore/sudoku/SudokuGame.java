package dev.nolagsemaphore.sudoku;

import java.io.*;


public class SudokuGame
{

    SudokuBoard myBoard;

    boolean[][] presetSpots;

    public void getUserInputChoices()
    {
        String typedValue = "";
        System.out.println("Welcome to the Instant Sodoku Solver!");

        boolean gettingInput = true;

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        while(gettingInput)
        {
            try
            {
                System.out.println
                        ("Enter Row number of preset sodoku square(1-9 from top to bottom:(press 99 to exit and get your solution) ");

                typedValue = keyboard.readLine();
                int myIntRowChoice  = Integer.parseInt(typedValue);

                if(myIntRowChoice == 99)
                {
                    gettingInput = false;
                }
                else
                {
                    System.out.println("Enter Column number of preset sodoku square(1-9 from top to bottom: ");


                    typedValue = keyboard.readLine();

                    int myIntColChoice = Integer.parseInt(typedValue);


                    System.out.println("Enter Preset choice for this square: ");

                    typedValue = keyboard.readLine();
                    int myIntValueChoice = Integer.parseInt(typedValue);

                    myBoard.setInitialSquareValue(myIntRowChoice, myIntColChoice, myIntValueChoice);
                    presetSpots[myIntRowChoice][myIntColChoice] = true;
                }


            }
            catch( IOException e )
            {
                System.out.println("Error!");
            }
            catch(java.lang.NumberFormatException e)
            {
                System.out.println("only type numbers and then press enter");

            }
        }// end while

        for(int row = 1; row < 10; row++)
        {
            for(int col = 1; col < 10; col++)
            {
                if(!presetSpots[row][col])
                {
                    myBoard.setInitialSquareValue(row, col, 10);
                }


            }

        }
    }

    // Constructor
    public SudokuGame()
    {
        presetSpots = new boolean[10][10];

        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                presetSpots[i][j] = false;
            }

        }
        myBoard = new SudokuBoard();
        this.getUserInputChoices();

    }

    public SudokuGame(dev.nolagsemaphore.sudoku.model.SudokuBoard boardFromFrontEnd){
        presetSpots = new boolean[10][10];

        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                presetSpots[i][j] = false;
            }

        }
        myBoard = new SudokuBoard();

        // Legacy solver uses 1-based indexing, so adjust accordingly
        for(int row = 0; row < 9; row++){
            for(int column = 0; column < 9; column ++){
                Integer selection = boardFromFrontEnd.getBoard().get(row).get(column);
                if(null != selection && selection != 0 ){
                    // offsetting by 1 to account for ugliness on earlier implementation.
                    myBoard.setInitialSquareValue(row+1, column+1, selection);
                    presetSpots[row+1][column+1] = true;
                }
            }
        }
    }

    public dev.nolagsemaphore.sudoku.model.SudokuBoard solveWithSolutionReturned(){
        return myBoard.solveWithSolutionReturned();
    }

    public boolean validateInputs(){
        return myBoard.isInitialBoardValid();
    }

    public void solve()
    {
        myBoard.solve();
    }
}