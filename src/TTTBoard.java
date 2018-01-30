//Karan Vombatkere
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe
//Project Partners: Rebecca Ho Van Dyke + Avram Webberman

//Imports
import java.io.*;
import java.util.*;
import java.awt.*;

//Class to specify the physical tic-tac-toe board and display
public class TTTBoard {
	
	//Specify class variables
	private String [][] mainBoard; //Use 2D String array for board
	
	
	//Constructor to instantiate 3x3 board (always fixed size)
	//Instantiates to initial state with all squares blank
	public TTTBoard(){
		mainBoard = new String [3][3];
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++) {
				mainBoard[i][j] = " ";
			}
		}
	}
	
	//Method to Display the board with some basic board formatting
	//Prints to Std.err
	public void displayBoard() {
		System.err.println("Displaying Tic Tac Toe Board Current State\n");
		
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++) {
				System.err.print(" " + (mainBoard[i][j]) + " ");
				if(j != 2) {
					System.err.print("||");
				}
			}
			if(i != 2) {
				System.err.println("\n==============");
			}
		}	
	}
	
	
	//Method to add an X or an O to position specified by digits 1-9
	public void move(String moveChar, int pos) {
		Point boardPos = new Point();
		
		//Get the coordinate values from the corresponding function
		boardPos = getCoordinates(pos);
		
		//Execute the move on the TTT Board
		//Note that only valid moves in valid positions are executed
		if(boardPos != null) {
			mainBoard[boardPos.x][boardPos.y] = moveChar;
		}
	}
	
	
	//Method to return the coordinates of the board position from a pos value of 1-9
	//Mostly to detect errors and get easy calls from 
	public Point getCoordinates(int boardPos) {
		Point coord = new Point(); //Initialize the coordinates
		
		//Assign the correct x and y values as per the position
		if(boardPos == 1) {coord.x = 0; coord.y = 0;}
		else if(boardPos == 2) {coord.x = 0; coord.y = 1;}
		else if(boardPos == 3) {coord.x = 0; coord.y = 2;}

		else if(boardPos == 4) {coord.x = 1; coord.y = 0;}
		else if(boardPos == 5) {coord.x = 1; coord.y = 1;}
		else if(boardPos == 6) {coord.x = 1; coord.y = 2;}
		
		else if(boardPos == 7) {coord.x = 2; coord.y = 0;}
		else if(boardPos == 8) {coord.x = 2; coord.y = 1;}
		else if(boardPos == 9) {coord.x = 2; coord.y = 2;}
		
		//Print error statement and return null if position is not 1-9
		else { 
			System.err.println("Error! Position "+ boardPos + " does not Exist!");
			return null;
		}
		
		//Return the coordinate of the board position
		return coord;		
	}
	
	
	//Main method to run some tests - comment out later
	public static void main(String[] args) throws IOException{
		TTTBoard B1 = new TTTBoard();
		
		B1.move("X",1);
		B1.move("O",3);
		B1.move("X",5);
		B1.move("O",6);
		B1.move("X",7);

		//B1.move("O",17);

		B1.displayBoard();	
	}
}
