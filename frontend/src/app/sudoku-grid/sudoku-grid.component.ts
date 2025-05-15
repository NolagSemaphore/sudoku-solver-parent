import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

type SudokuValue = number | null;

@Component({
  selector: 'app-sudoku-grid',
  standalone: true,
  imports: [CommonModule,FormsModule, HttpClientModule],
  templateUrl: './sudoku-grid.component.html',
  styleUrl: './sudoku-grid.component.css'
})


export class SudokuGridComponent {
  

  
  board: SudokuValue[][] = Array.from({ length: 9 }, () => Array(9).fill(null));


  constructor(private http: HttpClient) {}


  solveSudoku() {
    const requestBody = {
      board: this.board.map(row => row.map(cell => cell ?? 0))
    };

    this.http.post<{ board: number[][] }>('/api/sudoku/solve', requestBody)
      .subscribe({
        next: response => {
          this.board = response.board;
        },
        error: err => {
          console.error('Error solving Sudoku:', err);
        }
      });
  }

  clearBoard() {
    this.board = Array.from({ length: 9 }, () => Array(9).fill(null));
  }

  trackByIndex(index: number): number {
    return index;
  }
  
  filterInput(event: KeyboardEvent): void {
    const key = event.key;
    if (!/^[1-9]$/.test(key)) {
      event.preventDefault();
    }
  }
  
}

