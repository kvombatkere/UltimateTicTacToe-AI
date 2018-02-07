
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
	
	//Method to return set of applicable actions in a given state
	//If sent to board that isn't full, applicable actions are moves to any empty space on that board
	//If sent to board that is full, applicable actions are moves to any empty space on any board
//	@Override
//	public int[][] applicableActions() {
//		int boardSentTo = this.nextBoardIndex;
//		int[][] possibleMoves = new int [10][10];
//		
//		//
//		if((gameStatus[(this.nextBoardIndex-1)/3][(this.nextBoardIndex-1)%3] == 'n')) {
//			for(int i=0; i<3; i++) {
//				for(int j=0; j<3; j++) {
//					if(this.boardArray[(this.nextBoardIndex-1)/3][(this.nextBoardIndex-1)%3].mainBoard[i][j] == ' ') {
//						possibleMoves[this.nextBoardIndex][TTTBoard.getboardPosition(i, j)] = 1;
//					}
//				}
//			}
//		}
//		
//		else {
//			//loop through all boards in 3x3 array of boards
//			for(int i=0; i<3; i++) {
//				for(int j=0; j<3; j++) {
//				//loop through all squares small board
//					for(int k=0; k<3; k++) {
//						for(int l=0; l<3; l++) {
//							if(boardArray[i][j].mainBoard[k][l] == ' ') {
//								int[] legalMove = {TTTBoard.getboardPosition(i, j), TTTBoard.getboardPosition(k,  l)};
//								possibleMoves[TTTBoard.getboardPosition(i, j)][TTTBoard.getboardPosition(j, k)] = 1;
//								
//							}
//						}
//					}
//					
//				}
//			}
//			
//		}
//
//		return possibleMoves;	
//	}
	
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
