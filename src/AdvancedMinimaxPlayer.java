//Karan Vombatkere, Rebecca Ho Van Dyke + Avram Webberman
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe
//Basic TTT Game

public class AdvancedMinimaxPlayer {
	
	AdvancedTTTBoard currentGame;
	AdvancedTTTBoard searchStateBoard;
	final int depthCutoff = 10;
	
	AdvancedTTTGame game;
	
	//Keep track of recursive calls and total states examined
	public int recursionNum;
	public int totalStates;
	
	public boolean reachedTerminalState = false;
	
	public AdvancedMinimaxPlayer(AdvancedTTTGame game) {
		this.currentGame = game.board;
		this.game = game;
	}
	
	
	//Returns terminal state utility -> method must only be called if board is in terminal state
	public int getStateUtility(AdvancedTTTBoard stateBoardGSU) {
		
		//Check if game is drawn
		if(stateBoardGSU.overallGameStatus == 'd') {
			return 0;
		}
		
		//Check if computer wins
		else if((stateBoardGSU.overallGameStatus == 'X' && this.game.p2Char == 'X') || (stateBoardGSU.overallGameStatus == 'O' && this.game.p2Char == 'O')){
			return 50;
		}

		//Check if human wins
		else if((stateBoardGSU.overallGameStatus == 'X' && this.game.p2Char == 'O') || (stateBoardGSU.overallGameStatus == 'O' && this.game.p2Char == 'X')) {
			return -50;
		}
		
		System.err.println("Overall Game Status: " + stateBoardGSU.overallGameStatus); //This should never print and never return 2
		return 2;	
	} 

	
	//Heuristic Evaluation Function
	public int heuristicEvaluation(AdvancedTTTBoard stateBoardHF) {	
		int hVal = 0;
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				//Increment heuristic for every board where a player has an advantage
				int boardAdvantage = stateBoardHF.boardArray[i][j].getAdvantage(stateBoardHF.nextPlayer);
				if(boardAdvantage > 0) {
					hVal += boardAdvantage;
				}
			}
		}
		//System.err.println("Computed Heuristic Value = " + hVal);
		return hVal;
	}
	
	
	//Cutoff Test Function
	public boolean cutoffTest(AdvancedTTTBoard stateBoardCF) {
		//Check if a terminal state has been reached
		if(stateBoardCF.overallGameStatus != 'n') {
			this.reachedTerminalState = true; //set the tracker to true
			//stateBoardCF.displayBoard();
			//System.err.println("reachedTerminalState from cutoff test: " + this.reachedTerminalState);
			return this.reachedTerminalState;
		}
		
		//Return false if the recursion count < cutoff depth
		if(this.recursionNum < depthCutoff) {
			return false;
		}
		
		else {
			return true;
		}
		
	}
	
	//function to compute the h-minimax decision
	public int[] hMinimaxDecision() {
		int[] bestMove = this.randomMove();
		
		boolean updatedUtility = false;
		
		int moveUtility = -100;
		int bestMoveUtility = -100;
		
		totalStates = 0;
		
		searchStateBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(currentGame);
		
		//First get the list of possible actions
		int [][] possibleMoves = searchStateBoard.applicableActions();
		
		int v = this.maxValue(searchStateBoard, -100, 100);
		System.err.println("Initial max v: " + v);
		bestMoveUtility = v;

		//iterate through all the applicable actions to create game tree
		for(int i=1; i < possibleMoves.length; i++) {
			for(int j=1; j < possibleMoves.length; j++) {
					
				if(possibleMoves[i][j] != 0) { //We consider only legal moves
					//i is the candidate boardIndex and j is the candidate boardPos
					
					//Make a copy of the current game state to perform State Space Search on - this needs to be done for each action considered
					searchStateBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(currentGame);
					
					//Initialize the bookkeeping for recursion
					this.recursionNum = 0;
					this.reachedTerminalState = false;
	
					//Call the recursive state space process
					moveUtility = this.minValue((AdvancedTTTBoard) searchStateBoard.moveResult(searchStateBoard.nextPlayer, i, j),-100,100);
					System.err.println("Computed Move Utility Value = " + moveUtility);
			
					//Choose the best utility action and return immediately - terminal states are favored
					if(moveUtility >= bestMoveUtility) {
						bestMoveUtility = moveUtility;
						updatedUtility = true;
						bestMove[0] = i;
						bestMove[1] = j;
					}				
				}
			}	
		}
		if(!updatedUtility) {
			System.err.println("Did not update utility - playing Random Move");
		}
		
		System.err.println("Total Number of Terminal States Searched by Minimax (with Alpha Beta Pruning) = " + totalStates);
		System.err.println("Total Number of Recursive Calls by Minimax (with Alpha Beta Pruning) = " + recursionNum);
		System.err.println("minimaxDecision Utility for move: " + bestMove[0] + " " + bestMove[1] + " is = " + bestMoveUtility);

		return bestMove;
	}
	
	
	//Max value function for h_minimax
	public int maxValue(AdvancedTTTBoard stateBoardMax, int alpha, int beta) {
		recursionNum++;

		//Check the cut off test and return the utility value or heuristic evaluation
		if(this.cutoffTest(stateBoardMax)) {
			//If cut-off is reached due to terminal state, return utility value
			if(this.reachedTerminalState) {
				totalStates++;
				this.reachedTerminalState = false; //reset immediately to avoid future erroneous detection
				return this.getStateUtility(stateBoardMax);
			}
			
			//If cutoff is reached due to depth-limit, call heuristic evaluation function
			else {
				return this.heuristicEvaluation(stateBoardMax);
			}
		}
		
		int v = -100; //Assign v -inf value

		//iterate through all the applicable actions to create game tree
		for(int i=1; i <10; i++) {
			for(int j=1; j<10; j++) {
				AdvancedTTTBoard tempBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(stateBoardMax);
				//First get the list of possible actions
				int [][] possibleMoves = tempBoard.applicableActions();
				
				if(possibleMoves[i][j] != 0) { //We consider only legal moves/states	
					
					//i is the candidate boardIndex and j is the candidate boardPos
					//System.err.println("maxValue function, evaluating move: " + a + ". Current utility value = " + v);
	
					//Note that moveResult automatically toggles the nextplayer state
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
	public int minValue(AdvancedTTTBoard stateBoardMin, int alpha, int beta){
		recursionNum++;

		//Check the cut off test and return the utility value or heuristic evaluation
		if(this.cutoffTest(stateBoardMin)) {
			//If cut-off is reached due to terminal state, return utility value
			if(this.reachedTerminalState) {
				totalStates++;
				this.reachedTerminalState = false; //reset immediately to avoid future erroneous detection
				return this.getStateUtility(stateBoardMin);
			}
			
			//If cutoff is reached due to depth-limit, call heuristic evaluation function
			else {
				return this.heuristicEvaluation(stateBoardMin);
			}
		}
		
		
		int v = 100; //Assign v -inf value

		//iterate through all the applicable actions to create game tree
		for(int i=1; i <10; i++) {
			for(int j=1; j<10; j++) {
				AdvancedTTTBoard tempBoard = (AdvancedTTTBoard) AdvancedTTTBoard.deepClone(stateBoardMin);
				//First get the list of possible actions
				int [][] possibleMoves = tempBoard.applicableActions();
				
				if(possibleMoves[i][j] != 0) { //We consider only legal moves/states	
					
					//i is the candidate boardIndex and j is the candidate boardPos
					//System.err.println("maxValue function, evaluating move: " + a + ". Current utility value = " + v);
	
					//Note that moveResult automatically toggles the nextplayer state
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
	
	
	//Method to generate random move
		public int[] randomMove() {
			int boardNum = currentGame.nextBoardIndex;
			int movePos=0;
			
			TTTBoard localBoard = currentGame.getNextBoard();
			
			//identify board to play on
			if(localBoard.isBoardFull()) {
				for(int i=1; i<10; i++) {
					localBoard = currentGame.getBoard(i);
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
				
			} while(!currentGame.isMoveAllowed(boardNum, movePos));
			
			int[] randomMove = {boardNum, movePos};
			
			return randomMove;
		}
}

