//Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe
//Project Partners: Rebecca Ho Van Dyke + Avram Webberman

//Imports
import java.io.*;
import java.util.*;
import java.awt.*;

//Main class to implement state space search using regular minimax
@SuppressWarnings("unused")
public class MinimaxPlayer{

	//Define the various class variables
	//Note the minimax player must take a TTTBoard as an instance variable object
	TTTBoard currentGame;
	
	//Keep a copy of the TTTBoard object to be used to for state space search without modifying original
	//This one is overwritten in every search 
	TTTBoard searchStateBoard;
	
	//Keep track of recursive calls
	int recursionNum;
	
	//Constructor
	public MinimaxPlayer(TTTBoard currGame) throws CloneNotSupportedException {
		this.currentGame = currGame;
//		searchStateBoard = (TTTBoard) currentGame.clone();
		System.err.println("Computer Player Instantiated!");
	}
	
	
	//Specify the framework for minimax in terms of min, max and utility of states
	public int getStateUtility(TTTBoard stateBoard) {
		int stateUtility = 0;
		
		System.err.println("Terminal State Utility check");

		//Check if game is drawn
		if(stateBoard.gameDrawn) {
			return stateUtility = 1;
		}
		//Check if computer wins
		if(stateBoard.nextPlayer == stateBoard.compChar) {
			return stateUtility = 2;
		}
		
		//If opponent (human) wins
		else {
			return stateUtility = 0;
		}
	}
	
	
	//Method to find the optimal move using state-space search and return position
	public int minimaxDecision() throws CloneNotSupportedException {
		int bestMove = 0;
		int bestmoveUtility = -10;
		int moveUtility = -10;
		
		//searchStateBoard = TTTBoard.copyGame(currentGame);
		searchStateBoard = (TTTBoard) currentGame.clone();
		
		//First get the list of possible actions
		int [] possibleMoves = searchStateBoard.applicableActions();

		//iterate through all the applicable actions to create game tree
		for(int i=1; i < possibleMoves.length; i++) {
			//Make a copy of the current game state to perform State Space Search on
			//Note that this needs to be done for each action considered
			if(possibleMoves[i] != 0) { //We consider only legal moves
				recursionNum = 0;
				//searchStateBoard = TTTBoard.copyGame(currentGame);
				searchStateBoard = (TTTBoard) currentGame.clone();

				//Call the recursive state space process
				moveUtility = this.minValue(searchStateBoard);
				System.err.println("minimaxDecision Utility = " + moveUtility);
				
				//Choose the best utility action
				if(moveUtility > bestmoveUtility) {
					bestMove = possibleMoves[i];
				}
				
				System.err.println("Current Best Move = " + bestMove + ". Recursive Call Count = " + recursionNum);
				
			}
			
		}	
		return bestMove;
	}
	
	
	//Max value function for minimax -> returns a utility value
	public int maxValue(TTTBoard stateBoard) {
		//Check if it is in a terminal state and return the utility value
		recursionNum++;

		if(stateBoard.terminalState()) {
			return this.getStateUtility(stateBoard);
		}
			
		int v = -100; //Assign v -inf value
		
		//First get the list of possible actions
		int [] possibleMoves = stateBoard.applicableActions();
		System.err.println("List of Actions Remaining (max)= " + Arrays.toString(possibleMoves));

		//iterate through all the applicable actions to create game tree
		for(int i=1; i < possibleMoves.length; i++) {
			
			if(possibleMoves[i] != 0 && stateBoard.moveCounter < 9) { //We consider only legal moves
				int a = possibleMoves[i]; //Candidate action
				System.err.println("maxValue function, evaluating move: " + a + ". Current utility value = " + v);

				//Note that moveResult automatically toggles the nextplayer state
				v = Math.max(v, this.minValue(stateBoard.moveResult(stateBoard.nextPlayer, a)));
			}
		}
		

		return v;	
	}
	
	//Max value function for minimax -> returns a utility value
	public int minValue(TTTBoard stateBoard) {
		//Check if it is in a terminal state and return the utility value
		recursionNum++;

		if(stateBoard.terminalState()) {
			return this.getStateUtility(stateBoard);
		}
			
		int v = 100; //Assign v +inf value
		
		//First get the list of possible actions
		int [] possibleMoves = stateBoard.applicableActions();
		System.err.println("List of Actions Remaining (min)= " + Arrays.toString(possibleMoves));

		//iterate through all the applicable actions to create game tree
		for(int i=1; i < possibleMoves.length; i++) {
			
			if(possibleMoves[i] != 0 && stateBoard.moveCounter < 9) { //We consider only legal moves
				int a = possibleMoves[i]; //Candidate action
				System.err.println("minValue function, evaluating move: " + a + ". Current utility value = " + v);

				//Note that moveResult automatically toggles the nextplayer state
				v = Math.min(v, this.maxValue(stateBoard.moveResult(stateBoard.nextPlayer, a)));
			}
		}
		
		//System.err.println("Min value Utility = " + v);
		return v;	
	}
	
	
}
