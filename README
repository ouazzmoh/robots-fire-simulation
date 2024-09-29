## Project Overview

This project simulates the operations of a team of firefighting robots with different characteristics working together to extinguish fires of varying intensities across different terrains. The simulation distributes fires to the robots according to the selected strategy, calculates the shortest path, and orchestrates round trips for refilling water tanks and putting out the fire completely.

---

## Key Features

### Event Management

The simulation manages events using a `SortedMap<Long, LinkedList<Evenement>>` structure that ensures the order and uniqueness of events. As the simulation runs, robots take actions such as moving to fires, refilling their tanks, and extinguishing fires according to the events scheduled. This structure handles the passage of time and adjusts for delays between robots' actions, such as movement speed and time to extinguish the fire.

### Pathfinding Algorithms

The project implements a pathfinding algorithm:
 - **A-star** (chosen for optimal performance)

The A-star algorithm is used for finding the shortest path, with a heuristic function based on the Manhattan distance, adjusted by factors such as the robot's speed and terrain size. This approach optimizes the search by prioritizing paths that appear closer to the destination.

### Firefighting Strategies

The simulation supports two main strategies for assigning robots to fires:

1. **Elementary Strategy** (`ChefPompierSimple`):
   - Fires are assigned randomly to available robots, even if they are far from the fire.
   - The robot computes the shortest path to the fire and refills water when needed.
   
2. **Evolved Strategy** (`ChefPompierEvolue`):
   - Fires are assigned to the nearest available robot using a `closestRobot(Incendie incendie)` method.
   - Robots find the shortest path to the fire and manage refilling and firefighting actions efficiently.

### Time and Event Handling

The simulation tracks multiple variables for each robot (e.g., current reservoir level, current fire intensity, current position) and manages them through a dynamic event queue. The robots’ movement, firefighting, and refilling actions are calculated and scheduled in advance but executed step by step as time progresses in the simulation.

---

## How to Use

To compile all files:

```bash
make all
```

To execute, for example, the elementary strategy on the "desertOfDeath-20x20.map" map:
```bash
$ make exeElementaire CARTE=desertOdDeath-20x20
```
To execute, for example, the evolved strategy on the "mushroomOfHell-20x20.map" map:

```bash
$ make exeEvolue CARTE=mushroomOfHell-20x20
```

### Project Structure:

- **src**: Contains the classes.
- **cartes**: A few example data files.
- **bin/gui.jar**: Java archive containing the graphical interface classes. See an example of usage in `TestInvader.java`.
- **doc**: Documentation (API) of the classes.

### Remarks:

- The reset causes some problems for tests without `chefPompier` (TestPath and TestDeplacement), but it works perfectly for tests with a strategy (a `chefPompier`).
- For the "carteSujet.map" map, the size of the provided cells is very large, so we recommend adjusting the simulation time in the interface.


