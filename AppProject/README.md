# Flappy Bird Java Clone

<p align="center">
  <img src="src/image.png" alt="Flappy Bird Screenshot" width="300"/>
</p>

A modern, object-oriented Flappy Bird clone built with Java Swing. This was my attempt at delving into game development, GUI fundamentals and re-creating a childhood favourite game.

## Features
- Smooth 60 FPS gameplay
- Modular, maintainable codebase (OOP, MVC-inspired)
- Responsive keyboard and mouse controls
- Pause/resume functionality (ESC/Space)
- Restart after game over (Space)
- Professional UI with anti-aliased fonts and overlays
- Bird rotation animation for a polished look
- Centralized configuration for easy tuning
- High score tracking (per session)

## How to Run
1. **Compile:**
   ```bash
   cd AppProject
   javac -d bin src/*.java
   ```
2. **Run:**
   ```bash
   java -cp bin App
   ```

## Controls
- **Spacebar / Mouse Click:** Flap, start, restart, or resume
- **ESC:** Pause/unpause

## Project Structure
- `src/`
  - `App.java` — Main entry point
  - `FlappyBird.java` — Main game panel and loop
  - `Bird.java` — Bird logic and animation
  - `Pipe.java` — Pipe logic
  - `GameStateManager.java` — State and score management
  - `InputManager.java` — Keyboard/mouse input
  - `UIManager.java` — All UI rendering
  - `GameConfig.java` — Centralized game constants
  - `GameState.java` — Enum for game states
- `bin/` — Compiled classes

## Technical Highlights
- **Object-Oriented Design:** Each game concept is its own class
- **Separation of Concerns:** UI, input, state, and logic are modular
- **Enum-based State Management:** Robust, extensible game states
- **Professional UI:** Menus, overlays, and score display
- **Animation:** Bird rotation for visual polish
- **Easy Extensibility:** Add features or tweak difficulty via `GameConfig`

## Why This Project Stands Out
- Demonstrates Java Swing GUI skills
- Clean, interview-ready code
- Easy to explain and extend
- Great for learning or teaching OOP/game dev concepts

---

*Created as a learning project and portfolio piece. Inspired by the original Flappy Bird.*