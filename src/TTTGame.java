//Karan Vombatkere, Rebecca Ho Van Dyke + Avram Webberman
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe
//Basic TTT Game

//Imports
import java.io.*;
import java.util.*;
import java.awt.*;

//Main Class that runs the game with IO loop
@SuppressWarnings("unused")
public class TTTGame {
	
	//Main method to run the game
	@SuppressWarnings("resource")
	public static void main(String[] args) throws CloneNotSupportedException {
		
		//Instantiate the board
		TTTBoard Game = new TTTBoard();
		
		//Main outer loop always runs
		while(true) {
			
			//boolean to keep track of who plays first
			boolean computerFirst = false;
			//Integer to record the board position specified by the opponent and computer
			int compMovePos = 0;
			int oppMovePos = 0; 
			
			//Boolean to check if a move is legal before playing it
			boolean isMoveLegal;
			
			//Set up input stream using System in
			Scanner playerInput = new Scanner(System.in);
			
			//Print Basic Introduction messages
			System.err.println("===============================================================================================================");
			System.err.println("Initialized a new game of Tic Tac Toe! (Note that X always plays first)");
			System.err.println("Please choose if you want to play \"X\" or \"O\" by typing the desired character:");
	
			char inputChar = playerInput.next().charAt(0); //Read the first character of any input string
			boolean legalInput = false; //Boolean to keep track of input legality
			
			if((inputChar == 'o') || (inputChar == 'O') || (inputChar == 'x') || (inputChar == 'X')) {
				legalInput = true;
			}
			
			//Handle illegal inputs
			while(!legalInput) {
				System.err.println("Illegal Input. Please try again.");
				inputChar = playerInput.next().charAt(0); //Read the first character of any input string
				if((inputChar == 'o') || (inputChar == 'O') || (inputChar == 'x') || (inputChar == 'X')) {
					legalInput = true;
				}
			}
			
			inputChar = Character.toUpperCase(inputChar); //Make input upper case in case input was lower case
			
			//Set up a New Board
			Game.newBoard(inputChar);

			//Input is an O -> Computer plays first with X
			if(inputChar == 'O') {
				Game.compChar = 'X';
				computerFirst = true;
			}
			// Input is a X -> Computer plays second with O
			else {
				Game.compChar = 'O';
				computerFirst = false;
				}
			
			//Instantiate Minimax computer player
			MinimaxPlayer compPlayer = new MinimaxPlayer(Game);
					
			
			//Check if computer plays first -> preprogrammed first move
			if(computerFirst) {
				System.err.println("\nNow the computer will play. Using minimax to Search for position (1-9) to place a " + Game.compChar);
				compMovePos = 9;
				Game.moveResult(Game.compChar, compMovePos);
			}
			
			Game.displayBoard(); //Display the board

			//Define main game playing loop
			while(Game.gameOver == false) {
				
				//Opponent plays
				System.err.println("It is your turn to play, please select a position (1-9) to place a " + inputChar);
				oppMovePos = playerInput.nextInt();
				
				//Check if move is legal
				isMoveLegal = Game.ismoveAllowed(oppMovePos);
				while(!isMoveLegal) {
					System.err.println("That is an illegal move, please choose another board position (1-9)");
					oppMovePos = playerInput.nextInt();
					isMoveLegal = Game.ismoveAllowed(oppMovePos);
				}
				
				if(isMoveLegal) { //If the move is legal execute it on the board
					Game.moveResult(inputChar, oppMovePos);
					System.err.println(Game.nextPlayer + " to move next..");  //Display who's move it is next
				}
				
				Game.displayBoard(); //Display the board
				
				//check if game is over to break out of inner while loop
				if(Game.gameOver) {break;}
				
				System.err.println("\nNow the computer will play. Using minimax to Search for position (1-9) to place a " + Game.compChar);
				// Call minimax function to find best move
				compMovePos = compPlayer.minimaxDecision();
				
				//Output move
				System.err.println("Computer Move: " + compMovePos);
				//System.out.println(compMovePos);

				Game.moveResult(Game.compChar, compMovePos);	
				
				//Display who's move it is
				if(!Game.gameOver) {System.err.println(Game.nextPlayer + " to move next..");}
				
				Game.displayBoard(); //Display the board				
			}
			
			//Print result if game is over
			if(Game.gameOver) {
				Game.printGameresult();
			}
		}
	}	
}
