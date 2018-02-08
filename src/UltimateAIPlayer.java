
public class UltimateAIPlayer {

	public UltimateTTTGame game;
	public UltimateTTTBoard searchStateBoard;
	public boolean goFirst; //boolean to indicate if computer goes first
	public boolean firstMove = true;
	public int stage1counter = 0;
	
	
	public UltimateAIPlayer(UltimateTTTGame game) {
		this.game = game;
	}
	
	public int[] decision() {
		searchStateBoard = (UltimateTTTBoard) UltimateTTTBoard.deepClone(game.board);
		int[][] possibleMoves = searchStateBoard.applicableActions();
//		int[] bestMove = new int[2];
		
		//computer goes first
		if(goFirst) {
			//hard coded amount or times that computer will repeat "send to middle" strategy
			if(stage1counter < 8){
				stage1counter++;
				int[] move = {game.board.nextBoardIndex, 5};
				return move;
			}
			
			//once stage 1 is complete, move on to new strategy. currently: try to send opponent to full board, otherwise move randomly
			else {
				
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						//try to send opponent to games that are already finished	
						if((game.board.gameStatus[i][j] == 'X' || game.board.gameStatus[i][j] == 'O') && game.board.isMoveAllowed(game.board.nextBoardIndex, TTTBoard.getboardPosition(i, j)) && !game.board.boardArray[i][j].isBoardFull()) {
							System.out.println("LOOKING...");
							int[] move = {game.board.nextBoardIndex, TTTBoard.getboardPosition(i, j)};
							return move;
						}
								
							
					}		
				}
				return this.randomMove();		
			}	
			
		}		
			
	
		//computer goes second.  currently: try to send opponent to full board, otherwise move randomly
		else {
			
//			for(int i=0; i<3; i++) {
//				for(int j=0; j<3; j++) {
//					//try to send opponent to games that are already finished	
//					if((game.board.gameStatus[i][j] == 'X' ||game.board.gameStatus[i][j] == 'O')) {
//						for(int k=0; k<3;k++) {
//							for(int l=0; l<3; l++) {
//								if(game.board.boardArray[i][j].mainBoard[k][l] == ' ') {
//									System.out.println("NOT RANDOM");
//									System.out.println(k + " " + l);
//									int[] move = {TTTBoard.getboardPosition(xval, yval), TTTBoard.getboardPosition(k, l)};
//									return move;
//								}
//							}
//						}
//					}
//				}
//			}
			
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					//try to send opponent to games that are already finished	
					if((game.board.gameStatus[i][j] == 'X' || game.board.gameStatus[i][j] == 'O') && game.board.isMoveAllowed(game.board.nextBoardIndex, TTTBoard.getboardPosition(i, j))) {
						System.out.println("LOOKING...");
						int[] move = {game.board.nextBoardIndex, TTTBoard.getboardPosition(i, j)};
						return move;
					}
							
						
				}		
			}
			
			return this.randomMove();
		}
	}
	
	//Method to generate random move
	public int[] randomMove() {
		int boardNum = game.board.nextBoardIndex;
		int movePos=0;
		
		TTTBoard localBoard = game.board.getNextBoard();
		
		//identify board to play on
		if(localBoard.isBoardFull()) {
			for(int i=1; i<10; i++) {
				localBoard = game.board.getBoard(i);
				if(!localBoard.isBoardFull()) {
					boardNum = i;
					break;
				}
			}
		}
		
		//identify position to play on
		do {
			movePos = (int) (Math.random()*9) + 1; //generate a random number between 1 and 9
			//System.err.println(movePos);
			
		} while(!game.board.isMoveAllowed(boardNum, movePos));
		
		
		int[] randomMove = {boardNum, movePos};
		
		return randomMove;
	}
	
}
