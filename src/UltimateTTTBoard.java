//Rebecca Van Dyke, Avi Webberman, Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe
//Ultimate TTT Board

public class UltimateTTTBoard extends TTT9Board{
	static int counter = 0;

	public char checkWin(char player, int boardIndex) {

		//check if you won one board
		//game will only terminate on the last board you played on
		TTTBoard lastBoard = this.boardArray[(boardIndex-1)/3][(boardIndex-1)%3];
		
		//is the last board played on in a terminal state now and not already won?
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
					//if all boards are full and nobody won, game is a draw
					boolean allFull = true;
					for(int i=0; i<3; i++) {
						for(int j=0; j<3; j++) {
							if(this.gameStatus[i][j] == 'n') {
								allFull = false;
							}
						}
					}
					if(allFull) {
						System.out.println("Game is a Draw 1");
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
				
				boolean allFull = true;
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						if(this.gameStatus[i][j] == 'n') {
							allFull = false;
						}
					}
				}
				if(allFull) {
					System.out.println("Game is a Draw 2");
					this.overallGameStatus = 'd';
					System.out.println(this.overallGameStatus);
					return this.overallGameStatus;
				}
			}
			
			

		}			
		
//		boolean allFull = true;
//		for(int i=0; i<3; i++) {
//			for(int j=0; j<3; j++) {
//				if(this.gameStatus[i][j] == 'n') {
//					allFull = false;
//				}
//			}
//		}
//		if(allFull) {
//			System.out.println("Game is a Draw");
//			this.overallGameStatus = 'd';
//		}
		
		return this.overallGameStatus;
	}
	
	public boolean allFull() {
		boolean allFull = true;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(this.gameStatus[i][j] == 'n') {
					allFull = false;
				}
			}
		}
		
		return allFull;
		
	}
	

	
	public static void main(String[] args) {
		UltimateTTTGame game = new UltimateTTTGame();
		UltimateAIPlayer p1 = new UltimateAIPlayer(game);
		UltimateAIPlayer p2 = new UltimateAIPlayer(game);
		int moveCounter = 0;
		
		while(game.board.overallGameStatus == 'n') {
			if(game.board.overallGameStatus == 'd') {
				
				System.out.println("drawdrawdrawdraw");
				break;
			}
			//game.board.printWinStatus();
			
	//		System.out.println(game.board.overallGameStatus);
	//		System.out.println("-------------------------------");
		//	System.out.println("check1");
			int[] p1Move = p1.decision();
			game.board.moveResult('X', p1Move[0], p1Move[1]);
		//	game.board.displayBoard();
			System.out.println(p1Move[0] + " " + p1Move[1]);
			if(game.board.overallGameStatus == 'd') {
				
				System.out.println("drawdrawdrawdraw");
				break;
			}
			
			
	//		System.out.println(game.board.checkWin('O', game.board.nextBoardIndex));
			int[] p2Move = p2.decision();
			game.board.moveResult('O', p2Move[0], p2Move[1]);
		//	System.out.println(p2Move[0] + " " + p2Move[1]);
		//	game.board.displayBoard();
		//	System.out.println("check3");
			//game.board.displayBoard();
	//		System.out.println("check4");
		//	System.out.println(game.board.checkWin('O', game.board.nextBoardIndex));
			if(game.board.overallGameStatus == 'd') {
				
				System.out.println("drawdrawdrawdraw");
				break;
			}
		}
		System.out.println("GAME OVER");
		game.board.displayBoard();
		game.board.printWinStatus();
		System.out.println();
		System.out.println(game.board.overallGameStatus);
		
		
		
		
	}

	
	
} //end class SuperTTTBoard
