import java.util.Scanner;

public class AdvancedTTTGame {
	
	public AdvancedTTTBoard board;
	public char p2Char = ' ';
	
	public AdvancedTTTGame() {
		board = new AdvancedTTTBoard();
		char p2Char = ' ';
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		AdvancedTTTGame game = new AdvancedTTTGame();
		
		//Player 1: human player
		//Player 2: computer player
		
		while(true) {
			//boolean to keep track of who plays first
			boolean p1First = true;
			
			//String to record the board (index 0 of string) and position (index 2 of string) specified by the opponent
			String p1Move = "";
			
			//integer array to record board and position of computer's move (set to default optimal first move)
			int[] p2Move = {2,2};
			
			//Boolean to check if a move is legal before playing it
			boolean isMoveAllowed;
			
			//Set up input stream using System in
			Scanner playerInput = new Scanner(System.in);
			
			//Print Basic Introduction messages
			System.err.println("===============================================================================================================");
			System.err.println("Initialized a new game of Tic Tac Toe! (Note that X always plays first)");
			System.err.println("Please choose if you want to play \"X\" or \"O\" by typing the desired character:");

			char p1Char = playerInput.next().charAt(0); //Read the first character of any input string
			boolean legalInput = false; //Boolean to keep track of input legality
			
			if((p1Char == 'o') || (p1Char == 'O') || (p1Char == 'x') || (p1Char == 'X')) {
				legalInput = true;
			}
			
			//Handle illegal inputs
			while(!legalInput) {
				System.err.println("Illegal Input. Please try again.");
				p1Char = playerInput.next().charAt(0); //Read the first character of any input string
				if((p1Char == 'o') || (p1Char == 'O') || (p1Char == 'x') || (p1Char == 'X')) {
					legalInput = true;
				}
			}
			
			p1Char = Character.toUpperCase(p1Char); //Make input upper case in case input was lower case		
			
			if(p1Char == 'O') {
				p1First = false;
				game.p2Char = 'X';
			}
			
			else if (p1Char == 'X') {
				p1First = true;
				game.p2Char = 'O';
			}
			
			else {
				System.err.println("Oh god this should never happen please help"); //lol
				game.p2Char = 'N';
			}
			
			
			//Instantiate Minimax computer player
			//AdvancedMinimaxPlayer compPlayer = new AdvancedMinimaxPlayer(game);
			
			//use random computer player for testing game functionality
			AdvancedRandomPlayer compPlayer = new AdvancedRandomPlayer(game.board);
			
			if(!p1First) {
				//computer makes first move
				System.err.println("Computer playing first");
				game.board.moveResult(game.p2Char, p2Move[0], p2Move[1]);
				
			}
			
			Scanner input = new Scanner(System.in);
			while(game.board.overallGameStatus == 'n') {
				
				game.board.displayBoard();
				
				//Human player plays
				int boardIndex;
				int boardPos;
				
				//check if entered move is valid
				do {
					System.err.println("It is your turn to play, please enter the board(1-9) you would like to play on followed by"
							+ " the position on that board(1-9) " + p1Char);
					p1Move = input.nextLine();
					//TODO better input cleaning
					boardIndex = Character.getNumericValue(p1Move.charAt(0));
					boardPos = Character.getNumericValue(p1Move.charAt(2));
					
				} while(!game.board.isMoveAllowed( boardIndex, boardPos));
				
				game.board.moveResult(p1Char, boardIndex, boardPos);				
				
				
				/*System.err.println("\nNow the computer will play. Using minimax to Search for position (1-9) to place a " + game.compChar);
				// Call minimax function to find best move
				compMove = compPlayer.h_minimaxDecision();
				System.err.println("Computer Move: " + compMove[0] + " " + compMove[1]);
					
				//make move selected by heuristic minimax algorithm
				game.board.moveResult(game.compChar, compMove[0], compMove[1]); */
				
				//Make random move
				int [] computerMove = compPlayer.randomMove();
				game.board.moveResult(game.p2Char, computerMove[0], computerMove[1]);
				//System.err.print("Computer player just played");
				
				game.board.displayBoard();				
				
				
				
			}

		}
		
	
	}
}
