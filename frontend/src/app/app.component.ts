import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `<div class="page-container">
  <h1>Sudoku Solver</h1>
  <p class="instructions">
    Enter your known values into the grid and press the <strong>Solve</strong> button to fill in the rest.
  </p>
  <router-outlet></router-outlet>
</div>`,
  styles: [`
    .page-container {
      text-align: center;
      padding: 20px;
      color: white;
      font-family: sans-serif;
    }
    h1 {
      margin-bottom: 2em;
      font-size: 2.5em;
      color: #000;
    }
    .instructions {
      margin-top: 0.2em;
      font-size: 1.1em;
      color: #000;
    }
  `]
})
export class AppComponent {
  title = 'Sudoku Solver';
}
