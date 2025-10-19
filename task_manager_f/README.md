Perfect ✅ — Here’s your **complete English version** of the `README.md` for your **frontend project (`task_manager_f`)**, rewritten professionally and formatted for GitHub.
It includes everything from tools, structure, Docker setup, and environment configuration.

---

# 🎨 Task Manager Frontend

A modern **React + TypeScript + Vite** frontend for the **Task Manager** application.
Styled with **TailwindCSS**, linted with **ESLint**, and ready for production with **Docker** and **Nginx**.
This project connects to the Spring Boot backend via REST APIs.

---

## 🧱 Overview

This project provides the **user interface** for the **Task Manager** system — a Trello-like web application that allows users to:

* Create and manage **projects**
* Organize **tasks** with multiple states
* Assign users and manage permissions via **roles** (`ADMIN`, `PROJECT_MANAGER`, `USER`)
* Communicate securely with the backend using **JWT authentication**

---

## 🧩 Technologies Used

| Tool                          | Purpose                                       |
| ----------------------------- | --------------------------------------------- |
| ⚛️ **React 18**               | Frontend library for building user interfaces |
| ⚡ **Vite 5**                  | Fast build tool and development server        |
| 💡 **TypeScript 5**           | Typed superset of JavaScript                  |
| 🎨 **TailwindCSS 3**          | Utility-first CSS framework                   |
| 🧭 **Lucide React**           | Modern SVG icon library for React             |
| ✅ **ESLint 9**                | Static code analysis for code quality         |
| ⚙️ **PostCSS + Autoprefixer** | CSS processing and vendor prefixing           |

---

## 📂 Project Structure

```
task_manager_f/
├── src/
│   ├── components/      # Reusable UI components
│   ├── contexts/        # Global contexts (auth, theme, etc.)
│   ├── services/        # API service layer for backend communication
│   ├── types/           # TypeScript types and interfaces
│   ├── App.tsx          # Root component
│   ├── main.tsx         # Application entry point
│   ├── index.css        # Global styles
│   └── vite-env.d.ts    # Vite environment types
│
├── dist/                # Production build output
├── Dockerfile           # Docker configuration (Node + Nginx)
├── index.html           # Main HTML file
├── tailwind.config.js   # TailwindCSS configuration
├── postcss.config.js    # PostCSS configuration
├── tsconfig*.json       # TypeScript configurations
├── vite.config.ts       # Vite configuration
└── package.json         # Project dependencies and scripts
```

---

## ⚙️ Available Scripts

| Command           | Description                                            |
| ----------------- | ------------------------------------------------------ |
| `npm run dev`     | Starts the development server with hot reload          |
| `npm run build`   | Builds the project for production in the `dist` folder |
| `npm run preview` | Serves the production build locally for testing        |
| `npm run lint`    | Runs ESLint to check code quality                      |

