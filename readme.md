# 🧱 OOP Brick Breaker Game (Java)

This project is a classic Brick Breaker game developed using **Java** and **Swing**, designed to showcase mastery of **Object-Oriented Programming (OOP)** principles, including **Encapsulation**, **Abstraction**, **Inheritance**, and **Polymorphism**.

## ✨ Key Features & Technical Highlights

* **Polymorphic Brick Hierarchy:** The game uses a single abstract superclass (`Brick.java`) to manage four distinct brick behaviors:
    * **`MultiHitBrick`:** Requires 3 hits, changes color (`Red` → `Orange` → `White`) with each impact.
    * **`PowerUpBrick`:** Requires 2 hits, and on breaking, grants the **Super-Ball** power-up.
    * **`SimpleBrick`:** Breaks on 1 hit.
    * **`UnbreakableBrick`:** Remains permanently on the map.
* **"Fired Up" Super-Ball Power-Up:** The `Ball` object enters a temporary super-state, allowing it to instantly destroy any brick it touches without bouncing back. The state ends when the ball hits the paddle again.
* **Tuned Difficulty:** Ball speed increases progressively only after every 4 paddle bounces, preventing unbalanced difficulty jumps.
* **OOP Architecture:** The game state and behavior of core components (`Ball`, `Paddle`, `MapGenerator`) are strictly separated into their own classes, demonstrating clean **Encapsulation** and **Composition**.
* **Time Tracking:** Includes a real-time game clock to measure player efficiency.

## ⚙️ Project Structure (10 Files)
 
| File/Class                 | Role in OOP Hierarchy                 | Core OOP Concept |
| :--------------------------| :-------------------------------------| :----------------|
| **`Gameplay.java`**        | Game Engine/Controller                | Composition      |
| **`MapGenerator.java`**    | Level Data Manager                    | Abstraction      |
| **`Ball.java`**            | Entity State (with Power-Up State)    | Encapsulation    |
| **`Paddle.java`**          | Entity State (Input Control)          | Encapsulation    |
| **`Brick.java`**           | **Abstract Superclass**               | Abstraction      |
| **`SimpleBrick.java`**     | Concrete Subclass (1 Hit)             | Inheritance      |
| **`MultiHitBrick.java`**   | Concrete Subclass (3 Hits)            | Polymorphism     |
| **`PowerUpBrick.java`**    | Concrete Subclass (2 Hits + Power-Up) | Polymorphism     |
| **`UnbreakableBrick.java`**| Concrete Subclass (Permanent)         | Polymorphism     |
| **`Main.java`**            | Application Entry Point               | Modularity       |

## 🚀 Getting Started

### Prerequisites

* **Java Development Kit (JDK) 8 or higher.**

### How to Run

1.  **Clone the repository or download the source code.**
2.  **Open the project** in your favorite IDE (VS Code, IntelliJ, Eclipse).
3.  Ensure all ten (`10`) `.java` files are correctly placed in the same source directory (`src/`).
4.  Execute the `main` method in the **`Main.java`** file.

### Controls

| Key             | Action                    |
| :---------------| :-------------------------|
| **Left Arrow**  | Move paddle left.         |
| **Right Arrow** | Move paddle right.        |
| **Enter**       | Start or restart the game.|