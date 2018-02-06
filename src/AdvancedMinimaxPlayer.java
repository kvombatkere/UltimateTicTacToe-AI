
public class AdvancedMinimaxPlayer {
	
	TTT9Board currentGame;
	TTT9Board searchStateBoard;
	
	public AdvancedMinimaxPlayer(TTT9Board currBoard) {
		this.currentGame = currBoard;
	}
	
	public int getStateUtility(TTT9Board stateBoard) {
		//Check if game is drawn
		if(stateBoard.overallGameStatus == 'n') {
			return 0;
		}
		//Check if computer wins
		if(stateBoard.overallGameStatus == ' ') {
			return 10;
		}
		
		//Check if human wins
		else if(stateBoard.overallGameStatus == '_') {
			return -10;
		}
		
		//If opponent (human) wins
		else { 
			int utility = heuristic_evaluation(stateBoard);
			return utility;
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

