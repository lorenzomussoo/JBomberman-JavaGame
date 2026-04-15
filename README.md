# JBomberman 💣 

A Java-based clone of the classic Bomberman arcade game series originally created by Hudson Soft in 1983. This project was developed as part of a University course in Programming Methodologies at Sapienza University of Rome.

## 📖 Overview
The game consists of multiple sequential levels where the player takes control of Bomberman. The primary goal is to strategically place bombs to destroy destructible blocks, eliminate all enemies, and find the hidden trapdoor to progress to the next stage before the timer runs out. The game features an increasing difficulty curve, culminating in special Boss fights on Level 4 and Level 8.

## ✨ Gameplay Features
* **7 Unique Enemies & Bosses:** Encounter a variety of foes including Pakupa, Puropen, Bakuda, Cuppen, Senshiyan, Bigaron, and ClownMask. Each enemy has distinct movement patterns and health points.
* **Advanced Boss Mechanics:** Bosses require different strategies to defeat and have the unique ability to pass through destructible blocks unhindered.
* **Power-Ups & Penalties:** Destroying blocks can reveal hidden items that drastically change gameplay:
  * *Positive Effects:* Extra bomb capacity, movement speed boost, temporary invincibility, extra life, bonus points, timer reset, and a remote control to detonate bombs at will.
  * *Negative Effects:* Drastic speed reduction, loss of a life, or a decrease in maximum bomb capacity.
* **Level Editor:** A built-in graphical editor allowing players to design custom stages by clicking to place blocks and enemies, which can then be played immediately.
* **Robust Save System:** User profiles are saved using custom nicknames. The system tracks preferred control schemes, custom avatar colors, game statistics (wins, losses, total score), and custom-created levels.
* **Dual Control Schemes:** * *Keyboard:* W, A, S, D (or Arrow Keys) to move, 'B' to place bombs, 'Spacebar' to detonate remote bombs, and 'Esc' to exit a match. Menus can be navigated with arrows and 'Enter'.
  * *Mouse:* Click and hold in a direction to move, Right-Click (or trackpad equivalent) to place bombs.

## 🛠️ Technical Architecture
This application heavily emphasizes Object-Oriented Programming (OOP) principles and advanced Java design patterns:

* **MVC Architecture:** Clean separation of concerns between Model (game logic, entities, user data), View (JavaFX GUI components), and Controller (input handling, window management).
* **Observer/Observable Pattern:** Utilized for decoupled, asynchronous communication between the Model and View, specifically to update the real-time score GUI whenever an enemy is defeated.
* **Strategy Pattern:** Encapsulates the unique movement behaviors for each of the 7 enemy types. An overarching `ComportamentoNemico` interface is implemented by specific behavior classes to dictate pathfinding logic and sequences dynamically.
* **Java Streams:** Used extensively for advanced data filtering. Streams are utilized to check for the presence of Bosses (which alters the generation of destructible blocks in a level) and to verify if all enemies are eliminated when the player steps on the trapdoor.
* **JavaFX & SceneBuilder:** Handles the entire graphical user interface using FXML files for menus, stats (including a PieChart for win/loss ratios), and the level editor. The `AnimationTimer` class drives the core game loop, handling sprite animations, bomb countdowns, and entity movement.
* **Serialization:** Employs the `Serializable` interface to save and load user profiles, converting complex Java objects into `.bomb` save files.
* **Audio Management:** A dedicated AudioManager class handles the playback of all in-game sound effects and background music.

## 🚀 Getting Started

### Prerequisites
* Java Development Kit (JDK) 11 or higher.
* JavaFX SDK configured in your environment.

### Installation
1. Clone the repository:
   ```bash
   git clone [https://github.com/yourusername/JBomberman.git](https://github.com/yourusername/JBomberman.git)
2. Open the project in your preferred IDE (Eclipse, IntelliJ IDEA).
3. Add the JavaFX lib folder to your project's build path and configure the VM options:
   ```bash
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml,javafx.media
4. Run the main application file from the src directory to launch the game.
