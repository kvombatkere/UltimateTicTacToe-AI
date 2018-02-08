//Rebecca Van Dyke, Avi Webberman, Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe

public class UltimateTTTBoard extends TTT9Board{
	

	public char checkWin(char player, int boardIndex) {

		//check if you won one board
		//game will only terminate on the last board you played on
		TTTBoard lastBoard = this.boardArray[(boardIndex-1)/3][(boardIndex-1)%3];
		
		//is the last board played on in a terminal state now?
		if(lastBoard.terminalState() && this.gameStatus[(boardIndex-1)/3][(boardIndex-1)%3] != 'X' && this.gameStatus[(boardIndex-1)/3][(boardIndex-1)%3] != 'O') {
			//if it terminated and it's not a draw, the player who just played must have won
			if(!lastBoard.gameDrawn) {
				this.gameStatus[(boardIndex-1)/3][(boardIndex-1)%3] = player;
				
				//check if you have an overall win
				if(TTTBoard.check3InRow(player, this.gameStatus)) {
					this.overallGameStatus = player;
					this.printGameResult();
				}
				
				
				
			}
			
			//else if that board is a draw
			else {
				//update game status of board just played on
				this.gameStatus[(boardIndex-1)/3][(boardIndex-1)%3] = 'd';
				//check if a win is impossible
				if(!(TTTBoard.winPossible('X', 'n', this.gameStatus) || TTTBoard.winPossible('O', 'n', this.gameStatus))) {
					//if no one can win, entire game is a draw
					this.overallGameStatus = 'd';
					this.printGameResult();
				}
			}
		}
		
		//if all boards are full, game is a draw
		return this.overallGameStatus;
	}
	

	
	public static void main(String[] args) {
		
		UltimateTTTBoard board = new UltimateTTTBoard();
		board.moveResult('X', 1, 1);
		board.moveResult('X', 1, 2);
		board.moveResult('X', 1, 3);
		System.out.println(board.gameStatus[0][0]);
		board.moveResult('O', 1, 4);
		board.moveResult('O', 1, 5);
		board.moveResult('O', 1, 6);
		System.out.println(board.gameStatus[0][0]);
		board.moveResult('O', 2, 4);
		board.moveResult('O', 2, 5);
		board.moveResult('O', 2, 6);
		board.moveResult('O', 3, 4);
		board.moveResult('O', 3, 5);
		board.moveResult('O', 3, 6);
		System.out.println(board.gameStatus[0][1]);
		System.out.println(board.gameStatus[0][2]);
		System.out.println(board.gameStatus[0][0]);
		board.moveResult('O', 5, 4);
		board.moveResult('O', 5, 5);
		board.moveResult('O', 5, 6);
		board.moveResult('O', 8, 4);
		board.moveResult('O', 8, 5);
		board.moveResult('O', 8, 6);
		board.displayBoard();
		
		
		
		
	}

	
	
} //end class SuperTTTBoard
