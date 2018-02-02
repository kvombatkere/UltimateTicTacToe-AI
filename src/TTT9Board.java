import java.util.Arrays;

//Rebecca Van Dyke, Avi Webberman, Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe

public class TTT9Board {
	
	//class variables
	public TTTBoard [][] boardArray;
	public char [][] gameStatus; //use this array to track win/lose/draw statuses of each board
	//X=x won, O=o won, d=draw, n=not terminated
	
	public char nextPlayer;
	
	//constructor
	public TTT9Board() {
		this.boardArray = new TTTBoard [3][3];
		this.clearBoard();
		
		this.gameStatus = new char [3][3];
		Arrays.fill(gameStatus, 'n');
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
	
	//print status
	
	//check win
	
	//check super TTT win
	
	
} //end class TTT9Board
