import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes } from '@angular/router';
import { SudokuGridComponent } from './app/sudoku-grid/sudoku-grid.component';  // Adjust path as needed


const routes: Routes = [
  {
    path: '',
    component: SudokuGridComponent
  }
];


bootstrapApplication(AppComponent, {
  providers: [provideRouter(routes)],
}).catch(err => console.error(err));
