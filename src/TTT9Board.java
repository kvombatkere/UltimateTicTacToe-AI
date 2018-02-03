import java.awt.Point;

//Rebecca Van Dyke, Avi Webberman, Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe

public class TTT9Board {
	
	//class variables
	public TTTBoard [][] boardArray;
	public char [][] gameStatus; //use this array to track win/lose/draw statuses of each board
	//X=x won, O=o won, d=draw, n=not terminated
	
	public char nextPlayer;
	public int nextBoardIndex;
	
	//constructor
	public TTT9Board() {
		this.boardArray = new TTTBoard [3][3];
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				boardArray[i][j] = new TTTBoard();
			}
		}
		this.clearBoard();
		
		this.gameStatus = new char [3][3];
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				gameStatus[i][j] = 'n';
			}
		}
		this.nextPlayer = 'X';
	}
	
	//clear board
	public void clearBoard() {
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++) {
				this.boardArray[i][j].clearBoard();
			}
		}
	}
	
	//print board
	public void displayBoard() {
		System.err.println("----------------------------------------------");
		System.err.println("Displaying 9-Board Tic Tac Toe Current State:\n");
		
		for(int i=0; i<3; i++) { //3 rows of board array
			for(int j=0; j<3; j++) { //3 rows in each board
				for(int k=0; k<3; k++) { //3 boards in each row
					this.boardArray[i][k].printRow(j);
					if(k != 2) {
						System.err.print("  | ");
					}
				}
				if(j != 2) {
					System.err.println("\n------------ | ------------ | ------------");
				}
			}
			if(i != 2) {
				System.err.println("\n             |              |             ");
				System.err.println("------------------------------------------");
				System.err.println("             |              |             ");

			}
		}
		
		System.err.println();
	}
	
	//print status
	public void printWinStatus() {
		System.err.println("----------------------------------------------");
		System.err.println("Displaying 9-Board Tic Tac Toe Win/Lose/Draw State:\n");
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.err.print(" " + (gameStatus[i][j]) + " ");
				if(j != 2) {
					System.err.print("|");
				}
			}
			if(i != 2) {
				System.err.println("\n------------");
			}
		}
	}
	
	//make move
	public void makeMove(char player, int boardIndex, int boardPos) {
		//check if move is available
		Point board = TTTBoard.getCoordinates(boardIndex);
		Point intendedNextBoard = TTTBoard.getCoordinates(nextBoardIndex);
		///if this board is not terminated and it's the correct board or if the correct board is already terminated
		if((gameStatus[board.x][board.y] == 'n') && ((boardIndex == this.nextBoardIndex) || (gameStatus[intendedNextBoard.x][intendedNextBoard.y] != 'n'))) {
			if(boardArray[board.x][board.y].ismoveAllowed(boardPos)) {
				boardArray[board.x][board.y].moveResult(player, boardPos);
				this.nextBoardIndex=boardPos;
			}
		}
		
		//check if you won
		checkWin(this.nextPlayer, boardIndex);
		
		//if not, toggle nextPlayer and wait for another move
		if(this.nextPlayer=='X') {
			this.nextPlayer = 'O';
		}
		else {
			this.nextPlayer = 'X';
		}
	}
	
	//check win
	public char checkWin(char player, int boardIndex) {
		//if you win one board, you win the game
		
		//game will only terminate on the last board you played on
		return 'n';
	}
	
	//check super TTT win
	public char checkSuperTTTWin() {
		//must win TTT on results board
		return 'n';
	}
	
	
	//main method for testing
	public static void main(String [] args) {
		TTT9Board testBoard = new TTT9Board();
		testBoard.displayBoard();
		testBoard.printWinStatus();
	}
} //end class TTT9Board
