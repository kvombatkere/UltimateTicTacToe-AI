
public class AdvancedMinimaxPlayer {
	
	TTT9Board currentGame;
	TTT9Board searchStateBoard;
	final int depthCutoff = 10;
	AdvancedTTTGame game;
	
	
	public AdvancedMinimaxPlayer(AdvancedTTTGame game) {
		this.currentGame = game.board;
		this.game = game;
	}
	
	public int getStateUtility(TTT9Board stateBoard) {
		
		//Check if game is drawn
		if(stateBoard.overallGameStatus == 'd') {
			return 0;
		}
		//Check if computer wins
		else if(stateBoard.overallGameStatus == 'X' && this.game.compChar == 'X') {
			return 10;
		}
		
		else if(stateBoard.overallGameStatus == 'O' && this.game.compChar == 'O') {
			return 10;
		}
		
		//Check if human wins
		else if(stateBoard.overallGameStatus == 'X' && this.game.compChar == 'O') {
			return -10;
		}
		
		else if(stateBoard.overallGameStatus == 'O' && this.game.compChar == 'X') {
			return -10;
		}

		else if (stateBoard.overallGameStatus == 'n') { 
			int utility = heuristic_evaluation(stateBoard);
			return utility;
		}
		
		else {
			return 0;
		}
	}

	public int heuristic_evaluation(TTT9Board stateBoard) {
		return 5;
	}
	
	
	public int[] h_minimaxDecision() {
		
		int[] decision = {1, 1};
		return decision;
	}
	
	public int maxValue() {
		return 0;
	}
	
	public int minValue(){
		return 0;
	}
	
	
}

