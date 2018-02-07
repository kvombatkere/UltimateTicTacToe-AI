
public class AdvancedMinimaxPlayer {
	
	AdvancedTTTBoard currentGame;
	AdvancedTTTBoard searchStateBoard;
	final int depthCutoff = 10;
	AdvancedTTTGame game;
	//Keep track of recursive calls and total states examined
	int recursionNum;
	int totalStates;
	
	public AdvancedMinimaxPlayer(AdvancedTTTGame game) {
		this.currentGame = game.board;
		this.game = game;
	}
	
	public int getStateUtility(AdvancedTTTBoard stateBoard) {
		
		//Check if game is drawn
		if(stateBoard.overallGameStatus == 'd') {
			return 0;
		}
		//Check if computer wins
		else if(stateBoard.overallGameStatus == 'X' && this.game.p2Char == 'X') {
			return 10;
		}
		
		else if(stateBoard.overallGameStatus == 'O' && this.game.p2Char == 'O') {
			return 10;
		}
		
		//Check if human wins
		else if(stateBoard.overallGameStatus == 'X' && this.game.p2Char == 'O') {
			return -10;
		}
		
		else if(stateBoard.overallGameStatus == 'O' && this.game.p2Char == 'X') {
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

	public int heuristic_evaluation(AdvancedTTTBoard stateBoard) {
		
		return 5;
	}
	
	
	public int[] h_minimaxDecision() {
		

		int[] bestMove = new int[2];
		int moveUtility = -100;
		totalStates = 0;
		recursionNum = 0;
		
		searchStateBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(currentGame);
		
		//First get the list of possible actions
		int [][] possibleMoves = searchStateBoard.applicableActions();
		
		int v = this.maxValue(searchStateBoard, -100, 100);
		//System.err.println("Total States examined to find initial max v: " + totalStates);

		//iterate through all the applicable actions to create game tree
		for(int i=1; i < possibleMoves.length; i++) {
			for(int j=1; j < possibleMoves.length; j++) {
			//Make a copy of the current game state to perform State Space Search on - this needs to be done for each action considered
			if(possibleMoves[i][j] != 0) { //We consider only legal moves

				searchStateBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(currentGame);
				
		//		int a = possibleMoves[i]; //Candidate action
				
				//Call the recursive state space process
				moveUtility = this.minValue((AdvancedTTTBoard) searchStateBoard.moveResult(searchStateBoard.nextPlayer, i, j),-100,100);
				
				//System.err.println("minimaxDecision Utility for move: " + possibleMoves[i]+ " is = " + moveUtility);
				
				//Choose the best utility action and return immediately
				if(moveUtility == v) {
					
					bestMove[0] = i;
					bestMove[1] = j;
					
					System.err.println("Total Number of States Searched by Minimax (with Alpha Beta Pruning) = " + totalStates);
					System.err.println("Total Number of Recursive Calls by Minimax (with Alpha Beta Pruning) = " + recursionNum);
					return bestMove;
				}				
			}
			}	
		}
		return bestMove;
	}
	
	public int maxValue(AdvancedTTTBoard stateBoard, int alpha, int beta) {
		//Check if it is in a terminal state and return the utility value
		recursionNum++;

		if(stateBoard.advancedTerminalState()) {
			totalStates++;
			return this.getStateUtility(stateBoard);
		}
			
		int v = -100; //Assign v -inf value

		//iterate through all the applicable actions to create game tree
		for(int i=1; i <10; i++) {
			for(int j=1; j<10; j++) {
			AdvancedTTTBoard tempBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(stateBoard);
			//First get the list of possible actions
			int [][] possibleMoves = tempBoard.applicableActions();
			
			if(possibleMoves[i][j] != 0) { //We consider only legal moves/states	
				
				int a = possibleMoves[i][j]; //Candidate action
				//System.err.println("maxValue function, evaluating move: " + a + ". Current utility value = " + v);

				//Note that moveResult automatically toggles the nextplayer state
				//if problem with type look at casting
				v = Math.max(v, this.minValue((AdvancedTTTBoard) tempBoard.moveResult(tempBoard.nextPlayer, i, j), alpha, beta));
				
				if(v >= beta) {
					return v;
				}
				
				alpha = Math.max(alpha, v);
			}
		}
	}
		return v;
	}
	
	public int minValue(AdvancedTTTBoard stateBoard, int alpha, int beta){
		//Check if it is in a terminal state and return the utility value
				recursionNum++;

				if(stateBoard.advancedTerminalState()) {
					totalStates++;
					return this.getStateUtility(stateBoard);
				}
					
				int v = 100; //Assign v -inf value

				//iterate through all the applicable actions to create game tree
				for(int i=1; i <10; i++) {
					for(int j=1; j<10; j++) {
					AdvancedTTTBoard tempBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(stateBoard);
					//First get the list of possible actions
					int [][] possibleMoves = tempBoard.applicableActions();
					
					if(possibleMoves[i][j] != 0) { //We consider only legal moves/states	
						
						int a = possibleMoves[i][j]; //Candidate action
						//System.err.println("maxValue function, evaluating move: " + a + ". Current utility value = " + v);

						//Note that moveResult automatically toggles the nextplayer state
						//if problem with type look at casting
						v = Math.min(v, this.maxValue((AdvancedTTTBoard) tempBoard.moveResult(tempBoard.nextPlayer, i, j), alpha, beta));
						
						if(v >= beta) {
							return v;
						}
						
						beta = Math.max(beta, v);
					}
				}
			}
				return v;
	}
	
	
}

