//Karan Vombatkere, Rebecca Ho Van Dyke, Avram Webberman
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe
//Minimax without Alpha Beta Pruning

public class BasicMinimaxPlayer {

	//Define the various class variables
	//Note the minimax player must take a TTTBoard as an instance variable object
	TTTBoard currentGame;
	//Keep a copy of the TTTBoard object to be used to for state space search without modifying original
	//This one is overwritten in every search 
	TTTBoard searchStateBoard;
	
	//Keep track of recursive calls and total states examined
	int recursionNum;
	int totalStates;
		
	//Constructor
	public BasicMinimaxPlayer(TTTBoard currGame) {
		this.currentGame = currGame;
		System.err.println("Computer Player Instantiated!");
	}
	
	
	//Specify the framework for minimax in terms of min, max and utility of states
	public int getStateUtility(TTTBoard stateBoard) {
		//System.err.println("Terminal State Utility check");

		//Check if game is drawn
		if(stateBoard.gameDrawn) {
			return 0;
		}
		//Check if computer wins
		if(stateBoard.nextPlayer == stateBoard.compChar) {
			return 10;
		}
		
		//If opponent (human) wins
		else {
			return -10;
		}
	}
	
	
	//Method to find the optimal move using state-space search and return position
	public int minimaxDecision(){
		//Measure start time
		long startTime = System.nanoTime();
		
		int bestMove = 0;
		int bestmoveUtility = -100;
		int moveUtility = -100;
		
		recursionNum = 0;
		totalStates = 0;
		
		searchStateBoard = (TTTBoard) TTTBoard.deepClone(currentGame);
		
		//First get the list of possible actions
		int [] possibleMoves = searchStateBoard.applicableActions();

		//iterate through all the applicable actions to create game tree
		for(int i=1; i < possibleMoves.length; i++) {
			//Make a copy of the current game state to perform State Space Search on - this needs to be done for each action considered
			if(possibleMoves[i] != 0) { //We consider only legal moves

				searchStateBoard = (TTTBoard) TTTBoard.deepClone(currentGame);
				
				int a = possibleMoves[i]; //Candidate action
				
				//Call the recursive state space process
				moveUtility = this.minValue(searchStateBoard.moveResult(searchStateBoard.nextPlayer, a));
				
				//System.err.println("minimaxDecision Utility for move: " + possibleMoves[i]+ " is = " + moveUtility);
				
				//Choose the best utility action
				if(moveUtility > bestmoveUtility) {
					bestMove = possibleMoves[i];
					bestmoveUtility = moveUtility;
				}				
			}
			
		}
		
		long elapsedTime = System.nanoTime() - startTime;
		
		System.err.println("Total execution time for Minimax to find move:" + elapsedTime/1000000 + " ms");
		System.err.println("Total Number of States Searched by Minimax = " + totalStates);
		System.err.println("Total Number of Recursive Calls by Minimax = " + recursionNum);
		
		return bestMove;
	}
	
	
	//Max value function for minimax -> returns a utility value
	public int maxValue(TTTBoard stateBoard) {
		//Check if it is in a terminal state and return the utility value
		recursionNum++;

		if(stateBoard.terminalState()) {
			totalStates++;
			return this.getStateUtility(stateBoard);
		}
			
		int v = -100; //Assign v -inf value

		//iterate through all the applicable actions to create game tree
		for(int i=1; i <10; i++) {
			
			TTTBoard tempBoard = (TTTBoard) TTTBoard.deepClone(stateBoard);
			//First get the list of possible actions
			int [] possibleMoves = tempBoard.applicableActions();
			
			if(possibleMoves[i] != 0) { //We consider only legal moves/states	
				
				int a = possibleMoves[i]; //Candidate action
				//System.err.println("maxValue function, evaluating move: " + a + ". Current utility value = " + v);

				//Note that moveResult automatically toggles the nextplayer state
				v = Math.max(v, this.minValue(tempBoard.moveResult(tempBoard.nextPlayer, a)));

			}
		}
		return v;	
	}
	
	
	//Min value function for minimax -> returns a utility value
	public int minValue(TTTBoard stateBoard) {
		//Check if it is in a terminal state and return the utility value
		recursionNum++;
		
		if(stateBoard.terminalState()) {
			totalStates++;
			return this.getStateUtility(stateBoard);
		}
			
		int v = 100; //Assign v +inf value

		//iterate through all the applicable actions to create game tree
		for(int i=1; i < 10; i++) {
			
			TTTBoard tempBoard = (TTTBoard) TTTBoard.deepClone(stateBoard);
			//First get the list of possible actions
			int [] possibleMoves = tempBoard.applicableActions();
			
			if(possibleMoves[i] != 0) { //We consider only legal moves
				
				int a = possibleMoves[i]; //Candidate action
				//System.err.println("minValue function, evaluating move: " + a + ". Current utility value = " + v);

				//Note that moveResult automatically toggles the nextplayer state
				v = Math.min(v, this.maxValue(tempBoard.moveResult(tempBoard.nextPlayer, a)));
			}
		}
		
		return v;	
	}
}
