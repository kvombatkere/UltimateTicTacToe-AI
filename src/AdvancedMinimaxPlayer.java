
public class AdvancedMinimaxPlayer {
	
	AdvancedTTTBoard currentGame;
	AdvancedTTTBoard searchStateBoard;
	final int depthCutoff = 5;
	
	AdvancedTTTGame game;
	
	//Keep track of recursive calls and total states examined
	int recursionNum;
	int totalStates;
	
	boolean reachedTerminalState;
	
	public AdvancedMinimaxPlayer(AdvancedTTTGame game) {
		this.currentGame = game.board;
		this.game = game;
	}
	
	
	//Returns terminal state utility -> method must only be called if board is in terminal state
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
		
		return 0;	
	}

	
	//Heuristic Evaluation Function
	public int heuristicEvaluation(AdvancedTTTBoard stateBoard) {	
		
		//
		return 0;
	}
	
	
	//Cutoff Test Function
	public boolean cutoffTest(AdvancedTTTBoard stateBoard) {
		//Check if a terminal state has been reached
		if(stateBoard.advancedTerminalState()) {
			reachedTerminalState = true;
			return true;
		}
		
		//Return false if the recursion count < cutoff depth
		if(this.recursionNum < depthCutoff) {
			return false;
		}
		
		else {
			return true;
		}
		
	}
	
	
	public int[] hMinimaxDecision() {
		int[] bestMove = new int[2];
		int moveUtility = -100;
		totalStates = 0;
		
		searchStateBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(currentGame);
		
		//First get the list of possible actions
		int [][] possibleMoves = searchStateBoard.applicableActions();
		
		int v = this.maxValue(searchStateBoard, -100, 100);
		//System.err.println("Total States examined to find initial max v: " + totalStates);

		//iterate through all the applicable actions to create game tree
		for(int i=1; i < possibleMoves.length; i++) {
			for(int j=1; j < possibleMoves.length; j++) {
					
				if(possibleMoves[i][j] != 0) { //We consider only legal moves
					//i is the candidate boardIndex and j is the candidate boardPos
					
					//Make a copy of the current game state to perform State Space Search on - this needs to be done for each action considered
					searchStateBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(currentGame);
					
					//Initialize the bookkeeping for recursion
					recursionNum = 0;
					reachedTerminalState = false;
	
					//Call the recursive state space process
					moveUtility = this.minValue((AdvancedTTTBoard) searchStateBoard.moveResult(searchStateBoard.nextPlayer, i, j),-100,100);
					
					//System.err.println("minimaxDecision Utility for move: " + possibleMoves[i]+ " is = " + moveUtility);
					
					//Choose the best utility action and return immediately
					if(moveUtility == v) {
						
						bestMove[0] = i;
						bestMove[1] = j;
						
						System.err.println("Total Number of Terminal States Searched by Minimax (with Alpha Beta Pruning) = " + totalStates);
						System.err.println("Total Number of Recursive Calls by Minimax (with Alpha Beta Pruning) = " + recursionNum);
						return bestMove;
					}				
				}
			}	
		}
		
		return bestMove;
	}
	
	
	//Max value function for h_minimax
	public int maxValue(AdvancedTTTBoard stateBoard, int alpha, int beta) {
		recursionNum++;

		//Check the cut off test and return the utility value or heuristic evaluation
		if(this.cutoffTest(stateBoard)) {
			//If cut-off is reached due to terminal state, return utility value
			if(reachedTerminalState) {
				totalStates++;
				return this.getStateUtility(stateBoard);
			}
			
			//If cutoff is reached due to depth-limit, call heuristic evaluation function
			else {
				return this.heuristicEvaluation(stateBoard);
			}
		}
		
		int v = -100; //Assign v -inf value

		//iterate through all the applicable actions to create game tree
		for(int i=1; i <10; i++) {
			for(int j=1; j<10; j++) {
				AdvancedTTTBoard tempBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(stateBoard);
				//First get the list of possible actions
				int [][] possibleMoves = tempBoard.applicableActions();
				
				if(possibleMoves[i][j] != 0) { //We consider only legal moves/states	
					
					//i is the candidate boardIndex and j is the candidate boardPos
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
	
	
	//Min value function for h_minimax
	public int minValue(AdvancedTTTBoard stateBoard, int alpha, int beta){
		recursionNum++;

		//Check the cut off test and return the utility value or heuristic evaluation
		if(this.cutoffTest(stateBoard)) {
			//If cut-off is reached due to terminal state, return utility value
			if(reachedTerminalState) {
				totalStates++;
				return this.getStateUtility(stateBoard);
			}
			
			//If cutoff is reached due to depth-limit, call heuristic evaluation function
			else {
				return this.heuristicEvaluation(stateBoard);
			}
		}
		
		
		int v = 100; //Assign v -inf value

		//iterate through all the applicable actions to create game tree
		for(int i=1; i <10; i++) {
			for(int j=1; j<10; j++) {
				AdvancedTTTBoard tempBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(stateBoard);
				//First get the list of possible actions
				int [][] possibleMoves = tempBoard.applicableActions();
				
				if(possibleMoves[i][j] != 0) { //We consider only legal moves/states	
					
					//i is the candidate boardIndex and j is the candidate boardPos
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

