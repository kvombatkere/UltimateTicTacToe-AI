## Ultimate Tic Tac Toe AI Framework

#### A Tic Tac Toe implementation in Java that implements adversarial search using the Minimax algorithm with Alpha-Beta pruning. There are 3 main components in this project:
1. Regular 3x3 Tic Tac Toe AI player. This AI never loses and will always beat a sub-optimal opponent. 
2. Advanced Tic Tac Toe with a heuristic AI player.  Advanced Tic Tac Toe comprises nine 3x3 Tic Tac Toe boards, and the goal is to win __1 board__.
3. Ultimate Tic Tac Toe framework with a heuristic AI player. Ultimate Tic Tac Toe comprises nine 3x3 Tic Tac Toe boards, and the goal is to win __3 boards__ in a row.
   The heuristic AI searches promising game states upto a certain recursive depth, and was tested to beat a control player in 99 out of 100 games.
   
This framework is fully playable and ready to use. The project can be cloned and the code compiled and run as follows:
```
git clone https://github.com/kvombatkere/UltimateTicTacToe-AI
./compile.sh
```
To play the regular Tic Tac Toe game, against the regular AI:
```
java TTTGame
```
To play the Advanced Tic Tac Toe game, against the heuristic AI:
```
java AdvancedTTTGame
```
To play the Ultimate Tic Tac Toe game, against the heuristic AI:
```
java UltimateTTTGame
```

The snapshot below shows the command-line UI for the regular Tic Tac Toe game, however the implementation also allows for 2 AI players to play each other
   
![TTTgame](/images/TTTXwin.PNG)

Here are some basic code implementation notes:
1. **TTTBoard**: Tic Tac Toe board class, with the necessary information
about the state of the board, applicable actions and methods to enable the transition model. The
most important of these instance variables is a two-dimensional character array called **mainBoard** that
holds the actual state of the board. This class also provides methods for converting
between the 3x3 coordinate system of the two-dimensional array and the 1-9 positions specified in
the project as well as methods for checking for a winner, displaying and clearing the board, getting
a list of possible moves, and other helper methods.

2. **TTTGame**: Game blueprint, which includes the various I/O necessities - it consists primarily of a main method that contains the main while loop that is used
for playing the game. All output was to System.err, except for the computers moves, which were computed using the minimax algorithm and output to System.out. This also enables two AI's to play each other

3. **MinimaxPlayer**: The basic idea behind minimax is to perform a depth first state space search,
and recursively back up utility values for players. Given a game tree of all possible moves on a Tic
Tac Toe board, minimax computes the optimal action to play assuming worst case scenario (a perfect
opponent). Since Tic Tac Toe is a zero sum game, we can visualize the game tree as each level of this
tree altering between a player trying to maximize utility and a player trying to minimize utility. For
example, if X winning provides 10 utility and 'O' winning provides -10 utility for X, at each X(max)
stage you will assume a player looking for a branch that provides the most utility while at each O
(min) stage you will assume a player looking for a branch that provides the lowest utility. Note that
utility values are only defined for terminal states and thus the recursive structure of minimax enables
these values to be backed up, to higher nodes on the game tree. The methods that we used to implement this minimax
algorithm are detailed below:
    * **getStateUtility**: This method simply returned the utility of a terminal state, +10 or -10 for a 
    win or loss respectively and 0 for a draw.
    * **minimaxDecision**: This method considers the applicability function and uses legal moves as 
    a starting point to first generate a particular branch of the game tree and then search it for optimal moves recursively.
    * **maxValue** and **minValue**: These two methods form the interleaving min and max recursive structure of the algorithm. 
    As a result of the recursive call structure, the utility values of the terminal states are backed up the game tree by these two methods.
    * **Alpha-Beta Pruning**: This functionality was implemented within the existing structure itself, by adding a few lines of code to prune the 
    search tree by finding min and max bounds for sub-trees generated from the min and max value functions.

4. **UltimateTTTBoard** and **UltimateTTTGame**: The base classes for the regular Tic Tac Toe implementation were successfully extended for the advanced and ultimate versions of the game. Below is a screenshot of the game implementation with the UI for the Advanced Tic Tac Toe game

![AdvancedTTTgame](/images/AdvancedTTTXwin.PNG)

#### Here is a brief overview of the logic used in implementing the heuristic AI Player:
A depth-limited search that uses a heuristic evaluation of state utility when unable to find a terminal
state was implemented in the class **AdvancedMinimaxPlayer**. The heuristic that we used was based on
a combination of the number of pieces that a player had on each small board in a given state and the
total number of adjacent pairs (two-in-a-rows) on each board. We found that this heuristic consistently
outperformed an AI algorithm that used a constant heuristic value as well as a player that moved randomly.
One component of the heuristic we used was a property of the local boards we called \advantage". Unlike
the plain version of Tic Tac Toe, each board will not necessarily have even numbers of X's and O's. The
advantage of a board for player X is the difference in the number of X's and O's on that board and vice
versa.



##### CSC 442, Artificial Intelligence Course Project, Spring 2018 (University of Rochester)
###### Contributors: Rebecca Ho Van Dyke, Karan Vombatkere, Avram Webberman

