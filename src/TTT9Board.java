
//Rebecca Van Dyke, Avi Webberman, Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe

public abstract class TTT9Board {
	
	//class variables
	public TTTBoard [][] boardArray;
	public char [][] gameStatus; //use this array to track win/lose/draw statuses of each board
	//X=x won, O=o won, d=draw, n=not terminated
	public boolean firstMove = true;
	public char nextPlayer;
	public int nextBoardIndex;
	public char overallGameStatus;
	public int moveCounter;
	
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
		
		this.overallGameStatus = 'n';
		this.nextBoardIndex = 1;
	}
	
	//clear board
	public void clearBoard() {
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++) {
				this.boardArray[i][j].clearBoard();
			}
		}
		this.moveCounter = 0;
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
		
		System.err.println("\n");
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
	
	
	
	//check if move is allowed in game class
	//TODO make it return the board not a boolean
	public boolean makeMove(char player, int boardIndex, int boardPos) {
		boolean isValid = false;

		//TODO change terminated to full (use applicable actions)
		///if this board is not terminated and it's the correct board or if the correct board is already terminated
		if((gameStatus[(boardIndex-1)/3][(boardIndex-1)%3] == 'n') && ((boardIndex == this.nextBoardIndex) || (gameStatus[(this.nextBoardIndex-1)/3][(this.nextBoardIndex-1)%3] != 'n') || this.firstMove)) {
			if(this.firstMove) {
				this.firstMove = false;
			}
//		if((gameStatus[(boardIndex-1)/3][(boardIndex-1)%3] == 'n') && (boardIndex == this.nextBoardIndex)){ //|| (gameStatus[(nextBoardIndex-1)/3][(nextBoardIndex-1)%3] != 'n'))) {			
			//check if move is available using TTTBoard check
			if(boardArray[(boardIndex-1)/3][(boardIndex-1)%3].ismoveAllowed(boardPos)) {
				boardArray[(boardIndex-1)/3][(boardIndex-1)%3].moveResult(player, boardPos);
				this.nextBoardIndex=boardPos;
				isValid = true;
			}
			
			else {
				isValid = false;
				System.err.println("Move is invalid...please select a valid move...");
			}
		}
		
		if(isValid) {
			//check if you won
			System.err.println(player + " just moved");
			System.err.println(boardIndex);
			System.err.println(boardPos);
			this.checkWin(this.nextPlayer, boardIndex);
			
			//if not, toggle nextPlayer and wait for another move
			if(this.nextPlayer=='X') {
				this.nextPlayer = 'O';
			}
			else {
				this.nextPlayer = 'X';
			}
		}
		
		else if(!isValid) {
			System.err.println("Please enter a valid move");
			
		}
		
		return isValid;

	} //end makeMove()
	
	//check win
	public abstract char checkWin(char player, int boardIndex);
	
	public void printGameResult() {
		switch(this.overallGameStatus) {
		case 'X':
			System.err.println("Game Over! X wins in "+ moveCounter +" moves!");
			break;
		case 'O':
			System.err.println("Game Over! O wins in "+ moveCounter +" moves!");
			break;
		case 'd':
			System.err.println("Game Ended in a Draw!");
			break;
		default:
			System.err.println("Error, invalid game status");
		}
	}
	
	public int [][] applicableActions(){
		//each row has vector of applicable actions for corresponding board index following the convention set in TTTBoard
		int [][] possibleMoves = new int [10][10];
		
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++) {
				possibleMoves[i=j+1] = this.boardArray[i][j].applicableActions();
			}
		}
		
		return possibleMoves;
	}
	
	public boolean isMoveAllowed(int boardIndex, int pos) {
		if((gameStatus[(boardIndex-1)/3][(boardIndex-1)%3] == 'n') && ((boardIndex == this.nextBoardIndex) || (gameStatus[(this.nextBoardIndex-1)/3][(this.nextBoardIndex-1)%3] != 'n') || this.firstMove)) {
			if(this.firstMove) {
				this.firstMove = false;
			
				if(this.boardArray[(boardIndex-1)/3][(boardIndex-1)%3].ismoveAllowed(pos)) {
					return true;
				}
			}
		}
		return false;
	}
	
	//main method for testing
	public static void main(String [] args) {
		/*TTT9Board testBoard = new TTT9Board();
		testBoard.displayBoard();
		testBoard.printWinStatus();
		testBoard.makeMove('O', 4, 8);
		testBoard.displayBoard();
		testBoard.makeMove('X', 8, 2);
		testBoard.displayBoard(); */
	}
	
} //end class TTT9Board
