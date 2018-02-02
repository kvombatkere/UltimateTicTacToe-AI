//Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe
//Project Partners: Rebecca Ho Van Dyke + Avram Webberman

import java.awt.*;

//Class to specify the physical tic-tac-toe board and display
public class TTTBoard implements Cloneable{
	
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
	}
	
	//Function to Reset the board and start a new game
	//Takes the opponents character as the input
	public void newBoard(char oppChar) {
		System.err.println("\n----------------------------------------------");
		System.err.println("Setting up new Tic Tac Toe Board!");
		this.clearBoard();
		//Reset required variables
		this.gameOver = false;
		this.gameDrawn = false;
		this.moveCounter = 0;
		
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
		
	}
	
	
	//Method to Display the board with some basic board formatting
	//Prints to Std.err
	public void displayBoard() {
		System.err.println("----------------------------------------------");
		System.err.println("Displaying Tic Tac Toe Board Current State:\n");
		
		this.printBoard();
		
		System.err.println();
	}
	
	public void printBoard() {
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++) {
				System.err.print(" " + (mainBoard[i][j]) + " ");
				if(j != 2) {
					System.err.print("|");
				}
			}
			if(i != 2) {
				System.err.println("\n------------");
			}
		}
	}
	
	
	//Method to add an X or an O to position specified by digits 1-9
	//Method that computes the RESULT(s,a) function
	public TTTBoard moveResult(char moveChar, int pos) {
		//View applicable actions, legal moves available
		this.applicableActions();
		
		Point boardPos = new Point();
		
		//Get the coordinate values from the corresponding function
		boardPos = this.getCoordinates(pos);
		
		//Execute the move on the TTT Board
		//Note that only valid moves in valid positions are executed
		if(boardPos != null) {
			this.mainBoard[boardPos.x][boardPos.y] = moveChar;
			this.moveCounter++; //Increment move counter
		}
		
		//Call the method to check for win and toggle the player
		this.togglePlayer();
		
		//Note that toggle player check for terminal state first and also toggles nextPlayer
		return this; //Return the resulting board state
	}
	
	
	//Method to return the coordinates of the board position from a pos value of 1-9
	//Mostly to detect errors and get easy calls from 
	public Point getCoordinates(int boardPos) {
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
	public int getboardPosition(int xval, int yval) {
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
	
	
	//Result method to check if the last move won the game -> must be called after every move
	//Check all possible terminal states and toggle the next player + print I/O to System.err
	public void togglePlayer() {
		//Check if the game is in a terminal state
		this.gameOver = this.terminalState();
		
		//Print output if game is over
		if(this.gameOver) {
			if(this.gameDrawn) {
				System.err.println("Game Ended in a Draw!");
			}
			else {
				System.err.println("Game Over! " + nextPlayer + " wins in "+ moveCounter +" moves!");
			}
		}
		
		
		//Toggle the player if game is still valid
		if(!this.gameOver) {
			if(this.nextPlayer == 'X') {nextPlayer = 'O';}
			else{this.nextPlayer = 'X';}
			//Display who's move it iss
			System.err.println(nextPlayer + " to move next..");
		}
		
	}
	
	//Method to check terminal states separately
	//Returns true if the current board state is terminal and false otherwise
	public boolean terminalState() {
		boolean gameTerminated = false;
		char checkChar = this.nextPlayer; //The character to check for co-linearity
		
		//Define and check each of 8 terminal states that bring a win for nextPlayer
		//Horizontal line check
		if(this.mainBoard[0][0] == checkChar && this.mainBoard[0][1] == checkChar && this.mainBoard[0][2] == checkChar) {
			return gameTerminated = true;
			}
		if(this.mainBoard[1][0] == checkChar && this.mainBoard[1][1] == checkChar && this.mainBoard[1][2] == checkChar) {
			return gameTerminated = true;
			}
		if(this.mainBoard[2][0] == checkChar && this.mainBoard[2][1] == checkChar && this.mainBoard[2][2] == checkChar) {
			return gameTerminated = true;
			}
		
		//Vertical line check
		if(this.mainBoard[0][0] == checkChar && this.mainBoard[1][0] == checkChar && this.mainBoard[2][0] == checkChar) {
			return gameTerminated = true;
			}
		if(this.mainBoard[0][1] == checkChar && this.mainBoard[1][1] == checkChar && this.mainBoard[2][1] == checkChar) {
			return gameTerminated = true;
			}
		if(this.mainBoard[0][2] == checkChar && this.mainBoard[1][2] == checkChar && this.mainBoard[2][2] == checkChar) {
			return gameTerminated = true;
			}
		
		//Diagonal check
		if(this.mainBoard[0][0] == checkChar && this.mainBoard[1][1] == checkChar && this.mainBoard[2][2] == checkChar) {
			return gameTerminated = true;
			}
		if(this.mainBoard[0][2] == checkChar && this.mainBoard[1][1] == checkChar && this.mainBoard[2][0] == checkChar) {
			return gameTerminated = true;
			}
		
		//Check if the game ended in a draw, i.e. 9 moves have been played ONLY if gameTerminated is still false
		if(this.moveCounter == 9) {
			this.gameDrawn = true;
			gameTerminated = true;
		}
		
		return gameTerminated;	
	}
	
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
					int legalMove = this.getboardPosition(i,j);
					possibleMoves[legalMove] = legalMove; //Update corresponding array value with non-zero position
				}
			}
		}
		
		//Print out array to see non-zero values - comment out later
		//System.err.println("Available Moves: " + Arrays.toString(possibleMoves));
		
		return possibleMoves;	
	}
	
	//Method to check if a board position (1-9) is legal/allowed
	//Returns true or false
	public boolean ismoveAllowed(int pos) {
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
	}
	
	//Method to enable cloning of the object
	public Object clone()throws CloneNotSupportedException{
		return super.clone();  
	}
	
//	//Main method to run some tests - comment out later
//	public static void main(String[] args) throws IOException{
//		TTTBoard B1 = new TTTBoard();
//		B1.move('X',1);
//		B1.move('O',3);
//		B1.move('X',5);
//		B1.move('O',6);
//		B1.move('X',7);
//		B1.move('O',9);
//		B1.displayBoard();	
//		
//		B1.newBoard('X');
//		B1.displayBoard();	
//		
//		B1.move('X',5);
//		B1.move('O',6);
//		B1.move('X',2);
//		B1.displayBoard();	
//		System.out.println(B1.ismoveAllowed(5));
//	}

}