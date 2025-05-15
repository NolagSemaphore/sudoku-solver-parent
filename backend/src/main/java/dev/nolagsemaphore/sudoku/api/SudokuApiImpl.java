package dev.nolagsemaphore.sudoku.api;

import dev.nolagsemaphore.sudoku.SudokuGame;
import dev.nolagsemaphore.sudoku.model.SudokuBoard;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SudokuApiImpl implements SudokuApi{

    @Override
    public ResponseEntity<?> solveSudoku(@Valid @RequestBody SudokuBoard userSuppliedBoard){
        try {

            SudokuGame myGame = new SudokuGame(userSuppliedBoard);

            if(!myGame.validateInputs()){
                throw new IllegalArgumentException("Invalid puzzle: duplicates found in row/column/box.");
            }

            SudokuBoard returnSolution = myGame.solveWithSolutionReturned();


            return ResponseEntity.ok(returnSolution);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // Something else went wrong
        }
    }

}
