
public class AdvancedTTTBoard extends TTT9Board{

	public char checkWin(char player, int boardIndex) {
		//game will only terminate on the last board you played on
		TTTBoard lastBoard = this.boardArray[(boardIndex-1)/3][(boardIndex-1)%3];
		
		//if you win one board, you win the game
		if(lastBoard.terminalState()) {
			//if it terminated and it's not a draw, the player who just played must have won
			if(!lastBoard.gameDrawn) {
				this.overallGameStatus = player;
				this.printGameResult();
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
					this.printGameResult();
				}
			}
		}
		
		return this.overallGameStatus;
		
	} //end method checkWin()

}
