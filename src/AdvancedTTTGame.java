import java.util.Scanner;

public class AdvancedTTTGame {
	
	TTT9Board board;
	
	public AdvancedTTTGame() {
		board = new TTT9Board();
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		AdvancedTTTGame game = new AdvancedTTTGame();
		
		while(true) {
			
			//boolean to keep track of who plays first
			boolean computerFirst = false;
			
			//String to record the board (index 0 of string) and position (index 2 of string) specified by the opponent
			String oppMove = "";
			
			//integer array to record board and position of computer's move
			int[] compMove = {2,2};
			
			char compChar = ' ';
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
			
			if(inputChar == 'O') {
				computerFirst = true;
				compChar = 'X';
			}
			
			else if (inputChar == 'X') {
				computerFirst = false;
				compChar = 'O';
			}
			
			else {
				System.err.println("Oh god this should never happen please help");
			}
			
			
			//Instantiate Minimax computer player
			AdvancedMinimaxPlayer compPlayer = new AdvancedMinimaxPlayer(game.board);
			
			if(computerFirst) {
				//computer makes first move
				System.err.println("Computer playing first");
				game.board.makeMove('X', compMove[0], compMove[1]);
				
			}
			
			Scanner input = new Scanner(System.in);
			while(game.board.overallGameStatus == 'n') {
				
				game.board.displayBoard();
				
				//Opponent plays
				System.err.println("It is your turn to play, please enter the board(1-9) you would like to play on followed by"
						+ "the position on that board(1-9) " + inputChar);
				
				oppMove = input.nextLine();
			
				
				System.out.println(oppMove);
				while(game.board.makeMove(inputChar, Character.getNumericValue(oppMove.charAt(0)), Character.getNumericValue(oppMove.charAt(2))) == false ) {
					oppMove = input.nextLine();
				}
				
				System.err.println("\nNow the computer will play. Using minimax to Search for position (1-9) to place a " + compChar);
				// Call minimax function to find best move
				compMove = compPlayer.h_minimaxDecision();
				System.err.println("Computer Move: " + compMove[0] + " " + compMove[1]);
					
				//make move selected by heuristic minimax algorithm
				game.board.makeMove(compChar, compMove[0], compMove[1]);
				
				game.board.displayBoard();				
				
				
				
			}

		}
		
	
	}
}
