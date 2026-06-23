# 🚀 DSA Algorithm Visualizer

A full-stack algorithm visualization app built with a React frontend and a Java Spring Boot backend.
This project helps learners explore data structures and algorithms through interactive visualizations, execution traces, and step-by-step runtime state.


URL : https://a-gpt.onrender.com
---

## 🔍 Project Overview

This application visualizes algorithm execution in a user-friendly interface.
The frontend provides controls for selecting algorithms, adjusting speed, and stepping through execution, while the backend computes algorithm traces, metadata, and explanations

### Key capabilities

- Visualize sorting, search, and pointer-based algorithms
- Show array state, pointer positions, swaps, and comparisons
- Step-by-step execution with play/pause and speed control
- Backend-generated algorithm metadata and explanations
- Local computation only — no external AI or third-party trace service required

---

## 🧩 Tech Stack

| Layer | Technology | Description |
|---|---|---|
| Frontend | ![React](https://img.shields.io/badge/React-61DAFB?logo=react&logoColor=white) React 19 | UI and visualization components built with React and Vite |
| Frontend Tooling | ![Vite](https://img.shields.io/badge/Vite-646CFF?logo=vite&logoColor=white) Vite | Fast development server and build tooling |
| Backend | ![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white) Java 17 | Algorithm trace generation and API logic |
| Backend Framework | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-6DB33F?logo=springboot&logoColor=white) Spring Boot | REST API and web service framework |
| Build | ![Maven](https://img.shields.io/badge/Maven-3.9.0-007D9C?logo=apachemaven&logoColor=white) Maven | Java dependency management and build |

---

## 📈 System Workflow Diagram

```text
+----------------------+          HTTP          +---------------------------+
|                      |  GET /api/algorithms  |                           |
|   React Frontend     |---------------------->|   Spring Boot Backend      |
|  (frontend/)         |                       |  (backend/)               |
|                      |                       |                           |
|  - UI selection      |                       |  - algorithm metadata     |
|  - playback controls |                       |  - execution trace engine |
|  - step-by-step view |                       |  - result payload         |
+----------------------+                       +---------------------------+
           |                                                ^
           | POST /api/algorithms/{id}/run                  |
           |----------------------------------------------->|
           |                                                |
           |                 JSON response                   |
           |<-----------------------------------------------|
           v                                                |
+----------------------+                                       
|                      |                                       
|  Visual timeline     |                                       
|  + array state       |                                       
|  + pointer markers   |                                       
|  + swap highlights   |                                       
|                      |                                       
+----------------------+                                       
```

---

## 📂 Code Structure Diagram

```text
DSA-Algorithm-visualizer-main/
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/example/algoviz/
│       ├── AlgoVizApplication.java          # Spring Boot entrypoint
│       ├── controller/
│       │   └── AlgorithmController.java    # REST API endpoints
│       ├── model/
│       │   ├── AlgorithmInfo.java          # algorithm metadata
│       │   ├── AlgorithmResult.java        # API response payload
│       │   ├── CodeLine.java               # trace step source / state
│       │   └── ExecutionStep.java          # step-by-step execution model
│       └── service/
│           └── AlgorithmService.java       # algorithm trace generator
├── frontend/
│   ├── package.json
│   ├── vite.config.js
│   ├── App.jsx                             # main React application
│   ├── main.jsx                            # React entrypoint
│   ├── App.css
│   ├── index.css
│   └── README.md                           # frontend notes
└── render.yaml                             # deployment/render configuration
```

---

## 🚀 Run Locally

### 1. Start the backend

```bash
cd backend
mvn spring-boot:run
```

By default, the backend runs on `http://localhost:8080`.

### 2. Start the frontend

```bash
cd frontend
npm install
npm run dev
```

Visit the Vite URL shown in the terminal, typically `http://localhost:5173`.

> The frontend communicates with the backend through `/api` endpoints.

---

## 🧠 Supported Algorithm Examples

- Bubble Sort
- Selection Sort
- Remove Duplicates
- Binary Search
- Two Pointers Sum
- Insertion Sort

---

## 📌 Notes

- The backend handles algorithm execution logic and returns structured trace data.
- The frontend renders dynamic visualizations and user controls.
- This app is intended as an educational tool for understanding algorithm behavior.

---

## 🧑‍💻 Author

**Ankush** & **Parth**
if you like it then  give a star
