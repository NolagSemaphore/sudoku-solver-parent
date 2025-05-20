import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { environment } from '../../environments/environment';


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

  private apiUrl = environment.apiUrl;
  errorMessage: string | null = null;


  constructor(private http: HttpClient) {}


  solveSudoku() {
    const requestBody = {
      board: this.board.map(row => row.map(cell => cell ?? 0))
    };
  
    this.http.post<{ board: number[][] }>(`${this.apiUrl}/solve`, requestBody)
      .subscribe({
        next: response => {
          this.board = response.board;
          this.errorMessage = null;
        },
        error: err => {
          if (err.status === 400 && err.error) {
            this.errorMessage = err.error;
          } else {
            this.errorMessage = "An unexpected error occurred.";
          }
          console.error('Error solving Sudoku:', err);
        }
      });
  }

  clearBoard() {
    this.board = Array.from({ length: 9 }, () => Array(9).fill(null));
    this.errorMessage = null;
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

