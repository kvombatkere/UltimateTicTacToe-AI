//Rebecca Van Dyke, Avi Webberman, Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe

public class UltimateTTTBoard extends TTT9Board{
	static int counter = 0;

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
				
				else {
					//if all boards are full, game is a draw
					boolean allFull = true;
					for(int i=0; i<3; i++) {
						for(int j=0; j<3; j++) {
							if(this.gameStatus[i][j] == 'n') {
								allFull = false;
							}
						}
					}
					if(allFull) {
						this.overallGameStatus = 'd';
					}
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
		
		return this.overallGameStatus;
	}
	

	
	public static void main(String[] args) {
		
		UltimateTTTBoard board = new UltimateTTTBoard();
		board.moveResult('X', 1, 1);
		board.moveResult('X', 1, 2);
		board.moveResult('X', 1, 3);
		board.moveResult('X', 1, 4);
		board.moveResult('X', 1, 5);
		board.moveResult('X', 1, 6);
		board.moveResult('X', 1, 7);
		board.moveResult('X', 1, 8);
		board.moveResult('X', 1, 9);
		board.moveResult('X', 2, 1);
		board.moveResult('X', 2, 2);
		board.moveResult('X', 2, 3);
		board.moveResult('X', 2, 4);
		board.moveResult('X', 2, 5);
		board.moveResult('X', 2, 6);
		board.moveResult('X', 2, 7);
		board.moveResult('X', 2, 8);
		board.moveResult('X', 2, 9);
		board.moveResult('O', 3, 1);
		board.moveResult('O', 3, 2);
		board.moveResult('O', 3, 3);
		board.moveResult('O', 3, 4);
		board.moveResult('O', 3, 5);
		board.moveResult('O', 3, 6);
		board.moveResult('O', 3, 7);
		board.moveResult('O', 3, 8);
		board.moveResult('O', 3, 9);
		board.moveResult('O', 4, 1);
		board.moveResult('O', 4, 2);
		board.moveResult('O', 4, 3);
		board.moveResult('O', 4, 4);
		board.moveResult('O', 4, 5);
		board.moveResult('O', 4, 6);
		board.moveResult('O', 4, 7);
		board.moveResult('O', 4, 8);
		board.moveResult('O', 4, 9);
		board.moveResult('X', 5, 1);
		board.moveResult('X', 5, 2);
		board.moveResult('X', 5, 3);
		board.moveResult('X', 5, 4);
		board.moveResult('X', 5, 5);
		board.moveResult('X', 5, 6);
		board.moveResult('X', 5, 7);
		board.moveResult('X', 5, 8);
		board.moveResult('X', 5, 9);
		board.moveResult('X', 6, 1);
		board.moveResult('X', 6, 2);
		board.moveResult('X', 6, 3);
		board.moveResult('X', 6, 4);
		board.moveResult('X', 6, 5);
		board.moveResult('X', 6, 6);
		board.moveResult('X', 6, 7);
		board.moveResult('X', 6, 8);
		board.moveResult('X', 6, 9);
		board.moveResult('X', 7, 1);
		board.moveResult('X', 7, 2);
		board.moveResult('X', 7, 3);
		board.moveResult('X', 7, 4);
		board.moveResult('X', 7, 5);
		board.moveResult('X', 7, 6);
		board.moveResult('X', 7, 7);
		board.moveResult('X', 7, 8);
		board.moveResult('X', 7, 9);
		board.moveResult('O', 8, 2);
		board.moveResult('O', 8, 3);
		board.moveResult('O', 8, 4);
		board.moveResult('O', 8, 5);
		board.moveResult('O', 8, 6);
		board.moveResult('O', 8, 7);
		board.moveResult('O', 8, 8);
		board.moveResult('O', 8, 9);


		board.displayBoard();
		board.printWinStatus();
		System.out.println();
		System.out.println(board.overallGameStatus);
		
		
		
		
	}

	
	
} //end class SuperTTTBoard
