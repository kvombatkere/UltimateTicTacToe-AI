
public class AdvancedTTTBoard extends TTT9Board{

	public char checkWin(char player, int boardIndex) {
		//game will only terminate on the last board you played on
		TTTBoard lastBoard = this.getBoard(boardIndex);
		
		//if you win one board, you win the game
		if(lastBoard.terminalState()) {
			//if it terminated and it's not a draw, the player who just played must have won
			if(!lastBoard.gameDrawn) {
				this.overallGameStatus = player;
			}
			
			//if that board is a draw, check if there are still boards to be played on
			else {
				this.overallGameStatus = 'd';
				//if all boards are full, game is a draw
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						if(this.gameStatus[i][j] == 'n') {
							this.overallGameStatus = 'n';
						}
					}
				}
				if(this.overallGameStatus != 'n') {
				}
			}
		}
		
		return this.overallGameStatus;
		
	} //end method checkWin()
	
	
	public boolean advancedTerminalState() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				//if find terminal state return true
				if(this.boardArray[i][j].terminalState()) {
					return true;
				}
			}		
		}
		
		//if find no terminal states return false
		return false;
	}

}
