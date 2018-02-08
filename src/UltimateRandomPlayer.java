
public class UltimateRandomPlayer {
	public UltimateTTTBoard gameBoard;
	
	//Constructor
	public UltimateRandomPlayer(UltimateTTTBoard currentGame) {
		this.gameBoard = currentGame;
	}
	
	//Method to generate random move
	public int[] randomMove() {
		int boardNum = gameBoard.nextBoardIndex;
		int movePos=0;
		
		TTTBoard localBoard = gameBoard.getNextBoard();
		
		//identify board to play on
		if(localBoard.isBoardFull()) {
			for(int i=1; i<10; i++) {
				localBoard = gameBoard.getBoard(i);
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
			
		} while(!gameBoard.isMoveAllowed(boardNum, movePos));
		
		
		int[] randomMove = {boardNum, movePos};
		
		return randomMove;
	}
}
