# AdaShip - Advanced Programming
AdaShip is a copy of the classic Battleships game, where the player takes turns against the enemy strategically firing at each others ships.

## Challenge Outline
The assignment was to create an adaptation of the classic paper-based Battleships game, using a structured, object-oriented approach. Code should be complex and of a high standard, minimising duplication and inconsistency. The code needs to meet some basic requirements which were:
- Read in a configuration file
- A usable and efficient set-up interface
- A computer based opponent that play strategically
- A usable and efficient turn-based process of gameplay

### My solution
#### UML Diagram

#### Approach
My approach to challenge will be to break it down, into smaller tasks that can be worked on individually. Starting with outlining the classes as shown on my UML diagram, and then developing the game further. I would first focus all efforts on completing the turn-based gameplay design and implementation. Next developing the computer based opponent and finishing with the set-up interface. After finshing each task I will go over what I have added and refactor my code, applying the high standards required. Upon finishing my solution I will go over the code and comment, explaining the uses of certain classes and functions. Testing will be constant throughout.

## Development
Throughout development I have kept a consistant layout of classes and related naming of vairables. All classes begin with a list of the objects private variables being declared. Variable names are related to the role of the method and the data its storing. Next comes the constructor, where all of the objects' variables are assigned, some with parameters. Next are Getters and setters, and all other methods. A consistent layout provides an efficient, easily maintainable code, but also a readable system.

Although the system is complex, I have maintained a simple logic flow. Starting with reading in the configuration file and setting up the board dimensions and ship objects. Then running the menus and starting a new game. The system stays in a turn-based loop following a logical path through the classes and methods until someone wins. I have also avoided multipurpose functions and instantiating classes more than once, to avoid confusion and adopt best practices.

### Phase 1: UML Diagram Implementation

### Phase 2: Board Design and Gameplay
Whilst designing the board and implementing a simple console version, I decided to make a change and begin working with Java Swing libraries to create an interactive interface. This change would deviate from the initial solution, however would still roughly follow the UML Diagram and produce an improved solution. I used an array of panels each with their own button that corresponded with the players' grids, which had been marked with their ships locations. I then coloured in these buttons according to their type, for example and ocean tile was blue, a ship was yellow, a hit was red, and a miss was white. Using a frame I could display two of these grids, one showing the players ships, and the other was blank. I could then use event listeners on the buttons to trigger the turn-based gameplay, whilst recording the players hit/miss on the enemys grid. 

### Phase 3: Computer Opponent
When turn-based gameplay was implemented, I could then work on developing the computer opponent. I started with using generating a random number for the coordinates, and tested to make sure the turn-based gameplay was working properly. I then designed a logical flowchart that I could use to make the computer play strategically. I was able to implement the flowchart and improve on the opponents thought process.

### Phase 4: Menu Design Development
The menu design was incredibly simple, consisting of just buttons and text. I was able to re-use objects from the board design such as frames and panels. Using inheritance I created child classes from these objects, making the classes more efficient and reducing repitition. However, I was unable to fully complete one of my tasks due to time constraints, forcing players to only play randomly placed ships.

### Challenges and Innovations
A key design challenge was switching to an interactive interface and combining practices of advanced programming with the external objects and methods needed to produce frames, panels and buttons. This resulted in lots of testing and refactoring in order to produce 'good' well maintainable and efficient code. An interactive interface also presented problems with accessing the other players' grids and buttons in order to change their colours and record hits/misses. Using a singleton pattern helped me access update and access these objects from anywhere in the system.

## Evaluation
