# Sudoku Solver App

This is a full-stack web application for solving Sudoku puzzles, built with:

- **Angular 19** (frontend)
- **Spring Boot (Java)** (backend)

The app allows users to input Sudoku puzzles via a web interface, send them to a REST API, and receive the solved puzzle in real time.

---

## 🔧 Features

- Interactive 9x9 grid input
- REST API backend with recursive backtracking solver
- Instant solution generation
- Clean separation between frontend and backend
- Designed for deployment on platforms like Render

---

## 📁 Project Structure

```
sudoku-solver-parent/
├── frontend/   → Angular 19 app (UI for input and displaying solution)
└── backend/    → Spring Boot REST API (handles solving logic)
```

---

## 🚀 Getting Started

### Backend (Spring Boot)
1. Open a terminal:
```bash
cd backend
./mvnw spring-boot:run
```

> Make sure Java 17+ and Maven are installed.

### Frontend (Angular)
1. Open another terminal:
```bash
cd frontend
npm install
ng serve
```

> Requires Node.js and Angular CLI.

---

## 🌐 Live Demo (Coming Soon)

This app will be deployed on [Render.com](https://render.com). A public link will be added here once live.

---

## 📦 Built With

- Java 17
- Spring Boot
- Angular 19
- RESTful API
- Git & GitHub

---

## 🤝 Contributions

Pull requests and feedback are welcome. This project is for learning, showcasing, and future enhancements.

---

## 📄 License

This project is licensed under the MIT License.
