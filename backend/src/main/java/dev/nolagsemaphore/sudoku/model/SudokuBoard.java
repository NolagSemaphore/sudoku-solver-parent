package dev.nolagsemaphore.sudoku.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SudokuBoard
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T14:58:34.555439300-04:00[America/New_York]", comments = "Generator version: 7.9.0")
public class SudokuBoard {

  @Valid
  private List<List<@Min(0) @Max(9)Integer>> board = new ArrayList<>();

  public SudokuBoard() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SudokuBoard(List<List<@Min(0) @Max(9)Integer>> board) {
    this.board = board;
  }

  public SudokuBoard board(List<List<@Min(0) @Max(9)Integer>> board) {
    this.board = board;
    return this;
  }

  public SudokuBoard addBoardItem(List<@Min(0) @Max(9)Integer> boardItem) {
    if (this.board == null) {
      this.board = new ArrayList<>();
    }
    this.board.add(boardItem);
    return this;
  }

  /**
   * A 9x9 grid representing the Sudoku puzzle
   * @return board
   */
  @NotNull @Valid 
  @Schema(name = "board", example = "[[5,3,0,0,7,0,0,0,0],[6,0,0,1,9,5,0,0,0],[0,9,8,0,0,0,0,6,0],[8,0,0,0,6,0,0,0,3],[4,0,0,8,0,3,0,0,1],[7,0,0,0,2,0,0,0,6],[0,6,0,0,0,0,2,8,0],[0,0,0,4,1,9,0,0,5],[0,0,0,0,8,0,0,7,9]]", description = "A 9x9 grid representing the Sudoku puzzle", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("board")
  public List<List<@Min(0) @Max(9)Integer>> getBoard() {
    return board;
  }

  public void setBoard(List<List<@Min(0) @Max(9)Integer>> board) {
    this.board = board;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SudokuBoard sudokuBoard = (SudokuBoard) o;
    return Objects.equals(this.board, sudokuBoard.board);
  }

  @Override
  public int hashCode() {
    return Objects.hash(board);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SudokuBoard {\n");
    sb.append("    board: ").append(toIndentedString(board)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

