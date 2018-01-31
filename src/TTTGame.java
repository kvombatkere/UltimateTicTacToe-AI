//Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe
//Project Partners: Rebecca Ho Van Dyke + Avram Webberman

//Imports
import java.io.*;
import java.util.*;
import java.awt.*;

//Main Class that runs the game with IO
public class TTTGame {
	//Define class variables
	
	//Variable to keep track if X or O has won
	static boolean Xwins = false;
	static boolean Owins = false;
	
	
	
	//Main method to run the game
	public static void main(String[] args) {
		//Set up input stream
		Scanner playerInput = new Scanner(System.in);
		
		//Print Basic Introduction messages
		System.out.println("===============================================================================================================");
		System.out.println("Initialized a new game of Tic Tac Toe! (Note that X always plays first)");
		System.out.println("Please choose if you want to play \"X\" or \"O\" by typing the desired character:");

		
		//Instantiate the board
		TTTBoard Game = new TTTBoard();
		
		//Define main game playing loop
		while(Xwins == false || Owins == false) {
			
		}

	}	
}
