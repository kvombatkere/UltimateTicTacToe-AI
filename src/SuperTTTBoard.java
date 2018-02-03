
//Rebecca Van Dyke, Avi Webberman, Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe

public class SuperTTTBoard extends TTT9Board{

	@Override
	public char checkWin(char player, int boardIndex) {
		//check if you won one board
		//game will only terminate on the last board you played on
		TTTBoard lastBoard = this.boardArray[(boardIndex-1)/3][(boardIndex-1)%3];
		
		//if you win one board, update gameStatus and check if you won the whole game
		if(lastBoard.terminalState()) {
			//if it terminated and it's not a draw, the player who just played must have won
			if(!lastBoard.gameDrawn) {
				this.gameStatus[(boardIndex-1)/3][(boardIndex-1)%3] = player;
				
				//check if you have an overall win
				if(TTTBoard.check3InRow(player, this.gameStatus)) {
					this.overallGameStatus = player;
					this.printGameResult();
				}
			}
			
			//if that board is a draw, check if a win is still possible
			else {
				this.overallGameStatus = 'd';
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						if(this.gameStatus[i][j] == 'n') {
							this.overallGameStatus = 'n';
						}
					}
				}
				if(this.overallGameStatus != 'n') {
					this.printGameResult();
				}
			}
		}
		
		//if all boards are full, game is a draw
		return this.overallGameStatus;
	}

	
	
} //end class SuperTTTBoard
