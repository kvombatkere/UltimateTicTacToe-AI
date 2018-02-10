//Karan Vombatkere, Rebecca Ho Van Dyke, Avram Webberman
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe
//Basic Tic Tac Toe Board implementation class

import java.awt.*;
import java.io.*;

//Class to specify the physical tic-tac-toe board and display
@SuppressWarnings("serial")
public class TTTBoard implements Serializable{
	//Specify class variables
	
	//Use 2D char array for board
	public char [][] mainBoard; 
	//variable to keep track of next player to move (X or O)
	public char nextPlayer;
	//boolean to keep track of game and whether its a draw
	public boolean gameOver;
	public boolean gameDrawn;
	
	//Counter to keep track of moves starting from 1 through 9
	public int moveCounter;
	//character to keep track of what the computer is playing
	public char compChar;
	
	//keep running stats on moves played for 9-board versions
	private int numX;
	private int numO;
	
	
	//Constructor to instantiate 3x3 board (always fixed size)
	//Instantiates to initial state with all squares blank
	public TTTBoard(){
		this.mainBoard = new char [3][3];
	}
	
	//clear board (fill with spaces)
	public void clearBoard() {
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++) {
				this.mainBoard[i][j] = ' ';
			}
		}
		
		//Reset required variables
		this.gameOver = false;
		this.gameDrawn = false;
		this.moveCounter = 0;
		this.numX = 0;
		this.numO = 0;
		
	} //end clearBoard()
	
	//Function to Reset the board and start a new game
	//Takes the opponents character as the input
	public void newBoard(char oppChar) {
		System.err.println("\n----------------------------------------------");
		System.err.println("Setting up new Tic Tac Toe Board!");
		this.clearBoard();		
		
		//Character the computer plays with
		if(oppChar == 'O') {
			this.compChar = 'X';
			System.err.println("Computer to move first and play with " + this.compChar);
		}
		else {
			this.compChar = 'O';
			System.err.println("You are to move first and play with " + oppChar);
		}
		
		//Set up the new board and assign X to play first
		this.nextPlayer = 'X';
		
	}//end newBoard()
	
	
	//Method to Display the board with some basic board formatting
	//Prints to Std.err
	public void displayBoard() {
		System.err.println("----------------------------------------------");
		System.err.println("Displaying Tic Tac Toe Board Current State:\n");
		
		for(int i=0; i<3; i++) {
			printRow(i);
			if(i != 2) {
				System.err.println("\n------------");
			}
		}
		
		System.err.println();
	}//end displayBoard()
	
	public void printRow(int n) {
		for(int j=0; j<3; j++) {
			System.err.print(" " + (mainBoard[n][j]) + " ");
			if(j != 2) {
				System.err.print("|");
			}
		}
	} ///end printRow()
	
	
	//Method to add an X or an O to position specified by digits 1-9
	//Method that computes the RESULT(s,a) function and executes the action physically
	public TTTBoard moveResult(char moveChar, int pos) {		
		Point boardPos = new Point();
		
		//Get the coordinate values from the corresponding function
		boardPos = getCoordinates(pos);
		
		//Execute the move on the TTT Board
		//Note that only valid moves in valid positions are executed
		if(boardPos != null) {
			this.mainBoard[boardPos.x][boardPos.y] = moveChar;
			this.moveCounter++; //Increment move counter
			//update X and O counts
			if(moveChar == 'X') {
				this.numX++;}
			else{
				this.numO++;}
			
			//Check if the last move won the game -> must be done after every move
			//Check all possible terminal states and switch the next player + print I/O to System.err
			this.gameOver = this.terminalState();	
			
			//Switch the nextplayer variable if game is still valid
			if(!this.gameOver) {
				if(this.nextPlayer == 'X') {nextPlayer = 'O';}
				else{this.nextPlayer = 'X';}
			}
		
		}
		return this; //Return the resulting game state		
	} //end moveResult()
	
	//Method to return the coordinates of the board position from a pos value of 1-9
	//Mostly to detect errors and get easy calls from 
	public static Point getCoordinates(int boardPos) {
		Point coord = new Point(); //Initialize the coordinates
		
		//Assign the correct x and y values as per the position
		if(boardPos == 1) {coord.x = 0; coord.y = 0;}
		else if(boardPos == 2) {coord.x = 0; coord.y = 1;}
		else if(boardPos == 3) {coord.x = 0; coord.y = 2;}

		else if(boardPos == 4) {coord.x = 1; coord.y = 0;}
		else if(boardPos == 5) {coord.x = 1; coord.y = 1;}
		else if(boardPos == 6) {coord.x = 1; coord.y = 2;}
		
		else if(boardPos == 7) {coord.x = 2; coord.y = 0;}
		else if(boardPos == 8) {coord.x = 2; coord.y = 1;}
		else if(boardPos == 9) {coord.x = 2; coord.y = 2;}
		
		//Print error statement and return null if position is not 1-9
		else { 
			System.err.println("Error! Position "+ boardPos + " does not Exist!");
			return null;
		}
		
		//Return the coordinate of the board position
		return coord;		
	}
	
	//Method to return the board position (1-9) given the XY coordinates of the grid
	public static int getboardPosition(int xval, int yval) {
		int bPos = 0; //Initialize the int value (1-9) to return
		
		if(xval == 0) {
			if(yval == 0) bPos = 1;
			if(yval == 1) bPos = 2;
			if(yval == 2) bPos = 3;
		}
		if(xval == 1) {
			if(yval == 0) bPos = 4;
			if(yval == 1) bPos = 5;
			if(yval == 2) bPos = 6;
		}
		if(xval == 2) {
			if(yval == 0) bPos = 7;
			if(yval == 1) bPos = 8;
			if(yval == 2) bPos = 9;
		}
		return bPos;
	}
	
	public void printGameresult() {
		if(this.gameDrawn) {
			System.err.println("Game Ended in a Draw!");
		}
		else {
			System.err.println("Game Over! " + nextPlayer + " wins in "+ moveCounter +" moves!");
		}			
	} //end printGameResult()
	
	//Method to check terminal states separately
	//Returns true if the current board state is terminal and false otherwise
	public boolean terminalState() {
		boolean gameTerminated = false;
		char checkChar = this.nextPlayer; //The character to check for co-linearity
		
		//pulled check 3 in row to separate
		if(check3InRow(checkChar, this.mainBoard)) {
			gameTerminated = true;
		}
		
		//Check if the game ended in a draw, i.e. 9 moves have been played ONLY if gameTerminated is still false
		else if(this.moveCounter == 9) {
			this.gameDrawn = true;
			gameTerminated = true;
		}
		
		return gameTerminated;	
	} //end terminalState()
	
	public static boolean check3InRow(char checkChar, char [][] board) {
		//Define and check each of 8 terminal states that bring a win for nextPlayer
		
		//Horizontal line check
		for(int i=0; i<3; i++) {
			if(board[i][0] == checkChar && board[i][1] == checkChar && board[i][2] == checkChar) {
				return true;
			}
		}
				
		//Vertical line check
		for(int i=0; i<3; i++) {
			if(board[0][i] == checkChar && board[1][i] == checkChar && board[2][i] == checkChar) {
				return true;
			}
		}
				
		//Diagonal check
		if(board[0][0] == checkChar && board[1][1] == checkChar && board[2][2] == checkChar) {
			return true;
			}
		if(board[0][2] == checkChar && board[1][1] == checkChar && board[2][0] == checkChar) {
			return true;
			}
		
		return false;
	} //end check3InRow()
	
	//checks if a win is still possible for the next player
	public static boolean winPossible(char checkChar, char blankChar, char [][] board) {
		//must have 3 in a row of checkChar or blanks
		//Horizontal line check
		for(int i=0; i<3; i++) {
			if((board[i][0] == checkChar)||(board[i][0] == blankChar) && ((board[i][1] == checkChar)||(board[i][1] == blankChar)) && ((board[i][2] == checkChar)||(board[i][2] == blankChar))) {
				return true;
			}
		}
		
		//Vertical line check
		for(int i=0; i<3; i++) {
			if(((board[0][i] == checkChar)||(board[0][i] == blankChar)) && ((board[1][i] == checkChar)||(board[1][i] == blankChar)) && ((board[2][i] == checkChar)||(board[2][i] == blankChar))) {
				return true;
			}
		}
		
		//Diagonal check
		if(((board[0][0] == checkChar)||(board[0][0] == blankChar)) && ((board[1][1] == checkChar)||(board[1][1] == blankChar)) && ((board[2][2] == checkChar)||(board[2][2] == blankChar))) {
			return true;
			}
		if(((board[0][2] == checkChar)||(board[0][2] == blankChar)) && ((board[1][1] == checkChar)||(board[1][1] == blankChar)) && ((board[2][0] == checkChar)||(board[2][0] == blankChar))) {
			return true;
			}
			
		return false;
	} //end winPossible()
	
	//Method to return set of applicable actions in a given state
	//This basically returns all the empty board positions, since that is what the player must choose from
	public int[] applicableActions() {
		//Use an array of length 10 with all values initialized to 0
		int[] possibleMoves = new int [10];
		
		//Fill array with the possible move if the square is empty
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++) {
				//Check if square is blank
				if(this.mainBoard[i][j] == ' ') {
					int legalMove = getboardPosition(i,j);
					possibleMoves[legalMove] = legalMove; //Update corresponding array value with non-zero position
				}
			}
		}
		
		//Print out array to see non-zero values - comment out later
		//System.err.println("Available Moves: " + Arrays.toString(possibleMoves));
		return possibleMoves;	
	} //end applicableActions()
	
	//Method to check if a board position (1-9) is legal/allowed
	//Returns true or false
	public boolean isMoveAllowed(int pos) {
		int [] possibilities = this.applicableActions();	
		//Deal with illegal moves outside of 1-9 range first
		if(pos < 1 || pos > 9) {
			return false;
		}
		
		//possibilities[pos] = 0 for illegal moves
		if(possibilities[pos] == pos) {
			return true;
		}
		
		return false;	
	} //end isMoveAllowed()
	
	public boolean isBoardFull() {
		if(this.moveCounter > 8) {
			return true;
		}
		return false;
	} ///end isBoardFull()
	
	//Method to enable cloning of the object
	//Code Source: https://alvinalexander.com/java/java-deep-clone-example-source-code
	/**
	 * This method makes a "deep clone" of any Java object it is given.
	 */
	 public static Object deepClone(Object object) {
	   try {
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     ObjectOutputStream oos = new ObjectOutputStream(baos);
	     oos.writeObject(object);
	     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	     ObjectInputStream ois = new ObjectInputStream(bais);
	     return ois.readObject();
	   }
	   catch (Exception e) {
	     e.printStackTrace();
	     return null;
	   }
	 } //end deepClone()
	 
	 //Section: Helper methods for calculating heuristic in 9-board versions 
	 public double getBoardFullness() {
		 //returns a value between 0 and 1
		 return (double)this.moveCounter/9;
	 }
	 
	 
	 public int getAdvantage(char player) {		 
		 //if neither player can win in one move, estimate advantage based on # of pieces on the board
		 if(player == 'X') {
			 return this.numX - this.numO;
		 }
		 else {
			 return this.numO - this.numX;
		 }
		 
	 }
	 
	 public int getAdjacentPairs(char player) {
		int numPairs = 0;
		
		return numPairs;
		 
		 
	 }
	
}