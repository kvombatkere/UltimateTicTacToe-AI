//Karan Vombatkere, Rebecca Ho Van Dyke + Avram Webberman
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe
//Computer Player that generates random moves for AdvancedTTT

//Imports
import java.io.*;
import java.util.*;
import java.awt.*;

public class AdvancedRandomPlayer {
	
	public AdvancedTTTBoard gameBoard;
	
	//Constructor
	public AdvancedRandomPlayer(AdvancedTTTBoard currentGame) {
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
