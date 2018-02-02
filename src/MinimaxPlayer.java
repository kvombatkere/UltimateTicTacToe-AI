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
	
	//Constructor
	public MinimaxPlayer(TTTBoard currGame) {
		this.currentGame = currGame;
	}
	
	
	//Specify the framework for minimax in terms of min, max and utility of states
	public int getStateUtility(TTTBoard stateBoard) {
		int stateUtility = 0;
			
		//Check if game is drawn
		if(currentGame.gameDrawn) {
			return stateUtility = 1;
		}
		//Check if computer wins
		if(currentGame.nextPlayer == currentGame.compChar) {
			return stateUtility = 2;
		}
		
		//If opponent (human) wins
		else {
			return stateUtility = 0;
		}
	}
	
	
	//Method to find the optimal move using state-space search and return position
	public int minimaxDecision(TTTBoard currGame) throws CloneNotSupportedException {
		int bestMove = 0;
		int moveUtility = -10;
		
		//Make a copy of the current game state to perform State Space Search
		searchStateBoard = (TTTBoard) currGame.clone();
		
		//First get the list of possible actions
		int [] possibleMoves = currGame.applicableActions();
		
		//iterate through all the applicable actions to create game tree
		for(int i=1; i < possibleMoves.length; i++) {
			
		}
			
		return bestMove;
	}
	
	
	//Max value function for minimax -> returns a utility value
	public int maxValue(TTTBoard stateBoard) {
		//Check if it is in a terminal state and return the utility value
		if(stateBoard.terminalState()) {
			return this.getStateUtility(stateBoard);
		}
			
		int v = -100; //Assign v -inf value
		
		//First get the list of possible actions
		int [] possibleMoves = stateBoard.applicableActions();
		
		//iterate through all the applicable actions to create game tree
		for(int i=1; i < possibleMoves.length; i++) {
			int a = possibleMoves[i]; //Candidate action
			
			//Note that moveResult toggles the player state
			v = Math.max(v, minValue(stateBoard.moveResult(stateBoard.nextPlayer, a)));
		}
		
		return v;	
	}
	
	//Max value function for minimax -> returns a utility value
	public int minValue(TTTBoard stateBoard) {
		//Check if it is in a terminal state and return the utility value
		if(stateBoard.terminalState()) {
			return this.getStateUtility(stateBoard);
		}
			
		int v = 100; //Assign v -inf value
		
		//First get the list of possible actions
		int [] possibleMoves = stateBoard.applicableActions();
		
		//iterate through all the applicable actions to create game tree
		for(int i=1; i < possibleMoves.length; i++) {
			int a = possibleMoves[i]; //Candidate action
			
			//Note that moveResult toggles the player state
			v = Math.min(v, maxValue(stateBoard.moveResult(stateBoard.nextPlayer, a)));
		}
		
		return v;	
	}
	
	
}
